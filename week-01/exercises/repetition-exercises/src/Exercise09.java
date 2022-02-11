public class Exercise09 {

    public static void main(String[] args) {
        // 1. Write a loop to sum all numbers between 7 and 16.
        // 2. Print the result.

        int a = 7;
        int sum = 0;

        while (a < 16) {
            sum = sum + a;
            a= a+1;
        }

        System.out.println(sum);
    }

}
