import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Cursor;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.FocusListener;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class burnshow extends Applet implements Runnable
{
    Thread motor;
    int XSIZE;
    int YSIZE;
    int SIZE;
    loadburn bufImg;
    Image mainImg;
    fgClass fg;
    eventClass evt;
    ionlink overlink;
    String showtype;
    final Font MFONT;
    int vCursorCurrent;
    long memb4;
    
    public burnshow() {
        this.showtype = "_blank";
        this.MFONT = new Font("Arial", 1, 11);
        this.vCursorCurrent = 0;
    }
    
    void getTarget() {
        this.showtype = "_blank";
        final String parameter = this.getParameter("target");
        if (parameter != null) {
            this.showtype = parameter;
        }
    }
    
    public void init() {
        this.memb4 = Runtime.getRuntime().freeMemory();
        this.XSIZE = this.size().width;
        this.YSIZE = this.size().height;
        this.SIZE = this.XSIZE * this.YSIZE;
        System.err.println(String.valueOf(this.XSIZE) + " " + this.YSIZE + " " + this.SIZE);
        this.mainImg = this.createImage(this.XSIZE, this.YSIZE);
        final Graphics graphics = this.mainImg.getGraphics();
        this.bufImg = new loadburn(this, this.mainImg, this.XSIZE, this.YSIZE);
        this.overlink = new ionlink(this, graphics, this.XSIZE, this.YSIZE);
        this.fg = new fgClass(this, this.XSIZE, this.YSIZE);
        this.bufImg.setFgExist(this.fg.isMoving());
        graphics.setColor(this.bufImg.bgcolor);
        graphics.fillRect(0, 0, this.XSIZE, this.YSIZE);
        graphics.dispose();
        System.gc();
        final long freeMemory = Runtime.getRuntime().freeMemory();
        System.err.println("\nMemory Before:" + this.memb4);
        System.err.println("Memory After:" + freeMemory);
        System.err.println("Memory Used:" + (this.memb4 - freeMemory) + "\n");
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.mainImg, 0, 0, null);
    }
    
    public void run() {
        this.addFocusListener(this.evt = new eventClass());
        this.addMouseListener(this.evt);
        this.addMouseMotionListener(this.evt);
        System.err.println("PushShow Applet (C) 2001 I-Yuan Chen [6sense.com]");
        while (true) {
            final Graphics graphics = this.mainImg.getGraphics();
            this.bufImg.draw(graphics);
            this.fg.draw(graphics);
            final boolean over = this.overlink.isOver(this.evt.xpos, this.evt.ypos);
            if (this.evt.mou_enter) {
                this.overlink.draw(graphics);
            }
            if (over) {
                this.setCursorNow(12);
                if (this.evt.mou_down) {
                    this.showLink(this.overlink.getLink());
                    this.evt.mou_down = false;
                }
            }
            else {
                this.setCursorNow(0);
            }
            graphics.dispose();
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
    
    public void setCursorNow(final int vCursorCurrent) {
        if (this.vCursorCurrent != vCursorCurrent) {
            this.vCursorCurrent = vCursorCurrent;
            this.setCursor(Cursor.getPredefinedCursor(this.vCursorCurrent));
        }
    }
    
    public void showLink(final String s) {
        URL url;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException ex) {
            return;
        }
        this.getAppletContext().showDocument(url, this.showtype);
    }
    
    public void start() {
        (this.motor = new Thread(this)).setPriority(1);
        this.motor.start();
    }
    
    public void stop() {
        this.motor.stop();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
