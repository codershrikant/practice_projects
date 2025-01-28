package practice_projects.languages.java.advanced;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @Override
    public String toString() {
        return String.format("%.2f + %.2fi", real, imaginary);
    }

    public static ComplexNumber parseComplex(String input) {
        // Regex to extract real and imaginary parts from input like "2 + 3i"
        Pattern pattern = Pattern.compile("([-+]?\\d*\\.?\\d*)?\\s*([-+]\\s*\\d*\\.?\\d*)?i?");
        Matcher matcher = pattern.matcher(input.replaceAll("\\s", "")); // Remove spaces

        double real = 0, imaginary = 0;
        if (matcher.find()) {
            String realPart = matcher.group(1);
            String imagPart = matcher.group(2);

            if (realPart != null && !realPart.isEmpty()) real = Double.parseDouble(realPart);
            if (imagPart != null && !imagPart.isEmpty()) imaginary = Double.parseDouble(imagPart.replace(" ", ""));
        }
        return new ComplexNumber(real, imaginary);
    }
}

public class ComplexCalculatorSwing_v2 {
    private JFrame frame;
    private JTextField inputField;
    private JLabel resultLabel;

    public ComplexCalculatorSwing_v2() {
        frame = new JFrame("Complex Number Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 200);
        frame.setLayout(new GridLayout(3, 1, 10, 10));

        // User input field
        inputField = new JTextField();
        frame.add(new JLabel("Enter Complex Expression:"));
        frame.add(inputField);

        // Calculate button
        JButton calculateButton = new JButton("Calculate");
        frame.add(calculateButton);

        // Result label
        resultLabel = new JLabel("Result: ");
        frame.add(resultLabel);

        // Button Action
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
            String input = inputField.getText();
            String[] parts = input.split(" ");

            if (parts.length < 3) {
                resultLabel.setText("Invalid Input Format!");
                return;
            }

            ComplexNumber num1 = ComplexNumber.parseComplex(parts[0] + " " + parts[1]);
            String operator = parts[2];
            ComplexNumber num2 = ComplexNumber.parseComplex(parts[3] + " " + parts[4]);

            ComplexNumber result = null;

            switch (operator) {
                case "+":
                    result = num1.add(num2);
                    break;
                case "-":
                    result = num1.subtract(num2);
                    break;
                case "*":
                    result = num1.multiply(num2);
                    break;
                case "/":
                    result = num1.divide(num2);
                    break;
                default:
                    resultLabel.setText("Invalid Operator!");
                    return;
            }

            resultLabel.setText("Result: " + result);
        } catch (Exception ex) {
            resultLabel.setText("Error: Invalid Input!");
        }
    }

    public static void main(String[] args) {
        new ComplexCalculatorSwing_v2();
    }
}
