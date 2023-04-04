package Application.BusinessObjects;

import Application.DAOs.*;
import Application.DTOs.*;
import Application.Exceptions.DaoException;

import java.util.List;

public class CustomerObj {
    static Helpers helper = new Helpers();
    static CustomerDaoInterface customerDao = new MySqlCustomerDao();

    static BookingDaoInterface bookingDao = new MySqlBookingDao();


    //display all customers
    public void findAllCustomers() {
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

    //to delete customer by number and also if no customer found then it will display no customer found
    public void deleteCustomerByNumber() {
        CustomerDaoInterface customerDao = new MySqlCustomerDao();
        String customerNumber = helper.readString("Enter customer number: ");
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

    //to insert a new customer
    public void insertCustomer() {
        String customerNumber = helper.readInputField("customerNumber", 10);
        String customerName = helper.readInputField("customerName", 50);
        String email = helper.readEmail();
        String telephone = helper.readTelephone();
        String address = helper.readInputField("address", 50);

        Customer customer = new Customer(customerNumber, customerName, email, telephone, address);
        try {
            customerDao.insertCustomer(customer);
            System.out.println("Customer inserted.");
        } catch (DaoException e) {
            System.out.println("Error inserting customer: " + e.getMessage());
        }
    }

}
