import java.awt.Event;
import java.awt.image.ImageObserver;
import java.applet.AudioClip;
import java.awt.Font;
import java.util.Vector;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Leapfrog extends Applet
{
    Graphics sa;
    Image ta;
    Image ua;
    Image va;
    Image wa;
    Dimension size;
    FontMetrics xa;
    Color ya;
    Color za;
    Color Aa;
    Rectangle Ba;
    Rectangle Ca;
    int[] Da;
    int[] Ea;
    int[] Fa;
    static final int Ga = 0;
    static final int Ha = 1;
    static final int Ia = 2;
    int Ja;
    int Ka;
    int La;
    int Ma;
    int Na;
    int Oa;
    boolean Pa;
    boolean Qa;
    boolean Ra;
    Vector Sa;
    Font Ta;
    Font Ua;
    AudioClip Va;
    AudioClip Wa;
    u s;
    v Xa;
    private static String d = "\u9371\u9379\u936f\u936f\u937d\u937b\u9379\u937a\u9375\u9370\u9379";
    private static String e = "\u9371\u9379\u936f\u936f\u937d\u937b\u9379\u936f\u9343\u9379\u9372\u9332\u9378\u937d\u9368";
    private static String f = "\u936e\u9375\u937e\u937e\u9375\u9368";
    private static String g = "\u936b\u9375\u9372\u934f\u9373\u9369\u9372\u9378";
    private static String h = "\u937e\u937d\u937f\u9377\u937f\u9373\u9370\u9373\u936e";
    private static String i = "\u937a\u9373\u9372\u9368\u935f\u9373\u9370\u9373\u936e";
    private static String j = "\u935d\u936e\u9375\u937d\u9370";
    private static String v = "\u937a\u936e\u9373\u937b\u9332\u937b\u9375\u937a";
    private static String w = "\u9370\u9379\u937d\u936c\u937a\u936e\u9373\u937b\u9343\u937e\u937b\u9332\u9376\u936c\u937b";
    private static String x = "\u9365\u9373\u9369\u936b\u9375\u9372\u9331\u937a\u936e\u9373\u937b\u9332\u9376\u936c\u937b";
    private static String y = "\u9368\u9379\u9364\u9368\u935f\u9373\u9370\u9373\u936e";
    private static String z = "\u9369\u9372\u9378\u9373\u9370\u937d\u937e\u9343\u9371\u936f\u937b";
    private static String A = "\u936e\u9379\u936f\u9379\u9368\u9370\u937e\u9343\u9371\u936f\u937b";
    private static String B = "\u936f\u937f\u9373\u936e\u9379\u9370\u937e\u9343\u9371\u936f\u937b";
    private static String C = "\u9374\u9375\u936f\u937f\u9373\u936e\u9379\u9343\u9371\u936f\u937b";
    
    public void init() {
        String s = this.getParameter(Leapfrog.d);
        if (s == null) {
            s = Leapfrog.e;
        }
        this.s = new u(this.getCodeBase(), s);
        this.Xa = new v(this, this.s);
        this.Va = this.Xa._(Leapfrog.f);
        this.Wa = this.Xa._(Leapfrog.g);
        this.size = this.size();
        this.za = this.Xa.a(Leapfrog.h, Color.white);
        this.Aa = this.Xa.a(Leapfrog.i, Color.black);
        this.setBackground(this.za);
        this.setFont(new Font(Leapfrog.j, 1, 12));
        this.xa = this.getFontMetrics(this.getFont());
    }
    
    private boolean k() {
        if (this.Xa._() && !this.Xa.b(v.p)) {
            this.Xa.b(true);
            return false;
        }
        if (!this.l() || !this.m()) {
            return false;
        }
        this.ta = this.createImage(this.size.width, this.size.height);
        this.sa = this.ta.getGraphics();
        this.Ta = new Font(Leapfrog.j, 1, 11);
        this.Ua = new Font(Leapfrog.j, 1, 11);
        this.setFont(new Font(Leapfrog.j, 1, 11));
        this.Ba = new Rectangle(10, 280, 80, 20);
        this.Ca = new Rectangle(225, 280, 80, 20);
        this.reset();
        return !this.Xa.j();
    }
    
    private boolean m() {
        final Vector<Image> vector = new Vector<Image>();
        vector.addElement(this.ua = this.getImage(this.getCodeBase(), Leapfrog.v));
        vector.addElement(this.wa = this.getImage(this.getCodeBase(), Leapfrog.w));
        vector.addElement(this.va = this.getImage(this.getCodeBase(), Leapfrog.x));
        this.Xa._(vector, 0);
        return !this.Xa.j();
    }
    
    private boolean l() {
        this.ya = this.Xa.a(Leapfrog.y, new Color(13434828));
        return !this.Xa.j();
    }
    
    public void reset() {
        this.b();
        this.Pa = false;
        this.Qa = false;
        if (this.Ka > this.La) {
            this.La = this.Ka;
        }
        final boolean b = false;
        this.Ka = (b ? 1 : 0);
        this.Ja = (b ? 1 : 0);
        this.Sa = new Vector();
        this.repaint();
    }
    
    public void b() {
        final int[] array = { 0, 1, 5, 6, 7, 8, 12, 13, 35, 36, 40, 41, 42, 43, 47, 48 };
        int i = 0;
    Label_0150:
        while (i < 49) {
            while (true) {
                for (int j = 0; j < 16; ++j) {
                    if (i == array[j]) {
                        this.Da[i] = 2;
                        ++i;
                        continue Label_0150;
                    }
                }
                if (i == 24) {
                    this.Da[i] = 0;
                    continue;
                }
                this.Da[i] = 1;
                continue;
            }
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    private void _(final Graphics graphics) {
        this.Xa.b(graphics);
        final String b = this.s.b();
        graphics.setColor(this.Aa);
        graphics.drawString(b, this.Xa.b(b, true, graphics), this.Xa.b(b, false, graphics));
        if (this.k()) {
            this.Ra = true;
            this.Xa.b(graphics);
            this.repaint();
        }
        else {
            this.Xa.b(graphics);
            String s;
            if (this.Xa.b()) {
                s = v.n;
            }
            else {
                s = this.s._();
            }
            graphics.setColor(this.Aa);
            graphics.drawString(s, this.Xa.b(s, true, graphics), this.Xa.b(s, false, graphics));
        }
    }
    
    public void update(final Graphics graphics) {
        if (!this.Ra) {
            this._(graphics);
            return;
        }
        this.sa.drawImage(this.wa, 0, 0, this);
        for (int i = 0; i < 49; ++i) {
            if (this.Da[i] != 2 && this.Da[i] == 1) {
                this.sa.drawImage(this.ua, this.Oa + i % 7 * this.Ma, this.Oa + i / 7 * this.Na, this);
            }
        }
        this.sa.setColor(this.ya);
        this.sa.setFont(this.Ua);
        final String j = this.s.j(Leapfrog.z);
        this.sa.drawString(j, v._(j, true, this.Ba, this.sa), 293);
        final String k = this.s.j(Leapfrog.A);
        this.sa.drawString(k, v._(k, true, this.Ca, this.sa), 293);
        this.sa.setFont(this.Ta);
        final String l = this.s.j(Leapfrog.B);
        this.sa.drawString(l, v._(l, true, this.Ba, this.sa), 30);
        final String value = String.valueOf(this.Ka);
        this.sa.drawString(value, v._(value, true, this.Ba, this.sa), 50);
        final String m = this.s.j(Leapfrog.C);
        this.sa.drawString(m, v._(m, true, this.Ca, this.sa), 30);
        final String value2 = String.valueOf(this.La);
        this.sa.drawString(value2, v._(value2, true, this.Ca, this.sa), 50);
        graphics.drawImage(this.ta, 0, 0, this);
        if (this.Xa.j()) {
            this.showStatus(this.s.a());
        }
    }
    
    private void a(final Color color, final int n) {
        final Graphics graphics = this.getGraphics();
        graphics.setColor(color);
        graphics.drawRect(this.Oa + n % 7 * this.Ma + 1, this.Oa + n / 7 * this.Na + 1, this.Ma - 3, this.Na - 3);
    }
    
    private boolean _(final int n, final int n2) {
        return n > this.Oa && n < this.size.width - this.Oa && (n2 > this.Oa & n2 < 290);
    }
    
    public boolean mouseDown(final Event event, final int n, final int n2) {
        if (!this.Qa && this.Ba.inside(n, n2) && this.Sa.size() > 0) {
            final abstract abstract1 = this.Sa.lastElement();
            this.Da[abstract1._()] = 1;
            this.Da[abstract1.a()] = 1;
            this.Da[abstract1.m()] = 0;
            this.Sa.removeElement(abstract1);
            this.Ka -= 10;
            this.repaint();
            return true;
        }
        if (this.Qa && this.Ca.inside(n, n2)) {
            this.reset();
            this.repaint();
            return true;
        }
        if (this._(n, n2)) {
            if (this.Pa) {
                this.a(n, n2);
                this.repaint();
                return true;
            }
            this._(n, n2);
        }
        return true;
    }
    
    public boolean mouseUp(final Event event, final int n, final int n2) {
        if (!this.Pa) {
            if (this.n()) {
                this._();
            }
            else {
                this.repaint();
            }
        }
        return true;
    }
    
    private boolean _(final int n) {
        return (this.a(n + 2, n + 1) && this.b(n, n + 2)) || (this.a(n - 2, n - 1) && this.b(n, n - 2)) || (this.a(n + 14, n + 7) && this.b(n, n + 14)) || (this.a(n - 14, n - 7) && this.b(n, n - 14));
    }
    
    public void _(final int n, final int n2) {
        final int _ = this._(n, n2);
        if (this.Da[_] == 1) {
            if (this._(_)) {
                this.Ja = _;
                this.Pa = true;
                this.Da[_] = 0;
                this.a(Color.yellow, _);
            }
            else {
                this.a(Color.red, _);
            }
        }
    }
    
    private int _(final int n, final int n2) {
        return (n2 - this.Oa) / this.Na * 7 + (n - this.Oa) / this.Ma;
    }
    
    public void a(final int n, final int n2) {
        final int _ = this._(n, n2);
        if (this.b(_, this.Ja)) {
            if (this.c(_, this.Ja)) {
                final int a = this.a(_, this.Ja);
                if (this.a(_, a)) {
                    final int n3 = _;
                    this.Da[this.Ja] = 0;
                    this.Da[a] = 0;
                    this.Da[n3] = 1;
                    this.Ka += 10;
                    this.Pa = false;
                    v.a(this.Va);
                    this.Sa.addElement(new abstract(this.Ja, a, n3));
                }
                else {
                    this.a();
                }
            }
            else {
                this.a();
            }
        }
        else {
            this.a();
        }
    }
    
    private void a() {
        this.Pa = false;
        this.Da[this.Ja] = 1;
    }
    
    private boolean b(final int n, final int n2) {
        final int n3 = n2 / 7;
        final int n4 = n2 % 7;
        final int n5 = n / 7;
        final int n6 = n % 7;
        return n3 == n5 || n4 == n6;
    }
    
    private boolean c(final int n, final int n2) {
        return Math.abs(n - n2) == 2 || Math.abs(n - n2) == 14;
    }
    
    private boolean a(final int n, final int n2) {
        return n2 > 0 && n2 < 48 && n > 0 && n < 48 && this.Da[n] == 0 && this.Da[n2] == 1;
    }
    
    private int a(final int n, final int n2) {
        return n2 - (n2 - n) / 2;
    }
    
    private boolean n() {
        for (int i = 0; i < 49; ++i) {
            if (this._(i) && this.Da[i] == 1) {
                return false;
            }
        }
        return true;
    }
    
    public void _() {
        this.Qa = true;
        if (this.Ka == 310) {
            this.getGraphics().drawImage(this.va, (this.size.width - this.va.getWidth(this)) / 2 - 1, (this.size.height - this.va.getHeight(this)) / 2, this);
            v.a(this.Wa);
        }
        else {
            for (int i = 0; i < 49; ++i) {
                if (this.Da[i] == 1) {
                    this.a(Color.red, i);
                }
            }
        }
    }
    
    public void destroy() {
        v._((Object)this.Va);
        v._((Object)this.Wa);
        v._(this.sa);
        v._(this.ta);
        v._(this.wa);
    }
    
    public Leapfrog() {
        this.Da = new int[49];
        this.Ea = new int[] { 150, 325, 325, 435, 435, 325, 325, 150, 150, 40, 40, 150, 150 };
        this.Fa = new int[] { 10, 10, 110, 110, 270, 270, 370, 370, 270, 270, 110, 110, 10 };
        this.Ma = 40;
        this.Na = 40;
        this.Oa = 20;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF931C);
        }
        return new String(array);
    }
    
    static {
        Leapfrog.d = a(Leapfrog.d);
        Leapfrog.e = a(Leapfrog.e);
        Leapfrog.f = a(Leapfrog.f);
        Leapfrog.g = a(Leapfrog.g);
        Leapfrog.h = a(Leapfrog.h);
        Leapfrog.i = a(Leapfrog.i);
        Leapfrog.j = a(Leapfrog.j);
        Leapfrog.v = a(Leapfrog.v);
        Leapfrog.w = a(Leapfrog.w);
        Leapfrog.x = a(Leapfrog.x);
        Leapfrog.y = a(Leapfrog.y);
        Leapfrog.z = a(Leapfrog.z);
        Leapfrog.A = a(Leapfrog.A);
        Leapfrog.B = a(Leapfrog.B);
        Leapfrog.C = a(Leapfrog.C);
    }
}
