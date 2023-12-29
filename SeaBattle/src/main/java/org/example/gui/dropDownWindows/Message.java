package org.example.gui.dropDownWindows;

import javax.swing.*;

public class Message {
    public static void allShipsAdded(JFrame parentFrame) {
        JOptionPane.showMessageDialog(parentFrame,
                "Вы добалили все корабли",
                " ",
                JOptionPane.INFORMATION_MESSAGE);
    }

}
