package classes.company.airport;

import classes.company.flight.Flight;

import java.util.ArrayList;

public class Airport{
    public String name;
    public String acronym;
    public String city;
    public String country;
    public ArrayList<String> services=new ArrayList<String>();
    
    public Airport(String name,String acronym, String city,String country){
        this.name=name;
        this.acronym=acronym;
        this.city=city;
        this.country=country;
    }
    
    public void addSevices(String s){
        this.services.add(s);
    }
    
    @Override
    public String toString(){
        return "Aeropuerto "+this.acronym+" "+this.name+"-Ciudad"+this.city+"-pais"+this.country;
    }
    

    
    
}