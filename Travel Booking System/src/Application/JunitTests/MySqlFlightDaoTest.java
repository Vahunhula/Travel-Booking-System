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
}