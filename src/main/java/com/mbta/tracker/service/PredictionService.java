package com.mbta.tracker.service;

import com.mbta.tracker.client.MbtaApiClient;
import com.mbta.tracker.client.MbtaApiResponse;
import com.mbta.tracker.model.Prediction;
import com.mbta.tracker.model.Route;
import com.mbta.tracker.model.Stop;
import com.mbta.tracker.repository.PredictionRepository;
import com.mbta.tracker.repository.RouteRepository;
import com.mbta.tracker.repository.StopRepository;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.time.OffsetDateTime;
import java.util.List;

@Service
public class PredictionService {

    private final MbtaApiClient mbtaApiClient;
    private final PredictionRepository predictionRepository;
    private final RouteRepository routeRepository;
    private final StopRepository stopRepository;

    public PredictionService(MbtaApiClient mbtaApiClient,
                             PredictionRepository predictionRepository,
                             RouteRepository routeRepository,
                             StopRepository stopRepository) {
        this.mbtaApiClient = mbtaApiClient;
        this.predictionRepository = predictionRepository;
        this.routeRepository = routeRepository;
        this.stopRepository = stopRepository;
    }

    @CacheEvict(value = "predictions", key = "#stopId")
    public void fetchAndSavePredictions(String stopId) {
        MbtaApiResponse response = mbtaApiClient.getPredictionsForStop(stopId);

        if (response == null || response.getData() == null) {
            return;
        }

        for (MbtaApiResponse.PredictionData data : response.getData()) {
            Prediction prediction = new Prediction();
            prediction.setId(data.getId());

            MbtaApiResponse.Attributes attr = data.getAttributes();
            if (attr.getArrival_time() != null) {
                prediction.setArrivalTime(OffsetDateTime.parse(attr.getArrival_time()));
            }
            if (attr.getDeparture_time() != null) {
                prediction.setDepartureTime(OffsetDateTime.parse(attr.getDeparture_time()));
            }
            prediction.setArrivalUncertainty(attr.getArrival_uncertainty());
            if (attr.getDirection_id() != null) {
                prediction.setDirectionId(attr.getDirection_id().shortValue());
            }
            prediction.setStatus(attr.getStatus());
            prediction.setScheduleRelationship(attr.getSchedule_relationship());
            prediction.setStopSequence(attr.getStop_sequence());
            prediction.setRecordedAt(OffsetDateTime.now());

            if (data.getRelationships() != null) {
                if (data.getRelationships().getRoute() != null &&
                    data.getRelationships().getRoute().getData() != null) {
                    String routeId = data.getRelationships().getRoute().getData().getId();
                    if (!routeRepository.existsById(routeId)) {
                        Route route = new Route();
                        route.setId(routeId);
                        route.setLongName(routeId);
                        route.setType((short) 0);
                        routeRepository.save(route);
                    }
                    prediction.setRoute(routeRepository.getReferenceById(routeId));
                }
                if (data.getRelationships().getStop() != null &&
                    data.getRelationships().getStop().getData() != null) {
                    String sid = data.getRelationships().getStop().getData().getId();
                    if (!stopRepository.existsById(sid)) {
                        Stop stop = new Stop();
                        stop.setId(sid);
                        stop.setName(sid);
                        stopRepository.save(stop);
                    }
                    prediction.setStop(stopRepository.getReferenceById(sid));
                }
                if (data.getRelationships().getVehicle() != null &&
                    data.getRelationships().getVehicle().getData() != null) {
                    prediction.setVehicleId(
                        data.getRelationships().getVehicle().getData().getId()
                    );
                }
                if (data.getRelationships().getTrip() != null &&
                    data.getRelationships().getTrip().getData() != null) {
                    prediction.setTripId(
                        data.getRelationships().getTrip().getData().getId()
                    );
                }
            }

            predictionRepository.save(prediction);
        }
    }

    @Cacheable(value = "predictions", key = "#stopId")
    public List<Prediction> getPredictionsForStop(String stopId) {
        return predictionRepository.findByStopIdOrderByArrivalTimeAsc(stopId);
    }
}