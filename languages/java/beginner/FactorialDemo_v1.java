package practice_projects.languages.java.beginner;

import java.util.Scanner;

public class FactorialDemo_v1 {
    
    static int factorial(int num) {
        int res = 1, index;
        for (index = 2; index <= num; index++) {
            res *= index;
            System.out.println("index " + index + " res " + res);            
        }
        return res;
    }

    static int factorialRecursive(int num) {
        if (num == 0) {
            return 1;            
        }
        return num * factorialRecursive(num - 1);        
    }

    static int factorialTernary(int num) {
        return (num == 1 || num == 0) ? 1 : num *factorialTernary(num - 1);
    }
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the number");
        int num = scanner.nextInt();
        System.out.println("Factorial of " + num + " is " + factorial(num));
        System.out.println("Factorial of " + num + " is " + factorialRecursive(num));
        System.out.println("Factorial of " + num + " is " + factorialTernary(num));        
    }
}
