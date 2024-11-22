import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.swing.*;

public class Flappybird extends JPanel{

    int boardwidth = 360;
    int boardHeight = 640;

    

    Image backgroundImag;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;



    Flappybird(){
        setPreferredSize(new Dimension(boardwidth, boardHeight));
        setBackground(Color.blue);

        backgroundImag = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();



    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

        
    }

    public void draw(Graphics g){
        g.drawImage(backgroundImag, 0, 0, boardwidth, boardHeight, null);
    }
        

    
}
