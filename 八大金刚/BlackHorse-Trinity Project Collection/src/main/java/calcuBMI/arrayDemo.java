package calcuBMI;

public class arrayDemo {
    public static void main (String[] arg){
        int maxNumber = maxNumber();
        System.out.println(maxNumber);

    }

    public static void defineArray() {
        Integer[] arr = {1,2,3};
        String[] arr2 = {"1"};

        Integer[] arr3 = new Integer[8];

    }

    public static int maxNumber() {
        Integer[] arr = {12-1,-1,2,3,-6,9,7,-3,1,10};
        int maxNumber=0;
        int minNumber = 0;
        for (int i = 0; i < arr.length;i++) {
//            if (arr[i] > maxNumber ){
//                maxNumber = arr[i];
//            }
            int data = arr[i];
            maxNumber = data > maxNumber ? data : maxNumber;
            minNumber = data < minNumber ? data : minNumber;
        }
        return minNumber;
    }

}
