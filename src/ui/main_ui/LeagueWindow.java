package ui.main_ui;

import domain.TeamVerdict;
import org.xml.sax.SAXException;
import ui.betting_ui.BettingWindow;
import ui.results_ui.ResultsWindow;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LeagueWindow extends JFrame {
    public static final String TITLE = "Arithmetic Safety";
    public static final Dimension INITIAL_SIZE = new Dimension(1150, 450);
    public static final Point INITIAL_LOCATION = new Point(50, 62);
    public LeagueTable leagueTable;

    public LeagueWindow(){
        setTitle(TITLE);
        setSize(INITIAL_SIZE);
        setResizable(false);
        setLocation(INITIAL_LOCATION);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getContentPane().add(BorderLayout.NORTH, setupTopPanel());
        getContentPane().add(BorderLayout.SOUTH, new SouthPanel());
        getContentPane().add(BorderLayout.CENTER, leagueTable = new LeagueTable());

        setVisible(true);
    }

    private NorthPanel setupTopPanel(){
        final NorthPanel topPanel = new NorthPanel();

        topPanel.onCalculate(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                try {
                    if(topPanel.getSelectedValue() == null)
                        JOptionPane.showMessageDialog(null, "Please Select a League");
                    else {
                        if(topPanel.getSelectedValue().equals( "Please Select a League"))
                            JOptionPane.showMessageDialog(null, "Please Select a League");
                        else{
                            TeamVerdict leagueVerdict = new TeamVerdict(topPanel.getSelectedValue());
                            leagueTable.populateTable(leagueVerdict.getTeamList(), leagueVerdict.getVerdictArray(), leagueVerdict.getGamesRemaining());
                            if(leagueVerdict.getTeamList().get(1).gamesPlayed < 30){
                                int bottomDifference = leagueVerdict.getBottomDifference();
                                leagueTable.populateRelegationCell(bottomDifference);
                            }
                        }
                    }
                } catch (ParserConfigurationException e) {
                        e.printStackTrace();
                } catch (SAXException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }
            }
        });

        topPanel.onBettingButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new BettingWindow();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        topPanel.onResultsButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                javax.swing.SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            new ResultsWindow();
                        } catch (IOException e) {
                            e.printStackTrace();
                        } catch (SAXException e) {
                            e.printStackTrace();
                        } catch (ParserConfigurationException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

        return topPanel;
    }

}