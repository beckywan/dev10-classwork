import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;
import java.util.Arrays;

public class Exercise10 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        for (int i = 0 ; i < games.size(); i++) {
            BoardGame game = games.get(i);
            if (game.getMinPlayers() < 2) {
                games.remove(i);
            }
        }

        for (BoardGame game: games) {
            System.out.println(game);
        }
        // 1. Loop over `games` and remove any game that can be played by one person.
        // 2. Print `games` and confirm single-player games are removed.
    }
}
