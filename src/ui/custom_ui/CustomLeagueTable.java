package ui.custom_ui;

import domain.Team;
import domain.TeamStatus;
import domain.TeamStatusMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class CustomLeagueTable extends JTable {

    public static final Dimension INITIAL_SIZE = new Dimension(1000, 450);
    DefaultTableModel model;
    JTable table;
    ArrayList<Team> teamList = new ArrayList<Team>();

    public CustomLeagueTable(String teamAmount){

        model = new DefaultTableModel();
        table = new JTable(model){
            @Override
            public boolean isCellEditable(int row, int column) {
                return true;
            }};
        model.setColumnIdentifiers(new String[]{"Team Name", "Points", "Goal Diff", "Wins", "Loses",
                "Draws", "Played", "League Verdict"});
        model.setRowCount(Integer.parseInt(teamAmount));

        JScrollPane pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setPreferredSize(INITIAL_SIZE);
        setVisible(true);
        setLayout(new FlowLayout());

        table.setRowSelectionAllowed(false);

        TableColumn colTeamName = table.getColumnModel().getColumn(0);
        colTeamName.setPreferredWidth(150);

        TableColumn colVerdict = table.getColumnModel().getColumn(7);
        colVerdict.setPreferredWidth(290);

        add(pane);
    }

    public ArrayList<Team> getValuesInTable() {
        teamList.clear();
        int rowNum = table.getRowCount();
        for(int i = 0; i < rowNum; i++){
            String teamName = String.valueOf(table.getValueAt(i, 0));
            int teamPoints = Integer.parseInt(String.valueOf(table.getValueAt(i, 1)));
            int teamGD = Integer.parseInt(String.valueOf(table.getValueAt(i, 2)));
            int wins = Integer.parseInt(String.valueOf(table.getValueAt(i, 2)));
            int loses = Integer.parseInt(String.valueOf(table.getValueAt(i, 2)));
            int draws = Integer.parseInt(String.valueOf(table.getValueAt(i, 2)));
            int gamesPlayed = Integer.parseInt(String.valueOf(table.getValueAt(i, 3)));
            Team team = new Team(teamName, teamPoints, teamGD, wins, draws, loses, gamesPlayed);
            teamList.add(i, team);
        }
        return teamList;
    }


    public void populateTable(ArrayList<Team> teamList, ArrayList<TeamStatus> verdictArray) {

        for(int i = 0; i < 20; i++){
            if(verdictArray.get(i) == TeamStatus.finalPlace)
                model.setValueAt("Team finished " + (i + 1) + "th place!", i, 7);
            else{
                TeamStatus teamStatus = verdictArray.get(i);
                TeamStatusMap teamStatusMap = new TeamStatusMap(teamStatus);
                model.setValueAt(teamStatusMap.getTeamVerdict(), i, 7);
            }
        }
    }
    public void populateOnlyTeamInLeagueVerdict(int teamAmount){
        model.setValueAt("Only Team in league, pretty sure they're safe", teamAmount - 1, 7);
    }

    public void populateRelegationCell(int bottomDifference, int teamAmount){
        model.setValueAt("Team needs " + bottomDifference + " to overtake the team above", teamAmount - 1, 7);
    }

}
