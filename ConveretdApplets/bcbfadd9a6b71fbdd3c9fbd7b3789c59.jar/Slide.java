import java.awt.image.ImageProducer;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.net.URL;
import java.awt.Graphics;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Slide extends Applet implements Runnable
{
    Thread rn;
    Color bgc;
    int bgi;
    int i;
    int j;
    int num;
    int durum;
    int cw;
    int ch;
    int dly;
    int fd;
    int ci;
    int md;
    int md2;
    int cit;
    int wp;
    int tm;
    int crr;
    int w;
    int h;
    int X;
    int Y;
    int sc;
    int inout;
    int mx;
    int my;
    int acisay;
    int modd;
    double alfasin;
    double alfa;
    Image[] img;
    Image oi;
    Image offi;
    String bs;
    String errstr;
    String ialign;
    String ivalign;
    MediaTracker tracker;
    String[] links;
    String[] stb;
    int[] imgw;
    int[] imgh;
    PixelGrabber pg;
    int[][] dt;
    MemoryImageSource MIS;
    Graphics offg2;
    int[] isle;
    boolean rg;
    boolean whb;
    URL u;
    int[] dur;
    double[] acd;
    Frame frm;
    Container container;
    
    public Slide() {
        this.ci = 1;
        this.wp = 100;
        this.crr = 1;
        this.bs = "";
        this.errstr = "";
        this.ialign = "";
        this.ivalign = "";
        this.rg = false;
        this.whb = false;
    }
    
    @Override
    public void init() {
        this.w = -1;
        this.h = -1;
        this.whb = false;
        super.setBackground(Color.white);
        this.bgi = this.fi(super.getParameter("bgcolor"), 16, 0);
        this.dly = this.fi(super.getParameter("transition_delay"), 10, 100);
        this.wp = this.fi(super.getParameter("delay"), 10, 1000);
        this.sc = this.fi(super.getParameter("step_count"), 10, 20);
        this.modd = this.fi(super.getParameter("mode"), 10, 0);
        this.modd %= 2;
        this.bgc = new Color(this.bgi);
        this.tracker = new MediaTracker(this);
        this.i = 1;
        this.ivalign = super.getParameter("image_valign");
        this.ialign = super.getParameter("image_align");
        while (true) {
            this.bs = super.getParameter("image" + this.i);
            if (this.bs == null) {
                --this.i;
                if (this.i >= 0) {
                    break;
                }
                this.i = 0;
                break;
            }
            else if (!this.bs.endsWith(".gif") && !this.bs.endsWith(".jpg") && !this.bs.endsWith(".png")) {
                --this.i;
                if (this.i >= 0) {
                    break;
                }
                this.i = 0;
                break;
            }
            else {
                ++this.i;
            }
        }
        this.num = this.i;
        this.img = new Image[this.num + 1];
        this.links = new String[this.num + 1];
        this.stb = new String[this.num + 1];
        this.imgw = new int[this.num + 1];
        this.imgh = new int[this.num + 1];
        this.dur = new int[this.num + 1];
        this.i = 1;
        while (this.i <= this.num) {
            this.img[this.i] = super.getImage(super.getCodeBase(), "" + super.getParameter(new StringBuffer().append("image").append(this.i).toString()));
            this.tracker.addImage(this.img[this.i], this.i % 5);
            this.links[this.i] = super.getParameter("link" + this.i);
            this.stb[this.i] = super.getParameter("status_bar_msg" + this.i);
            if (this.links[this.i] == null) {
                this.dur[this.i] = 0;
            }
            else {
                this.dur[this.i] = 1;
                if (this.links[this.i].equals("") || this.links[this.i].equals(" ") || this.links[this.i].equals("no")) {
                    this.dur[this.i] = 0;
                }
            }
            ++this.i;
        }
        this.durum = 0;
        super.show();
        this.w = super.size().width;
        this.h = super.size().height;
        this.offi = super.createImage(this.w, this.h);
        (this.offg2 = this.offi.getGraphics()).setColor(this.bgc);
        this.offg2.fillRect(0, 0, this.w, this.h);
        this.container = super.getParent();
        while (!(this.container instanceof Frame)) {
            this.container = this.container.getParent();
        }
        this.frm = (Frame)this.container;
        this.whb = true;
    }
    
    public int fi(final String s, final int n, final int n2) {
        int int1;
        try {
            int1 = Integer.parseInt(s, n);
        }
        catch (Exception ex) {
            int1 = n2;
        }
        return int1;
    }
    
    public void smc() {
        if (!this.whb || this.inout != 1) {
            return;
        }
        if (this.dur[this.crr] == 0) {
            this.frm.setCursor(0);
            return;
        }
        this.frm.setCursor(12);
    }
    
    @Override
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        if (this.whb) {
            if (this.durum != 2) {
                return true;
            }
            if (this.num <= 0) {
                return true;
            }
            this.inout = 1;
            super.repaint();
            this.smc();
        }
        return true;
    }
    
    @Override
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (this.whb) {
            this.inout = 0;
            super.showStatus("");
            this.frm.setCursor(0);
        }
        return true;
    }
    
    @Override
    public void update(final Graphics graphics) {
        if (!this.whb) {
            return;
        }
        if (this.durum == 2) {
            this.oi.flush();
            this.offg2.drawImage(this.oi, this.X, this.Y, null);
        }
        this.paint(graphics);
    }
    
    @Override
    public void paint(final Graphics graphics) {
        if (!this.whb) {
            return;
        }
        if (this.durum == 2) {
            graphics.drawImage(this.offi, 0, 0, this);
            return;
        }
        graphics.setColor(Color.white);
        graphics.fillRect(0, 0, this.w, this.h);
        graphics.setColor(Color.black);
        if (this.durum == 0) {
            graphics.drawString("Loading images...", 10, 20);
        }
        if (this.durum == 1) {
            graphics.drawString("Images were not found.", 10, 20);
        }
        if (this.durum == 3) {
            graphics.drawString("Images must be the same size.", 10, 20);
        }
        if (this.durum != 4) {
            return;
        }
        graphics.drawString("Info parameter error.", 10, 20);
    }
    
    @Override
    public void start() {
        if (this.rn == null || !this.rn.isAlive()) {
            this.rn = new Thread(this);
        }
        this.rn.start();
    }
    
    @Override
    public void stop() {
        if (this.rn == null) {
            return;
        }
        this.rn.stop();
        this.rn = null;
    }
    
    @Override
    public void destroy() {
        this.rn = null;
    }
    
    @Override
    public void run() {
        this.durum = 0;
        try {
            this.tracker.waitForAll();
        }
        catch (InterruptedException ex2) {}
        if (this.tracker.isErrorAny()) {
            this.durum = 1;
            super.repaint();
            return;
        }
        this.i = 1;
        while (this.i <= this.num) {
            this.imgw[this.i] = this.img[this.i].getWidth(this);
            this.imgh[this.i] = this.img[this.i].getHeight(this);
            if (this.i > 1 && (this.imgw[this.i] != this.imgw[this.i - 1] || this.imgh[this.i] != this.imgh[this.i - 1])) {
                this.durum = 3;
                super.repaint();
                return;
            }
            this.cw = this.imgw[this.i];
            this.ch = this.imgh[this.i];
            ++this.i;
        }
        if (this.ialign != null) {
            if (this.cw < this.w) {
                this.X = 0;
                if (this.ialign.equals("center")) {
                    this.X = (this.w - this.cw) / 2;
                }
                if (this.ialign.equals("right")) {
                    this.X = this.w - this.cw;
                }
            }
            else {
                this.X = 0;
                if (this.ialign.equals("center")) {
                    this.X = -1 * (this.cw - this.w) / 2;
                }
                if (this.ialign.equals("right")) {
                    this.X = this.w - this.cw;
                }
            }
        }
        if (this.ivalign != null && this.ch < this.h) {
            if (this.ivalign.equals("center")) {
                this.Y = (this.h - this.ch) / 2;
            }
            if (this.ivalign.equals("bottom")) {
                this.Y = this.h - this.ch;
            }
        }
        this.dt = new int[this.num + 1][this.cw * this.ch];
        this.isle = new int[this.cw * this.ch];
        this.acd = new double[this.cw * this.ch];
        this.MIS = new MemoryImageSource(this.cw, this.ch, this.isle, 0, this.cw);
        this.i = 1;
        while (this.i <= this.num) {
            this.pg = new PixelGrabber(this.img[this.i].getSource(), 0, 0, this.cw, this.ch, this.dt[this.i], 0, this.cw);
            try {
                this.pg.grabPixels();
            }
            catch (Exception ex3) {}
            ++this.i;
        }
        this.mx = this.cw / 2;
        this.my = this.ch / 2;
        if (this.modd == 0) {
            this.alfa = 6.283185307179586 / this.sc;
        }
        else {
            this.alfa = 3.141592653589793 / this.sc;
        }
        this.i = 0;
        while (this.i < this.cw * this.ch) {
            this.isle[this.i] = this.dt[1][this.i];
            final double n = this.my - Math.floor(this.i / this.cw);
            final double n2 = this.i % this.cw - this.mx;
            double atan = Math.atan(n / n2);
            if (atan < 0.0) {
                atan *= -1.0;
            }
            if (this.modd == 0 && (n < 0.0 & n2 < 0.0)) {
                atan += 3.141592653589793;
            }
            if ((this.modd == 0 || this.modd == 1) && (n >= 0.0 & n2 < 0.0)) {
                atan = 3.141592653589793 - atan;
            }
            if (this.modd == 0 && (n < 0.0 & n2 >= 0.0)) {
                atan = 6.283185307179586 - atan;
            }
            if (this.modd == 1 && (n < 0.0 & n2 >= 0.0)) {
                atan = 3.141592653589793 - atan;
            }
            this.acd[this.i] = atan - 0.003141592653589793;
            if (atan >= 0.0) {}
            ++this.i;
        }
        this.oi = super.createImage(this.MIS);
        this.durum = 2;
        super.repaint();
        this.fd = 1;
        this.i = 0;
        this.ci = 0;
        this.cit = -1;
        while (true) {
            if (this.cit != this.ci) {
                this.cit = this.ci;
                this.tm = this.wp;
            }
            else {
                this.tm = this.dly;
            }
            try {
                Thread.sleep(this.tm);
            }
            catch (InterruptedException ex) {
                ex.printStackTrace();
            }
            this.md = this.ci % this.num + 1;
            this.md2 = this.md % this.num + 1;
            this.i = 0;
            if (this.fd > this.sc / 2) {
                this.crr = this.md2;
                this.smc();
            }
            else {
                this.crr = this.md;
            }
            this.alfasin = Math.sin(this.fd * this.alfa);
            if (this.fd == this.sc) {
                if (this.modd == 0) {
                    this.alfasin = 6.283185307179586;
                }
                else {
                    this.alfasin = 3.141592653589793;
                }
            }
            this.i = 0;
            while (this.i < this.cw * this.ch) {
                if (this.acd[this.i] <= this.fd * this.alfa) {
                    this.isle[this.i] = this.dt[this.md2][this.i];
                }
                else {
                    this.isle[this.i] = this.dt[this.md][this.i];
                }
                ++this.i;
            }
            this.isle[this.cw * this.my + this.mx] = this.dt[this.md2][this.cw * this.my + this.mx];
            ++this.fd;
            if (this.fd > this.sc) {
                this.fd = 1;
                ++this.ci;
            }
            super.repaint();
        }
    }
}
