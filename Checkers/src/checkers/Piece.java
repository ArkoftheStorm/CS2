
package checkers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;


public class Piece {
         private Color color; 
   
Piece(Color _color)   
{
    color = _color;
}
public Color getColor()
{
    return (color);
}
public void draw (Graphics2D g, int column, int row, int xdelta, int ydelta)
{
                   g.setColor(color);
                g.fillOval(Window.getX(column*xdelta),
                Window.getY(row*ydelta),
                xdelta,
                ydelta);
                
}
}
