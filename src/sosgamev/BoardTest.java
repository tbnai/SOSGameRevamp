package sosgamev;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

class BoardTest {

    @Test
    void testSetRedScore() {
        Board board = new Board(8);
        board.setRedScore(5);
        assertEquals(5, board.getRedScore());
    }

    @Test
    void testSetBlueScore() {
        Board board = new Board(8);
        board.setBlueScore(3);
        assertEquals(3, board.getBlueScore());
    }

}
