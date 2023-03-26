package Application.DAOs;

import Application.DAOs.MySqlDao;
import Application.Exceptions.DaoException;
import Application.DAOs.CustomerDaoInterface;
import Application.DTOs.Customer;

import java.sql.*;
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
                String customerNumber = resultSet.getString("customer_number");
                String customerName = resultSet.getString("customer_name");
                String customerEmail = resultSet.getString("email");
                String customerPhone = resultSet.getString("tel_num");
                String customerAddress = resultSet.getString("address");

                Customer c = new Customer(customerId,customerNumber,customerName,customerEmail,customerPhone,customerAddress);
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
    public Customer findCustomerByNumber(String customerNumber) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Customer c = null;

        try{
            connection = getConnection();
            String query = "SELECT * FROM customer WHERE LOWER(customer_number) = LOWER(?)";
            ps = connection.prepareStatement(query);
            ps.setString(1,customerNumber);

            resultSet = ps.executeQuery();

            if(resultSet.next()){
                int customerId = resultSet.getInt("customer_id");
                String customerName = resultSet.getString("customer_name");
                String customerEmail = resultSet.getString("email");
                String customerPhone = resultSet.getString("tel_num");
                String customerAddress = resultSet.getString("address");

                c = new Customer(customerId,customerNumber, customerName, customerEmail, customerPhone, customerAddress);
            }
        } catch(SQLException e){
            throw new DaoException("findCustomerByNumber() " + e.getMessage());
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
                throw new DaoException("findCustomerByNumber() " + e.getMessage());
            }
        }
        return c;
    }

    @Override
    public boolean deleteCustomerByNumber(String customerNumber) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        boolean deleted = false;

        try {
            connection = getConnection();
            String query = "DELETE FROM customer WHERE LOWER(customer_number) = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, customerNumber.toLowerCase());

            int result = ps.executeUpdate();
            if (result == 1) {
                deleted = true;
            }
        } catch (SQLException e) {
            throw new DaoException("deleteCustomerByNumber() " + e.getMessage());
        } finally {
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
                throw new DaoException("deleteCustomerByNumber() " + e.getMessage());
            }
        }
        return deleted;
    }

    @Override
    public Customer insertCustomer(Customer customer) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Customer c = null;

        try {
            connection = getConnection();
            String query = "INSERT INTO customer (customer_number, customer_name, email, tel_num, address) VALUES (?,?,?,?,?)";
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customer.getCustomer_number());
            ps.setString(2, customer.getCustomer_name());
            ps.setString(3, customer.getEmail());
            ps.setString(4, customer.getTel_num());
            ps.setString(5, customer.getAddress());

            int result = ps.executeUpdate();
            if (result == 1) {
                resultSet = ps.getGeneratedKeys();
                if (resultSet.next()) {
                    int customerId = resultSet.getInt(1);
                    c = new Customer(customerId, customer.getCustomer_number(), customer.getCustomer_name(), customer.getEmail(), customer.getTel_num(), customer.getAddress());
                }
            }
        } catch (SQLException e) {
            throw new DaoException("insertCustomerresultSet() " + e.getMessage());
        } finally {
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
                throw new DaoException("insertCustomer() " + e.getMessage());
            }
        }
        return c;
    }

    @Override
    public boolean checkIfEmailExists(String email) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        boolean exists = false;

        try {
            connection = getConnection();
            String query = "SELECT * FROM customer WHERE LOWER(email) = LOWER(?)";
            ps = connection.prepareStatement(query);
            ps.setString(1, email);

            resultSet = ps.executeQuery();
            if (resultSet.next()) {
                exists = true;
            }
        } catch (SQLException e) {
            throw new DaoException("checkIfEmailExists() " + e.getMessage());
        } finally {
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
                throw new DaoException("checkIfEmailExists() " + e.getMessage());
            }
        }
        return exists;
    }
}
