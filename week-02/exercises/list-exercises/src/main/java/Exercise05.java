import learn.BoardGame;
import learn.GameRepository;

import java.util.ArrayList;

public class Exercise05 {

    public static void main(String[] args) {

        ArrayList<BoardGame> games = GameRepository.getAll();
        for (BoardGame game: games) {
            if (game.getCategory() == "Adventure") {
                System.out.println(game);
            }
        }
        // 1. Loop over each BoardGame in `games` and print games with the "Adventure" category.
    }
}
