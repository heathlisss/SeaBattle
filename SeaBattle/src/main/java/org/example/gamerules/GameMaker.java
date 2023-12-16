package org.example.gamerules;

import org.example.utils.Config;

public class GameMaker {
    GameRules gameRules;
    private Player[] players;
    public GameMaker(){
        players = new Player[Config.PLAYERS_COUNT];
    }
}
