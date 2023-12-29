package org.example.gamerules;

import org.example.entity.Immovable;
import org.example.entity.PointBlock;
import org.example.utils.Point;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private PointBlock[][] table;
    private List<Immovable> entities = new ArrayList<>();
    private String name;

    public Player() {}

    public Player(PointBlock[][] table, List<Immovable> entities) {
        this.table = table;
        this.entities = entities;
    }

    public String getName() {
        return name;
    }

    public void action(Point point, Player attacker) {
        getBlock(point).open();
        if (!getBlock(point).hasHost()) {
            return;
        }
        Immovable entity = getBlock(point).getHost();
        entity.action(point, attacker);
        if (entity.isOpened() && !entity.canBeSurrounded()) {
            getBlocks(entity.getCoordsAround()).forEach(PointBlock::open);
        }
    }

    public List<PointBlock> getBlocks(ArrayList<Point> coordinates) {
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

    public PointBlock[][] getTable() {
        return table;
    }

    public void setTable(PointBlock[][] table) {
        this.table = table;
    }

    public List<Immovable> getEntities() {
        return entities;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addEntity(Immovable entity) {
        entities.add(entity);
    }
}