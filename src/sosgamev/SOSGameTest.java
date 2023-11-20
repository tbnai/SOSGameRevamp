package sosgamev;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

class SOSGameTest<JPanel> {

    @Test
    void testUpdatePlayerSelection() {
        // Assuming Board.setCurrentColor and SOSGame.updatePlayerSelection are working correctly
        Board.setCurrentColor(Color.RED);
        SOSGame.updatePlayerSelection();
        assertTrue(SOSGame.redButton.isSelected());
        assertFalse(SOSGame.blueButton.isSelected());

        Board.setCurrentColor(Color.BLUE);
        SOSGame.updatePlayerSelection();
        assertFalse(SOSGame.redButton.isSelected());
        assertTrue(SOSGame.blueButton.isSelected());
    }

    @Test
    void testInitializeControlPanel() {
        // Assuming SOSGame.initializeControlPanel and related methods work correctly
        JPanel panel = (JPanel) SOSGame.initializeControlPanel();
        assertNotNull(panel);
        assertEquals(2, ((Container) panel).getComponentCount());

        // Assuming redButton and blueButton are properly initialized
        assertTrue(SOSGame.redButton.isSelected());
        assertFalse(SOSGame.blueButton.isSelected());

        // Assuming sButton and oButton are properly initialized
        assertTrue(SOSGame.sButton.isSelected());
        assertFalse(SOSGame.oButton.isSelected());
    }

    @Test
    void testInitializeMenu() {
        // Assuming SOSGame.initializeMenu works correctly
        JFrame frame = new JFrame();
        SOSGame.initializeMenu(frame);

        // Assuming the menu bar and menu items are properly initialized
        assertNotNull(frame.getJMenuBar());
        assertEquals(1, frame.getJMenuBar().getMenuCount());
        assertNotNull(frame.getJMenuBar().getMenu(0));
        assertEquals("Menu", frame.getJMenuBar().getMenu(0).getText());
        assertEquals(1, frame.getJMenuBar().getMenu(0).getItemCount());
        assertNotNull(frame.getJMenuBar().getMenu(0).getItem(0));
        assertEquals("New Game", frame.getJMenuBar().getMenu(0).getItem(0).getText());
    }

}

