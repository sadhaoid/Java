package calcuBMI;

import lombok.Getter;
import lombok.Setter;

import java.util.Scanner;
public class SimpleCalculator {
    public static void main (String[] arg) {
        SimpleCalculator instance = new SimpleCalculator();
        InputObj instance1 = instance.userInput();
        if (instance1 != null){
            int result = logicCalculator(instance1);
            System.out.println("Answer is: " + result);
        }



    }

    public static int logicCalculator(InputObj inputObj) {
        int result = inputObj.getSymbol().equals("+")  ? inputObj.getNumber1() + inputObj.getNumber2() : inputObj.getSymbol().equals("-") ?
                inputObj.getNumber1() - inputObj.getNumber2() : inputObj.getSymbol().equals("*") ? inputObj.getNumber1() * inputObj.getNumber2() :inputObj.getSymbol().equals("/") ?
                inputObj.getNumber1() / inputObj.getNumber2() : 0;



//        int result2 = inputObj.symbol.equals("+")  ? inputObj.number1 + inputObj.number2 : inputObj.symbol.equals("-") ?
//                inputObj.number1 - inputObj.number2 : inputObj.symbol.equals("*") ? inputObj.number1 * inputObj.number2 :inputObj.symbol.equals("/") ?
//                inputObj.number1 / inputObj.number2 : 0;


//        result = Symbol == "-" ? Number1 - Number2 : null;
//        result = Symbol == "*" ? Number1 * Number2 : null;
//        result = Symbol == "/" ? Number1 / Number2 : null;
        return result;

    }

    public InputObj userInput(){
        Scanner sc = new Scanner(System.in);

        System.out.println("Input Number1: ");
        int Number1 = Integer.valueOf(sc.next());


        System.out.println("Input Number2: ");
        int Number2 = Integer.valueOf(sc.next());


        System.out.println("Input Symbol: ");
        String Symbol = sc.next();


//        InputObj inputObj = new InputObj();
//        inputObj.number1 = Number1;
//        inputObj.number2 = Number2;
//        inputObj.symbol = Symbol;


        InputObj inputObj = new InputObj(Number1, Number2);
//
//        inputObj.setNumber1(Number1);
//        inputObj.setNumber2(Number2);
//        inputObj.setSymbol(Symbol);
//
//        System.out.println(inputObj);


        return inputObj;
    }

}


class User{
    public static int number;

    public User(){
        this.number++;
    }
}




class InputObj {

    private int number1;
    private int number2;
    private String symbol;
    static int number3;

    public static void print(){
        int a;
        System.out.println(number3);
    }

    public InputObj() {
        this(0, 0);
        System.out.println(number1);
    }

    public InputObj(int a, int b) {
        this(a, b, "");
    }

    public InputObj(int number1, int b, String c) {
        this.number1 = number1;
        number2 = b;
        symbol = c;
    }



    public int getNumber1() {
        return number1;
    }

    public void setNumber1(int number1) {
        this.number1 = number1;
    }

    public int getNumber2() {
        return number2;
    }

    public void setNumber2(int number2) {
        this.number2 = number2;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return "InputObj{" +
                "number1=" + number1 +
                ", number2=" + number2 +
                ", symbol='" + symbol + '\'' +
                '}';
    }




}
