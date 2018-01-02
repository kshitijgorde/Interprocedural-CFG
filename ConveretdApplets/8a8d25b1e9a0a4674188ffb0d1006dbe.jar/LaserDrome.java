import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.image.PixelGrabber;
import java.awt.Font;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class LaserDrome extends Applet implements Runnable
{
    String[] str;
    Graphics bg;
    Image bi;
    Font[] f;
    int[] ld;
    PixelGrabber pg;
    int i;
    int j;
    int k;
    int[] c;
    int dw;
    int dh;
    int dw2;
    int dh2;
    Image[] b;
    Thread t;
    boolean sa;
    int[][] lx;
    int[][] ly;
    int x;
    int y;
    int xd;
    int yd;
    int co;
    int ic;
    int ns;
    RandomPlus r;
    Color tc;
    Color fc;
    Dimension d;
    int fs;
    Color bc;
    
    public void init() {
        this.str = new String[10];
        this.i = 1;
        Label_0035: {
            break Label_0035;
            String parameter;
            do {
                this.ns = this.i;
                ++this.i;
                final String[] str = this.str;
                final int n = this.i - 1;
                parameter = this.getParameter("string" + this.i);
                str[n] = parameter;
            } while (parameter != null);
        }
        if (this.ns > 10) {
            this.ns = 10;
        }
        this.d = this.getSize();
        this.dw = this.d.width;
        this.dh = this.d.height;
        this.dw2 = Math.round(this.dw / 2);
        this.dh2 = Math.round(this.dh / 2);
        this.t = new Thread(this);
        this.ld = new int[this.dw * this.dh];
        this.lx = new int[this.ns][500];
        this.ly = new int[this.ns][500];
        this.b = new Image[this.ns];
        this.c = new int[this.ns];
        this.r = new RandomPlus();
        this.f = new Font[this.ns];
        this.x = 10;
        this.y = 20;
        this.xd = 2;
        this.yd = 2;
        this.co = 0;
        this.ic = 0;
        try {
            this.xd = Integer.parseInt(this.getParameter("xspeed"));
            this.yd = Integer.parseInt(this.getParameter("yspeed"));
        }
        catch (Exception ex) {}
        this.fs = 24;
        try {
            this.fs = Integer.parseInt(this.getParameter("fontsize"));
        }
        catch (Exception ex2) {}
        this.i = 1;
        while (this.i < this.ns + 1) {
            if (this.getParameter("fontsize" + this.i) != null) {
                this.f[this.i - 1] = new Font("Dialog", 1, Integer.parseInt(this.getParameter("fontsize" + this.i)));
            }
            else {
                this.f[this.i - 1] = new Font("Dialog", 1, this.fs);
            }
            ++this.i;
        }
        this.bc = Color.lightGray;
        try {
            this.bc = new Color(Integer.parseInt(this.getParameter("backcolor"), 16));
        }
        catch (Exception ex3) {}
        this.bi = this.createImage(this.dw, this.dh);
        this.bg = this.bi.getGraphics();
        this.k = 0;
        while (this.k < this.ns) {
            this.c[this.k] = 0;
            this.bg.setFont(this.f[this.k]);
            this.bg.setColor(Color.lightGray);
            this.bg.fillRect(0, 0, this.dw, this.dh);
            this.bg.setColor(Color.red);
            this.bg.drawString(this.str[this.k], 20, 20);
            this.pg = new PixelGrabber(this.bi.getSource(), 0, 0, this.dw, this.dh, this.ld, 0, this.dw);
            try {
                this.pg.grabPixels();
            }
            catch (Exception ex4) {}
            this.bg.setColor(Color.lightGray);
            this.bg.fillRect(0, 0, this.dw, this.dh);
            this.i = 0;
            while (this.i < this.dw) {
                this.j = 0;
                while (this.j < this.dh) {
                    if ((this.ld[this.i + this.j * this.dw] & 0xFFFFFF) == 0xFF0000 && (this.ld[this.i + 1 + this.j * this.dw] & 0xFFFFFF) == 0xBDBDBD) {
                        this.lx[this.k][this.c[this.k]] = this.i;
                        this.ly[this.k][this.c[this.k]++] = this.j;
                    }
                    ++this.j;
                }
                ++this.i;
            }
            ++this.k;
        }
    }
    
    public void start() {
        this.t.start();
    }
    
    public void stop() {
        this.sa = false;
    }
    
    public void run() {
        while (this.sa) {
            this.x += this.xd;
            this.y += this.yd;
            ++this.co;
            if (this.co > 100) {
                this.co = 0;
                ++this.ic;
                this.ic %= this.ns;
                this.tc = this.r.nextColor();
                this.fc = this.r.nextColor();
            }
            if (this.x < -50 || this.x > this.dw - 100) {
                this.xd = -this.xd;
            }
            if (this.y < -20 || this.y > this.dh) {
                this.yd = -this.yd;
            }
            this.repaint();
            try {
                Thread.sleep(10L);
            }
            catch (Exception ex) {}
        }
    }
    
    public void update(final Graphics graphics) {
        this.bg.setColor(this.bc);
        this.bg.fillRect(0, 0, this.dw, this.dh);
        this.bg.setColor(this.tc);
        this.i = 0;
        while (this.i < this.c[this.ic]) {
            this.bg.drawLine(this.dw2, this.dh2, this.x + this.lx[this.ic][this.i], this.y + this.ly[this.ic][this.i]);
            ++this.i;
        }
        this.bg.setFont(this.f[this.ic]);
        this.bg.setColor(this.fc);
        this.bg.drawString(this.str[this.ic], this.x + 20, this.y + 20);
        graphics.drawImage(this.bi, 0, 0, this);
    }
    
    public LaserDrome() {
        this.sa = true;
        this.tc = Color.red;
        this.fc = Color.green;
    }
}
