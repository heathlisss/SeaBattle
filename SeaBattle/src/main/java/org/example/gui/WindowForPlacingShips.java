package org.example.gui;

import org.example.entity.Immovable;
import org.example.entity.PointBlock;
import org.example.gamerules.Player;
import org.example.gamerules.GameMaker;
import org.example.graphics.ShipGraphics;
import org.example.utils.Config;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import javax.swing.border.LineBorder;


public class WindowForPlacingShips {
    public static String showShipLocationDialog(JFrame parentFrame, int numberPlayer, Immovable[] entities, PointBlock[][] table) {
        JDialog dialog = new JDialog(parentFrame, "расстановка кораблей", true);
        dialog.setSize(600, 700);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setBackground(Config.BACKGROUND_COLOR);
        JLabel nameLabel = new JLabel("Имя игрока:");
        nameLabel.setForeground(new Color(0x9F9FA0));
        JTextField playerNameField = new JTextField("player " + (numberPlayer + 1), 20);
        playerNameField.setBorder(new LineBorder(new Color(0x202020)));
        playerNameField.setForeground(new Color(0xDEDEDE));
        playerNameField.setBackground(new Color(0x202020));
        inputPanel.add(nameLabel);
        inputPanel.add(playerNameField);

        JPanel drawingPanel = new DrawPanel(0, 0, dialog.getWidth(), dialog.getWidth(), table);
        for (Immovable immovable : entities) {
            ShipGraphics.draw();
        }

        dialog.add(inputPanel, BorderLayout.NORTH);
        dialog.add(drawingPanel, BorderLayout.CENTER);

        JButton okButton = new JButton("ОК");
        okButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Config.BACKGROUND_COLOR);
        okButton.setBackground(Config.BACKGROUND_COLOR);
        okButton.setForeground(new Color(0x9F9FA0));
        buttonPanel.add(okButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);

        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);

        if (playerNameField.getText() != null) {
            return playerNameField.getText();
        } else {
            return "player " + numberPlayer;
        }
    }

}