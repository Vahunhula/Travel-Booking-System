package Application.DAOs;

import Application.DTOs.Booking;
import Application.Exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlBookingDao extends MySqlDao implements BookingDaoInterface {
    @Override
    public List<Booking> findAllBookings() throws DaoException {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet resultSet = null;
        List<Booking> bookings = new ArrayList<>();
        try {
            connection = getConnection();
            String query = "SELECT * FROM booking";
            ps = connection.prepareStatement(query);
            resultSet = ps.executeQuery();
            while (resultSet.next()) {
                int bookingId = resultSet.getInt("booking_id");
                int customerId = resultSet.getInt("customer_id");
                int flightId = resultSet.getInt("flight_id");
                String travelDate = resultSet.getString("travel_date");
                String travelTime = resultSet.getString("travel_time");
                int seats = resultSet.getInt("seats");

                Booking b = new Booking(bookingId,customerId,flightId,travelDate,travelTime,seats);
                bookings.add(b);
            }
        } catch (SQLException e) {
            throw new DaoException("findAllBookingsresultSet() " + e.getMessage());
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
                throw new DaoException("findAllBookings() " + e.getMessage());
            }
        }
        return bookings;
    }
}

