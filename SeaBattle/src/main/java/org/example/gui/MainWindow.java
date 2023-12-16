package org.example.gui;

import org.example.gamerules.GameRules;
import org.example.gamerules.Player;
import org.example.utils.Config;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;

    public MainWindow(GameRules gameRules) {
        setTitle("SeaBattle");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(1, Config.PLAYERS_COUNT));

        for (int index = 0; index < Config.PLAYERS_COUNT; index++) {
            JTable display = new SeaBattleTable(gameRules, index);
            panel.add(display);
        }

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
