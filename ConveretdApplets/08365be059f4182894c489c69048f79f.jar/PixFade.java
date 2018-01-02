import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.image.ImageObserver;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.awt.LayoutManager;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.image.MemoryImageSource;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class PixFade extends Applet implements Runnable
{
    private Thread myThread;
    private Image bufferimage;
    private Graphics bufferg;
    private Image nextimage;
    private Graphics nextg;
    private Image currentImage;
    private Graphics currentg;
    private int[] currentpixels;
    private int[] nextpixels;
    Image myImage;
    MemoryImageSource source;
    MemoryImageSource source2;
    private String[] strings;
    private Color[] colors;
    private Color bgcolor;
    private int speed;
    private int pixchange;
    private int fontsize;
    private String fontname;
    int numberOfStrings;
    private Dimension d;
    private int h;
    private int w;
    private String tempStr;
    
    public String getAppletInfo() {
        return "Name: Pixfade\r\nAuthor: Mats Palm\r\nCreated: Jan 01, 2000\r\nE-Mail: mats_p@algonet.se\r\nTool: Visual Cafe";
    }
    
    public void init() {
        this.numberOfStrings = this.getIntegerParameter("numberOfStrings", 10);
        this.speed = this.getIntegerParameter("speed", 10);
        this.pixchange = this.getIntegerParameter("pixchange", 10);
        this.fontname = this.getParameter("fontname");
        this.fontsize = this.getIntegerParameter("fontsize", 10);
        this.strings = new String[this.numberOfStrings];
        for (int q = 0; q < this.numberOfStrings; ++q) {
            this.strings[q] = this.getParameter("string" + Integer.toString(q));
        }
        this.colors = new Color[this.numberOfStrings];
        for (int q2 = 0; q2 < this.numberOfStrings; ++q2) {
            this.tempStr = "color" + Integer.toString(q2);
            this.colors[q2] = new Color(this.getIntegerParameter(this.tempStr, 16));
        }
        this.setBackground(this.bgcolor = new Color(this.getIntegerParameter("bgcolor", 16)));
        this.setLayout(null);
        this.d = this.size();
        this.w = this.d.width;
        this.h = this.d.height;
        System.err.println(String.valueOf(this.h) + " " + this.w);
        this.currentpixels = new int[this.w * this.h];
        this.nextpixels = new int[this.w * this.h];
        this.bufferimage = this.createImage(this.w, this.h);
        this.bufferg = this.bufferimage.getGraphics();
        this.currentImage = this.createImage(this.w, this.h);
        this.currentg = this.currentImage.getGraphics();
        this.nextimage = this.createImage(this.w, this.h);
        this.nextg = this.nextimage.getGraphics();
        (this.source = new MemoryImageSource(this.w, this.h, this.currentpixels, 0, this.w)).setAnimated(true);
        this.myImage = this.createImage(this.source);
    }
    
    public void run() {
        while (this.myThread != null) {
            for (int stringNo = 0; stringNo < this.numberOfStrings; ++stringNo) {
                this.nextpaint(this.getGraphics(), this.strings[stringNo], 1, 25, this.colors[stringNo]);
                this.grabNextPicture();
                for (int i = 0; i < this.speed; ++i) {
                    this.mixColors();
                    try {
                        Thread.sleep(10L);
                    }
                    catch (InterruptedException ex) {}
                }
            }
        }
    }
    
    public void mixColors() {
        for (int i = 1; i < this.pixchange; ++i) {
            final int x = (int)(Math.random() * (this.w * this.h));
            this.currentpixels[x] = this.nextpixels[x];
        }
        this.source.newPixels();
    }
    
    public void grabNextPicture() {
        final PixelGrabber pg2 = new PixelGrabber(this.nextimage, 0, 0, this.w, this.h, this.nextpixels, 0, this.w);
        try {
            pg2.grabPixels();
        }
        catch (Exception ex) {
            System.err.println("interrupted waiting for pixels");
        }
    }
    
    public void update(final Graphics g) {
        this.paint(g);
    }
    
    public void paint(final Graphics g) {
        this.bufferg.drawImage(this.myImage, 0, 0, this);
        g.drawImage(this.bufferimage, 0, 0, this);
    }
    
    public void nextpaint(final Graphics g, final String mytext, final int x, final int y, final Color fgc) {
        final Font f = new Font(this.fontname, 1, this.fontsize);
        final FontMetrics fm = this.getFontMetrics(f);
        this.nextg.setFont(f);
        final int xstart = (this.w - fm.stringWidth(mytext)) / 2;
        final int ystart = (this.h + fm.getHeight()) / 2;
        this.nextg.clearRect(0, 0, this.w, this.h);
        this.nextg.setColor(fgc);
        this.nextg.drawString(mytext, xstart, ystart);
    }
    
    public void start() {
        if (this.myThread == null) {
            (this.myThread = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.myThread != null) {
            this.myThread.stop();
            this.myThread = null;
        }
    }
    
    public int getIntegerParameter(final String name, final int base) {
        final String value = this.getParameter(name);
        int intvalue;
        try {
            intvalue = Integer.parseInt(value, base);
        }
        catch (NumberFormatException ex) {
            return 0;
        }
        return intvalue;
    }
    
    public PixFade() {
        this.tempStr = new String();
    }
}
