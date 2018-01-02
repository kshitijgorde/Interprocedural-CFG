import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SpinTop extends Applet implements Runnable
{
    Thread spinThread;
    Image[] img;
    int counter;
    int clockwise;
    
    public void init() {
        for (int i = 0; i < 18; ++i) {
            this.img[i] = this.getImage(this.getCodeBase(), "tops" + i + ".gif");
        }
    }
    
    public void nextSpin() {
        if (this.clockwise == 1) {
            ++this.counter;
            if (this.counter == 18) {
                this.counter = 0;
            }
        }
        else {
            --this.counter;
            if (this.counter < 0) {
                this.counter = 17;
            }
        }
    }
    
    public void update(final Graphics graphics) {
        graphics.drawImage(this.img[this.counter], 0, 0, this);
    }
    
    public void start() {
        if (this.spinThread == null) {
            (this.spinThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.spinThread != null) {
            this.spinThread.stop();
            this.spinThread = null;
        }
    }
    
    public void run() {
        while (this.spinThread != null) {
            this.repaint();
            try {
                Thread.sleep(75L);
            }
            catch (InterruptedException ex) {}
            this.nextSpin();
        }
    }
    
    public boolean handleEvent(final Event event) {
        if (event.id == 501) {
            this.clockwise = 1 - this.clockwise;
        }
        return true;
    }
    
    public SpinTop() {
        this.img = new Image[18];
        this.clockwise = 1;
    }
}
