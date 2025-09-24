package calcuBMI;

import java.lang.reflect.Array;
import java.util.Arrays;

public class pokerDemo {

    public static void main (String[] arg){

        changeTurn();
//        System.out.println(Arrays.toString(showPoker));
//        String[][] twodem = new String[5][5];

    }

    public static String[] makePoker() {
        int pokerIndex = 0;

        String[] poker = new String[12];

        String[] color = {"!","@","#","$"};
        String[] number = {"2","3","4"};



        for (int i = 0;i < number.length; i++){
            for (int j = 0; j < color.length;j++){
                poker[pokerIndex++] =  color[j] + number[i]  ;
            }
        }
        return poker;
    }

    public static void changeTurn(){
        String[] showPoker = makePoker();
        for (int i = 0; i< showPoker.length;i++){

            int index = (int) (Math.random() * showPoker.length);
            int index2 = (int) (Math.random() * showPoker.length);


            String temp = showPoker[index];

            showPoker[index] = showPoker[index2];
            showPoker[index2] = temp;
        }


        System.out.println(Arrays.toString(showPoker));

    }
}
