package ui.test;

import org.junit.Before;
import org.junit.Test;
import ui.main_ui.LeagueWindow;

import javax.swing.*;
import java.awt.*;

import static org.junit.Assert.*;
import static ui.test.TestHelpers.assertInstanceOf;

public class LeagueWindowTest {
    private LeagueWindow lw;

    @Before
    public void setUp() throws Exception {
        lw = new LeagueWindow();
    }

    @Test
    public void isAJFrame(){
        assertInstanceOf(lw, JFrame.class);
    }
    
    @Test
    public void shouldHaveATitle(){
        assertEquals(LeagueWindow.TITLE, lw.getTitle());
    }

    @Test
    public void shouldTheWindowBeVisible(){
        assertTrue("Is Visible", lw.isVisible());
    }

    @Test
    public void shouldBeCorrectSize(){
        assertFalse(lw.isResizable());
        assertEquals("league table size", lw.INITIAL_SIZE, lw.getSize());
    }

    @Test
    public void shouldBeInCorrectPosition(){
        assertEquals("league table position", new Point(150, 22), lw.getLocation());
    }

    @Test
    public void shouldExitOnClose(){
        assertEquals("close operation", WindowConstants.EXIT_ON_CLOSE, lw.getDefaultCloseOperation());
    }

    @Test
    public void borderLayout(){
        assertInstanceOf(lw.getContentPane().getLayout(), BorderLayout.class);
    }
    @Test
    public void shouldATableAppear(){
        assertInstanceOf(lw.getContentPane().getComponents()[0], JTable.class);
    }

    @Test
    public void shouldHaveAJPanel(){
        assertInstanceOf(lw.getContentPane().getComponents()[1], JPanel.class);
    }

    @Test
    public void shouldHaveANorthJPanel(){
        assertInstanceOf(lw.getContentPane().getComponents()[2], JPanel.class);
    }
}
