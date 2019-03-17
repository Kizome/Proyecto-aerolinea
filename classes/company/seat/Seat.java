package classes.company.seat;

/**Clase asiento la cual refleja los asientos que puede haber en un avion
*@author Rafa Amo
*/

public class Seat{
    /**
    * Variable que indica si está disponible o no el asiento.
    */
    public boolean available;
    /**
    * Variable que indica la columna del asiento (de la A en adelante)
    */
    public char colum;
    /**
    * Variable que indica la fila del asiento (de 1 en adelante)
    */
    public int row;
    /**
    * Variable que indica si es un asiento VIP o no
    */
    public boolean vip;
    /**
    * Variable que indica el id del vuelo al que pertenece
    */
    public String id;
    /**
    * Variable que indica el precio del asiento vip y no vip
    */
    public int price;

    /**
    * Constructor de la clase Seat
    * @param colum Recibe la columna del asiento
    * @param row Recibe la fila del asiento
    * @param vip Recibe la variable que indicara si es vip o no
    */
    public Seat(char colum,int row, boolean vip){
        this.available=true;
        this.setId(colum,row);
        this.colum=colum;
        this.row=row;
        this.vip=vip;
    }

    /**Da formato al id segun la posicion del asiento
    *@param colum Dato tipo char que representa la columna
    *@param row Dato tipo int que representa la fila del avion
    */
    public void setId(char colum, int row){
        StringBuilder mySeat=new StringBuilder();

        String myRow=Integer.toString(row);
        String myColum=Character.toString(colum);

        mySeat.append(myColum);
        mySeat.append(myRow);

        this.id=mySeat.toString();
    }

    @Override
    public String toString(){
        String seat;

        if(this.vip==true){
            seat=this.id+"V"+" "+String.valueOf(this.price);
        }else{
            seat=this.id+" "+String.valueOf(this.price);
        }

        return seat;
    }
}
