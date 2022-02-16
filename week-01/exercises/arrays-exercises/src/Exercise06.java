public class Exercise06 {

    public static void main(String[] args) {
        int[] values = {18, 42, 54, 93, 22};
        int sum = 0;

        // 1. Create a loop to calculate the sum of elements in `values`.
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        // 2. Print the result.
        System.out.println(sum);

    }
}
