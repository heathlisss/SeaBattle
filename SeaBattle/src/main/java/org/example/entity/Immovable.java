package org.example.entity;

import org.example.graphics.Painter;
import org.example.utils.Point;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public interface Immovable {
    /**
     * @return коордианты`
     */
    public PointBlock[] getCoords();
    public void setBlocks(PointBlock[] blocks);

    /**
     * <h3> действие </h3>
     *
     * @param point вводимая координата
     */
    public void action(Point point);

    /**
     * @return можно ли ставить рядом  оъекты
     */
    public boolean canBeSurrounded();

    /**
     * проверяет наличие координаты
     */
    public boolean hasCoord(Point point);

    /**
     * прверяет состояние
     */
    public boolean isOpened();
    public void close();

    /**
     * @return координыты окружения`
     */
    public ArrayList<Point> getCoordsAround();
    /**
     * Возвращает объект, отвечающий за рисование класса
     * @return
     */
    public Painter getPainter();
    public int minX();
    public int minY();

    public int maxX();

    public int maxY();
}
