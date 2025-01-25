package practice_projects.languages.java.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUICalculator extends JFrame implements ActionListener {

    private JTextField input1, input2, result;
    private JButton addButton, subtractiButton, multiplyButton, divideButton;

    // Constructor to set up the GUI
    public GUICalculator() {
    // Set up the JFrame
    setTitle("Calculator");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLayout(new GridLayout(5, 2, 10, 10));

    // Create labels and text fields
    JLabel label1 = new JLabel("Enter First Number:");
    input1 = new JTextField();
    JLabel label2 = new JLabel("Enter Second Number:");
    input2 = new JTextField();
    JLabel labelResult = new JLabel("Result:");
    result = new JTextField();
    result.setEditable(false); // Result field is not editable 

    // Create buttons for operations
    addButton = new JButton("Add");
    subtractiButton = new JButton("Subtract");
    multiplyButton = new JButton("Mulitply");
    divideButton = new JButton("Divide");

    // Add action listeners to buttons
    addButton.addActionListener(this);
    subtractiButton.addActionListener(this);
    multiplyButton.addActionListener(this);
    divideButton.addActionListener(this);

    // Add components to the JFrame
    add(label1);
    add(input1);
    add(label2);
    add(input2);
    add(labelResult);
    add(result);
    add(addButton);
    add(subtractiButton);
    add(multiplyButton);
    add(divideButton);

    setVisible(true); // Make the frame visible

    }

    // Action listener for button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            double num1 = Double.parseDouble(input1.getText());
            double num2 = Double.parseDouble(input2.getText());
            double res = 0;

            // Determine which button was clicked
            if (e.getSource() == addButton) {
                res = num1 + num2;
            } else if (e.getSource() == subtractiButton) {
                res = num1 - num2;
            } else if (e.getSource() == multiplyButton) {
                res = num1 * num2;
            } else if (e.getSource() == divideButton) {
                if (num2 != 0) {
                    res = num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Division by zero is not allowed");
                    return;
                }
            }

            // Display the result
            result.setText(String.format("%.2f", res));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Error: Please enter valid numbers.");
        }
    }

    // Main method to launch the application
    public static void main(String[] args) {
        new GUICalculator();
    }
}