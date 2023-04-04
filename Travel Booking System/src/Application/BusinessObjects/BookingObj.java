package Application.BusinessObjects;

import Application.DAOs.*;
import Application.DTOs.*;
import Application.Exceptions.DaoException;

import java.util.List;

public class BookingObj {
    static Helpers helper = new Helpers();
    static BookingDaoInterface bookingDao = new MySqlBookingDao();
    static PaymentDaoInterface paymentDao = new MySqlPaymentDao();


    //display all bookings
    public void findAllBookings() {
        try {
            List<Booking> bookings = bookingDao.findAllBookings();
            if (bookings.isEmpty()) {
                System.out.println("No bookings found.");
            }
            for (Booking booking : bookings) {
                System.out.println(booking.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //to find booking by number and also if no booking found then it will display no booking found
    //and if booking found then it will display booking details(bookingNumber is a String)
    public void findBookingByNumber() {
        String bookingNumber = helper.readString("Enter booking number: ");
        try {
            Booking booking = bookingDao.findBookingByNumber(bookingNumber);
            if (booking == null) {
                System.out.println("No booking found.");
            } else {
                System.out.println(booking.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //to delete booking by number and also if no booking found then it will display no booking found
    //and also check if booking has related records in payment table then it will display those records
    public void deleteBookingByNumber() {
        String bookingNumber = helper.readString("Enter booking number: ");
        try {
            boolean deleted = bookingDao.deleteBookingByNumber(bookingNumber);
            if (deleted) {
                System.out.println("Booking deleted.");
            } else {
                System.out.println("No booking found.");
            }
        } catch (DaoException e) {
            if (e.getMessage().contains("foreign key constraint")) {
                System.out.println("Booking-" + bookingNumber + " cannot be deleted because it has related records in the database:");
                try {
                    List<Payment> payments = paymentDao.findAllPaymentsByBookingNumber(bookingNumber);
                    for (Payment payment : payments) {
                        System.out.println(payment);
                    }
                } catch (DaoException e1) {
                    System.out.println("Error: " + e1.getMessage());
                }
            } else {
                System.out.println("Error deleting booking: " + e.getMessage());
            }
        }
    }

    //to insert a new booking
    public void insertBooking() {
        String bookingNumber = helper.readInputField("bookingNumber", 10);
        String flightNumber = helper.checkFlightNumber();
        String customerNumber = helper.checkCustomerNumber();
        String travelDate = helper.readTravelDate();
        String seatNumber = helper.readSeatNumber();

        Booking booking = new Booking(bookingNumber, flightNumber, customerNumber, travelDate, seatNumber);
        try {
            bookingDao.insertBooking(booking);
            System.out.println("Booking inserted.");
        } catch (DaoException e) {
            System.out.println("Error inserting booking: " + e.getMessage());
        }
    }
}
