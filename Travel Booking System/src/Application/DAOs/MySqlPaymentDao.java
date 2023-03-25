package Application.DAOs;

import Application.DTOs.Payment;
import Application.DAOs.MySqlDao;
import Application.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlPaymentDao extends MySqlDao implements PaymentDaoInterface{
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
                int bookingId = resultSet.getInt("booking_id");
                double amountPaid = resultSet.getDouble("amount_paid");
                String paymentDate = resultSet.getString("payment_date");
                String method = resultSet.getString("method");

                Payment p = new Payment(paymentId,bookingId,amountPaid,paymentDate,method);
                payments.add(p);
            }

        }catch(SQLException e){
            throw new DaoException("findAllPaymentsresultSet() " + e.getMessage());
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
                throw new DaoException("findAllPayments() " + e.getMessage());
            }
        }
        return payments;
    }
    @Override
    public Payment findPaymentById(int paymentId) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Payment p = null;

        try{
            connection = getConnection();
            String query = "SELECT * FROM payment WHERE payment_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1,paymentId);

            resultSet = ps.executeQuery();

            if(resultSet.next()){
                int bookingId = resultSet.getInt("booking_id");
                double amountPaid = resultSet.getDouble("amount_paid");
                String paymentDate = resultSet.getString("payment_date");
                String method = resultSet.getString("method");

                p = new Payment(paymentId,bookingId,amountPaid,paymentDate,method);
            }
        }catch(SQLException e){
            throw new DaoException("findPaymentByIdresultSet() " + e.getMessage());
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
                throw new DaoException("findPaymentById() " + e.getMessage());
            }
        }
        return p;
    }
}
