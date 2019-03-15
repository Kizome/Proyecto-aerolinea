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

/*
*@author: Rafa Amo.
*/

public class Menu{
    
    private static Scanner console;
    private static Company myCompany;

    
    static{
        myCompany=Company.getInstance();
        console=new Scanner(System.in);
    }
       
    
    private Menu(){
        
    }
    
    
    
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

                option=Menu.console.nextLine();  
                
                try{
                   switch(option){
                        case "1":
                            Menu.searchFlight();break;
                        case "2":
                            Menu.checkTicket();break;
                        case "3":
                            Menu.removeTicket();break;
                        case "4":
                            System.out.println(Menu.myCompany.listFlight());break;
                        case "5":
                            System.out.println(Menu.myCompany.listEmployee());break;
                        case "6":
                            System.out.println(Menu.myCompany.clientsList());break;
                        case "7":
                            System.out.println(Menu.myCompany.listPlane());break;
                        case "8":
                            System.out.println(Menu.myCompany.totalSalary());break;
                        case "9":
                            System.out.println(Menu.flightsProfitability());break;
                        case "10":  
                            Menu.cleanConsole();break;
                        case "0":
                            System.out.println("Vuelva pronto");break;
                        case "11":
                            myCalendar.nextDay();
                            Menu.cleanConsole();break;
                        case "666":
                            System.out.println(Menu.myCompany.listTickets());break;
                        default:
                            System.out.println("Introduce una opcion del menu"); 
                    }
                }catch(Exception e){
                    System.out.println(e.getMessage());
                } 
        }while(!option.equals("0"));         
    }
    
    
    //Utilidad para imprimir listas de la clase
    private static String generateList(ArrayList<?> tmp){
        StringBuilder list =new StringBuilder();
        
        if(tmp.isEmpty()==false){
            for(int i=0;i<tmp.size();i++){
                list.append(Integer.toString(i+1)+")"+tmp.get(i)+"\n");
            }  
        }
        return list.toString();
    }
    
     private static void interruptMenu(String option)throws Exception{
        if(option.equals("0")){
            throw new Exception("");
        }
    }
    
    private static void cleanConsole(){
        String sSistemaOperativo = System.getProperty("os.name");
        System.out.println(sSistemaOperativo);
        
        try{
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }catch(Exception e){
            
        }       
    }
    
    /*Metodo planteado por el problema de tener un Scanner tipo Int ya que no 
    *coge los Enter, y hasta que no insertas un int podrias pulsar Enter hasta
    *el final de los tiempos, por lo que la solucion pasaria por modificar el 
    *Scanner a un Scanner tipo Line. Siendo de ese tipo se plantea el problema 
    *de que el usuario pueda introducir un caracter cuando se espera que 
    *introduzca un valor tipo Int. Hay entra en juego este metodo, que checkea 
    *si el String resultante del menu es un entero, sino lanza la excepcion 
    *"NumberFormatException" generada por el metodo parseInt() al intentar 
    *pasar un caracter no numerico a int*/
    
    private static void isStringInt(String option)throws Exception{
        try{
            int myOption=Integer.parseInt(option);
            Menu.interruptMenu(option);//Siendo de formato valido se comprueba si introdujo un 0 (Condicion de salida)
        }catch(NumberFormatException e){
            throw new Exception("Introduce un valor numerico valido");
        }catch(Exception e){
            throw e;
        } 
    }
    

    
    
    private static void searchFlight(){
        
        ArrayList<Flight> tmp;
        ArrayList<Seat> tmp2;
        String ticketOption;
        boolean checked=false;
                
        try{
            Menu.cleanConsole();
            System.out.println("\n\nPulse 0 para salir");
            System.out.println("Introduzca su Aeropuerto Origen");
            String OriginAirport=Menu.console.nextLine();
            Menu.interruptMenu(OriginAirport);//Compruebo si quiso salir
            
            System.out.println("Introduzca su Aeropuerto Destino");
            String destinyAirport=Menu.console.nextLine();
            Menu.interruptMenu(destinyAirport);//Compruebo si quiso salir
            
            
            tmp = Menu.myCompany.searchFlights(OriginAirport,destinyAirport);
            System.out.println(generateList(tmp));
            
            
            System.out.println("Eliga su Vuelo");
            String flightOption=Menu.console.nextLine();
            Menu.isStringInt(flightOption);//Compruebo si quiso salir
            
            tmp2 = Menu.myCompany.getFreeSeatFromFlight((tmp.get(Integer.parseInt(flightOption)-1)));
            System.out.println(generateList(tmp2));

            do{
                System.out.println("Eliga su Billete");
                ticketOption=Menu.console.nextLine();
                Menu.isStringInt(ticketOption);//Compruebo si quiso salir
                if(Integer.parseInt(ticketOption)>0&&Integer.parseInt(ticketOption)<=tmp2.size()){
                    checked=true;
                }else{
                    System.out.println("Elija un billete de los mostrados anteriormente\n");
                }
            }while(!checked);

            Menu.finalPurchase(tmp.get(Integer.parseInt(flightOption)-1),tmp2.get(Integer.parseInt(ticketOption)-1));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }   
    }
    
    private static void finalPurchase(Flight f, Seat s)throws Exception{
        boolean dniWellFormed=false;
        
        do{
            System.out.println("Introduzca su DNI");
            String dni=Menu.console.nextLine();
            Menu.interruptMenu(dni);//Compruebo si quiso salir
            String myDni=dni.toUpperCase();
               try{
                   if(People.checkDni(myDni)==true){
                      if(Menu.myCompany.searchClient(myDni)==true){
                            dniWellFormed=true;
                            String idTicket=Menu.myCompany.buyTicket(myDni,f,s);
                            System.out.println("Gracias por su compra, el identificador de su billete es "+idTicket);
                        }else{
                            Menu.addClient(myDni);
                            dniWellFormed=true;
                            String idTicket=Menu.myCompany.buyTicket(myDni,f,s);
                            System.out.println("Gracias por su compra, el identificador de su billete es "+idTicket);
                        }   
                    }else{
                        System.out.println("Intentelo de nuevo");
                    }
                 }catch(Exception e){
                        throw e;
                    } 
        }while(dniWellFormed==false);  
    }
    
    //Submenu para que el usuario se registre
    private static void addClient(String dni)throws Exception{
        System.out.println("Introduzca su nombre");
        String name=Menu.console.nextLine();
        Menu.interruptMenu(name);
        
        System.out.println("Introduzca sus apellidos");
        String surname=Menu.console.nextLine();
        Menu.interruptMenu(surname);
        
        System.out.println("Introduzca su fecha de nacimiento");
        String birthday=Menu.console.nextLine();
        Menu.interruptMenu(birthday);
        
        System.out.println("Introduzca su pais");
        String nationality=Menu.console.nextLine();
        Menu.interruptMenu(nationality);
        try{
            Menu.myCompany.addClient(new Client(name,surname,dni.toUpperCase(),null,nationality));
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    
    private static void checkTicket(){
        System.out.println("Introduzca su dni");
        String dni=Menu.console.nextLine();
        String mydni = dni.toUpperCase();
        try{
            People.checkDni(mydni);
            System.out.println("Introduzca el id de su Ticket");
            String idTicket=Menu.console.nextLine();
            System.out.println(Menu.myCompany.searchTicket(mydni,idTicket).mySeat.price);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    

    private static void removeTicket(){
        boolean removed=false;
        
        System.out.println("Introduzca su dni");
        String dni=Menu.console.nextLine();
        String mydni = dni.toUpperCase();
        try{
            People.checkDni(mydni);
            System.out.println("Introduzca el id de su Ticket");
            String idTicket=Menu.console.nextLine();
            Menu.myCompany.removeTicket(mydni,idTicket);
            System.out.println("Operacion realizada con exito");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    private static String flightsProfitability(){
        String myProfitability=null;

        System.out.println("Introduzca el id del vuelo");
        String flightId=Menu.console.nextLine();
        try{
            myProfitability=Menu.myCompany.flightsProfitablity(flightId);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return myProfitability;
        
    }
}