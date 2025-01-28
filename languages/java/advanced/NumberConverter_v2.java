package practice_projects.languages.java.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NumberConverter_v2 {
    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Number Converter");
        frame.setSize(400, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 2, 5, 5));

        // Create labels
        JLabel inputLabel = new JLabel("Input:");
        JLabel outputLabel = new JLabel("Output:");
        
        // Create text fields
        JTextField inputField = new JTextField("0.00");
        JTextField outputField = new JTextField("0.00");
        outputField.setEditable(false);
        
        // Create dropdown menus
        String[] numberSystems = {"Decimal", "Binary", "Octal", "Hexadecimal"};
        JComboBox<String> inputType = new JComboBox<>(numberSystems);
        JComboBox<String> outputType = new JComboBox<>(numberSystems);

        // Add real-time conversion
        inputField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                try {
                    String inputText = inputField.getText();
                    String fromType = (String) inputType.getSelectedItem();
                    String toType = (String) outputType.getSelectedItem();
                    
                    String result = convertNumber(inputText, fromType, toType);
                    outputField.setText(result);
                } catch (Exception ex) {
                    outputField.setText("Invalid");
                }
            }
        });
        
        // Add components to frame
        frame.add(inputLabel);
        frame.add(inputField);
        frame.add(inputType);
        frame.add(outputLabel);
        frame.add(outputField);
        frame.add(outputType);
        
        // Make frame visible
        frame.setVisible(true);
    }
    
    public static String convertNumber(String input, String fromType, String toType) {
        try {
            int decimalValue;
            switch (fromType) {
                case "Binary":
                    decimalValue = Integer.parseInt(input, 2);
                    break;
                case "Octal":
                    decimalValue = Integer.parseInt(input, 8);
                    break;
                case "Hexadecimal":
                    decimalValue = Integer.parseInt(input, 16);
                    break;
                default:
                    decimalValue = Integer.parseInt(input);
            }
            
            switch (toType) {
                case "Binary":
                    return Integer.toBinaryString(decimalValue);
                case "Octal":
                    return Integer.toOctalString(decimalValue);
                case "Hexadecimal":
                    return Integer.toHexString(decimalValue).toUpperCase();
                default:
                    return String.valueOf(decimalValue);
            }
        } catch (Exception e) {
            return "Invalid";
        }
    }
}
