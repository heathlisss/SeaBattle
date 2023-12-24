package org.example.gui;

import org.example.entity.PointBlock;
import org.example.entity.Ship;
import org.example.graphics.PointBlockGraphics;
import org.example.graphics.ShipGraphics;
import org.example.utils.Config;
import org.example.utils.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;

public class DrawPanel extends JPanel {
    private final int PANEL_WIDTH;
    private final int PANEL_HEIGHT;
    private final int START_X;
    private final int START_Y;
    private final int WIDTH_BLOCK;
    private final int HEIGHT_BLOCK;

    public DrawPanel(int startX, int startY, int width, int height) {
        PANEL_WIDTH = width;
        PANEL_HEIGHT = height;
        START_X = startX + 30;
        START_Y = startY + 30;
        WIDTH_BLOCK = PANEL_WIDTH / (Config.MAX_CORD + 1);
        HEIGHT_BLOCK = WIDTH_BLOCK;
        setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
        setBackground(new Color(startX / 10, startX / 10, startX / 10));
    }

    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        for (int colum = Config.MIN_CORD; colum < Config.MAX_CORD; colum++) {
            for (int row = Config.MIN_CORD; row < Config.MAX_CORD; row++) {
                PointBlock pointBlock = new PointBlock(new Point(
                        START_X + WIDTH_BLOCK * colum,
                        START_Y + WIDTH_BLOCK * row),
                        new Rectangle2D.Double(
                                START_X + WIDTH_BLOCK * colum,
                                START_Y + WIDTH_BLOCK * row,
                                WIDTH_BLOCK,
                                HEIGHT_BLOCK));
                PointBlockGraphics.drawACage((Graphics2D) g, pointBlock);
                 ShipGraphics.draw((Graphics2D) g, new Ship(new PointBlock[]{pointBlock}));
            }
        }
    }


}
