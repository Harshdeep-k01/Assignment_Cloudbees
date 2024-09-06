package com.example.trainticket.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.trainticket.model.Ticket;
@Service
public class TicketService {

    private static final int SECTION_A_SEATS = 50;
    private static final int SECTION_B_SEATS = 50;

    private int seatCounterA = 0;
    private int seatCounterB = 0;

    private final Map<Integer, Ticket> ticketDatabase = new HashMap<>();
    private int nextTicketId = 1;

    public int purchaseTicket(String firstName, String lastName, String email, String from, String to, String section) {
        if (section.equals("A") && seatCounterA >= SECTION_A_SEATS) {
            return -1;
        } else if (section.equals("B") && seatCounterB >= SECTION_B_SEATS) {
            return -1;
        }

        int seatNumber = section.equals("A") ? ++seatCounterA : ++seatCounterB;
        Ticket ticket = new Ticket(firstName, lastName, email, from, to, section, seatNumber);
        ticketDatabase.put(nextTicketId, ticket);
        return nextTicketId++;
    }

    public String getReceipt(int ticketId) {
        Ticket ticket = ticketDatabase.get(ticketId);
        return (ticket != null) ? ticket.toString() : "Ticket not found.";
    }

    public List<String> viewUsersAndSeats() {
        List<String> usersAndSeats = new ArrayList<>();
        for (Ticket ticket : ticketDatabase.values()) {
            usersAndSeats.add("Name: " + ticket.getFirstName() + " " + ticket.getLastName() +
                    ", Section: " + ticket.getSection() + ", Seat: " + ticket.getSeatNumber());
        }
        return usersAndSeats;
    }

    public boolean removeUser(int ticketId) {
        Ticket ticket = ticketDatabase.remove(ticketId);
        if (ticket != null) {
            if (ticket.getSection().equals("A")) {
                seatCounterA--;
            } else {
                seatCounterB--;
            }
            return true;
        }
        return false;
    }

    public boolean modifySeat(int ticketId, String newSection) {
        Ticket ticket = ticketDatabase.get(ticketId);
        if (ticket != null) {
            if (ticket.getSection().equals("A")) {
                seatCounterA--;
            } else {
                seatCounterB--;
            }

            if (newSection.equals("A") && seatCounterA < SECTION_A_SEATS) {
                ticketDatabase.put(ticketId, new Ticket(ticket.getFirstName(), ticket.getLastName(),
                        ticket.getEmail(), ticket.getFrom(), ticket.getTo(), "A", ++seatCounterA));
                return true;
            } else if (newSection.equals("B") && seatCounterB < SECTION_B_SEATS) {
                ticketDatabase.put(ticketId, new Ticket(ticket.getFirstName(), ticket.getLastName(),
                        ticket.getEmail(), ticket.getFrom(), ticket.getTo(), "B", ++seatCounterB));
                return true;
            }
        }
        return false;
    }
}
