package practice_projects.languages.java.intermediate;

import java.util.Scanner;

class ComplexNumber {
    double real, imaginary;
    
    public ComplexNumber(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    public ComplexNumber add (ComplexNumber other) {
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
        return real + " + " + imaginary + "i";
    }
}

public class ComplexCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter real part of first complex number: ");
        double real1 = scanner.nextDouble();
        System.out.print("Enter imaginary part of first complex number: ");
        double imaginary1 = scanner.nextDouble();

        System.out.print("Enter real part of second complex number: ");
        double real2 = scanner.nextDouble();
        System.out.print("Enter imaginary part of second complex number: ");
        double imaginary2 = scanner.nextDouble();

        ComplexNumber c1 = new ComplexNumber(real1, imaginary1);
        ComplexNumber c2 = new ComplexNumber(real2, imaginary2);

        System.out.println("Choose operation: ");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Modulus of first number");
        System.out.println("6. Modulus of second number");
        
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                System.out.println("Result: " + c1.add(c2));
                break;
            case 2:
                System.out.println("Result: " + c1.subtract(c2));
                break;
            case 3:
                System.out.println("Result: " + c1.multiply(c2));
                break;
            case 4:
                System.out.println("Result: " + c1.divide(c2));
                break;
            case 5:
                System.out.println("Modulus of first number: " + c1.modulus());
                break;
            case 6:
                System.out.println("Modulus of second number: " + c2.modulus());
                break;
            default:
                System.out.println("Invalid choice");
        }

        scanner.close();
    }
}