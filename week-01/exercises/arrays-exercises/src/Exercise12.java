import java.util.Arrays;
import java.util.Random;

public class Exercise12 {

    public static void main(String[] args) {
        int[] values = makeRandomArray();
        int posCount = 0;
        int nonPosCount = 0;

        // 1. Count the number of positive and non-positive elements in `values`.

        for (int i = 0; i < values.length; i++) {
            if (values[i] > 0) {
                posCount++;
            } else {
                nonPosCount++;
            }
        }

        // 2. Create two new int[]s, one for positive elements and one for non-positive.
        int[] pos = new int[posCount];
        int[] nonpos = new int[nonPosCount];


        // (We count first to determine the capacity to allocate.)
        // 3. Loop through `values` a second time. If an element is positive, add it to the positive array.
        // If it is non-positive, add it to the non-positive array.
        int posArrayIndex = 0;
        int nonPosArrayIndex = 0;

        for (int i = 0; i < values.length; i++) {
            if (values[i] > 0) {
                pos[posArrayIndex] = values[i];
                posArrayIndex++;
            } else {
                nonpos[nonPosArrayIndex] = values[i];
                nonPosArrayIndex++;
            }
        }


        // 4. Confirm that your secondary arrays are properly populated either by debugging or printing their elements.
        System.out.println("number of positive numbers in array: " + posCount);
        System.out.println("positive array: " + Arrays.toString(pos));
        System.out.println("number of non positive numbers in array: " + nonPosCount);
        System.out.println("nonpositive array: " + Arrays.toString(nonpos));



    }

    public static int[] makeRandomArray() {
        Random random = new Random();
        int[] result = new int[random.nextInt(100) + 50];
        for (int i = 0; i < result.length; i++) {
            result[i] = random.nextInt(1000) - 500;
        }
        return result;
    }
}
