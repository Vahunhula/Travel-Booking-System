package Application.JunitTests;

import Application.DAOs.AirportDaoInterface;
import Application.DAOs.MySqlAirportDao;
import Application.DTOs.Airport;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MySqlAirportDaoTest {

    @Test
    void testFindAllAirports() {
        AirportDaoInterface airportDao = new MySqlAirportDao();

        try{
            List<Airport> airports = airportDao.findAllAirports();
            //this is a test to see if the list is empty
            assertNotNull(airports);
            //if the list is not empty, then the size of the list should be greater than 0 which is true
            assertTrue(airports.size() > 0);
            for (Airport a : airports) {
                System.out.println(a);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this is a test to see if the airport is found by id
    @Test
    void testFindAirportById() {
        AirportDaoInterface airportDao = new MySqlAirportDao();
        int airportId = 1;
        try{
            Airport airport = airportDao.findAirportById(airportId);
            //this is a test to see if the airport is found by id
            //if the airport is found by id, then the airport should not be null
            assertNotNull(airport);
            System.out.println(airport);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}