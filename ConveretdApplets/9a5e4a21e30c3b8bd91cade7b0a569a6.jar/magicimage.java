import java.awt.Frame;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.net.URL;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Color;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class magicimage extends Applet implements Runnable
{
    Thread runner;
    String image_align;
    String image_valign;
    String bgcolor;
    String target_frame;
    String infostr;
    String link1;
    String scroll_way;
    String status_bar;
    String regcode;
    String errstr;
    Image offimg;
    Image offimg2;
    Image imgust;
    Image imgalt;
    int bgci;
    int genislik;
    int yukseklik;
    int delay_time;
    int durum;
    int X;
    int imgwidth;
    int imgheight;
    int kaplaboy;
    int kaplayuk;
    int don;
    int don2;
    int XX;
    int YY;
    int imgw;
    int imgh;
    Color bgc;
    MediaTracker tracker;
    Graphics offScreeng;
    Graphics offScreeng2;
    URL u;
    boolean inf;
    boolean rg;
    
    public void init() {
        this.durum = 0;
        this.X = 0;
        this.kaplaboy = 0;
        this.kaplayuk = 0;
        this.bgcolor = this.getParameter("bgcolor");
        try {
            this.bgci = Integer.parseInt(this.bgcolor, 16);
        }
        catch (Exception ex) {
            this.bgci = 0;
        }
        this.setBackground(this.bgc = new Color(this.bgci));
        this.show();
        this.image_align = this.getParameter("image_align");
        this.image_valign = this.getParameter("image_valign");
        this.link1 = this.getParameter("link");
        this.regcode = this.getParameter("regcode");
        if (this.regcode != null) {
            if (this.regcode.equals("mimg35856")) {
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
        this.scroll_way = this.getParameter("scroll_way");
        this.target_frame = this.getParameter("target_frame");
        this.genislik = this.size().width;
        this.yukseklik = this.size().height;
        this.offimg = this.createImage(this.genislik, this.yukseklik);
        this.offScreeng = this.offimg.getGraphics();
        try {
            this.delay_time = Integer.parseInt("" + this.getParameter("delay_time"), 10);
        }
        catch (Exception ex2) {
            this.delay_time = 2000;
        }
        this.tracker = new MediaTracker(this);
        this.imgust = this.getImage(this.getCodeBase(), this.getParameter("foreground_image"));
        this.imgalt = this.getImage(this.getCodeBase(), this.getParameter("background_image"));
        this.tracker.addImage(this.imgust, 1);
        this.tracker.addImage(this.imgalt, 2);
    }
    
    public void update(final Graphics graphics) {
        this.offScreeng.setColor(this.bgc);
        this.offScreeng.fillRect(0, 0, this.genislik, this.yukseklik);
        if (this.durum == 0) {
            this.offScreeng.setColor(Color.black);
            this.offScreeng.drawString("Loading...", 10, 20);
            this.offScreeng.setColor(this.bgc);
        }
        if (this.durum == 1) {
            this.offScreeng.setColor(Color.black);
            this.offScreeng.drawString("" + this.errstr, 10, 20);
            this.offScreeng.setColor(this.bgc);
        }
        if (this.durum == 2) {
            this.offScreeng.drawImage(this.offimg2, this.X, 0, this);
            this.offScreeng.drawImage(this.imgust, this.XX, this.YY, this);
        }
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.offimg, 0, 0, this);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
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
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.rg) {
            if (this.status_bar != null) {
                this.showStatus("" + this.status_bar);
            }
        }
        else {
            this.showStatus("  Unregistered version of colorfader Java applet. URL: japplets.tripod.com");
        }
        this.runner.suspend();
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
        this.runner.resume();
        ((Frame)this.getParent()).setCursor(0);
        return true;
    }
    
    public void run() {
        this.repaint();
        try {
            this.tracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        if (this.tracker.isErrorAny()) {
            this.durum = 1;
            this.errstr = "Image Error !";
        }
        else {
            this.imgw = this.imgust.getWidth(this);
            this.imgh = this.imgust.getHeight(this);
            this.imgwidth = this.imgalt.getWidth(this);
            this.imgheight = this.imgalt.getHeight(this);
            this.XX = 0;
            this.YY = 0;
            if (this.image_align != null && this.imgw < this.genislik) {
                if (this.image_align.equals("center")) {
                    this.XX = (this.genislik - this.imgw) / 2;
                }
                if (this.image_align.equals("right")) {
                    this.XX = this.genislik - this.imgw;
                }
            }
            if (this.image_valign != null && this.imgh < this.yukseklik) {
                if (this.image_valign.equals("center")) {
                    this.YY = (this.yukseklik - this.imgh) / 2;
                }
                if (this.image_valign.equals("bottom")) {
                    this.YY = this.yukseklik - this.imgh;
                }
            }
            int n = 0;
            int n2 = 0;
            this.kaplaboy = 0;
            this.kaplayuk = 0;
            if (this.imgwidth >= this.genislik) {
                this.kaplaboy = this.imgwidth + this.imgwidth;
                n = 1;
            }
            else {
                while (this.kaplaboy < this.genislik) {
                    this.kaplaboy += this.imgwidth;
                    ++n;
                }
                this.kaplaboy += this.imgwidth;
            }
            if (this.imgheight >= this.yukseklik) {
                this.kaplayuk = this.imgheight;
                n2 = 0;
            }
            else {
                while (this.kaplayuk < this.yukseklik) {
                    this.kaplayuk += this.imgheight;
                    ++n2;
                }
                --n2;
            }
            this.offimg2 = this.createImage(this.kaplaboy, this.yukseklik);
            (this.offScreeng2 = this.offimg2.getGraphics()).setColor(this.bgc);
            this.offScreeng2.fillRect(0, 0, this.kaplaboy, this.yukseklik);
            this.don2 = 0;
            while (this.don2 <= n2) {
                this.don = 0;
                while (this.don <= n) {
                    this.offScreeng2.drawImage(this.imgalt, this.don * this.imgwidth, this.don2 * this.imgheight, this);
                    ++this.don;
                }
                ++this.don2;
            }
            this.offScreeng2.dispose();
            this.X = -this.imgwidth;
            this.durum = 2;
        }
        this.X = -this.imgwidth;
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
        this.repaint();
        while (true) {
            try {
                Thread.sleep(this.delay_time);
            }
            catch (InterruptedException ex2) {}
            if (this.scroll_way.equals("false")) {
                --this.X;
                if (this.X <= -this.imgwidth) {
                    this.X = 0;
                }
            }
            else {
                ++this.X;
                if (this.X >= 0) {
                    this.X = -this.imgwidth;
                }
            }
            this.repaint();
        }
    }
    
    public magicimage() {
        this.image_align = "left";
        this.image_valign = "left";
        this.bgcolor = "";
        this.target_frame = "";
        this.infostr = "";
        this.link1 = "";
        this.scroll_way = "";
        this.status_bar = "";
        this.regcode = "";
        this.errstr = "";
        this.inf = false;
        this.rg = false;
    }
}
