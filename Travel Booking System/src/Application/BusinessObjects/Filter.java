package Application.BusinessObjects;

import Application.DAOs.*;
import Application.DTOs.Airport;
import Application.Exceptions.DaoException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Filter {
    static Helpers helper = new Helpers();
    static CustomerDaoInterface customerDao = new MySqlCustomerDao();
    static AirportDaoInterface airportDao = new MySqlAirportDao();
    static FlightDaoInterface flightDao = new MySqlFlightDao();
    static BookingDaoInterface bookingDao = new MySqlBookingDao();
    static PaymentDaoInterface paymentDao = new MySqlPaymentDao();

    public void filterAirportByCity(){
        try{
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
                    if(airports.isEmpty()){
                        System.out.println("No airports found.");
                    }else {
                        for (Airport airport : airports) {
                            System.out.println(airport.toString());
                        }
                    }
                    break;
                }
            }
        }catch (DaoException e){
            System.out.println("Error: " + e.getMessage());
        }
    }
}
