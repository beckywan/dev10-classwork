import java.util.Scanner;

public class Exercise10 {

    public static void main(String[] args) {
        // BALLOON GAME
        Scanner console = new Scanner(System.in);

        // 1. Instantiate three balloons of different colors.
        Balloon balloon1 = new Balloon("Red");
        Balloon balloon2 = new Balloon("Blue");
        Balloon balloon3 = new Balloon("Yellow");

        do {

            System.out.println("Inflate? [y/n]: ");
            if (console.nextLine().equalsIgnoreCase("y")) {
                // 2. If the user confirms an inflate, inflate each balloon.
                balloon1.inflate();
                balloon2.inflate();
                balloon3.inflate();
            }

            // 3. When one or more balloons explode, stop the loop.
        } while (!balloon1.isExploded() && !balloon2.isExploded() && !balloon3.isExploded()); {
            printBalloon(balloon1);
            printBalloon(balloon2);
            printBalloon(balloon3);
            // 4. Print the color of the winners (balloons that exploded).
        }
    }

    private static void printBalloon(Balloon balloon) {
        if (balloon.isExploded()) {
            System.out.println(balloon.getColor());
        }
    }
}
