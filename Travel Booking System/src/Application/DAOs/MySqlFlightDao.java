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
}
