package Application.BusinessObjects;

import java.util.Scanner;

import Application.DAOs.*;
import Application.DTOs.*;
import Application.Exceptions.DaoException;
import Application.BusinessObjects.Helpers;
import java.util.List;
import java.time.LocalDate;


public class App {
    static CustomerDaoInterface customerDao = new MySqlCustomerDao();
    static AirportDaoInterface airportDao = new MySqlAirportDao();
    static FlightDaoInterface flightDao = new MySqlFlightDao();
    static BookingDaoInterface bookingDao = new MySqlBookingDao();
    static PaymentDaoInterface paymentDao = new MySqlPaymentDao();
    //for my helper functions
    static Helpers helper = new Helpers();

    //to access the find all entities menu
    static FindAll findAll = new FindAll();

    //to access the find entity by number menu
    static FindBy findBy = new FindBy();

    //to access the delete entity by number menu
    static Delete delete = new Delete();

    //to access the insert entity menu
    static Insert insert = new Insert();

    //to access the filter menu
    static Filter filter = new Filter();


    public static void main(String[] args) {
        while (true) {
            printMainMenu();
            int choice = helper.readInt("Enter your choice: ");
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
                    filterMenu();
                    break;
                case 6:
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
        System.out.println("5. Filter Entity");
        System.out.println("6. Exit");
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
            int choice = helper.readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    findAll.findAllCustomers();
                    break;
                case 2:
                    findAll.findAllAirports();
                    break;
                case 3:
                    findAll.findAllFlights();
                    break;
                case 4:
                    findAll.findAllBookings();
                    break;
                case 5:
                    findAll.findAllPayments();
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
            int choice = helper.readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    findBy.findCustomerByNumber();
                    break;
                case 2:
                    findBy.findAirportByNumber();
                    break;
                case 3:
                    findBy.findFlightByNumber();
                    break;
                case 4:
                    findBy.findBookingByNumber();
                    break;
                case 5:
                    findBy.findPaymentByNumber();
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
            int choice = helper.readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    delete.deleteCustomerByNumber();
                    break;
                case 2:
                    delete.deleteAirportByNumber();
                    break;
                case 3:
                    delete.deleteFlightByNumber();
                    break;
                case 4:
                    delete.deleteBookingByNumber();
                    break;
                case 5:
                    delete.deletePaymentByNumber();
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
            int choice = helper.readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    insert.insertCustomer();
                    break;
                case 2:
                    insert.insertAirport();
                    break;
                case 3:
                    insert.insertFlight();
                    break;
                case 4:
                    insert.insertBooking();
                    break;
                case 5:
                    insert.insertPayment();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }

    //menu to filter
    private static void filterMenu() {
        while (true) {
            System.out.println("--- Filter Entity ---");
            System.out.println("1. Filter Airport With City");
            System.out.println("2. Filter Flights With Airline");
            System.out.println("3. Filter Flights By The Time Of The Day");
            System.out.println("4. Insert Booking");
            System.out.println("5. Insert Payment");
            System.out.println("6. Back to Main Menu");
            int choice = helper.readInt("Enter your choice: ");
            switch (choice) {
                case 1:
                    filter.filterAirportByCity();
                    break;
                case 2:
                    filter.filterFlightByAirline();
                    break;
                case 3:
                    filter.filterFlightByDepartureTime();
                    break;
                case 4:
                    insert.insertBooking();
                    break;
                case 5:
                    insert.insertPayment();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
                    break;
            }
        }
    }
}
