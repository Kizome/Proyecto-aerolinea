package classes.company.seat;

import classes.company.people.Employee;
import classes.company.people.Pilot;
import classes.company.people.Tripulation;

public class Seat{
    public boolean available=true;
    public char colum;
    public int row;
    public boolean vip=false;
    public String id;
    public int price;

    /**recibe la posicion del asiento y si es o no VIP*/
    public Seat(char colum,int row, boolean vip){ 
        this.setId(colum,row);
        this.colum=colum;
        this.row=row;
        this.vip=vip;
    }

    /**Muestra un ID segun la posicion del asiento*/
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
