import java.awt.image.ImageObserver;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class RMS_HR1 extends Applet implements Runnable
{
    Color strobe;
    Color bgcolor;
    Color[] pencolor;
    int steps;
    int[] ly;
    int redno;
    int greenno;
    int blueno;
    int i;
    int j;
    String temp;
    int direction;
    int appwidth;
    int appheight;
    Image winScratch;
    Graphics gScratch;
    Thread timer;
    String author;
    String author1;
    String author2;
    String author3;
    String author4;
    String author5;
    
    public RMS_HR1() {
        this.strobe = new Color(0, 0, 0);
        this.bgcolor = new Color(255, 255, 255);
        this.steps = 16;
        this.direction = 1;
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
        this.temp = this.getParameter("strobe");
        if (this.temp != null) {
            this.strobe = new Color(Integer.parseInt(this.temp, 16));
        }
        this.temp = this.getParameter("bgcolor");
        if (this.temp != null) {
            this.bgcolor = new Color(Integer.parseInt(this.temp, 16));
        }
        this.setBackground(this.bgcolor);
        this.pencolor = new Color[this.steps + 1];
        this.ly = new int[this.steps + 1];
        this.i = 0;
        while (this.i < this.steps) {
            this.ly[this.i] = this.i;
            ++this.i;
        }
        this.pencolor[0] = new Color(Integer.parseInt(this.getParameter("bgcolor"), 16));
        this.pencolor[this.steps] = new Color(Integer.parseInt(this.getParameter("strobe"), 16));
        this.redno = (this.pencolor[this.steps].getRed() - this.bgcolor.getRed()) / (this.steps - 1);
        this.greenno = (this.pencolor[this.steps].getGreen() - this.bgcolor.getGreen()) / (this.steps - 1);
        this.blueno = (this.pencolor[this.steps].getBlue() - this.bgcolor.getBlue()) / (this.steps - 1);
        this.i = 1;
        while (this.i < this.steps) {
            this.pencolor[this.i] = new Color(this.bgcolor.getRed() + this.i * this.redno, this.bgcolor.getGreen() + this.i * this.greenno, this.bgcolor.getBlue() + this.i * this.blueno);
            ++this.i;
        }
        final Dimension size = this.getSize();
        this.appwidth = size.width;
        this.appheight = size.height;
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
                Thread.sleep(10L);
            }
            catch (InterruptedException ex) {}
            this.i = 0;
            while (this.i < this.ly.length - 1) {
                this.ly[this.i] = this.ly[this.i + 1];
                ++this.i;
            }
            this.ly[this.ly.length - 1] += this.direction;
            if (this.ly[this.ly.length - 1] >= this.appwidth || this.ly[this.ly.length - 1] <= 0) {
                this.direction *= -1;
            }
            this.repaint();
        }
    }
    
    public void paint(final Graphics graphics) {
        this.gScratch.setColor(this.bgcolor);
        this.gScratch.fillRect(0, 0, this.appwidth, this.appheight);
        if (this.author.equals(this.author5)) {
            this.j = 0;
            while (this.j < this.ly.length) {
                this.gScratch.setColor(this.pencolor[this.j]);
                this.gScratch.drawLine(this.ly[this.j], 0, this.ly[this.j], this.appheight);
                ++this.j;
            }
        }
        graphics.drawImage(this.winScratch, 0, 0, this);
    }
    
    public final void update(final Graphics graphics) {
        this.paint(graphics);
    }
}
