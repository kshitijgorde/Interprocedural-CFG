import java.awt.Event;
import java.awt.Polygon;
import java.awt.Component;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class m16 extends Applet implements Runnable, ImageObserver
{
    Thread Rolling;
    Image Img;
    Image RoutImg;
    private int progress;
    private int mx;
    private int my;
    double scale;
    double ug;
    double inc;
    private Image BufImg;
    private Graphics Bufg;
    private Dimension screensize;
    private jKAAanimate It;
    private boolean loaded;
    private Dimension d;
    boolean Direct;
    
    public void init() {
        this.setBackground(Color.white);
        this.Img = this.getImage(this.getDocumentBase(), "m1600.gif");
    }
    
    public void paint(final Graphics g) {
        this.d = this.size();
        if (this.BufImg != null) {
            g.drawImage(this.BufImg, 0, 0, this);
        }
    }
    
    public boolean imageUpdate(final Image img, final int info, final int x, final int y, final int width, final int height) {
        if ((info & 0x20) != 0x1) {
            if (this.progress < this.d.height) {
                this.progress += height;
            }
            return true;
        }
        return false;
    }
    
    public void update(final Graphics g) {
        this.d = this.size();
        if (this.BufImg == null || this.d.width != this.screensize.width || this.d.height != this.screensize.height) {
            this.BufImg = this.createImage(this.d.width, this.d.height);
            this.screensize = this.d;
            (this.Bufg = this.BufImg.getGraphics()).setFont(this.getFont());
        }
        if (!this.loaded) {
            this.loaded = this.Bufg.drawImage(this.Img, 0, 0, this);
            return;
        }
        if (this.It == null) {
            this.It = new jKAAanimate(this.Img, this);
        }
        this.Bufg.setColor(this.getBackground());
        this.Bufg.fillRect(0, 0, this.d.width, this.d.height);
        this.Bufg.getFontMetrics();
        this.Bufg.setColor(Color.white);
        if (this.Direct && this.It != null) {
            this.It.Begin();
            this.It.RotatePlane(40, 60 - (int)(this.scale * 20.0), this.ug, null, this.scale, this.scale);
            this.RoutImg = this.It.Exec();
            this.Bufg.drawImage(this.RoutImg, 0, 0, this);
            this.RoutImg = null;
        }
        else {
            this.Bufg.drawImage(this.Img, 0, 0, this);
            this.RoutImg = null;
        }
        this.paint(g);
    }
    
    public boolean mouseDown(final Event evt, final int x, final int y) {
        this.It = new jKAAanimate(this.Img, this);
        this.Direct = !this.Direct;
        return true;
    }
    
    public void start() {
        if (this.Rolling == null) {
            (this.Rolling = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.Rolling != null) {
            this.Rolling.stop();
            this.Rolling = null;
        }
    }
    
    public void run() {
        while (!this.loaded) {
            this.repaint();
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
        }
        while (true) {
            try {
                Thread.sleep(50L);
            }
            catch (InterruptedException ex2) {}
            this.scale += this.inc;
            this.inc += 4.0E-4;
            this.ug -= 0.2;
            if (this.scale > 2.8000000000000003) {
                this.scale = 1.0;
                this.ug = 0.0;
                this.inc = 0.01;
            }
            this.repaint();
        }
    }
    
    public boolean mouseMove(final Event evt, final int x, final int y) {
        this.mx = x;
        this.my = y;
        return true;
    }
    
    public m16() {
        this.scale = 1.0;
        this.inc = 0.01;
        this.loaded = false;
        this.Direct = true;
    }
}
