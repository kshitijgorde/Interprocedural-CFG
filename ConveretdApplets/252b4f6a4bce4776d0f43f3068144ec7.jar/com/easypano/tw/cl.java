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
        final int q = com.easypano.tw.h.q;
        final double n = 40.0;
        final double a = this.a(bx);
        String s = "";
        final Vector<String> vector = new Vector<String>();
        this.b = (Image)this.a.d.a(bx.a(a("\u0019\u0000vG\u0013\b\fvDZ")), 1);
        this.a(cf, (int)n, a("&\u0004~MT\u0004\f?aR\u0018\u0002eFS\u001e\ns\tn\t\u0019pEQ\b\nm\u0007\u0013D"));
        this.f = ds.a(bx.a(a("\u0002\u0018}HOD\txJR\u0006\u0004m")));
        this.c = (Image)this.a.d.a(bx.a(a("\u0002\u0018}HOD\t~[\u0013\u0003\u0006x")), 1);
        this.d = (Image)this.a.d.a(bx.a(a("\u0002\u0018}HOD\t~[\u0013\u0007\u0004jZX\u0005\u001dz[T\u0007\f")), 1);
        this.e = (Image)this.a.d.a(bx.a(a("\u0002\u0018}HOD\t~[\u0013\u0007\u0004jZX\u000e\u0004hGT\u0007\f")), 1);
        final double n2;
        this.a(cf, (int)(n2 = n + a), a("&\u0004~MT\u0004\f?\u007fX\u0018\u001fvJ\\\u0006KLJO\u0005\u0007sK\\\u0018E1\u0007"));
        this.j = ds.a(bx.a(a("\u001c\u0018}HOD\txJR\u0006\u0004m")));
        this.g = (Image)this.a.d.a(bx.a(a("\u001c\u0018}HOD\t~[\u0013\u0003\u0006x")), 1);
        this.h = (Image)this.a.d.a(bx.a(a("\u001c\u0018}HOD\t~[\u0013\u0007\u0004jZX\u0005\u001dz[T\u0007\f")), 1);
        this.i = (Image)this.a.d.a(bx.a(a("\u001c\u0018}HOD\t~[\u0013\u0007\u0004jZX\u000e\u0004hGT\u0007\f")), 1);
        double n3;
        this.a(cf, (int)(n3 = n2 + a), a("&\u0004~MT\u0004\f?\u001byJ\"rHZ\u000fE1\u0007"));
        final int e = ds.e(bx.a(a("X\u000fvD\\\r\u000e1GH\u0007")));
        int n4 = 0;
        Rectangle bounds;
        int n6;
        int n5 = 0;
        while (true) {
            Label_0688: {
                if (n4 >= e) {
                    this.a(cf, (int)n3, a("&\u0004~MT\u0004\f?eT\u0019\tpQ\u0013DE"));
                    bounds = ds.b(bx.a(a("\u0006\u0002l]\u0013\b\u0004jGY\u0019")));
                    final int width;
                    n5 = (width = (n6 = bounds.width));
                    if (q == 0) {
                        break;
                    }
                }
                else {
                    s = a("X\u000f") + n4;
                    bounds = ds.b(bx.a(String.valueOf(s) + a("D\tp\\S\u000e\u0018")));
                    if (q != 0) {
                        break Label_0688;
                    }
                    final int width2 = bounds.width;
                }
                if (n5 > 0 && bounds.height > 0) {
                    final h h = new h();
                    final d d = new d(h);
                    h.setBounds(bounds);
                    d.f(31);
                    Image image = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("D\u0002rN")), 1);
                    if (q == 0) {
                        Label_0569: {
                            if (!bx.a(String.valueOf(s) + a("D\u0006pMX")).equalsIgnoreCase(a("\u0005\u0019vNT\u0004\ns"))) {
                                final Image image2 = image;
                                if (q == 0) {
                                    if (image2 == null) {
                                        break Label_0569;
                                    }
                                    final Image scaledInstance;
                                    image = (scaledInstance = image.getScaledInstance(bounds.width, bounds.height, 1));
                                }
                                ds.a(image2);
                            }
                        }
                        d.d(image);
                        h.a(d);
                        h.a(new cp(this, bx.a(String.valueOf(s) + a("D\n|]T\u0005\u0005"))));
                        this.w.addElement(h);
                    }
                    this.x.addElement(ds.f(bx.a(String.valueOf(s) + a("D\u0011"))));
                }
                this.a(cf, (int)(n3 += a), a("&\u0004~MT\u0004\f?\u001byJ\"rHZ\u000fE1\u0007"));
            }
            ++n4;
        }
        final int n7;
        Label_1077: {
            if (q == 0) {
                if (n5 > 0) {
                    n7 = (n6 = bounds.height);
                    if (q != 0) {
                        break Label_1077;
                    }
                    if (n7 > 0) {
                        this.p = new l();
                        int n8 = 0;
                        while (true) {
                            while (true) {
                                Label_0814: {
                                    if (q == 0) {
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
                            this.p.setBackground(ds.a(bx.a(a("\u0006\u0002l]\u0013\b\f|FQ\u0005\u0019"))));
                            this.p.setFont(ds.h(bx.a(a("\u0006\u0002l]\u0013\u001e\u000eg]\u0013\f\u0004q]"))));
                            if (q != 0) {
                                continue;
                            }
                            break;
                        }
                        final com.easypano.tw.d.q q2 = new com.easypano.tw.d.q();
                        q2.a(ds.a(bx.a(a("\u0006\u0002l]\u0013\u001e\u000eg]\u0013\t\u0004sFO"))));
                        q2.b(ds.a(bx.a(a("\u0006\u0002l]\u0013\u001e\u000eg]\u0013\u0007\u0004jZX\u000e\u0004hG^\u0005\u0007p["))));
                        q2.c(ds.a(bx.a(a("\u0006\u0002l]\u0013\u001e\u000eg]\u0013\u0007\u0004jZX\u0005\u001dz[^\u0005\u0007p["))));
                        ((com.easypano.tw.d.m)this.p.a()).a(q2);
                        this.p.addItemListener(new cm(this));
                        final p a2 = this.a();
                        ((o)a2.a()).a(ds.a(bx.a(a("\u0006\u0002l]\u0013\b\u0004mMX\u0018\bpER\u0018"))));
                        a2.setBounds(bounds);
                        a2.a((Component)this.p);
                        this.w.addElement(a2);
                        this.x.addElement(ds.f(bx.a(a("\u0006\u0002l]\u0013\u0010"))));
                    }
                }
                this.a(cf, (int)(n3 += a), a("&\u0004~MT\u0004\f?jR\u0007\tpKR\u0012E1\u0007"));
                bounds = ds.b(bx.a(a("\t\t}\u0007_\u0005\u001eqMN")));
                final int width;
                n6 = (width = bounds.width);
            }
        }
        Label_1652: {
            final Rectangle rectangle;
            Label_1111: {
                if (q == 0) {
                    if (n7 <= 0) {
                        break Label_1652;
                    }
                    rectangle = bounds;
                    if (q != 0) {
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
            this.q.g().setFont(ds.h(bx.a(a("\t\t}\u0007I\u000f\u0013k\u0007[\u0005\u0005k"))));
            this.q.f().setFont(ds.h(bx.a(a("\t\t}\u0007I\u000f\u0013k\u0007[\u0005\u0005k"))));
            final Color a3 = ds.a(bx.a(a("\t\t}\u0007_\r\bpER\u0018")));
            this.q.setBackground(a3);
            this.q.g().setBackground(a3);
            this.q.e().setPreferredSize(new Dimension(20, 20));
            final b b = new b(this.q.e());
            b.g(a3);
            b.d((Image)this.a.d.a(bx.a(a("\t\t}\u0007_\u001e\u00051@P\r")), 1));
            Image image5;
            Image image4;
            final Image image3 = image4 = (image5 = (Image)this.a.d.a(bx.a(a("\t\t}\u0007_\u001e\u00051DR\u001f\u0018zMR\u001d\u0005vDZ")), 1));
            cl cl = null;
            Label_1388: {
                if (q == 0) {
                    if (image3 != null) {
                        b.e(image4);
                    }
                    cl = this;
                    if (q != 0) {
                        break Label_1388;
                    }
                    image5 = (image4 = (Image)this.a.d.a(bx.a(a("\t\t}\u0007_\u001e\u00051DR\u001f\u0018zFK\u000f\u0019vDZ")), 1));
                }
                if (image5 != null) {
                    b.f(image4);
                }
                this.q.e().a(b);
                cl = this;
            }
            cl.q.e().b(false);
            final com.easypano.tw.d.q q3 = new com.easypano.tw.d.q();
            q3.a(ds.a(bx.a(a("\t\t}\u0007I\u000f\u0013k\u0007^\u0005\u0007p["))));
            final Color a4 = ds.a(bx.a(a("\t\t}\u0007I\u000f\u0013k\u0007P\u0005\u001elLY\u0005\u001cqJR\u0006\u0004m")));
            q3.b(a4);
            q3.c(ds.a(bx.a(a("\t\t}\u0007I\u000f\u0013k\u0007P\u0005\u001elLR\u001c\u000emJR\u0006\u0004m"))));
            ((c)this.q.f().a()).d(a4);
            ((com.easypano.tw.d.m)this.q.g().a()).a(q3);
            final Color a5 = ds.a(bx.a(a("\t\t}\u0007_\u0005\u0019{LO\t\u0004sFO")));
            ((com.easypano.tw.d.l)this.q.a()).a(a5);
            ((o)this.q.i().a()).a(a5);
            final dc e2 = this.q.g().e();
            int n9 = 0;
            while (true) {
                while (true) {
                    Label_1589: {
                        if (q == 0) {
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
                if (q != 0) {
                    continue;
                }
                break;
            }
            this.x.addElement(ds.f(bx.a(a("\t\t}\u0007G"))));
        }
        double n10;
        this.a(cf, (int)(n10 = n3 + a), a("&\u0004~MT\u0004\f?kH\u001e\u001fpG\u0013DE"));
        int n11 = ds.e(bx.a(a("\b\u001ek]R\u0004Eq\\P")));
        int n12 = 0;
        Rectangle bounds2;
        int n13 = 0;
        boolean b2;
        while (true) {
            while (true) {
                Label_2954: {
                    if (q == 0) {
                        break Label_2954;
                    }
                    s = a("\b\u001fq") + n12;
                    bounds2 = ds.b(bx.a(String.valueOf(s) + a("D\tp\\S\u000e\u0018")));
                    Label_2951: {
                        if (q != 0) {
                            break Label_2951;
                        }
                        final int width3 = bounds2.width;
                        if (n13 > 0 && bounds2.height > 0) {
                            final h h2 = new h();
                            final a a6 = (a)h2.a();
                            Label_1852: {
                                Label_1845: {
                                    if (q == 0) {
                                        if (!bx.a(String.valueOf(s) + a("D\u0018kPQ\u000f")).equalsIgnoreCase(a("\u0019\u001f~]T\t"))) {
                                            break Label_1845;
                                        }
                                        a6.c(22);
                                    }
                                    if (q == 0) {
                                        break Label_1852;
                                    }
                                }
                                a6.c(21);
                            }
                            final boolean equalsIgnoreCase = bx.a(String.valueOf(s) + a("D\u001fzQID\ns@Z\u0004")).equalsIgnoreCase(a("\u0018\u0002xAI"));
                            Label_1968: {
                                if (q == 0) {
                                    if (equalsIgnoreCase) {
                                        a6.b(14);
                                        if (q == 0) {
                                            break Label_1968;
                                        }
                                    }
                                    bx.a(String.valueOf(s) + a("D\u001fzQID\ns@Z\u0004")).equalsIgnoreCase(a("\u0006\u000ey]"));
                                }
                                if (equalsIgnoreCase) {
                                    a6.b(13);
                                    if (q == 0) {
                                        break Label_1968;
                                    }
                                }
                                a6.b(15);
                            }
                            h2.setFont(ds.h(bx.a(String.valueOf(s) + a("D\u001fzQID\rpGI"))));
                            h2.e().a(bx.a(String.valueOf(s) + a("D\u001fzQI")));
                            a6.d(ds.a(bx.a(String.valueOf(s) + a("D\u001fzQID\bpER\u0018"))));
                            h2.a(bx.a(String.valueOf(s) + a("D\u0003vGID\u001fzQI")), ds.a(bx.a(String.valueOf(s) + a("D\u0003vGID\txJR\u0006\u0004m"))), ds.a(bx.a(String.valueOf(s) + a("D\u001fzQID\bpER\u0018"))));
                            a6.d((Image)this.a.d.a(bx.a(String.valueOf(s) + a("D\u0002rN")), 1));
                            Image image8;
                            Image image7;
                            final Image image6 = image7 = (image8 = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("D\u0006p\\N\u000f\u0004iLO\u0003\u0006x")), 1));
                            if (q == 0) {
                                if (image6 != null) {
                                    a6.f(image7);
                                }
                                image8 = (image7 = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("D\u0006p\\N\u000f\u000fp^S\u0003\u0006x")), 1));
                            }
                            if (image8 != null) {
                                a6.e(image7);
                            }
                            Object o;
                            final String s2 = (String)(o = bx.a(String.valueOf(s) + a("D\u001ffYX")));
                            Label_2725: {
                                Label_2718: {
                                    if (q == 0) {
                                        if (!s2.equalsIgnoreCase(a("\u001e\u0004xNQ\u000f"))) {
                                            break Label_2718;
                                        }
                                        a6.d(32);
                                        h2.e().b(bx.a(String.valueOf(s) + a("D\u0018zEX\t\u001fzMI\u000f\u0013k")));
                                        a6.a(ds.a(bx.a(String.valueOf(s) + a("D\u001fzQID\bpER\u0018"))));
                                        h2.b(bx.a(String.valueOf(s) + a("D\u0003vGID\u0018zEX\t\u001fzMI\u000f\u0013k")), ds.a(bx.a(String.valueOf(s) + a("D\u0003vGID\txJR\u0006\u0004m"))), ds.a(bx.a(String.valueOf(s) + a("D\u001fzQID\bpER\u0018"))));
                                        a6.a((Image)this.a.d.a(bx.a(String.valueOf(s) + a("D\u0018zEX\t\u001fzMT\u0007\f")), 1));
                                        o = this.a.d.a(bx.a(String.valueOf(s) + a("D\u0018zEX\t\u001fzMP\u0005\u001elLR\u001c\u000em@P\r")), 1);
                                    }
                                    Image image11;
                                    Image image10;
                                    final Image image9 = image10 = (image11 = (Image)o);
                                    if (q == 0) {
                                        if (image9 != null) {
                                            a6.c(image10);
                                        }
                                        image11 = (image10 = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("D\u0018zEX\t\u001fzMP\u0005\u001elLY\u0005\u001cq@P\r")), 1));
                                    }
                                    if (image11 == null) {
                                        break Label_2725;
                                    }
                                    a6.b(image10);
                                    if (q == 0) {
                                        break Label_2725;
                                    }
                                }
                                a6.d(31);
                            }
                            h2.setBounds(bounds2);
                            final String a7 = bx.a(String.valueOf(s) + a("D\n|]T\u0005\u0005"));
                            h2.a(new cp(this, a7));
                            final int startsWith = a7.startsWith(a("\u001a\u0007~PP\u0005\u001dvL")) ? 1 : 0;
                            Label_2886: {
                                Vector<String> v = null;
                                Label_2877: {
                                    Label_2852: {
                                        if (q == 0) {
                                            if (startsWith == 0) {
                                                final boolean startsWith2 = a7.startsWith(a("\u001a\u0007~PM\u000b\u001fw"));
                                                if (q != 0) {
                                                    break Label_2852;
                                                }
                                                if (!startsWith2) {
                                                    break Label_2886;
                                                }
                                            }
                                            h2.e().e(false);
                                            ds.a(a7, vector);
                                            this.u.addElement(h2);
                                            final Vector<String> vector2 = v = vector;
                                            if (q != 0) {
                                                break Label_2877;
                                            }
                                            vector2.size();
                                        }
                                    }
                                    if (startsWith > 0) {
                                        this.v.addElement(vector.elementAt(0));
                                        if (q == 0) {
                                            break Label_2886;
                                        }
                                    }
                                    v = (Vector<String>)this.v;
                                }
                                v.addElement(a("GZ"));
                            }
                            this.w.addElement(h2);
                            this.x.addElement(ds.f(bx.a(String.valueOf(s) + a("D\u0011"))));
                        }
                        this.a(cf, (int)(n10 += a), a("&\u0004~MT\u0004\f?kH\u001e\u001fpG\u0013DE"));
                    }
                    ++n12;
                }
                if (n12 < n11) {
                    continue;
                }
                break;
            }
            this.a(cf, (int)(n10 += a), a("&\u0004~MT\u0004\f?}U\u001f\u0006}G\\\u0003\u00071\u0007\u0013"));
            bounds2 = ds.b(bx.a(a("\u001e\t1KR\u001f\u0005{Z")));
            n13 = ((b2 = (bounds2.width != 0)) ? 1 : 0);
            if (q != 0) {
                continue;
            }
            break;
        }
        final int n14;
        Label_3520: {
            Label_3482: {
                Label_3228: {
                    Label_3197: {
                        if (q == 0) {
                            if (n13 <= 0) {
                                final int height;
                                n14 = (height = bounds2.height);
                                if (q != 0) {
                                    break Label_3520;
                                }
                                if (n14 <= 0) {
                                    break Label_3482;
                                }
                            }
                            (this.r = new q()).setBackground(ds.a(bx.a(a("\u001e\t1KZ\t\u0004sFO"))));
                            this.r.a(ds.a(bx.a(a("\u001e\t1ZX\u0006\u000e|]_\u0005\u0019{LO\t\u0004sFO"))));
                            this.r.b(ds.a(bx.a(a("\u001e\t1DR\u001f\u0018zFK\u000f\u0019}FO\u000e\u000emJR\u0006\u0004m"))));
                            this.r.c(ds.a(bx.a(a("\u001e\t1DR\u001f\u0018zMR\u001d\u0005}FO\u000e\u000emJR\u0006\u0004m"))));
                            if (q != 0) {
                                break Label_3197;
                            }
                            b2 = bx.a(a("\u001e\t1DR\u000e\u000e")).equalsIgnoreCase(a("\u0007\u001es]T\u0006\u0002qL"));
                        }
                        if (b2) {
                            this.r.b(41);
                            this.r.setPreferredSize(new Dimension(bounds2.width - com.easypano.tw.o.l.width, bounds2.height));
                            if (q == 0) {
                                break Label_3228;
                            }
                        }
                        this.r.b(42);
                    }
                    this.r.setPreferredSize(new Dimension(bounds2.width, bounds2.height - com.easypano.tw.o.l.height));
                }
                this.r.d(ds.g(bx.a(a("\u001e\t1ZU\u0005\u001cqHP\u000f"))));
                this.r.d(ds.a(bx.a(a("\u001e\t1E\\\b\u000esJR\u0006\u0004m"))));
                this.r.a(ds.c(bx.a(a("\u001e\t1G\\\u0003\u0007l@G\u000f"))));
                int n15 = 0;
                while (true) {
                    Label_3373: {
                        if (q == 0) {
                            break Label_3373;
                        }
                        this.r.a(new j((Image)this.a.d.a(this.l.a(n15).l, 1), this.l.a(n15).e));
                        this.a(cf, (int)(n10 += a), a("&\u0004~MT\u0004\f?}U\u001f\u0006}G\\\u0003\u00071\u0007\u0013"));
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
                ((o)a8.a()).a(ds.a(bx.a(a("\u001e\t1KR\u0018\u000fz[^\u0005\u0007p["))));
                a8.a((Component)this.r);
                a8.setBounds(bounds2);
                this.w.addElement(a8);
                this.x.addElement(ds.f(bx.a(a("\u001e\t1S"))));
            }
            this.a(cf, (int)(n10 += a), a("&\u0004~MT\u0004\f?hZ\u000f\u0005k`S\f\u00041\u0007\u0013"));
            bounds2 = ds.b(bx.a(a("\u000b\fzGI\u0003\u0005yF\u0013\b\u0004jGY\u0019")));
            final int width4 = bounds2.width;
        }
        final int height2;
        Label_3861: {
            if (q == 0) {
                if (n14 > 0) {
                    height2 = bounds2.height;
                    if (q != 0) {
                        break Label_3861;
                    }
                    if (height2 > 0) {
                        final g g2;
                        final g g = g2 = new g();
                        Label_3603: {
                            if (q == 0) {
                                g2.setBounds(bounds2);
                                if (bx.a(a("\u000b\fzGI\u0003\u0005yF\u0013\u0006\nfFH\u001e")).equalsIgnoreCase(a("<\u000em]T\t\ns"))) {
                                    g.b(41);
                                    if (q == 0) {
                                        break Label_3603;
                                    }
                                }
                            }
                            g2.b(42);
                        }
                        g.setName(bx.a(a("\u000b\fzGI\u0003\u0005yF\u0013\u0004\nrL")));
                        g.b(bx.a(a("\u000b\fzGI\u0003\u0005yF\u0013\u001a\u0003pGX")));
                        g.c(bx.a(a("\u000b\fzGI\u0003\u0005yF\u0013\f\ng")));
                        final String a9 = bx.a(a("\u000b\fzGI\u0003\u0005yF\u0013\u0007\nvE"));
                        g.e(a9);
                        g.b(new cp(this, a("\u0005\u001bzGH\u0018\u00077") + a9 + ")"));
                        final String a10 = bx.a(a("\u000b\fzGI\u0003\u0005yF\u0013\u001d\u000e}ZT\u001e\u000e"));
                        g.d(a10);
                        g.a(new cp(this, a("\u0005\u001bzGH\u0018\u00077") + a10 + a("FK@KQ\u000b\u0005t\u0000")));
                        g.a((Image)this.a.d.a(bx.a(a("\u000b\fzGI\u0003\u0005yF\u0013\u0003\u0006x")), 1));
                        this.w.addElement(g);
                        this.x.addElement(ds.f(bx.a(a("\u000b\fzGI\u0003\u0005yF\u0013\u0010"))));
                    }
                }
                this.a(cf, (int)(n10 += a), a("&\u0004~MT\u0004\f?}X\u0012\u001f?eT\u0004\u00001\u0007\u0013"));
                n11 = ds.e(bx.a(a("\u001e\u000eg]Q\u0003\u0005t\u0007S\u001f\u0006")));
            }
        }
        int n16 = height2;
        Rectangle rectangle2;
        int n17 = 0;
        while (true) {
            while (true) {
                Label_4478: {
                    if (q == 0) {
                        break Label_4478;
                    }
                    s = a("\u001e\u0007") + n16;
                    rectangle2 = ds.b(bx.a(String.valueOf(s) + a("D\tp\\S\u000e\u0018")));
                    final int width5;
                    int height3 = width5 = rectangle2.width;
                    Label_4475: {
                        if (q == 0) {
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
                            h3.e().a(bx.a(String.valueOf(s) + a("D\u001fzQI")));
                            final boolean equalsIgnoreCase2 = bx.a(String.valueOf(s) + a("D\u001fzQID\ns@Z\u0004")).equalsIgnoreCase(a("\t\u000eq]X\u0018"));
                            Label_4130: {
                                if (q == 0) {
                                    if (equalsIgnoreCase2) {
                                        i.b(15);
                                        if (q == 0) {
                                            break Label_4130;
                                        }
                                    }
                                    bx.a(String.valueOf(s) + a("D\u001fzQID\ns@Z\u0004")).equalsIgnoreCase(a("\u0018\u0002xAI"));
                                }
                                if (equalsIgnoreCase2) {
                                    i.b(14);
                                    if (q == 0) {
                                        break Label_4130;
                                    }
                                }
                                i.b(13);
                            }
                            h3.setFont(ds.h(bx.a(String.valueOf(s) + a("D\u001fzQID\rpGI"))));
                            i.d(ds.a(bx.a(String.valueOf(s) + a("D\u001fzQID\bpER\u0018"))));
                            i.f(ds.a(bx.a(String.valueOf(s) + a("D\u001fzQID\u0006p\\N\u000f\u0004iLO\t\u0004sFO"))));
                            i.e(ds.a(bx.a(String.valueOf(s) + a("D\u001fzQID\u0006p\\N\u000f\u000fp^S\t\u0004sFO"))));
                            final boolean equalsIgnoreCase3 = bx.a(String.valueOf(s) + a("D\u0018kPQ\u000f")).equalsIgnoreCase(a("$\u000eiLO?\u0005{LO\u0006\u0002qL"));
                            Label_4387: {
                                if (q == 0) {
                                    if (equalsIgnoreCase3) {
                                        i.f(3);
                                        if (q == 0) {
                                            break Label_4387;
                                        }
                                    }
                                    bx.a(String.valueOf(s) + a("D\u0018kPQ\u000f")).equalsIgnoreCase(a("\"\u0004iLO?\u0005{LO\u0006\u0002qL"));
                                }
                                if (equalsIgnoreCase3) {
                                    i.f(1);
                                    if (q == 0) {
                                        break Label_4387;
                                    }
                                }
                                i.f(2);
                            }
                            h3.a(new cp(this, bx.a(String.valueOf(s) + a("D\n|]T\u0005\u0005"))));
                            this.w.addElement(h3);
                            this.x.addElement(ds.f(bx.a(String.valueOf(s) + a("D\u0011"))));
                        }
                    }
                    ++n16;
                }
                if (n16 < n11) {
                    continue;
                }
                break;
            }
            this.a(cf, (int)(n10 += a), a("&\u0004~MT\u0004\f?z^\u000f\u0005z\ty\u000f\u0018|[T\u001a\u001fvFSDE1"));
            rectangle2 = ds.b(bx.a(a("\u0019\bzGX\u000e\u000el\u0007_\u0005\u001eqMN")));
            int height3;
            final int n18;
            final int width6;
            n17 = (height3 = (width6 = (n18 = rectangle2.width)));
            if (q != 0) {
                continue;
            }
            break;
        }
        final int n19;
        Label_4737: {
            if (q == 0) {
                if (n17 > 0) {
                    final int n18;
                    n19 = (n18 = rectangle2.height);
                    if (q != 0) {
                        break Label_4737;
                    }
                    if (n19 > 0) {
                        this.s = new h();
                        final c c = new c(this.s, true);
                        this.s.a(c);
                        this.s.setBounds(rectangle2);
                        this.s.setFont(ds.h(bx.a(a("\u0019\bzGX\u000e\u000el\u0007I\u000f\u0013k\u0007[\u0005\u0005k"))));
                        c.d(ds.a(bx.a(a("\u0019\bzGX\u000e\u000el\u0007I\u000f\u0013k\u0007^\u0005\u0007p["))));
                        final p a11 = this.a();
                        ((o)a11.a()).a((Color)null);
                        a11.a(false);
                        a11.a((Component)this.s);
                        a11.setBounds(rectangle2);
                        this.w.addElement(a11);
                        this.x.addElement(ds.f(bx.a(a("\u0019\bzGX\u000e\u000el\u0007G"))));
                    }
                }
                this.a(cf, (int)(n10 += a), a("&\u0004~MT\u0004\f?zI\u000b\u001fjZ_\u000b\u00191\u0007\u0013"));
                rectangle2 = ds.b(bx.a(a("\u0019\u001f~]H\u0019\t~[\u0013\b\u0004jGY\u0019")));
                final int width6 = rectangle2.width;
            }
        }
        final int height4;
        Label_4904: {
            if (q == 0) {
                if (n19 > 0) {
                    height4 = rectangle2.height;
                    if (q != 0) {
                        break Label_4904;
                    }
                    if (height4 > 0) {
                        this.t = new h();
                        final c c2 = new c(this.t, false);
                        this.t.a(c2);
                        this.t.setBounds(rectangle2);
                        this.t.setFont(ds.h(bx.a(a("\u0019\u001f~]H\u0019\t~[\u0013\u001e\u000eg]\u0013\f\u0004q]"))));
                        c2.d(ds.a(bx.a(a("\u0019\u001f~]H\u0019\t~[\u0013\u001e\u000eg]\u0013\t\u0004sFO"))));
                        this.w.addElement(this.t);
                        this.x.addElement(ds.f(bx.a(a("\u0019\u001f~]H\u0019\t~[\u0013\u0010"))));
                    }
                }
                this.a(cf, (int)(n10 += a), a("&\u0004~MT\u0004\f?e\\\b\u000es\u0007\u0013D"));
                n11 = ds.e(bx.a(a("\u0006\n}LQD\u0005jD")));
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
        cz cz;
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
                    if (q == 0) {
                        break Label_5293;
                    }
                    s = a("\u0006\ts") + n20;
                    rectangle3 = ds.b(bx.a(String.valueOf(s) + a("D\tp\\S\u000e\u0018")));
                    height5 = (width7 = rectangle3.width);
                    Label_5290: {
                        if (q == 0) {
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
                            h4.e().a(bx.a(String.valueOf(s) + a("D\u001fzQI")));
                            equalsIgnoreCase4 = bx.a(String.valueOf(s) + a("D\u001fzQID\ns@Z\u0004")).equalsIgnoreCase(a("\u0018\u0002xAI"));
                            Label_5174: {
                                if (q == 0) {
                                    if (equalsIgnoreCase4) {
                                        c3.b(14);
                                        if (q == 0) {
                                            break Label_5174;
                                        }
                                    }
                                    bx.a(String.valueOf(s) + a("D\u001fzQID\ns@Z\u0004")).equalsIgnoreCase(a("\t\u000eq]X\u0018"));
                                }
                                if (equalsIgnoreCase4) {
                                    c3.b(15);
                                    if (q == 0) {
                                        break Label_5174;
                                    }
                                }
                                c3.b(13);
                            }
                            c3.d(ds.a(bx.a(String.valueOf(s) + a("D\u001fzQID\bpER\u0018"))));
                            h4.setFont(ds.h(bx.a(String.valueOf(s) + a("D\u001fzQID\rpGI"))));
                            this.w.addElement(h4);
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
            this.a(cf, (int)(n10 += a), a("&\u0004~MT\u0004\f?d\\\u001aE1\u0007"));
            rectangle3 = ds.b(bx.a(a("\u0007\no\u0007_\u0005\u001eqMN")));
            n21 = (height5 = (n22 = (height6 = (n23 = rectangle3.width))));
            if (q == 0) {
                Label_6589: {
                    if (q == 0) {
                        if (n21 > 0) {
                            n24 = (height6 = (n23 = rectangle3.height));
                            if (q != 0) {
                                break Label_6589;
                            }
                            if (n24 > 0) {
                                (this.n = new m(this.k)).setBackground(ds.a(bx.a(a("\u0007\no\u0007_\r\bpER\u0018"))));
                                ((r)this.n.a()).a((Image)this.a.d.a(bx.a(a("\u0007\no\u0007T\u0007\f")), 1));
                                preferredSize = this.n.getPreferredSize();
                                n25 = 0;
                                n26 = 0;
                                n27 = (width8 = preferredSize.width);
                                n28 = (width9 = rectangle3.width);
                                if (q == 0) {
                                    if (n27 < n28) {
                                        n25 = (rectangle3.width - preferredSize.width) / 2;
                                    }
                                    width8 = (height7 = preferredSize.height);
                                    width9 = (height8 = rectangle3.height);
                                }
                                Label_5517: {
                                    if (q == 0) {
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
                                bo.add(this.o, a(")\u000eq]X\u0018"));
                                bo.a(ds.a(bx.a(a("\u0007\no\u0007_\u0005\u0019{LO\t\u0004sFO"))));
                                this.w.addElement(bo);
                                this.x.addElement(ds.f(bx.a(a("\u0007\no\u0007G"))));
                                n11 = ds.e(bx.a(a("\u0007\no\u0007U\u0005\u001flYR\u001eEq\\P\b\u000em")));
                                n29 = 0;
                                while (true) {
                                    while (true) {
                                        Label_6188: {
                                            if (q == 0) {
                                                break Label_6188;
                                            }
                                            s = a("\u0007\no\u0007U\u0019") + n29;
                                            rectangle3 = (b3 = ds.b(bx.a(String.valueOf(s) + a("D\tp\\S\u000e\u0018"))));
                                            b3.x += n25;
                                            rectangle4 = rectangle3;
                                            rectangle4.y += n26;
                                            if (q == 0) {
                                                if (rectangle3.width > 0 && rectangle3.height > 0) {
                                                    j = new com.easypano.tw.i();
                                                    j.setBounds(rectangle3);
                                                    ((com.easypano.tw.d.h)j.a()).d((Image)this.a.d.a(bx.a(String.valueOf(s) + a("D\u0002rN")), 1));
                                                    image12 = (image13 = (image14 = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("D\u0006p\\N\u000f\u000fp^S\u0003\u0006x")), 1)));
                                                    Label_6158: {
                                                        if (q == 0) {
                                                            if (image12 != null) {
                                                                ((com.easypano.tw.d.h)j.a()).e(image13);
                                                            }
                                                            cl2 = this;
                                                            if (q != 0) {
                                                                break Label_6158;
                                                            }
                                                            image14 = (image13 = (Image)this.a.d.a(bx.a(String.valueOf(s) + a("D\u0006p\\N\u000f\u0004iLO\u0003\u0006x")), 1));
                                                        }
                                                        if (image14 != null) {
                                                            ((com.easypano.tw.d.h)j.a()).f(image13);
                                                        }
                                                        j.setForeground(ds.a(bx.a(String.valueOf(s) + a("D\bpER\u0018"))));
                                                        j.a(bx.a(String.valueOf(s) + a("D\u0003vGID\u001fzQI")), ds.a(bx.a(String.valueOf(s) + a("D\u0003vGID\tx"))));
                                                        j.a(this.k.j());
                                                        j.a(new cp(this, bx.a(String.valueOf(s) + a("D\n|]T\u0005\u0005"))));
                                                        cl2 = this;
                                                    }
                                                    cl2.n.add(j);
                                                }
                                                this.a(cf, (int)(n10 += a), a("&\u0004~MT\u0004\f?d\\\u001aE1\u0007"));
                                            }
                                            ++n29;
                                        }
                                        if (n29 < n11) {
                                            continue Label_5783_Outer;
                                        }
                                        break;
                                    }
                                    n11 = ds.e(bx.a(a("\u0007\no\u0007^\u0005\u0006oHN\u0019Eq\\P\b\u000em")));
                                    n29 = 0;
                                    if (q != 0) {
                                        continue Label_6223_Outer;
                                    }
                                    break;
                                }
                                while (true) {
                                    Label_6544: {
                                        if (q == 0) {
                                            break Label_6544;
                                        }
                                        string = a("\u0007\no\u0007^\u001a") + n29;
                                        cz = new cz();
                                        cz.b = ds.e(bx.a(String.valueOf(string) + a("D\u0018|LS\u000f")));
                                        cz.a = ds.d(bx.a(String.valueOf(string) + a("D\u0007pJ")));
                                        cz.f = ds.a(bx.a(String.valueOf(string) + a("D\tp[Y\u000f\u0019|FQ\u0005\u0019")));
                                        cz.c = (Image)this.a.d.a(bx.a(String.valueOf(string) + a("D\u0002rN")), 1);
                                        cz.d = ds.e(bx.a(String.valueOf(string) + a("D\u000fvD"))) * 256 / 100;
                                        if (q == 0) {
                                            if (bx.a() == 1) {
                                                cz.d = 50;
                                            }
                                            cz.e = ds.e(bx.a(String.valueOf(string) + a("D\u0002q@I\u000b\u0005xEX")));
                                            ((r)this.n.a()).a(cz);
                                            this.a(cf, (int)(n10 += a), a("&\u0004~MT\u0004\f?d\\\u001aE1\u0007"));
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
                        this.a(cf, (int)(n10 += a), a("&\u0004~MT\u0004\f?z^\u000f\u0005z\tk\u0003\u000ehLODE1"));
                        rectangle3 = ds.b(bx.a(a("\u001c\u0002z^X\u0018E}FH\u0004\u000fl")));
                        height6 = (n22 = (n23 = rectangle3.width));
                    }
                }
                if (q == 0) {
                    if (n24 <= 0) {
                        return;
                    }
                    n23 = (height6 = rectangle3.height);
                }
                if (q == 0) {
                    if (height6 <= 0) {
                        return;
                    }
                    (this.m = new bt(this.k)).setBounds(rectangle3);
                    this.m.setBackground(ds.a(bx.a(a("\u001c\u0002z^X\u0018E}N^\u0005\u0007p["))));
                    this.m.b((Image)this.a.d.a(bx.a(a("\u001c\u0002z^X\u0018Ey[\\\u0007\u000e")), 1));
                    this.m.b(ds.e(bx.a(a("\u001c\u0002z^X\u0018En\\\\\u0006\u0002kP"), "1")));
                    this.w.addElement(this.m);
                    this.x.addElement(ds.f(bx.a(a("\u001c\u0002z^X\u0018Ee"))));
                    n11 = ds.e(bx.a(a("\u001c\u0002z^X\u0018EwFI\u0019\u001bp]\u0013\u0004\u001erKX\u0018")));
                    n23 = 0;
                }
                n30 = n23;
                while (true) {
                    Label_7497: {
                        if (q == 0) {
                            break Label_7497;
                        }
                        string2 = a("\u001c\u0002z^X\u0018EwZ") + n30;
                        b4 = ds.b(bx.a(String.valueOf(string2) + a("D\tp\\S\u000e\u0018")));
                        if (q == 0) {
                            if (b4.width > 0 && b4.height > 0) {
                                h5 = new h();
                                a12 = (a)h5.a();
                                Label_6921: {
                                    Label_6914: {
                                        if (q == 0) {
                                            if (!bx.a(String.valueOf(string2) + a("D\u0018kPQ\u000f")).equalsIgnoreCase(a("\u0019\u001f~]T\t"))) {
                                                break Label_6914;
                                            }
                                            a12.c(22);
                                        }
                                        if (q == 0) {
                                            break Label_6921;
                                        }
                                    }
                                    a12.c(21);
                                }
                                h5.e().a(bx.a(String.valueOf(string2) + a("D\u001fzQI")));
                                equalsIgnoreCase5 = bx.a(String.valueOf(string2) + a("D\u001fzQID\ns@Z\u0004")).equalsIgnoreCase(a("\u0018\u0002xAI"));
                                Label_7073: {
                                    if (q == 0) {
                                        if (equalsIgnoreCase5) {
                                            a12.b(14);
                                            if (q == 0) {
                                                break Label_7073;
                                            }
                                        }
                                        bx.a(String.valueOf(string2) + a("D\u001fzQID\ns@Z\u0004")).equalsIgnoreCase(a("\u0006\u000ey]"));
                                    }
                                    if (equalsIgnoreCase5) {
                                        a12.b(13);
                                        if (q == 0) {
                                            break Label_7073;
                                        }
                                    }
                                    a12.b(15);
                                }
                                h5.setFont(ds.h(bx.a(String.valueOf(string2) + a("D\u001fzQID\rpGI"))));
                                a12.d(ds.a(bx.a(String.valueOf(string2) + a("D\u001fzQID\bpER\u0018"))));
                                h5.a(bx.a(String.valueOf(string2) + a("D\u0003vGID\u001fzQI")), ds.a(bx.a(String.valueOf(string2) + a("D\u0003vGID\txJR\u0006\u0004m"))), ds.a(bx.a(String.valueOf(string2) + a("D\u001fzQID\bpER\u0018"))));
                                a12.d((Image)this.a.d.a(bx.a(String.valueOf(string2) + a("D\u0002rN")), 1));
                                image15 = (image16 = (image17 = (Image)this.a.d.a(bx.a(String.valueOf(string2) + a("D\u0006p\\N\u000f\u0004iLO\u0003\u0006x")), 1)));
                                Label_7467: {
                                    if (q == 0) {
                                        if (image15 != null) {
                                            a12.f(image16);
                                        }
                                        cl3 = this;
                                        if (q != 0) {
                                            break Label_7467;
                                        }
                                        image17 = (image16 = (Image)this.a.d.a(bx.a(String.valueOf(string2) + a("D\u0006p\\N\u000f\u000fp^S\u0003\u0006x")), 1));
                                    }
                                    if (image17 != null) {
                                        a12.e(image16);
                                    }
                                    h5.a(this.k.j());
                                    h5.setBounds(b4);
                                    h5.a(new cp(this, bx.a(String.valueOf(string2) + a("D\n|]T\u0005\u0005"))));
                                    cl3 = this;
                                }
                                cl3.m.add(h5);
                            }
                            this.a(cf, (int)(n10 += a), a("&\u0004~MT\u0004\f?z^\u000f\u0005z\tk\u0003\u000ehLODE1"));
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
        if (com.easypano.tw.h.q != 0 || n2 > 90) {
            n = n2;
        }
        cf.a(n, s);
    }
    
    private double a(final bx bx) {
        return 50.0 / (14 + ds.e(bx.a(a("\u0007\no\u0007U\u0005\u001flYR\u001eEq\\P\b\u000em"))) + ds.e(bx.a(a("\u0007\no\u0007^\u0005\u0006oHN\u0019Eq\\P\b\u000em"))) + ds.e(bx.a(a("\u001c\u0002z^X\u0018EwFI\u0019\u001bp]\u0013\u0004\u001erKX\u0018"))) + ds.e(bx.a(a("X\u000fvD\\\r\u000e1GH\u0007"))) + ds.e(bx.a(a("\b\u001ek]R\u0004Eq\\P"))) + ds.e(bx.a(a("\u0019\bzGXD\u0005jD"))));
    }
    
    private p a() {
        final p p = new p();
        p.a(this.a(42), this.a(41));
        p.b(true);
        return p;
    }
    
    private com.easypano.tw.o a(final int n) {
        final int q = com.easypano.tw.h.q;
        final com.easypano.tw.o o = new com.easypano.tw.o();
        Label_0100: {
            if (q == 0) {
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
            if (q == 0) {
                if (e != null) {
                    o.b(this.e);
                }
                final Image d = this.d;
            }
            if (e == null) {
                return o;
            }
            o.c(this.d);
            if (q == 0) {
                return o;
            }
        }
        o.b(41);
        o.setBackground(this.j);
        o.a(this.g);
        final Image i = this.i;
        if (q == 0) {
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
        final int q = com.easypano.tw.h.q;
        Component[] array = new Component[0];
        final int size;
        final int n = size = this.w.size();
        if (q == 0) {
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
                    if (q == 0) {
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
            if (q != 0) {
                continue;
            }
            break;
        }
        this.a(array, array2);
        return array;
    }
    
    private void a(final Component[] array, final Integer[] array2) {
        final int q = com.easypano.tw.h.q;
        final int length = array.length;
        int n = 0;
    Label_0054_Outer:
        while (true) {
            Label_0129: {
                if (q == 0) {
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
                            if (q == 0) {
                                break Label_0072;
                            }
                            final int intValue;
                            int n4 = intValue = array2[n3];
                            Label_0069: {
                                Label_0068: {
                                    if (q != 0) {
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
                    if (q != 0) {
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
                            c2 = 'j';
                            break;
                        }
                        case 1: {
                            c2 = 'k';
                            break;
                        }
                        case 2: {
                            c2 = '\u001f';
                            break;
                        }
                        case 3: {
                            c2 = ')';
                            break;
                        }
                        default: {
                            c2 = '=';
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
