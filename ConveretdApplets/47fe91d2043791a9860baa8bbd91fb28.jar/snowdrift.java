import java.awt.Cursor;
import java.awt.Color;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Graphics;
import java.awt.image.MemoryImageSource;
import java.awt.Font;
import java.awt.Image;
import java.util.Random;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class snowdrift extends Applet implements Runnable
{
    Thread motor;
    private int[] starfield;
    private int XSIZE;
    private int YSIZE;
    private int SIZE;
    static Random rd;
    private Image art;
    private String showtype;
    snow[] thesnow;
    int maxsnow;
    int wind;
    fgClass fg;
    linkme toplink;
    wordset words;
    ion VERSION;
    final Font MFONT;
    MemoryImageSource ArtSource;
    Image SourceImg;
    long memFree;
    long memTotal;
    boolean mouseover;
    boolean mouseup;
    boolean mousedn;
    boolean mousedrag;
    int mousex;
    int mousey;
    int mouseupx;
    int mouseupy;
    int mousednx;
    int mousedny;
    int vCursorCurrent;
    
    public snowdrift() {
        this.MFONT = new Font("Arial", 1, 11);
        this.vCursorCurrent = 0;
    }
    
    void begin() {
        System.err.println("Host: [" + this.getDocumentBase().getHost() + "]");
        this.VERSION = new ion();
        final Graphics graphics = this.art.getGraphics();
        System.err.println("Memory: " + Runtime.getRuntime().freeMemory() + "/" + this.memTotal);
        this.toplink = new linkme(graphics, this.XSIZE, "by 6sense.com", "http://www.6sense.com/applets/", this.getParameter("maintext"), this.getParameter("mainlink"), this.VERSION.compare(this.getDocumentBase().getHost(), this.getParameter("id_key")));
        (this.words = new wordset(this, graphics, this.XSIZE, this.YSIZE)).shadowOn(1);
        graphics.dispose();
        final bgcopy bgcopy = new bgcopy(this, this.art, this.XSIZE, this.YSIZE, this.starfield);
        this.fg = new fgClass(this, this.XSIZE, this.YSIZE);
        this.getTarget();
        System.err.println("Memory: " + Runtime.getRuntime().freeMemory() + "/" + this.memTotal);
        System.err.println("Memory Used: " + (this.memFree - Runtime.getRuntime().freeMemory()) + "\n");
    }
    
    void checkOver() {
        if (this.toplink.isOver(this.mousex, this.mousey)) {
            this.setCursorNow(12);
            this.showStatus("Link: " + this.toplink.getLink());
        }
        else if (this.words.isOver(this.mousex, this.mousey)) {
            this.setCursorNow(12);
            this.showStatus("Link: " + this.words.getLink());
        }
        else {
            this.setCursorNow(0);
            this.words.move(this.mousedn, this.mousey - this.mousedny);
            this.mousedny = this.mousey;
        }
    }
    
    void checkPressed() {
        if (this.mouseup) {
            if (this.toplink.isOver(this.mouseupx, this.mouseupy)) {
                URL url;
                try {
                    url = new URL(this.toplink.getLink());
                }
                catch (MalformedURLException ex) {
                    return;
                }
                this.getAppletContext().showDocument(url, this.showtype);
            }
            if (this.words.isOver(this.mouseupx, this.mouseupy)) {
                URL url2;
                try {
                    url2 = new URL(this.words.getLink());
                }
                catch (MalformedURLException ex2) {
                    return;
                }
                this.getAppletContext().showDocument(url2, this.showtype);
            }
            this.mouseup = false;
        }
    }
    
    public void draw() {
        final Graphics graphics = this.art.getGraphics();
        for (int i = 0; i < this.maxsnow; ++i) {
            this.thesnow[i].draw(this.starfield);
        }
        this.ArtSource.newPixels(0, 0, this.XSIZE, this.YSIZE);
        graphics.drawImage(this.SourceImg, 0, 0, null);
        for (int j = this.maxsnow - 1; j >= 0; --j) {
            this.thesnow[j].erase(this.starfield, Math.abs(snowdrift.rd.nextInt()) % this.wind);
        }
        this.fg.drawOne(graphics);
        this.words.draw(graphics);
        this.fg.drawTwo(graphics);
        this.toplink.draw(graphics);
        graphics.dispose();
    }
    
    void draw_init() {
        (this.ArtSource = new MemoryImageSource(this.XSIZE, this.YSIZE, this.starfield, 0, this.XSIZE)).setAnimated(true);
        this.SourceImg = this.createImage(this.ArtSource);
    }
    
    void getTarget() {
        this.showtype = "_blank";
        final String parameter = this.getParameter("target");
        if (parameter != null) {
            this.showtype = parameter;
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this) {
            switch (event.id) {
                case 504: {
                    this.toplink.linkowner = true;
                    break;
                }
                case 505: {
                    this.toplink.linkowner = false;
                    break;
                }
                case 503: {
                    this.mousex = event.x;
                    this.mousey = event.y;
                    this.mouseover = true;
                    break;
                }
                case 502: {
                    this.mouseupx = event.x;
                    this.mouseupy = event.y;
                    this.mouseup = true;
                    this.mousedn = false;
                    break;
                }
                case 506: {
                    this.mousex = event.x;
                    this.mousey = event.y;
                    this.mousedrag = true;
                    break;
                }
                case 501: {
                    this.mousednx = event.x;
                    this.mousedny = event.y;
                    this.mousedn = true;
                    break;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public void init() {
        this.memFree = Runtime.getRuntime().freeMemory();
        this.memTotal = Runtime.getRuntime().totalMemory();
        this.setBackground(Color.black);
        this.XSIZE = this.size().width;
        this.YSIZE = this.size().height;
        this.SIZE = this.XSIZE * this.YSIZE;
        System.err.println(String.valueOf(this.XSIZE) + " " + this.YSIZE + " " + this.SIZE);
        this.starfield = new int[this.SIZE];
        this.art = this.createImage(this.XSIZE, this.YSIZE);
        snowdrift.rd = new Random();
        this.starfield_begin();
        this.draw_init();
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.art, 0, 0, null);
    }
    
    public void run() {
        int n = 0;
        System.err.println("SnowDrift Applet (C) 2000 I-Yuan Chen [6sense.com]");
        this.begin();
        while (true) {
            this.checkPressed();
            this.checkOver();
            this.draw();
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex) {}
            this.repaint();
            if (++n > 100) {
                n = 0;
                System.gc();
            }
        }
    }
    
    public void setCursorNow(final int vCursorCurrent) {
        if (this.vCursorCurrent != vCursorCurrent) {
            this.vCursorCurrent = vCursorCurrent;
            this.setCursor(Cursor.getPredefinedCursor(this.vCursorCurrent));
        }
    }
    
    void setWind(final int wind) {
        if (wind > 0 && wind <= 10) {
            this.wind = wind;
        }
    }
    
    void starfield_begin() {
        this.wind = 5;
        final String parameter = this.getParameter("wind");
        if (parameter != null) {
            this.wind = Integer.parseInt(parameter, 10);
        }
        if (this.wind < 1) {
            this.wind = 1;
        }
        else if (this.wind > 10) {
            this.wind = 10;
        }
        final String parameter2 = this.getParameter("density");
        if (parameter2 != null) {
            if (parameter2.toLowerCase().equals("low")) {
                this.maxsnow = this.SIZE / 600;
            }
            else if (parameter2.toLowerCase().equals("high")) {
                this.maxsnow = this.SIZE / 250;
            }
            else {
                this.maxsnow = this.SIZE / 400;
            }
        }
        else {
            this.maxsnow = this.SIZE / 400;
        }
        this.thesnow = new snow[this.maxsnow];
        for (int i = 0; i < this.maxsnow; ++i) {
            this.thesnow[i] = new snow(this.XSIZE, this.YSIZE);
        }
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
