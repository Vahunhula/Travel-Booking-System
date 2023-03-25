package Application.JunitTests;

import Application.DAOs.FlightDaoInterface;
import Application.DAOs.MySqlFlightDao;
import Application.DTOs.Flight;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MySqlFlightDaoTest {

    @Test
    void testFindAllFlights() {
        FlightDaoInterface flightDao = new MySqlFlightDao();

        try{
            List<Flight> flights = flightDao.findAllFlights();
            //this is a test to see if the list is empty
            assertNotNull(flights);
            //if the list is not empty, then the size of the list should be greater than 0 which is true
            assertTrue(flights.size() > 0);
            for (Flight f : flights) {
                System.out.println(f);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this is a test to see if the flight is found by id
    @Test
    void testFindFlightById(){
        FlightDaoInterface flightDao = new MySqlFlightDao();
        int flightId = 1;
        try{
            Flight flight = flightDao.findFlightById(flightId);
            //this is a test to see if the flight is found by id
            //if the flight is found by id, then the flight should not be null
            assertNotNull(flight);
            System.out.println(flight);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this is a test to see if the flight is deleted by id
    @Test
    void testDeleteFlightById(){
        FlightDaoInterface flightDao = new MySqlFlightDao();
        int flightId = 58;
        try{
            boolean deleted = flightDao.deleteFlightById(flightId);
            //this is a test to see if the flight is deleted by id
            //if the flight is deleted by id, then the flight should be true
            assertTrue(deleted);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this is a test to see if the flight is inserted
    @Test
    void testInsertFlght(){
        FlightDaoInterface flightDao = new MySqlFlightDao();
        Flight flight = new Flight();
        flight.setAirport_id(2);
        flight.setDeparture_location("Toronto");
        flight.setArrival_location("Vancouver");
        flight.setAirline_name("Air Canada");
        flight.setDuration(200);
        flight.setFlight_cost(200);

        try{
            Flight inserted = flightDao.insertFlight(flight);
            //this is a test to see if the flight is inserted
            //if the flight is inserted, then the flight should not be null
            assertNotNull(inserted);
            System.out.println(inserted);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}