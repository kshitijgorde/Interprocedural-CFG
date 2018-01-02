import java.awt.Graphics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.DirectColorModel;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Component;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageObserver;
import java.awt.image.ColorModel;
import java.awt.Image;
import java.awt.MediaTracker;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ImageFader extends Applet implements Runnable
{
    Thread kicker;
    private int h;
    private int w;
    private MediaTracker t;
    private Image textureImg;
    private Image texture2Img;
    private Image fadeImg;
    private Image img;
    private int[] Screen;
    private int[] textureR;
    private int[] textureG;
    private int[] textureB;
    private int[] textureR1;
    private int[] textureG1;
    private int[] textureB1;
    private int[] textureR2;
    private int[] textureG2;
    private int[] textureB2;
    private int[] fadeMap;
    private int[] light;
    int tw;
    int th;
    int counter;
    long time;
    long stime;
    int ready;
    ColorModel model;
    
    public final void start() {
        (this.kicker = new Thread(this)).start();
    }
    
    public final void stop() {
        this.kicker = null;
    }
    
    public final String getAppletInfo() {
        return "\nImageFader Applet v1.0\n" + "Copyright 1997 by Christian Cohnen.\n" + "http://home.pages.de/~cohnen\n";
    }
    
    private final void convertImage() {
        this.tw = this.textureImg.getWidth(null);
        this.th = this.textureImg.getHeight(null);
        this.textureR1 = new int[this.tw * this.th];
        this.textureG1 = new int[this.tw * this.th];
        this.textureB1 = new int[this.tw * this.th];
        this.textureR2 = new int[this.tw * this.th];
        this.textureG2 = new int[this.tw * this.th];
        this.textureB2 = new int[this.tw * this.th];
        this.fadeMap = new int[this.tw * this.th];
        int[] textureScreen = null;
        textureScreen = new int[this.tw * this.th];
        final PixelGrabber pg2 = new PixelGrabber(this.fadeImg, 0, 0, this.tw, this.th, textureScreen, 0, this.tw);
        try {
            pg2.grabPixels();
        }
        catch (InterruptedException ex) {}
        if ((pg2.status() & 0x80) != 0x0) {
            System.out.println("image  fetch  aborted  or  errored");
            return;
        }
        for (int p = 0; p < this.th * this.tw; ++p) {
            this.fadeMap[p] = (byte)(textureScreen[p] & 0xFF);
        }
        PixelGrabber pg3 = new PixelGrabber(this.textureImg, 0, 0, this.tw, this.th, textureScreen, 0, this.tw);
        try {
            pg3.grabPixels();
        }
        catch (InterruptedException ex2) {}
        if ((pg3.status() & 0x80) != 0x0) {
            System.out.println("image  fetch  aborted  or  errored");
            return;
        }
        for (int p2 = 0; p2 < this.th * this.tw; ++p2) {
            this.textureB1[p2] = (textureScreen[p2] & 0xFF);
            this.textureG1[p2] = (textureScreen[p2] >> 8 & 0xFF);
            this.textureR1[p2] = (textureScreen[p2] >> 16 & 0xFF);
        }
        pg3 = new PixelGrabber(this.texture2Img, 0, 0, this.tw, this.th, textureScreen, 0, this.tw);
        try {
            pg3.grabPixels();
        }
        catch (InterruptedException ex3) {}
        if ((pg3.status() & 0x80) != 0x0) {
            System.out.println("image  fetch  aborted  or  errored");
            return;
        }
        for (int p2 = 0; p2 < this.th * this.tw; ++p2) {
            this.textureB2[p2] = (textureScreen[p2] & 0xFF);
            this.textureG2[p2] = (textureScreen[p2] >> 8 & 0xFF);
            this.textureR2[p2] = (textureScreen[p2] >> 16 & 0xFF);
        }
        this.textureImg = null;
        this.texture2Img = null;
        this.fadeImg = null;
    }
    
    public void run() {
        try {
            this.t.waitForID(1);
        }
        catch (InterruptedException ex) {}
        this.convertImage();
        this.ready = 1;
        this.stime = System.currentTimeMillis();
        while (this.kicker != null) {
            this.time = System.currentTimeMillis() - this.stime;
            this.time /= 5L;
            this.counter = (int)(this.time % 1000L);
            if (this.counter > 500) {
                this.counter = 500 - (this.counter - 500);
            }
            this.counter -= 50;
            this.time /= 1000L;
            if (this.counter < -32) {
                if (1L == this.time % 2L) {
                    this.textureR = this.textureR1;
                    this.textureG = this.textureG1;
                    this.textureB = this.textureB1;
                }
                else {
                    this.textureR = this.textureR2;
                    this.textureG = this.textureG2;
                    this.textureB = this.textureB2;
                }
            }
            this.repaint();
            int i = 0;
            do {
                int l = this.counter - 256 + i;
                l *= 4;
                if (l < 0) {
                    l = 0;
                }
                if (l > 255) {
                    l = 255;
                }
                this.light[i] = l >> 3;
            } while (++i < 256);
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex2) {}
        }
    }
    
    public final void init() {
        this.w = this.size().width;
        this.h = this.size().height;
        System.out.println(this.getAppletInfo());
        String fadeName = null;
        String[] imageName = null;
        imageName = new String[10];
        int nr = 0;
        this.t = new MediaTracker(this);
        do {
            ++nr;
            imageName[nr - 1] = this.getParameter("logo" + nr);
            System.out.println(">" + imageName[nr - 1]);
        } while (imageName[nr - 1] != null && nr < 10);
        fadeName = this.getParameter("fade");
        try {
            this.textureImg = this.getImage(new URL(this.getDocumentBase(), imageName[0]));
            this.texture2Img = this.getImage(new URL(this.getDocumentBase(), imageName[1]));
            this.fadeImg = this.getImage(new URL(this.getDocumentBase(), fadeName));
        }
        catch (MalformedURLException ex) {}
        this.t.addImage(this.textureImg, 1);
        this.t.addImage(this.texture2Img, 1);
        this.t.addImage(this.fadeImg, 1);
        this.light = new int[256];
        this.Screen = new int[this.w * this.h];
        this.model = new DirectColorModel(32, 16711680, 65280, 255, -16777216);
        this.img = this.createImage(new MemoryImageSource(this.w, this.h, this.model, this.Screen, 0, this.w));
    }
    
    public final synchronized void update(final Graphics g) {
        this.paint(g);
    }
    
    public final void paint(final Graphics gr) {
        if (this.ready == 1) {
            for (int i = 0; i < this.w * this.h; ++i) {
                final int v = this.light[this.fadeMap[i] & 0xFF];
                int r = v * this.textureR[i] >> 5;
                int g = v * this.textureG[i] >> 5;
                final int b = v * this.textureB[i] >> 5;
                r <<= 16;
                g <<= 8;
                this.Screen[i] = (0xFF000000 | r | g | b);
            }
            this.img.flush();
            gr.drawImage(this.img, 0, 0, this.w, this.h, null);
        }
    }
}
