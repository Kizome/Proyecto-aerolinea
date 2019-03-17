package classes.company.plane;

import classes.company.seat.Seat;
import classes.myCalendar;
import classes.company.flight.Flight;

import java.util.Calendar;
import java.util.ArrayList;

/**Clase abstracta Plane desde la cual se definen caracteristicas comunes de
*los aviones define un metodo abstracto.
*@author Rafa Amo.
*/

public abstract class Plane{

    /**
    * Variable que almacena la matricula del avión
    */
    public String enrollment;
    /**
    * Variable que almacena la fecha de compra del avion
    */
    public Calendar purchaseDate;
    /**
    * Variable que almacena el coste del avion
    */
    public int cost;
    /**
    * Variable que almacena la autonomia del avion
    */
    public int autonomy;
    /**
    * Variable que almacena la capacidad del avion en cuanto a pasajeros se refiere
    */
    public int capacity;
    /**
    * Variable que almacena el consumo de combustible en litros por minuto
    */
    public int oilConsume;
    /**
    * Variable que almacena los asientos del avion
    */
    public ArrayList<ArrayList<Seat>> planesSeats;
    /**
    * Variable que almacena el vuelo al que pertenece el avion
    */
    public Flight myFlight;

    /**
    * Constructor de la clase Plane con todo lo necesario para el avión
    * @param acronym Recibe el acronimo de la compañia
    * @param number Recibe el numero del avion
    * @param purchaseDate Recibe la fecha de compra del avion
    * @param oilConsume Recibe el consumo del avion en Litros/minutos
    */
    protected Plane(String acronym,int number,Calendar purchaseDate,int oilConsume){
        this.planesSeats = new ArrayList<ArrayList<Seat>>();
        this.enrollment=setEnrollment(number,acronym);
        this.purchaseDate=purchaseDate;
        this.generateSeats();
        this.oilConsume=oilConsume;
    }

    public abstract void generateSeats();

    /**Metodo que comprueba si el avion ya esta asignado a un vuelo.
    @param f vuelo con el que se pretende relacionar al avion
    *@throws Exception Excepcion que se lanza si el avion ya se encuentra
    *asignado a un vuelo
    */
    public void setFlight(Flight f)throws Exception{
        if(this.myFlight==null){
            this.myFlight=f;
        }else{
            throw new Exception("Error: El avion "+this.enrollment+" esta actualmente designado en el vuelo "+this.myFlight.id);
        }
    }

    /**Metodo creado para segun el la dimension del entero que pasado por
    *parametro que representa el numero de avion, añadir tres ceros, dos o uno.
    *@param number int del numero de avion
    *@param acronym String cn el acronimo de la compañia
    *@return String con la matricula formateada del avion
    */
    public String setEnrollment(int number, String acronym){
        String enrollment=Integer.toString(number);

        switch(enrollment.length()){
            case 1:
                enrollment=acronym+"000"+enrollment;break;
            case 2:
                enrollment=acronym+"00"+enrollment;break;
            case 3:
                enrollment=acronym+"0"+enrollment;break;
            case 4:
                enrollment=acronym+enrollment;break;
            case 5:
                //perdida de control sobre matriculas
        }
        return enrollment;
    }

    @Override
    public String toString(){
        return "Matricula "+this.enrollment+" Fecha de compra "+myCalendar.format(this.purchaseDate);
    }
}
