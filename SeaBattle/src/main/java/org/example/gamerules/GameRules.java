package org.example.gamerules;

import org.example.entity.PointBlock;
import org.example.gui.DrawPanel;
import org.example.utils.Config;
import org.example.utils.Point;

public class GameRules {
    private int activePlayerIndex; // тот, на кого нападают.
    private final Player[] players;
    private DrawPanel[] panels;

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
        panels[(activePlayerIndex + 1) % Config.PLAYERS_COUNT].setActivePlayer(players[activePlayerIndex]);
        panels[activePlayerIndex].setActivePlayer(players[activePlayerIndex]);
        panels[activePlayerIndex].repaint();
    }

    public Player getPlayer(int index) {
        return players[index];
    }

    public void nextTurn(Point point) {
        PointBlock block = players[activePlayerIndex].getBlock(point);
        if (!players[activePlayerIndex].table[point.y][point.x].isOpened()) {
            players[activePlayerIndex].action(point);
            panels[activePlayerIndex].repaint();
            if (!block.hasHost()) {
                nextPlayer();
                //todo: прочекать кончилаьс ли игра, вдруг кто-то проиграл.
                return;
            }
        }
    }

    public void setActivePlayerIndex(int activePlayerIndex) {
        this.activePlayerIndex = activePlayerIndex;
    }

    public void setPanels(DrawPanel[] panels) {
        this.panels = panels;
    }

}