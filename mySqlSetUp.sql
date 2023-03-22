-- Create database if it does not exist
CREATE DATABASE IF NOT EXISTS `travel_booking_system` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `travel_booking_system`;

-- Drop tables if they exist IN A SPECIFIC ORDER TO AVOID FOREIGN KEY CONSTRAINTS
DROP TABLE IF EXISTS `payment`, `booking`, `flight`, `airport`, `customer`;

-- Create Customer table
CREATE TABLE customer (
    customer_id INT PRIMARY KEY AUTO_INCREMENT,
    customer_name VARCHAR(20),
    email VARCHAR(50) NOT NULL UNIQUE,
    tel_num VARCHAR(20),
    address VARCHAR(255)
);

-- Create Airport table
CREATE TABLE airport (
    airport_id INT PRIMARY KEY AUTO_INCREMENT,
    airport_name VARCHAR(30),
    airport_location VARCHAR(50)
);


-- Create Flight table
CREATE TABLE flight (
    flight_id INT PRIMARY KEY AUTO_INCREMENT,
    airport_id INT NOT NULL,
    departure_location VARCHAR(50),
    arrival_location VARCHAR(50),
    airline_name VARCHAR(30),
    duration INT,
    flight_cost DECIMAL(10, 2),
    FOREIGN KEY (airport_id) REFERENCES airport(airport_id)
);


-- Create Booking table
CREATE TABLE booking (
    booking_id INT PRIMARY KEY AUTO_INCREMENT,
    flight_id INT NOT NULL,
    customer_id INT NOT NULL,
    travel_date DATE NOT NULL,
    travel_time TIME NOT NULL,
    seats INT NOT NULL,
    FOREIGN KEY (flight_id) REFERENCES flight(flight_id),
    FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);

-- Create Payment table
CREATE TABLE Payment (
    payment_id INT PRIMARY KEY AUTO_INCREMENT,
    booking_id INT NOT NULL,
    amount_paid DECIMAL(10, 2),
    payment_date DATE,
    method VARCHAR(20),
    FOREIGN KEY (booking_id) REFERENCES booking(booking_id)
);