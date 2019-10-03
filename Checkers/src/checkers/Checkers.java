package checkers;

import static checkers.Menu.backToMenu;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;

public class Checkers extends JFrame implements Runnable {

    boolean animateFirstTime = true;
    Image image;
    public static Image explosion;
    public static Image bluePiece;
    public static Image yellowPiece;
    public static Image redPiece;
    Graphics2D g;
    Color brown = new Color(193, 154, 107);
    static boolean menu;
    sound theme = null;
    
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
                boolean menu = Menu.menuShow;
                
                Menu.ClickButton(e.getX(), e.getY());

                if(Menu.menuShow || Menu.helpShow)
                    return;
                if (e.getX() > Window.getX(Window.getWidth2() + 10)
                        && e.getY() > Window.getY(0)
                        && e.getX() < Window.getX(Window.getWidth2() + 10) + Board.getXdelta()
                        && e.getY() < Window.getY(0) + Board.getYdelta()) {
                    Random.Roll(g);
                }

                else {
                    Piece.Animate(e.getX(), e.getY());

                    if(Piece.piecemoves)
                        Board.movepiece(e.getX(), e.getY());
                    else
                        Board.selectpiece(e.getX(),e.getY());
                    Board.switchPiece(e.getX(), e.getY());

                    }
                 
                 
                
               



                


                if (e.BUTTON3 == e.getButton()) {

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

       
        if(Random.getExplode()){
            drawExplosion();
            Random.incExplodeTime();
            if(Random.explodeTimeDone())
                Random.doneExplode();
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
        Board.Reset();
        
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
            bluePiece = Toolkit.getDefaultToolkit().getImage("./blue_piece");
            yellowPiece = Toolkit.getDefaultToolkit().getImage("./yellow_piece");
            redPiece = Toolkit.getDefaultToolkit().getImage("./red_piece");
//             explosion = Toolkit.getDefaultToolkit().getImage("./explody_boi.GIF");
            theme = new sound("themeMusic.wav");
            reset();
            if (Menu.menuShow) {
                Board.Reset();
                Player.Reset();
                
         
            }
                    if (theme.donePlaying)
          theme = new sound("themeMusic.wav");
        }


            timeCount++;
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

    
class sound implements Runnable {
    Thread myThread;
    File soundFile;
    public boolean donePlaying = false;
    sound(String _name)
    {
        soundFile = new File(_name);
        myThread = new Thread(this);
        myThread.start();
    }
    public void run()
    {
        try {
        AudioInputStream ais = AudioSystem.getAudioInputStream(soundFile);
        AudioFormat format = ais.getFormat();
    //    System.out.println("Format: " + format);
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
        SourceDataLine source = (SourceDataLine) AudioSystem.getLine(info);
        source.open(format);
        source.start();
        int read = 0;
        byte[] audioData = new byte[16384];
        while (read > -1){
            read = ais.read(audioData,0,audioData.length);
            if (read >= 0) {
                source.write(audioData,0,read);
            }
        }
        donePlaying = true;
   
        source.drain();
        source.close();
        }
        catch (Exception exc) {
            System.out.println("error: " + exc.getMessage());
            exc.printStackTrace();
        }
    }

}
    public void drawExplosion(){
        g.drawImage(Checkers.explosion,
                (Random.getBombCol()+1)*Board.getXdelta(),
                Random.getBombRow()*Board.getYdelta(),
                Board.getYdelta() * (Random.getBombBorder() * 2),
                Board.getXdelta() * (Random.getBombBorder() * 2),
                this);
    }
    public void drawPieceImage(int row, int col){

    }
    
}
