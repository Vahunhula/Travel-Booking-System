package Application.DAOs;

import Application.DAOs.MySqlDao;
import Application.DTOs.Airport;
import Application.Exceptions.DaoException;
import Application.DAOs.CustomerDaoInterface;
import Application.DTOs.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class MySqlAirportDao extends MySqlDao implements AirportDaoInterface{
    @Override
    public List<Airport> findAllAirports() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Airport> airports = new ArrayList<>();

        try {
            connection = getConnection();
            String query = "SELECT * FROM airport";
            ps = connection.prepareStatement(query);

            resultSet = ps.executeQuery();

            while (resultSet.next()) {
                int airportId = resultSet.getInt("airport_id");
                String airportName = resultSet.getString("airport_name");
                String airportLocation = resultSet.getString("airport_location");

                Airport a = new Airport(airportId,airportName,airportLocation);
                airports.add(a);
            }
        }
        catch (SQLException e) {
            throw new DaoException("findAllAirportsresultSet() " + e.getMessage());
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
                throw new DaoException("findAllAirports() " + e.getMessage());
            }
        }
        return airports;
    }
}
