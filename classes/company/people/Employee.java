package classes.company.people;

import java.util.ArrayList;
import java.util.Calendar;
import classes.myCalendar;

abstract public class Employee extends People{
    
    public static int N_EMPLOYER=0;
    
    protected int salary;
    private int employeenumber;
    private ArrayList<String> languages = new ArrayList<String>();
    protected int numberOfFlight;
    

  /**Genera un empleado, cogiendo atributos comunes de la clase people
  * ademas se le introduce los idiomas,H_Vuelo y salario 
  */
  protected Employee(String name, String surname, String dni,Calendar b,String nationality, String language, int nflights,int salary)throws Exception{
    super(name, surname, dni, b, nationality);
      this.employeenumber=this.N_EMPLOYER++;
      this.salary=salary;
      this.languages.add(language);
      this.numberOfFlight=nflights;
    }

    public void addLanguage(String lan){
      this.languages.add(lan);
    }
    
    public int getSalary(){
        return this.salary;
    }

    @Override
    public String toString(){
      return super.toString()+
      "\n   Numero de trabajador "+this.employeenumber+"\n   Nacido en "
      +this.nationality+" con "+this.numberOfFlight+" vuelos realizados"+
      "\n   Fecha de nacimiento "+myCalendar.format(this.birthday);
    }
}
