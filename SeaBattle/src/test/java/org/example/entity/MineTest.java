package org.example.entity;

import org.example.utils.Point;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MineTest {

    @Test
    void testCoordinate() {
        Mine mine1 = new Mine(new PointBlock[]{
                new PointBlock(new Point(0, 0))
        });
        assertEquals(0, mine1.maxX());
        assertEquals(0, mine1.maxY());
        assertEquals(0, mine1.minX());
        assertEquals(0, mine1.minY());

        assertFalse(mine1.hasCoord(new Point(0,1)));
        assertTrue(mine1.hasCoord(new Point(0,0)));
    }
}