package Application.BusinessObjects;

import Application.DAOs.*;
import Application.DTOs.Booking;
import Application.DTOs.Flight;
import Application.DTOs.Payment;
import Application.Exceptions.DaoException;

import java.util.List;

public class Delete {
    static Helpers helper = new Helpers();
    static CustomerDaoInterface customerDao = new MySqlCustomerDao();
    static AirportDaoInterface airportDao = new MySqlAirportDao();
    static FlightDaoInterface flightDao = new MySqlFlightDao();
    static BookingDaoInterface bookingDao = new MySqlBookingDao();
    static PaymentDaoInterface paymentDao = new MySqlPaymentDao();
    //to delete customer by number and also if no customer found then it will display no customer found
    public void deleteCustomerByNumber() {
        CustomerDaoInterface customerDao = new MySqlCustomerDao();
        String customerNumber = helper.readString("Enter customer number: ");
        try {
            boolean deleted = customerDao.deleteCustomerByNumber(customerNumber);
            if (deleted) {
                System.out.println("Customer deleted.");
            } else {
                System.out.println("No customer found.");
            }
        } catch (DaoException e) {
            if (e.getMessage().contains("foreign key constraint")) {
                System.out.println("Customer-" + customerNumber + " cannot be deleted because it has related records in the database:");
                try {
                    List<Booking> bookings = bookingDao.findAllBookingsByCustomerNumber(customerNumber);
                    for (Booking booking : bookings) {
                        System.out.println(booking);
                    }
                } catch (DaoException e1) {
                    System.out.println("Error: " + e1.getMessage());
                }
            } else {
                System.out.println("Error deleting customer: " + e.getMessage());
            }
        }
    }

    //to delete airport by number and also if no airport found then it will display no airport found
    public void deleteAirportByNumber() {
        String airportNumber = helper.readString("Enter airport number: ");
        try {
            boolean deleted = airportDao.deleteAirportByNumber(airportNumber);
            if (deleted) {
                System.out.println("Airport deleted.");
            } else {
                System.out.println("No airport found.");
            }
        } catch (DaoException e) {
            if (e.getMessage().contains("foreign key constraint")) {
                System.out.println("Airport-" + airportNumber + " cannot be deleted because it has related records in the database:");
                try {
                    List<Flight> flights = flightDao.findAllFlightsByAirportNumber(airportNumber);
                    for (Flight flight : flights) {
                        System.out.println(flight);
                    }
                } catch (DaoException e1) {
                    System.out.println("Error: " + e1.getMessage());
                }
            } else {
                System.out.println("Error deleting airport: " + e.getMessage());
            }
        }
    }

    //to delete flight by number and also if no flight found then it will display no flight found
    //and also check if flight has related records in booking table then it will display those records
    public void deleteFlightByNumber() {
        String flightNumber = helper.readString("Enter flight number: ");
        try {
            boolean deleted = flightDao.deleteFlightByNumber(flightNumber);
            if (deleted) {
                System.out.println("Flight deleted.");
            } else {
                System.out.println("No flight found.");
            }
        } catch (DaoException e) {
            if (e.getMessage().contains("foreign key constraint")) {
                System.out.println("Flight-" + flightNumber + " cannot be deleted because it has related records in the database:");
                try {
                    List<Booking> bookings = bookingDao.findAllBookingsByFlightNumber(flightNumber);
                    for (Booking booking : bookings) {
                        System.out.println(booking);
                    }
                } catch (DaoException e1) {
                    System.out.println("Error: " + e1.getMessage());
                }
            } else {
                System.out.println("Error deleting flight: " + e.getMessage());
            }
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

    //to delete payment by number and also if no payment found then it will display no payment found
    public void deletePaymentByNumber() {
        String paymentNumber = helper.readString("Enter payment number: ");
        try {
            boolean deleted = paymentDao.deletePaymentByNumber(paymentNumber);
            if (deleted) {
                System.out.println("Payment deleted.");
            } else {
                System.out.println("No payment found.");
            }
        } catch (DaoException e) {
            System.out.println("Error deleting payment: " + e.getMessage());
        }
    }
}
