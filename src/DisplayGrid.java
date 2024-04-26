public class DisplayGrid {
    static int rows;
    static int columns;

    public static char getSymbol(int number){
        if(number == 1)
            return 'X';

        if(number == -1)
            return 'O';

        return ' ';
    }

    static void render(Board[][] data){
        System.out.println("┏━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┳━━━━━━━━━━━━━━━┓");
        for(int r = 0; r < 3; r++) {
            System.out.println("┃ ┌───┬───┬───┐ ┃ ┌───┬───┬───┐ ┃ ┌───┬───┬───┐ ┃");
            for (int x = 0; x < 3; x++) {
                int[] rowOne = new int[3];
                int[] rowTwo = new int[3];
                int[] rowThree = new int[3];

                Board tempBoardOne = data[r][0];
                Board tempBoardTwo = data[r][1];
                Board tempBoardThree = data[r][2];

                for (int y = 0; y < 3; y++) {

                    //For the purposes of display the grid, we only need one row of every board at a time
                    rowOne[y] = tempBoardOne.getValue(x, y);
                    rowTwo[y] = tempBoardTwo.getValue(x, y);
                    rowThree[y] = tempBoardThree.getValue(x, y);
                }
                System.out.print("┃ │ " + getSymbol(rowOne[0]) + " │ " + getSymbol(rowOne[1]) + " │ " + getSymbol(rowOne[2]) + " │ ┃");
                System.out.print(" │ " + getSymbol(rowTwo[0]) + " │ " + getSymbol(rowTwo[1]) + " │ " + getSymbol(rowTwo[2]) + " │ ┃");
                System.out.print(" │ " + getSymbol(rowThree[0]) + " │ " + getSymbol(rowThree[1]) + " │ " + getSymbol(rowThree[2]) + " │ ┃\n");
            }
            System.out.println("┃ └───┴───┴───┘ ┃ └───┴───┴───┘ ┃ └───┴───┴───┘ ┃");
            if(r == 2){
                System.out.println("┗━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┻━━━━━━━━━━━━━━━┛");
            }else{
                System.out.println("┣━━━━━━━━━━━━━━━╋━━━━━━━━━━━━━━━╋━━━━━━━━━━━━━━━┫");
            }
        }
    }
}
