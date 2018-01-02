import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Component;
import java.awt.MediaTracker;
import java.util.Vector;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class OddOneOut extends Applet
{
    Image ca;
    Graphics da;
    Dimension size;
    FontMetrics ea;
    Color fa;
    Color ga;
    Color ha;
    boolean ia;
    boolean ja;
    boolean ka;
    Rectangle la;
    int ma;
    int na;
    int oa;
    int pa;
    int qa;
    int ra;
    int sa;
    int ta;
    int ua;
    int va;
    int wa;
    Image xa;
    Image ya;
    Image za;
    Image Aa;
    Image[] Ba;
    Image Ca;
    Image Da;
    Rectangle[] Ea;
    Rectangle[] Fa;
    Rectangle Ga;
    package _;
    private Ha;
    private static String ib = "\uebc4\uebcc\uebda\uebda\uebc8\uebce\uebcc\uebcf\uebc0\uebc5\uebcc";
    private static String jb = "\uebc4\uebcc\uebda\uebda\uebc8\uebce\uebcc\uebda\uebf6\uebcc\uebc7\ueb87\uebcd\uebc8\uebdd";
    private static String kb = "\uebcb\uebc8\uebca\uebc2\uebca\uebc6\uebc5\uebc6\uebdb";
    private static String lb = "\uebcf\uebc6\uebc7\uebdd\uebea\uebc6\uebc5\uebc6\uebdb";
    private static String mb = "\uebe8\uebdb\uebc0\uebc8\uebc5";
    private static String nb = "\uebcb\uebc6\uebdb\uebcd\uebcc\uebdb\uebca\uebc6\uebc5\uebc6\uebdb";
    private static String ob = "\uebdd\uebc6\uebc2\uebcc\uebc7\uebda\uebc1\uebcc\uebcc\uebdd\ueb87\uebce\uebc0\uebcf";
    private static String c = "\uebc6\uebcd\uebcd\uebf6\uebcb\uebce\ueb87\uebce\uebc0\uebcf";
    private static String d = "\uebcf\uebdc\uebc5\uebc5\ueb87\uebce\uebc0\uebcf";
    private static String e = "\uebd9\uebc6\uebc0\uebc7\uebdd\ueb87\uebce\uebc0\uebcf";
    private static String f = "\uebc0\uebc4\uebc8\uebce\uebcc\uebca\uebdb\uebc6\uebd9\uebd9\uebc0\uebc7\uebce\ueb89\uebc1\uebc8\uebda\ueb89\uebcb\uebcc\uebcc\uebc7\ueb89\uebc0\uebc7\uebdd\uebcc\uebdb\uebdb\uebdc\uebd9\uebdd\uebcc\uebcd";
    
    public void init() {
        String s = this.getParameter(OddOneOut.ib);
        if (s == null) {
            s = OddOneOut.jb;
        }
        this._ = new package(this.getCodeBase(), s);
        this.Ha = new private(this, this._);
        this.size = this.getSize();
        this.fa = this.Ha.a(OddOneOut.kb, Color.white);
        this.ga = this.Ha.a(OddOneOut.lb, Color.black);
        this.setBackground(this.fa);
        this.setFont(new Font(OddOneOut.mb, 1, 12));
        this.ea = this.getFontMetrics(this.getFont());
    }
    
    public boolean h() {
        if (this.Ha.b() && !this.Ha.b(private.rb)) {
            this.Ha._(true);
            return false;
        }
        if (!this.i() || !this.j() || !this.k()) {
            return false;
        }
        this.ca = this.createImage(this.size.width, this.size.height);
        this.da = this.ca.getGraphics();
        this.Ea = new Rectangle[this.oa * this.pa];
        int n = this.qa;
        int qa = this.qa;
        int n2 = 0;
        for (int i = 0; i < this.pa; ++i) {
            for (int j = 0; j < this.oa; ++j, ++n2) {
                this.Ea[n2] = new Rectangle(n, qa, this.ra, this.ra);
                n += this.ra;
            }
            n = this.qa;
            qa += this.ra;
        }
        this.Ga = new Rectangle(362, 48, 80, 100);
        this.la = new Rectangle(this.oa * this.ra + 65, this.size.height - 106, 80, 68);
        this.a();
        this.enableEvents(48L);
        return !this.Ha.g();
    }
    
    private boolean i() {
        this.ha = this.Ha.a(OddOneOut.nb, Color.black);
        return !this.Ha.g();
    }
    
    private boolean j() {
        final Vector<Image> vector = new Vector<Image>();
        vector.addElement(this.xa = this.getImage(this.getCodeBase(), OddOneOut.ob));
        vector.addElement(this.Ca = this.getImage(this.getCodeBase(), OddOneOut.c));
        vector.addElement(this.za = this.getImage(this.getCodeBase(), OddOneOut.d));
        vector.addElement(this.Da = this.getImage(this.getCodeBase(), OddOneOut.e));
        this.Ha.b(vector, 0);
        return !this.Ha.g();
    }
    
    private boolean k() {
        final MediaTracker mediaTracker = new MediaTracker(this);
        final ImageProducer source = this.xa.getSource();
        this.Ba = new Image[50];
        int n = 1;
        int n2 = 1;
        int n3 = 0;
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 10; ++j, ++n3) {
                mediaTracker.addImage(this.Ba[n3] = this.createImage(new FilteredImageSource(source, new CropImageFilter(n, n2, 40, 40))), n3);
                try {
                    mediaTracker.waitForID(n3);
                }
                catch (InterruptedException ex) {
                    return this.Ha.n(OddOneOut.f);
                }
                n += 41;
            }
            n = 1;
            n2 += 41;
        }
        return !this.Ha.g();
    }
    
    private void a() {
        this.b();
        this.ja = false;
        this.ka = true;
        this.sa = (int)(Math.random() * (this.pa * this.oa));
        this.ta = -1;
    }
    
    private void b() {
        this.Fa = new Rectangle[this.oa * this.pa];
        int n = this.qa;
        int qa = this.qa;
        int n2 = 0;
        for (int i = 0; i < this.pa; ++i) {
            for (int j = 0; j < this.oa; ++j, ++n2) {
                this.Fa[n2] = new Rectangle(n + this.a(), qa + this.a(), this.ra, this.ra);
                n += this.ra;
            }
            n = this.qa;
            qa += this.ra;
        }
    }
    
    private int a() {
        switch ((int)(Math.random() * 3.0)) {
            case 0: {
                return -5;
            }
            case 1: {
                return 5;
            }
            default: {
                return 0;
            }
        }
    }
    
    private void b(final Graphics graphics) {
        this.Ha.a(graphics);
        final String _ = this._._();
        graphics.setColor(this.ga);
        graphics.drawString(_, this.Ha.a(_, true, graphics), this.Ha.a(_, false, graphics));
        if (this.h()) {
            this.ia = true;
            this.Ha.a(graphics);
            this.repaint();
            return;
        }
        this.Ha.a(graphics);
        String s;
        if (this.Ha.a()) {
            s = private.pb;
        }
        else {
            s = this._.a();
        }
        graphics.setColor(this.ga);
        graphics.drawString(s, this.Ha.a(s, true, graphics), this.Ha.a(s, false, graphics));
    }
    
    public void update(final Graphics graphics) {
        if (!this.ia) {
            this.b(graphics);
            return;
        }
        if (this.Ca != null) {
            this.da.drawImage(this.Ca, 0, 0, this);
        }
        if (!this.ka) {
            this.da.drawImage(this.za, this.Ga.x, this.Ga.y, this);
            this.da.drawImage(this.Da, this.la.x, this.la.y, this);
        }
        this._();
        if (this.Aa != null) {
            this.da.drawImage(this.Aa, this.ua, this.va, this);
        }
        graphics.drawImage(this.ca, 0, 0, this);
        if (this.Ha.g()) {
            this.showStatus(this._.b());
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    private void _() {
        if (this.ja) {
            for (int i = 0; i < this.Ea.length; ++i) {
                this.da.drawImage(this.Ba[this.wa], this.Ea[i].x + 5, this.Ea[i].y + 5, this);
            }
            return;
        }
        for (int j = 0; j < this.Ea.length; ++j) {
            if (j != this.sa && j != this.ta) {
                this.da.drawImage(this.Ba[this.wa], this.Fa[j].x, this.Fa[j].y, this);
            }
        }
        if (this.ta != this.sa) {
            this.da.drawImage(this.Ba[this.wa + 1], this.Fa[this.sa].x, this.Fa[this.sa].y, this);
        }
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this._(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
            }
            case 502: {
                this.a(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
            }
            default: {
                mouseEvent.consume();
            }
        }
    }
    
    public boolean _(final MouseEvent mouseEvent, final int n, final int n2) {
        if (this.la.contains(n, n2) && !this.ka) {
            this.wa += 2;
            if (this.wa >= this.Ba.length) {
                this.wa = 0;
            }
            this.a();
            this.repaint();
            return true;
        }
        for (int i = 0; i < this.Ea.length; ++i) {
            if (this.Ea[i].contains(n, n2)) {
                this.ta = i;
                if (this.ta == this.sa) {
                    this.Aa = this.Ba[this.wa + 1];
                }
                else {
                    this.Aa = this.Ba[this.wa];
                }
                this.ua = this.Fa[i].x;
                this.va = this.Fa[i].y;
                break;
            }
        }
        return true;
    }
    
    public boolean a(final MouseEvent mouseEvent, final int n, final int n2) {
        if (this.ta != -1) {
            if (this.ta == this.sa && this.Ga.contains(n, n2)) {
                this.ka = false;
                this.ja = true;
            }
            else {
                this.ua = this.Fa[this.ta].x;
                this.va = this.Fa[this.ta].y;
            }
            this.ta = -1;
            this.Aa = null;
            this.repaint();
        }
        return true;
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 506: {
                this.b(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
            }
            default: {
                mouseEvent.consume();
            }
        }
    }
    
    public boolean b(final MouseEvent mouseEvent, final int n, final int n2) {
        if (this.ta != -1) {
            this.ua = n - 20;
            this.va = n2 - 20;
            this.repaint();
        }
        return true;
    }
    
    public OddOneOut() {
        this.oa = 6;
        this.pa = 5;
        this.qa = 35;
        this.ra = 50;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFEEBA9);
        }
        return new String(array);
    }
    
    static {
        OddOneOut.ib = a(OddOneOut.ib);
        OddOneOut.jb = a(OddOneOut.jb);
        OddOneOut.kb = a(OddOneOut.kb);
        OddOneOut.lb = a(OddOneOut.lb);
        OddOneOut.mb = a(OddOneOut.mb);
        OddOneOut.nb = a(OddOneOut.nb);
        OddOneOut.ob = a(OddOneOut.ob);
        OddOneOut.c = a(OddOneOut.c);
        OddOneOut.d = a(OddOneOut.d);
        OddOneOut.e = a(OddOneOut.e);
        OddOneOut.f = a(OddOneOut.f);
    }
}
