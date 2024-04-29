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
//        Random rand = new Random();
//        for (int r = 0; r < 3; r++) {
//            for (int c = 0; c < 3; c++) {
//                for (int x = 0; x < 3; x++) {
//                    for (int y = 0; y < 3; y++) {
//                        data[r][c].modifyBoard(x, y, rand.nextInt(3) - 1);
//                    }
//                }
//            }
//        }


        //Game Loop
        int currentPlayer = 1, bigRow, bigColumn, smallRow = -1, smallColumn = -1, tempRow = -1, tempColumn = -1;
        boolean validInput = false;

        while (true) {
            DisplayGrid.render(data); //Display current board

            if (smallRow != -1 && smallColumn != -1) {
                bigRow = smallRow;
                bigColumn = smallColumn;
                if (data[smallRow][smallColumn].checkForWin() != 0) {
                    validInput = false;
                    while (!validInput) {
                        try {
                            System.out.println("\nPlayer " + (currentPlayer == 1 ? "'X'" : "'O'") + ", choose your board (row column):");
                            tempRow = Integer.parseInt(scanner.nextLine());
                            tempColumn = Integer.parseInt(scanner.nextLine());
                            validInput = true;
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter an integer.");
                        }
                    }
                    // Validate input
                    if (tempRow < 0 || tempRow > 2 || tempColumn < 0 || tempColumn > 2 || data[bigRow][bigColumn].getBoard()[tempRow][tempColumn] != 0) {
                        System.out.println("\nInvalid move. Try again.");
                        continue;
                    }
                    bigRow = tempRow;
                    bigColumn = tempColumn;
                }
                validInput = false;
                while (!validInput) {
                    try {
                        System.out.println("Player " + (currentPlayer == 1 ? "'X'" : "'O'") + ", enter your move (row column):");
                        tempRow = Integer.parseInt(scanner.nextLine());
                        tempColumn = Integer.parseInt(scanner.nextLine());
                        validInput = true;
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter an integer.");
                    }
                }
                // Validate input
                if (tempRow < 0 || tempRow > 2 || tempColumn < 0 || tempColumn > 2 || data[bigRow][bigColumn].getBoard()[tempRow][tempColumn] != 0) {
                    System.out.println("\nInvalid move. Try again.");
                    continue;
                }
                smallRow = tempRow;
                smallColumn = tempColumn;
            } else {
                System.out.println("\nPlayer " + (currentPlayer == 1 ? "'X'" : "'O'") + ", choose your board (row column):");
                bigRow = scanner.nextInt();
                bigColumn = scanner.nextInt();
                System.out.println("Player " + (currentPlayer == 1 ? "'X'" : "'O'") + ", enter your move (row column):");
                smallRow = scanner.nextInt();
                smallColumn = scanner.nextInt();
            }


            data[bigRow][bigColumn].modifyBoard(smallRow, smallColumn, currentPlayer);

            if (checkBoard(data) == 1) {
                System.out.println("Player X wins!");
                break;
            } else if (checkBoard(data) == -1) {
                System.out.println("Player O wins!");
                break;
            }
            currentPlayer = currentPlayer * -1;
        }
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