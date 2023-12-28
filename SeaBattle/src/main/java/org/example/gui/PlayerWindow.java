package org.example.gui;

import org.example.entity.*;
import org.example.gamerules.Player;
import org.example.utils.Config;
import org.example.utils.Point;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import java.util.Stack;

public class PlayerWindow extends JFrame {
    private Player player;
    private Stack<Immovable> entities;

    private Immovable activeImmovable;
    private JPanel display;

    public PlayerWindow(Player player) {
        super("расстановка кораблей");

        this.player = player;
        fillEntities();

        String name = player.getName();

        setSize(600, 700);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        inputPanel.setBackground(Config.BACKGROUND_COLOR);

        JLabel nameLabel = new JLabel("Имя игрока:");
        nameLabel.setForeground(new Color(0x9F9FA0));

        JTextField playerNameField = new JTextField(player.getName(), 20);
        playerNameField.setBorder(new LineBorder(new Color(0x202020)));
        playerNameField.setForeground(new Color(0xDEDEDE));
        playerNameField.setBackground(new Color(0x202020));

        playerNameField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                player.setName(playerNameField.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                player.setName(name);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                player.setName(playerNameField.getText());
            }
        });

        inputPanel.add(nameLabel);
        inputPanel.add(playerNameField);

        display = new DrawPanel(0, 0, getWidth(), player);

        add(inputPanel, BorderLayout.NORTH);
        add(display, BorderLayout.CENTER);

        JButton okButton = new JButton("ОК");
        okButton.addActionListener(e -> this.close());

        JButton addShipButton = new JButton("Add ship");
        addShipButton.addActionListener(e -> addShip());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Config.BACKGROUND_COLOR);
        okButton.setBackground(Config.BACKGROUND_COLOR);
        okButton.setForeground(new Color(0x9F9FA0));
        addShipButton.setBackground(Config.BACKGROUND_COLOR);
        addShipButton.setForeground(new Color(0x9F9FA0));
        buttonPanel.add(okButton);
        buttonPanel.add(addShipButton);

        add(buttonPanel, BorderLayout.SOUTH);

        display.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                int keyCode = e.getKeyCode();
                switch (keyCode) {
                    case KeyEvent.VK_UP:
                        System.out.println("up");
                        moveUp();
                        break;
                    case KeyEvent.VK_DOWN:
                        System.out.println("down");
                        moveDown();
                        break;
                    case KeyEvent.VK_LEFT:
                        System.out.println("left");
                        moveLeft();
                        break;
                    case KeyEvent.VK_RIGHT:
                        System.out.println("right");
                        moveRight();
                        break;
                    case KeyEvent.VK_SPACE:
                        System.out.println("rotate");
                        rotate();
                        break;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });

        setVisible(true);
    }

    private void close() {
        if (!isStandsCorrectly()) {
            // todo: вылезалка чтоб поправили корабли
            // todo: проввериить шоб все корабли были добавлены
        }
        for (Immovable immovable : player.getEntities()) {
            immovable.close();

            for (PointBlock block : immovable.getCoords()) {
                block.setHost(immovable);
            }
        }
        dispose();
    }

    private void addShip() {
        if (entities.empty()) {
            System.out.println("not shep");
        } else {
            activeImmovable = entities.peek();
            player.addEntity(entities.pop());
            repaint();

            display.repaint();
            display.requestFocus(false);
        }
    }

    private void fillEntities() {
        PointBlock[][] table = player.getTable();
        entities = new Stack<>();
        entities.push(new Ship(new PointBlock[]{table[0][0], table[1][0], table[2][0], table[3][0]}));
        entities.push(new Ship(new PointBlock[]{table[0][0], table[1][0], table[2][0]}));
        entities.push(new Ship(new PointBlock[]{table[0][0], table[1][0], table[2][0]}));
        entities.push(new Ship(new PointBlock[]{table[0][0], table[1][0]}));
        entities.push(new Ship(new PointBlock[]{table[0][0], table[1][0]}));
        entities.push(new Ship(new PointBlock[]{table[0][0], table[1][0]}));
        entities.push(new Ship(new PointBlock[]{table[0][0]}));
        entities.push(new Ship(new PointBlock[]{table[0][0]}));
        entities.push(new Ship(new PointBlock[]{table[0][0]}));
        entities.push(new Ship(new PointBlock[]{table[0][0]}));
    }

    private boolean isStandsCorrectly() {
        List<Immovable> ships = player.getEntities();

        for (int index1 = 0; index1 < ships.size(); index1++) {
            for (int index2 = 0; index2 < ships.size(); index2++) {
                //if ()
            }
        }
        return true;
    }

    private void moveRight() {
        PointBlock[] blocks = activeImmovable.getCoords();
        if (activeImmovable.maxX() < Config.MAX_CORD) {
            for (int i = 0; i < blocks.length; i++) {
                blocks[i] = player.getBlock(new Point(blocks[i].coordinate.x + 1, blocks[i].coordinate.y));
            }
            display.repaint();
        }
    }

    private void moveLeft() {
        PointBlock[] blocks = activeImmovable.getCoords();
        if (activeImmovable.minX() > Config.MIN_CORD) {
            for (int i = 0; i < blocks.length; i++) {
                blocks[i] = player.getBlock(new org.example.utils.Point(blocks[i].coordinate.x - 1, blocks[i].coordinate.y));
            }
            display.repaint();
        }
    }

    private void moveUp() {
        PointBlock[] blocks = activeImmovable.getCoords();
        if (activeImmovable.maxY() > Config.MIN_CORD) {
            for (int i = 0; i < blocks.length; i++) {
                blocks[i] = player.getBlock(new org.example.utils.Point(blocks[i].coordinate.x, blocks[i].coordinate.y - 1));
            }
            display.repaint();
        }
    }

    private void moveDown() {
        PointBlock[] blocks = activeImmovable.getCoords();
        if (activeImmovable.maxY() < Config.MAX_CORD) {
            for (int i = 0; i < blocks.length; i++) {
                blocks[i] = player.getBlock(new Point(blocks[i].coordinate.x, blocks[i].coordinate.y + 1));
            }
        display.repaint();
    }

}

    private void rotate() {
        //todo: сделать поворот
        display.repaint();
    }


//    public static String showShipLocationDialog(
//            JFrame parentFrame,
//            int numberPlayer,
//            PointBlock[][] table,
//            Immovable[] entities) {
//
//        JDialog dialog = new JDialog(parentFrame, "расстановка кораблей", true);
//        dialog.setSize(600, 700);
//        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
//
//        JPanel inputPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
//        inputPanel.setBackground(Config.BACKGROUND_COLOR);
//
//        JLabel nameLabel = new JLabel("Имя игрока:");
//        nameLabel.setForeground(new Color(0x9F9FA0));
//
//        JTextField playerNameField = new JTextField("player " + (numberPlayer + 1), 20);
//        playerNameField.setBorder(new LineBorder(new Color(0x202020)));
//        playerNameField.setForeground(new Color(0xDEDEDE));
//        playerNameField.setBackground(new Color(0x202020));
//
//        inputPanel.add(nameLabel);
//        inputPanel.add(playerNameField);
//
//        JPanel drawingPanel = new DrawPanel(0, 0, dialog.getWidth(), pl);
//        for (Immovable immovable : entities) {
//            immovable.getPainter().draw((Graphics2D) drawingPanel.getGraphics());
//        }
//
//        dialog.add(inputPanel, BorderLayout.NORTH);
//        dialog.add(drawingPanel, BorderLayout.CENTER);
//
//        JButton okButton = new JButton("ОК");
//        okButton.addActionListener(e -> dialog.dispose());
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setBackground(Config.BACKGROUND_COLOR);
//        okButton.setBackground(Config.BACKGROUND_COLOR);
//        okButton.setForeground(new Color(0x9F9FA0));
//        buttonPanel.add(okButton);
//
//        dialog.add(buttonPanel, BorderLayout.SOUTH);
//
//        dialog.setLocationRelativeTo(parentFrame);
//        dialog.setVisible(true);
//
//        if (playerNameField.getText() != null) {
//            return playerNameField.getText();
//        } else {
//            return "player " + numberPlayer;
//        }
//    }

}