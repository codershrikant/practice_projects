package practice_projects.languages.java.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class InventoryApp_v1 {

    // Database credentials
    private static final String DB_URL = "jdbc:mysql://localhost:3306/inventory_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "password";

    public static void main(String[] args) {
        // Set up the frame
        JFrame frame = new JFrame("Inventory Management System");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(5, 2, 10, 10));

        // Create input fields and labels
        JLabel nameLabel = new JLabel("Item Name:");
        JTextField nameField = new JTextField();

        JLabel quantityLabel = new JLabel("Quantity:");
        JTextField quantityField = new JTextField();

        JLabel priceLabel = new JLabel("Price:");
        JTextField priceField = new JTextField();

        JButton saveButton = new JButton("Save to Database");
        JLabel statusLabel = new JLabel();

        // Add components to the frame
        frame.add(nameLabel);
        frame.add(nameField);

        frame.add(quantityLabel);
        frame.add(quantityField);

        frame.add(priceLabel);
        frame.add(priceField);

        frame.add(saveButton);
        frame.add(statusLabel);

        // Save button action
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String itemName = nameField.getText().trim();
                String quantityText = quantityField.getText().trim();
                String priceText = priceField.getText().trim();

                if (itemName.isEmpty() || quantityText.isEmpty() || priceText.isEmpty()) {
                    statusLabel.setText("Please fill in all fields.");
                    statusLabel.setForeground(Color.RED);
                    return;
                }

                try {
                    int quantity = Integer.parseInt(quantityText);
                    double price = Double.parseDouble(priceText);

                    // Save to database
                    if (saveToDatabase(itemName, quantity, price)) {
                        statusLabel.setText("Item saved successfully!");
                        statusLabel.setForeground(Color.GREEN);

                        // Clear fields
                        nameField.setText("");
                        quantityField.setText("");
                        priceField.setText("");
                    } else {
                        statusLabel.setText("Failed to save item.");
                        statusLabel.setForeground(Color.RED);
                    }

                } catch (NumberFormatException ex) {
                    statusLabel.setText("Invalid quantity or price.");
                    statusLabel.setForeground(Color.RED);
                }
            }
        });

        // Set frame visibility
        frame.setVisible(true);
    }

    private static boolean saveToDatabase(String itemName, int quantity, double price) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // Connect to the database
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Create SQL query
            String sql = "INSERT INTO inventory (item_name, quantity, price) VALUES (?, ?, ?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, itemName);
            statement.setInt(2, quantity);
            statement.setDouble(3, price);

            // Execute update
            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        } finally {
            // Close resources
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
