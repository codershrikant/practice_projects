package practice_projects.languages.java.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class NumberSystemCalculator_v4 extends JFrame {

    private JTextField inputField;
    private JTextField resultField;
    private JComboBox<String> fromComboBox;
    private JComboBox<String> toComboBox;
    private JTextArea historyArea;

    private static final Map<String, Integer> bases = new HashMap<>();

    static {
        bases.put("Decimal", 10);
        bases.put("Binary", 2);
        bases.put("Octal", 8);
        bases.put("Hexadecimal", 16);
    }

    public NumberSystemCalculator_v4() {
        setTitle("Number System Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        // Input Panel
        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.add(new JLabel("Enter Value:"));
        inputField = new JTextField(20);
        inputPanel.add(inputField);

        // Options Panel
        JPanel optionsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        String[] options = bases.keySet().toArray(new String[0]);
        fromComboBox = new JComboBox<>(options);
        optionsPanel.add(new JLabel("From:"));
        optionsPanel.add(fromComboBox);

        toComboBox = new JComboBox<>(options);
        optionsPanel.add(new JLabel("To:"));
        optionsPanel.add(toComboBox);

        // Combine input and options panels
        JPanel topPanel = new JPanel(new GridLayout(2, 1, 10, 10));
        topPanel.add(inputPanel);
        topPanel.add(optionsPanel);
        add(topPanel, BorderLayout.NORTH);

        // Result Panel
        JPanel resultPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        resultPanel.add(new JLabel("Result:"));
        resultField = new JTextField(30);
        resultField.setEditable(false);
        resultField.setFont(new Font("Arial", Font.BOLD, 14));
        resultPanel.add(resultField);
        add(resultPanel, BorderLayout.CENTER);

        // Convert and History Panel
        JPanel bottomPanel = new JPanel(new BorderLayout(10, 10));

        JButton convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convert();
            }
        });
        bottomPanel.add(convertButton, BorderLayout.WEST);

        historyArea = new JTextArea(8, 40);
        historyArea.setEditable(false);
        historyArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(historyArea);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Conversion History"));
        bottomPanel.add(scrollPane, BorderLayout.CENTER);

        add(bottomPanel, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private void convert() {
        String input = inputField.getText().trim();
        String fromBase = (String) fromComboBox.getSelectedItem();
        String toBase = (String) toComboBox.getSelectedItem();

        try {
            int inputBase = bases.get(fromBase);
            int outputBase = bases.get(toBase);
            int decimalValue = Integer.parseInt(input, inputBase);
            String result = Integer.toString(decimalValue, outputBase).toUpperCase();

            resultField.setText(result);
            historyArea.append(fromBase + " (" + input + ") to " + toBase + " = " + result + "\n");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid input! Please enter a valid number.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new NumberSystemCalculator_v4();
    }
}