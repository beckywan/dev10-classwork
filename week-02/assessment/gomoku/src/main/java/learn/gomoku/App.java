package learn.gomoku;

import learn.gomoku.game.Game;
import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Result;
import learn.gomoku.game.Stone;
import learn.gomoku.players.HumanPlayer;
import learn.gomoku.players.Player;
import learn.gomoku.players.RandomPlayer;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class App {
    public static void main(String[] args) {
        Game newGame = new Game();
        newGame.run();
    }



}