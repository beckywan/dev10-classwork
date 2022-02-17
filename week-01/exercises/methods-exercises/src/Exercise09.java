public class Exercise09 {

    public static void main(String[] args) {
        // 2. Call your method in various ways to test it here.
        printBox(4,5 );
        printBox(1,5 );
        printBox(2,3 );
        printBox(4,8 );
    }


    // 1. Create a method.
    // Name: printBox
    // Inputs: int, int
    // Output: void
    // Description: prints a visual box to the console. The first parameter is the number of rows to print.
    // The second parameter is the number of columns.
    // See repetition Exercise15.
    public static void printBox(int rows, int cols) {
        for (int i1 = 0; i1 < rows; i1++) {
            for (int i2 = 0; i2 < cols; i2++) {
                System.out.print("#");
            }
            System.out.print("\n");
        }
    }

    // Expected Output (5 rows, 5 columns)
    // #####
    // #####
    // #####
    // #####
    // #####

    // (3 rows, 4 columns)
    // ####
    // ####
    // ####
}
