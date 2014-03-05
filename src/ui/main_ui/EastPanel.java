package ui.main_ui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class EastPanel extends JPanel {
    public final JTextField howManyTeams;
    public final JComboBox teamAmount;
    public final JButton createTable;
    public String newAmountValue;

    public EastPanel(){
        howManyTeams = new JTextField("How many teams in the League?");
        howManyTeams.setEditable(false);

        String[] tableAmount = new String[24];
        for(int i = 0; i < tableAmount.length; i++)
            tableAmount[i] = String.valueOf(i+1);

        teamAmount = new JComboBox(tableAmount);

        teamAmount.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if(itemEvent.getStateChange() == itemEvent.SELECTED)
                    setTeamAmountValue(itemEvent.getItem().toString());
            }
        });
        createTable = new JButton("Create Table");

        Box customLeagueBox = Box.createVerticalBox();

        customLeagueBox.add(howManyTeams);
        customLeagueBox.add(teamAmount);
        customLeagueBox.add(createTable);

        add(customLeagueBox);

    }

    public void setTeamAmountValue(String teamAmountValue) {
        this.newAmountValue = teamAmountValue;
    }

    public String getNewAmountValue(){
        return newAmountValue;
    }

    public void onCreateTable(ActionListener listener){
        createTable.addActionListener(listener);
    }
}
