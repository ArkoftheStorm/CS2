package checkers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Piece {

    private Color color;
    private int row;
    private int col;

    Piece(Color _color, int _row, int _col) {
        for (int zrow = 0; zrow < Board.getNumRows(); zrow++) {
            for (int zcol = 0; zcol < Board.getNumColumns(); zcol++) {

                if (4 < zrow) {
                    color = Color.red;
                } else if (zrow < 3) {
                    color = Color.blue;
                }
            }
        }
        row = _row;
        col = _col;
        color = _color;
    }

    public Color getColor() {
        return (color);
    }

    public static void Animate(int xpixel, int ypixel) {
        if ((xpixel - Window.getX(0)) < 0 || (xpixel - Window.getX(0)) > Window.getWidth2()) {
            return;
        }
        if ((ypixel - Window.getY(0)) < 0 || (ypixel - Window.getY(0)) > Window.getHeight2()) {
            return;
        }
        int ydelta = Window.getHeight2() / Board.getNumRows();
        int xdelta = Window.getWidth2() /  Board.getNumColumns();

        int zrow = (xpixel - Window.getX(0)) / xdelta;
        int zcol = (ypixel - Window.getY(0)) / ydelta;
        
        int r = zrow;
        int c = zcol;
//System.out.println(zrow+"   " +zcol);

    //  if(Menu.menuGone== true){
        if(Board.getColor(zrow, zcol) == Color.BLACK )
         {

           Board.Pieces[r][c] = new Piece(Color.red,r,c);
           System.out.println(r+"   " +c);
           System.out.println("The Color is ");
           

             
         // Board.Pieces[r][c] = new Piece(Player.GetCurrentPlayer().getColor(),r,c);
        

         }
     // }
    }

    public void draw(Graphics2D g) {
        if (row < 3 || 4 < row) {
            
            if (Board.Pieces[row][col] != null) {
                g.setColor(color);
                g.fillOval(Window.getX(col * Board.getXdelta()),
                        Window.getY(row * Board.getYdelta()),
                        Board.getXdelta(),
                        Board.getYdelta());

            }
        }

    }

//    public static Piece getPiece(int r, int c) {
//        return Pieces[r][c];
//    }
    public int getRow() {
        return row;
    }

}
