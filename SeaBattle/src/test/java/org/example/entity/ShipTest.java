package org.example.entity;

import org.example.utils.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShipTest {
    @Test
    void testCoordinate() {
        Ship ship = new Ship(new PointBlock[]{
                new PointBlock(new Point(0, 0))
        });
        assertEquals(0, ship.maxX());
        assertEquals(0, ship.maxY());
        assertEquals(0, ship.minX());
        assertEquals(0, ship.minY());

        assertFalse(ship.hasCoord(new Point(0, 1)));
        assertTrue(ship.hasCoord(new Point(0, 0)));

        Ship ship1 = new Ship(new PointBlock[]{
                new PointBlock(new Point(0, 0)),
                new PointBlock(new Point(1, 0)),
                new PointBlock(new Point(2, 0)),
                new PointBlock(new Point(3, 0))
        });
        assertEquals(3, ship1.maxX());
        assertEquals(0, ship1.maxY());
        assertEquals(0, ship1.minX());
        assertEquals(0, ship1.minY());

        assertFalse(ship1.hasCoord(new Point(0, 1)));
        assertTrue(ship1.hasCoord(new Point(0, 0)));
        assertTrue(ship1.hasCoord(new Point(3, 0)));
    }

}