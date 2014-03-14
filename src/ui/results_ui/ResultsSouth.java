package ui.results_ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ResultsSouth extends JPanel {
    public JButton predictedTable;
    public JButton close;
    public JButton refresh;
    public ResultsSouth(){
        refresh = new JButton("Refresh");
        predictedTable = new JButton("Predicted Table");
        close = new JButton("Close");
        add(refresh);
        add(predictedTable);
        add(close);
    }

    public void onRefresh(ActionListener listener){
        refresh.addActionListener(listener);
    }

    public void onPredictedTable(ActionListener listener) {
        predictedTable.addActionListener(listener);
    }

    public void onClose(ActionListener listener){
        close.addActionListener(listener);
    }
}
