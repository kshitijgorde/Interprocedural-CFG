import java.awt.Event;
import java.awt.image.ImageObserver;
import java.awt.Component;
import java.util.NoSuchElementException;
import java.awt.LayoutManager;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Color;
import java.util.StringTokenizer;
import java.awt.Rectangle;
import java.util.Vector;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class SpaceLinks2 extends Applet implements Runnable
{
    Vector parametri;
    Vector stringovi;
    Vector urlovi;
    Vector targeti;
    MojaNit[] nit;
    Rectangle[] rec;
    String lnk;
    StringTokenizer token;
    StringTokenizer bntok;
    StringTokenizer bstok;
    StringTokenizer botok;
    Color bojan;
    Color bojas;
    Color bojaosv;
    Graphics g;
    String cb0;
    int Width;
    int Height;
    Thread me;
    boolean suspend;
    Image im;
    Graphics offscreen;
    double rot;
    double dx;
    double ddx;
    String st;
    String pozicija;
    String por;
    String urlic;
    String target;
    String cb;
    int speed;
    int stars;
    int type;
    int brojparametara;
    int loff;
    int toff;
    int fs;
    int lv;
    int lh;
    int ld;
    double defddx;
    double max;
    Star[] pol;
    
    public void init() {
        this.g = this.getGraphics();
        this.bntok = new StringTokenizer(this.getParameter("LinkColor"), ",");
        int int1 = Integer.parseInt(this.bntok.nextToken());
        if (int1 > 255) {
            int1 = 0;
        }
        if (int1 < 0) {
            int1 = 0;
        }
        int int2 = Integer.parseInt(this.bntok.nextToken());
        if (int2 > 255) {
            int2 = 0;
        }
        if (int2 < 0) {
            int2 = 0;
        }
        int int3 = Integer.parseInt(this.bntok.nextToken());
        if (int3 > 255) {
            int3 = 0;
        }
        if (int3 < 0) {
            int3 = 0;
        }
        this.bojan = new Color(int1, int2, int3);
        this.botok = new StringTokenizer(this.getParameter("MouseOverColor"), ",");
        int int4 = Integer.parseInt(this.botok.nextToken());
        if (int4 > 255) {
            int4 = 0;
        }
        if (int4 < 0) {
            int4 = 0;
        }
        int int5 = Integer.parseInt(this.botok.nextToken());
        if (int5 > 255) {
            int5 = 0;
        }
        if (int5 < 0) {
            int5 = 0;
        }
        int int6 = Integer.parseInt(this.botok.nextToken());
        if (int6 > 255) {
            int6 = 0;
        }
        if (int6 < 0) {
            int6 = 0;
        }
        this.bojaosv = new Color(int4, int5, int6);
        this.bstok = new StringTokenizer(this.getParameter("TextColor"), ",");
        int int7 = Integer.parseInt(this.bstok.nextToken());
        if (int7 > 255) {
            int7 = 255;
        }
        if (int7 < 0) {
            int7 = 255;
        }
        int int8 = Integer.parseInt(this.bstok.nextToken());
        if (int8 > 255) {
            int8 = 255;
        }
        if (int8 < 0) {
            int8 = 255;
        }
        int int9 = Integer.parseInt(this.bstok.nextToken());
        if (int9 > 255) {
            int9 = 255;
        }
        if (int9 < 0) {
            int9 = 255;
        }
        this.bojas = new Color(int7, int8, int9);
        this.lv = Integer.parseInt(this.getParameter("LinkWidth"));
        this.lh = Integer.parseInt(this.getParameter("LinkHeight"));
        this.ld = Integer.parseInt(this.getParameter("DistanceBtwLinks"));
        this.loff = Integer.parseInt(this.getParameter("LeftOffset"));
        this.toff = Integer.parseInt(this.getParameter("TopOffset"));
        this.fs = Integer.parseInt(this.getParameter("FontSize"));
        this.cb = this.getParameter("Center");
        this.setLayout(null);
        int n = 1;
        Label_0521: {
            break Label_0521;
            String parameter;
            do {
                this.parametri.addElement(this.lnk);
                ++n;
                parameter = this.getParameter("link" + n);
                this.lnk = parameter;
            } while (parameter != null);
        }
        this.brojparametara = this.parametri.size();
        if (this.cb0.equalsIgnoreCase(this.cb)) {
            this.loff = (this.size().width - this.lv) / 2;
            this.toff = (this.size().height - this.brojparametara * (this.ld + this.lh)) / 2;
        }
        for (int i = 0; i < this.brojparametara; ++i) {
            this.st = (String)this.parametri.elementAt(i);
            this.token = new StringTokenizer(this.st, ",");
            this.por = this.token.nextToken();
            this.urlic = this.token.nextToken();
            try {
                this.target = this.token.nextToken();
            }
            catch (NoSuchElementException ex) {
                this.target = "_self";
            }
            this.stringovi.addElement(this.por);
            this.urlovi.addElement(this.urlic);
            this.targeti.addElement(this.target);
        }
        this.rec = new Rectangle[this.brojparametara];
        this.nit = new MojaNit[this.brojparametara];
        for (int j = 0; j < this.brojparametara; ++j) {
            this.add(this.nit[j] = new MojaNit((String)this.stringovi.elementAt(j), (String)this.urlovi.elementAt(j), (String)this.targeti.elementAt(j), this.bojan, this.bojas, this.bojaosv, this.fs, this.lv, this.lh));
            this.nit[j].reshape(this.loff, this.toff, this.lv, this.lh);
            this.rec[j] = new Rectangle(this.loff, this.toff, this.lv, this.lh);
            this.toff = this.toff + this.ld + this.lh;
        }
        this.rot = 0.0;
        this.dx = 0.0;
        this.ddx = 0.0;
        this.Width = this.size().width;
        this.Height = this.size().height;
        final String parameter2 = this.getParameter("Speed");
        this.speed = ((parameter2 == null) ? 50 : Integer.valueOf(parameter2));
        final String parameter3 = this.getParameter("NumberOfStars");
        this.stars = ((parameter3 == null) ? 30 : Integer.valueOf(parameter3));
        final String parameter4 = this.getParameter("type");
        this.type = ((parameter4 == null) ? 0 : Integer.valueOf(parameter4));
        final String parameter5 = this.getParameter("spin");
        this.rot = ((parameter5 == null) ? 0.0 : Double.valueOf(parameter5));
        final String parameter6 = this.getParameter("maxspin");
        this.max = ((parameter6 == null) ? 0.1 : Double.valueOf(parameter6));
        final String parameter7 = this.getParameter("ddx");
        this.defddx = ((parameter7 == null) ? 0.005 : Double.valueOf(parameter7));
        try {
            this.im = this.createImage(this.Width, this.Height);
            this.offscreen = this.im.getGraphics();
        }
        catch (Exception ex2) {
            this.offscreen = null;
        }
        this.pol = new Star[this.stars];
        for (int k = 0; k < this.stars; ++k) {
            this.pol[k] = new Star(this.Width, this.Height, 100, this.type);
        }
    }
    
    public void paint(final Graphics graphics) {
        this.offscreen.setColor(Color.black);
        this.offscreen.fillRect(0, 0, this.Width, this.Height);
        for (int i = 0; i < this.stars; ++i) {
            this.pol[i].Draw(this.offscreen, this.rot);
        }
        graphics.drawImage(this.im, 0, 0, this);
    }
    
    public void paintMe(final Graphics graphics) {
        graphics.setColor(Color.black);
        graphics.fillRect(0, 0, this.Width, this.Height);
        for (int i = 0; i < this.stars; ++i) {
            this.pol[i].Draw(graphics, this.rot);
        }
    }
    
    public void start() {
        if (this.me == null) {
            (this.me = new Thread(this)).start();
        }
    }
    
    public void stop() {
        if (this.me != null) {
            this.me.stop();
            this.me = null;
        }
    }
    
    public void run() {
        while (this.me != null) {
            this.rot += this.dx;
            this.dx += this.ddx;
            if (this.dx > this.max) {
                this.ddx = -this.defddx;
            }
            if (this.dx < -this.max) {
                this.ddx = this.defddx;
            }
            try {
                Thread.sleep(this.speed);
            }
            catch (InterruptedException ex) {}
            this.paint(this.g);
        }
    }
    
    public void update(final Graphics graphics) {
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        for (int i = 0; i < this.brojparametara; ++i) {
            if (this.rec[i].inside(n, n2)) {
                this.getAppletContext().showStatus("Opening page: " + this.nit[i].url);
                this.getAppletContext().showDocument(this.nit[i].url, this.nit[i].tr);
            }
        }
        return true;
    }
    
    public void Toggle() {
        if (this.me != null) {
            if (this.suspend) {
                this.me.resume();
            }
            else {
                this.me.suspend();
            }
            this.suspend = !this.suspend;
        }
    }
    
    public SpaceLinks2() {
        this.parametri = new Vector();
        this.stringovi = new Vector();
        this.urlovi = new Vector();
        this.targeti = new Vector();
        this.cb0 = new String("true");
        this.suspend = false;
    }
}
