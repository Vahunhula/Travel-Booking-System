package Application.DTOs;

public class Booking {
    //-- Create Booking table
    //CREATE TABLE booking (
    //    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    //    flight_id INT NOT NULL,
    //    customer_id INT NOT NULL,
    //    travel_date DATE NOT NULL,
    //    travel_time TIME NOT NULL,
    //    seats INT NOT NULL,
    //    FOREIGN KEY (flight_id) REFERENCES flight(flight_id),
    //    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
    //);

    private int booking_id;
    private static int booking_id_counter = 0;
    private int flight_id;
    private int customer_id;
    private String travel_date;
    private String travel_time;
    private int seats;

    //default constructor
    public Booking() {
    }

    //constructor with parameters
    public Booking(int flight_id, int customer_id, String travel_date, String travel_time, int seats) {
        this.booking_id = ++booking_id_counter;
        this.flight_id = flight_id;
        this.customer_id = customer_id;
        this.travel_date = travel_date;
        this.travel_time = travel_time;
        this.seats = seats;
    }

    //parameter with booking_id
    public Booking(int booking_id, int flight_id, int customer_id, String travel_date, String travel_time, int seats) {
        this.booking_id = booking_id;
        this.flight_id = flight_id;
        this.customer_id = customer_id;
        this.travel_date = travel_date;
        this.travel_time = travel_time;
        this.seats = seats;
    }
    //getters and setters
    public int getBooking_id() {
        return booking_id;
    }

    public int getFlight_id() {
        return flight_id;
    }

    public void setFlight_id(int flight_id) {
        this.flight_id = flight_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getTravel_date() {
        return travel_date;
    }

    public void setTravel_date(String travel_date) {
        this.travel_date = travel_date;
    }

    public String getTravel_time() {
        return travel_time;
    }

    public void setTravel_time(String travel_time) {
        this.travel_time = travel_time;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    @Override
    public String toString() {
        return "Booking{" + "booking_id=" + booking_id + ", flight_id=" + flight_id + ", customer_id=" + customer_id + ", travel_date=" + travel_date + ", travel_time=" + travel_time + ", seats=" + seats + '}';
    }
}
