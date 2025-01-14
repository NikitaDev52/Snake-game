package ru.project.Interface;

import java.io.IOException;

public class Snake implements Game{
    @Override
    public void startGame(WindowGame windowGame) {
        try {
            windowGame.createWindow();
        } catch (IOException e) {
            throw new RuntimeException("Не удалось запустить игру :( , стек: " + e);
        }
    }
}
