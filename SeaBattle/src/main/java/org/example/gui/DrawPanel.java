package org.example.gui;

import org.example.entity.PointBlock;
import org.example.graphics.PointBlockGraphics;
import org.example.utils.Config;
import org.example.utils.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawPanel extends JPanel {
    private final int panelWidth;
    private final int startX;
    private final int startY;
    private final int widthBlock;
    private final PointBlock[][] table;

    public DrawPanel(int startX, int startY, int width, PointBlock[][] table) {
        panelWidth = width;
        this.startX = startX + 20;
        this.startY = startY + 40;
        this.table = table;
        widthBlock = panelWidth / (Config.MAX_CORD + 1);
        setPreferredSize(new Dimension(panelWidth, panelWidth));
        setBackground(Config.BACKGROUND_COLOR);
    }

    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        for (int colum = Config.MIN_CORD; colum < Config.MAX_CORD; colum++) {
            for (int row = Config.MIN_CORD; row < Config.MAX_CORD; row++) {
                table[row][colum].rectangle = new Rectangle2D.Double(
                        startX + widthBlock * colum,
                        startY + widthBlock * row,
                        widthBlock,
                        widthBlock);
                PointBlockGraphics.drawACell((Graphics2D) g, table[row][colum]);
            }
        }
    }


}
