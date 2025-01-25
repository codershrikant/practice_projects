package practice_projects.languages.java.intermediate;

import java.util.Scanner;

class AdvancedCalculator {

    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Enter the first number:");
            double num1 = scanner.nextDouble();

            System.out.println("Enter the second number:");
            double num2 = scanner.nextDouble();

            System.out.println("Choose an operation:");
            System.out.println("1. Addition (+)");
            System.out.println("2. Subtraction (-)");
            System.out.println("3. Multiplication (*)");
            System.out.println("4. Division (/)");
            System.out.println("5. Modulus (%)");
            int choice = scanner.nextInt();

            double result = 0;
            boolean validOperation = true;

            switch(choice) {
                case 1: 
                    result = num1 + num2;
                    System.out.println("The result of addition is: " + result);
                    break;
                case 2: 
                    result = num1 - num2;
                    System.out.println("The result of Subtraction is: " + result);
                    break;
                case 3: 
                    result = num1 * num2;
                    System.out.println("The result of Multiplication is: " + result);
                    break;
                case 4:
                    if (num2 != 0) {
                        result = num1 / num2;
                        double rounded_result = Math.round(result * 100.0) / 100.0;
                        System.out.println("The result of Division is: " + rounded_result);
                        break;
                    } else {
                        System.out.println("Error: Division by zero is not allowed.");
                    }
                    break;
                case 5:
                    if (num2 != 0) {
                        result = num1 % num2;
                        System.out.println("The result of modulus is: " + result);
                        break;
                    } else {
                        System.out.println("Error: Modulus by zero is not allowed.");
                    }
                    break;
                default:
                    validOperation = false;
                    System.out.println("Invalid choice. Please select a valid operation (1-5).");                
            }

            if (validOperation) {
                System.out.println("Operation completed successfully!");
            }
        } catch (Exception e) {
            System.out.println("Error: Invalid input. Please enter numeric values for numbers and a valid operation.");
        } finally {
            scanner.close();
        }

    }
    
}