import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Cursor;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.event.FocusListener;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.Color;
import java.awt.Image;
import java.util.Random;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class textscroll extends Applet implements Runnable
{
    Thread motor;
    private int XSIZE;
    private int YSIZE;
    private int SIZE;
    static Random rd;
    private Image mainImg;
    eventClass evt;
    fgClass fg;
    wordset words;
    ionlink overlink;
    Color bgcolor;
    String showtype;
    final Font MFONT;
    long memFree;
    int vCursorCurrent;
    
    public textscroll() {
        this.showtype = "_blank";
        this.MFONT = new Font("Arial", 1, 11);
        this.vCursorCurrent = 0;
    }
    
    int getInt(final String s, final int len) {
        int i = 0;
        try {
            i = Integer.parseInt(s, len);
        }
        catch (NumberFormatException ex) {
            i = 0;
        }
        return i;
    }
    
    void getParameters() {
        this.bgcolor = Color.black;
        final String tempstr = this.getParameter("bgcolor");
        if (tempstr != "") {
            this.bgcolor = new Color(this.getInt(tempstr, 16));
        }
    }
    
    void getTarget() {
        this.showtype = "_blank";
        final String ts1 = this.getParameter("target");
        if (ts1 != null) {
            this.showtype = ts1;
        }
    }
    
    public void init() {
        this.memFree = Runtime.getRuntime().freeMemory();
        this.setBackground(Color.black);
        this.XSIZE = this.size().width;
        this.YSIZE = this.size().height;
        this.SIZE = this.XSIZE * this.YSIZE;
        textscroll.rd = new Random();
        this.mainImg = this.createImage(this.XSIZE, this.YSIZE);
        System.err.println("Host: [" + this.getDocumentBase().getHost() + "]");
        final Graphics g = this.mainImg.getGraphics();
        this.overlink = new ionlink(this, g, this.XSIZE, this.YSIZE);
        this.words = new wordset(this, g, this.XSIZE, this.YSIZE);
        g.dispose();
        this.fg = new fgClass(this, this.XSIZE, this.YSIZE);
        this.getTarget();
        this.getParameters();
        System.gc();
        System.err.println("Memory Used: " + (this.memFree - Runtime.getRuntime().freeMemory()));
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.mainImg, 0, 0, null);
    }
    
    public void run() {
        this.addFocusListener(this.evt = new eventClass());
        this.addMouseListener(this.evt);
        this.addMouseMotionListener(this.evt);
        System.err.println("TextScroll Applet (C) 2002 I-Yuan Chen [6sense.com]");
        while (true) {
            final Graphics mainGrp = this.mainImg.getGraphics();
            mainGrp.setColor(this.bgcolor);
            mainGrp.fillRect(0, 0, this.XSIZE, this.YSIZE);
            this.fg.drawOne(mainGrp);
            this.words.draw(mainGrp);
            this.fg.drawTwo(mainGrp);
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
            else if (this.words.isOver(this.evt.xpos, this.evt.ypos)) {
                this.setCursorNow(12);
                if (this.evt.mou_down) {
                    this.showLink(this.words.getLink());
                    this.evt.mou_down = false;
                }
            }
            else {
                this.setCursorNow(0);
                this.words.move(this.evt.mou_down, this.evt.ypos - this.evt.ydown);
                this.evt.ydown = this.evt.ypos;
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
