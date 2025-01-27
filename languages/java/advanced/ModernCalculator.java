package practice_projects.languages.java.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class ModernCalculator extends JFrame implements ActionListener {

    private JTextField displayField;  // Display field for input/output
    private JTextArea historyArea;   // Area for history of calculations
    private StringBuilder currentExpression; // Holds the current expression
    private boolean errorState = false;      // Flag to handle errors

    // Constructor
    public ModernCalculator() {
        // Set up JFrame
        setTitle("Modern Calculator");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setResizable(false);

        // Top Display Panel
        JPanel displayPanel = new JPanel(new BorderLayout());
        displayPanel.setBackground(new Color(50, 50, 50));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        displayField = new JTextField();
        displayField.setFont(new Font("Arial", Font.BOLD, 28));
        displayField.setHorizontalAlignment(SwingConstants.RIGHT);
        displayField.setEditable(false);
        displayField.setBackground(new Color(30, 30, 30));
        displayField.setForeground(Color.WHITE);
        displayPanel.add(displayField, BorderLayout.CENTER);
        add(displayPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(5, 4, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        buttonPanel.setBackground(new Color(40, 40, 40));

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

        // History Panel
        JPanel historyPanel = new JPanel(new BorderLayout());
        historyPanel.setPreferredSize(new Dimension(400, 150));
        historyPanel.setBackground(new Color(30, 30, 30));
        historyPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.LIGHT_GRAY),
                "History",
                0,
                0,
                new Font("Arial", Font.BOLD, 16),
                Color.LIGHT_GRAY
        ));

        historyArea = new JTextArea();
        historyArea.setFont(new Font("Arial", Font.PLAIN, 16));
        historyArea.setEditable(false);
        historyArea.setBackground(new Color(20, 20, 20));
        historyArea.setForeground(Color.LIGHT_GRAY);

        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setBorder(null);
        historyPanel.add(scrollPane, BorderLayout.CENTER);
        add(historyPanel, BorderLayout.SOUTH);

        // Initialize the current expression
        currentExpression = new StringBuilder();

        setVisible(true); // Show the GUI
    }

    // Method to style buttons
    private void styleButton(JButton button) {
        button.setFont(new Font("Arial", Font.BOLD, 18));
        button.setBackground(new Color(76, 175, 80));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(60, 140, 60), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));

        if (button.getText().equals("=")) {
            button.setBackground(new Color(255, 87, 34));
        } else if (button.getText().equals("C") || button.getText().equals("CE")) {
            button.setBackground(new Color(244, 67, 54));
        }
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
        new ModernCalculator();
    }
}
