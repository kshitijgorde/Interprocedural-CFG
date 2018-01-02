import java.net.URL;
import java.awt.Event;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.util.Date;
import java.awt.Component;
import java.awt.image.ImageObserver;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.PixelGrabber;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SlideOnDemandInterleave extends Applet implements Runnable
{
    Thread t;
    boolean suspended;
    Image oi;
    Image vimage;
    MediaTracker tracker;
    PixelGrabber imagegrabber;
    Graphics og;
    int width;
    int height;
    int bgr;
    int bgg;
    int bgb;
    Color bg;
    int total;
    int[] vpix;
    int textfontsize;
    int textfontstyle;
    String textfont;
    Color textcolor;
    int fontw;
    int fonth;
    int fontd;
    Font tf;
    String[][] it;
    int[] posx;
    int[] posy;
    boolean loaded;
    boolean firsttime;
    int delay;
    int border;
    Color bordercolor;
    boolean borderout;
    boolean copyright;
    int fit;
    int id;
    int id2;
    int framestep;
    int wstart;
    int vwstart;
    int hstart;
    int vhstart;
    int wstart2;
    int vwstart2;
    int hstart2;
    int vhstart2;
    int pos;
    int dpos;
    int direction;
    int band;
    boolean ran;
    boolean registered;
    int loading;
    int maxrot;
    int rot;
    boolean go;
    int nIntoID;
    Image[] img;
    int[][] pix;
    int[] imgw;
    int[] imgh;
    int nItem;
    int nXmid;
    int nYmid;
    int nBlock;
    
    public SlideOnDemandInterleave() {
        this.t = null;
        this.suspended = false;
        this.vimage = null;
        this.width = 0;
        this.height = 0;
        this.textfontsize = 12;
        this.textfontstyle = 0;
        this.textfont = "TimesRoman";
        this.textcolor = Color.red;
        this.fontw = 10;
        this.fonth = 10;
        this.fontd = 2;
        this.loaded = false;
        this.firsttime = true;
        this.delay = 100;
        this.border = 0;
        this.borderout = true;
        this.copyright = false;
        this.fit = 1;
        this.id = 0;
        this.id2 = 0;
        this.framestep = 1;
        this.pos = 0;
        this.dpos = 5;
        this.direction = 0;
        this.band = 3;
        this.ran = false;
        this.registered = false;
        this.loading = 1;
        this.maxrot = 0;
        this.rot = 0;
        this.go = true;
        this.nIntoID = 1;
        this.nItem = 0;
        this.nXmid = 0;
        this.nYmid = 0;
        this.nBlock = 0;
    }
    
    public void init() {
        super.init();
        final String parameter = this.getParameter("regcode");
        parameter.trim();
        parameter.toUpperCase();
        final char c = (char)(parameter.charAt(parameter.length() - 1) - '0');
        if (c + '\u0004' < parameter.length()) {
            final int intValue = new Integer(parameter.substring(c, c + '\u0003'));
            int intValue2 = new Integer(parameter.substring(parameter.length() - 4, parameter.length() - 1));
            final String upperCase = this.getDocumentBase().getHost().toUpperCase();
            char c2 = '\0';
            int n = 0;
            if (upperCase.length() > 0) {
                for (int i = upperCase.length() - 1; i >= 0; --i) {
                    if (upperCase.charAt(i) == '.') {
                        if (++n > 1) {
                            i = -1;
                        }
                    }
                    else if (n < 2 && upperCase.charAt(i) >= 'A' && upperCase.charAt(i) <= 'Z') {
                        c2 += (char)(upperCase.charAt(i) - 'A' + '\n');
                    }
                    else if (n < 2 && upperCase.charAt(i) >= '0' && upperCase.charAt(i) <= '9') {
                        c2 += (char)(upperCase.charAt(i) - '0');
                    }
                }
            }
            else {
                intValue2 = 0;
            }
            if (intValue == '\u01c6' && intValue2 == intValue * c2 % '\u03e8') {
                this.registered = true;
            }
        }
        this.loading = Integer.parseInt(this.getParameter("loading"));
        final int[] int1 = this.parseInt(this.getParameter("bgcolor"));
        this.bgr = int1[0];
        this.bgg = int1[1];
        this.bgb = int1[2];
        this.bg = new Color(this.bgr, this.bgg, this.bgb);
        this.delay = 1000 * Integer.parseInt(this.getParameter("pause"));
        this.maxrot = Integer.parseInt(this.getParameter("nrot"));
        this.rot = 0;
        this.go = true;
        this.dpos = Integer.parseInt(this.getParameter("interleavespeed"));
        this.direction = Integer.parseInt(this.getParameter("direction"));
        if (this.direction > 2 || this.direction <= 0) {
            this.ran = true;
            this.direction = (int)(100.0 * Math.random()) % 2 + 1;
        }
        this.band = Integer.parseInt(this.getParameter("bandwidth"));
        final String[] parse = this.parse(this.getParameter("border"));
        this.border = this.parseInt(parse[0])[0];
        final int[] int2 = this.parseInt(parse[1]);
        this.bordercolor = new Color(int2[0], int2[1], int2[2]);
        if (parse[2].trim().toUpperCase().startsWith("OUT")) {
            this.borderout = true;
        }
        else {
            this.borderout = false;
        }
        final String[] parse2 = this.parse(this.getParameter("text"));
        this.textfont = parse2[0];
        this.textfontstyle = this.parseInt(parse2[1])[0];
        this.textfontsize = this.parseInt(parse2[2])[0];
        final int[] int3 = this.parseInt(parse2[3]);
        this.textcolor = new Color(int3[0], int3[1], int3[2]);
        this.fit = Integer.parseInt(this.getParameter("canvasfit"));
        this.total = Integer.parseInt(this.getParameter("total"));
        this.pix = new int[2][];
        this.img = new Image[2];
        this.imgw = new int[2];
        this.imgh = new int[2];
        this.it = new String[this.total][4];
        this.posx = new int[this.total];
        this.posy = new int[this.total];
        for (int j = 0; j < this.total; ++j) {
            this.showStatus("Reading item " + j + "...");
            final String[] parse3 = this.parse(this.getParameter("item" + j));
            for (int k = 0; k < 4; ++k) {
                this.it[j][k] = parse3[k].trim();
            }
            final int[] int4 = this.parseInt(parse3[2]);
            this.posx[j] = int4[0];
            this.posy[j] = int4[1];
        }
        this.width = this.size().width;
        this.height = this.size().height;
        this.resize(this.width, this.height);
        this.oi = this.createImage(this.width, this.height);
        this.og = this.oi.getGraphics();
        this.vpix = new int[this.width * this.height];
        this.nXmid = this.width / 2;
        this.nYmid = this.height / 2;
        this.nItem = 0;
    }
    
    String[] parse(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, "|");
        final String[] array = new String[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = stringTokenizer.nextToken();
        }
        return array;
    }
    
    int[] parseInt(final String s) {
        final StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
        final int[] array = new int[stringTokenizer.countTokens()];
        for (int i = 0; i < array.length; ++i) {
            array[i] = Integer.parseInt(stringTokenizer.nextToken());
        }
        return array;
    }
    
    public void start() {
        if (this.t == null) {
            (this.t = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.t != null && this.t.isAlive()) {
            this.t.stop();
        }
        this.t = null;
        System.gc();
    }
    
    public void destroy() {
        if (this.oi != null) {
            this.oi.flush();
        }
        this.oi = null;
        if (this.og != null) {
            this.og.dispose();
        }
        this.og = null;
        System.gc();
    }
    
    private void waitForImage(final int n) {
        while (!this.tracker.checkID(n, true)) {
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex) {}
        }
    }
    
    void loadImage(final int n) {
        this.showStatus("Loading image '" + this.it[n][0] + "'...");
        this.img[this.nIntoID] = null;
        this.img[this.nIntoID] = this.getImage(this.getDocumentBase(), this.it[n][0]);
        this.tracker.addImage(this.img[this.nIntoID], n);
        this.waitForImage(n);
        this.imgw[this.nIntoID] = this.img[this.nIntoID].getWidth(this);
        this.imgh[this.nIntoID] = this.img[this.nIntoID].getHeight(this);
        this.pix[this.nIntoID] = null;
        this.pix[this.nIntoID] = new int[this.imgw[this.nIntoID] * this.imgh[this.nIntoID]];
        final PixelGrabber pixelGrabber = new PixelGrabber(this.img[this.nIntoID], 0, 0, this.imgw[this.nIntoID], this.imgh[this.nIntoID], this.pix[this.nIntoID], 0, this.imgw[this.nIntoID]);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    public void run() {
        if (!this.loaded) {
            this.tracker = new MediaTracker(this);
            this.loadImage(this.nIntoID = 0);
            this.loadImage(this.nIntoID = 1);
            this.id = 1;
            this.id2 = 0;
            this.nIntoID = 0;
            this.loaded = true;
        }
        this.program();
        System.gc();
        final Graphics graphics = this.getGraphics();
        int n = 0;
        while (Thread.currentThread() == this.t) {
            this.paint(graphics);
            try {
                this.pos += this.dpos;
                if (this.framestep == 0) {
                    this.nItem = this.id;
                }
                boolean b = false;
                switch (this.direction) {
                    case 1: {
                        if (this.pos > this.nXmid) {
                            b = true;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        if (this.pos > this.nYmid) {
                            b = true;
                            break;
                        }
                        break;
                    }
                }
                if (b) {
                    this.nItem = this.id;
                }
                else {
                    this.nItem = this.id2;
                }
                if (this.direction == 1) {
                    if (this.pos == this.dpos + this.width) {
                        this.pos = 0;
                        if (this.ran) {
                            this.direction = (int)(100.0 * Math.random()) % 2 + 1;
                        }
                        this.id = (this.id + this.framestep) % this.total;
                        this.id2 = (this.id - this.framestep + this.total) % this.total;
                        Thread.sleep(this.delay);
                        this.loadImage(this.id);
                        this.nIntoID = 1 - this.nIntoID;
                        if (this.maxrot > 0 && this.id == this.total - 1) {
                            ++this.rot;
                            if (this.rot == this.maxrot && this.go) {
                                this.go = false;
                            }
                        }
                    }
                    else {
                        if (this.pos > this.width) {
                            this.pos = this.width;
                        }
                        Thread.sleep(50L);
                    }
                }
                else if (this.pos == this.dpos + this.height) {
                    this.pos = 0;
                    if (this.ran) {
                        this.direction = (int)(100.0 * Math.random()) % 2 + 1;
                    }
                    this.id = (this.id + this.framestep) % this.total;
                    this.id2 = (this.id - this.framestep + this.total) % this.total;
                    Thread.sleep(this.delay);
                    this.loadImage(this.id);
                    this.nIntoID = 1 - this.nIntoID;
                    if (this.maxrot > 0 && this.id == this.total - 1) {
                        ++this.rot;
                        if (this.rot == this.maxrot && this.go) {
                            this.go = false;
                        }
                    }
                }
                else {
                    if (this.pos > this.height) {
                        this.pos = this.height;
                    }
                    Thread.sleep(50L);
                }
                if (this.go) {
                    this.program();
                }
                else {
                    this.id = this.total - 1;
                    this.id2 = this.total - 1;
                    this.framestep = 0;
                }
                if (n++ > 1000) {
                    n = 0;
                    System.gc();
                }
            }
            catch (InterruptedException ex) {}
            if (!this.registered) {
                final Date date = new Date();
                if (date.getMinutes() % 8 == 0 && date.getSeconds() < 15) {
                    this.nBlock = 15 - date.getSeconds();
                }
                else {
                    this.nBlock = 0;
                }
            }
        }
    }
    
    public void program() {
        float n = 1.0f;
        float n2 = 1.0f;
        float n3 = 1.0f;
        float n4 = 1.0f;
        final float n5 = this.width / 2.0f;
        final float n6 = this.height / 2.0f;
        this.id2 = (this.id - this.framestep + this.total) % this.total;
        final int nIntoID = this.nIntoID;
        int n7 = 1 - nIntoID;
        if (this.framestep == 0) {
            n7 = nIntoID;
        }
        if (this.fit == 1) {
            n = this.width / this.imgw[nIntoID];
            n2 = this.height / this.imgh[nIntoID];
            n3 = this.width / this.imgw[n7];
            n4 = this.height / this.imgh[n7];
        }
        final float n8 = this.imgw[nIntoID] / 2.0f;
        final float n9 = this.imgh[nIntoID] / 2.0f;
        final float n10 = this.imgw[n7] / 2.0f;
        final float n11 = this.imgh[n7] / 2.0f;
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                int n12;
                int n13;
                int n14;
                if (this.direction == 1) {
                    if (j <= this.pos && i / this.band % 2 == 0) {
                        n12 = (int)((i - n6) / n4 + n11);
                        n13 = (int)((j - n5) / n3 + n10 + this.width - this.pos);
                        n14 = n7;
                    }
                    else if (j >= this.width - this.pos && i / this.band % 2 == 1) {
                        n12 = (int)((i - n6) / n4 + n11);
                        n13 = (int)((j - n5) / n3 + n10 - this.width + this.pos);
                        n14 = n7;
                    }
                    else {
                        n12 = (int)((i - n6) / n2 + n9);
                        n13 = (int)((j - n5) / n + n8);
                        n14 = nIntoID;
                    }
                }
                else if (i <= this.pos && j / this.band % 2 == 0) {
                    n13 = (int)((j - n5) / n3 + n10);
                    n12 = (int)((i - n6) / n4 + n11 + this.height - this.pos);
                    n14 = n7;
                }
                else if (i >= this.height - this.pos && j / this.band % 2 == 1) {
                    n13 = (int)((j - n5) / n3 + n10);
                    n12 = (int)((i - n6) / n4 + n11 - this.height + this.pos);
                    n14 = n7;
                }
                else {
                    n13 = (int)((j - n5) / n + n8);
                    n12 = (int)((i - n6) / n2 + n9);
                    n14 = nIntoID;
                }
                final int n15 = n12 * this.imgw[n14] + n13;
                int bgr;
                int bgg;
                int bgb;
                if (n13 < 0 || n13 > this.imgw[n14] - 1 || n12 < 0 || n12 > this.imgh[n14] - 1) {
                    bgr = this.bgr;
                    bgg = this.bgg;
                    bgb = this.bgb;
                }
                else {
                    bgr = (this.pix[n14][n15] & 0xFF0000) >> 16;
                    bgg = (this.pix[n14][n15] & 0xFF00) >> 8;
                    bgb = (this.pix[n14][n15] & 0xFF);
                }
                this.vpix[i * this.width + j] = new Color(bgr, bgg, bgb).getRGB();
            }
        }
        this.vimage = this.createImage(new MemoryImageSource(this.width, this.height, this.vpix, 0, this.width));
    }
    
    public synchronized void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public synchronized void paint(final Graphics graphics) {
        if (!this.loaded) {
            return;
        }
        if (this.nBlock > 0) {
            this.og.setColor(Color.black);
            this.og.fillRect(0, 0, this.width, this.height);
            this.og.setColor(Color.white);
            final String[] array = { "\"SlideOnDemandFade\"", "by", "www.thejmaker.com", "Click to register: " + this.nBlock };
            int n = this.height / 2 - 30;
            for (int i = 0; i < 4; ++i) {
                if (i == 0) {
                    this.og.setFont(new Font("Helvetica", 1, 14));
                }
                else {
                    this.og.setFont(new Font("Helvetica", 1, 11));
                }
                this.og.drawString(array[i], (this.width - this.og.getFontMetrics().stringWidth(array[i])) / 2, n);
                if (i == 0) {
                    n += 16;
                }
                else if (i == 2) {
                    this.og.setColor(Color.yellow);
                    n += 20;
                }
                else {
                    n += 13;
                }
            }
            graphics.drawImage(this.oi, 0, 0, this);
            return;
        }
        if (this.firsttime) {
            this.og.setColor(this.textcolor);
            if (this.textfontstyle == 1) {
                this.tf = new Font(this.textfont, 1, this.textfontsize);
            }
            else if (this.textfontstyle == 2) {
                this.tf = new Font(this.textfont, 2, this.textfontsize);
            }
            else if (this.textfontstyle == 3) {
                this.tf = new Font(this.textfont, 3, this.textfontsize);
            }
            else {
                this.tf = new Font(this.textfont, 0, this.textfontsize);
            }
            this.og.setFont(this.tf);
            this.fonth = this.og.getFontMetrics().getHeight();
            this.fontd = this.og.getFontMetrics().getDescent();
            this.firsttime = false;
        }
        if (!this.it[this.nItem][3].trim().toUpperCase().startsWith("NONE")) {
            this.showStatus(this.it[this.nItem][3]);
        }
        else {
            this.showStatus("");
        }
        if (this.vimage != null) {
            this.og.drawImage(this.vimage, 0, 0, this);
        }
        this.og.setFont(this.tf);
        this.fontw = this.og.getFontMetrics().stringWidth(this.it[this.nItem][1]);
        final int n2 = this.posx[this.nItem];
        final int n3 = this.posy[this.nItem];
        if (!this.it[this.nItem][1].trim().toUpperCase().startsWith("NONE")) {
            this.og.setColor(Color.black);
            this.og.drawString(this.it[this.nItem][1], n2 - 1, n3);
            this.og.drawString(this.it[this.nItem][1], n2 + 1, n3);
            this.og.drawString(this.it[this.nItem][1], n2, n3 - 1);
            this.og.drawString(this.it[this.nItem][1], n2, n3 + 1);
            this.og.setColor(this.textcolor);
            this.og.drawString(this.it[this.nItem][1], n2, n3);
        }
        if (this.border != 0) {
            this.og.setColor(this.bordercolor);
            for (int j = 0; j < this.border; ++j) {
                this.og.draw3DRect(j, j, this.width - 1 - j * 2, this.height - 1 - j * 2, this.borderout);
            }
        }
        if (!this.registered && this.copyright) {
            this.og.setFont(new Font("Helvetica", 1, 10));
            final String s = "SlideOnDemandInterleave (C) thejmaker.com 2003";
            final int n4 = this.width - this.og.getFontMetrics().stringWidth(s) - 3 - this.border;
            final int n5 = this.height - 3 - this.border;
            this.og.setColor(Color.blue);
            this.og.drawString(s, n4, n5 - 1);
            this.og.drawString(s, n4, n5 + 1);
            this.og.drawString(s, n4 - 1, n5);
            this.og.drawString(s, n4 + 1, n5);
            this.og.setColor(Color.cyan);
            this.og.drawString(s, n4, n5);
        }
        graphics.drawImage(this.oi, 0, 0, this);
    }
    
    public boolean mouseExit(final Event event, final int n, final int n2) {
        if (!this.registered) {
            this.showStatus("SlideOnDemandInterleave (C) thejmaker.com 2003");
        }
        else {
            this.showStatus("");
        }
        this.copyright = false;
        this.framestep = 1;
        this.repaint();
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.copyright = true;
        this.framestep = 0;
        if (!this.it[this.nItem][3].trim().toUpperCase().startsWith("NONE")) {
            this.showStatus(this.it[this.nItem][3]);
        }
        else {
            this.showStatus("");
        }
        this.repaint();
        return true;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.nBlock > 0) {
            try {
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), "http://www.thejmaker.com/pricing.html"), "_blank");
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        this.repaint();
        this.framestep = 0;
        if (!this.it[this.nItem][3].trim().toUpperCase().startsWith("NONE")) {
            try {
                this.showStatus("Connecting " + this.it[this.nItem][3] + "...");
                this.getAppletContext().showDocument(new URL(this.getDocumentBase(), this.it[this.nItem][3]));
            }
            catch (Exception ex2) {
                ex2.printStackTrace();
            }
        }
        return true;
    }
}
