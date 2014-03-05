package ui.custom_ui;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CustomBottomPanel extends JPanel {

    public JTextField instructions;
    public final JButton calculateButton;
    public final JButton closeButton;

    public CustomBottomPanel() {

        instructions = new JTextField("Please start entering your League data");

        instructions.setEditable(false);

        calculateButton = new JButton("Calculate Safety");

        closeButton = new JButton("Close");

        add(instructions);
        add(calculateButton);
        add(closeButton);

    }

    public void onCalculate(ActionListener listener) {
        calculateButton.addActionListener(listener);
    }

    public void onCloseButton(ActionListener listener){
        closeButton.addActionListener(listener);
    }

    public void teamDefaultInstructions(){
        instructions.setText("Please start entering your League data");
    }

    public void teamNameInstructions(){
        instructions.setText("Please enter the name of the Teams");
    }

    public void teamPointsInstructions(){
        instructions.setText("Please enter the points of the Teams");
    }

    public void teamGoalDifferenceInstructions(){
        instructions.setText("Please enter the Goal Difference of the Teams");
    }

    public void teamGamesPlayedInstructions(){
        instructions.setText("Please enter amount of Games the Teams have played");
    }

}