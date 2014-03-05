package ui.test;

import org.junit.Before;
import org.junit.Test;
import ui.main_ui.BottomPanel;

import javax.swing.*;

import static junit.framework.Assert.assertEquals;
import static ui.test.TestHelpers.assertInstanceOf;

public class BottomPanelTest {

    private BottomPanel bottomPanel;

    @Before
    public void setUp() throws Exception {
        bottomPanel = new BottomPanel();
    }

    @Test
    public void beAPanel(){
        assertInstanceOf(new BottomPanel(), JPanel.class);
    }

    @Test
    public void shouldHaveACalculateButton(){
        assertIsAButton("Calculate", atIndex(0));
        assertIsAButton("Betting", atIndex(1));
        assertIsAButton("Quit", atIndex(2));
    }

    public void assertIsAButton(String name, int index) {
        assertEquals(name + " button", bottomPanel.getComponents()[index].getClass(), JButton.class);
        assertEquals(name + " button", ((JButton) bottomPanel.getComponents()[index]).getText(), name);
    }

    private int atIndex(int index) {
        return index;
    }

}
