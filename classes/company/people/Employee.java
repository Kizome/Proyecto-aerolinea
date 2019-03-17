package classes.company.people;

import classes.myCalendar;
import classes.company.flight.Flight;

import java.util.ArrayList;
import java.util.Calendar;

/**@author Omar Jesus Muñoz
*/

abstract public class Employee extends People{
    
    public static int N_EMPLOYER;
    
    protected int salary;
    private int employeenumber;
    private ArrayList<String> languages;
    protected int numberOfFlight;
    public Flight actualflight;
    
    
    static{
        N_EMPLOYER=0;
    }
    
    protected Employee(String name, String surname, String dni,Calendar b,String nationality, String language, int nflights,int salary)throws Exception{
        super(name, surname, dni, b, nationality);
        this.employeenumber=this.N_EMPLOYER++;
        this.salary=salary;
        this.languages = new ArrayList<>();
        this.languages.add(language);
        this.numberOfFlight=nflights;
    }

    /**Metodo para añadir un idioma
    *@param lan String con el idioma a añadir.
    */
    public void addLanguage(String lan){
        if(lan!=null){
            this.languages.add(lan);
        }
    }

    public int getSalary(){
        return this.salary;
    }

    @Override
    public String toString(){
      return super.toString()+
      "\n   Numero de trabajador "+this.employeenumber+"\n   Nacido en "
      +this.nationality+"\n   Con "+this.numberOfFlight+" vuelos realizados"+
      "\n   Fecha de nacimiento "+myCalendar.format(this.birthday)+
          "\n   Asignado actualmente en vuelo "+this.actualflight;
    }
}
