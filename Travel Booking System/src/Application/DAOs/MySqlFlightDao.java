package Application.DAOs;

import Application.DTOs.Flight;
import Application.Exceptions.DaoException;
import Application.DAOs.MySqlDao;
import Application.DAOs.FlightDaoInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MySqlFlightDao extends MySqlDao implements FlightDaoInterface{
    // @Override
    //    public List<Customer> findAllCustomers() throws DaoException {
    //        Connection connection = null;
    //        PreparedStatement ps = null;
    //        ResultSet resultSet = null;
    //        List<Customer> customers = new ArrayList<>();
    //
    //        try{
    //            connection = getConnection();
    //            String query = "SELECT * FROM customer";
    //            ps = connection.prepareStatement(query);
    //
    //            resultSet = ps.executeQuery();
    //
    //            while(resultSet.next()){
    //                int customerId = resultSet.getInt("customer_id");
    //                String customerNumber = resultSet.getString("customer_number");
    //                String customerName = resultSet.getString("customer_name");
    //                String customerEmail = resultSet.getString("email");
    //                String customerPhone = resultSet.getString("tel_num");
    //                String customerAddress = resultSet.getString("address");
    //
    //                Customer c = new Customer(customerId,customerNumber,customerName,customerEmail,customerPhone,customerAddress);
    //                customers.add(c);
    //            }
    //
    //        }catch(SQLException e){
    //            throw new DaoException("findAllCustomersresultSet() " + e.getMessage());
    //        }
    //        finally{
    //            try {
    //                if (resultSet != null) {
    //                    resultSet.close();
    //                }
    //                if (ps != null) {
    //                    ps.close();
    //                }
    //                if (connection != null) {
    //                    freeConnection(connection);
    //                }
    //            } catch (SQLException e) {
    //                throw new DaoException("findAllCustomers() " + e.getMessage());
    //            }
    //        }
    //        return customers;
    //    }
    //
    //    @Override
    //    public Customer findCustomerByNumber(String customerNumber) throws DaoException {
    //        Connection connection = null;
    //        PreparedStatement ps = null;
    //        ResultSet resultSet = null;
    //        Customer c = null;
    //
    //        try{
    //            connection = getConnection();
    //            String query = "SELECT * FROM customer WHERE LOWER(customer_number) = LOWER(?)";
    //            ps = connection.prepareStatement(query);
    //            ps.setString(1,customerNumber);
    //
    //            resultSet = ps.executeQuery();
    //
    //            if(resultSet.next()){
    //                int customerId = resultSet.getInt("customer_id");
    //                String customerName = resultSet.getString("customer_name");
    //                String customerEmail = resultSet.getString("email");
    //                String customerPhone = resultSet.getString("tel_num");
    //                String customerAddress = resultSet.getString("address");
    //
    //                c = new Customer(customerId,customerNumber, customerName, customerEmail, customerPhone, customerAddress);
    //            }
    //        } catch(SQLException e){
    //            throw new DaoException("findCustomerByNumber() " + e.getMessage());
    //        } finally{
    //            try {
    //                if (resultSet != null) {
    //                    resultSet.close();
    //                }
    //                if (ps != null) {
    //                    ps.close();
    //                }
    //                if (connection != null) {
    //                    freeConnection(connection);
    //                }
    //            } catch (SQLException e) {
    //                throw new DaoException("findCustomerByNumber() " + e.getMessage());
    //            }
    //        }
    //        return c;
    //    }
    //
    //    @Override
    //    public boolean deleteCustomerByNumber(String customerNumber) throws DaoException {
    //        Connection connection = null;
    //        PreparedStatement ps = null;
    //        ResultSet resultSet = null;
    //        boolean deleted = false;
    //
    //        try {
    //            connection = getConnection();
    //            String query = "DELETE FROM customer WHERE LOWER(customer_number) = ?";
    //            ps = connection.prepareStatement(query);
    //            ps.setString(1, customerNumber.toLowerCase());
    //
    //            int result = ps.executeUpdate();
    //            if (result == 1) {
    //                deleted = true;
    //            }
    //        } catch (SQLException e) {
    //            throw new DaoException("deleteCustomerByNumber() " + e.getMessage());
    //        } finally {
    //            try {
    //                if (resultSet != null) {
    //                    resultSet.close();
    //                }
    //                if (ps != null) {
    //                    ps.close();
    //                }
    //                if (connection != null) {
    //                    freeConnection(connection);
    //                }
    //            } catch (SQLException e) {
    //                throw new DaoException("deleteCustomerByNumber() " + e.getMessage());
    //            }
    //        }
    //        return deleted;
    //    }
    //
    //    @Override
    //    public Customer insertCustomer(Customer customer) throws DaoException {
    //        Connection connection = null;
    //        PreparedStatement ps = null;
    //        ResultSet resultSet = null;
    //        Customer c = null;
    //
    //        try {
    //            connection = getConnection();
    //            String query = "INSERT INTO customer (customer_number, customer_name, email, tel_num, address) VALUES (?,?,?,?,?)";
    //            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
    //            ps.setString(1, customer.getCustomer_number());
    //            ps.setString(2, customer.getCustomer_name());
    //            ps.setString(3, customer.getEmail());
    //            ps.setString(4, customer.getTel_num());
    //            ps.setString(5, customer.getAddress());
    //
    //            int result = ps.executeUpdate();
    //            if (result == 1) {
    //                resultSet = ps.getGeneratedKeys();
    //                if (resultSet.next()) {
    //                    int customerId = resultSet.getInt(1);
    //                    c = new Customer(customerId, customer.getCustomer_number(), customer.getCustomer_name(), customer.getEmail(), customer.getTel_num(), customer.getAddress());
    //                }
    //            }
    //        } catch (SQLException e) {
    //            throw new DaoException("insertCustomerresultSet() " + e.getMessage());
    //        } finally {
    //            try {
    //                if (resultSet != null) {
    //                    resultSet.close();
    //                }
    //                if (ps != null) {
    //                    ps.close();
    //                }
    //                if (connection != null) {
    //                    freeConnection(connection);
    //                }
    //            } catch (SQLException e) {
    //                throw new DaoException("insertCustomer() " + e.getMessage());
    //            }
    //        }
    //        return c;
    //    }


    @Override
    public List<Flight> findAllFlights() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Flight> flights = new ArrayList<>();

        try {
            connection = getConnection();
            String query = "SELECT * FROM flight";
            ps = connection.prepareStatement(query);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int flightId = resultSet.getInt("flight_id");
                String flightNumber = resultSet.getString("flight_number");
                String airportNumber = resultSet.getString("airport_number");
                String departureLocation = resultSet.getString("departure_location");
                String arrivalLocation = resultSet.getString("arrival_location");
                String airlineName = resultSet.getString("airline_name");
                int duration = resultSet.getInt("duration");
                double flightCost = resultSet.getDouble("flight_cost");

                Flight flight = new Flight(flightId, flightNumber, airportNumber, departureLocation, arrivalLocation, airlineName, duration, flightCost);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllFlights() " + e.getMessage());
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
                throw new DaoException("findAllFlights() " + e.getMessage());
            }
        }
        return flights;
    }

    @Override
    public Flight findFlightByNumber(String flightNumber) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Flight flight = null;

        try {
            connection = getConnection();
            String query = "SELECT * FROM flight WHERE LOWER(flight_number) = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, flightNumber.toLowerCase());

            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                int flightId = resultSet.getInt("flight_id");
                String airportNumber = resultSet.getString("airport_number");
                String departureLocation = resultSet.getString("departure_location");
                String arrivalLocation = resultSet.getString("arrival_location");
                String airlineName = resultSet.getString("airline_name");
                int duration = resultSet.getInt("duration");
                double flightCost = resultSet.getDouble("flight_cost");

                flight = new Flight(flightId, flightNumber, airportNumber, departureLocation, arrivalLocation, airlineName, duration, flightCost);
            }
        } catch (SQLException e) {
            throw new DaoException("findFlightByNumber() " + e.getMessage());
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
                throw new DaoException("findFlightByNumber() " + e.getMessage());
            }
        }
        return flight;
    }

    @Override
    public boolean deleteFlightByNumber(String flightNumber) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        boolean deleted = false;

        try {
            connection = getConnection();
            String query = "DELETE FROM flight WHERE LOWER(flight_number) = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, flightNumber.toLowerCase());

            int result = ps.executeUpdate();
            if (result == 1) {
                deleted = true;
            }
        } catch (SQLException e) {
            throw new DaoException("deleteFlightByNumber() " + e.getMessage());
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
                throw new DaoException("deleteFlightByNumber() " + e.getMessage());
            }
        }
        return deleted;
    }

    @Override
    public Flight insertFlight(Flight flight) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Flight f = null;

        try {
            connection = getConnection();
            String query = "INSERT INTO flight (flight_number, airport_number, departure_location, arrival_location, airline_name, duration, flight_cost) VALUES (?,?,?,?,?,?,?)";
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, flight.getFlight_number());
            ps.setString(2, flight.getAirport_number());
            ps.setString(3, flight.getDeparture_location());
            ps.setString(4, flight.getArrival_location());
            ps.setString(5, flight.getAirline_name());
            ps.setInt(6, flight.getDuration());
            ps.setDouble(7, flight.getFlight_cost());

            int result = ps.executeUpdate();
            if (result == 1) {
                resultSet = ps.getGeneratedKeys();
                if (resultSet.next()) {
                    int flightId = resultSet.getInt(1);
                    f = new Flight(flightId, flight.getFlight_number(), flight.getAirport_number(), flight.getDeparture_location(), flight.getArrival_location(), flight.getAirline_name(), flight.getDuration(), flight.getFlight_cost());
                }
            }
        } catch (SQLException e) {
            throw new DaoException("insertFlight() " + e.getMessage());
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
                throw new DaoException("insertFlight() " + e.getMessage());
            }
        }
        return f;
    }
}
