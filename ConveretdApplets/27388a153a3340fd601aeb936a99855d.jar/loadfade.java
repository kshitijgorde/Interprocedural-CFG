import java.awt.image.PixelGrabber;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Component;
import java.applet.Applet;
import java.util.Random;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.image.MemoryImageSource;

// 
// Decompiled by Procyon v0.5.30
// 

class loadfade
{
    MemoryImageSource ArtSource;
    Image SourceImg;
    Image TempImage;
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
    private int fadePercent;
    private long nextcycle;
    private long delayInterval;
    private Random rd;
    public boolean isGrabbing;
    private boolean fgExist;
    
    loadfade(final Applet app, final Image timg, final int w, final int h) {
        this.TempImage = timg;
        this.rd = new Random();
        this.width = w;
        this.height = h;
        this.bsize = w * h;
        this.fgExist = false;
        this.bgcolor = Color.black;
        String tempstr = app.getParameter("bgcolor");
        if (tempstr != null) {
            this.bgcolor = new Color(Integer.parseInt(tempstr, 16));
        }
        this.format = 1;
        tempstr = app.getParameter("format");
        if (tempstr != null && tempstr.equals("stretch")) {
            this.format = 0;
        }
        this.delayInterval = 1000L;
        tempstr = app.getParameter("delay");
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
        tempstr = app.getParameter("maximages");
        if (tempstr != null) {
            this.imgCount = this.convertString(tempstr);
            if (this.imgCount > 0) {
                System.err.println("Init ImgCount = " + this.imgCount);
                this.img = new Image[this.imgCount];
                int keep = 0;
                this.mt = new MediaTracker(app);
                for (int i = 1; i <= this.imgCount; ++i) {
                    tempstr = app.getParameter("image" + i);
                    if (tempstr != null) {
                        this.img[keep] = app.getImage(app.getCodeBase(), tempstr);
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
        (this.ArtSource = new MemoryImageSource(this.width, this.height, new DirectColorModel(24, 16711680, 65280, 255), this.buffer, 0, this.width)).setAnimated(true);
        this.SourceImg = app.createImage(this.ArtSource);
        final boolean b = false;
        this.fadePercent = (b ? 1 : 0);
        this.grabbed = (b ? 1 : 0);
        this.isGrabbing = false;
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
    
    void doFade() {
        if (++this.fadePercent >= 25) {
            this.fadeCycle();
        }
        else {
            final double fade = 1.0 / (25.0 - this.fadePercent);
            for (int i = 0; i < this.bsize; ++i) {
                int newcol = this.buffer[i];
                int r = newcol >> 16 & 0xFF;
                int g = newcol >> 8 & 0xFF;
                int b = newcol & 0xFF;
                newcol = this.newbuf[i];
                r -= (int)((r - (newcol >> 16 & 0xFF)) * fade);
                g -= (int)((g - (newcol >> 8 & 0xFF)) * fade);
                b -= (int)((b - (newcol & 0xFF)) * fade);
                newcol = (0xFF000000 | r << 16 | g << 8 | b);
                this.buffer[i] = newcol;
            }
        }
    }
    
    void draw(final Graphics g) {
        if (this.allLoaded() > 0) {
            if (this.grabbed == 0) {
                ++this.grabbed;
                this.grabImage(this.buffer, this.cur);
                this.ArtSource.newPixels(0, 0, this.width, this.height);
                g.drawImage(this.SourceImg, 0, 0, null);
                this.isGrabbing = false;
            }
            else if (this.imgLoaded > 1 || this.imgCount == 1) {
                if (this.fadePercent == 0) {
                    this.fadePercent = 1;
                    this.grabImage(this.newbuf, this.getNext(this.cur));
                    g.drawImage(this.SourceImg, 0, 0, null);
                    this.isGrabbing = false;
                }
                else if (this.nextcycle < System.currentTimeMillis()) {
                    this.doFade();
                    this.ArtSource.newPixels(0, 0, this.width, this.height);
                    g.drawImage(this.SourceImg, 0, 0, null);
                }
                else if (this.fgExist) {
                    g.drawImage(this.SourceImg, 0, 0, null);
                }
            }
        }
    }
    
    void fadeCycle() {
        this.copyNewBuffer();
        this.cur = this.getNext(this.cur);
        this.fadePercent = 0;
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
}
