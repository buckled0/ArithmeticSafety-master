package ui.betting_ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BettingSouthPanel extends JPanel {
    public final JButton closeButton;

    public BettingSouthPanel() {

        closeButton = new JButton();
        closeButton.setText("Close");

        add(closeButton);
    }

    public void onCloseButton(ActionListener listener){
        closeButton.addActionListener(listener);
    }
}
