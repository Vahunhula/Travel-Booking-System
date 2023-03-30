package Application.BusinessObjects;

import Application.DAOs.*;
import Application.DTOs.*;
import Application.Exceptions.DaoException;

public class FindBy {
    static Helpers helper = new Helpers();
    //to find customer by number and also if no customer found then it will display no customer found
    //and if customer found then it will display customer details(customerNumber is a String)
    public void findCustomerByNumber() {
        CustomerDaoInterface customerDao = new MySqlCustomerDao();
        String customerNumber = helper.readString("Enter customer number: ");
        try {
            Customer customer = customerDao.findCustomerByNumber(customerNumber);
            if (customer == null) {
                System.out.println("No customer found.");
            } else {
                System.out.println(customer.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //to find airport by number and also if no airport found then it will display no airport found
    //and if airport found then it will display airport details(airportNumber is a String)
    public void findAirportByNumber() {
        AirportDaoInterface airportDao = new MySqlAirportDao();
        String airportNumber = helper.readString("Enter airport number: ");
        try {
            Airport airport = airportDao.findAirportByNumber(airportNumber);
            if (airport == null) {
                System.out.println("No airport found.");
            } else {
                System.out.println(airport.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //to find flight by number and also if no flight found then it will display no flight found
    //and if flight found then it will display flight details(flightNumber is a String)
    public void findFlightByNumber() {
        FlightDaoInterface flightDao = new MySqlFlightDao();
        String flightNumber = helper.readString("Enter flight number: ");
        try {
            Flight flight = flightDao.findFlightByNumber(flightNumber);
            if (flight == null) {
                System.out.println("No flight found.");
            } else {
                System.out.println(flight.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //to find booking by number and also if no booking found then it will display no booking found
    //and if booking found then it will display booking details(bookingNumber is a String)
    public void findBookingByNumber() {
        BookingDaoInterface bookingDao = new MySqlBookingDao();
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

    //to find payment by number and also if no payment found then it will display no payment found
    //and if payment found then it will display payment details(paymentNumber is a String)
    public void findPaymentByNumber() {
        PaymentDaoInterface paymentDao = new MySqlPaymentDao();
        String paymentNumber = helper.readString("Enter payment number: ");
        try {
            Payment payment = paymentDao.findPaymentByNumber(paymentNumber);
            if (payment == null) {
                System.out.println("No payment found.");
            } else {
                System.out.println(payment.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
