package ui.test;

import org.junit.Before;
import org.junit.Test;
import ui.main_ui.LeagueTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static ui.test.TestHelpers.assertInstanceOf;

public class LeagueTableTest {

    private LeagueTable leagueTable;

    @Before
    public void setUp() throws Exception {
        leagueTable = new LeagueTable();
    }

    @Test
    public void beAJTable(){
        assertInstanceOf(new LeagueTable(), JTable.class);
    }

    @Test
    public void shouldHaveADefaultTableModel(){
        assertInstanceOf(leagueTable.getModel(), DefaultTableModel.class);
    }

    @Test
    public void shouldBeVisible(){
        assertTrue("Is visible", leagueTable.isVisible());
    }

    @Test
    public void shouldBeCorrectSize(){
        assertEquals("TeamVerdict Table Size", leagueTable.INITIAL_SIZE, leagueTable.getSize());
    }

    @Test
    public void flowLayout(){
        assertInstanceOf(leagueTable.getLayout(), FlowLayout.class);
    }

    @Test
    public void shouldBeAJScrollPane(){
        assertInstanceOf(leagueTable.getComponents()[1], JScrollPane.class);
    }
}
