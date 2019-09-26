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
    static boolean backToMenu;
    static boolean menuGone = false;

    Menu() {

    }

    public static void Reset() {
        menuShow = true;
        helpShow = false;
        backToMenu = false;
        menuGone = false;
        
    }
    
    public static void ClickButton(int xpixel, int ypixel) {

        if (menuShow == true) {
            if (xpixel > Window.getX(Window.getWidth2() / 3 - 115) && xpixel < Window.getX(Window.getWidth2() / 2 + 120) + Board.getXdelta() && ypixel > Window.getY(Window.getHeight2() / 3 + 33) && ypixel < Window.getY(Window.getHeight2() / 3 + 33) + Board.getYdelta()) {
                System.out.println("Play = " + xpixel + ypixel);
                menuShow = false;
                menuGone = true;
            } else if (xpixel > Window.getX(Window.getWidth2() / 3 - 115) && xpixel < Window.getX(Window.getWidth2() / 2 + 120) + Board.getXdelta() && ypixel > Window.getY(Window.getHeight2() / 3 + 130) && ypixel < Window.getY(Window.getHeight2() / 3 + 130) + Board.getYdelta()) {
                System.out.println("Help = " + xpixel + ypixel);
                menuShow = false;
                helpShow = true;
            }
        }
        if (menuShow == false) {
            if (xpixel > Window.getX(Window.getWidth2() + 10) && xpixel < Window.getX(Window.getWidth2() + 10) + Board.getXdelta() && ypixel > Window.getY(Window.getHeight2() / 2 - 3) && ypixel < Window.getY(Window.getHeight2() / 2 - 3) + Board.getYdelta()) {
                System.out.println("Back To Menu = " + xpixel + ypixel);
                Reset();
               
            }
        }

        if (helpShow == true) {
            if (xpixel > Window.getX(Window.getWidth2() / 3 - 115) && xpixel < Window.getX(Window.getWidth2() / 2 + 120) + Board.getXdelta() && ypixel > Window.getY(Window.getHeight2() / 3 + 311) && ypixel < Window.getY(Window.getHeight2() / 3 + 313) + Board.getYdelta()) {
                System.out.println("Back = " + xpixel + ypixel);

                helpShow = false;
                menuShow = true;
            }
        }
    }

    public static void draw(Graphics g) {
        Color menuColor = new Color(255, 140, 138);

//"BACK TO MENU" BUTTON
        if (menuShow == false) {

            g.setColor(Color.red);
            g.fillRect(Window.getX(Window.getWidth2() + 8), Window.getY(Window.getHeight2() / 2 - 3), Board.getXdelta() + 5, Board.getYdelta() + 6);
            g.setColor(Color.black);
            g.fillRect(Window.getX(Window.getWidth2() + 10), Window.getY(Window.getHeight2() / 2), Board.getXdelta(), Board.getYdelta());

            g.setColor(Color.red);
            g.setFont(new Font("times new roman", Font.BOLD, 24));
            g.drawString("Menu", Window.getWidth2() + 126, Window.getHeight2() / 2 + 90);

        }

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
            g.setFont(new Font("Segoe UI Semibold", Font.BOLD, 20));
            g.drawString("Made By: Caden Y, Luke H, and Joshua H ", Window.getWidth2() / 8 - 50, Window.getHeight2() + 50);
            g.drawString("(Don't Change Screen Size)", Window.getWidth2() / 8 + 475, Window.getHeight2() + 50);
        }
        if (helpShow == true) {
//HOW TO PLAY MENU
            g.setColor(menuColor);
            g.fillRect(0, 0, Window.xsize, Window.ysize);

            g.setColor(Color.black);
            g.fillRect(Window.getWidth2() / 5 - 44, Window.getHeight2() / 4 + 5, 656, 10);

            g.setColor(Color.black);

            g.setFont(new Font("Segoe UI Semibold", Font.BOLD, 100));
            g.drawString("HOW TO PLAY", Window.getWidth2() / 5 - 50, Window.getHeight2() / 4);

            g.setFont(new Font("Segoe UI Semibold", Font.BOLD, 25));
            g.drawString("Step 1: Decide who is player 1 and who is player 2. ", Window.getWidth2() / 5 - 44, Window.getHeight2() / 4 + 50);
            g.drawString("Step 2: Player 1 goes first. They are the bottom. ", Window.getWidth2() / 5 - 44, Window.getHeight2() / 4 + 100);
            g.drawString("Player 2 goes second. They are the top. ", Window.getWidth2() / 5 - 44, Window.getHeight2() / 4 + 130);
            g.drawString("Step 3: Click on the space where you want to move (Diagonal). ", Window.getWidth2() / 5 - 44, Window.getHeight2() / 4 + 180);
            g.drawString("Step 4: If there is a piece at a spot you can move to, ", Window.getWidth2() / 5 - 44, Window.getHeight2() / 4 + 230);
            g.drawString("and there is a empty spot diagonal to them in the way that you are ", Window.getWidth2() / 5 - 44, Window.getHeight2() / 4 + 260);
            g.drawString("moving, you can hop over them. This eliminates thier piece. ", Window.getWidth2() / 5 - 44, Window.getHeight2() / 4 + 290);
            g.drawString("Step 5: Get one of your pieces to the end to make it a king. ", Window.getWidth2() / 5 - 44, Window.getHeight2() / 4 + 330);
            g.drawString("Kings can move in all directions (Diagonal). ", Window.getWidth2() / 5 - 44, Window.getHeight2() / 4 + 360);

            g.setFont(new Font("Segoe UI Semibold", Font.BOLD, 30));
            g.drawString("How to Win: Eliminate all of the enemy pieces! ", Window.getWidth2() / 5 - 44, Window.getHeight2() / 4 + 410);

//"BACK" BUTTON
            g.setColor(Color.red);
            g.fillRect(Window.getWidth2() / 3 - 3, Window.getHeight2() / 2 + 280, 406, 57);

            g.setColor(Color.black);
            g.fillRect(Window.getWidth2() / 3, Window.getHeight2() / 2 + 283, 400, 50);

            g.setColor(Color.red);
            g.setFont(new Font("Segoe UI Semibold", Font.BOLD, 53));
            g.drawString("BACK TO MENU", Window.getWidth2() / 3 + 8, Window.getHeight2() / 2 + 327);

        }

    }

}
