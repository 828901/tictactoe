import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

//Class Author: Noah, Harry
//Class Purpose: Display a graphical user interface of the board to the user and registers user mouse inputs for each turn of the game. 
public class GraphicalUserInterface extends JPanel {

    private static final int NUM_BOARDS = 3;
    private static final int BOARD_SIZE = 3;
    private static final int CELL_SIZE = 100;
    private int boardRow = 0, boardCol = 0, cellRow = 0, cellCol = 0;
    private Board[][] boards = new Board[NUM_BOARDS][NUM_BOARDS];
    private Graphics graphics;

    private int hightlightRow = -1;
    private int hightlightCol = -1;

    private int[][] boardsWon;

    //Author: Harry, Noah
    //Precondition: X and Y exist when mouse
    //Postcondition: Creates a new GUI as well as detector for when the mouse is clicked. 
    //@para: The board, 
    public GraphicalUserInterface(Board[][] boards, int[][] boardsWonIn) { //XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX boardsWonIn as param?
        boardsWon = boardsWonIn;
        this.boards = boards;
        setPreferredSize(new Dimension(NUM_BOARDS * BOARD_SIZE * CELL_SIZE, NUM_BOARDS * BOARD_SIZE * CELL_SIZE));
        setBackground(Color.WHITE);
        addMouseListener(new MouseAdapter() {

            //Author: Harry, Noah
            //Precondition: X and Y exist where mouse is clicked. 
            //Postcondition: The GUI is updated with the users input at the location of the mouse clicked. 
            //@para: Mouse clicked
            @Override
          public void mouseClicked(MouseEvent e) {
            int x = e.getX();
            int y = e.getY();
            registerUserInput(x, y);
            Main.update(graphics,boardRow,boardCol,cellRow,cellCol);
            }
        });
        
        
    }

    //Author: Harry
    //Precondition: X and Y are not beyond the borders of the board. 
    //Postcondition: the board/cell row and collumn are updated to the users location. XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX double check w/ harry
    //@para: The locations of x and y where the mouse is clicked. 
    private void registerUserInput(int x, int y) {
        // Determine the larger board
        boardRow = y / (BOARD_SIZE * CELL_SIZE);
        boardCol = x / (BOARD_SIZE * CELL_SIZE);

        // Determine the cell within the smaller board
        cellRow = (y % (BOARD_SIZE * CELL_SIZE)) / CELL_SIZE;
        cellCol = (x % (BOARD_SIZE * CELL_SIZE)) / CELL_SIZE;
      }
    
    //Author: Harry
    //Precondition: XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX WTF IS THE HIGHLIGHT ROW/COL for PRE 
    //Postcondition: The GUI's visual aspect is created. If a user has won a board, a large symbol (X/O) is drawn in accordance to the user that won. 
    //@para: The GUI 
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        graphics = g;

        Graphics2D g2d = (Graphics2D) g;
        g2d.setStroke(new BasicStroke(4f));

        if(hightlightRow != -1 && hightlightCol != -1){
            g.setColor(new Color(200,200,200));
            g.fillRect(hightlightRow*300,hightlightCol*300,BOARD_SIZE * CELL_SIZE,BOARD_SIZE * CELL_SIZE);
            g.setColor(Color.BLACK);
        }else{
            g.setColor(new Color(200,200,200));
            g.fillRect(0,0,900,900);
            g.setColor(Color.BLACK);
        }
        
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
                       //System.out.print("\n" + r + " " + c);
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
    //Author: Harry
    //Precondition: The board/cell row and collumn exist and are not larger or smaller than the board size. 
    //Postcondition: An "X" is drawn in the specific cell. 
    //@para: The GUI of the board, the board/cell row and collumn.  
    private void drawX(Graphics2D g2d, int boardRow, int boardCol, int cellRow, int cellCol) {
        g2d.setStroke(new BasicStroke(4f));
        int x = boardCol * BOARD_SIZE * CELL_SIZE + cellCol * CELL_SIZE;
        int y = boardRow * BOARD_SIZE * CELL_SIZE + cellRow * CELL_SIZE;
        g2d.drawLine(x + 10, y + 10, x + CELL_SIZE - 10, y + CELL_SIZE - 10);
        g2d.drawLine(x + CELL_SIZE - 10, y + 10, x + 10, y + CELL_SIZE - 10);
    }
    
    //Author: Harry
    //Precondition: The board/cell row and collumn exist and are not larger or smaller than the board size. 
    //Postcondition: An "O" is drawn in the specific cell. 
    //@para: The GUI of the board, the board/cell row and collumn.  
    private void drawO(Graphics2D g2d, int boardRow, int boardCol, int cellRow, int cellCol) {
        g2d.setStroke(new BasicStroke(4f));
        int x = boardCol * BOARD_SIZE * CELL_SIZE + cellCol * CELL_SIZE;
        int y = boardRow * BOARD_SIZE * CELL_SIZE + cellRow * CELL_SIZE;
        g2d.drawOval(x + 10, y + 10, CELL_SIZE - 20, CELL_SIZE - 20);
    }
    
    //Author: Harry
    //Precondition: The board row and collumn exist and are not larger or smaller than the board size. 
    //Postcondition: An "X" is drawn in the specific cell. 
    //@para: The GUI of the board, the board row and collumn.  
    private void drawBigRedX(Graphics2D g2d, int boardRow, int boardCol) {
        boardsWon[boardRow][boardCol] = 1;
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(10f));
        int x = boardCol * BOARD_SIZE * CELL_SIZE;
        int y = boardRow * BOARD_SIZE * CELL_SIZE;
        g2d.drawLine(x + 10, y + 10, x + BOARD_SIZE * CELL_SIZE - 10, y + BOARD_SIZE * CELL_SIZE - 10);
        g2d.drawLine(x + BOARD_SIZE * CELL_SIZE - 10, y + 10, x + 10, y + BOARD_SIZE * CELL_SIZE - 10);
        g2d.setColor(Color.BLACK);
      }
    
    //Author: Harry
    //Precondition: The board row and collumn exist and are not larger or smaller than the board size. 
    //Postcondition: An "O" is drawn in the specific cell. 
    //@para: The GUI of the board, the board row and collumn. 
    private void drawBigRedO(Graphics2D g2d, int boardRow, int boardCol) {
        boardsWon[boardRow][boardCol] = -1;
        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(10f));
        int x = boardCol * BOARD_SIZE * CELL_SIZE;
        int y = boardRow * BOARD_SIZE * CELL_SIZE;
        g2d.drawOval(x + 10, y + 10, BOARD_SIZE * CELL_SIZE - 20, BOARD_SIZE * CELL_SIZE - 20);
        g2d.setColor(Color.BLACK);
    }

    //Author: Noah
    //Precondition: The board row and collumn exist and are not larger or smaller than the board size. 
    //Postcondition: The cell for which the user needs to make the next move on the board is highlighted. 
    //@para: The GUI of the board, the board row and collumn. 
    public void drawHighlight(Graphics g, int row, int col){
        hightlightRow = row;
        hightlightCol = col;
    }
    
    //Author: Noah
    //Precondition: The board row and collumn exist and are not larger or smaller than the board size. 
    //Postcondition: The board is updated with the inputed board. 
    //@para: The board. 
    private void updateBoard(Board[][] boards) {this.boards = boards;}
    
}
