import java.awt.Event;
import java.awt.image.ImageObserver;
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

public class ColoringSums extends Applet
{
    Image Ja;
    Graphics Ka;
    Dimension size;
    Image La;
    Image Ba;
    Image Ma;
    Image[] Na;
    Color Oa;
    Color Pa;
    FontMetrics Qa;
    Panel Ra;
    Rectangle Sa;
    o Ta;
    p Ua;
    q[][] Va;
    String Wa;
    boolean Xa;
    boolean Ya;
    int Ha;
    int Ia;
    int Za;
    int _b;
    int ab;
    r q;
    s bb;
    private static String e = "\u0791\u0799\u078f\u078f\u079d\u079b\u0799\u079a\u0795\u0790\u0799";
    private static String f = "\u0791\u0799\u078f\u078f\u079d\u079b\u0799\u078f\u07a3\u0799\u0792\u07d2\u0798\u079d\u0788";
    private static String g = "\u079e\u079d\u079f\u0797\u079f\u0793\u0790\u0793\u078e";
    private static String h = "\u079a\u0793\u0792\u0788\u079f\u0793\u0790\u0793\u078e";
    private static String i = "\u07bd\u078e\u0795\u079d\u0790";
    private static String j = "\u0798\u079d\u0788\u079d\u079a\u0795\u0790\u0799";
    private static String k = "\u0798\u079d\u0788\u079d\u079a\u0795\u0790\u0799\u07dc\u078c\u079d\u078e\u079d\u0791\u0799\u0788\u0799\u078e\u07dc\u0795\u078f\u07dc\u0791\u0795\u078f\u078f\u0795\u0792\u079b";
    private static String t = "\u0795\u0791\u079d\u079b\u0799";
    private static String u = "\u0791\u0793\u078e\u0799\u07dc\u0788\u0794\u079d\u0792\u07dc\u07cd\u07ca\u07dc\u0790\u0795\u0792\u0799\u078f\u07dc\u0795\u0792\u07dc\u078f\u0799\u079f\u0788\u0795\u0793\u0792\u07c6\u07dc";
    private static String v = "\u07d0";
    private static String w = "\u0795\u0790\u0790\u0799\u079b\u079d\u0790\u07dc\u078a\u079d\u0790\u0789\u0799\u07dc\u0795\u0792\u07dc\u078f\u0799\u079f\u0788\u0795\u0793\u0792\u07c6\u07dc";
    private static String x = "\u078e\u0799\u079d\u0798\u0795\u0792\u079b\u07dc";
    private static String y = "\u07dc\u0794\u079d\u078f\u07dc\u079a\u079d\u0795\u0790\u0799\u0798";
    private static String z = "\u079f\u0793\u0790\u0793\u078e\u078f\u0789\u0791\u078f\u07a3\u079e\u079b\u07d2\u079b\u0795\u079a";
    private static String A = "\u07bf\u0793\u078c\u0785\u078e\u0795\u079b\u0794\u0788\u07dc\u07d4\u079f\u07d5\u07dc\u07ce\u07cc\u07cc\u07cd\u07d0\u07dc";
    private static String B = "\u078b\u079d\u0795\u0788\u0795\u0792\u079b\u07a3\u0791\u078f\u079b";
    
    public void init() {
        String s = this.getParameter(ColoringSums.e);
        if (s == null) {
            s = ColoringSums.f;
        }
        this.q = new r(this.getCodeBase(), s);
        this.bb = new s(this, this.q);
        this.size = this.size();
        this.Oa = this.bb.a(ColoringSums.g, Color.white);
        this.Pa = this.bb.a(ColoringSums.h, Color.black);
        this.setBackground(this.Oa);
        this.setFont(new Font(ColoringSums.i, 1, 12));
        this.Qa = this.getFontMetrics(this.getFont());
    }
    
    public boolean e() {
        if (this.bb._() && !this.bb.a(s.n)) {
            this.bb.b(true);
            return false;
        }
        if (!this.f() || !this.g()) {
            return false;
        }
        this.Ja = this.createImage(this.size.width, this.size.height);
        this.Ka = this.Ja.getGraphics();
        final Font font = new Font(ColoringSums.i, 1, 12);
        this.setFont(font);
        this.Ka.setFont(font);
        this.Qa = this.Ka.getFontMetrics();
        this.Sa = new Rectangle(407, 313, 53, 21);
        final int length = this.Va[this._b].length;
        final Color[] array = new Color[length];
        final int[] array2 = new int[length];
        for (int i = 0; i < length; ++i) {
            array[i] = this.Va[this._b][i]._();
            array2[i] = this.Va[this._b][i].a();
        }
        this.Ua = new p(array, array2);
        this.c();
        return !this.bb.d();
    }
    
    private void a(final Image image) {
        (this.Ta = new o(image, this.Ha, this.Ia)).b(-16777216);
        this.Ta._(-3355444);
        this.Ta.init();
        this.Ma = this.createImage(this.Ta.a(-16777216));
    }
    
    private boolean g() {
        final String a = this.bb.a(ColoringSums.j, (String)null);
        if (a == null) {
            return this.bb.n(ColoringSums.k);
        }
        final t t = new t();
        if (t._(this.getCodeBase(), a)) {
            final int _ = t._();
            this.Va = new q[_][];
            for (int i = 0; i < _; ++i) {
                final String[] b = t.b(ColoringSums.t + (i + 1));
                if (b.length > 16) {
                    return this.bb.n(ColoringSums.u + (i + 1));
                }
                this.Va[i] = new q[b.length];
                for (int j = 0; j < this.Va[i].length; ++j) {
                    final StringTokenizer stringTokenizer = new StringTokenizer(b[j], ColoringSums.v);
                    while (stringTokenizer.hasMoreTokens()) {
                        try {
                            final Color b2 = s.b(stringTokenizer.nextToken().trim());
                            final int int1 = Integer.parseInt(stringTokenizer.nextToken().trim());
                            final Vector vector = new Vector<String>();
                            while (stringTokenizer.hasMoreTokens()) {
                                vector.addElement(stringTokenizer.nextToken().trim());
                            }
                            final int[] array = new int[vector.size()];
                            for (int k = 0; k < array.length; ++k) {
                                array[k] = (0xFF000000 | Integer.parseInt(vector.elementAt(k), 16));
                            }
                            this.Va[i][j] = new q(b2, array, int1);
                        }
                        catch (NumberFormatException ex) {
                            return this.bb.n(ColoringSums.w + (i + 1));
                        }
                    }
                }
            }
            return !this.bb.d();
        }
        return this.bb.n(ColoringSums.x + a + ColoringSums.y);
    }
    
    private boolean f() {
        final Vector<Image> vector = new Vector<Image>();
        vector.addElement(this.La = this.getImage(this.getCodeBase(), ColoringSums.z));
        this.bb.a(vector, 0);
        this.Na = this.bb.a(ColoringSums.t, 0);
        this.Ha = this.Na[0].getWidth(this);
        this.Ia = this.Na[0].getHeight(this);
        return !this.bb.d();
    }
    
    private void a(final Graphics graphics) {
        this.bb.b(graphics);
        final String a = this.q.a();
        graphics.setColor(this.Pa);
        graphics.drawString(a, this.bb.a(a, true, graphics), this.bb.a(a, false, graphics));
        if (this.e()) {
            this.Ya = true;
            this.bb.b(graphics);
            this.repaint();
            return;
        }
        this.bb.b(graphics);
        String s;
        if (this.bb.b()) {
            s = ColoringSums.A + s.l;
        }
        else {
            s = this.q.b();
        }
        graphics.setColor(this.Pa);
        graphics.drawString(s, this.bb.a(s, true, graphics), this.bb.a(s, false, graphics));
    }
    
    public void update(final Graphics graphics) {
        if (!this.Ya) {
            this.a(graphics);
            return;
        }
        if (this.La != null) {
            this.Ka.drawImage(this.La, 0, 0, this);
        }
        this.Ka.drawImage(this.Ma, this.ab, this.ab, this);
        this.Ua._(this.Ka);
        graphics.drawImage(this.Ja, 0, 0, this);
        if (this.bb.d()) {
            this.showStatus(this.q._());
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    private boolean b(final int n, final int n2) {
        return n > this.ab && n < this.ab + this.Ha && n2 > this.ab && n2 < this.ab + this.Ia;
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (this.Ua.inside(n, n2)) {
            this.Ua.a(n, n2);
            this.repaint();
        }
        else if (this.b(n, n2)) {
            this.b(n - this.ab, n2 - this.ab);
        }
        else if (this.Sa.inside(n, n2)) {
            ++this._b;
            if (this._b >= this.Va.length) {
                this._b = 0;
            }
            this.d();
            final int length = this.Va[this._b].length;
            final Color[] array = new Color[length];
            final int[] array2 = new int[length];
            for (int i = 0; i < length; ++i) {
                array[i] = this.Va[this._b][i]._();
                array2[i] = this.Va[this._b][i].a();
            }
            this.Ua.b(array);
            this.Ua.b(array2);
            this.Ua._();
            this.c();
        }
        return true;
    }
    
    private void b(final int n, int a) {
        if (this.Ta.b(n, a) == -4144960) {
            a = this.a(n, a);
        }
        if (this._(n, a) && this.Ta._(n, a) != this.Ta.b(n, a)) {
            this.Ta.b(this.Ua.b());
            this.Ma = this.createImage(this.Ta._(n, a));
            this.repaint();
        }
    }
    
    private int a(final int n, int n2) {
        do {
            ++n2;
        } while (this.Ta.b(n, n2) == -4144960);
        return n2;
    }
    
    private boolean _(final int n, final int n2) {
        final int[] _ = this.Va[this._b][this.Ua.l()]._();
        for (int length = _.length, i = 0; i < length; ++i) {
            if (this.Ta.b(n, n2) == _[i]) {
                return true;
            }
        }
        return false;
    }
    
    private void c() {
        this.a(this.Na[this._b]);
        this.Ua.reset();
        this.repaint();
    }
    
    private void d() {
        final String j = this.q.j(ColoringSums.B);
        final Graphics graphics = this.getGraphics();
        graphics.setColor(Color.white);
        graphics.drawString(j, 40, this.size.height - 17);
        graphics.dispose();
    }
    
    public ColoringSums() {
        this.ab = 20;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF07FC);
        }
        return new String(array);
    }
    
    static {
        ColoringSums.e = a(ColoringSums.e);
        ColoringSums.f = a(ColoringSums.f);
        ColoringSums.g = a(ColoringSums.g);
        ColoringSums.h = a(ColoringSums.h);
        ColoringSums.i = a(ColoringSums.i);
        ColoringSums.j = a(ColoringSums.j);
        ColoringSums.k = a(ColoringSums.k);
        ColoringSums.t = a(ColoringSums.t);
        ColoringSums.u = a(ColoringSums.u);
        ColoringSums.v = a(ColoringSums.v);
        ColoringSums.w = a(ColoringSums.w);
        ColoringSums.x = a(ColoringSums.x);
        ColoringSums.y = a(ColoringSums.y);
        ColoringSums.z = a(ColoringSums.z);
        ColoringSums.A = a(ColoringSums.A);
        ColoringSums.B = a(ColoringSums.B);
    }
}
