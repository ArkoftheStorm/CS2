package checkers;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Menu {

    static boolean menuShow;
    static boolean helpShow = false;

    Menu() {

    }

    public static void Reset() {
        menuShow = true;
        helpShow = false;
    }

    public static void ClickButton(int xpixel, int ypixel) {

        if (menuShow == true) {
            if (xpixel > Window.getX(Window.getWidth2() / 3 - 115) && xpixel < Window.getX(Window.getWidth2() / 2 + 120) + Board.getXdelta() && ypixel > Window.getY(Window.getHeight2() / 3 + 33) && ypixel < Window.getY(Window.getHeight2() / 3 + 33) + Board.getYdelta()) {
                System.out.println("" + xpixel + ypixel);
                menuShow = false;
            } else if (xpixel > Window.getX(Window.getWidth2() / 3 - 115) && xpixel < Window.getX(Window.getWidth2() / 2 + 120) + Board.getXdelta() && ypixel > Window.getY(Window.getHeight2() / 3 + 130) && ypixel < Window.getY(Window.getHeight2() / 3 + 130) + Board.getYdelta()) {
                System.out.println("Help = " + xpixel + ypixel);
                helpShow = true;
                menuShow = false;
            }
        }
    }

    public static void draw(Graphics g) {
        Color menuColor = new Color(255, 140, 138);

        if (menuShow == true) {

            g.setColor(menuColor);
            g.fillRect(0, 0, Window.xsize, Window.ysize);

//"PLAY" BUTTON        
            g.setColor(Color.red);
            g.fillRect(Window.getWidth2() / 3 - 3, Window.getHeight2() / 2 - 3, 406, 57);

            g.setColor(Color.black);
            g.fillRect(Window.getWidth2() / 3, Window.getHeight2() / 2, 400, 50);

            g.setColor(Color.red);
            g.setFont(new Font("Segoe UI Semibold", Font.BOLD, 55));
            g.drawString("PLAY", Window.getWidth2() / 3 + 130, Window.getHeight2() / 2 + 45);

//"HOW TO PLAY" BUTTON        
            g.setColor(Color.red);
            g.fillRect(Window.getWidth2() / 3 - 3, Window.getHeight2() / 2 + 100, 406, 57);

            g.setColor(Color.black);
            g.fillRect(Window.getWidth2() / 3, Window.getHeight2() / 2 + 103, 400, 50);

            g.setColor(Color.red);
            g.setFont(new Font("Segoe UI Semibold", Font.BOLD, 53));
            g.drawString("HOW TO PLAY", Window.getWidth2() / 3 + 23, Window.getHeight2() / 2 + 147);

//"CHECKERS"
            g.setColor(Color.black);
            g.setFont(new Font("Segoe UI Semibold", Font.BOLD, 125));
            g.drawString("CHECKERS", Window.getWidth2() / 5 - 15, Window.getHeight2() / 4);
        }
        if (helpShow == true) {

            g.setColor(menuColor);
            g.fillRect(0, 0, Window.xsize, Window.ysize);

        }

    }

}
