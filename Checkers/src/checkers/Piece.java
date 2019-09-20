
package checkers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class Piece {
         private Color color; 

     

    private static Piece board[][] = new Piece[Board.getNumRows()][ Board.getNumColumns()];

Piece(Color _color)   
{
    color = _color;
}

//public Color getColor()
//{
//    return (color);
//}
public static void draw (Graphics2D g, int column, int row, int xdelta, int ydelta)

{
    
      for(int zrow =0; zrow< Board.getNumRows();zrow++)
      for(int zcol =0; zcol<  Board.getNumColumns();zcol++)
     
         if(board[zrow][zcol] == null)
         {
       
         
                 g.setColor(Color.red);
                g.fillOval(Window.getX(zcol*xdelta),
                Window.getY(zrow*ydelta),
                xdelta,
                ydelta);
                

        }
    }
}
}