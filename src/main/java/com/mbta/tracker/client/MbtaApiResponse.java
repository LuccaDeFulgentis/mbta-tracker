package com.mbta.tracker.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MbtaApiResponse {

    private List<PredictionData> data;

    public List<PredictionData> getData() { return data; }
    public void setData(List<PredictionData> data) { this.data = data; }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PredictionData {
        private String id;
        private Attributes attributes;
        private Relationships relationships;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }

        public Attributes getAttributes() { return attributes; }
        public void setAttributes(Attributes attributes) { this.attributes = attributes; }

        public Relationships getRelationships() { return relationships; }
        public void setRelationships(Relationships relationships) { this.relationships = relationships; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Attributes {
        private String arrival_time;
        private String departure_time;
        private Integer arrival_uncertainty;
        private Integer direction_id;
        private String status;
        private String schedule_relationship;
        private Integer stop_sequence;

        public String getArrival_time() { return arrival_time; }
        public void setArrival_time(String arrival_time) { this.arrival_time = arrival_time; }

        public String getDeparture_time() { return departure_time; }
        public void setDeparture_time(String departure_time) { this.departure_time = departure_time; }

        public Integer getArrival_uncertainty() { return arrival_uncertainty; }
        public void setArrival_uncertainty(Integer arrival_uncertainty) { this.arrival_uncertainty = arrival_uncertainty; }

        public Integer getDirection_id() { return direction_id; }
        public void setDirection_id(Integer direction_id) { this.direction_id = direction_id; }

        public String getStatus() { return status; }
        public void setStatus(String status) { this.status = status; }

        public String getSchedule_relationship() { return schedule_relationship; }
        public void setSchedule_relationship(String schedule_relationship) { this.schedule_relationship = schedule_relationship; }

        public Integer getStop_sequence() { return stop_sequence; }
        public void setStop_sequence(Integer stop_sequence) { this.stop_sequence = stop_sequence; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Relationships {
        private RelationshipData route;
        private RelationshipData stop;
        private RelationshipData trip;
        private RelationshipData vehicle;

        public RelationshipData getRoute() { return route; }
        public void setRoute(RelationshipData route) { this.route = route; }

        public RelationshipData getStop() { return stop; }
        public void setStop(RelationshipData stop) { this.stop = stop; }

        public RelationshipData getTrip() { return trip; }
        public void setTrip(RelationshipData trip) { this.trip = trip; }

        public RelationshipData getVehicle() { return vehicle; }
        public void setVehicle(RelationshipData vehicle) { this.vehicle = vehicle; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class RelationshipData {
        private Data data;

        public Data getData() { return data; }
        public void setData(Data data) { this.data = data; }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Data {
        private String id;

        public String getId() { return id; }
        public void setId(String id) { this.id = id; }
    }
}