package org.example.gamerules;

import org.example.entity.Immovable;
import org.example.entity.PointBlock;
import org.example.utils.Point;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Player {
    public PointBlock[][] table;
    public List<Immovable> entities = new ArrayList<>();
    private String name;
    public Player() {
        //fillArray();
    }
    public Player(PointBlock[][] table, List<Immovable> entities, String name) {
        this.table = table;
        this.entities = entities;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void action(Point point) {
        getBlock(point).open();
        if (!getBlock(point).hasHost()) {
            return;
        }
        Immovable entity = getBlock(point).getHost();
        entity.action(point);
        if (entity.isOpened() && !entity.canBeSurrounded()) {
            getBlocks(entity.getCoordsAround()).forEach(PointBlock::open);
        }
    }

    public List<PointBlock> getBlocks(Point[] coordinates) {
        List<PointBlock> blocks = new ArrayList<>();
        for (Point point : coordinates) {
            blocks.add(getBlock(point));
        }
        return blocks;
    }
    public boolean isLost() {
        return entities.stream().allMatch(Immovable::isOpened);
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

    public PointBlock[][] getTable() {
        return table;
    }

    public void setTable(PointBlock[][] table) {
        this.table = table;
    }

    public List<Immovable> getEntities() {
        return entities;
    }

    public void setEntities(List<Immovable> entities) {
        this.entities = entities;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEntity(Immovable entity) {
        entities.add(entity);
    }
}
