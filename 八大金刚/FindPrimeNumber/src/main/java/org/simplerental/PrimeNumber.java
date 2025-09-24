package org.simplerental;


import javax.print.attribute.standard.NumberUpSupported;
import java.util.ArrayList;
import java.util.Scanner;

public class PrimeNumber {
    public static void main(String[] args) {
        System.out.println(findPrimeNumbers(200));

    }

    public static PrimNumberCharacter findPrimeNumbers(Integer bound) {
        PrimNumberCharacter result = new PrimNumberCharacter();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the Starter Number: ");
        int sum = 0;
        ArrayList<Integer> show = new ArrayList<>();
        if (scanner.hasNextInt()){
            int starter = scanner.nextInt();
            for (; starter < bound; starter++) {
                boolean isPrime = true;
                if (starter % 2 != 0){
                    for (int i = 2; i*i <= starter ; i++) {
                        if(starter % i == 0){
                            isPrime = false;
                            break;
                        }
                    }
                    if(isPrime){
                        sum += 1;
                        show.add(starter);
                    }
                }
            }
            result.setNumbers(sum);
            result.setShow(show);
        } else {
            System.out.println("Please, Input Integer. ");
        }
// 101, 103, 107, 109, 113, 127,
// 131, 137, 139, 149, 151, 157,
// 163, 167, 173, 179, 181, 191,
// 193, 197, 199.
        return result;
    }

}

