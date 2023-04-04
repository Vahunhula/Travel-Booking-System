package Application.DAOs;

import Application.DTOs.Payment;
import Application.DAOs.MySqlDao;
import Application.Exceptions.DaoException;

import java.sql.*;
import java.util.*;

public class MySqlPaymentDao extends MySqlDao implements PaymentDaoInterface{
    private static TreeSet<String> paymentNumbersCache = new TreeSet<>();

    public void populatePaymentCache() throws DaoException{
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        paymentNumbersCache.clear();

        try{
            connection = getConnection();
            String query = "SELECT * FROM payment";
            ps = connection.prepareStatement(query);

            resultSet = ps.executeQuery();

            while(resultSet.next()){
                String paymentNumber = resultSet.getString("payment_number");
                //add the payment number to the cache
                paymentNumbersCache.add(paymentNumber.toLowerCase());
            }

        }catch(SQLException e){
            throw new DaoException("populatePaymentCache() " + e.getMessage());
        }
        finally{
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("populatePaymentCache() " + e.getMessage());
            }
        }
    }
    @Override
    public List<Payment> findAllPayments() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Payment> payments = new ArrayList<>();

        try{
            connection = getConnection();
            String query = "SELECT * FROM payment";
            ps = connection.prepareStatement(query);

            resultSet = ps.executeQuery();

            while(resultSet.next()){
                int paymentId = resultSet.getInt("payment_id");
                String paymentNumber = resultSet.getString("payment_number");
                String bookingNumber = resultSet.getString("booking_number");
                double amountPaid = resultSet.getDouble("amount_paid");
                String paymentDate = resultSet.getString("payment_date");
                String method = resultSet.getString("method");

                Payment p = new Payment(paymentId, paymentNumber, bookingNumber, amountPaid, paymentDate, method);
                payments.add(p);
            }
        } catch(SQLException e){
            throw new DaoException("findAllPayments() " + e.getMessage());
        } finally{
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllPayments() " + e.getMessage());
            }
        }
        return payments;
    }

    @Override
    public Payment findPaymentByNumber(String paymentNumber) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Payment p = null;

        //check if the payment number is in the cache in both upper and lower case
        if(!paymentNumbersCache.contains(paymentNumber.toLowerCase()) && !paymentNumbersCache.contains(paymentNumber.toUpperCase())){
            return null;
        }

        try{
            connection = getConnection();
            String query = "SELECT * FROM payment WHERE LOWER(payment_number) = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, paymentNumber.toLowerCase());

            resultSet = ps.executeQuery();

            if(resultSet.next()){
                int paymentId = resultSet.getInt("payment_id");
                String bookingNumber = resultSet.getString("booking_number");
                double amountPaid = resultSet.getDouble("amount_paid");
                String paymentDate = resultSet.getString("payment_date");
                String method = resultSet.getString("method");

                p = new Payment(paymentId, paymentNumber, bookingNumber, amountPaid, paymentDate, method);
            }
        } catch(SQLException e){
            throw new DaoException("findPaymentByNumber() " + e.getMessage());
        } finally{
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findPaymentByNumber() " + e.getMessage());
            }
        }
        return p;
    }

    @Override
    public boolean deletePaymentByNumber(String paymentNumber) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        boolean deleted = false;

        try{
            connection = getConnection();
            String query = "DELETE FROM payment WHERE LOWER(payment_number) = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, paymentNumber.toLowerCase());

            int result = ps.executeUpdate();
            if(result == 1){
                deleted = true;

                //remove the payment number from the cache
                paymentNumbersCache.remove(paymentNumber.toLowerCase());
            }
        } catch(SQLException e){
            throw new DaoException("deletePaymentByNumber() " + e.getMessage());
        } finally{
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("deletePaymentByNumber() " + e.getMessage());
            }
        }
        return deleted;
    }

    @Override
    public Payment insertPayment(Payment payment) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Payment p = null;

        try{
            connection = getConnection();
            String query = "INSERT INTO payment (payment_number, booking_number, amount_paid, payment_date, method) VALUES (?,?,?,?,?)";
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, payment.getPayment_number());
            ps.setString(2, payment.getBooking_number());
            ps.setDouble(3, payment.getAmount_paid());
            ps.setString(4, payment.getPayment_date());
            ps.setString(5, payment.getMethod());

            int result = ps.executeUpdate();
            if(result == 1){
                resultSet = ps.getGeneratedKeys();
                if(resultSet.next()){
                    int paymentId = resultSet.getInt(1);
                    p = new Payment(paymentId, payment.getPayment_number().toLowerCase(), payment.getBooking_number(), payment.getAmount_paid(), payment.getPayment_date(), payment.getMethod());
                }

                //add the payment number to the cache
                paymentNumbersCache.add(payment.getPayment_number().toLowerCase());
            }
        } catch(SQLException e){
            throw new DaoException("insertPayment() " + e.getMessage());
        } finally{
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("insertPayment() " + e.getMessage());
            }
        }
        return p;
    }

    @Override
    public List<Payment> findAllPaymentsByBookingNumber(String bookingNumber) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Payment> payments = new ArrayList<>();

        try{
            connection = getConnection();
            String query = "SELECT * FROM payment WHERE LOWER(booking_number) = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, bookingNumber.toLowerCase());

            resultSet = ps.executeQuery();

            while(resultSet.next()){
                int paymentId = resultSet.getInt("payment_id");
                String paymentNumber = resultSet.getString("payment_number");
                double amountPaid = resultSet.getDouble("amount_paid");
                String paymentDate = resultSet.getString("payment_date");
                String method = resultSet.getString("method");

                Payment p = new Payment(paymentId, paymentNumber, bookingNumber, amountPaid, paymentDate, method);
                payments.add(p);
            }
        } catch(SQLException e){
            throw new DaoException("findAllPaymentsByBookingNumber() " + e.getMessage());
        } finally{
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findAllPaymentsByBookingNumber() " + e.getMessage());
            }
        }
        return payments;
    }

    @Override
    public Set<String> uniquePaymentMethod() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Set<String> paymentMethods = new HashSet<>();

        try{
            connection = getConnection();
            String query = "SELECT DISTINCT method FROM payment";
            ps = connection.prepareStatement(query);

            resultSet = ps.executeQuery();

            while(resultSet.next()){
                String method = resultSet.getString("method");
                paymentMethods.add(method);
            }
        } catch(SQLException e){
            throw new DaoException("uniquePaymentMethod() " + e.getMessage());
        } finally{
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("uniquePaymentMethod() " + e.getMessage());
            }
        }
        return paymentMethods;
    }

    @Override
    public List<Payment> findPaymentByPaymentMethod(String paymentMethod) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Payment> payments = new ArrayList<>();

        try{
            connection = getConnection();
            String query = "SELECT * FROM payment WHERE LOWER(method) = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, paymentMethod.toLowerCase());

            resultSet = ps.executeQuery();

            while(resultSet.next()){
                int paymentId = resultSet.getInt("payment_id");
                String paymentNumber = resultSet.getString("payment_number");
                String bookingNumber = resultSet.getString("booking_number");
                double amountPaid = resultSet.getDouble("amount_paid");
                String paymentDate = resultSet.getString("payment_date");

                Payment p = new Payment(paymentId, paymentNumber, bookingNumber, amountPaid, paymentDate, paymentMethod);
                payments.add(p);
            }
        } catch(SQLException e){
            throw new DaoException("findPaymentByPaymentMethod() " + e.getMessage());
        } finally{
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (connection != null) {
                    freeConnection(connection);
                }
            } catch (SQLException e) {
                throw new DaoException("findPaymentByPaymentMethod() " + e.getMessage());
            }
        }
        return payments;
    }
}
