package com.example.trainticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.trainticket.model.Ticket;
import com.example.trainticket.model.Train;
import com.example.trainticket.model.User;
import com.example.trainticket.repository.TicketRepository;
import com.example.trainticket.repository.TrainRepository;
import com.example.trainticket.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class TrainTicketService {
    private static final double TICKET_PRICE = 20.0;
    private static final String TRAIN_ID = "London-France-Express";

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TrainRepository trainRepository;

    public TrainTicketService(TrainRepository trainRepository) {
        this.trainRepository = trainRepository;
        initializeTrain();
    }

    private void initializeTrain() {
        Train train = new Train(TRAIN_ID, "London-France Express", "London-France");
        train.addSection("A", 50);
        train.addSection("B", 50);
        trainRepository.save(train);
    }

    public Ticket purchaseTicket(String from, String to, User user) {
        User savedUser = userRepository.save(user);
        Optional<Train> trainOptional = trainRepository.findById(TRAIN_ID);
        if (!trainOptional.isPresent()) {
            throw new IllegalStateException("Train not found: " + TRAIN_ID);
        }
        Train train = trainOptional.get();
        String section = allocateSection(train);
        int seatNumber = allocateSeat(train, section);
        Ticket ticket = new Ticket(from, to, savedUser, TICKET_PRICE, section, seatNumber);
        return ticketRepository.save(ticket);
    }

    private String allocateSection(Train train) {
        return train.getSection("A").getAvailableSeats() > train.getSection("B").getAvailableSeats() ? "A" : "B";
    }

    private int allocateSeat(Train train, String section) {
        int seatNumber = train.getSection(section).getNextAvailableSeat();
        train.occupySeat(section, seatNumber);
        trainRepository.save(train);
        return seatNumber;
    }

    public Optional<Ticket> getReceipt(String email) {
        return userRepository.findByEmail(email)
                .flatMap(ticketRepository::findByUser);
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    
    public List<Ticket> getUsersBySection(String section) {
        return ticketRepository.findBySection(section);
    }

    public boolean removeUser(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Optional<Ticket> ticketOpt = ticketRepository.findByUser(user);
            if (ticketOpt.isPresent()) {
                Ticket ticket = ticketOpt.get();
                ticketRepository.delete(ticket);
                userRepository.delete(user);
                Optional<Train> trainOptional = trainRepository.findById(TRAIN_ID);
                if (trainOptional.isPresent()) {
                    Train train = trainOptional.get();
                    train.releaseSeat(ticket.getSection(), ticket.getSeatNumber());
                    trainRepository.save(train);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean modifyUserSeat(String email, String newSection, int newSeatNumber) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            Optional<Ticket> ticketOpt = ticketRepository.findByUser(user);
            if (ticketOpt.isPresent()) {
                Ticket ticket = ticketOpt.get();
                Optional<Train> trainOptional = trainRepository.findById(TRAIN_ID);
                if (trainOptional.isPresent()) {
                    Train train = trainOptional.get();
                    if (train.isSeatAvailable(newSection, newSeatNumber)) {
                        train.releaseSeat(ticket.getSection(), ticket.getSeatNumber());
                        train.occupySeat(newSection, newSeatNumber);
                        
                        ticket.setSection(newSection);
                        ticket.setSeatNumber(newSeatNumber);
                        ticketRepository.save(ticket);
                        trainRepository.save(train);
                        return true;
                    }
                }
            }
        }
        return false;
    }
}