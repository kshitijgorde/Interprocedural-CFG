import java.awt.Dimension;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.Event;
import java.awt.Font;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class aipconvex extends Applet implements Runnable
{
    Thread t;
    boolean suspended;
    String infile;
    Image photo;
    Image oimg;
    Graphics og;
    int width;
    int height;
    MediaTracker tracker;
    float maxConvex;
    boolean loaded;
    int radius;
    int random;
    int gflag;
    int step;
    int sx;
    int sy;
    int centerX;
    int centerY;
    int delay;
    static int[] data;
    static int[] dataNew;
    
    public void init() {
        super.init();
        this.repaint();
        this.infile = this.getParameter("file");
        this.delay = new Integer(this.getParameter("delay"));
        this.radius = new Integer(this.getParameter("radius"));
        this.maxConvex = new Float(this.getParameter("maxconvex"));
        this.step = new Integer(this.getParameter("step"));
        this.gflag = new Integer(this.getParameter("blackandwhite"));
        this.tracker = new MediaTracker(this);
        this.photo = this.getImage(this.getDocumentBase(), this.infile);
        this.tracker.addImage(this.photo, 0);
        this.waitForImage();
        this.width = this.photo.getWidth(this);
        this.height = this.photo.getHeight(this);
        this.resize(this.width, this.height);
        aipconvex.data = new int[this.height * this.width];
        aipconvex.dataNew = new int[this.height * this.width];
        this.grabPixels();
        this.buildImage();
        this.oimg = this.createImage(this.width, this.height);
        this.og = this.oimg.getGraphics();
    }
    
    public void start() {
        if (this.t == null) {
            (this.t = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.t != null && this.t.isAlive()) {
            this.t.stop();
        }
        this.t = null;
    }
    
    public void run() {
        Thread.currentThread().setPriority(4);
        while (true) {
            this.repaint();
            try {
                Thread.sleep(this.delay);
            }
            catch (InterruptedException ex) {}
            this.program();
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (!this.loaded) {
            graphics.setColor(Color.white);
            graphics.fillRect(0, 0, this.width - 1, this.height - 1);
            graphics.setColor(Color.blue);
            graphics.drawString("Loading...", 3, 40);
            this.loaded = true;
            return;
        }
        if (this.photo != null) {
            this.og.drawImage(this.photo, 0, 0, this);
            this.photo = null;
        }
        this.og.setFont(new Font("Helvetica", 0, 8));
        final int n = this.og.getFontMetrics().stringWidth("C.Liu 97-99") + 4;
        final int height = this.og.getFontMetrics().getHeight();
        this.og.setColor(new Color(0, 255, 255));
        this.og.fillRoundRect(this.width - n - 2, this.height - height - 2, n + 1, height, 6, 6);
        this.og.setColor(new Color(0, 0, 255));
        this.og.drawRoundRect(this.width - n - 2, this.height - height - 2, n + 1, height, 6, 6);
        this.og.setColor(new Color(0, 0, 255));
        this.og.drawString("C.Liu 97-99", this.width - n + 1, this.height - 4);
        graphics.drawImage(this.oimg, 0, 0, this);
    }
    
    private void program() {
        if (this.step == 0) {
            this.centerX = (int)(Math.random() * (this.width - 1));
            this.centerY = (int)(Math.random() * (this.height - 1));
        }
        else {
            if (this.sx < 0 && this.centerX - this.radius < 0) {
                this.sx = (int)(Math.random() * (this.step + 1) + 1.0);
            }
            else if (this.sx >= 0 && this.centerX + this.radius > this.width - 1) {
                this.sx = -(int)(Math.random() * (this.step + 1) + 1.0);
            }
            this.centerX += this.sx;
            if (this.sy < 0 && this.centerY - this.radius < 0) {
                this.sy = (int)(Math.random() * (this.step + 1) + 1.0);
            }
            else if (this.sy >= 0 && this.centerY + this.radius > this.height - 1) {
                this.sy = -(int)(Math.random() * (this.step + 1) + 1.0);
            }
            this.centerY += this.sy;
        }
        for (int i = 0; i < this.height; ++i) {
            for (int j = 0; j < this.width; ++j) {
                final int n = j - this.centerX;
                final int n2 = i - this.centerY;
                final float n3 = (float)Math.sqrt(n * n + n2 * n2);
                float n4 = 1.0f;
                if (n3 < this.radius) {
                    n4 = this.maxConvex - (this.maxConvex - 1.0f) * n3 / this.radius;
                }
                final float n5 = n / n4 + this.centerX;
                final float n6 = n2 / n4 + this.centerY;
                if (n3 < this.radius) {
                    if (n5 > 0.0f && n5 < this.width - 1 && n6 > 0.0f && n6 < this.height - 1) {
                        final float n7 = n5 - (int)n5;
                        final float n8 = n6 - (int)n6;
                        this.setRGB(i, j, (int)((1.0f - n7) * (1.0f - n8) * this.red((int)n6, (int)n5) + (1.0f - n7) * n8 * this.red((int)n6 + 1, (int)n5) + n7 * (1.0f - n8) * this.red((int)n6, (int)n5 + 1) + n7 * n8 * this.red((int)n6 + 1, (int)n5 + 1)), (int)((1.0f - n7) * (1.0f - n8) * this.green((int)n6, (int)n5) + (1.0f - n7) * n8 * this.green((int)n6 + 1, (int)n5) + n7 * (1.0f - n8) * this.green((int)n6, (int)n5 + 1) + n7 * n8 * this.green((int)n6 + 1, (int)n5 + 1)), (int)((1.0f - n7) * (1.0f - n8) * this.blue((int)n6, (int)n5) + (1.0f - n7) * n8 * this.blue((int)n6 + 1, (int)n5) + n7 * (1.0f - n8) * this.blue((int)n6, (int)n5 + 1) + n7 * n8 * this.blue((int)n6 + 1, (int)n5 + 1)));
                    }
                    else {
                        this.setRGB(i, j, 255, 255, 255);
                    }
                }
                else if (this.gflag == 1) {
                    final int n9 = (this.red(i, j) + this.green(i, j) + this.blue(i, j)) / 3;
                    this.setRGB(i, j, n9, n9, n9);
                }
                else {
                    aipconvex.dataNew[i * this.width + j] = aipconvex.data[i * this.width + j];
                }
            }
        }
        this.buildImage();
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.suspended) {
            this.t.resume();
        }
        else {
            this.t.suspend();
        }
        this.suspended = !this.suspended;
        return true;
    }
    
    private void buildImage() {
        this.photo = this.createImage(new MemoryImageSource(this.width, this.height, aipconvex.dataNew, 0, this.width));
    }
    
    private void grabPixels() {
        final PixelGrabber pixelGrabber = new PixelGrabber(this.photo, 0, 0, this.width, this.height, aipconvex.data, 0, this.width);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {}
    }
    
    public int red(final int n, final int n2) {
        return (aipconvex.data[n * this.width + n2] & 0xFF0000) >> 16;
    }
    
    public int green(final int n, final int n2) {
        return (aipconvex.data[n * this.width + n2] & 0xFF00) >> 8;
    }
    
    public int blue(final int n, final int n2) {
        return aipconvex.data[n * this.width + n2] & 0xFF;
    }
    
    public void setRGB(final int n, final int n2, final int n3, final int n4, final int n5) {
        aipconvex.dataNew[n * this.width + n2] = new Color(n3, n4, n5).getRGB();
    }
    
    public Dimension minimizeSize() {
        return new Dimension(this.width, this.height);
    }
    
    public Dimension preferredSize() {
        return new Dimension(this.width, this.height);
    }
    
    private void waitForImage() {
        while (!this.tracker.checkID(0, true)) {
            try {
                Thread.sleep(20L);
            }
            catch (Exception ex) {}
        }
    }
    
    public aipconvex() {
        this.suspended = false;
        this.maxConvex = 2.0f;
        this.loaded = false;
        this.radius = 30;
        this.gflag = 1;
        this.step = 2;
        this.sx = 2;
        this.sy = 2;
        this.centerX = 100;
        this.centerY = 100;
        this.delay = 50;
    }
}
