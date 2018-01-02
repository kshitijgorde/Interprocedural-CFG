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
    
    loadburn(final Applet applet, final Image tempImage, final int width, final int height) {
        this.TempImage = tempImage;
        this.rd = new Random();
        this.width = width;
        this.height = height;
        this.bsize = width * height;
        this.fgExist = false;
        this.bgcolor = Color.black;
        final String parameter = applet.getParameter("bgcolor");
        if (parameter != null) {
            this.bgcolor = new Color(Integer.parseInt(parameter, 16));
        }
        this.format = 1;
        final String parameter2 = applet.getParameter("format");
        if (parameter2 != null && parameter2.equals("stretch")) {
            this.format = 0;
        }
        this.delayInterval = 1000L;
        final String parameter3 = applet.getParameter("delay");
        if (parameter3 != null) {
            this.delayInterval = this.convertStringLong(parameter3);
            if (this.delayInterval < 0L) {
                this.delayInterval = 0L;
            }
        }
        this.nextcycle = System.currentTimeMillis() + 3000L;
        this.imgCount = 0;
        this.imgLoaded = 0;
        this.cur = 0;
        final String parameter4 = applet.getParameter("maximages");
        if (parameter4 != null) {
            this.imgCount = this.convertString(parameter4);
            if (this.imgCount > 0) {
                System.err.println("Init ImgCount = " + this.imgCount);
                this.img = new Image[this.imgCount];
                int imgCount = 0;
                this.mt = new MediaTracker(applet);
                for (int i = 1; i <= this.imgCount; ++i) {
                    final String parameter5 = applet.getParameter("image" + i);
                    if (parameter5 != null) {
                        this.img[imgCount] = applet.getImage(applet.getCodeBase(), parameter5);
                        this.mt.addImage(this.img[imgCount], imgCount);
                        ++imgCount;
                    }
                }
                this.imgCount = imgCount;
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
            final int statusID = this.mt.statusID(this.imgLoaded, true);
            if (statusID != 1) {
                if (statusID == 4) {
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
        final String parameter = applet.getParameter("direction");
        if (parameter != null) {
            if (parameter.toLowerCase().equals("up")) {
                this.steadyDir = true;
            }
            else if (parameter.toLowerCase().equals("down")) {
                this.burnDir = 1;
                this.steadyDir = true;
            }
        }
    }
    
    int convertString(final String s) {
        int int1;
        try {
            int1 = Integer.parseInt(s);
        }
        catch (NumberFormatException ex) {
            int1 = 0;
        }
        return int1;
    }
    
    long convertStringLong(final String s) {
        long long1;
        try {
            long1 = Long.parseLong(s);
        }
        catch (NumberFormatException ex) {
            long1 = 0L;
        }
        return long1;
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
        int n = this.width >> 2;
        final int n2 = (this.width >> 1) - n;
        n -= 2;
        final double n3 = 0.25 * (this.burnCount / (1 + (this.height >> 1)));
        int n4 = this.bsize - this.width;
        for (int i = this.height - 6; i > 0; --i) {
            int n5 = n4;
            int n6 = n4 + this.width - 1;
            int n7 = 2;
            int n8 = 2;
            int n9 = 0;
            for (int j = 3; j > 0; --j) {
                for (int k = n7; k > 0; --k) {
                    final int n10 = (this.rand(5) + 1) * this.width;
                    final int n11 = this.rand(n8) - n9;
                    final int n12 = this.buffer[n5 - n10 + n11];
                    final int n13 = n12 >> 16 & 0xFF;
                    final int n14 = n12 >> 8 & 0xFF;
                    final int n15 = n12 & 0xFF;
                    final int n16 = this.newbuf[n5 - n10 + n11];
                    this.buffer[n5++] = (0xFF000000 | n13 - (int)((n13 - (n16 >> 16 & 0xFF)) * n3) << 16 | n14 - (int)((n14 - (n16 >> 8 & 0xFF)) * n3) << 8 | n15 - (int)((n15 - (n16 & 0xFF)) * n3));
                    final int n17 = this.buffer[n6 - n10 - n11];
                    final int n18 = n17 >> 16 & 0xFF;
                    final int n19 = n17 >> 8 & 0xFF;
                    final int n20 = n17 & 0xFF;
                    final int n21 = this.newbuf[n6 - n10 - n11];
                    this.buffer[n6--] = (0xFF000000 | n18 - (int)((n18 - (n21 >> 16 & 0xFF)) * n3) << 16 | n19 - (int)((n19 - (n21 >> 8 & 0xFF)) * n3) << 8 | n20 - (int)((n20 - (n21 & 0xFF)) * n3));
                }
                if (j == 2) {
                    n7 = n;
                    n9 = 2;
                    n8 = 5;
                }
                else {
                    n7 = n2;
                    n9 = 5;
                    n8 = 10;
                }
            }
            n4 -= this.width;
        }
        if (this.burnCount < 10) {
            int n22 = this.burnCount * this.width;
            final int rgb = this.bgcolor.getRGB();
            for (int l = this.width; l > 0; --l) {
                this.buffer[n22++] = rgb;
            }
        }
        if (++this.burnCount >= this.height >> 2) {
            this.burnCycle();
        }
    }
    
    void doBurnUp() {
        int n = this.width >> 2;
        final int n2 = (this.width >> 1) - n;
        n -= 2;
        final double n3 = 0.25 * (this.burnCount / (1 + (this.height >> 1)));
        int n4 = 0;
        for (int i = this.height - 6; i > 0; --i) {
            int n5 = n4;
            int n6 = n4 + this.width - 1;
            int n7 = 2;
            int n8 = 2;
            int n9 = 0;
            for (int j = 3; j > 0; --j) {
                for (int k = n7; k > 0; --k) {
                    final int n10 = (this.rand(5) + 1) * this.width;
                    final int n11 = this.rand(n8) - n9;
                    final int n12 = this.buffer[n5 + n10 + n11];
                    final int n13 = n12 >> 16 & 0xFF;
                    final int n14 = n12 >> 8 & 0xFF;
                    final int n15 = n12 & 0xFF;
                    final int n16 = this.newbuf[n5 + n10 + n11];
                    this.buffer[n5++] = (0xFF000000 | n13 - (int)((n13 - (n16 >> 16 & 0xFF)) * n3) << 16 | n14 - (int)((n14 - (n16 >> 8 & 0xFF)) * n3) << 8 | n15 - (int)((n15 - (n16 & 0xFF)) * n3));
                    final int n17 = this.buffer[n6 + n10 - n11];
                    final int n18 = n17 >> 16 & 0xFF;
                    final int n19 = n17 >> 8 & 0xFF;
                    final int n20 = n17 & 0xFF;
                    final int n21 = this.newbuf[n6 + n10 - n11];
                    this.buffer[n6--] = (0xFF000000 | n18 - (int)((n18 - (n21 >> 16 & 0xFF)) * n3) << 16 | n19 - (int)((n19 - (n21 >> 8 & 0xFF)) * n3) << 8 | n20 - (int)((n20 - (n21 & 0xFF)) * n3));
                }
                if (j == 2) {
                    n7 = n;
                    n9 = 2;
                    n8 = 5;
                }
                else {
                    n7 = n2;
                    n9 = 5;
                    n8 = 10;
                }
            }
            n4 += this.width;
        }
        if (this.burnCount < 10) {
            int n22 = this.bsize - this.width - this.burnCount * this.width;
            final int rgb = this.bgcolor.getRGB();
            for (int l = this.width; l > 0; --l) {
                this.buffer[n22++] = rgb;
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
            final double n = 1.0 / (25.0 - this.fadePercent);
            for (int i = 0; i < this.bsize; ++i) {
                final int n2 = this.buffer[i];
                final int n3 = n2 >> 16 & 0xFF;
                final int n4 = n2 >> 8 & 0xFF;
                final int n5 = n2 & 0xFF;
                final int n6 = this.newbuf[i];
                this.buffer[i] = (0xFF000000 | n3 - (int)((n3 - (n6 >> 16 & 0xFF)) * n) << 16 | n4 - (int)((n4 - (n6 >> 8 & 0xFF)) * n) << 8 | n5 - (int)((n5 - (n6 & 0xFF)) * n));
            }
        }
    }
    
    void draw(final Graphics graphics) {
        if (this.allLoaded() > 0) {
            if (this.grabbed == 0) {
                ++this.grabbed;
                this.grabImage(this.buffer, this.cur);
                this.ArtSource.newPixels(0, 0, this.width, this.height);
                graphics.drawImage(this.SourceImg, 0, 0, null);
            }
            else if (this.imgLoaded > 1 || this.imgCount == 1) {
                if (this.fadePercent == 0) {
                    this.fadePercent = 1;
                    this.grabImage(this.newbuf, this.getNext(this.cur));
                    graphics.drawImage(this.SourceImg, 0, 0, null);
                }
                else if (this.nextcycle < System.currentTimeMillis()) {
                    if (!this.burnDone) {
                        this.doBurn();
                    }
                    else {
                        this.doFade();
                    }
                    this.ArtSource.newPixels(0, 0, this.width, this.height);
                    graphics.drawImage(this.SourceImg, 0, 0, null);
                }
                else if (this.fgExist) {
                    graphics.drawImage(this.SourceImg, 0, 0, null);
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
    
    int getNext(final int n) {
        int n2 = n;
        do {
            if (++n2 >= this.imgLoaded) {
                n2 = 0;
            }
        } while (this.img[n2] == null);
        return n2;
    }
    
    boolean grabImage(final int[] array, final int n) {
        final Graphics graphics = this.TempImage.getGraphics();
        boolean b = true;
        if (this.img[n] != null) {
            switch (this.format) {
                case 1: {
                    graphics.setColor(this.bgcolor);
                    graphics.fillRect(0, 0, this.width, this.height);
                    graphics.drawImage(this.img[n], this.width - this.img[n].getWidth(null) >> 1, this.height - this.img[n].getHeight(null) >> 1, null);
                    break;
                }
                default: {
                    graphics.drawImage(this.img[n], 0, 0, this.width, this.height, null);
                    break;
                }
            }
            try {
                new PixelGrabber(this.TempImage, 0, 0, this.width, this.height, array, 0, this.width).grabPixels();
            }
            catch (InterruptedException ex) {
                b = false;
            }
        }
        else {
            b = false;
        }
        graphics.dispose();
        System.gc();
        return b;
    }
    
    int rand(final int n) {
        return Math.abs(this.rd.nextInt() % n);
    }
    
    void setFgExist(final boolean fgExist) {
        this.fgExist = fgExist;
    }
}
