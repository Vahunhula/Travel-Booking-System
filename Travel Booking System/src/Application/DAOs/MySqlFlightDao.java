package Application.DAOs;

import Application.DTOs.Flight;
import Application.Exceptions.DaoException;
import Application.DAOs.MySqlDao;
import Application.DAOs.FlightDaoInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlFlightDao extends MySqlDao implements FlightDaoInterface{
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
                int airportId = resultSet.getInt("airport_id");
                String departureLocation = resultSet.getString("departure_location");
                String arrivalLocation = resultSet.getString("arrival_location");
                String airlineName = resultSet.getString("airline_name");
                int duration = resultSet.getInt("duration");
                double flightCost = resultSet.getDouble("flight_cost");

                Flight f = new Flight(flightId,airportId,departureLocation,arrivalLocation,airlineName,duration,flightCost);
                flights.add(f);
            }
        }
        catch (SQLException e) {
            throw new DaoException("findAllFlightsresultSet() " + e.getMessage());
        }
        finally {
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
            }
            catch (SQLException e) {
                throw new DaoException("findAllFlights() " + e.getMessage());
            }
        }
        return flights;
    }

    @Override
    public Flight findFlightById(int flightId) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Flight f = null;

        try {
            connection = getConnection();
            String query = "SELECT * FROM flight WHERE flight_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1,flightId);

            resultSet = ps.executeQuery();

            if (resultSet.next()) {
                int airportId = resultSet.getInt("airport_id");
                String departureLocation = resultSet.getString("departure_location");
                String arrivalLocation = resultSet.getString("arrival_location");
                String airlineName = resultSet.getString("airline_name");
                int duration = resultSet.getInt("duration");
                double flightCost = resultSet.getDouble("flight_cost");

                f = new Flight(flightId,airportId,departureLocation,arrivalLocation,airlineName,duration,flightCost);
            }
        }
        catch (SQLException e) {
            throw new DaoException("findFlightByIdresultSet() " + e.getMessage());
        }
        finally {
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
            }
            catch (SQLException e) {
                throw new DaoException("findFlightById() " + e.getMessage());
            }
        }
        return f;
    }

    @Override
    public boolean deleteFlightById(int flightId) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        boolean deleted = false;

        try{
            connection = getConnection();
            String query = "DELETE FROM flight WHERE flight_id = ?";
            ps = connection.prepareStatement(query);
            ps.setInt(1,flightId);

            int result = ps.executeUpdate();
            if(result == 1){
                deleted = true;
            }
        }catch(SQLException e){
            throw new DaoException("deleteFlightByIdresultSet() " + e.getMessage());
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
                throw new DaoException("deleteFlightById() " + e.getMessage());
            }
        }
        return deleted;
    }

    //@Override
    //    public Customer insertCustomer(Customer customer) throws DaoException {
    //        Connection connection = null;
    //        PreparedStatement ps = null;
    //        ResultSet resultSet = null;
    //        Customer c = null;
    //
    //        try{
    //            connection = getConnection();
    //            String query = "INSERT INTO customer (customer_name, email, tel_num, address) VALUES (?,?,?,?)";
    //            ps = connection.prepareStatement(query);
    ////            ps.setInt(1,customer.getCustomer_id());
    //            ps.setString(1,customer.getCustomer_name());
    //            ps.setString(2,customer.getEmail());
    //            ps.setString(3,customer.getTel_num());
    //            ps.setString(4,customer.getAddress());
    //
    //            int result = ps.executeUpdate();
    //            if(result == 1){
    //                c = customer;
    //            }
    //        }catch(SQLException e){
    //            throw new DaoException("insertCustomerresultSet() " + e.getMessage());
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
    //                throw new DaoException("insertCustomer() " + e.getMessage());
    //            }
    //        }
    //        return c;
    //    }


    @Override
    public Flight insertFlight(Flight flight) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Flight f = null;

        try{
            connection = getConnection();
            String query = "INSERT INTO flight (airport_id, departure_location, arrival_location, airline_name, duration, flight_cost) VALUES (?,?,?,?,?,?)";
            ps = connection.prepareStatement(query);
            ps.setInt(1,flight.getAirport_id());
            ps.setString(2,flight.getDeparture_location());
            ps.setString(3,flight.getArrival_location());
            ps.setString(4,flight.getAirline_name());
            ps.setInt(5,flight.getDuration());
            ps.setDouble(6,flight.getFlight_cost());

            int result = ps.executeUpdate();
            if(result == 1){
                f = flight;
            }
        }catch(SQLException e){
            throw new DaoException("insertFlightresultSet() " + e.getMessage());
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
                throw new DaoException("insertFlight() " + e.getMessage());
            }
        }
        return f;
    }
}
