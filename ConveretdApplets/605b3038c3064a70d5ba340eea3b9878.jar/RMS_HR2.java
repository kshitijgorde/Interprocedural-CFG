import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class RMS_HR2 extends Applet implements Runnable
{
    Color strobe;
    Color bgcolor;
    int i;
    String temp;
    int appwidth;
    int appheight;
    float colour;
    Image winScratch;
    Graphics gScratch;
    Thread timer;
    String author;
    String author1;
    String author2;
    String author3;
    String author4;
    String author5;
    
    public RMS_HR2() {
        this.strobe = new Color(0, 0, 0);
        this.bgcolor = new Color(255, 255, 255);
        this.colour = 0.0f;
        this.timer = null;
        this.author = " ";
        this.author1 = "w";
        this.author2 = "rmspencer";
        this.author3 = "clara.net";
        this.author4 = ".";
        this.author5 = this.author1 + this.author1 + this.author1 + this.author4 + this.author2 + this.author4 + this.author3;
    }
    
    public void init() {
        this.temp = this.getParameter("author");
        if (this.temp != null) {
            this.author = this.temp;
        }
        this.setBackground(this.bgcolor);
        final Dimension size = this.getSize();
        this.appwidth = size.width;
        this.appheight = size.height;
        this.winScratch = this.createImage(this.appwidth, this.appheight);
        this.gScratch = this.winScratch.getGraphics();
        this.i = this.appwidth;
        while (this.i > 0) {
            this.colour += 0.005f;
            if (this.colour > 1.0) {
                this.colour = 0.0f;
            }
            this.gScratch.copyArea(0, 0, this.appwidth, this.appheight, 1, 0);
            this.gScratch.setColor(Color.getHSBColor(this.colour, 0.7f, 0.7f));
            this.gScratch.fillRect(0, 0, 1, this.appheight);
            --this.i;
        }
    }
    
    public void start() {
        if (this.timer == null) {
            (this.timer = new Thread(this)).start();
        }
    }
    
    public void stop() {
        this.timer = null;
    }
    
    public void run() {
        while (this.timer != null) {
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex) {}
            this.colour += 0.005f;
            if (this.colour > 1.0) {
                this.colour = 0.0f;
            }
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.author.equals(this.author5)) {
            this.gScratch.copyArea(0, 0, this.appwidth, this.appheight, 1, 0);
            this.gScratch.setColor(Color.getHSBColor(this.colour, 0.7f, 0.7f));
            this.gScratch.fillRect(0, 0, 1, this.appheight);
        }
        graphics.drawImage(this.winScratch, 0, 0, this);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
