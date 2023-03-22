package Application.DAOs;

import Application.DAOs.MySqlDao;
import Application.Exceptions.DaoException;
import Application.DAOs.CustomerDaoInterface;
import Application.DTOs.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlCustomerDao extends  MySqlDao implements CustomerDaoInterface{
    @Override
    public List<Customer> findAllCustomers() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Customer> customers = new ArrayList<>();

        try{
            connection = getConnection();
            String query = "SELECT * FROM customer";
            ps = connection.prepareStatement(query);

            resultSet = ps.executeQuery();

            while(resultSet.next()){
                int customerId = resultSet.getInt("customer_id");
                String customerName = resultSet.getString("customer_name");
                String customerEmail = resultSet.getString("email");
                String customerPhone = resultSet.getString("tel_num");
                String customerAddress = resultSet.getString("address");

                Customer c = new Customer(customerId,customerName,customerEmail,customerPhone,customerAddress);
                customers.add(c);
            }

        }catch(SQLException e){
            throw new DaoException("findAllCustomersresultSet() " + e.getMessage());
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
                throw new DaoException("findAllCustomers() " + e.getMessage());
            }
        }
        return customers;
    }

    @Override
    public Customer findCustomerById(int customerId) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Customer c = null;

        try{
            connection = getConnection();
            String query = "SELECT * FROM customer WHERE customer_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1,customerId);

            resultSet = ps.executeQuery();

            if(resultSet.next()){
                String customerName = resultSet.getString("customer_name");
                String customerEmail = resultSet.getString("email");
                String customerPhone = resultSet.getString("tel_num");
                String customerAddress = resultSet.getString("address");

                c = new Customer(customerId,customerName,customerEmail,customerPhone,customerAddress);
            }
        }catch(SQLException e){
            throw new DaoException("findCustomerByIdresultSet() " + e.getMessage());
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
                throw new DaoException("findCustomerById() " + e.getMessage());
            }
        }
        return c;
    }

    @Override
    public boolean deleteCustomerById(int customerId) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        boolean deleted = false;

        try{
            connection = getConnection();
            String query = "DELETE FROM customer WHERE customer_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1,customerId);

            int result = ps.executeUpdate();
            if(result == 1){
                deleted = true;
            }
        }catch(SQLException e){
            throw new DaoException("deleteCustomerByIdresultSet() " + e.getMessage());
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
                throw new DaoException("deleteCustomerById() " + e.getMessage());
            }
        }
        return deleted;
    }
}
