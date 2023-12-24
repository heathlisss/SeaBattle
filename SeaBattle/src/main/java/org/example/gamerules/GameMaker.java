package org.example.gamerules;

import org.example.entity.Immovable;
import org.example.entity.PointBlock;
import org.example.utils.Config;
import org.example.utils.Point;

import java.util.HashMap;
import java.util.Map;

public class GameMaker {
    public final GameRules GAMERULES;
    private Player[] players;
    public GameMaker(){
        GAMERULES = new GameRules(makePlayers());
    }

    private Player[] makePlayers() {
        players = new Player[Config.PLAYERS_COUNT];
        for (int i = 0; i < players.length; i++) {
            Immovable[] entities = new Immovable[Config.ENTITY_COUNT];
            Map<Point, PointBlock> table = new HashMap<>();
            for (int x = Config.MIN_CORD; x <= Config.MAX_CORD; x++) {
                for (int y = Config.MIN_CORD; y <= Config.MAX_CORD; y++) {
                    Point point = new Point(x, y);
//                    PointBlock block = new PointBlock(point, rectangle);
//                    table.put(point, block);
                }
            }
            players[i] = new Player(table, entities);
        }
        return players;
    }
}
