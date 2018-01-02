import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class RMS_HR3 extends Applet implements Runnable
{
    Color[] star;
    Color[] darkstar;
    Color bgcolor;
    int[] starX;
    int[] starY;
    int[] starStatus;
    int[] starColour;
    int stars;
    int i;
    int j;
    String temp;
    int appwidth;
    int appheight;
    boolean OK;
    Image winScratch;
    Graphics gScratch;
    Thread timer;
    String author;
    String author1;
    String author2;
    String author3;
    String author4;
    String author5;
    
    public RMS_HR3() {
        this.bgcolor = new Color(0, 0, 0);
        this.stars = 20;
        this.OK = false;
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
        this.j = 0;
        this.OK = true;
        while (this.OK) {
            this.temp = this.getParameter("star" + this.j);
            if (this.temp != null) {
                ++this.j;
            }
            else {
                this.OK = false;
            }
        }
        this.star = new Color[this.j];
        this.darkstar = new Color[this.j];
        this.i = 0;
        while (this.i < this.j) {
            this.star[this.i] = new Color(255, 255, 255);
            this.temp = this.getParameter("star" + this.i);
            if (this.temp != null) {
                this.star[this.i] = new Color(Integer.parseInt(this.temp, 16));
                this.darkstar[this.i] = this.star[this.i].darker();
            }
            ++this.i;
        }
        this.temp = this.getParameter("bgcolor");
        if (this.temp != null) {
            this.bgcolor = new Color(Integer.parseInt(this.temp, 16));
        }
        this.setBackground(this.bgcolor);
        this.temp = this.getParameter("stars");
        if (this.temp != null) {
            this.stars = Integer.parseInt(this.temp);
        }
        final Dimension size = this.getSize();
        this.appwidth = size.width;
        this.appheight = size.height;
        this.starX = new int[this.stars];
        this.starY = new int[this.stars];
        this.starStatus = new int[this.stars];
        this.starColour = new int[this.stars];
        this.i = 0;
        while (this.i < this.starStatus.length) {
            this.starX[this.i] = (int)((this.appwidth - 6) * Math.random()) + 3;
            this.starY[this.i] = (int)((this.appheight - 8) * Math.random()) + 4;
            this.starStatus[this.i] = (int)(9.0 * Math.random());
            this.starColour[this.i] = (int)(this.star.length * Math.random());
            ++this.i;
        }
        this.winScratch = this.createImage(this.appwidth, this.appheight);
        this.gScratch = this.winScratch.getGraphics();
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
                Thread.sleep(50L);
            }
            catch (InterruptedException ex) {}
            this.j = 0;
            while (this.j < this.starStatus.length) {
                final int[] starStatus = this.starStatus;
                final int j = this.j;
                ++starStatus[j];
                if (this.starStatus[this.j] == 9) {
                    this.starStatus[this.j] = 0;
                    this.starX[this.j] = (int)((this.appwidth - 6) * Math.random()) + 3;
                    this.starY[this.j] = (int)((this.appheight - 8) * Math.random()) + 4;
                    this.starColour[this.j] = (int)(this.star.length * Math.random());
                }
                ++this.j;
            }
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        this.gScratch.setColor(this.bgcolor);
        this.gScratch.fillRect(0, 0, this.appwidth, this.appheight);
        if (this.author.equals(this.author5)) {
            this.i = 0;
            while (this.i < this.starStatus.length) {
                this.gScratch.setColor(this.darkstar[this.starColour[this.i]]);
                if (this.starStatus[this.i] > 3 && this.starStatus[this.i] < 5) {
                    this.gScratch.drawLine(this.starX[this.i], this.starY[this.i] - 3, this.starX[this.i], this.starY[this.i] + 3);
                    this.gScratch.drawLine(this.starX[this.i] - 2, this.starY[this.i], this.starX[this.i] + 2, this.starY[this.i]);
                }
                if (this.starStatus[this.i] > 2 && this.starStatus[this.i] < 6) {
                    this.gScratch.drawLine(this.starX[this.i], this.starY[this.i] - 2, this.starX[this.i], this.starY[this.i] + 2);
                }
                if (this.starStatus[this.i] > 1 && this.starStatus[this.i] < 7) {
                    this.gScratch.drawLine(this.starX[this.i] - 1, this.starY[this.i], this.starX[this.i] + 1, this.starY[this.i]);
                    this.gScratch.drawLine(this.starX[this.i], this.starY[this.i] - 1, this.starX[this.i], this.starY[this.i] + 1);
                }
                if (this.starStatus[this.i] > 0) {
                    this.gScratch.setColor(this.star[this.starColour[this.i]]);
                    this.gScratch.drawLine(this.starX[this.i], this.starY[this.i], this.starX[this.i], this.starY[this.i]);
                }
                ++this.i;
            }
        }
        graphics.drawImage(this.winScratch, 0, 0, this);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
