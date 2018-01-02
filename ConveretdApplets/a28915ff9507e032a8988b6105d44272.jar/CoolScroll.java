import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.image.MemoryImageSource;
import java.awt.Image;
import java.awt.Color;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class CoolScroll extends Applet implements Runnable
{
    private Thread m_CoolScroll;
    boolean asodreadisegna;
    private double rad;
    private double rad_inc;
    private double RAD;
    private double ang_x;
    private double angolo_x;
    private double inc_factor;
    int veloce;
    char[] msg;
    int[] msgbounceindex;
    int[] lametable;
    int xadd;
    int scrollpos;
    int[] mirrorpic;
    int[] swappic;
    int startx;
    int starty;
    int msglenght;
    Color forecolor;
    Color backcolor;
    Image backdrop;
    Image appletimage;
    Image cropfont;
    Image upperimage;
    Image lowerimage;
    int redmaskadd;
    int greenmaskadd;
    int bluemaskadd;
    int redadd;
    int greenadd;
    int blueadd;
    Image letter;
    int extent_x;
    int extent_y;
    int letterwidth;
    int letterheight;
    private MemoryImageSource screenMem;
    Graphics gfx;
    Graphics lettergfx;
    Graphics appletgfx;
    
    public void stop() {
        if (this.m_CoolScroll != null) {
            this.m_CoolScroll.stop();
            this.m_CoolScroll = null;
        }
    }
    
    public CoolScroll() {
        this.rad = 20.0;
        this.rad_inc = 0.5;
        this.RAD = 0.017453292519444445;
        this.inc_factor = 1.5;
        this.redmaskadd = 90;
        this.greenmaskadd = 120;
        this.bluemaskadd = 140;
        this.redadd = 5;
        this.greenadd = 5;
        this.blueadd = 5;
    }
    
    public void paint(final Graphics g) {
        if (this.appletgfx != null) {
            g.drawImage(this.appletimage, 0, 0, this);
        }
    }
    
    private void BounceScroll() {
        final int retval = 0;
        for (int loopx = 0; loopx < this.msglenght; ++loopx) {
            if (this.scrollpos + loopx * this.letterwidth < this.extent_x && this.scrollpos + loopx * this.letterwidth > this.letterwidth * -1) {
                this.getletterIndex(this.msg[loopx]);
                this.lettergfx.drawImage(this.backdrop, -(this.scrollpos + loopx * this.letterwidth), -(1 + this.lametable[this.msgbounceindex[loopx]]), this);
                this.lettergfx.drawImage(this.cropfont, -(this.startx * this.letterwidth), -(this.starty * this.letterheight), this);
                this.gfx.drawImage(this.letter, this.scrollpos + loopx * this.letterwidth, 1 + this.lametable[this.msgbounceindex[loopx]], this);
                this.lettergfx.fillRect(0, 0, this.letterwidth, this.letterheight);
            }
            if (++this.msgbounceindex[loopx] > 15) {
                this.msgbounceindex[loopx] = 0;
            }
        }
        this.scrollpos -= this.veloce;
        if (this.scrollpos * -1 > this.msglenght * this.letterwidth) {
            this.scrollpos = this.extent_x;
        }
        this.angolo_x += this.inc_factor;
        if (this.angolo_x > 90.0 || this.angolo_x < 0.0) {
            this.inc_factor *= -1.0;
        }
        final double CX = Math.cos(this.angolo_x * this.RAD);
        this.xadd = (int)(this.angolo_x * CX);
    }
    
    public void update(final Graphics g) {
        this.gfx.drawImage(this.backdrop, 0, 0, this);
        this.BounceScroll();
        this.loadPix();
        this.renderPix();
        this.appletgfx.drawImage(this.upperimage, 0, 0, this.extent_x * 2, this.extent_y * 2, this);
        this.appletgfx.drawImage(this.lowerimage, 0, this.extent_y * 2, this.extent_x * 2, this.extent_y * 2 + this.xadd, this);
        this.paint(g);
    }
    
    private void renderPix() {
        int punto = 0;
        int themask = 0;
        int red = 0;
        int green = 0;
        int blue = 0;
        int yindex = this.extent_y - 1;
        this.redmaskadd += this.redadd;
        this.greenmaskadd += this.greenadd;
        this.bluemaskadd += this.blueadd;
        if (this.redmaskadd == 255 || this.redmaskadd == 90) {
            this.redadd *= -1;
        }
        if (this.greenmaskadd == 255 || this.greenmaskadd == 90) {
            this.greenadd *= -1;
        }
        if (this.bluemaskadd == 255 || this.bluemaskadd == 90) {
            this.blueadd *= -1;
        }
        for (int loopy = 0; loopy < this.extent_y; ++loopy) {
            for (int loopx = 0; loopx < this.extent_x; ++loopx) {
                punto = this.swappic[this.extent_x * yindex + loopx];
                themask = (punto & 0xFF0000);
                red = themask >> 16;
                themask = (punto & 0xFF00);
                green = themask >> 8;
                themask = (blue = (punto & 0xFF));
                red = red;
                green = green;
                red = red * this.redmaskadd << 8;
                green *= this.greenmaskadd;
                blue = blue * this.bluemaskadd >> 8;
                this.mirrorpic[this.extent_x * loopy + loopx] = (0xFF000000 | (red & 0xFF0000) | (green & 0xFF00) | (blue & 0xFF));
            }
            --yindex;
        }
        this.lowerimage = this.createImage(this.screenMem);
    }
    
    public void start() {
        if (this.m_CoolScroll == null) {
            (this.m_CoolScroll = new Thread(this)).start();
        }
    }
    
    private void loadPix() {
        final int punto = 0;
        final int themask = 0;
        this.asodreadisegna = true;
        final PixelGrabber grabber = new PixelGrabber(this.upperimage, 0, 0, this.extent_x, this.extent_y, this.swappic, 0, this.extent_x);
        boolean done = false;
        do {
            try {
                done = grabber.grabPixels(500L);
            }
            catch (InterruptedException ex) {}
        } while (!done);
        this.asodreadisegna = false;
    }
    
    private void getletterIndex(final int ASCIIcode) {
        int retval = 0;
        retval = ASCIIcode - 32;
        if (retval <= 0) {
            this.startx = 0;
            this.starty = 0;
        }
        else {
            this.starty = retval / 10;
            this.startx = retval % 10;
        }
    }
    
    public void run() {
        while (true) {
            try {
                this.repaint();
                Thread.sleep(50L);
                System.gc();
            }
            catch (InterruptedException e) {
                this.stop();
            }
        }
    }
    
    public void init() {
        String messaggio = this.getParameter("scrolltext");
        if (messaggio == null) {
            messaggio = "SAY SOMETHING TO THE WORLD...WRITE IT!";
        }
        this.veloce = Integer.parseInt(this.getParameter("scrollspeed"));
        this.letterwidth = Integer.parseInt(this.getParameter("fontx"));
        this.letterheight = Integer.parseInt(this.getParameter("fonty"));
        this.letter = this.createImage(this.letterwidth, this.letterheight);
        this.lettergfx = this.letter.getGraphics();
        this.appletimage = this.createImage(this.size().width, this.size().height);
        this.appletgfx = this.appletimage.getGraphics();
        this.extent_x = this.size().width / 2;
        this.extent_y = this.size().height / 4;
        this.upperimage = this.createImage(this.extent_x, this.extent_y);
        this.gfx = this.upperimage.getGraphics();
        this.scrollpos = this.extent_x;
        this.swappic = new int[this.extent_x * this.extent_y + this.extent_x];
        this.mirrorpic = new int[this.extent_x * this.extent_y + this.extent_x];
        this.msglenght = messaggio.length();
        this.msg = new char[this.msglenght + 1];
        this.msgbounceindex = new int[this.msglenght + 1];
        this.lametable = new int[16];
        this.loadlameBounce();
        int nowind = 0;
        for (int loop = 0; loop < this.msglenght; ++loop) {
            this.msgbounceindex[loop] = nowind;
            if (++nowind > 15) {
                nowind = 0;
            }
        }
        this.msg = messaggio.toCharArray();
        final MediaTracker tracker = new MediaTracker(this);
        tracker.addImage(this.cropfont = this.getImage(this.getDocumentBase(), this.getParameter("fontimage")), 0);
        try {
            tracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        final MediaTracker tracker2 = new MediaTracker(this);
        tracker2.addImage(this.backdrop = this.getImage(this.getDocumentBase(), this.getParameter("backimage")), 1);
        try {
            tracker2.waitForID(1);
        }
        catch (InterruptedException ex2) {}
        this.forecolor = new Color(255, 0, 0);
        this.backcolor = new Color(0, 0, 0);
        this.screenMem = new MemoryImageSource(this.extent_x, this.extent_y, this.mirrorpic, 0, this.extent_x);
    }
    
    private void loadlameBounce() {
        this.lametable[0] = 0;
        this.lametable[1] = 0;
        this.lametable[2] = 0;
        this.lametable[3] = 1;
        this.lametable[4] = 1;
        this.lametable[5] = 2;
        this.lametable[6] = 4;
        this.lametable[7] = 6;
        this.lametable[8] = 10;
        this.lametable[9] = 15;
        this.lametable[10] = 10;
        this.lametable[11] = 6;
        this.lametable[12] = 4;
        this.lametable[13] = 2;
        this.lametable[14] = 1;
        this.lametable[15] = 1;
    }
}
