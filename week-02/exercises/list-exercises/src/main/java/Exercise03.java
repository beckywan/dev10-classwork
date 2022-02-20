import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise03 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Add three new games to `games` with the `add` method.
        games.add(new BoardGame("Tic Tac Toe", 2, 4, "Mini game"));
        System.out.println(games.get(games.size() - 1));

        games.add(new BoardGame("Animal Crossing", 1, 4, "Video game"));
        System.out.println(games.get(games.size() - 1));

        games.add(new BoardGame("Pokemon", 1, 2, "Card game"));
        System.out.println(games.get(games.size() - 1));
        // 2. Print `games` after each add.
    }
}
