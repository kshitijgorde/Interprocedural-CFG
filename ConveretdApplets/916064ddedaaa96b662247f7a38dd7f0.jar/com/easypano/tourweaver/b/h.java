// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Graphics;
import java.awt.image.ImageObserver;
import com.easypano.tourweaver.d.e;
import java.awt.Rectangle;
import java.util.Enumeration;
import java.awt.event.KeyEvent;
import java.awt.Image;

public class h extends f
{
    Image v;
    Image w;
    Image x;
    String y;
    String z;
    String A;
    int B;
    int C;
    int D;
    int E;
    ab F;
    private static String[] G;
    
    public h() {
        this.y = "";
        this.z = "";
        this.A = "";
        this.B = 0;
        this.C = 0;
        this.D = 0;
        this.E = 0;
        this.enableEvents(8L);
    }
    
    public void processKeyEvent(final KeyEvent keyEvent) {
        System.out.println(h.G[4] + keyEvent);
    }
    
    public void setBounds(final int n, final int n2, final int n3, final int n4) {
        final boolean u = f.u;
        super.setBounds(n, n2, n3, n4);
        final Enumeration d = this.d();
        while (d.hasMoreElements()) {
            final a a = d.nextElement();
            if (a.getName().equals(h.G[0])) {
                this.c(a);
            }
            if (u) {
                break;
            }
        }
    }
    
    private void c(final a a) {
        final Rectangle bounds = this.getBounds();
        final Rectangle bounds2 = a.getBounds();
        a.setBounds(bounds.width - bounds2.width - 8, bounds2.y, bounds2.width, bounds2.height);
    }
    
    public void a(final e e) {
        final boolean u = f.u;
        super.a(e);
        e e2 = e;
        if (!u) {
            if (!(e instanceof a)) {
                return;
            }
            e2 = e;
        }
        final String name;
        final String s = name = e2.getName();
        if (!u) {
            if (name == null) {
                return;
            }
            s.trim();
        }
        if (name.equals(h.G[0])) {
            this.c((a)e);
        }
    }
    
    public void addAttributes(final String s, final String a) {
        final boolean u = f.u;
        super.addAttributes(s, a);
        boolean b2;
        final boolean b = b2 = s.equals(h.G[2]);
        if (!u) {
            if (b) {
                this.y = a;
                if (!u) {
                    return;
                }
            }
            final boolean equals;
            b2 = (equals = s.equals(h.G[3]));
        }
        if (!u) {
            if (b) {
                this.z = a;
                if (!u) {
                    return;
                }
            }
            b2 = s.equals(h.G[1]);
        }
        if (b2) {
            this.A = a;
        }
    }
    
    public void a(final Image x, final String s) {
        final boolean u = f.u;
        boolean b2;
        final boolean b = b2 = s.equals(this.y);
        if (!u) {
            if (b) {
                this.v = x;
                this.B = x.getWidth(this);
                this.c();
            }
            final boolean equals;
            b2 = (equals = s.equals(this.z));
        }
        if (!u) {
            if (b) {
                this.w = x;
                this.C = x.getWidth(this);
                this.E = x.getHeight(this);
                this.c();
            }
            b2 = s.equals(this.A);
        }
        if (b2) {
            this.x = x;
            this.c();
        }
        super.a(x, s);
    }
    
    public void c() {
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        final boolean u = f.u;
        final Rectangle bounds = this.getBounds();
        Image image2;
        final Image image = image2 = this.v;
        if (!u) {
            if (image != null) {
                graphics.drawImage(this.v, 0, 0, this);
            }
            final Image w;
            image2 = (w = this.w);
        }
        h h = null;
        Label_0125: {
            if (!u) {
                if (image != null) {
                    final int n2;
                    int n = n2 = bounds.width - this.B - this.D;
                    if (!u) {
                        if (n2 < 0) {
                            n = 0;
                        }
                        graphics.drawImage(this.w, this.B, 0, n, this.E, this);
                    }
                }
                h = this;
                if (u) {
                    break Label_0125;
                }
                image2 = this.x;
            }
            if (image2 != null) {
                graphics.drawImage(this.x, bounds.width - this.D, 0, this);
            }
            h = this;
        }
        h.paint(graphics);
    }
    
    static {
        final String[] g = new String[5];
        final int n = 0;
        final char[] charArray = "F}!Rq".toCharArray();
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
                            c2 = '%';
                            break;
                        }
                        case 1: {
                            c2 = '\u0011';
                            break;
                        }
                        case 2: {
                            c2 = 'N';
                            break;
                        }
                        case 3: {
                            c2 = '!';
                            break;
                        }
                        default: {
                            c2 = '\u0014';
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
        g[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "Gv\u0007Ls\u0016".toCharArray();
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
                            c4 = '%';
                            break;
                        }
                        case 1: {
                            c4 = '\u0011';
                            break;
                        }
                        case 2: {
                            c4 = 'N';
                            break;
                        }
                        case 3: {
                            c4 = '!';
                            break;
                        }
                        default: {
                            c4 = '\u0014';
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
        g[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "Gv\u0007Ls\u0014".toCharArray();
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
                            c6 = '%';
                            break;
                        }
                        case 1: {
                            c6 = '\u0011';
                            break;
                        }
                        case 2: {
                            c6 = 'N';
                            break;
                        }
                        case 3: {
                            c6 = '!';
                            break;
                        }
                        default: {
                            c6 = '\u0014';
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
        g[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "Gv\u0007Ls\u0017".toCharArray();
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
                            c8 = '%';
                            break;
                        }
                        case 1: {
                            c8 = '\u0011';
                            break;
                        }
                        case 2: {
                            c8 = 'N';
                            break;
                        }
                        case 3: {
                            c8 = '!';
                            break;
                        }
                        default: {
                            c8 = '\u0014';
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
        g[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "nt7db@\u007f:\u0001}K1(TxI1,@f\u0005".toCharArray();
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
                            c10 = '%';
                            break;
                        }
                        case 1: {
                            c10 = '\u0011';
                            break;
                        }
                        case 2: {
                            c10 = 'N';
                            break;
                        }
                        case 3: {
                            c10 = '!';
                            break;
                        }
                        default: {
                            c10 = '\u0014';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 <= n20) {
                g[n17] = new String(charArray5).intern();
                h.G = g;
                return;
            }
            continue;
        }
    }
}
