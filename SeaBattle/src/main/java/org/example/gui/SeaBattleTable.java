package org.example.gui;

import org.example.entity.PointBlock;
import org.example.gamerules.GameRules;
import org.example.utils.Config;
import org.example.utils.Point;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class SeaBattleTable extends JTable {

    public final GameRules GAME_RULES;
    public final int PLAYER_INDEX;
    public static final int ROWS = Config.MAX_CORD + 1;
    public static final int COLS = Config.MAX_CORD + 1;


    public SeaBattleTable(GameRules game, int playerIndex) {
        super(ROWS, COLS);
        GAME_RULES = game;
        PLAYER_INDEX = playerIndex;
        setDefaultRenderer(Object.class, new SeaBattleCellRenderer());
        addMouseListener(new SeaBattleMouseListener());
    }

    // эта хрень рисует
    private class SeaBattleCellRenderer extends DefaultTableCellRenderer {

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int y, int x) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, y, x);
            PointBlock pointBlock = GAME_RULES.getPlayer(PLAYER_INDEX).getBlock(new Point(x, y));
            pointBlock.draw((Graphics2D) c.getGraphics(), table.getRowHeight(),table.getColumn(0).getWidth());
            return c;
        }
    }

    private class SeaBattleMouseListener extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            int x = rowAtPoint(e.getPoint());
            int y = columnAtPoint(e.getPoint());
            Point point = new Point(x, y);
            repaint();
        }
    }
}