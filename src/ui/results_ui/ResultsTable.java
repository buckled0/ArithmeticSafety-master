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
            else if(teamName.equals("West Ham"))
                smallName = "WHam";
            else if(teamName.equals("West Brom"))
                smallName = "WBrom";
            else
                smallName = teamName.substring(0, 3);
            columnHeaders[i] = smallName;
            System.out.println(columnHeaders[i]);
        }
        int numRows = 20;
        model = new DefaultTableModel(numRows, columnHeaders.length);

        model.setColumnIdentifiers(columnHeaders);
        int numColumn = model.getColumnCount();
        table = new JTable(model){
            @Override
            public boolean isCellEditable(int row, int column) {
                if(column == 0)
                    return false;
                else
                    return true;
            }};

        for(int i = 0; i < columnHeaders.length - 1; i++){
            model.setValueAt(columnHeaders[i + 1], i, 0);
        }

        findTheIntersections(numRows, numColumn);

        JScrollPane pane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setPreferredSize(INITIAL_SIZE);
        setVisible(true);
        setLayout(new FlowLayout());

        add(pane);
    }

    private void findTheIntersections(int numRows, int numColumn) {
        for(int i = 0; i < numRows; i++){
            for(int j = 1; j < numColumn; j++){
                if(table.getColumnModel().getColumn(j).getHeaderValue().equals(model.getValueAt(i, 0)))
                    model.setValueAt("######", i, j);
            }
        }
    }
}
