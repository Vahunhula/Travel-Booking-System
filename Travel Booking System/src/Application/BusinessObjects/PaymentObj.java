package Application.BusinessObjects;

import Application.CompMethods.CompAscPayMethod;
import Application.CompMethods.CompDescPayMethod;
import Application.DAOs.*;
import Application.DTOs.*;
import Application.Exceptions.DaoException;
import Application.Protocol.MenuOptions;
import Application.Protocol.Packet;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.*;

public class PaymentObj {
    static Helpers helper;
    static PaymentDaoInterface paymentDao = new MySqlPaymentDao();

    private Scanner input;
    private PrintWriter output;

    public PaymentObj(Scanner input, PrintWriter output) {
        this.input = input;
        this.output = output;
    }

    //display all payments
    public void findAllPayments() {
        Packet request = new Packet(MenuOptions.PaymentMenuOptions.FIND_ALL_PAYMENTS, null);
        String jsonRequest = request.toJson();
        output.println(jsonRequest);
        output.flush();

        String jsonResponse = input.nextLine();
        Packet response = Packet.fromJson(jsonResponse);

        if (response.getException() != null) {
            System.out.println("Error: " + response.getException().getMessage());
        } else {
            String jsonPayments = (String) response.getData();
            Type paymentListType = new TypeToken<List<Payment>>(){}.getType();
            List<Payment> payments = new Gson().fromJson(jsonPayments, paymentListType);

            if (payments.isEmpty()) {
                System.out.println("No payments found.");
            }
            for (Payment payment : payments) {
                System.out.println(payment.toString());
            }
        }
    }

    //to find payment by number and also if no payment found then it will display no payment found
    //and if payment found then it will display payment details(paymentNumber is a String)
    public void findPaymentByNumber() {
        helper = new Helpers(input, output);
          String paymentNumber = helper.readString("Enter payment number: ");
        Packet request = new Packet(MenuOptions.PaymentMenuOptions.FIND_PAYMENT_BY_NUMBER, paymentNumber);
        String jsonRequest = request.toJson();
        output.println(jsonRequest);
        output.flush();

        String jsonResponse = input.nextLine();
        Packet response = Packet.fromJson(jsonResponse);

        if (response.getException() != null) {
            System.out.println("Error: " + response.getException().getMessage());
        } else {
            String jsonPayment = (String) response.getData();
            Payment payment = new Gson().fromJson(jsonPayment, Payment.class);
            if (payment == null) {
                System.out.println("No payment found.");
            } else {
                System.out.println(payment);
            }
        }
    }

    //to delete payment by number and also if no payment found then it will display no payment found
    public void deletePaymentByNumber() {
        helper = new Helpers(input, output);
        String paymentNumber = helper.readString("Enter payment number: ");
        Packet request = new Packet(MenuOptions.PaymentMenuOptions.DELETE_PAYMENT_BY_NUMBER, paymentNumber);
        String jsonRequest = request.toJson();
        output.println(jsonRequest);
        output.flush();

        String jsonResponse = input.nextLine();
        Packet response = Packet.fromJson(jsonResponse);

        if (response.getException() != null) {
            String exceptionMessage = response.getException().getMessage();
            System.out.println("Error: " + (exceptionMessage == null ? "Unknown error" : exceptionMessage));
        } else {
            boolean deleted = Boolean.parseBoolean((String) response.getData());
            if (deleted) {
                System.out.println("Payment deleted.");
            } else {
                System.out.println("No payment found.");
            }
        }
    }

    //to insert a new payment
    public void insertPayment() {
        helper = new Helpers(input, output);
        String paymentNumber = helper.readInputField("paymentNumber", 10);
        String bookingNumber = helper.checkBookingNumber();
        double amountPaid = helper.readAmountPaid();
        String paymentDate = helper.readPaymentDate();
        String method = helper.readString("Enter payment method: ");

        Payment payment = new Payment(paymentNumber, bookingNumber, amountPaid, paymentDate, method);
        Packet request = new Packet(MenuOptions.PaymentMenuOptions.INSERT_PAYMENT, payment);
        String jsonRequest = request.toJson();
        output.println(jsonRequest);
        output.flush();

        String jsonResponse = input.nextLine();
        Packet response = Packet.fromJson(jsonResponse);

        if (response.getException() != null) {
            System.out.println("Error: " + response.getException().getMessage());
        } else {
            String jsonPayment = (String) response.getData();
            Payment p = new Gson().fromJson(jsonPayment, Payment.class);
            if (p == null) {
                System.out.println("Payment was not inserted.");
            } else {
                System.out.println(p);
                System.out.println("Payment inserted.");
            }
        }
    }

    //public void filterAirportByCity() {
    //        helper = new Helpers(input, output);
    //        Packet request = new Packet(MenuOptions.AirportMenuOptions.FILTER_AIRPORT_BY_CITY, null);
    //        String jsonRequest = request.toJson();
    //        output.println(jsonRequest);
    //        output.flush();
    //
    //        String jsonResponse = input.nextLine();
    //        Packet response = Packet.fromJson(jsonResponse);
    //
    //        if (response.getException() != null) {
    //            System.out.println("Error: " + response.getException().getMessage());
    //        } else {
    //            // Deserialize the HashMap received from the server
    //            Type hashMapType = new TypeToken<HashMap<Integer, String>>() {
    //            }.getType();
    //            HashMap<Integer, String> numberedAirportLocations = new Gson().fromJson((String) response.getData(), hashMapType);
    //
    //            for (Map.Entry<Integer, String> entry : numberedAirportLocations.entrySet()) {
    //                System.out.println(entry.getKey() + ". " + entry.getValue());
    //            }
    //            while (true) {
    //                int choice = helper.readInt("Enter your choice: ");
    //                if (choice > numberedAirportLocations.size() || choice < 1) {
    //                    System.out.println("Invalid choice, please try again.");
    //                } else {
    //                    String airportLocation = numberedAirportLocations.get(choice);
    //
    //                    Packet locationRequest = new Packet(MenuOptions.AirportMenuOptions.FIND_AIRPORT_BY_LOCATION, airportLocation);
    //                    String jsonLocationRequest = locationRequest.toJson();
    //                    output.println(jsonLocationRequest);
    //                    output.flush();
    //
    //                    String jsonLocationResponse = input.nextLine();
    //                    Packet locationResponse = Packet.fromJson(jsonLocationResponse);
    //
    //                    if (locationResponse.getException() != null) {
    //                        System.out.println("Error: " + locationResponse.getException().getMessage());
    //                    } else {
    //                        String jsonAirports = (String) locationResponse.getData();
    //                        Type airportListType = new TypeToken<List<Airport>>(){}.getType();
    //                        List<Airport> airports = new Gson().fromJson(jsonAirports, airportListType);
    //
    //                        if (airports.isEmpty()) {
    //                            System.out.println("No airports found.");
    //                        } else {
    //                            System.out.println("How do you want the airports to be sorted?");
    //                            System.out.println("1. By Default");
    //                            System.out.println("2. By Ascending Order");
    //                            System.out.println("3. By Descending Order");
    //                            while (true) {
    //                                int sortChoice = helper.readInt("Enter your choice: ");
    //                                if (sortChoice > 3 || sortChoice < 1) {
    //                                    System.out.println("Invalid choice, please try again.");
    //                                } else {
    //                                    switch (sortChoice) {
    //                                        case 1:
    //                                            System.out.println("Airports in " + airportLocation + ":");
    //                                            for (Airport airport : airports) {
    //                                                System.out.println(airport);
    //                                            }
    //                                            break;
    //                                        case 2:
    //                                            System.out.println("Airports in " + airportLocation + " sorted by ascending order:");
    //                                            airports.sort(new CompAscAirportName());
    //                                            for (Airport airport : airports) {
    //                                                System.out.println(airport);
    //                                            }
    //                                            break;
    //                                        case 3:
    //                                            System.out.println("Airports in " + airportLocation + " sorted by descending order:");
    //                                            airports.sort(new CompDescAirportName());
    //                                            for (Airport airport : airports) {
    //                                                System.out.println(airport);
    //                                            }
    //                                            break;
    //                                    }
    //                                    break;
    //                                }
    //                            }
    //                        }
    //                    }
    //                    break;
    //                }
    //            }
    //        }
    //    }

    //to filter the payment with the payment method
    public void filterPaymentByPaymentMethod() {
        helper = new Helpers(input, output);
        Packet request = new Packet(MenuOptions.PaymentMenuOptions.FILTER_PAYMENT_BY_PAYMENT_METHOD, null);
        String jsonRequest = request.toJson();
        output.println(jsonRequest);
        output.flush();

        String jsonResponse = input.nextLine();
        Packet response = Packet.fromJson(jsonResponse);

        if (response.getException() != null) {
            System.out.println("Error: " + response.getException().getMessage());
        } else {
            // Deserialize the HashMap received from the server
            Type hashMapType = new TypeToken<HashMap<Integer, String>>() {
            }.getType();
            HashMap<Integer, String> numberedPaymentMethods = new Gson().fromJson((String) response.getData(), hashMapType);

            for (Map.Entry<Integer, String> entry : numberedPaymentMethods.entrySet()) {
                System.out.println(entry.getKey() + ". " + entry.getValue());
            }
            while (true) {
                int choice = helper.readInt("Enter your choice: ");
                if (choice > numberedPaymentMethods.size() || choice < 1) {
                    System.out.println("Invalid choice, please try again.");
                } else {
                    String paymentMethod = numberedPaymentMethods.get(choice);

                    Packet paymentMethodRequest = new Packet(MenuOptions.PaymentMenuOptions.FIND_PAYMENT_BY_PAYMENT_METHOD, paymentMethod);
                    String jsonPaymentMethodRequest = paymentMethodRequest.toJson();
                    output.println(jsonPaymentMethodRequest);
                    output.flush();

                    String jsonPaymentMethodResponse = input.nextLine();
                    Packet paymentMethodResponse = Packet.fromJson(jsonPaymentMethodResponse);

                    if (paymentMethodResponse.getException() != null) {
                        System.out.println("Error: " + paymentMethodResponse.getException().getMessage());
                    } else {
                        String jsonPayments = (String) paymentMethodResponse.getData();
                        Type paymentListType = new TypeToken<List<Payment>>() {
                        }.getType();
                        List<Payment> payments = new Gson().fromJson(jsonPayments, paymentListType);

                        if (payments.isEmpty()) {
                            System.out.println("No payments found.");
                        } else {
                            System.out.println("How do you want the payments to be sorted?");
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
                                            System.out.println("Payments with " + paymentMethod + ":");
                                            for (Payment payment : payments) {
                                                System.out.println(payment);
                                            }
                                            break;
                                        case 2:
                                            System.out.println("Payments with " + paymentMethod + " sorted by ascending order:");
                                            payments.sort(new CompAscPayMethod());
                                            for (Payment payment : payments) {
                                                System.out.println(payment);
                                            }
                                            break;
                                        case 3:
                                            System.out.println("Payments with " + paymentMethod + " sorted by descending order:");
                                            payments.sort(new CompDescPayMethod());
                                            for (Payment payment : payments) {
                                                System.out.println(payment);
                                            }
                                            break;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    break;
                }
            }
        }
    }
//    public void filterPaymentByPaymentMethod() {
//        helper = new Helpers(input, output);
//        try {
//            Set<String> uniquePaymentMethods = paymentDao.uniquePaymentMethod();
//            HashMap<Integer, String> numberedPaymentMethods = new HashMap<>();
//            int i = 1;
//            for (String paymentMethod : uniquePaymentMethods) {
//                numberedPaymentMethods.put(i, paymentMethod);
//                i++;
//            }
//            for (Map.Entry<Integer, String> entry : numberedPaymentMethods.entrySet()) {
//                System.out.println(entry.getKey() + ". " + entry.getValue());
//            }
//            while (true) {
//                int choice = helper.readInt("Enter your choice: ");
//                if (choice > numberedPaymentMethods.size() || choice < 1) {
//                    System.out.println("Invalid choice, please try again.");
//                } else {
//                    String paymentMethod = numberedPaymentMethods.get(choice);
//                    List<Payment> payments = paymentDao.findPaymentByPaymentMethod(paymentMethod);
//                    if (payments.isEmpty()) {
//                        System.out.println("No payments found.");
//                    } else {
//                        System.out.println("How do you want the payments to be sorted?");
//                        System.out.println("1. By Default");
//                        System.out.println("2. By Ascending Order");
//                        System.out.println("3. By Descending Order");
//                        while (true) {
//                            int sortChoice = helper.readInt("Enter your choice: ");
//                            if (sortChoice > 3 || sortChoice < 1) {
//                                System.out.println("Invalid choice, please try again.");
//                            } else {
//                                switch (sortChoice) {
//                                    case 1:
//                                        System.out.println("Payments by " + paymentMethod + ":");
//                                        for (Payment payment : payments) {
//                                            System.out.println(payment);
//                                        }
//                                        break;
//                                    case 2:
//                                        System.out.println("Payments by " + paymentMethod + " sorted by ascending order:");
//                                        payments.sort(new CompAscPayMethod());
//                                        for (Payment payment : payments) {
//                                            System.out.println(payment);
//                                        }
//                                        break;
//                                    case 3:
//                                        System.out.println("Payments by " + paymentMethod + " sorted by descending order:");
//                                        payments.sort(new CompDescPayMethod());
//                                        for (Payment payment : payments) {
//                                            System.out.println(payment);
//                                        }
//                                        break;
//                                }
//                                break;
//                            }
//                        }
//                    }
//                    break;
//                }
//            }
//        } catch (DaoException e) {
//            System.out.println("Error: " + e.getMessage());
//        }
//    }
}
