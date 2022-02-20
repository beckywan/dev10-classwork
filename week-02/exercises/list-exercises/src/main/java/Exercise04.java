import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise04 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        // 1. Instantiate a new ArrayList<BoardGame>.
        ArrayList<BoardGame> newGames = new ArrayList<BoardGame>();

        // 2. Add three BoardGames to the new list.
        newGames.add(new BoardGame("Tic Tac Toe", 2, 4, "Mini game"));
        System.out.println(newGames.get(newGames.size() - 1));

        newGames.add(new BoardGame("Animal Crossing", 1, 4, "Video game"));
        System.out.println(newGames.get(newGames.size() - 1));

        newGames.add(new BoardGame("Pokemon", 1, 2, "Card game"));
        System.out.println(newGames.get(newGames.size() - 1));

        // 3. Print the new list.
        // 4. Add items in the new list to `games` with the `addAll` method.
        games.addAll(newGames);
        // 5. Print `games`.
        for (BoardGame game: games) {
            System.out.println(game);
        }
    }
}
