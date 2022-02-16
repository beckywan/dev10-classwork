import java.util.Arrays;
import java.util.Random;

public class Exercise15 {


    public static void main(String[] args) {
        int[] one = makeRandomArray();
        int[] two = makeRandomArray();

        // 1. Create a new int[] with room enough for all elements in `one` and `two`.
        System.out.println("array one length: " + one.length);
        System.out.println("array one length: " + two.length);

        int sum = one.length + two.length;
        int [] both = new int[sum];
        System.out.println("array both length: " + both.length);

        // 2. Copy elements from `one` into the beginning of the array.
        for (int i = 0; i < one.length; i++) {
            both[i] = one[i];
        }
        System.out.println("array one: " + Arrays.toString(one));
        System.out.println("array both: " + Arrays.toString(both));

        // 3. Copy elements from `two` at the end of the array.

        for (int i2 = 0; i2 < two.length; i2++) {
            both[one.length + i2] = two[i2];
        }
        System.out.println("array two: " + Arrays.toString(two));
        System.out.println("array both: " + Arrays.toString(both));

        // 4. Print the results to confirm that it worked.
        System.out.println("array one: " + Arrays.toString(one));
        System.out.println("array two: " + Arrays.toString(two));
        System.out.println("array both: " + Arrays.toString(both));


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
