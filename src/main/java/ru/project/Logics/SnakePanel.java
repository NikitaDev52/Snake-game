package ru.project.Logics;

import ru.project.Resources;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class SnakePanel {
    private final ArrayList<Point> segments;
    private final int tileSize = 20;
    private String direction = "RIGHT";
    public SnakePanel() throws IOException {
        this.segments = new ArrayList<>();
        segments.add(new Point(5, 5)); // Голова
        segments.add(new Point(4, 5)); // Тело
        segments.add(new Point(3, 5)); // Хвост
    }
    public void paintSnake(Graphics g) {
        g.setColor(Color.GREEN);

        for (Point segment : segments) {
            g.fillRect(segment.x * tileSize, segment.y * tileSize, tileSize, tileSize);
        }
    }

    public void move() {
        Point head = segments.get(0);
        Point newHead = switch (direction) {
            case "UP" -> new Point(head.x, head.y - 1);
            case "DOWN" -> new Point(head.x, head.y + 1);
            case "LEFT" -> new Point(head.x - 1, head.y);
            case "RIGHT" -> new Point(head.x + 1, head.y);
            default -> head;
        };

        if (newHead.x < 0 || newHead.y < 0 ||
                newHead.x * tileSize >=  Resources.imgBackgroung.getWidth()||
                newHead.y * tileSize >= Resources.imgBackgroung.getHeight()) {
            endGame();
            return;
        }

        segments.add(0, newHead);
        segments.remove(segments.size() - 1);
    }

    private void endGame() {
        JOptionPane.showMessageDialog(null, "Game Over!", "Snake Game", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public Point getHead() {
        return segments.get(0);
    }

    public void grow() {
        segments.add(new Point(segments.get(segments.size() - 1)));
    }

    public int getTileSize() {
        return tileSize;
    }

    // Метод для установки нового направления
    public void setDirection(String direction) {
        if ((direction.equals("UP") && this.direction.equals("DOWN")) || direction.equals("DOWN") && this.direction.equals("UP")) {
            return;
        } else if ((direction.equals("LEFT") && this.direction.equals("RIGHT")) || direction.equals("RIGHT") && this.direction.equals("LEFT")) {
            return;
        } else {
            this.direction = direction;
        }
    }
}
