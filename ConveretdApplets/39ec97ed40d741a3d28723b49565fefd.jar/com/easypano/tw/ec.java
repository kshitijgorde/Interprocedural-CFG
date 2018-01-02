// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import com.easypano.tw.c.c;
import java.awt.Container;
import com.easypano.tw.b.a;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;

final class ec extends Thread
{
    private dm a;
    private dn b;
    private volatile boolean c;
    private volatile boolean d;
    private volatile boolean e;
    private volatile boolean f;
    private volatile boolean g;
    private volatile boolean h;
    private volatile boolean i;
    private volatile int j;
    private volatile boolean k;
    private volatile boolean l;
    public int m;
    private volatile double n;
    private volatile double o;
    private volatile double p;
    final /* synthetic */ bt q;
    
    public ec(final bt q) {
        super(a("\u001b\u001d\u0005[\u001e:\u00107A\t,\u0019\u0007"));
        this.q = q;
        this.a = new dm(q);
        this.b = new dn(q, this);
        this.c = false;
        this.d = true;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.k = true;
        this.l = false;
        this.m = 4;
    }
    
    public void a(final Runnable runnable) {
        this.a.a(runnable);
    }
    
    public void a() {
        this.c = true;
        ds.stopThread(this, a("\u001b\u001d\u0005[\u001e:\u0010C}\u0013;\u001d\u0002M[8\r\n]\u000f \u0016\u0004\u0007UgVM\u0007U"), 100, 0);
    }
    
    public void b() {
        this.d = true;
    }
    
    public void c() {
        final boolean q = com.easypano.tw.g.q;
        this.d = false;
        int n = 0;
        while (true) {
        Label_0052:
            while (true) {
                Label_0045: {
                    if (!q) {
                        break Label_0045;
                    }
                    try {
                        if (n++ >= 30 && !q) {
                            break Label_0052;
                        }
                        Thread.sleep(10L);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                if (this.e) {
                    continue;
                }
                break;
            }
            final Graphics graphics = this.q.getGraphics();
            if (!q) {
                if (bt.n(this.q) != null && graphics != null) {
                    try {
                        this.q.paint(graphics);
                    }
                    finally {
                        graphics.dispose();
                    }
                    graphics.dispose();
                }
                return;
            }
            continue;
        }
    }
    
    public void d() {
        final boolean q = com.easypano.tw.g.q;
        final int f = this.f ? 1 : 0;
        if (!q) {
            if (f == 0) {
                return;
            }
            this.f = false;
            this.o = 0.0;
            this.n = 0.0;
            this.p = 0.0;
        }
        int n = f;
        while (true) {
            Label_0068: {
                if (!q) {
                    break Label_0068;
                }
                if (n++ >= 30) {
                    if (!q) {
                        return;
                    }
                }
                try {
                    Thread.sleep(10L);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (this.g) {
                continue;
            }
            break;
        }
    }
    
    public void e() {
        this.f = true;
    }
    
    public void a(final double n, final double n2, final double n3) {
        this.o = n / 1000.0;
        this.n = n2 / 1000.0;
        this.p = n3 / 1000.0;
    }
    
    public boolean f() {
        return this.h;
    }
    
    public void g() {
        this.m = 4;
        this.h = true;
        bt.h(this.q).pathStateChanged(bt.o(this.q), 10);
        bt.p(this.q).c();
    }
    
    public void h() {
        final boolean q = com.easypano.tw.g.q;
        final int h = this.h ? 1 : 0;
        if (!q) {
            if (h == 0) {
                return;
            }
            this.h = false;
        }
        int n = h;
        while (true) {
        Label_0060:
            while (true) {
                Label_0053: {
                    if (!q) {
                        break Label_0053;
                    }
                    if (n++ >= 30) {
                        if (!q) {
                            break Label_0060;
                        }
                    }
                    try {
                        Thread.sleep(10L);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                if (this.i) {
                    continue;
                }
                break;
            }
            if (q) {
                continue;
            }
            break;
        }
        final cd p = bt.p(this.q);
        if (!q) {
            if (p == null) {
                return;
            }
            bt.p(this.q);
        }
        p.b();
        bt.h(this.q).pathStateChanged(bt.o(this.q), 11);
    }
    
    public void i() {
        this.a(true);
    }
    
    public void a(final boolean b) {
        final boolean q = com.easypano.tw.g.q;
        ec ec = this;
        Label_0074: {
            if (!q) {
                Label_0073: {
                    if (this.h) {
                        ec = this;
                        if (q) {
                            break Label_0074;
                        }
                        this.h = false;
                        if (b) {
                            int n = 0;
                            while (true) {
                                Label_0066: {
                                    if (!q) {
                                        break Label_0066;
                                    }
                                    if (n++ >= 30) {
                                        if (!q) {
                                            break Label_0073;
                                        }
                                    }
                                    try {
                                        Thread.sleep(10L);
                                    }
                                    catch (Exception ex) {
                                        ex.printStackTrace();
                                    }
                                }
                                if (this.i) {
                                    continue;
                                }
                                break;
                            }
                        }
                    }
                }
                ec = this;
            }
        }
        final cd p = bt.p(ec.q);
        if (!q) {
            if (p == null) {
                return;
            }
            bt.h(this.q).pathStateChanged(bt.o(this.q), 12);
            bt.p(this.q);
        }
        p.f();
    }
    
    public int j() {
        return 40;
    }
    
    public void run() {
        final boolean q = com.easypano.tw.g.q;
        long n = -1L;
        final int j = this.j();
        cc cc = null;
        int g = 0;
        double e = 0.0;
        double n2 = 0.0;
        int n3 = 0;
        long n4 = 0L;
        long n5 = 0L;
        int n6 = 0;
        int n7 = 0;
        System.currentTimeMillis();
    Label_3597_Outer:
        while (!this.c) {
            try {
                while (true) {
                    long n50 = 0L;
                    Label_3602: {
                        if (this.d) {
                            final long currentTimeMillis = System.currentTimeMillis();
                            this.e = true;
                            this.a.a();
                            final boolean f = this.f;
                            Label_0239: {
                                Label_0234: {
                                    ec ec = null;
                                    Label_0102: {
                                        if (!q) {
                                            if (!f) {
                                                break Label_0234;
                                            }
                                            ec = this;
                                            if (q) {
                                                break Label_0102;
                                            }
                                            bt.a(this.q);
                                        }
                                        if (!f) {
                                            break Label_0234;
                                        }
                                        ec = this;
                                    }
                                    ec.g = true;
                                    final long currentTimeMillis2 = System.currentTimeMillis();
                                    final long n8 = n;
                                    final long n9 = -1L;
                                    final long n10 = (!q && n8 == n9) ? 10L : (n8 - n9);
                                    n = currentTimeMillis2;
                                    bt.c(this.q).c(bt.c(this.q).d() + this.o * n10);
                                    bt.c(this.q).i(bt.c(this.q).f() + this.n * n10);
                                    bt.c(this.q).f(bt.c(this.q).e() + this.p * n10);
                                    if (!q) {
                                        break Label_0239;
                                    }
                                }
                                n = -1L;
                            }
                            int n13;
                            int n12;
                            final int n11 = n12 = (n13 = (this.h ? 1 : 0));
                            Label_3302: {
                                if (!q) {
                                    Label_3285: {
                                        if (n11 != 0) {
                                            this.i = true;
                                            int m;
                                            int g2;
                                            final int n14 = g2 = (m = this.m);
                                            if (!q) {
                                                Label_1681: {
                                                    switch (n14) {
                                                        case 1: {
                                                            if (q) {
                                                                break Label_1681;
                                                            }
                                                            break Label_3285;
                                                        }
                                                        case 2: {
                                                            final int i = this.j;
                                                            final int n15 = -1;
                                                            Label_0428: {
                                                                int n16 = 0;
                                                                Label_0426: {
                                                                    if (!q) {
                                                                        if (i == n15) {
                                                                            n4 = 1000L;
                                                                            n3 = 0;
                                                                            if (!q) {
                                                                                break Label_0428;
                                                                            }
                                                                        }
                                                                        final int d;
                                                                        n16 = (d = bt.p(this.q).a(this.j).d);
                                                                        if (q) {
                                                                            break Label_0426;
                                                                        }
                                                                        final int d2 = bt.p(this.q).a(this.j + 1).d;
                                                                    }
                                                                    if (i != n15) {
                                                                        n4 = bt.p(this.q).a(this.j).e;
                                                                        n4 = bt.p(this.q).a(this.j + 1).e - n4;
                                                                        n3 = 0;
                                                                        if (!q) {
                                                                            break Label_0428;
                                                                        }
                                                                    }
                                                                    n4 = 1000L;
                                                                    n16 = 1;
                                                                }
                                                                n3 = n16;
                                                            }
                                                            int n20;
                                                            int f2;
                                                            int n19;
                                                            int n18;
                                                            final int n17 = n18 = (n19 = (f2 = (n20 = bt.q(this.q))));
                                                            if (!q) {
                                                                if (n17 != -1) {
                                                                    bt.h(this.q).actionOnPanoSwitching(bt.q(this.q), bt.c(this.q).d(), bt.c(this.q).f(), bt.c(this.q).e());
                                                                }
                                                                final int n21;
                                                                n18 = (n21 = (n19 = (f2 = (n20 = lcmp(n4, 0L)))));
                                                            }
                                                            Label_1632: {
                                                                if (!q) {
                                                                    if (n17 <= 0) {
                                                                        break Label_1632;
                                                                    }
                                                                    this.q.a(false);
                                                                    g = bt.h(this.q).h().g.a(cc.d).g;
                                                                    n18 = (f2 = (n20 = g));
                                                                }
                                                                final int n22;
                                                                Label_0569: {
                                                                    if (!q) {
                                                                        if (n18 == 3) {
                                                                            n22 = (f2 = (n20 = bt.q(this.q)));
                                                                            if (q) {
                                                                                break Label_0569;
                                                                            }
                                                                            if (n22 == -1) {
                                                                                g = 1;
                                                                            }
                                                                        }
                                                                        f2 = (n19 = (n20 = g));
                                                                    }
                                                                }
                                                                Label_0680: {
                                                                    if (!q) {
                                                                        switch (n22) {
                                                                            case 3: {
                                                                                e = bt.c(this.q).e();
                                                                                n20 = (f2 = bt.f(this.q));
                                                                                break;
                                                                            }
                                                                            default: {
                                                                                break Label_0680;
                                                                            }
                                                                        }
                                                                    }
                                                                    Label_0665: {
                                                                        if (!q) {
                                                                            switch (f2) {
                                                                                case 2:
                                                                                case 3: {
                                                                                    n2 = 0.7853999733924866;
                                                                                    if (q) {
                                                                                        break;
                                                                                    }
                                                                                    break Label_0665;
                                                                                }
                                                                            }
                                                                            n20 = this.q.getBounds().width / 2;
                                                                        }
                                                                        n2 = n20;
                                                                    }
                                                                    n5 = System.currentTimeMillis();
                                                                    this.m = 3;
                                                                    if (!q) {
                                                                        break Label_1681;
                                                                    }
                                                                }
                                                                bt.a(this.q, bt.h(this.q).h().g.a(cc.d));
                                                                final Image image = (Image)bt.h(this.q).h().d.a(bt.b(this.q).k, 1);
                                                                int n24 = 0;
                                                                final int n23;
                                                                Label_0821: {
                                                                    Label_0785: {
                                                                        if (!q) {
                                                                            if (image != null) {
                                                                                n23 = (n24 = image.getWidth(ds.d));
                                                                                if (q) {
                                                                                    break Label_0821;
                                                                                }
                                                                                if (n23 != -1) {
                                                                                    break Label_0785;
                                                                                }
                                                                            }
                                                                            this.a(false);
                                                                            this.q.a(true);
                                                                        }
                                                                        if (!q) {
                                                                            break Label_3285;
                                                                        }
                                                                    }
                                                                    bt.c(this.q, cc.d);
                                                                    bt.d(this.q, bt.b(this.q).d);
                                                                    final int f3;
                                                                    n24 = (f3 = bt.f(this.q));
                                                                }
                                                                if (!q) {
                                                                    Label_1050: {
                                                                        switch (n23) {
                                                                            case 2:
                                                                            case 3: {
                                                                                final bt q2 = this.q;
                                                                                Label_0925: {
                                                                                    if (!q) {
                                                                                        if (bt.f(q2) == 3) {
                                                                                            bt.a(this.q, bt.r(this.q).get(a("\n\u0001\u000f@\u0015-\u001d\u0011y\u0017(\u0001\u0006[")));
                                                                                            if (!q) {
                                                                                                break Label_0925;
                                                                                            }
                                                                                        }
                                                                                        final bt q3 = this.q;
                                                                                    }
                                                                                    bt.a(q2, bt.r(this.q).get(a("\u001a\b\u000bL\t,(\u000fH\u0002,\n")));
                                                                                }
                                                                                final bt q4 = this.q;
                                                                                final bt q5 = this.q;
                                                                                final bt q6 = this.q;
                                                                                final double n25 = bt.c(this.q).g() * 1000.0 / this.j();
                                                                                bt.d(q6, n25);
                                                                                bt.e(q5, n25);
                                                                                bt.f(q4, n25);
                                                                                final bt q7 = this.q;
                                                                                final bt q8 = this.q;
                                                                                final bt q9 = this.q;
                                                                                final double g3 = bt.c(this.q).g();
                                                                                bt.g(q9, g3);
                                                                                bt.h(q8, g3);
                                                                                bt.i(q7, g3);
                                                                                final bt q10 = this.q;
                                                                                final bt q11 = this.q;
                                                                                final double n26 = 3.141592653589793;
                                                                                bt.j(q11, n26);
                                                                                bt.k(q10, n26);
                                                                                final bt q12 = this.q;
                                                                                final bt q13 = this.q;
                                                                                final bt q14 = this.q;
                                                                                final double n27 = 0.017453292519943295;
                                                                                bt.l(q14, n27);
                                                                                bt.m(q13, n27);
                                                                                bt.n(q12, n27);
                                                                                if (q) {
                                                                                    break Label_1050;
                                                                                }
                                                                                break;
                                                                            }
                                                                            case 1: {
                                                                                bt.a(this.q, bt.r(this.q).get(a("\u000f\u0014\u0002]+%\u0019\u001aL\t")));
                                                                                final bt q15 = this.q;
                                                                                final bt q16 = this.q;
                                                                                final bt q17 = this.q;
                                                                                final double n28 = bt.c(this.q).g() * 1000.0 / bt.i(this.q).j();
                                                                                bt.d(q17, n28);
                                                                                bt.e(q16, n28);
                                                                                bt.f(q15, n28);
                                                                                final bt q18 = this.q;
                                                                                final bt q19 = this.q;
                                                                                final bt q20 = this.q;
                                                                                final double g4 = bt.c(this.q).g();
                                                                                bt.g(q20, g4);
                                                                                bt.h(q19, g4);
                                                                                bt.i(q18, g4);
                                                                                final bt q21 = this.q;
                                                                                final bt q22 = this.q;
                                                                                final double n29 = this.q.getBounds().width;
                                                                                bt.j(q22, n29);
                                                                                bt.k(q21, n29);
                                                                                bt.n(this.q, bt.s(this.q));
                                                                                bt.m(this.q, bt.t(this.q));
                                                                                bt.l(this.q, bt.u(this.q));
                                                                                break;
                                                                            }
                                                                        }
                                                                    }
                                                                    bt.c(this.q).a(bt.b(this.q), image, this.q);
                                                                    bt.c(this.q).a(this.q.getBounds().width, this.q.getBounds().height);
                                                                    bt.c(this.q).c(cc.f);
                                                                    bt.c(this.q).i(cc.g);
                                                                    bt.c(this.q).f(cc.h);
                                                                    n24 = this.q.getBounds().width;
                                                                }
                                                                final int n30 = n24;
                                                                final int height = this.q.getBounds().height;
                                                                final Image image2 = this.q.createImage(n30, height);
                                                                final Graphics graphics = image2.getGraphics();
                                                                Label_1453: {
                                                                    try {
                                                                        if (!q) {
                                                                            if (bt.n(this.q) == null) {
                                                                                graphics.setColor(this.q.getBackground());
                                                                                graphics.fillRect(0, 0, n30, height);
                                                                                if (!q) {
                                                                                    break Label_1453;
                                                                                }
                                                                            }
                                                                            graphics.drawImage(bt.n(this.q), 0, 0, this.q);
                                                                        }
                                                                    }
                                                                    catch (Exception ex) {
                                                                        ex.printStackTrace();
                                                                    }
                                                                    finally {
                                                                        graphics.dispose();
                                                                    }
                                                                }
                                                                graphics.dispose();
                                                                if (!q) {
                                                                    Label_1510: {
                                                                        switch (g) {
                                                                            case 1: {
                                                                                bt.v(this.q).a(bt.w(this.q));
                                                                                if (q) {
                                                                                    break Label_1510;
                                                                                }
                                                                                break;
                                                                            }
                                                                            case 2: {
                                                                                bt.v(this.q).a(bt.x(this.q));
                                                                                break;
                                                                            }
                                                                        }
                                                                    }
                                                                    bt.c(this.q).k();
                                                                    bt.v(this.q).a(image2, bt.c(this.q).a());
                                                                    bt.v(this.q).a(this.q.getBounds().getSize());
                                                                    bt.v(this.q).a();
                                                                    bt.v(this.q).a(0);
                                                                    this.q.a(bt.v(this.q).b());
                                                                    n5 = System.currentTimeMillis();
                                                                    this.m = 3;
                                                                }
                                                                if (!q) {
                                                                    break Label_1681;
                                                                }
                                                            }
                                                            this.q.a(cc.d, cc.f, cc.g, cc.h, 0, true);
                                                            this.m = 4;
                                                            bt.p(this.q).c();
                                                            if (q) {
                                                                break Label_1681;
                                                            }
                                                            break Label_3285;
                                                        }
                                                        case 3: {
                                                            int n31 = (int)((System.currentTimeMillis() - n5) * 100L / n4);
                                                            int d3;
                                                            int n34;
                                                            int n33;
                                                            final int n32 = n33 = (n34 = (d3 = n31));
                                                            if (!q) {
                                                                if (n32 > 100) {
                                                                    n31 = 100;
                                                                }
                                                                final int n35;
                                                                n33 = (n35 = (n34 = (d3 = g)));
                                                            }
                                                            final int n36;
                                                            final int n37;
                                                            Label_2690: {
                                                                Label_2681: {
                                                                    if (!q) {
                                                                        switch (n32) {
                                                                            case 3: {
                                                                                bt.c(this.q).f((float)(e * (100 - n31) + n2 * n31) / 100.0f);
                                                                                bt.c(this.q).a(1);
                                                                                this.q.repaint();
                                                                                n36 = n31;
                                                                                n37 = 100;
                                                                                if (q) {
                                                                                    break Label_2690;
                                                                                }
                                                                                if (n36 != n37) {
                                                                                    break Label_2681;
                                                                                }
                                                                                bt.p(this.q).b();
                                                                                bt.a(this.q, bt.h(this.q).h().g.a(cc.d));
                                                                                final Image image3 = (Image)bt.h(this.q).h().d.a(bt.b(this.q).k, 1);
                                                                                final int width;
                                                                                Label_1958: {
                                                                                    Label_1922: {
                                                                                        if (!q) {
                                                                                            if (image3 != null) {
                                                                                                width = image3.getWidth(ds.d);
                                                                                                if (q) {
                                                                                                    break Label_1958;
                                                                                                }
                                                                                                if (width != -1) {
                                                                                                    break Label_1922;
                                                                                                }
                                                                                            }
                                                                                            this.a(false);
                                                                                            this.q.a(true);
                                                                                        }
                                                                                        if (!q) {
                                                                                            break Label_3285;
                                                                                        }
                                                                                    }
                                                                                    bt.c(this.q, cc.d);
                                                                                    bt.d(this.q, bt.b(this.q).d);
                                                                                    bt.f(this.q);
                                                                                }
                                                                                if (!q) {
                                                                                    Label_2186: {
                                                                                        switch (width) {
                                                                                            case 2:
                                                                                            case 3: {
                                                                                                final bt q23 = this.q;
                                                                                                Label_2061: {
                                                                                                    if (!q) {
                                                                                                        if (bt.f(q23) == 3) {
                                                                                                            bt.a(this.q, bt.r(this.q).get(a("\n\u0001\u000f@\u0015-\u001d\u0011y\u0017(\u0001\u0006[")));
                                                                                                            if (!q) {
                                                                                                                break Label_2061;
                                                                                                            }
                                                                                                        }
                                                                                                        final bt q24 = this.q;
                                                                                                    }
                                                                                                    bt.a(q23, bt.r(this.q).get(a("\u001a\b\u000bL\t,(\u000fH\u0002,\n")));
                                                                                                }
                                                                                                final bt q25 = this.q;
                                                                                                final bt q26 = this.q;
                                                                                                final bt q27 = this.q;
                                                                                                final double n38 = bt.c(this.q).g() * 1000.0 / this.j();
                                                                                                bt.d(q27, n38);
                                                                                                bt.e(q26, n38);
                                                                                                bt.f(q25, n38);
                                                                                                final bt q28 = this.q;
                                                                                                final bt q29 = this.q;
                                                                                                final bt q30 = this.q;
                                                                                                final double g5 = bt.c(this.q).g();
                                                                                                bt.g(q30, g5);
                                                                                                bt.h(q29, g5);
                                                                                                bt.i(q28, g5);
                                                                                                final bt q31 = this.q;
                                                                                                final bt q32 = this.q;
                                                                                                final double n39 = 3.141592653589793;
                                                                                                bt.j(q32, n39);
                                                                                                bt.k(q31, n39);
                                                                                                final bt q33 = this.q;
                                                                                                final bt q34 = this.q;
                                                                                                final bt q35 = this.q;
                                                                                                final double n40 = 0.017453292519943295;
                                                                                                bt.l(q35, n40);
                                                                                                bt.m(q34, n40);
                                                                                                bt.n(q33, n40);
                                                                                                if (q) {
                                                                                                    break Label_2186;
                                                                                                }
                                                                                                break;
                                                                                            }
                                                                                            case 1: {
                                                                                                bt.a(this.q, bt.r(this.q).get(a("\u000f\u0014\u0002]+%\u0019\u001aL\t")));
                                                                                                bt.i(this.q, bt.c(this.q).g() * image3.getWidth(this.q) / 50.0);
                                                                                                bt.h(this.q, bt.c(this.q).g() * image3.getHeight(this.q) / 50.0);
                                                                                                bt.g(this.q, bt.c(this.q).g());
                                                                                                bt.f(this.q, bt.s(this.q) * 1000.0 / this.j());
                                                                                                bt.e(this.q, bt.t(this.q) * 1000.0 / this.j());
                                                                                                bt.d(this.q, bt.u(this.q) * 1000.0 / this.j());
                                                                                                final bt q36 = this.q;
                                                                                                final bt q37 = this.q;
                                                                                                final double n41 = this.q.getBounds().width;
                                                                                                bt.j(q37, n41);
                                                                                                bt.k(q36, n41);
                                                                                                bt.n(this.q, bt.s(this.q));
                                                                                                bt.m(this.q, bt.t(this.q));
                                                                                                bt.l(this.q, bt.u(this.q));
                                                                                                break;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    bt.c(this.q).a(bt.b(this.q), image3, this.q);
                                                                                    bt.c(this.q).a(this.q.getBounds().width, this.q.getBounds().height);
                                                                                    bt.c(this.q).c(cc.f);
                                                                                    bt.c(this.q).i(cc.g);
                                                                                    bt.c(this.q).f(cc.h);
                                                                                    bt.a(this.q, bt.c(this.q).a());
                                                                                    bt.c(this.q).a(1);
                                                                                }
                                                                                if (q) {
                                                                                    break;
                                                                                }
                                                                                break Label_2681;
                                                                            }
                                                                        }
                                                                        bt.v(this.q).a(n31);
                                                                        n34 = (n33 = (d3 = bt.v(this.q).d()));
                                                                    }
                                                                    if (!q) {
                                                                        Label_2628: {
                                                                            switch (n33) {
                                                                                case 1: {
                                                                                    this.q.repaint();
                                                                                    if (q) {
                                                                                        break Label_2628;
                                                                                    }
                                                                                    break;
                                                                                }
                                                                                case 2: {
                                                                                    this.q.repaint();
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        n34 = n31;
                                                                    }
                                                                    final int n42 = 100;
                                                                    if (q) {
                                                                        break Label_2690;
                                                                    }
                                                                    if (n34 == n42) {
                                                                        bt.a(this.q, bt.c(this.q).a());
                                                                        this.q.repaint();
                                                                        bt.v(this.q).e();
                                                                    }
                                                                }
                                                                n12 = (n13 = n31);
                                                                if (q) {
                                                                    break Label_3302;
                                                                }
                                                            }
                                                            if (n36 == n37) {
                                                                n7 = 1;
                                                                this.q.a(true);
                                                                this.m = 4;
                                                                this.q.repaint();
                                                                if (!q) {
                                                                    if (n3 == 0) {
                                                                        bt.p(this.q).b(n4);
                                                                    }
                                                                    bt.p(this.q).c();
                                                                }
                                                                break;
                                                            }
                                                            break Label_3285;
                                                        }
                                                    }
                                                }
                                                final long n43;
                                                g2 = (int)(n43 = (m = lcmp(bt.p(this.q).d(), 0L)));
                                            }
                                            cc cc3 = null;
                                            final cc cc2;
                                            Label_3104: {
                                                Label_3102: {
                                                    if (!q) {
                                                        if (n14 < 0) {
                                                            this.j = -1;
                                                            n7 = 1;
                                                            cc = bt.p(this.q).b(0);
                                                            cc2 = (cc3 = cc);
                                                            if (q) {
                                                                break Label_3104;
                                                            }
                                                            if (cc2 == null) {
                                                                break Label_3102;
                                                            }
                                                            bt.h(this.q).i().a(bt.p(this.q).i, true, true);
                                                            bt.p(this.q).a(System.currentTimeMillis());
                                                            if (!q) {
                                                                if (cc.d == bt.q(this.q)) {
                                                                    bt.p(this.q).b((long)cc.e);
                                                                    if (!q) {
                                                                        break Label_3102;
                                                                    }
                                                                }
                                                                bt.p(this.q).b((long)(cc.e - 1000));
                                                            }
                                                            if (!q) {
                                                                break Label_3102;
                                                            }
                                                        }
                                                        cc = bt.p(this.q).d(System.currentTimeMillis());
                                                        m = (g2 = bt.p(this.q).g());
                                                    }
                                                    final cd p;
                                                    Label_3091: {
                                                        if (!q) {
                                                            Label_2970: {
                                                                switch (g2) {
                                                                    case 3: {
                                                                        this.a(false);
                                                                        if (q) {
                                                                            break Label_2970;
                                                                        }
                                                                        break Label_3102;
                                                                    }
                                                                    case 4: {
                                                                        final int n44 = bt.p(this.q).h() - this.j;
                                                                        ec ec2 = null;
                                                                        Label_3051: {
                                                                            if (!q) {
                                                                                if (n44 >= 2) {
                                                                                    cc = bt.p(this.q).b(this.j + 1);
                                                                                    if (!q) {
                                                                                        break Label_3102;
                                                                                    }
                                                                                }
                                                                                ec2 = this;
                                                                                if (q) {
                                                                                    break Label_3051;
                                                                                }
                                                                                final boolean k = bt.p(this.q).j;
                                                                            }
                                                                            if (n44 != 0) {
                                                                                bt.p(this.q).f();
                                                                                if (!q) {
                                                                                    break Label_3102;
                                                                                }
                                                                            }
                                                                            ec2 = this;
                                                                        }
                                                                        ec2.a(false);
                                                                        if (q) {
                                                                            break;
                                                                        }
                                                                        break Label_3102;
                                                                    }
                                                                }
                                                            }
                                                            p = bt.p(this.q);
                                                            if (q) {
                                                                break Label_3091;
                                                            }
                                                            m = p.h() - this.j;
                                                        }
                                                        if (m < 2) {
                                                            break Label_3102;
                                                        }
                                                        bt.p(this.q);
                                                    }
                                                    cc = p.b(this.j + 1);
                                                }
                                                final cc cc4;
                                                cc3 = (cc4 = cc);
                                            }
                                            Label_3280: {
                                                if (!q) {
                                                    if (cc2 == null) {
                                                        break Label_3280;
                                                    }
                                                    cc3 = cc;
                                                }
                                                if (cc3.d == bt.q(this.q)) {
                                                    this.j = bt.p(this.q).h();
                                                    bt.c(this.q).c(cc.f);
                                                    bt.c(this.q).i(cc.g);
                                                    bt.c(this.q).f(cc.h);
                                                    if (!q) {
                                                        break Label_3285;
                                                    }
                                                }
                                                bt.p(this.q).b();
                                                this.m = 1;
                                                final ci a = bt.h(this.q).h().g.a(cc.d);
                                                if (!q) {
                                                    if (a != null) {
                                                        bt.h(this.q).h().d.a(a.k, this.b, 1);
                                                        if (!q) {
                                                            break Label_3285;
                                                        }
                                                    }
                                                    this.a(false);
                                                }
                                                if (!q) {
                                                    break Label_3285;
                                                }
                                            }
                                            this.a(false);
                                        }
                                    }
                                    final int n45;
                                    n12 = (n45 = (n13 = bt.c(this.q).a(bt.y(this.q))));
                                }
                            }
                            ec ec3 = null;
                            Label_3575: {
                                Label_3564: {
                                    if (!q) {
                                        if (n11 > 0) {
                                            final int l = this.m;
                                            final int n46 = 4;
                                            final int n47;
                                            final int m2;
                                            Label_3413: {
                                                if (!q) {
                                                    Label_3357: {
                                                        if (l == n46) {
                                                            final int n48;
                                                            n47 = (n48 = n7);
                                                            if (q) {
                                                                break Label_3357;
                                                            }
                                                            if (n47 != 0) {
                                                                bt.h(this.q).actionOnPanoSwitched(bt.q(this.q));
                                                                n7 = 0;
                                                            }
                                                        }
                                                        m2 = this.m;
                                                    }
                                                    if (q) {
                                                        break Label_3413;
                                                    }
                                                }
                                                if (l == n46) {
                                                    bt.h(this.q).actionOnViewPortChanged(bt.c(this.q).d(), bt.c(this.q).f(), bt.c(this.q).e());
                                                }
                                                bt.y(this.q);
                                            }
                                            if (!q) {
                                                switch (n47) {
                                                    case 0: {
                                                        n6 = 1;
                                                        if (q) {
                                                            break;
                                                        }
                                                        break Label_3564;
                                                    }
                                                }
                                            }
                                            n6 = m2;
                                            if (!q) {
                                                break Label_3564;
                                            }
                                        }
                                        n13 = (n12 = n6);
                                    }
                                    if (!q) {
                                        if (n12 != 0) {
                                            bt.c(this.q).b(1);
                                            n6 = 0;
                                            if (!q) {
                                                break Label_3564;
                                            }
                                        }
                                        ec3 = this;
                                        if (q) {
                                            break Label_3575;
                                        }
                                        n13 = this.m;
                                    }
                                    if (n13 == 4) {
                                        final int n49 = n7;
                                        if (!q) {
                                            if (n49 == 0) {
                                                break Label_3564;
                                            }
                                            bt.h(this.q).actionOnPanoSwitched(bt.q(this.q));
                                            bt.h(this.q).actionOnViewPortChanged(bt.c(this.q).d(), bt.c(this.q).f(), bt.c(this.q).e());
                                        }
                                        n7 = n49;
                                    }
                                }
                                this.g = false;
                                this.i = false;
                                ec3 = this;
                            }
                            ec3.e = false;
                            n50 = j - System.currentTimeMillis() + currentTimeMillis;
                            if (!q) {
                                break Label_3602;
                            }
                        }
                        n50 = 10L;
                    }
                    if (n50 <= 0L) {
                        continue Label_3597_Outer;
                    }
                    try {
                        Thread.sleep(n50);
                        if (!q) {
                            continue Label_3597_Outer;
                        }
                        continue;
                    }
                    catch (Exception ex2) {
                        ex2.printStackTrace();
                    }
                    break;
                }
            }
            catch (Exception ex3) {
                System.out.println(a("\f\n\u0011F\ti\u0011\r\t\t<\u0016M"));
                ex3.printStackTrace();
                this.d = true;
                bt.a(this.q, true);
                this.f = false;
                this.h = false;
                this.i = false;
                this.g = false;
                this.m = 4;
            }
        }
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
                            c2 = 'I';
                            break;
                        }
                        case 1: {
                            c2 = 'x';
                            break;
                        }
                        case 2: {
                            c2 = 'c';
                            break;
                        }
                        case 3: {
                            c2 = ')';
                            break;
                        }
                        default: {
                            c2 = '{';
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
