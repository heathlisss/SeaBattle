package org.example.graphics;

import org.example.entity.PointBlock;
import org.example.entity.Ship;
import org.example.utils.MathUtils;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class ShipGraphics {
    public static void draw(Graphics2D g2d, Ship ship) {
        int maxX = ship.getCords()[0].coordinate.x;
        int maxY = ship.getCords()[0].coordinate.y;
        int minX = ship.getCords()[ship.getCords().length - 1].coordinate.x;
        int minY = ship.getCords()[ship.getCords().length - 1].coordinate.y;

        if (maxX < minX || maxY < minY) {
            int c = maxX;
            maxX = minX;
            minX = c;
            c = maxY;
            maxY = minY;
            minY = c;
        }

        ImageIcon imageIcon;
        if (maxX - minX == 0) {
             imageIcon = new ImageIcon("C:\\Users\\Lizka\\git\\SeaBattle\\SeaBattle\\image\\ship1.png");
        } else {
             imageIcon = new ImageIcon("C:\\Users\\Lizka\\git\\SeaBattle\\SeaBattle\\image\\ship2.jpg");
        }
        Image image = imageIcon.getImage();
        g2d.drawImage(image, minX, minY, (int) ((maxX - minX + 1) * ship.getCords()[0].rectangle.getWidth()), (int) ((maxY - minY + 1) * ship.getCords()[0].rectangle.getHeight()), null)
        ;
    }

    /**
     * попадание в корабль
     */
    public static void drawAHit(Graphics2D g2d, PointBlock pointBlock) {
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Lizka\\git\\SeaBattle\\SeaBattle\\image\\X.png");
        Image image = imageIcon.getImage();

        g2d.drawImage(image, pointBlock.coordinate.x, pointBlock.coordinate.y,
                (int) pointBlock.rectangle.getWidth(), (int) pointBlock.rectangle.getHeight(), null);
    }

}
