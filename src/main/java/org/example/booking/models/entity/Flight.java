package org.example.booking.models.entity;

import jakarta.persistence.*;

import java.time.Year;

@Entity
public class Flight {
    @Id
    private Long id;
    private String name;
    private Year year;
    private int rows;
    private int columns;

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setAirline(AirLine airline) {
        this.airline = airline;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Year getYear() {
        return year;
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public AirLine getAirline() {
        return airline;
    }

    @ManyToOne
    @JoinColumn(name = "id")
    private AirLine airline;
}
