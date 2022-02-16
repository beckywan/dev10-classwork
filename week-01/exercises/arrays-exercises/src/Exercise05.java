public class Exercise05 {

    public static void main(String[] args) {
        // 1. Declare an array to hold the names of the world's continents.
        String[] continents = new String[6];
        // Do not use array literal notation. Allocate space for 6 continents and then set each value by index.
        continents[0] = "Africa";
        continents[1] = "Asia";
        continents[2] = "North America";
        continents[3] = "South America";
        continents[4] = "Europe";
        continents[5] = "Australia";
        // there are 7 continents; we are excluding Antarctica
        // 2. Loop over each element and print it.
        for (int i = 0; i < continents.length; i++) {
            System.out.println(continents[i]);
        }
    }
}
