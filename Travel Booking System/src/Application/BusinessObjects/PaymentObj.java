package Application.BusinessObjects;

import Application.CompMethods.CompAscPayMethod;
import Application.CompMethods.CompDescPayMethod;
import Application.DAOs.*;
import Application.DTOs.*;
import Application.Exceptions.DaoException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class PaymentObj {
    static Helpers helper = new Helpers();
    static PaymentDaoInterface paymentDao = new MySqlPaymentDao();


    //display all payments
    public void findAllPayments() {
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

    //to find payment by number and also if no payment found then it will display no payment found
    //and if payment found then it will display payment details(paymentNumber is a String)
    public void findPaymentByNumber() {
        PaymentDaoInterface paymentDao = new MySqlPaymentDao();
        String paymentNumber = helper.readString("Enter payment number: ");
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

    //to delete payment by number and also if no payment found then it will display no payment found
    public void deletePaymentByNumber() {
        String paymentNumber = helper.readString("Enter payment number: ");
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

    //to insert a new payment
    public void insertPayment() {
        String paymentNumber = helper.readInputField("paymentNumber", 10);
        String bookingNumber = helper.checkBookingNumber();
        double amountPaid = helper.readAmountPaid();
        String paymentDate = helper.readPaymentDate();
        String method = helper.readString("Enter payment method: ");

        Payment payment = new Payment(paymentNumber, bookingNumber, amountPaid, paymentDate, method);
        try {
            paymentDao.insertPayment(payment);
            System.out.println("Payment inserted.");
        } catch (DaoException e) {
            System.out.println("Error inserting payment: " + e.getMessage());
        }
    }

    //to filter the payment with the payment method
    public void filterPaymentByPaymentMethod() {
        try {
            Set<String> uniquePaymentMethods = paymentDao.uniquePaymentMethod();
            HashMap<Integer, String> numberedPaymentMethods = new HashMap<>();
            int i = 1;
            for (String paymentMethod : uniquePaymentMethods) {
                numberedPaymentMethods.put(i, paymentMethod);
                i++;
            }
            for (Map.Entry<Integer, String> entry : numberedPaymentMethods.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue());
            }
            while (true) {
                int choice = helper.readInt("Enter your choice: ");
                if (choice > numberedPaymentMethods.size() || choice < 1) {
                    System.out.println("Invalid choice, please try again.");
                } else {
                    String paymentMethod = numberedPaymentMethods.get(choice);
                    List<Payment> payments = paymentDao.findPaymentByPaymentMethod(paymentMethod);
                    if (payments.isEmpty()) {
                        System.out.println("No payments found.");
                    } else {
                        System.out.println("How do you want the payments to be sorted?");
                        System.out.println("1. By Default");
                        System.out.println("2. By Ascending Order");
                        System.out.println("3. By Descending Order");
                        while (true) {
                            int sortChoice = helper.readInt("Enter your choice: ");
                            if (sortChoice > 3 || sortChoice < 1) {
                                System.out.println("Invalid choice, please try again.");
                            } else {
                                switch (sortChoice) {
                                    case 1:
                                        System.out.println("Payments by " + paymentMethod + ":");
                                        for (Payment payment : payments) {
                                            System.out.println(payment);
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Payments by " + paymentMethod + " sorted by ascending order:");
                                        payments.sort(new CompAscPayMethod());
                                        for (Payment payment : payments) {
                                            System.out.println(payment);
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Payments by " + paymentMethod + " sorted by descending order:");
                                        payments.sort(new CompDescPayMethod());
                                        for (Payment payment : payments) {
                                            System.out.println(payment);
                                        }
                                        break;
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
