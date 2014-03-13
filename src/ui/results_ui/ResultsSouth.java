package ui.results_ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ResultsSouth extends JPanel {
    public JButton predictedTable;
    public JButton close;
    public ResultsSouth(){
        predictedTable = new JButton("Predicted Table");
        close = new JButton("Close");
        add(predictedTable);
        add(close);
    }

    public void onPredictedTable(ActionListener listener) {
        predictedTable.addActionListener(listener);
    }

    public void onClose(ActionListener listener){
        close.addActionListener(listener);
    }
}
