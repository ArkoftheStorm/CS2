package checkers;

import java.awt.*;

public class Random {

    public static void Draw(Graphics2D g) {
        g.setColor(Color.yellow);
        g.fillRect(Window.getX(Window.getWidth2() + 10), Window.getY(0), Board.getXdelta(), Board.getYdelta());
    }
}
