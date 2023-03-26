package Application.BusinessObjects;

import java.util.Scanner;

import Application.DAOs.*;
import Application.DTOs.*;
import Application.Exceptions.DaoException;

import java.util.List;
import java.time.LocalDate;


public class App {
    private static Scanner scanner = new Scanner(System.in);
    static CustomerDaoInterface customerDao = new MySqlCustomerDao();
    static AirportDaoInterface airportDao = new MySqlAirportDao();
    static FlightDaoInterface flightDao = new MySqlFlightDao();
    static BookingDaoInterface bookingDao = new MySqlBookingDao();
    static PaymentDaoInterface paymentDao = new MySqlPaymentDao();


    public static void main(String[] args) {
        while (true) {
            printMainMenu();
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    findAllEntitiesMenu();
                    break;
                case 2:
                    findByNumberMenu();
                    break;
                case 3:
                    deleteMenu();
                    break;
                case 4:
                    insertMenu();
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    private static void printMainMenu() {
        System.out.println("--- Main Menu ---");
        System.out.println("1. Find All Entities");
        System.out.println("2. Find Entity by Number");
        System.out.println("3. Delete Entity by Number");
        System.out.println("4. Insert Entity");
        System.out.println("5. Exit");
    }

    private static void findAllEntitiesMenu() {
        while (true) {
            System.out.println("--- Find All Entities ---");
            System.out.println("1. Find All Customers");
            System.out.println("2. Find All Airports");
            System.out.println("3. Find All Flights");
            System.out.println("4. Find All Bookings");
            System.out.println("5. Find All Payments");
            System.out.println("6. Back to Main Menu");
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    findAllCustomers();
                    break;
                case 2:
                    findAllAirports();
                    break;
                case 3:
                    findAllFlights();
                    break;
                case 4:
                    findAllBookings();
                    break;
                case 5:
                    findAllPayments();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    private static void findByNumberMenu() {
        while (true) {
            System.out.println("--- Find Entity by Number ---");
            System.out.println("1. Find Customer by Number");
            System.out.println("2. Find Airport by Number");
            System.out.println("3. Find Flight by Number");
            System.out.println("4. Find Booking by Number");
            System.out.println("5. Find Payment by Number");
            System.out.println("6. Back to Main Menu");
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    findCustomerByNumber();
                    break;
                case 2:
                    findAirportByNumber();
                    break;
                case 3:
                    findFlightByNumber();
                    break;
                case 4:
                    findBookingByNumber();
                    break;
                case 5:
                    findPaymentByNumber();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    private static void deleteMenu() {
        while (true) {
            System.out.println("--- Delete Entity by Number ---");
            System.out.println("1. Delete Customer by Number");
            System.out.println("2. Delete Airport by Number");
            System.out.println("3. Delete Flight by Number");
            System.out.println("4. Delete Booking by Number");
            System.out.println("5. Delete Payment by Number");
            System.out.println("6. Back to Main Menu");
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    deleteCustomerByNumber();
                    break;
                case 2:
                    deleteAirportByNumber();
                    break;
                case 3:
                    deleteFlightByNumber();
                    break;
                case 4:
                    deleteBookingByNumber();
                    break;
                case 5:
                    deletePaymentByNumber();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    private static void insertMenu() {
        while (true) {
            System.out.println("--- Insert Entity ---");
            System.out.println("1. Insert Customer");
            System.out.println("2. Insert Airport");
            System.out.println("3. Insert Flight");
            System.out.println("4. Insert Booking");
            System.out.println("5. Insert Payment");
            System.out.println("6. Back to Main Menu");
            int choice = readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    insertCustomer();
                    break;
                case 2:
                    insertAirport();
                    break;
                case 3:
                    insertFlight();
                    break;
                case 4:
                    insertBooking();
                    break;
                case 5:
                    insertPayment();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    private static int readInt(String message) {
        Scanner scanner = new Scanner(System.in);
        int input;
        while (true) {
            try {
                System.out.print(message);
                input = Integer.parseInt(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter an integer.");
            }
        }
        return input;
    }

    private static String readString(String message) {
        Scanner scanner = new Scanner(System.in);
        String input;
        while (true) {
            System.out.print(message);
            input = scanner.nextLine();
            if (input.isEmpty()) {
                System.out.println("Invalid input, please enter a non-empty string.");
            } else {
                break;
            }
        }
        return input;
    }

    //to read Double
    private static double readDouble(String message) {
        Scanner scanner = new Scanner(System.in);
        double input;
        while (true) {
            try {
                System.out.print(message);
                input = Double.parseDouble(scanner.nextLine());
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, please enter a double.");
            }
        }
        return input;
    }

    private static void findAllCustomers() {
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
    private static void findAllAirports() {
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
    private static void findAllFlights() {
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
    private static void findAllBookings() {
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
    private static void findAllPayments() {
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

    //to find customer by number and also if no customer found then it will display no customer found
    //and if customer found then it will display customer details(customerNumber is a String)
    private static void findCustomerByNumber() {
        CustomerDaoInterface customerDao = new MySqlCustomerDao();
        String customerNumber = readString("Enter customer number: ");
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
    private static void findAirportByNumber() {
        AirportDaoInterface airportDao = new MySqlAirportDao();
        String airportNumber = readString("Enter airport number: ");
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
    private static void findFlightByNumber() {
        FlightDaoInterface flightDao = new MySqlFlightDao();
        String flightNumber = readString("Enter flight number: ");
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
    private static void findBookingByNumber() {
        BookingDaoInterface bookingDao = new MySqlBookingDao();
        String bookingNumber = readString("Enter booking number: ");
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
    private static void findPaymentByNumber() {
        PaymentDaoInterface paymentDao = new MySqlPaymentDao();
        String paymentNumber = readString("Enter payment number: ");
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

    //to delete customer by number and also if no customer found then it will display no customer found
    private static void deleteCustomerByNumber() {
        CustomerDaoInterface customerDao = new MySqlCustomerDao();
        String customerNumber = readString("Enter customer number: ");
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
    private static void deleteAirportByNumber() {
        String airportNumber = readString("Enter airport number: ");
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
    private static void deleteFlightByNumber() {
        String flightNumber = readString("Enter flight number: ");
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
    private static void deleteBookingByNumber() {
        String bookingNumber = readString("Enter booking number: ");
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
    private static void deletePaymentByNumber() {
        String paymentNumber = readString("Enter payment number: ");
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

    //a method to read a type of String from tge user and do proper check if it snot longer than teh max length
    private static String readField(String type, int max) {
        String field = "";
        //check if type is customerNumber
        if (type.equalsIgnoreCase("customerNumber")) {
            String customerNumber = "";
            while (customerNumber.isEmpty() || customerNumber.length() > max) {
                customerNumber = readString("Enter customer number (max " + max + " characters): ");
                if (customerNumber.isEmpty()) {
                    System.out.println("Customer number cannot be empty.");
                } else if (customerNumber.length() > max) {
                    System.out.println("Customer number cannot be longer than " + max + " characters.");
                }
                //check if customer number already exists
                try {
                    Customer customer = customerDao.findCustomerByNumber(customerNumber);
                    if (customer != null) {
                        System.out.println("Customer number already exists. Please enter a different number.");
                        customerNumber = "";
                    }
                } catch (DaoException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            field = customerNumber;
        }
        //check if type is customerName
        if (type.equalsIgnoreCase("customerName")) {
            String customerName = "";
            while (customerName.isEmpty() || customerName.length() > max) {
                customerName = readString("Enter customer name (max " + max + " characters): ");
                if (customerName.isEmpty()) {
                    System.out.println("Customer name cannot be empty.");
                } else if (customerName.length() > max) {
                    System.out.println("Customer name cannot be longer than " + max + " characters.");
                }
            }
            field = customerName;
        }
        //check if type is address
        if (type.equalsIgnoreCase("address")) {
            String address = "";
            while (address.isEmpty() || address.length() > max) {
                address = readString("Enter address (max " + max + " characters): ");
                if (address.isEmpty()) {
                    System.out.println("Address cannot be empty.");
                } else if (address.length() > max) {
                    System.out.println("Address cannot be longer than " + max + " characters.");
                }
            }
            field = address;
        }
        //check for airport number
        if (type.equalsIgnoreCase("airportNumber")) {
            String airportNumber = "";
            while (airportNumber.isEmpty() || airportNumber.length() > max) {
                airportNumber = readString("Enter airport number (max " + max + " characters): ");
                if (airportNumber.isEmpty()) {
                    System.out.println("Airport number cannot be empty.");
                } else if (airportNumber.length() > max) {
                    System.out.println("Airport number cannot be longer than " + max + " characters.");
                }
                //check if airport number already exists
                try {
                    Airport airport = airportDao.findAirportByNumber(airportNumber);
                    if (airport != null) {
                        System.out.println("Airport number already exists. Please enter a different number.");
                        airportNumber = "";
                    }
                } catch (DaoException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            field = airportNumber;
        }
        //check for airport name
        if (type.equalsIgnoreCase("airportName")) {
            String airportName = "";
            while (airportName.isEmpty() || airportName.length() > max) {
                airportName = readString("Enter airport name (max " + max + " characters): ");
                if (airportName.isEmpty()) {
                    System.out.println("Airport name cannot be empty.");
                } else if (airportName.length() > max) {
                    System.out.println("Airport name cannot be longer than " + max + " characters.");
                }
            }
            field = airportName;
        }
        //check for airport location
        if (type.equalsIgnoreCase("airportLocation")) {
            String airportLocation = "";
            while (airportLocation.isEmpty() || airportLocation.length() > max) {
                airportLocation = readString("Enter airport location (max " + max + " characters): ");
                if (airportLocation.isEmpty()) {
                    System.out.println("Airport location cannot be empty.");
                } else if (airportLocation.length() > max) {
                    System.out.println("Airport location cannot be longer than " + max + " characters.");
                }
            }
            field = airportLocation;
        }
        //check for flight number
        if (type.equalsIgnoreCase("flightNumber")) {
            String flightNumber = "";
            while (flightNumber.isEmpty() || flightNumber.length() > max) {
                flightNumber = readString("Enter flight number (max " + max + " characters): ");
                if (flightNumber.isEmpty()) {
                    System.out.println("Flight number cannot be empty.");
                } else if (flightNumber.length() > max) {
                    System.out.println("Flight number cannot be longer than " + max + " characters.");
                }
                //check if flight number already exists
                try {
                    Flight flight = flightDao.findFlightByNumber(flightNumber);
                    if (flight != null) {
                        System.out.println("Flight number already exists. Please enter a different number.");
                        flightNumber = "";
                    }
                } catch (DaoException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            field = flightNumber;
        }
        //check for flight departure_location
        if (type.equalsIgnoreCase("departureLocation")) {
            String departureLocation = "";
            while (departureLocation.isEmpty() || departureLocation.length() > max) {
                departureLocation = readString("Enter departure location (max " + max + " characters): ");
                if (departureLocation.isEmpty()) {
                    System.out.println("Departure location cannot be empty.");
                } else if (departureLocation.length() > max) {
                    System.out.println("Departure location cannot be longer than " + max + " characters.");
                }
            }
            field = departureLocation;
        }
        //check for flight arrival_location
        if (type.equalsIgnoreCase("arrivalLocation")) {
            String arrivalLocation = "";
            while (arrivalLocation.isEmpty() || arrivalLocation.length() > max) {
                arrivalLocation = readString("Enter arrival location (max " + max + " characters): ");
                if (arrivalLocation.isEmpty()) {
                    System.out.println("Arrival location cannot be empty.");
                } else if (arrivalLocation.length() > max) {
                    System.out.println("Arrival location cannot be longer than " + max + " characters.");
                }
            }
            field = arrivalLocation;
        }
        //check for flight airline_name
        if (type.equalsIgnoreCase("airlineName")) {
            String airlineName = "";
            while (airlineName.isEmpty() || airlineName.length() > max) {
                airlineName = readString("Enter airline name (max " + max + " characters): ");
                if (airlineName.isEmpty()) {
                    System.out.println("Airline name cannot be empty.");
                } else if (airlineName.length() > max) {
                    System.out.println("Airline name cannot be longer than " + max + " characters.");
                }
            }
            field = airlineName;
        }
        //check for booking number
        if (type.equalsIgnoreCase("bookingNumber")) {
            String bookingNumber = "";
            while (bookingNumber.isEmpty() || bookingNumber.length() > max) {
                bookingNumber = readString("Enter booking number (max " + max + " characters): ");
                if (bookingNumber.isEmpty()) {
                    System.out.println("Booking number cannot be empty.");
                } else if (bookingNumber.length() > max) {
                    System.out.println("Booking number cannot be longer than " + max + " characters.");
                }
                //check if booking number already exists
                try {
                    Booking booking = bookingDao.findBookingByNumber(bookingNumber);
                    if (booking != null) {
                        System.out.println("Booking number already exists. Please enter a different number.");
                        bookingNumber = "";
                    }
                } catch (DaoException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            field = bookingNumber;
        }
        //check for payment number
        if (type.equalsIgnoreCase("paymentNumber")) {
            String paymentNumber = "";
            while (paymentNumber.isEmpty() || paymentNumber.length() > max) {
                paymentNumber = readString("Enter payment number (max " + max + " characters): ");
                if (paymentNumber.isEmpty()) {
                    System.out.println("Payment number cannot be empty.");
                } else if (paymentNumber.length() > max) {
                    System.out.println("Payment number cannot be longer than " + max + " characters.");
                }
                //check if payment number already exists
                try {
                    Payment payment = paymentDao.findPaymentByNumber(paymentNumber);
                    if (payment != null) {
                        System.out.println("Payment number already exists. Please enter a different number.");
                        paymentNumber = "";
                    }
                } catch (DaoException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            field = paymentNumber;
        }
        return field;
    }

    //a separate method so when inserting in teh floght table, the airport number can be checked if it exists and if it does
    //then insert the airport number in the flight table and if not tell teh user that the airport number does not exist or they
    //can add the airport first
    private static String readAirportNumber() {
        String airportNumber = "";
        while (airportNumber.isEmpty() || airportNumber.length() > 10) {
            airportNumber = readString("Enter airport number (max 10 characters): ");
            if (airportNumber.isEmpty()) {
                System.out.println("Airport number cannot be empty.");
            } else if (airportNumber.length() > 10) {
                System.out.println("Airport number cannot be longer than 10 characters.");
            } else {
                try {
                    Airport airport = airportDao.findAirportByNumber(airportNumber);
                    if (airport == null) {
                        System.out.println("Airport number does not exist. Please add the airport first in the InsertAirport().");
                        airportNumber = "";
                    }
                } catch (DaoException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        return airportNumber;
    }

    //a separate method so when inserting in the booking table, the flight number can be checked if it exists and if it does
    //then insert the flight number in the flight table and if not tell teh user that the flight number does not exist or they
    //can add the flight first
    private static String readFlightNumber() {
        String flightNumber = "";
        while (flightNumber.isEmpty() || flightNumber.length() > 10) {
            flightNumber = readString("Enter flight number (max 10 characters): ");
            if (flightNumber.isEmpty()) {
                System.out.println("Flight number cannot be empty.");
            } else if (flightNumber.length() > 10) {
                System.out.println("Flight number cannot be longer than 10 characters.");
            } else {
                try {
                    Flight flight = flightDao.findFlightByNumber(flightNumber);
                    if (flight == null) {
                        System.out.println("Flight number does not exist. Please add the flight first in the InsertFlight().");
                        flightNumber = "";
                    }
                } catch (DaoException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        return flightNumber;
    }

    //a separate method so when inserting in the booking table, the customer number can be checked if it exists and if it does
    //then insert the customer number in the customer table and if not tell teh user that the customer number does not exist or they
    //can add the customer first
    private static String readCustomerNumber() {
        String customerNumber = "";
        while (customerNumber.isEmpty() || customerNumber.length() > 10) {
            customerNumber = readString("Enter customer number (max 10 characters): ");
            if (customerNumber.isEmpty()) {
                System.out.println("Customer number cannot be empty.");
            } else if (customerNumber.length() > 10) {
                System.out.println("Customer number cannot be longer than 10 characters.");
            } else {
                try {
                    Customer customer = customerDao.findCustomerByNumber(customerNumber);
                    if (customer == null) {
                        System.out.println("Customer number does not exist. Please add the customer first in the InsertCustomer().");
                        customerNumber = "";
                    }
                } catch (DaoException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        return customerNumber;
    }

    //a separate method so when inserting in the payment table, the booking number can be checked if it exists and if it does
    //then insert the booking number in the booking table and if not tell teh user that the booking number does not exist or they
    //can add the booking first
    private static String readBookingNumber() {
        String bookingNumber = "";
        while (bookingNumber.isEmpty() || bookingNumber.length() > 10) {
            bookingNumber = readString("Enter booking number (max 10 characters): ");
            if (bookingNumber.isEmpty()) {
                System.out.println("Booking number cannot be empty.");
            } else if (bookingNumber.length() > 10) {
                System.out.println("Booking number cannot be longer than 10 characters.");
            } else {
                try {
                    Booking booking = bookingDao.findBookingByNumber(bookingNumber);
                    if (booking == null) {
                        System.out.println("Booking number does not exist. Please add the booking first in the InsertBooking().");
                        bookingNumber = "";
                    }
                } catch (DaoException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }
        return bookingNumber;
    }

    //the method is to check if the email is valid and also if it is not already in the database
    private static String readEmail() {
        String email = "";
        while (email.isEmpty() || email.length() > 50) {
            email = readString("Enter email (max 50 characters): ");
            if (email.length() > 50) {
                System.out.println("Error: email cannot be more than 50 characters.");
            } else if (!email.matches("^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$")) {
                System.out.println("Error: invalid email format.");
                email = "";
            } else {
                try {
                    CustomerDaoInterface customerDao = new MySqlCustomerDao();
                    if (customerDao.checkIfEmailExists(email)) {
                        System.out.println("Error: email already exists.");
                        email = "";
                    }
                } catch (DaoException e) {
                    System.out.println("Error checking email: " + e.getMessage());
                }
            }
        }
        return email;
    }

    //the method is to check if the telephone number is valid
    private static String readTelephone() {
        String telNum = "";
        while (telNum.isEmpty() || !telNum.matches("^\\+353\\d{9}$")) {
            telNum = readString("Enter telephone number in Irish format (+353123456789): ");
            if (!telNum.matches("^\\+353\\d{9}$")) {
                System.out.println("Error: invalid telephone number format.");
            }
        }
        return telNum;
    }

    //to validate the duration of the flight
    private static int readDuration() {
        int duration = 0;
        while (duration <= 0) {
            duration = readInt("Enter duration (minutes): ");
            if (duration <= 0) {
                System.out.println("Duration must be a positive integer.");
            }
        }
        return duration;
    }

    //to validate the flight_cost
    private static double readFlightCost() {
        double flightCost = 0;
        while (flightCost <= 0) {
            flightCost = readDouble("Enter flight cost: ");
            if (flightCost <= 0) {
                System.out.println("Flight cost must be a positive number.");
            }
        }
        return flightCost;
    }

    //ro validate the travel_date in format yyyy-mm-dd and also check so that the date is not before today
    //and not more than 12 montsh format yyyy-mm-dd and not more than day 31
    private static String readTravelDate() {
        String travelDate = "";
        while (travelDate.isEmpty() || !travelDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            travelDate = readString("Enter travel date in format yyyy-mm-dd: ");
            if (!travelDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                System.out.println("Error: invalid date format.");
            } else {
                String[] date = travelDate.split("-");
                int year = Integer.parseInt(date[0]);
                int month = Integer.parseInt(date[1]);
                int day = Integer.parseInt(date[2]);
                if (year < 2023 || month < 1 || month > 12 || day < 1 || day > 31) {
                    System.out.println("Error: invalid date.");
                    travelDate = "";
                } else {
                    LocalDate travelDate1 = LocalDate.of(year, month, day);
                    LocalDate today = LocalDate.now();
                    if (travelDate1.isBefore(today)) {
                        System.out.println("Error: travel date cannot be before today.");
                        travelDate = "";
                    }
                }
            }
        }
        return travelDate;
    }

    //to validate the travel_time in format hh:mm:ss and also check so that the
    //time is not before 00:00:00 and not after 23:59:59
    private static String readTravelTime() {
        String travelTime = "";
        while (travelTime.isEmpty() || !travelTime.matches("^\\d{2}:\\d{2}:\\d{2}$")) {
            travelTime = readString("Enter travel time in format hh:mm:ss: ");
            if (!travelTime.matches("^\\d{2}:\\d{2}:\\d{2}$")) {
                System.out.println("Error: invalid time format.");
            } else {
                String[] time = travelTime.split(":");
                int hour = Integer.parseInt(time[0]);
                int minute = Integer.parseInt(time[1]);
                int second = Integer.parseInt(time[2]);
                if (hour < 0 || hour > 23 || minute < 0 || minute > 59 || second < 0 || second > 59) {
                    System.out.println("Error: invalid time format.");
                    travelTime = "";
                }
            }
        }
        return travelTime;
    }

    //to validate the seat_number not less taht 1A and more than 100F
    private static String readSeatNumber() {
        String seatNumber = "";
        while (seatNumber.isEmpty() || !seatNumber.matches("^[1-9][0-9]?[A-F]$")) {
            seatNumber = readString("Enter seat number (1A-100F): ");
            if (!seatNumber.matches("^[1-9][0-9]?[A-F]$")) {
                System.out.println("Error: invalid seat number format.");
            }
        }
        return seatNumber;
    }

    //to validate the amountpaid for the payment table
    private static double readAmountPaid() {
        double amountPaid = 0;
        while (amountPaid <= 0) {
            amountPaid = readDouble("Enter amount paid: ");
            if (amountPaid <= 0) {
                System.out.println("Amount paid must be a positive number.");
            }
        }
        return amountPaid;
    }

    //to validate the payment_date in format yyyy-mm-dd and also check so that the date is not before today
    //and not more than 12 montsh format yyyy-mm-dd and not more than day 31
    private static String readPaymentDate() {
        String paymentDate = "";
        while (paymentDate.isEmpty() || !paymentDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
            paymentDate = readString("Enter payment date in format yyyy-mm-dd: ");
            if (!paymentDate.matches("^\\d{4}-\\d{2}-\\d{2}$")) {
                System.out.println("Error: invalid date format.");
            } else {
                String[] date = paymentDate.split("-");
                int year = Integer.parseInt(date[0]);
                int month = Integer.parseInt(date[1]);
                int day = Integer.parseInt(date[2]);
                if (year < 2023 || month < 1 || month > 12 || day < 1 || day > 31) {
                    System.out.println("Error: invalid date.");
                    paymentDate = "";
                } else {
                    LocalDate paymentDate1 = LocalDate.of(year, month, day);
                    LocalDate today = LocalDate.now();
                    if (paymentDate1.isBefore(today)) {
                        System.out.println("Error: payment date cannot be before today.");
                        paymentDate = "";
                    }
                }
            }
        }
        return paymentDate;
    }

    //to insert a new customer
    private static void insertCustomer() {
        String customerNumber = readField("customerNumber", 10);
        String customerName = readField("customerName", 50);
        String email = readEmail();
        String telephone = readTelephone();
        String address = readField("address", 50);

        Customer customer = new Customer(customerNumber, customerName, email, telephone, address);
        try {
            customerDao.insertCustomer(customer);
            System.out.println("Customer inserted.");
        } catch (DaoException e) {
            System.out.println("Error inserting customer: " + e.getMessage());
        }
    }

    //to insert a new airport
    private static void insertAirport() {
        String airportNumber = readField("airportNumber", 10);
        String airportName = readField("airportName", 30);
        String airportLocation = readField("airportLocation", 50);

        Airport airport = new Airport(airportNumber, airportName, airportLocation);
        try {
            airportDao.insertAirport(airport);
            System.out.println("Airport inserted.");
        } catch (DaoException e) {
            System.out.println("Error inserting airport: " + e.getMessage());
        }
    }

    //to insert a new flight
    private static void insertFlight() {
        String flightNumber = readField("flightNumber", 10);
        String airportNumber = readAirportNumber();
        String departureLocation = readField("departureLocation", 50);
        String arrivalLocation = readField("arrivalLocation", 50);
        String airlineName = readField("airlineName", 30);
        int duration = readDuration();
        double flightCost = readFlightCost();

        Flight flight = new Flight(flightNumber, airportNumber, departureLocation, arrivalLocation, airlineName, duration, flightCost);
        try {
            flightDao.insertFlight(flight);
            System.out.println("Flight inserted.");
        } catch (DaoException e) {
            System.out.println("Error inserting flight: " + e.getMessage());
        }
    }

    //to insert a new booking
    private static void insertBooking() {
        String bookingNumber = readField("bookingNumber", 10);
        String flightNumber = readFlightNumber();
        String customerNumber = readCustomerNumber();
        String travelDate = readTravelDate();
        String travelTime = readTravelTime();
        String seatNumber = readSeatNumber();

        Booking booking = new Booking(bookingNumber, flightNumber, customerNumber, travelDate, travelTime, seatNumber);
        try {
            bookingDao.insertBooking(booking);
            System.out.println("Booking inserted.");
        } catch (DaoException e) {
            System.out.println("Error inserting booking: " + e.getMessage());
        }
    }

    //to insert a new payment
    private static void insertPayment() {
        String paymentNumber = readField("paymentNumber", 10);
        String bookingNumber = readBookingNumber();
        double amountPaid = readAmountPaid();
        String paymentDate = readPaymentDate();
        String method = readString("Enter payment method: ");

        Payment payment = new Payment(paymentNumber, bookingNumber, amountPaid, paymentDate, method);
        try {
            paymentDao.insertPayment(payment);
            System.out.println("Payment inserted.");
        } catch (DaoException e) {
            System.out.println("Error inserting payment: " + e.getMessage());
        }
    }
}
