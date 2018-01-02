// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.Container;
import com.easypano.tw.b.c;
import com.easypano.tw.b.a;
import java.awt.image.ImageObserver;
import java.awt.Image;
import java.awt.Graphics;

final class ed extends Thread
{
    private dn a;
    private dp b;
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
    
    public ed(final bt q) {
        super(a(";A\"k<\u001aL\u0010q+\fE "));
        this.q = q;
        this.a = new dn(q);
        this.b = new dp(q, this);
        this.c = false;
        this.d = true;
        this.e = false;
        this.f = false;
        this.g = false;
        this.h = false;
        this.i = false;
        this.k = true;
        this.l = false;
        this.setPriority(this.m = 4);
    }
    
    public void a(final Runnable runnable) {
        this.a.a(runnable);
    }
    
    public void a() {
        this.c = true;
        dt.stopThread(this, a(";A\"k<\u001aLdM1\u001bA%}y\u0018Q-m-\u0000J#7wG\nj7w"), 100, 0);
    }
    
    public void b() {
        this.d = true;
    }
    
    public void c() {
        final boolean q = com.easypano.tw.h.q;
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
                if (bt.o(this.q) != null && graphics != null) {
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
        final boolean q = com.easypano.tw.h.q;
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
        bt.h(this.q).pathStateChanged(bt.p(this.q), 10);
        bt.q(this.q).c();
    }
    
    public void h() {
        final boolean q = com.easypano.tw.h.q;
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
        final cd q2 = bt.q(this.q);
        if (!q) {
            if (q2 == null) {
                return;
            }
            bt.q(this.q);
        }
        q2.b();
        bt.h(this.q).pathStateChanged(bt.p(this.q), 11);
    }
    
    public void i() {
        this.a(true);
    }
    
    public void a(final boolean b) {
        final boolean q = com.easypano.tw.h.q;
        ed ed = this;
        Label_0074: {
            if (!q) {
                Label_0073: {
                    if (this.h) {
                        ed = this;
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
                ed = this;
            }
        }
        final cd q2 = bt.q(ed.q);
        if (!q) {
            if (q2 == null) {
                return;
            }
            bt.h(this.q).pathStateChanged(bt.p(this.q), 12);
            bt.q(this.q);
        }
        q2.f();
    }
    
    public int j() {
        return 40;
    }
    
    public void run() {
        final boolean q = com.easypano.tw.h.q;
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
        while (true) {
            Label_3863: {
                if (this.c) {
                    if (!q) {
                        break;
                    }
                    break Label_3863;
                }
                try {
                    long n47 = 0L;
                    Label_3868: {
                        if (this.d) {
                            final long currentTimeMillis = System.currentTimeMillis();
                            this.e = true;
                            this.a.a();
                            final boolean f = this.f;
                            Label_0239: {
                                Label_0234: {
                                    ed ed = null;
                                    Label_0102: {
                                        if (!q) {
                                            if (!f) {
                                                break Label_0234;
                                            }
                                            ed = this;
                                            if (q) {
                                                break Label_0102;
                                            }
                                            bt.a(this.q);
                                        }
                                        if (!f) {
                                            break Label_0234;
                                        }
                                        ed = this;
                                    }
                                    ed.g = true;
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
                            ed ed2 = this;
                            ed ed3 = this;
                            if (!q) {
                                Label_3537: {
                                    if (this.h) {
                                        this.i = true;
                                        int m;
                                        int g2;
                                        final int n11 = g2 = (m = this.m);
                                        if (!q) {
                                            Label_1813: {
                                                switch (n11) {
                                                    case 0: {
                                                        if (q) {
                                                            break Label_1813;
                                                        }
                                                        break Label_3537;
                                                    }
                                                    case 1: {
                                                        final int i = this.j;
                                                        final int n12 = -1;
                                                        Label_0428: {
                                                            int n13 = 0;
                                                            Label_0426: {
                                                                if (!q) {
                                                                    if (i == n12) {
                                                                        n4 = 1000L;
                                                                        n3 = 0;
                                                                        if (!q) {
                                                                            break Label_0428;
                                                                        }
                                                                    }
                                                                    final int d;
                                                                    n13 = (d = bt.q(this.q).a(this.j).d);
                                                                    if (q) {
                                                                        break Label_0426;
                                                                    }
                                                                    final int d2 = bt.q(this.q).a(this.j + 1).d;
                                                                }
                                                                if (i != n12) {
                                                                    n4 = bt.q(this.q).a(this.j).e;
                                                                    n4 = bt.q(this.q).a(this.j + 1).e - n4;
                                                                    n3 = 0;
                                                                    if (!q) {
                                                                        break Label_0428;
                                                                    }
                                                                }
                                                                n4 = 1000L;
                                                                n13 = 1;
                                                            }
                                                            n3 = n13;
                                                        }
                                                        int n17;
                                                        int f2;
                                                        int n16;
                                                        int n15;
                                                        final int n14 = n15 = (n16 = (f2 = (n17 = bt.r(this.q))));
                                                        if (!q) {
                                                            if (n14 != -1) {
                                                                bt.h(this.q).actionOnPanoSwitching(bt.r(this.q), bt.c(this.q).d(), bt.c(this.q).f(), bt.c(this.q).e());
                                                            }
                                                            final int n18;
                                                            n15 = (n18 = (n16 = (f2 = (n17 = lcmp(n4, 0L)))));
                                                        }
                                                        Label_1764: {
                                                            if (!q) {
                                                                if (n14 <= 0) {
                                                                    break Label_1764;
                                                                }
                                                                this.q.a(false);
                                                                g = bt.h(this.q).h().g.a(cc.d).g;
                                                                n15 = (f2 = (n17 = g));
                                                            }
                                                            final int n19;
                                                            Label_0569: {
                                                                if (!q) {
                                                                    if (n15 == 3) {
                                                                        n19 = (f2 = (n17 = bt.r(this.q)));
                                                                        if (q) {
                                                                            break Label_0569;
                                                                        }
                                                                        if (n19 == -1) {
                                                                            g = 1;
                                                                        }
                                                                    }
                                                                    f2 = (n16 = (n17 = g));
                                                                }
                                                            }
                                                            Label_0680: {
                                                                if (!q) {
                                                                    switch (n19) {
                                                                        case 0: {
                                                                            e = bt.c(this.q).e();
                                                                            n17 = (f2 = bt.f(this.q));
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
                                                                            case 0:
                                                                            case 1: {
                                                                                n2 = 0.7853999733924866;
                                                                                if (q) {
                                                                                    break;
                                                                                }
                                                                                break Label_0665;
                                                                            }
                                                                        }
                                                                        n17 = this.q.getBounds().width / 2;
                                                                    }
                                                                    n2 = n17;
                                                                }
                                                                n5 = System.currentTimeMillis();
                                                                this.m = 3;
                                                                if (!q) {
                                                                    break Label_1813;
                                                                }
                                                            }
                                                            bt.a(this.q, bt.h(this.q).h().g.a(cc.d));
                                                            final Image image = (Image)bt.h(this.q).h().d.a(bt.b(this.q).k, 1);
                                                            Label_0780: {
                                                                if (!q) {
                                                                    if (image != null && image.getWidth(dt.d) != -1) {
                                                                        break Label_0780;
                                                                    }
                                                                    this.a(false);
                                                                    this.q.a(true);
                                                                }
                                                                if (!q) {
                                                                    break Label_3537;
                                                                }
                                                            }
                                                            bt.c(this.q, cc.d);
                                                            bt.d(this.q, bt.b(this.q).d);
                                                            final bt q2 = this.q;
                                                            if (!q) {
                                                                if (bt.c(q2) != null) {
                                                                    bt.c(this.q).c();
                                                                    System.gc();
                                                                }
                                                                final bt q3 = this.q;
                                                            }
                                                            final int f3 = bt.f(q2);
                                                            Label_1250: {
                                                                Label_1074: {
                                                                    Label_0949: {
                                                                        final bt q4;
                                                                        Label_0927: {
                                                                            if (!q) {
                                                                                switch (f3) {
                                                                                    case 1:
                                                                                    case 2: {
                                                                                        q4 = this.q;
                                                                                        if (!q) {
                                                                                            bt.f(q4);
                                                                                            break;
                                                                                        }
                                                                                        break Label_0927;
                                                                                    }
                                                                                    case 0: {
                                                                                        break Label_1074;
                                                                                    }
                                                                                }
                                                                            }
                                                                            if (f3 == 3) {
                                                                                bt.a(this.q, bt.s(this.q).get(a("*](p7\rA6I5\b]!k")));
                                                                                if (!q) {
                                                                                    break Label_0949;
                                                                                }
                                                                            }
                                                                            final bt q5 = this.q;
                                                                        }
                                                                        bt.a(q4, bt.s(this.q).get(a(":T,|+\ft(x \fV")));
                                                                    }
                                                                    final bt q6 = this.q;
                                                                    final bt q7 = this.q;
                                                                    final bt q8 = this.q;
                                                                    final double n20 = bt.c(this.q).g() * 1000.0 / this.j();
                                                                    bt.d(q8, n20);
                                                                    bt.e(q7, n20);
                                                                    bt.f(q6, n20);
                                                                    final bt q9 = this.q;
                                                                    final bt q10 = this.q;
                                                                    final bt q11 = this.q;
                                                                    final double g3 = bt.c(this.q).g();
                                                                    bt.g(q11, g3);
                                                                    bt.h(q10, g3);
                                                                    bt.i(q9, g3);
                                                                    final bt q12 = this.q;
                                                                    final bt q13 = this.q;
                                                                    final double n21 = 3.141592653589793;
                                                                    bt.j(q13, n21);
                                                                    bt.k(q12, n21);
                                                                    final bt q14 = this.q;
                                                                    final bt q15 = this.q;
                                                                    final bt q16 = this.q;
                                                                    final double n22 = 0.017453292519943295;
                                                                    bt.l(q16, n22);
                                                                    bt.m(q15, n22);
                                                                    bt.n(q14, n22);
                                                                    if (!q) {
                                                                        break Label_1250;
                                                                    }
                                                                }
                                                                bt.a(this.q, bt.s(this.q).get(a("/H%m\t\u0005E=|+")));
                                                                final bt q17 = this.q;
                                                                final bt q18 = this.q;
                                                                final bt q19 = this.q;
                                                                final double n23 = bt.c(this.q).g() * 1000.0 / bt.i(this.q).j();
                                                                bt.d(q19, n23);
                                                                bt.e(q18, n23);
                                                                bt.f(q17, n23);
                                                                final bt q20 = this.q;
                                                                final bt q21 = this.q;
                                                                final bt q22 = this.q;
                                                                final double g4 = bt.c(this.q).g();
                                                                bt.g(q22, g4);
                                                                bt.h(q21, g4);
                                                                bt.i(q20, g4);
                                                                final bt q23 = this.q;
                                                                final bt q24 = this.q;
                                                                final double n24 = this.q.getBounds().width;
                                                                bt.j(q24, n24);
                                                                bt.k(q23, n24);
                                                                bt.n(this.q, bt.t(this.q));
                                                                bt.m(this.q, bt.u(this.q));
                                                                bt.l(this.q, bt.v(this.q));
                                                            }
                                                            final int[] b = dt.b(image);
                                                            bt.b(this.q).m = image.getWidth(this.q);
                                                            bt.b(this.q).n = image.getHeight(this.q);
                                                            final a c = bt.c(this.q);
                                                            Label_1364: {
                                                                if (!q) {
                                                                    if (c instanceof c) {
                                                                        bt.c(this.q).a(bt.b(this.q), image, this.q);
                                                                        if (!q) {
                                                                            break Label_1364;
                                                                        }
                                                                    }
                                                                    bt.c(this.q);
                                                                }
                                                                c.a(bt.b(this.q), b, this.q);
                                                            }
                                                            System.gc();
                                                            bt.c(this.q).a(this.q.getBounds().width, this.q.getBounds().height);
                                                            bt.c(this.q).c(cc.f);
                                                            bt.c(this.q).i(cc.g);
                                                            bt.c(this.q).f(cc.h);
                                                            final int width = this.q.getBounds().width;
                                                            final int height = this.q.getBounds().height;
                                                            final Image image2 = this.q.createImage(width, height);
                                                            final Graphics graphics = image2.getGraphics();
                                                            Label_1587: {
                                                                try {
                                                                    if (!q) {
                                                                        if (bt.o(this.q) == null) {
                                                                            graphics.setColor(this.q.getBackground());
                                                                            graphics.fillRect(0, 0, width, height);
                                                                            if (!q) {
                                                                                break Label_1587;
                                                                            }
                                                                        }
                                                                        graphics.drawImage(bt.o(this.q), 0, 0, this.q);
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
                                                            Label_1642: {
                                                                switch (g) {
                                                                    case 0: {
                                                                        bt.w(this.q).a(bt.x(this.q));
                                                                        if (q) {
                                                                            break Label_1642;
                                                                        }
                                                                        break;
                                                                    }
                                                                    case 1: {
                                                                        bt.w(this.q).a(bt.y(this.q));
                                                                        break;
                                                                    }
                                                                }
                                                            }
                                                            bt.c(this.q).k();
                                                            bt.w(this.q).a(image2, bt.c(this.q).a());
                                                            bt.w(this.q).a(this.q.getBounds().getSize());
                                                            bt.w(this.q).a();
                                                            bt.w(this.q).a(0);
                                                            this.q.a(bt.w(this.q).b());
                                                            n5 = System.currentTimeMillis();
                                                            this.m = 3;
                                                            if (!q) {
                                                                break Label_1813;
                                                            }
                                                        }
                                                        this.q.a(cc.d, cc.f, cc.g, cc.h, 0, true);
                                                        this.m = 4;
                                                        bt.q(this.q).c();
                                                        if (q) {
                                                            break Label_1813;
                                                        }
                                                        break Label_3537;
                                                    }
                                                    case 2: {
                                                        int n25 = (int)((System.currentTimeMillis() - n5) * 100L / n4);
                                                        int d3;
                                                        int n28;
                                                        int n27;
                                                        final int n26 = n27 = (n28 = (d3 = n25));
                                                        if (!q) {
                                                            if (n26 > 100) {
                                                                n25 = 100;
                                                            }
                                                            final int n29;
                                                            n27 = (n29 = (n28 = (d3 = g)));
                                                        }
                                                        int n37 = 0;
                                                        Label_2975: {
                                                            final int n30;
                                                            final int n31;
                                                            Label_2942: {
                                                                Label_2933: {
                                                                    if (!q) {
                                                                        switch (n26) {
                                                                            case 0: {
                                                                                bt.c(this.q).f((float)(e * (100 - n25) + n2 * n25) / 100.0f);
                                                                                bt.c(this.q).a(1);
                                                                                this.q.repaint();
                                                                                n30 = n25;
                                                                                n31 = 100;
                                                                                if (q) {
                                                                                    break Label_2942;
                                                                                }
                                                                                if (n30 != n31) {
                                                                                    break Label_2933;
                                                                                }
                                                                                bt.q(this.q).b();
                                                                                bt.a(this.q, bt.h(this.q).h().g.a(cc.d));
                                                                                final Image image3 = (Image)bt.h(this.q).h().d.a(bt.b(this.q).k, 1);
                                                                                Label_2049: {
                                                                                    if (!q) {
                                                                                        if (image3 != null && image3.getWidth(dt.d) != -1) {
                                                                                            break Label_2049;
                                                                                        }
                                                                                        this.a(false);
                                                                                        this.q.a(true);
                                                                                    }
                                                                                    if (!q) {
                                                                                        break Label_3537;
                                                                                    }
                                                                                }
                                                                                bt.c(this.q, cc.d);
                                                                                bt.d(this.q, bt.b(this.q).d);
                                                                                final bt q25 = this.q;
                                                                                if (!q) {
                                                                                    if (bt.c(q25) != null) {
                                                                                        bt.c(this.q).c();
                                                                                        System.gc();
                                                                                    }
                                                                                    final bt q26 = this.q;
                                                                                }
                                                                                final int f4 = bt.f(q25);
                                                                                Label_2589: {
                                                                                    Label_2342: {
                                                                                        Label_2217: {
                                                                                            final bt q27;
                                                                                            Label_2195: {
                                                                                                if (!q) {
                                                                                                    switch (f4) {
                                                                                                        case 1:
                                                                                                        case 2: {
                                                                                                            q27 = this.q;
                                                                                                            if (!q) {
                                                                                                                bt.f(q27);
                                                                                                                break;
                                                                                                            }
                                                                                                            break Label_2195;
                                                                                                        }
                                                                                                        case 0: {
                                                                                                            break Label_2342;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                if (f4 == 3) {
                                                                                                    bt.a(this.q, bt.s(this.q).get(a("*](p7\rA6I5\b]!k")));
                                                                                                    if (!q) {
                                                                                                        break Label_2217;
                                                                                                    }
                                                                                                }
                                                                                                final bt q28 = this.q;
                                                                                            }
                                                                                            bt.a(q27, bt.s(this.q).get(a(":T,|+\ft(x \fV")));
                                                                                        }
                                                                                        final bt q29 = this.q;
                                                                                        final bt q30 = this.q;
                                                                                        final bt q31 = this.q;
                                                                                        final double n32 = bt.c(this.q).g() * 1000.0 / this.j();
                                                                                        bt.d(q31, n32);
                                                                                        bt.e(q30, n32);
                                                                                        bt.f(q29, n32);
                                                                                        final bt q32 = this.q;
                                                                                        final bt q33 = this.q;
                                                                                        final bt q34 = this.q;
                                                                                        final double g5 = bt.c(this.q).g();
                                                                                        bt.g(q34, g5);
                                                                                        bt.h(q33, g5);
                                                                                        bt.i(q32, g5);
                                                                                        final bt q35 = this.q;
                                                                                        final bt q36 = this.q;
                                                                                        final double n33 = 3.141592653589793;
                                                                                        bt.j(q36, n33);
                                                                                        bt.k(q35, n33);
                                                                                        final bt q37 = this.q;
                                                                                        final bt q38 = this.q;
                                                                                        final bt q39 = this.q;
                                                                                        final double n34 = 0.017453292519943295;
                                                                                        bt.l(q39, n34);
                                                                                        bt.m(q38, n34);
                                                                                        bt.n(q37, n34);
                                                                                        if (!q) {
                                                                                            break Label_2589;
                                                                                        }
                                                                                    }
                                                                                    bt.a(this.q, bt.s(this.q).get(a("/H%m\t\u0005E=|+")));
                                                                                    bt.i(this.q, bt.c(this.q).g() * image3.getWidth(this.q) / 50.0);
                                                                                    bt.h(this.q, bt.c(this.q).g() * image3.getHeight(this.q) / 50.0);
                                                                                    bt.g(this.q, bt.c(this.q).g());
                                                                                    bt.f(this.q, bt.t(this.q) * 1000.0 / this.j());
                                                                                    bt.e(this.q, bt.u(this.q) * 1000.0 / this.j());
                                                                                    bt.d(this.q, bt.v(this.q) * 1000.0 / this.j());
                                                                                    final bt q40 = this.q;
                                                                                    final bt q41 = this.q;
                                                                                    final double n35 = this.q.getBounds().width;
                                                                                    bt.j(q41, n35);
                                                                                    bt.k(q40, n35);
                                                                                    bt.n(this.q, bt.t(this.q));
                                                                                    bt.m(this.q, bt.u(this.q));
                                                                                    bt.l(this.q, bt.v(this.q));
                                                                                }
                                                                                final int[] b2 = dt.b(image3);
                                                                                bt.b(this.q).m = image3.getWidth(this.q);
                                                                                bt.b(this.q).n = image3.getHeight(this.q);
                                                                                final a c2 = bt.c(this.q);
                                                                                Label_2703: {
                                                                                    if (!q) {
                                                                                        if (c2 instanceof c) {
                                                                                            bt.c(this.q).a(bt.b(this.q), image3, this.q);
                                                                                            if (!q) {
                                                                                                break Label_2703;
                                                                                            }
                                                                                        }
                                                                                        bt.c(this.q);
                                                                                    }
                                                                                    c2.a(bt.b(this.q), b2, this.q);
                                                                                }
                                                                                System.gc();
                                                                                bt.c(this.q).a(this.q.getBounds().width, this.q.getBounds().height);
                                                                                bt.c(this.q).c(cc.f);
                                                                                bt.c(this.q).i(cc.g);
                                                                                bt.c(this.q).f(cc.h);
                                                                                bt.a(this.q, bt.c(this.q).a());
                                                                                bt.c(this.q).a(1);
                                                                                if (q) {
                                                                                    break;
                                                                                }
                                                                                break Label_2933;
                                                                            }
                                                                        }
                                                                        bt.w(this.q).a(n25);
                                                                        n28 = (n27 = (d3 = bt.w(this.q).d()));
                                                                    }
                                                                    if (!q) {
                                                                        Label_2880: {
                                                                            switch (n27) {
                                                                                case 0: {
                                                                                    this.q.repaint();
                                                                                    if (q) {
                                                                                        break Label_2880;
                                                                                    }
                                                                                    break;
                                                                                }
                                                                                case 1: {
                                                                                    this.q.repaint();
                                                                                    break;
                                                                                }
                                                                            }
                                                                        }
                                                                        n28 = n25;
                                                                    }
                                                                    final int n36 = 100;
                                                                    if (q) {
                                                                        break Label_2942;
                                                                    }
                                                                    if (n28 == n36) {
                                                                        bt.a(this.q, bt.c(this.q).a());
                                                                        this.q.repaint();
                                                                        bt.w(this.q).e();
                                                                    }
                                                                }
                                                                n37 = (d3 = n25);
                                                                if (q) {
                                                                    break Label_2975;
                                                                }
                                                            }
                                                            if (n30 != n31) {
                                                                break Label_3537;
                                                            }
                                                            n7 = 1;
                                                            this.q.a(true);
                                                            this.m = 4;
                                                            this.q.repaint();
                                                            if (q) {
                                                                break;
                                                            }
                                                            n37 = n3;
                                                        }
                                                        if (n37 == 0) {
                                                            bt.q(this.q).b(n4);
                                                        }
                                                        bt.q(this.q).c();
                                                        break;
                                                    }
                                                }
                                            }
                                            final long n38;
                                            g2 = (int)(n38 = (m = lcmp(bt.q(this.q).d(), 0L)));
                                        }
                                        cc cc3 = null;
                                        final cc cc2;
                                        Label_3356: {
                                            Label_3354: {
                                                if (!q) {
                                                    if (n11 < 0) {
                                                        this.j = -1;
                                                        n7 = 1;
                                                        cc = bt.q(this.q).b(0);
                                                        cc2 = (cc3 = cc);
                                                        if (q) {
                                                            break Label_3356;
                                                        }
                                                        if (cc2 == null) {
                                                            break Label_3354;
                                                        }
                                                        bt.h(this.q).i().a(bt.q(this.q).i, true, true);
                                                        bt.q(this.q).a(System.currentTimeMillis());
                                                        if (!q) {
                                                            if (cc.d == bt.r(this.q)) {
                                                                bt.q(this.q).b((long)cc.e);
                                                                if (!q) {
                                                                    break Label_3354;
                                                                }
                                                            }
                                                            bt.q(this.q).b((long)(cc.e - 1000));
                                                        }
                                                        if (!q) {
                                                            break Label_3354;
                                                        }
                                                    }
                                                    cc = bt.q(this.q).d(System.currentTimeMillis());
                                                    m = (g2 = bt.q(this.q).g());
                                                }
                                                final cd q42;
                                                Label_3343: {
                                                    if (!q) {
                                                        Label_3222: {
                                                            switch (g2) {
                                                                case 0: {
                                                                    this.a(false);
                                                                    if (q) {
                                                                        break Label_3222;
                                                                    }
                                                                    break Label_3354;
                                                                }
                                                                case 1: {
                                                                    final int n39 = bt.q(this.q).h() - this.j;
                                                                    ed ed4 = null;
                                                                    Label_3303: {
                                                                        if (!q) {
                                                                            if (n39 >= 2) {
                                                                                cc = bt.q(this.q).b(this.j + 1);
                                                                                if (!q) {
                                                                                    break Label_3354;
                                                                                }
                                                                            }
                                                                            ed4 = this;
                                                                            if (q) {
                                                                                break Label_3303;
                                                                            }
                                                                            final boolean k = bt.q(this.q).j;
                                                                        }
                                                                        if (n39 != 0) {
                                                                            bt.q(this.q).f();
                                                                            if (!q) {
                                                                                break Label_3354;
                                                                            }
                                                                        }
                                                                        ed4 = this;
                                                                    }
                                                                    ed4.a(false);
                                                                    if (q) {
                                                                        break;
                                                                    }
                                                                    break Label_3354;
                                                                }
                                                            }
                                                        }
                                                        q42 = bt.q(this.q);
                                                        if (q) {
                                                            break Label_3343;
                                                        }
                                                        m = q42.h() - this.j;
                                                    }
                                                    if (m < 2) {
                                                        break Label_3354;
                                                    }
                                                    bt.q(this.q);
                                                }
                                                cc = q42.b(this.j + 1);
                                            }
                                            final cc cc4;
                                            cc3 = (cc4 = cc);
                                        }
                                        Label_3532: {
                                            if (!q) {
                                                if (cc2 == null) {
                                                    break Label_3532;
                                                }
                                                cc3 = cc;
                                            }
                                            if (cc3.d == bt.r(this.q)) {
                                                this.j = bt.q(this.q).h();
                                                bt.c(this.q).c(cc.f);
                                                bt.c(this.q).i(cc.g);
                                                bt.c(this.q).f(cc.h);
                                                if (!q) {
                                                    break Label_3537;
                                                }
                                            }
                                            bt.q(this.q).b();
                                            this.m = 1;
                                            final ci a = bt.h(this.q).h().g.a(cc.d);
                                            if (!q) {
                                                if (a != null) {
                                                    bt.h(this.q).h().d.a(a.k, this.b, 1);
                                                    if (!q) {
                                                        break Label_3537;
                                                    }
                                                }
                                                this.a(false);
                                            }
                                            if (!q) {
                                                break Label_3537;
                                            }
                                        }
                                        this.a(false);
                                    }
                                }
                                ed2 = this;
                                ed3 = this;
                            }
                            final ed ed5 = ed3;
                            synchronized (ed2) {
                                int n41;
                                final int n40 = n41 = bt.c(this.q).a(bt.z(this.q));
                                Label_3822: {
                                    Label_3820: {
                                        if (!q) {
                                            if (n40 > 0) {
                                                final int l = this.m;
                                                final int n42 = 4;
                                                final int n43;
                                                final int m2;
                                                Label_3670: {
                                                    if (!q) {
                                                        Label_3614: {
                                                            if (l == n42) {
                                                                final int n44;
                                                                n43 = (n44 = n7);
                                                                if (q) {
                                                                    break Label_3614;
                                                                }
                                                                if (n43 != 0) {
                                                                    bt.h(this.q).actionOnPanoSwitched(bt.r(this.q));
                                                                    n7 = 0;
                                                                }
                                                            }
                                                            m2 = this.m;
                                                        }
                                                        if (q) {
                                                            break Label_3670;
                                                        }
                                                    }
                                                    if (l == n42) {
                                                        bt.h(this.q).actionOnViewPortChanged(bt.c(this.q).d(), bt.c(this.q).f(), bt.c(this.q).e());
                                                    }
                                                    bt.z(this.q);
                                                }
                                                if (!q) {
                                                    switch (n43) {
                                                        case 0: {
                                                            n6 = 1;
                                                            if (q) {
                                                                break;
                                                            }
                                                            break Label_3820;
                                                        }
                                                    }
                                                }
                                                n6 = m2;
                                                if (!q) {
                                                    break Label_3820;
                                                }
                                            }
                                            final int n45;
                                            n41 = (n45 = n6);
                                        }
                                        if (!q) {
                                            if (n40 != 0) {
                                                bt.c(this.q).b(1);
                                                n6 = 0;
                                                if (!q) {
                                                    break Label_3820;
                                                }
                                            }
                                            final ed ed6 = this;
                                            if (q) {
                                                break Label_3822;
                                            }
                                            n41 = this.m;
                                        }
                                        if (n41 == 4) {
                                            final int n46 = n7;
                                            if (!q) {
                                                if (n46 == 0) {
                                                    break Label_3820;
                                                }
                                                bt.h(this.q).actionOnPanoSwitched(bt.r(this.q));
                                                bt.h(this.q).actionOnViewPortChanged(bt.c(this.q).d(), bt.c(this.q).f(), bt.c(this.q).e());
                                            }
                                            n7 = n46;
                                        }
                                    }
                                    final ed ed6 = ed5;
                                }
                            }
                            // monitorexit(ed6)
                            this.g = false;
                            this.i = false;
                            this.e = false;
                            n47 = j - System.currentTimeMillis() + currentTimeMillis;
                            if (!q) {
                                break Label_3868;
                            }
                        }
                        n47 = 10L;
                    }
                    final long n48 = n47;
                    Label_3905: {
                        if (!q) {
                            if (n48 <= 0L) {
                                break Label_3905;
                            }
                        }
                        try {
                            Thread.sleep(n48);
                            continue;
                        }
                        catch (InterruptedException ex4) {
                            continue;
                        }
                        catch (Exception ex2) {
                            ex2.printStackTrace();
                            if (!q) {
                                continue;
                            }
                        }
                    }
                    Thread.yield();
                }
                catch (Exception ex3) {
                    ed ed7 = this;
                    Label_3985: {
                        if (!q) {
                            if (this.isInterrupted()) {
                                break Label_3985;
                            }
                            System.out.println(a(",V6v+IM*9+\u001cJj"));
                            ex3.printStackTrace();
                            this.d = true;
                            bt.a(this.q, true);
                            this.f = false;
                            this.h = false;
                            this.i = false;
                            this.g = false;
                            ed7 = this;
                        }
                        ed7.m = 4;
                        if (!q) {
                            continue;
                        }
                    }
                    System.out.println(a(" J0|+\u001bQ4m<\r"));
                }
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
                            c2 = 'i';
                            break;
                        }
                        case 1: {
                            c2 = '$';
                            break;
                        }
                        case 2: {
                            c2 = 'D';
                            break;
                        }
                        case 3: {
                            c2 = '\u0019';
                            break;
                        }
                        default: {
                            c2 = 'Y';
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
