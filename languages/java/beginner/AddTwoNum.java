// This is a program that takes two decimal value, add them and print them.
package practice_projects.languages.java.beginner;

import java.util.Scanner;

public class AddTwoNum {
       public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Enter the first number");
            double num1 = scanner.nextDouble();

            System.out.println("Enter the second number");
            double num2 = scanner.nextDouble();

            double result = num1 + num2;
            double rounded_result = Math.round(result * 100.0) / 100.0;

            System.out.println("The result of adding " + num1
            + " and " + num2 + " is: " + rounded_result);

            System.out.println("The result in rounded form using String.format " + String.format("%.2f", result) );

            scanner.close();
       }
}
