package ui.main_ui;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class NorthPanel extends JPanel{

    public String[] leagues;
    public JComboBox leagueMenu;
    public JButton calculate;
    public JButton bettingButton;
    public String newValue;

    public NorthPanel() {
        leagues = new String[] {"Please Select a League", "Premier League Current", "Premier League, 09/02/2014",
                "Premier League, 12/02/2014", "Premier League, 23/02/2014", "Premier League, 02/03/2014",
                "Premier League Half Way Point", "Premier League, 21/04/2013", "Premier League, 28/04/2013", "Premier League, 05/05/2013",
                "Premier League, 12/05/2013", "Premier League, 19/05/2013", "Premier League, 07/04/2012", "Premier League, 11/04/2012",
                "Premier League, 28/04/2012", "Premier League, 07/05/2012", "Premier League, 08/05/2012", "Premier League, 13/05/2012"};
        leagueMenu = new JComboBox(leagues);

        leagueMenu.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if(itemEvent.getStateChange() == itemEvent.SELECTED)
                    setNewValue(itemEvent.getItem().toString());
            }
        });

        calculate = new JButton("Calculate Safety");
        bettingButton = new JButton("Arithmetic Safety Betting");

        add(leagueMenu);
        add(calculate);
        add(bettingButton);

    }

    public void onCalculate(ActionListener listener){
        calculate.addActionListener(listener);
    }

    public void onBettingButton(ActionListener listener){
        bettingButton.addActionListener(listener);
    }

    private void setNewValue(String selectedValue){
        this.newValue = selectedValue;
    }

    public String getSelectedValue() { return newValue; }


}
