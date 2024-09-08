package com.example.trainticket.repository;

import org.springframework.stereotype.Repository;
import com.example.trainticket.model.Train;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Repository
public class TrainRepository {
    private Map<String, Train> trains = new HashMap<>();

    public Train save(Train train) {
        trains.put(train.getId(), train);
        return train;
    }

    public Optional<Train> findById(String id) {
        return Optional.ofNullable(trains.get(id));
    }

    public List<Train> findAll() {
        return new ArrayList<>(trains.values());
    }

    public void delete(Train train) {
        trains.remove(train.getId());
    }

    public boolean deleteById(String id) {
        return trains.remove(id) != null;
    }
}