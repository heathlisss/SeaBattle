package org.example.gui;

import org.example.entity.*;
import org.example.gamerules.Player;
import org.example.gui.dropDownWindows.Message;
import org.example.utils.Config;
import org.example.utils.Point;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.LinkedList;
import java.util.Queue;
public class PlayerWindow extends JFrame {
    private Player player;
    private Queue<Immovable> entities;
    private Immovable activeImmovable;
    private JPanel display;

    public PlayerWindow(Player player) {
        super("расстановка кораблей");

        this.player = player;
        fillEntities();

        String name = player.getName();

        setSize(600, 700);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

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
        okButton.setVisible(false);
        okButton.setBackground(Config.BACKGROUND_COLOR);
        okButton.setForeground(new Color(0x9F9FA0));

        JButton restart = new JButton("restart");
        restart.addActionListener(e -> {
            this.fillEntities();
            display.repaint();
        });
        restart.setVisible(true);
        restart.setBackground(Config.BACKGROUND_COLOR);
        restart.setForeground(new Color(0x9F9FA0));

        JButton addShipButton = new JButton("Add ship");
        addShipButton.addActionListener(e -> addShip(addShipButton, okButton));
        addShipButton.setBackground(Config.BACKGROUND_COLOR);
        addShipButton.setForeground(new Color(0x9F9FA0));

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Config.BACKGROUND_COLOR);
        buttonPanel.add(okButton);
        buttonPanel.add(addShipButton);
        buttonPanel.add(restart);

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
        setLocationRelativeTo(null);
    }

    private void close() {
        if (!isStandingCorrectly(activeImmovable.getCoords())) {
            Message.shipMisplaced(this);
            display.requestFocus(false);
            return;
        }
        for (Immovable immovable : player.getEntities()) {
            immovable.close();

            for (PointBlock block : immovable.getCoords()) {
                block.setHost(immovable);
            }
        }

        dispose();
    }

    private void addShip(JButton addShipButton, JButton okButton) {
        if (Config.ENTITY_COUNT == player.getEntities().size()) {
            Message.allShipsAdded(this);
            addShipButton.setVisible(false);
            okButton.setVisible(true);
            display.requestFocus(false);
        } else {
            if (activeImmovable != null) {
                if (!isStandingCorrectly(activeImmovable.getCoords())) {
                    Message.shipMisplaced(this);
                    display.requestFocus(false);
                    return;
                }
            }
            activeImmovable = entities.peek();
            player.addEntity(entities.poll());

            display.repaint();
            display.requestFocus(false);
        }
    }

    private void fillEntities() {
        player.getEntities().clear();
        PointBlock[][] table = player.getTable();
        entities = new LinkedList<>();
        entities.add(new Ship(new PointBlock[]{table[0][0], table[1][0], table[2][0], table[3][0]}));
        entities.add(new Ship(new PointBlock[]{table[0][0], table[1][0], table[2][0]}));
        entities.add(new Ship(new PointBlock[]{table[0][0], table[1][0], table[2][0]}));
        entities.add(new Ship(new PointBlock[]{table[0][0], table[1][0]}));
        entities.add(new Ship(new PointBlock[]{table[0][0], table[1][0]}));
        entities.add(new Ship(new PointBlock[]{table[0][0], table[1][0]}));
        entities.add(new Ship(new PointBlock[]{table[0][0]}));
        entities.add(new Ship(new PointBlock[]{table[0][0]}));
        entities.add(new Ship(new PointBlock[]{table[0][0]}));
        entities.add(new Ship(new PointBlock[]{table[0][0]}));
        entities.add(new Mine(new PointBlock[]{table[0][0]}));
        entities.add(new Mine(new PointBlock[]{table[0][0]}));

    }

    private void moveRight() {
        PointBlock[] blocks = activeImmovable.getCoords();
        if (activeImmovable.maxX() < Config.MAX_CORD - 1) {
            for (int i = 0; i < blocks.length; i++) {
                blocks[i] = player.getBlock(new Point(blocks[i].coordinate.x + 1, blocks[i].coordinate.y));
            }
        }
        display.repaint();
    }

    private void moveLeft() {
        PointBlock[] blocks = activeImmovable.getCoords();
        if (activeImmovable.minX() > Config.MIN_CORD) {
            for (int i = 0; i < blocks.length; i++) {
                blocks[i] = player.getBlock(new Point(blocks[i].coordinate.x - 1, blocks[i].coordinate.y));
            }
        }
        display.repaint();
    }

    private void moveUp() {
        PointBlock[] blocks = activeImmovable.getCoords();
        if (activeImmovable.minY() > Config.MIN_CORD) {
            for (int i = 0; i < blocks.length; i++) {
                blocks[i] = player.getBlock(new Point(blocks[i].coordinate.x, blocks[i].coordinate.y - 1));
            }
        }
        display.repaint();
    }

    private void moveDown() {
        PointBlock[] blocks = activeImmovable.getCoords();
        if (activeImmovable.maxY() < Config.MAX_CORD - 1) {
            for (int i = 0; i < blocks.length; i++) {
                blocks[i] = player.getBlock(new Point(blocks[i].coordinate.x, blocks[i].coordinate.y + 1));
            }
        }
        display.repaint();
    }

    private void rotate() {
        PointBlock[] blocks = activeImmovable.getCoords();
        Point point = blocks[0].coordinate;
        if (activeImmovable.maxY() + (activeImmovable.maxX() - activeImmovable.minX()) < Config.MAX_CORD
                && activeImmovable.maxX() + (activeImmovable.maxY() - activeImmovable.minY()) < Config.MAX_CORD) {
            for (int i = 0; i < blocks.length; i++) {
                int y = blocks[i].coordinate.x - point.x;
                int x = blocks[i].coordinate.y - point.y;
                blocks[i] = player.getBlock(new Point(x + point.x, y + point.y));
            }
        }
        display.repaint();
    }

    private boolean isStandingCorrectly(PointBlock[] newCoordinate) {
        for (Immovable entity : player.getEntities()) {
            if (entity != activeImmovable) {
                for (PointBlock blockEntity : entity.getCoords()) {
                    for (PointBlock block : newCoordinate) {
                        if (block.coordinate == blockEntity.coordinate) return false;
                    }
                }
                if (!(entity.canBeSurrounded() || activeImmovable.canBeSurrounded())) {
                    for (PointBlock blockEntityAround : player.getBlocks(entity.getCoordsAround())) {
                        for (PointBlock block : newCoordinate) {
                            if (blockEntityAround.coordinate == block.coordinate) return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    public void setActiveImmovable(Immovable activeImmovable) {
        this.activeImmovable = activeImmovable;
    }
}