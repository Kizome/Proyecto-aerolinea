package classes.company.people;

import classes.company.ticket.Ticket;
import classes.company.flight.Flight;

import java.util.ArrayList;
import java.util.Calendar;

/**Clase que extiende de la clase Persona y que cottiene un ArrayList de tickets
*@author Rafa Amo Moral
*/

public class Client extends People{
    
    public ArrayList<Ticket> myTickets;
	
	public Client(String name,String surname,String dni, Calendar birthday, String nationality)throws Exception{
		super(name,surname,dni,birthday,nationality);
        myTickets=new ArrayList<>();
	}
    
    /**Metodo que añade un ticket
    *@param t Objeto ticket a insertar
    */
    public void addTicket(Ticket t){
        this.myTickets.add(t);
    }
    
    /**Metodo que busca en el ArrayList ticket de esta clase por el id del 
    *ticket. Si hay match lo elimina. 
    *@param idTicket String que representa el id del ticket
    *@return true si ha logrado eliminar el ticket
    */
    public boolean removeTicket(String idTicket){
        boolean deleted=false;
        
        for(int i=0;i<this.myTickets.size()&&!deleted;i++){
           if(this.myTickets.get(i).getId().equals(idTicket)){
               this.myTickets.remove(i);
               deleted=true;
           }
        }
        
        return deleted;
    }
    
    /**Metodo que busca un ticket por su id.
    *@param idTicket String con el id del ticket a buscar
    @return Ticket o null dependiendo de si el id pertenecia a alguna de las 
    *entradas del ArrayList de Ticket de esta clase.
    *@throws Exception Lanzada si el usuario o dispone de ningun ticket
    */
    public Ticket searchTicket(String idTicket)throws Exception{
        Ticket myTicket=null;
        boolean match=false;
        
        if(idTicket!=null){
            if(!this.myTickets.isEmpty()){
                for(int i=0;i<this.myTickets.size()&&!match;i++){
                    if(this.myTickets.get(i).getId().equals(idTicket)){
                        myTicket=this.myTickets.get(i);
                        match=true;
                    }
                } 
            }else{
                throw new Exception("No dispone de ningun ticket");
            }
        }
        return myTicket;
    }
    
    /**Metodo encargado de saber si alguna de las entradas en el ArrayList de
    *ticket de esta clase tiene como vuelo un vuelo en concreto
    *@param f Vuelo en concreto a buscar
    *@return Devuelve true si hay algun ticket de ese vuelo
    */
    public boolean haveTicketOfThisFlight(Flight f){
        boolean match=false;
        
        for(int i=0;i<this.myTickets.size()&&!match;i++){
            if(this.myTickets.get(i).getFlight()==f){
                this.myTickets.remove(i);
                match=true;
            }
        }
        return match;
    }
}
