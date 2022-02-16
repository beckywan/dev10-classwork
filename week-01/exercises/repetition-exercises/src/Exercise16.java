public class Exercise16 {

    public static void main(String[] args) {
        // BORDER BOX
        // 1. Use nested loops to print a box in the console with a different character for the border.
        // One loop should represent rows and the other should represent columns.
        // The border character should be different from the internal box character.
        // 2. Change the row and column limit to change the shape of the box.
        int row = 5;
        int col = 7;
        String border = "*";
        System.out.println(border.repeat(col));
        for(int rowCount = 2; rowCount < row; rowCount++){
            System.out.print(border);
            for (int colCount = 2; colCount < col ; colCount++){
                System.out.print("#");
            }
            System.out.println(border);
        }
        System.out.println(border.repeat(col));


        // Expected Output (5X5)
        // *****
        // *###*
        // *###*
        // *###*
        // *****

        // (3X4)
        // ****
        // *##*
        // ****

        // (2X2)
        // **
        // **
    }
}
