package ui.main_ui;

import domain.TeamVerdict;
import org.xml.sax.SAXException;
import ui.betting_ui.BettingWindow;
import ui.custom_ui.CustomLeagueWindow;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class LeagueWindow extends JFrame {
    public static final String TITLE = "Arithmetic Football";
    public static final Dimension INITIAL_SIZE = new Dimension(1350, 450);
    public static final Point INITIAL_LOCATION = new Point(300, 262);
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
        getContentPane().add(BorderLayout.EAST, setupEastPanel());

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
                        TeamVerdict leagueVerdict = new TeamVerdict(topPanel.getSelectedValue());
                        leagueTable.populateTable(leagueVerdict.getTeamList(), leagueVerdict.getVerdictArray(), leagueVerdict.getGamesRemaining());
                        if(leagueVerdict.getTeamList().get(1).getGamesPlayed() < 30){
                            int bottomDifference = leagueVerdict.getBottomDifference();
                            leagueTable.populateRelegationCell(bottomDifference);
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

        return topPanel;
    }

    private EastPanel setupEastPanel(){
        final EastPanel eastPanel = new EastPanel();

        eastPanel.onCreateTable(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                final String defaultValue = "1";
                final String teamAmount = eastPanel.getNewAmountValue();

                if(teamAmount == null){
                    javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            new CustomLeagueWindow(defaultValue);
                        }
                    });
                }
                else {
                    javax.swing.SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            new CustomLeagueWindow(teamAmount);
                        }
                    });
                }
            }
        });

        return eastPanel;
    }

}