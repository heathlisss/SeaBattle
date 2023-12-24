package org.example.entity;

import org.example.utils.Point;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public interface Immovable {
    /**
     * @return коордианты`
     */
    public PointBlock[] getCords();

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
    public boolean hasCord(Point point);

    /**
     * прверяет состояние
     */
    public boolean isOpened();

    /**
     * @return координыты окружения`
     */
    public Point[] getCordsAround();

}
