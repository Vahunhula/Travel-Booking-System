package Application.BusinessObjects;

import Application.DAOs.*;
import Application.DTOs.*;
import Application.Exceptions.DaoException;
import Application.Protocol.MenuOptions;
import Application.Protocol.Packet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Scanner;

public class BookingObj {
    static Helpers helper;
    static BookingDaoInterface bookingDao = new MySqlBookingDao();
    static PaymentDaoInterface paymentDao = new MySqlPaymentDao();

    private Scanner input;
    private PrintWriter output;

    public BookingObj(Scanner input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    //display all bookings
    public void findAllBookings() {
        Packet request = new Packet(MenuOptions.BookingMenuOptions.FIND_ALL_BOOKINGS, null);
        String jsonRequest = request.toJson();
        output.println(jsonRequest);
        output.flush();

        String jsonResponse = input.nextLine();
        Packet response = Packet.fromJson(jsonResponse);

        if (response.getException() != null) {
            System.out.println("Error: " + response.getException().getMessage());
        } else {
            String jsonBookings = (String) response.getData();
            Type bookingListType = new TypeToken<List<Booking>>(){}.getType();
            List<Booking> bookings = new Gson().fromJson(jsonBookings, bookingListType);

            if (bookings.isEmpty()) {
                System.out.println("No bookings found.");
            }
            for (Booking booking : bookings) {
                System.out.println(booking.toString());
            }
        }
    }

    //to find booking by number and also if no booking found then it will display no booking found
    //and if booking found then it will display booking details(bookingNumber is a String)
    public void findBookingByNumber() {
        helper = new Helpers(input, output);
        String bookingNumber = helper.readString("Enter booking number: ");
        Packet request = new Packet(MenuOptions.BookingMenuOptions.FIND_BOOKING_BY_NUMBER, bookingNumber);
        String jsonRequest = request.toJson();
        output.println(jsonRequest);
        output.flush();

        String jsonResponse = input.nextLine();
        Packet response = Packet.fromJson(jsonResponse);

        if (response.getException() != null) {
            System.out.println("Error: " + response.getException().getMessage());
        } else {
            String jsonBooking = (String) response.getData();
            Booking booking = new Gson().fromJson(jsonBooking, Booking.class);
            if (booking == null) {
                System.out.println("No booking found.");
            } else {
                System.out.println(booking);
            }
        }
    }

    //to delete booking by number and also if no booking found then it will display no booking found
    //and also check if booking has related records in payment table then it will display those records
    public void deleteBookingByNumber() {
        helper = new Helpers(input, output);
        String bookingNumber = helper.readString("Enter booking number: ");
        Packet request = new Packet(MenuOptions.BookingMenuOptions.DELETE_BOOKING_BY_NUMBER, bookingNumber);
        String jsonRequest = request.toJson();
        output.println(jsonRequest);
        output.flush();

        String jsonResponse = input.nextLine();
        Packet response = Packet.fromJson(jsonResponse);

        if (response.getException() != null) {
            String exceptionMessage = response.getException().getMessage();
            System.out.println("Error: " + (exceptionMessage == null ? "Unknown error" : exceptionMessage));

            if (response.getData() != null) {
                System.out.println("Booking-" + bookingNumber + " cannot be deleted because it has related records in the database:");

                // The related payments can be sent as part of the response in the 'data' field.
                String jsonPayments = (String) response.getData();
                Type paymentListType = new TypeToken<List<Payment>>(){}.getType();
                List<Payment> payments = new Gson().fromJson(jsonPayments, paymentListType);

                for (Payment payment : payments) {
                    System.out.println(payment);
                }
            }
        } else {
            boolean deleted = Boolean.parseBoolean((String) response.getData());
            if (deleted) {
                System.out.println("Booking deleted.");
            } else {
                System.out.println("No booking found.");
            }
        }
    }

    //to insert a new booking
    public void insertBooking() {
        helper = new Helpers(input, output);
        String bookingNumber = helper.readInputField("bookingNumber", 10);
        String flightNumber = helper.checkFlightNumber();
        String customerNumber = helper.checkCustomerNumber();
        String travelDate = helper.readTravelDate();
        String seatNumber = helper.readSeatNumber();

        Booking booking = new Booking(bookingNumber, flightNumber, customerNumber, travelDate, seatNumber);
        Packet request = new Packet(MenuOptions.BookingMenuOptions.INSERT_BOOKING, booking);
        String jsonRequest = request.toJson();
        output.println(jsonRequest);
        output.flush();

        String jsonResponse = input.nextLine();
        Packet response = Packet.fromJson(jsonResponse);

        if (response.getException() != null) {
            System.out.println("Error inserting booking: " + response.getExceptionMessage());
        } else {
            String jsonBooking = (String) response.getData();
            Booking b = new Gson().fromJson(jsonBooking, Booking.class);
            if (b == null) {
                System.out.println("Booking Was Not Inserted.");
            } else {
                System.out.println(b);
                System.out.println("Booking inserted.");
            }
        }
    }
}
