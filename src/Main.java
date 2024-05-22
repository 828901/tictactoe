import java.awt.*;
import javax.swing.*;

public class Main {
    static JFrame frame = new JFrame();
    static Board[][] data = new Board[3][3];
    static int currentPlayer = 1;// 1 for X, -1 for O
    static boolean pickAnywhere = true;
    static int lastRow, lastCol;
    static GraphicalUserInterface gui = new GraphicalUserInterface(data);

    public static void update(Graphics g, int boardRow, int boardCol, int cellRow, int cellCol){
        //there is currently a few bugs
        //if you have a board selected you can click on another board and choose a different spot
        //if you end up in a draw case, you are stuck.

            if((boardRow == lastRow && boardCol == lastCol) || pickAnywhere){
                if(((data[boardRow][boardCol].getValue(cellRow,cellCol) == 0) && data[boardRow][boardCol].checkForWin() == 0)){
                    lastRow = cellRow;
                    lastCol = cellCol;
                    data[boardRow][boardCol].modifyBoard(cellRow,cellCol,currentPlayer);
                    currentPlayer *= -1;
                    frame.repaint();
                    gui.drawHighlight(g,lastCol,lastRow);
                    pickAnywhere = false;
                }
            }else{
                lastRow = cellRow;
                lastCol = cellCol;
                frame.repaint();
                gui.drawHighlight(g,-1,-1);
                pickAnywhere = true;
        }


    }

    public static void main(String[] args) {



        // Scanner scanner = new Scanner(System.in);

        //Creates 3x3 Grid
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                data[r][c] = new Board();
            }
        }

        /*Random rand = new Random();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        data[r][c].modifyBoard(x, y, rand.nextInt(3) - 1);
                    }
                }
            }
        }*/


        frame.setTitle("Ultimate Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.add(gui);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
    

    public static int checkBoard(Board[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].checkForWin() != 0 && board[i][0].checkForWin() == board[i][1].checkForWin()
                    && board[i][0].checkForWin() == board[i][2].checkForWin())
                return board[i][0].checkForWin();
            if (board[0][i].checkForWin() != 0 && board[0][i].checkForWin() == board[1][i].checkForWin()
                    && board[0][i].checkForWin() == board[2][i].checkForWin())
                return board[0][i].checkForWin();
        }
        if (board[0][0].checkForWin() != 0 && board[0][0].checkForWin() == board[1][1].checkForWin()
                && board[0][0].checkForWin() == board[2][2].checkForWin())
            return board[0][0].checkForWin();
        if (board[0][2].checkForWin() != 0 && board[0][2].checkForWin() == board[1][1].checkForWin()
                && board[0][2].checkForWin() == board[2][0].checkForWin())
            return board[0][2].checkForWin();
        return 0;
    }
}
