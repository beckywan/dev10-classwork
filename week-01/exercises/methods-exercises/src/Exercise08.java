public class Exercise08 {

    // 1. Create a method.
    // Name: getRandomFruit
    // Inputs: none
    // Output: String
    // Description: returns a random fruit name as a string.
    // See Exercise01.
    // Choose from at least 5 fruit.
    public static String getRandomFruit() {
        switch ((int) (Math.random() * 5)) {
            case 0:
                return "apple";
            case 1:
                return "orange";
            case 2:
                return "pear";
            case 3:
                return "grape";
            case 4:
                return "pineapple";
        }
        return ""; // Should never happen.
    }
    public static void main(String[] args) {
        // 2. Call your method in various ways to test it here.
        System.out.println(getRandomFruit());
        System.out.println(getRandomFruit());
        System.out.println(getRandomFruit());
        System.out.println(getRandomFruit());
        System.out.println(getRandomFruit());

    }
}
