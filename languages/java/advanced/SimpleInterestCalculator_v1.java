package practice_projects.languages.java.advanced;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class SimpleInterestCalculator_v1 extends JFrame {
    private JSlider principalSlider, rateSlider, timeSlider;
    private JLabel principalLabel, rateLabel, timeLabel, totalLabel, interestLabel, totalAmountLabel;
    private JPanel chartPanel;

    public SimpleInterestCalculator_v1() {
        setTitle("Simple Interest Calculator");
        setSize(500, 400);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sliders Panel
        JPanel slidersPanel = new JPanel(new GridLayout(3, 2));

        // Principal Amount Slider
        principalSlider = new JSlider(1000, 200000, 100000);
        principalSlider.setMajorTickSpacing(50000);
        principalSlider.setPaintTicks(true);
        principalSlider.setPaintLabels(true);
        principalLabel = new JLabel("Principal Amount: ₹ " + principalSlider.getValue());

        // Rate of Interest Slider
        rateSlider = new JSlider(1, 15, 6);
        rateSlider.setMajorTickSpacing(2);
        rateSlider.setPaintTicks(true);
        rateSlider.setPaintLabels(true);
        rateLabel = new JLabel("Rate of Interest: " + rateSlider.getValue() + "%");

        // Time Period Slider
        timeSlider = new JSlider(1, 30, 5);
        timeSlider.setMajorTickSpacing(5);
        timeSlider.setPaintTicks(true);
        timeSlider.setPaintLabels(true);
        timeLabel = new JLabel("Time Period: " + timeSlider.getValue() + " Years");

        slidersPanel.add(principalLabel);
        slidersPanel.add(principalSlider);
        slidersPanel.add(rateLabel);
        slidersPanel.add(rateSlider);
        slidersPanel.add(timeLabel);
        slidersPanel.add(timeSlider);

        add(slidersPanel, BorderLayout.NORTH);

        // Results Panel
        JPanel resultsPanel = new JPanel(new GridLayout(3, 1));
        interestLabel = new JLabel();
        totalAmountLabel = new JLabel();
        updateResults();  // Initial calculation

        resultsPanel.add(interestLabel);
        resultsPanel.add(totalAmountLabel);
        add(resultsPanel, BorderLayout.CENTER);

        // Chart Panel (Pie Chart)
        chartPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawPieChart(g);
            }
        };
        add(chartPanel, BorderLayout.SOUTH);

        // Listeners for Sliders
        ChangeListener listener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateResults();
                repaint();
            }
        };
        principalSlider.addChangeListener(listener);
        rateSlider.addChangeListener(listener);
        timeSlider.addChangeListener(listener);
    }

    private void updateResults() {
        double principal = principalSlider.getValue();
        double rate = rateSlider.getValue();
        double time = timeSlider.getValue();

        double interest = (principal * rate * time) / 100;
        double totalAmount = principal + interest;

        interestLabel.setText("Total Interest: ₹ " + (int) interest);
        totalAmountLabel.setText("Total Amount: ₹ " + (int) totalAmount);
    }

    private void drawPieChart(Graphics g) {
        double principal = principalSlider.getValue();
        double rate = rateSlider.getValue();
        double time = timeSlider.getValue();
        double interest = (principal * rate * time) / 100;
        double totalAmount = principal + interest;

        int principalAngle = (int) ((principal / totalAmount) * 360);
        int interestAngle = 360 - principalAngle;

        g.setColor(Color.BLUE);
        g.fillArc(180, 30, 100, 100, 0, principalAngle);

        g.setColor(Color.GREEN);
        g.fillArc(180, 30, 100, 100, principalAngle, interestAngle);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SimpleInterestCalculator_v1 calculator = new SimpleInterestCalculator_v1();
            calculator.setVisible(true);
        });
    }
}
