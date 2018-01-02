import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.Color;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class zSlideShow2 extends Applet implements Runnable
{
    Image offI;
    Image image1;
    Image image2;
    Image image3;
    Image image4;
    Image image5;
    Image image6;
    Graphics offG;
    Thread mythread;
    int ypos;
    int count;
    String i1;
    String i2;
    String i3;
    String i4;
    String i5;
    String i6;
    MediaTracker mt;
    
    public void init() {
        this.setBackground(Color.white);
        this.i1 = this.getParameter("image1");
        this.i2 = this.getParameter("image2");
        this.i3 = this.getParameter("image3");
        this.i4 = this.getParameter("image4");
        this.i5 = this.getParameter("image5");
        this.i6 = this.getParameter("image6");
        this.image1 = this.getImage(this.getCodeBase(), this.i1);
        this.image2 = this.getImage(this.getCodeBase(), this.i2);
        this.image3 = this.getImage(this.getCodeBase(), this.i3);
        this.image4 = this.getImage(this.getCodeBase(), this.i4);
        this.image5 = this.getImage(this.getCodeBase(), this.i5);
        this.image6 = this.getImage(this.getCodeBase(), this.i6);
        this.offI = this.createImage(this.size().width, this.size().height);
        this.offG = this.offI.getGraphics();
        this.ypos = -this.size().height;
        (this.mt = new MediaTracker(this)).addImage(this.image1, 0);
        this.mt.addImage(this.image2, 0);
        this.mt.addImage(this.image3, 0);
        this.mt.addImage(this.image4, 0);
        this.mt.addImage(this.image5, 0);
        this.mt.addImage(this.image6, 0);
    }
    
    public void paint(final Graphics g) {
        if (this.mt.checkAll(true)) {
            this.offG.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
            g.drawImage(this.offI, 0, 0, this);
        }
        else {
            g.drawRect(0, 0, this.size().width - 1, this.size().height - 1);
            g.drawString("Loading...", this.size().width / 2 - 30, this.size().height / 2);
        }
    }
    
    public void start() {
        (this.mythread = new Thread(this)).start();
    }
    
    public void run() {
        try {
            this.mt.waitForAll();
        }
        catch (Exception ex) {}
        while (true) {
            if (this.i1.length() != 0) {
                this.down(this.image1);
                this.ypos = -this.size().height;
                this.pause();
                this.count = 0;
            }
            if (this.i2.length() != 0) {
                this.down(this.image2);
                this.ypos = -this.size().height;
                this.pause();
                this.count = 0;
            }
            if (this.i3.length() != 0) {
                this.down(this.image3);
                this.ypos = -this.size().height;
                this.pause();
                this.count = 0;
            }
            if (this.i4.length() != 0) {
                this.down(this.image4);
                this.ypos = -this.size().height;
                this.pause();
                this.count = 0;
            }
            if (this.i5.length() != 0) {
                this.down(this.image5);
                this.ypos = -this.size().height;
                this.pause();
                this.count = 0;
            }
            if (this.i6.length() != 0) {
                this.down(this.image6);
                this.ypos = -this.size().height;
                this.pause();
                this.count = 0;
            }
        }
    }
    
    public void stop() {
        this.mythread.stop();
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void down(final Image image) {
        while (this.ypos < 1) {
            try {
                Thread.sleep(1L);
                this.offG.drawImage(image, 0, this.ypos, this.size().width, this.size().height, this);
                this.ypos += 2;
                this.repaint();
            }
            catch (Exception ex) {}
        }
        while (this.ypos > -40) {
            try {
                Thread.sleep(1L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.drawImage(image, 0, this.ypos, this.size().width, this.size().height, this);
                this.ypos -= 2;
                this.repaint();
            }
            catch (Exception ex2) {}
        }
        while (this.ypos < 1) {
            try {
                Thread.sleep(1L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.drawImage(image, 0, this.ypos, this.size().width, this.size().height, this);
                this.ypos += 2;
                this.repaint();
            }
            catch (Exception ex3) {}
        }
        while (this.ypos > -30) {
            try {
                Thread.sleep(1L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.drawImage(image, 0, this.ypos, this.size().width, this.size().height, this);
                this.ypos -= 2;
                this.repaint();
            }
            catch (Exception ex4) {}
        }
        while (this.ypos < 1) {
            try {
                Thread.sleep(1L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.drawImage(image, 0, this.ypos, this.size().width, this.size().height, this);
                this.ypos += 2;
                this.repaint();
            }
            catch (Exception ex5) {}
        }
        while (this.ypos > -20) {
            try {
                Thread.sleep(1L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.drawImage(image, 0, this.ypos, this.size().width, this.size().height, this);
                this.ypos -= 2;
                this.repaint();
            }
            catch (Exception ex6) {}
        }
        while (this.ypos < 1) {
            try {
                Thread.sleep(1L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.drawImage(image, 0, this.ypos, this.size().width, this.size().height, this);
                this.ypos += 2;
                this.repaint();
            }
            catch (Exception ex7) {}
        }
        while (this.ypos > -10) {
            try {
                Thread.sleep(1L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.drawImage(image, 0, this.ypos, this.size().width, this.size().height, this);
                this.ypos -= 2;
                this.repaint();
            }
            catch (Exception ex8) {}
        }
        while (this.ypos < 1) {
            try {
                Thread.sleep(1L);
                this.offG.clearRect(0, 0, this.size().width, this.size().height);
                this.offG.drawImage(image, 0, this.ypos, this.size().width, this.size().height, this);
                this.ypos += 2;
                this.repaint();
            }
            catch (Exception ex9) {}
        }
    }
    
    public void pause() {
        while (this.count < 30) {
            try {
                Thread.sleep(100L);
                ++this.count;
            }
            catch (Exception ex) {}
        }
    }
    
    public boolean mouseMove(final Event evt, final int x, final int y) {
        this.showStatus("Applet from Plamen Gelev - Unregistered");
        return true;
    }
}
