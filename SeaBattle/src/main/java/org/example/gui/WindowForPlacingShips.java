package org.example.gui;

import javax.swing.*;
import java.awt.*;
import javax.swing.border.LineBorder;


public class WindowForPlacingShips {
    public static String showShipLocationDialog(JFrame parentFrame, int numberPlayer) {
        JDialog dialog = new JDialog(parentFrame, "расстановка кораблей", true);
        dialog.setSize(600, 700);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setBackground(Color.BLACK);
        JLabel nameLabel = new JLabel("Имя игрока:");
        nameLabel.setForeground(new Color(0x9F9FA0));
        JTextField playerNameField = new JTextField("player " + numberPlayer, 20);
        playerNameField.setBorder(new LineBorder(new Color(0x202020)));
        playerNameField.setForeground(new Color(0xDEDEDE));
        playerNameField.setBackground(new Color(0x202020));
        inputPanel.add(nameLabel);
        inputPanel.add(playerNameField);

        JPanel drawingPanel = new DrawPanel(0, 0, dialog.getWidth(), dialog.getWidth());

        dialog.add(inputPanel, BorderLayout.NORTH);
        dialog.add(drawingPanel, BorderLayout.CENTER);

        JButton okButton = new JButton("ОК");
        okButton.addActionListener(e -> dialog.dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.BLACK);
        okButton.setBackground(Color.BLACK);
        okButton.setForeground(new Color(0x9F9FA0));
        buttonPanel.add(okButton);

        dialog.add(buttonPanel, BorderLayout.SOUTH);

        // Устанавливаем расположение диалогового окна относительно родительского фрейма
        dialog.setLocationRelativeTo(parentFrame);
        dialog.setVisible(true);

        if (playerNameField.getText() != null) {
            return playerNameField.getText();
        } else {
            return "player " + numberPlayer;
        }
    }

//        SwingUtilities.invokeLater(() -> {
//            JFrame frame = new JFrame();
//            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            frame.setSize(400, 300);
//
//            // Вызываем диалоговое окно при запуске программы
//            String playerName = showShipLocationDialog(frame, 2);
//
//                System.out.println("Введенное имя игрока: " + playerName);
//
//            // Завершаем приложение при закрытии диалогового окна
//            System.exit(0);
//        });

}