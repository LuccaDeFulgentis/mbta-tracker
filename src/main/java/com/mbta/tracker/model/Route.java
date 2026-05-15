package com.mbta.tracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "routes")
public class Route {

    @Id
    private String id;

    @Column(name = "long_name", nullable = false)
    private String longName;

    @Column(name = "color")
    private String color;

    @Column(name = "type", nullable = false)
    private Short type;

    @Column(name = "direction_0")
    private String direction0;

    @Column(name = "direction_1")
    private String direction1;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getLongName() { return longName; }
    public void setLongName(String longName) { this.longName = longName; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public Short getType() { return type; }
    public void setType(Short type) { this.type = type; }

    public String getDirection0() { return direction0; }
    public void setDirection0(String direction0) { this.direction0 = direction0; }

    public String getDirection1() { return direction1; }
    public void setDirection1(String direction1) { this.direction1 = direction1; }
}