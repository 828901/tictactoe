// Purpose: To store the game state of each individual tic-tac-toe board as a 2D array of integers.
// Authors: Harry and Noah
public class Board {
    private int winStatus = 0;
    private int[][] board = new int[3][3];

    // Author: Noah
    // Precondition: N/A
    // Postcondition: A new board objects is created and int[][] board is filled
    // with 0s.
    public Board() {
        for (int i = 0; i < 3; i++) {
            for (int x = 0; x < 3; x++) {
                board[i][x] = 0;
            }
        }
    }

    // Author: Noah
    // Precondition: board exists.
    // Postcondition: board is returned.
    // @return the 2D array of integers known as board.
    public int[][] getBoard() {
        return board;
    }

    // Author: Noah
    // Precondition: board exists and x and y are integers between 0 an 2
    // (inclusive)
    // Postcondition: The integer at a specific index in board is returned.
    // @param x an integer representing the row of the board being accessed
    // @param y an integer representing the column of the board being accessed
    // @return the integer at a specific index in board is returned.
    public int getValue(int x, int y) {
        return board[x][y];
    }

    // Author: Noah
    // Precondition: board exists.
    // Postcondition: The integer at board[x][y] is replaced with value.
    // @param x an integer representing the row of the board being changed.
    // @param y an integer representing the column of the board being changed.
    public void modifyBoard(int x, int y, int value) {
        board[x][y] = value;
    }

    // Author: Noah
    // Precondition: board exists.
    // Postcondition: A String representing the game state of a board is returned.
    // @return a String representing the game state of a board is returned.
    public String toString() {
        String string = "";
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                string += board[x][y] + ", ";
            }
        }
        return string;
    }

    // Author: Noah
    // Precondition: board exists.
    // Postcondition: winStatus is returned
    // @return winStatus 0 if no player has won; 1 if x has won; -1 if 0 has won.
    public int getWinStatus() {
        return winStatus;
    }

    // Author Harry
    // Precondition: board exists
    // Postcondition: The following is returned: 0 if no player has won; 1 if x has
    // won; -1 if 0 has won.
    // @return 0 if no player has won; 1 if x has won; -1 if 0 has won.
    public int checkForWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            int rowSum = board[i][0] + board[i][1] + board[i][2];
            if (rowSum == 3) {
                winStatus = 1;
                return 1; // Player with 'X' has won
            } else if (rowSum == -3) {
                winStatus = -1;
                return -1; // Player with 'O' has won
            }
        }

        // Check columns
        for (int i = 0; i < 3; i++) {
            int colSum = board[0][i] + board[1][i] + board[2][i];
            if (colSum == 3)
                return 1; // Player with 'X' has won
            else if (colSum == -3)
                return -1; // Player with 'O' has won
        }

        // Check diagonals
        int diagSum1 = board[0][0] + board[1][1] + board[2][2];
        int diagSum2 = board[0][2] + board[1][1] + board[2][0];
        if (diagSum1 == 3 || diagSum2 == 3)
            return 1; // Player with 'X' has won
        else if (diagSum1 == -3 || diagSum2 == -3)
            return -1; // Player with 'O' has won

        // No winner yet
        return 0;
    }

    // Author Harry
    // Precondition: board exists
    // Postcondition: Return true if the board is full, false otherwise.
    // @return true if the board is full, false otherwise.
    public boolean checkFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
