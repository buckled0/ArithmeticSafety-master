package ui.results_ui;

import domain.Team;

import javax.swing.*;
import java.awt.*;

public class PredictedTableWindow extends JFrame {

    public static final String TITLE = "Premier League Predictions";
    public static final Point INITIAL_LOCATION = new Point(300, 262);
    public static final Dimension INITIAL_SIZE = new Dimension(280, 380);
    private PredictionTable predictionTable;

    public PredictedTableWindow(Team[] teamList){

        setTitle(TITLE);
        setSize(INITIAL_SIZE);
        setResizable(false);
        setLocation(INITIAL_LOCATION);

        getContentPane().add(BorderLayout.CENTER, predictionTable = new PredictionTable(teamList));

        setVisible(true);
    }

}
