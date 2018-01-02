import java.awt.LayoutManager;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Font;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Event;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class sphere extends Applet implements Runnable
{
    Thread motor;
    draw off;
    matrix3d mt;
    boolean ToDraw;
    int drawmode;
    int tm;
    int rx;
    int ry;
    int rz;
    int mv;
    int aa;
    int XSIZE;
    int YSIZE;
    int gXSIZE;
    int gYSIZE;
    int gWIDTH;
    String gTEXT;
    String gLINK;
    long frame;
    double fps;
    static final long DLAY = 20L;
    
    public boolean handleEvent(final Event event) {
        if (event.target == this) {
            switch (event.id) {
                case 501: {
                    this.sphere_MouseDown(event);
                    return true;
                }
                case 503: {
                    if (this.gLINK != "" && event.y < this.gYSIZE + 15 && event.y > this.gYSIZE && event.x > this.gXSIZE && event.x < this.gXSIZE + this.gWIDTH) {
                        this.showStatus("Link to: " + this.gLINK);
                        return true;
                    }
                    this.showStatus("Sphere by I-Yuan Ion Chen. [ 6sense.com ]");
                    return true;
                }
                case 502: {
                    if (this.gLINK != "" && event.y < this.gYSIZE + 15 && event.y > this.gYSIZE && event.x > this.gXSIZE && event.x < this.gXSIZE + this.gWIDTH) {
                        URL url;
                        try {
                            url = new URL(this.gLINK);
                        }
                        catch (MalformedURLException ex) {
                            return false;
                        }
                        this.stop();
                        this.getAppletContext().showDocument(url);
                        return true;
                    }
                    break;
                }
            }
        }
        return super.handleEvent(event);
    }
    
    synchronized void sphere_MouseDown(final Event event) {
        final draw off = this.off;
        final int shade = off.shade - 1;
        off.shade = shade;
        if (shade < 0) {
            this.off.shade = 1;
        }
    }
    
    public synchronized void process_param() {
        this.ToDraw = false;
        this.XSIZE = this.size().width;
        this.YSIZE = this.size().height;
        this.gLINK = this.getParameter("copyright");
        if (this.gLINK != "" && this.gLINK.equals("http://www.6sense.com")) {
            this.gLINK = this.getParameter("link");
            this.gTEXT = this.getParameter("text");
            if (this.gTEXT == null) {
                this.gTEXT = "";
            }
            else {
                this.off.off.setFont(new Font("Arial", 1, 11));
                final FontMetrics fm = this.off.off.getFontMetrics();
                this.gWIDTH = fm.stringWidth(this.gTEXT) + 16;
                this.gXSIZE = this.XSIZE / 2 - this.gWIDTH / 2;
                this.gYSIZE = this.YSIZE - 20;
                this.ToDraw = true;
            }
        }
    }
    
    public synchronized void process_step() {
        final int rx = this.rx + 8;
        this.rx = rx;
        if (rx >= 360) {
            this.rx -= 360;
        }
        if ((this.ry += 4) >= 360) {
            this.ry -= 360;
        }
        if ((this.rz += 2) >= 360) {
            this.rz -= 360;
        }
        if (this.mv == 0 && this.aa > 0) {
            if (this.tm <= 0) {
                this.tm = 100;
            }
            else if (--this.tm == 0) {
                this.mv += this.aa;
            }
        }
        else if ((this.mv += this.aa) > 10 || this.mv < -150) {
            this.aa = -this.aa;
        }
    }
    
    public synchronized void process() {
        this.mt.rotate(this.rx, this.ry, this.rz);
        this.mt.tz = this.mv;
        this.mt.transform(this.off.pts);
        this.mt.sortIt(this.off.pts, this.off.st);
        this.off.clear();
        this.off.drawball();
        if (this.ToDraw) {
            this.off.off.setFont(new Font("Arial", 1, 11));
            this.off.off.setColor(Color.yellow);
            this.off.off.drawString(this.gTEXT, this.gXSIZE, this.gYSIZE + 11);
        }
    }
    
    public synchronized void update(final Graphics g) {
        g.drawImage(this.off.offscreen, 0, 0, null);
    }
    
    public void paint(final Graphics g) {
        this.update(g);
    }
    
    public void init() {
        this.setLayout(null);
    }
    
    public void start() {
        (this.motor = new Thread(this)).setPriority(1);
        this.motor.start();
    }
    
    public void stop() {
        this.motor.stop();
    }
    
    public void run() {
        this.mt = new matrix3d();
        (this.off = new draw(this)).start();
        this.process_param();
        this.mt.center(150, 150);
        this.mt.scale(1.0);
        this.drawmode = 2;
        this.off.shade = 1;
        this.mv = -150;
        this.aa = 2;
        this.rx = this.off.rand(360);
        this.ry = this.off.rand(360);
        this.rz = this.off.rand(360);
        final long otime = System.currentTimeMillis();
        while (true) {
            final long ptime = System.currentTimeMillis() + 20L;
            this.process();
            this.repaint();
            try {
                Thread.sleep(2L);
            }
            catch (InterruptedException ex) {}
            if (System.currentTimeMillis() >= ptime) {
                this.process_step();
            }
        }
    }
}
