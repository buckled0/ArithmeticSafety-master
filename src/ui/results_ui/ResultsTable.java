package ui.results_ui;

import org.xml.sax.SAXException;
import xmlconnector.XMLLeagueConnector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

public class ResultsTable extends JTable{
    public static final Dimension INITIAL_SIZE = new Dimension(1025, 240);
    DefaultTableModel model;
    JTable table;

    public ResultsTable() throws ParserConfigurationException, SAXException, IOException {
        model = new DefaultTableModel();
        XMLLeagueConnector xmlLeagueConnector = new XMLLeagueConnector("Premier League");
        String[] columnHeaders = new String[21];
        for(int i = 1; i < xmlLeagueConnector.getCurrentPremLeagueList().size(); i++){
            columnHeaders[i] = xmlLeagueConnector.getCurrentPremLeagueList().get(i).teamName;
        }
        model.setColumnIdentifiers(columnHeaders);
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

        add(pane);
    }
}
