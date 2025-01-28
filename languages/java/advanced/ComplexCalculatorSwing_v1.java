package practice_projects.languages.java.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ComplexNumber {
    double real, imaginary;

    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber add(ComplexNumber other) {
        return new ComplexNumber(this.real + other.real, this.imaginary + other.imaginary);
    }

    public ComplexNumber subtract(ComplexNumber other) {
        return new ComplexNumber(this.real - other.real, this.imaginary - other.imaginary);
    }

    public ComplexNumber multiply(ComplexNumber other) {
        double realPart = this.real * other.real - this.imaginary * other.imaginary;
        double imaginaryPart = this.real * other.imaginary + this.imaginary * other.real;
        return new ComplexNumber(realPart, imaginaryPart);
    }

    public ComplexNumber divide(ComplexNumber other) {
        double denominator = other.real * other.real + other.imaginary * other.imaginary;
        double realPart = (this.real * other.real + this.imaginary * other.imaginary) / denominator;
        double imaginaryPart = (this.imaginary * other.real - this.real * other.imaginary) / denominator;
        return new ComplexNumber(realPart, imaginaryPart);
    }

    public double modulus() {
        return Math.sqrt(real * real + imaginary * imaginary);
    }

    @Override
    public String toString() {
        return String.format("%.2f + %.2fi", real, imaginary);
    }
}

public class ComplexCalculatorSwing_v1 {
    private JFrame frame;
    private JTextField real1Field, imag1Field, real2Field, imag2Field;
    private JLabel resultLabel;
    private JComboBox<String> operationBox;

    public ComplexCalculatorSwing_v1() {
        frame = new JFrame("Complex Number Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(new GridLayout(6, 2, 10, 10));

        // Labels & Input Fields
        frame.add(new JLabel("Real Part 1:"));
        real1Field = new JTextField();
        frame.add(real1Field);

        frame.add(new JLabel("Imaginary Part 1:"));
        imag1Field = new JTextField();
        frame.add(imag1Field);

        frame.add(new JLabel("Real Part 2:"));
        real2Field = new JTextField();
        frame.add(real2Field);

        frame.add(new JLabel("Imaginary Part 2:"));
        imag2Field = new JTextField();
        frame.add(imag2Field);

        // Operation Selection
        frame.add(new JLabel("Select Operation:"));
        String[] operations = {"Addition", "Subtraction", "Multiplication", "Division", "Modulus (1st)", "Modulus (2nd)"};
        operationBox = new JComboBox<>(operations);
        frame.add(operationBox);

        // Button to Compute Result
        JButton calculateButton = new JButton("Calculate");
        frame.add(calculateButton);

        // Result Label
        resultLabel = new JLabel("Result: ");
        frame.add(resultLabel);

        // Action Listener for Button
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateResult();
            }
        });

        frame.setVisible(true);
    }

    private void calculateResult() {
        try {
            double real1 = Double.parseDouble(real1Field.getText());
            double imag1 = Double.parseDouble(imag1Field.getText());
            double real2 = Double.parseDouble(real2Field.getText());
            double imag2 = Double.parseDouble(imag2Field.getText());

            ComplexNumber c1 = new ComplexNumber(real1, imag1);
            ComplexNumber c2 = new ComplexNumber(real2, imag2);

            String selectedOperation = (String) operationBox.getSelectedItem();
            ComplexNumber result = null;
            double modulus = 0;

            switch (selectedOperation) {
                case "Addition":
                    result = c1.add(c2);
                    break;
                case "Subtraction":
                    result = c1.subtract(c2);
                    break;
                case "Multiplication":
                    result = c1.multiply(c2);
                    break;
                case "Division":
                    result = c1.divide(c2);
                    break;
                case "Modulus (1st)":
                    modulus = c1.modulus();
                    break;
                case "Modulus (2nd)":
                    modulus = c2.modulus();
                    break;
            }

            if (result != null) {
                resultLabel.setText("Result: " + result);
            } else {
                resultLabel.setText("Result: " + String.format("%.2f", modulus));
            }
        } catch (NumberFormatException ex) {
            resultLabel.setText("Invalid input!");
        }
    }

    public static void main(String[] args) {
        new ComplexCalculatorSwing_v1();
    }
}
