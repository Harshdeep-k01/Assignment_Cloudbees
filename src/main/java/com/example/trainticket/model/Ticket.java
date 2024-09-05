package com.example.trainticket.model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fromLocation;
    private String toLocation;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private double pricePaid;
    private String section;
    private int seatNumber;
}
