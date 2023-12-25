package org.example.gamerules;

import org.example.entity.Immovable;
import org.example.entity.PointBlock;
import org.example.gui.PlayerCountInputDialogWithSpinner;
import org.example.gui.WindowForPlacingShips;
import org.example.utils.Config;
import org.example.utils.Point;

import javax.swing.*;
import java.util.HashMap;
import java.util.Map;

public class GameMaker {
    public final GameRules gameRules;
    private Player[] players;

    public GameMaker() {
        gameRules = new GameRules(makePlayers());
    }

    private Player[] makePlayers() {
        PlayerCountInputDialogWithSpinner.openPlayerCountDialog();

        players = new Player[Config.PLAYERS_COUNT];
        for (int numberPlayer = 0; numberPlayer < players.length; numberPlayer++) {
            String playerName = WindowForPlacingShips.showShipLocationDialog(new JFrame(), numberPlayer + 1);


            Immovable[] entities = new Immovable[Config.ENTITY_COUNT];
            Map<Point, PointBlock> table = new HashMap<>();
            for (int x = Config.MIN_CORD; x <= Config.MAX_CORD; x++) {
                for (int y = Config.MIN_CORD; y <= Config.MAX_CORD; y++) {
                    Point point = new Point(x, y);
//                    PointBlock block = new PointBlock(point, rectangle);
//                    table.put(point, block);
                }
            }
            players[numberPlayer] = new Player(table, entities, playerName);
        }
        return players;
    }
}
