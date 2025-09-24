package calcuBMI;

import java.util.Scanner;
public class CalculateBMI {
    public static void main (String[] arg) {
        calculateBMI();
    }
    public static void calculateBMI() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input Your Name: ");
        String username = scanner.next();
        System.out.println("Input Your Sex: ");
        String sex =  scanner.next();
        System.out.println("Input Your Weight: ");


        Integer weight = Integer.valueOf(scanner.next());
        System.out.println("Input Your Height(cm): ");
        //try.....



        Double high = Double.valueOf(scanner.next());
        high = high /100;

        System.out.println(high);

        Double BMI = weight / (high * high);

        System.out.println(BMI);

        System.out.println(username + "'s BMI is" + " "+ (weight / high / high));
        }
    }

