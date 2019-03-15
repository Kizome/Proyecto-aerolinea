package interfaces;
import classes.company.plane.Plane;
import classes.company.Company;
import classes.company.flight.Flight;
import classes.company.people.Employee;
import classes.company.airport.Airport;
import classes.company.people.Client;
import classes.company.seat.Seat;
import classes.company.ticket.Ticket;

public interface IAirCompany{
    public void hireEmployee(Employee e)throws Exception;
    public void fireEmployee(Employee e)throws Exception;
    public String listEmployee();
    public boolean searchEmployee(Employee e);
    public int totalSalary();
    
    public void addPlane(Plane p)throws Exception;
    public String listPlane()throws Exception;
    public void removePlane(Plane p)throws Exception;
    public boolean searchPlane(Plane p)throws Exception;
    
    public void addFlight(Flight f);
    public String listFlight()throws Exception;
    //public Flight searchFlight(String idFlight)throws Exception;
    public void removeFlight(Flight f)throws Exception;
    
    public String buyTicket(String dni,Flight f, Seat s)throws Exception;
    public void removeTicket(String dni, String ticketId)throws Exception;
    public Ticket searchTicket(String dni, String ticketId)throws Exception;
    
    public void addClient(Client c)throws Exception;
    public String clientsList();
    public boolean searchClient(String dni);
    public void removeClient(Client c)throws Exception;
}