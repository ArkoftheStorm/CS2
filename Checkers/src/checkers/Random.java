package checkers;

import java.awt.*;

public class Random {
    public static void Draw(Graphics2D g) {
        g.setColor(Color.red);
        g.drawRect(900, 900, Board.getXdelta(), Board.getYdelta());
    }
}
