// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Rectangle;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.easypano.tw.d.r;
import com.easypano.tw.d.i;
import com.easypano.tw.d.a;
import com.easypano.tw.d.c;
import com.easypano.tw.d.b;
import java.awt.Dimension;
import java.awt.Component;
import com.easypano.tw.d.o;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import com.easypano.tw.d.d;
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
    private m n;
    private p o;
    private l p;
    private k q;
    private q r;
    private h s;
    private h t;
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
        final boolean q = com.easypano.tw.h.q;
        final double n = 40.0;
        final double a = this.a(bx);
        String s = "";
        final Vector<String> vector = new Vector<String>();
        this.b = (Image)this.a.d.a(bx.a(a("p~ELoarEO&")), 1);
        this.a(cf, (int)n, a("OzMF(mr\fj.q|VM/wt@\u0002\u0012`gCN-at^\fo-"));
        this.f = dt.a(bx.a(a("kfNC3-wKA.oz^")));
        this.c = (Image)this.a.d.a(bx.a(a("kfNC3-wMPojxK")), 1);
        this.d = (Image)this.a.d.a(bx.a(a("kfNC3-wMPonzYQ$lcIP(nr")), 1);
        this.e = (Image)this.a.d.a(bx.a(a("kfNC3-wMPonzYQ$gz[L(nr")), 1);
        final double n2;
        this.a(cf, (int)(n2 = n + a), a("OzMF(mr\ft$qaEA o5\u007fA3ly@@ q;\u0002\f"));
        this.j = dt.a(bx.a(a("ufNC3-wKA.oz^")));
        this.g = (Image)this.a.d.a(bx.a(a("ufNC3-wMPojxK")), 1);
        this.h = (Image)this.a.d.a(bx.a(a("ufNC3-wMPonzYQ$lcIP(nr")), 1);
        this.i = (Image)this.a.d.a(bx.a(a("ufNC3-wMPonzYQ$gz[L(nr")), 1);
        double n3;
        this.a(cf, (int)(n3 = n2 + a), a("OzMF(mr\f\u0010\u0005#\\AC&f;\u0002\f"));
        final int e = dt.e(bx.a(a("1qEO dp\u0002L4n")));
        int n4 = 0;
        Rectangle bounds;
        int n6;
        int n5 = 0;
        while (true) {
            Label_0688: {
                if (n4 >= e) {
                    this.a(cf, (int)n3, a("OzMF(mr\fn(pwCZo-;"));
                    bounds = dt.b(bx.a(a("o|_VoazYL%p")));
                    final int width;
                    n5 = (width = (n6 = bounds.width));
                    if (!q) {
                        break;
                    }
                }
                else {
                    s = a("1q") + n4;
                    bounds = dt.b(bx.a(String.valueOf(s) + a("-wCW/gf")));
                    if (q) {
                        break Label_0688;
                    }
                    final int width2 = bounds.width;
                }
                if (n5 > 0 && bounds.height > 0) {
                    final h h = new h();
                    final d d = new d(h);
                    h.setBounds(bounds);
                    d.f(31);
                    Image image = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("-|AE")), 1);
                    if (!q) {
                        Label_0569: {
                            if (!bx.a(String.valueOf(s) + a("-xCF$")).equalsIgnoreCase(a("lgEE(mt@"))) {
                                final Image image2 = image;
                                if (!q) {
                                    if (image2 == null) {
                                        break Label_0569;
                                    }
                                    final Image scaledInstance;
                                    image = (scaledInstance = image.getScaledInstance(bounds.width, bounds.height, 1));
                                }
                                dt.a(image2);
                            }
                        }
                        d.d(image);
                        h.a(d);
                        h.a(new cp(this, bx.a(String.valueOf(s) + a("-tOV(l{"))));
                        this.w.addElement(h);
                    }
                    this.x.addElement(dt.f(bx.a(String.valueOf(s) + a("-o"))));
                }
                this.a(cf, (int)(n3 += a), a("OzMF(mr\f\u0010\u0005#\\AC&f;\u0002\f"));
            }
            ++n4;
        }
        final int n7;
        Label_1077: {
            if (!q) {
                if (n5 > 0) {
                    n7 = (n6 = bounds.height);
                    if (q) {
                        break Label_1077;
                    }
                    if (n7 > 0) {
                        this.p = new l();
                        int n8 = 0;
                        while (true) {
                            while (true) {
                                Label_0814: {
                                    if (!q) {
                                        break Label_0814;
                                    }
                                    int k = com.easypano.tw.f.k;
                                    com.easypano.tw.f.k = ++k;
                                    this.p.e().a(this.l.a(n8).e);
                                    ++n8;
                                }
                                if (n8 < this.l.a()) {
                                    continue;
                                }
                                break;
                            }
                            this.p.setBackground(dt.a(bx.a(a("o|_VoarOM-lg"))));
                            this.p.setFont(dt.h(bx.a(a("o|_VowpTVoezBV"))));
                            if (q) {
                                continue;
                            }
                            break;
                        }
                        final com.easypano.tw.d.q q2 = new com.easypano.tw.d.q();
                        q2.a(dt.a(bx.a(a("o|_VowpTVo`z@M3"))));
                        q2.b(dt.a(bx.a(a("o|_VowpTVonzYQ$gz[L\"lyCP"))));
                        q2.c(dt.a(bx.a(a("o|_VowpTVonzYQ$lcIP\"lyCP"))));
                        ((com.easypano.tw.d.m)this.p.a()).a(q2);
                        this.p.addItemListener(new cm(this));
                        final p a2 = this.a();
                        ((o)a2.a()).a(dt.a(bx.a(a("o|_Voaz^F$qvCN.q"))));
                        a2.setBounds(bounds);
                        a2.a((Component)this.p);
                        this.w.addElement(a2);
                        this.x.addElement(dt.f(bx.a(a("o|_Voy"))));
                    }
                }
                this.a(cf, (int)(n3 += a), a("OzMF(mr\fa.nwC@.{;\u0002\f"));
                bounds = dt.b(bx.a(a("`wN\f#l`BF2")));
                final int width;
                n6 = (width = bounds.width);
            }
        }
        Label_1652: {
            final Rectangle rectangle;
            Label_1111: {
                if (!q) {
                    if (n7 <= 0) {
                        break Label_1652;
                    }
                    rectangle = bounds;
                    if (q) {
                        break Label_1111;
                    }
                    n6 = rectangle.height;
                }
                if (n6 <= 0) {
                    break Label_1652;
                }
                this.q = new k();
            }
            rectangle.height = 20;
            this.q.setBounds(bounds);
            this.q.a(this.a());
            this.q.g().setFont(dt.h(bx.a(a("`wN\f5fmX\f'l{X"))));
            this.q.f().setFont(dt.h(bx.a(a("`wN\f5fmX\f'l{X"))));
            final Color a3 = dt.a(bx.a(a("`wN\f#dvCN.q")));
            this.q.setBackground(a3);
            this.q.g().setBackground(a3);
            this.q.e().setPreferredSize(new Dimension(20, 20));
            final b b = new b(this.q.e());
            b.g(a3);
            b.d((Image)this.a.d.a(bx.a(a("`wN\f#w{\u0002K,d")), 1));
            Image image5;
            Image image4;
            final Image image3 = image4 = (image5 = (Image)this.a.d.a(bx.a(a("`wN\f#w{\u0002O.vfIF.t{EO&")), 1));
            cl cl = null;
            Label_1388: {
                if (!q) {
                    if (image3 != null) {
                        b.e(image4);
                    }
                    cl = this;
                    if (q) {
                        break Label_1388;
                    }
                    image5 = (image4 = (Image)this.a.d.a(bx.a(a("`wN\f#w{\u0002O.vfIM7fgEO&")), 1));
                }
                if (image5 != null) {
                    b.f(image4);
                }
                this.q.e().a(b);
                cl = this;
            }
            cl.q.e().b(false);
            final com.easypano.tw.d.q q3 = new com.easypano.tw.d.q();
            q3.a(dt.a(bx.a(a("`wN\f5fmX\f\"lyCP"))));
            final Color a4 = dt.a(bx.a(a("`wN\f5fmX\f,l`_G%lbBA.oz^")));
            q3.b(a4);
            q3.c(dt.a(bx.a(a("`wN\f5fmX\f,l`_G.up^A.oz^"))));
            ((c)this.q.f().a()).d(a4);
            ((com.easypano.tw.d.m)this.q.g().a()).a(q3);
            final Color a5 = dt.a(bx.a(a("`wN\f#lgHG3`z@M3")));
            ((com.easypano.tw.d.l)this.q.a()).a(a5);
            ((o)this.q.i().a()).a(a5);
            final dd e2 = this.q.g().e();
            int n9 = 0;
            while (true) {
                while (true) {
                    Label_1589: {
                        if (!q) {
                            break Label_1589;
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
            this.x.addElement(dt.f(bx.a(a("`wN\f;"))));
        }
        double n10;
        this.a(cf, (int)(n10 = n3 + a), a("OzMF(mr\f`4waCLo-;"));
        int n11 = dt.e(bx.a(a("a`XV.m;BW,")));
        int n12 = 0;
        Rectangle bounds2;
        int n13 = 0;
        boolean b2;
        while (true) {
            while (true) {
                Label_2954: {
                    if (!q) {
                        break Label_2954;
                    }
                    s = a("aaB") + n12;
                    bounds2 = dt.b(bx.a(String.valueOf(s) + a("-wCW/gf")));
                    Label_2951: {
                        if (q) {
                            break Label_2951;
                        }
                        final int width3 = bounds2.width;
                        if (n13 > 0 && bounds2.height > 0) {
                            final h h2 = new h();
                            final a a6 = (a)h2.a();
                            Label_1852: {
                                Label_1845: {
                                    if (!q) {
                                        if (!bx.a(String.valueOf(s) + a("-fX[-f")).equalsIgnoreCase(a("paMV(`"))) {
                                            break Label_1845;
                                        }
                                        a6.c(22);
                                    }
                                    if (!q) {
                                        break Label_1852;
                                    }
                                }
                                a6.c(21);
                            }
                            final boolean equalsIgnoreCase = bx.a(String.valueOf(s) + a("-aIZ5-t@K&m")).equalsIgnoreCase(a("q|KJ5"));
                            Label_1968: {
                                if (!q) {
                                    if (equalsIgnoreCase) {
                                        a6.b(14);
                                        if (!q) {
                                            break Label_1968;
                                        }
                                    }
                                    bx.a(String.valueOf(s) + a("-aIZ5-t@K&m")).equalsIgnoreCase(a("opJV"));
                                }
                                if (equalsIgnoreCase) {
                                    a6.b(13);
                                    if (!q) {
                                        break Label_1968;
                                    }
                                }
                                a6.b(15);
                            }
                            h2.setFont(dt.h(bx.a(String.valueOf(s) + a("-aIZ5-sCL5"))));
                            h2.e().a(bx.a(String.valueOf(s) + a("-aIZ5")));
                            a6.d(dt.a(bx.a(String.valueOf(s) + a("-aIZ5-vCN.q"))));
                            h2.a(bx.a(String.valueOf(s) + a("-}EL5-aIZ5")), dt.a(bx.a(String.valueOf(s) + a("-}EL5-wKA.oz^"))), dt.a(bx.a(String.valueOf(s) + a("-aIZ5-vCN.q"))));
                            a6.d((Image)this.a.d.a(bx.a(String.valueOf(s) + a("-|AE")), 1));
                            Image image8;
                            Image image7;
                            final Image image6 = image7 = (image8 = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("-xCW2fzZG3jxK")), 1));
                            if (!q) {
                                if (image6 != null) {
                                    a6.f(image7);
                                }
                                image8 = (image7 = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("-xCW2fqCU/jxK")), 1));
                            }
                            if (image8 != null) {
                                a6.e(image7);
                            }
                            Object o;
                            final String s2 = (String)(o = bx.a(String.valueOf(s) + a("-aUR$")));
                            Label_2725: {
                                Label_2718: {
                                    if (!q) {
                                        if (!s2.equalsIgnoreCase(a("wzKE-f"))) {
                                            break Label_2718;
                                        }
                                        a6.d(32);
                                        h2.e().b(bx.a(String.valueOf(s) + a("-fIN$`aIF5fmX")));
                                        a6.a(dt.a(bx.a(String.valueOf(s) + a("-aIZ5-vCN.q"))));
                                        h2.b(bx.a(String.valueOf(s) + a("-}EL5-fIN$`aIF5fmX")), dt.a(bx.a(String.valueOf(s) + a("-}EL5-wKA.oz^"))), dt.a(bx.a(String.valueOf(s) + a("-aIZ5-vCN.q"))));
                                        a6.a((Image)this.a.d.a(bx.a(String.valueOf(s) + a("-fIN$`aIF(nr")), 1));
                                        o = this.a.d.a(bx.a(String.valueOf(s) + a("-fIN$`aIF,l`_G.up^K,d")), 1);
                                    }
                                    Image image11;
                                    Image image10;
                                    final Image image9 = image10 = (image11 = (Image)o);
                                    if (!q) {
                                        if (image9 != null) {
                                            a6.c(image10);
                                        }
                                        image11 = (image10 = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("-fIN$`aIF,l`_G%lbBK,d")), 1));
                                    }
                                    if (image11 == null) {
                                        break Label_2725;
                                    }
                                    a6.b(image10);
                                    if (!q) {
                                        break Label_2725;
                                    }
                                }
                                a6.d(31);
                            }
                            h2.setBounds(bounds2);
                            final String a7 = bx.a(String.valueOf(s) + a("-tOV(l{"));
                            h2.a(new cp(this, a7));
                            final int startsWith = a7.startsWith(a("syM[,lcEG")) ? 1 : 0;
                            Label_2886: {
                                Vector<String> v = null;
                                Label_2877: {
                                    Label_2852: {
                                        if (!q) {
                                            if (startsWith == 0) {
                                                final boolean startsWith2 = a7.startsWith(a("syM[1baD"));
                                                if (q) {
                                                    break Label_2852;
                                                }
                                                if (!startsWith2) {
                                                    break Label_2886;
                                                }
                                            }
                                            h2.e().e(false);
                                            dt.a(a7, vector);
                                            this.u.addElement(h2);
                                            final Vector<String> vector2 = v = vector;
                                            if (q) {
                                                break Label_2877;
                                            }
                                            vector2.size();
                                        }
                                    }
                                    if (startsWith > 0) {
                                        this.v.addElement(vector.elementAt(0));
                                        if (!q) {
                                            break Label_2886;
                                        }
                                    }
                                    v = (Vector<String>)this.v;
                                }
                                v.addElement(a(".$"));
                            }
                            this.w.addElement(h2);
                            this.x.addElement(dt.f(bx.a(String.valueOf(s) + a("-o"))));
                        }
                        this.a(cf, (int)(n10 += a), a("OzMF(mr\f`4waCLo-;"));
                    }
                    ++n12;
                }
                if (n12 < n11) {
                    continue;
                }
                break;
            }
            this.a(cf, (int)(n10 += a), a("OzMF(mr\fv)vxNL jy\u0002\fo"));
            bounds2 = dt.b(bx.a(a("ww\u0002@.v{HQ")));
            n13 = ((b2 = (bounds2.width != 0)) ? 1 : 0);
            if (q) {
                continue;
            }
            break;
        }
        final int n14;
        Label_3520: {
            Label_3482: {
                Label_3228: {
                    Label_3197: {
                        if (!q) {
                            if (n13 <= 0) {
                                final int height;
                                n14 = (height = bounds2.height);
                                if (q) {
                                    break Label_3520;
                                }
                                if (n14 <= 0) {
                                    break Label_3482;
                                }
                            }
                            (this.r = new q()).setBackground(dt.a(bx.a(a("ww\u0002@&`z@M3"))));
                            this.r.a(dt.a(bx.a(a("ww\u0002Q$opOV#lgHG3`z@M3"))));
                            this.r.b(dt.a(bx.a(a("ww\u0002O.vfIM7fgNM3gp^A.oz^"))));
                            this.r.c(dt.a(bx.a(a("ww\u0002O.vfIF.t{NM3gp^A.oz^"))));
                            if (q) {
                                break Label_3197;
                            }
                            b2 = bx.a(a("ww\u0002O.gp")).equalsIgnoreCase(a("n`@V(o|BG"));
                        }
                        if (b2) {
                            this.r.b(41);
                            this.r.setPreferredSize(new Dimension(bounds2.width - com.easypano.tw.o.l.width, bounds2.height));
                            if (!q) {
                                break Label_3228;
                            }
                        }
                        this.r.b(42);
                    }
                    this.r.setPreferredSize(new Dimension(bounds2.width, bounds2.height - com.easypano.tw.o.l.height));
                }
                this.r.d(dt.g(bx.a(a("ww\u0002Q)lbBC,f"))));
                this.r.d(dt.a(bx.a(a("ww\u0002N ap@A.oz^"))));
                this.r.a(dt.c(bx.a(a("ww\u0002L jy_K;f"))));
                int n15 = 0;
                while (true) {
                    Label_3373: {
                        if (!q) {
                            break Label_3373;
                        }
                        this.r.a(new j((Image)this.a.d.a(this.l.a(n15).l, 1), this.l.a(n15).e));
                        this.a(cf, (int)(n10 += a), a("OzMF(mr\fv)vxNL jy\u0002\fo"));
                        ++n15;
                    }
                    if (n15 < this.l.a()) {
                        continue;
                    }
                    break;
                }
                this.r.a(new co(this));
                this.r.doLayout();
                final p a8 = this.a();
                ((o)a8.a()).a(dt.a(bx.a(a("ww\u0002@.qqIP\"lyCP"))));
                a8.a((Component)this.r);
                a8.setBounds(bounds2);
                this.w.addElement(a8);
                this.x.addElement(dt.f(bx.a(a("ww\u0002X"))));
            }
            this.a(cf, (int)(n10 += a), a("OzMF(mr\fc&f{Xk/ez\u0002\fo"));
            bounds2 = dt.b(bx.a(a("brIL5j{JMoazYL%p")));
            final int width4 = bounds2.width;
        }
        final int height2;
        Label_3861: {
            if (!q) {
                if (n14 > 0) {
                    height2 = bounds2.height;
                    if (q) {
                        break Label_3861;
                    }
                    if (height2 > 0) {
                        final g g2;
                        final g g = g2 = new g();
                        Label_3603: {
                            if (!q) {
                                g2.setBounds(bounds2);
                                if (bx.a(a("brIL5j{JMootUM4w")).equalsIgnoreCase(a("Up^V(`t@"))) {
                                    g.b(41);
                                    if (!q) {
                                        break Label_3603;
                                    }
                                }
                            }
                            g2.b(42);
                        }
                        g.setName(bx.a(a("brIL5j{JMomtAG")));
                        g.b(bx.a(a("brIL5j{JMos}CL$")));
                        g.c(bx.a(a("brIL5j{JMoetT")));
                        final String a9 = bx.a(a("brIL5j{JMontEN"));
                        g.e(a9);
                        g.b(new cp(this, a("leIL4qy\u0004") + a9 + ")"));
                        final String a10 = bx.a(a("brIL5j{JMotpNQ(wp"));
                        g.d(a10);
                        g.a(new cp(this, a("leIL4qy\u0004") + a10 + a("/5s@-b{G\u000b")));
                        g.a((Image)this.a.d.a(bx.a(a("brIL5j{JMojxK")), 1));
                        this.w.addElement(g);
                        this.x.addElement(dt.f(bx.a(a("brIL5j{JMoy"))));
                    }
                }
                this.a(cf, (int)(n10 += a), a("OzMF(mr\fv${a\fn(m~\u0002\fo"));
                n11 = dt.e(bx.a(a("wpTV-j{G\f/vx")));
            }
        }
        int n16 = height2;
        Rectangle rectangle2;
        int n17 = 0;
        while (true) {
            while (true) {
                Label_4478: {
                    if (!q) {
                        break Label_4478;
                    }
                    s = a("wy") + n16;
                    rectangle2 = dt.b(bx.a(String.valueOf(s) + a("-wCW/gf")));
                    final int width5;
                    int height3 = width5 = rectangle2.width;
                    Label_4475: {
                        if (!q) {
                            if (n17 <= 0) {
                                break Label_4475;
                            }
                            height3 = rectangle2.height;
                        }
                        if (height3 > 0) {
                            final h h3 = new h();
                            final i i = new i(h3);
                            h3.a(i);
                            h3.setBounds(rectangle2);
                            h3.e().a(bx.a(String.valueOf(s) + a("-aIZ5")));
                            final boolean equalsIgnoreCase2 = bx.a(String.valueOf(s) + a("-aIZ5-t@K&m")).equalsIgnoreCase(a("`pBV$q"));
                            Label_4130: {
                                if (!q) {
                                    if (equalsIgnoreCase2) {
                                        i.b(15);
                                        if (!q) {
                                            break Label_4130;
                                        }
                                    }
                                    bx.a(String.valueOf(s) + a("-aIZ5-t@K&m")).equalsIgnoreCase(a("q|KJ5"));
                                }
                                if (equalsIgnoreCase2) {
                                    i.b(14);
                                    if (!q) {
                                        break Label_4130;
                                    }
                                }
                                i.b(13);
                            }
                            h3.setFont(dt.h(bx.a(String.valueOf(s) + a("-aIZ5-sCL5"))));
                            i.d(dt.a(bx.a(String.valueOf(s) + a("-aIZ5-vCN.q"))));
                            i.f(dt.a(bx.a(String.valueOf(s) + a("-aIZ5-xCW2fzZG3`z@M3"))));
                            i.e(dt.a(bx.a(String.valueOf(s) + a("-aIZ5-xCW2fqCU/`z@M3"))));
                            final boolean equalsIgnoreCase3 = bx.a(String.valueOf(s) + a("-fX[-f")).equalsIgnoreCase(a("MpZG3V{HG3o|BG"));
                            Label_4387: {
                                if (!q) {
                                    if (equalsIgnoreCase3) {
                                        i.f(3);
                                        if (!q) {
                                            break Label_4387;
                                        }
                                    }
                                    bx.a(String.valueOf(s) + a("-fX[-f")).equalsIgnoreCase(a("KzZG3V{HG3o|BG"));
                                }
                                if (equalsIgnoreCase3) {
                                    i.f(1);
                                    if (!q) {
                                        break Label_4387;
                                    }
                                }
                                i.f(2);
                            }
                            h3.a(new cp(this, bx.a(String.valueOf(s) + a("-tOV(l{"))));
                            this.w.addElement(h3);
                            this.x.addElement(dt.f(bx.a(String.valueOf(s) + a("-o"))));
                        }
                    }
                    ++n16;
                }
                if (n16 < n11) {
                    continue;
                }
                break;
            }
            this.a(cf, (int)(n10 += a), a("OzMF(mr\fq\"f{I\u0002\u0005ffOP(saEM/-;\u0002"));
            rectangle2 = dt.b(bx.a(a("pvIL$gp_\f#l`BF2")));
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
        Label_4737: {
            if (!q) {
                if (n17 > 0) {
                    final int n18;
                    n19 = (n18 = rectangle2.height);
                    if (q) {
                        break Label_4737;
                    }
                    if (n19 > 0) {
                        this.s = new h();
                        final c c = new c(this.s, true);
                        this.s.a(c);
                        this.s.setBounds(rectangle2);
                        this.s.setFont(dt.h(bx.a(a("pvIL$gp_\f5fmX\f'l{X"))));
                        c.d(dt.a(bx.a(a("pvIL$gp_\f5fmX\f\"lyCP"))));
                        final p a11 = this.a();
                        ((o)a11.a()).a((Color)null);
                        a11.a(false);
                        a11.a((Component)this.s);
                        a11.setBounds(rectangle2);
                        this.w.addElement(a11);
                        this.x.addElement(dt.f(bx.a(a("pvIL$gp_\f;"))));
                    }
                }
                this.a(cf, (int)(n10 += a), a("OzMF(mr\fq5baYQ#bg\u0002\fo"));
                rectangle2 = dt.b(bx.a(a("paMV4pwMPoazYL%p")));
                final int width6 = rectangle2.width;
            }
        }
        final int height4;
        Label_4904: {
            if (!q) {
                if (n19 > 0) {
                    height4 = rectangle2.height;
                    if (q) {
                        break Label_4904;
                    }
                    if (height4 > 0) {
                        this.t = new h();
                        final c c2 = new c(this.t, false);
                        this.t.a(c2);
                        this.t.setBounds(rectangle2);
                        this.t.setFont(dt.h(bx.a(a("paMV4pwMPowpTVoezBV"))));
                        c2.d(dt.a(bx.a(a("paMV4pwMPowpTVo`z@M3"))));
                        this.w.addElement(this.t);
                        this.x.addElement(dt.f(bx.a(a("paMV4pwMPoy"))));
                    }
                }
                this.a(cf, (int)(n10 += a), a("OzMF(mr\fn ap@\fo-"));
                n11 = dt.e(bx.a(a("otNG--{YO")));
            }
        }
        int n20 = height4;
        Rectangle rectangle3;
        int width7;
        int height5;
        int n21 = 0;
        h h4;
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
        bo bo;
        int n29;
        Rectangle b3;
        Rectangle rectangle4;
        com.easypano.tw.i j;
        Image image14;
        Image image13;
        Image image12;
        cl cl2 = null;
        String string;
        da da;
        int n30;
        String string2;
        Rectangle b4;
        h h5;
        a a12;
        boolean equalsIgnoreCase5;
        Image image17;
        Image image16;
        Image image15;
        cl cl3 = null;
        Label_5783_Outer:Label_6223_Outer:Label_6762_Outer:
        while (true) {
            while (true) {
                Label_5293: {
                    if (!q) {
                        break Label_5293;
                    }
                    s = a("ow@") + n20;
                    rectangle3 = dt.b(bx.a(String.valueOf(s) + a("-wCW/gf")));
                    height5 = (width7 = rectangle3.width);
                    Label_5290: {
                        if (!q) {
                            if (n21 <= 0) {
                                break Label_5290;
                            }
                            height5 = rectangle3.height;
                        }
                        if (height5 > 0) {
                            h4 = new h();
                            c3 = new c(h4, false);
                            h4.a(c3);
                            h4.setBounds(rectangle3);
                            h4.e().a(bx.a(String.valueOf(s) + a("-aIZ5")));
                            equalsIgnoreCase4 = bx.a(String.valueOf(s) + a("-aIZ5-t@K&m")).equalsIgnoreCase(a("q|KJ5"));
                            Label_5174: {
                                if (!q) {
                                    if (equalsIgnoreCase4) {
                                        c3.b(14);
                                        if (!q) {
                                            break Label_5174;
                                        }
                                    }
                                    bx.a(String.valueOf(s) + a("-aIZ5-t@K&m")).equalsIgnoreCase(a("`pBV$q"));
                                }
                                if (equalsIgnoreCase4) {
                                    c3.b(15);
                                    if (!q) {
                                        break Label_5174;
                                    }
                                }
                                c3.b(13);
                            }
                            c3.d(dt.a(bx.a(String.valueOf(s) + a("-aIZ5-vCN.q"))));
                            h4.setFont(dt.h(bx.a(String.valueOf(s) + a("-aIZ5-sCL5"))));
                            this.w.addElement(h4);
                            this.x.addElement(dt.f(bx.a(String.valueOf(s) + "z")));
                        }
                    }
                    ++n20;
                }
                if (n20 < n11) {
                    continue;
                }
                break;
            }
            this.a(cf, (int)(n10 += a), a("OzMF(mr\fo s;\u0002\f"));
            rectangle3 = dt.b(bx.a(a("nt\\\f#l`BF2")));
            n21 = (height5 = (n22 = (height6 = (n23 = rectangle3.width))));
            if (!q) {
                Label_6589: {
                    if (!q) {
                        if (n21 > 0) {
                            n24 = (height6 = (n23 = rectangle3.height));
                            if (q) {
                                break Label_6589;
                            }
                            if (n24 > 0) {
                                (this.n = new m(this.k)).setBackground(dt.a(bx.a(a("nt\\\f#dvCN.q"))));
                                ((r)this.n.a()).a((Image)this.a.d.a(bx.a(a("nt\\\f(nr")), 1));
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
                                Label_5517: {
                                    if (!q) {
                                        if (n27 >= n28) {
                                            break Label_5517;
                                        }
                                        width8 = rectangle3.height - preferredSize.height;
                                        width9 = 2;
                                    }
                                    n26 = width8 / width9;
                                }
                                this.o = this.a();
                                ((o)this.o.a()).a((Color)null);
                                this.o.setBounds(rectangle3);
                                this.o.a((Component)this.n);
                                this.o.a(this.k.j());
                                bo = new bo();
                                bo.setLayout(new BorderLayout());
                                bo.setBounds(rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height);
                                bo.add(this.o, a("@pBV$q"));
                                bo.a(dt.a(bx.a(a("nt\\\f#lgHG3`z@M3"))));
                                this.w.addElement(bo);
                                this.x.addElement(dt.f(bx.a(a("nt\\\f;"))));
                                n11 = dt.e(bx.a(a("nt\\\f)la_R.w;BW,ap^")));
                                n29 = 0;
                                while (true) {
                                    while (true) {
                                        Label_6188: {
                                            if (!q) {
                                                break Label_6188;
                                            }
                                            s = a("nt\\\f)p") + n29;
                                            rectangle3 = (b3 = dt.b(bx.a(String.valueOf(s) + a("-wCW/gf"))));
                                            b3.x += n25;
                                            rectangle4 = rectangle3;
                                            rectangle4.y += n26;
                                            if (!q) {
                                                if (rectangle3.width > 0 && rectangle3.height > 0) {
                                                    j = new com.easypano.tw.i();
                                                    j.setBounds(rectangle3);
                                                    ((com.easypano.tw.d.h)j.a()).d((Image)this.a.d.a(bx.a(String.valueOf(s) + a("-|AE")), 1));
                                                    image12 = (image13 = (image14 = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("-xCW2fqCU/jxK")), 1)));
                                                    Label_6158: {
                                                        if (!q) {
                                                            if (image12 != null) {
                                                                ((com.easypano.tw.d.h)j.a()).e(image13);
                                                            }
                                                            cl2 = this;
                                                            if (q) {
                                                                break Label_6158;
                                                            }
                                                            image14 = (image13 = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("-xCW2fzZG3jxK")), 1));
                                                        }
                                                        if (image14 != null) {
                                                            ((com.easypano.tw.d.h)j.a()).f(image13);
                                                        }
                                                        j.setForeground(dt.a(bx.a(String.valueOf(s) + a("-vCN.q"))));
                                                        j.a(bx.a(String.valueOf(s) + a("-}EL5-aIZ5")), dt.a(bx.a(String.valueOf(s) + a("-}EL5-wK"))));
                                                        j.a(this.k.j());
                                                        j.a(new cp(this, bx.a(String.valueOf(s) + a("-tOV(l{"))));
                                                        cl2 = this;
                                                    }
                                                    cl2.n.add(j);
                                                }
                                                this.a(cf, (int)(n10 += a), a("OzMF(mr\fo s;\u0002\f"));
                                            }
                                            ++n29;
                                        }
                                        if (n29 < n11) {
                                            continue Label_5783_Outer;
                                        }
                                        break;
                                    }
                                    n11 = dt.e(bx.a(a("nt\\\f\"lx\\C2p;BW,ap^")));
                                    n29 = 0;
                                    if (q) {
                                        continue Label_6223_Outer;
                                    }
                                    break;
                                }
                                while (true) {
                                    Label_6544: {
                                        if (!q) {
                                            break Label_6544;
                                        }
                                        string = a("nt\\\f\"s") + n29;
                                        da = new da();
                                        da.b = dt.e(bx.a(String.valueOf(string) + a("-fOG/f")));
                                        da.a = dt.d(bx.a(String.valueOf(string) + a("-yCA")));
                                        da.f = dt.a(bx.a(String.valueOf(string) + a("-wCP%fgOM-lg")));
                                        da.c = (Image)this.a.d.a(bx.a(String.valueOf(string) + a("-|AE")), 1);
                                        da.d = dt.e(bx.a(String.valueOf(string) + a("-qEO"))) * 256 / 100;
                                        if (!q) {
                                            if (bx.a() == 1) {
                                                da.d = 50;
                                            }
                                            da.e = dt.e(bx.a(String.valueOf(string) + a("-|BK5b{KN$")));
                                            ((r)this.n.a()).a(da);
                                            this.a(cf, (int)(n10 += a), a("OzMF(mr\fo s;\u0002\f"));
                                        }
                                        ++n29;
                                    }
                                    if (n29 < n11) {
                                        continue Label_6762_Outer;
                                    }
                                    break;
                                }
                            }
                        }
                        this.a(cf, (int)(n10 += a), a("OzMF(mr\fq\"f{I\u0002\u0017jp[G3-;\u0002"));
                        rectangle3 = dt.b(bx.a(a("u|IU$q;NM4mq_")));
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
                    this.m.setBackground(dt.a(bx.a(a("u|IU$q;NE\"lyCP"))));
                    this.m.b((Image)this.a.d.a(bx.a(a("u|IU$q;JP np")), 1));
                    this.m.b(dt.e(bx.a(a("u|IU$q;]W o|X["), "1")));
                    this.w.addElement(this.m);
                    this.x.addElement(dt.f(bx.a(a("u|IU$q;V"))));
                    n11 = dt.e(bx.a(a("u|IU$q;DM5peCVom`A@$q")));
                    n23 = 0;
                }
                n30 = n23;
                while (true) {
                    Label_7497: {
                        if (!q) {
                            break Label_7497;
                        }
                        string2 = a("u|IU$q;DQ") + n30;
                        b4 = dt.b(bx.a(String.valueOf(string2) + a("-wCW/gf")));
                        if (!q) {
                            if (b4.width > 0 && b4.height > 0) {
                                h5 = new h();
                                a12 = (a)h5.a();
                                Label_6921: {
                                    Label_6914: {
                                        if (!q) {
                                            if (!bx.a(String.valueOf(string2) + a("-fX[-f")).equalsIgnoreCase(a("paMV(`"))) {
                                                break Label_6914;
                                            }
                                            a12.c(22);
                                        }
                                        if (!q) {
                                            break Label_6921;
                                        }
                                    }
                                    a12.c(21);
                                }
                                h5.e().a(bx.a(String.valueOf(string2) + a("-aIZ5")));
                                equalsIgnoreCase5 = bx.a(String.valueOf(string2) + a("-aIZ5-t@K&m")).equalsIgnoreCase(a("q|KJ5"));
                                Label_7073: {
                                    if (!q) {
                                        if (equalsIgnoreCase5) {
                                            a12.b(14);
                                            if (!q) {
                                                break Label_7073;
                                            }
                                        }
                                        bx.a(String.valueOf(string2) + a("-aIZ5-t@K&m")).equalsIgnoreCase(a("opJV"));
                                    }
                                    if (equalsIgnoreCase5) {
                                        a12.b(13);
                                        if (!q) {
                                            break Label_7073;
                                        }
                                    }
                                    a12.b(15);
                                }
                                h5.setFont(dt.h(bx.a(String.valueOf(string2) + a("-aIZ5-sCL5"))));
                                a12.d(dt.a(bx.a(String.valueOf(string2) + a("-aIZ5-vCN.q"))));
                                h5.a(bx.a(String.valueOf(string2) + a("-}EL5-aIZ5")), dt.a(bx.a(String.valueOf(string2) + a("-}EL5-wKA.oz^"))), dt.a(bx.a(String.valueOf(string2) + a("-aIZ5-vCN.q"))));
                                a12.d((Image)this.a.d.a(bx.a(String.valueOf(string2) + a("-|AE")), 1));
                                image15 = (image16 = (image17 = (Image)this.a.d.a(bx.a(String.valueOf(string2) + a("-xCW2fzZG3jxK")), 1)));
                                Label_7467: {
                                    if (!q) {
                                        if (image15 != null) {
                                            a12.f(image16);
                                        }
                                        cl3 = this;
                                        if (q) {
                                            break Label_7467;
                                        }
                                        image17 = (image16 = (Image)this.a.d.a(bx.a(String.valueOf(string2) + a("-xCW2fqCU/jxK")), 1));
                                    }
                                    if (image17 != null) {
                                        a12.e(image16);
                                    }
                                    h5.a(this.k.j());
                                    h5.setBounds(b4);
                                    h5.a(new cp(this, bx.a(String.valueOf(string2) + a("-tOV(l{"))));
                                    cl3 = this;
                                }
                                cl3.m.add(h5);
                            }
                            this.a(cf, (int)(n10 += a), a("OzMF(mr\fq\"f{I\u0002\u0017jp[G3-;\u0002"));
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
        if (com.easypano.tw.h.q || n2 > 90) {
            n = n2;
        }
        cf.a(n, s);
    }
    
    private double a(final bx bx) {
        return 50.0 / (14 + dt.e(bx.a(a("nt\\\f)la_R.w;BW,ap^"))) + dt.e(bx.a(a("nt\\\f\"lx\\C2p;BW,ap^"))) + dt.e(bx.a(a("u|IU$q;DM5peCVom`A@$q"))) + dt.e(bx.a(a("1qEO dp\u0002L4n"))) + dt.e(bx.a(a("a`XV.m;BW,"))) + dt.e(bx.a(a("pvIL$-{YO"))));
    }
    
    private p a() {
        final p p = new p();
        p.a(this.a(42), this.a(41));
        p.b(true);
        return p;
    }
    
    private com.easypano.tw.o a(final int n) {
        final boolean q = com.easypano.tw.h.q;
        final com.easypano.tw.o o = new com.easypano.tw.o();
        Label_0100: {
            if (!q) {
                switch (n) {
                    case 42: {
                        o.b(42);
                        o.setBackground(this.f);
                        o.a(this.c);
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
                    o.b(this.e);
                }
                final Image d = this.d;
            }
            if (e == null) {
                return o;
            }
            o.c(this.d);
            if (!q) {
                return o;
            }
        }
        o.b(41);
        o.setBackground(this.j);
        o.a(this.g);
        final Image i = this.i;
        if (!q) {
            if (i != null) {
                o.b(this.i);
            }
            final Image h = this.h;
        }
        if (i != null) {
            o.c(this.h);
        }
        return o;
    }
    
    public Image b() {
        return this.b;
    }
    
    public Object[] c() {
        final boolean q = com.easypano.tw.h.q;
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
        final boolean q = com.easypano.tw.h.q;
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
    
    public m e() {
        return this.n;
    }
    
    public p f() {
        return this.o;
    }
    
    public l g() {
        return this.p;
    }
    
    public k h() {
        return this.q;
    }
    
    public q i() {
        return this.r;
    }
    
    public h j() {
        return this.s;
    }
    
    public h k() {
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
                            c2 = '\u0003';
                            break;
                        }
                        case 1: {
                            c2 = '\u0015';
                            break;
                        }
                        case 2: {
                            c2 = ',';
                            break;
                        }
                        case 3: {
                            c2 = '\"';
                            break;
                        }
                        default: {
                            c2 = 'A';
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
