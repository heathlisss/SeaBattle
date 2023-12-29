package org.example.gamerules;

import org.example.entity.PointBlock;
import org.example.gui.DrawPanel;
import org.example.gui.dropDownWindows.Message;
import org.example.utils.Config;
import org.example.utils.Point;

public class GameRules {
    private int activePlayerIndex; // тот, на кого нападают.
    private final Player[] players;
    private boolean isStarted;

    public GameRules(Player[] players) {
        this.players = players;
        activePlayerIndex = 0;
        isStarted = true;
    }

    public int getActivePlayerIndex() {
        return activePlayerIndex;
    }

    public boolean isStarted() {
        return isStarted;
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
        PointBlock block = getActivePlayer().getBlock(point);
        if (!block.isOpened()) {
            getActivePlayer().action(point, getAttacker());

            if (getActivePlayer().isLost()) {
                Message.gameOver(getActivePlayer().getName());
                isStarted = false;
            }
            if (!block.hasHost()) {
                nextPlayer();
                return;
            }
        }
    }


    public Player getAttacker() {
        return getPlayer((activePlayerIndex + 1) % Config.PLAYERS_COUNT);
    }
}