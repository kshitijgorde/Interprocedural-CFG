// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Graphics;
import com.easypano.tourweaver.g;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.Enumeration;
import com.easypano.tourweaver.d.e;
import java.awt.Color;
import java.util.Vector;
import java.awt.Image;
import com.easypano.tourweaver.PlayerListener;

public class o extends n implements PlayerListener
{
    Image F;
    Image G;
    Image H;
    String I;
    String J;
    String K;
    int L;
    Vector M;
    t N;
    String O;
    String P;
    int Q;
    Color R;
    String S;
    private static String[] T;
    
    public o() {
        this(new t());
    }
    
    public void a(t n) {
        final t t = n;
        if (!f.u) {
            if (t == null) {
                n = this.N;
            }
            n.e(this.O);
            n.c(this.L);
            n.a(super.d, this.R);
            n.b(this.getBounds().width);
        }
        t.doLayout();
        this.doLayout();
    }
    
    public void destroy() {
        super.destroy();
        this.F = null;
        this.G = null;
        this.H = null;
    }
    
    public o(final t n) {
        super(n);
        this.I = "";
        this.J = "";
        this.K = "";
        this.L = 0;
        this.M = new Vector();
        this.O = "";
        this.P = "";
        this.Q = 0;
        this.S = null;
        this.N = n;
    }
    
    public void a(final e e) {
        o o = this;
        o o2 = this;
        if (!f.u) {
            super.a(e);
            if (!(e instanceof y)) {
                return;
            }
            o = this;
            o2 = this;
        }
        o.Q = o2.Q + 1;
    }
    
    protected void a(final Image image, final Image image2, final Image image3) {
        final boolean u = f.u;
        this.a(this.N, image, image2, image3);
        this.N.doLayout();
        final Enumeration<t> elements = this.M.elements();
        while (elements.hasMoreElements()) {
            this.a(elements.nextElement(), image, image2, image3);
            if (u) {
                break;
            }
        }
    }
    
    protected void a(final t t, final Image image, final Image image2, final Image image3) {
        final boolean u = f.u;
        Image image4 = image;
        Image image5 = image;
        if (!u) {
            if (image != null) {
                this.a(image);
                t.c(this.L);
                t.a(image);
            }
            image4 = image2;
            image5 = image2;
        }
        if (!u) {
            if (image5 != null) {
                this.a(image2);
                t.c(this.L);
                t.b(image2);
            }
            image4 = image3;
        }
        if (image4 != null) {
            this.a(image3);
            t.c(this.L);
            t.c(image3);
        }
    }
    
    protected void a(final Image image) {
        final int height = image.getHeight(this);
        o o = this;
        if (!f.u) {
            if (this.Q == 0) {
                return;
            }
            o = this;
        }
        o.L = height / this.Q;
    }
    
    public void a(final Image h, final String s) {
        final boolean u = f.u;
        super.a(h, s);
        boolean b2;
        final boolean b = b2 = s.equals(this.I);
        if (!u) {
            if (b) {
                this.a(this.F = h, null, null);
                this.doLayout();
            }
            final boolean equals;
            b2 = (equals = s.equals(this.J));
        }
        o o = null;
        Label_0109: {
            if (!u) {
                if (b) {
                    this.a(null, this.G = h, null);
                }
                o = this;
                if (u) {
                    break Label_0109;
                }
                b2 = this.K.equals(s);
            }
            if (b2) {
                this.a(null, null, this.H = h);
            }
            this.N.a(h, s);
            o = this;
        }
        final Enumeration elements = o.M.elements();
        while (elements.hasMoreElements()) {
            final t t = elements.nextElement();
            t.a(h, s);
            t.doLayout();
            if (u) {
                break;
            }
        }
    }
    
    public void addAttributes(final String s, final String s2) {
        final boolean u = f.u;
        String s3 = s;
        String s4 = s;
        if (!u) {
            if (s == null) {
                return;
            }
            s3 = s2;
            s4 = s2;
        }
        if (!u) {
            if (s4 == null) {
                return;
            }
            super.addAttributes(s, s2);
            s3 = s;
        }
        boolean b5;
        boolean equals;
        boolean b4;
        boolean b3;
        boolean b2;
        final boolean b = b2 = (b3 = (b4 = (equals = (b5 = s3.equals(o.T[1])))));
        if (!u) {
            if (b) {
                this.I = s2;
                if (!u) {
                    return;
                }
            }
            final boolean b6;
            b2 = (b6 = (b3 = (b4 = (equals = (b5 = s.equals(o.T[4]))))));
        }
        if (!u) {
            if (b) {
                this.J = s2;
                if (!u) {
                    return;
                }
            }
            b3 = (b2 = (b4 = (equals = (b5 = s.equals(o.T[3])))));
        }
        if (!u) {
            if (b2) {
                this.K = s2;
                if (!u) {
                    return;
                }
            }
            b4 = (b3 = (equals = (b5 = s.equals(o.T[2]))));
        }
        if (!u) {
            if (b3) {
                this.O = s2.toLowerCase();
                if (!u) {
                    return;
                }
            }
            equals = (b4 = (b5 = s.equals(o.T[6])));
        }
        if (!u) {
            if (b4) {
                this.L = com.easypano.tourweaver.a.e.a(s2, 0);
                if (!u) {
                    return;
                }
            }
            b5 = (equals = s.equals(o.T[5]));
        }
        if (!u) {
            if (equals) {
                this.P = s2;
                if (!u) {
                    return;
                }
            }
            b5 = s.equals(o.T[0]);
        }
        if (b5) {
            this.R = com.easypano.tourweaver.a.e.b(s2);
        }
    }
    
    public boolean n() {
        final boolean u = f.u;
        boolean equals;
        final boolean b = equals = this.O.equals(o.T[7]);
        if (!u) {
            if (!b) {
                return false;
            }
            final boolean equals2;
            equals = (equals2 = this.P.equals(o.T[8]));
        }
        if (!u) {
            if (!b) {
                return false;
            }
            equals = true;
        }
        return equals;
        equals = false;
        return equals;
    }
    
    protected t o() {
        return new t();
    }
    
    public void a(final Vector vector, final String name) {
        final boolean u = f.u;
        final t o = this.o();
        o.setName(name);
        int i = vector.size() - 1;
        while (i >= 0) {
            final y c = this.N.c(vector.elementAt(i));
            if (u) {
                return;
            }
            if (!u) {
                if (c != null) {
                    o.a(c);
                }
                --i;
            }
            if (u) {
                break;
            }
        }
        this.a(o);
        this.N.doLayout();
        this.M.addElement(o);
    }
    
    public Image p() {
        return this.N.n();
    }
    
    public void a(final Rectangle rectangle) {
        this.N.a(rectangle);
    }
    
    public void sceneSwitching(final String s) {
    }
    
    public void sceneSwitched(final String s) {
        final boolean u = f.u;
        o o = this;
        if (!u) {
            if (!this.O.equals(com.easypano.tourweaver.b.o.T[7])) {
                return;
            }
            this.S = null;
            o = this;
        }
        final y d = o.N.d(s);
        o o2 = null;
        Label_0189: {
            int equals = 0;
            Label_0185: {
                if (!u) {
                    Label_0163: {
                        if (d != null) {
                            final int n = this.N.getBounds().x + d.getBounds().x;
                            final int n2 = this.N.getBounds().y + d.getBounds().y;
                            int n5;
                            int n4;
                            final int n3 = n4 = (n5 = n);
                            Label_0156: {
                                if (!u) {
                                    if (n3 < 0) {
                                        break Label_0156;
                                    }
                                    final int n6;
                                    n4 = (n6 = (n5 = n2));
                                }
                                if (!u) {
                                    if (n3 < 0) {
                                        break Label_0156;
                                    }
                                    n5 = (n4 = n + d.getBounds().width);
                                }
                                final int width = super.A.width;
                                if (!u) {
                                    if (n4 > width) {
                                        break Label_0156;
                                    }
                                    equals = (n5 = n2 + d.getBounds().height);
                                    if (u) {
                                        break Label_0185;
                                    }
                                    final int height = super.A.height;
                                }
                                if (n5 <= width) {
                                    break Label_0163;
                                }
                            }
                            this.b(n, n2);
                        }
                    }
                    this.doLayout();
                }
                o2 = this;
                if (u) {
                    break Label_0189;
                }
                equals = (this.P.equals(com.easypano.tourweaver.b.o.T[8]) ? 1 : 0);
            }
            if (equals == 0) {
                return;
            }
            o2 = this;
        }
        o2.S = s;
    }
    
    public void a(final g g) {
        final boolean u = f.u;
        super.a(g);
        this.N.a(g);
        final Enumeration<t> elements = this.M.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a(g);
            if (u) {
                break;
            }
        }
    }
    
    public void movieSwitching(final String s) {
        final boolean u = f.u;
        o o = this;
        if (!u) {
            if (!this.O.equals(com.easypano.tourweaver.b.o.T[11])) {
                return;
            }
            o = this;
        }
        final y d = o.N.d(s);
        if (d != null) {
            final int n = this.N.getBounds().x + d.getBounds().x;
            final int n2 = this.N.getBounds().y + d.getBounds().y;
            int n5;
            int n4;
            final int n3 = n4 = (n5 = n);
            Label_0141: {
                if (!u) {
                    if (n3 < 0) {
                        break Label_0141;
                    }
                    final int n6;
                    n4 = (n6 = (n5 = n2));
                }
                if (!u) {
                    if (n3 < 0) {
                        break Label_0141;
                    }
                    n5 = (n4 = n + d.getBounds().width);
                }
                final int width = super.A.width;
                if (!u) {
                    if (n4 > width) {
                        break Label_0141;
                    }
                    n5 = n2 + d.getBounds().height;
                    final int height = super.A.height;
                }
                if (n5 <= width) {
                    return;
                }
            }
            this.b(n, n2);
        }
    }
    
    public void mapSwitching(final String s) {
    }
    
    public void b(final int n, final int n2) {
        this.d(-n);
        this.e(-n2);
    }
    
    public void c() {
        this.repaint();
    }
    
    protected void b(final Graphics graphics) {
        final boolean u = f.u;
        o o = this;
        o o2 = this;
        if (!u) {
            if (super.d != null) {
                graphics.setColor(super.d);
                graphics.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
            }
            o = this;
            o2 = this;
        }
        if (!u) {
            if (o2.Q != 0) {
                return;
            }
            o = this;
        }
        if (o.F != null) {
            graphics.drawImage(this.F, 0, 0, this);
        }
    }
    
    public void paint(final Graphics graphics) {
        this.b(graphics);
        super.paint(graphics);
        if (!f.u) {
            if (super.a == null) {
                return;
            }
            graphics.setColor(super.a);
            graphics.drawLine(1, 0, this.getBounds().width - 2, 0);
            graphics.drawLine(0, 1, 0, this.getBounds().height - 2);
            graphics.drawLine(this.getBounds().width - 1, 1, this.getBounds().width - 1, this.getBounds().height - 2);
        }
        graphics.drawLine(1, this.getBounds().height - 1, this.getBounds().width - 2, this.getBounds().height - 1);
    }
    
    public int q() {
        return this.N.g();
    }
    
    public void mapSwitched(final String s) {
        final boolean u = f.u;
        System.out.println(o.T[10] + s);
        final boolean equals = this.O.equals(o.T[7]);
        o o = null;
        Label_0331: {
            Label_0327: {
                if (!u) {
                    if (equals) {
                        final boolean equals2 = this.P.equals(com.easypano.tourweaver.b.o.T[8]);
                        if (u) {
                            break Label_0327;
                        }
                        if (equals2) {
                            final Enumeration elements = this.M.elements();
                            while (elements.hasMoreElements()) {
                                final t n = elements.nextElement();
                                if (u) {
                                    return;
                                }
                                final String name = n.getName();
                                Label_0299: {
                                    final t t;
                                    Label_0150: {
                                        if (!u) {
                                            if (name == null) {
                                                break Label_0299;
                                            }
                                            t = n;
                                            if (u) {
                                                break Label_0150;
                                            }
                                            t.getName();
                                        }
                                        if (!name.equals(s)) {
                                            break Label_0299;
                                        }
                                        this.a(this.N = n);
                                        final t n2 = this.N;
                                    }
                                    final y d = t.d(this.S);
                                    this.doLayout();
                                    if (!u) {
                                        Label_0290: {
                                            if (d != null) {
                                                final int n3 = this.N.getBounds().x + d.getBounds().x;
                                                final int n4 = this.N.getBounds().y + d.getBounds().y;
                                                int n7;
                                                int n6;
                                                final int n5 = n6 = (n7 = n3);
                                                Label_0282: {
                                                    if (!u) {
                                                        if (n5 < 0) {
                                                            break Label_0282;
                                                        }
                                                        final int n8;
                                                        n6 = (n8 = (n7 = n4));
                                                    }
                                                    if (!u) {
                                                        if (n5 < 0) {
                                                            break Label_0282;
                                                        }
                                                        n7 = (n6 = n3 + d.getBounds().width);
                                                    }
                                                    final int width = super.A.width;
                                                    if (!u) {
                                                        if (n6 > width) {
                                                            break Label_0282;
                                                        }
                                                        n7 = n4 + d.getBounds().height;
                                                        final int height = super.A.height;
                                                    }
                                                    if (n7 <= width) {
                                                        break Label_0290;
                                                    }
                                                }
                                                this.b(n3, n4);
                                            }
                                        }
                                        this.repaint();
                                    }
                                    if (!u) {
                                        break;
                                    }
                                }
                                if (u) {
                                    break;
                                }
                            }
                            if (!u) {
                                return;
                            }
                        }
                    }
                    o = this;
                    if (u) {
                        break Label_0331;
                    }
                    this.O.equals(com.easypano.tourweaver.b.o.T[9]);
                }
            }
            if (!equals) {
                return;
            }
            o = this;
        }
        final y d2 = o.N.d(s);
        Label_0452: {
            if (d2 != null) {
                final int n9 = this.N.getBounds().x + d2.getBounds().x;
                final int n10 = this.N.getBounds().y + d2.getBounds().y;
                int n13;
                int n12;
                final int n11 = n12 = (n13 = n9);
                Label_0445: {
                    if (!u) {
                        if (n11 < 0) {
                            break Label_0445;
                        }
                        final int n14;
                        n12 = (n14 = (n13 = n10));
                    }
                    if (!u) {
                        if (n11 < 0) {
                            break Label_0445;
                        }
                        n13 = (n12 = n9 + d2.getBounds().width);
                    }
                    final int width2 = super.A.width;
                    if (!u) {
                        if (n12 > width2) {
                            break Label_0445;
                        }
                        n13 = n10 + d2.getBounds().height;
                        final int height2 = super.A.height;
                    }
                    if (n13 <= width2) {
                        break Label_0452;
                    }
                }
                this.b(n9, n10);
            }
        }
        this.doLayout();
    }
    
    public void moviePaused(final String s) {
    }
    
    public void movieStoped(final String s) {
    }
    
    static {
        final String[] t = new String[12];
        final int n = 0;
        final char[] charArray = "X{No\u0007_\\EI\u000bGqP".toCharArray();
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
                            c2 = '+';
                            break;
                        }
                        case 1: {
                            c2 = '\u001e';
                            break;
                        }
                        case 2: {
                            c2 = '\"';
                            break;
                        }
                        case 3: {
                            c2 = '\n';
                            break;
                        }
                        default: {
                            c2 = 'd';
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
        t[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "EqPg\u0005GWOm".toCharArray();
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
                            c4 = '+';
                            break;
                        }
                        case 1: {
                            c4 = '\u001e';
                            break;
                        }
                        case 2: {
                            c4 = '\"';
                            break;
                        }
                        case 3: {
                            c4 = '\n';
                            break;
                        }
                        default: {
                            c4 = 'd';
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
        t[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "]\u007fN\u007f\u0001".toCharArray();
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
                            c6 = '+';
                            break;
                        }
                        case 1: {
                            c6 = '\u001e';
                            break;
                        }
                        case 2: {
                            c6 = '\"';
                            break;
                        }
                        case 3: {
                            c6 = '\n';
                            break;
                        }
                        default: {
                            c6 = 'd';
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
        t[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "X{No\u0007_WOm".toCharArray();
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
                            c8 = '+';
                            break;
                        }
                        case 1: {
                            c8 = '\u001e';
                            break;
                        }
                        case 2: {
                            c8 = '\"';
                            break;
                        }
                        case 3: {
                            c8 = '\n';
                            break;
                        }
                        default: {
                            c8 = 'd';
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
        t[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "DhGx-Fy".toCharArray();
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
                            c10 = '+';
                            break;
                        }
                        case 1: {
                            c10 = '\u001e';
                            break;
                        }
                        case 2: {
                            c10 = '\"';
                            break;
                        }
                        case 3: {
                            c10 = '\n';
                            break;
                        }
                        default: {
                            c10 = 'd';
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
        t[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "XvM})DzG".toCharArray();
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
                            c12 = '+';
                            break;
                        }
                        case 1: {
                            c12 = '\u001e';
                            break;
                        }
                        case 2: {
                            c12 = '\"';
                            break;
                        }
                        case 3: {
                            c12 = '\n';
                            break;
                        }
                        default: {
                            c12 = 'd';
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
        t[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "BjGg,NwEb\u0010".toCharArray();
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
                            c14 = '+';
                            break;
                        }
                        case 1: {
                            c14 = '\u001e';
                            break;
                        }
                        case 2: {
                            c14 = '\"';
                            break;
                        }
                        case 3: {
                            c14 = '\n';
                            break;
                        }
                        default: {
                            c14 = 'd';
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
        t[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "X}Gd\u0001".toCharArray();
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
                            c16 = '+';
                            break;
                        }
                        case 1: {
                            c16 = '\u001e';
                            break;
                        }
                        case 2: {
                            c16 = '\"';
                            break;
                        }
                        case 3: {
                            c16 = '\n';
                            break;
                        }
                        default: {
                            c16 = 'd';
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
        t[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "Igok\u0014".toCharArray();
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
                            c18 = '+';
                            break;
                        }
                        case 1: {
                            c18 = '\u001e';
                            break;
                        }
                        case 2: {
                            c18 = '\"';
                            break;
                        }
                        case 3: {
                            c18 = '\n';
                            break;
                        }
                        default: {
                            c18 = 'd';
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
        t[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "F\u007fR".toCharArray();
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
                            c20 = '+';
                            break;
                        }
                        case 1: {
                            c20 = '\u001e';
                            break;
                        }
                        case 2: {
                            c20 = '\"';
                            break;
                        }
                        case 3: {
                            c20 = '\n';
                            break;
                        }
                        default: {
                            c20 = 'd';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 > n40) {
                continue;
            }
            break;
        }
        t[n37] = new String(charArray10).intern();
        final int n41 = 10;
        final char[] charArray11 = "XiK~\u0007CJMG\u0005[>KdDGwQh\u000bS>\u0018*".toCharArray();
        int length11;
        int n43;
        final int n42 = n43 = (length11 = charArray11.length);
        int n44 = 0;
        while (true) {
            Label_1278: {
                if (n42 > 1) {
                    break Label_1278;
                }
                length11 = (n43 = n44);
                do {
                    final char c21 = charArray11[n43];
                    char c22 = '\0';
                    switch (n44 % 5) {
                        case 0: {
                            c22 = '+';
                            break;
                        }
                        case 1: {
                            c22 = '\u001e';
                            break;
                        }
                        case 2: {
                            c22 = '\"';
                            break;
                        }
                        case 3: {
                            c22 = '\n';
                            break;
                        }
                        default: {
                            c22 = 'd';
                            break;
                        }
                    }
                    charArray11[length11] = (char)(c21 ^ c22);
                    ++n44;
                } while (n42 == 0);
            }
            if (n42 > n44) {
                continue;
            }
            break;
        }
        t[n41] = new String(charArray11).intern();
        final int n45 = 11;
        final char[] charArray12 = "FqTc\u0001".toCharArray();
        int length12;
        int n47;
        final int n46 = n47 = (length12 = charArray12.length);
        int n48 = 0;
        while (true) {
            Label_1398: {
                if (n46 > 1) {
                    break Label_1398;
                }
                length12 = (n47 = n48);
                do {
                    final char c23 = charArray12[n47];
                    char c24 = '\0';
                    switch (n48 % 5) {
                        case 0: {
                            c24 = '+';
                            break;
                        }
                        case 1: {
                            c24 = '\u001e';
                            break;
                        }
                        case 2: {
                            c24 = '\"';
                            break;
                        }
                        case 3: {
                            c24 = '\n';
                            break;
                        }
                        default: {
                            c24 = 'd';
                            break;
                        }
                    }
                    charArray12[length12] = (char)(c23 ^ c24);
                    ++n48;
                } while (n46 == 0);
            }
            if (n46 <= n48) {
                t[n45] = new String(charArray12).intern();
                o.T = t;
                return;
            }
            continue;
        }
    }
}
