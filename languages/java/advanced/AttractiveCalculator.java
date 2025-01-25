package practice_projects.languages.java.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AttractiveCalculator extends JFrame implements ActionListener {

    // Components of the calculator
    private JTextField input1, input2, result;
    private JButton addButton, subtractButton, multiplyButton, divideButton, clearButton;

    // Constructor to set up the GUI
    public AttractiveCalculator() {
        // Set up the JFrame
        setTitle("Attractive Calculator");
        setSize(450, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        // Create a header panel
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Two Num Calculator");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 24));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.setBackground(new Color(32, 32, 32));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Create a main panel for input and buttons
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(6, 2, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(24, 24, 24));

        // Create labels and text fields
        JLabel label1 = new JLabel("Enter First Number:");
        label1.setFont(new Font("Arial", Font.PLAIN, 16));
        label1.setForeground(Color.WHITE);
        // label1.setBackground(new Color(32, 32, 32));

        input1 = new JTextField();
        input1.setForeground(Color.WHITE);
        input1.setBackground(new Color(45, 45, 45));
        input1.setFont(new Font("Arial", Font.PLAIN, 16));

        JLabel label2 = new JLabel("Enter Second Number:");
        label2.setFont(new Font("Arial", Font.PLAIN, 16));
        label2.setForeground(Color.WHITE);

        input2 = new JTextField();
        input2.setForeground(Color.WHITE);
        input2.setBackground(new Color(45, 45, 45));

        JLabel labelResult = new JLabel("Result:");
        labelResult.setFont(new Font("Arial", Font.PLAIN, 16));
        labelResult.setForeground(Color.WHITE);

        result = new JTextField();
        result.setEditable(false);
        result.setForeground(Color.WHITE);
        result.setBackground(new Color(45, 45, 45));

        // Create buttons for operations
        addButton = new JButton("Add");
        subtractButton = new JButton("Subtract");
        multiplyButton = new JButton("Multiply");
        divideButton = new JButton("Divide");
        clearButton = new JButton("Clear");

        // Style the buttons
        styleButton(addButton);
        styleButton(subtractButton);
        styleButton(multiplyButton);
        styleButton(divideButton);
        styleButton(clearButton);

        // Add action listeners to buttons
        addButton.addActionListener(this);
        subtractButton.addActionListener(this);
        multiplyButton.addActionListener(this);
        divideButton.addActionListener(this);
        clearButton.addActionListener(this);

        // Add components to the main panel
        mainPanel.add(label1);
        mainPanel.add(input1);
        mainPanel.add(label2);
        mainPanel.add(input2);
        mainPanel.add(labelResult);
        mainPanel.add(result);
        mainPanel.add(addButton);
        mainPanel.add(subtractButton);
        mainPanel.add(multiplyButton);
        mainPanel.add(divideButton);
        mainPanel.add(clearButton);

        add(mainPanel, BorderLayout.CENTER);

        setVisible(true); // Make the frame visible
    }

    // Method to style buttons
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(70, 70, 70));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(80, 80, 80), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Action listener for button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Clear button logic
            if (e.getSource() == clearButton) {
                input1.setText("");
                input2.setText("");
                result.setText("");
                return;
            }

            // Parse numbers from the input fields
            double num1 = Double.parseDouble(input1.getText());
            double num2 = Double.parseDouble(input2.getText());
            double res = 0;

            // Determine which button was clicked
            if (e.getSource() == addButton) {
                res = num1 + num2;
            } else if (e.getSource() == subtractButton) {
                res = num1 - num2;
            } else if (e.getSource() == multiplyButton) {
                res = num1 * num2;
            } else if (e.getSource() == divideButton) {
                if (num2 != 0) {
                    res = num1 / num2;
                } else {
                    JOptionPane.showMessageDialog(this, "Error: Division by zero is not allowed.");
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
        new AttractiveCalculator();
    }
}
