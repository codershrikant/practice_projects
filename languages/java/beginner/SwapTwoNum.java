package practice_projects.languages.java.beginner;

import java.util.*;
import java.util.Scanner;

public class SwapTwoNum {

    static void swapNumByThirdVar(double first, double second) {
        double temp = first;
        first = second;
        second = temp;
        System.out.println("Inside function swapNumByThirdVar: Numbers after swapping.");
        System.out.println("first " + first + " second " + second);
    }

    static void swapNumWithoutThirdVar(double [] numbers) {
        numbers[0] = numbers[0] - numbers[1];
        numbers[1] = numbers[1] + numbers[0];
        numbers[0] = numbers[1] - numbers[0];
        
        System.out.println("Inside function swapNumWithoutThirdVar: Numbers after swapping.");
        System.out.println(numbers[0] + " and " + numbers[1]);
    }

    static void swapByXOROperator(int first, int second) {
        first = first ^ second;
        second = first ^ second;
        first = first ^ second;

        System.out.println("Inside function swapByXOROperator: Numbers after swapping.");
        System.out.println("first " + first + " second " + second);
    }

    static void swapWithArithmetic(int num1, int num2) {
        num1 = (num1 + num2) - (num2 = num1);  

        System.out.println("Inside function swapWithArithmetic: Numbers after swapping.");
        System.out.println("first " + num1 + " second " + num2);

    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

            System.out.println("Enter the first number");
            double first = scanner.nextDouble();

            System.out.println("Enter the second number");
            double second = scanner.nextDouble();

            System.out.println("Numbers before swapping.");
            System.out.println("first " + first + " second " + second);

            swapNumByThirdVar(first, second);

            System.out.println("Numbers after swapping.");
            System.out.println("first " + first + " second " + second);

            double[] values = {10.34, 7.54};
            swapNumWithoutThirdVar(values);

            System.out.println("Value of first is " + values[0] + " and Value of second is " + values[1]);

            int num1 = 1, num2 =2;
            swapByXOROperator(num1, num2);
            System.out.println("Numbers after swapping.");
            System.out.println("Num1 " + num1 + " Num2 " + num2);

            int num3 = 3, num4 = 4;
            swapWithArithmetic(num3, num4);
            System.out.println("Numbers after swapping.");
            System.out.println("Num3 " + num3 + " Num4 " + num4);
    }
    
}