public class Exercise10 {
    // 1. Add a `main` method.
    public static void main(String[] args) {
        System.out.println(whitespaceRemover("big ass pieeee"));
        System.out.println(whitespaceRemover("why    ee"));
        System.out.println(whitespaceRemover("  hello"));

    }
    // 2. Create method that accepts a String and returns that string with all of its whitespace remove.
    public static String whitespaceRemover(String input){
        return input.replaceAll(" ", "");
    }
    // 2. Call your method in various ways in the main method.
}
