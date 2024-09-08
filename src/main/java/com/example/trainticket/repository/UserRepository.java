package com.example.trainticket.repository;

import org.springframework.stereotype.Repository;

import com.example.trainticket.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public User save(User user) {
        users.removeIf(u -> u.getEmail().equals(user.getEmail()));
        users.add(user);
        return user;
    }

    public Optional<User> findByEmail(String email) {
        return users.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();
    }

    public void delete(User user) {
        users.remove(user);
    }

    public List<User> findAll() {
        return new ArrayList<>(users);
    }

    public User update(User user) {
        delete(user);
        return save(user);
    }

    public boolean deleteByEmail(String email) {
        return users.removeIf(u -> u.getEmail().equals(email));
    }
}