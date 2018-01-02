// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Rectangle;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.easypano.tw.a.r;
import com.easypano.tw.a.a;
import com.easypano.tw.a.c;
import com.easypano.tw.a.b;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ItemListener;
import com.easypano.tw.a.m;
import com.easypano.tw.a.q;
import java.awt.event.ActionListener;
import com.easypano.tw.a.d;
import java.util.Vector;
import java.awt.Color;
import java.awt.Image;

public class cl
{
    private by a;
    private Image b;
    private Image c;
    private Image d;
    private Image e;
    private Color f;
    private Image g;
    private Image h;
    private Image i;
    private Color j;
    private TWViewer k;
    private cj l;
    private bt m;
    private l n;
    private o o;
    private k p;
    private j q;
    private p r;
    private g s;
    private g t;
    private Vector u;
    private Vector v;
    Vector w;
    Vector x;
    
    public cl(final by a, final cj l, final TWViewer k) {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.f = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.j = null;
        this.m = null;
        this.n = null;
        this.o = null;
        this.p = null;
        this.q = null;
        this.r = null;
        this.s = null;
        this.t = null;
        this.u = new Vector();
        this.v = new Vector();
        this.w = new Vector(30, 30);
        this.x = new Vector(30, 30);
        this.a = a;
        this.l = l;
        this.k = k;
    }
    
    public void a(final bx bx, final cf cf) {
        final boolean q = com.easypano.tw.g.q;
        final double n = 40.0;
        final double a = this.a(bx);
        String s = "";
        final Vector<String> vector = new Vector<String>();
        this.b = (Image)this.a.d.a(bx.a(a("\u0016<1y`\u000701z)")), 1);
        this.a(cf, (int)n, a(")89s'\u000b0x_!\u0017>\"x \u0011647\u001d\u0006%7{\"\u00076*9`K"));
        this.f = ds.a(bx.a(a("\r$:v<K5?t!\t8*")));
        this.c = (Image)this.a.d.a(bx.a(a("\r$:v<K59e`\f:?")), 1);
        this.d = (Image)this.a.d.a(bx.a(a("\r$:v<K59e`\b8-d+\n!=e'\b0")), 1);
        this.e = (Image)this.a.d.a(bx.a(a("\r$:v<K59e`\b8-d+\u00018/y'\b0")), 1);
        final double n2;
        this.a(cf, (int)(n2 = n + a), a(")89s'\u000b0xA+\u0017#1t/\tw\u000bt<\n;4u/\u0017yv9"));
        this.j = ds.a(bx.a(a("\u0013$:v<K5?t!\t8*")));
        this.g = (Image)this.a.d.a(bx.a(a("\u0013$:v<K59e`\f:?")), 1);
        this.h = (Image)this.a.d.a(bx.a(a("\u0013$:v<K59e`\b8-d+\n!=e'\b0")), 1);
        this.i = (Image)this.a.d.a(bx.a(a("\u0013$:v<K59e`\b8-d+\u00018/y'\b0")), 1);
        double n3;
        this.a(cf, (int)(n3 = n2 + a), a(")89s'\u000b0x%\nE\u001e5v)\u0000yv9"));
        final int e = ds.e(bx.a(a("W31z/\u00022vy;\b")));
        int n4 = 0;
        Rectangle bounds;
        int n6;
        int n5 = 0;
        while (true) {
            Label_0678: {
                if (n4 >= e) {
                    this.a(cf, (int)n3, a(")89s'\u000b0x['\u001657o`Ky"));
                    bounds = ds.b(bx.a(a("\t>+c`\u00078-y*\u0016")));
                    final int width;
                    n5 = (width = (n6 = bounds.width));
                    if (!q) {
                        break;
                    }
                }
                else {
                    s = a("W3") + n4;
                    bounds = ds.b(bx.a(String.valueOf(s) + a("K57b \u0001$")));
                    if (q) {
                        break Label_0678;
                    }
                    final int width2 = bounds.width;
                }
                if (n5 > 0 && bounds.height > 0) {
                    final g g = new g();
                    final d d = new d(g);
                    g.setBounds(bounds);
                    d.f(31);
                    Image scaledInstance = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("K>5p")), 1);
                    if (!q) {
                        if (!bx.a(String.valueOf(s) + a("K:7s+")).equalsIgnoreCase(a("\n%1p'\u000b64"))) {
                            scaledInstance = scaledInstance.getScaledInstance(bounds.width, bounds.height, 1);
                            ds.a(scaledInstance);
                        }
                        d.d(scaledInstance);
                        g.a(d);
                        g.a(new cp(this, bx.a(String.valueOf(s) + a("K6;c'\n9"))));
                        this.w.addElement(g);
                    }
                    this.x.addElement(ds.f(bx.a(String.valueOf(s) + a("K-"))));
                }
                this.a(cf, (int)(n3 += a), a(")89s'\u000b0x%\nE\u001e5v)\u0000yv9"));
            }
            ++n4;
        }
        final int n7;
        Label_1068: {
            if (!q) {
                if (n5 > 0) {
                    n7 = (n6 = bounds.height);
                    if (q) {
                        break Label_1068;
                    }
                    if (n7 > 0) {
                        this.p = new k();
                        int n8 = 0;
                        while (true) {
                            while (true) {
                                Label_0805: {
                                    if (!q) {
                                        break Label_0805;
                                    }
                                    com.easypano.tw.e.k = !com.easypano.tw.e.k;
                                    this.p.e().a(this.l.a(n8).e);
                                    ++n8;
                                }
                                if (n8 < this.l.a()) {
                                    continue;
                                }
                                break;
                            }
                            this.p.setBackground(ds.a(bx.a(a("\t>+c`\u00070;x\"\n%"))));
                            this.p.setFont(ds.h(bx.a(a("\t>+c`\u00112 c`\u000386c"))));
                            if (q) {
                                continue;
                            }
                            break;
                        }
                        final q q2 = new q();
                        q2.a(ds.a(bx.a(a("\t>+c`\u00112 c`\u000684x<"))));
                        q2.b(ds.a(bx.a(a("\t>+c`\u00112 c`\b8-d+\u00018/y-\n;7e"))));
                        q2.c(ds.a(bx.a(a("\t>+c`\u00112 c`\b8-d+\n!=e-\n;7e"))));
                        ((m)this.p.a()).a(q2);
                        this.p.addItemListener(new cm(this));
                        final o a2 = this.a();
                        ((com.easypano.tw.a.o)a2.a()).a(ds.a(bx.a(a("\t>+c`\u00078*s+\u001747{!\u0017"))));
                        a2.setBounds(bounds);
                        a2.a((Component)this.p);
                        this.w.addElement(a2);
                        this.x.addElement(ds.f(bx.a(a("\t>+c`\u001f"))));
                    }
                }
                this.a(cf, (int)(n3 += a), a(")89s'\u000b0xT!\b57u!\u001dyv9"));
                bounds = ds.b(bx.a(a("\u00065:9,\n\"6s=")));
                final int width;
                n6 = (width = bounds.width);
            }
        }
        Label_1643: {
            final Rectangle rectangle;
            Label_1102: {
                if (!q) {
                    if (n7 <= 0) {
                        break Label_1643;
                    }
                    rectangle = bounds;
                    if (q) {
                        break Label_1102;
                    }
                    n6 = rectangle.height;
                }
                if (n6 <= 0) {
                    break Label_1643;
                }
                this.q = new j();
            }
            rectangle.height = 20;
            this.q.setBounds(bounds);
            this.q.a(this.a());
            this.q.g().setFont(ds.h(bx.a(a("\u00065:9:\u0000/,9(\n9,"))));
            this.q.f().setFont(ds.h(bx.a(a("\u00065:9:\u0000/,9(\n9,"))));
            final Color a3 = ds.a(bx.a(a("\u00065:9,\u000247{!\u0017")));
            this.q.setBackground(a3);
            this.q.g().setBackground(a3);
            this.q.e().a(new Dimension(20, 20));
            final b b = new b(this.q.e());
            b.g(a3);
            b.d((Image)this.a.d.a(bx.a(a("\u00065:9,\u00119v~#\u0002")), 1));
            Image image3;
            Image image2;
            final Image image = image2 = (image3 = (Image)this.a.d.a(bx.a(a("\u00065:9,\u00119vz!\u0010$=s!\u001291z)")), 1));
            cl cl = null;
            Label_1379: {
                if (!q) {
                    if (image != null) {
                        b.e(image2);
                    }
                    cl = this;
                    if (q) {
                        break Label_1379;
                    }
                    image3 = (image2 = (Image)this.a.d.a(bx.a(a("\u00065:9,\u00119vz!\u0010$=x8\u0000%1z)")), 1));
                }
                if (image3 != null) {
                    b.f(image2);
                }
                this.q.e().a(b);
                cl = this;
            }
            cl.q.e().b(false);
            final q q3 = new q();
            q3.a(ds.a(bx.a(a("\u00065:9:\u0000/,9-\n;7e"))));
            final Color a4 = ds.a(bx.a(a("\u00065:9:\u0000/,9#\n\"+r*\n 6t!\t8*")));
            q3.b(a4);
            q3.c(ds.a(bx.a(a("\u00065:9:\u0000/,9#\n\"+r!\u00132*t!\t8*"))));
            ((c)this.q.f().a()).d(a4);
            ((m)this.q.g().a()).a(q3);
            final Color a5 = ds.a(bx.a(a("\u00065:9,\n%<r<\u000684x<")));
            ((com.easypano.tw.a.l)this.q.a()).a(a5);
            ((com.easypano.tw.a.o)this.q.i().a()).a(a5);
            final dc e2 = this.q.g().e();
            int n9 = 0;
            while (true) {
                while (true) {
                    Label_1580: {
                        if (!q) {
                            break Label_1580;
                        }
                        e2.a(this.l.a(n9).e);
                        ++n9;
                    }
                    if (n9 < this.l.a()) {
                        continue;
                    }
                    break;
                }
                this.q.a(new cn(this));
                this.w.addElement(this.q);
                if (q) {
                    continue;
                }
                break;
            }
            this.x.addElement(ds.f(bx.a(a("\u00065:94"))));
        }
        double n10;
        this.a(cf, (int)(n10 = n3 + a), a(")89s'\u000b0xU;\u0011#7y`Ky"));
        int n11 = ds.e(bx.a(a("\u0007\",c!\u000by6b#")));
        int n12 = 0;
        Rectangle bounds2;
        int n13 = 0;
        boolean b2;
        while (true) {
            while (true) {
                Label_2945: {
                    if (!q) {
                        break Label_2945;
                    }
                    s = a("\u0007#6") + n12;
                    bounds2 = ds.b(bx.a(String.valueOf(s) + a("K57b \u0001$")));
                    Label_2942: {
                        if (q) {
                            break Label_2942;
                        }
                        final int width3 = bounds2.width;
                        if (n13 > 0 && bounds2.height > 0) {
                            final g g2 = new g();
                            final a a6 = (a)g2.a();
                            Label_1843: {
                                Label_1836: {
                                    if (!q) {
                                        if (!bx.a(String.valueOf(s) + a("K$,n\"\u0000")).equalsIgnoreCase(a("\u0016#9c'\u0006"))) {
                                            break Label_1836;
                                        }
                                        a6.c(22);
                                    }
                                    if (!q) {
                                        break Label_1843;
                                    }
                                }
                                a6.c(21);
                            }
                            final boolean equalsIgnoreCase = bx.a(String.valueOf(s) + a("K#=o:K64~)\u000b")).equalsIgnoreCase(a("\u0017>?\u007f:"));
                            Label_1959: {
                                if (!q) {
                                    if (equalsIgnoreCase) {
                                        a6.b(14);
                                        if (!q) {
                                            break Label_1959;
                                        }
                                    }
                                    bx.a(String.valueOf(s) + a("K#=o:K64~)\u000b")).equalsIgnoreCase(a("\t2>c"));
                                }
                                if (equalsIgnoreCase) {
                                    a6.b(13);
                                    if (!q) {
                                        break Label_1959;
                                    }
                                }
                                a6.b(15);
                            }
                            g2.setFont(ds.h(bx.a(String.valueOf(s) + a("K#=o:K17y:"))));
                            g2.e().a(bx.a(String.valueOf(s) + a("K#=o:")));
                            a6.d(ds.a(bx.a(String.valueOf(s) + a("K#=o:K47{!\u0017"))));
                            g2.a(bx.a(String.valueOf(s) + a("K?1y:K#=o:")), ds.a(bx.a(String.valueOf(s) + a("K?1y:K5?t!\t8*"))), ds.a(bx.a(String.valueOf(s) + a("K#=o:K47{!\u0017"))));
                            a6.d((Image)this.a.d.a(bx.a(String.valueOf(s) + a("K>5p")), 1));
                            Image image6;
                            Image image5;
                            final Image image4 = image5 = (image6 = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("K:7b=\u00008.r<\f:?")), 1));
                            if (!q) {
                                if (image4 != null) {
                                    a6.f(image5);
                                }
                                image6 = (image5 = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("K:7b=\u000037` \f:?")), 1));
                            }
                            if (image6 != null) {
                                a6.e(image5);
                            }
                            Object o;
                            final String s2 = (String)(o = bx.a(String.valueOf(s) + a("K#!g+")));
                            Label_2716: {
                                Label_2709: {
                                    if (!q) {
                                        if (!s2.equalsIgnoreCase(a("\u00118?p\"\u0000"))) {
                                            break Label_2709;
                                        }
                                        a6.d(32);
                                        g2.e().b(bx.a(String.valueOf(s) + a("K$={+\u0006#=s:\u0000/,")));
                                        a6.a(ds.a(bx.a(String.valueOf(s) + a("K#=o:K47{!\u0017"))));
                                        g2.b(bx.a(String.valueOf(s) + a("K?1y:K$={+\u0006#=s:\u0000/,")), ds.a(bx.a(String.valueOf(s) + a("K?1y:K5?t!\t8*"))), ds.a(bx.a(String.valueOf(s) + a("K#=o:K47{!\u0017"))));
                                        a6.a((Image)this.a.d.a(bx.a(String.valueOf(s) + a("K$={+\u0006#=s'\b0")), 1));
                                        o = this.a.d.a(bx.a(String.valueOf(s) + a("K$={+\u0006#=s#\n\"+r!\u00132*~#\u0002")), 1);
                                    }
                                    Image image9;
                                    Image image8;
                                    final Image image7 = image8 = (image9 = (Image)o);
                                    if (!q) {
                                        if (image7 != null) {
                                            a6.c(image8);
                                        }
                                        image9 = (image8 = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("K$={+\u0006#=s#\n\"+r*\n 6~#\u0002")), 1));
                                    }
                                    if (image9 == null) {
                                        break Label_2716;
                                    }
                                    a6.b(image8);
                                    if (!q) {
                                        break Label_2716;
                                    }
                                }
                                a6.d(31);
                            }
                            g2.setBounds(bounds2);
                            final String a7 = bx.a(String.valueOf(s) + a("K6;c'\n9"));
                            g2.a(new cp(this, a7));
                            final int startsWith = a7.startsWith(a("\u0015;9n#\n!1r")) ? 1 : 0;
                            Label_2877: {
                                Vector<String> v = null;
                                Label_2868: {
                                    Label_2843: {
                                        if (!q) {
                                            if (startsWith == 0) {
                                                final boolean startsWith2 = a7.startsWith(a("\u0015;9n>\u0004#0"));
                                                if (q) {
                                                    break Label_2843;
                                                }
                                                if (!startsWith2) {
                                                    break Label_2877;
                                                }
                                            }
                                            g2.e().e(false);
                                            ds.a(a7, vector);
                                            this.u.addElement(g2);
                                            final Vector<String> vector2 = v = vector;
                                            if (q) {
                                                break Label_2868;
                                            }
                                            vector2.size();
                                        }
                                    }
                                    if (startsWith > 0) {
                                        this.v.addElement(vector.elementAt(0));
                                        if (!q) {
                                            break Label_2877;
                                        }
                                    }
                                    v = (Vector<String>)this.v;
                                }
                                v.addElement(a("Hf"));
                            }
                            this.w.addElement(g2);
                            this.x.addElement(ds.f(bx.a(String.valueOf(s) + a("K-"))));
                        }
                        this.a(cf, (int)(n10 += a), a(")89s'\u000b0xU;\u0011#7y`Ky"));
                    }
                    ++n12;
                }
                if (n12 < n11) {
                    continue;
                }
                break;
            }
            this.a(cf, (int)(n10 += a), a(")89s'\u000b0xC&\u0010::y/\f;v9`"));
            bounds2 = ds.b(bx.a(a("\u00115vu!\u00109<d")));
            n13 = ((b2 = (bounds2.width != 0)) ? 1 : 0);
            if (q) {
                continue;
            }
            break;
        }
        final int n14;
        Label_3511: {
            Label_3473: {
                Label_3219: {
                    Label_3188: {
                        if (!q) {
                            if (n13 <= 0) {
                                final int height;
                                n14 = (height = bounds2.height);
                                if (q) {
                                    break Label_3511;
                                }
                                if (n14 <= 0) {
                                    break Label_3473;
                                }
                            }
                            (this.r = new p()).setBackground(ds.a(bx.a(a("\u00115vu)\u000684x<"))));
                            this.r.a(ds.a(bx.a(a("\u00115vd+\t2;c,\n%<r<\u000684x<"))));
                            this.r.b(ds.a(bx.a(a("\u00115vz!\u0010$=x8\u0000%:x<\u00012*t!\t8*"))));
                            this.r.c(ds.a(bx.a(a("\u00115vz!\u0010$=s!\u00129:x<\u00012*t!\t8*"))));
                            if (q) {
                                break Label_3188;
                            }
                            b2 = bx.a(a("\u00115vz!\u00012")).equalsIgnoreCase(a("\b\"4c'\t>6r"));
                        }
                        if (b2) {
                            this.r.b(41);
                            this.r.a(new Dimension(bounds2.width - com.easypano.tw.n.l.width, bounds2.height));
                            if (!q) {
                                break Label_3219;
                            }
                        }
                        this.r.b(42);
                    }
                    this.r.a(new Dimension(bounds2.width, bounds2.height - com.easypano.tw.n.l.height));
                }
                this.r.d(ds.g(bx.a(a("\u00115vd&\n 6v#\u0000"))));
                this.r.d(ds.a(bx.a(a("\u00115v{/\u000724t!\t8*"))));
                this.r.b(ds.c(bx.a(a("\u00115vy/\f;+~4\u0000"))));
                int n15 = 0;
                while (true) {
                    Label_3364: {
                        if (!q) {
                            break Label_3364;
                        }
                        this.r.a(new i((Image)this.a.d.a(this.l.a(n15).l, 1), this.l.a(n15).e));
                        this.a(cf, (int)(n10 += a), a(")89s'\u000b0xC&\u0010::y/\f;v9`"));
                        ++n15;
                    }
                    if (n15 < this.l.a()) {
                        continue;
                    }
                    break;
                }
                this.r.a(new co(this));
                this.r.doLayout();
                final o a8 = this.a();
                ((com.easypano.tw.a.o)a8.a()).a(ds.a(bx.a(a("\u00115vu!\u00173=e-\n;7e"))));
                a8.a((Component)this.r);
                a8.setBounds(bounds2);
                this.w.addElement(a8);
                this.x.addElement(ds.f(bx.a(a("\u00115vm"))));
            }
            this.a(cf, (int)(n10 += a), a(")89s'\u000b0xV)\u00009,^ \u00038v9`"));
            bounds2 = ds.b(bx.a(a("\u00040=y:\f9>x`\u00078-y*\u0016")));
            final int width4 = bounds2.width;
        }
        final int height2;
        Label_3852: {
            if (!q) {
                if (n14 > 0) {
                    height2 = bounds2.height;
                    if (q) {
                        break Label_3852;
                    }
                    if (height2 > 0) {
                        final f f2;
                        final f f = f2 = new f();
                        Label_3594: {
                            if (!q) {
                                f2.setBounds(bounds2);
                                if (bx.a(a("\u00040=y:\f9>x`\t6!x;\u0011")).equalsIgnoreCase(a("32*c'\u000664"))) {
                                    f.b(41);
                                    if (!q) {
                                        break Label_3594;
                                    }
                                }
                            }
                            f2.b(42);
                        }
                        f.setName(bx.a(a("\u00040=y:\f9>x`\u000b65r")));
                        f.b(bx.a(a("\u00040=y:\f9>x`\u0015?7y+")));
                        f.c(bx.a(a("\u00040=y:\f9>x`\u00036 ")));
                        final String a9 = bx.a(a("\u00040=y:\f9>x`\b61{"));
                        f.e(a9);
                        f.b(new cp(this, a("\n'=y;\u0017;p") + a9 + ")"));
                        final String a10 = bx.a(a("\u00040=y:\f9>x`\u00122:d'\u00112"));
                        f.d(a10);
                        f.a(new cp(this, a("\n'=y;\u0017;p") + a10 + a("Iw\u0007u\"\u000493>")));
                        f.a((Image)this.a.d.a(bx.a(a("\u00040=y:\f9>x`\f:?")), 1));
                        this.w.addElement(f);
                        this.x.addElement(ds.f(bx.a(a("\u00040=y:\f9>x`\u001f"))));
                    }
                }
                this.a(cf, (int)(n10 += a), a(")89s'\u000b0xC+\u001d#x['\u000b<v9`"));
                n11 = ds.e(bx.a(a("\u00112 c\"\f939 \u0010:")));
            }
        }
        int n16 = height2;
        Rectangle rectangle2;
        int n17 = 0;
        while (true) {
            while (true) {
                Label_4469: {
                    if (!q) {
                        break Label_4469;
                    }
                    s = a("\u0011;") + n16;
                    rectangle2 = ds.b(bx.a(String.valueOf(s) + a("K57b \u0001$")));
                    final int width5;
                    int height3 = width5 = rectangle2.width;
                    Label_4466: {
                        if (!q) {
                            if (n17 <= 0) {
                                break Label_4466;
                            }
                            height3 = rectangle2.height;
                        }
                        if (height3 > 0) {
                            final g g3 = new g();
                            final com.easypano.tw.a.i i = new com.easypano.tw.a.i(g3);
                            g3.a(i);
                            g3.setBounds(rectangle2);
                            g3.e().a(bx.a(String.valueOf(s) + a("K#=o:")));
                            final boolean equalsIgnoreCase2 = bx.a(String.valueOf(s) + a("K#=o:K64~)\u000b")).equalsIgnoreCase(a("\u000626c+\u0017"));
                            Label_4121: {
                                if (!q) {
                                    if (equalsIgnoreCase2) {
                                        i.b(15);
                                        if (!q) {
                                            break Label_4121;
                                        }
                                    }
                                    bx.a(String.valueOf(s) + a("K#=o:K64~)\u000b")).equalsIgnoreCase(a("\u0017>?\u007f:"));
                                }
                                if (equalsIgnoreCase2) {
                                    i.b(14);
                                    if (!q) {
                                        break Label_4121;
                                    }
                                }
                                i.b(13);
                            }
                            g3.setFont(ds.h(bx.a(String.valueOf(s) + a("K#=o:K17y:"))));
                            i.d(ds.a(bx.a(String.valueOf(s) + a("K#=o:K47{!\u0017"))));
                            i.f(ds.a(bx.a(String.valueOf(s) + a("K#=o:K:7b=\u00008.r<\u000684x<"))));
                            i.e(ds.a(bx.a(String.valueOf(s) + a("K#=o:K:7b=\u000037` \u000684x<"))));
                            final boolean equalsIgnoreCase3 = bx.a(String.valueOf(s) + a("K$,n\"\u0000")).equalsIgnoreCase(a("+2.r<09<r<\t>6r"));
                            Label_4378: {
                                if (!q) {
                                    if (equalsIgnoreCase3) {
                                        i.f(3);
                                        if (!q) {
                                            break Label_4378;
                                        }
                                    }
                                    bx.a(String.valueOf(s) + a("K$,n\"\u0000")).equalsIgnoreCase(a("-8.r<09<r<\t>6r"));
                                }
                                if (equalsIgnoreCase3) {
                                    i.f(1);
                                    if (!q) {
                                        break Label_4378;
                                    }
                                }
                                i.f(2);
                            }
                            g3.a(new cp(this, bx.a(String.valueOf(s) + a("K6;c'\n9"))));
                            this.w.addElement(g3);
                            this.x.addElement(ds.f(bx.a(String.valueOf(s) + a("K-"))));
                        }
                    }
                    ++n16;
                }
                if (n16 < n11) {
                    continue;
                }
                break;
            }
            this.a(cf, (int)(n10 += a), a(")89s'\u000b0xD-\u00009=7\n\u0000$;e'\u0015#1x Kyv"));
            rectangle2 = ds.b(bx.a(a("\u00164=y+\u00012+9,\n\"6s=")));
            int height3;
            final int n18;
            final int width6;
            n17 = (height3 = (width6 = (n18 = rectangle2.width)));
            if (q) {
                continue;
            }
            break;
        }
        final int n19;
        Label_4728: {
            if (!q) {
                if (n17 > 0) {
                    final int n18;
                    n19 = (n18 = rectangle2.height);
                    if (q) {
                        break Label_4728;
                    }
                    if (n19 > 0) {
                        this.s = new g();
                        final c c = new c(this.s, true);
                        this.s.a(c);
                        this.s.setBounds(rectangle2);
                        this.s.setFont(ds.h(bx.a(a("\u00164=y+\u00012+9:\u0000/,9(\n9,"))));
                        c.d(ds.a(bx.a(a("\u00164=y+\u00012+9:\u0000/,9-\n;7e"))));
                        final o a11 = this.a();
                        ((com.easypano.tw.a.o)a11.a()).a((Color)null);
                        a11.a(false);
                        a11.a((Component)this.s);
                        a11.setBounds(rectangle2);
                        this.w.addElement(a11);
                        this.x.addElement(ds.f(bx.a(a("\u00164=y+\u00012+94"))));
                    }
                }
                this.a(cf, (int)(n10 += a), a(")89s'\u000b0xD:\u0004#-d,\u0004%v9`"));
                rectangle2 = ds.b(bx.a(a("\u0016#9c;\u001659e`\u00078-y*\u0016")));
                final int width6 = rectangle2.width;
            }
        }
        final int height4;
        Label_4895: {
            if (!q) {
                if (n19 > 0) {
                    height4 = rectangle2.height;
                    if (q) {
                        break Label_4895;
                    }
                    if (height4 > 0) {
                        this.t = new g();
                        final c c2 = new c(this.t, false);
                        this.t.a(c2);
                        this.t.setBounds(rectangle2);
                        this.t.setFont(ds.h(bx.a(a("\u0016#9c;\u001659e`\u00112 c`\u000386c"))));
                        c2.d(ds.a(bx.a(a("\u0016#9c;\u001659e`\u00112 c`\u000684x<"))));
                        this.w.addElement(this.t);
                        this.x.addElement(ds.f(bx.a(a("\u0016#9c;\u001659e`\u001f"))));
                    }
                }
                this.a(cf, (int)(n10 += a), a(")89s'\u000b0x[/\u0007249`K"));
                n11 = ds.e(bx.a(a("\t6:r\"K9-z")));
            }
        }
        int n20 = height4;
        Rectangle rectangle3;
        int width7;
        int height5;
        int n21 = 0;
        g g4;
        c c3;
        boolean equalsIgnoreCase4;
        int n23;
        int height6;
        int n22;
        int n24 = 0;
        Dimension preferredSize;
        int n25;
        int n26;
        int width8;
        int n27;
        int width9;
        int n28;
        int height7;
        int height8;
        bp bp;
        int n29;
        Rectangle b3;
        Rectangle rectangle4;
        h h;
        Image image12;
        Image image11;
        Image image10;
        cl cl2 = null;
        String string;
        cz cz;
        int n30;
        String string2;
        Rectangle b4;
        g g5;
        a a12;
        boolean equalsIgnoreCase5;
        Image image15;
        Image image14;
        Image image13;
        cl cl3 = null;
        Label_5774_Outer:Label_6214_Outer:Label_6753_Outer:
        while (true) {
            while (true) {
                Label_5284: {
                    if (!q) {
                        break Label_5284;
                    }
                    s = a("\t54") + n20;
                    rectangle3 = ds.b(bx.a(String.valueOf(s) + a("K57b \u0001$")));
                    height5 = (width7 = rectangle3.width);
                    Label_5281: {
                        if (!q) {
                            if (n21 <= 0) {
                                break Label_5281;
                            }
                            height5 = rectangle3.height;
                        }
                        if (height5 > 0) {
                            g4 = new g();
                            c3 = new c(g4, false);
                            g4.a(c3);
                            g4.setBounds(rectangle3);
                            g4.e().a(bx.a(String.valueOf(s) + a("K#=o:")));
                            equalsIgnoreCase4 = bx.a(String.valueOf(s) + a("K#=o:K64~)\u000b")).equalsIgnoreCase(a("\u0017>?\u007f:"));
                            Label_5165: {
                                if (!q) {
                                    if (equalsIgnoreCase4) {
                                        c3.b(14);
                                        if (!q) {
                                            break Label_5165;
                                        }
                                    }
                                    bx.a(String.valueOf(s) + a("K#=o:K64~)\u000b")).equalsIgnoreCase(a("\u000626c+\u0017"));
                                }
                                if (equalsIgnoreCase4) {
                                    c3.b(15);
                                    if (!q) {
                                        break Label_5165;
                                    }
                                }
                                c3.b(13);
                            }
                            c3.d(ds.a(bx.a(String.valueOf(s) + a("K#=o:K47{!\u0017"))));
                            g4.setFont(ds.h(bx.a(String.valueOf(s) + a("K#=o:K17y:"))));
                            this.w.addElement(g4);
                            this.x.addElement(ds.f(bx.a(String.valueOf(s) + "z")));
                        }
                    }
                    ++n20;
                }
                if (n20 < n11) {
                    continue;
                }
                break;
            }
            this.a(cf, (int)(n10 += a), a(")89s'\u000b0xZ/\u0015yv9"));
            rectangle3 = ds.b(bx.a(a("\b6(9,\n\"6s=")));
            n21 = (height5 = (n22 = (height6 = (n23 = rectangle3.width))));
            if (!q) {
                Label_6580: {
                    if (!q) {
                        if (n21 > 0) {
                            n24 = (height6 = (n23 = rectangle3.height));
                            if (q) {
                                break Label_6580;
                            }
                            if (n24 > 0) {
                                (this.n = new l(this.k)).setBackground(ds.a(bx.a(a("\b6(9,\u000247{!\u0017"))));
                                ((r)this.n.a()).a((Image)this.a.d.a(bx.a(a("\b6(9'\b0")), 1));
                                preferredSize = this.n.getPreferredSize();
                                n25 = 0;
                                n26 = 0;
                                n27 = (width8 = preferredSize.width);
                                n28 = (width9 = rectangle3.width);
                                if (!q) {
                                    if (n27 < n28) {
                                        n25 = (rectangle3.width - preferredSize.width) / 2;
                                    }
                                    width8 = (height7 = preferredSize.height);
                                    width9 = (height8 = rectangle3.height);
                                }
                                Label_5508: {
                                    if (!q) {
                                        if (n27 >= n28) {
                                            break Label_5508;
                                        }
                                        width8 = rectangle3.height - preferredSize.height;
                                        width9 = 2;
                                    }
                                    n26 = width8 / width9;
                                }
                                this.o = this.a();
                                ((com.easypano.tw.a.o)this.o.a()).a((Color)null);
                                this.o.setBounds(rectangle3);
                                this.o.a((Component)this.n);
                                this.o.a(this.k.j());
                                bp = new bp();
                                bp.setLayout(new BorderLayout());
                                bp.setBounds(rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height);
                                bp.add(this.o, a("&26c+\u0017"));
                                bp.a(ds.a(bx.a(a("\b6(9,\n%<r<\u000684x<"))));
                                this.w.addElement(bp);
                                this.x.addElement(ds.f(bx.a(a("\b6(94"))));
                                n11 = ds.e(bx.a(a("\b6(9&\n#+g!\u0011y6b#\u00072*")));
                                n29 = 0;
                                while (true) {
                                    while (true) {
                                        Label_6179: {
                                            if (!q) {
                                                break Label_6179;
                                            }
                                            s = a("\b6(9&\u0016") + n29;
                                            rectangle3 = (b3 = ds.b(bx.a(String.valueOf(s) + a("K57b \u0001$"))));
                                            b3.x += n25;
                                            rectangle4 = rectangle3;
                                            rectangle4.y += n26;
                                            if (!q) {
                                                if (rectangle3.width > 0 && rectangle3.height > 0) {
                                                    h = new h();
                                                    h.setBounds(rectangle3);
                                                    ((com.easypano.tw.a.h)h.a()).d((Image)this.a.d.a(bx.a(String.valueOf(s) + a("K>5p")), 1));
                                                    image10 = (image11 = (image12 = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("K:7b=\u000037` \f:?")), 1)));
                                                    Label_6149: {
                                                        if (!q) {
                                                            if (image10 != null) {
                                                                ((com.easypano.tw.a.h)h.a()).e(image11);
                                                            }
                                                            cl2 = this;
                                                            if (q) {
                                                                break Label_6149;
                                                            }
                                                            image12 = (image11 = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("K:7b=\u00008.r<\f:?")), 1));
                                                        }
                                                        if (image12 != null) {
                                                            ((com.easypano.tw.a.h)h.a()).f(image11);
                                                        }
                                                        h.setForeground(ds.a(bx.a(String.valueOf(s) + a("K47{!\u0017"))));
                                                        h.a(bx.a(String.valueOf(s) + a("K?1y:K#=o:")), ds.a(bx.a(String.valueOf(s) + a("K?1y:K5?"))));
                                                        h.a(this.k.j());
                                                        h.a(new cp(this, bx.a(String.valueOf(s) + a("K6;c'\n9"))));
                                                        cl2 = this;
                                                    }
                                                    cl2.n.add(h);
                                                }
                                                this.a(cf, (int)(n10 += a), a(")89s'\u000b0xZ/\u0015yv9"));
                                            }
                                            ++n29;
                                        }
                                        if (n29 < n11) {
                                            continue Label_5774_Outer;
                                        }
                                        break;
                                    }
                                    n11 = ds.e(bx.a(a("\b6(9-\n:(v=\u0016y6b#\u00072*")));
                                    n29 = 0;
                                    if (q) {
                                        continue Label_6214_Outer;
                                    }
                                    break;
                                }
                                while (true) {
                                    Label_6535: {
                                        if (!q) {
                                            break Label_6535;
                                        }
                                        string = a("\b6(9-\u0015") + n29;
                                        cz = new cz();
                                        cz.b = ds.e(bx.a(String.valueOf(string) + a("K$;r \u0000")));
                                        cz.a = ds.d(bx.a(String.valueOf(string) + a("K;7t")));
                                        cz.f = ds.a(bx.a(String.valueOf(string) + a("K57e*\u0000%;x\"\n%")));
                                        cz.c = (Image)this.a.d.a(bx.a(String.valueOf(string) + a("K>5p")), 1);
                                        cz.d = ds.e(bx.a(String.valueOf(string) + a("K31z"))) * 256 / 100;
                                        if (!q) {
                                            if (bx.a() == 1) {
                                                cz.d = 50;
                                            }
                                            cz.e = ds.e(bx.a(String.valueOf(string) + a("K>6~:\u00049?{+")));
                                            ((r)this.n.a()).a(cz);
                                            this.a(cf, (int)(n10 += a), a(")89s'\u000b0xZ/\u0015yv9"));
                                        }
                                        ++n29;
                                    }
                                    if (n29 < n11) {
                                        continue Label_6753_Outer;
                                    }
                                    break;
                                }
                            }
                        }
                        this.a(cf, (int)(n10 += a), a(")89s'\u000b0xD-\u00009=7\u0018\f2/r<Kyv"));
                        rectangle3 = ds.b(bx.a(a("\u0013>=`+\u0017y:x;\u000b3+")));
                        height6 = (n22 = (n23 = rectangle3.width));
                    }
                }
                if (!q) {
                    if (n24 <= 0) {
                        return;
                    }
                    n23 = (height6 = rectangle3.height);
                }
                if (!q) {
                    if (height6 <= 0) {
                        return;
                    }
                    (this.m = new bt(this.k)).setBounds(rectangle3);
                    this.m.setBackground(ds.a(bx.a(a("\u0013>=`+\u0017y:p-\n;7e"))));
                    this.m.b((Image)this.a.d.a(bx.a(a("\u0013>=`+\u0017y>e/\b2")), 1));
                    this.m.b(ds.e(bx.a(a("\u0013>=`+\u0017y)b/\t>,n"), "1")));
                    this.w.addElement(this.m);
                    this.x.addElement(ds.f(bx.a(a("\u0013>=`+\u0017y\""))));
                    n11 = ds.e(bx.a(a("\u0013>=`+\u0017y0x:\u0016'7c`\u000b\"5u+\u0017")));
                    n23 = 0;
                }
                n30 = n23;
                while (true) {
                    Label_7488: {
                        if (!q) {
                            break Label_7488;
                        }
                        string2 = a("\u0013>=`+\u0017y0d") + n30;
                        b4 = ds.b(bx.a(String.valueOf(string2) + a("K57b \u0001$")));
                        if (!q) {
                            if (b4.width > 0 && b4.height > 0) {
                                g5 = new g();
                                a12 = (a)g5.a();
                                Label_6912: {
                                    Label_6905: {
                                        if (!q) {
                                            if (!bx.a(String.valueOf(string2) + a("K$,n\"\u0000")).equalsIgnoreCase(a("\u0016#9c'\u0006"))) {
                                                break Label_6905;
                                            }
                                            a12.c(22);
                                        }
                                        if (!q) {
                                            break Label_6912;
                                        }
                                    }
                                    a12.c(21);
                                }
                                g5.e().a(bx.a(String.valueOf(string2) + a("K#=o:")));
                                equalsIgnoreCase5 = bx.a(String.valueOf(string2) + a("K#=o:K64~)\u000b")).equalsIgnoreCase(a("\u0017>?\u007f:"));
                                Label_7064: {
                                    if (!q) {
                                        if (equalsIgnoreCase5) {
                                            a12.b(14);
                                            if (!q) {
                                                break Label_7064;
                                            }
                                        }
                                        bx.a(String.valueOf(string2) + a("K#=o:K64~)\u000b")).equalsIgnoreCase(a("\t2>c"));
                                    }
                                    if (equalsIgnoreCase5) {
                                        a12.b(13);
                                        if (!q) {
                                            break Label_7064;
                                        }
                                    }
                                    a12.b(15);
                                }
                                g5.setFont(ds.h(bx.a(String.valueOf(string2) + a("K#=o:K17y:"))));
                                a12.d(ds.a(bx.a(String.valueOf(string2) + a("K#=o:K47{!\u0017"))));
                                g5.a(bx.a(String.valueOf(string2) + a("K?1y:K#=o:")), ds.a(bx.a(String.valueOf(string2) + a("K?1y:K5?t!\t8*"))), ds.a(bx.a(String.valueOf(string2) + a("K#=o:K47{!\u0017"))));
                                a12.d((Image)this.a.d.a(bx.a(String.valueOf(string2) + a("K>5p")), 1));
                                image13 = (image14 = (image15 = (Image)this.a.d.a(bx.a(String.valueOf(string2) + a("K:7b=\u00008.r<\f:?")), 1)));
                                Label_7458: {
                                    if (!q) {
                                        if (image13 != null) {
                                            a12.f(image14);
                                        }
                                        cl3 = this;
                                        if (q) {
                                            break Label_7458;
                                        }
                                        image15 = (image14 = (Image)this.a.d.a(bx.a(String.valueOf(string2) + a("K:7b=\u000037` \f:?")), 1));
                                    }
                                    if (image15 != null) {
                                        a12.e(image14);
                                    }
                                    g5.a(this.k.j());
                                    g5.setBounds(b4);
                                    g5.a(new cp(this, bx.a(String.valueOf(string2) + a("K6;c'\n9"))));
                                    cl3 = this;
                                }
                                cl3.m.add(g5);
                            }
                            this.a(cf, (int)(n10 += a), a(")89s'\u000b0xD-\u00009=7\u0018\f2/r<Kyv"));
                        }
                        ++n30;
                    }
                    if (n30 < n11) {
                        continue;
                    }
                    break;
                }
                return;
            }
            continue;
        }
    }
    
    private void a(final cf cf, int n, final String s) {
        final int n2 = n;
        if (com.easypano.tw.g.q || n2 > 90) {
            n = n2;
        }
        cf.a(n, s);
    }
    
    private double a(final bx bx) {
        return 50.0 / (14 + ds.e(bx.a(a("\b6(9&\n#+g!\u0011y6b#\u00072*"))) + ds.e(bx.a(a("\b6(9-\n:(v=\u0016y6b#\u00072*"))) + ds.e(bx.a(a("\u0013>=`+\u0017y0x:\u0016'7c`\u000b\"5u+\u0017"))) + ds.e(bx.a(a("W31z/\u00022vy;\b"))) + ds.e(bx.a(a("\u0007\",c!\u000by6b#"))) + ds.e(bx.a(a("\u00164=y+K9-z"))));
    }
    
    private o a() {
        final o o = new o();
        o.a(this.a(42), this.a(41));
        o.b(true);
        return o;
    }
    
    private n a(final int n) {
        final boolean q = com.easypano.tw.g.q;
        final n n2 = new n();
        Label_0100: {
            if (!q) {
                switch (n) {
                    case 42: {
                        n2.b(42);
                        n2.setBackground(this.f);
                        n2.a(this.c);
                        break;
                    }
                    case 41: {
                        break Label_0100;
                    }
                }
            }
            final Image e = this.e;
            if (!q) {
                if (e != null) {
                    n2.b(this.e);
                }
                final Image d = this.d;
            }
            if (e == null) {
                return n2;
            }
            n2.c(this.d);
            if (!q) {
                return n2;
            }
        }
        n2.b(41);
        n2.setBackground(this.j);
        n2.a(this.g);
        final Image i = this.i;
        if (!q) {
            if (i != null) {
                n2.b(this.i);
            }
            final Image h = this.h;
        }
        if (i != null) {
            n2.c(this.h);
        }
        return n2;
    }
    
    public Image b() {
        return this.b;
    }
    
    public Object[] c() {
        final boolean q = com.easypano.tw.g.q;
        Component[] array = new Component[0];
        final int size;
        final int n = size = this.w.size();
        if (!q) {
            if (size <= 0) {
                return array;
            }
            array = new Component[n];
        }
        final Integer[] array2 = new Integer[size];
        int n2 = 0;
        while (true) {
            while (true) {
                Label_0080: {
                    if (!q) {
                        break Label_0080;
                    }
                    array[n2] = (Component)this.w.elementAt(n2);
                    array2[n2] = (Integer)this.x.elementAt(n2);
                    ++n2;
                }
                if (n2 < n) {
                    continue;
                }
                break;
            }
            if (q) {
                continue;
            }
            break;
        }
        this.a(array, array2);
        return array;
    }
    
    private void a(final Component[] array, final Integer[] array2) {
        final boolean q = com.easypano.tw.g.q;
        final int length = array.length;
        int n = 0;
    Label_0054_Outer:
        while (true) {
            Label_0129: {
                if (!q) {
                    break Label_0129;
                }
                int n2 = n;
                array2[n];
                int n3 = n + 1;
                int n5 = 0;
                int n6 = 0;
                while (true) {
                    while (true) {
                        Label_0072: {
                            if (!q) {
                                break Label_0072;
                            }
                            final int intValue;
                            int n4 = intValue = array2[n3];
                            Label_0069: {
                                Label_0068: {
                                    if (q) {
                                        break Label_0068;
                                    }
                                    if (n5 >= n6) {
                                        break Label_0069;
                                    }
                                    array2[n3];
                                    n4 = n3;
                                }
                                n2 = n4;
                            }
                            ++n3;
                        }
                        if (n3 < length) {
                            continue Label_0054_Outer;
                        }
                        break;
                    }
                    n5 = n2;
                    n6 = n;
                    if (q) {
                        continue;
                    }
                    break;
                }
                if (n5 != n6) {
                    final Integer n7 = array2[n2];
                    array2[n2] = array2[n];
                    array2[n] = n7;
                    final Component component = array[n2];
                    array[n2] = array[n];
                    array[n] = component;
                }
                ++n;
            }
            if (n >= length) {
                return;
            }
            continue;
        }
    }
    
    public bt d() {
        return this.m;
    }
    
    public l e() {
        return this.n;
    }
    
    public o f() {
        return this.o;
    }
    
    public k g() {
        return this.p;
    }
    
    public j h() {
        return this.q;
    }
    
    public p i() {
        return this.r;
    }
    
    public g j() {
        return this.s;
    }
    
    public g k() {
        return this.t;
    }
    
    public Vector l() {
        return this.u;
    }
    
    public Vector m() {
        return this.v;
    }
    
    public void destroyResource() {
        this.b = null;
        this.c = null;
        this.d = null;
        this.e = null;
        this.g = null;
        this.h = null;
        this.i = null;
        this.w.removeAllElements();
        this.w = null;
        this.x.removeAllElements();
        this.x = null;
        this.u.removeAllElements();
        this.u = null;
        this.v.removeAllElements();
        this.v = null;
        this.a = null;
        this.l = null;
        this.k = null;
    }
    
    static TWViewer a(final cl cl) {
        return cl.k;
    }
    
    private static String a(final String s) {
        final char[] charArray = s.toCharArray();
        final int length = charArray.length;
        int n = 0;
        while (true) {
            Label_0089: {
                if (length > 1) {
                    break Label_0089;
                }
                char[] array2;
                char[] array = array2 = charArray;
                int n3;
                int n2 = n3 = n;
                while (true) {
                    final char c = array2[n3];
                    char c2 = '\0';
                    switch (n % 5) {
                        case 0: {
                            c2 = 'e';
                            break;
                        }
                        case 1: {
                            c2 = 'W';
                            break;
                        }
                        case 2: {
                            c2 = 'X';
                            break;
                        }
                        case 3: {
                            c2 = '\u0017';
                            break;
                        }
                        default: {
                            c2 = 'N';
                            break;
                        }
                    }
                    array[n2] = (char)(c ^ c2);
                    ++n;
                    if (length != 0) {
                        break;
                    }
                    array = (array2 = charArray);
                    n2 = (n3 = length);
                }
            }
            if (n >= length) {
                return new String(charArray);
            }
            continue;
        }
    }
}
