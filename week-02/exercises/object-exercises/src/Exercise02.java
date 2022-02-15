public class Exercise02 {

    public static void main(String[] args) {

        // 1. Add a getter for the rating field in Musician.

        Musician ocean = new Musician("Frank Ocean", 10);
        System.out.println(ocean.getName());
        // 2. Uncomment the line below and insure that it compiles and runs.
        System.out.println(ocean.getRating());

        // 3. Instantiate two musicians and assign them to new variables.
        // 4. Print each musicians' name and rating on a single line.
        Musician swift = new Musician("Taylor Swift", 9);
        System.out.printf("%s: %s/n", swift.getName(), swift.getRating());

        Musician styles = new Musician("Harry Styles", 7);
        System.out.println(styles.getName() + "," + styles.getRating());
    }
}
