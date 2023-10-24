package com.deepa.billing.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class PriceSlab {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int startUnits;
    private int endUnits;
    private double rate;
    private LocalDate startDate;
    private LocalDate endDate;

    public PriceSlab() {
    }

    // Constructor
    public PriceSlab(int startUnits, int endUnits, double rate) {
        this.startUnits = startUnits;
        this.endUnits = endUnits;
        this.rate = rate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getStartUnits() {
        return startUnits;
    }

    public void setStartUnits(int startUnits) {
        this.startUnits = startUnits;
    }

    public int getEndUnits() {
        return endUnits;
    }

    public void setEndUnits(int endUnits) {
        this.endUnits = endUnits;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

}
