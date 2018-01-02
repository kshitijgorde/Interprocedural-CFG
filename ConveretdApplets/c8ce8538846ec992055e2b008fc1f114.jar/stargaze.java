import java.awt.Event;
import java.awt.Color;
import java.awt.image.ImageProducer;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.image.MemoryImageSource;
import java.awt.Font;
import java.awt.Image;
import java.util.Random;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class stargaze extends Applet implements Runnable
{
    Thread motor;
    private int[] starfield;
    private int XSIZE;
    private int YSIZE;
    private int SIZE;
    static Random rd;
    private Image art;
    private String showtype;
    star[] thestars;
    int maxstars;
    foreimage[] foreground;
    int minForeImages;
    int maxForeImages;
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
    
    public stargaze() {
        this.MFONT = new Font("Arial", 1, 11);
    }
    
    void begin() {
        System.err.println("Host: [" + this.getDocumentBase().getHost() + "]");
        this.VERSION = new ion();
        final Graphics graphics = this.art.getGraphics();
        this.toplink = new linkme(graphics, this.XSIZE, "by 6sense.com", "http://www.6sense.com/applets/", this.getParameter("maintext"), this.getParameter("mainlink"), this.VERSION.compare(this.getDocumentBase().getHost(), this.getParameter("id_key")));
        this.words = new wordset(this, graphics, this.XSIZE, this.YSIZE);
        graphics.dispose();
        final bgcopy bgcopy = new bgcopy(this, this.art, this.XSIZE, this.YSIZE, this.starfield);
        this.getForeImages();
        this.getTarget();
        System.err.println("Memory: " + Runtime.getRuntime().freeMemory() + "/" + this.memTotal);
        System.err.println("Memory Used: " + (this.memFree - Runtime.getRuntime().freeMemory()) + "\n");
    }
    
    void checkOver() {
        if (this.toplink.isOver(this.mousex, this.mousey)) {
            this.setCursor(Cursor.getPredefinedCursor(12));
            this.showStatus("Link: " + this.toplink.getLink());
        }
        else if (this.words.isOver(this.mousex, this.mousey)) {
            this.setCursor(Cursor.getPredefinedCursor(12));
            this.showStatus("Link: " + this.words.getLink());
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(0));
            if (this.toplink.linkowner) {
                if (!this.toplink.fullversion) {
                    this.showStatus("StarGaze [ 6sense.com ]");
                }
                else {
                    this.showStatus("");
                }
            }
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
        for (int i = 0; i < this.maxstars; ++i) {
            this.thestars[i].drawCopy(this.starfield);
        }
        this.ArtSource.newPixels(0, 0, this.XSIZE, this.YSIZE);
        graphics.drawImage(this.SourceImg, 0, 0, null);
        for (int j = this.maxstars - 1; j >= 0; --j) {
            this.thestars[j].eraseCopy(this.starfield);
        }
        int k = 0;
        while (k < this.minForeImages) {
            this.foreground[k++].draw(graphics);
        }
        this.words.draw(graphics);
        while (k < this.maxForeImages) {
            this.foreground[k++].draw(graphics);
        }
        this.toplink.draw(graphics);
        graphics.dispose();
    }
    
    void draw_init() {
        (this.ArtSource = new MemoryImageSource(this.XSIZE, this.YSIZE, this.starfield, 0, this.XSIZE)).setAnimated(true);
        this.SourceImg = this.createImage(this.ArtSource);
    }
    
    void getForeImages() {
        this.minForeImages = 0;
        this.maxForeImages = 0;
        final String parameter = this.getParameter("fgdivide");
        if (parameter != null) {
            this.minForeImages = Integer.parseInt(parameter, 10);
        }
        final String parameter2 = this.getParameter("fgmax");
        if (parameter2 != null) {
            final int int1 = Integer.parseInt(parameter2, 10);
            if (int1 > 0) {
                this.foreground = new foreimage[int1];
                this.maxForeImages = int1;
            }
            int i = 1;
            final Graphics graphics = this.art.getGraphics();
            graphics.setFont(this.MFONT);
            while (i <= int1) {
                graphics.setColor(Color.white);
                graphics.fillRect(0, 5, this.XSIZE, 20);
                graphics.setColor(Color.gray);
                graphics.drawRect(0, 5, this.XSIZE, 20);
                graphics.setColor(Color.black);
                graphics.drawString("Loading " + int1 + " ForeGround Images: " + i, 10, 20);
                this.repaint();
                this.foreground[i - 1] = new foreimage(this, i);
                ++i;
            }
        }
        if (this.minForeImages > this.maxForeImages) {
            this.minForeImages = this.maxForeImages;
        }
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
        stargaze.rd = new Random();
        this.starfield_begin();
        this.draw_init();
        System.err.println("Memory: " + Runtime.getRuntime().freeMemory() + "/" + this.memTotal);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.art, 0, 0, null);
    }
    
    public void run() {
        System.err.println("StarGaze Applet (C) 2000 I-Yuan Chen [6sense.com]");
        this.begin();
        while (true) {
            this.checkPressed();
            this.checkOver();
            this.draw();
            this.repaint();
            try {
                Thread.sleep(15L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    void starfield_begin() {
        final String parameter = this.getParameter("density");
        if (parameter != null) {
            if (parameter.toLowerCase().equals("low")) {
                this.maxstars = this.SIZE / 600;
            }
            else if (parameter.toLowerCase().equals("high")) {
                this.maxstars = this.SIZE / 250;
            }
            else {
                this.maxstars = this.SIZE / 400;
            }
        }
        else {
            this.maxstars = this.SIZE / 400;
        }
        this.thestars = new star[this.maxstars];
        for (int i = 0; i < this.maxstars; ++i) {
            this.thestars[i] = new star(this.XSIZE, this.YSIZE);
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
