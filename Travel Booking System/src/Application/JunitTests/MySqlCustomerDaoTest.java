package Application.JunitTests;

import static org.junit.jupiter.api.Assertions.*;
import Application.DAOs.CustomerDaoInterface;
import Application.DAOs.MySqlCustomerDao;
import Application.DTOs.Customer;

import java.util.List;

class MySqlCustomerDaoTest {

    @org.junit.jupiter.api.Test
    void testFindAllCustomers(){
        CustomerDaoInterface customerDao = new MySqlCustomerDao();
        try {
            List<Customer> customers = customerDao.findAllCustomers();
            //this is a test to see if the list is empty
            assertNotNull(customers);
            //if the list is not empty, then the size of the list should be greater than 0 which is true
            assertTrue(customers.size() > 0);
            for (Customer c : customers) {
                System.out.println(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this is a test to see if the customer is found by id
    @org.junit.jupiter.api.Test
    void testFindCustomerById(){
        CustomerDaoInterface customerDao = new MySqlCustomerDao();
        int customerId = 1;
        try{
            Customer customer = customerDao.findCustomerById(customerId);
            //this is a test to see if the customer is found by id
            //if the customer is found by id, then the customer should not be null
            assertNotNull(customer);
            System.out.println(customer);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this is a test to see if the customer is deleted by id
    @org.junit.jupiter.api.Test
    void testDeleteCustomerById(){
        CustomerDaoInterface customerDao = new MySqlCustomerDao();
        int customerId = 13;
        try{
            boolean deleted = customerDao.deleteCustomerById(customerId);
            //this is a test to see if the customer is deleted by id
            //if the customer is deleted by id, then the customer should be true
            assertTrue(deleted);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    //this is a test to see if the customer is inserted
    @org.junit.jupiter.api.Test
    void testInsertCustomer(){
        CustomerDaoInterface customerDao = new MySqlCustomerDao();
        Customer customer = new Customer("Patrick","d005@student.dkit.ie","0871234567","Dundalk");
        try{
            Customer insertedCustomer = customerDao.insertCustomer(customer);
            //this is a test to see if the customer is inserted
            //if the customer is inserted, then the customer should not be null
            assertNotNull(insertedCustomer);
            System.out.println(insertedCustomer);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}