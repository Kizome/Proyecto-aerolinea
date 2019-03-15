package classes;

import classes.company.Company;

import java.util.Calendar;


public final class myCalendar{
    
    
    public static Calendar simulatedDate;
    public static Company myCompany;
    
    static{
        simulatedDate=Calendar.getInstance();
        simulatedDate.set(2019,02,28);
        myCompany=Company.getInstance();
    }
    
    private myCalendar(){
        
    }

    
    /*
    *NextDay comprueba vuelos(si eliminase uno, lo ayadiria la siguiente semana)
    comprueba tickets de cliente(no comprueba si hay tickets mayores de la fecha actual ya que eso lo hace el primer punto)
    comprueba tickets generales
    */
    public static void nextDay()throws Exception{
        myCalendar.simulatedDate.add(Calendar.DAY_OF_MONTH,1);
        try{
            myCompany.comprobeFlights(myCalendar.simulatedDate);
        }catch(Exception e){
            throw e;
        }
    }
    
    
    
    public static String format(Calendar c){
        String myFormat=myCalendar.formatYear(c.get(Calendar.YEAR))+"/"+myCalendar.formatMonth((c.get(Calendar.MONTH)))+
            "/"+myCalendar.formatDay(c.get(Calendar.DATE));
        return myFormat;
    }
    
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
    
     private static String formatMonth(int m){
        String myFormat=String.valueOf(m);
        
        switch(String.valueOf(m).length()){
            case 1:
                myFormat="0"+m;
        }
         return myFormat;
    }
    
    private static String formatDay(int d){
        String myFormat=String.valueOf(d);
        
        switch(String.valueOf(d).length()){
            case 1:
                myFormat="0"+d;
        }
         return myFormat;
    }

}