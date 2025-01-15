package ru.project;

import ru.project.Interface.Game;
import ru.project.Interface.Snake;
import ru.project.Interface.SnakeWindowGame;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Game game = new Snake();
        game.startGame(new SnakeWindowGame());
    }
}