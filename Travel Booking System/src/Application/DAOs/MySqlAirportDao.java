package Application.DAOs;

import Application.DAOs.MySqlDao;
import Application.DTOs.Airport;
import Application.Exceptions.DaoException;
import Application.DAOs.CustomerDaoInterface;
import Application.DTOs.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MySqlAirportDao extends MySqlDao implements AirportDaoInterface{
    //private String airport_number
    //private String airport_name
    //private String airport_location


    @Override
    public List<Airport> findAllAirports() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Airport> airports = new ArrayList<>();

        try{
            connection = getConnection();
            String query = "SELECT * FROM airport";
            ps = connection.prepareStatement(query);

            resultSet = ps.executeQuery();

            while(resultSet.next()){
                int airportId = resultSet.getInt("airport_id");
                String airportNumber = resultSet.getString("airport_number");
                String airportName = resultSet.getString("airport_name");
                String airportLocation = resultSet.getString("airport_location");

                Airport a = new Airport(airportId,airportNumber,airportName,airportLocation);
                airports.add(a);
            }

        }catch(SQLException e){
            throw new DaoException("findAllAirportsresultSet() " + e.getMessage());
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
                throw new DaoException("findAllAirports() " + e.getMessage());
            }
        }
        return airports;
    }

    @Override
    public Airport findAirportByNumber(String airportNumber) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Airport a = null;

        try{
            connection = getConnection();
            String query = "SELECT * FROM airport WHERE LOWER(airport_number) = LOWER(?)";
            ps = connection.prepareStatement(query);
            ps.setString(1,airportNumber);

            resultSet = ps.executeQuery();

            if(resultSet.next()){
                int airportId = resultSet.getInt("airport_id");
                String airportName = resultSet.getString("airport_name");
                String airportLocation = resultSet.getString("airport_location");

                a = new Airport(airportId,airportNumber, airportName, airportLocation);
            }
        } catch(SQLException e){
            throw new DaoException("findAirportByNumber() " + e.getMessage());
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
                throw new DaoException("findAirportByNumber() " + e.getMessage());
            }
        }
        return a;
    }

    @Override
    public boolean deleteAirportByNumber(String airportNumber) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        boolean deleted = false;

        try{
            connection = getConnection();
            String query = "DELETE FROM airport WHERE LOWER(airport_number) = LOWER(?)";
            ps = connection.prepareStatement(query);
            ps.setString(1,airportNumber);

            int rowsAffected = ps.executeUpdate();

            if(rowsAffected == 1){
                deleted = true;
            }
        } catch(SQLException e){
            throw new DaoException("deleteAirportByNumber() " + e.getMessage());
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
                throw new DaoException("deleteAirportByNumber() " + e.getMessage());
            }
        }
        return deleted;
    }

    @Override
    public Airport insertAirport(Airport airport) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Airport a = null;

        try {
            connection = getConnection();
            String query = "INSERT INTO airport (airport_number, airport_name, airport_location) VALUES (?,?,?)";
            ps = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, airport.getAirport_number());
            ps.setString(2, airport.getAirport_name());
            ps.setString(3, airport.getAirport_location());

            int result = ps.executeUpdate();
            if (result == 1) {
                resultSet = ps.getGeneratedKeys();
                if (resultSet.next()) {
                    int airport_id = resultSet.getInt(1);
                    a = new Airport(airport_id, airport.getAirport_number(), airport.getAirport_name(), airport.getAirport_location());
                }
            }
        } catch (SQLException e) {
            throw new DaoException("insertAirportresultSet() " + e.getMessage());
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
                throw new DaoException("insertAirport() " + e.getMessage());
            }
        }
        return a;
    }

    @Override
    public Set<String> uniqueAirportLocation() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        //in case can easily change to TreeSet
        HashSet<String> airportLocations = null;

        try{
            connection = getConnection();
            String query = "SELECT DISTINCT airport_location FROM airport";
            ps = connection.prepareStatement(query);

            resultSet = ps.executeQuery();

            while(resultSet.next()){
                String airportLocation = resultSet.getString("airport_location");
                airportLocations.add(airportLocation);
            }

        }catch(SQLException e){
            throw new DaoException("uniqueAirportLocationresultSet() " + e.getMessage());
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
                throw new DaoException("uniqueAirportLocation() " + e.getMessage());
            }
        }
        return airportLocations;
    }
}
