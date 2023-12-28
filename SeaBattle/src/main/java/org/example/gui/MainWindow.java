package org.example.gui;

import org.example.gamerules.GameRules;
import org.example.utils.Config;
import org.example.utils.Point;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainWindow extends JFrame {

    private static final int WIDTH = 1400;
    private static final int HEIGHT = 800;
    private GameRules gameRules;

    public MainWindow(GameRules gameRules) {
        setTitle("SeaBattle");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        //setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.gameRules = gameRules;

        JPanel panel = new JPanel(new GridLayout(1, Config.PLAYERS_COUNT));
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                panel.removeAll();
                for (int index = 0; index < Config.PLAYERS_COUNT; index++) {
                  /*JLabel nameLabel = new JLabel(gameRules.players[index].getName());
                    nameLabel.setForeground(new Color(0x9F9FA0));
                   nameLabel.setFont(new Font("Arial ", Font.PLAIN, 17));

                    JPanel panelSeaDisplay = new DrawPanel(
                           0,
                           0,
                            (int) (getSize().width / Config.PLAYERS_COUNT), gameRules.players[index].table);
                    panelSeaDisplay.add(nameLabel);
                    panel.add(panelSeaDisplay);
                   */
                    DrawPanel panelSeaDisplay = getPanelSeaDisplay(index);
                    panelSeaDisplay.addMouseListener(new CustomMouseListener(panelSeaDisplay));
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

    private DrawPanel getPanelSeaDisplay(int index) {
        JLabel nameLabel = new JLabel(gameRules.players[index].getName());
        nameLabel.setForeground(new Color(0x9F9FA0));
        nameLabel.setFont(new Font("Arial ", Font.PLAIN, 17));
        DrawPanel panelSeaDisplay = new DrawPanel(
                0,
                0,
                (getSize().width / Config.PLAYERS_COUNT),
                gameRules.players[index]);

        panelSeaDisplay.add(nameLabel);
        return panelSeaDisplay;
    }

    private class CustomMouseListener extends MouseAdapter {
        private DrawPanel drawPanel;

        public CustomMouseListener(DrawPanel drawPanel) {
            this.drawPanel = drawPanel;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            // Получите координаты мыши относительно DrawPanel
            int x = e.getX();
            int y = e.getY();

            if ((x >= drawPanel.getStartX() && x <= drawPanel.getStartX() + drawPanel.getWidthBlock() * Config.MAX_CORD)
                    && (y >= drawPanel.getStartY() && y <= drawPanel.getStartY() + drawPanel.getWidthBlock() * Config.MAX_CORD)) {
                int column = (x - drawPanel.getStartX()) / drawPanel.getWidthBlock();
                int row = (y - drawPanel.getStartY()) / drawPanel.getWidthBlock();

                Point point = new Point(column, row);
                if (gameRules.getActivePlayer() == drawPanel.getPlayer()) {
                    gameRules.nextTurn(point);
                }

                System.out.println(column + " " + row);
            }

        }
    }

}
