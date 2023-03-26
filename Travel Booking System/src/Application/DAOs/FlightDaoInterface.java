package Application.DAOs;

import Application.DTOs.Flight;
import Application.Exceptions.DaoException;

import java.util.List;

public interface FlightDaoInterface {
    //Feature 1 – Find all Entities e.g. findAllPlayers() to return a List of all the entities and display
    //that list
    public List<Flight> findAllFlights() throws DaoException;

    //Feature 2 – Find and Display (a single) Entity by Key e.g. findPlayerById( id ) – return a single
    //entity and display its contents.
    public Flight findFlightByNumber(String flightNumber) throws DaoException;

    //Feature 3 – Delete an Entity by key e.g. deletePlayerById( id ) – remove entity from database
    public boolean deleteFlightByNumber(String flightNumber) throws DaoException;

    //Feature 4 – Insert an Entity in the database using DAO (gather data from user, store in DTO
    //object, pass into method insertPlayer ( Player ), return new entity including assigned auto-id.
    public Flight insertFlight(Flight flight) throws DaoException;

    //an extra method made by me to find all flights by customer number so that when a customer is
    //about to be deleted, all their affected flights will be displayed
    public List<Flight> findAllFlightsByAirportNumber(String customerNumber) throws DaoException;
}
