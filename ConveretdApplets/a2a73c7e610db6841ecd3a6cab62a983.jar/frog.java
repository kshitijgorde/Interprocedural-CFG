import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Component;
import java.awt.image.ImageFilter;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public final class frog extends Applet implements Runnable
{
    int i;
    int j;
    int k;
    int counter;
    int[] flyX;
    int[] flyY;
    int[] flyOldX;
    int[] flyOldY;
    int[] flyDx;
    int[] flyDy;
    int nearX;
    int nearY;
    int nearNum;
    int eatDx;
    int eatDy;
    int eatState;
    Color bg_col;
    int[] flyBaseX;
    int[] flyBaseY;
    int[] tongueX;
    int[] tongueY;
    double tongue;
    boolean[] flyOn;
    int currHead;
    final int[] headList;
    double headcount;
    Image collection;
    Image offImage;
    Image frog;
    Image[] frogheads;
    Image legs;
    Image[] flies;
    Graphics offGraphics;
    MediaTracker tracker;
    ImageFilter filter;
    private volatile Thread updateThread;
    Math m;
    
    public frog() {
        this.counter = 0;
        this.eatState = 0;
        this.tongueX = new int[] { 18, 22, 0, 0 };
        this.tongueY = new int[] { 27, 28, 0, 0 };
        this.flyOn = new boolean[5];
        this.currHead = 0;
        this.headList = new int[] { 0, 1, 2, 3, 2, 1 };
        this.headcount = 0.0;
    }
    
    public void init() {
        try {
            this.bg_col = new Color(Integer.parseInt(this.getParameter("bgcolor"), 16));
        }
        catch (Exception ex) {}
        this.setBackground(this.bg_col);
        this.offImage = this.createImage(96, 60);
        this.offGraphics = this.offImage.getGraphics();
        this.tracker = new MediaTracker(this);
        this.collection = this.getImage(this.getCodeBase(), "./img/froggies.gif");
        this.tracker.addImage(this.collection, 0);
        try {
            this.tracker.waitForID(0);
        }
        catch (InterruptedException ex2) {}
        this.frogheads = new Image[8];
        this.flies = new Image[3];
        this.filter = new CropImageFilter(0, 0, 96, 60);
        this.frog = this.createImage(new FilteredImageSource(this.collection.getSource(), this.filter));
        this.tracker.addImage(this.frog, 1);
        this.i = 0;
        while (this.i < 4) {
            this.j = 0;
            while (this.j < 2) {
                this.filter = new CropImageFilter(this.j * 48, 60 + this.i * 40, 48, 40);
                this.frogheads[this.i * 2 + this.j] = this.createImage(new FilteredImageSource(this.collection.getSource(), this.filter));
                this.tracker.addImage(this.frogheads[this.i * 2 + this.j], 1);
                ++this.j;
            }
            ++this.i;
        }
        this.filter = new CropImageFilter(48, 40, 48, 20);
        this.legs = this.createImage(new FilteredImageSource(this.collection.getSource(), this.filter));
        this.tracker.addImage(this.legs, 1);
        this.j = 0;
        while (this.j < 3) {
            this.filter = new CropImageFilter(this.j * 13, 220, 13, 8);
            this.flies[this.j] = this.createImage(new FilteredImageSource(this.collection.getSource(), this.filter));
            this.tracker.addImage(this.flies[this.j], 1);
            ++this.j;
        }
        this.flyX = new int[5];
        this.flyY = new int[5];
        this.flyOldX = new int[5];
        this.flyOldY = new int[5];
        this.flyDx = new int[5];
        this.flyDy = new int[5];
        this.flyBaseX = new int[5];
        this.flyBaseY = new int[5];
        try {
            this.tracker.waitForID(1);
        }
        catch (InterruptedException ex3) {}
        this.resize(500, 60);
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.frog, 0, 0, this);
    }
    
    public void run() {
        while (this.updateThread == Thread.currentThread()) {
            try {
                Thread.sleep(75L);
            }
            catch (InterruptedException ex) {}
            this.counter = (this.counter + 1) % 30;
            this.nearX = 500;
            this.nearY = 0;
            this.nearNum = -1;
            this.j = 0;
            while (this.j < 5) {
                if (this.flyOn[this.j]) {
                    this.flyOldX[this.j] = this.flyX[this.j];
                    this.flyOldY[this.j] = this.flyY[this.j];
                    if (this.counter == this.j) {
                        this.flyBaseX[this.j] = (int)(100.0 + Math.random() * 400.0);
                    }
                    if (this.counter == this.j + 10) {
                        this.flyBaseY[this.j] = (int)(16.0 + Math.random() * 19.0);
                    }
                    if (this.flyX[this.j] < this.flyBaseX[this.j] && this.flyDx[this.j] < 11) {
                        final int[] flyDx = this.flyDx;
                        final int j = this.j;
                        flyDx[j] += 2;
                    }
                    else if (this.flyX[this.j] > this.flyBaseX[this.j] && this.flyDx[this.j] > -11) {
                        final int[] flyDx2 = this.flyDx;
                        final int i = this.j;
                        flyDx2[i] -= 2;
                    }
                    if (this.flyY[this.j] < this.flyBaseY[this.j] && this.flyDy[this.j] < 7) {
                        final int[] flyDy = this.flyDy;
                        final int k = this.j;
                        flyDy[k] += 2;
                    }
                    else if (this.flyY[this.j] > this.flyBaseY[this.j] && this.flyDy[this.j] > -7) {
                        final int[] flyDy2 = this.flyDy;
                        final int l = this.j;
                        flyDy2[l] -= 2;
                    }
                    final int[] flyX = this.flyX;
                    final int m = this.j;
                    flyX[m] += this.flyDx[this.j];
                    final int[] flyY = this.flyY;
                    final int j2 = this.j;
                    flyY[j2] += this.flyDy[this.j];
                    if (this.flyX[this.j] < this.nearX) {
                        this.nearX = this.flyX[this.j];
                        this.nearY = this.flyY[this.j];
                        this.nearNum = this.j;
                    }
                }
                else if (Math.random() < 0.01) {
                    this.flyX[this.j] = 500;
                    this.flyY[this.j] = 25;
                    this.flyBaseY[this.j] = 25;
                    this.flyDx[this.j] = 0;
                    this.flyDy[this.j] = 0;
                    this.flyOn[this.j] = true;
                }
                ++this.j;
            }
            switch (this.eatState) {
                case 0: {
                    this.headcount += 0.003 * (700 - this.nearX);
                    if (this.headcount > 12.0) {
                        this.headcount -= 6.0;
                    }
                    this.currHead = this.headList[(int)this.headcount % 6];
                    if (this.nearX > 96 && this.nearX < 130 && this.nearY < 40 && this.nearY > 6) {
                        this.eatState = 1;
                        this.eatDx = this.nearX - 59;
                        this.eatDy = this.nearY - 22;
                        this.tongue = 1.0;
                        this.flyOn[this.nearNum] = false;
                        this.currHead = 4;
                        break;
                    }
                    break;
                }
                case 1: {
                    this.tongue -= 0.3;
                    if (this.tongue < 0.0) {
                        this.eatState = 2;
                        this.headcount = 0.0;
                        this.currHead = 5;
                        break;
                    }
                    break;
                }
                case 2: {
                    this.headcount += 0.1;
                    if (this.headcount < 0.6) {
                        this.currHead = 5;
                        break;
                    }
                    if (this.headcount < 3.3) {
                        this.currHead = 6 + ((int)(this.headcount * 2.0) & 0x1);
                        break;
                    }
                    if (this.headcount < 4.4) {
                        this.currHead = 5;
                        break;
                    }
                    this.eatState = 0;
                    break;
                }
            }
            this.repaint();
        }
    }
    
    public void start() {
        if (this.updateThread == null) {
            (this.updateThread = new Thread(this, "Flies")).start();
        }
    }
    
    public void stop() {
        this.updateThread = null;
    }
    
    public void update(final Graphics graphics) {
        this.offGraphics.setColor(this.bg_col);
        this.offGraphics.fillRect(0, 0, 96, 60);
        this.offGraphics.drawImage(this.legs, 0, 40, this);
        this.offGraphics.drawImage(this.frogheads[this.currHead], 0, 0, this);
        if (this.eatState == 1) {
            this.offGraphics.setColor(Color.red);
            this.tongueX[2] = (int)(18.0 + this.tongue * this.eatDx);
            this.tongueY[2] = (int)(30.0 + this.tongue * this.eatDy);
            this.tongueX[3] = this.tongueX[2];
            this.tongueY[3] = (int)(28.0 + this.tongue * this.eatDy);
            this.offGraphics.fillPolygon(this.tongueX, this.tongueY, 4);
            this.offGraphics.drawPolygon(this.tongueX, this.tongueY, 4);
            this.offGraphics.drawImage(this.flies[2], (int)(11.0 + this.tongue * this.eatDx), (int)(25.0 + this.tongue * this.eatDy), this);
        }
        graphics.setColor(this.bg_col);
        graphics.fillRect(144, 0, 356, 60);
        graphics.drawImage(this.offImage, 48, 0, this);
        this.k = 0;
        while (this.k < 5) {
            if (this.flyOn[this.k]) {
                graphics.drawImage(this.flies[this.counter & 0x1], this.flyX[this.k], this.flyY[this.k], this);
            }
            ++this.k;
        }
    }
}
