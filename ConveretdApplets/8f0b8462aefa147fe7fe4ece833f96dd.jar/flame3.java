import java.awt.FontMetrics;
import java.awt.image.PixelGrabber;
import java.util.StringTokenizer;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.MemoryImageSource;
import java.awt.Font;
import java.awt.Image;
import java.util.Random;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class flame3 extends Applet implements Runnable
{
    final int XS = 1;
    final int YS = 1;
    final int XB = 15;
    final int SMOOTH = 1;
    int decay;
    boolean fade;
    boolean stove;
    boolean solid;
    int colmask;
    int colshift;
    int curpalnum;
    int BOTTOM;
    int XE;
    int XSIZE;
    int YSIZE;
    int SIZE;
    final int MAXPALNUM = 6;
    final int MAXPAL = 256;
    int[] pal;
    String showtype;
    Random rd;
    Image art;
    Thread motor;
    int[] off;
    int count;
    int burn;
    linkme colorlink;
    linkme toplink;
    ion VERSION;
    final Font MFONT;
    final long TW_DLAY = 4500L;
    final long TW_FADE = 1000L;
    final int TW_MAXCOLOR = 50;
    int TW_LEN;
    long tw_timer;
    int[][] tw_words;
    int tw_max;
    int tw_count;
    int[] tw_color;
    MemoryImageSource FlameSource;
    Image FlameImage;
    boolean mouseover;
    boolean mouseup;
    boolean mousedn;
    boolean mousedrag;
    int mousex;
    int mousey;
    int mouseupx;
    int mouseupy;
    int mousednx;
    int mousedny;
    
    public flame3() {
        this.colmask = 16711680;
        this.colshift = 16;
        this.curpalnum = 1;
        this.pal = new int[256];
        this.rd = new Random();
        this.MFONT = new Font("Arial", 1, 11);
        this.tw_color = new int[50];
    }
    
    public void begin() {
        this.VERSION = new ion();
        final boolean compare = this.VERSION.compare(this.getDocumentBase().getHost(), this.getParameter("id_key"));
        final Graphics graphics = this.art.getGraphics();
        (this.toplink = new linkme(graphics, this.XSIZE, "6sense.com", "http://www.6sense.com/applets/", this.getParameter("maintext"), this.getParameter("mainlink"), compare)).setcenter(graphics, this.YSIZE - 30);
        final String parameter = this.getParameter("color_change");
        boolean b = false;
        if (parameter != null && parameter.toLowerCase().equals("yes")) {
            b = true;
        }
        (this.colorlink = new linkme(graphics, this.XSIZE, null, null, "Color", "http://www.6sense.com", b)).setright(graphics, this.YSIZE - 30);
        this.colorlink.setcolor(Color.black, Color.gray, Color.yellow, Color.yellow);
        graphics.dispose();
        this.getTarget();
    }
    
    void checkOver() {
        if (this.toplink.isOver(this.mousex, this.mousey)) {
            this.setCursor(Cursor.getPredefinedCursor(12));
            this.showStatus("Link: " + this.toplink.getLink());
            this.colorlink.linkowner = false;
        }
        else if (this.colorlink.isOver(this.mousex, this.mousey)) {
            this.setCursor(Cursor.getPredefinedCursor(12));
            this.showStatus("Click to change color.");
            this.colorlink.linkowner = true;
        }
        else {
            this.setCursor(Cursor.getPredefinedCursor(0));
            this.showStatus("Click to cool flame.");
            this.colorlink.linkowner = false;
        }
    }
    
    void checkPressed() {
        if (this.mouseup && this.toplink.isOver(this.mouseupx, this.mouseupy)) {
            URL url;
            try {
                url = new URL(this.toplink.getLink());
            }
            catch (MalformedURLException ex) {
                return;
            }
            this.getAppletContext().showDocument(url, this.showtype);
        }
        if (this.mousedn) {
            if (this.colorlink.isOver(this.mousednx, this.mousedny)) {
                this.makepal(this.curpalnum);
                if (++this.curpalnum > 6) {
                    this.curpalnum = 1;
                }
            }
            else {
                this.burn = 40;
            }
        }
        this.mouseup = false;
        this.mousedn = false;
    }
    
    void clearOff() {
        for (int i = this.SIZE - 1; i >= 0; this.off[i--] = -16777216) {}
    }
    
    void clearline(final int n) {
        int n2 = this.XSIZE - this.XSIZE;
        if (n < 1 || n >= this.XE) {
            for (int i = 1; i < this.YSIZE; ++i) {
                this.off[n + n2] = -16777216;
                n2 += this.XSIZE;
            }
        }
    }
    
    int colorit(final int n) {
        return this.pal[n & 0xFF];
    }
    
    int coloroff(final int n) {
        return (n & this.colmask) >> this.colshift;
    }
    
    void draw_init() {
        this.clearOff();
        (this.FlameSource = new MemoryImageSource(this.XSIZE, this.YSIZE, this.off, 0, this.XSIZE)).setAnimated(true);
        this.FlameImage = this.createImage(this.FlameSource);
    }
    
    void drawflame() {
        this.FlameSource.newPixels(0, 0, this.XSIZE, this.YSIZE);
        final Graphics graphics = this.art.getGraphics();
        graphics.drawImage(this.FlameImage, 0, 0, null);
        this.toplink.draw(graphics);
        this.colorlink.draw(graphics);
        graphics.dispose();
    }
    
    void getTarget() {
        this.showtype = "_blank";
        final String parameter = this.getParameter("target");
        if (parameter != null) {
            if (parameter.equals("_self")) {
                this.showtype = parameter;
            }
            else if (parameter.equals("_parent")) {
                this.showtype = parameter;
            }
            else if (parameter.equals("_top")) {
                this.showtype = parameter;
            }
            else if (parameter.equals("_blank")) {
                this.showtype = parameter;
            }
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.target == this) {
            switch (event.id) {
                case 503: {
                    this.mousex = event.x;
                    this.mousey = event.y;
                    this.mouseover = true;
                    break;
                }
                case 502: {
                    this.mouseupx = event.x;
                    this.mouseupy = event.y;
                    this.mouseup = true;
                    this.mousedn = false;
                    break;
                }
                case 506: {
                    this.mousex = event.x;
                    this.mousey = event.y;
                    this.mousedrag = true;
                    break;
                }
                case 501: {
                    this.mousednx = event.x;
                    this.mousedny = event.y;
                    this.mousedn = true;
                    break;
                }
                case 504: {
                    this.toplink.linkowner = true;
                    break;
                }
                case 505: {
                    this.toplink.linkowner = false;
                    this.colorlink.linkowner = false;
                    break;
                }
                case 1008: {
                    this.makepal(1);
                    break;
                }
                case 1009: {
                    this.makepal(2);
                    break;
                }
                case 1010: {
                    this.makepal(3);
                    break;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    public void init() {
        this.setBackground(Color.black);
        final Dimension size = this.getSize();
        this.XSIZE = size.width;
        this.YSIZE = size.height;
        this.SIZE = this.XSIZE * this.YSIZE;
        System.err.println(String.valueOf(this.XSIZE) + " " + this.YSIZE + " " + this.SIZE);
        this.XE = this.XSIZE - 1;
        this.BOTTOM = this.SIZE - this.XSIZE;
        this.off = new int[this.SIZE];
        this.art = this.createImage(this.XSIZE, this.YSIZE);
    }
    
    void makepal(final int n) {
        if (n == 5) {
            this.colmask = 65280;
            this.colshift = 8;
            for (int i = 0; i < 256; ++i) {
                this.pal[i] = (0xFF000000 | i << 16 | i << 8 | i);
            }
            for (int j = 0; j < 50; ++j) {
                this.tw_color[j] = (this.pal[240 - j] & 0xFF00FFFF);
            }
        }
        else if (n == 2) {
            this.colmask = 65280;
            this.colshift = 8;
            int n2 = 1;
            int n3 = 1;
            int n4 = 3;
            int n5 = 5;
            for (int k = 0; k < 256; ++k) {
                this.pal[k] = -16777216;
            }
            for (int l = 1; l < 60; ++l) {
                this.pal[l] = (0xFF000000 | n3 << 16 | l << 8 | n2);
                if (n4 == 0) {
                    n4 = 5;
                    ++n2;
                }
                if (n5 == 0) {
                    n5 = 7;
                    ++n3;
                }
                --n4;
                --n5;
            }
            for (int n6 = 60; n6 < 90; ++n6) {
                this.pal[n6] = (0xFF000000 | n3 << 16 | n6 << 8 | n2);
                if (n4 == 0) {
                    n4 = 3;
                    ++n2;
                }
                if (n5 == 0) {
                    n5 = 5;
                    ++n3;
                }
                --n4;
                --n5;
            }
            for (int n7 = 90; n7 < 130; ++n7) {
                this.pal[n7] = (0xFF000000 | n3 << 16 | n7 << 8 | n2);
                if (n2 < 255) {
                    ++n2;
                }
                if (n3 < 255) {
                    ++n3;
                }
                --n5;
            }
            for (int n8 = 130; n8 < 210; ++n8) {
                this.pal[n8] = (0xFF000000 | n3 << 16 | n8 << 8 | n2);
                n2 += 2;
                if (n2 >= 255) {
                    n2 = 255;
                }
                if (n3 < 255) {
                    ++n3;
                }
            }
            for (int n9 = 210; n9 < 256; ++n9) {
                this.pal[n9] = (0xFF000000 | n3 << 16 | n9 << 8 | n2);
                n2 += 3;
                if (n2 >= 255) {
                    n2 = 255;
                }
                n3 += 2;
                if (n3 >= 255) {
                    n3 = 255;
                }
            }
            for (int n10 = 0; n10 < 50; ++n10) {
                this.tw_color[n10] = ((this.pal[240 - n10] & 0xFF00FF00) | (0xFF0000 & this.pal[240 - n10] << 8));
            }
        }
        else if (n == 3) {
            this.colmask = 255;
            this.colshift = 0;
            int n11 = 1;
            int n12 = 1;
            int n13 = 3;
            int n14 = 5;
            for (int n15 = 0; n15 < 256; ++n15) {
                this.pal[n15] = -16777216;
            }
            for (int n16 = 1; n16 < 60; ++n16) {
                this.pal[n16] = (0xFF000000 | n12 << 16 | n11 << 8 | n16);
                if (n13 == 0) {
                    n13 = 5;
                    ++n11;
                }
                if (n14 == 0) {
                    n14 = 7;
                    ++n12;
                }
                --n13;
                --n14;
            }
            for (int n17 = 60; n17 < 90; ++n17) {
                this.pal[n17] = (0xFF000000 | n12 << 16 | n11 << 8 | n17);
                if (n13 == 0) {
                    n13 = 3;
                    ++n11;
                }
                if (n14 == 0) {
                    n14 = 5;
                    ++n12;
                }
                --n13;
                --n14;
            }
            for (int n18 = 90; n18 < 130; ++n18) {
                this.pal[n18] = (0xFF000000 | n12 << 16 | n11 << 8 | n18);
                if (n11 < 255) {
                    ++n11;
                }
                if (n12 < 255) {
                    ++n12;
                }
                --n14;
            }
            for (int n19 = 130; n19 < 210; ++n19) {
                this.pal[n19] = (0xFF000000 | n12 << 16 | n11 << 8 | n19);
                if (++n11 >= 255) {
                    n11 = 255;
                }
                if (n12 < 255) {
                    ++n12;
                }
            }
            for (int n20 = 210; n20 < 256; ++n20) {
                this.pal[n20] = (0xFF000000 | n12 << 16 | n11 << 8 | n20);
                n11 += 3;
                if (n11 >= 255) {
                    n11 = 255;
                }
                n12 += 2;
                if (n12 >= 255) {
                    n12 = 255;
                }
            }
            for (int n21 = 0; n21 < 50; ++n21) {
                this.tw_color[n21] = (0xFF000000 | 255 - n21 << 16 | 255 - n21 << 8 | 255 - n21);
            }
        }
        else if (n == 4) {
            this.colmask = 16711680;
            this.colshift = 16;
            int n22 = 1;
            int n23 = 1;
            int n24 = 3;
            int n25 = 5;
            for (int n26 = 0; n26 < 256; ++n26) {
                this.pal[n26] = -16777216;
            }
            for (int n27 = 1; n27 < 60; ++n27) {
                this.pal[n27] = (0xFF000000 | n27 << 16 | n22 << 8 | n23);
                if (n24 == 0) {
                    n24 = 7;
                    ++n22;
                }
                if (n25 == 0) {
                    n25 = 3;
                    ++n23;
                }
                --n24;
                --n25;
            }
            for (int n28 = 60; n28 < 90; ++n28) {
                this.pal[n28] = (0xFF000000 | n28 << 16 | n22 << 8 | n23);
                if (n24 == 0) {
                    n24 = 5;
                    ++n22;
                }
                if (n25 == 0) {
                    n25 = 2;
                    ++n23;
                }
                --n24;
                --n25;
            }
            for (int n29 = 90; n29 < 130; ++n29) {
                this.pal[n29] = (0xFF000000 | n29 << 16 | n22 << 8 | n23);
                if (n24 == 0) {
                    n24 = 3;
                    ++n22;
                }
                if (n23 < 255) {
                    ++n23;
                }
                --n24;
            }
            for (int n30 = 130; n30 < 210; ++n30) {
                this.pal[n30] = (0xFF000000 | n30 << 16 | n22 << 8 | n23);
                if (n22 < 255) {
                    ++n22;
                }
                n23 += 2;
                if (n23 >= 255) {
                    n23 = 255;
                }
            }
            for (int n31 = 210; n31 < 256; ++n31) {
                this.pal[n31] = (0xFF000000 | n31 << 16 | n22 << 8 | n23);
                n22 += 2;
                if (n22 >= 255) {
                    n22 = 255;
                }
                n23 += 4;
                if (n23 >= 255) {
                    n23 = 255;
                }
            }
            for (int n32 = 0; n32 < 50; ++n32) {
                this.tw_color[n32] = (0xFF000000 | 255 - n32 << 16 | 255 - n32 << 8 | 255 - n32);
            }
        }
        else if (n == 6) {
            this.colmask = 16711680;
            this.colshift = 16;
            int n33 = 1;
            int n34 = 1;
            int n35 = 3;
            int n36 = 5;
            for (int n37 = 0; n37 < 256; ++n37) {
                this.pal[n37] = -16777216;
            }
            for (int n38 = 1; n38 < 60; ++n38) {
                this.pal[n38] = (0xFF000000 | n38 << 16 | n33 << 8 | n34);
                if (n35 == 0) {
                    n35 = 9;
                    ++n33;
                }
                if (n36 == 0) {
                    n36 = 6;
                    ++n34;
                }
                --n35;
                --n36;
            }
            for (int n39 = 60; n39 < 90; ++n39) {
                this.pal[n39] = (0xFF000000 | n39 << 16 | n33 << 8 | n34);
                if (n35 == 0) {
                    n35 = 8;
                    ++n33;
                }
                if (n36 == 0) {
                    n36 = 5;
                    ++n34;
                }
                --n35;
                --n36;
            }
            for (int n40 = 90; n40 < 130; ++n40) {
                this.pal[n40] = (0xFF000000 | n40 << 16 | n33 << 8 | n34);
                if (n35 == 0) {
                    n35 = 5;
                    ++n33;
                }
                if (n36 == 0) {
                    n36 = 3;
                    ++n34;
                }
                --n35;
                --n36;
            }
            for (int n41 = 130; n41 < 210; ++n41) {
                this.pal[n41] = (0xFF000000 | n41 << 16 | n33 << 8 | n34);
                if (++n33 >= 255) {
                    n33 = 255;
                }
                if (++n34 >= 255) {
                    n34 = 255;
                }
            }
            for (int n42 = 210; n42 < 256; ++n42) {
                this.pal[n42] = (0xFF000000 | n42 << 16 | n33 << 8 | n34);
                n33 += 3;
                if (n33 >= 255) {
                    n33 = 255;
                }
                n34 += 3;
                if (n34 >= 255) {
                    n34 = 255;
                }
            }
            for (int n43 = 0; n43 < 50; ++n43) {
                this.tw_color[n43] = (0xFF000000 | 255 - n43 << 16 | 255 - n43 << 8 | 255 - n43);
            }
        }
        else {
            this.colmask = 16711680;
            this.colshift = 16;
            int n44 = 1;
            int n45 = 1;
            int n46 = 3;
            int n47 = 5;
            for (int n48 = 0; n48 < 256; ++n48) {
                this.pal[n48] = -16777216;
            }
            for (int n49 = 1; n49 < 60; ++n49) {
                this.pal[n49] = (0xFF000000 | n49 << 16 | n44 << 8 | n45);
                if (n46 == 0) {
                    n46 = 5;
                    ++n44;
                }
                if (n47 == 0) {
                    n47 = 7;
                    ++n45;
                }
                --n46;
                --n47;
            }
            for (int n50 = 60; n50 < 90; ++n50) {
                this.pal[n50] = (0xFF000000 | n50 << 16 | n44 << 8 | n45);
                if (n46 == 0) {
                    n46 = 3;
                    ++n44;
                }
                if (n47 == 0) {
                    n47 = 6;
                    ++n45;
                }
                --n46;
                --n47;
            }
            for (int n51 = 90; n51 < 130; ++n51) {
                this.pal[n51] = (0xFF000000 | n51 << 16 | n44 << 8 | n45);
                if (n44 < 255) {
                    ++n44;
                }
                if (n47 == 0) {
                    n47 = 6;
                    ++n45;
                }
                --n47;
            }
            for (int n52 = 130; n52 < 210; ++n52) {
                this.pal[n52] = (0xFF000000 | n52 << 16 | n44 << 8 | n45);
                n44 += 2;
                if (n44 >= 255) {
                    n44 = 255;
                }
                if (n47 == 0) {
                    n47 = 5;
                    ++n45;
                }
                --n47;
            }
            for (int n53 = 210; n53 < 256; ++n53) {
                this.pal[n53] = (0xFF000000 | n53 << 16 | n44 << 8 | n45);
                n44 += 3;
                if (n44 >= 255) {
                    n44 = 255;
                }
                if (n47 == 0) {
                    n47 = 4;
                    ++n45;
                }
                --n47;
            }
            for (int n54 = 0; n54 < 50; ++n54) {
                this.tw_color[n54] = ((this.pal[240 - n54] & 0xFFFFFF00) | (this.pal[240 - n54] >> 16 & 0xFF));
            }
        }
        if (!this.solid) {
            for (int n55 = 0; n55 < 50; ++n55) {
                this.tw_color[n55] = this.colorit(210 + this.rand(40));
            }
        }
    }
    
    void mouse_init() {
        this.mouseover = false;
        this.mouseup = false;
        this.mousedn = false;
        this.mousedrag = false;
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.art, 0, 0, null);
    }
    
    public synchronized void process() {
        final int n = 1 + (this.XE - 1) / 2;
        if (this.stove) {
            for (int i = this.rand(8); i > 0; --i) {
                final int n2 = this.rand(this.XE - 1 - 15 - 15) + 15;
                for (int j = 0; j < 10; ++j) {
                    this.off[this.BOTTOM + n2 + j] = this.colorit(220 + this.rand(36));
                }
            }
        }
        if (this.burn > 0) {
            this.splash();
            --this.burn;
        }
        this.splashRandom();
        this.smoothBottom();
        this.clearline(0);
        this.clearline(this.XE);
        for (int k = 1; k < n; ++k) {
            this.processline(k);
        }
        for (int l = this.XE - 1; l >= n; --l) {
            this.processline(l);
        }
    }
    
    void processline(final int n) {
        int xsize = this.XSIZE;
        int n2 = xsize - this.XSIZE;
        if (n >= 1 && n < this.XE) {
            for (int i = 1; i < this.YSIZE; ++i) {
                final int coloroff = this.coloroff(this.off[n + xsize]);
                if (coloroff < this.decay) {
                    this.off[n + n2] = -16777216;
                }
                else {
                    this.off[n2 + n - (this.rand(3) - 1)] = this.colorit(coloroff - this.rand(this.decay));
                }
                n2 += this.XSIZE;
                xsize += this.XSIZE;
            }
        }
    }
    
    int rand(final int n) {
        return Math.abs(this.rd.nextInt() % n);
    }
    
    public void run() {
        System.err.println("Flame Applet 2 (C) 2000 I-Yuan Chen [6sense.com]");
        this.mouse_init();
        this.draw_init();
        this.begin();
        this.tw_begin();
        this.clearOff();
        while (true) {
            this.process();
            this.tw_process();
            this.checkPressed();
            this.checkOver();
            this.drawflame();
            this.repaint();
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    void smoothBottom() {
        for (int i = 2; i < this.XE - 1; ++i) {
            int n = 0;
            for (int j = -1; j <= 1; ++j) {
                n += this.coloroff(this.off[this.BOTTOM + i + j]);
            }
            this.off[this.BOTTOM + i] = this.colorit(n / 3);
        }
    }
    
    void splash() {
        final int n = this.XE - 1;
        for (int i = 0; i < 25; ++i) {
            final int n2 = this.rand(4) + 2;
            int rand = this.rand(n - n2);
            for (int j = 0; j < n2; ++j) {
                this.off[this.BOTTOM + 1 + rand] = -16777216;
                ++rand;
            }
        }
    }
    
    void splashRandom() {
        final int rand = this.rand(15);
        final int n = this.XE - 1;
        for (int i = 0; i < rand; ++i) {
            final int rand2 = this.rand(n);
            int n2;
            if ((n2 = (this.coloroff(this.off[this.BOTTOM + 1 + rand2]) & 0x7)) < 0) {
                n2 = 0;
            }
            this.off[this.BOTTOM + 1 + rand2] = this.colorit(n2);
        }
    }
    
    public void start() {
        (this.motor = new Thread(this)).setPriority(1);
        this.motor.start();
    }
    
    public void stop() {
        this.art.flush();
        this.motor.stop();
    }
    
    void tw_begin() {
        this.decay = 8;
        this.fade = true;
        final String parameter = this.getParameter("fade");
        if (parameter != null && parameter.toLowerCase().equals("off")) {
            this.fade = false;
        }
        this.stove = true;
        final String parameter2 = this.getParameter("stove");
        if (parameter2 != null && parameter2.toLowerCase().equals("off")) {
            this.stove = false;
            this.decay = 12;
        }
        this.solid = false;
        final String parameter3 = this.getParameter("solid");
        if (parameter3 != null && parameter3.toLowerCase().equals("on")) {
            this.solid = true;
        }
        int int1 = 0;
        final String parameter4 = this.getParameter("decay");
        if (parameter4 != null) {
            int1 = Integer.parseInt(parameter4);
        }
        if (int1 >= 5 && int1 <= 20) {
            this.decay = int1;
        }
        final String parameter5 = this.getParameter("color");
        if (parameter5 != null) {
            this.curpalnum = Integer.parseInt(parameter5);
            if (this.curpalnum < 1 || this.curpalnum > 6) {
                this.curpalnum = 1;
            }
        }
        this.makepal(this.curpalnum);
        if (++this.curpalnum > 6) {
            this.curpalnum = 1;
        }
        String s = "TimesRoman";
        final String parameter6 = this.getParameter("font");
        if (parameter6 != null) {
            if (parameter6.toLowerCase().equals("courier")) {
                s = "Courier";
            }
            else if (parameter6.toLowerCase().equals("dialog")) {
                s = "Dialog";
            }
            else if (parameter6.toLowerCase().equals("dialoginput")) {
                s = "DialogInput";
            }
            else if (parameter6.toLowerCase().equals("helvetica")) {
                s = "Helvetica";
            }
            else if (parameter6.toLowerCase().equals("symbol")) {
                s = "Symbol";
            }
            else {
                s = "TimesRoman";
            }
        }
        int int2 = 40;
        final String parameter7 = this.getParameter("fontsize");
        if (parameter7 != null) {
            int2 = Integer.parseInt(parameter7);
        }
        if (int2 < 10) {
            int2 = 10;
        }
        this.TW_LEN = int2 * (this.XSIZE / (int2 / 8));
        int int3 = 0;
        final String parameter8 = this.getParameter("position");
        if (parameter8 != null) {
            int3 = Integer.parseInt(parameter8);
        }
        final int n = this.YSIZE / 2 + int2 / 3 + int3;
        final Graphics graphics = this.art.getGraphics();
        graphics.setFont(new Font(s, 1, int2));
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        String parameter9 = this.getParameter("text");
        if (parameter9 == null) {
            parameter9 = "Flames+by+IoN CheN";
        }
        final StringTokenizer stringTokenizer = new StringTokenizer(parameter9, "+");
        this.tw_max = stringTokenizer.countTokens();
        this.tw_words = new int[this.tw_max][this.TW_LEN];
        this.tw_count = 0;
        System.err.println("Process Words: " + this.tw_max + " " + this.TW_LEN);
        while (stringTokenizer.hasMoreTokens()) {
            this.clearOff();
            final String nextToken = stringTokenizer.nextToken();
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.XSIZE, this.YSIZE);
            graphics.setColor(Color.white);
            graphics.drawString(nextToken, (this.XSIZE - fontMetrics.stringWidth(nextToken)) / 2, n);
            final PixelGrabber pixelGrabber = new PixelGrabber(this.art, 0, 0, this.XSIZE, this.YSIZE, this.off, 0, this.XSIZE);
            boolean b = true;
            try {
                pixelGrabber.grabPixels();
            }
            catch (InterruptedException ex) {
                b = false;
            }
            if (b) {
                int n2 = 1;
                int n5;
                for (int n3 = 0; n3 < this.SIZE - 1 && n2 < this.TW_LEN - 2; this.tw_words[this.tw_count][n2++] = n5) {
                    int n4;
                    for (n4 = 0; n3 < this.SIZE - 1 && (this.off[n3] & 0xFFFFFF) == 0x0; ++n3, ++n4) {}
                    this.tw_words[this.tw_count][n2++] = n4;
                    for (n5 = 0; n3 < this.SIZE - 1 && (this.off[n3] & 0xFFFFFF) != 0x0; ++n3, ++n5) {}
                }
                System.err.println(String.valueOf(this.tw_count) + " " + nextToken + ": " + n2);
                this.tw_words[this.tw_count][0] = n2 - 2;
                ++this.tw_count;
            }
        }
        this.tw_max = this.tw_count;
        this.tw_count = 0;
        this.tw_timer = System.currentTimeMillis() + 4500L - 1000L;
    }
    
    void tw_process() {
        final long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis < this.tw_timer - 1000L || !this.fade) {
            this.words_draw(this.tw_words[this.tw_count]);
        }
        if (currentTimeMillis > this.tw_timer) {
            if (++this.tw_count >= this.tw_max) {
                this.tw_count = 0;
            }
            this.tw_timer = System.currentTimeMillis() + 4500L;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    void words_draw(final int[] array) {
        int i = 1;
        int n = 0;
        while (i < array[0]) {
            n += array[i++];
            this.count = array[i++];
            Label_0054: {
                break Label_0054;
                int j;
                do {
                    this.off[n++] = this.tw_color[this.rand(50)];
                    j = this.count - 1;
                    this.count = j;
                } while (j >= 0);
            }
        }
    }
}
