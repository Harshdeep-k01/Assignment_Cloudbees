package com.example.trainticket.service;

import com.example.trainticket.model.User;
import com.example.trainticket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User registerUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> updateUser(String email, User updatedUser) {
        Optional<User> existingUser = userRepository.findByEmail(email);
        if (existingUser.isPresent()) {
            updatedUser.setEmail(email); 
            return Optional.of(userRepository.update(updatedUser));
        }
        return Optional.empty();
    }

    public boolean deleteUser(String email) {
        return userRepository.deleteByEmail(email);
    }
}