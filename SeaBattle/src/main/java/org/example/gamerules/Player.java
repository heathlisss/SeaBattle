package org.example.gamerules;

import org.example.entity.Immovable;
import org.example.entity.PointBlock;
import org.example.utils.Point;

import java.util.Arrays;
public class Player {
    public final PointBlock[][] table;
    public final Immovable[] entities;
    private final String name;
    //private PointBlock[][] matrix;
    public Player(PointBlock[][] table, Immovable[] entities, String name) {
        this.table = table;
        this.entities = entities;
        this.name = name;
        //fillArray();
    }
    public String getName(){
        return name;
    }
    public void action(Point point) {
//        table.get(point).open();
//        if (!table.get(point).hasHost()) {
//            return;
//        }
//        Immovable entity = table.get(point).getHost();
//        entity.action(point);
//        if (entity.isOpened() && !entity.canBeSurrounded()) {
//            getBlocks(entity.getCordsAround()).forEach(PointBlock::open);
//        }
    }

    public PointBlock[][] getBlocks(Point[] coordinates) {
       return table;
    }

    public boolean isLost() {
        return Arrays.stream(entities).allMatch(Immovable::isOpened);
    }

    public PointBlock getBlock(Point point) {
        return table[point.y][point.x];
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
