package ui.main_ui;

import domain.Team;
import domain.TeamStatus;
import domain.TeamStatusMap;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.util.ArrayList;

public class LeagueTable extends JTable {

    public static final Dimension INITIAL_SIZE = new Dimension(1125, 340);
    DefaultTableModel model;
    JTable table;

    public LeagueTable(){

        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Team Name", "Points", "Goal Diff",
                "Wins", "Draws", "Loses", "Played", "League Verdict"});
        table = new JTable(model){
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
        }};

        JScrollPane pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setPreferredSize(INITIAL_SIZE);
        setVisible(true);
        setLayout(new FlowLayout());

        TableColumn colVerdict = table.getColumnModel().getColumn(7);

        colVerdict.setPreferredWidth(380);

        add(pane);
    }

    public void populateTable(ArrayList<Team> teamList, ArrayList<TeamStatus> verdictArray, int gamesRemaining) {

        model.setRowCount(0);

        if(teamList == null){
            model.setRowCount(0);

        }
        else {

            for(Team team: teamList){
                model.addRow(new Object[]{team.name, team.points, team.goalDifference,
                        team.wins, team.draws, team.draws, team.gamesPlayed});
            }

            for(int i = 0; i < 20; i++){
                if(verdictArray.get(i) == TeamStatus.finalPlace)
                    model.setValueAt("Team finished " + (i + 1) + "th place!", i, 7);
                else if(verdictArray.get(i) == TeamStatus.canGetOutOfRelegation)
                    model.setValueAt("Team can get out of relegation if they win their next " + gamesRemaining + " games", i, 7);
                else{
                    TeamStatus teamStatus = verdictArray.get(i);
                    TeamStatusMap teamStatusMap = new TeamStatusMap(teamStatus);
                    model.setValueAt(teamStatusMap.getTeamVerdict(), i, 7);
                }
            }
        }
    }

    public void populateRelegationCell(int bottomDifference){
        model.setValueAt("Team needs " + bottomDifference + " points to overtake the team above", 19, 7);
    }

}
