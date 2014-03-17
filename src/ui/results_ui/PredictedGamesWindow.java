package ui.results_ui;

import domain.Game;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class PredictedGamesWindow extends JFrame {
    public static final String TITLE = "Premier League Scores";
    public static final Point INITIAL_LOCATION = new Point(300, 262);
    public static final Dimension INITIAL_SIZE = new Dimension(320, 380);
    private PredictedGamesTable predictedGamesTable;

    public PredictedGamesWindow(ArrayList<Game> gamesLeft){
        setTitle(TITLE);
        setSize(INITIAL_SIZE);
        setResizable(false);
        setLocation(INITIAL_LOCATION);

        getContentPane().add(BorderLayout.CENTER, predictedGamesTable = new PredictedGamesTable(gamesLeft));

        setVisible(true);
    }
}
