package ui.results_ui;

import domain.Game;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class PredictedGamesTable extends JTable{

    public static final Dimension INITIAL_SIZE = new Dimension(280, 300);
    DefaultTableModel model;
    JTable table;

    public PredictedGamesTable(ArrayList<Game> gamesLeft){
        model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"Home Team", "Away Team", "Score"});
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

        populateTable(gamesLeft);

        add(pane);
    }

    private void populateTable(ArrayList<Game> gamesLeft) {
        model.setRowCount(0);

        if(gamesLeft == null){
            model.setRowCount(0);

        }
        else {
            for(Game game: gamesLeft){
                model.addRow(new Object[]{game.homeTeam, game.awayTeam, game.score});
            }
        }
    }
}
