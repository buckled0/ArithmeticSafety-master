package ui.main_ui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BottomPanel extends JPanel {

    public final JButton quitButton;

    public BottomPanel() {

        quitButton = new JButton();
        quitButton.setText("Quit");

        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
               System.exit(0);
            }
        });

        add(quitButton);
    }

}
