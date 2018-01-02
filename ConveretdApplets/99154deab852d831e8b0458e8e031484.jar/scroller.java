import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.PixelGrabber;
import java.awt.Font;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class scroller extends Applet implements Runnable
{
    Thread thread;
    int w;
    int h;
    Image offimage;
    Graphics offg;
    String teksti;
    FontMetrics fm;
    int leveys;
    int[] intTeksti;
    Image talo;
    double rx;
    double rxold;
    double ry;
    double ryold;
    int xkerroin;
    int ykerroin;
    double xinc;
    double yinc;
    Color vari;
    int c1;
    int c2;
    double s;
    
    public void initFontit() {
        final Font font = new Font("Arial", 0, 14);
        final Graphics graphics = this.createImage(20, 20).getGraphics();
        graphics.setFont(font);
        this.fm = graphics.getFontMetrics();
        this.leveys = this.fm.stringWidth(this.teksti);
        this.intTeksti = new int[20 * this.leveys];
        final Image image = this.createImage(this.leveys, 20);
        final Graphics graphics2 = image.getGraphics();
        graphics2.setFont(font);
        this.fm = graphics2.getFontMetrics();
        graphics2.setColor(Color.black);
        graphics2.fillRect(0, 0, this.leveys, 20);
        graphics2.setColor(Color.blue);
        graphics2.drawString(this.teksti, 0, 14);
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.leveys, 20, this.intTeksti, 0, this.leveys);
        boolean grabPixels = false;
        do {
            try {
                grabPixels = pixelGrabber.grabPixels(500L);
            }
            catch (InterruptedException ex) {}
        } while (!grabPixels);
    }
    
    public void scroll() {
        this.c2 = this.c1;
        this.s = 3.141592653589793;
        for (int i = 0; i < 125; ++i) {
            this.s += 0.05;
            if (this.c2 >= this.leveys) {
                this.c2 = 0;
            }
            for (int j = 0; j < 20; ++j) {
                if (this.intTeksti[this.c2 + j * this.leveys] != -16777216) {
                    this.vari = new Color(this.intTeksti[this.c2 + j * this.leveys]);
                    this.offg.setColor(this.vari);
                    this.offg.drawOval((int)(this.w / 2 + Math.sin(this.s) * (j + 20) * 4.0), (int)(this.h / 2 + Math.cos(this.s) * (j + 20) * 4.0), 6, 6);
                }
            }
            ++this.c2;
        }
        ++this.c1;
        if (this.c1 >= this.leveys) {
            this.c1 = 0;
        }
    }
    
    public void init() {
        this.w = this.getSize().width;
        this.h = this.getSize().height;
        this.offimage = this.createImage(this.w, this.h);
        this.offg = this.offimage.getGraphics();
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(this.talo = this.getImage(this.getCodeBase(), "bg.gif"), 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        if (this.getParameter("TEKSTI") != null) {
            this.teksti = this.getParameter("TEKSTI");
        }
        this.initFontit();
    }
    
    public void update(final Graphics graphics) {
        this.scroll();
        graphics.drawImage(this.offimage, 0, 0, null);
        this.offg.setColor(Color.white);
        this.offg.fillRect(0, 0, this.w, this.h);
        this.offg.drawImage(this.talo, 0, 0, null);
    }
    
    public void start() {
        if (this.thread == null) {
            (this.thread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.thread != null) {
            this.thread.stop();
            this.thread = null;
        }
    }
    
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawString("hep", 50, 50);
    }
    
    public String getAppletInfo() {
        return "seppo.tiainen@welho.com";
    }
    
    public scroller() {
        this.Block$();
    }
    
    private void Block$() {
        this.thread = null;
        this.teksti = "                          SHIT HAPPENS";
        this.rx = 0.0;
        this.rxold = 0.0;
        this.ry = 0.0;
        this.ryold = 0.0;
        this.xkerroin = 5;
        this.ykerroin = 5;
        this.xinc = 0.02;
        this.yinc = 0.03;
        this.c1 = 0;
        this.c2 = 0;
        this.s = 0.0;
    }
}
