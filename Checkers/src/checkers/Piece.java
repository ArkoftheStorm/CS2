
package checkers;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Piece {

    private Color color;
    private int row;
    private int col;
    boolean clickPiece;
    static boolean piecemoves;
    private static int deleteRow;
    private static int deleteCol;
    boolean king;
    
    Piece(Color _color, int _row, int _col) {
        for (int zrow = 0; zrow < Board.getNumRows(); zrow++) {
            for (int zcol = 0; zcol < Board.getNumColumns(); zcol++) {

                if (4 < zrow) 
                {
                   color = Color.RED;
                } 
                else if (zrow < 3) 
                {
                   color = Color.blue;
                }
              
            }
        }
        
        row = _row;
        col = _col;
        color = _color;
        clickPiece = false;
        piecemoves = false;
        king = false;
        
    }

    public Color getColor() {
        return (color);
    }
    public Color makeYellow(){
        return(Color.YELLOW);
    }


    
       public static void deletepiece(int xPixel, int yPixel){
        if(Menu.menuShow || Menu.helpShow)
            return;


       int _col = (xPixel-Window.getX(0))/Board.getXdelta();
       int _row = (yPixel-Window.getY(0))/Board.getYdelta();
        

         
      for (int row=0;row<Board.getNumRows();row++)
        {
            for (int col=0;col<Board.getNumColumns();col++)
            {
                if(Piece.piecemoves() == false)
                {
                       Board.Pieces[deleteRow][deleteCol] = null;
                }
                
                
            }
        }
        
     

    } 
    public static void Addpiece(int xpixel, int ypixel){
               if ((xpixel - Window.getX(0)) < 0 || (xpixel - Window.getX(0)) > Window.getWidth2()) {
            return;
        }
        if ((ypixel - Window.getY(0)) < 0 || (ypixel - Window.getY(0)) > Window.getHeight2()) {
            return;
        }
        int ydelta = Window.getHeight2() / Board.getNumRows();
        int xdelta = Window.getWidth2() /  Board.getNumColumns();

        int zcol = (xpixel-Window.getX(0))/xdelta;
        int zrow = (ypixel-Window.getY(0))/ydelta;

        
//        int r = zrow;
//        int c = zcol;
//System.out.println(zrow+"   " +zcol);


    //  if(Menu.menuGone== true){
        if(Board.getColor(zrow, zcol) == Color.BLACK )
         {
          
          Board.Pieces[zrow][zcol] = new Piece(Player.getCurrentPlayer().getColor(),zrow,zcol);

//           System.out.println(zrow+"   " +zcol);

           System.out.println("The Color is "+Player.getCurrentPlayer().getColor());
           

             
         // Board.Pieces[r][c] = new Piece(Player.GetCurrentPlayer().getColor(),r,c);
        

         }
     // }
    }

    public void draw(Graphics2D g) {
         
//        if (row < 3 || 4 < row) {
           if(clickPiece)
                g.setColor(Color.YELLOW);
           else
               g.setColor(color);
            if (Board.Pieces[row][col] != null) {
                g.fillOval(Window.getX(col * Board.getXdelta()),
                        Window.getY(row * Board.getYdelta()),
                        Board.getXdelta(),
                        Board.getYdelta());
                g.setColor(Color.white);
//                if(king){
                    g.setFont (new Font("times new roman",Font.BOLD,24));
                    g.drawString("K", Window.getX(col * Board.getXdelta())+28, Window.getY(row * Board.getYdelta())+40);
//                }

            }

          
            
            
            
//        }

    }
    public void checkKing(){
        if(Player.GetCurrentPlayer().getColor() == Color.blue && row == 7)
            king = true;
        else if(Player.GetCurrentPlayer().getColor() == Color.red && row == 0)
            king = true;
    }

//    public static Piece getPiece(int r, int c) {
//        return Pieces[r][c];
//    }
    public int getRow() {
        return row;
    }
    public int getCol() {
        return col;
    }
    public void ClickPieceT(){
        clickPiece = true;
    }
    public void ClickPieceF(){
        clickPiece = false;
        
    }
    public static  void piecemovesT(){
        piecemoves= true;
    }
     public static void piecemovesF(){
        piecemoves= false;
    }
     public static boolean piecemoves(){
         return(piecemoves);
     }
     public static int getDeleteRow(){
         return deleteRow;
     }
     public static int getDeleteCol(){
         return deleteCol;
     }
     public static void setDelete(int row, int col){
         deleteCol = col;
         deleteRow = row;
     }
     
     
     
    public void switchColor(){
        if(color == Color.red)
            color = Color.blue;
        else if (color == Color.blue)
            color = Color.red;
        else{}
    }

}

