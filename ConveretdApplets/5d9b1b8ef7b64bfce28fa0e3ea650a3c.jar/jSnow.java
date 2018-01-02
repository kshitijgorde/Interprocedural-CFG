import java.awt.Component;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class jSnow extends Applet implements Runnable
{
    String paramString;
    int[] x;
    int[] y;
    int[] dy;
    int[] xoff;
    int[] Sin;
    int numFlakes;
    int nx;
    int ny;
    int nw;
    int nh;
    Image nissen;
    Image maanen;
    Image fjellet;
    int mx;
    int fy;
    MediaTracker tracker;
    int height;
    int width;
    Image bgImg;
    Graphics bgGrap;
    Color bgCol;
    Color fgCol;
    Thread runner;
    int index;
    
    public void start() {
        if (this.runner == null) {
            (this.runner = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.runner != null) {
            this.runner.stop();
            this.runner = null;
        }
    }
    
    public String getAppletInfo() {
        return new String("jSnow Applet by Lars Nicolai L\u00f8vdal\n" + "Feel free to use this applet at your own page!!\n\n" + "For more of my applets:\n" + "http://home.sol.no/~llovdal/\n");
    }
    
    public void run() {
        while (this.runner != null) {
            this.nx += 4;
            if (this.nx > this.width) {
                this.nx = this.nw * -1;
                this.ny = (int)(Math.random() * (this.height - this.nh));
            }
            this.index = 0;
            while (this.index < this.numFlakes) {
                final int[] y = this.y;
                final int index = this.index;
                y[index] += this.dy[this.index];
                final int[] xoff = this.xoff;
                final int index2 = this.index;
                xoff[index2] += this.dy[this.index];
                final int[] xoff2 = this.xoff;
                final int index3 = this.index;
                xoff2[index3] &= 0xF;
                if (this.y[this.index] > this.height) {
                    this.x[this.index] = (int)(Math.random() * this.width);
                    this.y[this.index] = -1;
                    this.dy[this.index] = (int)(Math.random() * 5.0) + 1;
                    this.xoff[this.index] = (int)(Math.random() * 16.0);
                }
                ++this.index;
            }
            this.bgGrap.setColor(this.bgCol);
            this.bgGrap.fillRect(0, 0, this.width, this.height);
            this.bgGrap.drawImage(this.maanen, this.mx, 0, this);
            this.bgGrap.drawImage(this.fjellet, 0, this.fy, this.width, 44, this);
            this.bgGrap.drawImage(this.nissen, this.nx, this.ny, this);
            this.bgGrap.setColor(this.fgCol);
            this.index = 0;
            while (this.index < this.numFlakes) {
                this.bgGrap.drawLine(this.x[this.index] + this.Sin[this.xoff[this.index]], this.y[this.index], this.x[this.index] + this.Sin[this.xoff[this.index]], this.y[this.index] + 1);
                ++this.index;
            }
            this.repaint();
            try {
                Thread.sleep(25L);
            }
            catch (InterruptedException ex) {}
        }
    }
    
    public void init() {
        this.nissen = this.getImage(this.getCodeBase(), "santa.gif");
        this.maanen = this.getImage(this.getCodeBase(), "moon.gif");
        this.fjellet = this.getImage(this.getCodeBase(), "fjell.gif");
        (this.tracker = new MediaTracker(this)).addImage(this.nissen, 1);
        this.tracker.addImage(this.maanen, 2);
        this.tracker.addImage(this.fjellet, 3);
        this.bgCol = new Color(0, 0, 74);
        this.fgCol = new Color(255, 255, 255);
        this.height = this.size().height;
        this.width = this.size().width;
        this.bgImg = this.createImage(this.width, this.height);
        (this.bgGrap = this.bgImg.getGraphics()).setColor(this.bgCol);
        this.bgGrap.fillRect(0, 0, this.width, this.height);
        this.paramString = this.getParameter("numFlakes");
        if (this.paramString == null) {
            this.numFlakes = 500;
        }
        try {
            this.numFlakes = Integer.parseInt(this.paramString);
        }
        catch (NumberFormatException wakeUp) {
            this.numFlakes = 500;
        }
        this.x = new int[this.numFlakes];
        this.y = new int[this.numFlakes];
        this.dy = new int[this.numFlakes];
        this.xoff = new int[this.numFlakes];
        this.index = 0;
        while (this.index < this.numFlakes) {
            this.x[this.index] = (int)(Math.random() * this.width);
            this.y[this.index] = (int)(Math.random() * this.height);
            this.dy[this.index] = (int)(Math.random() * 5.0) + 1;
            this.xoff[this.index] = (int)(Math.random() * 16.0);
            ++this.index;
        }
        this.Sin = new int[16];
        this.index = 0;
        while (this.index < 16) {
            this.Sin[this.index] = (int)(Math.cos(0.39269908169872414 * this.index) * 4.0);
            ++this.index;
        }
        try {
            this.tracker.waitForAll();
        }
        catch (InterruptedException ex) {}
        this.nw = this.nissen.getWidth(this);
        this.nh = this.nissen.getHeight(this);
        this.nx = this.nw * -1;
        this.ny = (int)(Math.random() * (this.height - this.nh));
        this.mx = this.width - this.maanen.getWidth(this);
        this.fy = this.height - this.fjellet.getHeight(this);
    }
    
    public void paint(final Graphics g) {
        g.drawImage(this.bgImg, 0, 0, this);
    }
    
    public void update(final Graphics g) {
        g.drawImage(this.bgImg, 0, 0, this);
    }
}
