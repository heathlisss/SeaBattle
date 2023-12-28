package org.example.gamerules;

import org.example.entity.PointBlock;
import org.example.gui.DrawPanel;
import org.example.utils.Config;
import org.example.utils.Point;

public class GameRules {
    private int activePlayerIndex; // тот, на кого нападают.
    public final Player[] players;
    public DrawPanel[] panels;

    public GameRules(Player[] players) {
        this.players = players;
        activePlayerIndex = 0;
    }

    public Player getActivePlayer() {
        return players[activePlayerIndex];
    }

    public void nextPlayer() {
        activePlayerIndex++;
        activePlayerIndex %= Config.PLAYERS_COUNT;
    }

    public Player getPlayer(int index) {
        return players[index];
    }

    public void nextTurn(Point point) {
        PointBlock block = players[activePlayerIndex].getBlock(point);
        players[activePlayerIndex].action(point);

        //todo: проверить там закрытые открытые клетки чета сделать учи правила морского боя короче
        if (!block.hasHost()) {
            nextPlayer();

            //todo: прочекать кончилаьс ли игра, вдруг кто-то проиграл.
            return;
        } else {

        }
    }
}