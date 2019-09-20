
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
    Menu(){
        
    }
    public static void Reset()
    {
        menuShow = true;
    }
     public static void draw(Graphics g){
      Color menuColor = new Color(91, 195, 255);
         
      if(menuShow == true)   
      {
        g.setColor(menuColor);   
        g.fillRect(0, 0, 1000, 1000);
      }
         
         
     }
    
}
