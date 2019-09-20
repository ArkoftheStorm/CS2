
package checkers;

import java.awt.Color;
import java.awt.Graphics2D;


public class Board {
    public final static int NUM_ROWS = 8;
    public final static int NUM_COLUMNS = 8;
    private static int xdelta = Window.getWidth2()/NUM_COLUMNS;
    private static int ydelta = Window.getHeight2()/NUM_ROWS;
    public static void Draw(Graphics2D g) {
        xdelta = Window.getWidth2()/NUM_COLUMNS;
        ydelta = Window.getHeight2()/NUM_ROWS;
        int c=0;
        int r=0;
        boolean nRow = false;
        Color prevColor = null;
        while (r<NUM_ROWS){
            c = 0;
            while (c<NUM_COLUMNS){
                if(nRow){
                    nRow =false;
                    g.setColor(prevColor);
                }
                else{
                    if(prevColor == Color.red)
                        prevColor = Color.black;
                    else
                        prevColor = Color.red;
                    g.setColor(prevColor);
                }
                
                g.fillRect(Window.getX(c*xdelta), Window.getY(r*ydelta), xdelta, ydelta);
                c++;
            }
            nRow = true;
            r++;
        }
        g.setColor(Color.white);
        for (int zi=1;zi<NUM_ROWS;zi++)
        {
            g.drawLine(Window.getX(0) ,Window.getY(0)+zi*Window.getHeight2()/NUM_ROWS ,
            Window.getX(Window.getWidth2()) ,Window.getY(0)+zi*Window.getHeight2()/NUM_ROWS );
        }
        for (int zi=1;zi<NUM_COLUMNS;zi++)
        {
            g.drawLine(Window.getX(0)+zi*Window.getWidth2()/NUM_COLUMNS ,Window.getY(0) ,
            Window.getX(0)+zi*Window.getWidth2()/NUM_COLUMNS,Window.getY(Window.getHeight2())  );
        }

    }
}
