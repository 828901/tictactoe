import java.util.*;
//https://en.wikipedia.org/wiki/Box-drawing_characters
public class SprintOneFinal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //Creates 3x3 Grid
        Board[][] data = new Board[3][3];
        for(int x = 0; x < 3; x++){
            for(int y = 0; y < 3; y++){
                data[x][y] = new Board(0,0);
            }
        }

        //Randomly fills all 9 games in a grid for testing purposes
        Random rand = new Random();
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++){
                Board temp = data[r][c];
                for(int x = 0; x < 3; x++){
                    for(int y = 0; y < 3; y++){
                        temp.modifyBoard(x,y,rand.nextInt(2)-1);
                    }
                }
                //System.out.println(temp);
            }

        }




        //for(int x=0; x<3; x++)
        //    for(int y=0; y<3; y++)
        //        data[x][y] = new Board(x,y);

        DisplayGrid.render(data);
//        Board test2 = new Board(0,0);
//        Display.render(test2);


//        if (test2.checkForWin() == 1) System.out.println("\nCongratulations! Player with 'X' has won.");
//        else if (test2.checkForWin() == -1) System.out.println("\nCongratulations! Player with the 'O' has won.");
//        else System.out.print("\nGame not won. The next player may make a move.");


        //Example modifications
//        test2.modifyBoard(1,1,1);
//        test2.modifyBoard(0,0,-1);
//        test2.modifyBoard(1,0,-1);
//        test2.modifyBoard(2,0,-1);

//        Display.render(test2);

        int currentPlayer = 1; // Player 1 starts (X)
        Board gameBoard = new Board(0,0);
        while (true) {
            Display.render(gameBoard); // Display the current board

            System.out.println("\nPlayer " + (currentPlayer == 1 ? "'X'" : "'O'") + ", make your move!");
            System.out.print("Enter row (0-2): ");
            int row = scanner.nextInt();
            System.out.print("Enter column (0-2): ");
            int column = scanner.nextInt();

            // Validate input
            if (row < 0 || row > 2 || column < 0 || column > 2 || gameBoard.getBoard()[row][column] != 0) {
                System.out.println("\nInvalid move. Try again.");
                continue;
            }

            // Update the board
            gameBoard.modifyBoard(row, column, currentPlayer);

            if (gameBoard.checkForWin() == 1) {
                Display.render(gameBoard);
                System.out.println("\nCongratulations! Player with 'X' has won.");
                break;
            }
            else if (gameBoard.checkForWin() == -1) {
                Display.render(gameBoard);
                System.out.println("\nCongratulations! Player with the 'O' has won");
                break;
            }


//        for(int r = 0; r < 3; r++){
//            for(int c = 0; c < 3; c++) {
//                Board test = new Board(r,c);
//
//                //At this current time, the method call below will print out all 9 boards sequentially.
//                //Render.render(test);
//
//            }
//        }

            // Switch players
            currentPlayer = (currentPlayer == 1) ? -1 : 1;
        }
    }
}