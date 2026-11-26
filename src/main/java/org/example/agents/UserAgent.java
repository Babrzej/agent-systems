package org.example.agents;

import org.example.gui.TravelPlannerGUI;
import org.example.models.TravelPlan;
import org.example.models.TravelRequest;
import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.CyclicBehaviour;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

import javax.swing.*;
import java.util.List;

public class UserAgent extends Agent {
    private TravelPlannerGUI gui;

    @Override
    protected void setup() {
        System.out.println(getLocalName() + " ready.");
        SwingUtilities.invokeLater(() -> {
            gui = new TravelPlannerGUI(this);
            gui.setVisible(true);
        });

        addBehaviour(new CyclicBehaviour(this) {
            @Override
            public void action() {
                ACLMessage msg = receive(MessageTemplate.MatchPerformative(ACLMessage.INFORM));
                if (msg != null) {
                    try {
                        @SuppressWarnings("unchecked")
                        List<TravelPlan> plans = (List<TravelPlan>) msg.getContentObject();
                        System.out.println(getLocalName() + " received INFORM with " + plans.size() + " plan(s).");
                        SwingUtilities.invokeLater(() -> gui.showResults(plans));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    block();
                }
            }
        });
    }

    public void sendTravelRequest(TravelRequest request) {
        addBehaviour(new OneShotBehaviour(this) {
            @Override
            public void action() {
                try {
                    ACLMessage msg = new ACLMessage(ACLMessage.REQUEST);
                    msg.addReceiver(new AID("plannerAgent", AID.ISLOCALNAME));
                    msg.setConversationId("travel-planning");
                    msg.setContentObject(request);
                    send(msg);
                    System.out.println(getLocalName() + " sent REQUEST to plannerAgent.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}