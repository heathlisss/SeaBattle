package org.example.gamerules;

import org.example.entity.Immovable;
import org.example.entity.PointBlock;
import org.example.gui.PlayerCountInputDialogWithSpinner;
import org.example.gui.WindowForPlacingShips;
import org.example.utils.Config;
import org.example.utils.CreateArrayImmovable;
import org.example.utils.Point;

import javax.swing.*;

public class GameMaker {
    public final GameRules gameRules;
    public static Player[] players;

    public GameMaker() {
        gameRules = new GameRules(makePlayers());
    }

    private Player[] makePlayers() {
        PlayerCountInputDialogWithSpinner.openPlayerCountDialog();

        players = new Player[Config.PLAYERS_COUNT];
        for (int numberPlayer = 0; numberPlayer < players.length; numberPlayer++) {

            PointBlock[][] table = new PointBlock[Config.MAX_CORD + 1][Config.MAX_CORD + 1];
            for (int x = Config.MIN_CORD; x <= Config.MAX_CORD; x++) {
                for (int y = Config.MIN_CORD; y <= Config.MAX_CORD; y++) {
                    Point point = new Point(x, y);
                    PointBlock block = new PointBlock(point);
                    table[y][x] = block;
                }
            }
            System.out.println();
            Immovable[] entities = CreateArrayImmovable.locationDefault(table);
            String playerName = WindowForPlacingShips.showShipLocationDialog(new JFrame(), numberPlayer,  table , entities);

            players[numberPlayer] = new Player(table, entities, playerName);
        }
        return players;
    }
}
