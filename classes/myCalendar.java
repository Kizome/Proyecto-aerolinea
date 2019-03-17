package classes;

//importacion de clases del programa
import classes.company.Company;

//Importacion de utilidades
import java.util.Calendar;
import java.util.GregorianCalendar;

/**Clase creada por el problema de tener que representar de alguna forma la hora
*actual, y ademas para ayudar en el proceso de formatear un Objeto tipo Calendar
*@author Rafa Amo Moral
*/
public final class myCalendar{

    public static Calendar simulatedDate;
    public static Company myCompany;

    static{
        simulatedDate=new GregorianCalendar(2019,Calendar.FEBRUARY,28);
        myCompany=Company.getInstance();
    }

    private myCalendar(){

    }

    /**Este metodo primero suma un dia a la hora actual simulada, posteriormente
    *llama al metodo de la clase Company comprobeFligts() pasandole como
    *parametro la hora actual simulada.
    *@throws Exception Excepcion lanzada por el metodo comprobeFlights, clase
    *Company
    */
    public static void nextDay()throws Exception{
        myCalendar.simulatedDate.add(Calendar.DAY_OF_MONTH,1);
        myCompany.comprobeFlights(myCalendar.simulatedDate);
    }

    /**Metodo que da formato al Objeto tipo calendar.
    *@param c Objeto tipo Calendar a formatear
    *@return String con el formato dado
    */
    public static String format(Calendar c){
        String myFormat=myCalendar.formatYear(c.get(Calendar.YEAR))+"/"+myCalendar.formatMonth((c.get(Calendar.MONTH))+1)+
            "/"+myCalendar.formatDay(c.get(Calendar.DAY_OF_MONTH));
        return myFormat;
    }

    public static String timeFormat(Calendar c){
        String myFormat=formatHour(c.get(Calendar.HOUR))+":"+formatMinute(c.get(Calendar.MINUTE));
        return myFormat;
    }

    /**Metodo que formatea el año
    *@param y int represantador del año a formatear
    @return String con el año fomateado
    */
    private static String formatYear(int y){
        String myFormat=String.valueOf(y);

        switch(String.valueOf(y).length()){
            case 2:
                myFormat="000"+y;
            case 3:
                myFormat="00"+y;
        }
        return myFormat;
    }

    /**Metodo que formatea el mes
    *@param m int represantador del mes a formatear
    @return String con el mes fomateado
    */
     private static String formatMonth(int m){
        String myFormat=String.valueOf(m);

        switch(String.valueOf(m).length()){
            case 1:
                myFormat="0"+m;
        }
         return myFormat;
    }

    /**Metodo que formatea el dia
    *@param d int represantador del dia a formatear
    @return String con el dia fomateado
    */
    private static String formatDay(int d){
        String myFormat=String.valueOf(d);

        switch(String.valueOf(d).length()){
            case 1:
                myFormat="0"+d;
        }
         return myFormat;
    }

    /**Metodo que formatea los minutos
    *@param h int represantador del minuto a formatear
    @return String con el minuto fomateado
    */
    private static String formatHour(int h){
        String myFormat=String.valueOf(h);

        switch(String.valueOf(h).length()){
            case 1:
                myFormat="0"+h;
        }
         return myFormat;
    }

    /**Metodo que formatea el segundo
    *@param m int represantador del segundo a formatear
    @return String con el segundo fomateado
    */
    private static String formatMinute(int m){
        String myFormat=String.valueOf(m);

        switch(String.valueOf(m).length()){
            case 1:
                myFormat="0"+m;
        }
         return myFormat;
    }
}
