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

public class fadeshow extends Applet implements Runnable
{
    Thread motor;
    int XSIZE;
    int YSIZE;
    int SIZE;
    loadfade bufImg;
    Image mainImg;
    fgClass fg;
    eventClass evt;
    ionlink overlink;
    String showtype;
    final Font MFONT;
    int vCursorCurrent;
    long memb4;
    
    public fadeshow() {
        this.showtype = "_blank";
        this.MFONT = new Font("Arial", 1, 11);
        this.vCursorCurrent = 0;
    }
    
    void getTarget() {
        this.showtype = "_blank";
        final String ts1 = this.getParameter("target");
        if (ts1 != null) {
            this.showtype = ts1;
        }
    }
    
    public void init() {
        this.memb4 = Runtime.getRuntime().freeMemory();
        this.XSIZE = this.size().width;
        this.YSIZE = this.size().height;
        this.SIZE = this.XSIZE * this.YSIZE;
        System.err.println(String.valueOf(this.XSIZE) + " " + this.YSIZE + " " + this.SIZE);
        this.mainImg = this.createImage(this.XSIZE, this.YSIZE);
        final Graphics mainGrp = this.mainImg.getGraphics();
        this.bufImg = new loadfade(this, this.mainImg, this.XSIZE, this.YSIZE);
        this.overlink = new ionlink(this, mainGrp, this.XSIZE, this.YSIZE);
        this.fg = new fgClass(this, this.XSIZE, this.YSIZE);
        this.bufImg.setFgExist(this.fg.isMoving());
        mainGrp.setColor(this.bufImg.bgcolor);
        mainGrp.fillRect(0, 0, this.XSIZE, this.YSIZE);
        mainGrp.dispose();
        System.gc();
        final long memafter = Runtime.getRuntime().freeMemory();
        System.err.println("\nMemory Before:" + this.memb4);
        System.err.println("Memory After:" + memafter);
        System.err.println("Memory Used:" + (this.memb4 - memafter) + "\n");
    }
    
    public void paint(final Graphics g) {
        if (!this.bufImg.isGrabbing) {
            g.drawImage(this.mainImg, 0, 0, null);
        }
    }
    
    public void run() {
        this.addFocusListener(this.evt = new eventClass());
        this.addMouseListener(this.evt);
        this.addMouseMotionListener(this.evt);
        System.err.println("BurnShow Applet (C) 2001 I-Yuan Chen [6sense.com]");
        while (true) {
            final Graphics mainGrp = this.mainImg.getGraphics();
            this.bufImg.draw(mainGrp);
            this.fg.draw(mainGrp);
            final boolean isOver = this.overlink.isOver(this.evt.xpos, this.evt.ypos);
            if (this.evt.mou_enter) {
                this.overlink.draw(mainGrp);
            }
            if (isOver) {
                this.setCursorNow(12);
                if (this.evt.mou_down) {
                    this.showLink(this.overlink.getLink());
                    this.evt.mou_down = false;
                }
            }
            else {
                this.setCursorNow(0);
            }
            mainGrp.dispose();
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
    
    public void setCursorNow(final int cur) {
        if (this.vCursorCurrent != cur) {
            this.vCursorCurrent = cur;
            this.setCursor(Cursor.getPredefinedCursor(this.vCursorCurrent));
        }
    }
    
    public void showLink(final String link) {
        URL u;
        try {
            u = new URL(link);
        }
        catch (MalformedURLException ex) {
            return;
        }
        this.getAppletContext().showDocument(u, this.showtype);
    }
    
    public void start() {
        (this.motor = new Thread(this)).setPriority(1);
        this.motor.start();
    }
    
    public void stop() {
        this.motor.stop();
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
