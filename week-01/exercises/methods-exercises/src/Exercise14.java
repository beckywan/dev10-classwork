import java.util.Scanner;

public class Exercise14 {
    /* SHORT SURVEY

    Write a program that asks a user four questions and prints the results:
    - What is your first name?
    - What is your last name?
    - How many towns/cities have you lived in?
    - How many musical instruments can you play?

    Store each answer in a variable with an appropriate type.
    Print the results after the user has answered all four questions.

    Use methods to break the program into reusable blocks of code.
     */
    public static void main(String[] args) {
        String firstName = readString("What is your first name?");
        String lastName = readString("What is your last name?");
        String numTowns = readString("How many towns/cities have you lived in?");
        String numInstruments = readString("How many musical instruments can you play?");
        System.out.println(firstName + " " + lastName + "\nNumber of towns lived in: " + numTowns + "\nNumber of playable instruments: " + numInstruments);
    }
    public static String readString(String prompt) {
        Scanner console = new Scanner(System.in);
        System.out.print(prompt);
        return console.nextLine();
    }
}
