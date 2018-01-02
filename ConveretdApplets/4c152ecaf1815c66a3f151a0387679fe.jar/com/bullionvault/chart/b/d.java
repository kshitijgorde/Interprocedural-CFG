// 
// Decompiled by Procyon v0.5.30
// 

package com.bullionvault.chart.b;

import java.util.Locale;
import java.util.Iterator;
import com.bullionvault.chart.a.r;
import java.util.Vector;
import java.text.DateFormat;
import java.util.Collection;
import com.bullionvault.chart.c.h;
import java.util.HashSet;
import com.bullionvault.chart.resources.Resources;
import java.util.Calendar;
import java.util.Set;
import com.bullionvault.chart.f.c;
import java.awt.FontMetrics;
import java.awt.Font;
import java.awt.Color;
import java.util.TimeZone;
import java.awt.Dimension;
import java.awt.Graphics;

public final class d
{
    private Graphics q;
    private com.bullionvault.chart.e.d r;
    private int s;
    private Dimension t;
    private int u;
    private boolean v;
    private TimeZone w;
    public Color a;
    public Color b;
    public Color c;
    public Color d;
    private Color x;
    public Color e;
    private Color y;
    public Color f;
    public Color g;
    public Color h;
    public Color i;
    public Color j;
    public Color k;
    public Color l;
    private Color z;
    private Color A;
    private String B;
    private String C;
    private String D;
    private a E;
    private boolean F;
    private Font G;
    private Font H;
    private FontMetrics I;
    private FontMetrics J;
    private c K;
    private float L;
    private float M;
    private float N;
    private int O;
    private int P;
    public g m;
    private f Q;
    private f R;
    public int n;
    private int S;
    private int T;
    private int U;
    private int V;
    private int W;
    private int X;
    private int Y;
    private int Z;
    private int aa;
    public int o;
    private int ab;
    public int p;
    private int ac;
    private int ad;
    private int ae;
    private int af;
    private boolean ag;
    private boolean ah;
    private float ai;
    private Set aj;
    private Set ak;
    private boolean al;
    private c am;
    private int an;
    private int ao;
    
    public d(final int n, final int n2, final int n3) {
        this.u = 1;
        this.v = false;
        this.w = Calendar.getInstance().getTimeZone();
        this.a = Color.white;
        this.b = new Color(255, 255, 199);
        this.c = new Color(255, 255, 199);
        this.d = new Color(255, 255, 239);
        this.x = Color.black;
        this.e = Color.lightGray;
        this.y = Color.black;
        this.f = Color.magenta;
        this.g = this.b;
        this.h = this.b;
        this.i = this.b;
        this.j = Color.blue.darker();
        this.k = new Color(0.1f, 0.5f, 1.0f);
        this.l = Color.red.darker();
        this.z = this.g;
        this.A = this.j;
        this.B = "";
        this.C = Resources.b("chart_painter.toz");
        this.D = Resources.b("chart_painter.kg");
        this.F = true;
        this.G = new Font("SansSerif", 0, 10);
        this.H = new Font("SansSerif", 1, 12);
        this.n = 0;
        this.S = 0;
        this.T = 5;
        this.U = 5;
        this.ag = true;
        this.ah = true;
        this.ai = -1.0f;
        this.aj = new HashSet();
        this.al = true;
        this.a(n);
        this.t = new Dimension(n2, n3);
        this.C = Resources.b("chart_painter.toz");
        this.D = Resources.b("chart_painter.kg");
    }
    
    public final void a(final Graphics q) {
        this.q = q;
        this.ag = true;
    }
    
    public final void a(final String b) {
        this.B = b;
    }
    
    public final void b(final String s) {
        if (s.equals("menu.style.point")) {
            this.s = 1;
        }
        else if (s.equals("menu.style.line")) {
            this.s = 2;
        }
        else {
            if (!s.equals("menu.style.hlc")) {
                throw new RuntimeException("Unknown Chart Style Name requested [" + s + "]");
            }
            this.s = 3;
        }
        com.bullionvault.chart.c.h.e("Chart Style set to " + s);
    }
    
    public final void a(final int s) {
        switch (s) {
            case 1:
            case 2:
            case 3: {
                com.bullionvault.chart.c.h.f("Setting Chart Style to: [" + s + "]");
                this.s = s;
            }
            default: {
                throw new RuntimeException("Unknown Chart Style ID requested [" + s + "]");
            }
        }
    }
    
    public final void a(final a e) {
        this.E = e;
    }
    
    public final void a(final com.bullionvault.chart.e.d r) {
        this.r = r;
    }
    
    public final void a(final c k) {
        if (k != null) {
            com.bullionvault.chart.c.h.e("Setting new realtime point -- [" + k.c + "]");
            if (this.K == null) {
                this.A = this.j;
                this.z = this.g;
            }
            else if (k.c != this.K.c) {
                if (k.c > this.K.c) {
                    this.A = this.k;
                    this.z = this.h;
                }
                else {
                    this.A = this.l;
                    this.z = this.i;
                }
            }
            this.K = k;
            return;
        }
        com.bullionvault.chart.c.h.e("Setting end of realtime data.");
        this.K = null;
    }
    
    public final void a(final Dimension t) {
        this.t = t;
        this.ag = true;
    }
    
    public final void a(final float ai) {
        this.ai = ai;
    }
    
    public final Set a() {
        return this.aj;
    }
    
    public final void a(final boolean al) {
        this.al = al;
    }
    
    public final boolean b() {
        return this.al;
    }
    
    public final void b(final Graphics graphics) {
        this.ah = true;
        (this.ak = new HashSet()).addAll(this.aj);
        if (this.r == null || this.r.c() <= 0) {
            com.bullionvault.chart.c.h.f("No reader set yet.");
            return;
        }
        if (this.ag) {
            if (this.q != null) {
                this.I = this.q.getFontMetrics(this.G);
                this.O = this.I.getHeight();
                this.I.getDescent();
                this.P = this.I.getAscent();
                this.J = this.q.getFontMetrics(this.H);
            }
            this.L = this.r.c;
            this.M = this.r.b;
            if (this.K != null) {
                if (this.K.c < this.M) {
                    this.M = this.K.c;
                }
                if (this.K.c > this.L) {
                    this.L = this.K.c;
                }
            }
            this.N = this.L - this.M;
            com.bullionvault.chart.c.h.g("y_axis_height=" + this.S + ", x_axis_width=" + this.n);
            this.V = this.I.stringWidth(Resources.c().format(this.L / 32.15)) + this.U;
            this.W = (this.O << 1) + this.T;
            this.X = this.I.stringWidth(Resources.b().format(this.L)) + this.U;
            this.Y = this.O + this.T;
            this.Z = this.t.height - (this.W + this.Y);
            this.aa = this.t.width - (this.V + this.X);
            this.o = this.V;
            this.ab = this.W;
            this.p = this.V + this.aa;
            this.ac = this.W + this.Z;
            this.S = this.Z;
            this.n = this.aa;
            this.ad = this.n / (this.r.c() + 1) - 1;
            if (this.ad < 2) {
                this.ad = 2;
            }
            this.af = this.ad + 1;
            this.ae = this.ad / 2 - 1;
            if (this.ae < 0) {
                this.ae = 0;
            }
            this.m = new g(this.w, this.r.d, this.r.e, this.r.f, this.o, this.p - this.ad, this.I);
            final double n = (this.N + 0.1) * 0.05;
            final double n2 = this.M - n;
            final double n3 = this.L + n;
            this.Q = new f(n2 / 32.15, n3 / 32.15, this.ab, this.ac, this.I);
            final double[] f = { 1.0, 2.0, 5.0, 10.0, 20.0, 25.0, 50.0, 100.0, 200.0, 250.0, 500.0, 1000.0, 2000.0, 2500.0, 5000.0, 10000.0, 20000.0, 25000.0, 50000.0, 100000.0, 200000.0, 250000.0, 500000.0, 1000000.0 };
            this.R = new f(n2, n3, this.ab, this.ac, this.I);
            this.R.f = f;
            this.R.g = 0;
            this.R.a();
            this.E.a(new Dimension(this.o, this.ab));
            this.E.b(new Dimension(this.aa, this.Z));
            this.ag = false;
        }
        graphics.setFont(this.G);
        graphics.setColor(this.a);
        graphics.fillRect(0, 0, this.t.width, this.t.height);
        a(graphics, this.V - 1, this.W - 1, this.aa + 1, this.Z + 1, this.c, this.d, 16);
        if (this.F) {
            this.E.a(graphics);
        }
        graphics.setFont(this.H);
        graphics.setColor(this.y);
        graphics.drawString(this.C, 3, this.ab - this.O);
        graphics.drawString(this.B, (this.t.width - this.J.stringWidth(this.B)) / 2, this.ab - this.O);
        graphics.setFont(this.G);
        final Vector e = this.Q.e;
        for (int i = 0; i < e.size(); ++i) {
            final e e2 = e.elementAt(i);
            final int n4 = this.Z - (e2.b - this.ab) + this.ab;
            graphics.setColor(this.y);
            graphics.drawString(e2.c, 3, n4);
            graphics.setColor(this.e);
            graphics.drawLine(this.V, n4, this.p, n4);
        }
        graphics.setFont(this.H);
        graphics.setColor(this.y);
        graphics.drawString(this.D, this.t.width - this.J.stringWidth(this.D) - 3, this.ab - this.O);
        graphics.setFont(this.G);
        final Vector e3 = this.R.e;
        for (int j = 0; j < e3.size(); ++j) {
            final e e4 = e3.elementAt(j);
            final int n5 = this.Z - (e4.b - this.ab) + this.ab;
            graphics.setColor(this.y);
            graphics.drawString(e4.c, this.p + 3, n5);
        }
        final int n6 = this.ac + this.T + this.O - 3;
        final Calendar instance = Calendar.getInstance(this.w);
        final int n7 = (instance.get(15) + instance.get(16)) / 3600000;
        String s = "GMT";
        if (n7 > 0) {
            s = s + "+" + n7;
        }
        else if (n7 < 0) {
            s += n7;
        }
        graphics.drawString(s, 3, n6);
        final Vector h = this.m.h;
        for (int k = 0; k < h.size(); ++k) {
            final com.bullionvault.chart.b.c c;
            if ((c = h.elementAt(k)).f) {
                graphics.setColor(this.y);
                graphics.drawString(c.d, c.c, n6);
                graphics.setColor(this.e);
                graphics.drawLine(c.c, this.ab, c.c, this.ac);
            }
        }
        graphics.setColor(this.x);
        graphics.drawRect(this.V - 1, this.W - 1, this.aa + 1, this.Z + 1);
        switch (this.s) {
            case 1: {
                break;
            }
            case 2: {
                this.c(graphics);
                break;
            }
            case 3: {
                this.d(graphics);
                break;
            }
            default: {
                throw new RuntimeException("Unknown style specified: [" + this.s + "]");
            }
        }
        if (this.K != null) {
            final String format = Resources.b().format(this.K.c);
            final String format2 = Resources.c().format(this.K.c / 32.15);
            int n8;
            if ((n8 = this.Z - (this.R.a(this.K.c) - this.ab) + this.ab) > this.ac) {
                n8 = this.ac;
            }
            if (n8 < this.ab) {
                n8 = this.ab;
            }
            graphics.setColor(this.a);
            graphics.drawRect(0, n8 - this.P - 3, this.o - 1, this.O + 5);
            graphics.drawRect(this.p, n8 - this.P - 3, this.t.width - this.p - 1, this.O + 5);
            graphics.setColor(this.A);
            graphics.drawRect(0, n8 - this.P - 2, this.o - 1, this.O + 3);
            graphics.drawRect(this.p, n8 - this.P - 2, this.t.width - this.p - 1, this.O + 3);
            graphics.setColor(this.z);
            graphics.fillRect(1, n8 - this.P - 1, this.o - 2, this.O + 2);
            graphics.fillRect(this.p + 1, n8 - this.P - 1, this.t.width - this.p - 2, this.O + 2);
            graphics.setColor(this.A);
            graphics.drawString(format2, 3, n8);
            graphics.drawString(format, this.p + 3, n8);
            graphics.drawLine(this.o, n8, this.p, n8);
        }
        switch (this.s) {
            case 3: {
                final c am = this.am;
                final int ao = this.ao;
                final int an = this.an;
                final int n9 = ao;
                final c c2 = am;
                if (c2 != null && this.ai != -1.0f) {
                    int n10 = 28;
                    if (this.aa - this.o - n9 < 150) {
                        n10 = -258;
                    }
                    int n11 = 50;
                    if (an > 120) {
                        n11 = -40;
                    }
                    graphics.setColor(this.A);
                    graphics.drawOval(n9 - 5, an - 5, 10, 10);
                    graphics.setColor(Color.WHITE);
                    graphics.fillRoundRect(n9 + n10, an + n11 - 45, 165, 65, 6, 6);
                    graphics.setColor(Color.BLACK);
                    graphics.drawRoundRect(n9 + n10, an + n11 - 45, 165, 65, 6, 6);
                    if (0.0f != c2.a) {
                        graphics.drawString("H", n9 + n10 + 5, an + n11 - 12);
                        graphics.drawString(Resources.b().format(c2.a) + "/kg", n9 + n10 + 95, an + n11 - 12);
                        graphics.drawString(Resources.c().format(c2.a / 32.15) + "/oz", n9 + n10 + 25, an + n11 - 12);
                    }
                    if (0.0f != c2.b) {
                        graphics.drawString("L", n9 + n10 + 5, an + n11);
                        graphics.drawString(Resources.b().format(c2.b) + "/kg", n9 + n10 + 95, an + n11);
                        graphics.drawString(Resources.c().format(c2.b / 32.15) + "/oz", n9 + n10 + 25, an + n11);
                    }
                    graphics.drawString("C", n9 + n10 + 5, an + n11 + 11);
                    graphics.drawString(Resources.b().format(c2.c) + "/kg", n9 + n10 + 95, an + n11 + 11);
                    graphics.drawString(Resources.c().format(c2.c / 32.15) + "/oz", n9 + n10 + 25, an + n11 + 11);
                    graphics.drawString(DateFormat.getDateTimeInstance().format(c2.e), n9 + n10 + 5, an + n11 - 30);
                }
            }
            case 2: {
                final c am2 = this.am;
                final int ao2 = this.ao;
                final int an2 = this.an;
                final int n12 = ao2;
                final c c3 = am2;
                if (c3 != null && this.ai != -1.0f) {
                    int n13 = 18;
                    if (this.aa - this.o - n12 < 150) {
                        n13 = -258;
                    }
                    int n14 = 50;
                    if (an2 > 80) {
                        n14 = -40;
                    }
                    graphics.setColor(this.A);
                    graphics.drawOval(n12 - 5, an2 - 5, 10, 10);
                    graphics.setColor(Color.WHITE);
                    graphics.fillRoundRect(n12 + n13, an2 + n14 - 45, 145, 40, 6, 6);
                    graphics.setColor(Color.BLACK);
                    graphics.drawRoundRect(n12 + n13, an2 + n14 - 45, 145, 40, 6, 6);
                    graphics.drawString(Resources.b().format(c3.c) + "/kg", n12 + n13 + 75, an2 + n14 - 12);
                    graphics.drawString(Resources.c().format(c3.c / 32.15) + "/oz", n12 + n13 + 5, an2 + n14 - 12);
                    graphics.drawString(DateFormat.getDateTimeInstance().format(c3.e), n12 + n13 + 5, an2 + n14 - 30);
                    break;
                }
                break;
            }
        }
    }
    
    private static void a(final Graphics graphics, final int n, final int n2, final int n3, final int n4, final Color color, final Color color2, final int n5) {
        final Color color3 = graphics.getColor();
        for (int i = 0; i < 16; ++i) {
            final int n6 = n2 + n4 / 16 * i;
            final int n7 = n2 + n4 / 16 * (i + 1) - n6;
            final double n8 = i / 16;
            graphics.setColor(new Color((int)((color2.getRed() - color.getRed()) * n8 + color.getRed()), (int)((color2.getGreen() - color.getGreen()) * n8 + color.getGreen()), (int)((color2.getBlue() - color.getBlue()) * n8 + color.getBlue())));
            graphics.fillRect(n, n6, n3, n7);
        }
        graphics.setColor(color3);
    }
    
    private void c(final Graphics graphics) {
        com.bullionvault.chart.c.h.e("Painting Line Chart");
        final Vector a = this.r.a;
        final int n = this.af / 2;
        for (int i = 0; i < a.size(); ++i) {
            c k = a.elementAt(i);
            final int a2 = this.m.a(k.e);
            boolean b = false;
            if (this.K != null && i == 0) {
                b = true;
                k = this.K;
            }
            int an = this.Z - (this.R.a(k.c) - this.ab) + this.ab;
            if (b) {
                if (an > this.ac) {
                    an = this.ac;
                }
                if (an < this.ab) {
                    an = this.ab;
                }
            }
            if (k.d && i < a.size() - 1) {
                graphics.setColor(this.f);
                int n2;
                c c;
                for (n2 = i + 1, c = a.elementAt(n2); n2 < a.size() - 1 && !c.d; ++n2, c = a.elementAt(n2)) {}
                if (n2 <= a.size() && c.d) {
                    final int a3 = this.m.a(c.e);
                    final int n3 = this.Z - (this.R.a(c.c) - this.ab) + this.ab;
                    if (this.u == 1) {
                        graphics.drawLine(a2, an, a3, n3);
                    }
                    else {
                        final int n4 = this.u - 1;
                        final int[] array = { a2, a2, a3, a3 };
                        final int[] array2 = { an - n4, an + n4, n3 + n4, n3 - n4 };
                        graphics.drawPolygon(array, array2, 4);
                        graphics.fillPolygon(array, array2, 4);
                    }
                }
                if (this.al) {
                    for (final r r : this.ak) {
                        if (k.e.before(r.a())) {
                            int n5 = -2;
                            int n6 = -5;
                            int n7 = -48;
                            int n8 = -20;
                            if (this.aa - this.o - a2 < 50) {
                                n8 = -150;
                            }
                            if (this.ah) {
                                n5 = 0;
                                n6 = 6;
                                n7 = 36;
                            }
                            graphics.setColor(Color.BLACK);
                            graphics.drawOval(a2 - 5, an - 5, 10, 10);
                            graphics.drawLine(a2, an + n6, a2, an + n7 - n6 + n5);
                            graphics.drawString((r.b().length() > 36) ? r.b().substring(0, 36) : r.b(), a2 + n8, an + n7 + n6);
                            this.ak.remove(r);
                            this.ah ^= true;
                            break;
                        }
                    }
                }
                if (Math.abs(a2 - this.ai) <= n) {
                    this.am = k;
                    this.ao = a2;
                    this.an = an;
                }
            }
            if (b) {
                graphics.setColor(this.A);
                graphics.fillOval(a2 - 1, an - 1 - this.af, this.af << 1, this.af << 1);
                com.bullionvault.chart.c.h.e("Plotting real time HLC @ " + (a2 - n) + "," + (an - 1 - n));
            }
        }
    }
    
    private void d(final Graphics graphics) {
        com.bullionvault.chart.c.h.e("Painting Bar High-Low-Close Chart");
        final Vector a = this.r.a;
        int p = this.p;
        for (int i = 0; i < a.size(); ++i) {
            c k = a.elementAt(i);
            final int a2 = this.m.a(k.e);
            boolean b = false;
            Calendar.getInstance(Locale.ENGLISH).setTime(k.e);
            if (this.K != null && i == 0) {
                b = true;
                k = this.K;
            }
            int an = this.Z - (this.R.a(k.c) - this.ab) + this.ab;
            if (b) {
                graphics.setColor(this.A);
                if (an > this.ac) {
                    an = this.ac;
                }
                if (an < this.ab) {
                    an = this.ab;
                }
                graphics.fillOval(a2 - 1, an - 1 - this.af, this.af << 1, this.af << 1);
                com.bullionvault.chart.c.h.e("Plotting real time HLC @ " + a2 + "," + (an - 1));
            }
            else if (k.d) {
                final int n = this.Z - (this.R.a(k.a) - this.ab) + this.ab;
                final int n2 = this.Z - (this.R.a(k.b) - this.ab) + this.ab;
                graphics.setColor(this.f);
                graphics.drawLine(a2, n, a2, n2);
                if (i < a.size() - 1) {
                    if (k.c > a.elementAt(i + 1).c) {
                        graphics.setColor(this.k);
                    }
                    else {
                        graphics.setColor(this.l);
                    }
                }
                else {
                    graphics.setColor(this.j);
                }
                graphics.drawLine(a2, an, p, an);
                p = a2;
            }
            if (this.al) {
                for (final r r : this.ak) {
                    if (k.e.before(r.a())) {
                        int n3 = -2;
                        int n4 = -5;
                        int n5 = -48;
                        int n6 = -20;
                        if (this.aa - this.o - a2 < 50) {
                            n6 = -150;
                        }
                        if (this.ah) {
                            n3 = 0;
                            n4 = 6;
                            n5 = 36;
                        }
                        graphics.setColor(Color.BLACK);
                        graphics.drawOval(a2 - 5, an - 5, 10, 10);
                        graphics.drawLine(a2, an + n4, a2, an + n5 - n4 + n3);
                        graphics.drawString((r.b().length() > 36) ? r.b().substring(0, 36) : r.b(), a2 + n6, an + n5 + n4);
                        this.ak.remove(r);
                        this.ah ^= true;
                        break;
                    }
                }
                if (Math.abs(a2 - this.ai) <= this.af && k.d) {
                    this.am = k;
                    this.ao = a2;
                    this.an = an;
                }
            }
        }
    }
}
