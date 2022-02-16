import java.util.Arrays;
import java.util.Random;

public class Exercise16 {

    public static void main(String[] args) {
        // MERGE
        int[] one = makeRandomAscendingArray();
        int[] two = makeRandomAscendingArray();
        System.out.println("array1: " + Arrays.toString(one));
        System.out.println("array2: " + Arrays.toString(two));


        // makeRandomAscendingArray creates a random array with a capacity between 50 and 150.
        // Its elements are guaranteed to be sorted ascending.
        // 1. Create a new int[] with capacity for all elements from `one` and `two`.
        int sum = one.length + two.length;
        int[] both = new int[sum];

        // 2. "Merge" elements from `one` and `two` into the new array so that its values are sorted.


        for (int i = 0, oneIndex = 0, twoIndex = 0; i < both.length; i++) {
            if (oneIndex >= one.length) {
                both[i] = two[twoIndex];
                twoIndex++;
            } else if (twoIndex >= two.length) {
                both[i] = one[oneIndex];
                oneIndex++;
            } else if (one[oneIndex] <= two[twoIndex]) {
                both[i] = one[oneIndex];
                oneIndex++;
            } else if (two[twoIndex] < one[oneIndex]) {
                both[i] = two[twoIndex];
                twoIndex++;
            }


        }

        System.out.println("array both: " + Arrays.toString(both));
         /* Pseudocode:
         Create an integer index for `one`, `two`, and the result array, all starting at 0.
         Loop resultIndex from 0 to result.length - 1:
           if one[oneIndex] is less than two[twoIndex], add it to the result and increment oneIndex.
           if two[twoIndex] is less than one[oneIndex], add it to the result and increment twoIndex.
           determine how to settle ties
           if oneIndex >= one.length, there are no `one` elements remaining so use elements from two
           if twoIndex >= two.length, there are no `two` elements remaining so use elements from one
          */
    }


    public static int[] makeRandomAscendingArray() {
        Random random = new Random();
        int[] result = new int[random.nextInt(100) + 50];
        int current = random.nextInt(11);
        for (int i = 0; i < result.length; i++) {
            result[i] = current;
            current += random.nextInt(4);
        }
        return result;
    }
}
