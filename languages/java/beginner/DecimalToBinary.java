package practice_projects.languages.java.beginner;

import java.util.Scanner;

public class DecimalToBinary {
    static String decimalToBinary(int num) {
        int[] binaryNum = new int[1000];
        int index = 0;
        String result = "";
    
        while (num > 0) {
            binaryNum[index] = num % 2;
            num = num / 2;
            index++;
        }

        for (int loopIndex = index - 1; loopIndex>=0; loopIndex--) {
            // System.out.println(binaryNum[loopIndex]);
            result += binaryNum[loopIndex]; 
        }
        return result;
    }

    public static void decimalToBinaryUsingBitwiseOperator(int num) {
        for (int index = 31; index >= 0; index--) {
            int mostSignificantBit = num >> index;
            if ((mostSignificantBit & 1) > 0) {
                System.out.print("1");
            } else 
                System.out.print("0");
        }
    }

    public static int deicmalToBinaryUsingMathPow(int num) {
        int binaryNum = 0;
        int count = 0;

        while (num != 0) {
            int remainder = num % 2;
            double exponentResult = Math.pow(10, count);
            binaryNum += remainder * exponentResult;
            // System.out.println("remainder " + remainder + " exponentResult " + exponentResult + " binaryNum " + binaryNum);
            num /= 2;

            count ++;
        }

        return binaryNum;
    }

    public static String decimalToBinaryFourthFunction (int num) {
        int remainder, quotient = num;
        String binaryNum = "";
        while (quotient > 0) {
            remainder = quotient % 2;
            binaryNum = Integer.toString(remainder) + binaryNum;
            quotient = quotient / 2;
        }

        return binaryNum;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the Decimal number");
        int num = scanner.nextInt();

        System.out.println("Decimal Number " + num);
        System.out.println("Binary Number " + decimalToBinary(num));

        System.out.println("Decimal to Binary using Bitwise Operator");
        decimalToBinaryUsingBitwiseOperator(num);

        System.out.println("Decimal to Binary using Math.pow \n" + deicmalToBinaryUsingMathPow(num));
        
        System.out.println("Decimal to Binary using another method \n" + decimalToBinaryFourthFunction(num));

        scanner.close();
    }


}
