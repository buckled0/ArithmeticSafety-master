package ui.results_ui;

import domain.Game;
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
import java.util.*;

public class ResultsTable extends JTable implements TableModelListener{
    public static final Dimension INITIAL_SIZE = new Dimension(1895, 342);
    DefaultTableModel model;
    JTable table;
    String[] homeTeamName;
    String[] awayTeamName;
    Team[] setTeams;
    ArrayList<Game> gamesLeft = new ArrayList<Game>();

    public ResultsTable() throws ParserConfigurationException, SAXException, IOException {
        XMLLeagueConnector xmlLeagueConnector = new XMLLeagueConnector("Premier League");
        XMLFixtureConnector xmlFixtureConnector = new XMLFixtureConnector();
        String[] columnHeaders = new String[21];
        columnHeaders[0] = " ";
        setTeams = new Team[20];
        awayTeamName = new String[20];
        homeTeamName = new String[20];

        for (int i = 1; i < xmlLeagueConnector.getListOfTeams().size() + 1; i++) {
            Team team = xmlLeagueConnector.getListOfTeams().get(i - 1);
            setTeams[i-1] = team;
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

    public void populateRemainingGames(int numRows, int numColumn) {
        for (int i = 0; i < numRows; i++) {
            for (int j = 1; j < numColumn; j++) {
                if (model.getValueAt(i, j).equals("GNP")) {
                    PremierLeaguePredictions premierLeaguePredictions = new PremierLeaguePredictions(String.valueOf(table.getColumnModel().getColumn(j).getHeaderValue()),
                            String.valueOf(model.getValueAt(i, 0)));
                    int homePoints = premierLeaguePredictions.homePoints;
                    int awayPoints = premierLeaguePredictions.awayPoints;
                    int homeGD = premierLeaguePredictions.homeGD;
                    int awayGD = premierLeaguePredictions.awayGD;
                    String score = premierLeaguePredictions.homeScore + "-" + premierLeaguePredictions.awayScore;
                    gamesLeft.add(i, new Game(String.valueOf(model.getValueAt(i, 0)),
                            String.valueOf(table.getColumnModel().getColumn(j).getHeaderValue()), score));
                    for(int k = 0; k < setTeams.length; k++){
                        if(setTeams[k].name.equals(String.valueOf(model.getValueAt(i, 0)))){
                            setTeams[k].points += homePoints;
                            setTeams[k].goalDifference += homeGD;
                        }
                        if(setTeams[k].name.equals(table.getColumnModel().getColumn(j).getHeaderValue())){
                            setTeams[k].points += awayPoints;
                            setTeams[k].goalDifference += awayGD;
                        }
                    }
                    model.setValueAt("*" + premierLeaguePredictions.homeScore + "-" + premierLeaguePredictions.awayScore + "*", i, j);
                }
            }
        }
        Arrays.sort(setTeams, new Comparator<Team>() {
            @Override
            public int compare(Team team, Team team2) {
                return team2.points - team.points;
            }
        });
        Collections.sort(gamesLeft, new Comparator<Game>() {
            @Override
            public int compare(Game game, Game game2) {
                return game.homeTeam.compareTo(game2.homeTeam);
            }
        });
    }

}
