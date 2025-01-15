package ru.project;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resources {
    public static final BufferedImage imgBackgroung;
    public static final String imgBackgroungPath = "src/main/resources/IMGs/фон для змейки.png";

    static {
        try {
            imgBackgroung = ImageIO.read(new File(imgBackgroungPath));
        } catch (IOException e) {
            throw new RuntimeException("Не удалось найти или открыть фото :( , стек: " + e);
        }
    }
}
