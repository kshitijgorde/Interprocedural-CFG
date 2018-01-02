import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.ImageObserver;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.applet.Applet;

// 
// Decompiled by Procyon v0.5.30
// 

public class Pyramid extends Applet
{
    Image Z;
    Graphics _a;
    Dimension size;
    FontMetrics aa;
    Font ba;
    Font ca;
    char[][] da;
    char[][] ea;
    String fa;
    String[] ga;
    boolean ha;
    boolean ia;
    boolean ja;
    boolean[][] ka;
    boolean la;
    boolean ma;
    boolean na;
    Color oa;
    Color pa;
    Color qa;
    Color ra;
    Color sa;
    Color ta;
    Color ua;
    int va;
    int wa;
    int xa;
    int ya;
    int za;
    int Aa;
    int Ba;
    int Ca;
    int Da;
    int Ea;
    int Fa;
    implements[] Ga;
    implements W;
    Rectangle Ha;
    Rectangle Ia;
    import fb;
    instanceof Ja;
    private static String Ua = "\u1389\u1381\u1397\u1397\u1385\u1383\u1381\u1382\u138d\u1388\u1381";
    private static String Va = "\u1389\u1381\u1397\u1397\u1385\u1383\u1381\u1397\u13bb\u1381\u138a\u13ca\u1380\u1385\u1390";
    private static String Wa = "\u1386\u1385\u1387\u138f\u13a7\u138b\u1388\u138b\u1396";
    private static String Xa = "\u1382\u138b\u138a\u1390\u13a7\u138b\u1388\u138b\u1396";
    private static String Ya = "\u13a5\u1396\u138d\u1385\u1388";
    private static String Za = "\u13b0\u138d\u1389\u1381\u1397\u13b6\u138b\u1389\u1385\u138a";
    private static String _b = "\u13b4\u139d\u1396\u1385\u1389\u138d\u1380\u13c4\u1396\u1381\u1387\u138b\u1389\u1389\u1381\u138a\u1380\u1381\u1380\u13c4\u1397\u138d\u139e\u1381\u13de\u13c4\u1393\u138d\u1380\u1390\u138c\u13d9";
    private static String ib = "\u13c8\u13c4\u138c\u1381\u138d\u1383\u138c\u1390\u13d9";
    private static String jb = "\u1380\u1385\u1390\u1385\u1382\u138d\u1388\u1381";
    private static String kb = "\u1383\u1396\u138d\u1380\u13a7\u138b\u1388\u138b\u1396";
    private static String lb = "\u1386\u138b\u1396\u1380\u13a7\u138b\u1388\u138b\u1396";
    private static String mb = "\u1397\u1381\u1388\u1381\u13a7\u138b\u1388\u138b\u1396";
    private static String nb = "\u1387\u1391\u1396\u1397\u13a7\u138b\u1388\u138b\u1396";
    private static String ob = "\u1387\u138b\u1396\u1396\u13a7\u138b\u1388\u138b\u1396";
    private static String pb = "\u1386\u138b\u139c\u13c9\u1397\u138d\u139e\u1381";
    private static String qb = "\u1382\u138d\u1396\u1397\u1390\u13c9\u1396\u138b\u1393\u13c9\u1397\u138d\u139e\u1381";
    private static String rb = "\u1388\u1385\u1397\u1390\u13c9\u1396\u138b\u1393\u13c9\u1397\u138d\u139e\u1381";
    private static String sb = "\u1386\u138b\u1396\u1380\u1381\u1396\u13c9\u1397\u138d\u139e\u1381";
    private static String tb = "\u1382\u138b\u138a\u1390\u13b7\u138d\u139e\u1381";
    private static String _ = "\u1382\u138b\u138a\u1390\u13b7\u1390\u139d\u1388\u1381";
    private static String a = "\u1387\u1388\u1391\u1381\u13a8\u1381\u1390\u1390\u1381\u1396\u13ab\u138a";
    private static String b = "\u1385\u1391\u1390\u138b\u13bb\u1387\u138b\u1396\u1396\u1381\u1387\u1390";
    private static String c = "\u1380\u1396\u1385\u1393\u13a6\u138b\u1396\u1380\u1381\u1396";
    private static String d = "\u1391\u1394\u1394\u1381\u1396\u13a7\u1385\u1397\u1381";
    private static String e = "\u1380\u1385\u1390\u1385\u1382\u138d\u1388\u1381\u13c4\u1394\u1385\u1396\u1385\u1389\u1381\u1390\u1381\u1396\u13c4\u138d\u1397\u13c4\u1389\u138d\u1397\u1397\u138d\u138a\u1383";
    private static String f = "\u1387\u1388\u1391\u1381\u1397";
    private static String g = "\u1383\u1396\u138d\u1380";
    private static String h = "\u1396\u1381\u1385\u1380\u138d\u138a\u1383\u13c4";
    private static String i = "\u13c4\u138c\u1385\u1397\u13c4\u1382\u1385\u138d\u1388\u1381\u1380";
    
    public void init() {
        String s = this.getParameter(Pyramid.Ua);
        if (s == null) {
            s = Pyramid.Va;
        }
        this.fb = new import(this.getCodeBase(), s);
        this.Ja = new instanceof(this, this.fb);
        this.size = this.getSize();
        this.oa = this.Ja.b(Pyramid.Wa, Color.white);
        this.qa = this.Ja.b(Pyramid.Xa, Color.black);
        this.setBackground(this.oa);
        this.setFont(new Font(Pyramid.Ya, 1, 12));
        this.aa = this.getFontMetrics(this.getFont());
    }
    
    private boolean n() {
        if (this.Ja.a() && !this.Ja._(instanceof.cb)) {
            this.Ja.b(true);
            return false;
        }
        if (!this.c() || !this.d()) {
            return false;
        }
        this.Z = this.createImage(this.size.width, this.size.height);
        (this._a = this.Z.getGraphics()).setFont(new Font(Pyramid.Za, this.Ea, this.Fa));
        this.aa = this.getFontMetrics(this._a.getFont());
        this.Ca = interface._('m', this.Aa, this._a.getFont()) + 1;
        this.W = this.Ga[0];
        System.out.println(Pyramid._b + (2 * this.Da + this.ya * this.Aa) + Pyramid.ib + (2 * this.Da + (this.ya + 2 - this.xa) * this.Aa + 10));
        this.requestFocus();
        this.enableEvents(24L);
        return !this.Ja.k();
    }
    
    private boolean c() {
        this.fa = this.Ja.l(Pyramid.jb);
        this.pa = this.Ja.b(Pyramid.kb, Color.black);
        this.ua = this.Ja.b(Pyramid.lb, Color.black);
        this.qa = this.Ja.b(Pyramid.Xa, Color.black);
        this.ra = this.Ja.b(Pyramid.mb, Color.yellow);
        this.sa = this.Ja.b(Pyramid.nb, Color.green);
        this.ta = this.Ja.b(Pyramid.ob, Color.red);
        this.Aa = this.Ja._(Pyramid.pb, 24) / 2 * 2;
        this.xa = this.Ja.b(Pyramid.qb);
        this.ya = this.Ja.b(Pyramid.rb);
        this.Da = this.Ja._(Pyramid.sb, 5);
        this.Fa = this.Ja._(Pyramid.tb, 14);
        this.Ea = this.Ja.a(Pyramid._, 1);
        this.la = this.Ja.b(Pyramid.a, false);
        this.ha = this.Ja.b(Pyramid.b, false);
        this.ma = this.Ja.b(Pyramid.c, false);
        this.na = this.Ja.b(Pyramid.d, true);
        return !this.Ja.k();
    }
    
    public boolean d() {
        final String a = this.Ja.a(Pyramid.jb, (String)null);
        if (a == null) {
            return this.Ja.l(Pyramid.e);
        }
        final native native1 = new native();
        if (native1.a(this.getCodeBase(), a)) {
            this.ga = native1.a(Pyramid.f);
            final String[] a2 = native1.a(Pyramid.g);
            this.za = this.ya - this.xa + 1;
            this.Ga = new implements[this.za];
            this.Ba = this.Da + this.ya * this.Aa / 2 - this.xa * this.Aa / 2;
            int ba = this.Ba;
            final int da = this.Da;
            for (int i = 0; i < this.za; ++i) {
                (this.Ga[i] = new implements(a2[i], 0, ba, i * this.Aa + this.Da, this.Aa))._(true);
                ba -= this.Aa / 2;
            }
            this.ka = new boolean[this.za][];
            for (int j = 0; j < this.za; ++j) {
                this.ka[j] = new boolean[this.xa + j];
                final String e = this.Ga[j].e();
                for (int k = 0; k < this.ka[j].length; ++k) {
                    if (!Character.isLowerCase(e.charAt(k))) {
                        this.ka[j][k] = true;
                    }
                }
            }
            this.ea = new char[this.za][];
            for (int l = 0; l < this.za; ++l) {
                if (this.na) {
                    this.ea[l] = this.Ga[l].e().toUpperCase().toCharArray();
                }
                else {
                    this.ea[l] = this.Ga[l].e().toLowerCase().toCharArray();
                }
            }
            this.da = new char[this.za][];
            for (int n = 0; n < this.za; ++n) {
                this.da[n] = new char[this.xa + n];
                for (int n2 = 0; n2 < this.da[n].length; ++n2) {
                    if (this.ka[n][n2]) {
                        this.da[n][n2] = this.ea[n][n2];
                    }
                }
            }
            return true;
        }
        return this.Ja.l(Pyramid.h + a + Pyramid.i);
    }
    
    private void a() {
        this._a.setColor(this.oa);
        this._a.fillRect(0, 0, this.size.width, this.size.height);
        if (this.W != null) {
            this._a.setColor(this.ra);
            this.Ha = this.W.bounds();
            this._a.fillRect(this.Ha.x, this.Ha.y, this.Ha.width, this.Ha.height);
            if (!this.W.l()) {
                this._a.setColor(this.sa);
                this.Ia = this.W.b(this.ka[this.va], this.ja);
                this._a.fillRect(this.Ia.x, this.Ia.y, this.Aa, this.Aa);
            }
        }
        int ba = this.Ba;
        int da = this.Da;
        this._a.setColor(this.pa);
        for (int i = 0; i < this.za; ++i) {
            for (int n = this.xa + i, j = 0; j < n; ++j) {
                this._a.drawRect(ba + j * this.Aa, da, this.Aa, this.Aa);
            }
            ba -= this.Aa / 2;
            da += this.Aa;
        }
        this._a.drawRect(this.Da, da + 10, this.ya * this.Aa, this.Aa);
        if (this.ma) {
            instanceof._(2, 2, this.size.width - 5, this.size.height - 5, this._a);
        }
    }
    
    private void b() {
        int ba = this.Ba;
        int n = this.Da + this.Ca;
        this._a.setColor(this.qa);
        for (int i = 0; i < this.za; ++i) {
            for (int n2 = this.xa + i, j = 0; j < n2; ++j) {
                if (this.da[i][j] != '\0') {
                    if (this.ha && !this._(this.da[i][j], this.ea[i][j])) {
                        this._a.setColor(this.ta);
                    }
                    else {
                        this._a.setColor(this.qa);
                    }
                    if (this.ka[i][j]) {
                        this._a.drawChars(this.ea[i], j, 1, ba + (this.Aa - this.aa.charWidth(this.ea[i][j])) / 2 + j * this.Aa, n);
                    }
                    else {
                        this._a.drawChars(this.da[i], j, 1, ba + (this.Aa - this.aa.charWidth(this.da[i][j])) / 2 + j * this.Aa, n);
                    }
                }
            }
            ba -= this.Aa / 2;
            n += this.Aa;
        }
    }
    
    private void f() {
        this._a.drawString(this.ga[this.va], this.Da + 10, this.za * this.Aa + this.Ca + this.Da + 10);
    }
    
    private boolean _(final char c, final char c2) {
        return c == c2;
    }
    
    private void _(final Graphics graphics) {
        this.Ja.b(graphics);
        final String _ = this.fb._();
        graphics.setColor(this.qa);
        graphics.drawString(_, this.Ja.a(_, true, graphics), this.Ja.a(_, false, graphics));
        if (this.n()) {
            this.ia = true;
            this.Ja.b(graphics);
            this.repaint();
            return;
        }
        this.Ja.b(graphics);
        String s;
        if (this.Ja._()) {
            s = instanceof.ab;
        }
        else {
            s = this.fb.a();
        }
        graphics.setColor(this.qa);
        graphics.drawString(s, this.Ja.a(s, true, graphics), this.Ja.a(s, false, graphics));
    }
    
    public void update(final Graphics graphics) {
        if (!this.ia) {
            this._(graphics);
            return;
        }
        this.a();
        this.b();
        this.f();
        graphics.drawImage(this.Z, 0, 0, this);
        if (this.Ja.k()) {
            this.showStatus(this.fb.b());
        }
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        switch (mouseEvent.getID()) {
            case 501: {
                this._(mouseEvent, mouseEvent.getX(), mouseEvent.getY());
            }
            default: {
                mouseEvent.consume();
            }
        }
    }
    
    public boolean _(final MouseEvent mouseEvent, final int n, final int n2) {
        if (n2 > this.Da && n2 < this.Da + this.za * this.Aa) {
            this.va = (n2 - this.Da) / this.Aa;
            this.g();
            if (mouseEvent.getClickCount() == 2 && this.la) {
                final int n3 = (n - this.W.bounds().x) / this.Aa;
                this.da[this.va][n3] = this.ea[this.va][n3];
            }
            this.repaint();
        }
        return true;
    }
    
    private void g() {
        if (this.va >= 0 && this.va < this.Ga.length) {
            this.ja = true;
            (this.W = this.Ga[this.va]).l(0);
        }
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        switch (keyEvent.getID()) {
            case 401: {
                this.b(keyEvent, keyEvent.getKeyCode());
            }
            default: {
                keyEvent.consume();
            }
        }
    }
    
    public boolean b(final KeyEvent keyEvent, final int n) {
        if (n == 8) {
            if (this.W != null) {
                this.da[this.va][this.W.h()] = '\0';
                this.W.b(-1);
                this.ja = false;
                this.repaint();
            }
            return true;
        }
        if (n == 127) {
            if (this.W != null && !this.e()) {
                this.da[this.va][this.W.h()] = '\0';
                this.repaint();
            }
            return true;
        }
        if (n > 64 && n < 1000) {
            if (this.W != null && !this.e()) {
                char c;
                if (this.na) {
                    c = Character.toUpperCase((char)n);
                }
                else {
                    c = Character.toLowerCase((char)n);
                }
                this.da[this.va][this.W.h()] = c;
                this.W.b(1);
                this.ja = true;
                this.repaint();
            }
            this.repaint();
            return true;
        }
        if (n == 38) {
            if (this.va > 0) {
                --this.va;
                this.g();
                this.repaint();
            }
            return true;
        }
        if (n == 40) {
            if (this.va < this.za - 1) {
                ++this.va;
                this.g();
                this.repaint();
            }
            return true;
        }
        if (n == 37) {
            if (this.W != null) {
                this.W.b(-1);
                this.ja = false;
                this.repaint();
            }
            return true;
        }
        if (n == 39) {
            if (this.W != null) {
                this.W.b(1);
                this.ja = true;
                this.repaint();
            }
            return true;
        }
        return true;
    }
    
    private boolean e() {
        return this.ka[this.va][this.W.h()];
    }
    
    public Pyramid() {
        this.ja = true;
    }
    
    private static String a(final String s) {
        final int length = s.length();
        final char[] array = new char[length];
        for (int i = 0; i < length; ++i) {
            array[i] = (char)(s.charAt(i) ^ 0xFFFF13E4);
        }
        return new String(array);
    }
    
    static {
        Pyramid.Ua = a(Pyramid.Ua);
        Pyramid.Va = a(Pyramid.Va);
        Pyramid.Wa = a(Pyramid.Wa);
        Pyramid.Xa = a(Pyramid.Xa);
        Pyramid.Ya = a(Pyramid.Ya);
        Pyramid.Za = a(Pyramid.Za);
        Pyramid._b = a(Pyramid._b);
        Pyramid.ib = a(Pyramid.ib);
        Pyramid.jb = a(Pyramid.jb);
        Pyramid.kb = a(Pyramid.kb);
        Pyramid.lb = a(Pyramid.lb);
        Pyramid.mb = a(Pyramid.mb);
        Pyramid.nb = a(Pyramid.nb);
        Pyramid.ob = a(Pyramid.ob);
        Pyramid.pb = a(Pyramid.pb);
        Pyramid.qb = a(Pyramid.qb);
        Pyramid.rb = a(Pyramid.rb);
        Pyramid.sb = a(Pyramid.sb);
        Pyramid.tb = a(Pyramid.tb);
        Pyramid._ = a(Pyramid._);
        Pyramid.a = a(Pyramid.a);
        Pyramid.b = a(Pyramid.b);
        Pyramid.c = a(Pyramid.c);
        Pyramid.d = a(Pyramid.d);
        Pyramid.e = a(Pyramid.e);
        Pyramid.f = a(Pyramid.f);
        Pyramid.g = a(Pyramid.g);
        Pyramid.h = a(Pyramid.h);
        Pyramid.i = a(Pyramid.i);
    }
}
