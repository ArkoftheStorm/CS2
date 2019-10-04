
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
    private static boolean winner = false;
    
    Color color = null;
    public static void Draw(Graphics2D g) {
        xdelta = Window.getWidth2() / NUM_COLUMNS;
        ydelta = Window.getHeight2() / NUM_ROWS;
        int c = 0;
        int r = 0;
        boolean nRow = false;
        Image explosion;

        
        
        
        
        
        Color prevColor = Color.red;

        while (r < NUM_ROWS) {
            c = 0;
            while (c < NUM_COLUMNS) {
                if (nRow) {
                    nRow = false;
                    if(prevColor == Color.black)
                        if(r > 4 && Menu.menuShow)
                            Pieces[r][c] = new Piece (Player.getPlayer(0).getColor(),r,c);
                    else
                        if(r < 3 && Menu.menuShow)   
                            Pieces[r][c] = new Piece (Player.getPlayer(1).getColor(),r,c);
                } 
                else {
                    if (prevColor == Color.red) {
                        prevColor = Color.black;
                        board[r][c] = prevColor;
                        if(r < 3 && Menu.menuShow)
                            Pieces[r][c] = new Piece (Player.getPlayer(1).getColor(),r,c);
                         else  if(r > 4 && Menu.menuShow)
                            Pieces[r][c] = new Piece (Player.getPlayer(0).getColor(),r,c);
                    }
                    else if(prevColor == Color.black){
                        prevColor = Color.red;
                        if(Menu.menuShow)
                            board[r][c] = prevColor;
                    
                    }
                }
                g.setColor(prevColor);
                g.fillRect(Window.getX(c * xdelta), Window.getY(r * ydelta), xdelta, ydelta);
                if(Pieces[r][c] != null)
                    Pieces[r][c].draw(g);
                c++;
            }
            nRow = true;
            r++;
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
        g.setFont (new Font("times new roman",Font.BOLD,20));
        if(Player.GetCurrentPlayer() == Player.getPlayer(0))
            g.drawString("Player One's Turn", Window.getX(0), Window.getY(0)-5);
        else 
            g.drawString("Player Two's Turn", Window.getX(0), Window.getY(0)-5);

    
        
        if(winner == true)
        {
            g.setColor(Color.white);
            g.setFont (new Font("times new roman",Font.BOLD,50));
            if(Player.GetCurrentPlayer() == Player.getPlayer(0))
                g.drawString("Player 1 Wins!", Window.getX(0), Window.getY(0)+50); 
            else
                g.drawString("Player 2 Wins!", Window.getX(0), Window.getY(0)+50); 
        }
        
        
        
        
   }
    public static void switchPiece(int xPixel, int yPixel){
        if(Player.GetCurrentPlayer().getChangeCount() <= 0)
            return;
        int _col = (xPixel-Window.getX(0))/xdelta;
        int _row = (yPixel-Window.getY(0))/ydelta;
        if(_col >= NUM_COLUMNS || _col <= 0 || _row >= NUM_ROWS || _row <= 0)
            return;
        if(Pieces[_row][_col] != null && _row < NUM_ROWS && _col < NUM_COLUMNS)
            Pieces[_row][_col].switchColor();
        Player.GetCurrentPlayer().decChangeCount();
        Player.SwitchTurn();
    }
    public static void selectpiece(int xPixel, int yPixel){
        if(Menu.menuShow)
            return;

        int _col = (xPixel-Window.getX(0))/xdelta;
        int _row = (yPixel-Window.getY(0))/ydelta;
          System.out.println(_row+"   " +_col);
        
               if(Board.getColor(_row, _col) != null && Board.getColor(_row, _col) != Color.BLACK)
                  return;
               
                   
                   
                        if(_row < NUM_ROWS && _row > -1 && _col < NUM_COLUMNS && _col > -1)
                            if(Board.Pieces[_row][_col] != null && Board.Pieces[_row][_col].getColor() == Player.GetCurrentPlayer().getColor() && Board.Pieces[_row][_col].getColor() == Player.GetCurrentPlayer().getColor()){     
                              Pieces[_row][_col].ClickPieceT();
                              Piece.piecemovesT();
                            }
                    



//                    if(Piece.piecemoves())
//                       movepiece(_row,_col);
//                    else 
//                      Piece.piecemovesF();
                    
                 
                    
                 

    
    }
     public static void movepiece(int xPixel, int yPixel){
        if(Menu.menuShow || Menu.helpShow)
            return;


        int _col = (xPixel/xdelta)-1 ;
        int _row = (yPixel-Window.getY(0))/ydelta;
       if(Board.getColor(_row, _col) != Color.BLACK)
                  return; 
     //     System.out.println(_row+"   " +_col);
    Board.Pieces[_row][_col] = new Piece(Player.getCurrentPlayer().getColor(),_row,_col);
    for(int r = 0; r<NUM_ROWS; r++){
        for(int c = 0; c<NUM_COLUMNS; c++){
            if(Board.Pieces[r][c] != null && Board.Pieces[r][c].clickPiece)
                Board.Pieces[r][c].clickPiece = false;
        }
    }
    Piece.piecemovesF();
    Player.SwitchTurn();      

    }
     
     public static void checkWin() {
         
         if(winner)
                 return;
       
         winner = checkWinWholeBoard();
         
     }
     public static boolean checkWinWholeBoard(){
        
         
         for(int r = 0; r<NUM_ROWS;r++)
         {
            for(int c = 0; c<NUM_COLUMNS;c++)
               {
                   if(Board.Pieces[r][c].getColor() == Color.BLUE && Board.Pieces[r][c].getColor() != Color.RED);
                   return(true);
//                   else
//                   return(false);
               }
         }
         return(false);
     }
    
     
    public static void Reset(){
        winner = false;
        
        
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
        if(r<NUM_ROWS && c<NUM_COLUMNS && c>0 && r>0)
            return board[r][c];
        return null;
    }

}

