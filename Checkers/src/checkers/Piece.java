package checkers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Piece {

    private Color color;

    private static Piece board[][] = new Piece[Board.getNumRows()][Board.getNumColumns()];

    Piece(Color _color) {
        color = _color;
    }

//public Color getColor()
//{
//    return (color);
//}
    public static void draw(Graphics2D g, int column, int row, int xdelta, int ydelta) {

        for (int zrow = 0; zrow < Board.getNumRows(); zrow++) {
            for (int zcol = 0; zcol < Board.getNumColumns(); zcol++) {

                if (Board.getColor(zrow, zcol) == Color.black) {
                    
                    if(zrow <3 || 4<zrow){
                   if (board[zrow][zcol] == null) {
                         if (4<zrow)
                        g.setColor(Color.red);
                         else if(zrow<3)
                        g.setColor(Color.blue);
                        
                        g.fillOval(Window.getX(zcol * xdelta),
                                Window.getY(zrow * ydelta),
                                xdelta,
                                ydelta);

                    }
                    }
                }
            }
        }

    }
}
