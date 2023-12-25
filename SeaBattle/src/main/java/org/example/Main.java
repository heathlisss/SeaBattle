package org.example;

import org.example.gamerules.GameMaker;
import org.example.gamerules.GameRules;
import org.example.gui.MainWindow;
import org.example.gui.PlayerCountInputDialogWithSpinner;
import org.example.gui.WindowForPlacingShips;
import org.example.utils.Config;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        GameMaker maker = new GameMaker();
        MainWindow window = new MainWindow(/*maker.GAMERULES*/);
        window.setVisible(true);
    }
}