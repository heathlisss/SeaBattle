package org.example;

import org.example.gamerules.GameMaker;
import org.example.gamerules.GameRules;
import org.example.gui.MainWindow;

public class Main {
    public static void main(String[] args) {
        GameMaker maker = new GameMaker();
        MainWindow window = new MainWindow(/*maker.GAMERULES*/);
        window.setVisible(true);
    }
}