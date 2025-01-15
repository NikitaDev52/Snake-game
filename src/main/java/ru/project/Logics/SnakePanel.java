package ru.project.Logics;

import ru.project.Resources;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class SnakePanel {
    private final ArrayList<Point> segments;
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
            g.fillRect(segment.x * Resources.SIZE_OBJ, segment.y * Resources.SIZE_OBJ, Resources.SIZE_OBJ, Resources.SIZE_OBJ);
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
                newHead.x * Resources.SIZE_OBJ >=  Resources.IMG_BACKGROUNG.getWidth()||
                newHead.y * Resources.SIZE_OBJ >= Resources.IMG_BACKGROUNG.getHeight()) {
            endGame("Ты проиграл :(");
            return;
        }

        segments.add(0, newHead);
        segments.remove(segments.size() - 1);
    }

    public void endGame(String message) {
        JOptionPane.showMessageDialog(null, "Game over! " + message, "Snake Game", JOptionPane.INFORMATION_MESSAGE);
        System.exit(0);
    }

    public Point getHead() {
        return segments.get(0);
    }

    public void grow() {
        segments.add(new Point(segments.get(segments.size() - 1)));
    }

    public int getTileSize() {
        return Resources.SIZE_OBJ;
    }

    public int getSnakeSize() {
        return segments.size();
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
