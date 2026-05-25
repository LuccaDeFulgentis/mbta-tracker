package com.mbta.tracker.controller;

import com.mbta.tracker.model.Prediction;
import com.mbta.tracker.service.PredictionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PredictionController {

    private final PredictionService predictionService;

    public PredictionController(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @GetMapping("/predictions")
    public List<Prediction> getPredictions(@RequestParam String stop) {
        return predictionService.getPredictionsForStop(stop);
    }
}