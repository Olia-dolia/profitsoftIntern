package FirstModule.TaskOne;

import java.util.Arrays;

public class PositiveNumbers {
    public static int getRandomNumber() {
        return (int) ((Math.random() * (50 + 49)) - 49);
    }

    public static int[] arrMoreThanZero(int[] arr){
        return Arrays.stream(arr).filter(x -> x >= 0).toArray();
    }
    public static void main(String[] args) {
        int[] arrInput = new int[20];
        System.out.println("Input data: ");
        for (int i = 0; i < arrInput.length; i++) {
            arrInput[i] = getRandomNumber();
            System.out.print(arrInput[i] + " ");
        }
        System.out.println();
        System.out.println("Output data: ");
        System.out.println(Arrays.toString(arrMoreThanZero(arrInput)));
    }
}
