import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class jb extends Applet implements Runnable
{
    Thread thr;
    Image[] img;
    int intCounter;
    int intI;
    int intInc;
    
    public void init() {
        for (int i = 0; i < 10; ++i) {
            this.intI = i + 1;
            this.img[i] = this.getImage(this.getCodeBase(), "jb" + this.intI + ".jpg");
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
        while (true) {
            this.repaint();
            try {
                Thread.currentThread();
                Thread.sleep(150L);
            }
            catch (InterruptedException ex) {}
            this.getNextTaj();
        }
    }
    
    public void getNextTaj() {
        this.intCounter += this.intInc;
        if (this.intCounter == 0) {
            try {
                Thread.currentThread();
                Thread.sleep(1500L);
            }
            catch (InterruptedException ex) {}
        }
        if (this.intCounter == 9 || this.intCounter == 0) {
            this.intInc *= -1;
        }
    }
    
    public void paint(final Graphics graphics) {
        graphics.drawImage(this.img[this.intCounter], 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public jb() {
        this.img = new Image[10];
        this.intInc = 1;
    }
}
