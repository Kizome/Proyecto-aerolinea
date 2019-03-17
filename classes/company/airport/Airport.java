package classes.company.airport;

import classes.company.flight.Flight;

import java.util.ArrayList;

/**Clase Airport, su fin es definir tanto la llegada como la salida de un vuelo.
@author Rafa Amo Moral
*/

public class Airport{
    public String name;
    public String acronym;
    public String city;
    public String country;
    public ArrayList<String> services;

    public Airport(String name,String acronym, String city,String country){
        this.services=new ArrayList<>();
        this.name=name;
        this.acronym=acronym;
        this.city=city;
        this.country=country;
    }
    /**
    * Metodo que añade servicios al aeropuerto
    */
    public void addSevices(String s){
        this.services.add(s);
    }

    @Override
    public String toString(){
        return "Aeropuerto "+this.acronym+" "+this.name+"-Ciudad"+this.city+"-pais"+this.country;
    }




}
