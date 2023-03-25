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
}