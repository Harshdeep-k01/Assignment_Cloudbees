package com.example.trainticket.service;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class SeatService {
    private Map<String, Set<Integer>> occupiedSeats = new HashMap<>();

    public SeatService() {
        occupiedSeats.put("A", new HashSet<>());
        occupiedSeats.put("B", new HashSet<>());
    }

    public int allocateSeat(String section) {
        Set<Integer> sectionSeats = occupiedSeats.get(section);
        for (int i = 1; i <= 50; i++) {
            if (!sectionSeats.contains(i)) {
                sectionSeats.add(i);
                return i;
            }
        }
        throw new RuntimeException("No available seats in section " + section);
    }

    public void allocateSpecificSeat(String section, int seatNumber) {
        occupiedSeats.get(section).add(seatNumber);
    }

    public void releaseSeat(String section, int seatNumber) {
        occupiedSeats.get(section).remove(seatNumber);
    }

    public boolean isSeatAvailable(String section, int seatNumber) {
        return !occupiedSeats.get(section).contains(seatNumber);
    }
}