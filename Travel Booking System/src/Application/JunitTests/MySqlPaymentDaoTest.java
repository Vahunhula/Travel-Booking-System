package Application.JunitTests;

import Application.DAOs.MySqlPaymentDao;
import Application.DAOs.PaymentDaoInterface;
import Application.DTOs.Payment;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MySqlPaymentDaoTest {
    @Test
    void testFindAllPayments() {
        PaymentDaoInterface paymentDao = new MySqlPaymentDao();

        try{
            List<Payment> payments = paymentDao.findAllPayments();
            //this is a test to see if the list is empty
            assertNotNull(payments);
            //if the list is not empty, then the size of the list should be greater than 0 which is true
            assertTrue(payments.size() > 0);
            for (Payment p : payments) {
                System.out.println(p);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}