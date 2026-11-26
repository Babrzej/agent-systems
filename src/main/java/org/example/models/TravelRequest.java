package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelRequest implements Serializable {
    private static final long serialVersionUID = 1L;

    private String requestId;
    private String origin;
    private String destination;
    private double maxTransportEur;
    private double maxHotelPerNightEur;
    private int nights;
    private String preferredMode; // "ANY", "TRAIN", "AIR"
    private int maxTransportDurationMin;
    private double minHotelRating;
    private double maxCenterDistanceKm;
    private boolean negotiationEnabled;

    public double getTotalMaxBudget() {
        return maxTransportEur + (maxHotelPerNightEur * nights);
    }
}