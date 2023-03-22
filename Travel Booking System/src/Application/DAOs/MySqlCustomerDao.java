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
            String query = "SELECT * FROM customers";
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
}
