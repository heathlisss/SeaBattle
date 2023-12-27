package org.example.gamerules;

import org.example.utils.Config;

public class GameRules {
    private int activePlayerIndex;
    public final Player[] players;

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
}