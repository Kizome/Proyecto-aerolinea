package classes.company;

//Importaciones de interface y clases de las que dependemos
import interfaces.IAirCompany;
import classes.company.airport.Airport;
import classes.company.plane.Plane;
import classes.company.flight.Flight;
import classes.company.people.Employee;
import classes.company.people.Pilot;
import classes.company.people.Tripulation;
import classes.company.people.Client;
import classes.company.ticket.Ticket;
import classes.company.seat.Seat;
import classes.myCalendar;


//Importacion de utilidades
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Collections;
import java.util.Comparator;

public class Company implements IAirCompany{


    //Propiedad flag singleton
    private static Company myCompany;

    //Constantes
    public final static String NAME="Iberia";
    public final static String ACRONYM="IBE";
    public final static GregorianCalendar fundationDate=new GregorianCalendar(1998,8,19);

    //Propiedades de la clase
    public static String ourCeo;
    public static ArrayList<Employee> ourEmployees;
    public static ArrayList<Plane> ourPlanes;
    public static ArrayList<Flight> ourFlights;
    public static ArrayList<Client> ourClients;
    public static ArrayList<Ticket> ourTickets;



    //Bloque static para inicializar propiedades static
    static{
        ourCeo="Carlos Serrano";
        ourEmployees=new ArrayList<>();
        ourPlanes= new ArrayList<>();
        ourFlights= new ArrayList<>();
        ourClients = new ArrayList<>();
        ourTickets = new ArrayList<>();
    }

    //Constructor privado para impedir instanciacion no controlada
    private Company(){

    }

    /**Metodo static constructor de la clase si previamente no ha sido
    *construida, de modo que o bien instanciamos la clase(Primera llamada del
    metodo), o bien obtenemos de resultado la primera instancia.
    */
    public static Company getInstance() {
        if (myCompany == null) {
            myCompany = new Company();
        }
        return myCompany;
    }




    /*TESTEO
    */
    public String listTickets(){
        //Collections.sort(this.ourTickets,new Comparator());
        StringBuilder myString=new StringBuilder();

        for(int i=0;i<this.ourTickets.size();i++){
            myString.append(this.ourTickets.get(i));//EL PRECIO
        }
        return myString.toString();
    }




    /**Metodo que comprueba si alguno de los vuelos en el ArrayList<Flight> de
    *esta clase es igual o mayor a la hora simulada actual, de ser así, llama
    *a el metodo coprobeTickets ,posteriormente elimina el vuelo y usa recursividad.
    */
    public void comprobeFlights(Calendar simulatedDate){
        Calendar mc=Calendar.getInstance();
        mc.set(simulatedDate.get(Calendar.YEAR),simulatedDate.get(Calendar.MONTH),(simulatedDate.get(Calendar.DAY_OF_MONTH)-1));

        for(int i=0;i<this.ourFlights.size();i++){
            if(this.ourFlights.get(i).flightDate.before(mc)){
                this.comprobeTickets(this.ourFlights.get(i));
                this.ourFlights.remove(i);
                this.comprobeFlights(simulatedDate);
            }
        }
    }

    /**Metodo apoyo del metodo comprobeFlights, su funcion consiste en buscar
    *en el ArrayList<Ticket> de esta clase los tickets cuyo vuelo sea el pasado
    *por referencia en esta funcion. Si hay match elimina el ticket y ademas usa
    *recursividad para no alterar la relacion i-size(). Si no hay match,
    *llama al metodo comprobesClientsTicket.
    */
    public void comprobeTickets(Flight f){
        for(int i=0;i<this.ourTickets.size();i++){
            if(this.ourTickets.get(i).getFlight()==f){
                this.ourTickets.remove(this.ourTickets.get(i));
                this.comprobeTickets(f);
            }else{
                 this.comprobesClientsTicket(f);
            }
        }
    }

    /**Metodo apoyo del metodo comprobeTickets el cual busca en el ArrayList
    *<Client> de esta clase y llama al metodo haveTicketOfThisFlight de cada
    posicion.
    */
    public void comprobesClientsTicket(Flight f){
        for(int i=0;i<this.ourClients.size();i++){
            this.ourClients.get(i).haveTicketOfThisFlight(f);
        }
    }




    //Inserta el empleado al ArrayList si no se encuentra previamente
    public void hireEmployee(Employee e)throws Exception{
        if(this.searchEmployee(e)==false){
           this.ourEmployees.add(e);
        }else{
            throw new Exception("Ya existe ese empleado");
        }
    }

    /**Metodo que busca en el ArrayList<Employee> y elimina el empleado pasado
    *por referencia, sino lo encuentra lanza una excepcion.
    @param e: Empleado a eliminar.
    */
    public void fireEmployee(Employee e)throws Exception{
        boolean match=false;

        for(int i=0;i<this.ourEmployees.size()&&!match;i++){
            if(this.ourEmployees.get(i)!=null){
               if(this.ourEmployees.get(i)==e){
                    this.ourEmployees.remove(i);//borro el empleado? recolector?
                    match=true;
                }
            }else{
                throw new Exception("No existe ese empleado");
            }
        }
    }

    /*Metodo que crea un objeto de tipo StrinBuilder y concatena cada posicion
    *del ArrayList<Employee> ourEmployees de esta clase, si esta vacio, imprime un mensaje diferente.
    *@return String Devuelve una lista de los empleados, si esta vacia imprime un mensaje diferente.
    */
    public String listEmployee(){
        StringBuilder listOfEmployees = new StringBuilder();

        if(!this.ourEmployees.isEmpty()){
            for(int i=0;i<this.ourEmployees.size();i++){
                    listOfEmployees.append(this.ourEmployees.get(i)+"\n\n");
            }
        }else{
            listOfEmployees.append("Lista de empleados vacia");
        }
        return listOfEmployees.toString();
    }

    /**Metodo que busca un empleado en concreto.
    *@param e: Recibe un objeto tipo Employee (empleado a buscar)
    *@return boolean: true si se encuentra en el ArrayList<Employee> de esta clase, false sino se encuentra.
    */
    public boolean searchEmployee(Employee e){
        boolean match=false;

        for(int i=0;i<this.ourEmployees.size()&&!match;i++){
                if(this.ourEmployees.get(i)==e){
                    match=true;
                }
        }
        return match;
    }

    /**Metodo que recorre el ArrayList<Client> de esta clase y suma el total
    *de los atributos de Client, salary.
    *@return int total: Total del sueldo de todos los empleados.
    */
    public int totalSalary(){
        int total=0;

         for(int i=0;i<ourEmployees.size();i++){
                total=total+this.ourEmployees.get(i).getSalary();
        }
        return total;
    }

    /**Metodo que inserta un Objeto tipo Plane en el ArrayList<Plane> de esta
    *clase, si ese mismo bjeto, estaba en el ArrayList<Plane> de esta clase
    *lanza excepcion.
    *@throws:Dependiendo de que el objeto sea null o bien si el objeto se
    *encuentra en el ArrayList<Plane> de esta clase, lanza una excepcion propia
    *de cada posibilidad.
    */
    public void addPlane(Plane p)throws Exception{
        if(p!=null){
            if(!this.searchPlane(p)){
                this.ourPlanes.add(p);
            }else{
                throw new Exception("Ya existe ese avion");
            }
        }else{
            throw new Exception("Avion nulo");
        }
    }

    public String listPlane(){
        StringBuilder listOfPlanes = new StringBuilder();

        if(!this.ourPlanes.isEmpty()){
            for(int i=0;i<this.ourPlanes.size();i++){
                listOfPlanes.append(this.ourPlanes.get(i)+"\n");
            }
        }else{
            listOfPlanes.append("No hay aviones en el hangar");
        }
        return listOfPlanes.toString();
    }


    public void removePlane(Plane p)throws Exception{
        boolean match=false;

        if(p!=null){
            for(int i=0;i<this.ourPlanes.size()&&!match;i++){
                if(this.ourPlanes.get(i)!=null){
                   if(this.ourPlanes.get(i)==p){
                        this.ourPlanes.remove(i);//borro el avion? recolector?
                        match=true;
                    }
                }else{
                    throw new Exception("No existe ese avion");
                }
            }
        }else{
            throw new Exception("Error: Intento de inserccion de objeto(Plane) nulo");
        }
    }


    public boolean searchPlane(Plane p)throws Exception{
        boolean match=false;

        if(p!=null){
            for(int i=0;i<ourPlanes.size()&&!match;i++){
                    if(this.ourPlanes.get(i)==p){
                        match=true;
                    }
            }
        }else{
            throw new Exception("Error: Intento de buscar objeto(Plane) nulo");
        }
    return match;
    }


    /**Metodo responsable de añadir un vuelo y de añadir los
    *Billetes de dicho vuelo a el general de Billetes de la compañia.
    @param f:Vuelo a añadir
    */
    public void addFlight(Flight f){
        this.ourFlights.add(f);
        this.addFlightTickets(f);
    }

    public void addFlightTickets(Flight f){

        for(int i=0;i<f.seatsFlight.size();i++){
            for(int z=0;z<f.seatsFlight.get(i).size();z++){
                this.ourTickets.add(new Ticket(f,f.seatsFlight.get(i).get(z)));
            }
        }
    }




    public String listFlight(){
        StringBuilder listOfFlights = new StringBuilder();

        if(!this.ourFlights.isEmpty()){
            for(int i=0;i<this.ourFlights.size();i++){
                    listOfFlights.append(this.ourFlights.get(i)+"\n");
            }
        }else{
            listOfFlights.append("No hay ningun vuelo disponible");
        }
        return listOfFlights.toString();
    }


    /*No controlo horas

    */
    /*public Flight searchFlight(String idFlight)throws Exception{
        boolean match=false;
        Flight myFlight=null;

        for(int i=0;i<this.ourFlights.size()&&!match;i++){
            if(this.ourFlights.get(i).id.equals(idFlight)){
                myFlight=this.ourFlights.get(i);
                match=true;
            }
        }
        if(myFlight!=null){
            return myFlight;
        }else{
            throw new Exception("Vuelo no encontrado");
        }
    }*/

    public ArrayList<Flight> searchFlights(String origin, String destination)throws Exception{
        ArrayList<Flight> tmp=new ArrayList<Flight>();

        if(!origin.equals(destination)){
            if(!this.ourFlights.isEmpty()){
                for(int i=0;i<this.ourFlights.size();i++){
                        if(this.ourFlights.get(i).originAirport.name.equals(origin)&&
                        this.ourFlights.get(i).destinationAirport.name.equals(destination)||
                        this.ourFlights.get(i).originAirport.acronym.equals(origin)&&
                        this.ourFlights.get(i).destinationAirport.acronym.equals(destination)){
                            tmp.add(this.ourFlights.get(i));
                        }
                }
            }else{
                throw new Exception("No hay vuelos");
            }
        }else{
            throw new Exception("Destino y Origen identicos");
        }

        if(tmp.isEmpty()){
            throw new Exception("0 resultados disponibles");
        }

        return tmp;
    }

    //si elimino avion que pasa con sus tickets en el arrayList?
    public void removeFlight(Flight f)throws Exception{
        boolean match=false;

        for(int i=0;i<this.ourFlights.size()&&!match;i++){
            if(this.ourFlights.get(i)!=null){
               if(this.ourFlights.get(i)==f){
                    this.ourFlights.remove(i);//borro el avion? recolector?
                    match=true;
                }
            }else{
                throw new Exception("No existe ese avion");
            }
        }
    }

    /*Ayade el ticket al cliente(con la condicion del dia)
    *Lo retira de los disponibles en el vuelo
    *Lo retira de los disponibles en el global
    */
    public String buyTicket(String dni,Flight f, Seat s)throws Exception{
        Ticket theSameTicket=this.searchCompanyTicket(f,s);
        this.removeCompanyTicket(theSameTicket);////////////////////////DIA?
        Client myClient=this.searchObjectClient(dni);
        myClient.addTicket(theSameTicket);
        f.buyTicket(s);

        return theSameTicket.getId();//IMPOSIBLE YA QUE EL TICKET NO TIENE ID
    }

    public Ticket searchCompanyTicket(Flight f, Seat s)throws Exception{
        Ticket myTicket=null;
        boolean match=false;

        for(int i=0;i<this.ourTickets.size()&&!match;i++){
            if(this.ourTickets.get(i).getFlight()==f){
                if(this.ourTickets.get(i).getSeat()==s){
                    myTicket=this.ourTickets.get(i);
                    match=true;
                }
            }
        }
        if(myTicket==null){
             throw new Exception("Elija una opcion valida");
        }else{
           return myTicket;
        }
    }

    public void removeCompanyTicket(Ticket t)throws Exception{
        boolean deleted=false;
        Calendar mc=Calendar.getInstance();
        mc.set(myCalendar.simulatedDate.get(Calendar.YEAR),myCalendar.simulatedDate.get(Calendar.MONTH),(myCalendar.simulatedDate.get(Calendar.DAY_OF_MONTH)-1));

        for(int i=0;i<this.ourTickets.size()&&!deleted;i++){
            if(this.ourTickets.get(i)==t){
                if(t.getDate().after(mc)){
                    this.ourTickets.remove(i);
                    deleted=true;
                }else{
                    throw new Exception("Es imposible devolver el ticket el mismo día del vuelo, lo sentimos.");
                }
            }
        }
    }


    /*Elimina el ticket del cliente(con la condicion del dia)
    *Lo vuelve disponible en el vuelo
    *Lo vuelve disponible en el global
    */
    public void removeTicket(String dni, String ticketId)throws Exception{
        String myDni=dni.toUpperCase();
        String myTicketId=ticketId.toUpperCase();
        Ticket theSameTicket=this.searchTicket(myDni,myTicketId);
        boolean user=false;
        boolean ticket=false;

        for(int i=0;i<this.ourClients.size()&&!user;i++){
            if(this.ourClients.get(i).getDni().equals(dni)){
                if(theSameTicket.myFlight.flightDate.get(Calendar.DATE)>myCalendar.simulatedDate.get(Calendar.DATE)){
                    user=true;
                    ticket=this.ourClients.get(i).removeTicket(ticketId);//si la fecha actual es superior a un dia de antelacion
                    theSameTicket.myFlight.addSeat(theSameTicket.mySeat);
                    this.ourTickets.add(theSameTicket);//Volver a ayadirlo al global*/ no se ayade en el mismo hueco, necesita metodo intermedio
                }else{
                    throw new Exception("Solo se aceptan devoluciones con mas de un dia de antelacion sobre la salida del vuelo, lo sentimos");
                }
            }
        }

            if(user==true&&ticket==false){
                throw new Exception("No se encontro su ticket");
            }else if(user==false){
                throw new Exception("Usuario desconocido");
            }
        }


    public Ticket searchTicket(String dni, String ticketId)throws Exception{
        boolean user=false;
        Ticket myTicket=null;

        for(int i=0;i<this.ourClients.size()&&!user;i++){
            if(this.ourClients.get(i).getDni().equals(dni)){
                try{
                    myTicket=this.ourClients.get(i).searchTicket(ticketId);
                    user=true;
                }catch(Exception e){
                    throw e;
                }
            }
        }
        if(user==true&&myTicket==null){
            throw new Exception("No se encontro su ticket, asegurese que introduce correctamente su identificador");
        }else if(user==false){
            throw new Exception("Usuario desconocido");
        }
        return myTicket;
    }



    public void addClient(Client c)throws Exception{
        if(c!=null){
                this.ourClients.add(c);
        }
    }

    public String clientsList(){
        StringBuilder listOfClients = new StringBuilder();

        if(this.ourClients.isEmpty()==false){
            for(int i=0;i<this.ourClients.size();i++){
                    listOfClients.append(this.ourClients.get(i)+"\n");
            }
        }else{
            listOfClients.append("Lista de empleados vacia");
        }
        return listOfClients.toString();
    }

    public boolean searchClient(String dni){
        boolean match=false;

        for(int i=0;i<this.ourClients.size()&&!match;i++){
                if(this.ourClients.get(i).getDni().equals(dni)){
                    match=true;
                }
        }
        return match;
    }


    public Client searchObjectClient(String dni){
        boolean match=false;
        Client myClient=null;

        for(int i=0;i<this.ourClients.size()&&!match;i++){
                if(this.ourClients.get(i).getDni().equals(dni)){
                    match=true;
                    myClient=this.ourClients.get(i);
                }
        }
        return myClient;
    }

    public void removeClient(Client c)throws Exception{
        boolean match=false;

        for(int i=0;i<this.ourClients.size()&&!match;i++){
            if(this.ourClients.get(i)!=null){
               if(this.ourClients.get(i)==c){
                    this.ourClients.remove(i);//borro el avion? recolector?
                    match=true;
                }
            }else{
                throw new Exception("No existe ese avion");
            }
        }
    }

    public ArrayList<Seat> getFreeSeatFromFlight(Flight f)throws Exception{
        return f.getFreeSeats();
    }

    public String flightsProfitablity(String flightId)throws Exception{
        String profitability=null;

        if(this.ourFlights.isEmpty()==false){
            System.out.println("hola");
            for(int i=0;i<this.ourFlights.size();i++){
                System.out.println("hola");
                if(this.ourFlights.get(i).id.equals(flightId)){
                    System.out.println("hola");
                    profitability=this.ourFlights.get(i).myProfitability();
                }
            }
        }else{
            throw new Exception("No hay vuelos disponibles");
        }
        return profitability;
    }






    @Override
    public String toString(){
        return "String compania"+this.ourCeo;
    }

    /*@Override
    public int compareTo(Calendar anotherCalendar){
        return this.Calendar - anotherCalendar;
    }*/
}
