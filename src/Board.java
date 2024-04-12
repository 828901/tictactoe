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
}
