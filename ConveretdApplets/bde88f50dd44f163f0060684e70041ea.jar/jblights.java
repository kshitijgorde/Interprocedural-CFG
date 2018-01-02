import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class jblights extends Applet implements Runnable
{
    Thread thr;
    Image[] img;
    final int MAIN_IMAGE = 0;
    int intCounter;
    int intI;
    int intInc;
    MediaTracker tracker;
    boolean bShow;
    String strMsg;
    Image imgMain;
    int intNap;
    
    public void init() {
        for (int i = 0; i < 12; ++i) {
            this.intI = i + 1;
            this.img[i] = this.getImage(this.getCodeBase(), "Lens" + this.intI + ".jpg");
            this.imgMain = this.getImage(this.getCodeBase(), "Lens1.jpg");
            (this.tracker = new MediaTracker(this)).addImage(this.imgMain, 0);
        }
    }
    
    public void start() {
        if (this.thr == null) {
            (this.thr = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.thr != null) {
            this.thr.stop();
            this.thr = null;
        }
    }
    
    public void run() {
        try {
            this.tracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        if (this.tracker.isErrorID(0)) {
            this.strMsg = "error";
        }
        else {
            this.bShow = true;
        }
        while (true) {
            this.repaint();
            try {
                Thread.currentThread();
                Thread.sleep(this.intNap);
            }
            catch (InterruptedException ex2) {}
            this.getNextTaj();
        }
    }
    
    public void getNextTaj() {
        this.intCounter += this.intInc;
        if (this.intCounter == 11 || this.intCounter == 0) {
            this.intInc *= -1;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.bShow) {
            graphics.drawImage(this.img[this.intCounter], 0, 0, this);
            return;
        }
        graphics.drawString(this.strMsg, 20, 20);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public jblights() {
        this.img = new Image[12];
        this.intInc = 1;
        this.bShow = false;
        this.strMsg = "Loading...";
        this.intNap = 150;
    }
}
