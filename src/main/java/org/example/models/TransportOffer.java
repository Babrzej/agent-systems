package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransportOffer implements Serializable {
    private static final long serialVersionUID = 1L;

    private String optionId;
    private String providerAgentName;
    private String mode; // TRAIN or AIR
    private String provider;
    private String originCity;
    private String originCountry;
    private String destinationCity;
    private String destinationCountry;
    private String departureTime;
    private String arrivalTime;
    private int durationMin;
    private double priceEur;
    private int transfers;
    private double comfortScore;
    private boolean refundable;

    // reduced constructor for basic setup
    public TransportOffer(String optionId, String mode, String provider,
                          String originCity, String destinationCity, int durationMin, double priceEur) {
        this.optionId = optionId;
        this.mode = mode;
        this.provider = provider;
        this.originCity = originCity;
        this.destinationCity = destinationCity;
        this.durationMin = durationMin;
        this.priceEur = priceEur;
    }
}
