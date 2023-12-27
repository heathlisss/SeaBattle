package org.example.entity;

import org.example.utils.Config;
import org.example.utils.Point;

import java.util.ArrayList;
import java.util.Arrays;

public class Ship implements Immovable {
    private final PointBlock[] BLOCKS;

    public Ship(PointBlock[] coordinates) {
        BLOCKS = coordinates;
    }

    @Override
    public PointBlock[] getCords() {
        return BLOCKS;
    }

    @Override
    public void action(Point point) {
//        Optional<PointBlock> localPoint = Arrays.stream(BLOCKS).filter(pointBlock -> pointBlock.COORDINATE.equals(point)).findFirst();
//        localPoint.ifPresent(PointBlock::open);
    }

    @Override
    public boolean canBeSurrounded() {
         return false;
    }

    @Override
    public boolean hasCord(Point point) {
        return Arrays.stream(BLOCKS).anyMatch(pointBlock -> pointBlock.coordinate.equals(point));
    }

    @Override
    public boolean isOpened() {
        return Arrays.stream(BLOCKS).allMatch(PointBlock::isOpened);
    }

    @Override
    public Point[] getCordsAround() {
        ArrayList<Point> points = new ArrayList<>();
        int minX = BLOCKS[0].coordinate.x - 1;
        int minY = BLOCKS[0].coordinate.y - 1;
        int maxX = BLOCKS[BLOCKS.length - 1].coordinate.x + 1;
        int maxY = BLOCKS[BLOCKS.length - 1].coordinate.y + 1;

        for (int xInd = minX; xInd < maxX; xInd++) {
            if (xInd >= Config.MIN_CORD && xInd <= Config.MAX_CORD) {
                for (int yInd = minY; yInd < maxY; yInd++) {
                    if (yInd >= Config.MIN_CORD && yInd <= Config.MAX_CORD) {
                        Point p = new Point(xInd, yInd);
                        if (!hasCord(p))
                            points.add(p);
                    }
                }
            }
        }
        return (Point[]) points.toArray();
    }
}
