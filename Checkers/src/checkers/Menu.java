
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
      
      if(menuShow == true)   
      {
        g.setColor(Color.cyan);   
        g.fillRect(0, 0, 1000, 1000);
      }
         
         
     }
    
}
