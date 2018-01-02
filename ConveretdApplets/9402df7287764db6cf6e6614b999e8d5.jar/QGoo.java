import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.image.PixelGrabber;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Label;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class QGoo extends Applet implements Runnable
{
    public static GooCanvas canvas;
    public static GooControls gcontrols;
    ThreadGroup appletThreadGroup;
    Thread runner;
    String imageurl;
    public static Color bg;
    Label l;
    int w;
    int h;
    Image gooimg;
    Image goodimg;
    MediaTracker tracker;
    public int[] origpix;
    public int[] modpix;
    public int[] bakpix;
    float[] transmapx;
    float[] transmapy;
    PixelGrabber pg;
    
    public void destroy() {
        this.remove(QGoo.canvas);
        this.remove(QGoo.gcontrols);
    }
    
    public String getAppletInfo() {
        return "QGoo - An Image Goo-ing Applet. Written by QuanCex (wittens@pandora.be)";
    }
    
    public String[][] getParameterInfo() {
        return new String[][] { { "image", "URL", "Any valid URL to a JPG" } };
    }
    
    public void init() {
        final Graphics graphics = this.getGraphics();
        this.imageurl = this.getParameter("image");
        try {
            QGoo.bg = Color.decode(this.getParameter("bgcolor"));
        }
        catch (NumberFormatException ex) {
            QGoo.bg = new Color(192, 192, 192);
        }
        graphics.setColor(QGoo.bg);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        this.tracker = new MediaTracker(this);
        this.gooimg = this.getImage(this.getCodeBase(), this.imageurl);
        this.tracker.addImage(this.gooimg, 1000);
        try {
            this.tracker.waitForID(1000);
        }
        catch (InterruptedException ex2) {
            System.out.println("Error waiting for goo-image to load");
        }
        do {
            this.w = this.gooimg.getWidth(this);
            this.h = this.gooimg.getHeight(this);
            if (this.w == -1) {
                this.showStatus("Image not found");
            }
        } while (this.w == -1);
        this.showStatus("Initializing Goo-Engine");
        this.resize(this.w, this.h + 70);
        this.transmapx = new float[this.w * this.h];
        this.transmapy = new float[this.w * this.h];
        this.origpix = new int[this.w * this.h];
        this.modpix = new int[this.w * this.h];
        this.bakpix = new int[this.w * this.h];
        this.pg = new PixelGrabber(this.gooimg, 0, 0, this.w, this.h, this.origpix, 0, this.w);
        try {
            this.pg.grabPixels();
        }
        catch (InterruptedException ex3) {
            this.showStatus("Error Grabbing Pixels!");
        }
        System.arraycopy(this.origpix, 0, this.bakpix, 0, this.w * this.h);
        (QGoo.canvas = new GooCanvas(this)).setsize(this.w, this.h);
        QGoo.canvas.reset();
        this.goodimg = this.createImage(this.w, this.h);
        QGoo.canvas.setImage(this.goodimg);
        this.goodimg.getGraphics().drawImage(this.gooimg, 0, 0, this.w, this.h, this);
        this.add("North", QGoo.canvas);
        this.add("Center", QGoo.gcontrols = new GooControls(this));
        this.add(this.l = new Label("QGoo v1.2 - by QuanCex (wittens@pandora.be)"));
        this.l.setBackground(QGoo.bg);
        this.showStatus("");
        this.appletThreadGroup = Thread.currentThread().getThreadGroup();
    }
    
    public static void main(final String[] array) {
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(QGoo.bg);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
    }
    
    public void run() {
    }
    
    public void setImage(final String s) {
        this.gooimg = this.getImage(this.getCodeBase(), s);
        this.tracker.addImage(this.gooimg, 1000);
        this.showStatus("Loading Image...");
        try {
            this.tracker.waitForID(1000);
        }
        catch (InterruptedException ex) {
            System.out.println("Error waiting for goo-image to load");
        }
        do {
            this.w = this.gooimg.getWidth(this);
            this.h = this.gooimg.getHeight(this);
            if (this.w == -1) {
                this.showStatus("Image not found");
            }
        } while (this.w == -1);
        this.showStatus("Loaded new image");
        this.transmapx = new float[this.w * this.h];
        this.transmapy = new float[this.w * this.h];
        this.origpix = new int[this.w * this.h];
        this.modpix = new int[this.w * this.h];
        this.bakpix = new int[this.w * this.h];
        this.pg = new PixelGrabber(this.gooimg, 0, 0, this.w, this.h, this.origpix, 0, this.w);
        try {
            this.pg.grabPixels();
        }
        catch (InterruptedException ex2) {
            this.showStatus("Error Grabbing Pixels!");
        }
        System.arraycopy(this.origpix, 0, this.bakpix, 0, this.w * this.h);
        QGoo.canvas.setsize(this.w, this.h);
        QGoo.canvas.reset();
        this.goodimg = this.createImage(this.w, this.h);
        this.goodimg.getGraphics().drawImage(this.gooimg, 0, 0, this.w, this.h, this);
        QGoo.canvas.setImage(this.goodimg);
    }
    
    public void start() {
        (this.runner = new Thread(this)).start();
    }
    
    public void stop() {
        this.runner = null;
    }
}
