public class Exercise04 {

    public static void main(String[] args) {
        System.out.println(getFirstVowel("magnificent")); // Expected: a
        System.out.println(getFirstVowel("winsome")); // Expected: i
        System.out.println(getFirstVowel("xxx")); // Expected:
        System.out.println(getFirstVowel("abcde")); // Expected: a
        System.out.println(getFirstVowel("ufo")); // Expected: u

        // 2. Call getFirstVowel at least one more time.
    }

    // getFirstVowel returns the first vowel in a string as a char.
    // 1. Complete getFirstVowel.
    // If no vowel is found, return 0. (As a char, 0 represents the NULL value.)
    public static char getFirstVowel(String value) {
        for (int i = 0; i < value.length(); i++ ) {
            if (value.charAt(i) == 'a' || value.charAt(i) == 'e' || value.charAt(i) == 'i' || value.charAt(i) == 'o' || value.charAt(i) == 'u') {
                return value.charAt(i);
            }
        }
        return 0;
    }
}
