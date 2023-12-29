package org.example.graphics;

import org.example.entity.PointBlock;
import org.example.entity.Ship;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class ShipGraphics implements Painter {
    private Ship ship;

    public ShipGraphics(Ship ship) {
        this.ship = ship;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (!ship.isOpened()) {
            for (PointBlock block : ship.getCoords()) {
                drawAHit(g2d, block);
            }
            return;
        }
        int maxX = ship.getCoords()[0].coordinate.x;
        int minX = ship.getCoords()[ship.getCoords().length - 1].coordinate.x;
        Rectangle2D bounds = convert(ship.getCoords());

        ImageIcon imageIcon;
        if (maxX - minX == 0) {
            imageIcon = new ImageIcon("SeaBattle\\image\\ship1.png");
        } else {
            imageIcon = new ImageIcon("SeaBattle\\image\\ship2.png");
        }
        Image image = imageIcon.getImage();


        g2d.drawImage(
                image,
                (int) bounds.getX(),
                (int) bounds.getY(),
                (int) bounds.getWidth(),
                (int) bounds.getHeight(),
                null);

        for (PointBlock block : ship.getCoords()) {
            drawAHit(g2d, block);
        }
    }

    /**
     * попадание в корабль
     */
    public static void drawAHit(Graphics2D g2d, PointBlock pointBlock) {
        if (!pointBlock.isOpened()) {
            return;
        }
        ImageIcon imageIcon = new ImageIcon("SeaBattle\\image\\X.png");
        Image image = imageIcon.getImage();

        g2d.drawImage(image, (int) pointBlock.getRectangle().getX(), (int) pointBlock.getRectangle().getY(),
                (int) pointBlock.getRectangle().getWidth(), (int) pointBlock.getRectangle().getHeight(), null);
    }

    /**
     * Считает новый прямоугольник по массиву PointBlocks
     *
     * @param blocks массив поинтблоков (корабля)
     * @return прямоугольник максимальной ширины и высоты
     */
    public static Rectangle2D convert(PointBlock[] blocks) {
        double x1 = blocks[0].getRectangle().getX();
        double x2 = blocks[blocks.length - 1].getRectangle().getX();

        double y1 = blocks[0].getRectangle().getY();
        double y2 = blocks[blocks.length - 1].getRectangle().getY();

        double h = Math.abs(y1 - y2) + blocks[0].getRectangle().getHeight();
        double w = Math.abs(x1 - x2) + blocks[0].getRectangle().getWidth();

        double x = Math.min(x1, x2);
        double y = Math.min(y1, y2);

        return new Rectangle2D.Double(x, y, w, h);
    }
}
