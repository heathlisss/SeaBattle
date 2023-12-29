package org.example.entity;

import org.example.gamerules.Player;
import org.example.utils.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class PlayerTest {
    @Test
    void testAction() {
        Player player1 = createPlayer(createTable(3));
        Player player2 = createPlayer(createTable(3));

        player1.action(new Point(0, 1), player2);
        player1.action(new Point(0, 2), player2);
        assertTrue(player1.getBlock(new Point(0, 2)).getHost().isOpened());

        player1.action(new Point(2, 0), player2);
        assertTrue(player1.getBlock(new Point(2, 0)).isOpened());

        player1.action(new Point(2, 2), player2);
        assertTrue(player1.getBlock(new Point(2, 2)).isOpened());
        assertTrue(player1.getBlock(new Point(2, 2)).hasHost());
        assertTrue(player2.getBlock(new Point(2, 2)).isOpened());

        assertTrue(player1.isLost());
    }

    public static PointBlock[][] createTable(int n) {
        PointBlock[][] table = new PointBlock[n+1][n+1];
        for (int x = 0; x <= n; x++) {
            for (int y = 0; y <= n; y++) {
                Point point = new Point(x, y);
                PointBlock block = new PointBlock(point);
                table[y][x] = block;
            }
        }
        return table;
    }

    public static Player createPlayer(PointBlock[][] table) {
        Ship ship1 = new Ship(new PointBlock[]{
                table[1][0],
                table[2][0]
        });
        Mine mine = new Mine(new PointBlock[]{table[2][2]});

        ArrayList<Immovable> list = new ArrayList<>();
        list.add(ship1);
        list.add(mine);

        for(Immovable immovable: list){
            for (PointBlock block : immovable.getCoords()) {
                block.setHost(immovable);
            }
            immovable.close();
        }
        Player player = new Player(
                table,
                list);
        return player;
    }
}
