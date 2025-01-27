package practice_projects.languages.java.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ExpressionCalculator extends JFrame implements ActionListener {

    // Components
    private JTextField inputField;
    private JButton evaluateButton, clearButton;
    private JLabel resultLabel;

    // Constructor
    public ExpressionCalculator() {
        // Set up JFrame
        setTitle("Expression Calculator");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        // Header
        JPanel headerPanel = new JPanel();
        JLabel headerLabel = new JLabel("Enter a Mathematical Expression");
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerPanel.setBackground(new Color(33, 150, 243));
        headerLabel.setForeground(Color.WHITE);
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Main Panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setBackground(new Color(240, 240, 240));

        inputField = new JTextField();
        inputField.setFont(new Font("Arial", Font.PLAIN, 16));

        // Buttons
        evaluateButton = new JButton("Evaluate");
        clearButton = new JButton("Clear");

        styleButton(evaluateButton);
        styleButton(clearButton);

        evaluateButton.addActionListener(this);
        clearButton.addActionListener(this);

        // Result Label
        resultLabel = new JLabel("Result: ");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));

        // Add components to the main panel
        mainPanel.add(inputField);
        mainPanel.add(evaluateButton);
        mainPanel.add(resultLabel);

        add(mainPanel, BorderLayout.CENTER);

        // Footer Panel for Clear Button
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(240, 240, 240));
        footerPanel.add(clearButton);
        add(footerPanel, BorderLayout.SOUTH);

        setVisible(true); // Display GUI
    }

    // Method to style buttons
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(new Color(76, 175, 80));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(60, 140, 60), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Action Listener for buttons
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == evaluateButton) {
            String expression = inputField.getText();
            try {
                // Use ScriptEngine to evaluate the mathematical expression
                ScriptEngineManager manager = new ScriptEngineManager();
                ScriptEngine engine = manager.getEngineByName("JavaScript");
                Object result = engine.eval(expression);

                // Display the result
                resultLabel.setText("Result: " + result);
            } catch (ScriptException ex) {
                JOptionPane.showMessageDialog(this, "Invalid Expression!", "Error", JOptionPane.ERROR_MESSAGE);
                resultLabel.setText("Result: Error");
            }
        } else if (e.getSource() == clearButton) {
            // Clear input and result
            inputField.setText("");
            resultLabel.setText("Result: ");
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        new ExpressionCalculator();
    }
}
