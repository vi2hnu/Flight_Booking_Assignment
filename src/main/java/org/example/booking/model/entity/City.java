package org.example.booking.model.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class City {
    @Id
    long id;
    String cityName;
    String airportCode;

    public void setId(long id) {
        this.id = id;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public long getId() {
        return id;
    }

    public String getCityName() {
        return cityName;
    }

    public String getAirportCode() {
        return airportCode;
    }
}
