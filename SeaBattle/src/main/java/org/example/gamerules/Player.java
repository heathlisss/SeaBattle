package org.example.gamerules;

import org.example.entity.Immovable;
import org.example.entity.PointBlock;
import org.example.utils.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Player {
    private final Map<Point, PointBlock> table;
    private final Immovable[] entities;
    private final String name;
    //private PointBlock[][] matrix;

    public Player(Map<Point, PointBlock> table, Immovable[] entities, String name) {
        this.table = table;
        this.entities = entities;
        //fillArray();
        this.name = name;
    }

    public void action(Point point) {
        table.get(point).open();
        if (!table.get(point).hasHost()) {
            return;
        }
        Immovable entity = table.get(point).getHost();
        entity.action(point);
        if (entity.isOpened() && !entity.canBeSurrounded()) {
            getBlocks(entity.getCordsAround()).forEach(PointBlock::open);
        }
    }

    public ArrayList<PointBlock> getBlocks(Point[] coordinates) {
        ArrayList<PointBlock> blocks = new ArrayList<>();
        Arrays.stream(coordinates).forEach(point -> {
            blocks.add(table.get(point));
        });
        return blocks;
    }

    public boolean isLost() {
        return Arrays.stream(entities).allMatch(Immovable::isOpened);
    }
    public PointBlock getBlock(Point point) {
        return table.get(point);
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
