import classes.Menu;
import classes.company.airport.Airport;
import classes.company.Company;
import classes.company.seat.Seat;
import classes.company.plane.Plane;
import classes.company.plane.AirBusA320;
import classes.company.plane.Boing787;
import classes.company.flight.Flight;
import classes.company.people.Pilot;
//import classes.company.employee.Tripulation;
import classes.company.people.Client;
import classes.company.ticket.Ticket;
import classes.company.people.People;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.ArrayList;


public class Main{
    public static void main(String[] Args){
        
        try{
            Company myCompany=Company.getInstance();
        
            Calendar myCalendar=Calendar.getInstance();
            myCalendar.set(1998,8,19);
            Plane myPlane1=new AirBusA320(myCalendar);

            Calendar myCalendar1=Calendar.getInstance();
            myCalendar1.set(1997,8,4);
            Plane myPlane2=new AirBusA320(myCalendar1);

            Calendar myCalendar2=Calendar.getInstance();
            myCalendar2.set(20,10,30);
            Plane myPlane3=new Boing787(myCalendar2);
            
            Calendar myCalendar3=Calendar.getInstance();
            myCalendar3.set(20,10,30);
            Plane myPlane4=new Boing787(myCalendar3);

            
            Calendar myCalendar4=Calendar.getInstance();
            myCalendar4.set(20,10,30);
            Plane myPlane5=new Boing787(myCalendar4);


            myCompany.addPlane(myPlane1);
            myCompany.addPlane(myPlane2);
            myCompany.addPlane(myPlane3);
            myCompany.addPlane(myPlane4);
            myCompany.addPlane(myPlane5);




            Client c1=new Client("Rafa","Amo","26825394B",null,"Espaniol");
            myCompany.addClient(c1);

            Airport a1=new Airport("Aeropuerto de Cordoba","ODB","Cordoba","Espania");
            Airport a2=new Airport("Barajas","MAD","Madrid","Espania");
            Airport a3=new Airport("El-Prat","BCN","Barcelona","Espania");
            Airport a4=new Airport("Aeropuerto de Valencia","VLC","Valencia","Espania");
            Airport a5=new Airport("Aeropuerto de Bilbao","BIO","Bilbao","Espania");
            Airport a6=new Airport("JF Kennedy","JFK","New York","United States");

            Pilot p1=new Pilot("Rafa","Amo Moral","26825394B",new GregorianCalendar(2010,Calendar.AUGUST,19,19,54),"Espania","Esapniol",120,100000);
            Pilot p2=new Pilot("Pepe","Perez Pamplona","46646964C",new GregorianCalendar(2010,Calendar.AUGUST,19,19,54),"Espania","Espaniol",80,70000);
            Pilot p3=new Pilot("Francisco","Cubillo Cantero","75682956F",new GregorianCalendar(2010,Calendar.AUGUST,19,19,54),"Espania","Espaniol",20,10000);
            Pilot p4=new Pilot("Antonio","Gallego Galana","80604005E",new GregorianCalendar(2010,Calendar.AUGUST,19,19,54),"Espania","Espaniol",101,80000);
            Pilot p5=new Pilot("Laura","Larameda Lozano","53851406B",new GregorianCalendar(2010,Calendar.AUGUST,19,19,54),"Espania","Espaniol",10,50);
            myCompany.hireEmployee(p1); 
            myCompany.hireEmployee(p2);
            myCompany.hireEmployee(p3);
            myCompany.hireEmployee(p4);
            myCompany.hireEmployee(p5);


            Calendar myCalendar5=Calendar.getInstance();
            myCalendar5.set(2019,02,29,00,00);
            Flight f1=new Flight(a2,a1,myPlane1,p1,80,50,myCalendar5);
            myCompany.addFlight(f1);

            Calendar myCalendar6=Calendar.getInstance();
            myCalendar6.set(2019,03,01,00,00);
            Flight f2=new Flight(a3,a2,myPlane2,p2,50,40,myCalendar6);
            myCompany.addFlight(f2);
            
            Calendar myCalendar7=Calendar.getInstance();
            myCalendar7.set(2019,03,02,00,00);
            Flight f3=new Flight(a4,a2,myPlane3,p3,60,45,myCalendar7);
            myCompany.addFlight(f3);
            
            Calendar myCalendar8=Calendar.getInstance();
            myCalendar8.set(2019,03,03,00,00);
            Flight f4=new Flight(a5,a4,myPlane4,p4,70,55,myCalendar8);
             myCompany.addFlight(f4);
            
            Calendar myCalendar9=Calendar.getInstance();
            myCalendar9.set(2019,03,04,00,00);
            Flight f5=new Flight(a6,a2,myPlane5,p5,600,620,myCalendar9);
            myCompany.addFlight(f5);
            
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
          
        
        
          Menu.showMenu();
        
    }
}
       
        
