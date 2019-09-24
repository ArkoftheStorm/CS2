package checkers;

import java.awt.*;

public class Random {

    public static void Draw(Graphics2D g) {
        g.setColor(Color.yellow);
        g.fillRect(Window.getX(Window.getWidth2() + 10), Window.getY(0), Board.getXdelta(), Board.getYdelta());
        g.setFont (new Font("times new roman",Font.PLAIN,24));
    }
    public static void Roll(Graphics2D g) {
        int roll = (int)(Math.random()*3);
        if(roll == 1){
            Player.GetCurrentPlayer().changeCount++;
        }
        else if(roll == 2){
            
        }
        else if(roll == 3){
            
        }
        Player.SwitchTurn();
    }
}
