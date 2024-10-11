
package game;

import javax.swing.JFrame;

public class Game {

    
    public static void main(String[] args) {
        JFrame obj=new JFrame();
        gameplay Gameplay=new gameplay();
        obj.setBounds(20, 20, 800, 600);
        obj.setTitle("Breakout Ball");
        obj.setResizable(false);
        obj.setVisible(true);
        obj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        obj.add(Gameplay);
        
        
        
    }
    
}
