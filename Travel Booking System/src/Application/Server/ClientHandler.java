package Application.Server;

import Application.Protocol.MenuOptions;
import Application.Protocol.Packet;

import java.io.*;
import java.net.Socket;

import static Application.Protocol.MenuOptions.CustomerMenuOptions.*;

public class ClientHandler implements Runnable {
    private Socket clientSocket;
    private int clientNumber;

    public ClientHandler(Socket clientSocket, int clientNumber) {
        this.clientSocket = clientSocket;
        this.clientNumber = clientNumber;
    }

    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(new OutputStreamWriter(clientSocket.getOutputStream()), true);

            while (true) {
                String input = in.readLine();

                if (input == null) {
                    System.out.println("Server: Client " + clientNumber + " disconnected.");
                    clientSocket.close();
                    break;
                }

                Packet packet = Packet.fromJson(input);
                CommandFactory factory = new CommandFactory();

                Command command = null;
                Object data = packet.getData();

                Enum option = packet.getOption();

                if (option.equals(FIND_ALL_CUSTOMERS) || option.equals(FIND_CUSTOMER_BY_NUMBER) || option.equals(DELETE_CUSTOMER_BY_NUMBER) || option.equals(INSERT_CUSTOMER)) {
                    command = factory.createCustomerCommand((MenuOptions.CustomerMenuOptions)option);
                    //                    case FIND_ALL_AIRPORTS:
//                    case FIND_AIRPORT_BY_NUMBER:
//                    case DELETE_AIRPORT_BY_NUMBER:
//                    case INSERT_AIRPORT:
//                    case FILTER_AIRPORT_BY_CITY:
//                        command = factory.createAirportCommand(option);
//                        break;
//                    case FIND_ALL_FLIGHTS:
//                    case FIND_FLIGHT_BY_NUMBER:
//                    case DELETE_FLIGHT_BY_NUMBER:
//                    case INSERT_FLIGHT:
//                    case FILTER_FLIGHT_BY_AIIRLINE:
//                    case FILTER_FLIGHT_BY_DEPARTURE_TIME:
//                        command = factory.createFlightCommand(option);
//                        break;
//                    case FIND_ALL_BOOKINGS:
//                    case FIND_BOOKING_BY_NUMBER:
//                    case DELETE_BOOKING_BY_NUMBER:
//                    case INSERT_BOOKING:
//                        command = factory.createBookingCommand(option);
//                        break;
//                    case FIND_ALL_PAYMENTS:
//                    case FIND_PAYMENT_BY_NUMBER:
//                    case DELETE_PAYMENT_BY_NUMBER:
//                    case INSERT_PAYMENT:
//                    case FILTER_PAYMENT_BY_PAYMENT_METHOD:
//                        command = factory.createPaymentCommand(option);
//                        break;
                }

                if (command != null) {
                    Packet responsePacket = command.execute(data);
                    out.println(responsePacket.toJson());
                }
            }
        } catch (IOException e) {
            System.out.println("Server: IOException: " + e.getMessage());
        }
    }
}
