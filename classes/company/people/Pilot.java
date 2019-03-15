package classes.company.people;

import java.util.Calendar;

public class Pilot extends Employee{

    private int flyingHour=0;
    public static final int SALARY_DEF=100000;
    public static final int SALARYSINCREMENT=5000;

    /** Genera un piloto, cogiendo atributos comunes de la clase employee,
    * y añadiendo
    */
    public Pilot(String name, String surname, String dni, Calendar b, String nationality, String lang, int nflights, int fly)throws Exception{
        super(name, surname, dni, b, nationality, lang, nflights,SALARY_DEF);
        this.flyingHour=fly;
    }

    /**Añade las horas del ultimo vuelo realizado a las horas totales del piloto
    */
    public void addHours(int hours){
        this.flyingHour=this.flyingHour+hours;
        this.numberOfFlight++;
        this.salarysIncrement();
    }
    /**incrementa el salario del piloto por cada vuelo
    */
    public void salarysIncrement(){
        this.salary=this.salary+Pilot.SALARYSINCREMENT;
    }
}
