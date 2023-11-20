package sosgamev;

import javax.swing.JButton;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.Color;

public class Button extends JButton {
    private int row;
    private int col;

    public Button(int row, int col) {
        this.row = row;
        this.col = col;
        setPreferredSize(new Dimension(50, 50));
        setBorder(BorderFactory.createLineBorder(Color.GRAY));
        setBackground(Color.WHITE);  // Set default background color
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
