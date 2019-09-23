package checkers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Piece {

    private Color color;

    static Piece board[][] = new Piece[Board.getNumRows()][Board.getNumColumns()];

    Piece(Color _color) {
        for (int zrow = 0; zrow < Board.getNumRows(); zrow++) {
            for (int zcol = 0; zcol < Board.getNumColumns(); zcol++) {
                if (4 < zrow) {
                    color = Color.red;
                } else if (zrow < 3) {
                    color = Color.blue;
                }
            }
        }

        color = _color;
    }

public Color getColor()
{
    return (color);
}
    public void draw(Graphics2D g, int column, int row, int xdelta, int ydelta) {

        for (int zrow = 0; zrow < Board.getNumRows(); zrow++) {
            for (int zcol = 0; zcol < Board.getNumColumns(); zcol++) {

                if (Board.getColor(zrow, zcol) == Color.black) {

                    if (zrow < 3 || 4 < zrow) {
                        if (board[zrow][zcol] != null) {
                             g.setColor(color);
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
    
      public static Piece getPiece(int r, int c) {
        return board[r][c];
    }

}
