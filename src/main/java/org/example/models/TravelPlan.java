package org.example.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class TravelPlan implements Serializable {
    private static final long serialVersionUID = 1L;

    private TransportOffer transport;
    private AccommodationOffer accommodation;
    private int nights;
    private double totalPriceEur;
    private double score;
    private boolean negotiated;

    public TravelPlan(TransportOffer transport, AccommodationOffer accommodation, int nights) {
        this.transport = transport;
        this.accommodation = accommodation;
        this.nights = nights;
        recalculateTotalPrice();
    }

    public void recalculateTotalPrice() {
        this.totalPriceEur = transport.getPriceEur() + (accommodation.getNightlyPriceEur() * nights);
    }
}
