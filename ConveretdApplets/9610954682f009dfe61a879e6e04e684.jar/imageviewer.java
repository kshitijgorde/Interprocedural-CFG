import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Component;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Font;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class imageviewer extends Applet implements Runnable
{
    Image image1;
    Image image2;
    Image image3;
    Image image4;
    Image image5;
    Image image6;
    int click;
    int count;
    String p1;
    String p2;
    String p3;
    String p4;
    String p5;
    String p6;
    Font font;
    MediaTracker mytracker;
    Thread mythread;
    
    public imageviewer() {
        this.click = 0;
    }
    
    public void init() {
        this.p1 = this.getParameter("image1");
        this.p2 = this.getParameter("image2");
        this.p3 = this.getParameter("image3");
        this.p4 = this.getParameter("image4");
        this.p5 = this.getParameter("image5");
        this.p6 = this.getParameter("image6");
        this.setBackground(Color.white);
        this.image1 = this.getImage(this.getCodeBase(), this.p1);
        this.image2 = this.getImage(this.getCodeBase(), this.p2);
        this.image3 = this.getImage(this.getCodeBase(), this.p3);
        this.image4 = this.getImage(this.getCodeBase(), this.p4);
        this.image5 = this.getImage(this.getCodeBase(), this.p5);
        this.image6 = this.getImage(this.getCodeBase(), this.p6);
        this.setFont(this.font = new Font("TimesRoman", 1, 24));
        (this.mytracker = new MediaTracker(this)).addImage(this.image1, 0);
        this.mytracker.addImage(this.image2, 0);
        this.mytracker.addImage(this.image2, 0);
        this.mytracker.addImage(this.image4, 0);
        this.mytracker.addImage(this.image5, 0);
        this.mytracker.addImage(this.image6, 0);
    }
    
    public void paint(final Graphics graphics) {
        graphics.setColor(Color.red);
        if (this.click == 0) {
            if (this.mytracker.checkID(0)) {
                graphics.drawImage(this.image1, 0, 0, 120, 100, this);
                graphics.drawImage(this.image2, 120, 0, 120, 100, this);
                graphics.drawImage(this.image3, 240, 0, 120, 100, this);
                graphics.drawImage(this.image4, 0, 100, 120, 100, this);
                graphics.drawImage(this.image5, 120, 100, 120, 100, this);
                graphics.drawImage(this.image6, 240, 100, 120, 100, this);
                graphics.fillRect(0, 0, 360, 5);
                graphics.fillRect(355, 0, 5, 240);
                graphics.fillRect(0, 195, 360, 5);
                graphics.fillRect(0, 0, 5, 240);
                graphics.fillRect(0, 235, 360, 5);
                graphics.drawString("Click to enlarge", 100, 225);
                this.mythread.suspend();
            }
            else {
                graphics.drawString("Loading images...", 90, 110);
                graphics.fillRect(0, 0, 360, 5);
                graphics.fillRect(355, 0, 5, 240);
                graphics.fillRect(0, 195, 360, 5);
                graphics.fillRect(0, 0, 5, 240);
                graphics.fillRect(0, 235, 360, 5);
            }
        }
        if (this.click == 1) {
            if (this.count == 1) {
                graphics.drawImage(this.image1, 0, 0, 360, 240, this);
                graphics.fillRect(0, 0, 360, 5);
                graphics.fillRect(355, 0, 5, 240);
                graphics.fillRect(0, 0, 5, 240);
                graphics.fillRect(0, 235, 360, 5);
                graphics.drawString("Click to return", 100, 225);
            }
            if (this.count == 2) {
                graphics.drawImage(this.image2, 0, 0, 360, 240, this);
                graphics.fillRect(0, 0, 360, 5);
                graphics.fillRect(355, 0, 5, 240);
                graphics.fillRect(0, 0, 5, 240);
                graphics.fillRect(0, 235, 360, 5);
                graphics.drawString("Click to return", 100, 225);
            }
            if (this.count == 3) {
                graphics.drawImage(this.image3, 0, 0, 360, 240, this);
                graphics.fillRect(0, 0, 360, 5);
                graphics.fillRect(355, 0, 5, 240);
                graphics.fillRect(0, 0, 5, 240);
                graphics.fillRect(0, 235, 360, 5);
                graphics.drawString("Click to return", 100, 225);
            }
            if (this.count == 4) {
                graphics.drawImage(this.image4, 0, 0, 360, 240, this);
                graphics.fillRect(0, 0, 360, 5);
                graphics.fillRect(355, 0, 5, 240);
                graphics.fillRect(0, 0, 5, 240);
                graphics.fillRect(0, 235, 360, 5);
                graphics.drawString("Click to return", 100, 225);
            }
            if (this.count == 5) {
                graphics.drawImage(this.image5, 0, 0, 360, 240, this);
                graphics.fillRect(0, 0, 360, 5);
                graphics.fillRect(355, 0, 5, 240);
                graphics.fillRect(0, 0, 5, 240);
                graphics.fillRect(0, 235, 360, 5);
                graphics.drawString("Click to return", 100, 225);
            }
            if (this.count == 6) {
                graphics.drawImage(this.image6, 0, 0, 360, 240, this);
                graphics.fillRect(0, 0, 360, 5);
                graphics.fillRect(355, 0, 5, 240);
                graphics.fillRect(0, 0, 5, 240);
                graphics.fillRect(0, 235, 360, 5);
                graphics.drawString("Click to return", 100, 225);
            }
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.click == 0) {
            this.click = 1;
            if (n < 120 && n2 < 100) {
                this.count = 1;
            }
            if (n > 120 && n2 < 100 && n < 240) {
                this.count = 2;
            }
            if (n > 240 && n2 < 100) {
                this.count = 3;
            }
            if (n < 120 && n2 > 100) {
                this.count = 4;
            }
            if (n > 120 && n2 > 100 && n < 240) {
                this.count = 5;
            }
            if (n > 240 && n2 > 100) {
                this.count = 6;
            }
            this.repaint();
        }
        else {
            this.click = 0;
            this.repaint();
        }
        return true;
    }
    
    public void start() {
        (this.mythread = new Thread(this)).start();
    }
    
    public void run() {
    Label_0003:
        while (true) {
            break Label_0003;
            while (true) {
                try {
                    while (true) {
                        Thread.sleep(1000L);
                        this.mytracker.checkID(0, true);
                        this.repaint();
                    }
                }
                catch (InterruptedException ex) {
                    continue;
                }
                continue Label_0003;
            }
            break;
        }
    }
    
    public void stop() {
        this.mythread.stop();
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.showStatus("Unregistered");
        return true;
    }
}
