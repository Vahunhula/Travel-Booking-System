package Application.BusinessObjects;

import Application.CompMethods.CompAscAirportName;
import Application.CompMethods.CompDescAirportName;
import Application.DAOs.*;
import Application.DTOs.*;
import Application.Exceptions.DaoException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class AirportObj {
    static Helpers helper = new Helpers();
    static AirportDaoInterface airportDao = new MySqlAirportDao();
    static FlightDaoInterface flightDao = new MySqlFlightDao();


    //display all airports
    public void findAllAirports() {
        try {
            List<Airport> airports = airportDao.findAllAirports();
            if (airports.isEmpty()) {
                System.out.println("No airports found.");
            }
            for (Airport airport : airports) {
                System.out.println(airport.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //to find airport by number and also if no airport found then it will display no airport found
    //and if airport found then it will display airport details(airportNumber is a String)
    public void findAirportByNumber() {
        String airportNumber = helper.readString("Enter airport number: ");
        try {
            Airport airport = airportDao.findAirportByNumber(airportNumber);
            if (airport == null) {
                System.out.println("No airport found.");
            } else {
                System.out.println(airport.toString());
            }
        } catch (DaoException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //to delete airport by number and also if no airport found then it will display no airport found
    public void deleteAirportByNumber() {
        String airportNumber = helper.readString("Enter airport number: ");
        try {
            boolean deleted = airportDao.deleteAirportByNumber(airportNumber);
            if (deleted) {
                System.out.println("Airport deleted.");
            } else {
                System.out.println("No airport found.");
            }
        } catch (DaoException e) {
            if (e.getMessage().contains("foreign key constraint")) {
                System.out.println("Airport-" + airportNumber + " cannot be deleted because it has related records in the database:");
                try {
                    List<Flight> flights = flightDao.findAllFlightsByAirportNumber(airportNumber);
                    for (Flight flight : flights) {
                        System.out.println(flight);
                    }
                } catch (DaoException e1) {
                    System.out.println("Error: " + e1.getMessage());
                }
            } else {
                System.out.println("Error deleting airport: " + e.getMessage());
            }
        }
    }

    //to insert a new airport
    public void insertAirport() {
        String airportNumber = helper.readInputField("airportNumber", 10);
        String airportName = helper.readInputField("airportName", 30);
        String airportLocation = helper.readInputField("airportLocation", 50);

        Airport airport = new Airport(airportNumber, airportName, airportLocation);
        try {
            airportDao.insertAirport(airport);
            System.out.println("Airport inserted.");
        } catch (DaoException e) {
            System.out.println("Error inserting airport: " + e.getMessage());
        }
    }

    //to filter the airports by location
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
}
