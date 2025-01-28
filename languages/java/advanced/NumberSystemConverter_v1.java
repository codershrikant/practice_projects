package practice_projects.languages.java.advanced;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NumberSystemConverter_v1 {

    public static void main(String[] args) {
        // Create frame
        JFrame frame = new JFrame("Number System Converter");
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 1));

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter Value:"));
        JTextField inputField = new JTextField(20);
        inputPanel.add(inputField);
        frame.add(inputPanel);

        // Conversion Options Panel
        JPanel optionsPanel = new JPanel();
        String[] options = {
            "Decimal to Binary", "Decimal to Octal", "Decimal to Hexadecimal",
            "Binary to Decimal", "Binary to Octal", "Binary to Hexadecimal",
            "Octal to Decimal", "Octal to Binary", "Octal to Hexadecimal",
            "Hexadecimal to Decimal", "Hexadecimal to Binary", "Hexadecimal to Octal"
        };
        JComboBox<String> conversionOptions = new JComboBox<>(options);
        optionsPanel.add(new JLabel("Select Conversion:"));
        optionsPanel.add(conversionOptions);
        frame.add(optionsPanel);

        // Result Panel
        JPanel resultPanel = new JPanel();
        resultPanel.add(new JLabel("Result:"));
        JTextField resultField = new JTextField(20);
        resultField.setEditable(false);
        resultPanel.add(resultField);
        frame.add(resultPanel);

        // Convert Button
        JPanel buttonPanel = new JPanel();
        JButton convertButton = new JButton("Convert");
        buttonPanel.add(convertButton);
        frame.add(buttonPanel);

        // History Panel
        JPanel historyPanel = new JPanel();
        JTextArea historyArea = new JTextArea(5, 40);
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        historyPanel.add(new JLabel("Conversion History:"));
        historyPanel.add(scrollPane);
        frame.add(historyPanel);

        // Action Listener for Convert Button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String input = inputField.getText().trim();
                String selectedOption = (String) conversionOptions.getSelectedItem();

                try {
                    String result = "";

                    // Perform the conversion based on selected option
                    switch (selectedOption) {
                        case "Decimal to Binary":
                            result = Integer.toBinaryString(Integer.parseInt(input));
                            break;
                        case "Decimal to Octal":
                            result = Integer.toOctalString(Integer.parseInt(input));
                            break;
                        case "Decimal to Hexadecimal":
                            result = Integer.toHexString(Integer.parseInt(input)).toUpperCase();
                            break;
                        case "Binary to Decimal":
                            result = String.valueOf(Integer.parseInt(input, 2));
                            break;
                        case "Binary to Octal":
                            result = Integer.toOctalString(Integer.parseInt(input, 2));
                            break;
                        case "Binary to Hexadecimal":
                            result = Integer.toHexString(Integer.parseInt(input, 2)).toUpperCase();
                            break;
                        case "Octal to Decimal":
                            result = String.valueOf(Integer.parseInt(input, 8));
                            break;
                        case "Octal to Binary":
                            result = Integer.toBinaryString(Integer.parseInt(input, 8));
                            break;
                        case "Octal to Hexadecimal":
                            result = Integer.toHexString(Integer.parseInt(input, 8)).toUpperCase();
                            break;
                        case "Hexadecimal to Decimal":
                            result = String.valueOf(Integer.parseInt(input, 16));
                            break;
                        case "Hexadecimal to Binary":
                            result = Integer.toBinaryString(Integer.parseInt(input, 16));
                            break;
                        case "Hexadecimal to Octal":
                            result = Integer.toOctalString(Integer.parseInt(input, 16));
                            break;
                        default:
                            result = "Invalid Option";
                    }

                    // Display result and append to history
                    resultField.setText(result);
                    historyArea.append(selectedOption + " of " + input + " = " + result + "\n");

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(frame, "Invalid input! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set frame visibility
        frame.setVisible(true);
    }
}