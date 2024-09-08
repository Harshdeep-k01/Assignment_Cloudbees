package com.example.trainticket.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.trainticket.model.Train;
import com.example.trainticket.service.TrainService;
import java.util.List;

@RestController
@RequestMapping("/api/trains")
public class TrainController {

    @Autowired
    private TrainService trainService;

    @PostMapping
    public ResponseEntity<Train> createTrain(@RequestBody Train train) {
        Train createdTrain = trainService.createTrain(train);
        return ResponseEntity.ok(createdTrain);
    }

    @GetMapping
    public ResponseEntity<List<Train>> getAllTrains() {
        List<Train> trains = trainService.getAllTrains();
        return ResponseEntity.ok(trains);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Train> getTrainById(@PathVariable String id) {
        return trainService.getTrainById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Train> updateTrain(@PathVariable String id, @RequestBody Train updatedTrain) {
        return trainService.updateTrain(id, updatedTrain)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTrain(@PathVariable String id) {
        boolean deleted = trainService.deleteTrain(id);
        if (deleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}