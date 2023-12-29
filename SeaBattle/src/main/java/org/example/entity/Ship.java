package org.example.entity;

import org.example.graphics.Painter;
import org.example.graphics.ShipGraphics;
import org.example.utils.Config;
import org.example.utils.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Optional;

public class Ship implements Immovable {
    private PointBlock[] blocks;
    private Painter painter;
    private boolean isOpen;

    public Ship(PointBlock[] coordinates) {
        blocks = coordinates;
        painter = new ShipGraphics(this);
        isOpen = true;
    }

    public void setBlocks(PointBlock[] blocks) {
        this.blocks = blocks;
    }

    @Override
    public PointBlock[] getCoords() {
        return blocks;
    }

    @Override
    public void action(Point point) {
        Optional<PointBlock> localPoint = Arrays.stream(blocks)
                .filter(pointBlock -> pointBlock.coordinate.equals(point))
                .findFirst();
        //localPoint.ifPresent(PointBlock::open);
        isOpen = Arrays.stream(blocks).allMatch(PointBlock::isOpened);
    }

    @Override
    public boolean canBeSurrounded() {
        return false;
    }

    @Override
    public boolean hasCoord(Point point) {
        return Arrays.stream(blocks).anyMatch(pointBlock -> pointBlock.coordinate.equals(point));
    }

    @Override
    public boolean isOpened() {
        return isOpen;
    }

    @Override
    public void close() {
        isOpen = false;
    }

    @Override
    public ArrayList<Point> getCoordsAround() {
        ArrayList<Point> points = new ArrayList<>();
        int minX = minX();
        int minY = minY();
        int maxX = maxX();
        int maxY = maxY();

        for (int xInd = minX - 1; xInd <= maxX + 1; xInd++) {
            if (xInd >= Config.MIN_CORD && xInd <= Config.MAX_CORD) {
                for (int yInd = minY - 1; yInd <= maxY + 1; yInd++) {
                    if (yInd >= Config.MIN_CORD && yInd <= Config.MAX_CORD) {
                        Point p = new Point(xInd, yInd);
                        if (!hasCoord(p))
                            points.add(p);
                    }
                }
            }
        }
        return points;
    }

    @Override
    public Painter getPainter() {
        return painter;
    }

    @Override
    public int minX() {
        int minX = blocks[0].coordinate.x;
        for (PointBlock pointBlock : blocks) {
            minX = Math.min(minX, pointBlock.coordinate.x);
        }
        return minX;
    }

    @Override
    public int minY() {
        int minY = blocks[0].coordinate.y;
        for (PointBlock pointBlock : blocks) {
            minY = Math.min(minY, pointBlock.coordinate.y);
        }
        return minY;
    }

    @Override
    public int maxX() {
        int maxX = blocks[0].coordinate.x;
        for (PointBlock pointBlock : blocks) {
            maxX = Math.max(maxX, pointBlock.coordinate.x);
        }
        return maxX;
    }

    @Override
    public int maxY() {
        int maxY = blocks[0].coordinate.y;
        for (PointBlock pointBlock : blocks) {
            maxY = Math.max(maxY, pointBlock.coordinate.y);
        }
        return maxY;
    }
}
