package checkers;

import java.awt.Color;
import java.awt.Graphics2D;

public class Board {

    private final static int NUM_ROWS = 8;
    private final static int NUM_COLUMNS = 8;
    private static int xdelta = Window.getWidth2() / NUM_COLUMNS;
    private static int ydelta = Window.getHeight2() / NUM_ROWS;
    private static Color board[][] = new Color[NUM_ROWS][NUM_COLUMNS];
    Color color = null;

    public static void Draw(Graphics2D g) {
        int c = 0;
        int r = 0;
        boolean nRow = false;
        Color prevColor = null;
        while (r < NUM_ROWS) {
            c = 0;
            while (c < NUM_COLUMNS) {
                if (nRow) {
                    nRow = false;
                    g.setColor(prevColor);
                } else {
                    if (prevColor == Color.red) {
                        prevColor = Color.black;



                   //     board[r][c].color = prevColor;
                    }
                    else if(prevColor == Color.black){
                        prevColor = Color.red;
                    //    board[r][c].color = prevColor;

//                        board[r][c].color = prevColor;
                    }
                    else{
                        board[r][c] = prevColor;
                    } 
		    else {

                        prevColor = Color.red;
//                        board[r][c].color = prevColor;

                        board[r][c] = prevColor;
   //                 } else {
   //                     prevColor = Color.red;
    //                    board[r][c] = prevColor;


                    }
                    g.setColor(prevColor);
                }

                g.fillRect(Window.getX(c * xdelta), Window.getY(r * ydelta), xdelta, ydelta);
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
        Piece.draw(g, r, c, xdelta, ydelta);

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
