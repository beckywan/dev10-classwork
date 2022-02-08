public class Exercise20 {

    public static void main(String[] args) {

        // From Alice Roosevelt Longworth:
        String aliceQuote = "If you can't say something good about someone, sit right here by me.";

        char tenthChar = aliceQuote.charAt(9);
        System.out.println(tenthChar);

        char firstChar = aliceQuote.charAt(0);
        char twentyChar = aliceQuote.charAt(19);
        char sixtyeightChar = aliceQuote.charAt(67);

        System.out.println(firstChar);
        System.out.println(twentyChar);
        System.out.println(sixtyeightChar);
        // 1. Store the first character from aliceQuote in a char variable.
        // 2. Print it.
        // 3. Print the 20th character.
        // 4. Print the 68th character.
    }
}