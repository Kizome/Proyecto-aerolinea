package classes.company;

//Importaciones de interface y clases del programa
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

/**Clase desde la que se controla la gestion de vuelos, clientes, Empleados
Aviones y Billetes entre otras cosas.
@author Rafa Amo Moral
*/

public class Company implements IAirCompany{
    
    //Propiedad flag singleton
    private static Company myCompany;
    
    //Constantes
    public final static String NAME;
    public final static String ACRONYM;
    public final static GregorianCalendar fundationDate;
    
    //Propiedades de la clase
    public static String ourCeo;
    public static ArrayList<Employee> ourEmployees;
    public static ArrayList<Plane> ourPlanes;
    public static ArrayList<Flight> ourFlights;
    public static ArrayList<Client> ourClients;
    public static ArrayList<Ticket> ourTickets;
    
    //Bloque static para inicializar propiedades static
    static{
        NAME="Ibeia";
        ACRONYM="IBE";
        fundationDate=new GregorianCalendar(1998,8,19);
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
    *@return Objeto Singletone Company
    */
    public static Company getInstance() {
        if (myCompany == null) {
            myCompany = new Company();
        }
        return myCompany;
    }
   
    /**Metodo que comprueba si alguno de los vuelos en el ArrayList de Flight de 
    *esta clase es igual o mayor a la hora simulada actual, de ser así, 
    *resetea la disponibilidad de los asientos del vuelo, desasigna los 
    *empleados del vuelo, llama a el metodo comprobeTickets(), posteriormente 
    *elimina el vuelo, crea un nuevo vuelo identico al eliminado pero con una 
    *semana de posterioridad y usa recursividad para no romper la relacion i-size().
    *@param simulatedDate con la hora actual simulada.
    *@throws Exception En el metodo de esta clase removeFlight()
    */
    public void comprobeFlights(Calendar simulatedDate)throws Exception{
        Calendar mc=new GregorianCalendar(simulatedDate.get(Calendar.YEAR),
        simulatedDate.get(Calendar.MONTH),(simulatedDate.get(Calendar.DAY_OF_MONTH)));
        
        for(int i=0;i<this.ourFlights.size();i++){
            if(this.ourFlights.get(i).flightDate.before(mc)){
                Flight tmp=this.ourFlights.get(i);
                tmp.resetSeat();
                this.unsignEmployees(this.ourFlights.get(i));
                this.comprobeTickets(this.ourFlights.get(i));
                this.removeFlight(this.ourFlights.get(i));
                //Aqui empieza la creacion del nuevo Vuelo
                tmp.flightDate.add(Calendar.HOUR_OF_DAY,168);
                Flight mf=new Flight(tmp.destinationAirport,tmp.originAirport,
                    tmp.myPlane,tmp.myPilot[0],tmp.priceSeat,
                    tmp.durationFlight,tmp.flightDate,tmp.scales);
                this.addFlight(mf);
                //Recursividad
                this.comprobeFlights(mc);
            }
        }
    }
   
    /**Metodo apoyo del metodo comprobeFlights, su funcion consiste en buscar
    *en el ArrayList de Ticket de esta clase los tickets cuyo vuelo sea el pasado
    *por referencia en esta funcion. Si hay match elimina el ticket y ademas usa
    *recursividad para no alterar la relacion i-size(). Si no hay match, 
    *llama al metodo comprobesClientsTicket.
    *@param f Obeto tipo Flight a comprobar
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
    *de Client de esta clase y llama al metodo haveTicketOfThisFlight de cada
    *posicion.
    *@param f Objeto tipo Flight a comprobar
    */
    public void comprobesClientsTicket(Flight f){
        for(int i=0;i<this.ourClients.size();i++){
            this.ourClients.get(i).haveTicketOfThisFlight(f);
        }
    }
    
    /**Inserta el empleado al ArrayList si no se encuentra previamente
    @param e Objeto tipo Employee a insertar
    */
    public void hireEmployee(Employee e)throws Exception{
        if(e!=null){
            if(this.searchEmployee(e)==false){
                this.ourEmployees.add(e);   
            }else{
            throw new Exception("Ya existe ese empleado");
            } 
        }
    }
    
    /**Metodo que busca en el ArrayList de Employee y elimina el empleado pasado
    *por referencia, sino lo encuentra lanza una excepcion.
    @param e: Empleado a eliminar.
    */
    public void fireEmployee(Employee e)throws Exception{
        boolean match=false;
        
        for(int i=0;i<this.ourEmployees.size()&&!match;i++){
            if(this.ourEmployees.get(i)==e){
                e.actualflight.removeEmployee(e);
                this.ourEmployees.remove(i);
                match=true;
            } 
        }
        if(!match){
            throw new Exception("Empleado no encontrado");
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
    *@return boolean: true si se encuentra en el ArrayList de Employee de esta clase, false sino se encuentra.
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
    
    /**Pone a null la propiedad de la clase Employee actualflight.
    *@param f: Objeto de la clase flight que representa el vuelo 
    *al que pertenece los empleados a los que se les quiere desasignar el 
    *vuelo.
    */
    public void unsignEmployees(Flight f){
        ArrayList<Employee>employees = f.flightsEmployee();
        
        if(!employees.isEmpty()){
            for(int i=0;i<employees.size();i++){
                employees.get(i).actualflight=null;
            }
        }
    }
    
    /**Metodo que recorre el ArrayList de Client de esta clase y suma el total 
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
    
    /**Metodo que inserta un Objeto tipo Plane en el ArrayList de Plane de esta 
    *clase, si ese mismo bjeto, estaba en el ArrayList de Plane de esta clase
    *lanza excepcion.
    *@throws Exception Dependiendo de que el objeto sea null o bien si el objeto se 
    *encuentra en el ArrayList de Plane de esta clase, lanza una excepcion propia 
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
    
    /**Metodo encargado de crear un String con el contenido del 
    ArrayList de Plane de esta clase, si no hay ninguno, lanza una excepcion 
    *@return String con la lista de aviones.
    */
    public String listPlane()throws Exception{
        StringBuilder listOfPlanes = new StringBuilder();
        
        if(!this.ourPlanes.isEmpty()){
            for(int i=0;i<this.ourPlanes.size();i++){
                listOfPlanes.append(this.ourPlanes.get(i)+"\n");
            } 
        }else{
            throw new Exception("No hay aviones en el hangar");
        } 
        return listOfPlanes.toString();
    }
    
    /**Metodo encargado de eliminar el objeto tipo Plane pasado por referencia
    *@param p Objeto tipo Plane a insertar.
    */
    public void removePlane(Plane p)throws Exception{
        boolean match=false;
        
        if(p!=null){
            for(int i=0;i<this.ourPlanes.size()&&!match;i++){
                if(this.ourPlanes.get(i)==p){
                    p.myFlight.myPlane=null;
                    this.ourPlanes.remove(i);
                    match=true;  
                }
            }
        }else{
            throw new Exception("Error: Intento de inserccion de objeto(Plane) nulo");
        }
    }
    
    /**Metodo el cual sirve para buscar un Objeto plane en especifico.
    *@param p Objeto Plane a buscar.
    *@return Un booleano el que devuelve true si encuentra el avion.
    */
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
    
    
    /**Metodo responsable de añadir el vuelo pasado por referencia a el
    *ArrayList de Ticket de esta clase, posteriormente llama a el metodo de esta 
    *clase, addFlightTickets.
    @param f Vuelo a añadir
    */
    public void addFlight(Flight f){
        if(f!=null){
            this.ourFlights.add(f);
            this.addFlightTickets(f);
        }
    }
    
    /**Metodo de apoyo para el metodo de esta clase addFlight. Este metodo 
    *recibe un vuelo, recorre los asientos del vuelo y por cada uno de ellos,
    *instancia un objeto tipo Ticket.
    *@param f Vuelo del cual se quieren crear sus Tickets
    */
    public void addFlightTickets(Flight f){
        for(int i=0;i<f.seatsFlight.size();i++){
            for(int z=0;z<f.seatsFlight.get(i).size();z++){
                this.ourTickets.add(new Ticket(f,f.seatsFlight.get(i).get(z)));
            }   
        }
    }
    
    /**Metodo destinado a recorrer el ArrayList de Flight de esta clase y añadir
    *a un StringBuilder cada uno de los vuelos
    *@return:Un String formateado con la lista de vuelos
    */
    public String listFlight()throws Exception{
        StringBuilder listOfFlights = new StringBuilder();
        
        if(!this.ourFlights.isEmpty()){
            for(int i=0;i<this.ourFlights.size();i++){
                listOfFlights.append(this.ourFlights.get(i).flightInformation()+"\n");
            }
        }else{
            throw new Exception("No hay ningun vuelo disponible");
        }
        return listOfFlights.toString();
    }
    
    /**Metodo destinado a buscar un vuelo en el ArrayList de Flight de esta clase
    *@param idFlight El id del vuelo por el que lo buscaremos.
    *@return El vuelo si ha sido encontrado.
    *@throws Exception Lanza una excepcion si el vuelo no ha sido encontrado
    */
    public Flight searchFlight(String idFlight)throws Exception{
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
    }
    
    /**Metodo encargado de devolver un ArrayList de Flight de vuelos que coincidan
    *con el nombre de la ciudad o siglas de los aeropuertos origen y destino. 
    *Por otra parte comprueba si siglas o nombre de la ciudad destino concuerdan con
    *el vuelo y ademas llaman al metodo comprobeScales(), para comprobar si en 
    *el vuelo hay alguna escala que lleve al destino.
    *@param origin del nombre del aeropuerto origen.
    *@param destination del nombre del aeropuerto destino.
    *@return ArrayList de Flight con los vuelos encontrados.
    *@throws Exception Si no hay vuelos en la compañia
    *@throws Exception Si Destino y Origen son identicos
    *@throws Exception Si no hay vuelos de ese Origen-Destino
    */
    public ArrayList<Flight> searchFlights(String origin, String destination)throws Exception{
        ArrayList<Flight> tmp=new ArrayList<Flight>();
        
        if(!origin.equals(destination)){
            if(!this.ourFlights.isEmpty()){
                for(int i=0;i<this.ourFlights.size();i++){
                    if(this.ourFlights.get(i).originAirport.city.equals(origin)&&
                        this.ourFlights.get(i).destinationAirport.city.equals(destination)||
                        this.ourFlights.get(i).originAirport.acronym.equals(origin)&&
                        this.ourFlights.get(i).destinationAirport.acronym.equals(destination)||
                        (this.ourFlights.get(i).originAirport.city.equals(origin)||
                         this.ourFlights.get(i).originAirport.acronym.equals(origin))&&
                        comprobeScales(this.ourFlights.get(i),destination)){
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
    
    /**Metodo que adquiere el ArrayList de aeropuertos (simil de escalas) y 
    *comprueba si alguno de ellos es el destino del vuelo
    @param f: Objeto de tipo vueo con el vuelo a comparar.
    @param destination: Objeto de tipo String con el destino del vuelo
    @return match Boolean que devuelve true si hubo coincidencia en el destino
    *con algunas de las escalas.
    */
    public boolean comprobeScales(Flight f,String destination){
        ArrayList<Airport> scales=f.scales;
        boolean match=false;
        
        if(!scales.isEmpty()){
            for(int i=0;i<scales.size()&&!match;i++){
                if(scales.get(i).city.equals(destination)||scales.get(i).acronym.equals(destination)){
                    match=true;
                }
            }
        }
        return match;
    }
    
    /**Busca un vuelo en el ArrayList de Flight de esta clase, si lo encuentra
    *lo elimina del ArrayList general y tambien desasigna a sus empleados.
    *@param f Objeto tipo Fligh a eliminar
    */
    public void removeFlight(Flight f)throws Exception{
        boolean match=false;
        
        for(int i=0;i<this.ourFlights.size()&&!match;i++){
               if(this.ourFlights.get(i)==f){
                   f.myPlane.myFlight=null;
                    this.unsignEmployees(f);
                    this.comprobeTickets(f);
                    this.ourFlights.remove(i);
                    match=true;
                }  
        }
        
        if(!match){
            throw new Exception("Vuelo no econtrado");
        }
    }
    
    /**Metodo encargado de buscar en el ArrayList de Ticket un ticket en concreto
    *eliminarlo del general de la compañia, buscar a un cliente por el dni e
    *agregarle el Ticket buscado anteriormente y finalmente comprar el asiento
    *del vuelo.
    @param dni con el dni del cliente por el cual buscaremos al objeto 
    Cliente. 
    @param f con el vuelo en el que comparemos el asiento.
    @param s Seat que sera el asiento que compraremos.
    @return String con el id del Ticket.
    */
    public String buyTicket(String dni,Flight f, Seat s)throws Exception{
        Ticket theSameTicket=this.searchCompanyTicket(f,s);
        this.removeCompanyTicket(theSameTicket);
        Client myClient=this.searchObjectClient(dni);
        myClient.addTicket(theSameTicket); 
        f.buyTicket(s);
        
        return theSameTicket.getId();
    }
    
    /**Metodo el cual busca un Ticket en especifico del ArrayList Ticket de
    *esta clase por su vuelo y asiento.
    *@param f Objeto tipo Flight, representa el Vuelo del ticket a buscar. 
    *@param s Objeto tipo Seat representa el asiento del ticket a encontrar.
    @return Objeto Ticket.
    @throws Exception si no hay ningun Ticket relacionado con un vuelo y asiento
    *en especifico
    */
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
    
    
    /**Metodo el cual busca en el ArrayList de Ticket de esta clase y elimina
    *un Ticket en concreto.
    *@param t Objeto tipo Ticket a eliminar.
    */
    public void removeCompanyTicket(Ticket t){
        boolean deleted=false;
        
        for(int i=0;i<this.ourTickets.size()&&!deleted;i++){
            if(this.ourTickets.get(i)==t){
                    this.ourTickets.remove(i);
                    deleted=true;
            }
        }
    }
    
    /**En primer lugar, pasa el String a mayuscula de modo que independietemente
    *de la letra (tanto si es mayuscula como miniscula lo aceptaria), por otro
    *lado obtengo el Objeto Ticket por el id del mismo. Luego comparando la 
    *hora actual simulada y el dia de salida del billete, y si el dia de salida 
    *del billete es inferior a minimo un dia lo elimina sino lanzará una 
    *excepcion
    *@param dni Dni del cliente para eliminarlo de su cuenta y para obtenerlo del
    ArrayList de Ticket del general.
    *@param ticketId identificador del ticket a buscar.
    */
    public void removeTicket(String dni, String ticketId)throws Exception{
        String myDni=dni.toUpperCase();
        String myTicketId=ticketId.toUpperCase();
        Ticket theSameTicket=this.searchTicket(myDni,myTicketId);
        boolean user=false;
        boolean ticket=false;
        GregorianCalendar mc=new GregorianCalendar(myCalendar.simulatedDate.get(Calendar.YEAR), myCalendar.simulatedDate.get(Calendar.MONTH),myCalendar.simulatedDate.get(Calendar.DAY_OF_MONTH)+1);
        
        for(int i=0;i<this.ourClients.size()&&!user;i++){
            if(this.ourClients.get(i).getDni().equals(dni)){
                user=true;
                if(theSameTicket.myFlight.flightDate.after(mc)){
                    ticket=this.ourClients.get(i).removeTicket(ticketId);
                    theSameTicket.myFlight.addSeat(theSameTicket.mySeat);
                    this.ourTickets.add(theSameTicket);
                }
            }    
        }
        
            if(user==true&&ticket==false){
                throw new Exception("No se pueden devolver Tickets el mismo dia del vuelo");
            }else if(user==false){
                throw new Exception("Usuario desconocido");
            }
        }
    
    /**Este metodo busca un Cliente por el dni y una vez encontrado busca en su 
    *ArrayList de Ticket, si se encuentra lo introduce en un Ticket tmp.
    *@param dni Dni del cliente a buscar.
    *@param ticketId id del ticket a buscar
    @return Ticket Objeto Ticket a devolver
    */
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
    
    
    /**Inserta un cliente, si no es nulo.
    *@param c Cliente a insertar
    */
    public void addClient(Client c){
        if(c!=null){
            this.ourClients.add(c);
        } 
    }
    
    /**Crea un String con cada elemento del ArrayList de Client de esta clase,
    *si no hay ningun elemento devuelve un String con un mensaje que dice Lista
    *de empleados vacia
    *@return String con la lista de clientes(si hay)
    */
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
    
    /**Busca en el ArrayList de Client de esta clase un cliente por el dni
    *@param dni con el dni del cliente a buscar
    *@return boolean true si se ha encontrado al cliente
    */
    public boolean searchClient(String dni){
        boolean match=false;
        
        for(int i=0;i<this.ourClients.size()&&!match;i++){
            if(this.ourClients.get(i).getDni().equals(dni)){
                match=true;
            }
        }
        return match;
    }

    /**Busca en el ArrayList de Client de esta clase un cliente con el dni pasado 
    *por parametro
    *@param dni con el dni del cliente
    *@return Objeto tipo Cliente o null dependiendo de si lo ha encontrado o no
    */
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
    
    /**Busca en el ArrayList de Client de esta clase un Objeto Cliente en concreto
    *si lo encuentra lo elimina
    *@param c Objeto tipo Client a buscar
    */
    public void removeClient(Client c){
        boolean match=false;
        
        for(int i=0;i<this.ourClients.size()&&!match;i++){
            if(this.ourClients.get(i)==c){
                this.ourClients.remove(i);
                match=true; 
            }
        }
    }
    
    /**Devuelve el ArrayList de Seat de la clase Flight de un vuelo en concreto.
    *@param f Objeto tipo Flight que representa un vuelo en concreto
    *@return ArrayList de Seat con los asientos disponibles del vuelo
    *@throws Exception si el metodo getFreeSeats de Flight lanza una excepcion 
    */
    public ArrayList<Seat> getFreeSeatFromFlight(Flight f)throws Exception{
        return f.getFreeSeats();
    }
    
    /**Metodo el cual busca un vuelo en el ArrayList de Flight de esta clase
    *y si lo encuentra, llama a su metodo myProfitability()
    *@param flightId con el id del vuelo en cuestion
    *@return String con la probabilidad del vuelo
    *@throws Exception Si no hay vuelos disponibles
    */
    public String flightsProfitablity(String flightId)throws Exception{
        String profitability=null;
        boolean match=false;
        
        if(this.ourFlights.isEmpty()==false){
            for(int i=0;i<this.ourFlights.size()&&!match;i++){
                if(this.ourFlights.get(i).id.equals(flightId)){
                    profitability=this.ourFlights.get(i).myProfitability();
                    match=true;
                }
            }
        }else{
            throw new Exception("No hay vuelos disponibles");
        }
        
        if(!match){
            throw new Exception("Vuelo desconocido");
        }
        return profitability;
    }
    

    
    
    @Override
    public String toString(){
        return "String compania"+this.ourCeo;
    }

}
           