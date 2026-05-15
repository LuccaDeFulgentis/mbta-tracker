package com.mbta.tracker.repository;

import com.mbta.tracker.model.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, String> {

    List<Prediction> findByStopIdOrderByArrivalTimeAsc(String stopId);

    List<Prediction> findByRouteIdOrderByRecordedAtDesc(String routeId);
}