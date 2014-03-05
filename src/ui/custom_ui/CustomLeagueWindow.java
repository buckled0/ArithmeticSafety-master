package ui.custom_ui;

import domain.Team;
import domain.TeamStatus;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CustomLeagueWindow extends JFrame {
    public static final String TITLE = "Arithmetic Football";
    public static final Dimension INITIAL_SIZE = new Dimension(800, 550);
    public static final Point INITIAL_LOCATION = new Point(200, 50);
    public CustomLeagueTable customLeagueTable;
    public String teamAmount;
    public ArrayList<Team> teamList = new ArrayList<Team>();
    public ArrayList<TeamStatus> verdictList = new ArrayList<TeamStatus>();

    public CustomLeagueWindow(String teamAmount){
        this.teamAmount = teamAmount;
        setTitle(TITLE);
        setSize(INITIAL_SIZE);
        setResizable(false);
        setLocation(INITIAL_LOCATION);

        getContentPane().add(BorderLayout.CENTER, customLeagueTable = new CustomLeagueTable(teamAmount));
        getContentPane().add(BorderLayout.SOUTH, setupCustomBottomPanel(Integer.parseInt(teamAmount)));

        setVisible(true);
    }

    private CustomBottomPanel setupCustomBottomPanel(final int teamAmount){
        final CustomBottomPanel customBottomPanel = new CustomBottomPanel();

        customBottomPanel.onCalculate(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                teamList.clear();
                verdictList.clear();
                int arraySize = customLeagueTable.getValuesInTable().size();
                if(arraySize == 1) {
                    customLeagueTable.populateOnlyTeamInLeagueVerdict(teamAmount);
                } else {
                    for(int i = 0; i < arraySize; i++){
                        teamList.add(i, customLeagueTable.getValuesInTable().get(i));
                    }
                    for(int i = 0; i < teamList.size() - 1; i++){
                        Team team1 = teamList.get(i);
                        Team team2 = teamList.get(i + 1);
                        TeamStatus teamStatus = team1.leagueStatus(team1, team2);
                        verdictList.add(i, teamStatus);
                    }
                    customLeagueTable.populateTable(teamList, verdictList);
                    int bottom = teamList.size()-1;
                    int secondOffBottom = teamList.size()-2;
                    int bottomGoalDifference = teamList.get(secondOffBottom).getPoints() -
                            teamList.get(bottom).getPoints() + 1;
                    customLeagueTable.populateRelegationCell(bottomGoalDifference, teamAmount);
                }
            }
        });

        customBottomPanel.onCloseButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);         }
        });

        return customBottomPanel;
    }

}