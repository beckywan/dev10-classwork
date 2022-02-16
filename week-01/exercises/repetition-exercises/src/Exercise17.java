import java.util.Scanner;

public class Exercise17 {

    public static void main(String[] args) {
        // USER-DEFINED BOX
        // 1. Collect the following from a user: rows, columns, box character, border character.
        // 2. Use nested loops to print a user-defined box in the console.
        // (See Exercise16.)
        Scanner console = new Scanner(System.in);

        System.out.println("How many rows?");
        int row = Integer.parseInt(console.nextLine());
        System.out.println("How many columns?");
        int col = Integer.parseInt(console.nextLine());
        System.out.println("Border pattern?");
        String border = console.nextLine();
        System.out.println("Box character?");
        String boxChar = console.nextLine();

        System.out.println(border.repeat(col));
        for(int rowCount = 2; rowCount < row; rowCount++){
            System.out.print(border);
            for (int colCount = 2; colCount < col ; colCount++){
                System.out.print(boxChar);
            }
            System.out.println(border);
        }
        System.out.println(border.repeat(col));


    }
}
