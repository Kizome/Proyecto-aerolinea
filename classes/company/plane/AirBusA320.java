package classes.company.plane;

import java.util.ArrayList;
import java.util.Calendar;

import classes.company.seat.Seat;

/**Clase AirBusA320 la cual hereda de Plane y desarrolla un metodo abstracto.
*@author Rafa Amo Moral.
*/

public class AirBusA320 extends Plane{
    /**
    * Atributo constante que guarda el precio del avion
    */
    private final static int COST=80000000;
    /**
    * Atributo constante que guarda la autonomia del avion
    */
    private final static int AUTONOMY=4000;
    /**
    * Atributo constante que guarda la capacidad del avion
    */
    private final static int PASSENGERSCAPACITY=80;
    /**
    * Atributo constante que guarda el precio en litros por kilometros, del combustible
    */
    private final static int OILCONSUME=140;//l/km
    /**
    * Atributo constante que guarda la filas de asientos
    */
    private final static int NROWS=20;
    /**
    * Atributo constante que guarda, en un array, las columnas de asientos
    */
    private final static char[] NCOLUMS={'A','B','C','D'};
    /**
    * Atributo constante que guarda el numero de pilotos que tendra el avión
    */
    private final static int NPILOT=2;
    /**
    * Atributo constante que guarda el numero de tripulantes del avión
    */
    private final static int NTRIPULATION=2;
    /**
    * Atributo constante que guarda el numero de asientos vips
    */
    private final static int NVIP=20;
    /**
    * Atributo constante que guarda el orden del avión según su compra (instanciación)
    */
    private static int number=0000;
    /**
    * Atributo constante que guarda el acronimo de la compañia en el avión
    */
    private final static String ACRONYM="IBA";


    /**
    * Constructor que manda a su padre (super) datos constantes y una variable que recibe
    * @param purchaseDate Fecha de compra del avión
    */
    public AirBusA320(Calendar purchaseDate){
        super(AirBusA320.ACRONYM,AirBusA320.number++,purchaseDate,AirBusA320.OILCONSUME);
    }



    /**
    * Metodo que genera los asientos del avión tanto vips como normales
    */
   public void generateSeats(){//hago uso de variables static ya que el metodo es llamado desde el constructor
       int nvip=this.NVIP;
        for(int i=0;i<this.NROWS;i++){
            this.planesSeats.add(new ArrayList<Seat>());
            for(int z=0;z<this.NCOLUMS.length;z++){
                if(nvip>0){
                    this.planesSeats.get(i).add(new Seat(this.NCOLUMS[z],i,true));
                    nvip--;
                }else{
                    this.planesSeats.get(i).add(new Seat(this.NCOLUMS[z],i,false));
                }
            }
        }
    }
}
