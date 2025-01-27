package practice_projects.languages.java.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class FullFledgedCalculator extends JFrame implements ActionListener {

    private JTextField displayField; // Field for displaying input/output
    private JTextArea historyArea;  // Text area to store history
    private StringBuilder currentExpression; // Holds the current expression
    private boolean errorState = false; // Flag to handle errors

    // Constructor
    public FullFledgedCalculator() {
        // Initialize the JFrame
        setTitle("Full-Fledged Calculator");
        setSize(500, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        // Header panel for display
        JPanel headerPanel = new JPanel(new BorderLayout());
        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 24));
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setEditable(false);
        headerPanel.add(displayField, BorderLayout.CENTER);
        add(headerPanel, BorderLayout.NORTH);

        // Main panel for buttons
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        buttonPanel.setBackground(new Color(240, 240, 240));

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "C", "CE", "(", ")"
        };

        for (String text : buttons) {
            JButton button = new JButton(text);
            styleButton(button);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        // History panel
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setPreferredSize(new Dimension(500, 150));
        historyPanel.setBorder(BorderFactory.createTitledBorder("History"));
        historyArea = new JTextArea();
        historyArea.setFont(new Font("Arial", Font.PLAIN, 16));
        historyArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(historyArea);
        historyPanel.add(scrollPane, BorderLayout.CENTER);
        add(historyPanel, BorderLayout.SOUTH);

        currentExpression = new StringBuilder();
        setVisible(true); // Display GUI
    }

    // Method to style buttons
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(76, 175, 80));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(60, 140, 60), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    // Action listener for button clicks
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Clear the display
        if (command.equals("C")) {
            currentExpression.setLength(0);
            displayField.setText("");
            errorState = false;
        } 
        // Clear everything, including history
        else if (command.equals("CE")) {
            currentExpression.setLength(0);
            displayField.setText("");
            historyArea.setText("");
            errorState = false;
        } 
        // Perform the calculation
        else if (command.equals("=")) {
            if (!errorState) {
                try {
                    ScriptEngineManager manager = new ScriptEngineManager();
                    ScriptEngine engine = manager.getEngineByName("JavaScript");
                    Object result = engine.eval(currentExpression.toString());
                    String resultStr = String.format("%.2f", Double.parseDouble(result.toString()));

                    // Update history
                    historyArea.append(currentExpression + " = " + resultStr + "\n");
                    currentExpression.setLength(0);
                    currentExpression.append(resultStr);
                    displayField.setText(resultStr);
                } catch (ScriptException | NumberFormatException ex) {
                    displayField.setText("Error");
                    errorState = true;
                }
            }
        } 
        // Handle other buttons
        else {
            if (errorState) {
                currentExpression.setLength(0);
                errorState = false;
            }
            currentExpression.append(command);
            displayField.setText(currentExpression.toString());
        }
    }

    // Main method
    public static void main(String[] args) {
        new FullFledgedCalculator();
    }
}

