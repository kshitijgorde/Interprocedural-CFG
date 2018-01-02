import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.TextArea;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class fxText extends Applet implements Runnable
{
    int width;
    int height;
    String[] szImages;
    Image[] images;
    Image virtualImage;
    Graphics virtualGC;
    Image offscrImage;
    Graphics offscrImageGC;
    Thread kicker;
    imageLoader il;
    int X;
    int Y;
    boolean allOK;
    URL link;
    
    public void init() {
        this.width = this.size().width;
        this.height = this.size().height;
        this.virtualImage = this.createImage(this.width * 2, this.height * 2);
        this.virtualGC = this.virtualImage.getGraphics();
        this.offscrImage = this.createImage(this.width, this.height);
        this.offscrImageGC = this.offscrImage.getGraphics();
        System.out.println("fxText Applet Copyright(C)1996 by Bill Giel");
        this.szImages[0] = this.getParameter("BACKGROUND");
        if (this.szImages[0] == null) {
            System.out.println("fxText Error: No background parameter.");
            this.allOK = false;
        }
        this.szImages[1] = this.getParameter("FOREGROUND");
        if (this.szImages[1] == null) {
            System.out.println("fxText Error: No foreground parameter.");
            this.allOK = false;
        }
        final String param = this.getParameter("HYPERLINK");
        if (param != null) {
            try {
                this.link = new URL(param);
            }
            catch (MalformedURLException ex) {
                System.out.println("fxText Error: Malformed URL");
                this.link = null;
            }
        }
        if (this.allOK) {
            (this.il = new imageLoader(this, this.szImages, 2, null)).start();
        }
        final boolean b = false;
        this.Y = (b ? 1 : 0);
        this.X = (b ? 1 : 0);
        this.images = null;
    }
    
    public void start() {
        if (this.kicker == null) {
            (this.kicker = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.kicker != null) {
            this.kicker.stop();
            this.kicker = null;
        }
    }
    
    public boolean handleEvent(final Event e) {
        if (e.id == 501 && this.link != null) {
            this.getAppletContext().showDocument(this.link);
            return true;
        }
        return super.handleEvent(e);
    }
    
    public void run() {
        if (this.allOK && this.il != null) {
            Thread.currentThread().setPriority(1);
            while ((this.images = this.il.retrieveImages()) == null) {}
            final int tileWidth = this.images[0].getWidth(this);
            final int tileHeight = this.images[0].getHeight(this);
            final int xTiles = this.width / tileWidth;
            final int yTiles = this.height / tileHeight;
            for (int y = 0; y < this.virtualImage.getHeight(this); y += tileHeight) {
                for (int x = 0; x < this.virtualImage.getWidth(this); x += tileWidth) {
                    this.virtualGC.drawImage(this.images[0], x, y, this);
                }
            }
            while (this.kicker != null) {
                try {
                    Thread.sleep(50L);
                }
                catch (InterruptedException ex) {}
                final int x2 = this.X - 1;
                this.X = x2;
                if (-x2 > ((xTiles == 0) ? this.width : (xTiles * tileWidth))) {
                    this.X = 0;
                }
                final int y2 = this.Y - 1;
                this.Y = y2;
                if (-y2 > ((yTiles == 0) ? this.height : (yTiles * tileHeight))) {
                    this.Y = 0;
                }
                this.repaint();
            }
            return;
        }
        this.repaint();
    }
    
    public void paint(final Graphics g) {
        if (this.images != null) {
            this.update(g);
            return;
        }
        if (this.allOK) {
            g.drawString("Loading Images...", 5, this.height / 2);
            return;
        }
        g.drawString("Error - No images!", 5, this.height / 2);
    }
    
    public void update(final Graphics g) {
        if (this.images != null) {
            this.offscrImageGC.drawImage(this.virtualImage, this.X, this.Y, this);
            this.offscrImageGC.drawImage(this.images[1], 0, 0, this);
            g.drawImage(this.offscrImage, 0, 0, this);
        }
    }
    
    public fxText() {
        this.szImages = new String[2];
        this.images = new Image[2];
        this.allOK = true;
    }
}
