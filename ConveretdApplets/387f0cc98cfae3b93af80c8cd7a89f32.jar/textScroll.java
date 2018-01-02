import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.util.Random;
import java.awt.Color;
import java.awt.Image;
import java.awt.image.MemoryImageSource;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

class textScroll
{
    Applet app;
    MemoryImageSource ArtSource;
    Image SourceImg;
    Image TempImage;
    private Image[] img;
    private int[] buffer;
    private int[] xbuf;
    private int[] ybuf;
    private point3d[] stars;
    private int maxStars;
    int drawMin;
    int drawMax;
    int drawHeight;
    int scrollPos;
    matrix3d mat;
    private int width;
    private int height;
    private int bsize;
    private int halfheight;
    private int halfwidth;
    public Color bgcolor;
    public int bgColorRGB;
    private int starColorR;
    private int starColorG;
    private int starColorB;
    private long nextcycle;
    private long delayInterval;
    private Random rd;
    int[][] wordsData;
    int wordsHeight;
    int rotx;
    int rotinc;
    
    textScroll(final Applet app, final Image tempImage, final int width, final int height) {
        this.rotx = 0;
        this.rotinc = 1;
        this.TempImage = tempImage;
        this.app = app;
        this.rd = new Random();
        this.width = width;
        this.height = height;
        this.bsize = width * height;
        this.halfwidth = this.width >> 1;
        this.halfheight = this.height >> 1;
        this.bgcolor = Color.black;
        final String parameter = this.app.getParameter("bgcolor");
        if (parameter != null) {
            this.bgcolor = new Color(Integer.parseInt(parameter, 16));
        }
        this.starColorR = 170;
        this.starColorG = 230;
        this.starColorB = 245;
        final String parameter2 = this.app.getParameter("starcolor");
        if (parameter2 != null) {
            final int convertHex2Int = this.convertHex2Int(parameter2);
            if (convertHex2Int > 0) {
                this.starColorR = (0xFF & convertHex2Int >> 16);
                this.starColorG = (0xFF & convertHex2Int >> 8);
                this.starColorB = (0xFF & convertHex2Int);
                if (this.starColorR < 10) {
                    this.starColorR = 10;
                }
                else if (this.starColorR > 245) {
                    this.starColorR = 245;
                }
                if (this.starColorG < 10) {
                    this.starColorG = 10;
                }
                else if (this.starColorG > 245) {
                    this.starColorG = 245;
                }
                if (this.starColorB < 10) {
                    this.starColorB = 10;
                }
                else if (this.starColorB > 245) {
                    this.starColorB = 245;
                }
            }
        }
        this.delayInterval = 80L;
        final String parameter3 = this.app.getParameter("delay");
        if (parameter3 != null) {
            this.delayInterval = this.convertStringLong(parameter3);
            if (this.delayInterval < 0L) {
                this.delayInterval = 0L;
            }
            else if (this.delayInterval > 800L) {
                this.delayInterval = 800L;
            }
        }
        this.nextcycle = System.currentTimeMillis() + this.delayInterval;
        this.maxStars = this.width;
        if (this.height < this.maxStars) {
            this.maxStars = this.height;
        }
        this.mat = new matrix3d(this.height >> 2);
        this.buffer = new int[this.bsize];
        this.xbuf = new int[this.height];
        this.ybuf = new int[this.height];
        this.stars = new point3d[this.maxStars];
        for (int i = 0; i < this.maxStars; ++i) {
            int n = this.rand(this.maxStars) - (this.maxStars >> 1);
            int n2 = this.rand(this.maxStars) - (this.maxStars >> 1);
            if (n == 0) {
                n = 1;
            }
            if (n2 == 0) {
                n2 = 1;
            }
            (this.stars[i] = new point3d(n, n2, this.rand(100) - 50, 0xFF000000 | this.starColorR + this.rand(20) - 10 << 16 | this.starColorG + this.rand(20) - 10 + 230 << 8 | this.starColorB + this.rand(20) - 10)).setSpeed(this.rand(2) + 1);
        }
        this.bgColorRGB = (0xFF000000 | this.bgcolor.getRed());
        for (int j = 0; j < this.bsize; ++j) {
            this.buffer[j] = this.bgColorRGB;
        }
        this.grabWords(this.app, this.buffer);
        for (int k = 0; k < this.height; ++k) {
            this.xbuf[k] = (this.ybuf[k] = 0);
        }
        (this.ArtSource = new MemoryImageSource(this.width, this.height, this.buffer, 0, this.width)).setAnimated(true);
        this.SourceImg = this.app.createImage(this.ArtSource);
        this.setPoints();
    }
    
    void clearScreen() {
        int bsize = this.bsize;
        while (--bsize >= 0) {
            this.buffer[bsize] = -16777216;
        }
    }
    
    int convertHex2Int(final String s) {
        int int1;
        try {
            int1 = Integer.parseInt(s, 16);
        }
        catch (NumberFormatException ex) {
            int1 = 0;
        }
        return int1;
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
    
    void draw(final Graphics graphics) {
        this.clearScreen();
        this.drawStars();
        this.drawPage();
        if (this.nextcycle < System.currentTimeMillis()) {
            if (++this.scrollPos > this.wordsHeight) {
                this.scrollPos = -this.drawHeight;
            }
            this.nextcycle = System.currentTimeMillis() + this.delayInterval;
        }
        this.ArtSource.newPixels(0, 0, this.width, this.height);
        graphics.drawImage(this.SourceImg, 0, 0, null);
    }
    
    void drawLine(final int n, final int n2, int width, final int n3, final int n4) {
        if (n3 > 0 && n3 < this.wordsHeight) {
            final int[] array = this.wordsData[n3];
            int n5 = array[0];
            if (n5 > 0) {
                final int n6 = width - n2;
                int i = n6 >> 1;
                if (width > this.width) {
                    width = this.width;
                }
                int n7 = n2;
                int n8 = 1;
                while (n5-- > 0 && n7 < width) {
                    final int n9 = array[n8++];
                    int n10 = array[n8++];
                    if ((n9 & 0xFFFFFF) == 0x0) {
                        while (--n10 >= 0) {
                            if (n7 >= width) {
                                break;
                            }
                            for (i += n6; i > this.width; i -= this.width, ++n7) {}
                        }
                    }
                    else {
                        final int n11 = 0xFF & n9 >> 16;
                        final int n12 = 0xFF & n9 >> 8;
                        final int n13 = 0xFF & n9;
                        final int n14 = n11 + n4;
                        final int n15 = (n14 > 255) ? 255 : ((n14 < 0) ? 0 : n14);
                        final int n16 = n12 + n4;
                        final int n17 = (n16 > 255) ? 255 : ((n16 < 0) ? 0 : n16);
                        final int n18 = n13 + n4;
                        final int n19 = 0xFF000000 | n15 << 16 | n17 << 8 | ((n18 > 255) ? 255 : ((n18 < 0) ? 0 : n18));
                        while (--n10 >= 0 && n7 < width) {
                            i += n6;
                            while (i > this.width) {
                                i -= this.width;
                                if (n7 >= 0 && n7 < width) {
                                    this.buffer[n + n7] = n19;
                                }
                                ++n7;
                            }
                        }
                    }
                }
            }
        }
    }
    
    void drawPage() {
        final int n = this.width * this.drawMin;
        int i = 200;
        int n2 = -256;
        int n3 = 0;
        int j = this.ybuf[n3] + this.scrollPos;
        while (j < 0) {
            j = this.ybuf[++n3] + this.scrollPos;
            i += 400;
            while (i > this.drawMax) {
                i -= this.drawMax;
                if (++n2 > 0) {
                    n2 = 0;
                }
            }
        }
        int n4 = this.width * (this.drawMin + n3);
        while (n3 < this.drawMax && j < this.wordsHeight) {
            j = this.ybuf[n3] + this.scrollPos;
            final int n5 = this.xbuf[n3];
            this.drawLine(n4, this.halfwidth - n5, this.halfwidth + n5, j, n2);
            n4 += this.width;
            ++n3;
            i += 400;
            while (i > this.drawMax) {
                i -= this.drawMax;
                if (++n2 > 0) {
                    n2 = 0;
                }
            }
        }
    }
    
    void drawStars() {
        if (++this.rotx > 360) {
            this.rotx = 0;
        }
        this.mat.rotate(0.0, 0.0, this.rotx);
        this.mat.transform(this.maxStars, this.stars);
        for (int i = 0; i < this.maxStars; ++i) {
            final point3d point3d = this.stars[i];
            if (point3d.xp < 0 || point3d.xp >= this.width || point3d.yp < 0 || point3d.yp >= this.height || point3d.lz > 200.0) {
                int n = this.rand(this.maxStars) - (this.maxStars >> 1);
                int n2 = this.rand(this.maxStars) - (this.maxStars >> 1);
                if (n < 0 && n > -3) {
                    n = -4;
                }
                else if (n >= 0 && n <= 3) {
                    n = 4;
                }
                if (n2 < 0 && n2 > -2) {
                    n2 = -3;
                }
                else if (n2 >= 0 && n2 <= 2) {
                    n2 = 3;
                }
                point3d.set(n, n2, -this.rand(40) - 5);
            }
            else {
                final int color = point3d.color;
                final int n3 = 0xFF & color >> 16;
                final int n4 = 0xFF & color >> 8;
                final int n5 = 0xFF & color;
                final int n6 = (int)(n4 + point3d.lz);
                final int n7 = (n6 > 255) ? 255 : ((n6 < 0) ? 0 : n6);
                final int n8 = (int)(n5 + point3d.lz);
                final int n9 = 0xFF000000 | n3 << 16 | n7 << 8 | ((n8 > 255) ? 255 : ((n8 < 0) ? 0 : n8));
                this.buffer[point3d.xp + point3d.yp * this.width] = n9;
                if (point3d.lz > -10.0 && point3d.xp > 0 && point3d.lz > 10.0) {
                    final int n10 = point3d.xp + point3d.yp * this.width - 1;
                    this.buffer[n10] = n9;
                    if (point3d.yp > 0) {
                        final int n11 = n10 - this.width;
                        this.buffer[n11] = n9;
                        this.buffer[n11 + 1] = n9;
                    }
                }
                final point3d point3d2 = point3d;
                point3d2.lz += point3d.speed;
            }
        }
    }
    
    public void grabWords(final Applet applet, final int[] array) {
        final Graphics graphics = this.TempImage.getGraphics();
        final wordload wordload = new wordload(applet, graphics, this.width, this.height);
        wordload.setTop();
        final int[] array2 = new int[this.width << 1];
        this.wordsHeight = wordload.getHeight();
        this.wordsData = new int[this.wordsHeight][];
        int i = 0;
        do {
            graphics.setColor(Color.black);
            graphics.fillRect(0, 0, this.width, this.height);
            wordload.draw(graphics);
            try {
                new PixelGrabber(this.TempImage, 0, 0, this.width, this.height, array, 0, this.width).grabPixels();
            }
            catch (InterruptedException ex) {}
            for (int n = 0; n < this.height && i < this.wordsHeight; ++i, ++n) {
                int n2 = 1;
                int j = 0;
                final int n3 = n * this.width;
                do {
                    final int n4 = array[n3 + j];
                    int n5 = 0;
                    while (j < this.width && array[n3 + j] == n4) {
                        ++n5;
                        ++j;
                    }
                    if ((n4 & 0xFFFFFF) != 0x0 || j < this.width) {
                        array2[n2++] = n4;
                        array2[n2++] = n5;
                    }
                } while (j < this.width);
                (this.wordsData[i] = new int[n2])[0] = n2 - 1 >> 1;
                if (n2 > 1) {
                    for (int k = 1; k < n2; ++k) {
                        this.wordsData[i][k] = array2[k];
                    }
                }
            }
            wordload.setTopNext();
        } while (i < this.wordsHeight);
        graphics.dispose();
        System.gc();
    }
    
    int rand(final int n) {
        return Math.abs(this.rd.nextInt() % n);
    }
    
    void setPoints() {
        final point3d point3d = new point3d();
        this.mat.scale(1.0);
        this.mat.rotate(30.0, 0.0, 0.0);
        this.mat.translate(0.0, 0.0, 0.0);
        int n = -this.halfheight;
        point3d.set(this.halfwidth, n++, 0.0);
        this.mat.form(point3d);
        this.mat.project(point3d);
        int drawMax = 0;
        int n2 = point3d.xp;
        int yp = point3d.yp - 1;
        do {
            point3d.set(this.halfwidth, n++, 0.0);
            this.mat.form(point3d);
            this.mat.project(point3d);
            final int n3 = point3d.xp - n2;
            final int n4 = point3d.yp - yp;
            int n5 = 0;
            while (++n5 <= n4) {
                this.xbuf[drawMax] = n2 + n3 * (n5 / n4);
                this.ybuf[drawMax] = n + this.halfheight;
                ++drawMax;
            }
            n2 = point3d.xp;
            yp = point3d.yp;
        } while (point3d.yp < this.height - (point3d.yp + this.halfheight));
        this.mat.center(this.width >> 1, this.height >> 1);
        this.mat.transform(this.maxStars, this.stars);
        this.drawMax = drawMax;
        this.drawMin = this.height - drawMax;
        this.drawHeight = this.ybuf[drawMax - 1];
        this.scrollPos = -this.drawHeight;
    }
}
