package Application.DTOs;

public class Payment {
    //-- Create Payment table
    //CREATE TABLE Payment (
    //    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    //    booking_id INT NOT NULL,
    //    amount_paid DECIMAL(10, 2),
    //    payment_date DATE,
    //    method VARCHAR(20),
    //    FOREIGN KEY (booking_id) REFERENCES booking(booking_id)
    //);

    private int payment_id;
    private static int payment_id_counter = 0;
    private int booking_id;
    private double amount_paid;
    private String payment_date;
    private String method;

    //default constructor
    public Payment() {
    }

    //constructor with parameters
    public Payment(int booking_id, double amount_paid, String payment_date, String method) {
        this.payment_id = ++payment_id_counter;
        this.booking_id = booking_id;
        this.amount_paid = amount_paid;
        this.payment_date = payment_date;
        this.method = method;
    }

    //constructor with parameters
    public Payment(int payment_id, int booking_id, double amount_paid, String payment_date, String method) {
        this.payment_id = payment_id;
        this.booking_id = booking_id;
        this.amount_paid = amount_paid;
        this.payment_date = payment_date;
        this.method = method;
    }

    //getters and setters
    public int getPayment_id() {
        return payment_id;
    }

    public int getBooking_id() {
        return booking_id;
    }

    public void setBooking_id(int booking_id) {
        this.booking_id = booking_id;
    }

    public double getAmount_paid() {
        return amount_paid;
    }

    public void setAmount_paid(double amount_paid) {
        this.amount_paid = amount_paid;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String toString() {
        return "Payment{" + "payment_id=" + payment_id + ", booking_id=" + booking_id + ", amount_paid=" + amount_paid + ", payment_date=" + payment_date + ", method=" + method + '}';
    }
}
