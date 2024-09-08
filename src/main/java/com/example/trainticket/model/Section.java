package com.example.trainticket.model;
import java.util.HashSet;
import java.util.Set;

public class Section {
    private String name;
    private int capacity;
    private Set<Integer> occupiedSeats;

    public Section(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.occupiedSeats = new HashSet<>();
    }

    public boolean isSeatAvailable(int seatNumber) {
        return seatNumber > 0 && seatNumber <= capacity && !occupiedSeats.contains(seatNumber);
    }

    public void occupySeat(int seatNumber) {
        if (isSeatAvailable(seatNumber)) {
            occupiedSeats.add(seatNumber);
        } else {
            throw new IllegalStateException("Seat " + seatNumber + " is not available in section " + name);
        }
    }

    public void releaseSeat(int seatNumber) {
        occupiedSeats.remove(seatNumber);
    }

    public int getNextAvailableSeat() {
        for (int i = 1; i <= capacity; i++) {
            if (isSeatAvailable(i)) {
                return i;
            }
        }
        throw new IllegalStateException("No available seats in section " + name);
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getAvailableSeats() {
        return capacity - occupiedSeats.size();
    }
}