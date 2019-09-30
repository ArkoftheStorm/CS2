package checkers;

import java.awt.*;

public class Random {
    private static int bombBorder = 1;
    private static int bombRow;
    private static int bombCol;
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
        int roll = (int)(Math.random()*3);
        Player.SwitchTurn();
        if(roll == 0){
            Player.GetCurrentPlayer().incChangeCount();
        }
        else if(roll == 1){
            Player.GetCurrentPlayer().extraTurn();
        }
        else if (roll == 2){
            int row = (int)(Math.random()*Board.getNumRows());
            int col = (int)(Math.random()*Board.getNumColumns());
            while(row - bombBorder < 0 || row + bombBorder >= Board.getNumRows())
                row = (int)(Math.random()*Board.getNumRows());
            while(col - bombBorder < 0 || col + bombBorder >= Board.getNumColumns())
                col = (int)(Math.random()*Board.getNumColumns());
            for(int r = row - bombBorder; r <= row + bombBorder; r++){
                for(int c = col - bombBorder; c <= col + bombBorder; c++){
                    Board.Pieces[r][c] = null;
                    bombRow = r;
                    bombCol = c;
                }
            }
        }
        
    }
}
