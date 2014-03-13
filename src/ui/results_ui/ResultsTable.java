package ui.results_ui;

import domain.PremierLeaguePredictions;
import domain.Team;
import org.xml.sax.SAXException;
import xmlconnector.XMLFixtureConnector;
import xmlconnector.XMLLeagueConnector;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class ResultsTable extends JTable implements TableModelListener{
    public static final Dimension INITIAL_SIZE = new Dimension(1500, 342);
    DefaultTableModel model;
    JTable table;
    String[] homeTeamName;
    String[] awayTeamName;
    Team[] homeTeam;
    Team[] awayTeam;

    public ResultsTable() throws ParserConfigurationException, SAXException, IOException {
        XMLLeagueConnector xmlLeagueConnector = new XMLLeagueConnector("Premier League");
        XMLFixtureConnector xmlFixtureConnector = new XMLFixtureConnector();
        String[] columnHeaders = new String[21];
        columnHeaders[0] = " ";
        awayTeam = new Team[20];
        homeTeam = new Team[20];
        awayTeamName = new String[20];
        homeTeamName = new String[20];

        for (int i = 1; i < xmlLeagueConnector.getListOfTeams().size() + 1; i++) {
            Team team = xmlLeagueConnector.getListOfTeams().get(i - 1);
            columnHeaders[i] = team.name;
        }

        Arrays.sort(columnHeaders);

        int numRows = 20;
        model = new DefaultTableModel(numRows, columnHeaders.length);

        model.setColumnIdentifiers(columnHeaders);
        final int numColumn = model.getColumnCount();
        table = new JTable(model) {
            @Override
            public boolean isCellEditable(int row, int column) {
                if (column == 0)
                    return false;
                else
                    return true;
            }
        };

        for (int i = 0; i < columnHeaders.length - 1; i++) {
            model.setValueAt(columnHeaders[i + 1], i, 0);
        }

        populateTable(xmlFixtureConnector, columnHeaders, numRows, numColumn);
        findPostponedGames(numRows, numColumn);
        populateRemainingGames(numRows, numColumn);

        JScrollPane pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setPreferredSize(INITIAL_SIZE);
        setVisible(true);
        setLayout(new FlowLayout());

        add(pane);
    }

    private void populateTable(XMLFixtureConnector xmlFixtureConnector, String[] columnHeaders, int numRows, int numColumn) {
        for(int i = 0; i < numRows; i++){
            for(int j = 1; j < numColumn; j++){
                if(table.getColumnModel().getColumn(j).getHeaderValue().equals(model.getValueAt(i, 0)))
                    model.setValueAt("######", i, j);
                else
                    for(int k = 0; k < xmlFixtureConnector.getListOfFixtures().size(); k++){
                        if((columnHeaders[j].equals(xmlFixtureConnector.getListOfFixtures().get(k).awayTeam))
                                && columnHeaders[i + 1].equals(xmlFixtureConnector.getListOfFixtures().get(k).homeTeam)){
                            model.setValueAt(xmlFixtureConnector.getListOfFixtures().get(k).score, i, j);
                        }
                    }
            }
        }
    }


    private void findPostponedGames(int numRows, int numColumn) {
        for(int i = 0; i < numRows; i++){
            for(int j = 1; j <  numColumn; j++){
                String value = (String) model.getValueAt(i, j);
                if(value == null)
                    model.setValueAt("GNP", i, j);
            }
        }
    }

    private void populateRemainingGames(int numRows, int numColumn) {
        for (int i = 0; i < numRows; i++) {
            for (int j = 1; j < numColumn; j++) {
                homeTeamName[i] = String.valueOf(table.getColumnModel().getColumn(j).getHeaderValue());
                awayTeamName[i] = String.valueOf(model.getValueAt(i, 0));
                if (model.getValueAt(i, j).equals("GNP")) {
                    PremierLeaguePredictions premierLeaguePredictions = new PremierLeaguePredictions(String.valueOf(table.getColumnModel().getColumn(j).getHeaderValue()),
                            String.valueOf(model.getValueAt(i, 0)));
                    model.setValueAt("*" + premierLeaguePredictions.getHomeScore() + "-" + premierLeaguePredictions.getAwayScore() + "*", i, j);
                    int homePoints = premierLeaguePredictions.getHomePoints();
                    int awayPoints = premierLeaguePredictions.getAwayPoints();
                    System.out.println(homeTeamName[i] + " " +homePoints + " - " + awayTeamName[i] + " " + awayPoints);
                }
            }
        }
    }
}
