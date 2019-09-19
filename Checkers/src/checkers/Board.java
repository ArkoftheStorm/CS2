
package checkers;

import java.awt.Color;
import java.awt.Graphics2D;


public class Board {
    private final static int NUM_ROWS = 8;
    private final static int NUM_COLUMNS = 8;
    private static int xdelta = Window.getWidth2()/NUM_COLUMNS;
    private static int ydelta = Window.getHeight2()/NUM_ROWS;
    public static void Draw(Graphics2D g) {
        xdelta = Window.getWidth2()/NUM_COLUMNS;
        ydelta = Window.getHeight2()/NUM_ROWS;
        g.setColor(Color.black);
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
