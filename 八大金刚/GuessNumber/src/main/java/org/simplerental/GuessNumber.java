package org.simplerental;

import java.util.Random;
import java.util.Scanner;

public class GuessNumber{


        public static void main(String[] args) {
                letsPlay();

        }

        public static void letsPlay() {
                Scanner scanner = new Scanner(System.in);

                //int goal = (int) (Math.random() ) + 1;
                Random r = new Random();
                Integer goal = r.nextInt(100) + 1;
                System.out.println(goal);
                Boolean condition = Boolean.TRUE;
                System.out.println("I generate a number, lets play!");
                System.out.println("Input your guess number:");
                //Double guess = Double.valueOf(scanner.next());
                while (condition){
                        Double guess = Double.valueOf(scanner.next());
                        if (guess > goal){
                                System.out.println("Ur greater, please re-input");
                        }
                        else if (guess < goal){
                                System.out.println("Ur smaller, please re-input");
                        }
                        else {
                                System.out.println("Congradulations!");
                                break;
                        }
                }






//                while (guess > goal) {
//                        System.out.println("Ur greater, please re-input");
//                        guess = Double.valueOf(scanner.next());
//
//                }
//                while (guess < goal) {System.out.println("Ur smaller, please re-input");
//                        guess = Double.valueOf(scanner.next());
//
//
//                }
//                while (guess == goal){
//                        System.out.println("Congratulations!");
//                        break;
//                }

        }
}