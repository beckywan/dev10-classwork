import java.util.Arrays;
import java.util.Random;

public class Exercise11 {

    public static void main(String[] args) {
        int[] values = makeRandomArray();

        // 1. Count the number of positive elements in `values`.
        int count = 0;

        for (int i = 0; i < values.length; i++) {
            if (values[i] > 0) {
                count++;
            }
        }

        // 2. Create a new int[] to hold the positive elements.

        int[] pos = new int[count];

        // (We must count first to know the capacity to allocate.)
        // 3. Loop through `values` a second time. Add positive elements to the new array.
        int arrayIndex = 0;

        for (int i = 0; i < values.length; i++) {
            if (values[i] > 0) {
                pos[arrayIndex] = values[i];
                arrayIndex++;
            }
        }
        // 4. Confirm the positive array is properly populated either by debugging or printing its elements.
        System.out.println("number of positive numbers in array: " + count);
        System.out.println("positive array: " + Arrays.toString(pos));
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
