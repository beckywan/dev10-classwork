package learn.gomoku.game;

import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.Player;
import learn.gomoku.players.RandomPlayer;

import java.util.ArrayList;
import java.util.Scanner;


public class Game {
    private static Player playerOne;
    private static Player playerTwo;
    private final static String EMPTY_CHARACTER = "-";
    static char[][] board = new char[15][15];

    public static void run() {
        Scanner console = new Scanner(System.in);
        System.out.println("Welcome to the Gomoku Game! \n");

        boolean end = false;
        while (end == false) {
            setUpPlayers(console);

            Gomoku game = new Gomoku(playerOne, playerTwo);
            ArrayList<Stone> stones = new ArrayList<>();
            char[][] board = new char[15][15];
            System.out.println("Randomizing");
            System.out.println(game.getCurrent().getName() + " is going first.");
            Result result;

            playGame(console, game, board);

            if (end(console, game, board, end) == true) {
                end = true;
            }

        }
    }

    private static boolean end(Scanner console, Gomoku game, char[][] board, boolean input) {
        boolean end = false;

        if (game.getWinner() == null) {
            System.out.printf("%nThe game was a draw between %s and %s.",
                    playerOne.getName(), playerTwo.getName());
        } else {
            System.out.printf("%nThe winner of the game is %s.", game.getWinner().getName());
        }


        System.out.println("\nDo you want to play again? [y/n]:");
        String answer = console.nextLine();
        if (answer.equalsIgnoreCase("y")) {
            System.out.println("Ok! Reloading...");
        } else {
            System.out.println("Goodbye!");
            end = true;
            return end;
        }

        return end;
    }

    private static void playGame(Scanner console, Gomoku game, char[][] board) {
        Result result;
        for (int row = 0; row < 15; row++) {
            System.out.print("\n");
            for (int col = 0; col < 15; col++) {
                if (board[row][col] == 'B') {
                    System.out.print("B");
                } else if (board[row][col] == 'W') {
                    System.out.print("W");
                } else {
                    System.out.print(EMPTY_CHARACTER);
                }
                System.out.print(" ");
            }
        }
        do {
            Stone stone = game.getCurrent().generateMove(game.getStones());
            Stone newStone = stone;
            System.out.println("\nIt is now " + game.getCurrent().getName() + "'s turn.");
            if (stone == null) {
                System.out.println(game.getCurrent().getName() + " places the stone down on...\n" +
                        "Row:");
                int row = Integer.parseInt(console.nextLine());
                System.out.println("Column:");
                int col = Integer.parseInt(console.nextLine());
                newStone = new Stone(row, col, game.isBlacksTurn());
            } else {
                System.out.printf("%s places the stone down on row %s and column %s.%n",
                        game.getCurrent().getName(), stone.getRow(), stone.getColumn());
            }

            result = game.place(newStone);

            if (!result.isSuccess()) {
                System.out.println("Try again. Error: " + result.getMessage());
            } else if (game.getWinner() == null) {
            board[newStone.getRow()][newStone.getColumn()] = game.isBlacksTurn() ? 'B' : 'W';
            } else if (game.getWinner() != null) {
                board[newStone.getRow()][newStone.getColumn()] = !game.isBlacksTurn() ? 'B' : 'W';
            }


            for (int row = 0; row < 15; row++) {
                System.out.print("\n");
                for (int col = 0; col < 15; col++) {
                    if (board[row][col] == 'B') {
                        System.out.print("B");
                    } else if (board[row][col] == 'W') {
                        System.out.print("W");
                    } else {
                        System.out.print(EMPTY_CHARACTER);
                    }
                    System.out.print(" ");
                }
            }

        } while (!game.isOver());

    }

    private static void setUpPlayers(Scanner console) {
        System.out.println(
                "Player 1 do you want to play as a HumanPlayer or RandomPlayer? \n" +
                        "Choose option 1 or 2:");
        int option = Integer.parseInt(console.nextLine());
        if (option == 1) {
            System.out.println("What is the name of the HumanPlayer?");
            String name = console.nextLine();
            playerOne = new HumanPlayer(name);
        } else if (option == 2) {
            playerOne = new RandomPlayer();
            System.out.println("RandomPlayer is generated and named " + playerOne.getName());
        }

        System.out.println("Player 2 do you want to play as a HumanPlayer or RandomPlayer? \n" +
                "Choose option 1 or 2:");
        option = Integer.parseInt(console.nextLine());
        if (option == 1) {
            System.out.println("What is the name of the HumanPlayer?");
            String name = console.nextLine();
            playerTwo = new HumanPlayer(name);
        } else if (option == 2) {
            playerTwo = new RandomPlayer();
            System.out.println("RandomPlayer is generated. \nName of player 2: " + playerTwo.getName());
        }
    }

}
