import java.awt.image.PixelGrabber;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.Component;
import java.util.Random;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class loadsnow
{
    Applet app;
    MemoryImageSource ArtSource;
    Image SourceImg;
    Image TempImage;
    private snow[] thesnow;
    private Image[] img;
    private int[] buffer;
    private int[] newbuf;
    private int format;
    private int grabbed;
    private int imgCount;
    private int imgLoaded;
    private int width;
    private int height;
    private int bsize;
    private int cur;
    private MediaTracker mt;
    public Color bgcolor;
    private int fadeWidth;
    private int fadePercent;
    private int fadeStep;
    private int fadeAdd;
    private long nextcycle;
    private long delayInterval;
    public Random rd;
    public boolean isGrabbing;
    int maxsnow;
    int wind;
    private boolean fgExist;
    
    loadsnow(final Applet mainApp, final Image timg, final int w, final int h) {
        this.TempImage = timg;
        this.app = mainApp;
        this.rd = new Random();
        this.width = w;
        this.height = h;
        this.bsize = w * h;
        this.isGrabbing = false;
        this.bgcolor = Color.black;
        String tempstr = this.app.getParameter("bgcolor");
        if (tempstr != null) {
            this.bgcolor = new Color(Integer.parseInt(tempstr, 16));
        }
        this.format = 1;
        tempstr = this.app.getParameter("format");
        if (tempstr != null && tempstr.toLowerCase().equals("stretch")) {
            this.format = 0;
        }
        this.delayInterval = 1000L;
        tempstr = this.app.getParameter("delay");
        if (tempstr != null) {
            this.delayInterval = this.convertStringLong(tempstr);
            if (this.delayInterval < 0L) {
                this.delayInterval = 0L;
            }
        }
        this.nextcycle = System.currentTimeMillis() + 3000L;
        this.imgCount = 0;
        this.imgLoaded = 0;
        this.cur = 0;
        tempstr = this.app.getParameter("maximages");
        if (tempstr != null) {
            this.imgCount = this.convertString(tempstr);
            if (this.imgCount > 0) {
                System.err.println("Init ImgCount = " + this.imgCount);
                this.img = new Image[this.imgCount];
                int keep = 0;
                this.mt = new MediaTracker(this.app);
                for (int i = 1; i <= this.imgCount; ++i) {
                    tempstr = this.app.getParameter("image" + i);
                    if (tempstr != null) {
                        this.img[keep] = this.app.getImage(this.app.getCodeBase(), tempstr);
                        this.mt.addImage(this.img[keep], keep);
                        ++keep;
                    }
                }
                this.imgCount = keep;
                System.err.println("Final ImgCount = " + this.imgCount);
            }
        }
        this.buffer = new int[this.bsize];
        this.newbuf = new int[this.bsize];
        (this.ArtSource = new MemoryImageSource(this.width, this.height, this.buffer, 0, this.width)).setAnimated(true);
        this.SourceImg = this.app.createImage(this.ArtSource);
        this.grabbed = 0;
        final boolean b = false;
        this.fadeWidth = (b ? 1 : 0);
        this.fadePercent = (b ? 1 : 0);
        this.fadeStep = (b ? 1 : 0);
        this.fadeAdd = (b ? 1 : 0);
        this.snowBegin(this.app);
        final int col = this.bgcolor.getRGB();
        for (int i = 0; i < this.bsize; ++i) {
            this.buffer[i] = (this.newbuf[i] = col);
        }
    }
    
    int allLoaded() {
        if (this.imgLoaded < this.imgCount) {
            final int status = this.mt.statusID(this.imgLoaded, true);
            if (status != 1) {
                if (status == 4) {
                    this.img[this.imgLoaded] = null;
                    System.err.println("Image" + this.imgLoaded + " has an Error.");
                }
                else {
                    System.err.println("Image" + this.imgLoaded + " has been Loaded." + this.img[this.imgLoaded].getWidth(null) + ":" + this.img[this.imgLoaded].getHeight(null));
                }
                if (++this.imgLoaded >= this.imgCount) {
                    this.mt = null;
                    System.gc();
                }
            }
        }
        return this.imgLoaded;
    }
    
    int convertString(final String s) {
        int i = 0;
        try {
            i = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            i = 0;
        }
        return i;
    }
    
    long convertStringLong(final String s) {
        long i = 0L;
        try {
            i = Long.parseLong(s);
        }
        catch (NumberFormatException ex) {
            i = 0L;
        }
        return i;
    }
    
    void copyNewBuffer() {
        System.arraycopy(this.newbuf, 0, this.buffer, 0, this.bsize);
    }
    
    void doFadeSide() {
        if (++this.fadePercent >= 30) {
            this.fadeCycle();
        }
        else {
            final double fadeStart = 1.0 / (30.0 - this.fadePercent);
            int xstart = 0;
            for (int y = this.height; y > 0; --y) {
                final double fade = fadeStart;
                int i = xstart;
                int j = xstart + this.width - 1;
                for (int x = this.width >> 1; x > 0; --x) {
                    int newcol = this.buffer[i];
                    int r = newcol >> 16 & 0xFF;
                    int g = newcol >> 8 & 0xFF;
                    int b = newcol & 0xFF;
                    newcol = this.newbuf[i];
                    r -= (int)((r - (newcol >> 16 & 0xFF)) * fade);
                    g -= (int)((g - (newcol >> 8 & 0xFF)) * fade);
                    b -= (int)((b - (newcol & 0xFF)) * fade);
                    this.buffer[i] = (0xFF000000 | r << 16 | g << 8 | b);
                    newcol = this.buffer[j];
                    r = (newcol >> 16 & 0xFF);
                    g = (newcol >> 8 & 0xFF);
                    b = (newcol & 0xFF);
                    newcol = this.newbuf[j];
                    r -= (int)((r - (newcol >> 16 & 0xFF)) * fade);
                    g -= (int)((g - (newcol >> 8 & 0xFF)) * fade);
                    b -= (int)((b - (newcol & 0xFF)) * fade);
                    this.buffer[j] = (0xFF000000 | r << 16 | g << 8 | b);
                    ++i;
                    --j;
                }
                xstart += this.width;
            }
        }
    }
    
    void draw(final Graphics g) {
        if (this.allLoaded() > 0) {
            if (this.grabbed == 0) {
                ++this.grabbed;
                this.grabImage(this.buffer, this.cur);
                this.drawSnow();
                this.ArtSource.newPixels(0, 0, this.width, this.height);
                g.drawImage(this.SourceImg, 0, 0, null);
                this.eraseSnow();
            }
            else if (this.imgLoaded > 1 || this.imgCount == 1) {
                if (this.fadePercent == 0) {
                    this.grabImage(this.newbuf, this.getNext(this.cur));
                    this.fadePercent = 1;
                    this.isGrabbing = false;
                }
                else if (this.nextcycle < System.currentTimeMillis()) {
                    this.doFadeSide();
                }
                this.drawSnow();
                this.ArtSource.newPixels(0, 0, this.width, this.height);
                g.drawImage(this.SourceImg, 0, 0, null);
                this.eraseSnow();
            }
        }
    }
    
    void drawSnow() {
        for (int i = 0; i < this.maxsnow; ++i) {
            this.thesnow[i].draw(this.buffer);
        }
    }
    
    void eraseSnow() {
        for (int i = this.maxsnow - 1; i >= 0; --i) {
            this.thesnow[i].erase(this.buffer, this.rd, Math.abs(this.rd.nextInt()) % this.wind);
        }
    }
    
    void fadeCycle() {
        this.copyNewBuffer();
        this.cur = this.getNext(this.cur);
        this.fadeStep = 0;
        this.fadePercent = 0;
        this.fadeWidth = 0;
        this.fadeAdd = 0;
        this.nextcycle = System.currentTimeMillis() + this.delayInterval;
    }
    
    int getNext(final int i) {
        int next = i;
        do {
            if (++next >= this.imgLoaded) {
                next = 0;
            }
        } while (this.img[next] == null && next != i);
        return next;
    }
    
    boolean grabImage(final int[] buf, final int num) {
        final Graphics g = this.TempImage.getGraphics();
        this.isGrabbing = true;
        boolean exist = true;
        if (this.img[num] != null) {
            switch (this.format) {
                case 1: {
                    g.setColor(this.bgcolor);
                    g.fillRect(0, 0, this.width, this.height);
                    g.drawImage(this.img[num], this.width - this.img[num].getWidth(null) >> 1, this.height - this.img[num].getHeight(null) >> 1, null);
                    break;
                }
                default: {
                    g.drawImage(this.img[num], 0, 0, this.width, this.height, null);
                    break;
                }
            }
            try {
                final PixelGrabber pg = new PixelGrabber(this.TempImage, 0, 0, this.width, this.height, buf, 0, this.width);
                pg.grabPixels();
            }
            catch (InterruptedException ex) {
                exist = false;
            }
        }
        else {
            exist = false;
        }
        g.dispose();
        System.gc();
        return exist;
    }
    
    int rand(final int n) {
        return Math.abs(this.rd.nextInt() % n);
    }
    
    void setFgExist(final boolean v) {
        this.fgExist = v;
    }
    
    void snowBegin(final Applet app) {
        this.wind = 5;
        String tempstr = app.getParameter("wind");
        if (tempstr != null) {
            this.wind = this.convertString(tempstr);
        }
        if (this.wind < 1) {
            this.wind = 1;
        }
        else if (this.wind > 10) {
            this.wind = 10;
        }
        tempstr = app.getParameter("density");
        if (tempstr != null) {
            if (tempstr.toLowerCase().equals("low")) {
                this.maxsnow = this.bsize / 600;
            }
            else if (tempstr.toLowerCase().equals("high")) {
                this.maxsnow = this.bsize / 250;
            }
            else {
                this.maxsnow = this.bsize / 400;
            }
        }
        else {
            this.maxsnow = this.bsize / 400;
        }
        this.thesnow = new snow[this.maxsnow];
        for (int i = 0; i < this.maxsnow; ++i) {
            this.thesnow[i] = new snow(this.width, this.height, this.rd);
        }
    }
}
