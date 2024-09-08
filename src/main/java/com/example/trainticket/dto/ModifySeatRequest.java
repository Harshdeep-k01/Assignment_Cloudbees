package com.example.trainticket.dto;

public class ModifySeatRequest {
    private String email;
    private String newSection;
    private int newSeatNumber;
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNewSection() {
        return newSection;
    }
    public void setNewSection(String newSection) {
        this.newSection = newSection;
    }
    public int getNewSeatNumber() {
        return newSeatNumber;
    }
    public void setNewSeatNumber(int newSeatNumber) {
        this.newSeatNumber = newSeatNumber;
    }

    
}
