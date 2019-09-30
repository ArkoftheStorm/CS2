package checkers;

import static checkers.Menu.backToMenu;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Checkers extends JFrame implements Runnable {

    boolean animateFirstTime = true;
    Image image;
    Image explosion;
    Graphics2D g;
    Color brown = new Color(193, 154, 107);
    static boolean menu;
    //Image explosion;
    int timeCount;

    public static void main(String[] args) {
        Checkers frame = new Checkers();
        frame.setSize(Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public Checkers() {
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {

                if (e.BUTTON1 == e.getButton()) {
                }


                Menu.ClickButton(e.getX(), e.getY());
                Piece.Animate(e.getX(), e.getY());

                if (e.getX() > Window.getX(Window.getWidth2() + 10)
                        && e.getY() > Window.getY(0)
                        && e.getX() < Window.getX(Window.getWidth2() + 10) + Board.getXdelta()
                        && e.getY() < Window.getY(0) + Board.getYdelta()) {
                    Random.Roll(g);
                }


                if (e.BUTTON3 == e.getButton()) {
                    Board.switchPiece(e.getX(), e.getY());
                }
                repaint();
            }
        });

//    addMouseMotionListener(new MouseMotionAdapter() {
//      public void mouseDragged(MouseEvent e) {
//
//        repaint();
//      }
//    });
//
//    addMouseMotionListener(new MouseMotionAdapter() {
//      public void mouseMoved(MouseEvent e) {
//
//        repaint();
//      }
//    });
        addKeyListener(new KeyAdapter() {

            public void keyPressed(KeyEvent e) {
                if (e.VK_SPACE == e.getKeyCode()) {

                } else if (e.VK_PERIOD == e.getKeyCode()) {
                    Menu.menuShow = false;
                }

                if (e.VK_UP == e.getKeyCode()) {

                } else if (e.VK_DOWN == e.getKeyCode()) {

                } else if (e.VK_LEFT == e.getKeyCode()) {

                } else if (e.VK_RIGHT == e.getKeyCode()) {

                } else if (e.VK_ESCAPE == e.getKeyCode()) {
                    reset();
                }
                repaint();
            }
        });
        init();
        start();
    }
    Thread relaxer;
////////////////////////////////////////////////////////////////////////////

    public void init() {
        requestFocus();
    }
////////////////////////////////////////////////////////////////////////////

    public void destroy() {
    }
////////////////////////////////////////////////////////////////////////////

    public void paint(Graphics gOld) {
        if (image == null || Window.xsize != getSize().width || Window.ysize != getSize().height) {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
            if (explosion == null || Window.xsize != getSize().width || Window.ysize != getSize().height) {
            Window.xsize = getSize().width;
            Window.ysize = getSize().height;
            image = createImage(Window.xsize, Window.ysize);
            g = (Graphics2D) image.getGraphics();
            g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
        }
//fill background

        g.setColor(brown);
        g.fillRect(0, 0, Window.xsize, Window.ysize);

        int x[] = {Window.getX(0), Window.getX(Window.getWidth2()), Window.getX(Window.getWidth2()), Window.getX(0), Window.getX(0)};
        int y[] = {Window.getY(0), Window.getY(0), Window.getY(Window.getHeight2()), Window.getY(Window.getHeight2()), Window.getY(0)};
//fill border
        g.setColor(Color.white);
        g.fillPolygon(x, y, 4);
// draw border
        g.setColor(Color.red);
        g.drawPolyline(x, y, 5);

//      System.out.println("checker time");
//  draw methods/classes
        if (animateFirstTime) {
            gOld.drawImage(image, 0, 0, null);
            return;
        }

        Board.Draw(g);
        Random.Draw(g);
        Menu.draw(g);

       
    
            if (10 % 9 == 1) 
            {
                g.drawImage(explosion, 50, 50, 100, 100, this);
            }
        

        gOld.drawImage(image, 0, 0, null);
    }

    public void drawImage(Image image, int xpos, int ypos, double rot, double xscale,
            double yscale) {

        int width = image.getWidth(this);
        int height = image.getHeight(this);

        g.translate(xpos, ypos);
        g.rotate(rot * Math.PI / 180.0);
        g.scale(xscale, yscale);

        g.drawImage(image, -width / 2, -height / 2,
                width, height, this);

        g.scale(1.0 / xscale, 1.0 / yscale);
        g.rotate(-rot * Math.PI / 180.0);
        g.translate(-xpos, -ypos);
    }
////////////////////////////////////////////////////////////////////////////
// needed for     implement runnable

    public void run() {
        while (true) {
            animate();
            repaint();
            double seconds = .1;    //time that 1 frame takes.
            int miliseconds = (int) (1000.0 * seconds);
            try {
                Thread.sleep(miliseconds);
            } catch (InterruptedException e) {
            }
        }
    }

/////////////////////////////////////////////////////////////////////////
    public void reset() {

        Menu.Reset();

        timeCount = 0;
       
    }
/////////////////////////////////////////////////////////////////////////

    public void animate() {

        if (animateFirstTime) {
            animateFirstTime = false;
            if (Window.xsize != getSize().width || Window.ysize != getSize().height) {
                Window.xsize = getSize().width;
                Window.ysize = getSize().height;
            }
            explosion = Toolkit.getDefaultToolkit().getImage("./explody_boi.GIF");
            reset();
            if (Menu.menuShow) {
                Player.Reset();
                
            }

            timeCount++;
//             explosion = Toolkit.getDefaultToolkit().getImage("./explody_boi.GIF");

        }
    }

////////////////////////////////////////////////////////////////////////////
    public void start() {
        if (relaxer == null) {
            relaxer = new Thread(this);
            relaxer.start();
        }
    }
////////////////////////////////////////////////////////////////////////////

    public void stop() {
        if (relaxer.isAlive()) {
            relaxer.stop();
        }
        relaxer = null;
    }

}
