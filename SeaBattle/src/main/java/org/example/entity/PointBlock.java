package org.example.entity;

import org.example.utils.Config;
import org.example.utils.Point;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PointBlock {
    public final Point coordinate;
    public  Rectangle2D rectangle;
    private boolean isOpened;
    private Immovable host;

    public PointBlock(Point coordinate) {
        this.coordinate = coordinate;
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
}
