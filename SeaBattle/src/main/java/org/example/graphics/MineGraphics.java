package org.example.graphics;

import org.example.entity.Mine;
import org.example.entity.PointBlock;

import javax.swing.*;
import java.awt.*;

public class MineGraphics implements Painter{
    private final Mine mine;

    public MineGraphics(Mine mine) {
        this.mine = mine;
    }

    @Override
    public void draw(Graphics2D g2d) {
        if (mine.isVisible()) {
            ImageIcon imageIcon = new ImageIcon("SeaBattle\\image\\mine.png");
            Image image = imageIcon.getImage();

            PointBlock pointBlock = mine.getCoords()[0];
            g2d.drawImage(
                    image,
                    (int) pointBlock.getRectangle().getX(),
                    (int) pointBlock.getRectangle().getY(),
                    (int) pointBlock.getRectangle().getWidth(),
                    (int) pointBlock.getRectangle().getHeight(),
                    null);
        }
    }
}
