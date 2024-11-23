

import javax.swing.JFrame;

public class App {
    public static void main(String[] args) throws Exception {
        int boardwidth = 360;
        int boardHeight = 640;

        JFrame frame = new JFrame("Flappybird");
        frame.setVisible(true);
        frame.setSize(boardwidth , boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Flappybird flappybird = new Flappybird();
        frame.add(flappybird);
        frame.pack();
        flappybird.requestFocus();
        frame.setVisible(true);
        



        

        
    }
}
