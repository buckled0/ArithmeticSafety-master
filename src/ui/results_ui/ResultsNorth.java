package ui.results_ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ResultsNorth extends JPanel {
    public JButton close;
    public ResultsNorth(){
        close = new JButton("Close");
        add(close);
    }

    public void onClose(ActionListener listener){
        close.addActionListener(listener);
    }
}
