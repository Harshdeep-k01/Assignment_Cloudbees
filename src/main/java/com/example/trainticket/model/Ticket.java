package com.example.trainticket.model;


public class Ticket {
    private String from;
    private String to;
    private User user;
    private double price;
    private String section;
    private int seatNumber;

    public Ticket(String from, String to, User user, double price, String section, int seatNumber) {
        this.from = from;
        this.to = to;
        this.user = user;
        this.price = price;
        this.section = section;
        this.seatNumber = seatNumber;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    
}