// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.awt.image.ImageProducer;
import java.awt.Toolkit;
import java.awt.image.ColorModel;
import java.awt.image.DirectColorModel;
import java.awt.Dimension;
import com.easypano.tourweaver.c.c;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.awt.Component;
import com.easypano.tourweaver.c.i;
import com.easypano.tourweaver.c.g;
import com.easypano.tourweaver.c.h;
import com.easypano.tourweaver.c.j;
import com.easypano.tourweaver.a.d;
import java.util.Vector;
import com.easypano.tourweaver.c.f;
import java.awt.Image;

public class bb implements q
{
    Image a;
    y b;
    f c;
    Vector d;
    int e;
    d f;
    double g;
    double h;
    double i;
    double j;
    int k;
    int l;
    int m;
    int n;
    int o;
    int p;
    int[] q;
    int[] r;
    int[] s;
    boolean t;
    int[] u;
    int v;
    int w;
    private static String[] z;
    
    public bb() {
        this.d = new Vector();
        this.e = 0;
        this.g = 0.0;
        this.h = 0.0;
        this.i = 0.0;
        this.j = 1.0;
        this.k = -1;
        this.l = 8000000;
        this.t = false;
    }
    
    public f a(final String s) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        String s2 = s;
        if (!i) {
            if (s == null) {
                return null;
            }
            s2 = s;
        }
        boolean b6;
        boolean equals;
        boolean b5;
        boolean b4;
        boolean b3;
        boolean b2;
        final boolean b = b2 = (b3 = (b4 = (b5 = (equals = (b6 = s2.equals(bb.z[2]))))));
        if (!i) {
            if (b) {
                return new j();
            }
            final boolean b7;
            b2 = (b7 = (b3 = (b4 = (b5 = (equals = (b6 = s.equals(bb.z[7])))))));
        }
        if (!i) {
            if (b) {
                return new j();
            }
            b3 = (b2 = (b4 = (b5 = (equals = (b6 = s.equals(bb.z[5]))))));
        }
        if (!i) {
            if (b2) {
                return new j();
            }
            b4 = (b3 = (b5 = (equals = (b6 = s.equals(bb.z[1])))));
        }
        if (!i) {
            if (b3) {
                return new j();
            }
            b5 = (b4 = (equals = (b6 = s.equals(bb.z[3]))));
        }
        if (!i) {
            if (b4) {
                return new h();
            }
            equals = (b5 = (b6 = s.equals(bb.z[0])));
        }
        if (!i) {
            if (b5) {
                return new h();
            }
            b6 = (equals = s.equals(bb.z[6]));
        }
        if (!i) {
            if (equals) {
                return new g();
            }
            b6 = s.equals(bb.z[4]);
        }
        if (b6) {
            return new i();
        }
        return null;
    }
    
    public void a(final com.easypano.tourweaver.f.h h, final com.easypano.tourweaver.f.d d) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        if (!(h instanceof y)) {
            return;
        }
        bb bb = this;
        final double n;
        final double n4;
        final double n5;
        Label_0224: {
            Label_0215: {
                if (!i) {
                    if (this.b != h) {
                        this.c();
                        this.a((y)h, d);
                        final Enumeration j = this.b.i();
                        d.b(true);
                        while (j.hasMoreElements()) {
                            final e e = j.nextElement();
                            final Rectangle bounds = e.getBounds();
                            e.setBounds(-100, -100, bounds.width, bounds.height);
                            d.a(e);
                            if (i) {
                                break Label_0215;
                            }
                            if (i) {
                                break;
                            }
                        }
                    }
                    bb = this;
                }
                double n3;
                double n2;
                n = (n2 = (n3 = dcmpl(bb.g, d.h())));
                if (i) {
                    break Label_0224;
                }
                if (n == 0) {
                    n4 = (n3 = dcmpl(this.h, d.i()));
                    if (i) {
                        break Label_0224;
                    }
                    if (n4 == 0) {
                        n5 = dcmpl(this.i, d.j());
                        if (i) {
                            break Label_0224;
                        }
                        if (n5 == 0) {
                            double n7;
                            final double n6 = n7 = (n2 = (n3 = this.d.size()));
                            if (i) {
                                break Label_0224;
                            }
                            if (n6 == 0) {
                                final double n8 = n7 = (n2 = (n3 = (d.D() ? 1 : 0)));
                                if (i) {
                                    break Label_0224;
                                }
                                if (n8 == 0) {
                                    final double n9 = dcmpl(this.j, (double)d.b());
                                    if (i) {
                                        break Label_0224;
                                    }
                                    if (n9 == 0) {
                                        return;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            double n3 = dcmpl(this.g, d.h());
        }
        bb bb5 = null;
        Label_0895: {
            Label_0894: {
                com.easypano.tourweaver.f.d d2 = null;
                Label_0606: {
                    bb bb2 = null;
                    Label_0567: {
                        Label_0566: {
                            com.easypano.tourweaver.f.d d3 = null;
                            Label_0547: {
                                Label_0476: {
                                    Label_0370: {
                                        final int l;
                                        Label_0348: {
                                            Label_0339: {
                                                if (!i) {
                                                    if (n == 0) {
                                                        final double n10 = dcmpl(this.h, d.i());
                                                        if (i) {
                                                            break Label_0339;
                                                        }
                                                        if (n10 == 0) {
                                                            final double n11 = dcmpl(this.i, d.j());
                                                            if (i) {
                                                                break Label_0339;
                                                            }
                                                            if (n11 == 0) {
                                                                double n2;
                                                                final int n12 = (int)(n2 = this.b.k() * this.b.l());
                                                                l = this.l;
                                                                if (i) {
                                                                    break Label_0348;
                                                                }
                                                                if (n12 > l) {
                                                                    final double n3;
                                                                    final double n13 = n2 = (n3 = (d.D() ? 1 : 0));
                                                                    if (i) {
                                                                        break Label_0339;
                                                                    }
                                                                    if (n13 == 0) {
                                                                        final double n14 = dcmpl(this.j, (double)d.b());
                                                                        if (i) {
                                                                            break Label_0339;
                                                                        }
                                                                        if (n14 == 0) {
                                                                            return;
                                                                        }
                                                                    }
                                                                }
                                                            }
                                                        }
                                                    }
                                                    double n2 = this.b.k() * this.b.l();
                                                }
                                            }
                                            if (i) {
                                                break Label_0370;
                                            }
                                            final int k = this.l;
                                        }
                                        if (n4 <= l) {
                                            this.a(1.0);
                                            bb2 = this;
                                            if (i) {
                                                break Label_0567;
                                            }
                                            final double n3 = dcmpl(this.g, d.h());
                                        }
                                        else {
                                            final float n15 = Runtime.getRuntime().freeMemory();
                                            if (i) {
                                                break Label_0566;
                                            }
                                            if (n15 <= 30.0f) {
                                                System.runFinalization();
                                                System.gc();
                                            }
                                            break Label_0476;
                                        }
                                    }
                                    if (n5 == 0) {
                                        bb2 = this;
                                        if (i) {
                                            break Label_0567;
                                        }
                                        if (this.h == d.i()) {
                                            bb2 = this;
                                            if (i) {
                                                break Label_0567;
                                            }
                                            if (this.i == d.j()) {
                                                bb2 = this;
                                                if (i) {
                                                    break Label_0567;
                                                }
                                                if (!this.t) {
                                                    d2 = d;
                                                    d3 = d;
                                                    if (i) {
                                                        break Label_0547;
                                                    }
                                                    if (!d.D()) {
                                                        bb2 = this;
                                                        if (i) {
                                                            break Label_0567;
                                                        }
                                                        if (this.j == d.b()) {
                                                            return;
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                                this.j = d.b();
                                this.g = this.c.a(d.h());
                                this.h = this.c.b(d.i());
                                this.i = this.c.c(d.j());
                                d.a(this.g, this.h, this.i);
                                d2 = d;
                                d3 = d;
                            }
                            if (i) {
                                break Label_0606;
                            }
                            d3.c();
                            this.c.a(d.b());
                        }
                        bb2 = this;
                    }
                    if (bb2.a == null) {
                        final Rectangle u = d.u();
                        this.a(this.c, u.width, u.height, d);
                        d.c(false);
                        if (!i) {
                            break Label_0894;
                        }
                    }
                    d2 = d;
                }
                final boolean d4 = d2.D();
                bb bb6 = null;
                Label_0876: {
                    if (!i) {
                        if (d4) {
                            final Rectangle u2 = d.u();
                            bb bb3 = this;
                            bb bb4 = this;
                            Label_0768: {
                                if (!i) {
                                    if (this.c instanceof i) {
                                        this.q = null;
                                        if (!i) {
                                            break Label_0768;
                                        }
                                    }
                                    bb3 = this;
                                    bb4 = this;
                                }
                                Label_0693: {
                                    if (!i) {
                                        if (bb4.s != null) {
                                            break Label_0693;
                                        }
                                        System.gc();
                                        this.s = new int[u2.width * u2.height];
                                        this.o = u2.width;
                                        bb3 = this;
                                    }
                                    bb3.p = u2.height;
                                }
                                int n17;
                                final int n16 = n17 = u2.width;
                                int n19;
                                final int n18 = n19 = this.m;
                                final int height;
                                final int n20;
                                Label_0741: {
                                    if (!i) {
                                        if (n16 == n18) {
                                            height = u2.height;
                                            n20 = this.n;
                                            if (i) {
                                                break Label_0741;
                                            }
                                            if (height == n20) {
                                                this.q = this.r;
                                            }
                                        }
                                        final int width = u2.width;
                                        final int o = this.o;
                                    }
                                }
                                if (!i) {
                                    if (n16 != n18) {
                                        break Label_0768;
                                    }
                                    n17 = u2.height;
                                    n19 = this.p;
                                }
                                if (height == n20) {
                                    this.q = this.s;
                                }
                            }
                            this.c.a(u2.width, u2.height, this.q, (Component)d.k());
                            this.a(this.c, u2.width, u2.height, d);
                            d.c(false);
                            if (!i) {
                                break Label_0894;
                            }
                        }
                        this.c.a(this.b.E());
                        bb5 = this;
                        bb6 = this;
                        if (i) {
                            break Label_0876;
                        }
                        final boolean b = this.c instanceof i;
                    }
                    if (d4) {
                        this.a = this.c.a((Component)d.k(), this.b.r());
                    }
                    bb5 = this;
                    bb6 = this;
                }
                if (i) {
                    break Label_0895;
                }
                if (bb6.f != null) {
                    this.f.c();
                }
            }
            bb5 = this;
        }
        final Enumeration m = bb5.b.i();
        while (m.hasMoreElements()) {
            final e e2 = m.nextElement();
            final Rectangle bounds2 = e2.getBounds();
            this.c.a(e2.getX(), e2.getY());
            e2.setBounds(this.c.e() - bounds2.width / 2, this.c.f() - bounds2.height / 2, bounds2.width, bounds2.height);
            if (i) {
                return;
            }
            if (i) {
                break;
            }
        }
        d.a(this.a);
    }
    
    private void a(final y b, final com.easypano.tourweaver.f.d d) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        this.b = b;
        this.g = d.h() / 2.0;
        this.c = this.a(b.F());
        bb bb = this;
        if (!i) {
            if (this.c == null) {
                throw new IllegalArgumentException(com.easypano.tourweaver.f.bb.z[8] + b.getName() + com.easypano.tourweaver.f.bb.z[9]);
            }
            bb = this;
        }
        bb.d.removeAllElements();
        c[] array = b.M();
        int n2 = 0;
        Label_0178: {
            while (true) {
                Label_0134: {
                    if (array != null) {
                        final y y = b;
                        if (i) {
                            break Label_0134;
                        }
                        final int n = n2 = (b.M.h() ? 1 : 0);
                        if (i) {
                            break Label_0178;
                        }
                        if (n != 0) {
                            break;
                        }
                    }
                    try {
                        Thread.sleep(10L);
                        final y y = b;
                        array = y.M();
                        continue;
                    }
                    catch (Exception ex) {
                        if (!i) {
                            continue;
                        }
                    }
                }
                break;
            }
            this.e = array.length;
            this.d.addElement(b.h());
            this.a(this.d, array);
            n2 = b.k();
        }
        final int n3 = n2;
        final int l = b.l();
        final Rectangle u = d.u();
        final int width = u.width;
        final int height = u.height;
        this.m = width;
        this.n = height;
        bb bb2 = this;
        Label_0265: {
            if (!i) {
                if (this.c instanceof i) {
                    this.q = null;
                    if (!i) {
                        break Label_0265;
                    }
                }
                System.gc();
                this.r = new int[width * height];
                bb2 = this;
            }
            bb2.q = this.r;
        }
        int n5;
        final int n4 = n5 = n3 * l;
        if (!i) {
            if (n4 > this.l) {
                n5 = 1;
            }
            else {
                n5 = 0;
            }
        }
        final int n6 = n5;
        final double n7 = b.g() / d.d();
        double n9;
        final int n8 = (int)(n9 = n6);
        Label_0799: {
            if (!i) {
                if (n8 != 0) {
                    final c h = b.h();
                    final int[] array2 = new int[h.c() * h.d()];
                    this.c.a(h.c(), h.d(), b.z(), b.C(), b.y(), b.B(), b.A(), b.D(), b.m(), b.n(), d.j(), d.i(), d.h(), false, width, height, (Component)d.k());
                    d.a(this.c.c(), this.c.b(), this.c.d());
                    b.a(this.c.c(), this.c.b(), this.c.d());
                    this.c.a(h.a(array2), h.f(), h.g(), h.c(), h.d());
                    this.c.a(n3, l, n6 != 0);
                    this.c.a(width, height, this.q, (Component)d.k());
                    this.c.a(array, b.H());
                    if (!i) {
                        break Label_0799;
                    }
                }
                this.c.a(n3, l, b.z(), b.C(), b.y(), b.B(), b.A(), b.D(), b.m(), b.n(), d.j(), d.i(), d.h(), false, width, height, (Component)d.k());
                final boolean b2;
                n9 = ((b2 = (this.c instanceof i)) ? 1 : 0);
            }
            com.easypano.tourweaver.f.d d2 = null;
            Label_0775: {
                if (!i) {
                    if (n8 != 0) {
                        ((i)this.c).d(n7);
                    }
                    this.c.a(width, height, this.q, (Component)d.k());
                    d2 = d;
                    if (i) {
                        break Label_0775;
                    }
                    n9 = dcmpg(Math.abs(d.h() - b.v()), 3.0E-4);
                }
                if (n9 <= 0) {
                    d2 = d;
                    if (i) {
                        break Label_0775;
                    }
                    if (Math.abs(d.i() - b.w()) <= 3.0E-4) {
                        d2 = d;
                        if (i) {
                            break Label_0775;
                        }
                        if (Math.abs(d.j() - b.x()) <= 3.0E-4) {
                            b.a(this.c.c(), this.c.b(), this.c.d());
                        }
                    }
                }
                d2 = d;
            }
            d2.a(this.c.c(), this.c.b(), this.c.d());
        }
        System.gc();
    }
    
    private void a(final Vector vector, final Object[] array) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        int j = 0;
        while (j < array.length) {
            vector.addElement(array[j]);
            ++j;
            if (i) {
                break;
            }
        }
    }
    
    public void a(final f f, final int n, final int n2, final com.easypano.tourweaver.f.d d) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        if (f == null) {
            return;
        }
        boolean b = n != 0;
        boolean b2 = n != 0;
        if (!i) {
            if (n == 0) {
                return;
            }
            b = (n2 != 0);
            b2 = (n2 != 0);
        }
        f f2 = null;
        Label_0086: {
            if (!i) {
                if (!b2) {
                    return;
                }
                f2 = f;
                if (i) {
                    break Label_0086;
                }
                b = (f instanceof i);
            }
            if (b) {
                this.a = ((i)f).a((Component)d.k(), this.b.r());
                d.a(f.c(), f.b(), f.d());
                return;
            }
            f2 = f;
        }
        f2.a(this.b.E());
        this.a(this.q, n, n2);
    }
    
    private void a(final int[] array, final int n, final int n2) {
        if (array != null) {
            System.gc();
            this.f = new d(new Dimension(n, n2), new DirectColorModel(32, 16711680, 65280, 255, 0), array);
            com.easypano.tourweaver.a.e.a(this.a = Toolkit.getDefaultToolkit().createImage(this.f));
        }
    }
    
    private synchronized boolean a() {
        final boolean i = com.easypano.tourweaver.f.r.i;
        int j = 1;
        boolean h = false;
        while (j < this.d.size()) {
            final c c = this.d.elementAt(j);
            if (!i) {
                h = c.h();
                if (i) {
                    return h;
                }
                if (!h) {
                    return false;
                }
                ++j;
            }
            if (i) {
                break;
            }
        }
        return h;
    }
    
    public synchronized void a(final double n) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        this.t = false;
        final int size = this.d.size();
        if (!i) {
            if (size == 0) {
                return;
            }
            final int n2 = (int)(n * this.e);
        }
        int n3 = size;
        int j = 0;
        final int n4 = n3;
        if (!i && n4 < 1) {
            n3 = 1;
            goto Label_0050;
        }
        int n5 = n4;
        while (j < this.d.size()) {
            final c c = this.d.elementAt(j);
            if (!i) {
                if (c != null && c.h()) {
                    this.t = true;
                    bb bb = this;
                    int w = 0;
                    final int n6;
                    Label_0312: {
                        Label_0250: {
                            final int v;
                            final int c2;
                            Label_0162: {
                                Label_0154: {
                                    if (!i) {
                                        if (this.u == null) {
                                            break Label_0154;
                                        }
                                        bb = this;
                                    }
                                    v = bb.v;
                                    c2 = c.c();
                                    if (i) {
                                        break Label_0162;
                                    }
                                    if (v == c2) {
                                        n6 = (w = this.w);
                                        if (i) {
                                            break Label_0312;
                                        }
                                        if (n6 == c.d()) {
                                            break Label_0250;
                                        }
                                    }
                                }
                                c.e();
                            }
                            if (v == c2) {
                                this.v = c.c();
                                this.w = c.d();
                                System.gc();
                                this.u = new int[this.v * this.w];
                                if (!i) {
                                    break Label_0250;
                                }
                            }
                            this.v = this.b.k();
                            this.w = this.b.l();
                            System.gc();
                            this.u = new int[this.v * this.w];
                        }
                        this.c.a(c.a(this.u), c.f(), c.g(), this.v, this.w);
                        this.d.removeElementAt(j);
                        ++n5;
                        --j;
                        final boolean f;
                        w = ((f = this.b.f()) ? 1 : 0);
                    }
                    if (!i) {
                        if (n6 != 0 && !i) {
                            break;
                        }
                        w = n5;
                    }
                    if (w == n3 + 1 && !i) {
                        break;
                    }
                }
                ++j;
            }
            if (i) {
                break;
            }
        }
    }
    
    public Image g() {
        return this.a;
    }
    
    public boolean a(final com.easypano.tourweaver.f.h h) {
        return h instanceof y;
    }
    
    public void a(final q q) {
    }
    
    public q h() {
        return null;
    }
    
    public void c() {
        bb bb = this;
        if (!com.easypano.tourweaver.f.r.i) {
            if (this.c != null) {
                this.q = null;
                this.r = null;
                this.s = null;
                this.c.a();
                this.c = null;
            }
            this.b = null;
            this.a = null;
            bb = this;
        }
        bb.f = null;
    }
    
    public boolean b(final com.easypano.tourweaver.f.h h) {
        return h == this.b;
    }
    
    static {
        final String[] z = new String[10];
        final int n = 0;
        final char[] charArray = "^l`\t3{".toCharArray();
        int length;
        int n3;
        final int n2 = n3 = (length = charArray.length);
        int n4 = 0;
        while (true) {
            Label_0098: {
                if (n2 > 1) {
                    break Label_0098;
                }
                length = (n3 = n4);
                do {
                    final char c = charArray[n3];
                    char c2 = '\0';
                    switch (n4 % 5) {
                        case 0: {
                            c2 = '\u0015';
                            break;
                        }
                        case 1: {
                            c2 = '\r';
                            break;
                        }
                        case 2: {
                            c2 = '\t';
                            break;
                        }
                        case 3: {
                            c2 = 'm';
                            break;
                        }
                        default: {
                            c2 = 'R';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n4;
                } while (n2 == 0);
            }
            if (n2 > n4) {
                continue;
            }
            break;
        }
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "Fdg\n>pK`\u001e:Ptl".toCharArray();
        int length2;
        int n7;
        final int n6 = n7 = (length2 = charArray2.length);
        int n8 = 0;
        while (true) {
            Label_0214: {
                if (n6 > 1) {
                    break Label_0214;
                }
                length2 = (n7 = n8);
                do {
                    final char c3 = charArray2[n7];
                    char c4 = '\0';
                    switch (n8 % 5) {
                        case 0: {
                            c4 = '\u0015';
                            break;
                        }
                        case 1: {
                            c4 = '\r';
                            break;
                        }
                        case 2: {
                            c4 = '\t';
                            break;
                        }
                        case 3: {
                            c4 = 'm';
                            break;
                        }
                        default: {
                            c4 = 'R';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n8;
                } while (n6 == 0);
            }
            if (n6 > n8) {
                continue;
            }
            break;
        }
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "Ghd\u0002&p".toCharArray();
        int length3;
        int n11;
        final int n10 = n11 = (length3 = charArray3.length);
        int n12 = 0;
        while (true) {
            Label_0330: {
                if (n10 > 1) {
                    break Label_0330;
                }
                length3 = (n11 = n12);
                do {
                    final char c5 = charArray3[n11];
                    char c6 = '\0';
                    switch (n12 % 5) {
                        case 0: {
                            c6 = '\u0015';
                            break;
                        }
                        case 1: {
                            c6 = '\r';
                            break;
                        }
                        case 2: {
                            c6 = '\t';
                            break;
                        }
                        case 3: {
                            c6 = 'm';
                            break;
                        }
                        default: {
                            c6 = 'R';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 > n12) {
                continue;
            }
            break;
        }
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "Vte\u0004<q\u007f`\u000e3y".toCharArray();
        int length4;
        int n15;
        final int n14 = n15 = (length4 = charArray4.length);
        int n16 = 0;
        while (true) {
            Label_0446: {
                if (n14 > 1) {
                    break Label_0446;
                }
                length4 = (n15 = n16);
                do {
                    final char c7 = charArray4[n15];
                    char c8 = '\0';
                    switch (n16 % 5) {
                        case 0: {
                            c8 = '\u0015';
                            break;
                        }
                        case 1: {
                            c8 = '\r';
                            break;
                        }
                        case 2: {
                            c8 = '\t';
                            break;
                        }
                        case 3: {
                            c8 = 'm';
                            break;
                        }
                        default: {
                            c8 = 'R';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "Fy`\u0001>\\`h\n7".toCharArray();
        int length5;
        int n19;
        final int n18 = n19 = (length5 = charArray5.length);
        int n20 = 0;
        while (true) {
            Label_0562: {
                if (n18 > 1) {
                    break Label_0562;
                }
                length5 = (n19 = n20);
                do {
                    final char c9 = charArray5[n19];
                    char c10 = '\0';
                    switch (n20 % 5) {
                        case 0: {
                            c10 = '\u0015';
                            break;
                        }
                        case 1: {
                            c10 = '\r';
                            break;
                        }
                        case 2: {
                            c10 = '\t';
                            break;
                        }
                        case 3: {
                            c10 = 'm';
                            break;
                        }
                        default: {
                            c10 = 'R';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 > n20) {
                continue;
            }
            break;
        }
        z[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "% :[b".toCharArray();
        int length6;
        int n23;
        final int n22 = n23 = (length6 = charArray6.length);
        int n24 = 0;
        while (true) {
            Label_0678: {
                if (n22 > 1) {
                    break Label_0678;
                }
                length6 = (n23 = n24);
                do {
                    final char c11 = charArray6[n23];
                    char c12 = '\0';
                    switch (n24 % 5) {
                        case 0: {
                            c12 = '\u0015';
                            break;
                        }
                        case 1: {
                            c12 = '\r';
                            break;
                        }
                        case 2: {
                            c12 = '\t';
                            break;
                        }
                        case 3: {
                            c12 = 'm';
                            break;
                        }
                        default: {
                            c12 = 'R';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n24;
                } while (n22 == 0);
            }
            if (n22 > n24) {
                continue;
            }
            break;
        }
        z[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "Vxk\u00041".toCharArray();
        int length7;
        int n27;
        final int n26 = n27 = (length7 = charArray7.length);
        int n28 = 0;
        while (true) {
            Label_0798: {
                if (n26 > 1) {
                    break Label_0798;
                }
                length7 = (n27 = n28);
                do {
                    final char c13 = charArray7[n27];
                    char c14 = '\0';
                    switch (n28 % 5) {
                        case 0: {
                            c14 = '\u0015';
                            break;
                        }
                        case 1: {
                            c14 = '\r';
                            break;
                        }
                        case 2: {
                            c14 = '\t';
                            break;
                        }
                        case 3: {
                            c14 = 'm';
                            break;
                        }
                        default: {
                            c14 = 'R';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 > n28) {
                continue;
            }
            break;
        }
        z[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "F}a\b |nh\u0001".toCharArray();
        int length8;
        int n31;
        final int n30 = n31 = (length8 = charArray8.length);
        int n32 = 0;
        while (true) {
            Label_0918: {
                if (n30 > 1) {
                    break Label_0918;
                }
                length8 = (n31 = n32);
                do {
                    final char c15 = charArray8[n31];
                    char c16 = '\0';
                    switch (n32 % 5) {
                        case 0: {
                            c16 = '\u0015';
                            break;
                        }
                        case 1: {
                            c16 = '\r';
                            break;
                        }
                        case 2: {
                            c16 = '\t';
                            break;
                        }
                        case 3: {
                            c16 = 'm';
                            break;
                        }
                        default: {
                            c16 = 'R';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 > n32) {
                continue;
            }
            break;
        }
        z[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "AelM!vhg\b\t".toCharArray();
        int length9;
        int n35;
        final int n34 = n35 = (length9 = charArray9.length);
        int n36 = 0;
        while (true) {
            Label_1038: {
                if (n34 > 1) {
                    break Label_1038;
                }
                length9 = (n35 = n36);
                do {
                    final char c17 = charArray9[n35];
                    char c18 = '\0';
                    switch (n36 % 5) {
                        case 0: {
                            c18 = '\u0015';
                            break;
                        }
                        case 1: {
                            c18 = '\r';
                            break;
                        }
                        case 2: {
                            c18 = '\t';
                            break;
                        }
                        case 3: {
                            c18 = 'm';
                            break;
                        }
                        default: {
                            c18 = 'R';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 > n36) {
                continue;
            }
            break;
        }
        z[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "H*zM&l}lM;f-~\u001f={j(".toCharArray();
        int length10;
        int n39;
        final int n38 = n39 = (length10 = charArray10.length);
        int n40 = 0;
        while (true) {
            Label_1158: {
                if (n38 > 1) {
                    break Label_1158;
                }
                length10 = (n39 = n40);
                do {
                    final char c19 = charArray10[n39];
                    char c20 = '\0';
                    switch (n40 % 5) {
                        case 0: {
                            c20 = '\u0015';
                            break;
                        }
                        case 1: {
                            c20 = '\r';
                            break;
                        }
                        case 2: {
                            c20 = '\t';
                            break;
                        }
                        case 3: {
                            c20 = 'm';
                            break;
                        }
                        default: {
                            c20 = 'R';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 <= n40) {
                z[n37] = new String(charArray10).intern();
                bb.z = z;
                return;
            }
            continue;
        }
    }
}
