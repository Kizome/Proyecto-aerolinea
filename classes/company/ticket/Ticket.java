package classes.company.ticket;

import classes.company.flight.Flight;
import classes.myCalendar;
import classes.company.seat.Seat;
import classes.company.people.Client;
import java.util.Calendar;

/**
* @author Jesus Torralbo
*/

public class Ticket{

	/**
	* Atributo que guarda el vuelo asociado al ticket
	*/
	public Flight myFlight;
	/**
	* Atributo que guarda el asiento asociado al ticket
	*/
	public Seat mySeat;
	/**
	* Atributo que guarda el dni del cliente asociado al ticket
	*/
	private String dni;
	/**
	* Atributo que guarda el id del vuelo asociado al ticket
	*/
	public String id;
	/**
	* Atributo que guarda la fecha de salida del vuelo asociado al ticket
	*/
	private Calendar date;
	/**
	* Atributo que guarda el precio del ticket
	*/
	private int price;
	/**
	* Atributos que guardan la hora, minutos y segundos del vuelo del ticket
	*/
	private int heur, min, seg;

	//Constructor
	/**Constructor principal que recibe un vuelo, un asiento y un dni, además de
	* establecer el id del ticket y el precio del asiento.
	* @param myFlight Vuelo instanciado desde el menú
	* @param myseat Asiento instanciado desde el menú
	* @param dni Recibe un DNI validado con anterioridad
	*/
	public Ticket(Flight myFlight,Seat myseat, String dni){
		this.myFlight=myFlight;
		this.mySeat=myseat;
		this.dni=dni;
		this.setDate();
        this.id=myFlight.id+myseat.id+myCalendar.format(date);
        this.price=myseat.price;
	}
	/**Constructor secundario que recibe un vuelo y un asiento, además de
	* establecer el id del ticket y el precio del asiento.
	* @param myFlight Vuelo instanciado desde el menú
	* @param myseat Asiento instanciado desde el menú
	*/
    public Ticket(Flight myFlight,Seat myseat){
		this.myFlight=myFlight;
		this.mySeat=myseat;
    this.setDate();
        this.id=myFlight.id+myseat.id+myCalendar.format(date);
        this.price=myseat.price;
	}

	/**
	* Metodo utilizado para la obtención de ID del ticker
	* @return Devuelve un String formado de juntar el id del vuelo, del asiento y la fecha.
	*/
    public String getId(){
        return this.myFlight.id+"-"+this.mySeat.id+"-"+myCalendar.format(date);
    }


	/**
	* Metodo getter para obtener el Vuelo
	* @return Devuelve el vuelo del ticket
	*/
	public Flight getFlight(){
		return this.myFlight;
	}
	/**
	* Metodo getter para obtener el asiento
	* @return Devuelve el asiento del ticket
	*/
	public Seat getSeat(){
		return this.mySeat;
	}
	/**
	* Metodo getter para obtener la fecha del vuelo
	* @return Devuelve la fecha del vuelo del ticket
	*/
    public Calendar getDate(){
		return this.date;
	}
	/**
	* Metodo getter para obtener el precio del ticket
	* @return Devuelve el precio del ticket
	*/
    public int getPrice(){
		return this.price;
	}
	/**
	* Metodo setter para definir la fecha del vuelo
	*/
	public void setDate(){
		this.date=this.myFlight.flightDate;
	}

    @Override
    public String toString(){
        return this.getId();
    }
}
