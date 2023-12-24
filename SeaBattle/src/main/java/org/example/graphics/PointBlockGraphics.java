package org.example.graphics;

import org.example.entity.PointBlock;
import org.example.utils.Config;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PointBlockGraphics {
    /**
     * рисование клетки`
     */
    public static void drawACage(Graphics2D g2d, PointBlock pointBlock) {
        g2d.setColor(Config.WATER_COLOR);
        g2d.draw(pointBlock.rectangle);
    }

    /**
     * попадание в корабль
     */
    public static void drawAHit(Graphics2D g2d, PointBlock pointBlock) {
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Lizka\\git\\SeaBattle\\SeaBattle\\image\\X.png");
        Image image = imageIcon.getImage();

        g2d.drawImage(image, pointBlock.coordinate.X, pointBlock.coordinate.Y,
                (int) pointBlock.rectangle.getWidth(), (int) pointBlock.rectangle.getHeight(), null);
    }

    /**
     * заполнение пустой клетки
     */
    public static void drawAnEmptyCage(Graphics2D g2d, PointBlock pointBlock) throws IOException {
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Lizka\\git\\SeaBattle\\SeaBattle\\image\\hatching.png");
        Image image = imageIcon.getImage();

        g2d.drawImage(image, pointBlock.coordinate.X, pointBlock.coordinate.Y,
                (int) pointBlock.rectangle.getWidth(), (int) pointBlock.rectangle.getHeight(), null);
    }
}
