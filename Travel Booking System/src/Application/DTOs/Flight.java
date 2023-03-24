package Application.DTOs;

public class Flight {
    //-- Create Flight table
    //CREATE TABLE flight (
    //    flight_id INT PRIMARY KEY AUTO_INCREMENT,
    //    airport_id INT NOT NULL,
    //    departure_location VARCHAR(50),
    //    arrival_location VARCHAR(50),
    //    airline_name VARCHAR(30),
    //    duration INT,
    //    flight_cost DECIMAL(10, 2),
    //    FOREIGN KEY (airport_id) REFERENCES airport(airport_id)
    //);

    private int flight_id;
    private static int flight_id_counter = 0;
    private int airport_id;
    private String departure_location;
    private String arrival_location;
    private String airline_name;
    private int duration;
    private double flight_cost;

    //default constructor
    public Flight() {
    }

    //constructor with parameters
    public Flight(int airport_id, String departure_location, String arrival_location, String airline_name, int duration, double flight_cost) {
        this.flight_id = ++flight_id_counter;
        this.airport_id = airport_id;
        this.departure_location = departure_location;
        this.arrival_location = arrival_location;
        this.airline_name = airline_name;
        this.duration = duration;
        this.flight_cost = flight_cost;
    }

    //parameter with flight_id
    public Flight(int flight_id, int airport_id, String departure_location, String arrival_location, String airline_name, int duration, double flight_cost) {
        this.flight_id = flight_id;
        this.airport_id = airport_id;
        this.departure_location = departure_location;
        this.arrival_location = arrival_location;
        this.airline_name = airline_name;
        this.duration = duration;
        this.flight_cost = flight_cost;
    }

    //getters and setters
    public int getFlight_id() {
        return flight_id;
    }

    public int getAirport_id() {
        return airport_id;
    }

    public void setAirport_id(int airport_id) {
        this.airport_id = airport_id;
    }

    public String getDeparture_location() {
        return departure_location;
    }

    public void setDeparture_location(String departure_location) {
        this.departure_location = departure_location;
    }

    public String getArrival_location() {
        return arrival_location;
    }

    public void setArrival_location(String arrival_location) {
        this.arrival_location = arrival_location;
    }

    public String getAirline_name() {
        return airline_name;
    }

    public void setAirline_name(String airline_name) {
        this.airline_name = airline_name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public double getFlight_cost() {
        return flight_cost;
    }

    public void setFlight_cost(double flight_cost) {
        this.flight_cost = flight_cost;
    }

    //toString method
    @Override
    public String toString() {
        return "Flight{" +
                "flight_id=" + flight_id +
                ", airport_id=" + airport_id +
                ", departure_location='" + departure_location + '\'' +
                ", arrival_location='" + arrival_location + '\'' +
                ", airline_name='" + airline_name + '\'' +
                ", duration=" + duration +
                ", flight_cost=" + flight_cost +
                '}';
    }
}
