package ui.betting_ui;

import domain.TeamVerdict;
import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.Arrays;

public class BettingTeamSelection extends JPanel {
    public final JComboBox homeTeam;
    public final JComboBox awayTeam;
    public final JButton calculateOddsButton;
    public final JTextField homeTeamOdds;
    public final JTextField drawingOdds;
    public final JTextField awayTeamOdds;
    public final JTextField homeOddsValue;
    public final JTextField drawingOddsValue;
    public final JTextField awayOddsValue;
    private String newHomeTeamValue;
    private String newAwayTeamValue;
    public double homeOdds;
    public double drawOdds;
    public double awayOdds;

    public BettingTeamSelection() throws IOException, SAXException, ParserConfigurationException {
        TeamVerdict leagueVerdict = new TeamVerdict("Premier League Current");

        String[] team1List = new String[20];
        String[] team2List = new String[20];

        for(int i = 0; i < 20; i++){
            team1List[i] = leagueVerdict.getTeamList().get(i).getName();
            team2List[i] = leagueVerdict.getTeamList().get(i).getName();
        }

        Arrays.sort(team1List);
        Arrays.sort(team2List);

        setHomeTeamValue("Arsenal");
        setAwayTeamValue("Arsenal");

        homeTeam = new JComboBox(team1List);

        homeTeam.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    setHomeTeamValue(itemEvent.getItem().toString());
                }
            }
        });

        awayTeam = new JComboBox(team2List);

        awayTeam.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent itemEvent) {
                if (itemEvent.getStateChange() == ItemEvent.SELECTED) {
                    setAwayTeamValue(itemEvent.getItem().toString());
                }
            }
        });

        calculateOddsButton = new JButton("Calculate Odds");

        Box teamSelectionBox = Box.createVerticalBox();

        teamSelectionBox.add(homeTeam);
        teamSelectionBox.add(awayTeam);
        teamSelectionBox.add(calculateOddsButton);

        homeTeamOdds = new JTextField("Home Team Odds: ");
        drawingOdds = new JTextField("Drawing Odds: ");
        awayTeamOdds = new JTextField("Away Team Odds: ");

        homeTeamOdds.setEditable(false);
        drawingOdds.setEditable(false);
        awayTeamOdds.setEditable(false);

        Box textFieldBox = Box.createVerticalBox();

        textFieldBox.add(homeTeamOdds);
        textFieldBox.add(drawingOdds);
        textFieldBox.add(awayTeamOdds);

        homeOddsValue = new JTextField("0.00");
        drawingOddsValue = new JTextField("0.00");
        awayOddsValue = new JTextField("0.00");

        Box teamOddsValueBox = Box.createVerticalBox();

        teamOddsValueBox.add(homeOddsValue);
        teamOddsValueBox.add(drawingOddsValue);
        teamOddsValueBox.add(awayOddsValue);

        add(teamSelectionBox);
        add(textFieldBox);
        add(teamOddsValueBox);
    }

    public void onCalculateOddsButton(ActionListener listener){
        calculateOddsButton.addActionListener(listener);
    }

    public void setHomeOdds(double homeOdds){
        this.homeOdds = homeOdds;
        homeOddsValue.setText(String.valueOf(homeOdds));
    }

    public void setDrawOdds(double drawOdds){
        this.drawOdds = drawOdds;
        drawingOddsValue.setText(String.valueOf(drawOdds));
    }

    public void setAwayOdds(double awayOdds){
        this.awayOdds = awayOdds;
        awayOddsValue.setText(String.valueOf(awayOdds));
    }

    private void setHomeTeamValue(String selectedTeam1){ this.newHomeTeamValue = selectedTeam1; }

    public String getHomeTeamValue() { return newHomeTeamValue; }

    private void setAwayTeamValue(String selectedTeam2){ this.newAwayTeamValue = selectedTeam2; }

    public String getAwayTeamValue() { return newAwayTeamValue; }
}
