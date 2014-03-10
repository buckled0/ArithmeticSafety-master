package ui.results_ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class ResultsNorth extends JPanel {
    public JButton save;
    public ResultsNorth(){
        save = new JButton("Save");
        add(save);
    }

    public void onSave(ActionListener listener){
        save.addActionListener(listener);
    }
}
