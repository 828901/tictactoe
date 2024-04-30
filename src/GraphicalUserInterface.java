import javax.swing.*;
import java.awt.*;

public class GraphicalUserInterface{
    public GraphicalUserInterface(Board[][] data) {
        for (int r = 0; r < 3; r++) {
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
            }
        }
    }
        static class Render extends JComponent{
        private static int OFFSET = 50;
        private int xOffset = 100;
        private int yOffset = 100;

        public Render(){
            //setContentPane(new JPanel ());

        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            for(int y = 0; y < 3; y++){
                for(int x = 0; x < 3; x++){
                    displayGame(g, y, x);
                }
            }
        }

        private void displayGame(Graphics g, int y, int x){
            g.setColor(Color.BLACK);
            g.drawRect(xOffset,yOffset,200, 200);
            System.out.println(xOffset + " " +  yOffset);
            if(x < 2){
                System.out.println(x);
                xOffset = xOffset + OFFSET + 200;
            }else{
                xOffset = 100;
                yOffset += OFFSET + 200;
            }
            //System.out.println(xOffset);
        }

    }
    public static void main(String[] args) {

        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        frame.setTitle("Ultimate TicTacToe");

        frame.setSize(new Dimension(900, 950));
        frame.setResizable(false);


        JButton button = new JButton("EXAMPLE BUTTON");
        frame.setContentPane(new Render());
        //frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.add(new Render());
        panel.add(button);
        //JLabel emptyLabel = new JLabel("DEMO");
        //frame.getContentPane().add();

        //frame.pack();
        frame.setVisible(true);

    }

}