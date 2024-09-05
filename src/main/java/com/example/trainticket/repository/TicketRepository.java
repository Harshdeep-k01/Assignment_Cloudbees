package com.example.trainticket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.trainticket.model.Ticket;

public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> findBySection(String section);
}
