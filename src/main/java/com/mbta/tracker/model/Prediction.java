package com.mbta.tracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.time.OffsetDateTime;

@Entity
@Table(name = "predictions")
public class Prediction {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "route_id")
    private Route route;

    @ManyToOne
    @JoinColumn(name = "stop_id")
    private Stop stop;

    @Column(name = "trip_id")
    private String tripId;

    @Column(name = "vehicle_id")
    private String vehicleId;

    @Column(name = "arrival_time")
    private OffsetDateTime arrivalTime;

    @Column(name = "departure_time")
    private OffsetDateTime departureTime;

    @Column(name = "arrival_uncertainty")
    private Integer arrivalUncertainty;

    @Column(name = "direction_id")
    private Short directionId;

    @Column(name = "status")
    private String status;

    @Column(name = "schedule_relationship")
    private String scheduleRelationship;

    @Column(name = "stop_sequence")
    private Integer stopSequence;

    @Column(name = "recorded_at")
    private OffsetDateTime recordedAt;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public Route getRoute() { return route; }
    public void setRoute(Route route) { this.route = route; }

    public Stop getStop() { return stop; }
    public void setStop(Stop stop) { this.stop = stop; }

    public String getTripId() { return tripId; }
    public void setTripId(String tripId) { this.tripId = tripId; }

    public String getVehicleId() { return vehicleId; }
    public void setVehicleId(String vehicleId) { this.vehicleId = vehicleId; }

    public OffsetDateTime getArrivalTime() { return arrivalTime; }
    public void setArrivalTime(OffsetDateTime arrivalTime) { this.arrivalTime = arrivalTime; }

    public OffsetDateTime getDepartureTime() { return departureTime; }
    public void setDepartureTime(OffsetDateTime departureTime) { this.departureTime = departureTime; }

    public Integer getArrivalUncertainty() { return arrivalUncertainty; }
    public void setArrivalUncertainty(Integer arrivalUncertainty) { this.arrivalUncertainty = arrivalUncertainty; }

    public Short getDirectionId() { return directionId; }
    public void setDirectionId(Short directionId) { this.directionId = directionId; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getScheduleRelationship() { return scheduleRelationship; }
    public void setScheduleRelationship(String scheduleRelationship) { this.scheduleRelationship = scheduleRelationship; }

    public Integer getStopSequence() { return stopSequence; }
    public void setStopSequence(Integer stopSequence) { this.stopSequence = stopSequence; }

    public OffsetDateTime getRecordedAt() { return recordedAt; }
    public void setRecordedAt(OffsetDateTime recordedAt) { this.recordedAt = recordedAt; }
}