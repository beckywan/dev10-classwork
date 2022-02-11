import java.util.Scanner;

public class Exercise14 {

    public static void main(String[] args) {
    Scanner console = new Scanner(System.in);
    System.out.println("insert phrase");
    String phrase = console.nextLine();

    int i = 0;
    int count = 0;

    while (i < phrase.length()) {
        if (Character.isDigit(phrase.charAt(i))){
            count = count + 1;
        }
        i++;
    }

    System.out.println(count);
    // 1. Collect a phrase from a user via the console.
        // 2. Count the number of digits in the phrase.
        // Hint: Character.isDigit
        // 3. Print the result.
    }
}
