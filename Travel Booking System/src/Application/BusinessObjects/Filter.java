package Application.BusinessObjects;

import Application.CompMethods.*;
import Application.DAOs.*;
import Application.DTOs.Airport;
import Application.DTOs.Flight;
import Application.Exceptions.DaoException;

import java.util.*;

public class Filter {
    static Helpers helper = new Helpers();
    static CustomerDaoInterface customerDao = new MySqlCustomerDao();
    static AirportDaoInterface airportDao = new MySqlAirportDao();
    static FlightDaoInterface flightDao = new MySqlFlightDao();
    static BookingDaoInterface bookingDao = new MySqlBookingDao();
    static PaymentDaoInterface paymentDao = new MySqlPaymentDao();

    public void filterAirportByCity() {
        try {
            Set<String> uniqueAirportLocations = airportDao.uniqueAirportLocation();
            HashMap<Integer, String> numberedAirportLocations = new HashMap<>();
            int i = 1;
            for (String airportLocation : uniqueAirportLocations) {
                numberedAirportLocations.put(i, airportLocation);
                i++;
            }
            for (Map.Entry<Integer, String> entry : numberedAirportLocations.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue());
            }
            while (true) {
                int choice = helper.readInt("Enter your choice: ");
                if (choice > numberedAirportLocations.size() || choice < 1) {
                    System.out.println("Invalid choice, please try again.");
                } else {
                    String airportLocation = numberedAirportLocations.get(choice);
                    List<Airport> airports = airportDao.findAirportByLocation(airportLocation);
                    if (airports.isEmpty()) {
                        System.out.println("No airports found.");
                    } else {
                        System.out.println("How do you want the airports to be sorted?");
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
                                        System.out.println("Airports in " + airportLocation + ":");
                                        for (Airport airport : airports) {
                                            System.out.println(airport);
                                        }
                                        break;
                                    case 2:
                                        System.out.println("Airports in " + airportLocation + " sorted by ascending order:");
                                        airports.sort(new CompAscAirportName());
                                        for (Airport airport : airports) {
                                            System.out.println(airport);
                                        }
                                        break;
                                    case 3:
                                        System.out.println("Airports in " + airportLocation + " sorted by descending order:");
                                        airports.sort(new CompDescAirportName());
                                        for (Airport airport : airports) {
                                            System.out.println(airport);
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
