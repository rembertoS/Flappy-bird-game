import java.awt.*;
import java.awt.event.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import javax.swing.*;


public class Flappybird extends JPanel implements ActionListener , KeyListener{

    int boardwidth = 360;
    int boardHeight = 640;

    

    Image backgroundImag;
    Image birdImg;
    Image topPipeImg;
    Image bottomPipeImg;

    int birdX = boardwidth/8;
    int birdY = boardHeight/2;
    int birdWidth = 34;
    int birdHeight = 24; 

    class Bird{
        int x = birdX;
        int y = birdY;
        int width = birdWidth;
        int height = birdHeight;
        Image img;

        Bird(Image img){
            this.img=img;

        }
    }

    int pipeX = boardwidth;
    int pipeY = 0;
    int pipeWidth = 64;
    int pipeHeight = 512;

    class Pipe{
        int x = pipeX; 
        int y = pipeY; 
        int width = pipeWidth;
        int height = pipeHeight;
        Image img; 
        boolean passed = false; 

        Pipe(Image img){
            this.img = img; 
        }
    }




    //game logic 

    Bird bird;
    int velocityX = -4;
    int velocityY = 0;
    int gravity = 1;

    ArrayList<Pipe> pipes; 
    Random random = new Random();




    javax.swing.Timer gameloop;
    javax.swing.Timer placePipesTimer;
    boolean gameOver = false; 
    double score = 0 ;






    Flappybird(){
        setPreferredSize(new Dimension(boardwidth, boardHeight));
        setBackground(Color.blue);

        setFocusable(true);
        addKeyListener(this);

        backgroundImag = new ImageIcon(getClass().getResource("./flappybirdbg.png")).getImage();
        birdImg = new ImageIcon(getClass().getResource("./flappybird.png")).getImage();
        topPipeImg = new ImageIcon(getClass().getResource("./toppipe.png")).getImage();
        bottomPipeImg = new ImageIcon(getClass().getResource("./bottompipe.png")).getImage();



        bird = new Bird(birdImg);
        pipes = new ArrayList<Pipe>();


        placePipesTimer = new javax.swing.Timer(1500, new ActionListener() {

            public void actionPerformed(ActionEvent e){
                placePipes();
            }
        });

        placePipesTimer.start();


        //gmae timer 
        gameloop = new javax.swing.Timer(1000/60, this);
        gameloop.start();


    }

    public void placePipes(){

        //(0-1) * pipeHeight/2 -> (0-256)
        //128 
        //0 - 128 - (0-256) ->>> pipeHeight --> 3/5 pipeHeight 

        int randomPipeY = (int) (pipeY - pipeHeight/4 - Math.random()*(pipeHeight/2));
        int openingspace = boardHeight/4;



        Pipe topPipe = new Pipe(topPipeImg);
        topPipe.y = randomPipeY;
        pipes.add(topPipe);

        Pipe bottomPipe = new Pipe(bottomPipeImg);
        bottomPipe.y = topPipe.y + pipeHeight + openingspace;
        pipes.add(bottomPipe);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        draw(g);

        
    }

    public void draw(Graphics g){
       
       
        g.drawImage(backgroundImag, 0, 0, boardwidth, boardHeight, null);

        g.drawImage(birdImg, bird.x, bird.y, bird.width, bird.height, null);

        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            g.drawImage(pipe.img, pipe.x , pipe.y, pipe.width, pipe.height , null);
        }

        g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.PLAIN, 32));
        if(gameOver){
            g.drawString("Game Over; " + String.valueOf((int) score), 10, 35);
        }else{
            g.drawString(String.valueOf((int) score ), 10 , 35);
        }



    }

    public void move(){
        velocityY += gravity; 
        bird.y += velocityY;
        bird.y = Math.max(bird.y, 0);

        for(int i = 0; i < pipes.size(); i++){
            Pipe pipe = pipes.get(i);
            pipe.x += velocityX; 

            if(!pipe.passed && bird.x > pipe.x + pipe.width){
                pipe.passed = true; 
                score += 0.5;
            }


            if(collission(bird, pipe)){
                gameOver = true; 
            }

        }
        if(bird.y > boardHeight){
            gameOver = true; 
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        move();
        repaint();

        if(gameOver){
            placePipesTimer.stop();
            gameloop.stop();
        }
    }

    public boolean collission(Bird a, Pipe b){
        return a.x < b.x + b.width &&  // A's top left corner doesnt reach b's top right corner 
        a.x + a.width > b.x &&  // A's top right corner passes b's top left corner 
        a.y < b.y + b.height &&  // A's top left corner doesnt reach b's top left corner 
        a.y + a.height > b.y;    // A's bottom left corner passes b's top left corner 

    }

   

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            velocityY = -9;
           
        }

        if(e.getKeyCode() ==  KeyEvent.VK_R){
            if(gameOver){
                bird.y = birdY;
                velocityY = 0;
                pipes.clear();
                score = 0; 
                gameOver = false;
                gameloop.start();
                placePipesTimer.start();
            }
        }
    }




    @Override
    public void keyTyped(KeyEvent e){}

    @Override
    public void keyReleased(KeyEvent e){}
        

    
}
