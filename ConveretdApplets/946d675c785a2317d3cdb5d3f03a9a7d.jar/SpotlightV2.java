import java.awt.FontMetrics;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.AWTEvent;
import java.net.URL;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.MediaTracker;
import java.awt.image.PixelGrabber;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SpotlightV2 extends Applet implements Runnable
{
    private Thread m_Spotlight;
    private Image the_pic;
    private Image bbuff;
    private Image tmp;
    private int w;
    private int h;
    private int radius;
    private int radius2;
    private int trad;
    private int tradm;
    private int midx;
    private int midy;
    private int x;
    private int vx;
    private int y;
    private int vy;
    private int[] baseBuffer;
    private int[] screenBuffer;
    private double[] spot;
    private PixelGrabber pg;
    private MediaTracker mt;
    private Graphics backbuffer;
    private boolean first;
    private boolean init;
    private boolean run;
    private boolean freeMovement;
    
    public SpotlightV2() {
        this.m_Spotlight = null;
        this.first = true;
        this.init = false;
        this.run = true;
        this.freeMovement = true;
    }
    
    private final void clearBeam() {
        final int n = this.y - this.radius;
        final int n2 = this.y + this.radius - 1;
        final int n3 = this.x - this.radius;
        final int n4 = this.x + this.radius - 1;
        for (int i = n; i <= n2; ++i) {
            for (int j = n3; j <= n4; ++j) {
                if (j < this.w && j > 0 && i < this.h && i > 0) {
                    final int n5 = this.baseBuffer[i * this.w + j];
                    this.screenBuffer[i * this.w + j] = ((n5 & 0xFF000000) | ((int)(0.08969999999999999 * (n5 >> 16 & 0xFF)) << 16 & 0xFF0000) | ((int)(0.15 * (n5 >> 8 & 0xFF)) << 8 & 0xFF00) | ((int)(0.033 * (n5 & 0xFF)) & 0xFF));
                }
            }
        }
    }
    
    public void destroy() {
        this.stop();
    }
    
    private final void drawBeam() {
        for (int i = this.y - this.radius; i < this.y + this.radius; ++i) {
            for (int j = this.x - this.radius; j < this.x + this.radius; ++j) {
                if (j < this.w && j > 0 && i < this.h && i > 0) {
                    final double n = this.spot[j - this.x + this.radius + (i - this.y + this.radius) * this.trad];
                    if (n > 0.0) {
                        final int n2 = this.baseBuffer[i * this.w + j];
                        this.screenBuffer[i * this.w + j] = ((n2 & 0xFF000000) | ((int)(n * (n2 >> 16 & 0xFF)) << 16 & 0xFF0000) | ((int)(n * (n2 >> 8 & 0xFF)) << 8 & 0xFF00) | ((int)(n * (n2 & 0xFF)) & 0xFF));
                    }
                }
            }
        }
    }
    
    private final void fullClearScreen() {
        final int n = 0;
        final int n2 = this.h - 1;
        final int n3 = 0;
        final int n4 = this.w - 1;
        for (int i = n; i <= n2; ++i) {
            for (int j = n3; j <= n4; ++j) {
                final int n5 = this.baseBuffer[i * this.w + j];
                this.screenBuffer[i * this.w + j] = ((n5 & 0xFF000000) | ((int)(0.08969999999999999 * (n5 >> 16 & 0xFF)) << 16 & 0xFF0000) | ((int)(0.15 * (n5 >> 8 & 0xFF)) << 8 & 0xFF00) | ((int)(0.033 * (n5 & 0xFF)) & 0xFF));
            }
        }
    }
    
    public String getAppletInfo() {
        return "Name:   Spotlight V2\r\nAuthor: Bojan Car\r\nEmail:  bojan.car@virgin.net";
    }
    
    public void init() {
        this.enableEvents(48L);
        final URL documentBase = this.getDocumentBase();
        this.mt = new MediaTracker(this);
        this.the_pic = this.getImage(documentBase, this.getParameter("image"));
        this.mt.addImage(this.the_pic, 0);
    }
    
    private synchronized void moveBeam() {
        if (this.freeMovement) {
            if (this.x < this.midx) {
                ++this.vx;
            }
            else {
                --this.vx;
            }
            this.x += this.vx;
            if (this.y < this.midy) {
                ++this.vy;
            }
            else {
                --this.vy;
            }
            this.y += this.vy;
        }
        else {
            this.x += (this.midx - this.x) / 3;
            this.y += (this.midy - this.y) / 3;
        }
    }
    
    public void paint(final Graphics graphics) {
    }
    
    public synchronized void processEvent(final AWTEvent awtEvent) {
        switch (awtEvent.getID()) {
            case 503: {
                final MouseEvent mouseEvent = (MouseEvent)awtEvent;
                this.freeMovement = false;
                this.midx = mouseEvent.getX();
                this.midy = mouseEvent.getY();
                break;
            }
            case 505: {
                this.freeMovement = true;
                this.midx = this.w / 2;
                this.midy = this.h / 2;
                this.vx = 0;
                this.vy = 0;
                break;
            }
            default: {
                super.processEvent(awtEvent);
                break;
            }
        }
    }
    
    public void run() {
        final Graphics graphics = this.getGraphics();
        final FontMetrics fontMetrics = graphics.getFontMetrics();
        final int height = fontMetrics.getHeight();
        String s = "http://freespace.virgin.net/bojan.car/";
        String s2;
        String s3;
        String s4;
        if (fontMetrics.stringWidth(s) + 10 > this.getSize().width) {
            s2 = "Spotlight V2";
            s3 = "  NON-COMMERCIAL";
            s4 = "by Bojan Car";
            s = "bojan.car@virgin.net";
        }
        else {
            s2 = "Spotlight V2";
            s3 = "    for NON-COMMERCIAL use!";
            s4 = "by Bojan Car";
        }
        this.freeMovement = true;
        StringBuffer sb = new StringBuffer("Loading");
        try {
            Thread.sleep(100L);
        }
        catch (InterruptedException ex) {}
        this.setCursor(new Cursor(3));
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.getSize().width, this.getSize().height);
        graphics.setColor(Color.gray);
        graphics.drawString(s2, this.getSize().width / 8, this.getSize().height / 4);
        graphics.drawString(s3, this.getSize().width / 8, this.getSize().height / 4 + height);
        graphics.drawString(s4, this.getSize().width / 8, this.getSize().height / 4 + height * 2);
        int n = 0;
        while (this.mt.statusID(0, true) == 1) {
            if (++n == 5) {
                n = 0;
                sb = new StringBuffer("Loading");
            }
            graphics.setColor(Color.gray);
            graphics.drawString(sb.toString(), this.getSize().width / 8, this.getSize().height * 3 / 4);
            sb.append(".");
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex2) {}
        }
        this.w = this.the_pic.getWidth(this);
        this.h = this.the_pic.getHeight(this);
        this.showStatus("Image width=" + this.w + ", height=" + this.h);
        this.baseBuffer = new int[this.w * this.h];
        this.screenBuffer = new int[this.w * this.h];
        this.pg = new PixelGrabber(this.the_pic, 0, 0, this.w, this.h, this.baseBuffer, 0, this.w);
        try {
            this.pg.grabPixels();
        }
        catch (InterruptedException ex3) {
            this.showStatus("Interrupted  waiting  for  pixels!");
            return;
        }
        if ((this.pg.status() & 0x80) != 0x0) {
            this.showStatus("Image  fetch  aborted  or  errored");
            return;
        }
        this.the_pic.flush();
        System.gc();
        this.bbuff = this.createImage(this.w, this.h);
        this.backbuffer = this.bbuff.getGraphics();
        try {
            final Integer n2 = new Integer(this.getParameter("radius"));
            if (n2 != null) {
                this.radius = n2;
            }
            else {
                this.radius = 20;
            }
        }
        catch (NumberFormatException ex4) {
            this.showStatus("Bad parameter \"radius\", exiting!!!");
            this.stop();
        }
        this.radius2 = this.radius * this.radius;
        this.spot = new double[4 * this.radius2];
        this.trad = 2 * this.radius;
        this.tradm = this.trad - 1;
        for (int i = this.tradm; i >= 0; --i) {
            for (int j = 0; j < this.trad; ++j) {
                this.spot[j + (this.tradm - i) * this.trad] = this.withinCircle(j, i);
            }
        }
        this.midx = this.w / 2;
        this.midy = this.h / 2;
        this.x = (int)(Math.random() * this.w / 4.0);
        this.y = (int)(Math.random() * this.w / 4.0);
        graphics.drawString("Dimming Picture...", this.getSize().width / 8, this.getSize().height * 3 / 4 + height);
        this.fullClearScreen();
        this.setCursor(new Cursor(0));
        while (this.run) {
            this.clearBeam();
            this.moveBeam();
            this.drawBeam();
            try {
                this.tmp = this.createImage(new MemoryImageSource(this.w, this.h, this.screenBuffer, 0, this.w));
                this.backbuffer.drawImage(this.tmp, 0, 0, this);
                this.backbuffer.setColor(Color.gray);
                this.backbuffer.drawString(s, 10, 20);
                graphics.drawImage(this.bbuff, 0, 0, this);
                this.tmp.flush();
            }
            catch (OutOfMemoryError outOfMemoryError) {
                System.gc();
            }
            try {
                Thread.sleep(16L);
            }
            catch (InterruptedException ex5) {
                this.stop();
            }
        }
        this.tmp.flush();
        System.gc();
    }
    
    public void start() {
        if (this.m_Spotlight == null) {
            this.m_Spotlight = new Thread(this);
            this.run = true;
            this.m_Spotlight.start();
        }
    }
    
    public void stop() {
        if (this.m_Spotlight != null) {
            this.run = false;
            try {
                Thread.sleep(1000L);
            }
            catch (InterruptedException ex) {}
            this.m_Spotlight = null;
            System.gc();
        }
    }
    
    public void update(final Graphics graphics) {
    }
    
    private final double withinCircle(final int n, final int n2) {
        final double n3 = n - this.radius;
        final double n4 = n2 - this.radius;
        final double n5 = n3 * n3 + n4 * n4;
        final double n6 = this.radius / 0.83666;
        final double sqrt = Math.sqrt(n5);
        final double n7 = 1.0 - sqrt / n6 * (sqrt / n6);
        if (n7 > 0.3) {
            return n7;
        }
        return -1.0;
    }
}
