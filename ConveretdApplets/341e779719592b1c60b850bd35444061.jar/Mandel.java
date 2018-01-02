import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.awt.Event;
import java.awt.Color;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Mandel extends Applet implements Runnable
{
    int[] pixel;
    int[] color;
    Thread mainthread;
    Graphics bbg;
    Image bb;
    Image fractimg;
    int nw;
    int nh;
    int max_i;
    int n_bailout;
    int colMult;
    double sx;
    double sy;
    double ex;
    double ey;
    int pal_len;
    int col_period;
    int zoom_sx;
    int zoom_sy;
    int zoom_ex;
    int zoom_ey;
    boolean mdown;
    
    public Mandel() {
        this.nw = 400;
        this.nh = this.nw;
        this.max_i = 300;
        this.n_bailout = 4;
        this.colMult = 5;
        this.sx = -2.0;
        this.sy = -2.0;
        this.ex = 2.0;
        this.ey = 2.0;
        this.pal_len = 2000;
        this.col_period = 15;
        this.zoom_sx = 0;
        this.zoom_sy = 0;
        this.zoom_ex = 0;
        this.zoom_ey = 0;
        this.mdown = false;
    }
    
    public void init() {
        this.pixel = new int[this.nw * this.nh];
        this.bb = this.createImage(this.nw, this.nh);
        this.fractimg = this.createImage(this.nw, this.nh);
        this.bbg = this.bb.getGraphics();
        this.initPal();
        this.calcMandel();
        this.setBackground(Color.black);
        if (this.mainthread != null) {
            (this.mainthread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.mainthread != null) {
            this.mainthread.stop();
            this.mainthread = null;
        }
    }
    
    public void destroy() {
        this.bb = null;
        this.bbg = null;
        this.fractimg = null;
    }
    
    public void run() {
        while (true) {
            this.repaint();
            try {
                Thread.sleep(30L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean mouseDown(final Event event, final int zoom_sx, final int zoom_sy) {
        this.zoom_sx = zoom_sx;
        this.zoom_sy = zoom_sy;
        return this.mdown = true;
    }
    
    public boolean mouseUp(final Event event, final int zoom_ex, final int zoom_ey) {
        if (this.mdown) {
            this.mdown = false;
            this.zoom_ex = zoom_ex;
            this.zoom_ey = zoom_ey;
            if (this.zoom_sx != this.zoom_ex && this.zoom_sy != this.zoom_ey) {
                if (this.zoom_sx > this.zoom_ex) {
                    final int zoom_sx = this.zoom_sx;
                    this.zoom_sx = this.zoom_ex;
                    this.zoom_ex = zoom_sx;
                }
                if (this.zoom_sy > this.zoom_ey) {
                    final int zoom_sy = this.zoom_sy;
                    this.zoom_sy = this.zoom_ey;
                    this.zoom_ey = zoom_sy;
                }
                final double n = this.sx + (this.zoom_sx + this.zoom_ex) / 2 * (this.ex - this.sx) / this.nw;
                final double n2 = this.sy + (this.zoom_sy + this.zoom_ey) / 2 * (this.ey - this.sy) / this.nh;
                double n3;
                if (this.zoom_ex - this.zoom_sx > this.zoom_ey - this.zoom_sy) {
                    n3 = (this.zoom_ex - this.zoom_sx) * (this.ex - this.sx) / this.nw;
                }
                else {
                    n3 = (this.zoom_ey - this.zoom_sy) * (this.ey - this.sy) / this.nh;
                }
                this.sx = n - n3 / 2.0;
                this.sy = n2 - n3 / 2.0;
                this.ex = n + n3 / 2.0;
                this.ey = n2 + n3 / 2.0;
                this.calcMandel();
                this.repaint();
            }
        }
        return true;
    }
    
    public boolean mouseDrag(final Event event, final int zoom_ex, final int zoom_ey) {
        if (this.mdown) {
            this.zoom_ex = zoom_ex;
            this.zoom_ey = zoom_ey;
            this.repaint();
        }
        return true;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        this.bbg.drawImage(this.fractimg, 0, 0, null);
        if (this.mdown) {
            int n;
            int n2;
            if (this.zoom_sx < this.zoom_ex) {
                n = this.zoom_sx;
                n2 = this.zoom_ex;
            }
            else {
                n = this.zoom_ex;
                n2 = this.zoom_sx;
            }
            int n3;
            int n4;
            if (this.zoom_sy < this.zoom_ey) {
                n3 = this.zoom_sy;
                n4 = this.zoom_ey;
            }
            else {
                n3 = this.zoom_ey;
                n4 = this.zoom_sy;
            }
            this.bbg.setColor(Color.black);
            this.bbg.drawRect(n, n3, n2 - n, n4 - n3);
        }
        graphics.drawImage(this.bb, 0, 0, null);
    }
    
    public void calcMandel() {
        double n = 0.0;
        double n2 = 0.0;
        double n3 = 0.0;
        final double[] array = new double[3];
        final double[] array2 = new double[3];
        for (int i = 0; i < this.nh; ++i) {
            for (int j = 0; j < this.nw; ++j) {
                final double n4 = this.sx + (this.ex - this.sx) * j / this.nw;
                final double n5 = this.sy + (this.ey - this.sy) * i / this.nh;
                int k = 0;
                double n6 = 0.0;
                double n7 = 0.0;
                boolean b = false;
                while (k < this.max_i) {
                    final double n8 = n6 * n6 - n7 * n7 + n4;
                    final double n9 = 2.0 * n6 * n7 + n5;
                    if (n8 * n8 + n9 * n9 > this.n_bailout) {
                        b = true;
                        n = n6 * n6 + n7 * n7;
                        n3 = this.n_bailout - n;
                        n2 = n8 * n8 + n9 * n9;
                        break;
                    }
                    n6 = n8;
                    n7 = n9;
                    ++k;
                }
                if (b) {
                    final int n10 = k * this.colMult % this.pal_len;
                    array[0] = (this.color[n10] >> 16 & 0xFF);
                    array[1] = (this.color[n10] >> 8 & 0xFF);
                    array[2] = (this.color[n10] & 0xFF);
                    array2[0] = (this.color[n10 + this.colMult] >> 16 & 0xFF);
                    array2[1] = (this.color[n10 + this.colMult] >> 8 & 0xFF);
                    array2[2] = (this.color[n10 + this.colMult] & 0xFF);
                    final double n11 = n2 - n;
                    this.pixel[i * this.nw + j] = (0xFF000000 | (int)(array[0] + (array2[0] - array[0]) * n3 / n11) << 16 | (int)(array[1] + (array2[1] - array[1]) * n3 / n11) << 8 | (int)(array[2] + (array2[2] - array[2]) * n3 / n11));
                }
                else {
                    this.pixel[i * this.nw + j] = -1;
                }
            }
        }
        this.fractimg = this.createImage(new MemoryImageSource(this.nw, this.nh, this.pixel, 0, this.nw));
    }
    
    public void initPal() {
        int n = 0;
        final int[] array = new int[3];
        final int[] array2 = new int[3];
        this.color = new int[this.pal_len];
        array[0] = (int)(Math.random() * 256.0);
        array[1] = (int)(Math.random() * 256.0);
        array[2] = (int)(Math.random() * 256.0);
        array2[0] = (int)(Math.random() * 256.0);
        array2[1] = (int)(Math.random() * 256.0);
        array2[2] = (int)(Math.random() * 256.0);
        for (int i = 0; i < this.pal_len; ++i) {
            this.color[i] = (0xFF000000 | array[0] + (array2[0] - array[0]) * n / this.col_period << 16 | array[1] + (array2[1] - array[1]) * n / this.col_period << 8 | array[2] + (array2[2] - array[2]) * n / this.col_period);
            if (n == this.col_period) {
                n = 0;
                array[0] = array2[0];
                array[1] = array2[1];
                array[2] = array2[2];
                array2[0] = (int)(Math.random() * 256.0);
                array2[1] = (int)(Math.random() * 256.0);
                array2[2] = (int)(Math.random() * 256.0);
            }
            ++n;
        }
    }
}
