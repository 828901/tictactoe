import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GraphicalUserInterface extends JPanel {

    private static final int NUM_BOARDS = 3;
    private static final int BOARD_SIZE = 3;
    private static final int CELL_SIZE = 100;
    private int boardRow = 0, boardCol = 0, cellRow = 0, cellCol = 0;
    private Board[][] boards = new Board[NUM_BOARDS][NUM_BOARDS];

    public GraphicalUserInterface(Board[][] boards) {
        this.boards = boards;
        setPreferredSize(new Dimension(NUM_BOARDS * BOARD_SIZE * CELL_SIZE, NUM_BOARDS * BOARD_SIZE * CELL_SIZE));
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {
          @Override
          public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            registerUserInput(x, y);
            System.out.print(boardRow + "" + boardCol + "" + cellRow + "" + cellCol);
            }
        });
        
        
    }

    private void registerUserInput(int x, int y) {
        // Determine the larger board
        boardRow = y / (BOARD_SIZE * CELL_SIZE);
        boardCol = x / (BOARD_SIZE * CELL_SIZE);

        // Determine the cell within the smaller board
        cellRow = (y % (BOARD_SIZE * CELL_SIZE)) / CELL_SIZE;
        cellCol = (x % (BOARD_SIZE * CELL_SIZE)) / CELL_SIZE;

        // UPDATE THE BOARD HERE

    
        System.out.println("\nLarger board row: " + boardRow + ", col: " + boardCol);
        System.out.println("Smaller board row: " + cellRow + ", col: " + cellCol);       
        
      }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4f));
        
        for (int i = 0; i < NUM_BOARDS; i++) {
            for (int j = 0; j < NUM_BOARDS; j++) {
                g.drawLine(i * BOARD_SIZE * CELL_SIZE, j * BOARD_SIZE * CELL_SIZE,
                        i * BOARD_SIZE * CELL_SIZE + BOARD_SIZE * CELL_SIZE, j * BOARD_SIZE * CELL_SIZE);
                g.drawLine(i * BOARD_SIZE * CELL_SIZE, j * BOARD_SIZE * CELL_SIZE,
                        i * BOARD_SIZE * CELL_SIZE, j * BOARD_SIZE * CELL_SIZE + BOARD_SIZE * CELL_SIZE);
            }
        }

        g2d.setStroke(new BasicStroke(2f));

        // Draw the small tic-tac-toe board lines
        for (int i = 0; i < NUM_BOARDS; i++) {
            for (int j = 0; j < NUM_BOARDS; j++) {
                for (int k = 0; k < BOARD_SIZE; k++) {
                    for (int l = 0; l < BOARD_SIZE; l++) {
                        g.drawLine(i * BOARD_SIZE * CELL_SIZE + k * CELL_SIZE, j * BOARD_SIZE * CELL_SIZE + l * CELL_SIZE,
                                i * BOARD_SIZE * CELL_SIZE + k * CELL_SIZE + CELL_SIZE, j * BOARD_SIZE * CELL_SIZE + l * CELL_SIZE);
                        g.drawLine(i * BOARD_SIZE * CELL_SIZE + k * CELL_SIZE, j * BOARD_SIZE * CELL_SIZE + l * CELL_SIZE,
                                i * BOARD_SIZE * CELL_SIZE + k * CELL_SIZE, j * BOARD_SIZE * CELL_SIZE + l * CELL_SIZE + CELL_SIZE);
                    }
                }
            }
        }
        
        
        for (int r = 0; r < 3; r++) {
               for (int c = 0; c < 3; c++) {
                   if (boards[r][c].checkForWin() != 0) {
                       System.out.print("\n" + r + " " + c);
                       if (boards[r][c].checkForWin() == 1) drawBigRedX(g2d, r, c);
                       else drawBigRedO(g2d, r, c);
                       continue;
                   }
                   for (int x = 0; x < 3; x++) {
                       for (int y = 0; y < 3; y++) {
                           if (boards[r][c].getValue(x, y) == 1)
                               drawX(g2d, r, c, x, y);
                           else if (boards[r][c].getValue(x, y) == -1)
                               drawO(g2d, r, c, x, y);
                       }
                   }
               }
        }
        
    }

    // Method to draw X or O in a cell
    private void drawX(Graphics2D g2d, int boardRow, int boardCol, int cellRow, int cellCol) {
        g2d.setStroke(new BasicStroke(4f));
        int x = boardCol * BOARD_SIZE * CELL_SIZE + cellCol * CELL_SIZE;
        int y = boardRow * BOARD_SIZE * CELL_SIZE + cellRow * CELL_SIZE;
        g2d.drawLine(x + 10, y + 10, x + CELL_SIZE - 10, y + CELL_SIZE - 10);
        g2d.drawLine(x + CELL_SIZE - 10, y + 10, x + 10, y + CELL_SIZE - 10);
    }
    private void drawO(Graphics2D g2d, int boardRow, int boardCol, int cellRow, int cellCol) {
        g2d.setStroke(new BasicStroke(4f));
        int x = boardCol * BOARD_SIZE * CELL_SIZE + cellCol * CELL_SIZE;
        int y = boardRow * BOARD_SIZE * CELL_SIZE + cellRow * CELL_SIZE;
        g2d.drawOval(x + 10, y + 10, CELL_SIZE - 20, CELL_SIZE - 20);
    }
    private void drawBigRedX(Graphics2D g2d, int boardRow, int boardCol) {
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(10f));
        int x = boardCol * BOARD_SIZE * CELL_SIZE;
        int y = boardRow * BOARD_SIZE * CELL_SIZE;
        g2d.drawLine(x + 10, y + 10, x + BOARD_SIZE * CELL_SIZE - 10, y + BOARD_SIZE * CELL_SIZE - 10);
        g2d.drawLine(x + BOARD_SIZE * CELL_SIZE - 10, y + 10, x + 10, y + BOARD_SIZE * CELL_SIZE - 10);
        g2d.setColor(Color.BLACK);
      }
    private void drawBigRedO(Graphics2D g2d, int boardRow, int boardCol) {
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(10f));
        int x = boardCol * BOARD_SIZE * CELL_SIZE;
        int y = boardRow * BOARD_SIZE * CELL_SIZE;
        g2d.drawOval(x + 10, y + 10, BOARD_SIZE * CELL_SIZE - 20, BOARD_SIZE * CELL_SIZE - 20);
        g2d.setColor(Color.BLACK);
    }
    private void updateBoard(Board[][] boards) {this.boards = boards;}
    
}
