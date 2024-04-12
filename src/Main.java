//https://en.wikipedia.org/wiki/Box-drawing_characters
public class Main {
    public static void main(String[] args) {
        Board[][] data = new Board[3][3];
        Board test2 = new Board(0,0);
        Display.render(test2);

        //Example modifications
        test2.modifyBoard(1,1,1);
        test2.modifyBoard(0,0,-1);

        Display.render(test2);
        for(int r = 0; r < 3; r++){
            for(int c = 0; c < 3; c++) {
                Board test = new Board(r,c);

                //At this current time, the method call below will print out all 9 boards sequentially.
                //Render.render(test);

            }
        }
    }
}