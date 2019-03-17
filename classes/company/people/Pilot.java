package classes.company.people;

import java.util.Calendar;

/**Clase Pilot la cual extiende de Employee. Esta clase cuenta con un atributo
*propio "flyingHour".
*@author Rafa Amo Moral
*/

public class Pilot extends Employee{

    private int flyingHour;
    public static final int SALARY_DEF;
    public static final int SALARYSINCREMENT;

    static{
        SALARY_DEF=100000;
        SALARYSINCREMENT=5000;
    }

    public Pilot(String name, String surname, String dni, Calendar b, String nationality, String lang, int nflights, int fly)throws Exception{
        super(name, surname, dni, b, nationality, lang, nflights,SALARY_DEF);
        this.flyingHour=fly;
    }

    /**Metodo que añade horas de vuelo al pioto, le añade un vuelo, porque en
    *el contexto del proble si le añado horas es porque participa en un vuelo
    *y llamo a el metodo de esta clase slarysIncrement()
    *@param hours int con el numero de horas del piloto
    */
    public void addHours(int hours){
        this.flyingHour=this.flyingHour+hours;
        this.numberOfFlight++;
        this.salarysIncrement();
    }
    /**
    * Metodo que incrementa el salario del piloto en funcion de los vuelos realizados
    */
    public void salarysIncrement(){
        this.salary=this.salary+Pilot.SALARYSINCREMENT;
    }
}
