import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class ZSlideShow extends Applet implements Runnable
{
    Thread mythread;
    Image offI;
    Image i1;
    Image i2;
    Image i3;
    Image i4;
    Image i5;
    Image i6;
    Graphics offG;
    int xpos;
    int ypos;
    int count;
    String s1;
    String s2;
    String s3;
    String s4;
    String s5;
    String s6;
    String c;
    StringTokenizer t;
    Color color;
    MediaTracker mt;
    
    public void init() {
        this.c = this.getParameter("color");
        this.t = new StringTokenizer(this.c, ";");
        this.setBackground(this.color = new Color(Integer.parseInt(this.t.nextToken()), Integer.parseInt(this.t.nextToken()), Integer.parseInt(this.t.nextToken())));
        this.offI = this.createImage(this.size().width, this.size().height);
        this.offG = this.offI.getGraphics();
        this.s1 = this.getParameter("image1");
        this.s2 = this.getParameter("image2");
        this.s3 = this.getParameter("image3");
        this.s4 = this.getParameter("image4");
        this.s5 = this.getParameter("image5");
        this.s6 = this.getParameter("image6");
        this.i1 = this.getImage(this.getCodeBase(), this.s1);
        this.i2 = this.getImage(this.getCodeBase(), this.s2);
        this.i3 = this.getImage(this.getCodeBase(), this.s3);
        this.i4 = this.getImage(this.getCodeBase(), this.s4);
        this.i5 = this.getImage(this.getCodeBase(), this.s5);
        this.i6 = this.getImage(this.getCodeBase(), this.s6);
        (this.mt = new MediaTracker(this)).addImage(this.i1, 0);
        this.mt.addImage(this.i2, 0);
        this.mt.addImage(this.i3, 0);
        this.mt.addImage(this.i4, 0);
        this.mt.addImage(this.i5, 0);
        this.mt.addImage(this.i6, 0);
    }
    
    public void paint(final Graphics graphics) {
        if (this.mt.checkAll(true)) {
            graphics.drawImage(this.offI, 0, 0, this);
        }
        else {
            graphics.drawString("Loading images...", 20, this.size().height / 2);
        }
    }
    
    public void start() {
        (this.mythread = new Thread(this)).start();
    }
    
    public void run() {
        try {
            this.mt.waitForAll();
        }
        catch (InterruptedException ex) {}
        while (true) {
            this.slide(this.i1);
            this.count = 0;
            this.slide(this.i2);
            this.count = 0;
            this.slide(this.i3);
            this.count = 0;
            this.slide(this.i4);
            this.count = 0;
            this.slide(this.i5);
            this.count = 0;
            this.slide(this.i6);
            this.count = 0;
        }
    }
    
    public void stop() {
        this.mythread.stop();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void slide(final Image image) {
        while (this.count < 100) {
            try {
                Thread.sleep(100L);
                for (int i = 0; i < 200; ++i) {
                    this.xpos = (int)(Math.random() * this.size().width - 5.0);
                    this.ypos = (int)(Math.random() * this.size().height - 5.0);
                    this.offG.setClip(this.xpos, this.ypos, 5, 5);
                    this.offG.drawImage(image, 2, 2, this.size().width - 4, this.size().height - 4, this);
                }
                ++this.count;
                this.repaint();
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        this.count = 100;
        return true;
    }
    
    public boolean mouseMove(final Event event, final int n, final int n2) {
        this.showStatus("Unregistered version");
        return true;
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus("Unregistered version");
        return true;
    }
}
