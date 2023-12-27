package org.example.utils;

import java.util.Objects;

public class Point {
    public final Integer x;
    public final Integer y;

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point that = (Point) o;
        if (!Objects.equals(x, that.x)) return false;
        return Objects.equals(y, that.y);
    }

    @Override
    public int hashCode() {
        int code = 17;
        code = 31 * code + x;
        code = 31 * code + y;
        return code;
    }

}
