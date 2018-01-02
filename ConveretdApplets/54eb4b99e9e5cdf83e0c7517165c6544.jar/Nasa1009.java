import java.awt.Event;
import java.awt.Component;
import java.awt.Color;
import java.awt.Polygon;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Nasa1009 extends Applet implements Runnable, ImageObserver
{
    Thread Rolling;
    Image Img;
    Image RoutImg;
    int progress;
    int mx;
    int my;
    double ug;
    private Image BufImg;
    private Graphics Bufg;
    private Dimension screensize;
    private jKAAanimate It;
    private boolean loaded;
    private Dimension d;
    boolean Direct;
    Polygon pl;
    
    public void init() {
        this.setBackground(Color.white);
        (this.pl = new Polygon()).addPoint(243, 129);
        this.pl.addPoint(267, 129);
        this.pl.addPoint(267, 162);
        this.pl.addPoint(243, 162);
        this.Img = this.getImage(this.getDocumentBase(), "Nasa1009.gif");
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
            this.ug += 0.05;
        }
        else {
            this.ug -= 0.05;
        }
        if (this.ug > 2.5 || this.ug < 1.0) {
            this.Direct = !this.Direct;
        }
        this.It.Begin();
        this.It.RotatePlane(243, 129, 0.0, this.pl, this.ug, this.ug);
        this.RoutImg = this.It.Exec();
        this.Bufg.drawImage(this.RoutImg, 0, 0, this);
        this.Bufg.setColor(Color.red);
        this.Bufg.drawRect(242, 129, (int)((this.pl.xpoints[1] - this.pl.xpoints[0]) * this.ug + 0.0), (int)((this.pl.ypoints[2] - this.pl.ypoints[1]) * this.ug + 0.0));
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
                Thread.sleep(40L);
            }
            catch (InterruptedException ex2) {}
            this.repaint();
        }
    }
    
    public boolean mouseMove(final Event evt, final int x, final int y) {
        this.mx = x;
        this.my = y;
        return true;
    }
    
    public Nasa1009() {
        this.ug = 1.0;
        this.loaded = false;
        this.Direct = true;
    }
}
