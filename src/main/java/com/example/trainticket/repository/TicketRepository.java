package com.example.trainticket.repository;
import org.springframework.stereotype.Repository;

import com.example.trainticket.model.Ticket;
import com.example.trainticket.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class TicketRepository {
    private List<Ticket> tickets = new ArrayList<>();

    public Ticket save(Ticket ticket) {
        tickets.removeIf(t -> t.getUser().equals(ticket.getUser()));
        tickets.add(ticket);
        return ticket;
    }

    public Optional<Ticket> findByUser(User user) {
        return tickets.stream()
                .filter(t -> t.getUser().equals(user))
                .findFirst();
    }

    public List<Ticket> findBySection(String section) {
        return tickets.stream()
                .filter(t -> t.getSection().equals(section))
                .collect(Collectors.toList());
    }

    public void delete(Ticket ticket) {
        tickets.remove(ticket);
    }

    public List<Ticket> findAll() {
        return new ArrayList<>(tickets);
    }
}
