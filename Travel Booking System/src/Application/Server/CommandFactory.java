package Application.Server;

import Application.DAOs.*;
import Application.Protocol.MenuOptions;
import Application.Server.CustomerCommands.DeleteCustomerByNumberCommand;
import Application.Server.CustomerCommands.FindAllCustomersCommand;
import Application.Server.CustomerCommands.FindCustomerByNumberCommand;
import Application.Server.CustomerCommands.InsertCustomerCommand;

import static Application.Protocol.MenuOptions.CustomerMenuOptions.*;

public class CommandFactory {
    private CustomerDaoInterface customerDao = new MySqlCustomerDao();
    private AirportDaoInterface airportDao = new MySqlAirportDao();
    private FlightDaoInterface flightDao = new MySqlFlightDao();
    private BookingDaoInterface bookingDao = new MySqlBookingDao();
    private PaymentDaoInterface paymentDao = new MySqlPaymentDao();

    public Command createCustomerCommand(MenuOptions.CustomerMenuOptions option) {
        if (option.equals(FIND_ALL_CUSTOMERS)) {
            return new FindAllCustomersCommand(customerDao);
        } else if (option.equals(FIND_CUSTOMER_BY_NUMBER)) {
            return new FindCustomerByNumberCommand(customerDao);
        } else if (option.equals(DELETE_CUSTOMER_BY_NUMBER)) {
            return new DeleteCustomerByNumberCommand(customerDao, bookingDao);
        } else if (option.equals(INSERT_CUSTOMER)) {
            return new InsertCustomerCommand(customerDao);
        }
        throw new IllegalArgumentException("Invalid option: " + option);
    }

//    public Command createAirportCommand(MenuOptions.AirportMenuOptions option) {
//        switch (option) {
//            case FIND_ALL_AIRPORTS:
//                return new FindAllAirportsCommand(airportDao);
//            case FIND_AIRPORT_BY_NUMBER:
//                return new FindAirportByNumberCommand(airportDao);
//            case DELETE_AIRPORT_BY_NUMBER:
//                return new DeleteAirportByNumberCommand(airportDao);
//            case INSERT_AIRPORT:
//                return new InsertAirportCommand(airportDao);
//            case FILTER_AIRPORT_BY_CITY:
//                return new FilterAirportByCityCommand(airportDao);
//            default:
//                throw new IllegalArgumentException("Invalid option: " + option);
//        }
//    }
//
//    public Command createFlightCommand(MenuOptions.FlightMenuOptions option) {
//        switch (option) {
//            case FIND_ALL_FLIGHTS:
//                return new FindAllFlightsCommand(flightDao);
//            case FIND_FLIGHT_BY_NUMBER:
//                return new FindFlightByNumberCommand(flightDao);
//            case DELETE_FLIGHT_BY_NUMBER:
//                return new DeleteFlightByNumberCommand(flightDao);
//            case INSERT_FLIGHT:
//                return new InsertFlightCommand(flightDao);
//            case FILTER_FLIGHT_BY_AIIRLINE:
//                return new FilterFlightByAirlineCommand(flightDao);
//            case FILTER_FLIGHT_BY_DEPARTURE_TIME:
//                return new FilterFlightByDepartureTimeCommand(flightDao);
//            default:
//                throw new IllegalArgumentException("Invalid option: " + option);
//        }
//    }
//
//    public Command createBookingCommand(MenuOptions.BookingMenuOptions option) {
//        switch (option) {
//            case FIND_ALL_BOOKINGS:
//                return new FindAllBookingsCommand(bookingDao);
//            case FIND_BOOKING_BY_NUMBER:
//                return new FindBookingByNumberCommand(bookingDao);
//            case DELETE_BOOKING_BY_NUMBER:
//                return new DeleteBookingByNumberCommand(bookingDao);
//            case INSERT_BOOKING:
//                return new InsertBookingCommand(bookingDao);
//            default:
//                throw new IllegalArgumentException("Invalid option: " + option);
//        }
//    }
//
//    public Command createPaymentCommand(MenuOptions.PaymentMenuOptions option) {
//        switch (option) {
//            case FIND_ALL_PAYMENTS:
//                return new FindAllPaymentsCommand(paymentDao);
//            case FIND_PAYMENT_BY_NUMBER:
//                return new FindPaymentByNumberCommand(paymentDao);
//            case DELETE_PAYMENT_BY_NUMBER:
//                return new DeletePaymentByNumberCommand(paymentDao);
//            case INSERT_PAYMENT:
//                return new InsertPaymentCommand(paymentDao);
//            case FILTER_PAYMENT_BY_PAYMENT_METHOD:
//                return new FilterPaymentByPaymentMethodCommand(paymentDao);
//            default:
//                throw new IllegalArgumentException("Invalid option: " + option);
//        }
//    }
}