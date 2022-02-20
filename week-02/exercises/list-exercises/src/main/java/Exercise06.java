import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise06 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();

        int max = 0;
        BoardGame maxGame = null;

        for (BoardGame game: games) {
            if (game.getMaxPlayers() > max) {
                max = game.getMaxPlayers();
                maxGame = game;
            }
        }
        System.out.println(maxGame.getName());
        // 1. Use a loop to find the game in `games` that can be played by the most players.
        // 2. Print the game. (Expected: "Ultimate Werewolf...")
    }
}

