import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseListener;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class JVMU extends Applet implements Runnable, MouseListener
{
    int[] imageLoadSwirl;
    int[] imageLoadArraySmall;
    int[] imageLoadArray;
    String version;
    int BorringBits;
    int pageSize;
    int RowLen;
    int ColTotal;
    int frameCur;
    int frameTotal;
    int delayCounter;
    int swirlX;
    int swirlY;
    int swirlD;
    frameLoader loader;
    int speed;
    String paintlock;
    String linkURL;
    int RowLenB;
    int ColTotalB;
    Thread sequence;
    Image offScreenBuf;
    Image loadScr;
    Image loadSwirl;
    double incPerAnim;
    double incPerFrame;
    int aWidth;
    int aHeight;
    Graphics gBuff;
    boolean pause;
    boolean small;
    Color bkColor;
    
    public JVMU() {
        this.imageLoadSwirl = new int[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 7, 7, 0, 0, 0, 0, 0, 7, 7, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 5, 5, 0, 0, 0, 7, 0, 0, 0, 7, 7, 7, 7, 7, 0, 0, 0, 7, 0, 0, 5, 5, 0, 0, 0, 7, 0, 0, 7, 0, 0, 0, 0, 0, 7, 0, 0, 7, 0, 0, 5, 5, 0, 0, 7, 0, 0, 7, 0, 0, 7, 7, 7, 0, 0, 7, 0, 0, 0, 0, 5, 5, 0, 0, 7, 0, 0, 7, 0, 7, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 5, 5, 0, 0, 7, 0, 0, 7, 0, 0, 7, 7, 0, 7, 0, 0, 7, 0, 0, 0, 5, 5, 0, 0, 0, 7, 0, 0, 7, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 5, 5, 0, 0, 0, 7, 0, 0, 0, 7, 7, 7, 7, 0, 0, 7, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 7, 7, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
        this.imageLoadArraySmall = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 7, 7, 7, 0, 0, 7, 0, 0, 7, 7, 0, 0, 7, 7, 7, 0, 7, 0, 0, 7, 0, 0, 7, 7, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 7, 0, 7, 0, 7, 7, 7, 0, 7, 0, 7, 0, 0, 7, 0, 0, 7, 7, 0, 7, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 7, 0, 0, 7, 0, 0, 7, 7, 7, 7, 0, 7, 0, 7, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 7, 0, 7, 0, 7, 7, 7, 0, 7, 0, 7, 0, 0, 7, 0, 0, 7, 0, 7, 7, 0, 7, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 0, 7, 7, 7, 0, 7, 0, 7, 0, 7, 7, 0, 0, 7, 7, 7, 0, 7, 0, 0, 7, 0, 0, 7, 7, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 0, 0, 0, 0, 0, 7, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 7, 7, 7, 7, 7, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 7, 0, 0, 0, 0, 0, 7, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 7, 0, 0, 7, 7, 7, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 7, 0, 7, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 7, 0, 0, 7, 7, 0, 7, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 7, 0, 0, 0, 0, 7, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 7, 7, 7, 7, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 7, 7, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 7, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0 };
        this.version = "6.1";
        this.BorringBits = 14;
        this.pageSize = 1536;
        this.RowLen = 48;
        this.ColTotal = 32;
        this.frameCur = 0;
        this.frameTotal = 0;
        this.delayCounter = 0;
        this.swirlX = 0;
        this.swirlY = 0;
        this.swirlD = 0;
        this.loader = new frameLoader();
        this.speed = 150;
        this.paintlock = "OFF";
        this.RowLenB = this.RowLen * 3;
        this.ColTotalB = this.ColTotal * 4;
        this.sequence = null;
        this.incPerAnim = 0.0;
        this.incPerFrame = 128.0 / this.pageSize;
        this.pause = false;
        this.small = false;
    }
    
    private int Randomize(final int n) {
        return (int)(Math.random() * n);
    }
    
    public void convert() {
        this.imageLoadArray = new int[this.RowLenB * this.ColTotalB];
        int n = 0;
        for (int i = 0; i < this.ColTotal; ++i) {
            for (int j = 0; j < this.RowLen; ++j) {
                int n2;
                if (this.imageLoadArraySmall[j + i * this.RowLen] == 0) {
                    n2 = 0;
                }
                else {
                    n2 = -16777216;
                }
                if (!this.small) {
                    this.imageLoadArray[n] = n2;
                    this.imageLoadArray[n + 1] = n2;
                    this.imageLoadArray[n + this.RowLenB] = n2;
                    this.imageLoadArray[n + 1 + this.RowLenB] = n2;
                    n += 3;
                }
                else {
                    this.imageLoadArraySmall[j + i * this.RowLen] = n2;
                }
            }
            n = n + this.RowLenB + this.RowLenB + this.RowLenB;
        }
    }
    
    public void convertColor(final int n, final int n2) {
        for (int i = 0; i < n2; ++i) {
            for (int j = 0; j < n; ++j) {
                int n3;
                if (this.imageLoadSwirl[j + i * n] == 0) {
                    n3 = -1;
                }
                else if (this.imageLoadSwirl[j + i * n] == 5) {
                    n3 = -16777216;
                }
                else {
                    n3 = -251723776;
                }
                this.imageLoadSwirl[j + i * n] = n3;
            }
        }
    }
    
    public void faster() {
        this.speed -= 30;
        if (this.speed < 10) {
            this.speed = 10;
        }
    }
    
    public void firstFrame() {
        this.frameCur = 0;
    }
    
    public void init() {
        this.addMouseListener(this);
        if (this.getParameter("size") != null && this.getParameter("size").equals("small")) {
            this.small = true;
        }
        if (this.getParameter("click") != null) {
            this.linkURL = this.getParameter("click");
        }
        else {
            this.linkURL = "";
        }
        this.bkColor = new Color(140, 180, 80);
        this.paintlock = "ON";
        System.out.println("JVMU v" + this.version + " - rednuht@rocketmail.com ");
        this.convert();
        if (!this.small) {
            this.loadScr = this.createImage(new MemoryImageSource(this.RowLenB, this.ColTotalB, this.imageLoadArray, 0, this.RowLenB));
        }
        else {
            this.loadScr = this.createImage(new MemoryImageSource(this.RowLen, this.ColTotal, this.imageLoadArraySmall, 0, this.RowLen));
        }
        this.convertColor(20, 19);
        this.loadSwirl = this.createImage(new MemoryImageSource(20, 19, this.imageLoadSwirl, 0, 20));
        final Dimension size = this.size();
        this.aHeight = size.height;
        this.aWidth = size.width;
        this.offScreenBuf = this.createImage(this.aWidth, this.aHeight);
        this.loader.setFile(this.getParameter("file"), this.getCodeBase());
        this.loader.start();
        this.paintlock = "OFF";
        this.showStatus("Loading " + this.getParameter("file"));
    }
    
    public void lastFrame() {
        if (this.loader == null) {
            System.out.println(".-.");
        }
        else {
            this.frameCur = this.loader.noFrames - 1;
        }
    }
    
    public void loadAnim(final String s) {
        if (this.loader == null) {
            System.out.println(".-.");
        }
        else {
            Runtime.getRuntime();
            this.showStatus("Loading " + s);
            if (!this.loader.loading) {
                this.frameCur = 0;
                this.speed = 150;
                this.pause = false;
                this.loader.loading = false;
                this.loader = null;
                System.gc();
                (this.loader = new frameLoader()).setFile(s, this.getCodeBase());
                this.loader.start();
            }
            else {
                this.showStatus("Please Wait for current Animation to load completly.");
            }
        }
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        if (this.linkURL != "") {
            try {
                URL url;
                if (this.linkURL.startsWith("http://")) {
                    url = new URL(this.linkURL);
                }
                else {
                    url = new URL(this.getCodeBase(), this.linkURL);
                }
                this.getAppletContext().showDocument(url, "_blank");
            }
            catch (MalformedURLException ex) {
                System.out.println("URL: could not be constructed");
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
    }
    
    public void nextFrame() {
        if (this.loader == null) {
            System.out.println(".-.");
        }
        else {
            ++this.frameCur;
            if (this.frameCur >= this.loader.noFrames) {
                this.frameCur = 0;
            }
        }
    }
    
    public void nextFramer() {
        this.paintlock = "ON";
        if (this.loader == null) {
            System.out.println(".-.");
        }
        else if (!this.loader.loading && this.loader.loadError == "") {
            this.showStatus("Animation Loaded");
            final aFrame aFrame = this.loader.anim.elementAt(this.frameCur);
            if (!this.pause) {
                ++this.delayCounter;
            }
            if (this.delayCounter >= aFrame.duration) {
                this.delayCounter = 0;
                ++this.frameCur;
                if (this.frameCur >= this.loader.noFrames) {
                    this.frameCur = 0;
                }
            }
        }
        else if (this.loader.loadError == "") {
            this.showStatus("Animation Loading ...");
            if (!this.small) {
                switch (this.swirlD) {
                    case 0: {
                        this.swirlX += 2;
                        this.swirlY += 2;
                        if (this.swirlX >= this.aWidth - 20) {
                            this.swirlD = 1;
                            break;
                        }
                        if (this.swirlY >= this.aHeight - 55) {
                            this.swirlD = 2;
                            break;
                        }
                        break;
                    }
                    case 1: {
                        this.swirlX -= 2;
                        this.swirlY += 2;
                        if (this.swirlX <= 0) {
                            this.swirlD = 0;
                            break;
                        }
                        if (this.swirlY >= this.aHeight - 55) {
                            this.swirlD = 3;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        this.swirlX += 2;
                        this.swirlY -= 2;
                        if (this.swirlX >= this.aWidth - 20) {
                            this.swirlD = 3;
                            break;
                        }
                        if (this.swirlY <= 0) {
                            this.swirlD = 0;
                            break;
                        }
                        break;
                    }
                    case 3: {
                        this.swirlX -= 2;
                        this.swirlY -= 2;
                        if (this.swirlX <= 0) {
                            this.swirlD = 2;
                            break;
                        }
                        if (this.swirlY <= 0) {
                            this.swirlD = 1;
                            break;
                        }
                        break;
                    }
                }
            }
            else {
                switch (this.swirlD) {
                    case 0: {
                        this.swirlX += 2;
                        this.swirlY += 2;
                        if (this.swirlX >= this.aWidth - 10) {
                            this.swirlD = 1;
                            break;
                        }
                        if (this.swirlY >= this.aHeight - 10) {
                            this.swirlD = 2;
                            break;
                        }
                        break;
                    }
                    case 1: {
                        this.swirlX -= 2;
                        this.swirlY += 2;
                        if (this.swirlX <= 0) {
                            this.swirlD = 0;
                            break;
                        }
                        if (this.swirlY >= this.aHeight - 10) {
                            this.swirlD = 3;
                            break;
                        }
                        break;
                    }
                    case 2: {
                        this.swirlX += 2;
                        this.swirlY -= 2;
                        if (this.swirlX >= this.aWidth - 10) {
                            this.swirlD = 3;
                            break;
                        }
                        if (this.swirlY <= 0) {
                            this.swirlD = 0;
                            break;
                        }
                        break;
                    }
                    case 3: {
                        this.swirlX -= 2;
                        this.swirlY -= 2;
                        if (this.swirlX <= 0) {
                            this.swirlD = 2;
                            break;
                        }
                        if (this.swirlY <= 0) {
                            this.swirlD = 1;
                            break;
                        }
                        break;
                    }
                }
            }
        }
        this.paintlock = "OFF";
    }
    
    public void paint(final Graphics graphics) {
        if (this.loader == null) {
            System.out.println(".-.");
        }
        else if (this.paintlock == "OFF") {
            if (!this.small) {
                this.incPerAnim = 128.0 / this.loader.noFrames;
            }
            else {
                this.incPerFrame = 43.0 / this.pageSize;
                this.incPerAnim = 43.0 / this.loader.noFrames;
            }
            (this.gBuff = this.offScreenBuf.getGraphics()).setColor(this.bkColor);
            this.gBuff.fillRect(0, 0, this.aWidth, this.aHeight);
            if (this.loader.loading) {
                if (!this.small) {
                    this.gBuff.drawImage(this.loadSwirl, this.swirlX, this.swirlY, this);
                }
                else {
                    this.gBuff.drawImage(this.loadSwirl, this.swirlX, this.swirlY, 10, 8, this);
                }
                this.gBuff.setColor(new Color(0, 0, 0));
                final int n = (int)(this.incPerAnim * this.loader.frameCur);
                final int n2 = (int)(this.incPerFrame * this.loader.frameCurLoaded);
                if (!this.small) {
                    this.gBuff.fillRect(10, 101, n, 2);
                    this.gBuff.fillRect(10, 117, n2, 2);
                    this.gBuff.setColor(this.bkColor);
                    for (int i = 9; i < 136; i += 3) {
                        this.gBuff.drawLine(i, 100, i, 120);
                    }
                }
                else {
                    this.gBuff.fillRect(3, 26, n, 1);
                    this.gBuff.fillRect(3, 30, n2, 1);
                }
                this.gBuff.drawImage(this.loadScr, 1, 1, this);
            }
            else if (this.loader.loadError == "") {
                final aFrame aFrame = this.loader.anim.elementAt(this.frameCur);
                if (!this.small) {
                    this.gBuff.drawImage(aFrame.img, 1, 1, this);
                }
                else {
                    this.gBuff.drawImage(aFrame.imgSmall, 1, 1, this);
                }
            }
            else if (this.loader.loadError.endsWith("InternetReadFile")) {
                this.bkColor = new Color(0, 0, 0);
                this.gBuff.setColor(new Color(255, 0, 0));
                if (!this.small) {
                    this.gBuff.drawImage(this.loadScr, 1, -30, this);
                    this.gBuff.fillRect(1, 70, this.aWidth, this.aHeight);
                }
                else {
                    this.gBuff.drawImage(this.loadScr, 1, -7, this);
                    this.gBuff.fillRect(1, 17, this.aWidth, this.aHeight);
                }
                if (!this.small) {
                    this.gBuff.drawImage(this.loadSwirl, this.aWidth / 2, this.aHeight / 2, this);
                }
                else {
                    this.gBuff.drawImage(this.loadSwirl, this.aWidth / 2, this.aHeight / 2, 10, 8, this);
                }
                this.linkURL = "problems.html#ie";
                this.showStatus("Internet Explorer Java VM out of date, http://www.microsoft.com/ie");
            }
            else {
                this.gBuff.setColor(new Color(255, 0, 0));
                if (!this.small) {
                    this.gBuff.drawImage(this.loadScr, 1, -30, this);
                    this.gBuff.fillRect(1, 70, this.aWidth, this.aHeight);
                }
                else {
                    this.gBuff.drawImage(this.loadScr, 1, -7, this);
                    this.gBuff.fillRect(1, 17, this.aWidth, this.aHeight);
                }
                if (!this.small) {
                    this.gBuff.drawImage(this.loadSwirl, this.aWidth / 2, this.aHeight / 2, this);
                }
                else {
                    this.gBuff.drawImage(this.loadSwirl, this.aWidth / 2, this.aHeight / 2, 10, 8, this);
                }
                this.linkURL = "problems.html";
                this.showStatus("Loading of this animation has failed, please select a new animation..");
            }
            graphics.drawImage(this.offScreenBuf, 0, 0, this);
            this.gBuff.dispose();
        }
    }
    
    public void prevFrame() {
        if (this.loader == null) {
            System.out.println(".-.");
        }
        else {
            --this.frameCur;
            if (this.frameCur <= 0) {
                this.frameCur = this.loader.noFrames - 1;
            }
        }
    }
    
    public void resetColor() {
        this.bkColor = new Color(this.Randomize(200) + 50, this.Randomize(200) + 50, this.Randomize(200) + 50);
    }
    
    public void run() {
        while (true) {
            try {
                Thread.sleep(this.speed);
            }
            catch (InterruptedException ex) {}
            this.nextFramer();
            this.repaint();
        }
    }
    
    public void slower() {
        this.speed += 30;
    }
    
    public void start() {
        if (this.sequence == null) {
            (this.sequence = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.sequence != null && this.sequence.isAlive()) {
            this.sequence.stop();
        }
        this.sequence = null;
    }
    
    public void togglePause() {
        if (this.loader == null) {
            System.out.println(".-.");
        }
        else if (!this.loader.loading) {
            if (!this.pause) {
                this.pause = true;
            }
            else {
                this.pause = false;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
