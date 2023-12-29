package org.example.gamerules;

import org.example.entity.Immovable;
import org.example.entity.PointBlock;
import org.example.entity.Ship;
import org.example.gui.PlayerWindow;
import org.example.utils.Config;
import org.example.utils.Point;

public class GameMaker {
    public GameMaker() {}

    private Player[] makePlayers() {
        //PlayerCountInputDialog.openPlayerCountDialog();
        Player[] players = new Player[Config.PLAYERS_COUNT];
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
            player.setName("player " + (numberPlayer + 1));

            PlayerWindow windowForPlacingShips = new PlayerWindow(player);
            // Цикл проверки открытости JFrame
            while (windowForPlacingShips.isVisible()) {
                try {
                    Thread.sleep(100); // Пауза для уменьшения нагрузки на процессор
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            players[numberPlayer] = player;
        }
        return players;
    }

    public GameRules newGame(){
        return new GameRules(makePlayers());
    }
}