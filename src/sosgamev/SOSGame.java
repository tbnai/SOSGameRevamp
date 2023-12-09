package sosgamev;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.function.BooleanSupplier;

public class SOSGame {
    private static Board Board;
    public static JRadioButton redButton = new JRadioButton("Red");
    public static JRadioButton blueButton = new JRadioButton("Blue");
    static JRadioButton sButton = new JRadioButton("S");
    static JRadioButton oButton = new JRadioButton("O");

    private static BufferedWriter writer;
    private static final String FILE_NAME = "game_record.txt";

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("SOS Game");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());

                try {
                    writer = new BufferedWriter(new FileWriter(FILE_NAME));
                } catch (IOException e) {
                    e.printStackTrace();
                }

                int boardSize = promptForBoardSize();
                Board = new Board(boardSize);
                frame.add(Board, BorderLayout.CENTER);
                frame.add(initializeControlPanel(), BorderLayout.NORTH);
                initializeMenu(frame);
                frame.pack();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            }
        });
    }

    public static void updatePlayerSelection() {
        if (Board.getCurrentColor() == Color.RED) {
            redButton.setSelected(true);
        } else {
            blueButton.setSelected(true);
        }
    }

    public static String promptForOpponentType() {
        String[] options = {"Human", "Computer", "Computer-vs-Computer"};
        return (String) JOptionPane.showInputDialog(null,
                "Choose your opponent:",
                "Select Opponent",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
    }

    public static Color promptForPlayerColor() {
        Object[] options = {"Red", "Blue"};
        int choice = JOptionPane.showOptionDialog(null, "Choose your color", "Color Selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        return choice == 0 ? Color.RED : Color.BLUE;
    }

    public static JPanel initializeControlPanel() {
        JPanel panel = new JPanel(new GridLayout(2, 1));

        JPanel colorPanel = new JPanel();

        ButtonGroup colorGroup = new ButtonGroup();
        colorGroup.add(redButton);
        colorGroup.add(blueButton);
        redButton.setSelected(true);
        redButton.addActionListener(e -> Board.setCurrentColor(Color.RED));
        blueButton.addActionListener(e -> Board.setCurrentColor(Color.BLUE));

        colorPanel.add(redButton);
        colorPanel.add(blueButton);

        JPanel letterPanel = new JPanel();
        ButtonGroup letterGroup = new ButtonGroup();
        letterGroup.add(sButton);
        letterGroup.add(oButton);
        sButton.setSelected(true);
        sButton.addActionListener(e -> Board.setCurrentLetter("S"));
        oButton.addActionListener(e -> Board.setCurrentLetter("O"));
        letterPanel.add(sButton);
        letterPanel.add(oButton);

        // Add a "Record" button
        JButton recordButton = new JButton("Record");
        recordButton.addActionListener(e -> recordGame());

        panel.add(colorPanel);
        panel.add(letterPanel);
        panel.add(recordButton);

        return panel;
    }

    private static void recordGame() {
        try {
            writer.write("Game recorded. Current scores - Red: " + Board.getRedScore() +
                    ", Blue: " + Board.getBlueScore() + "\n");
            writer.flush();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public static void initializeMenu(JFrame frame) {
        JMenuBar menuBar = new JMenuBar();
        JMenu gameMenu = new JMenu("Menu");
        JMenuItem newGame = new JMenuItem("New Game");

        newGame.addActionListener(e -> {
            int boardSize = promptForBoardSize();
            Board.setCurrentLetter("S");
            sButton.setSelected(true);

            Board newBoard = new Board(boardSize);

            frame.getContentPane().remove(Board);
            Board = newBoard;
            frame.add(Board, BorderLayout.CENTER);

            try {
                writer.write("Game started. Board size: " + boardSize + ", Game mode: " +
                        (Board.getGameMode().equals("simple") ? "Simple" : "General") + "\n");
                writer.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            frame.revalidate();
            frame.repaint();

            frame.pack();
            frame.setLocationRelativeTo(null);
        });

        gameMenu.add(newGame);
        menuBar.add(gameMenu);
        frame.setJMenuBar(menuBar);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });
    }

    public static int promptForBoardSize() {
        System.out.println("3---just entered promptForBoardSize method");
        Object[] possibleValues = {"3", "4", "5", "6", "7", "8"};
        int selectedOption = JOptionPane.showOptionDialog(null,
                "Select a board size:", "New Game",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null,
                possibleValues, possibleValues[5]);

        int boardSize = 8;
        if (selectedOption >= 0 && selectedOption <= 5) {
            boardSize = Integer.parseInt((String) possibleValues[selectedOption]);
        } else if (selectedOption == -1) {
            System.exit(0);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid input. Using default size of 8x8.", "Warning", JOptionPane.WARNING_MESSAGE);
        }

        System.out.println("4---done with the code to select size  ");

        System.out.println("5---game mode selection start  ");
        Object[] options = {"Simple", "General"};
        int n = JOptionPane.showOptionDialog(null,
                "Choose a game mode:",
                "Game Mode",
                JOptionPane.DEFAULT_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        if (n == 0) {
            Board.setGameMode("simple");
        } else {
            Board.setGameMode("general");
        }
        System.out.println("6---about to return board size and exit promptForBoardSize method in SOSGame3 class");
        return boardSize;
    }
}
