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
        this.MFONT = new Font("Arial", 1, 11);
        this.showtype = "_blank";
        this.vCursorCurrent = 0;
    }
    
    void getTarget() {
        this.showtype = "_blank";
        final String s = this.getParameter("target");
        if (s != null) {
            this.showtype = s;
        }
    }
    
    public void init() {
        this.memb4 = Runtime.getRuntime().freeMemory();
        this.XSIZE = this.size().width;
        this.YSIZE = this.size().height;
        this.SIZE = this.XSIZE * this.YSIZE;
        System.err.println(this.XSIZE + " " + this.YSIZE + " " + this.SIZE);
        this.mainImg = this.createImage(this.XSIZE, this.YSIZE);
        final Graphics g = this.mainImg.getGraphics();
        this.bufImg = new loadburn(this, this.mainImg, this.XSIZE, this.YSIZE);
        this.overlink = new ionlink(this, g, this.XSIZE, this.YSIZE);
        this.fg = new fgClass(this, this.XSIZE, this.YSIZE);
        this.bufImg.setFgExist(this.fg.isMoving());
        g.setColor(this.bufImg.bgcolor);
        g.fillRect(0, 0, this.XSIZE, this.YSIZE);
        g.dispose();
        System.gc();
        final long l = Runtime.getRuntime().freeMemory();
        System.err.println("\nMemory Before:" + this.memb4);
        System.err.println("Memory After:" + l);
        System.err.println("Memory Used:" + (this.memb4 - l) + "\n");
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.mainImg, 0, 0, null);
    }
    
    public void run() {
        this.addFocusListener(this.evt = new eventClass());
        this.addMouseListener(this.evt);
        this.addMouseMotionListener(this.evt);
        System.err.println("Cobayes et Hamsters (c) 2001-2003");
        while (true) {
            final Graphics g = this.mainImg.getGraphics();
            this.bufImg.draw(g);
            this.fg.draw(g);
            final boolean flag = this.overlink.isOver(this.evt.xpos, this.evt.ypos);
            if (this.evt.mou_enter) {
                this.overlink.draw(g);
            }
            if (flag) {
                this.setCursorNow(12);
                if (this.evt.mou_down) {
                    this.showLink(this.overlink.getLink());
                    this.evt.mou_down = false;
                }
            }
            else {
                this.setCursorNow(0);
            }
            g.dispose();
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
    }
    
    public void setCursorNow(final int i) {
        if (this.vCursorCurrent != i) {
            this.vCursorCurrent = i;
            this.setCursor(Cursor.getPredefinedCursor(this.vCursorCurrent));
        }
    }
    
    public void showLink(final String s) {
        URL url;
        try {
            url = new URL(s);
        }
        catch (MalformedURLException _ex) {
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
    
    public void update(final Graphics g) {
        this.paint(g);
    }
}
