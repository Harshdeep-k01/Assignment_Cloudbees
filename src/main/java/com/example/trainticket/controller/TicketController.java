package com.example.trainticket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.trainticket.service.TicketService;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/purchase")
    public String purchaseTicket(@RequestParam String firstName,
                                 @RequestParam String lastName,
                                 @RequestParam String email,
                                 @RequestParam String from,
                                 @RequestParam String to,
                                 @RequestParam String section) {
        int ticketId = ticketService.purchaseTicket(firstName, lastName, email, from, to, section);
        if (ticketId != -1) {
            return "Ticket purchased successfully! Ticket ID: " + ticketId;
        } else {
            return "No seats available in Section " + section;
        }
    }

    @GetMapping("/receipt/{ticketId}")
    public String getReceipt(@PathVariable int ticketId) {
        return ticketService.getReceipt(ticketId);
    }

    @GetMapping("/view")
    public List<String> viewUsersAndSeats() {
        return ticketService.viewUsersAndSeats();
    }

    @DeleteMapping("/remove/{ticketId}")
    public String removeUser(@PathVariable int ticketId) {
        boolean removed = ticketService.removeUser(ticketId);
        return removed ? "User removed successfully." : "User not found.";
    }

    @PutMapping("/modify/{ticketId}")
    public String modifySeat(@PathVariable int ticketId, @RequestParam String newSection) {
        boolean modified = ticketService.modifySeat(ticketId, newSection);
        return modified ? "Seat modified successfully!" : "Modification failed. No seats available in Section " + newSection;
    }
}
