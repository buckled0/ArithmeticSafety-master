package ui.results_ui;

import domain.Team;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import java.awt.*;

public class PredictionTable extends JTable {

    public static final Dimension INITIAL_SIZE = new Dimension(420, 380);
    DefaultTableModel model;
    JTable table;

    public PredictionTable (Team[] teamList){
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Team Name", "Goal Diff", "Points"});
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

        populateTable(teamList);

        TableColumn colTeamName = table.getColumnModel().getColumn(0);
        colTeamName.setPreferredWidth(15);

        TableColumn colTeamGoalDifference = table.getColumnModel().getColumn(1);
        colTeamGoalDifference.setPreferredWidth(5);

        TableColumn colTeamPoints = table.getColumnModel().getColumn(2);
        colTeamPoints.setPreferredWidth(5);

        add(pane);
    }


    public void populateTable(Team[] teamList) {
        model.setRowCount(0);

        if(teamList == null){
            model.setRowCount(0);

        }
        else {
            for(Team team: teamList){
                model.addRow(new Object[]{team.name, team.goalDifference, team.points});
            }
        }
    }
}
