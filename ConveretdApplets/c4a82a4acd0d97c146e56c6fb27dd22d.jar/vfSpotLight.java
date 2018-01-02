import java.awt.Frame;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.image.PixelGrabber;
import java.awt.image.ImageProducer;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Event;
import java.awt.image.MemoryImageSource;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class vfSpotLight extends Applet implements Runnable
{
    private Thread m_backg;
    private Image buf;
    private Image buf2;
    private Graphics gbuf;
    private int screen_W;
    private int screen_H;
    private int mouse_X;
    private int mouse_Y;
    private int old_X;
    private int old_Y;
    private int ibgcolor;
    int[] pixels;
    int[] pixDest;
    int[] pixSave;
    int radius;
    int diam;
    Color bgcolor;
    public static final int[] Vals;
    private int focallength;
    private int uniformity;
    boolean needsRepaint;
    boolean isBusy;
    private String m_var1;
    MemoryImageSource mImsrc;
    MemoryImageSource mIm2src;
    
    public void stop() {
        if (this.m_backg != null) {
            this.m_backg.stop();
            this.m_backg = null;
        }
    }
    
    public boolean mouseEnter(final Event event, final int n, final int n2) {
        this.showStatus("Spotlight applet by Xavier Potier, v-era.com");
        return true;
    }
    
    public void startup() throws InterruptedException {
        this.isBusy = true;
        this.needsRepaint = false;
        final MediaTracker mediaTracker = new MediaTracker(this);
        if (this.pixels == null) {
            final Image image = this.getImage(this.getDocumentBase(), this.m_var1);
            mediaTracker.addImage(image, 0);
            do {
                mediaTracker.waitForID(0);
            } while (mediaTracker.isErrorID(0));
            this.gbuf.drawImage(image, 0, 0, this.screen_W, this.screen_H, this);
            final int width = this.size().width;
            this.screen_W = width;
            this.mouse_X = width;
            final int height = this.size().height;
            this.screen_H = height;
            this.mouse_Y = height;
            this.buf2 = this.createImage(width, height);
            this.buf2.getGraphics().drawImage(image, 0, 0, this.screen_W, this.screen_H, this);
            this.gbuf.setColor(this.bgcolor);
            this.getPixels(this.buf);
            this.gbuf.fillRect(0, 0, this.screen_W, this.screen_H);
        }
        this.isBusy = false;
        this.needsRepaint = true;
        this.repaint();
    }
    
    void GetParameters() {
        final String parameter = this.getParameter("image");
        if (parameter != null) {
            this.m_var1 = parameter;
        }
        final String parameter2 = this.getParameter("radius");
        if (parameter2 != null) {
            this.radius = Integer.parseInt(parameter2);
        }
        this.diam = this.radius * 2;
        this.pixDest = new int[this.diam * this.diam];
        this.pixSave = new int[this.diam * this.diam];
        this.mImsrc = new MemoryImageSource(this.diam, this.diam, this.pixDest, 0, this.diam);
        this.mIm2src = new MemoryImageSource(this.diam, this.diam, this.pixSave, 0, this.diam);
        final String parameter3 = this.getParameter("focallength");
        if (parameter3 != null) {
            this.focallength = Integer.parseInt(parameter3);
        }
        final String parameter4 = this.getParameter("uniformity");
        if (parameter4 != null) {
            this.uniformity = Integer.parseInt(parameter4);
        }
        final String parameter5 = this.getParameter("bgcolor");
        if (parameter5 != null) {
            this.bgcolor = new Color(this.ibgcolor = Integer.parseInt(parameter5, 16));
        }
        this.ibgcolor -= 16777216;
    }
    
    public void paint(final Graphics graphics) {
        if (!this.needsRepaint) {
            return;
        }
        this.needsRepaint = false;
        try {
            try {
                this.plainSpot();
            }
            catch (OutOfMemoryError outOfMemoryError) {
                System.err.println("E:out of mem\n");
                this.startup();
            }
        }
        catch (InterruptedException ex) {}
        graphics.drawImage(this.buf, 0, 0, this);
        this.needsRepaint = true;
    }
    
    public void plainSpot() throws OutOfMemoryError {
        if (this.gbuf == null || this.pixels == null || this.pixDest == null) {
            return;
        }
        int n = 0;
        int n2 = 0;
        final int n3 = this.radius * this.radius;
        int n4 = this.mouse_Y - this.radius;
        if (this.mouse_Y < this.radius) {
            n4 = 0;
            n2 = this.radius - this.mouse_Y;
        }
        int screen_H = this.mouse_Y + this.radius + n2;
        if (screen_H > this.screen_H) {
            screen_H = this.screen_H;
        }
        int n5 = this.mouse_X - this.radius;
        if (n5 < 0) {
            n5 = 0;
            n = this.radius - this.mouse_X;
        }
        int screen_W = this.mouse_X + this.radius + n;
        if (screen_W > this.screen_W) {
            screen_W = this.screen_W;
        }
        this.gbuf.fillRect(this.old_X, this.old_Y, this.diam, this.diam);
        for (int i = n4, n6 = n4 - this.mouse_Y; i < screen_H; ++i, ++n6) {
            final int n7 = n6 * n6;
            final int n8 = i * this.screen_W;
            final int n9 = (i - n4) * this.diam;
            int n10 = n5 - this.mouse_X;
            int n11 = n9;
            for (int j = n5 + n8; j < screen_W + n8; ++j) {
                final int n12;
                final int n13;
                this.pixDest[n11] = (((n12 = n7 + n10 * n10) > n3) ? this.ibgcolor : (((this.pixels[j] & 0xFF00FF) * (255 - (n13 = 255 * n12 / n3)) + (this.ibgcolor & 0xFF00FF) * n13 >> 8 & 0xFF00FF) | ((this.pixels[j] & 0xFF00) * (255 - n13) + (this.ibgcolor & 0xFF00) * n13 >> 8 & 0xFF00) | 0xFF000000));
                ++n10;
                ++n11;
            }
        }
        this.gbuf.drawImage(this.createImage(this.mImsrc), n5, n4, this);
        this.isBusy = false;
    }
    
    public vfSpotLight() {
        this.mouse_X = 50;
        this.mouse_Y = 50;
        this.old_X = 50;
        this.old_Y = 50;
        this.ibgcolor = -16777216;
        this.radius = 100;
        this.diam = 200;
        this.m_var1 = "Xavier Potier";
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void getPixels(final Image image) {
        this.pixels = new int[this.screen_W * this.screen_H];
        final PixelGrabber pixelGrabber = new PixelGrabber(image, 0, 0, this.screen_W, this.screen_H, this.pixels, 0, this.screen_W);
        try {
            pixelGrabber.grabPixels();
        }
        catch (InterruptedException ex) {
            System.err.println("interrupted  waiting  for  pixels!");
            return;
        }
        if ((pixelGrabber.status() & 0x80) != 0x0) {
            System.err.println("image  fetch  aborted  or  errored");
        }
    }
    
    static {
        Vals = new int[256];
    }
    
    public void start() {
        if (this.m_backg == null) {
            (this.m_backg = new Thread(this)).start();
        }
    }
    
    public String getAppletInfo() {
        return "Name: General Lens Algorithm\r\n" + "Copyright: Xavier Potier, 2000\r\n";
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        try {
            this.getAppletContext().showDocument(new URL("http://www.v-era.com"));
        }
        catch (MalformedURLException ex) {}
        return true;
    }
    
    public void run() {
        ((Frame)this.getParent()).setCursor(1);
        try {
            this.startup();
        }
        catch (InterruptedException ex) {
            System.err.println("E:startup interrupted!");
        }
        System.err.println("started");
    }
    
    public void init() {
        this.GetParameters();
        final int width = this.size().width;
        this.screen_W = width;
        this.mouse_X = width;
        final int height = this.size().height;
        this.screen_H = height;
        this.mouse_Y = height;
        this.buf = this.createImage(width, height);
        this.gbuf = this.buf.getGraphics();
    }
    
    public boolean mouseMove(final Event event, final int mouse_X, final int mouse_Y) {
        if (this.pixels == null || this.isBusy) {
            return false;
        }
        this.isBusy = true;
        this.old_X = this.mouse_X - this.radius;
        this.old_Y = this.mouse_Y - this.radius;
        this.mouse_X = mouse_X;
        this.mouse_Y = mouse_Y;
        this.needsRepaint = true;
        this.repaint();
        return true;
    }
}
