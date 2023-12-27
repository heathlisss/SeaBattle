package org.example.gui;

import org.example.gamerules.GameRules;
import org.example.utils.Config;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class MainWindow extends JFrame {

    private static final int WIDTH = 1400;
    private static final int HEIGHT = 800;

    public MainWindow(GameRules gameRules) {
        setTitle("SeaBattle");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(1, Config.PLAYERS_COUNT));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.removeAll();
                for (int index = 0; index < Config.PLAYERS_COUNT; index++) {
                    JLabel nameLabel = new JLabel(gameRules.players[index].getName());
                    nameLabel.setForeground(new Color(0x9F9FA0));
                    nameLabel.setFont(new Font("Arial ", Font.PLAIN, 17));
                    JPanel panelSeaDisplay = new DrawPanel(
                            0,
                            0,
                            (int) (getSize().width / Config.PLAYERS_COUNT),
                            (int) (getSize().width / Config.PLAYERS_COUNT), table);
                    panelSeaDisplay.add(nameLabel);
                    panel.add(panelSeaDisplay);

                }
                panel.revalidate(); // Пересчитать макет после изменения компонентов
                panel.repaint();
            }
        });

        add(panel);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
