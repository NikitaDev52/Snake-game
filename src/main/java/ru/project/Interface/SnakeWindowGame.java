package ru.project.Interface;

import ru.project.Logics.Apple;
import ru.project.Logics.SnakePanel;
import ru.project.Resources;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class SnakeWindowGame implements WindowGame {
    private final BufferedImage imgBackgroung = Resources.IMG_BACKGROUNG;
    private final SnakePanel snake = new SnakePanel();
    private final Apple apple = new Apple(Resources.IMG_BACKGROUNG.getWidth(), Resources.IMG_BACKGROUNG.getHeight());
    // 3 - изначальный размер змейки
    private int score = snake.getSnakeSize() - 3;

    public SnakeWindowGame() throws IOException {
    }

    @Override
    public void createWindow() {
            JFrame frame = new JFrame("Snake game");
            frame.setSize(Resources.IMG_BACKGROUNG.getWidth(), Resources.IMG_BACKGROUNG.getHeight());
            // фон для змейки
            JPanel panel = new JPanel() {

                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Image backgroundImage = Toolkit.getDefaultToolkit().getImage(Resources.IMG_BACKGROUNG_PATH);
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

                    snake.paintSnake(g);

                    apple.paintApple(g);

                    g.setColor(Color.WHITE);
                    g.setFont(new Font(Resources.FONT_TEXT, Font.BOLD, Resources.TEXT_SIZE));
                    g.drawString(Resources.VICTORY_CONDITIONS + "Твой счет: " + score, 10, 20);
                }
            };
        panel.setPreferredSize(new Dimension(Resources.IMG_BACKGROUNG.getWidth(), Resources.IMG_BACKGROUNG.getHeight()));
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);


        frame.addKeyListener(new java.awt.event.KeyAdapter() {
                @Override
                public void keyPressed(java.awt.event.KeyEvent e) {
                    switch (e.getKeyCode()) {
                        case KeyEvent.VK_UP -> snake.setDirection("UP");
                        case KeyEvent.VK_DOWN -> snake.setDirection("DOWN");
                        case KeyEvent.VK_LEFT -> snake.setDirection("LEFT");
                        case KeyEvent.VK_RIGHT -> snake.setDirection("RIGHT");
                    }
                }
            });

            // Таймер для обновления игры
        Timer timer = new Timer(100, e -> {
            snake.move();

            // Проверяем, съела ли змейка яблоко
            Point appleGridPosition = new Point(apple.getPosition().x / snake.getTileSize(), apple.getPosition().y / snake.getTileSize());
            if (snake.getHead().equals(appleGridPosition)) {
                if (score == Resources.SCORE_WIN) {
                    snake.endGame(Resources.MESSAGE_WIN);
                }
                score += 1;
                snake.grow();
                apple.generateNewApplePos(Resources.IMG_BACKGROUNG.getWidth(), Resources.IMG_BACKGROUNG.getHeight());
            }
            panel.repaint();
        });

        timer.start();
    }
}
