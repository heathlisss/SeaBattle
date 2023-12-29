package org.example.gui;

import org.example.entity.Immovable;
import org.example.gamerules.Player;
import org.example.graphics.PointBlockGraphics;
import org.example.utils.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DrawPanel extends JPanel {
    private final int panelWidth;
    private final int startX;
    private final int startY;
    private final int widthBlock;
    private final Player player;
    private Player activePlayer;

    public DrawPanel(int startX, int startY, int width, Player player) {
        panelWidth = width;
        this.startX = startX + 20;
        this.startY = startY + 40;
        this.player = player;
        widthBlock = panelWidth / (Config.MAX_CORD + 1);
        setPreferredSize(new Dimension(panelWidth, panelWidth));
        setRectangles();
        setBackground(Config.BACKGROUND_COLOR);
    }

    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        drawBlocks((Graphics2D) g);
        drawEntities((Graphics2D) g);
        if (activePlayer == player) {
            drawActivePlayer((Graphics2D) g);
        } else {
           drawNotActivePlayer ((Graphics2D) g);
        }
    }

    private void setRectangles() {
        for (int column = Config.MIN_CORD; column < Config.MAX_CORD; column++) {
            for (int row = Config.MIN_CORD; row < Config.MAX_CORD; row++) {
                player.getTable()[row][column].setRectangle(
                        new Rectangle2D.Double(
                                startX + widthBlock * column,
                                startY + widthBlock * row,
                                widthBlock,
                                widthBlock));
            }
        }
    }

    private void drawEntities(Graphics2D g2d) {
        for (Immovable immovable : player.getEntities()) {
            if (immovable != null) {
                immovable.getPainter().draw(g2d);
            }
        }
    }

    private void drawBlocks(Graphics2D g2d) {
        setRectangles();
        for (int column = Config.MIN_CORD; column < Config.MAX_CORD; column++) {
            for (int row = Config.MIN_CORD; row < Config.MAX_CORD; row++) {
                PointBlockGraphics.drawACell(g2d, player.getTable()[row][column]);
                if (!player.getTable()[row][column].hasHost()) {
                    PointBlockGraphics.drawAnEmptyCell(g2d, player.getTable()[row][column]);
                }
            }
        }
    }

    private void drawActivePlayer(Graphics2D g2d) {
        g2d.setColor(new Color(206, 229, 147));
        g2d.drawRect(startX - 1, startY - 1, widthBlock * (Config.MAX_CORD ) + 2, widthBlock * (Config.MAX_CORD ) + 2);
    }

    private void drawNotActivePlayer(Graphics2D g2d) {
        g2d.setColor(Config.BACKGROUND_COLOR);
        g2d.drawRect(startX - 1, startY - 1, widthBlock * (Config.MAX_CORD ) + 2, widthBlock * (Config.MAX_CORD ) + 2);

    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getWidthBlock() {
        return widthBlock;
    }

    public Player getPlayer() {
        return player;
    }

    public void setActivePlayer(Player activePlayer) {
        this.activePlayer = activePlayer;
    }
}