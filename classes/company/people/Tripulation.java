package classes.company.people;

import java.util.Calendar;

/**Clase la cual contiene el sueldo por defecto de la tripulacion
*@author Rafa Amo Moral
*/

public class Tripulation extends Employee{

  private final static int SALARY_DEF;

    static{
        SALARY_DEF=40000;
    }

    /**
    * Constructor que llama al padre con unos parametros heredados
    * @param name Recibe el nombre del tripulante
    * @param secondname Recibe el apellido del tripulante
    * @param dni Recibe el dni del tripulante
    * @param b Recibe la fecha de nacimiento del tripulante
    * @param nationality Recibe la nacionalidad del tripulante
    * @param languages Recibe el idioma del tripulante
    * @param nflights Recibe el numero de vuelos que lleva realizado el tripulante
    * @throws Exception Lanza una excepcion si hay un error inesperado
    */
    public Tripulation(String name, String secondname,String dni,Calendar b, String nationality, String languages, int nflights)throws Exception{
        super(dni, name, secondname, b, nationality, languages, nflights,SALARY_DEF);
    }


}
