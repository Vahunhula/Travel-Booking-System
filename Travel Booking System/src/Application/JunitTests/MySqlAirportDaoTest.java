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
}