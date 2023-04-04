package Application.BusinessObjects;

import Application.CompMethods.*;
import Application.DAOs.*;
import Application.DTOs.Airport;
import Application.DTOs.Flight;
import Application.DTOs.Payment;
import Application.Exceptions.DaoException;

import java.util.*;

public class Filter {
    static Helpers helper = new Helpers();
    static CustomerDaoInterface customerDao = new MySqlCustomerDao();
    static AirportDaoInterface airportDao = new MySqlAirportDao();
    static FlightDaoInterface flightDao = new MySqlFlightDao();
    static BookingDaoInterface bookingDao = new MySqlBookingDao();
    static PaymentDaoInterface paymentDao = new MySqlPaymentDao();








}
