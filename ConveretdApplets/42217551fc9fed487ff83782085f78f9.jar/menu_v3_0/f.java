// 
// Decompiled by Procyon v0.5.30
// 

package menu_v3_0;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.image.ImageObserver;
import java.applet.AudioClip;
import java.util.Vector;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.Canvas;

public class f extends Canvas
{
    private MenuCanvasListener J;
    private Graphics h;
    private Image l;
    private Dimension S;
    private Color g;
    private Color C;
    public Color D;
    public Color char;
    public Color L;
    public Color int;
    public Color new;
    public Color long;
    public Color do;
    public Color u;
    public Color R;
    private Image b;
    public Vector O;
    private Image j;
    private Image try;
    private Image I;
    private Image p;
    private Image G;
    private Image f;
    public AudioClip k;
    public AudioClip for;
    private int null;
    private int B;
    private int U;
    public int a;
    public int d;
    public int r;
    public int T;
    public int K;
    public int t;
    public int A;
    public int c;
    public int H;
    public int v;
    public int m;
    private String i;
    private Vector F;
    private Vector s;
    private Vector P;
    private int void;
    private int N;
    private boolean o;
    public boolean if;
    public boolean case;
    public boolean q;
    private boolean byte;
    private int M;
    private Color n;
    private int Q;
    public int E;
    public int else;
    public int z;
    public Color w;
    public Color goto;
    public Color e;
    
    public f(final String i, final Color g, final Color c, final boolean byte1, final Color n, int m) {
        this.g = Color.black;
        this.C = Color.white;
        this.D = Color.black;
        this.char = Color.white;
        this.L = Color.white;
        this.int = Color.black;
        this.new = Color.yellow;
        this.R = Color.white;
        this.a = 10;
        this.d = 2;
        this.r = 1;
        this.K = 1;
        this.t = 1;
        this.A = 1;
        this.c = 1;
        this.H = 2;
        this.v = 15;
        this.void = -1;
        this.N = -1;
        this.o = false;
        this.if = true;
        this.case = true;
        this.q = false;
        this.byte = true;
        this.M = 12;
        this.n = new Color(12632268);
        this.E = 7;
        this.else = 9;
        this.z = 9;
        this.w = new Color(10066329);
        this.goto = new Color(10066329);
        this.e = new Color(13421772);
        this.i = i;
        this.g = g;
        this.C = c;
        this.byte = byte1;
        this.n = n;
        this.M = m;
        if (this.M < 4) {
            m = 4;
        }
    }
    
    public synchronized void if(final int q) {
        this.Q = q;
        this.repaint();
    }
    
    public synchronized void a(Vector s, final Vector o, final Vector p12, final Image b, final int u, final Image try1, final Image j, final Image i, final Image p13, final Image g, final Image f, final MenuCanvasListener k) {
        if (s == null) {
            s = new Vector();
        }
        this.U = u;
        this.b = b;
        this.try = try1;
        this.j = j;
        this.I = i;
        this.p = p13;
        this.G = g;
        this.f = f;
        this.s = s;
        this.O = o;
        this.P = p12;
        this.o = true;
        this.J = k;
        this.a(this.getGraphics());
    }
    
    public synchronized void a(final int null) {
        this.null = null;
        if (this.F != null) {
            this.a(this.getGraphics());
        }
    }
    
    public synchronized int do() {
        return this.B;
    }
    
    public synchronized void do(final int b) {
        this.B = b;
        if (this.F != null) {
            this.a(this.getGraphics());
        }
    }
    
    public synchronized void a(final int null, final int b) {
        this.null = null;
        this.B = b;
        if (this.F != null) {
            this.a(this.getGraphics());
        }
    }
    
    public synchronized void finalize() throws Throwable {
        synchronized (this.b) {
            if (this.b != null) {
                this.b.flush();
                this.b = null;
            }
        }
        // monitorexit(this.b)
        if (this.j != null) {
            this.j.flush();
            this.j = null;
        }
        if (this.try != null) {
            this.try.flush();
            this.try = null;
        }
        if (this.p != null) {
            this.p.flush();
            this.p = null;
        }
        if (this.O != null) {
            for (int size = this.O.size(), i = 0; i < size; ++i) {
                final g g = this.O.elementAt(i);
                if (g.if != null) {
                    g.if.flush();
                    g.if = null;
                }
            }
        }
        if (this.l != null) {
            this.l.flush();
            this.l = null;
        }
    }
    
    public synchronized int for() {
        if (this.F != null) {
            final int size = this.F.size();
            if (size > 0) {
                final d d = this.F.elementAt(size - 1);
                int n = Math.max(d.for, d.null);
                if (this.q && d.try.null != null) {
                    n = Math.max(n, d.goto);
                }
                return n + this.r;
            }
        }
        return -1;
    }
    
    public synchronized void a() {
        if (this.void >= 0) {
            this.void = -1;
            this.a(this.getGraphics());
        }
    }
    
    public synchronized e do(final int n, final int n2) {
        final e if1 = this.if(n, n2);
        if (if1 != null && if1.void != this.void) {
            this.void = if1.void;
            this.a(this.getGraphics());
        }
        else if (if1 == null) {
            this.a();
        }
        return if1;
    }
    
    private void a(final e e) {
        Vector vector;
        if (e.else == null) {
            vector = this.s;
        }
        else {
            vector = e.else.null;
        }
        if (vector != null) {
            for (int i = 0; i < vector.size(); ++i) {
                final e e2 = vector.elementAt(i);
                if (e2 != e && e2.goto != 20) {
                    e2.goto = 30;
                }
            }
            if (e.else != null) {
                this.a(e.else);
            }
        }
    }
    
    public synchronized e a(final int n, final int n2, final String s, final String s2) {
        final e if1 = this.if(n, n2);
        if (if1 != null && (if1.null != null || (if1.f != null && s != null) || (if1.int != null && s2 != null) || if1.a != null)) {
            if (this.if && (if1.goto != 20 || if1.a != null || (if1.f != null && s != null) || (if1.int != null && s2 != null))) {
                this.a(if1);
            }
            if (if1.null != null) {
                if (((this.N == if1.void && ((if1.f != null && s != null) || (if1.int != null && s2 != null) || if1.a != null)) || if1.goto == 30 || ((if1.f == null || s == null) && (if1.int == null || s2 == null) && if1.a == null)) && if1.goto != 20) {
                    if (if1.goto == 10) {
                        if1.goto = 30;
                    }
                    else {
                        if1.goto = 10;
                    }
                }
                if (if1.goto != 20 && (if1.f == null || s == null) && (if1.int == null || s2 == null)) {
                    if (if1.goto == 10) {
                        if (this.k != null) {
                            this.k.play();
                        }
                    }
                    else if (this.for != null) {
                        this.for.play();
                    }
                }
            }
            if ((if1.f != null && s != null) || (if1.int != null && s2 != null) || if1.a != null) {
                this.N = if1.void;
            }
            this.F = this.a(this.s, if1);
            final e if2 = this.if(n, n2);
            if (if2 != null) {
                this.void = if2.void;
            }
            else {
                this.void = -1;
            }
            this.a(this.getGraphics());
            return if1;
        }
        return null;
    }
    
    public synchronized e if(final int n, final int n2) {
        final Dimension size = this.getSize();
        final int n3 = n + this.null;
        final int n4 = n2 + this.B;
        if (this.F == null) {
            return null;
        }
        if (n < 0 || n2 < 0 || n > size.width - 1 || n2 > size.height - 1) {
            return null;
        }
        for (int size2 = this.F.size(), i = 0; i < size2; ++i) {
            final d d = this.F.elementAt(i);
            if (n4 >= this.a && n3 >= d.byte && n3 <= d.do) {
                if (n4 >= d.void && n4 <= d.null) {
                    return d.try;
                }
                if (d.try.c != null && n3 >= d.else && n3 <= d.char && n4 >= d.new && n4 <= d.for) {
                    return d.try;
                }
                if (this.q && d.try.null != null && n3 >= d.if && n3 <= d.a && n4 >= d.long && n4 <= d.goto) {
                    return d.try;
                }
            }
        }
        return null;
    }
    
    private Vector a(final Vector vector, final e e) {
        final Vector vector2 = new Vector();
        final Dimension dimension = new Dimension(0, 0);
        this.a(vector, vector2, dimension, 0, null, this.a, e);
        if (this.B + this.S.height > dimension.height) {
            this.B = dimension.height - this.S.height;
            if (this.B < 0) {
                this.B = 0;
            }
        }
        if (this.J != null) {
            this.J.a(dimension);
        }
        return vector2;
    }
    
    private int a(final Vector vector, final Vector vector2, final Dimension dimension, final int case1, final d d, int n, final e e) {
        final int size = vector.size();
        final int n2 = this.P.size() - 1;
        for (int i = 0; i < size; ++i) {
            final e try1 = vector.elementAt(i);
            final d d2 = new d();
            a case2;
            if (try1.case != null) {
                case2 = try1.case;
            }
            else if (case1 > n2) {
                case2 = this.P.elementAt(n2);
            }
            else {
                case2 = this.P.elementAt(case1);
            }
            final FontMetrics fontMetrics = this.getFontMetrics(case2.do);
            final int height = fontMetrics.getHeight();
            int size2;
            if (try1.b != null) {
                size2 = try1.b.size();
            }
            else {
                size2 = 1;
            }
            final Image if1 = this.if(try1);
            int n3;
            if (if1 != null) {
                if1.getHeight(this);
                n3 = Math.max(this.m, if1.getWidth(this));
            }
            else {
                n3 = this.m;
            }
            if (this.q) {
                n3 = Math.max(n3, this.else);
            }
            int max = 0;
            if (try1.b != null) {
                for (int j = 0; j < try1.b.size(); ++j) {
                    max = Math.max(max, fontMetrics.stringWidth((String)try1.b.elementAt(j)));
                }
            }
            final int if2 = this.H + case1 * this.v;
            int else1;
            if (this.q) {
                if (d != null) {
                    d2.if = d.else + (d.char - d.else + 1) / 2 - this.else / 2;
                }
                else {
                    d2.if = if2;
                }
                d2.long = n + this.d;
                d2.a = d2.if + this.else - 1;
                if (try1.null == null) {
                    d2.goto = d2.long;
                }
                else {
                    d2.goto = d2.long + this.z - 1;
                }
                else1 = d2.a + 1 + this.E;
            }
            else {
                else1 = if2;
            }
            d2.else = else1;
            d2.new = n + this.d;
            if (if1 == null) {
                d2.char = d2.else + n3 - 1;
                d2.for = d2.new;
            }
            else {
                d2.char = d2.else + if1.getWidth(this) - 1;
                d2.for = d2.new + if1.getHeight(this) + case2.new - 1;
            }
            d2.int = d2.else + n3 + this.T;
            d2.do = d2.int + max - 1 + this.A + case2.try + this.c;
            d2.void = n + this.d;
            d2.null = d2.void + height * size2 - 1 + case2.new + this.K + this.t;
            d2.case = case1;
            if (this.q && try1.null != null) {
                d2.byte = d2.if;
            }
            else if (if1 != null) {
                d2.byte = d2.else;
            }
            else {
                d2.byte = d2.int;
            }
            int n4 = Math.max(height + this.K + this.t, d2.for - d2.new + 1);
            if (this.q) {
                n4 = Math.max(n4, d2.goto - d2.long + 1);
                if (d2.goto - d2.long + 1 < n4) {
                    final int n5 = (n4 - (d2.goto - d2.long + 1)) / 2;
                    final d d3 = d2;
                    d3.long += n5;
                    final d d4 = d2;
                    d4.goto += n5;
                }
            }
            if (d2.for - d2.new + 1 < n4) {
                final int n6 = (n4 - (d2.for - d2.new + 1)) / 2;
                final d d5 = d2;
                d5.new += n6;
                final d d6 = d2;
                d6.for += n6;
            }
            if (d2.null - d2.void + 1 < n4) {
                final int n7 = (n4 - (d2.null - d2.void + 1)) / 2;
                final d d7 = d2;
                d7.void += n7;
                final d d8 = d2;
                d8.null += n7;
            }
            d2.try = try1;
            vector2.addElement(d2);
            if (try1 == e && this.B > n) {
                this.B = n;
            }
            n = Math.max(d2.for, d2.null);
            if (this.q && try1.null != null) {
                n = Math.max(n, d2.goto);
            }
            n += this.r;
            dimension.width = Math.max(dimension.width, d2.do);
            dimension.height = n;
            if ((try1.goto == 10 || try1.goto == 20) && try1.null != null) {
                n = this.a(try1.null, vector2, dimension, case1 + 1, d2, n, e);
            }
        }
        return n;
    }
    
    public void paint(final Graphics graphics) {
        this.update(graphics);
    }
    
    public void update(final Graphics graphics) {
        this.a(graphics);
    }
    
    public synchronized void a(final Graphics graphics) {
        this.if();
        if (this.l != null) {
            graphics.drawImage(this.l, 0, 0, this);
        }
    }
    
    private void a(final Graphics graphics, final Dimension dimension, final String s) {
        if (dimension != null && graphics != null) {
            graphics.setColor(this.g);
            graphics.fillRect(0, 0, dimension.width, dimension.height);
            if (s != null || this.byte) {
                int n = dimension.height / 2;
                if (s != null) {
                    final FontMetrics fontMetrics = this.getFontMetrics(new Font("Serif", 0, 9));
                    fontMetrics.getHeight();
                    final int stringWidth = fontMetrics.stringWidth(s);
                    final int descent = fontMetrics.getDescent();
                    final int n2 = (dimension.width - stringWidth) / 2;
                    final int n3 = n + descent;
                    graphics.setColor(this.C);
                    graphics.drawString(s, n2, n3);
                    n += 10 + this.M / 2;
                }
                if (this.byte) {
                    final int n4 = (int)(dimension.width * 0.8);
                    final int n5 = n4 * this.Q / 100;
                    final int n6 = (int)(dimension.width * 0.1);
                    final int n7 = n - this.M / 2;
                    graphics.setColor(this.n);
                    graphics.drawRect(n6, n7, n4, this.M);
                    if (n5 > 0) {
                        graphics.fill3DRect(n6, n7, n5, this.M, true);
                    }
                }
            }
        }
    }
    
    private void if() {
        final Dimension size = this.getSize();
        if (size.width > 0 && size.height > 0) {
            if (this.l == null || this.S.width != size.width || this.S.height != size.height) {
                if (this.l != null) {
                    this.l.flush();
                    this.l = null;
                }
                this.l = this.createImage(size.width, size.height);
                this.S = size;
                this.h = this.l.getGraphics();
                if (this.o) {
                    this.F = this.a(this.s, null);
                }
            }
            else if (this.o && this.F == null) {
                this.F = this.a(this.s, null);
            }
            this.h.setClip(0, 0, size.width, size.height);
            if (this.o) {
                this.h.setColor(this.D);
                this.h.fillRect(0, 0, size.width, size.height);
                if (this.b != null) {
                    this.h.drawImage(this.b, -this.U, 0, this);
                }
                this.a(this.h, this.F);
                if (!this.R.equals(this.D)) {
                    final int for1 = this.for();
                    this.h.setColor(this.R);
                    this.h.drawLine(0, this.a + this.d - this.B, 0, for1 - this.r - this.B);
                }
            }
            else {
                this.a(this.h, size, this.i);
            }
        }
    }
    
    private Image if(final e e) {
        if (e.b == null || e.b.size() == 0) {
            return null;
        }
        if (this.void == e.void) {
            if (e.e != null || e.null == null) {
                return e.e;
            }
            if (e.goto == 10 || e.goto == 20) {
                return e.new;
            }
            return e.c;
        }
        else {
            if (e.void == this.N || e.goto == 10 || e.goto == 20) {
                return e.new;
            }
            return e.c;
        }
    }
    
    private void a(final Graphics graphics, final Vector vector) {
        this.a(graphics, this.getSize(), null, vector, 0);
    }
    
    private void a(final Graphics graphics, final d d, final int n) {
        final int n2 = d.if + (d.a - d.if + 1) / 2 - this.null;
        final int n3 = d.long + (d.goto - d.long + 1) / 2 - this.B;
        graphics.setColor(this.goto);
        graphics.drawRect(d.if - this.null, d.long - this.B, d.a - d.if, d.goto - d.long);
        graphics.setColor(this.e);
        graphics.drawLine(d.if - this.null + 2, n3, d.a - this.null - 2, n3);
        if (n == 30) {
            graphics.drawLine(n2, d.long - this.B + 2, n2, d.goto - this.B - 2);
        }
    }
    
    private void a(final Graphics graphics, final int n, int n2, final int n3, final int n4) {
        graphics.setColor(this.w);
        while (n2 <= n3 && n2 <= n4) {
            graphics.drawLine(n, n2, n, n2);
            n2 += 2;
        }
    }
    
    private void a(final Graphics graphics, final d d, final d d2, final d d3) {
        int n;
        if (d2 == null && d != null) {
            n = d.for - this.B + 2;
        }
        else {
            if (d2 == null) {
                return;
            }
            n = d2.goto - this.B + 2;
        }
        if (d3 != null) {
            final int n2 = d3.if + (d3.a - d3.if + 1) / 2 - this.null;
            int n3 = d3.long - this.B - 2;
            if (d3.try.null == null) {
                n3 += 2;
            }
            int n4 = n;
            final int height = this.S.height;
            graphics.setColor(this.w);
            while (n4 <= n3 && n4 <= height) {
                graphics.drawLine(n2, n4, n2, n4);
                n4 += 2;
            }
        }
    }
    
    private void if(final Graphics graphics, int n, final int n2, final int n3, final int n4) {
        graphics.setColor(this.w);
        while (n <= n2 && n <= n4) {
            graphics.drawLine(n, n3, n, n3);
            n += 2;
        }
    }
    
    private void a(final Graphics graphics, final d d) {
        if (d.try.null != null || d.try.a != null || (d.try.g != null && d.try.f != null) || (d.try.try != null && d.try.int != null)) {
            final int n = d.long + (d.goto - d.long + 1) / 2 - this.B;
            final int n2 = d.else - this.null - 2;
            int n3;
            if (d.try.null == null) {
                n3 = d.if + (d.a - d.if + 1) / 2 + 2 - this.null;
            }
            else {
                n3 = d.a + 2 - this.null;
            }
            int n4;
            if (d.try.c == null) {
                n4 = d.else + (d.char - d.else) / 2 - this.null;
            }
            else {
                n4 = d.else - this.null - 2;
            }
            int n5 = n3;
            final int width = this.S.width;
            graphics.setColor(this.w);
            while (n5 <= n4 && n5 <= width) {
                graphics.drawLine(n5, n, n5, n);
                n5 += 2;
            }
        }
    }
    
    private int a(final Graphics graphics, final Dimension dimension, final d d, final Vector vector, int i) {
        int case1 = 0;
        final int size = vector.size();
        final int n = this.P.size() - 1;
        d d2 = null;
        d d3 = null;
        if (i >= 0 && i < size) {
            case1 = vector.elementAt(i).case;
        }
        while (i < size) {
            final d d4 = vector.elementAt(i);
            final e try1 = d4.try;
            if (d4.case > case1) {
                i = this.a(graphics, dimension, d2, vector, i);
            }
            else {
                if (d4.case < case1) {
                    if (this.q && d3 != null) {
                        this.a(graphics, d, d2, d3);
                    }
                    return i - 1;
                }
                if (try1.b != null) {
                    if (this.q) {
                        if (try1.null != null) {
                            this.a(graphics, d4, try1.goto);
                            this.a(graphics, d, d2, d4);
                            d2 = d4;
                            d3 = null;
                        }
                        else {
                            d3 = d4;
                        }
                        this.a(graphics, d4);
                    }
                    if (Math.min(d4.new, d4.void) - this.B <= dimension.height && Math.max(d4.for, d4.null) - this.B >= 0) {
                        a case2;
                        if (try1.case != null) {
                            case2 = try1.case;
                        }
                        else if (d4.case > n) {
                            case2 = this.P.elementAt(n);
                        }
                        else {
                            case2 = this.P.elementAt(d4.case);
                        }
                        graphics.setFont(case2.do);
                        final FontMetrics fontMetrics = graphics.getFontMetrics();
                        final int ascent = fontMetrics.getAscent();
                        final int height = fontMetrics.getHeight();
                        final Image if1 = this.if(try1);
                        if (if1 != null) {
                            int n2 = d4.else - this.null;
                            int n3 = d4.new - this.B;
                            if (this.void == try1.void && case2.for) {
                                n2 += case2.try;
                                n3 += case2.new;
                            }
                            graphics.drawImage(if1, n2, n3, this);
                        }
                        Color color;
                        Color color2;
                        if (this.void == try1.void && ((try1.null != null && try1.goto != 20) || (try1.f != null && try1.g != null) || (try1.int != null && try1.try != null) || try1.a != null)) {
                            if (this.case && try1.b != null) {
                                graphics.setColor(this.char);
                                if (case2.for) {
                                    graphics.fillRect(d4.int - this.null + case2.try, d4.void - this.B + case2.new, d4.do - d4.int + 1 - case2.try, d4.null - d4.void + 1 - case2.new);
                                }
                                else {
                                    graphics.fillRect(d4.int - this.null, d4.void - this.B, d4.do + 1 - d4.int, d4.null + 1 - d4.void);
                                }
                            }
                            color = this.int;
                            color2 = this.u;
                        }
                        else if (try1.void == this.N) {
                            color = this.new;
                            color2 = this.do;
                        }
                        else {
                            if (try1.for == null) {
                                color = this.L;
                            }
                            else {
                                color = try1.for;
                            }
                            if (try1.byte == null) {
                                color2 = this.long;
                            }
                            else {
                                color2 = try1.byte;
                            }
                        }
                        final int n4 = d4.int + this.A - this.null;
                        int n5 = d4.void + this.K + ascent - this.B;
                        for (int j = 0; j < try1.b.size(); ++j) {
                            final String s = try1.b.elementAt(j);
                            if (this.void == try1.void && case2.for) {
                                graphics.setColor(color);
                                graphics.drawString(s, n4 + case2.try, n5 + case2.new);
                            }
                            else {
                                if ((case2.try != 0 || case2.new != 0) && case2.byte && color2 != null) {
                                    graphics.setColor(color2);
                                    graphics.drawString(s, n4 + case2.try, n5 + case2.new);
                                }
                                graphics.setColor(color);
                                graphics.drawString(s, n4, n5);
                            }
                            n5 += height;
                        }
                    }
                }
            }
            ++i;
        }
        if (this.q && d3 != null) {
            this.a(graphics, d, d2, d3);
        }
        return i;
    }
}
