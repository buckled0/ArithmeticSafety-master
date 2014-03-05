package ui.betting_ui;

import javax.swing.*;

public class BettingNorthPanel extends JPanel {
    public final JTextField instructionsField;

    public BettingNorthPanel(){
        instructionsField = new JTextField("Welcome to Odds On Betting, select which two teams are playing and press" +
        " calculate to get their odds");

        instructionsField.setEditable(false);

        add(instructionsField);
    }
}
