//Author: Noah
//Class Purpose: Display the moves of the players as they choose empty squares to play their shape
public class Display {
    
    //Author: Noah
    //Precondition: board length and width !> 3
    //Postcondition: Renders the board with the moves of the users. 
    //@para: The board 
    static void render(Board data){
        int[][] board = data.getBoard();
        System.out.print("\n┌───┬───┬───┐");
        for(int x = 0; x < 3; x++){
            System.out.print("\n│");
            for(int y = 0; y < 3; y++){
                if (board[x][y] != 0 ) {
                    if(board[x][y] == 1){
                        System.out.print(" " + "X" + " │");
                    }else if(board[x][y] == -1){
                        System.out.print(" " + "O" + " │");
                    }
                }else{

                    System.out.print("   │");
                }
                if(y==2){
                    System.out.println();
                }
            }
            if(x==2){
                //if the current row is the last row print the end of the grid;
                System.out.print("└───┴───┴───┘");
            }else{
                //otherwise print the horizontal dividing lines;
                System.out.print("├───┼───┼───┤");

            }
        }
    }
}
