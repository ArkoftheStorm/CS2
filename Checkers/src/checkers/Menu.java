package checkers;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Menu {

    static boolean menuShow;
    static int but1;
    static int but2;
    static int but3;

    Menu() {

    }

    public static void Reset() {
        menuShow = true;
    }

     public static void draw(Graphics g){
      Color menuColor = new Color(91, 195, 255);

      if(menuShow == false)   
         return;      
      
      
        g.setColor(menuColor);   
        g.fillRect(0, 0, Window.xsize, Window.ysize);
        
        g.setColor(Color.black);
        g.setFont(new Font("Segoe UI Semibold",Font.BOLD,125));
        g.drawString("CHECKERS",Window.getWidth2()/5,Window.getHeight2()/4); 
      
        g.fillRect(0, 0, Window.getWidth2()/2,Window.getHeight2()/4);
         
     }
    


}
