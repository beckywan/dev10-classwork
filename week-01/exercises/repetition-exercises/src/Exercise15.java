public class Exercise15 {

    public static void main(String[] args) {
        // BOX
        // 1. Use nested loops to print a box in the console.
        // One loop should represent rows and the other should represent columns.
        // 2. Change the row and column limit to change the shape of the box.
        int row = 5;
        int column = 4;
        int i = 0;
        int ind = 0;
        String letter = "#";
        String text = "";

        while (i < row) {
            while (ind < column) {
                text = text + letter;
                ind++;
            }
            System.out.println(text);
            i++;
        }


        // Expected Output (5X5)
        // #####
        // #####
        // #####
        // #####
        // #####

        // (3X4)
        // ####
        // ####
        // ####
    }
}
