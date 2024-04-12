public class Board {

    //row and column might not be necessary, they were originally added in case you wanted to store their position on a grid of boards
    private static int row;
    private static int column;

    private int[][] board = new int[3][3];

    public Board(int row, int column) {
        row = row;
        column = column;
        for(int i = 0; i < 3; i++){
            for(int x = 0; x < 3; x++){
                board[i][x] = 0;
            }
        }
    }

    public int[][] getBoard(){
        return board;
    }

    public void modifyBoard(int x, int y, int value){
        board[x][y] = value;
    }

    public int getRow(){
        return row;
    }
    public int getColumn(){
        return column;
    }
    public int checkForWin() {
        // Check rows
        for (int i = 0; i < 3; i++) {
            int rowSum = board[i][0] + board[i][1] + board[i][2];
            if (rowSum == 3)
                return 1; // Player with 'X' has won
            else if (rowSum == -3)
                return -1; // Player with 'O' has won
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
}
