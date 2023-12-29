package org.example.gamerules;

import org.example.entity.PlayerTest;
import org.example.gui.DrawPanel;
import org.example.utils.Point;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameRulesTest {
    @Test
    void changePlayer() {
        GameRules gameRules = new GameRules(new Player[]{
                PlayerTest.createPlayer(PlayerTest.createTable(3)),
                PlayerTest.createPlayer(PlayerTest.createTable(3))
        });

        gameRules.nextTurn(new Point(0, 0));
        assertEquals(1, gameRules.getActivePlayerIndex());
        gameRules.nextTurn(new Point(0,1 ));
        assertEquals(1, gameRules.getActivePlayerIndex());

    }

}