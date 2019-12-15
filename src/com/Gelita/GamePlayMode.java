package com.Gelita;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.security.Key;
import java.util.Random;

public class GamePlayMode extends JPanel implements KeyListener, ActionListener
{
    private String programer ="Created By oto";
    private int score=0;
    private  int lengthOfSnake=3;
    private int[] snakeX=new int[750];
    private int[] snakeY=new int[750];

    private int[] targetX={25,50,75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,
    525,550,575,600,625,650,675,700,725,750,775,800,825,850};

    private int[] targetY={75,100,125,150,175,200,225,250,275,300,325,350,375,400,425,450,475,500,
            525,550,575,600,625};

    private Random random=new Random();
    private int X=random.nextInt(34);
    private int Y=random.nextInt(23);
    private boolean right=false;
    private boolean left=false;
    private boolean up=false;
    private boolean down=false;

    private ImageIcon RightMouth;
    private ImageIcon LeftMouth;
    private ImageIcon UpMouth;
    private ImageIcon DownMouth;

    private ImageIcon targetImage;
    private ImageIcon image;
    private ImageIcon SnakeImage;
    private  Timer timer;
    private int delay=100;
    private int moves=0;

    public GamePlayMode()
    {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer=new Timer(delay,this);
        timer.start();
    }
        public void paint (Graphics g)
        {
            if(moves==0)
            {
                snakeX[0]=100;
                snakeX[1]=75;
                snakeX[2]=50;

                snakeY[0]=100;
                snakeY[1]=100;
                snakeY[2]=100;
            }
            g.setColor(Color.white);
            g.drawRect(24,10,851,55);
            image=new ImageIcon("Photos/snaketitle.jpg");
            image.paintIcon(this,g,25,11);

            g.setColor(Color.white);
            g.drawRect(24,74,851,557);

            g.setColor(Color.black);
            g.fillRect(25,75,850,575);

            g.setColor(Color.white);
            g.setFont(new Font("arial",Font .PLAIN,16));
            g.drawString("Your Scores:"+score,760,30);

            g.setColor(Color.white);
            g.setFont(new Font("arial",Font .PLAIN,16));
            g.drawString("Snake Length:"+lengthOfSnake,760,55);

            RightMouth=new ImageIcon("Photos/rightmouth.png");
            RightMouth.paintIcon(this,g,snakeX[0],snakeY[0]);
                for(int i=0;i<lengthOfSnake;++i)
                {
                    if(i==0&&right)
                    {
                        RightMouth=new ImageIcon("Photos/rightmouth.png");
                        RightMouth.paintIcon(this,g,snakeX[i],snakeY[i]);
                    }
                    if(i==0&& left)
                    {
                        LeftMouth=new ImageIcon("Photos/leftmouth.png");
                        LeftMouth.paintIcon(this,g,snakeX[i],snakeY[i]);
                    }
                    if(i==0&& up)
                    {
                        UpMouth=new ImageIcon("Photos/upmouth.png");
                        UpMouth.paintIcon(this,g,snakeX[i],snakeY[i]);
                    }
                    if(i==0&&down)
                    {
                        DownMouth=new ImageIcon("Photos/downmouth.png");
                        DownMouth.paintIcon(this,g,snakeX[i],snakeY[i]);
                    }
                    if(i!=0)
                    {
                        SnakeImage=new ImageIcon("Photos/snakeimage.png");
                        SnakeImage.paintIcon(this,g,snakeX[i],snakeY[i]);
                    }
                }
                targetImage=new ImageIcon("Photos/target.png");
                targetImage.paintIcon(this,g,targetX[X],targetY[Y]);
                    if(targetX[X]==snakeX[0] && targetY[Y]==snakeY[0])
                    {
                        score++;
                        lengthOfSnake++;
                        X=random.nextInt(34);
                        Y=random.nextInt(23);
                    }

                    for(int i=1;i<lengthOfSnake;i++)
                    {
                        if (snakeX[i]==snakeX[0] &&snakeY[i]==snakeY[0])
                        {
                            right=false;
                            left=false;
                            up=false;
                            down=false;

                            g.setColor(Color.white);
                            g.setFont(new Font("arial",Font.BOLD,50));
                            g.drawString("Game Over :(",300,300);


                            g.setFont(new Font("arial",Font.BOLD,20));
                            g.drawString("Press SPACE to restart",350,340);

                        }
                    }
            g.setColor(Color.red);
            g.setFont(new Font("arial",Font .PLAIN,16));
            g.drawString( programer,60,50);

            g.dispose();
        }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        timer.start();
        if(right)
        {
            for(int r=lengthOfSnake-1;r>=0;r--)
            {
                snakeY[r+1]= snakeY[r];

            }
            for(int r=lengthOfSnake;r>=0;r--)
            {
                if(r==0)
                {
                    snakeX[r]=snakeX[r]+25;
                }
                else
                {
                    snakeX[r]=snakeX[r-1];
                }
                if(snakeX[r]>850)
                {
                    snakeX[r]=25;
                }
                repaint();
            }
        }
        if(left)
        {
            for(int r=lengthOfSnake-1;r>=0;r--)
            {
                snakeY[r+1]= snakeY[r];

            }
            for(int r=lengthOfSnake;r>=0;r--)
            {
                if(r==0)
                {
                    snakeX[r]=snakeX[r]-25;
                }
                else
                {
                    snakeX[r]=snakeX[r-1];
                }
                if(snakeX[r]< 25)
                {
                    snakeX[r]=850;
                }
                repaint();
            }
        }
        if(down)
        {
            for(int r=lengthOfSnake-1;r>=0;r--)
            {
                snakeX[r+1]= snakeX[r];

            }
            for(int r=lengthOfSnake;r>=0;r--)
            {
                if(r==0)
                {
                    snakeY[r]=snakeY[r]+25;
                }
                else
                {
                    snakeY[r]=snakeY[r-1];
                }
                if(snakeY[r]>625)
                {
                    snakeY[r]=75;
                }
                repaint();
            }
        }

        if(up)
        {
            for(int r=lengthOfSnake-1;r>=0;r--)
            {
                snakeX[r+1]= snakeX[r];

            }
            for(int r=lengthOfSnake;r>=0;r--)
            {
                if(r==0)
                {
                    snakeY[r]=snakeY[r]-25;
                }
                else
                {
                    snakeY[r]=snakeY[r-1];
                }
                if(snakeY[r]<75)
                {
                    snakeY[r]=625;
                }
                repaint();
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode()==keyEvent.VK_SPACE)
        {
            moves=0;
            score=0;
            lengthOfSnake=3;
            repaint();
        }
        if(keyEvent.getKeyCode()== KeyEvent.VK_RIGHT)
        {
            moves++;
            right=true;
            if(!left)
            {
                right=true;
            }
            else {
                right = false;
                left = true;
            }
            up=false;
            down=false;
        }
        if(keyEvent.getKeyCode()== KeyEvent.VK_LEFT)
        {
            moves++;
            left=true;
            if(!right)
            {
                left=true;
            }
            else {
                left = false;
                right = true;
            }
            up=false;
            down=false;
        }
        if(keyEvent.getKeyCode()== KeyEvent.VK_UP)
        {
            moves++;
            up=true;
            if(!down)
            {
                up=true;
            }
            else {
                up = false;
                down = true;
            }
            right=false;
            left=false;
        }
        if(keyEvent.getKeyCode()== KeyEvent.VK_DOWN)
        {
            moves++;
            down=true;
            if(!up)
            {
                down=true;
            }
            else {
                down = false;
                up = true;
            }
            right=false;
            left=false;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }
}
