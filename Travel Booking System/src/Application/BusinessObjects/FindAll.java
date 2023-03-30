package Application.BusinessObjects;

import Application.DAOs.*;
import Application.DTOs.*;
import Application.Exceptions.DaoException;

import java.util.List;

public class FindAll {
    public void findAllCustomers() {
        CustomerDaoInterface customerDao = new MySqlCustomerDao();
        try {
            List<Customer> customers = customerDao.findAllCustomers();
            if (customers.isEmpty()) {
                System.out.println("No customers found.");
            }
            for (Customer customer : customers) {
                System.out.println(customer.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //display all airports
    public void findAllAirports() {
        AirportDaoInterface airportDao = new MySqlAirportDao();
        try {
            List<Airport> airports = airportDao.findAllAirports();
            if (airports.isEmpty()) {
                System.out.println("No airports found.");
            }
            for (Airport airport : airports) {
                System.out.println(airport.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //display all flights
    public void findAllFlights() {
        FlightDaoInterface flightDao = new MySqlFlightDao();
        try {
            List<Flight> flights = flightDao.findAllFlights();
            if (flights.isEmpty()) {
                System.out.println("No flights found.");
            }
            for (Flight flight : flights) {
                System.out.println(flight.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //display all bookings
    public void findAllBookings() {
        BookingDaoInterface bookingDao = new MySqlBookingDao();
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

    //display all payments
    public void findAllPayments() {
        PaymentDaoInterface paymentDao = new MySqlPaymentDao();
        try {
            List<Payment> payments = paymentDao.findAllPayments();
            if (payments.isEmpty()) {
                System.out.println("No payments found.");
            }
            for (Payment payment : payments) {
                System.out.println(payment.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
