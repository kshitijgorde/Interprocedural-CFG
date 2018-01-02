// 
// Decompiled by Procyon v0.5.30
// 

package xpl;

import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.applet.Applet;
import java.awt.Image;
import java.awt.Canvas;

public class XplView extends Canvas implements Runnable
{
    public Image img;
    public int[] pixels;
    int x;
    int y;
    int w;
    int h;
    int destw;
    int desth;
    public int[] gradPixels;
    float[] px;
    float[] py;
    float[] dpx;
    float[] dpy;
    int[] color;
    char[] sleep;
    public int coreX;
    public int coreY;
    public int step;
    public int stepsBeforeNew;
    public int lifeTime;
    public int xplCounts;
    public int particlesInCore;
    public int coresCount;
    public int totalPCount;
    public int spiralK;
    public int linear;
    public int minR;
    public int deltaR;
    public int minV;
    public int maxV;
    public int xcolor;
    public int xg2r;
    public int yg2r;
    public int sleepK;
    public int shadeK;
    public float speed;
    public boolean randDirX;
    public boolean randDirY;
    public boolean callStart;
    int gammaR;
    int gammaB;
    int gammaG;
    int gammadR;
    int gammadB;
    int gammadG;
    Thread xTr;
    public Applet app;
    
    public XplView(final Applet app, final int x, final int y, final int w, final int h, final int destw, final int desth) {
        this.img = null;
        this.step = 0;
        this.linear = 0;
        this.xg2r = 180;
        this.yg2r = 180;
        this.randDirX = false;
        this.randDirY = false;
        this.callStart = false;
        this.xTr = null;
        this.app = app;
        this.w = w;
        this.h = h;
        this.x = x;
        this.y = y;
        this.desth = desth;
        this.destw = destw;
        this.randDirX = true;
        this.randDirY = true;
        this.coresCount = 7;
        this.spiralK = 0;
        this.particlesInCore = 400;
        this.totalPCount = this.coresCount * this.particlesInCore;
        this.minR = 3;
        this.deltaR = 12;
        this.minV = 2;
        this.maxV = 5;
        this.speed = 2.0f;
        this.sleepK = 28;
        this.stepsBeforeNew = 0;
        this.shadeK = 1;
        this.lifeTime = 150;
        this.gammaR = 0;
        this.gammadR = 255;
        this.gammaB = 0;
        this.gammadG = 255;
        this.gammaG = 0;
        this.gammadB = 255;
        this.px = new float[this.totalPCount];
        this.py = new float[this.totalPCount];
        this.dpx = new float[this.totalPCount];
        this.dpy = new float[this.totalPCount];
        this.color = new int[this.totalPCount];
        this.sleep = new char[this.totalPCount];
        this.gradPixels = new int[256];
        for (int i = 0; i < 255; ++i) {
            this.gradPixels[i] = -1;
        }
        this.pixels = new int[this.w * this.h];
        for (int j = 0; j < this.h; ++j) {
            for (int k = 0; k < this.w; ++k) {
                this.pixels[j * this.w + k] = -16777216;
            }
        }
        this.img = this.app.createImage(new MemoryImageSource(this.w, this.h, this.pixels, 0, this.w));
    }
    
    public int sign(final float n) {
        if (n > 0) {
            return 1;
        }
        if (n < 0) {
            return -1;
        }
        return 0;
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void run() {
        while (true) {
            try {
                if (this.img != null) {
                    this.action2();
                }
                final Graphics graphics = this.app.getGraphics();
                if (this.img != null) {
                    graphics.drawImage(this.img, this.x, this.y, this.destw, this.desth, this);
                }
                Thread.sleep(11L);
            }
            catch (InterruptedException ex) {
                this.stop();
            }
        }
    }
    
    public void start() {
        if (this.xTr == null) {
            (this.xTr = new Thread(this)).setPriority(10);
            this.xTr.start();
        }
    }
    
    public void stop() {
        if (this.xTr != null) {
            this.xTr.stop();
            this.xTr = null;
        }
    }
    
    public void filter() {
        for (int i = this.w; i < this.w * (this.h - 1); ++i) {
            final int n = 0;
            final int n2 = 0;
            final int n3 = 0;
            final int n4 = this.pixels[i - 1];
            final int n5 = n3 + (n4 & 0xFF);
            final int n6 = n2 + ((n4 & 0xFF00) >> 8);
            final int n7 = n + ((n4 & 0xFF0000) >> 16);
            final int n8 = this.pixels[i + 1];
            final int n9 = n5 + (n8 & 0xFF);
            final int n10 = n6 + ((n8 & 0xFF00) >> 8);
            final int n11 = n7 + ((n8 & 0xFF0000) >> 16);
            final int n12 = this.pixels[i + this.w];
            final int n13 = n9 + (n12 & 0xFF);
            final int n14 = n10 + ((n12 & 0xFF00) >> 8);
            final int n15 = n11 + ((n12 & 0xFF0000) >> 16);
            final int n16 = this.pixels[i - this.w];
            this.pixels[i] = -16777216 + (Math.min(255, n15 + ((n16 & 0xFF0000) >> 16) >> 2) << 16) + (Math.min(255, n14 + ((n16 & 0xFF00) >> 8) >> 2) << 8) + Math.min(255, n13 + (n16 & 0xFF) >> 2);
        }
        for (int j = 0; j < this.w; ++j) {
            this.pixels[j] = -16777216;
        }
        for (int k = (this.h - 1) * this.w; k < this.h * this.w - 1; ++k) {
            this.pixels[k] = -16777216;
        }
        this.img.flush();
    }
    
    public void pp(final int n, final int n2, final int n3) {
        if (n2 < this.h - 1 && n2 >= 0 && n < this.w - 1 && n >= 0) {
            this.pixels[n2 * this.w + n] = n3;
        }
    }
    
    public void action2() {
        --this.stepsBeforeNew;
        if (this.stepsBeforeNew < 0) {
            if (this.callStart) {
                this.app.start();
            }
            this.stepsBeforeNew = this.lifeTime;
            this.coreX = this.w / 2;
            this.coreY = this.h / 2;
            for (int i = 0; i < this.totalPCount; ++i) {
                if (i % this.particlesInCore == 0) {
                    this.xcolor = -16777216 + ((int)(this.gammaR + Math.random() * this.gammadR) << 16) + ((int)(this.gammaG + Math.random() * this.gammadG) << 8) + (int)(this.gammaB + Math.random() * this.gammadB);
                }
                final int n = (int)(360 * Math.random());
                final int n2 = this.minR + (int)(this.deltaR * Math.random());
                if (this.spiralK > 5) {
                    final int n3 = n2 + i / this.spiralK;
                    this.px[i] = this.coreX + n3 * (float)Math.sin(i * 3.14 / this.xg2r);
                    this.py[i] = this.coreY + n3 * (float)Math.cos(i * 3.14 / this.yg2r);
                }
                else {
                    this.px[i] = this.coreX + n2 * (float)Math.sin(n * 3.14 / this.xg2r);
                    this.py[i] = this.coreY + n2 * (float)Math.cos(n * 3.14 / this.yg2r);
                }
                if (this.linear == 1) {
                    this.px[i] = i % this.w;
                    this.py[i] = this.h / 2;
                }
                if (this.randDirX) {
                    this.dpx[i] = this.sign(0.5f - (float)Math.random()) * (float)(this.speed / 10.0 * Math.sin(n * 3.14 / 180.0));
                }
                else {
                    this.dpx[i] = (float)(this.speed / 10.0 * Math.sin(n * 3.14 / 180.0));
                }
                if (this.randDirY) {
                    this.dpy[i] = this.sign(0.5f - (float)Math.random()) * (float)(this.speed / 10.0 * Math.cos(n * 3.14 / 180.0));
                }
                else {
                    this.dpy[i] = (float)(this.speed / 10.0 * Math.cos(n * 3.14 / 180.0));
                }
                this.sleep[i] = (char)(i / this.sleepK);
                if (i > this.totalPCount - 150) {
                    this.xcolor = 0;
                    final char[] sleep = this.sleep;
                    final int n4 = i;
                    sleep[n4] += '\n';
                }
                this.color[i] = this.xcolor;
            }
        }
        for (int j = 0; j < this.totalPCount; ++j) {
            if (this.sleep[j] > '\0') {
                final char[] sleep2 = this.sleep;
                final int n5 = j;
                --sleep2[n5];
            }
            else {
                final float[] px = this.px;
                final int n6 = j;
                px[n6] += this.dpx[j];
                final float[] py = this.py;
                final int n7 = j;
                py[n7] += this.dpy[j];
                final int n8 = (int)this.px[j];
                final int n9 = (int)this.py[j];
                int n10 = this.color[j] & 0xFF;
                int n11 = (this.color[j] & 0xFF00) >> 8;
                int n12 = (this.color[j] & 0xFF0000) >> 16;
                if (n12 > 10) {
                    n12 -= this.shadeK;
                }
                if (n11 > 10) {
                    n11 -= this.shadeK;
                }
                if (n10 > 10) {
                    n10 -= this.shadeK;
                }
                this.pp(n8, n9, this.color[j] = -16777216 + (n12 << 16) + (n11 << 8) + n10);
                this.pp(n8 - 1, n9, this.color[j]);
                this.pp(n8, n9 + 1, this.color[j]);
            }
        }
        this.filter();
    }
}
