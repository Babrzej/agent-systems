package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccommodationOffer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String propertyId;
    private String providerAgentName;
    private String propertyName;
    private String city;
    private String country;
    private String type; // hotel, guesthouse, apartment, hostel
    private int stars;
    private double rating;
    private double distanceCenterKm;
    private double nightlyPriceEur;
    private boolean breakfastIncluded;
    private boolean freeCancellation;
    private boolean wifi;
    private int capacity;
    private boolean negotiationEnabled;
    private int maxDiscountPercent;

    // reduced constructor for basic setup
    public AccommodationOffer(String propertyId, String propertyName, String city,
                              int stars, double rating, double distanceCenterKm, double nightlyPriceEur) {
        this.propertyId = propertyId;
        this.propertyName = propertyName;
        this.city = city;
        this.stars = stars;
        this.rating = rating;
        this.distanceCenterKm = distanceCenterKm;
        this.nightlyPriceEur = nightlyPriceEur;
    }
}
