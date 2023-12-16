package org.example.entity;

import org.example.utils.Point;

import java.awt.*;

public class PointBlock {
    public final Point COORDINATE;
    private boolean isOpened;
    private Immovable host;

    public PointBlock(Point coordinate) {
        COORDINATE = coordinate;
        isOpened = false;
    }

    public boolean isOpened() {
        return isOpened;
    }

    public void open() {
        isOpened = true;
    }

    public Immovable getHost() {
        return host;
    }

    public void setHost(Immovable host) {
        this.host = host;
    }

    public boolean hasHost() {
        return this.host != null;
    }

    public void draw(Graphics2D g2d, Integer h, Integer w){

    }
}
