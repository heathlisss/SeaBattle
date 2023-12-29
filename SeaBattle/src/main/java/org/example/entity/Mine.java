package org.example.entity;

import org.example.gamerules.Player;
import org.example.graphics.MineGraphics;
import org.example.graphics.Painter;
import org.example.utils.Point;

import java.util.ArrayList;

public class Mine implements Immovable {
    private PointBlock[] blocks;
    private boolean isVisible;
    private final Painter painter;

    public Mine(PointBlock[] coordinates) {
        painter = new MineGraphics(this);
        blocks = coordinates;
        isVisible = true;
    }

    public boolean isVisible() {
        return isVisible || blocks[0].isOpened();
    }

    @Override
    public PointBlock[] getCoords() {
        return blocks;
    }

    @Override
    public void action(Point point, Player attacker) {
        isVisible = true;
        if (hasCoord(point)) {
            blocks[0].open();
        }
        attacker.action(point, attacker);
    }

    @Override
    public boolean canBeSurrounded() {
        return true;
    }

    @Override
    public boolean hasCoord(Point point) {
        return blocks[0].coordinate.equals(point);
    }

    @Override
    public boolean isOpened() {
        return true;
    }

    @Override
    public void close() {
        isVisible = false;
    }

    @Override
    public ArrayList<Point> getCoordsAround() {
        ArrayList<Point> list = new ArrayList<>();
        list.add(blocks[0].coordinate);
        return list;
    }

    @Override
    public Painter getPainter() {
        return painter;
    }

    @Override
    public int minX() {
        return blocks[0].coordinate.x;
    }

    @Override
    public int minY() {
        return blocks[0].coordinate.y;
    }

    @Override
    public int maxX() {
        return blocks[0].coordinate.x;
    }

    @Override
    public int maxY() {
        return blocks[0].coordinate.y;
    }
}
