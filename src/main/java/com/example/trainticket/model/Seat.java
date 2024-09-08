package com.example.trainticket.model;

public class Seat {

    private Long seatId;
    private int seatNumber;
    private Section section;
    private boolean isAvailable;
    
    public Long getSeatId() {
        return seatId;
    }
    public void setSeatId(Long seatId) {
        this.seatId = seatId;
    }
    public int getSeatNumber() {
        return seatNumber;
    }
    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }
    public Section getSection() {
        return section;
    }
    public void setSection(Section section) {
        this.section = section;
    }
    public boolean isAvailable() {
        return isAvailable;
    }
    public void setAvailable(boolean isAvailable) {
        this.isAvailable = isAvailable;
    }
    public Seat(Long seatId, int seatNumber, Section section, boolean isAvailable) {
        this.seatId = seatId;
        this.seatNumber = seatNumber;
        this.section = section;
        this.isAvailable = isAvailable;
    }

    
    
}
