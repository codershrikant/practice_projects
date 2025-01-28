package practice_projects.languages.java.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class InventoryManager_v1 extends JFrame {

    private JTextField nameField, quantityField;
    private JButton addButton, viewButton;
    private JTextArea displayArea;

    public InventoryManager_v1() {
        setTitle("Inventory Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(2, 2));
        inputPanel.add(new JLabel("Product Name:"));
        nameField = new JTextField(20);
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Quantity:"));
        quantityField = new JTextField(10);
        inputPanel.add(quantityField);
        add(inputPanel, BorderLayout.NORTH);

        // Button Panel
        JPanel buttonPanel = new JPanel();
        addButton = new JButton("Add Product");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addProduct();
            }
        });
        buttonPanel.add(addButton);

        viewButton = new JButton("View Inventory");
        viewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewInventory();
            }
        });
        buttonPanel.add(viewButton);
        add(buttonPanel, BorderLayout.CENTER);

        // Display Panel
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);
        add(scrollPane, BorderLayout.SOUTH);

        pack();
        setVisible(true);
    }

    private void addProduct() {
        String name = nameField.getText();
        int quantity;
        try {
            quantity = Integer.parseInt(quantityField.getText());
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Invalid Quantity", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "your_username", "your_password");
             PreparedStatement statement = connection.prepareStatement("INSERT INTO products (name, quantity) VALUES (?, ?)")) {
            statement.setString(1, name);
            statement.setInt(2, quantity);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(this, "Product added successfully!");
            clearFields();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding product: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void viewInventory() {
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventory", "your_username", "your_password");
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM products")) {
            displayArea.setText("");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                int quantity = resultSet.getInt("quantity");
                displayArea.append("ID: " + id + ", Name: " + name + ", Quantity: " + quantity + "\n");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error viewing inventory: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        nameField.setText("");
        quantityField.setText("");
    }

    public static void main(String[] args) {
        new InventoryManager_v1();
    }
}