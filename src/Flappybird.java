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

    int birdX = boardwidth/8;
    int bitdY = boardHeight/2;
    int birdWidth = 34;
    int birdHeight = 24; 

    class Bird{
        int x = birdX;
        int y = bitdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img){
            this.img=img;

        }
    }

    Bird bird;




    Flappybird(){
        setPreferredSize(new Dimension(boardwidth, boardHeight));
        setBackground(Color.blue);

        backgroundImag = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();



        bird = new Bird(birdImg);




    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

        
    }

    public void draw(Graphics g){
        g.drawImage(backgroundImag, 0, 0, boardwidth, boardHeight, null);

        g.drawImage(birdImg, bird.x, bird.y, bird.width, bird.height, null);
        
    }
        

    
}
