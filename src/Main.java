//https://en.wikipedia.org/wiki/Box-drawing_characters
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Board[][] data = new Board[3][3];
        Board test2 = new Board(0,0);
        Display.render(test2);


        if (test2.checkForWin() == 1) System.out.println("\nCongratulations! Player with 'X' has won.");
        else if (test2.checkForWin() == -1) System.out.println("\nCongratulations! Player with the 'O' has won.");
        else System.out.print("\nGame not won. The next player may make a move.");


        //Example modifications
        test2.modifyBoard(1,1,1);
        test2.modifyBoard(0,0,-1);
        test2.modifyBoard(1,0,-1);
        test2.modifyBoard(2,0,-1);

        Display.render(test2);

        int currentPlayer = 1; // Player 1 starts (X)

        while (true) {
            Display.render(gameBoard); // Display the current board

            System.out.println("Player " + (currentPlayer == 1 ? "'X'" : "'O'") + ", make your move!");
            System.out.print("Enter row (0-2): ");
            int row = scanner.nextInt();
            System.out.print("Enter column (0-2): ");
            int column = scanner.nextInt();

            // Validate input
            if (row < 0 || row > 2 || column < 0 || column > 2 || gameBoard.getBoard()[row][column] != 0) {
                System.out.println("Invalid move. Try again.");
                continue;
            }

            // Update the board
            gameBoard.modifyBoard(row, column, currentPlayer);

            if (test2.checkForWin() == 1) System.out.println("\nCongratulations! Player with 'X' has won.");
            else if (test2.checkForWin() == -1) System.out.println("\nCongratulations! Player with the 'O' has won");
            else System.out.print("\nGame not won. The next player may make a move.");


            for(int r = 0; r < 3; r++){
                for(int c = 0; c < 3; c++) {
                    Board test = new Board(r,c);

                    //At this current time, the method call below will print out all 9 boards sequentially.
                    //Render.render(test);

                }
            }

            // Switch players
            currentPlayer = (currentPlayer == 1) ? -1 : 1;
        }
    }
}