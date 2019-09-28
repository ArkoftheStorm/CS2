package checkers;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;

public class Board {

    private final static int NUM_ROWS = 8;
    private final static int NUM_COLUMNS = 8;
    private static int xdelta = Window.getWidth2() / NUM_COLUMNS;
    private static int ydelta = Window.getHeight2() / NUM_ROWS;
    private static Color board[][] = new Color[NUM_ROWS][NUM_COLUMNS];

    public static Piece Pieces[][] = new Piece[NUM_ROWS][NUM_COLUMNS];

    Color color = null;
    public static void Draw(Graphics2D g) {
        xdelta = Window.getWidth2() / NUM_COLUMNS;
        ydelta = Window.getHeight2() / NUM_ROWS;
        int c = 0;
        int r = 0;
        boolean nRow = false;
        Image explosion;

        Color prevColor = Color.red;

        while (r < NUM_ROWS) {
            c = 0;
            while (c < NUM_COLUMNS) {
                if (nRow) {
                    nRow = false;
                    if(prevColor == Color.black)
                        if(r > 4 && Menu.menuShow)
                            Pieces[r][c] = new Piece (Player.getPlayer(0).getColor(),r,c);
                    else
                        if(r < 3 && Menu.menuShow)   
                            Pieces[r][c] = new Piece (Player.getPlayer(1).getColor(),r,c);
                } 
                else {
                    if (prevColor == Color.red) {
                        prevColor = Color.black;
                        board[r][c] = prevColor;
                        if(r < 3 && Menu.menuShow)
                            Pieces[r][c] = new Piece (Player.getPlayer(1).getColor(),r,c);
                         else  if(r > 4 && Menu.menuShow)
                            Pieces[r][c] = new Piece (Player.getPlayer(0).getColor(),r,c);
                    }
                    else if(prevColor == Color.black){
                        prevColor = Color.red;
                        if(Menu.menuShow)
                            board[r][c] = prevColor;
                    
                    }
                }
                g.setColor(prevColor);
                g.fillRect(Window.getX(c * xdelta), Window.getY(r * ydelta), xdelta, ydelta);
                if(Pieces[r][c] != null)
                    Pieces[r][c].draw(g);
                c++;
            }
            nRow = true;
            r++;
        }
        g.setColor(Color.white);
        for (int zi = 1; zi < NUM_ROWS; zi++) {
            g.drawLine(Window.getX(0), Window.getY(0) + zi * Window.getHeight2() / NUM_ROWS,
                    Window.getX(Window.getWidth2()), Window.getY(0) + zi * Window.getHeight2() / NUM_ROWS);
        }
        for (int zi = 1; zi < NUM_COLUMNS; zi++) {
            g.drawLine(Window.getX(0) + zi * Window.getWidth2() / NUM_COLUMNS, Window.getY(0),
                    Window.getX(0) + zi * Window.getWidth2() / NUM_COLUMNS, Window.getY(Window.getHeight2()));
        }
        

   }
    public static void switchPiece(int xPixel, int yPixel){
        if(Player.GetCurrentPlayer().getChangeCount() <= 0)
            return;
        int _col = (xPixel/xdelta) - 1;
        int _row = (yPixel/ydelta);
        if(Pieces[_row][_col] != null && _row < NUM_ROWS && _col < NUM_COLUMNS)
            Pieces[_row][_row].switchColor();
        Player.GetCurrentPlayer().decChangeCount();
    }

    public static int getNumRows() {
        return NUM_ROWS;
    }

    public static int getNumColumns() {
        return NUM_COLUMNS;
    }

    public static int getXdelta() {
        return xdelta;
    }

    public static int getYdelta() {
        return ydelta;
    }

    public static Color getColor(int r, int c) {
        return board[r][c];
    }

}
