package ru.project.Logics;

import java.awt.*;
import java.util.ArrayList;

public class SnakePanel {
    private final ArrayList<Point> segments;
    private final int tileSize = 20;
    private String direction = "RIGHT";
    public SnakePanel() {
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

        segments.add(0, newHead);
        segments.remove(segments.size() - 1);
    }

    // Метод для установки нового направления
    public void setDirection(String direction) {
        this.direction = direction;
    }
}
