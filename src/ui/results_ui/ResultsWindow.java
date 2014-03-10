package ui.results_ui;

import org.xml.sax.SAXException;

import javax.swing.*;
import javax.xml.parsers.ParserConfigurationException;
import java.awt.*;
import java.io.IOException;

public class ResultsWindow extends JFrame{

    public static final String TITLE = "Premier League Results";
    public static final Dimension INITIAL_SIZE = new Dimension(1550, 600);
    public static final Point INITIAL_LOCATION = new Point(300, 262);

    public ResultsWindow() throws IOException, SAXException, ParserConfigurationException {
        setTitle(TITLE);
        setSize(INITIAL_SIZE);
        setResizable(false);
        setLocation(INITIAL_LOCATION);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        getContentPane().add(BorderLayout.CENTER, new ResultsTable());

        setVisible(true);
    }

}
