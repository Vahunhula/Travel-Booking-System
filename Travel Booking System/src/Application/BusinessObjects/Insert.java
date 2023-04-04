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








}
