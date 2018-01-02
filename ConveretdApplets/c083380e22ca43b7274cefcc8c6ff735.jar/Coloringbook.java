import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.image.ImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.CropImageFilter;
import java.awt.Component;
import java.awt.MediaTracker;
import java.util.Vector;
import java.util.StringTokenizer;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.Panel;
import java.awt.FontMetrics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Coloringbook extends Applet
{
    private static final int Va = 380;
    private static final int Wa = 280;
    Image Xa;
    Graphics Ya;
    Dimension size;
    Image Za;
    Image _b;
    Image Sa;
    Color[] ab;
    Color bb;
    FontMetrics cb;
    Panel db;
    Rectangle eb;
    Rectangle fb;
    Rectangle gb;
    n hb;
    n[] ib;
    o Ua;
    Color jb;
    Color kb;
    boolean lb;
    boolean mb;
    boolean nb;
    int x;
    int y;
    int ob;
    int pb;
    int qb;
    int[] rb;
    int sb;
    int tb;
    int _;
    Rectangle a;
    Rectangle[] b;
    Rectangle c;
    p J;
    q d;
    private static String t = "\u25a3\u25ab\u25bd\u25bd\u25af\u25a9\u25ab\u25a8\u25a7\u25a2\u25ab";
    private static String y = "\u25a3\u25ab\u25bd\u25bd\u25af\u25a9\u25ab\u25bd\u2591\u25ab\u25a0\u25e0\u25aa\u25af\u25ba";
    private static String z = "\u25a3\u25a7\u25a0\u25a7\u25a3\u25bb\u25a3\u25ee\u25bd\u25a7\u25b4\u25ab\u25ee\u25a7\u25bd\u25f4\u25ee\u25b9\u25a7\u25aa\u25ba\u25a6\u25f3";
    private static String A = "\u25e2\u25ee\u25a6\u25ab\u25a7\u25a9\u25a6\u25ba\u25f3";
    private static String B = "\u25ac\u25af\u25ad\u25a5\u25ad\u25a1\u25a2\u25a1\u25bc";
    private static String C = "\u25a8\u25a1\u25a0\u25ba\u25ad\u25a1\u25a2\u25a1\u25bc";
    private static String D = "\u258f\u25bc\u25a7\u25af\u25a2";
    private static String M = "\u25a0\u25a1\u25bc\u25ba\u25a6\u25be\u25a1\u25a2\u25ab\u25e0\u25ad\u25a1\u25a3";
    private static String N = "\u25ad\u25a1\u25a2\u25a1\u25bc\u25bd";
    private static String O = "\u25e2";
    private static String P = "\u25ad\u25a1\u25a2\u25a1\u25bc\u25bd\u25ee\u25be\u25af\u25bc\u25af\u25a3\u25ab\u25ba\u25ab\u25bc\u25ee\u25a0\u25ab\u25ab\u25aa\u25bd\u25ee\u25ff\u25f8\u25ee\u25ad\u25a1\u25a2\u25a1\u25bc\u25ee\u25b8\u25af\u25a2\u25bb\u25ab\u25bd\u25f5\u25ee\u25bb\u25bd\u25a7\u25a0\u25a9\u25ee\u25aa\u25ab\u25a8\u25af\u25bb\u25a2\u25ba\u25ee\u25be\u25af\u25a2\u25ab\u25ba\u25ba\u25ab";
    private static String Q = "\u25a7\u25a2\u25a2\u25ab\u25a9\u25af\u25a2\u25ee\u25b8\u25af\u25a2\u25bb\u25ab\u25ee\u25af\u25ba\u25ee\u25ad\u25a1\u25a2\u25a1\u25bc\u25bd\u25ee\u25be\u25af\u25bc\u25af\u25a3\u25ab\u25ba\u25ab\u25bc\u25f5\u25ee\u25bb\u25bd\u25a7\u25a0\u25a9\u25ee\u25aa\u25ab\u25a8\u25af\u25bb\u25a2\u25ba\u25ee\u25be\u25af\u25a2\u25ab\u25ba\u25ba\u25ab";
    private static String R = "\u25a7\u25a3\u25af\u25a9\u25ab\u25ff";
    private static String S = "\u25a7\u25a3\u25af\u25a9\u25ab\u25ff\u25ee\u25be\u25af\u25bc\u25af\u25a3\u25ab\u25ba\u25ab\u25bc\u25ee\u25a7\u25bd\u25ee\u25a3\u25a7\u25bd\u25bd\u25a7\u25a0\u25a9";
    private static String T = "\u25a7\u25a3\u25af\u25a9\u25ab";
    private static String U = "\u25ad\u25a1\u25a2\u25a1\u25bc\u25ac\u25a1\u25a1\u25a5\u2591\u25ac\u25a9\u25e0\u25a9\u25a7\u25a8";
    
    public void init() {
        String s = this.getParameter(Coloringbook.t);
        if (s == null) {
            s = Coloringbook.y;
        }
        this.J = new p(this.getCodeBase(), s);
        this.d = new q(this, this.J);
        this.size = this.getSize();
        if (this.size.width < 280 || this.size.height < 380) {
            this.d.c(Coloringbook.z + 280 + Coloringbook.A + 380);
        }
        this.jb = this.d.a(Coloringbook.B, new Color(7039975));
        this.kb = this.d.a(Coloringbook.C, Color.white);
        this.setBackground(this.jb);
        this.setFont(new Font(Coloringbook.D, 1, 12));
        this.cb = this.getFontMetrics(this.getFont());
    }
    
    public boolean e() {
        if (this.d.a() && !this.d.a(Coloringbook.M)) {
            this.d.a(true);
            return false;
        }
        if (!this.f()) {
            return false;
        }
        this.Za = this.a(this.Za);
        this.Xa = this.createImage(this.size.width, this.size.height);
        this.Ya = this.Xa.getGraphics();
        this.a = new Rectangle(36, this.size.height - 34, 25, 24);
        this.c = this.a;
        this.Ua = new o(this.size.width - 82);
        final String n = this.d.n(Coloringbook.N);
        this.ab = this.Ua.Ka;
        if (n != null) {
            final StringTokenizer stringTokenizer = new StringTokenizer(n, Coloringbook.O);
            if (stringTokenizer.countTokens() != 16) {
                this.d.b(Coloringbook.P);
            }
            else {
                final Color[] array = new Color[16];
                for (int i = 0; i < 16; ++i) {
                    try {
                        array[i] = q.a(stringTokenizer.nextToken().trim());
                    }
                    catch (IllegalArgumentException ex) {
                        this.d.b(Coloringbook.Q);
                        break;
                    }
                }
                this.Ua.Ka = array;
                this.ab = array;
            }
        }
        if (this.d.n(Coloringbook.R) == null) {
            return this.d.c(Coloringbook.S);
        }
        final Image[] _ = this.d._(Coloringbook.T, 0);
        this.ib = new n[_.length];
        for (int j = 0; j < this.ib.length; ++j) {
            this.ib[j] = new n(_[j], this.gb, this.Ua);
        }
        this.b[0] = new Rectangle(62, this.size.height - 34, 25, 24);
        this.b[1] = new Rectangle(89, this.size.height - 34, 25, 24);
        this.b[2] = new Rectangle(119, this.size.height - 34, 25, 24);
        this.eb = new Rectangle(this.size.width - 89, this.size.height - 72, 30, 27);
        this.fb = new Rectangle(this.size.width - 50, this.size.height - 71, 30, 27);
        this.hb = this.ib[this._];
        this.x = this.hb.getX();
        this.y = this.hb.getY();
        this.enableEvents(48L);
        return !this.d.d();
    }
    
    private boolean f() {
        final Vector<Image> vector = new Vector<Image>();
        vector.addElement(this.Za = this.getImage(this.getCodeBase(), Coloringbook.U));
        this.d._(vector, 0);
        return !this.d.d();
    }
    
    private Image a(final Image image) {
        final int n = 26;
        final int n2 = 115;
        final int n3 = 165;
        final int n4 = 305;
        final int n5 = 75;
        final int n6 = 287;
        final int n7 = 100;
        final ImageProducer source = image.getSource();
        final Image[] array = new Image[9];
        final MediaTracker mediaTracker = new MediaTracker(this);
        mediaTracker.addImage(array[0] = this.createImage(new FilteredImageSource(source, new CropImageFilter(0, 0, n3, n4))), 0);
        mediaTracker.addImage(array[1] = this.createImage(new FilteredImageSource(source, new CropImageFilter(n3, 0, 1, n))), 0);
        mediaTracker.addImage(array[2] = this.createImage(new FilteredImageSource(source, new CropImageFilter(n6 - n2, 0, n2, n4))), 0);
        mediaTracker.addImage(array[3] = this.createImage(new FilteredImageSource(source, new CropImageFilter(n6 - n2, n4, n2, 1))), 0);
        mediaTracker.addImage(array[4] = this.createImage(new FilteredImageSource(source, new CropImageFilter(n6 - n2, n4, n2, n5))), 0);
        mediaTracker.addImage(array[5] = this.createImage(new FilteredImageSource(source, new CropImageFilter(n3, n4, 1, n5))), 0);
        mediaTracker.addImage(array[6] = this.createImage(new FilteredImageSource(source, new CropImageFilter(0, n4, n3, n5))), 0);
        mediaTracker.addImage(array[7] = this.createImage(new FilteredImageSource(source, new CropImageFilter(0, n4, n, 1))), 0);
        mediaTracker.addImage(array[8] = this.createImage(new FilteredImageSource(source, new CropImageFilter(n + 10, n + 10, n7, n7))), 0);
        try {
            mediaTracker.waitForID(0);
        }
        catch (InterruptedException ex) {}
        final Image image2 = this.createImage(this.size.width, this.size.height);
        final Graphics graphics = image2.getGraphics();
        for (int i = 0; i < this.size.height / n7 + 1; ++i) {
            for (int j = 0; j < this.size.width / n7 + 1; ++j) {
                graphics.drawImage(array[8], j * n7, i * n7, this);
            }
        }
        graphics.drawImage(array[0], 0, 0, this);
        graphics.drawImage(array[2], this.size.width - n2, 0, this);
        graphics.drawImage(array[4], this.size.width - n2, this.size.height - n5, this);
        graphics.drawImage(array[6], 0, this.size.height - n5, this);
        for (int n8 = this.size.width - (n3 + n2), k = 0; k < n8; ++k) {
            graphics.drawImage(array[1], n3 + k, 0, this);
            graphics.drawImage(array[5], n3 + k, this.size.height - n5, this);
        }
        for (int n9 = this.size.height - 380, l = 0; l < n9; ++l) {
            graphics.drawImage(array[3], this.size.width - n2, n4 + l, this);
            graphics.drawImage(array[7], 0, n4 + l, this);
        }
        this.gb = new Rectangle();
        this.gb.x = n;
        this.gb.y = n;
        this.gb.width = this.size.width - (n2 + n);
        this.gb.height = this.size.height - n5;
        return image2;
    }
    
    private void b(final Graphics graphics) {
        this.d._(graphics);
        final String _ = this.J._();
        graphics.setColor(this.kb);
        graphics.drawString(_, this.d._(_, true, graphics), this.d._(_, false, graphics));
        if (this.e()) {
            this.mb = true;
            this.d._(graphics);
            this.repaint();
            return;
        }
        this.d._(graphics);
        String s;
        if (this.d._()) {
            s = q.E;
        }
        else {
            s = this.J.a();
        }
        graphics.setColor(this.kb);
        graphics.drawString(s, this.d._(s, true, graphics), this.d._(s, false, graphics));
    }
    
    public void update(final Graphics graphics) {
        if (!this.mb) {
            this.b(graphics);
            return;
        }
        this.Ya.drawImage(this.Za, 0, 0, this);
        if (this.hb != null) {
            this.Ya.drawImage(this.hb.b(), this.hb.getX(), this.hb.getY(), this);
        }
        this.Ua.a(this.Ya);
        this.Ya.setColor(this.bb);
        this.Ya.drawRect(this.c.x, this.c.y, this.c.width, this.c.height);
        graphics.drawImage(this.Xa, 0, 0, this);
        if (this.d.d()) {
            this.showStatus(this.J.b());
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.getID() == 501) {
            this.b(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    private boolean b(final MouseEvent mouseEvent, int x, int y) {
        if (this.gb.contains(x, y)) {
            if (this.nb) {
                x -= this.x;
                y -= this.y;
                this.hb._(x, y, this.Ua.b());
            }
            else {
                this.ob = x - this.x;
                this.pb = y - this.y;
                this._(mouseEvent, x, y);
            }
            this.repaint();
            return true;
        }
        if (this.eb.contains(x, y)) {
            this.hb.clear();
            this.repaint();
            return true;
        }
        if (this.fb.contains(x, y)) {
            ++this._;
            if (this._ >= this.ib.length) {
                this._ = 0;
            }
            this.hb = this.ib[this._];
            x = this.hb.getX();
            y = this.hb.getY();
            this.repaint();
            return true;
        }
        if (this.Ua.contains(x, y)) {
            this.Ua.b(x, y);
            this.repaint();
            return true;
        }
        if (this.a.contains(x, y)) {
            this.c = this.a;
            this.nb = true;
            this.repaint();
            return true;
        }
        for (int i = 0; i < this.b.length; ++i) {
            if (this.b[i].contains(x, y)) {
                this.nb = false;
                this.tb = i;
                this.c = this.b[i];
                this.repaint();
                return true;
            }
        }
        return true;
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        if (mouseEvent.getID() == 506) {
            this._(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
        }
    }
    
    private boolean _(final MouseEvent mouseEvent, int ob, int pb) {
        if (!this.nb && this.gb.contains(ob, pb)) {
            ob -= this.x;
            pb -= this.y;
            ob -= this.rb[this.tb] / 2;
            pb -= this.rb[this.tb] / 2;
            this.hb._(this.ob, this.pb, ob, pb, this.rb[this.tb]);
            this.ob = ob;
            this.pb = pb;
            this.repaint();
        }
        return true;
    }
    
    public Coloringbook() {
        this.bb = new Color(11384319);
        this.nb = true;
        this.rb = new int[] { 6, 10, 15 };
        this.b = new Rectangle[3];
    }
    
    private static String _(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ '\u25ce');
        }
        return new String(array);
    }
    
    static {
        Coloringbook.t = _(Coloringbook.t);
        Coloringbook.y = _(Coloringbook.y);
        Coloringbook.z = _(Coloringbook.z);
        Coloringbook.A = _(Coloringbook.A);
        Coloringbook.B = _(Coloringbook.B);
        Coloringbook.C = _(Coloringbook.C);
        Coloringbook.D = _(Coloringbook.D);
        Coloringbook.M = _(Coloringbook.M);
        Coloringbook.N = _(Coloringbook.N);
        Coloringbook.O = _(Coloringbook.O);
        Coloringbook.P = _(Coloringbook.P);
        Coloringbook.Q = _(Coloringbook.Q);
        Coloringbook.R = _(Coloringbook.R);
        Coloringbook.S = _(Coloringbook.S);
        Coloringbook.T = _(Coloringbook.T);
        Coloringbook.U = _(Coloringbook.U);
    }
}
