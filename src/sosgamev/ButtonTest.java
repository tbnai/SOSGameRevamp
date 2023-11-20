package sosgamev;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ButtonTest {

    @Test
    void testButtonInitialization() {
        Button button = new Button(1, 2);
        assertEquals(1, button.getRow());
        assertEquals(2, button.getCol());
        assertEquals("", button.getText());
    }

}
