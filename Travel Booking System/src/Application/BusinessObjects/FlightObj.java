package Application.BusinessObjects;

import Application.CompMethods.CompAscFliAirName;
import Application.CompMethods.CompAscFliTime;
import Application.CompMethods.CompDescFliAirName;
import Application.CompMethods.CompDescFliTime;
import Application.DAOs.*;
import Application.DTOs.*;
import Application.Exceptions.DaoException;

import java.util.*;

public class FlightObj {
    static Helpers helper = new Helpers();
    static FlightDaoInterface flightDao = new MySqlFlightDao();
    static BookingDaoInterface bookingDao = new MySqlBookingDao();

    //display all flights
    public void findAllFlights() {
        FlightDaoInterface flightDao = new MySqlFlightDao();
        try {
            List<Flight> flights = flightDao.findAllFlights();
            if (flights.isEmpty()) {
                System.out.println("No flights found.");
            }
            for (Flight flight : flights) {
                System.out.println(flight.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //to find flight by number and also if no flight found then it will display no flight found
    //and if flight found then it will display flight details(flightNumber is a String)
    public void findFlightByNumber() {
        FlightDaoInterface flightDao = new MySqlFlightDao();
        String flightNumber = helper.readString("Enter flight number: ");
        try {
            Flight flight = flightDao.findFlightByNumber(flightNumber);
            if (flight == null) {
                System.out.println("No flight found.");
            } else {
                System.out.println(flight.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //to delete flight by number and also if no flight found then it will display no flight found
    //and also check if flight has related records in booking table then it will display those records
    public void deleteFlightByNumber() {
        String flightNumber = helper.readString("Enter flight number: ");
        try {
            boolean deleted = flightDao.deleteFlightByNumber(flightNumber);
            if (deleted) {
                System.out.println("Flight deleted.");
            } else {
                System.out.println("No flight found.");
            }
        } catch (DaoException e) {
            if (e.getMessage().contains("foreign key constraint")) {
                System.out.println("Flight-" + flightNumber + " cannot be deleted because it has related records in the database:");
                try {
                    List<Booking> bookings = bookingDao.findAllBookingsByFlightNumber(flightNumber);
                    for (Booking booking : bookings) {
                        System.out.println(booking);
                    }
                } catch (DaoException e1) {
                    System.out.println("Error: " + e1.getMessage());
                }
            } else {
                System.out.println("Error deleting flight: " + e.getMessage());
            }
        }
    }

    //to insert a new flight
    public void insertFlight() {
        String flightNumber = helper.readInputField("flightNumber", 10);
        String airportNumber = helper.checkAirportNumber();
        String departureLocation = helper.readInputField("departureLocation", 50);
        String departureTime = helper.readTime();
        String arrivalLocation = helper.readInputField("arrivalLocation", 50);
        String arrivalTime = helper.readTime();
        String airlineName = helper.readInputField("airlineName", 30);
        double flightCost = helper.readFlightCost();

        Flight flight = new Flight(flightNumber, airportNumber, departureLocation, departureTime, arrivalLocation, arrivalTime, airlineName, flightCost);
        try {
            flightDao.insertFlight(flight);
            System.out.println("Flight inserted.");
        } catch (DaoException e) {
            System.out.println("Error inserting flight: " + e.getMessage());
        }
    }

    //to filter flights by airline
    public void filterFlightByAirline() {
        try {
            Set<String> uniqueAirlineNames = flightDao.uniqueAirlineName();
            HashMap<Integer, String> numberedAirlineNames = new HashMap<>();
            int i = 1;
            for (String airlineName : uniqueAirlineNames) {
                numberedAirlineNames.put(i, airlineName);
                i++;
            }
            for (Map.Entry<Integer, String> entry : numberedAirlineNames.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue());
            }
            while (true) {
                int choice = helper.readInt("Enter your choice: ");
                if (choice > numberedAirlineNames.size() || choice < 1) {
                    System.out.println("Invalid choice, please try again.");
                } else {
                    String airlineName = numberedAirlineNames.get(choice);
                    List<Flight> flights = flightDao.findFlightByAirlineName(airlineName);
                    if (flights.isEmpty()) {
                        System.out.println("No flights found.");
                    } else {
                        System.out.println("How do you want the flights to be sorted?");
                        System.out.println("1. By Default");
                        System.out.println("2. By Ascending Order");
                        System.out.println("3. By Descending Order");
                        while (true) {
                            int sortChoice = helper.readInt("Enter your choice: ");
                            if (sortChoice > 3 || sortChoice < 1) {
                                System.out.println("Invalid choice, please try again.");
                            } else {
                                switch (sortChoice) {
                                    case 1:
                                        System.out.println("Flights by " + airlineName + ":");
                                        for (Flight flight : flights) {
                                            System.out.println(flight);
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Flights by " + airlineName + " sorted by ascending order:");
                                        flights.sort(new CompAscFliAirName());
                                        for (Flight flight : flights) {
                                            System.out.println(flight);
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Flights by " + airlineName + " sorted by descending order:");
                                        flights.sort(new CompDescFliAirName());
                                        for (Flight flight : flights) {
                                            System.out.println(flight);
                                        }
                                        break;
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //to filter flights by departure time
    public void filterFlightByDepartureTime(){
        try{
            Map<String, List<Flight>> flightsByDepartureTime = flightDao.timeOfFlight();
            int i = 1;
            for (Map.Entry<String, List<Flight>> entry : flightsByDepartureTime.entrySet()) {
                System.out.println(i + ". " + entry.getKey());
                i++;
            }
            while (true) {
                int choice = helper.readInt("Enter your choice: ");
                if (choice > flightsByDepartureTime.size() || choice < 1) {
                    System.out.println("Invalid choice, please try again.");
                } else {
                    String departureTime = new ArrayList<>(flightsByDepartureTime.keySet()).get(choice - 1);
                    //the flights is the value of the map with the key of departureTime
                    List<Flight> flights = flightsByDepartureTime.get(departureTime);
                    if (flights.isEmpty()) {
                        System.out.println("No flights found.");
                    } else {
                        System.out.println("How do you want the flights to be sorted?");
                        System.out.println("1. By Default");
                        System.out.println("2. By Ascending Order");
                        System.out.println("3. By Descending Order");
                        while (true) {
                            int sortChoice = helper.readInt("Enter your choice: ");
                            if (sortChoice > 3 || sortChoice < 1) {
                                System.out.println("Invalid choice, please try again.");
                            } else {
                                switch (sortChoice) {
                                    case 1:
                                        System.out.println("Flights by " + departureTime + ":");
                                        for (Flight flight : flights) {
                                            System.out.println(flight);
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Flights by " + departureTime + " sorted by ascending order:");
                                        flights.sort(new CompAscFliTime());
                                        for (Flight flight : flights) {
                                            System.out.println(flight);
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Flights by " + departureTime + " sorted by descending order:");
                                        flights.sort(new CompDescFliTime());
                                        for (Flight flight : flights) {
                                            System.out.println(flight);
                                        }
                                        break;
                                }
                                break;
                            }
                        }
                    }
                    break;
                }
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
