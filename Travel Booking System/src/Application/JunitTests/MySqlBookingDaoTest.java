package Application.JunitTests;

import Application.DAOs.AirportDaoInterface;
import Application.DAOs.BookingDaoInterface;
import Application.DAOs.MySqlAirportDao;
import Application.DAOs.MySqlBookingDao;
import Application.DTOs.Airport;
import Application.DTOs.Booking;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MySqlBookingDaoTest {
    //
    //test to find all airports
//    @Test
//    void testFindAllAirports(){
//        AirportDaoInterface airportDao = new MySqlAirportDao();
//        try{
//            List<Airport> airports = airportDao.findAllAirports();
//            //this is a test to see if the list is empty
//            assertNotNull(airports);
//            //if the list is not empty, then the size of the list should be greater than 0 which is true
//            assertTrue(airports.size() > 0);
//            for (Airport a : airports) {
//                System.out.println(a);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    //test to find airport by number
//    @Test
//    void testFindAirportByNumber(){
//        AirportDaoInterface airportDao = new MySqlAirportDao();
//        String airportNumber = "AMS";
//        try{
//            Airport airport = airportDao.findAirportByNumber(airportNumber);
//            //this is a test to see if the airport is found by number
//            //if the airport is found by number, then the airport should not be null
//            assertNotNull(airport);
//            System.out.println(airport);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    //test to delete airport by number
//    @Test
//    void testDeleteAirportByNumber(){
//        AirportDaoInterface airportDao = new MySqlAirportDao();
//        String airportNumber = "ttt";
//        try{
//            boolean deleted = airportDao.deleteAirportByNumber(airportNumber);
//            //this is a test to see if the airport is deleted by number
//            //if the airport is deleted by number, then the airport should be true
//            assertTrue(deleted);
//        }catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    //test to insert airport
//    @Test
//    void testInsertAirport() {
//        AirportDaoInterface airportDao = new MySqlAirportDao();
//        Airport airport = new Airport("ttt", "test", "test");
//        try {
//            Airport insertedAirport = airportDao.insertAirport(airport);
//            //this is a test to see if the airport is inserted
//            //if the airport is inserted, then the airport should not be null
//            assertNotNull(insertedAirport);
//            System.out.println(insertedAirport);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }

    @Test
    void testFindAllBookings() {
        BookingDaoInterface bookingDao = new MySqlBookingDao();

        try{
            List<Booking> bookings = bookingDao.findAllBookings();
            //this is a test to see if the list is empty
            assertNotNull(bookings);
            //if the list is not empty, then the size of the list should be greater than 0 which is true
            assertTrue(bookings.size() > 0);
            for (Booking b : bookings) {
                System.out.println(b);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this is a test to see if the booking is found by id
    @Test
    void testFindBookingByNumber(){
        BookingDaoInterface bookingDao = new MySqlBookingDao();
        String bookingNumber = "B0004";
        try{
            Booking booking = bookingDao.findBookingByNumber(bookingNumber);
            //this is a test to see if the booking is found by id
            //if the booking is found by id, then the booking should not be null
            assertNotNull(booking);
            System.out.println(booking);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this is a test to see if the booking is deleted by NUMBER
    @Test
    void testDeleteBookingByNumber(){
        BookingDaoInterface bookingDao = new MySqlBookingDao();
        String bookingNumber = "B0051";
        try{
            boolean deleted = bookingDao.deleteBookingByNumber(bookingNumber);
            //this is a test to see if the booking is deleted by id
            //if the booking is deleted by id, then the booking should be true
            assertTrue(deleted);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this is a test to see if the booking is inserted
    @Test
    void testInsertBooking() {
        BookingDaoInterface bookingDao = new MySqlBookingDao();
        Booking booking = new Booking("B0051", "EI4001", "C0004", "2023-03-21", "00:52:04", "20F");
        try {
            Booking insertedBooking = bookingDao.insertBooking(booking);
            //this is a test to see if the booking is inserted
            //if the booking is inserted, then the booking should not be null
            assertNotNull(insertedBooking);
            System.out.println(insertedBooking);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}