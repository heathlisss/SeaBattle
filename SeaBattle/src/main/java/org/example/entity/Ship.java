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

    @Override
    public PointBlock[] getCoords() {
        return blocks;
    }

    @Override
    public void action(Point point) {
        Optional<PointBlock> localPoint = Arrays.stream(blocks)
                .filter(pointBlock -> pointBlock.coordinate.equals(point))
                .findFirst();
        localPoint.ifPresent(PointBlock::open);
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
    public Point[] getCoordsAround() {
        ArrayList<Point> points = new ArrayList<>();
        int minX = blocks[0].coordinate.x - 1;
        int minY = blocks[0].coordinate.y - 1;
        int maxX = blocks[blocks.length - 1].coordinate.x + 1;
        int maxY = blocks[blocks.length - 1].coordinate.y + 1;

        for (int xInd = minX; xInd < maxX; xInd++) {
            if (xInd >= Config.MIN_CORD && xInd <= Config.MAX_CORD) {
                for (int yInd = minY; yInd < maxY; yInd++) {
                    if (yInd >= Config.MIN_CORD && yInd <= Config.MAX_CORD) {
                        Point p = new Point(xInd, yInd);
                        if (!hasCoord(p))
                            points.add(p);
                    }
                }
            }
        }
        return (Point[]) points.toArray();
    }

    @Override
    public Painter getPainter() {
        return painter;
    }

    @Override
    public int minX() {
        int minX = blocks[0].coordinate.x;
        for (PointBlock pointBlock : blocks) {
            minX = Math.min(pointBlock.coordinate.x, pointBlock.coordinate.x);
        }
        return minX;
    }

    @Override
    public int minY() {
        int minY = blocks[0].coordinate.y;
        for (PointBlock pointBlock : blocks) {
            minY = Math.min(pointBlock.coordinate.y, pointBlock.coordinate.y);
        }
        return minY;
    }

    @Override
    public int maxX() {
        int maxX = blocks[0].coordinate.x;
        for (PointBlock pointBlock : blocks) {
            maxX = Math.max(pointBlock.coordinate.x, pointBlock.coordinate.x);
        }
        return maxX;    }

    @Override
    public int maxY() {
        int maxY = blocks[0].coordinate.y;
        for (PointBlock pointBlock : blocks) {
            maxY = Math.max(pointBlock.coordinate.y, pointBlock.coordinate.y);
        }
        return maxY;    }
}
