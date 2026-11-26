package org.example.gui;

import org.example.agents.UserAgent;
import org.example.models.TravelPlan;
import org.example.models.TravelRequest;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TravelPlannerGUI extends JFrame {
    private final JTextField originField = new JTextField("Krakow", 10);
    private final JTextField destField = new JTextField("Warsaw", 10);
    private final JTextField maxTransportField = new JTextField("243.87", 8);
    private final JTextField maxHotelField = new JTextField("199.25", 8);
    private final JTextField nightsField = new JTextField("5", 4);
    private final JTextArea outputArea = new JTextArea(10, 45);

    public TravelPlannerGUI(UserAgent userAgent) {
        setTitle("Travel Planner - Checkpoint 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(8, 8));

        JPanel top = new JPanel(new FlowLayout());
        top.add(new JLabel("Origin:")); top.add(originField);
        top.add(new JLabel("Dest:")); top.add(destField);
        top.add(new JLabel("Max Trans (€):")); top.add(maxTransportField);
        top.add(new JLabel("Max Hotel (€):")); top.add(maxHotelField);
        top.add(new JLabel("Nights:")); top.add(nightsField);

        JButton sendBtn = new JButton("Send Request");
        top.add(sendBtn);
        add(top, BorderLayout.NORTH);

        outputArea.setEditable(false);
        add(new JScrollPane(outputArea), BorderLayout.CENTER);

        sendBtn.addActionListener(e -> {
            TravelRequest req = new TravelRequest(
                    "R0001",
                    originField.getText().trim(),
                    destField.getText().trim(),
                    Double.parseDouble(maxTransportField.getText().trim()),
                    Double.parseDouble(maxHotelField.getText().trim()),
                    Integer.parseInt(nightsField.getText().trim()),
                    "ANY", 258, 7.8, 4.9, false
            );
            outputArea.setText("Sending REQUEST to PlannerAgent...\n");
            userAgent.sendTravelRequest(req);
        });

        pack();
        setLocationRelativeTo(null);
    }

    public void showResults(List<TravelPlan> plans) {
        outputArea.append("Received " + plans.size() + " plan(s) via INFORM:\n\n");
        for (TravelPlan p : plans) {
            outputArea.append(String.format("- %s (%s) + %s (%d nights) | Total: €%.2f%n",
                    p.getTransport().getProvider(),
                    p.getTransport().getMode(),
                    p.getAccommodation().getPropertyName(),
                    p.getNights(),
                    p.getTotalPriceEur()));
        }
    }
}