package Application.BusinessObjects;

import java.util.Scanner;

import Application.DAOs.*;
import Application.DTOs.*;
import Application.Exceptions.DaoException;

import java.util.List;


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
                    System.out.println("Insert Airport");
                    break;
                case 3:
                    System.out.println("Insert Flight");
                    break;
                case 4:
                    System.out.println("Insert Booking");
                    break;
                case 5:
                    System.out.println("Insert Payment");
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
        return field;
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
}
