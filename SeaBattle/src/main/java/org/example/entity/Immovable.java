package org.example.entity;

import org.example.graphics.Painter;
import org.example.utils.Point;

import java.util.ArrayList;

public interface Immovable {
    /**
     * @return коордианты`
     */
    PointBlock[] getCoords();

    /**
     * <h3> действие </h3>
     *
     * @param point вводимая координата
     */
    void action(Point point);

    /**
     * @return можно ли ставить рядом  оъекты
     */
    boolean canBeSurrounded();

    /**
     * проверяет наличие координаты
     */
    boolean hasCoord(Point point);

    /**
     * прверяет состояние
     */
    boolean isOpened();

    void close();

    /**
     * @return координыты окружения`
     */
    ArrayList<Point> getCoordsAround();

    /**
     * Возвращает объект, отвечающий за рисование класса
     *
     * @return
     */
    Painter getPainter();

    int minX();

    int minY();

    int maxX();

    int maxY();
}
