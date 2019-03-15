package classes.company.plane;

/**
*@author Rafa Amo.
*/

import classes.company.seat.Seat;
import classes.myCalendar;
import classes.company.flight.Flight;

import java.util.Calendar;
import java.util.ArrayList;

public abstract class Plane{

  /**
 * Atributo que guarda el vuelo asociado al ticket
 */
    public String enrollment;
    /**
   * Atributo que guarda la fecha de compra
   */
    public Calendar purchaseDate;
    /**
   * Atributo que guarda el coste del avion
   */
    public int cost;
    /**
   * Atributo que guarda la autonomia del avion
   */
    public int autonomy;
    /**
   * Atributo que guarda la capacidad del avion
   */
    public int capacity;
    /**
   * Atributo que guarda el consumo de gasoil
   */
    public int oilConsume;
    /**
   * Arraylist de arraylist que guarda los asientos del avion
   */
    public ArrayList<ArrayList<Seat>> planesSeats = new ArrayList<ArrayList<Seat>>();
    /**
   * Atributo que guarda el vuelo asociado al avion
   */
    public Flight myFlight;


    /**
   * Constructor de la clase que inicializa las variables del avión
   * @param acronym Acronimo de la compañia
   * @param number khvkh
   * @param purchaseDate Variable con la fecha de compra del avion
   * @param oilConsume Variable que guarda el precio del combustible por minuto
   */
    protected Plane(String acronym,int number,Calendar purchaseDate,int oilConsume){
        this.enrollment=setEnrollment(number,acronym);
        this.purchaseDate=purchaseDate;
        this.generateSeats();
        this.oilConsume=oilConsume;
    }



    public abstract void generateSeats();


    public void setFlight(Flight f)throws Exception{
        if(this.myFlight==null){
            this.myFlight=f;
        }else{
            throw new Exception("Error: El avion "+this.enrollment+" esta actualmente designado en el vuelo "+this.myFlight.id);
        }
    }



    public String setEnrollment(int number, String acronym){
        String enrollment=Integer.toString(number);

        switch(enrollment.length()){
            case 1:
                enrollment=acronym+"000"+enrollment;break;
            case 2:
                enrollment=acronym+"0000"+enrollment;break;
            case 3:
                //Lanzaria una exception("Perdida de control sobre formato de avion")
        }
        return enrollment;
    }



    @Override
    public String toString(){
        return "Matricula "+this.enrollment+" Fecha de compra "+myCalendar.format(this.purchaseDate);
    }
}
