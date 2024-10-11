
package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.*;
import javax.swing.JPanel;
import javax.swing.Timer;


public class gameplay extends JPanel implements KeyListener,ActionListener{
    private boolean play=false;
    private int score =0;
    
    private int totalBricks=21;
    
    private Timer timer;
    private int delay=4;
    private int playerx=316;
    
    private int ballposx=135;
    private int ballposy=340;
    private int ballxdir=-1;
    private int ballydir=-2;
    
    private breaks map;
    
   public gameplay(){
       map=new breaks(3,7);
       addKeyListener(this);
       setFocusable(true);
       setFocusTraversalKeysEnabled(false);
       timer=new Timer(delay,this);
       timer.start();
       
   } 
   
   public void paint(Graphics g){
       g.setColor(Color.blue);
       g.fillRect(1, 1, 800, 600);
       
       map.draw((Graphics2D)g);
       
       g.setColor(Color.MAGENTA);
       g.fillRect(0, 0, 3, 600);
       g.fillRect(0, 0, 798, 3);
       g.fillRect(798, 0, 3, 600);
       
       g.setColor(Color.white);
       g.setFont(new Font("serif",Font.BOLD,25));
       g.drawString(""+score, 700, 30);
       
       g.setColor(Color.WHITE);
       g.fillRect(playerx, 550, 100, 8);
       
       
       g.setColor(Color.WHITE);
       g.fillOval( ballposx, ballposy, 20, 20);
       if(totalBricks<=0){
           play=false;
           ballxdir=0;
           ballydir=0;
           g.setColor(Color.red);
           g.setFont(new Font("serif",Font.BOLD,30));
           g.drawString("YOU WIN THE GAME", 190, 300);
              
           
           
       }
       
       if(ballposy>670){
           play=false;
           ballxdir=0;
           ballydir=0;
           g.setColor(Color.red);
           g.setFont(new Font("serif",Font.BOLD,30));
           g.drawString("GAME OVER,Scores:"+score, 190, 300);
              
           
            g.setFont(new Font("serif",Font.BOLD,28));
           g.drawString("Press Enter To Restart", 230, 350);
       }
       
       g.dispose();
       
       
   }
    
    

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode()==KeyEvent.VK_RIGHT){
            if(playerx>=700){
                playerx=700;
            }
            else{
                moveRight();
            }
        }
        
         if(e.getKeyCode()==KeyEvent.VK_LEFT){
            if(playerx<10){
                playerx=10;
            }
            else{
                moveLeft();
            }
        }
        if((e.getKeyCode()== KeyEvent.VK_ENTER)){
            if(!play){
                play=true;
                ballposx=120;
                ballposy=350;
                ballxdir=-1;
                ballydir=-3;
                playerx=310;
                score=0;
                totalBricks=21;
                map=new breaks(3,7);
                
                repaint();
            }
        } 
    }
    public void moveRight(){
        play=true;
        playerx+=20;
        
    }
    public void moveLeft(){
        play=true;
        playerx-=20;
        
    }
    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timer.start();
        if(play){
            if(new Rectangle(ballposx,ballposy,20,20).intersects(new Rectangle(playerx,550,100,8))){
                ballydir=-ballydir;
            }
            
            a:for(int i=0;i<map.map.length;i++){
                for(int j=0;j<map.map[0].length;j++){
                    if(map.map[i][j]>0){
                        int brickx=j*map.brickWidth+80;
                        int bricky=i*map.brickHeight+50;
                        int brickWidth=map.brickWidth;
                        int brickHeight=map.brickHeight;
                        
                        Rectangle rect=new Rectangle(brickx,bricky,brickWidth,brickHeight);
                        Rectangle ballRect=new Rectangle(ballposx,ballposy,20,20);
                        Rectangle brickRect=rect;
                        
                        
                        if(ballRect.intersects(brickRect)){
                            map.setBrickValue(0, i, j);
                            totalBricks--;
                            score+=5;
                            
                            if(ballposx+19<=brickRect.x || ballposx +1 >= brickRect.x + brickRect.width){
                                ballxdir=-ballxdir;
                            }
                            else{
                                ballydir=-ballydir;
                            }
                            break a;
                        }
                    }
                }
            }
            
            ballposx+=ballxdir;
            ballposy+=ballydir;
            if(ballposx<0){
                ballxdir=-ballxdir;
            }
             if(ballposy<0){
                ballydir=-ballydir;
            }
              if(ballposx>770){
                ballxdir=-ballxdir;
            }
            
        }
        repaint();
        
    }

    
}
