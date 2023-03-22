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
            assertNotNull(customers);
            assertTrue(customers.size() > 0);
            for (Customer c : customers) {
                System.out.println(c);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}