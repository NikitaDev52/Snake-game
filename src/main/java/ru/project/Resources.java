package ru.project;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Resources {
    public static final BufferedImage IMG_BACKGROUNG;
    public static final String IMG_BACKGROUNG_PATH = "src/main/resources/IMGs/фон для змейки.png";

    public static final int SIZE_OBJ = 20;
    public static final int SCORE_WIN = 50;
    public static final int TEXT_SIZE = 18;

    public static final String MESSAGE_WIN = "Ты победил!";
    public static final String FONT_TEXT = "Arial";
    public static final String VICTORY_CONDITIONS = "Собери " + SCORE_WIN + " яблок чтобы победить! ";


    static {
        try {
            IMG_BACKGROUNG = ImageIO.read(new File(IMG_BACKGROUNG_PATH));
        } catch (IOException e) {
            throw new RuntimeException("Не удалось найти или открыть фото :( , стек: " + e);
        }
    }
}
