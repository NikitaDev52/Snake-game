package ru.project.Interface;

import ru.project.Logics.SnakePanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class SnakeWindowGame implements WindowGame {
    private final String imgBackgroungPath = "src/main/resources/IMGs/фон для змейки.png";
    private final SnakePanel snake = new SnakePanel();
    @Override
    public void createWindow() {
        try {
            BufferedImage imageBackGroung = ImageIO.read(new File(imgBackgroungPath));
            JFrame frame = new JFrame("Snake game");
            frame.setSize(imageBackGroung.getWidth(), imageBackGroung.getHeight());
            // фон для змейки
            JPanel panel = new JPanel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Image backgroundImage = Toolkit.getDefaultToolkit().getImage(imgBackgroungPath);
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

                    snake.paintSnake(g);
                }
            };
            frame.add(panel);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
                panel.repaint();
            });

            timer.start();
        } catch (IOException e) {
            throw new RuntimeException("Не можем найти или открыть изображение :( , стек ошибки: " + e);
        }
    }
}
