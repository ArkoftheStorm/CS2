package checkers;

import java.awt.*;

public class Random {

    public static void Draw(Graphics2D g) {
        if(Menu.menuShow || Menu.helpShow)
            return;
        g.setColor(Color.yellow);
        g.fillRect(Window.getX(Window.getWidth2() + 10), Window.getY(0), Board.getXdelta(), Board.getYdelta());
        g.setFont (new Font("times new roman",Font.BOLD,18));
        g.drawString("P1 Changes: " + Player.getPlayer(0).getChangeCount(), Window.getWidth2()/64, Window.getY(Window.getHeight2())+18);
        g.drawString("P2 Changes: " + Player.getPlayer(1).getChangeCount(), Window.getWidth2(), Window.getY(Window.getHeight2())+18);
    }
    public static void Roll(Graphics2D g) {
        int roll = (int)(Math.random()*3+1);
        Player.SwitchTurn();
        if(roll == 1){
            Player.GetCurrentPlayer().incChangeCount();
        }
        else if(roll == 2){
            Player.GetCurrentPlayer().extraTurn();
        }
        else if (roll == 3){
            int row = (int)(Math.random()*Board.getNumRows());
            int col = (int)(Math.random()*Board.getNumColumns());
            while(row - 2 < 0 || row + 2 >= Board.getNumRows())
                row = (int)(Math.random()*Board.getNumRows());
            while(col - 2 < 0 || col + 2 >= Board.getNumColumns())
                col = (int)(Math.random()*Board.getNumColumns());
            for(int r = row - 2; r <= row + 2; r++){
                for(int c = col - 2; c <= col + 2; c++){
                    Board.Pieces[r][c] = null;
                }
            }
        }
        
    }
}
