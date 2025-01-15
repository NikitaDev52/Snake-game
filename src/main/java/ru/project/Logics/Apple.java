package ru.project.Logics;

import ru.project.Resources;

import java.awt.*;
import java.util.Random;

public class Apple {
    private Random random = new Random();
    private Point position;

    public Apple(int width, int height) {
        generateNewApplePos(width, height);
    }

    public void generateNewApplePos(int width, int height) {
        int x = random.nextInt(width / Resources.SIZE_OBJ) * Resources.SIZE_OBJ;
        int y = random.nextInt(height / Resources.SIZE_OBJ) * Resources.SIZE_OBJ;

        position = new Point(x, y);
    }

    public void paintApple(Graphics graphics) {
        graphics.setColor(Color.RED);
        graphics.fillRect(position.x, position.y, Resources.SIZE_OBJ, Resources.SIZE_OBJ);
    }

    public Point getPosition() {
        return position;
    }
}
