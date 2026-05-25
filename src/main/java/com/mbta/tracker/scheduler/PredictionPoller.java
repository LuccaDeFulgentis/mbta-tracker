package com.mbta.tracker.scheduler;

import com.mbta.tracker.service.PredictionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class PredictionPoller {

    private static final Logger log = LoggerFactory.getLogger(PredictionPoller.class);

    private final PredictionService predictionService;

    public PredictionPoller(PredictionService predictionService) {
        this.predictionService = predictionService;
    }

    @Scheduled(fixedRate = 30000)
    public void pollPredictions() {
        log.info("Polling MBTA predictions...");
        predictionService.fetchAndSavePredictions("place-pktrm");
        predictionService.fetchAndSavePredictions("place-dwnxg");
        predictionService.fetchAndSavePredictions("place-ogmnl");
        log.info("Done polling.");
    }
}