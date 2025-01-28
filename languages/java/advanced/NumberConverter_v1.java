package practice_projects.languages.java.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NumberConverter_v1 {
    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Number Converter");
        frame.setSize(350, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(2, 3, 5, 5));

        // Create labels
        JLabel decimalLabel = new JLabel("Decimal >");
        JLabel binaryLabel = new JLabel("Binary >");
        JLabel equalsLabel = new JLabel("=");

        // Create text fields
        JTextField decimalField = new JTextField("0.00");
        JTextField binaryField = new JTextField("0.00");
        binaryField.setEditable(false);

        // Add real-time conversion
        decimalField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    int decimalValue = Integer.parseInt(decimalField.getText());
                    binaryField.setText(Integer.toBinaryString(decimalValue));
                } catch (NumberFormatException ex) {
                    binaryField.setText("Invalid");
                }
            }
        });

        // Add components to frame
        frame.add(decimalLabel);
        frame.add(equalsLabel);
        frame.add(binaryLabel);
        frame.add(decimalField);
        frame.add(new JLabel()); // Empty space
        frame.add(binaryField);

        // Make frame visible
        frame.setVisible(true);
    }
}
