package org.example.graphics;

import org.example.entity.PointBlock;
import org.example.entity.Ship;
import org.example.utils.MathUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Arrays;
import java.util.Comparator;

public class ShipGraphics {
    public static void draw(Graphics2D g2d, Ship ship) {
        int[] xArray = Arrays.stream(ship.getCords()).mapToInt(pointBlock -> pointBlock.coordinate.X).toArray();
        int[] yArray = Arrays.stream(ship.getCords()).mapToInt(pointBlock -> pointBlock.coordinate.Y).toArray();
        int maxX = MathUtils.max(xArray);
        int maxY = MathUtils.max(yArray);
        int minX = MathUtils.min(xArray);
        int minY = MathUtils.min(yArray);

        if (maxY - minY > maxX - minX) {
            ImageIcon imageIcon = new ImageIcon("C:\\Users\\Lizka\\git\\SeaBattle\\SeaBattle\\image\\ship1.png");
            Image image = imageIcon.getImage();
            g2d.drawImage(image, minX, minY, (int) ship.getCords()[0].rectangle.getWidth(), (int) (yArray.length * ship.getCords()[0].rectangle.getHeight()), null);
        } else {
            ImageIcon imageIcon = new ImageIcon("C:\\Users\\Lizka\\git\\SeaBattle\\SeaBattle\\image\\ship2.jpg");
            Image image = imageIcon.getImage();
            g2d.drawImage(image, minX, minY, (int) (xArray.length * ship.getCords()[0].rectangle.getWidth()), (int) ship.getCords()[0].rectangle.getHeight(), null);
        }
    }
    public static void drawAHit(Graphics2D g2d, PointBlock pointBlock) {
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Lizka\\git\\SeaBattle\\SeaBattle\\image\\ship1.png");
        Image image = imageIcon.getImage();
        g2d.drawImage(image, pointBlock.coordinate.X, pointBlock.coordinate.Y,(int) pointBlock.rectangle.getWidth(), (int) pointBlock.rectangle.getHeight()*2, null);
    }
}
