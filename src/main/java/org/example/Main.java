package org.example;

import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.StaleProxyException;

public class Main {
    public static void main(String[] args) {
        Runtime rt = Runtime.instance();

        Profile profile = new ProfileImpl();
        profile.setParameter(Profile.MAIN_HOST, "localhost");
        profile.setParameter(Profile.GUI, "true"); // Launches the JADE RMA management & Sniffer GUI

        AgentContainer mainContainer = rt.createMainContainer(profile);

        try {
            // Start PlannerAgent first so it is ready before the UserAgent GUI opens
            mainContainer.createNewAgent("plannerAgent", "org.example.agents.PlannerAgent", null).start();
            mainContainer.createNewAgent("userAgent", "org.example.agents.UserAgent", null).start();

            // Uncomment once teammates push their initial agent classes:
            // mainContainer.createNewAgent("transportAgent1", "org.example.agents.TransportAgent",
            //         new Object[]{"data/europe_travel_transport.csv"}).start();
            // mainContainer.createNewAgent("accommodationAgent1", "org.example.agents.AccommodationAgent",
            //         new Object[]{"data/europe_travel_accommodation.csv"}).start();
            // mainContainer.createNewAgent("negotiationAgent", "org.example.agents.NegotiationAgent", null).start();

        } catch (StaleProxyException e) {
            e.printStackTrace();
        }
    }
}
