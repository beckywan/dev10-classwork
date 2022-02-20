import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class Exercise13 {

    private ArrayList<BoardGame> games = GameRepository.getAll();
    private Scanner console = new Scanner(System.in);

    public static void main(String[] args) {
        Exercise13 instance = new Exercise13();
        instance.run();
    }

    private void printHeader(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }

    private void run() {

        printHeader("Welcome to the Board Game Manager.");

        String input;
        do {
            printHeader("Menu");
            System.out.println("1. Add a board game.");
            System.out.println("2. Display all board games.");
            System.out.println("3. Remove a board game.");
            System.out.println("4. Exit");
            System.out.print("Select [1-4]: ");
            input = console.nextLine();

            switch (input) {
                case "1":
                    // 1. Create a method that gathers user input via `console` to instantiate a BoardGame
                    // and then adds it to the `games` list.
                    addGame();

                    // 2. Replace the the line of code below with a call to the method.
                    break;
                case "2":
                    // 3. Create a method to display all board games in the `games` list.
                    // 4. Replace the the line of code below with a call to the method.
                    viewGames();

                    break;
                case "3":
                    // 5. Stretch Goal: Create a method that allows the user to remove a board game from the
                    // `games` list with an index.
                    removeGame();

                    // 6. Replace the the line of code below with a call to the method.
                    printHeader("Remove a board game.");
                    break;
                case "4":
                    printHeader("Goodbye.");
                    break;
                default:
                    System.out.printf("%nI don't understand '%s'.%n", input);
                    break;
            }
        } while (!input.equals("4"));
    }

    private void removeGame() {
        System.out.println("# of the game to remove:");
        int number = Integer.parseInt(console.nextLine());
        int index = number - 1;
            games.remove(number - 1);
    }

    private void viewGames() {
        for (BoardGame game: games) {
            System.out.println(game);
        }
    }

    private void addGame() {
        System.out.println("Name of the game:");
        String name = console.nextLine();
        System.out.println("Min players:");
        int minPlayers = Integer.parseInt(console.nextLine());
        System.out.println("Max Players:");
        int maxPlayers = Integer.parseInt(console.nextLine());
        System.out.println("Category:");
        String category = console.nextLine();
        games.add(new BoardGame(name, minPlayers, maxPlayers, category));
    }
}
