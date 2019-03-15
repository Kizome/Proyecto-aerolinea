package classes.company.plane;

/**
*@Author: Rafa Amo.
*/
import java.util.ArrayList;
import java.util.Calendar;

import classes.company.seat.Seat;


public class Boing787 extends Plane{
    /**
    * Atributo constante que guarda el precio del avion
    */
    private final static int COST=280000000;
    /**
    * Atributo constante que guarda la autonomia del avion
    */
    private final static int AUTONOMY=8000;
    /**
    * Atributo constante que guarda la capacidad del avion
    */
    private final static int PASSENGERSCAPACITY=300;
    /**
    * Atributo constante que guarda el precio en litros por kilometros, del combustible
    */
    private final static int OILCONSUME=12;//l/km
    /**
    * Atributo constante que guarda la filas de asientos
    */
    private final static int NROWS=50;
    /**
    * Atributo constante que guarda, en un array, las columnas de asientos
    */
    private final static char[] COLUMS={'A','B','C','D','E','F'};
    /**
    * Atributo constante que guarda el numero de asientos vips
    */
    private final static int NVIP=60;
    /**
    * Atributo constante que guarda la cantidad de huecos para coches del avión
    */
    private String[] planeCars= new String[4];
    /**
    * Atributo constante que guarda el orden del avión según su compra (instanciación)
    */
    private static int number=0;
    /**
    * Atributo constante que guarda el acronimo de la compañia en el avión
    */
    private final static String ACRONYM="IBB";


    /**
    * Constructor que manda a su padre (super) datos constantes y una variable que recibe
    * @param purchaseDate Fecha de compra del avión
    */
    public Boing787(Calendar purchaseDate){
        super(Boing787.ACRONYM,Boing787.number++,purchaseDate,Boing787.OILCONSUME);
    }

    /**
    * Metodo que genera los asientos del avión tanto vips como normales
    */
    public void generateSeats(){
        int nvip=this.NVIP;

        for(int i=0;i<this.NROWS;i++){
            this.planesSeats.add(new ArrayList<Seat>());
            for(int z=0;z<this.COLUMS.length;z++){
                if(nvip>0){
                    this.planesSeats.get(i).add(new Seat(this.COLUMS[z],i,true));
                    nvip--;
                }else{
                    this.planesSeats.get(i).add(new Seat(this.COLUMS[z],i,false));
                }
            }
        }
    }
}
