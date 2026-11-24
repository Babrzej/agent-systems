package org.example.agents;

import jade.core.Agent;

public class UserAgent extends Agent {
    @Override
    protected void setup() {
        System.out.println(getLocalName() + " started and ready.");
    }
}