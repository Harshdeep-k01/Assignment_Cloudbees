package com.example.trainticket.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.trainticket.dto.ModifySeatRequest;
import com.example.trainticket.dto.TicketPurchaseRequest;
import com.example.trainticket.model.Ticket;
import com.example.trainticket.model.User;
import com.example.trainticket.service.TrainTicketService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/tickets")
public class TrainTicketController {

    @Autowired
    private TrainTicketService trainTicketService;

    @PostMapping("/purchase")
public ResponseEntity<Ticket> purchaseTicket(@RequestBody TicketPurchaseRequest request) {
    Optional<User> userOpt = trainTicketService.getUserByEmail(request.getUserEmail());
    if (userOpt.isPresent()) {
        Ticket ticket = trainTicketService.purchaseTicket("London", "France", userOpt.get());
        return ResponseEntity.ok(ticket);
    } else {
        return ResponseEntity.notFound().build();
    }
}

    @GetMapping("/receipt/{email}")
    public ResponseEntity<Ticket> getReceipt(@PathVariable String email) {
        Optional<Ticket> ticketOpt = trainTicketService.getReceipt(email);
        return ticketOpt.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/section/{section}")
    public ResponseEntity<List<Ticket>> getUsersBySection(@PathVariable String section) {
        List<Ticket> tickets = trainTicketService.getUsersBySection(section);
        return ResponseEntity.ok(tickets);
    }

    @DeleteMapping("/remove/{email}")
    public ResponseEntity<Void> removeUser(@PathVariable String email) {
        boolean removed = trainTicketService.removeUser(email);
        if (removed) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/modify-seat")
    public ResponseEntity<Void> modifyUserSeat(@RequestBody ModifySeatRequest request) {
        boolean modified = trainTicketService.modifyUserSeat(request.getEmail(), request.getNewSection(), request.getNewSeatNumber());
        if (modified) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
