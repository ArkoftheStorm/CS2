package checkers;

import java.awt.*;

public class Random {
    private static int bombBorder = 1;
    private static int bombRow;
    private static int bombCol;
    private static boolean explode;
    private static int explodeTime;
    private static int explodeTimeLimit = 30;
    private static int roll = -1;
    public static void Draw(Graphics2D g) {
        if(Menu.menuShow || Menu.helpShow)
            return;
        g.setColor(Color.yellow);
        g.fillRect(Window.getX(Window.getWidth2() + 10), Window.getY(0), Board.getXdelta(), Board.getYdelta());
        g.setFont (new Font("times new roman",Font.BOLD,18));
        g.drawString("P1 Changes: " + Player.getPlayer(0).getChangeCount(), Window.getWidth2()/64, Window.getY(Window.getHeight2())+18);
        g.drawString("P2 Changes: " + Player.getPlayer(1).getChangeCount(), Window.getWidth2(), Window.getY(Window.getHeight2())+18);
        if(roll != -1){
            g.setColor(Color.black);
            g.setFont (new Font("times new roman",Font.BOLD,20));
            if(roll == 0){
                g.drawString("Switch", Window.getX(Window.getWidth2() + 10), Window.getY(0)+20);
                g.drawString("  a   ", Window.getX(Window.getWidth2() + 10), Window.getY(0)+40);
                g.drawString("Piece", Window.getX(Window.getWidth2() + 10), Window.getY(0)+60);
            }
            else if(roll == 1){
                g.drawString("Extra", Window.getX(Window.getWidth2() + 10), Window.getY(0)+20);
                g.drawString("Turn", Window.getX(Window.getWidth2() + 10), Window.getY(0)+60);
            }
            else
                g.drawString("Explode!", Window.getX(Window.getWidth2() + 10), Window.getY(0)+40);
            
        }
    }
    public static void Roll(Graphics2D g) {
        roll = (int)(Math.random()*3);
        Player.SwitchTurn();
        if(roll == 0){
            Player.GetCurrentPlayer().incChangeCount();
        }
        else if(roll == 1){
            Player.GetCurrentPlayer().extraTurn();
        }
        else if (roll == 2){
            bombRow = (int)(Math.random()*Board.getNumRows());
            bombCol = (int)(Math.random()*Board.getNumColumns());
            while(bombRow - bombBorder < 0 || bombRow + bombBorder >= Board.getNumRows())
                bombRow = (int)(Math.random()*Board.getNumRows());
            while(bombCol - bombBorder < 0 || bombCol + bombBorder >= Board.getNumColumns())
                bombCol = (int)(Math.random()*Board.getNumColumns());
            for(int r = bombRow - bombBorder; r <= bombRow + bombBorder; r++){
                for(int c = bombCol - bombBorder; c <= bombCol + bombBorder; c++){
                    Board.Pieces[r][c] = null;
                }
            }
            explodeTime = 0;
            explode = true;
        }
        
    }
    public static boolean getExplode(){
        return explode;
    }
    public static int getBombRow(){
        return bombRow;
    }
    public static int getBombCol(){
        return bombCol;
    }
    public static int getBombBorder(){
        return bombBorder;
    }
    public static void doneExplode(){
        explode = false;
    }
    public static void incExplodeTime(){
        explodeTime++;
    }
    public static boolean explodeTimeDone(){
        if(explodeTime >= explodeTimeLimit)
            return true;
        else
            return false;
    }
}
