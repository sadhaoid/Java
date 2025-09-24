package org.simplerental;

import java.util.Random;
import java.util.Scanner;

public class VerifyCode {
    public static final  String wholeCode = "ABCDEFGHIJKLMNOPQRSTUVWSVZabcdefghijklmnopqrstuvwxyz0123456789";

    public static void main(String[] args) {
        String result = getGenerateCode();
        System.out.println(result);
//      System.out.println(numbers);

    }

    public static String getGenerateCode() {
        Scanner scanner = new Scanner(System.in);
        String result;

        while (true) {
            System.out.println("Input your VerifyCode Numbers: ");
            if (scanner.hasNextInt()) {
                Integer number = scanner.nextInt();
                if (number > 1) {
                    result = generateCode(number);
                    break;
                } else {
                    System.out.println("Please, re-input a int number greater than 1.");
                }
            } else {
                System.out.println("Invalid input. Please enter a int number greater than 1 .");
                scanner.next();
            }
        }
        return result;
    }

    public static String generateCode(int number) {


        String verifyCode = "";

        for (int i = 0; i < number; i++) {
            Random random = new Random();
            Integer index = random.nextInt(62);
            verifyCode += wholeCode.charAt(index);
        }
        return verifyCode;
    }
}