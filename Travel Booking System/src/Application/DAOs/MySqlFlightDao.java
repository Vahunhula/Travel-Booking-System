package Application.DAOs;

import Application.DTOs.Flight;
import Application.Exceptions.DaoException;
import Application.DAOs.MySqlDao;
import Application.DAOs.FlightDaoInterface;

import java.sql.*;
import java.util.*;

public class MySqlFlightDao extends MySqlDao implements FlightDaoInterface {
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
                String departureTime = resultSet.getString("departure_time");
                String arrivalLocation = resultSet.getString("arrival_location");
                String arrivalTime = resultSet.getString("arrival_time");
                String airlineName = resultSet.getString("airline_name");
                double flightCost = resultSet.getDouble("flight_cost");

                Flight flight = new Flight(flightId, flightNumber, airportNumber, departureLocation, departureTime, arrivalLocation, arrivalTime, airlineName, flightCost);
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
                String departureTime = resultSet.getString("departure_time");
                String arrivalLocation = resultSet.getString("arrival_location");
                String arrivalTime = resultSet.getString("arrival_time");
                String airlineName = resultSet.getString("airline_name");
                double flightCost = resultSet.getDouble("flight_cost");

                flight = new Flight(flightId, flightNumber, airportNumber, departureLocation, departureTime, arrivalLocation, arrivalTime, airlineName, flightCost);
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
            ps.setString(4, flight.getDeparture_time());
            ps.setString(5, flight.getArrival_location());
            ps.setString(6, flight.getArrival_time());
            ps.setString(7, flight.getAirline_name());
            ps.setDouble(8, flight.getFlight_cost());

            int result = ps.executeUpdate();
            if (result == 1) {
                resultSet = ps.getGeneratedKeys();
                if (resultSet.next()) {
                    int flightId = resultSet.getInt(1);
                    f = new Flight(flightId, flight.getFlight_number(), flight.getAirport_number(), flight.getDeparture_location(), flight.getDeparture_time(), flight.getArrival_location(), flight.getArrival_time(), flight.getAirline_name(), flight.getFlight_cost());
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

    @Override
    public List<Flight> findAllFlightsByAirportNumber(String airportNumber) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Flight> flights = new ArrayList<>();

        try {
            connection = getConnection();
            String query = "SELECT * FROM flight WHERE LOWER(airport_number) = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, airportNumber.toLowerCase());

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int flightId = resultSet.getInt("flight_id");
                String flightNumber = resultSet.getString("flight_number");
                String departureLocation = resultSet.getString("departure_location");
                String departureTime = resultSet.getString("departure_time");
                String arrivalLocation = resultSet.getString("arrival_location");
                String arrivalTime = resultSet.getString("arrival_time");
                String airlineName = resultSet.getString("airline_name");
                double flightCost = resultSet.getDouble("flight_cost");

                Flight flight = new Flight(flightId, flightNumber, airportNumber, departureLocation, departureTime, arrivalLocation, arrivalTime, airlineName, flightCost);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllFlightsByAirportNumber() " + e.getMessage());
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
                throw new DaoException("findAllFlightsByAirportNumber() " + e.getMessage());
            }
        }
        return flights;
    }

    @Override
    public Set<String> uniqueAirlineName() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Set<String> airlineNames = new HashSet<>();

        try {
            connection = getConnection();
            String query = "SELECT DISTINCT airline_name FROM flight";
            ps = connection.prepareStatement(query);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                String airlineName = resultSet.getString("airline_name");
                airlineNames.add(airlineName);
            }
        } catch (SQLException e) {
            throw new DaoException("uniqueAirlineName() " + e.getMessage());
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
                throw new DaoException("uniqueAirlineName() " + e.getMessage());
            }
        }
        return airlineNames;
    }

    @Override
    public List<Flight> findFlightByAirlineName(String airlineName) throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Flight> flights = new ArrayList<>();

        try {
            connection = getConnection();
            String query = "SELECT * FROM flight WHERE LOWER(airline_name) = ?";
            ps = connection.prepareStatement(query);
            ps.setString(1, airlineName.toLowerCase());

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int flightId = resultSet.getInt("flight_id");
                String flightNumber = resultSet.getString("flight_number");
                String airportNumber = resultSet.getString("airport_number");
                String departureLocation = resultSet.getString("departure_location");
                String departureTime = resultSet.getString("departure_time");
                String arrivalLocation = resultSet.getString("arrival_location");
                String arrivalTime = resultSet.getString("arrival_time");
                double flightCost = resultSet.getDouble("flight_cost");

                Flight flight = new Flight(flightId, flightNumber, airportNumber, departureLocation, departureTime, arrivalLocation, arrivalTime, airlineName, flightCost);
                flights.add(flight);
            }
        } catch (SQLException e) {
            throw new DaoException("findFlightByAirlineName() " + e.getMessage());
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
                throw new DaoException("findFlightByAirlineName() " + e.getMessage());
            }
        }
        return flights;
    }

    //to store the deparure time in morning, afternoon and night using a map that i will use in the App to filter the flights, return a map
    public Map<String, List<Flight>> timeOfFlight() throws DaoException {
        //check the departure time if it is between 00:00 and 11:59 then it is morning, 12:00 and 17:59 is afternoon and 18:00 and 23:59 is night
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        Map<String, List<Flight>> timeOfFlight = new HashMap<>();
        List<Flight> morningFlights = new ArrayList<>();
        List<Flight> afternoonFlights = new ArrayList<>();
        List<Flight> nightFlights = new ArrayList<>();

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
                String departureTime = resultSet.getString("departure_time");
                String arrivalLocation = resultSet.getString("arrival_location");
                String arrivalTime = resultSet.getString("arrival_time");
                String airlineName = resultSet.getString("airline_name");
                double flightCost = resultSet.getDouble("flight_cost");

                Flight flight = new Flight(flightId, flightNumber, airportNumber, departureLocation, departureTime, arrivalLocation, arrivalTime, airlineName, flightCost);

                //check the departure time if it is between 00:00 and 11:59 then it is morning, 12:00 and 17:59 is afternoon and 18:00 and 23:59 is night
                //using the compareTo method of the String class
                if (departureTime.compareTo("00:00:00") >= 0 && departureTime.compareTo("11:59:59") <= 0) {
                    morningFlights.add(flight);
                } else if (departureTime.compareTo("12:00:00") >= 0 && departureTime.compareTo("17:59:59") <= 0) {
                    afternoonFlights.add(flight);
                } else if (departureTime.compareTo("18:00:00") >= 0 && departureTime.compareTo("23:59:59") <= 0) {
                    nightFlights.add(flight);
                }
            }
            timeOfFlight.put("morning", morningFlights);
            timeOfFlight.put("afternoon", afternoonFlights);
            timeOfFlight.put("night", nightFlights);
        } catch (SQLException e) {
            throw new DaoException("timeOfFlight() " + e.getMessage());
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
                throw new DaoException("timeOfFlight() " + e.getMessage());
            }
        }
        return timeOfFlight;
    }
}
