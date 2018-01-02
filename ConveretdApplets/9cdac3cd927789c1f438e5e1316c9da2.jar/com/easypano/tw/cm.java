// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Rectangle;
import java.awt.LayoutManager;
import java.awt.BorderLayout;
import com.easypano.tw.c.r;
import com.easypano.tw.c.a;
import com.easypano.tw.c.c;
import com.easypano.tw.c.b;
import java.awt.Dimension;
import java.awt.Component;
import java.awt.event.ItemListener;
import com.easypano.tw.c.m;
import com.easypano.tw.c.q;
import java.awt.event.ActionListener;
import com.easypano.tw.c.d;
import java.util.Vector;
import java.awt.Color;
import java.awt.Image;

public class cm
{
    private bz a;
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
    private ck l;
    private bu m;
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
    
    public cm(final bz a, final ck l, final TWViewer k) {
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
    
    public void a(final by by, final cg cg) {
        final boolean q = com.easypano.tw.g.q;
        final double n = 40.0;
        final double a = this.a(by);
        String s = "";
        final Vector<String> vector = new Vector<String>();
        this.b = (Image)this.a.d.a(by.a(a("a-,B5p!,A|")), 1);
        this.a(cg, (int)n, a("^)$Hr|!edt`/?Cuf')\fHq4*@wp'7\u00025<"));
        this.f = dt.a(by.a(a("z5'Mi<$\"Ot~)7")));
        this.c = (Image)this.a.d.a(by.a(a("z5'Mi<$$^5{+\"")), 1);
        this.d = (Image)this.a.d.a(by.a(a("z5'Mi<$$^5\u007f)0_~}0 ^r\u007f!")), 1);
        this.e = (Image)this.a.d.a(by.a(a("z5'Mi<$$^5\u007f)0_~v)2Br\u007f!")), 1);
        final double n2;
        this.a(cg, (int)(n2 = n + a), a("^)$Hr|!ez~`2,Oz~f\u0016Oi}*)Nz`hk\u0002"));
        this.j = dt.a(by.a(a("d5'Mi<$\"Ot~)7")));
        this.g = (Image)this.a.d.a(by.a(a("d5'Mi<$$^5{+\"")), 1);
        this.h = (Image)this.a.d.a(by.a(a("d5'Mi<$$^5\u007f)0_~}0 ^r\u007f!")), 1);
        this.i = (Image)this.a.d.a(by.a(a("d5'Mi<$$^5\u007f)0_~v)2Br\u007f!")), 1);
        double n3;
        this.a(cg, (int)(n3 = n2 + a), a("^)$Hr|!e\u001e_2\u000f(M|whk\u0002"));
        final int e = dt.e(by.a(a(" \",Azu#kBn\u007f")));
        int n4 = 0;
        Rectangle bounds;
        int n6;
        int n5 = 0;
        while (true) {
            Label_0678: {
                if (n4 >= e) {
                    this.a(cg, (int)n3, a("^)$Hr|!e`ra$*T5<h"));
                    bounds = dt.b(by.a(a("~/6X5p)0B\u007fa")));
                    final int width;
                    n5 = (width = (n6 = bounds.width));
                    if (!q) {
                        break;
                    }
                }
                else {
                    s = a(" \"") + n4;
                    bounds = dt.b(by.a(String.valueOf(s) + a("<$*Yuv5")));
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
                    Image scaledInstance = (Image)this.a.d.a(by.a(String.valueOf(s) + a("</(K")), 1);
                    if (!q) {
                        if (!by.a(String.valueOf(s) + a("<+*H~")).equalsIgnoreCase(a("}4,Kr|')"))) {
                            scaledInstance = scaledInstance.getScaledInstance(bounds.width, bounds.height, 1);
                            dt.a(scaledInstance);
                        }
                        d.d(scaledInstance);
                        g.a(d);
                        g.a(new cq(this, by.a(String.valueOf(s) + a("<'&Xr}("))));
                        this.w.addElement(g);
                    }
                    this.x.addElement(dt.f(by.a(String.valueOf(s) + a("<<"))));
                }
                this.a(cg, (int)(n3 += a), a("^)$Hr|!e\u001e_2\u000f(M|whk\u0002"));
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
                            this.p.setBackground(dt.a(by.a(a("~/6X5p!&Cw}4"))));
                            this.p.setFont(dt.h(by.a(a("~/6X5f#=X5t)+X"))));
                            if (q) {
                                continue;
                            }
                            break;
                        }
                        final q q2 = new q();
                        q2.a(dt.a(by.a(a("~/6X5f#=X5q))Ci"))));
                        q2.b(dt.a(by.a(a("~/6X5f#=X5\u007f)0_~v)2Bx}**^"))));
                        q2.c(dt.a(by.a(a("~/6X5f#=X5\u007f)0_~}0 ^x}**^"))));
                        ((m)this.p.a()).a(q2);
                        this.p.addItemListener(new cn(this));
                        final o a2 = this.a();
                        ((com.easypano.tw.c.o)a2.a()).a(dt.a(by.a(a("~/6X5p)7H~`%*@t`"))));
                        a2.setBounds(bounds);
                        a2.a((Component)this.p);
                        this.w.addElement(a2);
                        this.x.addElement(dt.f(by.a(a("~/6X5h"))));
                    }
                }
                this.a(cg, (int)(n3 += a), a("^)$Hr|!eot\u007f$*Ntjhk\u0002"));
                bounds = dt.b(by.a(a("q$'\u0002y}3+Hh")));
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
            this.q.g().setFont(dt.h(by.a(a("q$'\u0002ow>1\u0002}}(1"))));
            this.q.f().setFont(dt.h(by.a(a("q$'\u0002ow>1\u0002}}(1"))));
            final Color a3 = dt.a(by.a(a("q$'\u0002yu%*@t`")));
            this.q.setBackground(a3);
            this.q.g().setBackground(a3);
            this.q.e().a(new Dimension(20, 20));
            final b b = new b(this.q.e());
            b.g(a3);
            b.d((Image)this.a.d.a(by.a(a("q$'\u0002yf(kEvu")), 1));
            Image image3;
            Image image2;
            final Image image = image2 = (image3 = (Image)this.a.d.a(by.a(a("q$'\u0002yf(kAtg5 Hte(,A|")), 1));
            cm cm = null;
            Label_1379: {
                if (!q) {
                    if (image != null) {
                        b.e(image2);
                    }
                    cm = this;
                    if (q) {
                        break Label_1379;
                    }
                    image3 = (image2 = (Image)this.a.d.a(by.a(a("q$'\u0002yf(kAtg5 Cmw4,A|")), 1));
                }
                if (image3 != null) {
                    b.f(image2);
                }
                this.q.e().a(b);
                cm = this;
            }
            cm.q.e().b(false);
            final q q3 = new q();
            q3.a(dt.a(by.a(a("q$'\u0002ow>1\u0002x}**^"))));
            final Color a4 = dt.a(by.a(a("q$'\u0002ow>1\u0002v}36I\u007f}1+Ot~)7")));
            q3.b(a4);
            q3.c(dt.a(by.a(a("q$'\u0002ow>1\u0002v}36Itd#7Ot~)7"))));
            ((c)this.q.f().a()).d(a4);
            ((m)this.q.g().a()).a(q3);
            final Color a5 = dt.a(by.a(a("q$'\u0002y}4!Iiq))Ci")));
            ((com.easypano.tw.c.l)this.q.a()).a(a5);
            ((com.easypano.tw.c.o)this.q.i().a()).a(a5);
            final dd e2 = this.q.g().e();
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
                this.q.a(new co(this));
                this.w.addElement(this.q);
                if (q) {
                    continue;
                }
                break;
            }
            this.x.addElement(dt.f(by.a(a("q$'\u0002a"))));
        }
        double n10;
        this.a(cg, (int)(n10 = n3 + a), a("^)$Hr|!ennf2*B5<h"));
        int n11 = dt.e(by.a(a("p31Xt|h+Yv")));
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
                    s = a("p2+") + n12;
                    bounds2 = dt.b(by.a(String.valueOf(s) + a("<$*Yuv5")));
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
                                        if (!by.a(String.valueOf(s) + a("<51Uww")).equalsIgnoreCase(a("a2$Xrq"))) {
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
                            final boolean equalsIgnoreCase = by.a(String.valueOf(s) + a("<2 To<')E||")).equalsIgnoreCase(a("`/\"Do"));
                            Label_1959: {
                                if (!q) {
                                    if (equalsIgnoreCase) {
                                        a6.b(14);
                                        if (!q) {
                                            break Label_1959;
                                        }
                                    }
                                    by.a(String.valueOf(s) + a("<2 To<')E||")).equalsIgnoreCase(a("~##X"));
                                }
                                if (equalsIgnoreCase) {
                                    a6.b(13);
                                    if (!q) {
                                        break Label_1959;
                                    }
                                }
                                a6.b(15);
                            }
                            g2.setFont(dt.h(by.a(String.valueOf(s) + a("<2 To< *Bo"))));
                            g2.e().a(by.a(String.valueOf(s) + a("<2 To")));
                            a6.d(dt.a(by.a(String.valueOf(s) + a("<2 To<%*@t`"))));
                            g2.a(by.a(String.valueOf(s) + a("<.,Bo<2 To")), dt.a(by.a(String.valueOf(s) + a("<.,Bo<$\"Ot~)7"))), dt.a(by.a(String.valueOf(s) + a("<2 To<%*@t`"))));
                            a6.d((Image)this.a.d.a(by.a(String.valueOf(s) + a("</(K")), 1));
                            Image image6;
                            Image image5;
                            final Image image4 = image5 = (image6 = (Image)this.a.d.a(by.a(String.valueOf(s) + a("<+*Yhw)3Ii{+\"")), 1));
                            if (!q) {
                                if (image4 != null) {
                                    a6.f(image5);
                                }
                                image6 = (image5 = (Image)this.a.d.a(by.a(String.valueOf(s) + a("<+*Yhw\"*[u{+\"")), 1));
                            }
                            if (image6 != null) {
                                a6.e(image5);
                            }
                            Object o;
                            final String s2 = (String)(o = by.a(String.valueOf(s) + a("<2<\\~")));
                            Label_2716: {
                                Label_2709: {
                                    if (!q) {
                                        if (!s2.equalsIgnoreCase(a("f)\"Kww"))) {
                                            break Label_2709;
                                        }
                                        a6.d(32);
                                        g2.e().b(by.a(String.valueOf(s) + a("<5 @~q2 How>1")));
                                        a6.a(dt.a(by.a(String.valueOf(s) + a("<2 To<%*@t`"))));
                                        g2.b(by.a(String.valueOf(s) + a("<.,Bo<5 @~q2 How>1")), dt.a(by.a(String.valueOf(s) + a("<.,Bo<$\"Ot~)7"))), dt.a(by.a(String.valueOf(s) + a("<2 To<%*@t`"))));
                                        a6.a((Image)this.a.d.a(by.a(String.valueOf(s) + a("<5 @~q2 Hr\u007f!")), 1));
                                        o = this.a.d.a(by.a(String.valueOf(s) + a("<5 @~q2 Hv}36Itd#7Evu")), 1);
                                    }
                                    Image image9;
                                    Image image8;
                                    final Image image7 = image8 = (image9 = (Image)o);
                                    if (!q) {
                                        if (image7 != null) {
                                            a6.c(image8);
                                        }
                                        image9 = (image8 = (Image)this.a.d.a(by.a(String.valueOf(s) + a("<5 @~q2 Hv}36I\u007f}1+Evu")), 1));
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
                            final String a7 = by.a(String.valueOf(s) + a("<'&Xr}("));
                            g2.a(new cq(this, a7));
                            final int startsWith = a7.startsWith(a("b*$Uv}0,I")) ? 1 : 0;
                            Label_2877: {
                                Vector<String> v = null;
                                Label_2868: {
                                    Label_2843: {
                                        if (!q) {
                                            if (startsWith == 0) {
                                                final boolean startsWith2 = a7.startsWith(a("b*$Uks2-"));
                                                if (q) {
                                                    break Label_2843;
                                                }
                                                if (!startsWith2) {
                                                    break Label_2877;
                                                }
                                            }
                                            g2.e().e(false);
                                            dt.a(a7, vector);
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
                                v.addElement(a("?w"));
                            }
                            this.w.addElement(g2);
                            this.x.addElement(dt.f(by.a(String.valueOf(s) + a("<<"))));
                        }
                        this.a(cg, (int)(n10 += a), a("^)$Hr|!ennf2*B5<h"));
                    }
                    ++n12;
                }
                if (n12 < n11) {
                    continue;
                }
                break;
            }
            this.a(cg, (int)(n10 += a), a("^)$Hr|!exsg+'Bz{*k\u00025"));
            bounds2 = dt.b(by.a(a("f$kNtg(!_")));
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
                            (this.r = new p()).setBackground(dt.a(by.a(a("f$kN|q))Ci"))));
                            this.r.a(dt.a(by.a(a("f$k_~~#&Xy}4!Iiq))Ci"))));
                            this.r.b(dt.a(by.a(a("f$kAtg5 Cmw4'Civ#7Ot~)7"))));
                            this.r.c(dt.a(by.a(a("f$kAtg5 Hte('Civ#7Ot~)7"))));
                            if (q) {
                                break Label_3188;
                            }
                            b2 = by.a(a("f$kAtv#")).equalsIgnoreCase(a("\u007f3)Xr~/+I"));
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
                this.r.d(dt.g(by.a(a("f$k_s}1+Mvw"))));
                this.r.d(dt.a(by.a(a("f$k@zp#)Ot~)7"))));
                this.r.b(dt.c(by.a(a("f$kBz{*6Eaw"))));
                int n15 = 0;
                while (true) {
                    Label_3364: {
                        if (!q) {
                            break Label_3364;
                        }
                        this.r.a(new i((Image)this.a.d.a(this.l.a(n15).l, 1), this.l.a(n15).e));
                        this.a(cg, (int)(n10 += a), a("^)$Hr|!exsg+'Bz{*k\u00025"));
                        ++n15;
                    }
                    if (n15 < this.l.a()) {
                        continue;
                    }
                    break;
                }
                this.r.a(new cp(this));
                this.r.doLayout();
                final o a8 = this.a();
                ((com.easypano.tw.c.o)a8.a()).a(dt.a(by.a(a("f$kNt`\" ^x}**^"))));
                a8.a((Component)this.r);
                a8.setBounds(bounds2);
                this.w.addElement(a8);
                this.x.addElement(dt.f(by.a(a("f$kV"))));
            }
            this.a(cg, (int)(n10 += a), a("^)$Hr|!em|w(1eut)k\u00025"));
            bounds2 = dt.b(by.a(a("s! Bo{(#C5p)0B\u007fa")));
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
                                if (by.a(a("s! Bo{(#C5~'<Cnf")).equalsIgnoreCase(a("D#7Xrq')"))) {
                                    f.b(41);
                                    if (!q) {
                                        break Label_3594;
                                    }
                                }
                            }
                            f2.b(42);
                        }
                        f.setName(by.a(a("s! Bo{(#C5|'(I")));
                        f.b(by.a(a("s! Bo{(#C5b.*B~")));
                        f.c(by.a(a("s! Bo{(#C5t'=")));
                        final String a9 = by.a(a("s! Bo{(#C5\u007f',@"));
                        f.e(a9);
                        f.b(new cq(this, a("}6 Bn`*m") + a9 + ")"));
                        final String a10 = by.a(a("s! Bo{(#C5e#'_rf#"));
                        f.d(a10);
                        f.a(new cq(this, a("}6 Bn`*m") + a10 + a(">f\u001aNws(.\u0005")));
                        f.a((Image)this.a.d.a(by.a(a("s! Bo{(#C5{+\"")), 1));
                        this.w.addElement(f);
                        this.x.addElement(dt.f(by.a(a("s! Bo{(#C5h"))));
                    }
                }
                this.a(cg, (int)(n10 += a), a("^)$Hr|!ex~j2e`r|-k\u00025"));
                n11 = dt.e(by.a(a("f#=Xw{(.\u0002ug+")));
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
                    s = a("f*") + n16;
                    rectangle2 = dt.b(by.a(String.valueOf(s) + a("<$*Yuv5")));
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
                            final com.easypano.tw.c.i i = new com.easypano.tw.c.i(g3);
                            g3.a(i);
                            g3.setBounds(rectangle2);
                            g3.e().a(by.a(String.valueOf(s) + a("<2 To")));
                            final boolean equalsIgnoreCase2 = by.a(String.valueOf(s) + a("<2 To<')E||")).equalsIgnoreCase(a("q#+X~`"));
                            Label_4121: {
                                if (!q) {
                                    if (equalsIgnoreCase2) {
                                        i.b(15);
                                        if (!q) {
                                            break Label_4121;
                                        }
                                    }
                                    by.a(String.valueOf(s) + a("<2 To<')E||")).equalsIgnoreCase(a("`/\"Do"));
                                }
                                if (equalsIgnoreCase2) {
                                    i.b(14);
                                    if (!q) {
                                        break Label_4121;
                                    }
                                }
                                i.b(13);
                            }
                            g3.setFont(dt.h(by.a(String.valueOf(s) + a("<2 To< *Bo"))));
                            i.d(dt.a(by.a(String.valueOf(s) + a("<2 To<%*@t`"))));
                            i.f(dt.a(by.a(String.valueOf(s) + a("<2 To<+*Yhw)3Iiq))Ci"))));
                            i.e(dt.a(by.a(String.valueOf(s) + a("<2 To<+*Yhw\"*[uq))Ci"))));
                            final boolean equalsIgnoreCase3 = by.a(String.valueOf(s) + a("<51Uww")).equalsIgnoreCase(a("\\#3IiG(!Ii~/+I"));
                            Label_4378: {
                                if (!q) {
                                    if (equalsIgnoreCase3) {
                                        i.f(3);
                                        if (!q) {
                                            break Label_4378;
                                        }
                                    }
                                    by.a(String.valueOf(s) + a("<51Uww")).equalsIgnoreCase(a("Z)3IiG(!Ii~/+I"));
                                }
                                if (equalsIgnoreCase3) {
                                    i.f(1);
                                    if (!q) {
                                        break Label_4378;
                                    }
                                }
                                i.f(2);
                            }
                            g3.a(new cq(this, by.a(String.valueOf(s) + a("<'&Xr}("))));
                            this.w.addElement(g3);
                            this.x.addElement(dt.f(by.a(String.valueOf(s) + a("<<"))));
                        }
                    }
                    ++n16;
                }
                if (n16 < n11) {
                    continue;
                }
                break;
            }
            this.a(cg, (int)(n10 += a), a("^)$Hr|!e\u007fxw( \f_w5&^rb2,Cu<hk"));
            rectangle2 = dt.b(by.a(a("a% B~v#6\u0002y}3+Hh")));
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
                        this.s.setFont(dt.h(by.a(a("a% B~v#6\u0002ow>1\u0002}}(1"))));
                        c.d(dt.a(by.a(a("a% B~v#6\u0002ow>1\u0002x}**^"))));
                        final o a11 = this.a();
                        ((com.easypano.tw.c.o)a11.a()).a((Color)null);
                        a11.a(false);
                        a11.a((Component)this.s);
                        a11.setBounds(rectangle2);
                        this.w.addElement(a11);
                        this.x.addElement(dt.f(by.a(a("a% B~v#6\u0002a"))));
                    }
                }
                this.a(cg, (int)(n10 += a), a("^)$Hr|!e\u007fos20_ys4k\u00025"));
                rectangle2 = dt.b(by.a(a("a2$Xna$$^5p)0B\u007fa")));
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
                        this.t.setFont(dt.h(by.a(a("a2$Xna$$^5f#=X5t)+X"))));
                        c2.d(dt.a(by.a(a("a2$Xna$$^5f#=X5q))Ci"))));
                        this.w.addElement(this.t);
                        this.x.addElement(dt.f(by.a(a("a2$Xna$$^5h"))));
                    }
                }
                this.a(cg, (int)(n10 += a), a("^)$Hr|!e`zp#)\u00025<"));
                n11 = dt.e(by.a(a("~''Iw<(0A")));
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
        cm cm2 = null;
        String string;
        da da;
        int n30;
        String string2;
        Rectangle b4;
        g g5;
        a a12;
        boolean equalsIgnoreCase5;
        Image image15;
        Image image14;
        Image image13;
        cm cm3 = null;
        Label_5774_Outer:Label_6214_Outer:Label_6753_Outer:
        while (true) {
            while (true) {
                Label_5284: {
                    if (!q) {
                        break Label_5284;
                    }
                    s = a("~$)") + n20;
                    rectangle3 = dt.b(by.a(String.valueOf(s) + a("<$*Yuv5")));
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
                            g4.e().a(by.a(String.valueOf(s) + a("<2 To")));
                            equalsIgnoreCase4 = by.a(String.valueOf(s) + a("<2 To<')E||")).equalsIgnoreCase(a("`/\"Do"));
                            Label_5165: {
                                if (!q) {
                                    if (equalsIgnoreCase4) {
                                        c3.b(14);
                                        if (!q) {
                                            break Label_5165;
                                        }
                                    }
                                    by.a(String.valueOf(s) + a("<2 To<')E||")).equalsIgnoreCase(a("q#+X~`"));
                                }
                                if (equalsIgnoreCase4) {
                                    c3.b(15);
                                    if (!q) {
                                        break Label_5165;
                                    }
                                }
                                c3.b(13);
                            }
                            c3.d(dt.a(by.a(String.valueOf(s) + a("<2 To<%*@t`"))));
                            g4.setFont(dt.h(by.a(String.valueOf(s) + a("<2 To< *Bo"))));
                            this.w.addElement(g4);
                            this.x.addElement(dt.f(by.a(String.valueOf(s) + "z")));
                        }
                    }
                    ++n20;
                }
                if (n20 < n11) {
                    continue;
                }
                break;
            }
            this.a(cg, (int)(n10 += a), a("^)$Hr|!eazbhk\u0002"));
            rectangle3 = dt.b(by.a(a("\u007f'5\u0002y}3+Hh")));
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
                                (this.n = new l(this.k)).setBackground(dt.a(by.a(a("\u007f'5\u0002yu%*@t`"))));
                                ((r)this.n.a()).a((Image)this.a.d.a(by.a(a("\u007f'5\u0002r\u007f!")), 1));
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
                                ((com.easypano.tw.c.o)this.o.a()).a((Color)null);
                                this.o.setBounds(rectangle3);
                                this.o.a((Component)this.n);
                                this.o.a(this.k.j());
                                bp = new bp();
                                bp.setLayout(new BorderLayout());
                                bp.setBounds(rectangle3.x, rectangle3.y, rectangle3.width, rectangle3.height);
                                bp.add(this.o, a("Q#+X~`"));
                                bp.a(dt.a(by.a(a("\u007f'5\u0002y}4!Iiq))Ci"))));
                                this.w.addElement(bp);
                                this.x.addElement(dt.f(by.a(a("\u007f'5\u0002a"))));
                                n11 = dt.e(by.a(a("\u007f'5\u0002s}26\\tfh+Yvp#7")));
                                n29 = 0;
                                while (true) {
                                    while (true) {
                                        Label_6179: {
                                            if (!q) {
                                                break Label_6179;
                                            }
                                            s = a("\u007f'5\u0002sa") + n29;
                                            rectangle3 = (b3 = dt.b(by.a(String.valueOf(s) + a("<$*Yuv5"))));
                                            b3.x += n25;
                                            rectangle4 = rectangle3;
                                            rectangle4.y += n26;
                                            if (!q) {
                                                if (rectangle3.width > 0 && rectangle3.height > 0) {
                                                    h = new h();
                                                    h.setBounds(rectangle3);
                                                    ((com.easypano.tw.c.h)h.a()).d((Image)this.a.d.a(by.a(String.valueOf(s) + a("</(K")), 1));
                                                    image10 = (image11 = (image12 = (Image)this.a.d.a(by.a(String.valueOf(s) + a("<+*Yhw\"*[u{+\"")), 1)));
                                                    Label_6149: {
                                                        if (!q) {
                                                            if (image10 != null) {
                                                                ((com.easypano.tw.c.h)h.a()).e(image11);
                                                            }
                                                            cm2 = this;
                                                            if (q) {
                                                                break Label_6149;
                                                            }
                                                            image12 = (image11 = (Image)this.a.d.a(by.a(String.valueOf(s) + a("<+*Yhw)3Ii{+\"")), 1));
                                                        }
                                                        if (image12 != null) {
                                                            ((com.easypano.tw.c.h)h.a()).f(image11);
                                                        }
                                                        h.setForeground(dt.a(by.a(String.valueOf(s) + a("<%*@t`"))));
                                                        h.a(by.a(String.valueOf(s) + a("<.,Bo<2 To")), dt.a(by.a(String.valueOf(s) + a("<.,Bo<$\""))));
                                                        h.a(this.k.j());
                                                        h.a(new cq(this, by.a(String.valueOf(s) + a("<'&Xr}("))));
                                                        cm2 = this;
                                                    }
                                                    cm2.n.add(h);
                                                }
                                                this.a(cg, (int)(n10 += a), a("^)$Hr|!eazbhk\u0002"));
                                            }
                                            ++n29;
                                        }
                                        if (n29 < n11) {
                                            continue Label_5774_Outer;
                                        }
                                        break;
                                    }
                                    n11 = dt.e(by.a(a("\u007f'5\u0002x}+5Mhah+Yvp#7")));
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
                                        string = a("\u007f'5\u0002xb") + n29;
                                        da = new da();
                                        da.b = dt.e(by.a(String.valueOf(string) + a("<5&Iuw")));
                                        da.a = dt.d(by.a(String.valueOf(string) + a("<**O")));
                                        da.f = dt.a(by.a(String.valueOf(string) + a("<$*^\u007fw4&Cw}4")));
                                        da.c = (Image)this.a.d.a(by.a(String.valueOf(string) + a("</(K")), 1);
                                        da.d = dt.e(by.a(String.valueOf(string) + a("<\",A"))) * 256 / 100;
                                        if (!q) {
                                            if (by.a() == 1) {
                                                da.d = 50;
                                            }
                                            da.e = dt.e(by.a(String.valueOf(string) + a("</+Eos(\"@~")));
                                            ((r)this.n.a()).a(da);
                                            this.a(cg, (int)(n10 += a), a("^)$Hr|!eazbhk\u0002"));
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
                        this.a(cg, (int)(n10 += a), a("^)$Hr|!e\u007fxw( \fM{#2Ii<hk"));
                        rectangle3 = dt.b(by.a(a("d/ [~`h'Cn|\"6")));
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
                    (this.m = new bu(this.k)).setBounds(rectangle3);
                    this.m.setBackground(dt.a(by.a(a("d/ [~`h'Kx}**^"))));
                    this.m.b((Image)this.a.d.a(by.a(a("d/ [~`h#^z\u007f#")), 1));
                    this.m.b(dt.e(by.a(a("d/ [~`h4Yz~/1U"), "1")));
                    this.w.addElement(this.m);
                    this.x.addElement(dt.f(by.a(a("d/ [~`h?"))));
                    n11 = dt.e(by.a(a("d/ [~`h-Coa6*X5|3(N~`")));
                    n23 = 0;
                }
                n30 = n23;
                while (true) {
                    Label_7488: {
                        if (!q) {
                            break Label_7488;
                        }
                        string2 = a("d/ [~`h-_") + n30;
                        b4 = dt.b(by.a(String.valueOf(string2) + a("<$*Yuv5")));
                        if (!q) {
                            if (b4.width > 0 && b4.height > 0) {
                                g5 = new g();
                                a12 = (a)g5.a();
                                Label_6912: {
                                    Label_6905: {
                                        if (!q) {
                                            if (!by.a(String.valueOf(string2) + a("<51Uww")).equalsIgnoreCase(a("a2$Xrq"))) {
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
                                g5.e().a(by.a(String.valueOf(string2) + a("<2 To")));
                                equalsIgnoreCase5 = by.a(String.valueOf(string2) + a("<2 To<')E||")).equalsIgnoreCase(a("`/\"Do"));
                                Label_7064: {
                                    if (!q) {
                                        if (equalsIgnoreCase5) {
                                            a12.b(14);
                                            if (!q) {
                                                break Label_7064;
                                            }
                                        }
                                        by.a(String.valueOf(string2) + a("<2 To<')E||")).equalsIgnoreCase(a("~##X"));
                                    }
                                    if (equalsIgnoreCase5) {
                                        a12.b(13);
                                        if (!q) {
                                            break Label_7064;
                                        }
                                    }
                                    a12.b(15);
                                }
                                g5.setFont(dt.h(by.a(String.valueOf(string2) + a("<2 To< *Bo"))));
                                a12.d(dt.a(by.a(String.valueOf(string2) + a("<2 To<%*@t`"))));
                                g5.a(by.a(String.valueOf(string2) + a("<.,Bo<2 To")), dt.a(by.a(String.valueOf(string2) + a("<.,Bo<$\"Ot~)7"))), dt.a(by.a(String.valueOf(string2) + a("<2 To<%*@t`"))));
                                a12.d((Image)this.a.d.a(by.a(String.valueOf(string2) + a("</(K")), 1));
                                image13 = (image14 = (image15 = (Image)this.a.d.a(by.a(String.valueOf(string2) + a("<+*Yhw)3Ii{+\"")), 1)));
                                Label_7458: {
                                    if (!q) {
                                        if (image13 != null) {
                                            a12.f(image14);
                                        }
                                        cm3 = this;
                                        if (q) {
                                            break Label_7458;
                                        }
                                        image15 = (image14 = (Image)this.a.d.a(by.a(String.valueOf(string2) + a("<+*Yhw\"*[u{+\"")), 1));
                                    }
                                    if (image15 != null) {
                                        a12.e(image14);
                                    }
                                    g5.a(this.k.j());
                                    g5.setBounds(b4);
                                    g5.a(new cq(this, by.a(String.valueOf(string2) + a("<'&Xr}("))));
                                    cm3 = this;
                                }
                                cm3.m.add(g5);
                            }
                            this.a(cg, (int)(n10 += a), a("^)$Hr|!e\u007fxw( \fM{#2Ii<hk"));
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
    
    private void a(final cg cg, int n, final String s) {
        final int n2 = n;
        if (com.easypano.tw.g.q || n2 > 90) {
            n = n2;
        }
        cg.a(n, s);
    }
    
    private double a(final by by) {
        return 50.0 / (14 + dt.e(by.a(a("\u007f'5\u0002s}26\\tfh+Yvp#7"))) + dt.e(by.a(a("\u007f'5\u0002x}+5Mhah+Yvp#7"))) + dt.e(by.a(a("d/ [~`h-Coa6*X5|3(N~`"))) + dt.e(by.a(a(" \",Azu#kBn\u007f"))) + dt.e(by.a(a("p31Xt|h+Yv"))) + dt.e(by.a(a("a% B~<(0A"))));
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
    
    public bu d() {
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
    
    static TWViewer a(final cm cm) {
        return cm.k;
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
                            c2 = '\u0012';
                            break;
                        }
                        case 1: {
                            c2 = 'F';
                            break;
                        }
                        case 2: {
                            c2 = 'E';
                            break;
                        }
                        case 3: {
                            c2 = ',';
                            break;
                        }
                        default: {
                            c2 = '\u001b';
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
