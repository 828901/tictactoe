import java.util.*;
//https://en.wikipedia.org/wiki/Box-drawing_characters
public class SprintTwoParker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Creates 3x3 Grid
        Board[][] data = new Board[3][3];
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                data[r][c] = new Board(3,3);
            }
        }

        //Randomly fills all 9 games in a grid for testing purposes
        Random rand = new Random();
        for(int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                for (int x = 0; x < 3; x++) {
                    for (int y = 0; y < 3; y++) {
                        data[r][c].modifyBoard(x, y, rand.nextInt(3) - 1);
                    }
                }
                //System.out.println(temp);
            }

        }

        if (checkBoard(data)==1) System.out.print("X won!");
        else if (checkBoard(data)==-1) System.out.print("O won!");
        else System.out.print("No win yet!");


            //DisplayGrid.render(data);


//        int currentPlayer = 1; // Player 1 starts (X)
//        Board gameBoard = new Board(0,0);
//        while (true) {
//            Display.render(gameBoard); // Display the current board
//
//            System.out.println("\nPlayer " + (currentPlayer == 1 ? "'X'" : "'O'") + ", make your move!");
//            int row = -1;
//            int column = -1;
//            boolean validInput = false;
//            while (!validInput) {
//                try {
//                    System.out.print("Enter row (0-2): ");
//                    row = Integer.parseInt(scanner.nextLine());
//                    System.out.print("Enter column (0-2): ");
//                    column = Integer.parseInt(scanner.nextLine());
//                    validInput = true;
//                } catch (NumberFormatException e) {
//                    System.out.println("Invalid input. Please enter an integer.");
//                }
//            }
//
//            // Validate input
//            if (row < 0 || row > 2 || column < 0 || column > 2 || gameBoard.getBoard()[row][column] != 0) {
//                System.out.println("\nInvalid move. Try again.");
//                continue;
//            }
//
//            // Update the board
//            gameBoard.modifyBoard(row, column, currentPlayer);
//
//            if (gameBoard.checkForWin() == 1) {
//                Display.render(gameBoard);
//                System.out.println("\nCongratulations! Player with 'X' has won.");
//                break;
//            }
//            else if (gameBoard.checkForWin() == -1) {
//                Display.render(gameBoard);
//                System.out.println("\nCongratulations! Player with the 'O' has won");
//                break;
//            }
//            // Switch players
//            currentPlayer = (currentPlayer == 1) ? -1 : 1;
//        }
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

  //Checking board, return coord of next board, if won return 0
  public static int[] getNextBoard(int r, int c){
        int[] coord = [r,c];
      int[] anywhere = [;
      if(data[r][c].checkForWin() != 0){
          return coord;
      }
      else{
          return 
  }
  
}
