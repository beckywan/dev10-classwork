import java.util.Scanner;

public class Exercise12 {

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Enter a phrase: ");
        String phrase = console.nextLine();

        // 1. Write a loop to determine if the letter `x` occurs in a user-entered phrase.
        // 2. Print a message for both finding and not finding the `x`.
        int i = 0;

        while (i < phrase.length()) {
            char result = phrase.charAt(i);
            i = i + 1;
            if (result == 'x') {
                System.out.println("yay x");
                break;
            }
            if (i + 1 == phrase.length()) {
                    {
                        System.out.println("no x");
                        break;
                    }

            }

        }
    }
}