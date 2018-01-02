// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tw;

import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.awt.Container;
import com.easypano.tw.d.p;
import com.easypano.tw.d.j;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.awt.Component;
import com.easypano.tw.b.c;
import com.easypano.tw.b.d;
import java.awt.event.KeyListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import com.easypano.tw.a.b;
import com.easypano.tw.b.a;
import java.util.Hashtable;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;

public final class bt extends Panel implements db
{
    public static final int a = 1;
    public static final int b = 2;
    public static final int c = 3;
    public static final int d = 4;
    public static final String e = "\u0002I\u001c< 4i\u00188+4K";
    public static final String f = "\u0012@\u00180<5\\\u0006\t>0@\u0011+";
    public static final String g = "\u0017U\u0015-\u0002=X\r< ";
    private Image h;
    private Graphics i;
    private Image j;
    private Image k;
    private Hashtable l;
    private volatile a m;
    private int n;
    private ci o;
    private volatile int p;
    private volatile cd q;
    private volatile int r;
    private volatile int s;
    private double t;
    private double u;
    private double v;
    private double w;
    private double x;
    private double y;
    private double z;
    private double A;
    private double B;
    private double C;
    private double D;
    private int E;
    private int F;
    private volatile boolean G;
    private volatile boolean H;
    private TWViewer I;
    private ed J;
    private cq K;
    private com.easypano.tw.a.a L;
    private b M;
    private volatile boolean N;
    private volatile boolean O;
    private du P;
    protected f Q;
    
    public bt(final TWViewer twViewer) {
        this.h = null;
        this.i = null;
        this.j = null;
        this.k = null;
        this.l = new Hashtable();
        this.m = null;
        this.n = 2;
        this.o = null;
        this.p = -1;
        this.q = null;
        this.r = -1;
        this.s = 1;
        this.G = true;
        this.H = true;
        this.J = new ed(this);
        this.K = new cq();
        this.L = new com.easypano.tw.a.a();
        this.M = new b();
        this.N = true;
        this.O = false;
        this.P = null;
        this.Q = null;
        this.a(twViewer);
        try {
            this.a();
            this.b();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    void a() throws Exception {
        this.setLayout(null);
        final bc bc = new bc(this);
        this.addMouseListener(bc);
        this.addMouseMotionListener(bc);
        this.addKeyListener(new u(this));
    }
    
    private void b() {
        this.l.put(b("\u0002I\u001c< 4i\u00188+4K"), new d());
        this.l.put(b("\u0012@\u00180<5\\\u0006\t>0@\u0011+"), new com.easypano.tw.b.b());
        this.l.put(b("\u0017U\u0015-\u0002=X\r< "), new c());
        this.m = this.l.get(b("\u0002I\u001c< 4i\u00188+4K"));
        this.K.a(this);
        this.J.start();
    }
    
    public void destroyResource() {
        if (this.J != null) {
            this.J.a();
            this.J = null;
        }
        if (this.l != null) {
            this.l.get(b("\u0002I\u001c< 4i\u00188+4K")).b();
            this.l.get(b("\u0012@\u00180<5\\\u0006\t>0@\u0011+")).b();
            this.l.get(b("\u0017U\u0015-\u0002=X\r< ")).b();
            this.l.clear();
            this.l = null;
        }
        if (this.K != null) {
            this.K.f();
            this.K = null;
        }
        if (this.L != null) {
            this.L.b();
            this.L = null;
        }
        if (this.M != null) {
            this.M.b();
            this.M = null;
        }
        this.P = null;
        this.Q = null;
        this.j = null;
        this.k = null;
        this.I = null;
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final boolean q = com.easypano.tw.h.q;
        final Image h = this.h;
        Label_0128: {
            bt bt = null;
            Label_0106: {
                if (!q) {
                    if (h == null) {
                        final Rectangle bounds = this.getBounds();
                        this.h = this.createImage(bounds.width, bounds.height);
                        this.i = this.h.getGraphics();
                    }
                    bt = this;
                    if (q) {
                        break Label_0106;
                    }
                    final Image j = this.j;
                }
                if (h != null) {
                    synchronized (this.J) {
                        this.i.drawImage(this.j, 0, 0, this);
                        // monitorexit(this.J)
                        break Label_0128;
                    }
                }
                this.i.setColor(this.getBackground());
                bt = this;
            }
            bt.i.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
        }
        final dv[] l = this.m.l();
        int n = l.length - 1;
        while (true) {
            while (true) {
                Label_0163: {
                    if (!q) {
                        break Label_0163;
                    }
                    l[n].a(this.i);
                    --n;
                }
                if (n >= 0) {
                    continue;
                }
                break;
            }
            if (!q) {
                bt bt2 = this;
                if (!q) {
                    if (this.k != null) {
                        this.i.drawImage(this.k, 0, 0, this);
                    }
                    bt2 = this;
                }
                bt2.paint(this.i);
                graphics.drawImage(this.h, 0, 0, this);
                return;
            }
            continue;
        }
    }
    
    public void a(final du p) {
        this.P = p;
    }
    
    public void a(final String s) {
        this.a(s, Color.yellow);
    }
    
    public void a(final String s, final Color color) {
        this.a(s, color, null);
    }
    
    public void a(final String s, final Color background, final Color color) {
        final boolean q = com.easypano.tw.h.q;
        String s2 = s;
        Label_0034: {
            Label_0024: {
                if (!q) {
                    if (s == null) {
                        break Label_0024;
                    }
                    s2 = s;
                }
                if (!s2.equals("")) {
                    break Label_0034;
                }
            }
            this.Q = null;
            if (!q) {
                return;
            }
        }
        final h q2 = new h();
        q2.setBackground(background);
        final j j = new j(q2);
        j.d(color);
        q2.a(j);
        q2.e().a(s);
        this.Q = q2;
    }
    
    public void a(final f q) {
        final boolean q2 = com.easypano.tw.h.q;
        bt bt = null;
        Label_0052: {
            final f q3;
            Label_0048: {
                if (!q2) {
                    if (q != null) {
                        q3 = this.Q;
                        if (q2) {
                            break Label_0048;
                        }
                        if (q3 != null) {
                            final f q4 = this.Q;
                            if (q2) {
                                break Label_0048;
                            }
                            if (q4.equals(q)) {
                                return;
                            }
                        }
                    }
                    bt = this;
                    if (q2) {
                        break Label_0052;
                    }
                    this.Q = q;
                }
            }
            if (q3 != null) {
                return;
            }
            bt = this;
        }
        bt.P.a((Component)this);
    }
    
    public void a(final Image j) {
        (this.j = j).flush();
        this.repaint();
    }
    
    public void b(final Image k) {
        this.k = k;
    }
    
    public void a(final TWViewer i) {
        this.I = i;
        this.K.a(i);
    }
    
    private void a(final int n) {
        switch (n) {
            case 1:
            case 3: {
                this.n = n;
                if (com.easypano.tw.h.q) {
                    break;
                }
                return;
            }
        }
        this.n = 2;
    }
    
    public synchronized void a(final int n, final double n2, final double n3, final double n4) {
        this.a(n, n2, n3, n4, 0);
    }
    
    public synchronized void a(final int n, final double n2, final double n3, final double n4, final int n5) {
        try {
            this.a(n, n2, n3, n4, n5, false);
        }
        catch (Exception ex) {
            System.out.println(b("\u0014K\u00066 qP\u001ay!4M':7?\\"));
            ex.printStackTrace();
        }
        finally {
            this.J.b();
        }
        this.J.b();
    }
    
    public synchronized void a(final int n, final double n2, final double n3, final double n4, final int n5, final boolean b) throws Exception {
        try {
            this.a(n, n2, n3, n4, n5, this.I.h().g.a(n).y, false);
        }
        catch (Exception ex) {
            System.out.println(b("\u0014K\u00066 qP\u001ay!4M':7?\\"));
            ex.printStackTrace();
        }
    }
    
    public synchronized void a(final int p7, final double n, final double n2, final double n3, final int n4, final int n5, final boolean b) throws Exception {
        final boolean q = com.easypano.tw.h.q;
        this.N = false;
        Label_1757: {
            try {
                int n6 = p7;
                int n7 = p7;
                int n8 = p7;
                int n9 = p7;
                if (!q) {
                    if (p7 < 0) {
                        break Label_1757;
                    }
                    n6 = p7;
                    n7 = p7;
                    n8 = p7;
                    n9 = p7;
                }
                if (!q) {
                    if (n9 >= this.I.h().g.a()) {
                        break Label_1757;
                    }
                    n7 = (n8 = (n6 = (this.G ? 1 : 0)));
                }
                if (!q) {
                    if (n8 == 0) {
                        break Label_1757;
                    }
                    n6 = (b ? 1 : 0);
                    n7 = (b ? 1 : 0);
                }
                bt bt = null;
                Label_1631: {
                    if (!q) {
                        if (n7 == 0) {
                            this.J.d();
                            this.J.h();
                            this.J.c();
                        }
                        bt = this;
                        if (q) {
                            break Label_1631;
                        }
                        n6 = this.p;
                    }
                    Label_1613: {
                        if (n6 != p7) {
                            int n10 = 0;
                            this.o = this.I.h().g.a(p7);
                            final Image image2;
                            final Image image = image2 = (Image)this.I.h().d.a(this.o.k, 1);
                            Label_0171: {
                                if (q || image2 != null) {
                                    final int width = image2.getWidth(dt.d);
                                    final int n11 = -1;
                                    int g = 0;
                                    Label_0296: {
                                        if (!q) {
                                            if (width == n11) {
                                                break Label_0171;
                                            }
                                            final int p8;
                                            g = (p8 = this.p);
                                            if (q) {
                                                break Label_0296;
                                            }
                                        }
                                        if (width != n11) {
                                            this.I.actionOnPanoSwitching(this.p, this.m.d(), this.m.f(), this.m.e());
                                        }
                                        g = this.I.h().g.a(p7).g;
                                    }
                                    int n12 = g;
                                    if (!q) {
                                        Label_0597: {
                                            if (n4 > 0) {
                                                int n14;
                                                final int n13 = n14 = n12;
                                                if (!q) {
                                                    switch (n13) {
                                                        case 0:
                                                        case 1: {
                                                            if (q) {
                                                                break;
                                                            }
                                                            break Label_0597;
                                                        }
                                                    }
                                                    final int p9;
                                                    n14 = (p9 = this.p);
                                                }
                                                if (!q) {
                                                    if (n13 != -1) {
                                                        final double e = this.m.e();
                                                        final int n15 = this.n;
                                                        double n16 = 0.0;
                                                        Label_0418: {
                                                            if (!q) {
                                                                switch (n15) {
                                                                    case 0:
                                                                    case 1: {
                                                                        n16 = 0.7853999733924866;
                                                                        if (q) {
                                                                            break;
                                                                        }
                                                                        break Label_0418;
                                                                    }
                                                                }
                                                                final int n17 = this.getBounds().width / 2;
                                                            }
                                                            n16 = n15;
                                                        }
                                                        final long currentTimeMillis = System.currentTimeMillis();
                                                        final Graphics graphics = this.getGraphics();
                                                        this.getBounds();
                                                        try {
                                                            while (true) {
                                                                Label_0537: {
                                                                    if (!q) {
                                                                        break Label_0537;
                                                                    }
                                                                    final int n18;
                                                                    n10 = (n18 = (int)((System.currentTimeMillis() - currentTimeMillis) * 100L / n4));
                                                                    if (q || n18 > 100) {
                                                                        n10 = n18;
                                                                    }
                                                                    this.m.f((float)(e * (100 - n10) + n16 * n10) / 100.0f);
                                                                    this.m.a(1);
                                                                    this.paint(graphics);
                                                                    try {
                                                                        Thread.sleep(20L);
                                                                    }
                                                                    catch (Exception ex) {
                                                                        ex.printStackTrace();
                                                                    }
                                                                }
                                                                if (n10 < 100) {
                                                                    continue;
                                                                }
                                                                break;
                                                            }
                                                        }
                                                        catch (Exception ex2) {
                                                            ex2.printStackTrace();
                                                        }
                                                        finally {
                                                            graphics.dispose();
                                                        }
                                                        graphics.dispose();
                                                        if (!q) {
                                                            break Label_0597;
                                                        }
                                                    }
                                                    n14 = 1;
                                                }
                                                n12 = n14;
                                            }
                                        }
                                        this.p = p7;
                                        this.a(this.o.d);
                                    }
                                    bt bt2 = this;
                                    if (!q) {
                                        if (this.m != null) {
                                            this.m.c();
                                            System.gc();
                                        }
                                        bt2 = this;
                                    }
                                    final int n19 = bt2.n;
                                    Label_0932: {
                                        Label_0817: {
                                            Label_0728: {
                                                bt bt3 = null;
                                                Label_0710: {
                                                    if (!q) {
                                                        switch (n19) {
                                                            case 1:
                                                            case 2: {
                                                                bt3 = this;
                                                                if (!q) {
                                                                    final int n20 = this.n;
                                                                    break;
                                                                }
                                                                break Label_0710;
                                                            }
                                                            case 0: {
                                                                break Label_0817;
                                                            }
                                                        }
                                                    }
                                                    if (n19 == 3) {
                                                        this.m = this.l.get(b("\u0012@\u00180<5\\\u0006\t>0@\u0011+"));
                                                        if (!q) {
                                                            break Label_0728;
                                                        }
                                                    }
                                                    bt3 = this;
                                                }
                                                bt3.m = this.l.get(b("\u0002I\u001c< 4i\u00188+4K"));
                                            }
                                            final double t = this.m.g() * 1000.0 / this.J.j();
                                            this.v = t;
                                            this.u = t;
                                            this.t = t;
                                            final double g2 = this.m.g();
                                            this.y = g2;
                                            this.x = g2;
                                            this.w = g2;
                                            final double n21 = 3.141592653589793;
                                            this.A = n21;
                                            this.z = n21;
                                            final double b2 = 0.017453292519943295;
                                            this.D = b2;
                                            this.C = b2;
                                            this.B = b2;
                                            if (!q) {
                                                break Label_0932;
                                            }
                                        }
                                        this.m = this.l.get(b("\u0017U\u0015-\u0002=X\r< "));
                                        final double t2 = this.m.g() * 1000.0 / this.J.j();
                                        this.v = t2;
                                        this.u = t2;
                                        this.t = t2;
                                        final double g3 = this.m.g();
                                        this.y = g3;
                                        this.x = g3;
                                        this.w = g3;
                                        final double n22 = this.getBounds().width;
                                        this.A = n22;
                                        this.z = n22;
                                        this.B = this.w;
                                        this.C = this.x;
                                        this.D = this.y;
                                    }
                                    final int[] b3 = dt.b(image);
                                    this.o.m = image.getWidth(this);
                                    this.o.n = image.getHeight(this);
                                    final a m = this.m;
                                    Label_1013: {
                                        if (!q) {
                                            if (m instanceof c) {
                                                this.m.a(this.o, image, this);
                                                if (!q) {
                                                    break Label_1013;
                                                }
                                            }
                                            final a i = this.m;
                                        }
                                        m.a(this.o, b3, this);
                                    }
                                    System.gc();
                                    final Rectangle bounds = this.getBounds();
                                    this.m.a(bounds.width, bounds.height);
                                    this.m.c(n);
                                    this.m.i(n2);
                                    this.m.f(n3);
                                    Label_1579: {
                                        if (!q) {
                                            if (n4 > 0) {
                                                final int n23 = n12;
                                                if (q) {
                                                    break Label_1579;
                                                }
                                                if (n23 != 3) {
                                                    final int n24 = n12;
                                                    if (!q) {
                                                        Label_1136: {
                                                            switch (n24) {
                                                                case 0: {
                                                                    this.K.a(this.L);
                                                                    if (q) {
                                                                        break Label_1136;
                                                                    }
                                                                    break;
                                                                }
                                                                case 1: {
                                                                    this.K.a(this.M);
                                                                    break;
                                                                }
                                                            }
                                                        }
                                                        final int width2 = bounds.width;
                                                    }
                                                    final int n25 = n24;
                                                    final int height = bounds.height;
                                                    final int[] array = new int[n25 * height];
                                                    bt bt4 = this;
                                                    Label_1272: {
                                                        if (!q) {
                                                            if (this.j != null) {
                                                                final PixelGrabber pixelGrabber = new PixelGrabber(this.j, 0, 0, n25, height, array, 0, n25);
                                                                try {
                                                                    pixelGrabber.grabPixels();
                                                                }
                                                                catch (Exception ex3) {
                                                                    ex3.printStackTrace();
                                                                }
                                                                break Label_1272;
                                                            }
                                                            bt4 = this;
                                                        }
                                                        final int rgb = bt4.getBackground().getRGB();
                                                        final int n26 = n25 * height;
                                                        int n27 = 0;
                                                        while (true) {
                                                            Label_1222: {
                                                                if (!q) {
                                                                    break Label_1222;
                                                                }
                                                                array[n27] = rgb;
                                                                ++n27;
                                                            }
                                                            if (n27 < n26) {
                                                                continue;
                                                            }
                                                            break;
                                                        }
                                                    }
                                                    final Image image3 = this.createImage(new MemoryImageSource(n25, height, array, 0, n25));
                                                    dt.a(image3);
                                                    this.m.k();
                                                    this.K.a(image3, this.m.a());
                                                    this.K.a(bounds.getSize());
                                                    this.K.a();
                                                    this.K.a(0);
                                                    this.a(this.K.b());
                                                    final Graphics graphics2 = this.getGraphics();
                                                    try {
                                                        final long currentTimeMillis2 = System.currentTimeMillis();
                                                        while (true) {
                                                            Label_1489: {
                                                                if (!q) {
                                                                    break Label_1489;
                                                                }
                                                                final int n28;
                                                                n10 = (n28 = (int)((System.currentTimeMillis() - currentTimeMillis2) * 100L / n4));
                                                                Label_1479: {
                                                                    if (!q) {
                                                                        if (n28 > 100) {
                                                                            n10 = 100;
                                                                        }
                                                                        this.K.a(n10);
                                                                        if (q) {
                                                                            break Label_1479;
                                                                        }
                                                                        this.K.d();
                                                                    }
                                                                    Label_1467: {
                                                                        switch (n28) {
                                                                            case 0: {
                                                                                this.paint(graphics2);
                                                                                if (q) {
                                                                                    break Label_1467;
                                                                                }
                                                                                break;
                                                                            }
                                                                            case 1: {
                                                                                this.paint(graphics2);
                                                                                break;
                                                                            }
                                                                        }
                                                                    }
                                                                    try {
                                                                        Thread.sleep(20L);
                                                                    }
                                                                    catch (Exception ex4) {
                                                                        ex4.printStackTrace();
                                                                    }
                                                                }
                                                            }
                                                            if (n10 < 100) {
                                                                continue;
                                                            }
                                                            break;
                                                        }
                                                    }
                                                    catch (Exception ex5) {
                                                        ex5.printStackTrace();
                                                    }
                                                    finally {
                                                        graphics2.dispose();
                                                    }
                                                    graphics2.dispose();
                                                    this.K.e();
                                                }
                                            }
                                            this.I.actionOnPanoSwitched(p7);
                                            this.j = this.m.a();
                                            this.repaint();
                                            this.m.a(1);
                                        }
                                    }
                                    this.I.actionOnViewPortChanged(this.m.d(), this.m.f(), this.m.e());
                                    if (q) {
                                        break Label_1613;
                                    }
                                    break Label_1757;
                                }
                            }
                            if (!b) {
                                this.J.b();
                            }
                            return;
                        }
                    }
                    this.m.c(n);
                    this.m.i(n2);
                    bt = this;
                }
                bt.m.f(n3);
            }
            catch (Exception ex6) {
                ex6.printStackTrace();
            }
            finally {
                this.J.b();
                Label_1701: {
                    Label_1678: {
                        if (q) {
                            break Label_1678;
                        }
                        if (b) {
                            break Label_1701;
                        }
                        try {
                            Thread.sleep(50L);
                        }
                        catch (Exception ex7) {
                            ex7.printStackTrace();
                        }
                    }
                    this.a(n5, 0, 0);
                    this.a(true);
                }
            }
        }
        this.J.b();
        Label_1781: {
            if (q) {
                break Label_1781;
            }
            if (b) {
                return;
            }
            try {
                Thread.sleep(50L);
            }
            catch (Exception ex8) {
                ex8.printStackTrace();
            }
        }
        this.a(n5, 0, 0);
        this.a(true);
    }
    
    public a c() {
        return this.m;
    }
    
    public int d() {
        return this.p;
    }
    
    public cd e() {
        return this.q;
    }
    
    public void b(int s) {
        final boolean q = com.easypano.tw.h.q;
        final int n = s;
        Label_0038: {
            if (!q) {
                switch (n) {
                    case 0:
                    case 1: {
                        if (q) {
                            break;
                        }
                        break Label_0038;
                    }
                }
            }
            s = n;
        }
        this.s = s;
    }
    
    public void f() {
        this.a(0.0, 0.0, -this.v);
    }
    
    public void g() {
        this.a(0.0, 0.0, this.v);
    }
    
    public void h() {
        this.a(-this.t, 0.0, 0.0);
    }
    
    public void i() {
        this.a(this.t, 0.0, 0.0);
    }
    
    public void j() {
        this.a(0.0, this.u, 0.0);
    }
    
    public void k() {
        this.a(0.0, -this.u, 0.0);
    }
    
    public void a(final double n, double n2, final double n3) {
        final boolean q = com.easypano.tw.h.q;
        int n4;
        final boolean b = (n4 = (this.G ? 1 : 0)) != 0;
        if (!q) {
            if (!b) {
                return;
            }
            final boolean x;
            n4 = ((x = this.o.x) ? 1 : 0);
        }
        bt bt = null;
        Label_0094: {
            if (!q) {
                if (!b) {
                    return;
                }
                this.J.h();
                bt = this;
                if (q) {
                    break Label_0094;
                }
                n4 = this.n;
            }
            switch (n4) {
                case 1: {
                    n2 = -n2;
                    break;
                }
            }
            this.J.m = 4;
            this.J.a(n, n2, n3);
            bt = this;
        }
        bt.J.e();
    }
    
    public void a(final int n, final int n2, final int n3) {
        this.a(this.t * n / 20.0, this.u * n2 / 20.0, this.v * n3 / 20.0);
    }
    
    public void stopAutoPan() {
        if (this.G && !this.N) {
            this.J.d();
            this.J.h();
        }
    }
    
    public void c(final int r) {
        final boolean q = com.easypano.tw.h.q;
        int g;
        final int n = g = (this.G ? 1 : 0);
        if (!q) {
            if (n == 0) {
                return;
            }
            g = r;
        }
        if (!q) {
            if (n < 0) {
                return;
            }
            g = r;
        }
        if (g == this.r) {
            this.l();
            if (!q) {
                return;
            }
        }
        final cd a = this.I.h().f.a(r);
        if (!q) {
            if (a == null) {
                return;
            }
            this.J.d();
            this.J.i();
            this.r = r;
            this.q = a;
        }
        this.J.g();
    }
    
    public void l() {
        final boolean q = com.easypano.tw.h.q;
        final boolean g = this.G;
        final ed j;
        Label_0051: {
            if (!q) {
                if (!g) {
                    return;
                }
                this.J.d();
                j = this.J;
                if (q) {
                    break Label_0051;
                }
                j.f();
            }
            if (g) {
                this.J.h();
                if (!q) {
                    return;
                }
            }
            final ed i = this.J;
        }
        j.g();
    }
    
    public void stopMovie() {
        if (this.G && !this.N && this.q != null) {
            this.J.d();
            this.J.i();
            this.q.f();
            this.I.i().stopAudioClip();
            final cc a = this.q.a(0);
            final ci a2 = this.I.h().g.a(a.d);
            try {
                this.a(a.d, a.f, a.g, a.h, a2.h, 0, false);
            }
            catch (Exception ex) {
                System.out.println(b("\u0014K\u00066 qP\u001ay!%V\u0004\t3%Q"));
                ex.printStackTrace();
            }
        }
    }
    
    public void b(final double n, final double n2, final double n3) {
        bt bt = this;
        if (!com.easypano.tw.h.q) {
            if (!this.G) {
                return;
            }
            this.stopAutoPan();
            bt = this;
        }
        bt.J.a(new dj(this, n, n2, n3));
    }
    
    public void a(final boolean g) {
        this.G = g;
        this.N = false;
    }
    
    private void a(final double n) {
        bt bt = this;
        if (!com.easypano.tw.h.q) {
            if (!this.G) {
                return;
            }
            bt = this;
        }
        bt.J.a(new dk(this, n));
    }
    
    private void b(final double n) {
        bt bt = this;
        if (!com.easypano.tw.h.q) {
            if (!this.G) {
                return;
            }
            bt = this;
        }
        bt.J.a(new dl(this, n));
    }
    
    private void c(final double n) {
        bt bt = this;
        if (!com.easypano.tw.h.q) {
            if (!this.G) {
                return;
            }
            bt = this;
        }
        bt.J.a(new dm(this, n));
    }
    
    public void m() {
        bt bt = this;
        if (!com.easypano.tw.h.q) {
            if (!this.G) {
                return;
            }
            this.m.h();
            bt = this;
        }
        bt.repaint();
    }
    
    public void n() {
        bt bt = this;
        if (!com.easypano.tw.h.q) {
            if (!this.G) {
                return;
            }
            this.m.i();
            bt = this;
        }
        bt.repaint();
    }
    
    public void o() {
        bt bt = this;
        if (!com.easypano.tw.h.q) {
            if (!this.G) {
                return;
            }
            this.m.j();
            bt = this;
        }
        bt.repaint();
    }
    
    static boolean a(final bt bt) {
        return bt.G;
    }
    
    static ci b(final bt bt) {
        return bt.o;
    }
    
    static a c(final bt bt) {
        return bt.m;
    }
    
    static double d(final bt bt) {
        return bt.D;
    }
    
    static void a(final bt bt, final double n) {
        bt.c(n);
    }
    
    static double e(final bt bt) {
        return bt.B;
    }
    
    static void b(final bt bt, final double n) {
        bt.a(n);
    }
    
    static int f(final bt bt) {
        return bt.n;
    }
    
    static double g(final bt bt) {
        return bt.C;
    }
    
    static void c(final bt bt, final double n) {
        bt.b(n);
    }
    
    static TWViewer h(final bt bt) {
        return bt.I;
    }
    
    static ed i(final bt bt) {
        return bt.J;
    }
    
    static void a(final bt bt, final int e) {
        bt.E = e;
    }
    
    static void b(final bt bt, final int f) {
        bt.F = f;
    }
    
    static int j(final bt bt) {
        return bt.E;
    }
    
    static int k(final bt bt) {
        return bt.F;
    }
    
    static double l(final bt bt) {
        return bt.z;
    }
    
    static double m(final bt bt) {
        return bt.A;
    }
    
    static du n(final bt bt) {
        return bt.P;
    }
    
    static Image o(final bt bt) {
        return bt.j;
    }
    
    static int p(final bt bt) {
        return bt.r;
    }
    
    static cd q(final bt bt) {
        return bt.q;
    }
    
    static int r(final bt bt) {
        return bt.p;
    }
    
    static void a(final bt bt, final ci o) {
        bt.o = o;
    }
    
    static void c(final bt bt, final int p2) {
        bt.p = p2;
    }
    
    static void d(final bt bt, final int n) {
        bt.a(n);
    }
    
    static Hashtable s(final bt bt) {
        return bt.l;
    }
    
    static void a(final bt bt, final a m) {
        bt.m = m;
    }
    
    static void d(final bt bt, final double v) {
        bt.v = v;
    }
    
    static void e(final bt bt, final double u) {
        bt.u = u;
    }
    
    static void f(final bt bt, final double t) {
        bt.t = t;
    }
    
    static void g(final bt bt, final double y) {
        bt.y = y;
    }
    
    static void h(final bt bt, final double x) {
        bt.x = x;
    }
    
    static void i(final bt bt, final double w) {
        bt.w = w;
    }
    
    static void j(final bt bt, final double a) {
        bt.A = a;
    }
    
    static void k(final bt bt, final double z) {
        bt.z = z;
    }
    
    static void l(final bt bt, final double d) {
        bt.D = d;
    }
    
    static void m(final bt bt, final double c) {
        bt.C = c;
    }
    
    static void n(final bt bt, final double b) {
        bt.B = b;
    }
    
    static double t(final bt bt) {
        return bt.w;
    }
    
    static double u(final bt bt) {
        return bt.x;
    }
    
    static double v(final bt bt) {
        return bt.y;
    }
    
    static cq w(final bt bt) {
        return bt.K;
    }
    
    static com.easypano.tw.a.a x(final bt bt) {
        return bt.L;
    }
    
    static b y(final bt bt) {
        return bt.M;
    }
    
    static void a(final bt bt, final Image j) {
        bt.j = j;
    }
    
    static int z(final bt bt) {
        return bt.s;
    }
    
    static void a(final bt bt, final boolean g) {
        bt.G = g;
    }
    
    private static String b(final String s) {
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
                            c2 = 'Q';
                            break;
                        }
                        case 1: {
                            c2 = '9';
                            break;
                        }
                        case 2: {
                            c2 = 't';
                            break;
                        }
                        case 3: {
                            c2 = 'Y';
                            break;
                        }
                        default: {
                            c2 = 'R';
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
