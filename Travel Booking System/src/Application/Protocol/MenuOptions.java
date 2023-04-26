package Application.Protocol;

public class MenuOptions {
    public enum CustomerMenuOptions{
        FIND_ALL_CUSTOMERS,
        FIND_CUSTOMER_BY_NUMBER,
        DELETE_CUSTOMER_BY_NUMBER,
        INSERT_CUSTOMER,
        CHECK_DUPLICATE_EMAIL
    }

    public enum AirportMenuOptions{
        FIND_ALL_AIRPORTS,
        FIND_AIRPORT_BY_NUMBER,
        DELETE_AIRPORT_BY_NUMBER,
        INSERT_AIRPORT,
        FILTER_AIRPORT_BY_CITY,
        FIND_AIRPORT_BY_LOCATION
    }

    public enum FlightMenuOptions{
        FIND_ALL_FLIGHTS,
        FIND_FLIGHT_BY_NUMBER,
        DELETE_FLIGHT_BY_NUMBER,
        INSERT_FLIGHT,
        FILTER_FLIGHT_BY_AIRLINE_NAME,
        FILTER_FLIGHT_BY_DEPARTURE_TIME,
        FIND_FLIGHT_BY_AIRLINE_NAME
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
        FILTER_PAYMENT_BY_PAYMENT_METHOD,
        FIND_PAYMENT_BY_PAYMENT_METHOD
    }

    public enum VerifyMenuOptions{
        SUCCESS,
        FAILURE
    }

    public enum ErrorOption {
        RUNTIME_EXCEPTION,
        DAO_EXCEPTION
    }

}
