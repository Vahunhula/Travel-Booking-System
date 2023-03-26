package Application.BusinessObjects;

import java.util.Scanner;

import Application.DAOs.*;
import Application.DTOs.*;
import Application.Exceptions.DaoException;
import java.util.List;


public class App {
    private static Scanner scanner = new Scanner(System.in);

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
                    System.out.println("Find Customer by Number");
                    break;
                case 2:
                    System.out.println("Find Airport by Number");
                    break;
                case 3:
                    System.out.println("Find Flight by Number");
                    break;
                case 4:
                    System.out.println("Find Booking by Number");
                    break;
                case 5:
                    System.out.println("Find Payment by Number");
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
                    System.out.println("Delete Customer by Number");
                    break;
                case 2:
                    System.out.println("Delete Airport by Number");
                    break;
                case 3:
                    System.out.println("Delete Flight by Number");
                    break;
                case 4:
                    System.out.println("Delete Booking by Number");
                    break;
                case 5:
                    System.out.println("Delete Payment by Number");
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
                    System.out.println("Insert Customer");
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

    private static void findAllCustomers() {
        MySqlCustomerDao customerDao = new MySqlCustomerDao();
        try {
            List<Customer> customers = customerDao.findAllCustomers();
            for (Customer customer : customers) {
                System.out.println(customer.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //display all airports
    private static void findAllAirports() {
        MySqlAirportDao airportDao = new MySqlAirportDao();
        try {
            List<Airport> airports = airportDao.findAllAirports();
            for (Airport airport : airports) {
                System.out.println(airport.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //display all flights
    private static void findAllFlights() {
        MySqlFlightDao flightDao = new MySqlFlightDao();
        try {
            List<Flight> flights = flightDao.findAllFlights();
            for (Flight flight : flights) {
                System.out.println(flight.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //display all bookings
    private static void findAllBookings() {
        MySqlBookingDao bookingDao = new MySqlBookingDao();
        try {
            List<Booking> bookings = bookingDao.findAllBookings();
            for (Booking booking : bookings) {
                System.out.println(booking.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //display all payments
    private static void findAllPayments() {
        MySqlPaymentDao paymentDao = new MySqlPaymentDao();
        try {
            List<Payment> payments = paymentDao.findAllPayments();
            for (Payment payment : payments) {
                System.out.println(payment.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
