package com.example.trainticket.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromLocation;
    private String toLocation;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private double pricePaid;
    private String section;
    private int seatNumber;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getFromLocation() {
        return fromLocation;
    }
    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }
    public String getToLocation() {
        return toLocation;
    }
    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public double getPricePaid() {
        return pricePaid;
    }
    public void setPricePaid(double pricePaid) {
        this.pricePaid = pricePaid;
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
