package org.example.gamerules;

import org.example.entity.Immovable;
import org.example.entity.PointBlock;
import org.example.utils.Config;
import org.example.utils.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Player {
    private final Map<Point, PointBlock> TABLE;
    private final Immovable[] ENTITIES;
    //private PointBlock[][] matrix;

    public Player(Map<Point, PointBlock> table, Immovable[] entities) {
        TABLE = table;
        ENTITIES = entities;
        //fillArray();
    }

    public void action(Point point) {
        TABLE.get(point).open();
        if (!TABLE.get(point).hasHost()) {
            return;
        }
        Immovable entity = TABLE.get(point).getHost();
        entity.action(point);
        if (entity.isOpened() && !entity.canBeSurrounded()) {
            getBlocks(entity.getCordsAround()).forEach(PointBlock::open);
        }
    }

    public ArrayList<PointBlock> getBlocks(Point[] coordinates) {
        ArrayList<PointBlock> blocks = new ArrayList<>();
        Arrays.stream(coordinates).forEach(point -> {
            blocks.add(TABLE.get(point));
        });
        return blocks;
    }

    public boolean isLost() {
        return Arrays.stream(ENTITIES).allMatch(Immovable::isOpened);
    }
    public PointBlock getBlock(Point point) {
        return TABLE.get(point);
    }


//    public void fillArray() {
//        matrix = new PointBlock[Config.MAX_CORD + 1][Config.MAX_CORD + 1];
//        for (int y = 0; y < matrix.length; y++) {
//            for (int x = 0; x < matrix[0].length; x++) {
//                matrix[y][x] = TABLE.get(new Point(x, y));
//            }
//        }
//    }
//
//    public PointBlock[][] getMatrix() {
//        return matrix;
//    }
}
