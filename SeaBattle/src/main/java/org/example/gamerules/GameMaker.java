package org.example.gamerules;

import org.example.entity.Immovable;
import org.example.entity.PointBlock;
import org.example.entity.Ship;
import org.example.gui.PlayerWindow;
import org.example.utils.Config;
import org.example.utils.Point;

public class GameMaker {
    public final GameRules gameRules;
    public static Player[] players;

    public GameMaker() {
        gameRules = new GameRules(makePlayers());
    }

    private Player[] makePlayers() {
        //PlayerCountInputDialog.openPlayerCountDialog();

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
            Player player = new Player();

            player.setTable(table);
            player.setName(String.valueOf("player "+(numberPlayer+1)));

            PlayerWindow windowForPlacingShips = new PlayerWindow(player);
            // Цикл проверки открытости JFrame
            while (windowForPlacingShips.isVisible()) {
                try {
                    Thread.sleep(100); // Пауза для уменьшения нагрузки на процессор
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
           // String playerName = WindowForPlacingShips.showShipLocationDialog(new JFrame(), numberPlayer,  table , entities);

            players[numberPlayer] = player;
        }
        return players;
    }
    private static Immovable[] locationDefault(PointBlock[][] table) {
        Immovable[] entities = new Immovable[Config.ENTITY_COUNT];
        entities[0] = new Ship(new PointBlock[]{table[0][0], table[1][0], table[2][0], table[3][0]});
        entities[1] = new Ship(new PointBlock[]{table[0][9], table[1][9], table[2][9]});
        entities[2] = new Ship(new PointBlock[]{table[7][9], table[8][9], table[9][9]});
        entities[3] = new Ship(new PointBlock[]{table[0][3], table[1][3]});
        entities[4] = new Ship(new PointBlock[]{table[0][5], table[1][5]});
        entities[5] = new Ship(new PointBlock[]{table[0][7], table[1][7]});
        entities[6] = new Ship(new PointBlock[]{table[4][3]});
        entities[7] = new Ship(new PointBlock[]{table[7][2]});
        entities[8] = new Ship(new PointBlock[]{table[8][5]});
        entities[9] = new Ship(new PointBlock[]{table[5][7]});

        table[0][0].setHost(entities[0]); table[1][0].setHost(entities[0]); table[2][0].setHost(entities[0]); table[3][0].setHost(entities[0]);
        table[0][9].setHost(entities[1]); table[1][9].setHost(entities[1]); table[2][9].setHost(entities[1]);
        table[7][9].setHost(entities[2]); table[8][9].setHost(entities[2]); table[9][9].setHost(entities[2]);
        table[0][3].setHost(entities[3]); table[1][3].setHost(entities[3]);
        table[0][5].setHost(entities[4]); table[1][5].setHost(entities[4]);
        table[0][7].setHost(entities[5]); table[1][7].setHost(entities[5]);
        table[4][3].setHost(entities[6]);
        table[7][2].setHost(entities[7]);
        table[8][5].setHost(entities[8]);
        table[5][7].setHost(entities[9]);


        return entities;
    }

}

