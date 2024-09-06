package com.example.trainticket.model;

public class Ticket {

    private String firstName;
    private String lastName;
    private String email;
    private String from;
    private String to;
    private double price;
    private String section;
    private int seatNumber;

    public Ticket(String firstName, String lastName, String email, String from, String to, String section, int seatNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.from = from;
        this.to = to;
        this.price = 20.0; // Fixed price as per requirements
        this.section = section;
        this.seatNumber = seatNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getFrom() {
        return from;
    }

    public String getTo() {
        return to;
    }

    public double getPrice() {
        return price;
    }

    public String getSection() {
        return section;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    @Override
    public String toString() {
        return "Ticket Receipt: \n" +
                "Name: " + firstName + " " + lastName + "\n" +
                "Email: " + email + "\n" +
                "From: " + from + "\n" +
                "To: " + to + "\n" +
                "Price: $" + price + "\n" +
                "Section: " + section + "\n" +
                "Seat Number: " + seatNumber;
    }
    
}
