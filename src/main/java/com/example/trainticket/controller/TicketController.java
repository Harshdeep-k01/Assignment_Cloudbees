package com.example.trainticket.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trainticket.model.Ticket;
import com.example.trainticket.repository.TicketRepository;

@RestController
@RequestMapping("/api")
public class TicketController {
    @Autowired
    private TicketRepository ticketRepository;

    private Random random = new Random();

    @PostMapping("/purchase_ticket")
    public ResponseEntity<?> purchaseTicket(@RequestBody Ticket ticket) {
        ticket.setSection(random.nextBoolean() ? "A" : "B");
        ticket.setSeatNumber(random.nextInt(50) + 1);
        try {
            Ticket savedTicket = ticketRepository.save(ticket);
            return new ResponseEntity<>(Map.of(
                "message", "Ticket purchased successfully",
                "ticket_id", savedTicket.getId(),
                "section", savedTicket.getSection(),
                "seat_number", savedTicket.getSeatNumber()
            ), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(Map.of("error", "Email already exists"), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_receipt/{ticketId}")
    public ResponseEntity<?> getReceipt(@PathVariable Long ticketId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            return ResponseEntity.ok(Map.of(
                "from", ticket.getFromLocation(),
                "to", ticket.getToLocation(),
                "user", ticket.getFirstName() + " " + ticket.getLastName(),
                "email", ticket.getEmail(),
                "price_paid", ticket.getPricePaid(),
                "section", ticket.getSection(),
                "seat_number", ticket.getSeatNumber()
            ));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/view_users_by_section/{section}")
    public ResponseEntity<?> viewUsersBySection(@PathVariable String section) {
        List<Map<String, Object>> users = ticketRepository.findBySection(section, toUpperCase())
        .stream
        .map(ticket -> Map.of(
            "name", ticket.getFirstName() + " " + ticket.getLastName(),
            "seat_number", ticket.getSeatNumber()
        ))
        .collect(Collectors.toList());
        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/remove_user/{ticketId}")
    public ResponseEntity<?> removeUser(@PathVariable Long ticketId) {
        if (ticketRepository.existsById(ticketId)) {
            ticketRepository.deleteById(ticketId);
            return ResponseEntity.ok(Map.of("message", "User removed successfully"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/modify_seat/{ticketId}")
    public ResponseEntity<?> modifySeat(@PathVariable Long ticketId, @RequestBody Map<String, String> seatInfo) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(ticketId);
        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticket.setSection(seatInfo.get("section"));
            ticket.setSeatNumber(Integer.parseInt(seatInfo.get("seat_number")));
            ticketRepository.save(ticket);
            return ResponseEntity.ok(Map.of("message", "Seat modified successfully"));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
