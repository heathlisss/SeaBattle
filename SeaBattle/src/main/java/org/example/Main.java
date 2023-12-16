package org.example;

import org.example.gamerules.GameRules;
import org.example.gui.MainWindow;

public class Main {
    public static void main(String[] args) {
        GameRules rules = new GameRules();
        MainWindow window = new MainWindow(rules);
        window.setVisible(true);
    }
}