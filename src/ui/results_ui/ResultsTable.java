package ui.results_ui;

import org.xml.sax.SAXException;
import xmlconnector.XMLLeagueConnector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

public class ResultsTable extends JTable{
    public static final Dimension INITIAL_SIZE = new Dimension(1500, 342);
    DefaultTableModel model;
    JTable table;

    public ResultsTable() throws ParserConfigurationException, SAXException, IOException {
        XMLLeagueConnector xmlLeagueConnector = new XMLLeagueConnector("Premier League");
        String[] columnHeaders = new String[21];
        columnHeaders[0] = " ";
        String smallName;
        for(int i = 1; i < xmlLeagueConnector.getListOfTeams().size() + 1; i++){
            String teamName = xmlLeagueConnector.getListOfTeams().get(i-1).name;
            if(teamName.equals("Man Utd"))
                smallName = "Man U";
            else if (teamName.equals("Man City"))
                smallName = "Man C";
            else
                smallName = teamName.substring(0, 3);
            columnHeaders[i] = smallName;
            System.out.println(columnHeaders[i]);
        }
        int numRows = 20;
        model = new DefaultTableModel(numRows, columnHeaders.length);

        model.setColumnIdentifiers(columnHeaders);

        table = new JTable(model){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0)
                    return false;
                else
                    return true;
            }};

        for(int i = 0; i < columnHeaders.length - 1; i++){
            for(int j = 0; j < numRows; j++){
                if(table.getColumnName(i + 1).equals(table.getValueAt(j, 0)))
                    model.setValueAt("#######", i - 1, j);
                else
                    model.setValueAt(columnHeaders[i + 1], i, 0);
            }
        }

        JScrollPane pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setPreferredSize(INITIAL_SIZE);
        setVisible(true);
        setLayout(new FlowLayout());



        add(pane);
    }
}
