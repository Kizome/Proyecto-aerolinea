package classes.company.flight;
import interfaces.IAirCompany;
import classes.company.airport.Airport;
import classes.company.Company;
import classes.company.seat.Seat;
import classes.company.plane.Plane;
import classes.company.plane.AirBusA320;
import classes.company.plane.Boing787;
import classes.company.flight.Flight;
import classes.company.people.*;
import classes.company.ticket.Ticket;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.ArrayList;
import java.time.format.*;
import java.time.*;
import static java.util.Comparator.*;
/**
*@author: Omar Jesús Muñoz Cuenca
*/

public class Flight{
//Atributos
    /**
    * Variable que guarda el nombre del aeropuerto de destino
    */
    public Airport destinationAirport;
    /**
    * Variable que guarda el nombre del aeropuerto de origen
    */
    public Airport originAirport;
    /**
    * Variable que almacena el nombre de la compañia
    */
    public Company mycompany;
    /**
    * Variable que almacena el avión usado en el vuelo
    */
    public Plane myPlane;
    /**
    * Almacena la duración del vuelo en minutos
    */
    public int durationFlight;
    /**
    * Variable que almacena la fecha y hora de salida del vuelo
    */
    public Calendar flightDate= Calendar.getInstance();
    /**
    * Almacena, dependiento de la salida del vuelo, la llegada del mismo
    */
    public Calendar flightArrived= Calendar.getInstance();
    /**
    * Array adimensional para guardar la tripulación de cada avión.
    */
    public Tripulation[] myTripulation;
    /**
    * Array dimensionado a 2, de pilotos. Guardará los dos pilotos de cada avión.
    */
    public Pilot[] myPilot= new Pilot[2];
    /**
    * Guarda el número de asientos normales del vuelo.
    */
    public int normalSeats;
    /**
    * Guarda el número de asientos vips del vuelo.
    */
    public int vipSeats;
    /**
    * Almacena el precio del asiento básico.
    */
    public int priceSeat;
    /**
    * Atributo para almacenar la hora.
    */
    //public StringBuilder hour=new StringBuilder();
    /**
    * Variable que guarda la ide del vuelo, generada con el acronimo de la compañia, la hora de salida y el acronimo del
    * aeropuerto de destino.
    */
    public String id;
    /**
    * Arraylist de arraylist donde se guardarán todos los asientos de vuelo.
    */
    public ArrayList<ArrayList<Seat>> seatsFlight= new ArrayList<ArrayList<Seat>>();

    /**
    * Constructor con 7 parametros para instanciar un vuelo
    * @param dest Aeropuerto de Destino
    * @param ori Aeropuerto de origen
    * @param pla Tipo de avion del vuelo
    * @param p Uno de los pilotos del vuelo
    * @param price Precio del asiento.
    * @param dura Duracion del vuelo.
    * @param cal Recibe la fecha y hora de salida del vuelo
    * @throws Exception Captura de algún posible error que lancen los métodos requeridos en el constructor.
    */
    public Flight(Airport dest, Airport ori, Plane pla,
    Pilot p, int price, int dura, Calendar cal)throws Exception{
        pla.setFlight(this);
        this.destinationAirport=dest;
        this.originAirport=ori;
        this.myPlane=pla;
        this.seatsFlight= pla.planesSeats;
        this.generatePrice(price);
        this.durationFlight=dura;
        this.id=generateFlightName(Company.ACRONYM, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), dest.acronym);
        this.flightDate=cal;
        this.addPilot(p);
        this.generateTripulation(pla);
        generateArrive(cal);
    }

    /**
    * Obtencion de asientos libres de un vuelo.
    * @return Devuelve un arraylist simple con los asientos disponibles del vuelo
    * @throws Exception Posible error lanzado a la hora de buscar un asiento libre.
    */
    public ArrayList<Seat> getFreeSeats()throws Exception{
      ArrayList<Seat> myFreeSeats = new ArrayList<Seat>();

      if (!this.seatsFlight.isEmpty()) {
        for (int i=0 ;i<this.seatsFlight.size() ;i++ ) {
          for (int z=0;z<this.seatsFlight.get(i).size() ;z++ ) {
            if (this.seatsFlight.get(i).get(z).available==true) {
              myFreeSeats.add(this.seatsFlight.get(i).get(z));
            }

          }
        }
      }else{
        throw new Exception("0 resultados encontrados");
      }

      return myFreeSeats;
    }
    /**
    * Genera la fecha y hora de llegada del vuelo en función de la fecha de salida y la duración del mismo.
    * @param ca Recibe la fecha de salida del vuelo y lo almacena en otra variable
    */
    public void generateArrive(Calendar ca){
      this.flightArrived=ca;
      this.flightArrived.add(Calendar.MINUTE, this.durationFlight);
    }

    /**
    * Metodo que retira un empleado de la tripulación si al compararlo son objetos identicos
    * @param e Recibe un objeto de tipo empleado
    */
    public void removeEmployee(Employee e){
      boolean removed=false;
      for (int i=0;i<this.myTripulation.length&&!removed ;i++ ) {
        if (this.myTripulation[i]==e) {
          this.myTripulation[i]=null;
          removed=true;
        }
      }
    }

    /**
    * Metodo que vuelve un asiento valido si ha sido descambiado el ticket, siempre y cuando sea identico a algun asiento del vuelo.
    * @param a Recibe un asiento
    */
    public void addSeat(Seat a){
      boolean added=false;
      for(int i=1 ;i<(this.seatsFlight.size())+1&&!added;i++ ) {
          for (int z=0;z<this.seatsFlight.get(i).size()&&!added;z++ ) {
              if(this.seatsFlight.get(i).get(z)==a) {

                  this.seatsFlight.get(i).get(z).available=true;
                  added=true;


              }
          }

      }
    }

    /**
    * Guarda todos los asientos en un StringBuilder de forma consecutiva
    * @return Devuelve un StrinBuilder convertido a String con los asientos almacenados
    */
    public String listSeats(){
         StringBuilder mySeats=new StringBuilder();

             for(int i=0;i<this.seatsFlight.size();i++){
                     mySeats.append(this.seatsFlight.get(i)+"\n");
             }
         return mySeats.toString();
     }

    /**
    * Metodo para obtener un arraylist de asientos comprados
    * @return devuelve un arraylist sencillo de asientos no disponibles almacenados de forma consecutiva
    */
    public ArrayList<Seat> getBuyedSeats(){
      ArrayList<Seat> myBuyedSeats = new ArrayList<Seat>();

      for (int i=0 ;i<this.seatsFlight.size() ;i++ ) {
        for (int z=0;z<this.seatsFlight.get(i).size() ;z++ ) {
          if (this.seatsFlight.get(i).get(z).available==false) {
            myBuyedSeats.add(this.seatsFlight.get(i).get(z));
          }
        }
      }
      return myBuyedSeats;
    }

    /**
    * Metodo que calcula el dinero obtenido de la venta de asientos normales
    * @param n Recibe un Arraylist de asientos, normales y vip, del vuelo actual
    * @return Devuelve un entero con la suma del coste de los asientos normales
    */
    public int getnormalSeats(ArrayList<Seat> n){
      int normal=0;
      for (int i=0;i<n.size() ;i++ ) {

        if (n.get(i).vip==false) {

          normal=(int)normal+(int)n.get(i).price;

        }
      }
      return normal;
    }

    /**
    * Metodo que calcula el dinero obtenido de la venta de asientos vips
    * @param v Recibe un Arraylist de asientos, normales y vip, del vuelo actual
    * @return Devuelve un entero con la suma del coste de los asientos vips
    */
    public int getvipSeats(ArrayList<Seat> v){
      int vipsea=0;
      for (int i=0;i<v.size() ;i++ ) {

        if (v.get(i).vip==true) {

          vipsea=(int)vipsea+(int)v.get(i).price;

        }
      }
      return vipsea;
    }

    /**
    * Calcula la rentabilidad del vuelo en %
    * @return Devuelve un StringBuilder convertido a String con un % con un maximo de 2 decimales
    */
    public String myProfitability(){
      StringBuilder defProfitability=new StringBuilder();
      float profitability;
      int flightCost;
      int flightSeatCost;
      int vseats=this.getvipSeats(this.getBuyedSeats());
      int nseats=this.getnormalSeats(this.getBuyedSeats());

      flightSeatCost=vseats+nseats;
      flightCost=this.myPlane.oilConsume*this.durationFlight;
      profitability=(float)(((flightSeatCost*1.0)/(flightCost*1.0))*100.0);
      defProfitability.append(decimalNumber((double)profitability, 2));
      defProfitability.append("%");
      return defProfitability.toString();
    }

    /**
    * Metodo complementario a myProfitability que reduce los decimales del número obtenido a 2.
    * @param number Recibe un numero con diversos decimales
    * @param digits Recibe un numero entero que especificara el numero de decimales que se desean obtener
    * @return Devuelve el numero decimal recibido pero reducido a parte entera con dos decimales
    */
    private double decimalNumber(double number, int digits) {
       double result;
       result = number * Math.pow(10, digits);
       result = Math.round(result);
       result = result/Math.pow(10, digits);
       return result;
   }

    /**
    * Compra un asiento disponible del vuelo actual.
    * @param s Recibe un asiento que será comparado con el arraylist de asientos para convertir su atributo "available" en false
    */
    public void buyTicket(Seat s){
      boolean deleted=false;

        for(int i=0 ;i<this.seatsFlight.size()&&!deleted;i++ ) {
            for (int z=0;z<this.seatsFlight.get(i).size()&&!deleted;z++ ) {
                if(this.seatsFlight.get(i).get(z)==s) {
                    this.seatsFlight.get(i).get(z).available=false;
                    deleted=true;
                }
            }
        }
    }

    /**
    * Método utilizado para establecer el precio de los asientos normales y de los vips un 20% mas caros
    * @param pri Recibe un entero con el precio del asiento normal
    */
    public void generatePrice(int pri){
      for (int i=0 ;i<this.seatsFlight.size() ;i++ ) {
        for (int z=0;z<this.seatsFlight.get(i).size() ;z++ ) {
          if (this.seatsFlight.get(i).get(z).vip==true) {
            this.seatsFlight.get(i).get(z).price=pri+((pri*20)/100);
          }else{
            this.seatsFlight.get(i).get(z).price=pri;
          }
        }
      }
    }

    /**
    * Metodo que recibe un avion y dimensiona el array de tripulación en función de la capacidad del avión
    * @param p Recibe un avión para poder extraer la capacidad del mismo
    */
    public void generateTripulation(Plane p){
      this.myTripulation= new Tripulation[(int)Math.ceil((p.capacity)*0.2)];
    }

    /**
    * Metodo utilizado para añadir un miembro de la tripulación al array de tripulación del vuelo siempre que encuentre un hueco vacío
    * @param t Recibe un objeto de tipo Tripulation
    * @throws Exception Posible lanzamiento de excepción del metodo
    */
    public void addTripulation(Tripulation t)throws Exception{
      boolean added=false;
      for (int i=0;i<this.myTripulation.length&&!added;i++ ) {
        if (this.myTripulation[i]==null) {
          this.myTripulation[i]=t;
          added=true;
        }else{
          throw new Exception("Tripulacion llena.");
        }
      }
    }

    /**
    * Metodo para añadir un piloto al array de pilotos siempre que haya un hueco disponible
    * @param p Recibe un objeto de tipo Pilot
    * @throws Exception Posible lanzamiento de error por tener todo el array de pilotos lleno
    */
    public void addPilot(Pilot p)throws Exception{
      boolean added=false;
      for (int i=0;i<this.myPilot.length&&!added;i++ ) {
        if (this.myPilot[i]==null) {
          this.myPilot[i]=p;
          added=true;
        }else{
          throw new Exception("Pilotos completos.");
        }
      }
    }

    /**
    * Metodo empleado en la generación del ID del vuelo juntando en un StringBuilder, las iniciales de la compañia
    * la hora, los minutos y las iniciales del aeropuerto de destino
    * @param a Recibe un String con las iniciales de la compañia
    * @param b Recibe un entero con las horas
    * @param c Recibe un entero con los minutos
    * @param d Recibe un String con el acronimo del aeropuerto destino
    * @return Devuelve un stringbuilder convertido en string con el id del vuelo
    */
    public String generateFlightName(String a, int b, int c, String d){
      StringBuilder newname=new StringBuilder();
      newname.append(a);
      if ((b-10)<0) {
        newname.append(0);
        newname.append(b);
      }else{
        newname.append(b);
      }
      if ((c-10)<0) {
        newname.append(0);
        newname.append(c);
      }else{
        newname.append(c);
      }
      newname.append(d);
      return newname.toString();
    }

    /**
    * Sobrescritura del método toString
    * @return devuelve una frase con el aeropuerto origen y destino
    */
    @Override
    public String toString(){
      return "Vuelo con salida desde "+this.originAirport.name+", con destino "+this.destinationAirport.name;
    }

}
