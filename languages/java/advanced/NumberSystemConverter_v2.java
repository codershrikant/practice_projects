package practice_projects.languages.java.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NumberSystemConverter_v2 {
    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Number System Converter");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout(10, 10));

        // Input and Options Panel
        JPanel topPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(new JLabel("Enter Value:"));
        JTextField inputField = new JTextField(20);
        inputPanel.add(inputField);

        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] options = {
            "Decimal to Binary", "Decimal to Octal", "Decimal to Hexadecimal",
            "Binary to Decimal", "Binary to Octal", "Binary to Hexadecimal",
            "Octal to Decimal", "Octal to Binary", "Octal to Hexadecimal",
            "Hexadecimal to Decimal", "Hexadecimal to Binary", "Hexadecimal to Octal"
        };
        JComboBox<String> conversionOptions = new JComboBox<>(options);
        optionsPanel.add(new JLabel("Select Conversion:"));
        optionsPanel.add(conversionOptions);

        topPanel.add(inputPanel);
        topPanel.add(optionsPanel);
        frame.add(topPanel, BorderLayout.NORTH);

        // Result Panel
        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        resultPanel.add(new JLabel("Result:"));
        JTextField resultField = new JTextField(30);
        resultField.setEditable(false);
        resultField.setFont(new Font("Arial", Font.BOLD, 14));
        resultPanel.add(resultField);
        frame.add(resultPanel, BorderLayout.CENTER);

        // Convert and History Panel
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));

        JButton convertButton = new JButton("Convert");
        convertButton.setPreferredSize(new Dimension(100, 30));
        bottomPanel.add(convertButton, BorderLayout.WEST);

        JTextArea historyArea = new JTextArea(8, 40);
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Conversion History"));
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Action Listener for Convert Button
        convertButton.addActionListener((ActionEvent e) -> {
            String input = inputField.getText().trim();
            String selectedOption = (String) conversionOptions.getSelectedItem();

            try {
                String result = switch (selectedOption) {
                    case "Decimal to Binary" -> Integer.toBinaryString(Integer.parseInt(input));
                    case "Decimal to Octal" -> Integer.toOctalString(Integer.parseInt(input));
                    case "Decimal to Hexadecimal" -> Integer.toHexString(Integer.parseInt(input)).toUpperCase();
                    case "Binary to Decimal" -> String.valueOf(Integer.parseInt(input, 2));
                    case "Binary to Octal" -> Integer.toOctalString(Integer.parseInt(input, 2));
                    case "Binary to Hexadecimal" -> Integer.toHexString(Integer.parseInt(input, 2)).toUpperCase();
                    case "Octal to Decimal" -> String.valueOf(Integer.parseInt(input, 8));
                    case "Octal to Binary" -> Integer.toBinaryString(Integer.parseInt(input, 8));
                    case "Octal to Hexadecimal" -> Integer.toHexString(Integer.parseInt(input, 8)).toUpperCase();
                    case "Hexadecimal to Decimal" -> String.valueOf(Integer.parseInt(input, 16));
                    case "Hexadecimal to Binary" -> Integer.toBinaryString(Integer.parseInt(input, 16));
                    case "Hexadecimal to Octal" -> Integer.toOctalString(Integer.parseInt(input, 16));
                    default -> "Invalid Option";
                };

                // Display result and append to history
                resultField.setText(result);
                historyArea.append(selectedOption + " of " + input + " = " + result + "\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Set frame visibility
        frame.setVisible(true);
    }
}
