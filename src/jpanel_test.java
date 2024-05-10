import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphicalUserInterface extends JFrame {

    private Board board;
    private JPanel boardPanel;
    private JLabel[][] boardLabels;
    private boolean isXTurn;
    private boolean gameOver;

    public GraphicalUserInterface() {
        setTitle("Tic-Tac-Toe");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        board = new Board(0, 0);

        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(3, 3));

        boardLabels = new JLabel[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardLabels[i][j] = new JLabel();
                boardLabels[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                boardLabels[i][j].setHorizontalAlignment(JLabel.CENTER);
                boardLabels[i][j].addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (!gameOver) {
                            int x = ((JLabel) e.getSource()).getX() / 100;
                            int y = ((JLabel) e.getSource()).getY() / 100;
                            if (board.getValue(x, y) == 0) {
                                if (isXTurn) {
                                    board.modifyBoard(x, y, 1);
                                    boardLabels[x][y].setText("X");
                                } else {
                                    board.modifyBoard(x, y, -1);
                                    boardLabels[x][y].setText("O");
                                }
                                isXTurn = !isXTurn;

                                int result = board.checkForWin();
                                if (result == 1) {
                                    JOptionPane.showMessageDialog(GraphicalUserInterface.this, "X wins!");
                                    gameOver = true;
                                } else if (result == -1) {
                                    JOptionPane.showMessageDialog(GraphicalUserInterface.this, "O wins!");
                                    gameOver = true;
                                }
                            }
                        }
                    }
                });
                boardPanel.add(boardLabels[i][j]);
            }
        }

        add(boardPanel);

        setVisible(true);
    }

    public static void main(String[] args) {
        new GraphicalUserInterface();
    }
}
