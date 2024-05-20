import java.util.*;
import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // Scanner scanner = new Scanner(System.in);

        //Creates 3x3 Grid
        public Board[][] data = new Board[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                data[r][c] = new Board(3, 3);
            }
        }

        JFrame frame = new JFrame();
        frame.setTitle("Ultimate Tic-Tac-Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GraphicalUserInterface gui = new GraphicalUserInterface(data);
        frame.add(gui);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        //Add game loop here
        int currentPlayer = 1; // 1 for X, -1 for O
        boolean gameOver = false;

        Scanner scan = new Scanner(System.in);
        System.out.println("enter row col");
        int bRow = scan.nextInt();
        int bCol = scan.nextInt();
        System.out.println("enter row col");
        int sRow = scan.nextInt();
        int sCol = scan.nextInt();

        data[bRow][bCol].modifyBoard(sRow, sCol, currentPlayer);

        while (gameOver == false) {
            //add latency
            
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            frame.repaint();
        }

        
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
