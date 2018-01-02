// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import com.easypano.tourweaver.f.h;
import java.awt.Cursor;
import java.awt.Container;
import java.awt.event.MouseEvent;
import com.easypano.tourweaver.a.e;
import java.awt.image.ImageObserver;
import java.awt.Color;
import java.awt.Graphics;
import com.easypano.tourweaver.f.n;
import java.util.Hashtable;
import java.awt.Rectangle;
import java.awt.Image;
import com.easypano.tourweaver.f.b;
import com.easypano.tourweaver.PlayerListener;

public class ab extends a implements PlayerListener, b
{
    public static final String p;
    public static final String q;
    long r;
    long s;
    double t;
    boolean u;
    String v;
    String w;
    String x;
    String y;
    Image z;
    Image A;
    Image B;
    Image C;
    Rectangle D;
    Rectangle E;
    Hashtable F;
    n G;
    Image H;
    Rectangle I;
    boolean J;
    Image K;
    Graphics L;
    private int M;
    int N;
    int O;
    boolean P;
    double Q;
    Color R;
    int S;
    int T;
    private static String[] U;
    
    public ab() {
        this.r = 0L;
        this.t = 0.5;
        this.u = true;
        this.v = "";
        this.w = "";
        this.x = "";
        this.y = "";
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.D = new Rectangle();
        this.E = new Rectangle();
        this.F = new Hashtable();
        this.I = new Rectangle();
        this.J = false;
        this.K = null;
        this.L = null;
        this.M = 6;
        this.N = 0;
        this.O = 0;
        this.P = false;
        this.R = Color.green;
        this.S = 14;
        this.T = 15;
    }
    
    public void movieStoped(final String s) {
    }
    
    public void destroy() {
        this.z = null;
        this.A = null;
        this.B = null;
        this.C = null;
        this.H = null;
        this.K = null;
    }
    
    public void a(final n n) {
        n n2 = n;
        if (!com.easypano.tourweaver.b.f.u) {
            if (n == null) {
                return;
            }
            this.F.put(n.getName(), n);
            n2 = n;
        }
        n2.a(this);
    }
    
    public n e() {
        return this.G;
    }
    
    public void a(final Image h, final int n, final int n2, final int n3, final int n4) {
        this.H = h;
        this.I.setBounds(n, n2, n3, n4);
    }
    
    private void f() {
        final boolean u = com.easypano.tourweaver.b.f.u;
        final Image k = this.K;
        ab ab = null;
        Label_0083: {
            if (!u) {
                if (k == null) {
                    this.K = this.createImage(this.getBounds().width, this.getBounds().height);
                    this.L = this.K.getGraphics();
                }
                this.L.clearRect(0, 0, this.getBounds().width, this.getBounds().height);
                ab = this;
                if (u) {
                    break Label_0083;
                }
                final Image h = this.H;
            }
            if (k == null) {
                return;
            }
            ab = this;
        }
        ab.L.drawImage(this.H, this.I.x, this.I.y, this.I.width, this.I.height, this);
    }
    
    public void a(final Image image, final String s) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        boolean b2;
        boolean equals;
        final boolean b = equals = (b2 = this.w.equals(s));
        if (!u) {
            if (b) {
                this.A = image;
                this.c();
            }
            final boolean b3;
            equals = (b3 = (b2 = this.v.equals(s)));
        }
        if (!u) {
            if (b) {
                this.z = image;
                this.c();
            }
            b2 = (equals = this.x.equals(s));
        }
        ab ab = null;
        Label_0097: {
            if (!u) {
                if (equals) {
                    this.B = image;
                    this.c();
                }
                ab = this;
                if (u) {
                    break Label_0097;
                }
                b2 = this.y.equals(s);
            }
            if (!b2) {
                return;
            }
            this.C = image;
            ab = this;
        }
        ab.c();
    }
    
    public void addAttributes(final String s, final String s2) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        super.addAttributes(s, s2);
        boolean b5;
        boolean equals;
        boolean b4;
        boolean b3;
        boolean b2;
        final boolean b = b2 = (b3 = (b4 = (equals = (b5 = s.equals(ab.U[3])))));
        if (!u) {
            if (b) {
                this.w = s2;
                if (!u) {
                    return;
                }
            }
            final boolean b6;
            b2 = (b6 = (b3 = (b4 = (equals = (b5 = s.equals(ab.U[0]))))));
        }
        if (!u) {
            if (b) {
                this.v = s2;
                if (!u) {
                    return;
                }
            }
            b3 = (b2 = (b4 = (equals = (b5 = s.equals(ab.U[1])))));
        }
        if (!u) {
            if (b2) {
                this.x = s2;
                if (!u) {
                    return;
                }
            }
            b4 = (b3 = (equals = (b5 = s.equals(ab.U[2]))));
        }
        if (!u) {
            if (b3) {
                this.u = com.easypano.tourweaver.a.e.e(s2);
                this.g();
                if (!u) {
                    return;
                }
            }
            equals = (b4 = (b5 = s.equals(ab.U[6])));
        }
        if (!u) {
            if (b4) {
                this.y = s2;
                if (!u) {
                    return;
                }
            }
            b5 = (equals = s.equals(ab.U[5]));
        }
        if (!u) {
            if (equals) {
                this.J = s2.equals(ab.p);
                if (!u) {
                    return;
                }
            }
            b5 = s.equals(ab.U[4]);
        }
        if (b5) {
            this.R = com.easypano.tourweaver.a.e.b(s2);
        }
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        super.setBounds(n, n2, n3, n4);
        this.g();
    }
    
    private void g() {
        final boolean u = com.easypano.tourweaver.b.f.u;
        final Rectangle bounds = this.getBounds();
        int width = bounds.width;
        int u2;
        final int n = u2 = (this.u ? 1 : 0);
        Label_0115: {
            if (!u) {
                if (n != 0) {
                    final int n2 = width;
                    final int n3 = 95 + this.M;
                    Label_0048: {
                        if (!u) {
                            if (n2 >= n3) {
                                break Label_0048;
                            }
                            final int m = this.M;
                        }
                        width = n2 + n3;
                    }
                    this.D.setBounds(73, 8, width - 73 - 8, 6);
                    if (!u) {
                        break Label_0115;
                    }
                }
                final int n4;
                u2 = (n4 = width);
            }
            final int n5 = 30 + this.M;
            Label_0095: {
                if (!u) {
                    if (n >= n5) {
                        break Label_0095;
                    }
                    u2 = 30;
                    final int i = this.M;
                }
                width = u2 + n5;
            }
            this.D.setBounds(8, 8, width - 8 - 8, 6);
        }
        this.E.setBounds(this.D.x, this.D.y, 14, 6);
        super.setBounds(bounds.x, bounds.y, width, 22);
    }
    
    public void a(final long r) {
        this.r = r;
    }
    
    public void b(long r) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        final long n = lcmp(r, 0L);
        final long n2;
        Label_0046: {
            if (!u) {
                if (n < 0) {
                    r = 0L;
                }
                n2 = r;
                if (u) {
                    break Label_0046;
                }
                final long n3 = lcmp(n2, this.r);
            }
            if (n > 0) {
                r = this.r;
            }
            this.s = r;
            final long s = this.s;
        }
        this.E.x = this.D.x + (int)((this.D.width - this.E.width) * (n2 / this.r));
        this.c();
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        super.processMouseEvent(mouseEvent);
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int id = mouseEvent.getID();
        final int n = 501;
        if (!u) {
            if (id == n) {
                int contains;
                final int n2 = contains = (this.D.contains(x, y) ? 1 : 0);
                if (!u) {
                    if (n2 == 0) {
                        return;
                    }
                    this.P = true;
                    contains = x - this.E.width / 2 - this.D.x;
                }
                final int c = this.c(contains);
                this.E.x = c + this.D.x;
                this.O = this.E.x;
                this.b(c);
                this.N = x;
                this.c();
                if (!u) {
                    return;
                }
            }
            mouseEvent.getID();
        }
        if (id == n) {
            this.P = false;
            super.f.addmovietomoviepercent(this.Q);
        }
    }
    
    public Graphics h() {
        final Container parent = this.getParent();
        if (!com.easypano.tourweaver.b.f.u) {
            if (parent == null) {
                return null;
            }
            this.getParent();
        }
        return parent.getGraphics();
    }
    
    private void b(final int n) {
        this.Q = n / (this.D.width - this.E.width);
        this.s = (long)(this.Q * this.r);
    }
    
    public void a(final double n) {
        if (this.G != null) {
            final double n2 = dcmpl(n, 0.0);
            if (!com.easypano.tourweaver.b.f.u) {
                if (n2 < 0) {
                    return;
                }
                final double n3 = dcmpg(n, 1.0);
            }
            if (n2 <= 0) {
                this.G.a(n);
            }
        }
    }
    
    private int c(int n) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        int n3;
        final int n2 = n3 = n;
        if (!u) {
            if (n2 < 0) {
                n = 0;
            }
            final int n4;
            n3 = (n4 = n);
        }
        if (!u) {
            if (n2 > this.D.width - this.E.width) {
                n = this.D.width - this.E.width;
            }
            n3 = n;
        }
        return n3;
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        super.processMouseMotionEvent(mouseEvent);
        final int x = mouseEvent.getX();
        final int y = mouseEvent.getY();
        final int id = mouseEvent.getID();
        final int n = 506;
        ab ab = null;
        Label_0155: {
            int n3 = 0;
            Label_0137: {
                if (!u) {
                    Label_0112: {
                        if (id == n) {
                            final int id2;
                            final int n2 = id2 = (n3 = (this.P ? 1 : 0));
                            if (u) {
                                break Label_0112;
                            }
                            if (n2 != 0) {
                                final int c = this.c(this.O + x - this.N - this.D.x);
                                this.E.x = c + this.D.x;
                                this.b(c);
                                this.c();
                                if (!u) {
                                    return;
                                }
                            }
                        }
                        int id2;
                        n3 = (id2 = mouseEvent.getID());
                    }
                    if (u) {
                        break Label_0137;
                    }
                }
                if (id != n) {
                    return;
                }
                ab = this;
                if (u) {
                    break Label_0155;
                }
                n3 = (this.D.contains(x, y) ? 1 : 0);
            }
            if (n3 != 0) {
                this.setCursor(Cursor.getPredefinedCursor(12));
                if (!u) {
                    return;
                }
            }
            ab = this;
        }
        ab.setCursor(Cursor.getDefaultCursor());
    }
    
    public void paint(final Graphics graphics) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        this.f();
        ab ab = this;
        ab ab2 = this;
        Label_0101: {
            final boolean j;
            Label_0067: {
                if (!u) {
                    if (this.A != null) {
                        j = this.J;
                        if (u) {
                            break Label_0067;
                        }
                        if (!j) {
                            this.L.drawImage(this.A, 0, 0, this.getBounds().width, this.getBounds().height, this);
                        }
                    }
                    ab = this;
                    ab2 = this;
                }
                if (u) {
                    break Label_0101;
                }
                final boolean u2 = ab2.u;
            }
            if (j) {
                this.L.setColor(this.R);
                this.L.drawString(this.l(), this.S, this.T);
            }
            ab = this;
        }
        Image image2;
        final Image image = image2 = ab.C;
        if (!u) {
            if (image != null) {
                this.L.drawImage(this.C, this.D.x, this.D.y, this.D.width, this.D.height, this);
            }
            final Image b;
            image2 = (b = this.B);
        }
        Label_0264: {
            ab ab3 = null;
            Label_0224: {
                if (!u) {
                    if (image != null) {
                        this.L.drawImage(this.B, this.D.x, this.D.y, this.E.x - this.D.x, this.E.height, this);
                    }
                    ab3 = this;
                    if (u) {
                        break Label_0224;
                    }
                    image2 = this.z;
                }
                if (image2 == null) {
                    break Label_0264;
                }
                ab3 = this;
            }
            ab3.L.drawImage(this.z, this.E.x, this.E.y, this.E.width, this.E.height, this);
        }
        graphics.drawImage(this.K, 0, 0, this);
    }
    
    public void a(final int s, final int t) {
        this.S = s;
        this.T = t;
    }
    
    public void c(final Color r) {
        this.R = r;
    }
    
    private String l() {
        final int n = (int)(this.s / 3600000L);
        final long n2 = this.s - n * 3600000;
        final int n3 = (int)(n2 / 60000L);
        return this.c(String.valueOf(n)) + ":" + this.c(String.valueOf(n3)) + ":" + this.c(String.valueOf((int)((int)(n2 - n3 * 60000) / 1000L)));
    }
    
    private String c(String string) {
        final String s = string;
        if (!com.easypano.tourweaver.b.f.u && s.length() < 2) {
            string = "0" + string;
            goto Label_0034;
        }
        return s;
    }
    
    public void sceneSwitching(final String s) {
    }
    
    public void sceneSwitched(final String s) {
    }
    
    public void moviePaused(final String s) {
    }
    
    public void movieSwitching(final String s) {
        System.out.println(s);
        this.G = this.F.get(s);
        this.r = this.G.h();
    }
    
    public void mapSwitching(final String s) {
    }
    
    public void updateRenderable(final com.easypano.tourweaver.f.a a, final h h, final int n) {
    }
    
    public void updateAnimation(final com.easypano.tourweaver.f.a a, final com.easypano.tourweaver.f.a a2, final int n) {
    }
    
    public void updateProgress(final com.easypano.tourweaver.f.a a, final double n) {
        this.b((long)(n * this.r));
    }
    
    public void mapSwitched(final String s) {
    }
    
    public void pause(final String s) {
    }
    
    static {
        final String[] u = new String[7];
        final int n = 0;
        final char[] charArray = "\rT4u".toCharArray();
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
                            c2 = '@';
                            break;
                        }
                        case 1: {
                            c2 = '=';
                            break;
                        }
                        case 2: {
                            c2 = 'Z';
                            break;
                        }
                        case 3: {
                            c2 = '\u001c';
                            break;
                        }
                        default: {
                            c2 = '#';
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
        p = new String(charArray).intern();
        final char[] charArray2 = "\u0013I;rG!O>".toCharArray();
        int length2;
        int n6;
        final int n5 = n6 = (length2 = charArray2.length);
        int n7 = 0;
        while (true) {
            Label_0214: {
                if (n5 > 1) {
                    break Label_0214;
                }
                length2 = (n6 = n7);
                do {
                    final char c3 = charArray2[n6];
                    char c4 = '\0';
                    switch (n7 % 5) {
                        case 0: {
                            c4 = '@';
                            break;
                        }
                        case 1: {
                            c4 = '=';
                            break;
                        }
                        case 2: {
                            c4 = 'Z';
                            break;
                        }
                        case 3: {
                            c4 = '\u001c';
                            break;
                        }
                        default: {
                            c4 = '#';
                            break;
                        }
                    }
                    charArray2[length2] = (char)(c3 ^ c4);
                    ++n7;
                } while (n5 == 0);
            }
            if (n5 > n7) {
                continue;
            }
            break;
        }
        q = new String(charArray2).intern();
        final char[] charArray3 = "\"\\(UN'".toCharArray();
        int length3;
        int n9;
        final int n8 = n9 = (length3 = charArray3.length);
        int n10 = 0;
        while (true) {
            Label_0330: {
                if (n8 > 1) {
                    break Label_0330;
                }
                length3 = (n9 = n10);
                do {
                    final char c5 = charArray3[n9];
                    char c6 = '\0';
                    switch (n10 % 5) {
                        case 0: {
                            c6 = '@';
                            break;
                        }
                        case 1: {
                            c6 = '=';
                            break;
                        }
                        case 2: {
                            c6 = 'Z';
                            break;
                        }
                        case 3: {
                            c6 = '\u001c';
                            break;
                        }
                        default: {
                            c6 = '#';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n10;
                } while (n8 == 0);
            }
            if (n8 > n10) {
                continue;
            }
            break;
        }
        u[n] = new String(charArray3).intern();
        final int n11 = 1;
        final char[] charArray4 = "3^(sO,X>UN'".toCharArray();
        int length4;
        int n13;
        final int n12 = n13 = (length4 = charArray4.length);
        int n14 = 0;
        while (true) {
            Label_0446: {
                if (n12 > 1) {
                    break Label_0446;
                }
                length4 = (n13 = n14);
                do {
                    final char c7 = charArray4[n13];
                    char c8 = '\0';
                    switch (n14 % 5) {
                        case 0: {
                            c8 = '@';
                            break;
                        }
                        case 1: {
                            c8 = '=';
                            break;
                        }
                        case 2: {
                            c8 = 'Z';
                            break;
                        }
                        case 3: {
                            c8 = '\u001c';
                            break;
                        }
                        default: {
                            c8 = '#';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n14;
                } while (n12 == 0);
            }
            if (n12 > n14) {
                continue;
            }
            break;
        }
        u[n11] = new String(charArray4).intern();
        final int n15 = 2;
        final char[] charArray5 = "3U5kw)P?".toCharArray();
        int length5;
        int n17;
        final int n16 = n17 = (length5 = charArray5.length);
        int n18 = 0;
        while (true) {
            Label_0562: {
                if (n16 > 1) {
                    break Label_0562;
                }
                length5 = (n17 = n18);
                do {
                    final char c9 = charArray5[n17];
                    char c10 = '\0';
                    switch (n18 % 5) {
                        case 0: {
                            c10 = '@';
                            break;
                        }
                        case 1: {
                            c10 = '=';
                            break;
                        }
                        case 2: {
                            c10 = 'Z';
                            break;
                        }
                        case 3: {
                            c10 = '\u001c';
                            break;
                        }
                        default: {
                            c10 = '#';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n18;
                } while (n16 == 0);
            }
            if (n16 > n18) {
                continue;
            }
            break;
        }
        u[n15] = new String(charArray5).intern();
        final int n19 = 3;
        final char[] charArray6 = "\"Z\u0013qD".toCharArray();
        int length6;
        int n21;
        final int n20 = n21 = (length6 = charArray6.length);
        int n22 = 0;
        while (true) {
            Label_0678: {
                if (n20 > 1) {
                    break Label_0678;
                }
                length6 = (n21 = n22);
                do {
                    final char c11 = charArray6[n21];
                    char c12 = '\0';
                    switch (n22 % 5) {
                        case 0: {
                            c12 = '@';
                            break;
                        }
                        case 1: {
                            c12 = '=';
                            break;
                        }
                        case 2: {
                            c12 = 'Z';
                            break;
                        }
                        case 3: {
                            c12 = '\u001c';
                            break;
                        }
                        default: {
                            c12 = '#';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n22;
                } while (n20 == 0);
            }
            if (n20 > n22) {
                continue;
            }
            break;
        }
        u[n19] = new String(charArray6).intern();
        final int n23 = 4;
        final char[] charArray7 = "4T7y`/Q5n".toCharArray();
        int length7;
        int n25;
        final int n24 = n25 = (length7 = charArray7.length);
        int n26 = 0;
        while (true) {
            Label_0794: {
                if (n24 > 1) {
                    break Label_0794;
                }
                length7 = (n25 = n26);
                do {
                    final char c13 = charArray7[n25];
                    char c14 = '\0';
                    switch (n26 % 5) {
                        case 0: {
                            c14 = '@';
                            break;
                        }
                        case 1: {
                            c14 = '=';
                            break;
                        }
                        case 2: {
                            c14 = 'Z';
                            break;
                        }
                        case 3: {
                            c14 = '\u001c';
                            break;
                        }
                        default: {
                            c14 = '#';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n26;
                } while (n24 == 0);
            }
            if (n24 > n26) {
                continue;
            }
            break;
        }
        u[n23] = new String(charArray7).intern();
        final int n27 = 5;
        final char[] charArray8 = "3I#pF".toCharArray();
        int length8;
        int n29;
        final int n28 = n29 = (length8 = charArray8.length);
        int n30 = 0;
        while (true) {
            Label_0910: {
                if (n28 > 1) {
                    break Label_0910;
                }
                length8 = (n29 = n30);
                do {
                    final char c15 = charArray8[n29];
                    char c16 = '\0';
                    switch (n30 % 5) {
                        case 0: {
                            c16 = '@';
                            break;
                        }
                        case 1: {
                            c16 = '=';
                            break;
                        }
                        case 2: {
                            c16 = 'Z';
                            break;
                        }
                        case 3: {
                            c16 = '\u001c';
                            break;
                        }
                        default: {
                            c16 = '#';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n30;
                } while (n28 == 0);
            }
            if (n28 > n30) {
                continue;
            }
            break;
        }
        u[n27] = new String(charArray8).intern();
        final int n31 = 6;
        final char[] charArray9 = "3^(sO,\u007f=UN'".toCharArray();
        int length9;
        int n33;
        final int n32 = n33 = (length9 = charArray9.length);
        int n34 = 0;
        while (true) {
            Label_1030: {
                if (n32 > 1) {
                    break Label_1030;
                }
                length9 = (n33 = n34);
                do {
                    final char c17 = charArray9[n33];
                    char c18 = '\0';
                    switch (n34 % 5) {
                        case 0: {
                            c18 = '@';
                            break;
                        }
                        case 1: {
                            c18 = '=';
                            break;
                        }
                        case 2: {
                            c18 = 'Z';
                            break;
                        }
                        case 3: {
                            c18 = '\u001c';
                            break;
                        }
                        default: {
                            c18 = '#';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n34;
                } while (n32 == 0);
            }
            if (n32 <= n34) {
                u[n31] = new String(charArray9).intern();
                ab.U = u;
                return;
            }
            continue;
        }
    }
}
