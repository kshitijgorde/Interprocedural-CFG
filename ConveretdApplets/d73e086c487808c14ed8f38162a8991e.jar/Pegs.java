import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.ImageObserver;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.applet.AudioClip;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Pegs extends Applet implements Runnable, MouseListener, MouseMotionListener, KeyListener
{
    Image buffer;
    Graphics gBuffer;
    Thread runner;
    GameEngine game;
    AudioClip tap;
    AudioClip pop;
    AudioClip rot;
    public static final int FPS = 60;
    MouseEvent a;
    KeyEvent blah;
    
    public void init() {
        try {
            this.tap = this.getAudioClip(this.getCodeBase(), "tap.au");
            this.pop = this.getAudioClip(this.getCodeBase(), "pop.au");
            this.rot = this.getAudioClip(this.getCodeBase(), "rot.au");
        }
        catch (Exception ex) {}
        this.tap.play();
        this.pop.play();
        this.rot.play();
        this.buffer = this.createImage(600, 600);
        this.gBuffer = this.buffer.getGraphics();
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addKeyListener(this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.game != null) {
            this.game.Cycle(this.gBuffer);
        }
        graphics.drawImage(this.buffer, 0, 0, this);
    }
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void run() {
        URL url = null;
        try {
            url = new URL(this.getCodeBase(), "lvl.txt");
        }
        catch (MalformedURLException ex) {
            System.out.println("Malformed URL ");
            this.stop();
        }
        this.game = new GameEngine(url, this, this.getGraphics());
        final int n = 16;
        while (true) {
            final int n2 = (int)System.currentTimeMillis();
            this.update(this.getGraphics());
            int n3 = (int)System.currentTimeMillis() - n2;
            if (n3 > n - 5) {
                n3 = n - 5;
            }
            try {
                final Thread runner = this.runner;
                Thread.sleep(n - n3);
            }
            catch (Exception ex2) {}
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.game != null) {
            this.game.setMouse(mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        if (this.game != null) {
            this.game.setMouse(mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        if (this.game != null) {
            this.game.setMousem(true);
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (this.game != null) {
            this.game.setMousem(false);
        }
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void keyTyped(final KeyEvent keyEvent) {
    }
    
    public void keyPressed(final KeyEvent blah) {
        this.blah = blah;
        this.game.getBlah(this.blah);
        this.game.setMousem(false);
    }
    
    public void keyReleased(final KeyEvent keyEvent) {
    }
    
    public MouseEvent m() {
        return this.a;
    }
}
