package checkers;

import java.awt.*;

public class Random {
    public static void Draw(Graphics2D g) {
        g.setColor(Color.yellow);
        g.fillRect(Window.getWidth2(), Window.getHeight2(), Board.getXdelta(), Board.getYdelta());
    }
}
