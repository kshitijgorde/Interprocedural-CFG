import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class NewLineDemo extends Applet implements Runnable
{
    double red;
    double green;
    double blue;
    int check;
    Thread t;
    Thread t1;
    Thread t2;
    Thread change;
    Lines[] r;
    int total;
    Image Buffer;
    Graphics gBuffer;
    int j;
    int w;
    int h;
    
    public NewLineDemo() {
        this.red = 0.5;
        this.green = 1.1;
        this.blue = 1.1;
        this.total = 25;
    }
    
    public void init() {
        this.Buffer = this.createImage(this.size().width, this.size().height);
        this.gBuffer = this.Buffer.getGraphics();
        this.r = new Lines[this.total];
        this.w = this.size().width;
        this.h = this.size().height;
        this.j = 0;
        while (this.j < this.total) {
            this.r[this.j] = new Lines(this.w, this.h);
            ++this.j;
        }
        this.t1 = new Thread(this);
        this.t2 = new Thread(this);
        this.change = new Thread(this);
        this.t1.start();
        this.t2.start();
        this.change.start();
    }
    
    public void run() {
        this.t = Thread.currentThread();
        if (this.t == this.t1) {
            while (true) {
                try {
                    Thread.sleep(20L);
                }
                catch (Exception ex) {}
                this.repaint();
            }
        }
        else if (this.t == this.t2) {
            while (true) {
                this.red = (int)(Math.random() * 255.0);
                this.green = (int)(Math.random() * 255.0);
                this.blue = (int)(Math.random() * 255.0);
                try {
                    Thread.sleep(500L);
                }
                catch (Exception ex2) {}
                this.repaint();
            }
        }
        else {
            if (this.t != this.change) {
                return;
            }
            while (true) {
                this.check = 0;
                while (this.check <= 7000) {
                    try {
                        Thread.sleep(10L);
                    }
                    catch (Exception ex3) {}
                    this.repaint();
                    ++this.check;
                }
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.gBuffer.setColor(Color.black);
        this.gBuffer.fillRect(0, 0, this.size().width, this.size().height);
        if (this.red <= 20.0) {
            this.red = 100.0;
        }
        this.gBuffer.setColor(new Color((int)this.red, (int)this.blue, (int)this.green));
        if (this.check > 0 && this.check <= 2000) {
            for (int i = 0; i < this.total; ++i) {
                this.r[i].move1();
            }
        }
        if (this.check >= 2000 && this.check <= 4000) {
            for (int j = 0; j < this.total; ++j) {
                this.r[j].move2();
            }
        }
        if (this.check >= 4000 && this.check <= 6000) {
            for (int k = 0; k < this.total; ++k) {
                this.r[k].move3();
            }
        }
        if (this.check >= 6000 && this.check <= 7000) {
            for (int l = 0; l < this.total; ++l) {
                this.r[l].move4();
            }
        }
        for (int n = 0; n < this.total; ++n) {
            this.r[n].paint(this.gBuffer);
        }
        graphics.drawImage(this.Buffer, 0, 0, this);
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
