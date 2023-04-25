package Application.Protocol;

public class MenuOptions {
    public enum CustomerMenuOptions{
        FIND_ALL_CUSTOMERS,
        FIND_CUSTOMER_BY_NUMBER,
        DELETE_CUSTOMER_BY_NUMBER,
        INSERT_CUSTOMER
    }

    public enum AirportMenuOptions{
        FIND_ALL_AIRPORTS,
        FIND_AIRPORT_BY_NUMBER,
        DELETE_AIRPORT_BY_NUMBER,
        INSERT_AIRPORT,
        FILTER_AIRPORT_BY_CITY
    }

    public enum FlightMenuOptions{
        FIND_ALL_FLIGHTS,
        FIND_FLIGHT_BY_NUMBER,
        DELETE_FLIGHT_BY_NUMBER,
        INSERT_FLIGHT,
        FILTER_FLIGHT_BY_AIIRLINE,
        FILTER_FLIGHT_BY_DEPARTURE_TIME
    }

    public enum BookingMenuOptions{
        FIND_ALL_BOOKINGS,
        FIND_BOOKING_BY_NUMBER,
        DELETE_BOOKING_BY_NUMBER,
        INSERT_BOOKING
    }

    public enum PaymentMenuOptions{
        FIND_ALL_PAYMENTS,
        FIND_PAYMENT_BY_NUMBER,
        DELETE_PAYMENT_BY_NUMBER,
        INSERT_PAYMENT,
        FILTER_PAYMENT_BY_PAYMENT_METHOD
    }
}
