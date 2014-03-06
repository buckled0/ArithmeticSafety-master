package ui.betting_ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class BettingSouthPanel extends JPanel {
    public final JButton closeButton;
    public final JTextField disclaimer;

    public BettingSouthPanel() {

        disclaimer = new JTextField("Disclaimer: The creator of this application holds no responsibility to any money " +
        "lost with the odds shown");
        disclaimer.setEditable(false);
        closeButton = new JButton();
        closeButton.setText("Close");

        add(disclaimer);
        add(closeButton);
    }

    public void onCloseButton(ActionListener listener){
        closeButton.addActionListener(listener);
    }
}
