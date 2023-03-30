package Application.BusinessObjects;

import Application.DTOs.*;
import Application.Exceptions.DaoException;
import Application.DAOs.*;


public class Insert {
    static Helpers helper = new Helpers();
    static CustomerDaoInterface customerDao = new MySqlCustomerDao();
    static AirportDaoInterface airportDao = new MySqlAirportDao();
    static FlightDaoInterface flightDao = new MySqlFlightDao();
    static BookingDaoInterface bookingDao = new MySqlBookingDao();
    static PaymentDaoInterface paymentDao = new MySqlPaymentDao();

    //to insert a new customer
    public void insertCustomer() {
        String customerNumber = helper.readInputField("customerNumber", 10);
        String customerName = helper.readInputField("customerName", 50);
        String email = helper.readEmail();
        String telephone = helper.readTelephone();
        String address = helper.readInputField("address", 50);

        Customer customer = new Customer(customerNumber, customerName, email, telephone, address);
        try {
            customerDao.insertCustomer(customer);
            System.out.println("Customer inserted.");
        } catch (DaoException e) {
            System.out.println("Error inserting customer: " + e.getMessage());
        }
    }

    //to insert a new airport
    public void insertAirport() {
        String airportNumber = helper.readInputField("airportNumber", 10);
        String airportName = helper.readInputField("airportName", 30);
        String airportLocation = helper.readInputField("airportLocation", 50);

        Airport airport = new Airport(airportNumber, airportName, airportLocation);
        try {
            airportDao.insertAirport(airport);
            System.out.println("Airport inserted.");
        } catch (DaoException e) {
            System.out.println("Error inserting airport: " + e.getMessage());
        }
    }

    //to insert a new flight
    public void insertFlight() {
        String flightNumber = helper.readInputField("flightNumber", 10);
        String airportNumber = helper.checkAirportNumber();
        String departureLocation = helper.readInputField("departureLocation", 50);
        String departureTime = helper.readTime();
        String arrivalLocation = helper.readInputField("arrivalLocation", 50);
        String arrivalTime = helper.readTime();
        String airlineName = helper.readInputField("airlineName", 30);
        double flightCost = helper.readFlightCost();

        Flight flight = new Flight(flightNumber, airportNumber, departureLocation, departureTime, arrivalLocation, arrivalTime, airlineName, flightCost);
        try {
            flightDao.insertFlight(flight);
            System.out.println("Flight inserted.");
        } catch (DaoException e) {
            System.out.println("Error inserting flight: " + e.getMessage());
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

    //to insert a new payment
    public void insertPayment() {
        String paymentNumber = helper.readInputField("paymentNumber", 10);
        String bookingNumber = helper.checkBookingNumber();
        double amountPaid = helper.readAmountPaid();
        String paymentDate = helper.readPaymentDate();
        String method = helper.readString("Enter payment method: ");

        Payment payment = new Payment(paymentNumber, bookingNumber, amountPaid, paymentDate, method);
        try {
            paymentDao.insertPayment(payment);
            System.out.println("Payment inserted.");
        } catch (DaoException e) {
            System.out.println("Error inserting payment: " + e.getMessage());
        }
    }
}
