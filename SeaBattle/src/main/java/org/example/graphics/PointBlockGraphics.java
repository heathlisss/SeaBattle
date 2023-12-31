package org.example.graphics;

import org.example.entity.PointBlock;
import org.example.utils.Config;

import javax.swing.*;
import java.awt.*;
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
    public static void drawAnEmptyCell(Graphics2D g2d, PointBlock pointBlock){
        if (!pointBlock.isOpened()) {
            return;
        }
        ImageIcon imageIcon = new ImageIcon("SeaBattle\\image\\hatching.png");
        Image image = imageIcon.getImage();


        g2d.drawImage(
                image,
                (int) pointBlock.getRectangle().getX(),
                (int) pointBlock.getRectangle().getY(),
                (int) pointBlock.getRectangle().getWidth(),
                (int) pointBlock.getRectangle().getHeight(),
                null);
    }
}
