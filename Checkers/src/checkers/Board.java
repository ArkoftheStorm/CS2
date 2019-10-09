package checkers;

import java.awt.*;

public class Board {

    private final static int NUM_ROWS = 8;
    private final static int NUM_COLUMNS = 8;
    private static int xdelta = Window.getWidth2() / NUM_COLUMNS;
    private static int ydelta = Window.getHeight2() / NUM_ROWS;
    private static Color board[][] = new Color[NUM_ROWS][NUM_COLUMNS];
    static int piecemove = 0;

    public static Piece Pieces[][] = new Piece[NUM_ROWS][NUM_COLUMNS];

    private static boolean winRed = false;
    private static boolean winBlu = false;


    Color color = null;

    public static void Draw(Graphics2D g) {
        xdelta = Window.getWidth2() / NUM_COLUMNS;
        ydelta = Window.getHeight2() / NUM_ROWS;
        boolean nRow = false;
        Image explosion;

        Color prevColor = Color.red;

        for (int r = 0; r < NUM_COLUMNS; r++) {
            for (int c = 0; c < NUM_COLUMNS; c++) {
                if (nRow) {

                    nRow = false;
                    if (prevColor == Color.black) {
                        if (r > 4 && Menu.menuShow) {
                            Pieces[r][c] = new Piece(Player.getPlayer(0).getColor(), r, c);
                        } else if (Menu.menuShow && c != 0) {
                            Pieces[r][c] = null;
                        } else if (r < 3 && Menu.menuShow) {
                            Pieces[r][c] = new Piece(Player.getPlayer(1).getColor(), r, c);
                        } else if (Menu.menuShow) {
                            Pieces[r][c] = null;
                        }
                    }
                    board[r][c] = prevColor;
                } else {
                    if (prevColor == Color.red) {
                        prevColor = Color.black;
                        board[r][c] = prevColor;
                        if (r < 3 && Menu.menuShow) {
                            Pieces[r][c] = new Piece(Player.getPlayer(1).getColor(), r, c);
                        } else if (r > 4 && Menu.menuShow) {
                            Pieces[r][c] = new Piece(Player.getPlayer(0).getColor(), r, c);
                        } else if (Menu.menuShow) {
                            Pieces[r][c] = null;
                        }
                    } else if (prevColor == Color.black) {
                        prevColor = Color.red;
                        if (Menu.menuShow) {
                            board[r][c] = prevColor;
                        }

                    }
                }
                g.setColor(prevColor);
                g.fillRect(Window.getX(c * xdelta), Window.getY(r * ydelta), xdelta, ydelta);
                if (Pieces[r][c] != null) {
                    Pieces[r][c].draw(g);
                }
//                c++;
            }
            nRow = true;
//            r++;
        }
        g.setColor(Color.white);
        for (int zi = 1; zi < NUM_ROWS; zi++) {
            g.drawLine(Window.getX(0), Window.getY(0) + zi * Window.getHeight2() / NUM_ROWS,
                    Window.getX(Window.getWidth2()), Window.getY(0) + zi * Window.getHeight2() / NUM_ROWS);
        }
        for (int zi = 1; zi < NUM_COLUMNS; zi++) {
            g.drawLine(Window.getX(0) + zi * Window.getWidth2() / NUM_COLUMNS, Window.getY(0),
                    Window.getX(0) + zi * Window.getWidth2() / NUM_COLUMNS, Window.getY(Window.getHeight2()));
        }
        g.setColor(Player.GetCurrentPlayer().getColor());
        g.setFont(new Font("times new roman", Font.BOLD, 20));
        if (Player.GetCurrentPlayer() == Player.getPlayer(0)) {
            g.drawString("Player One's Turn", Window.getX(0), Window.getY(0) - 5);
        } else {
            g.drawString("Player Two's Turn", Window.getX(0), Window.getY(0) - 5);
        }

        g.setColor(Color.white);
        g.setFont(new Font("times new roman", Font.BOLD, 50));

        if (winRed == true) {
                g.drawString("Player 1 Wins!", Window.getX(0), Window.getY(0) + 50);
        }



        if (winBlu == true) {

                g.drawString("Player 2 Wins!", Window.getX(0), Window.getY(0) + 50);
        }

    }

    public static void switchPiece(int xPixel, int yPixel) {
        if (Player.GetCurrentPlayer().getChangeCount() <= 0) {
            return;
        }
        if(Board.getWinBlu() || Board.getWinRed())
            return;
        int _col = (xPixel - Window.getX(0)) / xdelta;
        int _row = (yPixel - Window.getY(0)) / ydelta;
        if (_col >= NUM_COLUMNS || _col <= 0 || _row >= NUM_ROWS || _row <= 0) {
            return;
        }
        if (Pieces[_row][_col] != null && _row < NUM_ROWS && _col < NUM_COLUMNS) {
            Pieces[_row][_col].switchColor();
        }
        Player.GetCurrentPlayer().decChangeCount();
        Player.SwitchTurn();
    }

    public static void selectpiece(int xPixel, int yPixel) {
        if (Menu.menuShow) {
            return;
        }
        if(Board.getWinBlu() || Board.getWinRed())
            return;

        int _col = (xPixel - Window.getX(0)) / xdelta;
        int _row = (yPixel - Window.getY(0)) / ydelta;


        if (Board.Pieces[_row][_col] != null) {
            Board.Pieces[_row][_col].checkKing();
        }
        System.out.println(_row + "   " + _col);

        if (Board.getColor(_row, _col) != null && Board.getColor(_row, _col) != Color.BLACK) {
            return;
        }

        if (_row < NUM_ROWS && _row > -1 && _col < NUM_COLUMNS && _col > -1) {

            if (Board.Pieces[_row][_col] != null && Board.Pieces[_row][_col].getColor() == Player.GetCurrentPlayer().getColor() && Board.Pieces[_row][_col].getColor() == Player.GetCurrentPlayer().getColor()) {
                Pieces[_row][_col].ClickPieceT();
                Piece.setDelete(_row, _col);
                Piece.piecemovesT();
            }
        }

//                    if(Piece.piecemoves())
//                       movepiece(_row,_col);
//                    else 
//                      Piece.piecemovesF();
    }

    public static void movepiece(int xPixel, int yPixel) {
        if (Menu.menuShow || Menu.helpShow) {
            return;
        }
        if(Board.getWinBlu() || Board.getWinRed())
            return;
        int _col = (xPixel - Window.getX(0)) / xdelta;
        int _row = (yPixel - Window.getY(0)) / ydelta;
        if (Board.getColor(_row, _col) != Color.BLACK) {

            return;
        } //     System.out.println(_row+"   " +_col);
        else if (Player.GetCurrentPlayer().getColor() == Color.blue && _row < Piece.getDeleteRow() && !Board.Pieces[Piece.getDeleteRow()][Piece.getDeleteCol()].king) {
            return;
        } else if (Player.GetCurrentPlayer().getColor() == Color.red && _row > Piece.getDeleteRow() && !Board.Pieces[Piece.getDeleteRow()][Piece.getDeleteCol()].king) {
            return;
        } else if (_row == Piece.getDeleteRow() && _col == Piece.getDeleteCol()) {
            Board.Pieces[_row][_col].ClickPieceF();
            Board.Pieces[_row][_col].piecemovesF();
            return;
        } else if (_row > Piece.getDeleteRow() + 1
                || _row < Piece.getDeleteRow() - 1
                || _col > Piece.getDeleteCol() + 1
                || _col < Piece.getDeleteCol() - 1) {
            return;
        } 
        else if(_row > NUM_ROWS|| _row < 0 || _col > NUM_COLUMNS|| _col < 0 || _row + 1 > NUM_ROWS|| _row - 1< 0 || _col + 1> NUM_COLUMNS|| _col - 1 < 0)
            return;
        else if (Board.Pieces[_row][_col] != null && Player.GetCurrentPlayer().getColor() != Board.Pieces[_row][_col].getColor()) {
            int tempDelRow = _row;
            int tempDelCol = _col;
            if (_row == Piece.getDeleteRow() + 1 && _col == Piece.getDeleteCol() - 1 && Board.Pieces[_row + 1][_col - 1] == null) {

                _row += 1;
                _col -= 1;
                   
            } else if (_row == Piece.getDeleteRow() + 1 && _col == Piece.getDeleteCol() +1 &&Board.Pieces[_row + 1][_col + 1] == null) {
                _row += 1;
                _col += 1;
            }

            else if (_row == Piece.getDeleteRow() - 1 && _col == Piece.getDeleteCol() +1 &&Board.Pieces[_row-1][_col+1] ==null)
           {
                _row -= 1;
                _col += 1;
           }
            else if (_row == Piece.getDeleteRow() - 1 && _col == Piece.getDeleteCol() -1 && Board.Pieces[_row-1][_col-1] ==null)
           {
                _row -= 1;
                _col -= 1;
           }
            if(Board.Pieces[_row][_col] != null)
                return;
            Piece.piecemovesF();
            Piece.deletepiece(tempDelRow, tempDelCol);
            Piece.piecemovesT();
             
        }
        if(Board.Pieces[_row][_col] != null && Board.Pieces[_row][_col].getColor() == Player.GetCurrentPlayer().getColor())
            return;
        Board.Pieces[_row][_col] = new Piece(Player.getCurrentPlayer().getColor(), _row, _col);
        for (int r = 0; r < NUM_ROWS; r++) {
            for (int c = 0; c < NUM_COLUMNS; c++) {
                if (Board.Pieces[r][c] != null && Board.Pieces[r][c].clickPiece) {
                    Board.Pieces[r][c].clickPiece = false;
                }
            }
        }
        if (Board.Pieces[Piece.getDeleteRow()][Piece.getDeleteCol()].king) {
            Board.Pieces[_row][_col].king = true;
        }
        Piece.piecemovesF();
        if (Piece.piecemoves() == false) {

            Piece.deletepiece(Piece.getDeleteRow(), Piece.getDeleteCol());

        }

        Player.SwitchTurn();

    }

    public static void checkWinner(){
        int win = -1;
//        for (int r = 0; r < NUM_ROWS; r++){ 
//            for (int c = 0; c < NUM_COLUMNS; c++){ 
//                if(Board.Pieces[r][c] != null && Board.Pieces[r][c].getColor() == Color.red)
//                    Board.Pieces[r][c] = null;
//            }
//        }
        for (int r = 0; r < NUM_ROWS && win != 0; r++) {
            for (int c = 0; c < NUM_COLUMNS && win != 0; c++) {
                if(Board.Pieces[r][c] == null){}
                else if(Board.Pieces[r][c].getColor() != Player.getPlayer(0).getColor())
                    win = 0;
                else if(Board.Pieces[r][c].getColor() == Player.getPlayer(0).getColor())
                    win = 1;
            }
        }
        if(win == 0){
            win = -1;
            for (int r = 0; r < NUM_ROWS && win != 0; r++) {
                for (int c = 0; c < NUM_COLUMNS && win != 0; c++) {
                    if(Board.Pieces[r][c] == null){}
                    else if(Board.Pieces[r][c].getColor() != Player.getPlayer(1).getColor())
                        win = 0;
                    else if(Board.Pieces[r][c].getColor() == Player.getPlayer(1).getColor())
                        win = 2;
                }
            }
        }
        if(win == 1)
            winRed = true;
        else if(win == 2)
            winBlu = true;
    }
   

    public static void Reset() {
        
        winBlu = false;
        winRed = false;

    }

    public static int getNumRows() {
        return NUM_ROWS;
    }

    public static int getNumColumns() {
        return NUM_COLUMNS;
    }

    public static int getXdelta() {
        return xdelta;
    }

    public static int getYdelta() {
        return ydelta;
    }

    public static Color getColor(int r, int c) {
        if (r < NUM_ROWS && c < NUM_COLUMNS && c >= 0 && r >= 0) {
            return board[r][c];
        }
        return null;
    }
    public static boolean getWinBlu(){
        return winBlu;
    }
    public static boolean getWinRed(){
        return winRed;
    }
    public static void kingCheck(){
        for (int r = 0; r < NUM_ROWS; r++) 
            for (int c = 0; c < NUM_COLUMNS; c++) 
                if(Board.Pieces[r][c] != null)
                    Board.Pieces[r][c].checkKing();
    }
}
