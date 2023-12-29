package org.example.gamerules;

import org.example.entity.PointBlock;
import org.example.gui.DrawPanel;
import org.example.gui.dropDownWindows.Message;
import org.example.utils.Config;
import org.example.utils.Point;

public class GameRules {
    private int activePlayerIndex; // тот, на кого нападают.
    private final Player[] players;
    private DrawPanel[] panels;
    private boolean isStarted;

    public GameRules(Player[] players) {
        this.players = players;
        activePlayerIndex = 0;
        isStarted = true;
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
        panels[(activePlayerIndex + 1) % Config.PLAYERS_COUNT].setActivePlayer(players[activePlayerIndex]);
        panels[activePlayerIndex].setActivePlayer(players[activePlayerIndex]);
        panels[activePlayerIndex].repaint();
    }

    public Player getPlayer(int index) {
        return players[index];
    }

    public void nextTurn(Point point) {
        PointBlock block = getActivePlayer().getBlock(point);
        if (!block.isOpened()) {
            getActivePlayer().action(point, getAttacker());
            for (DrawPanel panel : panels) {
                panel.repaint();
            }
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

    public void setPanels(DrawPanel[] panels) {
        this.panels = panels;
    }

    public DrawPanel getActivePanel() {
        return panels[activePlayerIndex];
    }

    public Player getAttacker() {
        return getPlayer((activePlayerIndex + 1) % Config.PLAYERS_COUNT);
    }
}