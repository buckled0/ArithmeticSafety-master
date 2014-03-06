package ui.test;

import org.junit.Before;
import org.junit.Test;
import ui.main_ui.NorthPanel;

import javax.swing.*;

import static junit.framework.Assert.assertEquals;
import static ui.test.TestHelpers.assertInstanceOf;

public class TopPanelTest {

    private NorthPanel topPanel;

    @Before
    public void setUp() throws Exception {
        topPanel = new NorthPanel();
    }

    @Test
    public void beAJPanel(){
        assertInstanceOf(new NorthPanel(), JPanel.class);
    }

    @Test
    public void shouldHaveComboBox(){
        int index = 0;
        assertEquals("Should have a combobox", topPanel.getComponents()[index].getClass(), JComboBox.class);
    }


    @Test
    public void shouldHaveARoundsComboBox(){
        int index = 1;
        assertEquals("Should have a combo box", topPanel.getComponents()[index].getClass(), JComboBox.class);
    }

}
