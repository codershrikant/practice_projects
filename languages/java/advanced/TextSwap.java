package practice_projects.languages.java.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TextSwap extends JFrame implements ActionListener {
    
    private JTextField textField1, textField2;
    private JButton swapButton, clearButton;

    public TextSwap() {
        setTitle("Swap Text App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);        
        setLayout(new BorderLayout());
        setResizable(false);

        // Header Panel
        JPanel headerPanel = new JPanel();
        headerPanel.setBackground(new Color(33, 150, 243));
        JLabel headerLabel = new JLabel("Swap Application of Text");
        headerLabel.setBackground(Color.WHITE);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(headerLabel);
        add(headerPanel, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(3, 1, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        inputPanel.setBackground(new Color(240, 240, 240));

        textField1 = createStyledTextField();
        textField2 = createStyledTextField();

        inputPanel.add(createLabeledPanel("Input 1: ", textField1));
        inputPanel.add(createLabeledPanel("Input 2: ", textField2));

        add(inputPanel, BorderLayout.CENTER);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(240, 240, 240));
        
        swapButton = createStyledButton("Swap");
        clearButton = createStyledButton("Clear");

        swapButton.addActionListener(this);
        clearButton.addActionListener(this);

        buttonPanel.add(swapButton);
        buttonPanel.add(clearButton);

        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);

    }

    private JPanel createLabeledPanel(String labelText, JTextField textField) {
        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(label, BorderLayout.WEST);
        panel.add(textField, BorderLayout.CENTER);
        panel.setBackground(new Color(240, 240, 240));
        return panel;
    }

    private JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200), 2));
        textField.setPreferredSize(new Dimension(200, 30));
        return textField;
    }

    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, size:14));
        button.setBackground(new Color(76, 175, 80));
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createLineBorder(new Color(60, 140, 60), 2));
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == swapButton) {
            String temp = textField1.getText();
            textField1.setText(textField2.getText());
            textField2.setText(temp);
        } else if (e.getSource() == clearButton) {
            textField1.setText("");
            textField2.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(TwoTextSwapApp::new);
    }
}
