package Application.JunitTests;

import Application.DAOs.BookingDaoInterface;
import Application.DAOs.MySqlBookingDao;
import Application.DTOs.Booking;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MySqlBookingDaoTest {

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
    void testFindBookingById(){
        BookingDaoInterface bookingDao = new MySqlBookingDao();
        int bookingId = 1;
        try{
            Booking booking = bookingDao.findBookingById(bookingId);
            //this is a test to see if the booking is found by id
            //if the booking is found by id, then the booking should not be null
            assertNotNull(booking);
            System.out.println(booking);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this is a test to see if the booking is deleted by id
    @Test
    void testDeleteBookingById(){
        BookingDaoInterface bookingDao = new MySqlBookingDao();
        int bookingId = 17;
        try{
            boolean deleted = bookingDao.deleteBookingById(bookingId);
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
    void testInsertBooking(){
        BookingDaoInterface bookingDao = new MySqlBookingDao();
        Booking booking = new Booking(2,4,"2023-04-01","08:00:00",2);
        try{
            Booking newBooking = bookingDao.insertBooking(booking);
            //this is a test to see if the booking is inserted
            //if the booking is inserted, then the booking should not be null
            assertNotNull(newBooking);
            System.out.println(newBooking);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}