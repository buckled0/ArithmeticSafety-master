package ui.results_ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ResultsSouth extends JPanel {
    public JButton gamesRemaining;
    public JButton predictedTable;
    public JButton close;
    public ResultsSouth(){
        gamesRemaining = new JButton("Games Remaining");
        predictedTable = new JButton("Predicted Table");
        close = new JButton("Close");
        add(gamesRemaining);
        add(predictedTable);
        add(close);
    }

    public void onGamesRemaining(ActionListener listener){
        gamesRemaining.addActionListener(listener);
    }

    public void onPredictedTable(ActionListener listener) {
        predictedTable.addActionListener(listener);
    }

    public void onClose(ActionListener listener){
        close.addActionListener(listener);
    }
}
