package org.example.gui;

import org.example.utils.Config;

import javax.swing.*;
import java.awt.*;

public class PlayerCountInputDialogWithSpinner {
    private static boolean resultRead = false;

    public static void openPlayerCountDialog() {

        SpinnerModel spinnerModel = new SpinnerNumberModel(2, Config.MIN_PLAYERS_COUNT, Config.MAX_PLAYERS_COUNT, 1);
        JSpinner playerCountSpinner = new JSpinner(spinnerModel);

        JComponent editor = playerCountSpinner.getEditor();
        if (editor instanceof JSpinner.DefaultEditor) {
            JSpinner.DefaultEditor spinnerEditor = (JSpinner.DefaultEditor) editor;
            spinnerEditor.getTextField().setBackground(new Color(227, 227, 226));
            spinnerEditor.getTextField().setFont(new Font("Arial ", Font.PLAIN, 14));
        }

        JPanel panel = new JPanel(new GridLayout(2, 2));
        panel.add(new JLabel("Выберите количество игроков (2-8):"));
        panel.add(playerCountSpinner);

        int result = JOptionPane.showConfirmDialog(null, panel, "Выбор количества игроков", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            Config.PLAYERS_COUNT = (int) playerCountSpinner.getValue();
            resultRead = true;
        } else resultRead = false;

        if (!resultRead) System.exit(0);

    }
}
