package classes;

import classes.company.airport.Airport;
import classes.company.Company;
import classes.myCalendar;
import classes.company.seat.Seat;
import classes.company.plane.Plane;
import classes.company.plane.AirBusA320;
import classes.company.plane.Boing787;
import classes.company.flight.Flight;
import classes.company.people.Pilot;
import classes.company.people.Client;
import classes.company.ticket.Ticket;
import classes.company.people.People;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.ArrayList;

/**Clase Menu la cual hace a su vez de Interfaz.
*@author Rafa Amo.
*/

public class Menu{
    
    private static Scanner console;
    private static Company myCompany;
    private static Menu me;

    static{
        myCompany=Company.getInstance();
        console=new Scanner(System.in);
    }
    
    private Menu(){
        
    }
    
    public static Menu getInstance(){
        if(me==null){
            me=new Menu();
        }
        return me;
    }
    
    /**Metodo que representa el menu con el que interactuara el usuario
    */
    public static void showMenu(){
        String option=null;
        
        do{
                System.out.println(myCalendar.format(myCalendar.simulatedDate));
                System.out.println("Elija una opcion");
                System.out.println("1) Buscar Vuelo");
                System.out.println("2) Consultar Billete");
                System.out.println("3) Devolver Billete");
                System.out.println("4) Listar Vuelos");
                System.out.println("5) Listar Empleados");
                System.out.println("6) Listar Clientes");
                System.out.println("7) Listar flota");
                System.out.println("8) Calcular Salarios Total");
                System.out.println("9) Calcular la rentabilidad de un vuelo");
                System.out.println("10) Limpiar la pantalla");
                System.out.println("11) Avanzar un dia");
                System.out.println("0) Salir");

                option=console.nextLine();  
                
                try{
                   switch(option){
                        case "1":
                            searchFlight();
                            pressAnyKey();break;
                        case "2":
                            checkTicket();
                            pressAnyKey();break;
                        case "3":
                            removeTicket();
                            pressAnyKey();break;
                        case "4":
                            System.out.println(myCompany.listFlight());
                            pressAnyKey();break;
                        case "5":
                            System.out.println(myCompany.listEmployee());
                            pressAnyKey();break;
                        case "6":
                            System.out.println(myCompany.clientsList());
                            pressAnyKey();break;
                        case "7":
                            System.out.println(myCompany.listPlane());
                           pressAnyKey();break;
                        case "8":
                            System.out.println(myCompany.totalSalary());
                            pressAnyKey();break;
                        case "9":
                            System.out.println(flightsProfitability());
                            pressAnyKey();break;
                        case "10":  
                            cleanConsole();break;
                        case "0":
                            System.out.println("Vuelva pronto");break;
                        case "11":
                            myCalendar.nextDay();
                            cleanConsole();break;
                        default:
                            System.out.println("Introduce una opcion del menu"); 
                    }
                }catch(Exception e){
                    System.out.println(e.getMessage());
                    pressAnyKey();
                } 
        }while(!option.equals("0"));         
    }
    
    /**Utilidad la cual congela el flujo del programa y "obliga" al usuario a 
    *introducir un Enter para continuar. Además limpia la pantalla
    */
    public static void pressAnyKey(){
        System.out.println("Pulse enter para continuar");
        console.nextLine();
        cleanConsole();
    }
    
    /**Utilidad para imprimir listas sin necesidad de definir su tipo
    @param tmp ArrayList temporal del que se convertiran sus entradas en 
    *Strings
    *@return String con la lista
    */
    private static String generateList(ArrayList<?> tmp){
        StringBuilder list =new StringBuilder();
        
        if(tmp.isEmpty()==false){
            for(int i=0;i<tmp.size();i++){
                list.append(Integer.toString(i+1)+")"+tmp.get(i)+"\n");
            }  
        }
        return list.toString();
    }
    
    /**Utilidad que comprueba si la opcion de salida en el menu "0" ha sido 
    *registrada, de serasi lanza una excepcion para contar el flujo
    *@param option String que representa un valor numerico
    @throws Exception Excepcion si la condicion de salida se ha cumplido
    */
     private static void interruptMenu(String option)throws Exception{
        if(option.equals("0")){
            throw new Exception("");
        }
    }
    
    /**Utilidad para limpiar la consola
    */
    private static void cleanConsole(){
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }catch(Exception e){     
        }     
    }
    
    /**Utilidad que formatea el ArrayList de tipo seat. En primer lugar separa
    *el ArrayList en 20 items y cada uno de ellos los guarda e la segunda 
    *dimension de un ArraList de ArrayList (haciendo un simil de pagina). Luego
    *segun la opcion que elija el usuario se cambiara hacia delante o atras la 
    *pagina o bien, si la opcion esta dentro del rango de tickets disponibles,
    *volvera al menu.
    @param tmp ArrayList de tipo seat el cual es el contenedor de los asientos
    *disponibles del vuelo
    *@return option Con la opcion del usuario
    */
    private static String formatList(ArrayList<Seat> tmp)throws Exception{
        String option=null;
        int itemsPerPage=20;
        int nPages=(int)Math.ceil((tmp.size()*1.0/itemsPerPage*1.0));
        int myPage=1;
        boolean checked=false;
        ArrayList<ArrayList<Seat>> pages= new ArrayList<ArrayList<Seat>>();
        
        for(int i=0;i<nPages;i++){
            ArrayList<Seat> tmp2=new ArrayList<>();
            for(int z=0;z<itemsPerPage&&(i*itemsPerPage+z)<tmp.size()&&z<tmp.size();z++){
                    tmp2.add(tmp.get((i*itemsPerPage)+z));
            }
            pages.add(tmp2);
        }
        
        do{
            cleanConsole();
            System.out.println("Pulse 0 para salir  -------------  Pagina"+myPage+"/"+nPages);
            if(myPage==1){
                System.out.println("Pulse S para avanzar");
            }else if(myPage==nPages){
                System.out.println("Pulse A para retroceder");
            }else{
                System.out.println("Pulse A para retroceder------Pulse S para avanzar");
            }
            
            for(int i=0;i<pages.get(myPage-1).size();i++){
                    System.out.println((i+((myPage-1)*itemsPerPage+1)+") "+pages.get(myPage-1).get(i)));
            } 
            
            option=console.nextLine();
            
            try{
                if(option.equals("A")&&myPage!=1){
                    myPage--;
                }else if(option.equals("S")&&myPage!=nPages){
                    myPage++;
                }else if(Integer.parseInt(option)>=0&&Integer.parseInt(option)<=tmp.size()&&!option.equals("00")){
                    checked=true;
                }
            }catch(Exception e){
                System.out.println(" ");
            }  
            
        }while(!checked);
        
        return option; 
    }
    
    /**Metodo planteado por el problema de tener un Scanner tipo Int ya que no 
    *coge los Enter, y hasta que no insertas un int podrias pulsar Enter hasta
    *el final de los tiempos, por lo que la solucion pasaria por modificar el 
    *Scanner a un Scanner tipo Line. Siendo de ese tipo se plantea el problema 
    *de que el usuario pueda introducir un caracter cuando se espera que 
    *introduzca un valor tipo Int. Hay entra en juego este metodo, que checkea 
    *si el String resultante del menu es un entero, sino lanza la excepcion 
    *"NumberFormatException" generada por el metodo parseInt() al intentar 
    *pasar un caracter no numerico a int
    *@param option String a comprobar
    @throws Exception si el String no respresenta un valor numerico
    */
    private static void isStringInt(String option)throws Exception{
        try{
            int myOption=Integer.parseInt(option);
            interruptMenu(option);//Siendo de formato valido se comprueba si introdujo un 0 (Condicion de salida)
        }catch(NumberFormatException e){
            throw new Exception("Introduce un valor numerico valido");
        }catch(Exception e){
            throw e;
        } 
    }
    
    /**Metodo que representa el primer punto del menu en el cual se busca un 
    *vuelo por aeropuerto origen o destino, posteriormente se elige y una vez 
    *elegido el vuelo se imprimen los asientos disponibles para ese vuelo. Una 
    *vez elegido bilete se llama al metodo de esta clase finalPurchase()
    *Como se puede observar cada operacion tiene un punto de guardado haciendo 
    *de simil de una transacción.
    */
    private static void searchFlight(){
        String OriginAirport=null;
        String destinyAirport=null;
        String flightOption=null;
        String ticketOption=null;
        ArrayList<Flight> tmp=null;
        ArrayList<Seat> tmp2=null;
        boolean op1=false;
        boolean op2=false;
        boolean op3=false;
        boolean op4=false;
        boolean op5=false;
        boolean checked=false;
        boolean blocked=false;
                
        try{
            do{
                blocked=false;
                if(!op1){
                    cleanConsole();
                    System.out.println("Pulse 0 para salir");
                    System.out.println("Introduzca su Aeropuerto Origen");
                    OriginAirport=console.nextLine();
                    interruptMenu(OriginAirport);//Compruebo si quiso salir
                    op1=true;
                }
                    
                if(!op2&&!blocked){
                    System.out.println("Introduzca su Aeropuerto Destino");
                    destinyAirport=console.nextLine();
                    if(destinyAirport.equals("0")){
                        op1=false;
                        blocked=true;
                    }else{
                        op2=true;
                    }
                }

                if(!op3&&!blocked){
                    tmp = myCompany.searchFlights(OriginAirport,destinyAirport);
                    boolean flight=false;
                    
                    do{
                        cleanConsole();
                        System.out.println(generateList(tmp));
                        System.out.println("Eliga su Vuelo");
                        flightOption=console.nextLine(); 
                        try{
                           if(Integer.parseInt(flightOption)>=0&&Integer.parseInt(flightOption)<=tmp.size()&&!flightOption.equals("00")){
                                flight=true;
                            } 
                        }catch(NumberFormatException NFE){
                           System.out.println("Inserte un valor numerico valido");
                        }
                    }while(!flight);
                    
                    if(flightOption.equals("0")){
                        op2=false;
                        blocked=true;
                        cleanConsole();
                    }else{
                        op3=true;
                    }
                }
                
                if(!op4&&!blocked){
                    tmp2 = myCompany.getFreeSeatFromFlight((tmp.get(Integer.parseInt(flightOption)-1)));
                    ticketOption = formatList(tmp2);
                    if(ticketOption.equals("0")){
                        op3=false;
                        blocked=true;
                    }else{
                        op4=true;
                    }
                }
               
                if(!op5&&!blocked){
                    op5=finalPurchase(tmp.get(Integer.parseInt(flightOption)-1),tmp2.get(Integer.parseInt(ticketOption)-1));
                    if(op5){
                      checked=true;  
                    }else{
                        op4=false;
                    }
                }
            }while(!checked);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }   
    }
    
    /**Metodo encargado de la compra final de un billete. En primer lugar pide 
    *un dni al cliente, si es un dni valido, comprueba si se encuentra entre 
    *los clientes de la compañia, sino lo crea llamando al metodo addClient() de
    *esta clase.
    *@param f Objeto Flight que representa al vuelo elegido por el usuario.
    *@param s Objeto tipo Seat que representa al asiento del vuelo elegido 
    *por el cliente.
    *@throws Exception Lanza una excepcion si el dni no es valido
    *@throws Exception Hay que tener en cuenta que aunque por el 
    *contexto del problema el metodo de la clase compañia, buyTicket()
    *no lanzaria excepcion, este metodo llama en su interior a otro metodo
    *de la misma clase, searchCompanyTicket() que busca un Objeto Ticket en
    *especifico y lanza una excepcion si no lo encuentra, pero este, nunca
    *lanzara esa excepcion en nuestro metodo actual ya que siempre va a estar
    *en la compañia como disponible ya que no ha sido comprado.Todo cambiaria 
    *si mientras el usuario quiere comprar el ticket la fecha del vuelo pasara 
    *ya que de esta forma no se encontraria el ticket. Aun asi el error debe de 
    *ser capturado.
    */
    private static boolean finalPurchase(Flight f, Seat s)throws Exception{
        boolean dniWellFormed=false;
        boolean blocked=false;
        boolean op5=false;
        
        do{
            System.out.println("Introduzca su DNI");
            String dni=console.nextLine();
            
            if(dni.equals("0")){
                op5=false;
                blocked=true;
            }else{
                op5=true;
               String myDni=dni.toUpperCase();
               try{
                   if(People.checkDni(myDni)){
                      if(myCompany.searchClient(myDni)){
                            dniWellFormed=true;
                            String idTicket=myCompany.buyTicket(myDni,f,s);
                            System.out.println("Gracias por su compra, el identificador de su billete es "+idTicket);
                        }else{
                            addClient(myDni);
                            dniWellFormed=true;
                            String idTicket=myCompany.buyTicket(myDni,f,s);
                            System.out.println("Gracias por su compra, el identificador de su billete es "+idTicket);
                        }   
                    }else{
                        System.out.println("Intentelo de nuevo");
                    }
                 }catch(Exception e){
                        throw e;
                    }  
            }
            
        }while(!dniWellFormed&&!blocked); 
        return op5;
    }
    
    /**Submenu para que el usuario se registre
    *@param dni String con el di del cliente
    *@throws Exception Lanza una excepcion si la opcion de salida se cumple 
    *para romper el flujo del metodo
    */
    private static void addClient(String dni)throws Exception{
        System.out.println("Introduzca su nombre");
        String name=console.nextLine();
        interruptMenu(name);
        
        System.out.println("Introduzca sus apellidos");
        String surname=console.nextLine();
        interruptMenu(surname);
        
        System.out.println("Introduzca su fecha de nacimiento");
        String birthday=console.nextLine();
        interruptMenu(birthday);
        
        System.out.println("Introduzca su pais");
        String nationality=console.nextLine();
        interruptMenu(nationality);
        
        myCompany.addClient(new Client(name,surname,dni.toUpperCase(),null,nationality));
    }
    
    /**Metodo equivalente al punto 2 del menu en el cual se checkea si un 
    *usuario tiene un ticket en concreto a travez del id del Ticket
    */
    private static void checkTicket(){
        System.out.println("Introduzca su dni");
        String dni=console.nextLine();
        String mydni = dni.toUpperCase();
        try{
            People.checkDni(mydni);
            System.out.println("Introduzca el id de su Ticket");
            String idTicket=console.nextLine();
            Ticket t=myCompany.searchTicket(mydni,idTicket);
            System.out.println("--------\nSalida "+myCalendar.format(t.myFlight.flightDate));
            System.out.println("Llegada "+t.myFlight.flightArrived);
            System.out.println("Precio "+t.getPrice()+"\n--------");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**Metodo equivalente al punto 3 del menu en el cual se pide el dni del 
    *usuario y un identificador de billete, posteriormente se llama al metodo
    *de la clase company removeTicket()
    */
    private static void removeTicket(){        
        System.out.println("Introduzca su dni");
        String dni=console.nextLine();
        String mydni = dni.toUpperCase();
        try{
            People.checkDni(mydni);
            System.out.println("Introduzca el id de su Ticket");
            String idTicket=console.nextLine();
            myCompany.removeTicket(mydni,idTicket);
            System.out.println("Operacion realizada con exito");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    /**Metodo equivalente al punto 9 del menu en el cual se pide el id de un 
    *vuelo y se llama a el metodo de la clase company flightsProfitability 
    *pasando por parametro el id del vuelo
    @return String con el profit del vuelo
    */
    private static String flightsProfitability()throws Exception{
        String myProfitability=null;

        System.out.println("Introduzca el id del vuelo");
        String flightId=console.nextLine();
        myProfitability=myCompany.flightsProfitablity(flightId);
        
        return myProfitability;
        
    }
}