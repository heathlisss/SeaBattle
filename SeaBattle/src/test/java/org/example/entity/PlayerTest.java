package org.example.entity;

import org.example.gamerules.Player;
import org.example.utils.Config;
import org.example.utils.Point;
import org.junit.jupiter.api.Test;

public class PlayerTest {
//    @Test
//    void test() {
//        PointBlock[][] po
//        Player player = new Player(
//                createTable(3),
//
//        )
//    }

    public static PointBlock[][] createTable(int n) {
        PointBlock[][] table = new PointBlock[n][n];
        for (int x = 0; x < n; x++) {
            for (int y = 0; y < n; y++) {
                Point point = new Point(x, y);
                PointBlock block = new PointBlock(point);
                table[y][x] = block;
            }
        }
        return table;
    }
}
