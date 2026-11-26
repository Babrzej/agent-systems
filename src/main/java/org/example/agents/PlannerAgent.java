package org.example.agents;

import org.example.models.*;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import java.util.ArrayList;
import java.util.List;

public class PlannerAgent extends Agent {

    @Override
    protected void setup() {
        System.out.println(getLocalName() + " ready.");

        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.REQUEST));
                if (msg != null) {
                    try {
                        TravelRequest req = (TravelRequest) msg.getContentObject();
                        System.out.println(getLocalName() + " received REQUEST from " + msg.getSender().getLocalName());

                        List<TravelPlan> plans = buildPreliminaryPlans(req);

                        ACLMessage reply = msg.createReply();
                        reply.setPerformative(ACLMessage.INFORM);
                        reply.setContentObject((java.io.Serializable) plans);
                        send(reply);
                        System.out.println(getLocalName() + " sent INFORM back to " + msg.getSender().getLocalName());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    block();
                }
            }
        });
    }

    private List<TravelPlan> buildPreliminaryPlans(TravelRequest req) {
        List<TravelPlan> feasible = new ArrayList<>();

        // Sample offers matching CSV structure for Checkpoint 1 demo
        TransportOffer t = new TransportOffer("T001", "TRAIN", "InterCity", req.getOrigin(), req.getDestination(), 160, 65.0);
        AccommodationOffer a = new AccommodationOffer("H001", "Central Hotel", req.getDestination(), 4, 8.4, 1.5, 95.0);

        TravelPlan plan = new TravelPlan(t, a, req.getNights());

        // Initial budget & duration constraint validation
        if (req.getNights() > 0
                && t.getPriceEur() <= req.getMaxTransportEur()
                && a.getNightlyPriceEur() <= req.getMaxHotelPerNightEur()
                && plan.getTotalPriceEur() <= req.getTotalMaxBudget()) {
            feasible.add(plan);
        }
        return feasible;
    }
}