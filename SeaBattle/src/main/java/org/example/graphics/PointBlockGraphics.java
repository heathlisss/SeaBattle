package org.example.graphics;

import org.example.entity.PointBlock;
import org.example.utils.Config;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class PointBlockGraphics {
    /**
     * рисование клетки`
     */
    public static void drawACell(Graphics2D g2d, PointBlock pointBlock) {
        g2d.setColor(Config.BACKGROUND_COLOR);
        g2d.fill(pointBlock.getRectangle());

        g2d.setColor(Config.Cell_COLOR);
        g2d.draw(pointBlock.getRectangle());
    }

    /**
     * заполнение пустой клетки
     */
    public static void drawAnEmptyCell(Graphics2D g2d, PointBlock pointBlock) throws IOException {
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Lizka\\git\\SeaBattle\\SeaBattle\\image\\hatching.png");
        Image image = imageIcon.getImage();

        g2d.drawImage(image, pointBlock.coordinate.x, pointBlock.coordinate.y,
                (int) pointBlock.getRectangle().getWidth(), (int) pointBlock.getRectangle().getHeight(), null);
    }
}
