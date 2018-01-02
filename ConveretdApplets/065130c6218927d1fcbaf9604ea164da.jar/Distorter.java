import java.awt.event.MouseMotionAdapter;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.Color;
import java.awt.image.ImageObserver;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.Component;
import java.awt.MediaTracker;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Distorter extends Applet implements Runnable
{
    Thread Faden;
    Image offscreenImage;
    Graphics offscreenGraphics;
    MediaTracker tracker;
    Image Bild;
    int AppletW;
    int AppletH;
    boolean busy;
    boolean ready;
    int mouseX;
    int mouseY;
    int posx;
    int posy;
    int autox;
    int autoy;
    int mouseXtemp;
    int mouseYtemp;
    double autosx;
    double autosy;
    double autovx;
    double autovy;
    boolean auto;
    int mousePressed;
    double zoom;
    double zoomspeed;
    double zoomMax;
    int[] x;
    int[] y;
    int[] xs;
    int[] ys;
    double t;
    double dt;
    int idlecounter;
    int idlemax;
    double modus;
    double modusd;
    String Imagefile;
    int Zustand;
    
    public Distorter() {
        this.busy = true;
        this.ready = false;
        this.mouseX = 0;
        this.mouseY = 0;
        this.mouseXtemp = 0;
        this.mouseYtemp = 0;
        this.auto = true;
        this.mousePressed = 0;
        this.zoom = 0.0;
        this.zoomspeed = 0.03;
        this.zoomMax = 0.5;
        this.t = 0.0;
        this.dt = 0.05;
        this.idlecounter = 50;
        this.idlemax = 50;
        this.modus = 1.0;
        this.modusd = 0.01;
        this.Zustand = 0;
    }
    
    public void init() {
        this.AppletW = this.size().width;
        this.AppletH = this.size().height;
        this.mouseX = this.AppletW / 2;
        this.mouseY = this.AppletH / 2;
        this.mouseXtemp = this.AppletW / 2;
        this.mouseYtemp = this.AppletH / 2;
        this.x = new int[5];
        this.y = new int[5];
        this.xs = new int[5];
        this.ys = new int[5];
        this.xs[0] = 0;
        this.xs[1] = this.AppletW / 4;
        this.xs[2] = this.AppletW / 2;
        this.xs[3] = this.xs[1] + this.xs[2];
        this.xs[4] = this.AppletW - 1;
        this.ys[0] = 0;
        this.ys[1] = this.AppletH / 4;
        this.ys[2] = this.AppletH / 2;
        this.ys[3] = this.ys[1] + this.ys[2];
        this.ys[4] = this.AppletH - 1;
        if (this.getParameter("auto").equals("off")) {
            this.auto = false;
        }
        else {
            this.auto = true;
        }
        this.idlemax = Integer.parseInt(this.getParameter("autoWait"));
        this.idlecounter = this.idlemax;
        this.modusd = 1.0E-4 * Integer.parseInt(this.getParameter("autoChange"));
        this.zoomspeed = 0.001 * Integer.parseInt(this.getParameter("zoomSpeed"));
        this.zoomMax = 0.006 * Integer.parseInt(this.getParameter("zoom"));
        this.Imagefile = this.getParameter("Imagefile");
        this.autosx = Integer.parseInt(this.getParameter("autoStrengthX"));
        if (this.autosx > 100.0) {
            this.autosx = 100.0;
        }
        else if (this.autosx < 0.0) {
            this.autosx = 0.0;
        }
        this.autosx *= 0.004;
        this.autosy = Integer.parseInt(this.getParameter("autoStrengthY"));
        if (this.autosy > 100.0) {
            this.autosy = 100.0;
        }
        else if (this.autosy < 0.0) {
            this.autosy = 0.0;
        }
        this.autosy *= 0.004;
        this.autovx = 0.01 * Integer.parseInt(this.getParameter("autoSpeedX"));
        this.autovy = 0.01 * Integer.parseInt(this.getParameter("autoSpeedY"));
        this.tracker = new MediaTracker(this);
        this.Bild = this.getImage(this.getDocumentBase(), this.Imagefile);
        this.tracker.addImage(this.Bild, 0);
        this.tracker.checkID(0, true);
        this.addMouseListener(new MouseEventHandler());
        this.addMouseMotionListener(new MouseMotionEventHandler());
    }
    
    public void start() {
        if (this.Faden == null) {
            (this.Faden = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.Faden != null) {
            this.Faden.stop();
            this.Faden = null;
            this.busy = false;
        }
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        if (this.Zustand >= 1) {
            if (this.Zustand == 1) {
                this.offscreenGraphics.drawString("loading image...", 10, 30);
            }
            else if (this.Zustand == 2) {
                if (this.auto) {
                    if (this.idlecounter < this.idlemax) {
                        this.modus -= 4.0 * this.modusd;
                        if (this.modus < 0.0) {
                            this.modus = 0.0;
                        }
                    }
                    else {
                        this.modus += this.modusd;
                        if (this.modus > 1.0) {
                            this.modus = 1.0;
                        }
                    }
                    this.autox = (int)(0.5 * this.AppletW + this.autosx * this.AppletW * Math.sin(this.autovx * this.t));
                    this.autoy = (int)(0.5 * this.AppletH + this.autosy * this.AppletH * Math.cos(this.autovy * this.t));
                    this.posx = (int)((1.0 - this.modus) * this.mouseX + this.modus * this.autox);
                    this.posy = (int)((1.0 - this.modus) * this.mouseY + this.modus * this.autoy);
                }
                else {
                    this.posx = this.mouseX;
                    this.posy = this.mouseY;
                }
                final double sqrt = Math.sqrt((1.0 * this.AppletW - this.posx) / this.posx);
                double n = 1.0;
                double n2 = sqrt;
                double n3 = sqrt * sqrt;
                double n4 = n3 * sqrt;
                if (this.zoom > 0.0) {
                    final double n5 = this.zoom * n;
                    n -= n5;
                    n2 += n5;
                    final double n6 = this.zoom * n4;
                    n4 -= n6;
                    n3 += n6;
                }
                else if (this.zoom < 0.0) {
                    final double n7 = -this.zoom * n2;
                    n += n7;
                    n2 -= n7;
                    final double n8 = -this.zoom * n3;
                    n4 += n8;
                    n3 -= n8;
                }
                final double n9 = 1.0 * this.AppletW / (n + n2 + n3 + n4);
                final double n10 = n * n9;
                final double n11 = n2 * n9;
                final double n12 = n3 * n9;
                this.x[0] = 0;
                this.x[1] = (int)n10;
                this.x[2] = this.x[1] + (int)n11;
                this.x[3] = this.x[2] + (int)n12;
                this.x[4] = this.AppletW - 1;
                final double sqrt2 = Math.sqrt((1.0 * this.AppletH - this.posy) / this.posy);
                double n13 = 1.0;
                double n14 = sqrt2;
                double n15 = sqrt2 * sqrt2;
                double n16 = n15 * sqrt2;
                if (this.zoom > 0.0) {
                    final double n17 = this.zoom * n13;
                    n13 -= n17;
                    n14 += n17;
                    final double n18 = this.zoom * n16;
                    n16 -= n18;
                    n15 += n18;
                }
                else if (this.zoom < 0.0) {
                    final double n19 = -this.zoom * n14;
                    n13 += n19;
                    n14 -= n19;
                    final double n20 = -this.zoom * n15;
                    n16 += n20;
                    n15 -= n20;
                }
                final double n21 = 1.0 * this.AppletH / (n13 + n14 + n15 + n16);
                final double n22 = n13 * n21;
                final double n23 = n14 * n21;
                final double n24 = n15 * n21;
                this.y[0] = 0;
                this.y[1] = (int)n22;
                this.y[2] = this.y[1] + (int)n23;
                this.y[3] = this.y[2] + (int)n24;
                this.y[4] = this.AppletH - 1;
                for (int i = 0; i < 4; ++i) {
                    for (int j = 0; j < 4; ++j) {
                        this.offscreenGraphics.drawImage(this.Bild, this.x[i], this.y[j], this.x[i + 1], this.y[j + 1], this.xs[i], this.ys[j], this.xs[i + 1], this.ys[j + 1], this);
                    }
                }
                this.offscreenGraphics.setColor(Color.white);
                this.offscreenGraphics.drawString("applets and more:", 5, this.AppletH - 20);
                this.offscreenGraphics.setColor(Color.black);
                this.offscreenGraphics.drawString("www.eigelb.at", 6, this.AppletH - 4);
                if (this.mouseY > this.AppletH - 20 && this.mouseX < 85) {
                    this.offscreenGraphics.setColor(Color.yellow);
                }
                else {
                    this.offscreenGraphics.setColor(Color.white);
                }
                this.offscreenGraphics.drawString("www.eigelb.at", 5, this.AppletH - 5);
            }
            graphics.drawImage(this.offscreenImage, 0, 0, this);
        }
    }
    
    public void run() {
        this.offscreenImage = this.createImage(this.AppletW, this.AppletH);
        this.offscreenGraphics = this.offscreenImage.getGraphics();
        this.Zustand = 1;
        while (!this.tracker.checkAll()) {
            try {
                Thread.sleep(100L);
            }
            catch (InterruptedException ex) {}
            this.repaint();
        }
        this.offscreenGraphics.drawImage(this.Bild, 0, 0, this);
        this.Zustand = 2;
        while (this.busy) {
            this.t += this.dt;
            if (this.mousePressed == 1) {
                this.zoom += 2.0 * this.zoomspeed;
            }
            if (this.mousePressed == 2) {
                this.zoom -= 2.0 * this.zoomspeed;
            }
            if (this.zoom > this.zoomMax) {
                this.zoom = this.zoomMax;
            }
            if (this.zoom < -this.zoomMax) {
                this.zoom = -this.zoomMax;
            }
            if (this.zoom > 0.0) {
                this.zoom -= this.zoomspeed;
                if (this.zoom < 0.0) {
                    this.zoom = 0.0;
                }
            }
            if (this.zoom < 0.0) {
                this.zoom += this.zoomspeed;
                if (this.zoom > 0.0) {
                    this.zoom = 0.0;
                }
            }
            if (this.mouseXtemp == this.mouseX && this.mouseYtemp == this.mouseY) {
                ++this.idlecounter;
            }
            else {
                this.idlecounter = 0;
            }
            this.mouseX = this.mouseXtemp;
            this.mouseY = this.mouseYtemp;
            this.repaint();
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex2) {}
        }
    }
    
    class MouseEventHandler extends MouseAdapter
    {
        public void mousePressed(final MouseEvent mouseEvent) {
            if ((mouseEvent.getModifiers() & 0x4) != 0x0) {
                Distorter.this.mousePressed = 2;
            }
            else {
                Distorter.this.mousePressed = 1;
            }
            Distorter.this.mouseXtemp = mouseEvent.getX();
            Distorter.this.mouseYtemp = mouseEvent.getY();
            if (Distorter.this.mouseYtemp > Distorter.this.AppletH - 20 && Distorter.this.mouseXtemp < 85) {
                Distorter.this.mousePressed = 0;
                try {
                    Distorter.this.getAppletContext().showDocument(new URL("http://www.eigelb.at"), "_blank");
                }
                catch (MalformedURLException ex) {}
            }
        }
        
        public void mouseReleased(final MouseEvent mouseEvent) {
            Distorter.this.mousePressed = 0;
        }
    }
    
    class MouseMotionEventHandler extends MouseMotionAdapter
    {
        public void mouseMoved(final MouseEvent mouseEvent) {
            Distorter.this.mouseXtemp = mouseEvent.getX();
            Distorter.this.mouseYtemp = mouseEvent.getY();
            if (Distorter.this.mouseXtemp < 10) {
                Distorter.this.mouseXtemp = 10;
            }
            if (Distorter.this.mouseYtemp < 10) {
                Distorter.this.mouseYtemp = 10;
            }
            if (Distorter.this.mouseXtemp > Distorter.this.AppletW - 10) {
                Distorter.this.mouseXtemp = Distorter.this.AppletW - 10;
            }
            if (Distorter.this.mouseYtemp > Distorter.this.AppletH - 10) {
                Distorter.this.mouseYtemp = Distorter.this.AppletH - 10;
            }
        }
        
        public void mouseDragged(final MouseEvent mouseEvent) {
            Distorter.this.mouseXtemp = mouseEvent.getX();
            Distorter.this.mouseYtemp = mouseEvent.getY();
            if (Distorter.this.mouseXtemp < 10) {
                Distorter.this.mouseXtemp = 10;
            }
            if (Distorter.this.mouseYtemp < 10) {
                Distorter.this.mouseYtemp = 10;
            }
            if (Distorter.this.mouseXtemp > Distorter.this.AppletW - 10) {
                Distorter.this.mouseXtemp = Distorter.this.AppletW - 10;
            }
            if (Distorter.this.mouseYtemp > Distorter.this.AppletH - 10) {
                Distorter.this.mouseYtemp = Distorter.this.AppletH - 10;
            }
        }
    }
}
