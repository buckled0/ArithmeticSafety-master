package ui.results_ui;

import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ResultsWindow extends JFrame{

    public static final String TITLE = "Premier League Results";
    public static final Dimension INITIAL_SIZE = new Dimension(1900, 410);
    public static final Point INITIAL_LOCATION = new Point(25, 62);
    private ResultsTable resultsTable;

    public ResultsWindow() throws IOException, SAXException, ParserConfigurationException {
        setTitle(TITLE);
        setSize(INITIAL_SIZE);
        setResizable(false);
        setLocation(INITIAL_LOCATION);
        getContentPane().add(BorderLayout.CENTER, resultsTable = new ResultsTable());
        getContentPane().add(BorderLayout.SOUTH, setUpSouthPanel());

        setVisible(true);
    }

    private ResultsSouth setUpSouthPanel(){
        ResultsSouth resultsSouth = new ResultsSouth();

        resultsSouth.onGamesRemaining(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new PredictedGamesWindow(resultsTable.gamesLeft);
                    }
                });
            }
        });

        resultsSouth.onPredictedTable(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        new PredictedTableWindow(resultsTable.setTeams);
                    }
                });
            }
        });

        resultsSouth.onClose(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                setVisible(false);
            }
        });
        return resultsSouth;
    }

}
