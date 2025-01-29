package practice_projects.languages.java.advanced;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class CompoundInterestCalculator extends JFrame {
    private JSlider principalSlider, rateSlider, timeSlider, frequencySlider;
    private JLabel principalLabel, rateLabel, timeLabel, frequencyLabel, interestLabel, totalAmountLabel;
    private JPanel chartPanel;

    public CompoundInterestCalculator() {
        setTitle("Compound Interest Calculator");
        setSize(500, 500);
        setLayout(new BorderLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Sliders Panel
        JPanel slidersPanel = new JPanel(new GridLayout(4, 2));

        // Principal Amount Slider
        principalSlider = new JSlider(1000, 200000, 100000);
        principalSlider.setMajorTickSpacing(50000);
        principalSlider.setPaintTicks(true);
        principalSlider.setPaintLabels(true);
        principalLabel = new JLabel();

        // Rate of Interest Slider
        rateSlider = new JSlider(1, 15, 6);
        rateSlider.setMajorTickSpacing(2);
        rateSlider.setPaintTicks(true);
        rateSlider.setPaintLabels(true);
        rateLabel = new JLabel();

        // Time Period Slider
        timeSlider = new JSlider(1, 30, 5);
        timeSlider.setMajorTickSpacing(5);
        timeSlider.setPaintTicks(true);
        timeSlider.setPaintLabels(true);
        timeLabel = new JLabel();

        // Compounding Frequency Slider
        frequencySlider = new JSlider(1, 12, 4);  // Compounded Monthly (Default: Quarterly)
        frequencySlider.setMajorTickSpacing(3);
        frequencySlider.setPaintTicks(true);
        frequencySlider.setPaintLabels(true);
        frequencyLabel = new JLabel();

        slidersPanel.add(principalLabel);
        slidersPanel.add(principalSlider);
        slidersPanel.add(rateLabel);
        slidersPanel.add(rateSlider);
        slidersPanel.add(timeLabel);
        slidersPanel.add(timeSlider);
        slidersPanel.add(frequencyLabel);
        slidersPanel.add(frequencySlider);

        add(slidersPanel, BorderLayout.NORTH);

        // Results Panel
        JPanel resultsPanel = new JPanel(new GridLayout(3, 1));
        interestLabel = new JLabel();
        totalAmountLabel = new JLabel();
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
        chartPanel.setPreferredSize(new Dimension(200, 150));
        add(chartPanel, BorderLayout.SOUTH);

        // Listener for Sliders
        ChangeListener listener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                updateResults();
            }
        };
        principalSlider.addChangeListener(listener);
        rateSlider.addChangeListener(listener);
        timeSlider.addChangeListener(listener);
        frequencySlider.addChangeListener(listener);

        // Initial Update
        updateResults();
    }

    private void updateResults() {
        double principal = principalSlider.getValue();
        double rate = rateSlider.getValue() / 100.0; // Convert to decimal
        double time = timeSlider.getValue();
        double n = frequencySlider.getValue(); // Compounding times per year

        // Compound Interest Formula
        double amount = principal * Math.pow((1 + rate / n), (n * time));
        double interest = amount - principal;

        principalLabel.setText("Principal Amount: ₹ " + principal);
        rateLabel.setText("Rate of Interest: " + (rate * 100) + "%");
        timeLabel.setText("Time Period: " + time + " Years");
        frequencyLabel.setText("Compounded " + (int)n + " times per year");

        interestLabel.setText("Total Interest: ₹ " + (int) interest);
        totalAmountLabel.setText("Total Amount: ₹ " + (int) amount);

        // Refresh Chart
        chartPanel.repaint();
    }

    private void drawPieChart(Graphics g) {
        double principal = principalSlider.getValue();
        double rate = rateSlider.getValue() / 100.0;
        double time = timeSlider.getValue();
        double n = frequencySlider.getValue();

        double amount = principal * Math.pow((1 + rate / n), (n * time));
        double interest = amount - principal;

        int principalAngle = (int) ((principal / amount) * 360);
        int interestAngle = 360 - principalAngle;

        g.setColor(Color.BLUE);
        g.fillArc(150, 20, 100, 100, 0, principalAngle);

        g.setColor(Color.GREEN);
        g.fillArc(150, 20, 100, 100, principalAngle, interestAngle);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CompoundInterestCalculator calculator = new CompoundInterestCalculator();
            calculator.setVisible(true);
        });
    }
}
