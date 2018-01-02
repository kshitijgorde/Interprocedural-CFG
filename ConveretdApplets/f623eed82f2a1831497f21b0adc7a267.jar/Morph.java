import java.awt.Dimension;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.PixelGrabber;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Morph extends Applet implements Runnable
{
    boolean isStandalone;
    int w;
    int h;
    int cnt;
    int cnt1;
    int totalpictures;
    int temp;
    int max;
    int delay;
    int[] buf;
    int[] clearbuf;
    int[][] bufimg;
    int imagewidth;
    int imageheight;
    static int zoomout;
    int[] lsmargin;
    int[] rsmargin;
    int[] ldmargin;
    int[] rdmargin;
    int lscnt;
    int rscnt;
    int[] clear;
    int ldcnt;
    int rdcnt;
    int srcdif;
    int dstdif;
    int tempdif;
    int xdif;
    int xinc;
    int tcnt;
    int bcnt;
    int tsmargin;
    int bsmargin;
    int tdmargin;
    int bdmargin;
    int dsthdif;
    int srchdif;
    int preh;
    int yinc;
    Thread t;
    Image[] img;
    Image img1;
    MediaTracker med;
    PixelGrabber[] pg;
    String[] imagenames;
    String bgcolor;
    String fontcolor;
    String author;
    Graphics offscreengraphics;
    Image offscreenimage;
    
    static {
        Morph.zoomout = 1;
    }
    
    public Morph() {
        this.isStandalone = false;
        this.cnt = 0;
        this.cnt1 = 0;
        this.lscnt = 0;
        this.rscnt = 0;
        this.ldcnt = 0;
        this.rdcnt = 0;
        this.tcnt = 0;
        this.bcnt = 0;
        this.tsmargin = 0;
        this.bsmargin = 0;
        this.tdmargin = 0;
        this.bdmargin = 0;
        this.offscreenimage = null;
    }
    
    public int compositefilter(final int n, final int n2) {
        return 0xFF000000 | (n >> 16 & 0xFF) * n2 / this.max << 16 | (n >> 8 & 0xFF) * n2 / this.max << 8 | (n & 0xFF) * n2 / this.max;
    }
    
    public void destroy() {
        if (this.t != null) {
            this.t = null;
        }
    }
    
    public int filter(final int n, final int n2) {
        return 0xFF000000 | (n >> 16 & 0xFF) * 8 / 100 << 16 | (n >> 8 & 0xFF) * 8 / 100 << 8 | (n & 0xFF) * 8 / 100;
    }
    
    public String getAppletInfo() {
        return "Morph  by LaxmiNarayana.Kota, Java vesion 1.0.2 - 11 Sep '99";
    }
    
    public String getParameter(final String s, final String s2) {
        return this.isStandalone ? System.getProperty(s, s2) : ((this.getParameter(s) != null) ? this.getParameter(s) : s2);
    }
    
    public String[][] getParameterInfo() {
        return null;
    }
    
    public void init() {
        try {
            this.totalpictures = Integer.parseInt(this.getParameter("totalpictures", "0"));
            this.bgcolor = this.getParameter("bgcolor", "0x000000");
            this.fontcolor = this.getParameter("fontcolor", "0x00ff00");
            this.max = Integer.parseInt(this.getParameter("fadecount", "30"));
            this.delay = Integer.parseInt(this.getParameter("delay", "30"));
            this.author = this.getParameter("author", "");
        }
        catch (Exception ex) {}
        if (!this.author.equalsIgnoreCase("Laxminarayana.kota")) {
            System.exit(0);
        }
        this.setBackground(new Color(Integer.parseInt(this.bgcolor.substring(2), 16)));
        this.img = new Image[this.totalpictures];
        this.imagenames = new String[this.totalpictures];
        if (this.totalpictures > 0) {
            try {
                for (int i = 0; i < this.totalpictures; ++i) {
                    this.imagenames[i] = this.getParameter("imagenames[" + i + "]", "");
                }
            }
            catch (Exception ex2) {}
        }
        for (int j = 0; j < this.totalpictures; ++j) {
            this.img[j] = this.getImage(this.getCodeBase(), this.imagenames[j]);
        }
        this.med = new MediaTracker(this);
        for (int k = 0; k < this.img.length; ++k) {
            this.med.addImage(this.img[k], 0);
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.med.statusAll(false) == 8) {
            graphics.drawImage(this.img1, 0, 0, this);
        }
        else {
            graphics.setColor(new Color(Integer.parseInt(this.fontcolor.substring(2), 16)));
            graphics.drawString("Loading Images Please wait...", 20, 100);
        }
    }
    
    public void run() {
        Label_0326: {
            try {
                this.med.waitForAll();
                this.imagewidth = this.img[0].getWidth(null);
                this.imageheight = this.img[0].getHeight(null);
                this.buf = new int[this.imagewidth * this.imageheight];
                this.clearbuf = new int[this.imagewidth * this.imageheight];
                this.lsmargin = new int[this.imageheight];
                this.rsmargin = new int[this.imageheight];
                this.ldmargin = new int[this.imageheight];
                this.rdmargin = new int[this.imageheight];
                this.clear = new int[this.imageheight];
                for (int i = 0; i < this.imageheight; ++i) {
                    for (int j = 0; j < this.imagewidth; ++j) {
                        this.buf[this.imagewidth * i + j] = 16777215;
                        this.clearbuf[this.imagewidth * i + j] = 16777215;
                    }
                }
                for (int k = 0; k < this.imageheight; ++k) {
                    this.clear[k] = 0;
                }
                this.bufimg = new int[this.img.length][this.imagewidth * this.imageheight];
                this.clear = new int[this.imageheight];
                this.pg = new PixelGrabber[this.img.length];
                for (int l = 0; l < this.img.length; ++l) {
                    (this.pg[l] = new PixelGrabber(this.img[l], 0, 0, this.imagewidth, this.imageheight, this.bufimg[l], 0, this.imagewidth)).grabPixels();
                }
                break Label_0326;
            }
            catch (InterruptedException ex) {
                this.getAppletContext().showStatus("Loading images is failed");
            }
            try {
                while (true) {
                    ++this.cnt;
                    if (this.cnt > this.max) {
                        ++this.cnt1;
                        if (this.cnt1 > this.img.length - 1) {
                            this.cnt1 = 0;
                        }
                        this.cnt = 0;
                    }
                    this.temp = ((this.cnt1 < this.img.length - 1) ? (1 + this.cnt1) : 0);
                    System.arraycopy(this.clearbuf, 0, this.buf, 0, this.imagewidth * this.imageheight);
                    this.tcnt = 0;
                    if (this.cnt >= 0 && this.cnt <= 1) {
                        for (int tsmargin = 0; tsmargin < this.imageheight; ++tsmargin) {
                            this.lscnt = 0;
                            this.rscnt = 0;
                            for (int n = 0; n < Morph.zoomout * this.imagewidth; ++n) {
                                if (this.lscnt == 0 && n > 0 && this.filter(this.bufimg[this.cnt1][this.imagewidth * tsmargin + n * this.imagewidth / (Morph.zoomout * this.imagewidth)], 11) != this.filter(this.bufimg[this.cnt1][this.imagewidth * tsmargin], 11)) {
                                    this.lsmargin[tsmargin] = n / Morph.zoomout;
                                    ++this.lscnt;
                                    if (this.tcnt == 0) {
                                        this.tsmargin = tsmargin;
                                        ++this.tcnt;
                                    }
                                    else {
                                        ++this.tcnt;
                                    }
                                }
                            }
                        }
                        this.bsmargin = this.tcnt + this.tsmargin;
                        this.tcnt = 0;
                        for (int n2 = 0; n2 < this.imageheight; ++n2) {
                            this.rscnt = 0;
                            for (int n3 = 0; n3 < Morph.zoomout * this.imagewidth; ++n3) {
                                if (this.rscnt == 0 && n3 < Morph.zoomout * this.imagewidth && this.filter(this.bufimg[this.cnt1][this.imagewidth * n2 + (Morph.zoomout * this.imagewidth - 1 - n3) * this.imagewidth / (Morph.zoomout * this.imagewidth)], 11) != this.filter(this.bufimg[this.cnt1][this.imagewidth * n2 + this.imagewidth - 1], 11)) {
                                    this.rsmargin[n2] = n3 / Morph.zoomout;
                                    ++this.rscnt;
                                }
                            }
                        }
                        for (int tdmargin = 0; tdmargin < this.imageheight; ++tdmargin) {
                            this.ldcnt = 0;
                            this.rdcnt = 0;
                            for (int n4 = 0; n4 < Morph.zoomout * this.imagewidth; ++n4) {
                                if (this.ldcnt == 0 && n4 > 0 && this.filter(this.bufimg[this.temp][this.imagewidth * tdmargin + n4 * this.imagewidth / (Morph.zoomout * this.imagewidth)], 11) != this.filter(this.bufimg[this.temp][this.imagewidth * tdmargin], 11)) {
                                    this.ldmargin[tdmargin] = n4 / Morph.zoomout;
                                    ++this.ldcnt;
                                    if (this.tcnt == 0) {
                                        this.tdmargin = tdmargin;
                                        ++this.tcnt;
                                    }
                                    else {
                                        ++this.tcnt;
                                    }
                                }
                            }
                        }
                        this.bdmargin = this.tcnt + this.tdmargin;
                        this.tcnt = 0;
                        for (int n5 = 0; n5 < this.imageheight; ++n5) {
                            this.rdcnt = 0;
                            for (int n6 = 0; n6 < Morph.zoomout * this.imagewidth; ++n6) {
                                if (this.rdcnt == 0 && n6 < Morph.zoomout * this.imagewidth && this.filter(this.bufimg[this.temp][this.imagewidth * n5 + (Morph.zoomout * this.imagewidth - 1 - n6) * this.imagewidth / (Morph.zoomout * this.imagewidth)], 11) != this.filter(this.bufimg[this.temp][this.imagewidth * n5 + this.imagewidth - 1], 11)) {
                                    this.rdmargin[n5] = n6 / Morph.zoomout;
                                    ++this.rdcnt;
                                }
                            }
                        }
                    }
                    for (int n7 = 0; n7 < this.imageheight; ++n7) {
                        this.dsthdif = this.bdmargin - this.tdmargin;
                        this.srchdif = this.bsmargin - this.tsmargin;
                        this.yinc = this.tsmargin - (this.tsmargin - this.tdmargin) * this.cnt / this.max;
                        this.preh = this.srchdif - (this.srchdif - this.dsthdif) * this.cnt / this.max;
                        if (n7 >= this.yinc && n7 <= this.yinc + this.preh) {
                            for (int n8 = 0; n8 < this.imagewidth; ++n8) {
                                final int n9 = this.tsmargin + (n7 - this.yinc) * this.srchdif / this.preh;
                                final int n10 = this.tdmargin + (n7 - this.yinc) * this.dsthdif / this.preh;
                                this.dstdif = this.imagewidth - 1 - this.rdmargin[n10] - this.ldmargin[n10];
                                this.srcdif = this.imagewidth - 1 - this.rsmargin[n9] - this.lsmargin[n9];
                                this.xdif = this.lsmargin[n9] - this.ldmargin[n10];
                                this.xinc = this.lsmargin[n9] - this.xdif * this.cnt / this.max;
                                this.tempdif = this.srcdif - (this.srcdif - this.dstdif) * this.cnt / this.max;
                                if (n8 > this.xinc && n8 <= this.xinc + this.tempdif) {
                                    this.buf[this.imagewidth * n7 + n8] = (0xFF000000 | this.compositefilter(this.bufimg[this.cnt1][this.imagewidth * n9 + this.lsmargin[n9] + (n8 - this.xinc) * this.srcdif / this.tempdif], this.max - this.cnt) + this.compositefilter(this.bufimg[this.temp][this.imagewidth * n10 + this.ldmargin[n10] + (n8 - this.xinc) * this.dstdif / this.tempdif], this.cnt));
                                }
                                else {
                                    this.buf[this.imagewidth * n7 + n8] = -16777216;
                                }
                            }
                        }
                    }
                    this.img1 = this.createImage(new MemoryImageSource(this.imagewidth, this.imageheight, this.buf, 0, this.imagewidth));
                    if (this.cnt == this.max) {
                        Thread.sleep(1000L);
                    }
                    else {
                        Thread.sleep(this.delay);
                    }
                    this.repaint();
                }
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    public void start() {
        (this.t = new Thread(this)).start();
    }
    
    public void stop() {
        this.t.interrupt();
    }
    
    public synchronized void update(final Graphics graphics) {
        final Dimension size = this.getSize();
        if (this.offscreenimage == null) {
            this.offscreenimage = this.createImage(size.width, size.height);
            this.offscreengraphics = this.offscreenimage.getGraphics();
        }
        this.offscreengraphics.setColor(this.getBackground());
        this.offscreengraphics.fillRect(0, 0, size.width, size.height);
        this.paint(this.offscreengraphics);
        graphics.drawImage(this.offscreenimage, 0, 0, this);
    }
}
