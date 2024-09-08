package com.example.trainticket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.trainticket.model.Train;
import com.example.trainticket.repository.TrainRepository;
import java.util.List;
import java.util.Optional;

@Service
public class TrainService {

    @Autowired
    private TrainRepository trainRepository;

    public Train createTrain(Train train) {
        return trainRepository.save(train);
    }

    public List<Train> getAllTrains() {
        return trainRepository.findAll();
    }

    public Optional<Train> getTrainById(String id) {
        return trainRepository.findById(id);
    }

    public Optional<Train> updateTrain(String id, Train updatedTrain) {
        if (trainRepository.findById(id).isPresent()) {
            updatedTrain.setId(id); // Ensure ID doesn't change
            return Optional.of(trainRepository.save(updatedTrain));
        }
        return Optional.empty();
    }

    public boolean deleteTrain(String id) {
        return trainRepository.deleteById(id);
    }
}