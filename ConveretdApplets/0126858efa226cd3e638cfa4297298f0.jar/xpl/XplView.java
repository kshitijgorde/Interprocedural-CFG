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
    public int[] pixels2;
    int x;
    int y;
    int w;
    int h;
    int destw;
    int desth;
    int kaleidok;
    public int[] gradPixels;
    public int k3deffect;
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
    int centerKX;
    int centerKY;
    int centerX;
    int centerY;
    Thread xTr;
    public Applet app;
    int k;
    
    public XplView(final Applet app, final int x, final int y, final int w, final int h, final int destw, final int desth) {
        this.img = null;
        this.k3deffect = 0;
        this.step = 0;
        this.linear = 0;
        this.xg2r = 180;
        this.yg2r = 180;
        this.randDirX = false;
        this.randDirY = false;
        this.callStart = false;
        this.xTr = null;
        this.k = 0;
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
        this.kaleidok = 0;
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
        this.centerKX = 0;
        this.centerKY = 0;
        this.centerX = this.w / 2;
        this.centerY = this.h / 2;
        this.gradPixels = new int[256];
        for (int i = 0; i < 255; ++i) {
            this.gradPixels[i] = -1;
        }
        this.pixels = new int[this.w * this.h];
        this.pixels2 = new int[this.w * this.h];
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
                if (this.img != null && graphics != null) {
                    graphics.drawImage(this.img, this.x, this.y, this.destw, this.desth, this);
                }
                Thread.sleep(11L);
                if (++this.k < this.lifeTime) {
                    continue;
                }
                this.callStart = true;
                this.k = 0;
            }
            catch (InterruptedException ex) {
                this.stop();
            }
        }
    }
    
    public void start() {
        System.out.println("----------------------------------------------");
        System.out.println("Brain Damage java applet by Igor Samtsevich");
        System.out.println("More freeware availavle at:");
        System.out.println("http://www.controlzed.com/areality/");
        System.out.println("----------------------------------------------");
        if (this.xTr != null) {
            this.xTr.stop();
            this.xTr = null;
        }
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
                this.app.showStatus(new String(""));
                this.callStart = false;
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
                if (this.centerKX == 0) {
                    this.dpx[i] = this.sign(0.5f - (float)Math.random()) * (float)Math.random() * (float)(this.speed / 10.0 * Math.sin(n * 3.14 / 180.0));
                }
                if (this.centerKY == 0) {
                    this.dpy[i] = this.sign(0.5f - (float)Math.random()) * (float)Math.random() * (float)(this.speed / 10.0 * Math.cos(n * 3.14 / 180.0));
                }
                if (this.centerKX != 0) {
                    this.dpx[i] = (this.px[i] - this.centerX) / this.centerKX;
                }
                if (this.centerKY != 0) {
                    this.dpy[i] = (this.py[i] - this.centerY) / this.centerKY;
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
        for (int k = 0; k < this.h; ++k) {
            for (int l = 0; l < this.w; ++l) {
                this.pixels2[this.w * k + l] = this.pixels[this.w * k + l];
            }
        }
        if (this.k3deffect == 1) {
            final int n13 = this.w / 2;
            final int n14 = this.h / 2;
            ++this.kaleidok;
            final int n15 = this.kaleidok % n13;
            final int n16 = this.kaleidok % n14;
            for (int n17 = 0; n17 < n14; ++n17) {
                for (int n18 = 0; n18 < n13; ++n18) {
                    final int n19 = this.pixels2[this.w * (n17 + n16) + n18 + n15];
                    this.pixels[n17 * this.w + n18] = n19;
                    this.pixels[n17 * this.w + this.w - n18] = n19;
                    this.pixels[(this.h - n17 - 2) * this.w + n18] = n19;
                    this.pixels[(this.h - n17 - 2) * this.w + this.w - n18] = n19;
                }
            }
        }
        this.filter();
    }
    
    void initByStr(final String s) {
        final String s2 = new String("");
        String s3 = new String("");
        final int[] array = new int[20];
        for (int i = 0; i < 20; ++i) {
            array[i] = 0;
        }
        int j = 0;
        if (s.substring(j, j + 1).equals("<")) {
            ++j;
            String substring;
            do {
                substring = s.substring(j, j + 1);
                if (!substring.equals(">")) {
                    s3 = String.valueOf(s3).concat(String.valueOf(substring));
                }
                ++j;
            } while (!substring.equals(">"));
            s3 = "";
        }
        int n = 0;
        int n2 = 0;
        while (j < s.length()) {
            final String substring2 = s.substring(j, j + 1);
            if (!substring2.equals(".")) {
                if (substring2.equals("-")) {
                    n = 1;
                }
                else {
                    s3 = String.valueOf(s3).concat(String.valueOf(substring2));
                }
            }
            else {
                array[n2] = new Integer(s3);
                if (n == 1) {
                    array[n2] = -array[n2];
                }
                s3 = "";
                ++n2;
                n = 0;
            }
            ++j;
        }
        this.gammaR = array[0];
        this.gammadR = array[1];
        this.gammaG = array[2];
        this.gammadG = array[3];
        this.gammaB = array[4];
        this.gammadB = array[5];
        this.minR = array[6];
        this.deltaR = array[7];
        this.speed = array[8];
        this.spiralK = array[9];
        this.lifeTime = array[10];
        this.sleepK = array[11];
        this.shadeK = array[12];
        this.xg2r = array[13];
        this.yg2r = array[14];
        this.centerKX = array[18];
        this.centerKY = array[19];
    }
    
    public XplView() {
        this.img = null;
        this.k3deffect = 0;
        this.step = 0;
        this.linear = 0;
        this.xg2r = 180;
        this.yg2r = 180;
        this.randDirX = false;
        this.randDirY = false;
        this.callStart = false;
        this.xTr = null;
        this.k = 0;
        try {
            this.jbInit();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private void jbInit() throws Exception {
    }
}
