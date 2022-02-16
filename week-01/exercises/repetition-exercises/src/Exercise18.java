import java.util.Scanner;

public class Exercise18 {

    public static void main(String[] args) {
        // CUT THE MIDDLE
        Scanner console = new Scanner(System.in);

        System.out.print("Phrase: ");
        String phrase = console.nextLine();

        System.out.print("Start: ");
        int start = Integer.parseInt(console.nextLine());

        System.out.print("End: ");
        int end = Integer.parseInt(console.nextLine());

        // 1. Write a loop to create a new string from a phrase by "cutting out" any characters from the start index
        // to the end index.
        int i = 0;
        String newPhrase = "";
        while (i < phrase.length()){
            if (!(i >= start && i <= end)) {
                newPhrase += phrase.charAt(i);
            }
            i++;
        }

        String cut = phrase.substring(start, end);

        // 2. Print the result.
        System.out.println(newPhrase);
        // Examples
        // phrase, start, end -> result
        // ========================
        // "orange", 1, 2 -> "onge"
        // "orange", 3, 3 -> "orage"
        // "orange", 4, 3 -> "orange" (ignore when start is greater than end)
        // "orange", 15, 25 -> "orange" (ignore when start and end are bigger than the length of the phrase)
        // "one two three", 4, 7 -> "one three"
        // "interrupting moooo cow", 12, 17 -> "interrupting cow"
    }
}
