// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.io.Serializable;
import java.util.Enumeration;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import com.easypano.tourweaver.a.c;
import java.awt.image.ImageObserver;
import com.easypano.tourweaver.a.e;
import java.util.Vector;
import java.awt.Graphics;
import com.easypano.tourweaver.f.d;
import java.awt.Image;
import com.easypano.tourweaver.PlayerListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import com.easypano.tourweaver.f.ab;

public class l extends f implements ab, MouseListener, MouseMotionListener, PlayerListener, Runnable
{
    Image v;
    Image w;
    Image x;
    double y;
    double z;
    String A;
    String B;
    double C;
    int D;
    d E;
    int F;
    double G;
    boolean H;
    boolean I;
    boolean J;
    Image K;
    Image L;
    int M;
    int N;
    Image O;
    Graphics P;
    long Q;
    long R;
    int S;
    int T;
    int U;
    int V;
    int W;
    int X;
    int Y;
    int Z;
    boolean ab;
    int bb;
    int cb;
    boolean db;
    int eb;
    int fb;
    int gb;
    int hb;
    long ib;
    PlayerListener jb;
    Vector kb;
    String lb;
    boolean mb;
    boolean nb;
    Thread ob;
    long pb;
    private static String[] qb;
    
    public l() {
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = 0.0;
        this.z = 0.0;
        this.A = "";
        this.B = "";
        this.C = 0.0;
        this.D = 0;
        this.F = 0;
        this.G = 0.0;
        this.H = false;
        this.I = true;
        this.M = 0;
        this.N = 0;
        this.O = null;
        this.P = null;
        this.Q = 0L;
        this.R = 0L;
        this.S = 0;
        this.T = 0;
        this.U = 0;
        this.V = 0;
        this.W = 0;
        this.X = 0;
        this.Y = 0;
        this.Z = 60;
        this.ab = false;
        this.bb = 0;
        this.cb = 0;
        this.db = false;
        this.eb = 0;
        this.fb = 0;
        this.gb = 0;
        this.hb = 0;
        this.ib = 0L;
        this.kb = new Vector();
        this.lb = l.qb[3];
        this.mb = false;
        this.nb = false;
        this.ob = null;
        this.pb = 0L;
        this.addMouseListener(this);
        this.addMouseMotionListener(this);
        this.addCamera(new d());
        this.mb = true;
    }
    
    public void a(final boolean j) {
        this.J = j;
    }
    
    public boolean h() {
        return this.J;
    }
    
    public void destroy() {
        super.destroy();
        this.v = null;
        this.w = null;
        this.x = null;
        this.O = null;
        this.L = null;
        this.K = null;
        final Graphics p = this.P;
        if (!f.u) {
            if (p == null) {
                return;
            }
            final Graphics p2 = this.P;
        }
        p.dispose();
        this.P = null;
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        final boolean u = f.u;
        super.setBounds(n, n2, n3, n4);
        l l = this;
        if (!u) {
            if (this.E != null) {
                this.E.a(0, 0, n3, n4);
            }
            this.O = null;
            l = this;
        }
        final int n5 = n3 / 30;
        final int n6 = 2;
        l.y = ((!u && n5 <= n6) ? 2.0 : (n5 / n6));
        final int n7 = n4 / 30;
        final int n8 = 2;
        this.z = ((!u && n7 <= n8) ? 2.0 : (n7 / n8));
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public Graphics l() {
        return this.P;
    }
    
    protected Graphics m() {
        return this.getParent().getGraphics();
    }
    
    public void a(final Image image, final String s) {
        final boolean u = f.u;
        final boolean equals = s.equals(this.A);
        Label_0075: {
            if (!u) {
                if (equals) {
                    this.w = image;
                    if (!u) {
                        break Label_0075;
                    }
                }
                s.equals(this.B);
            }
            if (equals) {
                com.easypano.tourweaver.a.e.a(this.x = image);
                this.M = image.getWidth(this);
                this.N = image.getHeight(this);
                this.mb = true;
                this.c();
            }
        }
        super.a(image, s);
    }
    
    public void paint(final Graphics graphics) {
        final boolean u = f.u;
        Image image2;
        final Image image = image2 = this.L;
        if (!u) {
            l l = null;
            Label_0083: {
                if (image == null) {
                    this.S = this.getBounds().width;
                    this.U = this.getBounds().height;
                    l = this;
                    if (u) {
                        break Label_0083;
                    }
                    if (this.S != 0) {
                        l = this;
                        if (u) {
                            break Label_0083;
                        }
                        if (this.U != 0) {
                            System.gc();
                            this.L = this.createImage(this.S, this.U);
                        }
                    }
                }
                l = this;
            }
            final Image k;
            image2 = (k = l.K);
        }
        l i = null;
        l j = null;
        l m = null;
        Label_0310: {
            if (!u) {
                l l2 = null;
                Label_0192: {
                    if (image == null) {
                        this.T = this.getBounds().width;
                        this.V = this.getBounds().height;
                        i = this;
                        j = this;
                        m = this;
                        l2 = this;
                        if (u) {
                            break Label_0192;
                        }
                        if (this.T != this.S) {
                            i = this;
                            j = this;
                            m = this;
                            l2 = this;
                            if (u) {
                                break Label_0192;
                            }
                            if (this.V != this.U) {
                                i = this;
                                j = this;
                                m = this;
                                l2 = this;
                                if (u) {
                                    break Label_0192;
                                }
                                if (this.T != 0) {
                                    i = this;
                                    j = this;
                                    m = this;
                                    l2 = this;
                                    if (u) {
                                        break Label_0192;
                                    }
                                    if (this.V != 0) {
                                        System.gc();
                                        this.K = this.createImage(this.T, this.V);
                                    }
                                }
                            }
                        }
                    }
                    i = this;
                    j = this;
                    m = this;
                    l2 = this;
                }
                if (u) {
                    break Label_0310;
                }
                image2 = l2.O;
            }
            if (image2 == null) {
                l l3 = this;
                Label_0286: {
                    Label_0279: {
                        if (!u) {
                            if (this.getBounds().width == this.S) {
                                l3 = this;
                                if (u) {
                                    break Label_0279;
                                }
                                if (this.getBounds().height == this.U) {
                                    l3 = this;
                                    if (u) {
                                        break Label_0279;
                                    }
                                    if (this.S != 0) {
                                        l3 = this;
                                        if (u) {
                                            break Label_0279;
                                        }
                                        if (this.U != 0) {
                                            this.O = this.L;
                                            if (!u) {
                                                break Label_0286;
                                            }
                                        }
                                    }
                                }
                            }
                            l3 = this;
                        }
                    }
                    l3.O = this.K;
                }
                i = this;
                j = this;
                m = this;
                if (u) {
                    break Label_0310;
                }
                if (this.O != null) {
                    this.P = this.O.getGraphics();
                }
            }
            i = this;
            j = this;
            m = this;
        }
        if (!u) {
            if (m.d != null) {
                this.P.setColor(super.d);
                this.P.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
            }
            i = this;
            j = this;
        }
        Label_0410: {
            if (!u) {
                if (j.v != null) {
                    final long currentTimeMillis = System.currentTimeMillis();
                    this.P.drawImage(this.v, 0, 0, this);
                    this.D = (int)(System.currentTimeMillis() - currentTimeMillis);
                    if (!u) {
                        break Label_0410;
                    }
                }
                i = this;
            }
            com.easypano.tourweaver.a.c.c(i, l.qb[2]);
        }
        l l4 = this;
        l l5 = this;
        Label_0533: {
            final Image x;
            Label_0495: {
                if (!u) {
                    if (this.mb) {
                        x = this.x;
                        if (u) {
                            break Label_0495;
                        }
                        if (x != null) {
                            this.P.drawImage(this.x, (this.getBounds().width - this.M) / 2, (this.getBounds().height - this.N) / 2, this.getParent());
                        }
                    }
                    super.paint(this.P);
                    l4 = this;
                    l5 = this;
                }
                if (u) {
                    break Label_0533;
                }
                final Image w = l5.w;
            }
            if (x != null) {
                l4 = this;
                if (u) {
                    break Label_0533;
                }
                if (!this.J) {
                    this.P.drawImage(this.w, 0, 0, this);
                }
            }
            this.R = System.currentTimeMillis();
            l4 = this;
        }
        final long n2;
        final long n = n2 = l4.R - this.Q;
        Label_0634: {
            if (!u) {
                l l6 = null;
                Label_0624: {
                    if (n2 < 500L) {
                        final long n3 = n;
                        if (u) {
                            break Label_0634;
                        }
                        if (n3 > 0L) {
                            l6 = this;
                            if (u) {
                                break Label_0624;
                            }
                            if (this.isEnabled()) {
                                this.C = 1000L / n;
                                l6 = this;
                                if (u) {
                                    break Label_0624;
                                }
                                if (this.H) {
                                    ++this.F;
                                    this.G += this.C;
                                }
                            }
                        }
                    }
                    l6 = this;
                }
                l6.Q = this.R;
                System.currentTimeMillis();
            }
        }
        l l7 = this;
        if (!u) {
            if (this.O != null) {
                graphics.drawImage(this.O, 0, 0, this);
            }
            l7 = this;
        }
        l7.ib = this.R;
    }
    
    public long n() {
        return this.ib;
    }
    
    public void addAttributes(final String s, String trim) {
        final boolean u = f.u;
        String s2 = s;
        String s3 = s;
        if (!u) {
            if (s == null) {
                return;
            }
            s2 = (s3 = trim);
        }
        if (!u) {
            if (s3 == null) {
                return;
            }
            super.addAttributes(s, trim);
            trim = trim.trim();
            s2 = s;
        }
        boolean b2;
        boolean equals;
        final boolean b = equals = (b2 = s2.equals(l.qb[6]));
        if (!u) {
            if (b) {
                this.A = trim;
                if (!u) {
                    return;
                }
            }
            final boolean b3;
            equals = (b3 = (b2 = s.equals(l.qb[4])));
        }
        if (!u) {
            if (b) {
                this.B = trim;
                if (!u) {
                    return;
                }
            }
            b2 = (equals = s.equals(l.qb[5]));
        }
        if (!u) {
            if (equals) {
                this.setBackground(com.easypano.tourweaver.a.e.b(trim));
                if (!u) {
                    return;
                }
            }
            b2 = s.equals(l.qb[7]);
        }
        if (b2) {
            this.E.b(this.getBounds().width);
        }
    }
    
    public boolean imageUpdate(final Image image, final int n, final int n2, final int n3, final int n4, final int n5) {
        int n7;
        final int n6 = n7 = (n & 0x20);
        if (!f.u) {
            if (n6 != 32) {
                n7 = (true ? 1 : 0);
            }
            else {
                n7 = (false ? 1 : 0);
            }
        }
        return n7 != 0;
    }
    
    public void mouseEntered(final MouseEvent mouseEvent) {
    }
    
    public void mouseExited(final MouseEvent mouseEvent) {
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        final boolean u = f.u;
        super.processKeyEvent(keyEvent);
        final int keyCode = keyEvent.getKeyCode();
        int n10;
        int n9;
        int n8;
        int n7;
        int n6;
        int n5;
        int n4;
        int n3;
        int n2;
        final int n = n2 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = keyEvent.getID()))))))));
        int n20;
        int n19;
        int n18;
        int n17;
        int n16;
        int n15;
        int n14;
        int n13;
        int n12;
        final int n11 = n12 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = 401))))))));
        if (!u) {
            if (n == n11) {
                int n30;
                int n29;
                int n28;
                int n27;
                int n26;
                int n25;
                int n24;
                int n23;
                int n22;
                final int n21 = n22 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = keyCode))))))));
                int n40;
                int n39;
                int n38;
                int n37;
                int n36;
                int n35;
                int n34;
                int n33;
                int n32;
                final int n31 = n32 = (n33 = (n34 = (n35 = (n36 = (n37 = (n38 = (n39 = (n40 = 37))))))));
                l l = null;
                Label_0276: {
                    Label_0275: {
                        if (!u) {
                            if (n21 == n31) {
                                this.W = -this.Z;
                                if (!u) {
                                    break Label_0275;
                                }
                            }
                            final int n41;
                            n22 = (n41 = (n23 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = keyCode)))))))));
                            final int n42;
                            n32 = (n42 = (n33 = (n34 = (n35 = (n36 = (n37 = (n38 = (n39 = (n40 = 39)))))))));
                        }
                        if (!u) {
                            if (n21 == n31) {
                                this.W = this.Z;
                                if (!u) {
                                    break Label_0275;
                                }
                            }
                            n23 = (n22 = (n24 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = keyCode))))))));
                            n33 = (n32 = (n34 = (n35 = (n36 = (n37 = (n38 = (n39 = (n40 = 38))))))));
                        }
                        if (!u) {
                            if (n22 == n32) {
                                this.X = this.Z;
                                if (!u) {
                                    break Label_0275;
                                }
                            }
                            n24 = (n23 = (n25 = (n26 = (n27 = (n28 = (n29 = (n30 = keyCode)))))));
                            n34 = (n33 = (n35 = (n36 = (n37 = (n38 = (n39 = (n40 = 40)))))));
                        }
                        if (!u) {
                            if (n23 == n33) {
                                this.X = -this.Z;
                                if (!u) {
                                    break Label_0275;
                                }
                            }
                            n25 = (n24 = (n26 = (n27 = (n28 = (n29 = (n30 = keyCode))))));
                            n35 = (n34 = (n36 = (n37 = (n38 = (n39 = (n40 = 17))))));
                        }
                        Label_0153: {
                            Label_0150: {
                                Label_0138: {
                                    if (!u) {
                                        if (n24 == n34) {
                                            break Label_0138;
                                        }
                                        n26 = (n25 = (n27 = (n28 = (n29 = (n30 = keyCode)))));
                                        n36 = (n35 = (n37 = (n38 = (n39 = (n40 = 90)))));
                                    }
                                    if (u) {
                                        break Label_0153;
                                    }
                                    if (n25 != n35) {
                                        break Label_0150;
                                    }
                                }
                                this.Y = this.Z;
                                if (!u) {
                                    break Label_0275;
                                }
                            }
                            n27 = (n26 = (n28 = (n29 = (n30 = keyCode))));
                            n37 = (n36 = (n38 = (n39 = (n40 = 16))));
                        }
                        Label_0186: {
                            Label_0183: {
                                Label_0170: {
                                    if (!u) {
                                        if (n26 == n36) {
                                            break Label_0170;
                                        }
                                        n28 = (n27 = (n29 = (n30 = keyCode)));
                                        n38 = (n37 = (n39 = (n40 = 65)));
                                    }
                                    if (u) {
                                        break Label_0186;
                                    }
                                    if (n27 != n37) {
                                        break Label_0183;
                                    }
                                }
                                this.Y = -this.Z;
                                if (!u) {
                                    break Label_0275;
                                }
                            }
                            n29 = (n28 = (n30 = keyCode));
                            n39 = (n38 = (n40 = 18));
                        }
                        if (!u) {
                            if (n28 == n38) {
                                this.ab = true;
                                if (!u) {
                                    break Label_0275;
                                }
                            }
                            n30 = (n29 = keyCode);
                            n40 = (n39 = 10);
                        }
                        if (!u) {
                            if (n29 == n39) {
                                l = this;
                                if (u) {
                                    break Label_0276;
                                }
                                if (!this.ab) {
                                    break Label_0275;
                                }
                                l i = this;
                                if (!u) {
                                    if (!this.J) {
                                        this.b(com.easypano.tourweaver.b.l.qb[0]);
                                        if (!u) {
                                            break Label_0275;
                                        }
                                    }
                                    i = this;
                                }
                                i.b(com.easypano.tourweaver.b.l.qb[1]);
                                if (!u) {
                                    break Label_0275;
                                }
                            }
                            n30 = keyCode;
                            n40 = 27;
                        }
                        if (n30 == n40) {
                            this.b(com.easypano.tourweaver.b.l.qb[1]);
                        }
                    }
                    l = this;
                }
                l.f.autopanscene(this.W, this.X, this.Y, this.getName());
                if (!u) {
                    return;
                }
            }
            final int n43;
            n2 = (n43 = (n3 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = keyEvent.getID())))))))));
            final int n44;
            n12 = (n44 = (n13 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = 402)))))))));
        }
        if (!u) {
            if (n != n11) {
                return;
            }
            n3 = (n2 = (n4 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = keyCode))))))));
            n13 = (n12 = (n14 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = 37))))))));
        }
        int n46 = 0;
        int x = 0;
        int n45 = 0;
        Label_0442: {
            Label_0438: {
                Label_0348: {
                    Label_0345: {
                        Label_0336: {
                            if (!u) {
                                if (n2 == n12) {
                                    break Label_0336;
                                }
                                n4 = (n3 = (n5 = (n6 = (n7 = (n8 = (n9 = (n10 = keyCode)))))));
                                n14 = (n13 = (n15 = (n16 = (n17 = (n18 = (n19 = (n20 = 39)))))));
                            }
                            if (u) {
                                break Label_0348;
                            }
                            if (n3 != n13) {
                                break Label_0345;
                            }
                        }
                        this.W = 0;
                        if (!u) {
                            break Label_0438;
                        }
                    }
                    n5 = (n4 = (n6 = (n7 = (n8 = (n9 = (n10 = keyCode))))));
                    n15 = (n14 = (n16 = (n17 = (n18 = (n19 = (n20 = 38))))));
                }
                Label_0377: {
                    Label_0374: {
                        Label_0365: {
                            if (!u) {
                                if (n4 == n14) {
                                    break Label_0365;
                                }
                                n6 = (n5 = (n7 = (n8 = (n9 = (n10 = keyCode)))));
                                n16 = (n15 = (n17 = (n18 = (n19 = (n20 = 40)))));
                            }
                            if (u) {
                                break Label_0377;
                            }
                            if (n5 != n15) {
                                break Label_0374;
                            }
                        }
                        this.X = 0;
                        if (!u) {
                            break Label_0438;
                        }
                    }
                    n7 = (n6 = (n8 = (n9 = (n10 = keyCode))));
                    n17 = (n16 = (n18 = (n19 = (n20 = 17))));
                }
                Label_0430: {
                    Label_0423: {
                        Label_0414: {
                            if (!u) {
                                if (n6 == n16) {
                                    break Label_0414;
                                }
                                n8 = (n7 = (n9 = (n10 = keyCode)));
                                n18 = (n17 = (n19 = (n20 = 90)));
                            }
                            if (!u) {
                                if (n7 == n17) {
                                    break Label_0414;
                                }
                                n9 = (n8 = (n10 = keyCode));
                                n19 = (n18 = (n20 = 16));
                            }
                            if (!u) {
                                if (n8 == n18) {
                                    break Label_0414;
                                }
                                n10 = (n9 = keyCode);
                                n20 = (n19 = 65);
                            }
                            if (u) {
                                break Label_0430;
                            }
                            if (n9 != n19) {
                                break Label_0423;
                            }
                        }
                        this.Y = 0;
                        if (!u) {
                            break Label_0438;
                        }
                    }
                    n45 = (n10 = (x = (n46 = keyCode)));
                    if (u) {
                        break Label_0442;
                    }
                    n20 = 18;
                }
                if (n10 == n20) {
                    this.ab = false;
                }
            }
            x = (n45 = (n46 = this.W));
        }
        if (!u) {
            if (n45 != 0) {
                return;
            }
            n46 = (x = this.X);
        }
        l j = null;
        Label_0472: {
            if (!u) {
                if (x != 0) {
                    return;
                }
                j = this;
                if (u) {
                    break Label_0472;
                }
                n46 = this.Y;
            }
            if (n46 != 0) {
                return;
            }
            j = this;
        }
        j.f.stop();
    }
    
    public void mousePressed(final MouseEvent mouseEvent) {
        final boolean u = f.u;
        this.db = true;
        this.bb = mouseEvent.getX();
        this.cb = mouseEvent.getY();
        final int modifiers = mouseEvent.getModifiers();
        int n2;
        final int n = n2 = (modifiers & 0x10);
        if (!u) {
            if (n == 0) {
                return;
            }
            final int n3;
            n2 = (n3 = modifiers);
        }
        final int n4 = 26;
        if (!u) {
            if (n == n4) {
                return;
            }
            n2 = modifiers;
        }
        if (n2 != n4) {
            super.f.stop();
        }
    }
    
    public void mouseReleased(final MouseEvent mouseEvent) {
        final boolean u = f.u;
        final int modifiers = mouseEvent.getModifiers();
        if (!u) {
            l l = null;
            Label_0059: {
                Label_0058: {
                    if ((modifiers & 0x10) != 0x0) {
                        final int n = modifiers;
                        final int n2 = 26;
                        int db = 0;
                        Label_0048: {
                            if (!u) {
                                if (n == n2) {
                                    break Label_0058;
                                }
                                final int n3;
                                db = (n3 = modifiers);
                                if (u) {
                                    break Label_0048;
                                }
                            }
                            if (n == n2) {
                                break Label_0058;
                            }
                            l = this;
                            if (u) {
                                break Label_0059;
                            }
                            db = (this.db ? 1 : 0);
                        }
                        if (db != 0) {
                            super.f.stop();
                        }
                    }
                }
                l = this;
            }
            l.db = false;
        }
    }
    
    public void c(final String s) {
        final boolean u = f.u;
        final Enumeration<com.easypano.tourweaver.b.c> elements = (Enumeration<com.easypano.tourweaver.b.c>)this.kb.elements();
        while (elements.hasMoreElements()) {
            final com.easypano.tourweaver.b.c c = elements.nextElement();
            final boolean equals = s.equals(c.m());
            Label_0075: {
                final com.easypano.tourweaver.b.c c2;
                Label_0071: {
                    if (!u) {
                        if (!equals) {
                            break Label_0075;
                        }
                        c2 = c;
                        if (u) {
                            break Label_0071;
                        }
                        c2.isVisible();
                    }
                    if (equals) {
                        c.setVisible(false);
                        if (!u) {
                            break Label_0075;
                        }
                    }
                }
                c2.setVisible(true);
            }
            if (u) {
                break;
            }
        }
    }
    
    public void mouseDragged(final MouseEvent mouseEvent) {
        final boolean u = f.u;
        final int modifiers = mouseEvent.getModifiers();
        if (!u) {
            if ((modifiers & 0x10) == 0x0) {
                return;
            }
            this.eb = mouseEvent.getX();
            this.fb = mouseEvent.getY();
            this.gb = (int)((this.eb - this.bb) / this.y);
        }
        final int gb = this.gb;
        final int n = 5;
        int gb2 = 0;
        Label_0099: {
            l l = null;
            Label_0096: {
                if (!u) {
                    if (gb > n) {
                        gb2 = 5;
                        break Label_0099;
                    }
                    l = this;
                    if (u) {
                        break Label_0096;
                    }
                    final int gb3 = this.gb;
                }
                if (gb < n) {
                    gb2 = -5;
                    break Label_0099;
                }
                l = this;
            }
            gb2 = l.gb;
        }
        this.gb = gb2;
        this.hb = (int)((this.fb - this.cb) / this.z);
        final int hb = this.hb;
        final int n2 = 5;
        int hb2 = 0;
        Label_0163: {
            l i = null;
            Label_0160: {
                if (!u) {
                    if (hb > n2) {
                        hb2 = 5;
                        break Label_0163;
                    }
                    i = this;
                    if (u) {
                        break Label_0160;
                    }
                    final int hb3 = this.hb;
                }
                if (hb < n2) {
                    hb2 = -5;
                    break Label_0163;
                }
                i = this;
            }
            hb2 = i.hb;
        }
        this.hb = hb2;
        synchronized (super.f) {
            l j = this;
            Label_0198: {
                if (!u) {
                    if (this.gb == 0) {
                        j = this;
                        if (u) {
                            break Label_0198;
                        }
                        if (this.hb == 0) {
                            return;
                        }
                    }
                    j = this;
                }
            }
            j.f.autopanscene(this.gb * 20, -this.hb * 20, 0, this.getName());
        }
    }
    
    public void mouseMoved(final MouseEvent mouseEvent) {
    }
    
    public void mouseClicked(final MouseEvent mouseEvent) {
        final boolean u = f.u;
        int clickCount;
        final int n = clickCount = (mouseEvent.getModifiers() & 0x10);
        if (!u) {
            if (n == 0) {
                return;
            }
            final int id;
            clickCount = (id = mouseEvent.getID());
        }
        final int n2 = 500;
        l l = null;
        Label_0071: {
            int j = 0;
            Label_0054: {
                if (!u) {
                    if (n != n2) {
                        return;
                    }
                    j = (clickCount = mouseEvent.getClickCount());
                    if (u) {
                        break Label_0054;
                    }
                }
                if (clickCount != n2) {
                    return;
                }
                l = this;
                if (u) {
                    break Label_0071;
                }
                j = (this.J ? 1 : 0);
            }
            if (j == 0) {
                this.b(com.easypano.tourweaver.b.l.qb[0]);
                if (!u) {
                    return;
                }
            }
            l = this;
        }
        l.b(com.easypano.tourweaver.b.l.qb[1]);
    }
    
    public void addCamera(final d e) {
        (this.E = e).a(this);
    }
    
    public double getAverFPS() {
        return this.G / this.F;
    }
    
    public d getCamera() {
        return this.E;
    }
    
    public double getPreFPS() {
        return this.C;
    }
    
    public void reCountFps() {
        final boolean h = this.H;
        if (!f.u && h) {}
        this.H = h;
        this.F = 0;
        this.G = 0.0;
    }
    
    public void o() {
        final boolean u = f.u;
        final Enumeration d = this.d();
        while (d.hasMoreElements()) {
            final b nextElement = d.nextElement();
            final boolean b = nextElement instanceof b;
            Label_0059: {
                Serializable kb = null;
                Label_0052: {
                    if (!u) {
                        if (!b) {
                            break Label_0059;
                        }
                        final Vector vector = (Vector)(kb = this.kb);
                        if (u) {
                            break Label_0052;
                        }
                        vector.contains(nextElement);
                    }
                    if (b) {
                        break Label_0059;
                    }
                    kb = nextElement;
                }
                ((b)kb).setVisible(true);
            }
            if (u) {
                break;
            }
        }
    }
    
    public void p() {
        final boolean u = f.u;
        final Enumeration d = this.d();
        while (d.hasMoreElements()) {
            final b nextElement = d.nextElement();
            final boolean b = nextElement instanceof b;
            Label_0059: {
                Serializable kb = null;
                Label_0052: {
                    if (!u) {
                        if (!b) {
                            break Label_0059;
                        }
                        final Vector vector = (Vector)(kb = this.kb);
                        if (u) {
                            break Label_0052;
                        }
                        vector.contains(nextElement);
                    }
                    if (b) {
                        break Label_0059;
                    }
                    kb = nextElement;
                }
                ((b)kb).setVisible(false);
            }
            if (u) {
                break;
            }
        }
    }
    
    public void render() {
        final boolean u = f.u;
        final Image r = this.E.r();
        if (r != null) {
            l l = this;
            if (!u) {
                Label_0120: {
                    if (this.E.n()) {
                        final Vector s = this.E.s();
                        this.a(this.kb.elements());
                        this.kb.removeAllElements();
                        final Enumeration<b> elements = s.elements();
                        while (true) {
                            while (elements.hasMoreElements()) {
                                final b b = elements.nextElement();
                                this.kb.addElement(b);
                                this.a(b);
                                if (u) {
                                    this.E.b(false);
                                    break Label_0120;
                                }
                                if (u) {
                                    break;
                                }
                            }
                            s.removeAllElements();
                            continue;
                        }
                    }
                }
                this.v = r;
                this.c();
                l = this;
            }
            final boolean i = this.jb != null;
            l.I = i;
            if (i) {
                this.jb.sceneSwitched("");
                this.I = false;
            }
            this.mb = false;
            this.nb = false;
            this.ob = null;
        }
    }
    
    public void a(final PlayerListener jb) {
        this.jb = jb;
    }
    
    public void takePictureOfSelf() {
        final d e = this.E;
        if (!f.u) {
            if (e == null) {
                return;
            }
            final d e2 = this.E;
        }
        e.a(this.O);
    }
    
    public void takeEmptyPicture() {
        l l = this;
        if (!f.u) {
            if (this.E == null) {
                return;
            }
            l = this;
        }
        final Image image = l.createImage(this.getBounds().width, this.getBounds().height);
        final Graphics graphics = image.getGraphics();
        graphics.setColor(this.getBackground());
        graphics.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
        this.E.a(image);
    }
    
    public void takeEmptyPicture(final d d) {
        if (d != null) {
            final Image image = this.createImage(this.getBounds().width, this.getBounds().height);
            final Graphics graphics = image.getGraphics();
            graphics.setColor(this.getBackground());
            graphics.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
            d.a(image);
        }
    }
    
    public void takePictureOfSelf(final d d) {
        d d2 = d;
        if (!f.u) {
            if (d == null) {
                return;
            }
            d2 = d;
        }
        d2.a(this.O);
    }
    
    public void setType(final String lb) {
        this.lb = lb;
    }
    
    public String getType() {
        return this.lb;
    }
    
    public void sceneSwitching(final String s) {
        this.nb = true;
        this.pb = System.currentTimeMillis();
        (this.ob = new Thread(this)).start();
    }
    
    public void run() {
        final boolean u = f.u;
        while (this.ob != null) {
            long interrupted;
            final int n = (int)(interrupted = (this.ob.isInterrupted() ? 1 : 0));
            Label_0068: {
                final long n2;
                if (!u) {
                    if (n != 0) {
                        break;
                    }
                    n2 = System.currentTimeMillis() - this.pb;
                    if (u) {
                        break Label_0068;
                    }
                    interrupted = lcmp(n2, 500L);
                }
                if (interrupted >= 0) {
                    l l = this;
                    if (!u) {
                        if (!this.nb) {
                            break Label_0068;
                        }
                        this.mb = true;
                        l = this;
                    }
                    l.c();
                    if (!u) {
                        break;
                    }
                }
                try {
                    Thread.sleep(n2);
                    continue;
                }
                catch (Exception ex) {
                    if (!u) {
                        continue;
                    }
                }
            }
            break;
        }
    }
    
    public void movieSwitching(final String s) {
    }
    
    public void movieStoped(final String s) {
    }
    
    public void mapSwitching(final String s) {
    }
    
    public void sceneSwitched(final String s) {
        this.ob = null;
    }
    
    public void mapSwitched(final String s) {
    }
    
    public void moviePaused(final String s) {
    }
    
    static {
        final String[] qb = new String[8];
        final int n = 0;
        final char[] charArray = "tF+5bqA\"<\u007f:@3+x|Tg*rw]\"/xwD\"+8".toCharArray();
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
                            c2 = '\u0012';
                            break;
                        }
                        case 1: {
                            c2 = '3';
                            break;
                        }
                        case 2: {
                            c2 = 'G';
                            break;
                        }
                        case 3: {
                            c2 = 'Y';
                            break;
                        }
                        default: {
                            c2 = '\u0011';
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
        qb[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "wK.-wg_+*r`V\"79aG50\u007fu\u00134:t|V10teV5p".toCharArray();
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
                            c4 = '\u0012';
                            break;
                        }
                        case 1: {
                            c4 = '3';
                            break;
                        }
                        case 2: {
                            c4 = 'G';
                            break;
                        }
                        case 3: {
                            c4 = 'Y';
                            break;
                        }
                        default: {
                            c4 = '\u0011';
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
        qb[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "`V)=t`z*>1{@g7d~_".toCharArray();
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
                            c6 = '\u0012';
                            break;
                        }
                        case 1: {
                            c6 = '3';
                            break;
                        }
                        case 2: {
                            c6 = 'G';
                            break;
                        }
                        case 3: {
                            c6 = 'Y';
                            break;
                        }
                        default: {
                            c6 = '\u0011';
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
        qb[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "AP\"7tDZ\".t`".toCharArray();
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
                            c8 = '\u0012';
                            break;
                        }
                        case 1: {
                            c8 = '3';
                            break;
                        }
                        case 2: {
                            c8 = 'G';
                            break;
                        }
                        case 3: {
                            c8 = 'Y';
                            break;
                        }
                        default: {
                            c8 = '\u0011';
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
        qb[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "bA\"\u0015~sW.7v[^ ".toCharArray();
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
                            c10 = '\u0012';
                            break;
                        }
                        case 1: {
                            c10 = '3';
                            break;
                        }
                        case 2: {
                            c10 = 'G';
                            break;
                        }
                        case 3: {
                            c10 = 'Y';
                            break;
                        }
                        default: {
                            c10 = '\u0011';
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
        qb[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "pT\u00046}}A".toCharArray();
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
                            c12 = '\u0012';
                            break;
                        }
                        case 1: {
                            c12 = '3';
                            break;
                        }
                        case 2: {
                            c12 = 'G';
                            break;
                        }
                        case 3: {
                            c12 = 'Y';
                            break;
                        }
                        default: {
                            c12 = '\u0011';
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
        qb[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "tA&4t[^ ".toCharArray();
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
                            c14 = '\u0012';
                            break;
                        }
                        case 1: {
                            c14 = '3';
                            break;
                        }
                        case 2: {
                            c14 = 'G';
                            break;
                        }
                        case 3: {
                            c14 = 'Y';
                            break;
                        }
                        default: {
                            c14 = '\u0011';
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
        qb[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "p\\27ua".toCharArray();
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
                            c16 = '\u0012';
                            break;
                        }
                        case 1: {
                            c16 = '3';
                            break;
                        }
                        case 2: {
                            c16 = 'G';
                            break;
                        }
                        case 3: {
                            c16 = 'Y';
                            break;
                        }
                        default: {
                            c16 = '\u0011';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n32;
                } while (n30 == 0);
            }
            if (n30 <= n32) {
                qb[n29] = new String(charArray8).intern();
                l.qb = qb;
                return;
            }
            continue;
        }
    }
}
