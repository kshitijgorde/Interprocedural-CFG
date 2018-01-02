import java.awt.image.ImageProducer;
import java.awt.image.ImageObserver;
import java.awt.Frame;
import java.awt.Event;
import java.awt.Component;
import java.net.URL;
import java.awt.Color;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class colorfader extends Applet implements Runnable
{
    Thread runner;
    Image offimg;
    Image image;
    Image img1;
    MediaTracker tracker;
    Graphics offScreeng;
    PixelGrabber pg;
    int[] birboyut;
    int[] isle;
    int imgw;
    int imgh;
    int i;
    int genislik;
    int yukseklik;
    int bgci;
    int durum;
    int X;
    int Y;
    int delay_period;
    MemoryImageSource MIS;
    float ft;
    Color bgc;
    String image_align;
    String image_valign;
    String link1;
    String infostr;
    String status_bar;
    String target_frame;
    String regcode;
    String errstr;
    boolean inf;
    boolean rg;
    URL u;
    Color bufc;
    float[][] hsb;
    
    public void init() {
        try {
            this.bgci = Integer.parseInt(this.getParameter("bgcolor"), 16);
        }
        catch (Exception ex) {
            this.bgci = 0;
        }
        this.setBackground(this.bgc = new Color(this.bgci));
        this.show();
        try {
            this.delay_period = Integer.parseInt(this.getParameter("delay_period"), 10);
        }
        catch (Exception ex2) {
            this.delay_period = 120;
        }
        if (this.delay_period < 15) {
            this.delay_period = 15;
        }
        this.image_align = this.getParameter("image_align");
        this.image_valign = this.getParameter("image_valign");
        this.genislik = this.size().width;
        this.yukseklik = this.size().height;
        this.link1 = this.getParameter("link");
        this.regcode = this.getParameter("regcode");
        if (this.regcode != null) {
            if (this.regcode.equals("cfader3464")) {
                this.rg = true;
            }
            else {
                this.rg = false;
            }
        }
        else {
            this.rg = false;
        }
        this.infostr = this.getParameter("info");
        this.status_bar = this.getParameter("status_bar");
        this.target_frame = this.getParameter("target_frame");
        this.offimg = this.createImage(this.genislik, this.yukseklik);
        this.offScreeng = this.offimg.getGraphics();
        this.tracker = new MediaTracker(this);
        this.image = this.getImage(this.getCodeBase(), this.getParameter("image"));
        this.tracker.addImage(this.image, 0);
        this.repaint();
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.rg) {
            if (this.status_bar != null) {
                this.showStatus("" + this.status_bar);
            }
        }
        else {
            this.showStatus("  Unregistered version of colorfader Java applet. URL: japplets.tripod.com");
        }
        this.repaint();
        if (this.link1 == null) {
            return true;
        }
        if (this.link1.equals("") || this.link1.equals(" ")) {
            return true;
        }
        ((Frame)this.getParent()).setCursor(12);
        return true;
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        this.showStatus("");
        ((Frame)this.getParent()).setCursor(0);
        this.repaint();
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.offScreeng.setColor(this.bgc);
        this.offScreeng.fillRect(0, 0, this.genislik, this.yukseklik);
        if (this.durum == 0) {
            this.offScreeng.setColor(Color.red);
            this.offScreeng.drawString("Loading ...", 10, 20);
            this.offScreeng.setColor(this.bgc);
        }
        if (this.durum == 1) {
            this.offScreeng.setColor(Color.white);
            this.offScreeng.fillRect(0, 0, this.genislik, this.yukseklik);
            this.offScreeng.setColor(Color.black);
            this.offScreeng.drawString("" + this.errstr, 5, 20);
            this.offScreeng.setColor(this.bgc);
        }
        if (this.durum == 2) {
            this.offScreeng.drawImage(this.img1, this.X, this.Y, this);
        }
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offimg, 0, 0, this);
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (this.link1 == null) {
            return true;
        }
        if (this.link1.equals("") || this.link1.equals(" ")) {
            return true;
        }
        try {
            this.u = new URL("" + this.link1);
            this.getAppletContext().showDocument(this.u, this.target_frame);
        }
        catch (Exception ex) {
            return true;
        }
        return true;
    }
    
    public void start() {
        if (this.runner == null || !this.runner.isAlive()) {
            this.runner = new Thread(this);
        }
        this.runner.start();
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public void destroy() {
        this.runner = null;
    }
    
    public void run() {
        try {
            this.tracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        if (this.tracker.isErrorAny()) {
            this.durum = 1;
            this.errstr = "Image Error !";
        }
        else {
            this.durum = 2;
            this.imgw = this.image.getWidth(this);
            this.imgh = this.image.getHeight(this);
            this.X = 0;
            this.Y = 0;
            if (this.image_align != null && this.imgw < this.genislik) {
                if (this.image_align.equals("center")) {
                    this.X = (this.genislik - this.imgw) / 2;
                }
                if (this.image_align.equals("right")) {
                    this.X = this.genislik - this.imgw;
                }
            }
            if (this.image_valign != null && this.imgh < this.yukseklik) {
                if (this.image_valign.equals("center")) {
                    this.Y = (this.yukseklik - this.imgh) / 2;
                }
                if (this.image_valign.equals("bottom")) {
                    this.Y = this.yukseklik - this.imgh;
                }
            }
        }
        this.imgw = this.image.getWidth(this);
        this.imgh = this.image.getHeight(this);
        this.birboyut = new int[this.imgw * this.imgh];
        this.isle = new int[this.imgw * this.imgh];
        this.pg = new PixelGrabber(this.image.getSource(), 0, 0, this.imgw, this.imgh, this.birboyut, 0, this.imgw);
        try {
            this.pg.grabPixels();
        }
        catch (InterruptedException ex2) {}
        this.i = 0;
        while (this.i < this.imgw * this.imgh) {
            this.isle[this.i] = this.birboyut[this.i];
            ++this.i;
        }
        this.MIS = new MemoryImageSource(this.imgw, this.imgh, this.isle, 0, this.imgw);
        this.hsb = new float[this.imgw * this.imgh + 1][3];
        this.img1 = this.createImage(this.MIS);
        this.i = 0;
        while (this.i < this.imgw * this.imgh) {
            this.bufc = new Color(this.birboyut[this.i]);
            Color.RGBtoHSB(this.bufc.getRed(), this.bufc.getGreen(), this.bufc.getBlue(), this.hsb[this.i]);
            ++this.i;
        }
        if (this.infostr != null) {
            if (!this.infostr.equals("Applet by Gokhan Dagli,japplets.tripod.com")) {
                this.durum = 1;
                this.errstr = "info parameter error!";
            }
        }
        else {
            this.durum = 1;
            this.errstr = "info parameter error!";
        }
        while (true) {
            try {
                Thread.sleep(this.delay_period);
            }
            catch (InterruptedException ex3) {}
            this.ft += 0.005f;
            if (this.ft >= 1.0) {
                this.ft = 0.005f;
            }
            this.i = 0;
            while (this.i < this.imgw * this.imgh) {
                this.hsb[this.i][0] = this.ft;
                this.isle[this.i] = Color.HSBtoRGB(this.hsb[this.i][0], this.hsb[this.i][1], this.hsb[this.i][2]);
                ++this.i;
            }
            this.img1 = this.createImage(this.MIS);
            this.repaint();
        }
    }
    
    public colorfader() {
        this.delay_period = 100;
        this.ft = 0.005f;
        this.image_align = "left";
        this.image_valign = "left";
        this.link1 = "";
        this.infostr = "";
        this.status_bar = "";
        this.target_frame = "_self";
        this.regcode = "";
        this.errstr = "";
        this.inf = false;
        this.rg = false;
    }
}
