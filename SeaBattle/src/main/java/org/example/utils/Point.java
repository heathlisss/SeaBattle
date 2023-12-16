package org.example.utils;

import java.util.Objects;

public class Point {
    public final Integer X;
    public final Integer Y;

    public Point(Integer x, Integer y) {
        X = x;
        Y = y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point that = (Point) o;
        if (!Objects.equals(X, that.X)) return false;
        return Objects.equals(Y, that.Y);
    }

    @Override
    public int hashCode() {
        int code = 17;
        code = 31 * code + X;
        code = 31 * code + Y;
        return code;
    }

}
