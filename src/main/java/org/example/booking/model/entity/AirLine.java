package org.example.booking.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class AirLine {
    @Id
    private long id;

    public void setId(long id) {
        this.id = id;
    }

    public void setAirlineName(String airlineName) {
        this.airlineName = airlineName;
    }

    public String getAirlineName() {
        return airlineName;
    }

    public long getId() {
        return id;
    }

    private String airlineName;
}
