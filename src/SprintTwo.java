import java.util.*;
//https://en.wikipedia.org/wiki/Box-drawing_characters
public class SprintTwo {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Creates 3x3 Grid
        Board[][] data = new Board[3][3];
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                data[r][c] = new Board(3, 3);
            }
        }

        //Randomly fills all 9 games in a grid for testing purposes
        Random rand = new Random();
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        data[r][c].modifyBoard(x, y, rand.nextInt(3) - 1);
                    }
                }
            }

        }

        DisplayGrid.render(data);

    }

    public static int checkBoard(Board[][] board) {
        for (int i = 0; i < 3; i++) {
            if (board[i][0].checkForWin() != 0 && board[i][0].checkForWin() == board[i][1].checkForWin() && board[i][0].checkForWin() == board[i][2].checkForWin())
                return board[i][0].checkForWin();
            if (board[0][i].checkForWin() != 0 && board[0][i].checkForWin() == board[1][i].checkForWin() && board[0][i].checkForWin() == board[2][i].checkForWin())
                return board[0][i].checkForWin();
        }
        if (board[0][0].checkForWin() != 0 && board[0][0].checkForWin() == board[1][1].checkForWin() && board[0][0].checkForWin() == board[2][2].checkForWin())
            return board[0][0].checkForWin();
        if (board[0][2].checkForWin() != 0 && board[0][2].checkForWin() == board[1][1].checkForWin() && board[0][2].checkForWin() == board[2][0].checkForWin())
            return board[0][2].checkForWin();
        return 0;
    }
}