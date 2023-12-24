package org.example.entity;

import org.example.utils.Config;
import org.example.utils.Point;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class PointBlock {
    public final Point coordinate;
    public final Rectangle2D rectangle;
    private boolean isOpened;
    private Immovable host;

    public PointBlock(Point coordinate, Rectangle2D rectangle) {
        this.coordinate = coordinate;
        this.rectangle = rectangle;
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
