// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import com.easypano.tourweaver.a.e;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Image;
import java.awt.Panel;

public class hb extends Panel
{
    Image a;
    String b;
    int c;
    int d;
    Color e;
    Color f;
    Rectangle g;
    int h;
    Color i;
    Image j;
    Graphics k;
    private static String[] z;
    
    public hb() {
        this.b = "";
        this.c = 0;
        this.d = 0;
        this.g = new Rectangle();
        this.h = 0;
        this.j = null;
        this.k = null;
    }
    
    public void a(final Image image, final String s) {
        if (s.equals(this.b)) {
            this.a(image);
        }
    }
    
    public void a(final Image a) {
        this.a = a;
        this.c = a.getWidth(this);
        this.d = a.getHeight(this);
        this.repaint();
    }
    
    public void a(final String s, final String b) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        boolean b4;
        boolean equals;
        boolean b3;
        final boolean b2 = b3 = (equals = (b4 = s.equals(hb.z[2])));
        if (!u) {
            if (b2) {
                this.i = com.easypano.tourweaver.a.e.b(b);
            }
            final boolean b5;
            b3 = (b5 = (equals = (b4 = s.equals(hb.z[3]))));
        }
        if (!u) {
            if (b2) {
                this.b = b;
                if (!u) {
                    return;
                }
            }
            equals = (b3 = (b4 = s.equals(hb.z[1])));
        }
        if (!u) {
            if (b3) {
                this.f = com.easypano.tourweaver.a.e.b(b);
                if (!u) {
                    return;
                }
            }
            b4 = (equals = s.equals(hb.z[4]));
        }
        if (!u) {
            if (equals) {
                this.e = com.easypano.tourweaver.a.e.b(b);
                if (!u) {
                    return;
                }
            }
            b4 = s.equals(hb.z[0]);
        }
        if (b4) {
            this.g = com.easypano.tourweaver.a.e.c(b);
        }
    }
    
    public void a(final int n) {
        this.h = (int)(n / 100.0 * this.g.width);
        this.repaint();
    }
    
    public void update(final Graphics graphics) {
        this.paint(graphics);
    }
    
    public void paint(final Graphics graphics) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        final Rectangle bounds = this.getBounds();
        hb hb = this;
        hb hb2 = this;
        hb hb3 = this;
        hb hb4 = this;
        Label_0059: {
            if (!u) {
                if (this.j == null) {
                    this.j = this.createImage(bounds.width, bounds.height);
                    hb = this;
                    hb2 = this;
                    hb3 = this;
                    hb4 = this;
                    if (u) {
                        break Label_0059;
                    }
                    if (this.j != null) {
                        this.k = this.j.getGraphics();
                    }
                }
                hb = this;
                hb2 = this;
                hb3 = this;
                hb4 = this;
            }
        }
        if (!u) {
            if (hb4.k == null) {
                return;
            }
            hb = this;
            hb2 = this;
            hb3 = this;
        }
        if (!u) {
            if (hb3.i != null) {
                this.k.setColor(this.i);
                this.k.fillRect(0, 0, bounds.width, bounds.height);
            }
            hb = this;
            hb2 = this;
        }
        if (!u) {
            if (hb2.a != null) {
                this.k.drawImage(this.a, (bounds.width - this.c) / 2, (bounds.height - this.d) / 2, this);
            }
            hb = this;
        }
        final Color e = hb.e;
        hb hb5 = null;
        Label_0267: {
            if (!u) {
                if (e != null) {
                    this.k.setColor(this.e);
                    this.k.fillRect(this.g.x, this.g.y, this.g.width, this.g.height);
                }
                hb5 = this;
                if (u) {
                    break Label_0267;
                }
                final Color f = this.f;
            }
            if (e != null) {
                this.k.setColor(this.f);
                this.k.fillRect(this.g.x, this.g.y, this.h, this.g.height);
            }
            hb5 = this;
        }
        if (hb5.j != null) {
            graphics.drawImage(this.j, 0, 0, this);
        }
    }
    
    public void a() {
        hb hb = this;
        if (!com.easypano.tourweaver.b.f.u) {
            if (this.k != null) {
                this.k.dispose();
            }
            this.j = null;
            hb = this;
        }
        hb.a = null;
    }
    
    static {
        final String[] z = new String[5];
        final int n = 0;
        final char[] charArray = "t?<uYc0*D".toCharArray();
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
                            c2 = '\u0016';
                            break;
                        }
                        case 1: {
                            c2 = '^';
                            break;
                        }
                        case 2: {
                            c2 = 'N';
                            break;
                        }
                        case 3: {
                            c2 = '7';
                            break;
                        }
                        default: {
                            c2 = '6';
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
        final char[] charArray2 = "t?<tYz1<".toCharArray();
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
                            c4 = '\u0016';
                            break;
                        }
                        case 1: {
                            c4 = '^';
                            break;
                        }
                        case 2: {
                            c4 = 'N';
                            break;
                        }
                        case 3: {
                            c4 = '7';
                            break;
                        }
                        default: {
                            c4 = '6';
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
        final char[] charArray3 = "t9\rXZy,".toCharArray();
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
                            c6 = '\u0016';
                            break;
                        }
                        case 1: {
                            c6 = '^';
                            break;
                        }
                        case 2: {
                            c6 = 'N';
                            break;
                        }
                        case 3: {
                            c6 = '7';
                            break;
                        }
                        default: {
                            c6 = '6';
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
        final char[] charArray4 = "t9\u0007ZQ".toCharArray();
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
                            c8 = '\u0016';
                            break;
                        }
                        case 1: {
                            c8 = '^';
                            break;
                        }
                        case 2: {
                            c8 = 'N';
                            break;
                        }
                        case 3: {
                            c8 = '7';
                            break;
                        }
                        default: {
                            c8 = '6';
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
        final char[] charArray5 = "t?<uQU1\"XD".toCharArray();
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
                            c10 = '\u0016';
                            break;
                        }
                        case 1: {
                            c10 = '^';
                            break;
                        }
                        case 2: {
                            c10 = 'N';
                            break;
                        }
                        case 3: {
                            c10 = '7';
                            break;
                        }
                        default: {
                            c10 = '6';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 <= n20) {
                z[n17] = new String(charArray5).intern();
                hb.z = z;
                return;
            }
            continue;
        }
    }
}
