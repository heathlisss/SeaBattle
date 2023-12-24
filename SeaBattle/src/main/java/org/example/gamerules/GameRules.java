package org.example.gamerules;

import org.example.utils.Config;

public class GameRules {
    private int activePlayerIndex;
    private final Player[] PLAYERS;

    public GameRules(Player[] players) {
        PLAYERS = players;
        activePlayerIndex = 0;
    }

    public Player getActivePlayer() {
        return PLAYERS[activePlayerIndex];
    }

    public void nextPlayer() {
        activePlayerIndex++;
        activePlayerIndex %= Config.PLAYERS_COUNT;
    }

    public Player getPlayer(int index) {
        return PLAYERS[index];
    }
}