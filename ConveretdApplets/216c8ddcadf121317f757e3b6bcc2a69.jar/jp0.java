import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Cursor;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class jp0 extends Applet implements Runnable, MouseListener, MouseMotionListener
{
    jp1 j1;
    Graphics backGC;
    Image backBuffer;
    Thread movieThread;
    int mX;
    int mY;
    int mE;
    int lctr1;
    
    public jp0() {
        this.movieThread = null;
    }
    
    public void init() {
        this.setBackground(Color.white);
    }
    
    public void destroy() {
        this.removeMouseListener(this);
        this.removeMouseMotionListener(this);
        this.movieThread = null;
    }
    
    public void run() {
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.backBuffer = this.createImage(this.getSize().width, this.getSize().height);
        this.backGC = this.backBuffer.getGraphics();
        final int n = 5;
        final Thread currentThread = Thread.currentThread();
        currentThread.setPriority(1);
        (this.j1 = new jp1()).movieInit(this.backGC, this, n);
        this.j1.loadFile();
        if (this.j1.mRC > 0) {
            return;
        }
        this.j1.loadImages();
        if (this.j1.mRC > 0) {
            return;
        }
        while (this.movieThread == currentThread) {
            if (this.mE > 0) {
                this.lctr1 = this.mE;
                this.mE = 0;
                this.j1.mouseManager(this.mX, this.mY, this.lctr1);
                if (this.j1.mRC > 0) {
                    if (this.j1.mRC == 9999) {
                        this.showStatus("End");
                    }
                    this.setCursor(Cursor.getPredefinedCursor(0));
                    return;
                }
            }
            this.j1.moviePlay();
            if (this.j1.mRC > 0) {
                if (this.j1.mRC == 9999) {
                    this.showStatus("End");
                }
                this.setCursor(Cursor.getPredefinedCursor(0));
                return;
            }
            try {
                Thread.sleep(n);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void start() {
        if (this.movieThread == null) {
            (this.movieThread = new Thread(this)).setName("jp1 thread");
            this.movieThread.start();
        }
    }
    
    public void stop() {
        this.movieThread = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.j1 != null) {
            graphics.drawImage(this.backBuffer, 0, 0, this);
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        this.mE = 3;
        this.mX = mouseEvent.getX();
        this.mY = mouseEvent.getY();
        mouseEvent.consume();
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        if (mouseEvent.getModifiers() == 4) {
            this.mE = 4;
        }
        else {
            this.mE = 2;
        }
        this.mX = mouseEvent.getX();
        this.mY = mouseEvent.getY();
        mouseEvent.consume();
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
        this.mE = 1;
        this.mX = 0;
        this.mY = 0;
        mouseEvent.consume();
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
        if (this.mE == 0) {
            this.mE = 1;
            this.mX = mouseEvent.getX();
            this.mY = mouseEvent.getY();
            mouseEvent.consume();
        }
    }
    
    public String getAppletInfo() {
        return "(c) Copyright 2002 by VisualCowboys";
    }
}
