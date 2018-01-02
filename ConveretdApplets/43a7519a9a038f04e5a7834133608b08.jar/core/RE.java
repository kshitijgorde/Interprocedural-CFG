// 
// Decompiled by Procyon v0.5.30
// 

package core;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import B.B;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.LayoutManager;
import java.awt.Color;
import java.net.MalformedURLException;
import java.net.URL;
import java.awt.Image;
import java.applet.Applet;

public final class RE extends Applet implements Runnable
{
    public int a;
    public int b;
    public S c;
    private Image i;
    private Z j;
    private Z k;
    private Z l;
    private T m;
    private final Thread n;
    public final I d;
    private K o;
    public P e;
    private D.Z p;
    public Y f;
    public final W g;
    public boolean h;
    private boolean q;
    
    public RE() {
        this.n = new Thread(this);
        this.d = new I();
        new E(this);
        this.n.setPriority(10);
        this.g = new W();
        this.q = false;
    }
    
    public final URL a() {
        try {
            return new URL(this.getCodeBase().getProtocol(), this.getCodeBase().getHost(), "");
        }
        catch (MalformedURLException ex) {
            return null;
        }
    }
    
    public final void init() {
        this.h = (this.getDocumentBase().getHost().endsWith(I.I.I(237)) && this.getParameter(I.I.I(254)) != null && this.getParameter(I.I.I(254)).equals(I.I.I(12)));
        final D.I a = D.I.a(this);
        final int n = this.getDocumentBase().getHost().endsWith(I.I.I(237)) ? 700 : 350;
        int n2 = 300;
        try {
            final int int1;
            if ((int1 = Integer.parseInt(this.getParameter(I.I.I(257)))) >= 180 && int1 <= n) {
                n2 = int1;
            }
        }
        catch (Exception ex) {}
        final int n3 = n2;
        this.b = n3;
        this.setSize(this.a = n3, this.b);
        String s;
        if ((s = this.getParameter(I.I.I(262))) == null) {
            s = I.I.I(14);
        }
        this.o = new K(new Z.Z(this, K.a(this.a(), I.I.I(276) + ((this.a > 500) ? (I.I.I(264) + s) : (I.I.I(270) + s)) + I.I.I(281))));
        this.setBackground(new Color(16777215));
        this.e = new P(this);
        this.f = new Y(this);
        int int2 = 16747008;
        try {
            int2 = Integer.parseInt(this.getParameter(I.I.I(286)), 16);
        }
        catch (Exception ex2) {}
        this.c = new S(this);
        this.setLayout(null);
        this.j = new Z(new FI(this));
        final V v;
        (v = new V(5, this.b - 40, 16, this.getImage(this.getClass().getResource(I.I.I(292))))).addMouseListener(this.j.b());
        v.setCursor(new Cursor(12));
        this.add(v);
        final V v2;
        (v2 = new V(5, this.b - 21, 16, this.getImage(this.getClass().getResource(I.I.I(311))))).addMouseListener(this.j.c());
        v2.setCursor(new Cursor(12));
        this.add(v2);
        try {
            this.getClass().getMethod(I.I.I(365), Class.forName(I.I.I(331))).invoke(this, new G(this));
        }
        catch (Exception ex3) {}
        this.k = new Z(new DI(this));
        final V v3;
        (v3 = new V(43, this.b - 40, 16, this.getImage(this.getClass().getResource(I.I.I(387))))).addMouseListener(this.k.b());
        v3.setCursor(new Cursor(12));
        this.add(v3);
        final V v4;
        (v4 = new V(43, this.b - 21, 16, this.getImage(this.getClass().getResource(I.I.I(405))))).addMouseListener(this.k.c());
        v4.setCursor(new Cursor(12));
        this.add(v4);
        this.l = new Z(new BI(this));
        final V v5;
        (v5 = new V(24, this.b - 40, 16, this.getImage(this.getClass().getResource(I.I.I(425))))).addMouseListener(this.l.b());
        v5.setCursor(new Cursor(12));
        this.add(v5);
        final V v6;
        (v6 = new V(24, this.b - 21, 16, this.getImage(this.getClass().getResource(I.I.I(445))))).addMouseListener(this.l.c());
        v6.setCursor(new Cursor(12));
        this.add(v6);
        final V v7;
        (v7 = new V(this.a - 85 - 5, this.b - 16 - 5, 85, this.getImage(this.getClass().getResource(I.I.I(466))))).setCursor(new Cursor(12));
        v7.addMouseListener(new CI(this));
        this.add(v7);
        if (!this.h) {
            final V v8;
            (v8 = new V(62, this.b - 21, 16, this.getImage(this.getClass().getResource(I.I.I(484))))).setCursor(new Cursor(12));
            v8.addMouseListener(new D(this));
            this.add(v8);
        }
        this.p = a.a(this, new C.I(K.a(this.a(), I.I.I(519) + ((this.getParameter(I.I.I(502)) != null) ? this.getParameter(I.I.I(502)) : I.I.I(511)) + I.I.I(56)), int2, this.o));
        this.validate();
    }
    
    public final boolean b() {
        return this.q;
    }
    
    public final synchronized void start() {
        if (!this.q) {
            this.q = true;
            this.p.start();
            this.n.start();
        }
    }
    
    public final synchronized void stop() {
        this.q = false;
        try {
            this.n.join();
        }
        catch (InterruptedException ex) {}
        this.getParent().removeAll();
    }
    
    public final void run() {
        final double n = (this.o.a - 1) / 6.283185307179586;
        final double n2 = (this.o.b - 1) / 3.141592653589793;
        final int n3 = this.a * this.b - this.a;
        final int n4 = this.a - 1;
        Graphics graphics;
        while ((graphics = this.getGraphics()) == null) {
            try {
                Thread.sleep(20L);
            }
            catch (InterruptedException ex) {}
        }
        this.m = new T(this, this.a, this.b);
        System.gc();
        final double a = this.c.a();
        final double b = this.c.b();
        int int1 = 0;
        try {
            int1 = Integer.parseInt(this.getParameter(I.I.I(1)), 16);
        }
        catch (NumberFormatException ex2) {}
        final int n5 = int1 >> 16 & 0xFF;
        final int n6 = int1 >> 8 & 0xFF;
        final int n7 = int1 & 0xFF;
        while (this.q) {
            this.d.a();
            this.c.c(this.d.b());
            Thread.yield();
            try {
                Thread.sleep(10L);
            }
            catch (InterruptedException ex3) {}
            this.j.a();
            this.k.a();
            this.l.a();
            final double c = this.c.c();
            final double d = this.c.d();
            final double cos = Math.cos(c);
            final double sin = Math.sin(c);
            final double cos2 = Math.cos(d);
            final double sin2 = Math.sin(d);
            this.g.a();
            this.g.a(-sin, -sin2, cos, cos2);
            final double e = this.c.e();
            int n8 = 0;
            final double n10;
            final double n9 = (n10 = e * e) - 1.0;
            final double n11 = n10 / n9;
            for (int i = 0; i < this.c.b; ++i) {
                final int n12 = this.a * i;
                final int n13 = n3 - n12;
                final double n15;
                final double n14 = n15 = b * i - 0.011;
                final double n16 = n14 * n14 + 1.0;
                for (int j = 0; j < this.c.a; ++j) {
                    final double n18;
                    final double n17 = n18 = a * j - 0.011;
                    final double n19;
                    if ((n19 = n17 * n17 + n16) < n11) {
                        final double n21;
                        final double n20 = (n21 = (e + Math.sqrt(n10 - n9 * n19)) / -n19) * n18;
                        final double n22 = cos2 * n20;
                        final double n23 = sin2 * n20;
                        final double n24 = n21 * n15;
                        final double n25 = cos * n24;
                        final double n26 = sin * n24;
                        final double n27 = -n21 - e;
                        final double n28 = sin * n27;
                        final double n29 = cos * n27;
                        final int n30 = n4 - j;
                        final double n31 = n28 - n25;
                        final double n32 = n26 + n29;
                        final double n33 = cos2 * n32;
                        final double n34 = sin2 * n32;
                        double acos = B.a.a(n31);
                        final double n35 = B.b.a(acos);
                        final double n37;
                        final double n36 = (n37 = n22 + sin2 * n32) / n35;
                        final double n39;
                        double n38;
                        if ((n38 = (n39 = ((n33 > n23) ? (6.283185307179586 - B.a.a(n36)) : B.a.a(n36)))) != n39) {
                            acos = Math.acos(n31);
                            final double n40 = n37 / Math.sin(acos);
                            n38 = ((n33 > n23) ? (6.283185307179586 - B.a.a(n40)) : B.a.a(n40));
                        }
                        final int n41;
                        T.a(this.m)[n12 + j] = this.a(this.c.c[n8], (n41 = this.o.a * (int)(acos * n2)) + (int)(n38 * n));
                        final double n42 = n34 - n22;
                        final double n43 = n33 + n23;
                        final double n44 = n42 / n35;
                        final double n46;
                        double n45;
                        if ((n45 = (n46 = ((n43 > 0.0) ? (6.283185307179586 - B.a.a(n44)) : B.a.a(n44)))) != n46) {
                            final double n47 = n42 / Math.sin(Math.acos(n31));
                            n45 = ((n43 > 0.0) ? (6.283185307179586 - B.a.a(n47)) : B.a.a(n47));
                        }
                        T.a(this.m)[n12 + n30] = this.a(this.c.d[n8], n41 + (int)(n45 * n));
                        final double n48 = n25 + n28;
                        final double n49 = n29 - n26;
                        final double n50 = cos2 * n49;
                        final double n51 = sin2 * n49;
                        double acos2 = B.a.a(n48);
                        final double n52 = B.b.a(acos2);
                        final double n54;
                        final double n53 = (n54 = n22 + n51) / n52;
                        final double n56;
                        double n55;
                        if ((n55 = (n56 = ((n50 > n23) ? (6.283185307179586 - B.a.a(n53)) : B.a.a(n53)))) != n56) {
                            acos2 = Math.acos(n48);
                            final double n57 = n54 / Math.sin(acos2);
                            n55 = ((n50 > n23) ? (6.283185307179586 - B.a.a(n57)) : B.a.a(n57));
                        }
                        final int n58;
                        T.a(this.m)[n13 + j] = this.a(this.c.c[n8], (n58 = this.o.a * (int)(acos2 * n2)) + (int)(n55 * n));
                        final double n59 = n51 - n22;
                        final double n60 = n50 + n23;
                        final double n61 = n59 / n52;
                        final double n63;
                        double n62;
                        if ((n62 = (n63 = ((n60 > 0.0) ? (6.283185307179586 - B.a.a(n61)) : B.a.a(n61)))) != n63) {
                            final double n64 = n59 / Math.sin(Math.acos(n48));
                            n62 = ((n60 > 0.0) ? (6.283185307179586 - B.a.a(n64)) : B.a.a(n64));
                        }
                        T.a(this.m)[n13 + n30] = this.a(this.c.d[n8], n58 + (int)(n62 * n));
                    }
                    else {
                        final int n65 = this.c.c[n8] & 0xFF;
                        final int n66 = 255 - n65;
                        T.a(this.m)[n12 + j] = (n65 + (n5 * n66 >> 8) << 16 | n65 + (n6 * n66 >> 8) << 8 | n65 + (n7 * n66 >> 8));
                        final int n67 = this.c.d[n8] & 0xFF;
                        final int n68 = 255 - n67;
                        final int n69 = n67 + (n5 * n68 >> 8) << 16 | n67 + (n6 * n68 >> 8) << 8;
                        final int n70 = n4 - j;
                        T.a(this.m)[n12 + n70] = (n69 | n67 + (n7 * n68 >> 8));
                        final int n71 = this.c.e[n8] & 0xFF;
                        final int n72 = 255 - n71;
                        T.a(this.m)[n13 + j] = (n71 + (n5 * n72 >> 8) << 16 | n71 + (n6 * n72 >> 8) << 8 | n71 + (n7 * n72 >> 8));
                        final int n73 = this.c.f[n8] & 0xFF;
                        final int n74 = 255 - n73;
                        T.a(this.m)[n13 + n70] = (n73 + (n5 * n74 >> 8) << 16 | n73 + (n6 * n74 >> 8) << 8 | n73 + (n7 * n74 >> 8));
                    }
                    ++n8;
                }
            }
            this.p.a(this.m);
            T.b(this.m).newPixels();
            graphics.drawImage(this.i, 0, 0, null);
            this.f.a();
        }
    }
    
    private final int a(int n, final int n2) {
        n &= 0xFF;
        int n3;
        if ((n3 = (K.a(this.o)[n2] & 0xFF) + n) > 255) {
            n3 = 255;
        }
        final int n4 = n3 << 16;
        int n5;
        if ((n5 = (K.b(this.o)[n2] & 0xFF) + n) > 255) {
            n5 = 255;
        }
        final int n6 = n4 | n5 << 8;
        int n7;
        if ((n7 = (K.c(this.o)[n2] & 0xFF) + n) > 255) {
            n7 = 255;
        }
        return n6 | n7;
    }
    
    public final void update(final Graphics graphics) {
    }
    
    public final void paint(final Graphics graphics) {
    }
    
    public final boolean a(String parameter, final boolean b) {
        if ((parameter = this.getParameter(parameter)) != null) {
            return parameter.equals(I.I.I(551));
        }
        return b;
    }
    
    public final String getAppletInfo() {
        return I.I.I(69);
    }
    
    static final void a(final RE re, final Image i) {
        re.i = i;
    }
}
