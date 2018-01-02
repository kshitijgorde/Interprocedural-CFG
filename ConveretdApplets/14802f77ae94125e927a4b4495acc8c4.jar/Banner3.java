import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.FontMetrics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Banner3 extends Applet implements Runnable
{
    public int width;
    public int height;
    public int y;
    public int j;
    public int i;
    public int fheight;
    public int xstart;
    public int ystart;
    public int xstop;
    public int ystop;
    public int msgwidth;
    public int[] catfwidth;
    public int[] msgfwidth;
    public int[] msgx;
    public int[] catx;
    boolean slept;
    static FontMetrics metrics;
    static Graphics gc;
    static Graphics g;
    static Image offscreen;
    static Rectangle apprect;
    static String fontname;
    static int fontsize;
    static int fontstyle;
    static Font font;
    static Color msgbgcolor;
    static Color[] catbgcolor;
    static Color tempColor;
    static Color linkColor;
    static Color msgtextcolor;
    static Color[] cattextcolor;
    static int totalmessages;
    static String[] msgtext;
    static String[] cattext;
    static String[] textURL;
    static String valign;
    static String align;
    static String direction;
    static int delay;
    int speed;
    static int catwidth;
    static int pauseDelay;
    static int xdelta;
    static int ydelta;
    private Thread juicer;
    
    public void init() {
        new ParseArgs3(this);
        Banner3.g = this.getGraphics();
        Banner3.tempColor = Banner3.msgtextcolor;
        this.catfwidth = new int[Banner3.totalmessages];
        this.catx = new int[Banner3.totalmessages];
        this.msgfwidth = new int[Banner3.totalmessages];
        this.msgx = new int[Banner3.totalmessages];
        this.msgfwidth = new int[Banner3.totalmessages];
        Banner3.apprect = this.bounds();
        this.width = Banner3.apprect.width;
        this.height = Banner3.apprect.height;
        this.resize(this.width, this.height);
        Banner3.offscreen = this.createImage(this.width, this.height);
        Banner3.font = new Font(Banner3.fontname, Banner3.fontstyle, Banner3.fontsize);
        Banner3.metrics = Banner3.g.getFontMetrics(Banner3.font);
        this.fheight = Banner3.metrics.getMaxAscent() - Banner3.metrics.getMaxDescent() + 2;
        this.msgwidth = this.width - Banner3.catwidth;
        this.i = 0;
        while (this.i < Banner3.totalmessages) {
            this.catfwidth[this.i] = Banner3.metrics.stringWidth(Banner3.cattext[this.i]);
            this.catx[this.i] = (Banner3.catwidth - this.catfwidth[this.i]) / 2;
            this.msgfwidth[this.i] = Banner3.metrics.stringWidth(Banner3.msgtext[this.i]);
            this.msgx[this.i] = (this.msgwidth - this.msgfwidth[this.i]) / 2 + Banner3.catwidth;
            ++this.i;
        }
        this.i = 0;
        try {
            if (Banner3.direction.equalsIgnoreCase("BottomTop")) {
                final int n = this.height + this.fheight;
                this.y = n;
                this.ystart = n;
                this.ystop = -this.fheight;
                final int abs = Math.abs(this.ystop - this.ystart);
                if (abs % Banner3.ydelta != 0) {
                    this.ystop -= Banner3.ydelta - abs % Banner3.ydelta;
                }
                this.xstart = this.msgx[0];
                this.xstop = (Banner3.xdelta = 0);
                Banner3.ydelta = -Banner3.ydelta;
                return;
            }
            if (Banner3.direction.equalsIgnoreCase("TopBottom")) {
                final int n2 = -this.fheight;
                this.y = n2;
                this.ystart = n2;
                this.ystop = this.height + this.fheight;
                final int abs2 = Math.abs(this.ystop - this.ystart);
                if (abs2 % Banner3.ydelta != 0) {
                    this.ystop += Banner3.ydelta - abs2 % Banner3.ydelta;
                }
                this.xstart = this.msgx[0];
                this.xstop = (Banner3.xdelta = 0);
            }
        }
        catch (Exception ex) {
            final int n3 = -this.fheight;
            this.y = n3;
            this.ystart = n3;
            this.ystop = this.height + this.fheight;
            final int abs3 = Math.abs(this.ystop - this.ystart);
            if (abs3 % Banner3.ydelta != 0) {
                this.ystop += Banner3.ydelta - abs3 % Banner3.ydelta;
            }
            this.xstart = this.msgx[0];
            this.xstop = (Banner3.xdelta = 0);
        }
    }
    
    public void start() {
        if (this.juicer == null) {
            (this.juicer = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.juicer != null && this.juicer.isAlive()) {
            this.juicer.stop();
            this.juicer = null;
        }
    }
    
    public void run() {
        while (this.juicer != null) {
            this.update(Banner3.g);
            try {
                Thread.sleep(1L);
            }
            catch (InterruptedException ex) {
                System.out.println("something wrong with juicer");
            }
            if (this.y == (this.height - this.fheight) / 2 + this.fheight - 1 && !this.slept) {
                try {
                    Thread.sleep(Banner3.pauseDelay);
                }
                catch (InterruptedException ex2) {
                    System.out.println("juicer pauseDelay");
                }
                this.slept = true;
            }
            if (this.y != this.ystop) {
                this.y += Banner3.ydelta;
            }
            else {
                this.y = this.ystart;
                if (this.i == Banner3.totalmessages - 1) {
                    this.i = 0;
                    this.slept = false;
                }
                else {
                    ++this.i;
                    this.slept = false;
                }
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        (Banner3.gc = Banner3.offscreen.getGraphics()).setFont(Banner3.font);
        Banner3.gc.setColor(Banner3.catbgcolor[this.i]);
        Banner3.gc.fillRect(2, 2, Banner3.catwidth - 4, this.height - 4);
        Banner3.gc.setColor(Banner3.cattextcolor[this.i]);
        Banner3.gc.drawString(Banner3.cattext[this.i], this.catx[this.i], this.y);
        Banner3.gc.setColor(Banner3.msgbgcolor);
        Banner3.gc.fillRect(Banner3.catwidth - 2, 2, this.width - 4, this.height - 4);
        Banner3.gc.setColor(Banner3.tempColor);
        Banner3.gc.drawString(Banner3.msgtext[this.i], this.msgx[this.i], this.y);
        Banner3.gc.setColor(Color.black);
        Banner3.gc.drawRect(0, 0, this.width - 1, this.height - 1);
        Banner3.gc.setColor(Color.white);
        Banner3.gc.drawRect(1, 1, this.width - 3, this.height - 3);
        graphics.drawImage(Banner3.offscreen, 0, 0, null);
        Banner3.gc.dispose();
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        try {
            this.getAppletContext().showDocument(new URL(Banner3.textURL[this.i]));
        }
        catch (MalformedURLException ex) {
            System.out.println("hey I caught it!!!");
        }
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        Banner3.tempColor = Banner3.linkColor;
        this.update(Banner3.g);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        Banner3.tempColor = Banner3.msgtextcolor;
        this.update(Banner3.g);
        return true;
    }
    
    public Banner3() {
        this.slept = false;
    }
    
    static {
        Banner3.gc = null;
    }
}
