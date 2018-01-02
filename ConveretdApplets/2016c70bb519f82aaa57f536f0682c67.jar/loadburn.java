import java.awt.image.PixelGrabber;
import java.awt.Graphics;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
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

class loadburn
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
    private boolean fgExist;
    boolean steadyDir;
    int burnCount;
    int burnDir;
    boolean burnDone;
    
    loadburn(final Applet applet, final Image image, final int i, final int j) {
        this.TempImage = image;
        this.rd = new Random();
        this.width = i;
        this.height = j;
        this.bsize = i * j;
        this.fgExist = false;
        this.bgcolor = Color.black;
        String s = applet.getParameter("bgcolor");
        if (s != null) {
            this.bgcolor = new Color(Integer.parseInt(s, 16));
        }
        this.format = 1;
        s = applet.getParameter("format");
        if (s != null && s.equals("stretch")) {
            this.format = 0;
        }
        this.delayInterval = 1000L;
        s = applet.getParameter("delay");
        if (s != null) {
            this.delayInterval = this.convertStringLong(s);
            if (this.delayInterval < 0L) {
                this.delayInterval = 0L;
            }
        }
        this.nextcycle = System.currentTimeMillis() + 3000L;
        this.imgCount = 0;
        this.imgLoaded = 0;
        this.cur = 0;
        s = applet.getParameter("maximages");
        if (s != null) {
            this.imgCount = this.convertString(s);
            if (this.imgCount > 0) {
                System.err.println("Init ImgCount = " + this.imgCount);
                this.img = new Image[this.imgCount];
                int k = 0;
                this.mt = new MediaTracker(applet);
                for (int l = 1; l <= this.imgCount; ++l) {
                    final String s2 = applet.getParameter("image" + l);
                    if (s2 != null) {
                        this.img[k] = applet.getImage(applet.getCodeBase(), s2);
                        this.mt.addImage(this.img[k], k);
                        ++k;
                    }
                }
                this.imgCount = k;
                System.err.println("Final ImgCount = " + this.imgCount);
            }
        }
        this.buffer = new int[this.bsize];
        this.newbuf = new int[this.bsize];
        (this.ArtSource = new MemoryImageSource(this.width, this.height, this.buffer, 0, this.width)).setAnimated(true);
        this.SourceImg = applet.createImage(this.ArtSource);
        final boolean b = false;
        this.fadePercent = (b ? 1 : 0);
        this.grabbed = (b ? 1 : 0);
        this.burnInit(applet);
    }
    
    int allLoaded() {
        if (this.imgLoaded < this.imgCount) {
            final int i = this.mt.statusID(this.imgLoaded, true);
            if (i != 1) {
                if (i == 4) {
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
    
    void burnCycle() {
        this.burnCount = 0;
        this.burnDone = true;
        if (!this.steadyDir && --this.burnDir < 0) {
            this.burnDir = 1;
        }
    }
    
    void burnInit(final Applet applet) {
        this.burnCount = 0;
        this.burnDir = 0;
        this.burnDone = false;
        this.steadyDir = false;
        final String s = applet.getParameter("direction");
        if (s != null) {
            if (s.toLowerCase().equals("up")) {
                this.steadyDir = true;
            }
            else if (s.toLowerCase().equals("down")) {
                this.burnDir = 1;
                this.steadyDir = true;
            }
        }
    }
    
    int convertString(final String s) {
        int i = 0;
        try {
            i = Integer.parseInt(s);
        }
        catch (NumberFormatException numberformatexception) {
            i = 0;
        }
        return i;
    }
    
    long convertStringLong(final String s) {
        long l = 0L;
        try {
            l = Long.parseLong(s);
        }
        catch (NumberFormatException numberformatexception) {
            l = 0L;
        }
        return l;
    }
    
    void copyNewBuffer() {
        System.arraycopy(this.newbuf, 0, this.buffer, 0, this.bsize);
    }
    
    void doBurn() {
        switch (this.burnDir) {
            case 1: {
                this.doBurnDown();
                break;
            }
            default: {
                this.doBurnUp();
                break;
            }
        }
    }
    
    void doBurnDown() {
        final byte byte0 = 5;
        int i = this.width >> 2;
        final int j = (this.width >> 1) - i;
        i -= 2;
        final double d = 0.25 * (this.burnCount / (1 + (this.height >> 1)));
        int k = this.bsize - this.width;
        for (int l = this.height - 6; l > 0; --l) {
            int i2 = k;
            int k2 = k + this.width - 1;
            int i3 = 2;
            byte byte2 = 2;
            byte byte3 = 0;
            for (int k3 = 3; k3 > 0; --k3) {
                for (int l2 = i3; l2 > 0; --l2) {
                    final int i4 = (this.rand(5) + 1) * this.width;
                    final int j2 = this.rand(byte2) - byte3;
                    int k4 = this.buffer[i2 - i4 + j2];
                    int l3 = k4 >> 16 & 0xFF;
                    int i5 = k4 >> 8 & 0xFF;
                    int j3 = k4 & 0xFF;
                    k4 = this.newbuf[i2 - i4 + j2];
                    l3 -= (int)((l3 - (k4 >> 16 & 0xFF)) * d);
                    i5 -= (int)((i5 - (k4 >> 8 & 0xFF)) * d);
                    j3 -= (int)((j3 - (k4 & 0xFF)) * d);
                    this.buffer[i2++] = (0xFF000000 | l3 << 16 | i5 << 8 | j3);
                    k4 = this.buffer[k2 - i4 - j2];
                    l3 = (k4 >> 16 & 0xFF);
                    i5 = (k4 >> 8 & 0xFF);
                    j3 = (k4 & 0xFF);
                    k4 = this.newbuf[k2 - i4 - j2];
                    l3 -= (int)((l3 - (k4 >> 16 & 0xFF)) * d);
                    i5 -= (int)((i5 - (k4 >> 8 & 0xFF)) * d);
                    j3 -= (int)((j3 - (k4 & 0xFF)) * d);
                    this.buffer[k2--] = (0xFF000000 | l3 << 16 | i5 << 8 | j3);
                }
                if (k3 == 2) {
                    i3 = i;
                    byte3 = 2;
                    byte2 = 5;
                }
                else {
                    i3 = j;
                    byte3 = 5;
                    byte2 = 10;
                }
            }
            k -= this.width;
        }
        if (this.burnCount < 10) {
            int j4 = this.burnCount * this.width;
            final int l4 = this.bgcolor.getRGB();
            for (int j5 = this.width; j5 > 0; --j5) {
                this.buffer[j4++] = l4;
            }
        }
        if (++this.burnCount >= this.height >> 2) {
            this.burnCycle();
        }
    }
    
    void doBurnUp() {
        final byte byte0 = 5;
        int i = this.width >> 2;
        final int j = (this.width >> 1) - i;
        i -= 2;
        final double d = 0.25 * (this.burnCount / (1 + (this.height >> 1)));
        int k = 0;
        for (int l = this.height - 6; l > 0; --l) {
            int i2 = k;
            int k2 = k + this.width - 1;
            int i3 = 2;
            byte byte2 = 2;
            byte byte3 = 0;
            for (int k3 = 3; k3 > 0; --k3) {
                for (int l2 = i3; l2 > 0; --l2) {
                    final int i4 = (this.rand(5) + 1) * this.width;
                    final int j2 = this.rand(byte2) - byte3;
                    int k4 = this.buffer[i2 + i4 + j2];
                    int l3 = k4 >> 16 & 0xFF;
                    int i5 = k4 >> 8 & 0xFF;
                    int j3 = k4 & 0xFF;
                    k4 = this.newbuf[i2 + i4 + j2];
                    l3 -= (int)((l3 - (k4 >> 16 & 0xFF)) * d);
                    i5 -= (int)((i5 - (k4 >> 8 & 0xFF)) * d);
                    j3 -= (int)((j3 - (k4 & 0xFF)) * d);
                    this.buffer[i2++] = (0xFF000000 | l3 << 16 | i5 << 8 | j3);
                    k4 = this.buffer[k2 + i4 - j2];
                    l3 = (k4 >> 16 & 0xFF);
                    i5 = (k4 >> 8 & 0xFF);
                    j3 = (k4 & 0xFF);
                    k4 = this.newbuf[k2 + i4 - j2];
                    l3 -= (int)((l3 - (k4 >> 16 & 0xFF)) * d);
                    i5 -= (int)((i5 - (k4 >> 8 & 0xFF)) * d);
                    j3 -= (int)((j3 - (k4 & 0xFF)) * d);
                    this.buffer[k2--] = (0xFF000000 | l3 << 16 | i5 << 8 | j3);
                }
                if (k3 == 2) {
                    i3 = i;
                    byte3 = 2;
                    byte2 = 5;
                }
                else {
                    i3 = j;
                    byte3 = 5;
                    byte2 = 10;
                }
            }
            k += this.width;
        }
        if (this.burnCount < 10) {
            int j4 = this.bsize - this.width - this.burnCount * this.width;
            final int l4 = this.bgcolor.getRGB();
            for (int j5 = this.width; j5 > 0; --j5) {
                this.buffer[j4++] = l4;
            }
        }
        if (++this.burnCount >= this.height >> 2) {
            this.burnCycle();
        }
    }
    
    void doFade() {
        if (++this.fadePercent >= 25) {
            this.fadeCycle();
        }
        else {
            final double d = 1.0 / (25.0 - this.fadePercent);
            for (int i = 0; i < this.bsize; ++i) {
                int j = this.buffer[i];
                int k = j >> 16 & 0xFF;
                int l = j >> 8 & 0xFF;
                int i2 = j & 0xFF;
                j = this.newbuf[i];
                k -= (int)((k - (j >> 16 & 0xFF)) * d);
                l -= (int)((l - (j >> 8 & 0xFF)) * d);
                i2 -= (int)((i2 - (j & 0xFF)) * d);
                j = (0xFF000000 | k << 16 | l << 8 | i2);
                this.buffer[i] = j;
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
            }
            else if (this.imgLoaded > 1 || this.imgCount == 1) {
                if (this.fadePercent == 0) {
                    this.fadePercent = 1;
                    this.grabImage(this.newbuf, this.getNext(this.cur));
                    g.drawImage(this.SourceImg, 0, 0, null);
                }
                else if (this.nextcycle < System.currentTimeMillis()) {
                    if (!this.burnDone) {
                        this.doBurn();
                    }
                    else {
                        this.doFade();
                    }
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
        this.burnDone = false;
        this.nextcycle = System.currentTimeMillis() + this.delayInterval;
    }
    
    int getNext(final int i) {
        int j = i;
        do {
            if (++j >= this.imgLoaded) {
                j = 0;
            }
        } while (this.img[j] == null);
        return j;
    }
    
    boolean grabImage(final int[] ai, final int i) {
        final Graphics g = this.TempImage.getGraphics();
        boolean flag = true;
        if (this.img[i] != null) {
            switch (this.format) {
                case 1: {
                    g.setColor(this.bgcolor);
                    g.fillRect(0, 0, this.width, this.height);
                    g.drawImage(this.img[i], this.width - this.img[i].getWidth(null) >> 1, this.height - this.img[i].getHeight(null) >> 1, null);
                    break;
                }
                default: {
                    g.drawImage(this.img[i], 0, 0, this.width, this.height, null);
                    break;
                }
            }
            try {
                final PixelGrabber pixelgrabber = new PixelGrabber(this.TempImage, 0, 0, this.width, this.height, ai, 0, this.width);
                pixelgrabber.grabPixels();
            }
            catch (InterruptedException interruptedexception) {
                flag = false;
            }
        }
        else {
            flag = false;
        }
        g.dispose();
        System.gc();
        return flag;
    }
    
    int rand(final int i) {
        return Math.abs(this.rd.nextInt() % i);
    }
    
    void setFgExist(final boolean flag) {
        this.fgExist = flag;
    }
}
