// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import com.easypano.tourweaver.a.e;
import java.awt.Image;
import java.awt.Color;

public class y extends a
{
    Color p;
    Color q;
    Color r;
    int s;
    Image t;
    Image u;
    Image v;
    int w;
    Image x;
    int y;
    String z;
    String A;
    Color B;
    Color C;
    Color D;
    int E;
    int F;
    int G;
    int H;
    boolean I;
    private static String[] J;
    
    public y() {
        this.s = 0;
        this.t = null;
        this.u = null;
        this.v = null;
        this.w = 0;
        this.x = null;
        this.y = 0;
        this.z = "";
        this.E = 0;
        this.F = 0;
        this.G = 0;
        this.H = 0;
        this.I = false;
    }
    
    public void destroy() {
        this.t = null;
        this.u = null;
        this.v = null;
        this.x = null;
    }
    
    public void b(final int s) {
        this.s = s;
    }
    
    public void c(final String z) {
        this.z = z;
    }
    
    public void a(final Color p3, final Color q, final Color r) {
        this.p = p3;
        this.q = q;
        this.r = r;
    }
    
    public void a(final Color c, final Color d) {
        this.C = c;
        super.d = this.C;
        this.D = d;
    }
    
    public void c(final int w) {
        this.w = w;
    }
    
    public void addAttributes(final String s, final String s2) {
        y y = this;
        String s3 = s;
        if (!com.easypano.tourweaver.b.f.u) {
            super.addAttributes(s, s2);
            if (!s.equals(com.easypano.tourweaver.b.y.J[9])) {
                return;
            }
            y = this;
            s3 = s2;
        }
        y.s = com.easypano.tourweaver.a.e.a(s3, 0);
    }
    
    public void a(final Image v, final String s) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        boolean b2;
        final boolean b = b2 = s.equals(com.easypano.tourweaver.b.y.J[6]);
        if (!u) {
            if (b) {
                this.y = v.getWidth(com.easypano.tourweaver.a.e.f);
                this.t = v;
                this.F = this.s * this.w;
                this.G = this.E + this.y;
                this.H = this.F + this.w;
                this.x = this.t;
                final Rectangle bounds = this.getBounds();
                bounds.width = this.y;
                this.setBounds(bounds);
                this.repaint();
                if (!u) {
                    return;
                }
            }
            final boolean equals;
            b2 = (equals = s.equals(com.easypano.tourweaver.b.y.J[8]));
        }
        if (!u) {
            if (b) {
                this.y = v.getWidth(com.easypano.tourweaver.a.e.f);
                this.u = v;
                if (!u) {
                    return;
                }
            }
            b2 = s.equals(com.easypano.tourweaver.b.y.J[7]);
        }
        if (b2) {
            this.y = v.getWidth(com.easypano.tourweaver.a.e.f);
            this.v = v;
        }
    }
    
    public void paint(final Graphics graphics) {
        if (this.x != null) {
            graphics.drawImage(this.x, 0, 0, this.getBounds().width, this.getBounds().height, this.E, this.F, this.G, this.H, this);
        }
    }
    
    public void a(final Rectangle rectangle) {
        rectangle.x = this.E;
        rectangle.y = this.F;
        rectangle.width = this.G - this.E;
        rectangle.height = this.w;
    }
    
    public void processMouseMotionEvent(final MouseEvent mouseEvent) {
        y y = this;
        if (!com.easypano.tourweaver.b.f.u) {
            super.processMouseMotionEvent(mouseEvent);
            if (mouseEvent.getID() != 503) {
                return;
            }
            y = this;
        }
        y.a(Cursor.getPredefinedCursor(12));
    }
    
    public void processMouseEvent(final MouseEvent mouseEvent) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        super.processMouseEvent(mouseEvent);
        int n2;
        final int n = n2 = mouseEvent.getID();
        int n4;
        final int n3 = n4 = 504;
        y y = null;
        Label_0191: {
            Label_0190: {
                if (!u) {
                    if (n == n3) {
                        y = this;
                        if (u) {
                            break Label_0191;
                        }
                        if (this.I) {
                            break Label_0190;
                        }
                        this.x = this.v;
                        this.B = this.q;
                        this.a(Cursor.getPredefinedCursor(12));
                        if (!u) {
                            break Label_0190;
                        }
                    }
                    final int id;
                    n2 = (id = mouseEvent.getID());
                    final int n5;
                    n4 = (n5 = 505);
                }
                int modifiers = 0;
                Label_0141: {
                    if (!u) {
                        if (n == n3) {
                            y = this;
                            if (u) {
                                break Label_0191;
                            }
                            if (this.I) {
                                break Label_0190;
                            }
                            this.x = this.t;
                            this.B = this.p;
                            super.d = this.C;
                            this.a(Cursor.getDefaultCursor());
                            if (!u) {
                                break Label_0190;
                            }
                        }
                        modifiers = (n2 = mouseEvent.getID());
                        if (u) {
                            break Label_0141;
                        }
                        n4 = 501;
                    }
                    if (n2 != n4) {
                        break Label_0190;
                    }
                    modifiers = mouseEvent.getModifiers();
                }
                final int n6 = modifiers;
                if (u) {
                    return;
                }
                if ((n6 & 0x10) != 0x0) {
                    y y2 = this;
                    if (!u) {
                        if (this.A == null) {
                            this.e();
                        }
                        super.f.removeListbox();
                        this.a(Cursor.getDefaultCursor());
                        y2 = this;
                    }
                    y2.b(this.A);
                }
            }
            y = this;
        }
        y.c();
    }
    
    protected void e() {
        final boolean u = com.easypano.tourweaver.b.f.u;
        boolean b2;
        final boolean b = b2 = this.z.toLowerCase().equals(com.easypano.tourweaver.b.y.J[0]);
        if (!u) {
            if (b) {
                this.A = com.easypano.tourweaver.b.y.J[1] + this.getName() + ")";
                if (!u) {
                    return;
                }
            }
            final boolean equals;
            b2 = (equals = this.z.toLowerCase().equals(com.easypano.tourweaver.b.y.J[3]));
        }
        y y = null;
        Label_0147: {
            if (!u) {
                if (b) {
                    this.A = com.easypano.tourweaver.b.y.J[2] + this.getName() + ")";
                    if (!u) {
                        return;
                    }
                }
                y = this;
                if (u) {
                    break Label_0147;
                }
                b2 = this.z.toLowerCase().equals(com.easypano.tourweaver.b.y.J[4]);
            }
            if (!b2) {
                return;
            }
            y = this;
        }
        y.A = com.easypano.tourweaver.b.y.J[5] + this.getName() + ")";
    }
    
    public void a(final boolean i) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        this.I = i;
        y y = this;
        Label_0072: {
            if (!u) {
                if (this.I) {
                    this.x = this.u;
                    this.B = this.r;
                    super.d = this.D;
                    if (!u) {
                        break Label_0072;
                    }
                }
                this.x = this.t;
                this.B = this.p;
                y = this;
            }
            y.d = this.C;
        }
        this.c();
    }
    
    static {
        final String[] j = new String[10];
        final int n = 0;
        final char[] charArray = "5.^\u001f\u0012".toCharArray();
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
                            c2 = 'F';
                            break;
                        }
                        case 1: {
                            c2 = 'M';
                            break;
                        }
                        case 2: {
                            c2 = ';';
                            break;
                        }
                        case 3: {
                            c2 = 'q';
                            break;
                        }
                        default: {
                            c2 = 'w';
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
        j[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "5:R\u0005\u0014.\u0019T\"\u0014##^Y$2?R\u001f\u0010f".toCharArray();
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
                            c4 = 'F';
                            break;
                        }
                        case 1: {
                            c4 = 'M';
                            break;
                        }
                        case 2: {
                            c4 = ';';
                            break;
                        }
                        case 3: {
                            c4 = 'q';
                            break;
                        }
                        default: {
                            c4 = 'w';
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
        j[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "5:R\u0005\u0014.\u0019T<\u00180$^Y$2?R\u001f\u0010f".toCharArray();
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
                            c6 = 'F';
                            break;
                        }
                        case 1: {
                            c6 = 'M';
                            break;
                        }
                        case 2: {
                            c6 = ';';
                            break;
                        }
                        case 3: {
                            c6 = 'q';
                            break;
                        }
                        default: {
                            c6 = 'w';
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
        j[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "+\"M\u0018\u0012".toCharArray();
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
                            c8 = 'F';
                            break;
                        }
                        case 1: {
                            c8 = 'M';
                            break;
                        }
                        case 2: {
                            c8 = ';';
                            break;
                        }
                        case 3: {
                            c8 = 'q';
                            break;
                        }
                        default: {
                            c8 = 'w';
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
        j[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "+,K".toCharArray();
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
                            c10 = 'F';
                            break;
                        }
                        case 1: {
                            c10 = 'M';
                            break;
                        }
                        case 2: {
                            c10 = ';';
                            break;
                        }
                        case 3: {
                            c10 = 'q';
                            break;
                        }
                        default: {
                            c10 = 'w';
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
        j[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "5:R\u0005\u0014.\u0019T<\u00166eh\u0005\u0005/#\\Q".toCharArray();
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
                            c12 = 'F';
                            break;
                        }
                        case 1: {
                            c12 = 'M';
                            break;
                        }
                        case 2: {
                            c12 = ';';
                            break;
                        }
                        case 3: {
                            c12 = 'q';
                            break;
                        }
                        default: {
                            c12 = 'w';
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
        j[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "(\"I\u001c\u0016*".toCharArray();
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
                            c14 = 'F';
                            break;
                        }
                        case 1: {
                            c14 = 'M';
                            break;
                        }
                        case 2: {
                            c14 = ';';
                            break;
                        }
                        case 3: {
                            c14 = 'q';
                            break;
                        }
                        default: {
                            c14 = 'w';
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
        j[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = ");^\u0003".toCharArray();
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
                            c16 = 'F';
                            break;
                        }
                        case 1: {
                            c16 = 'M';
                            break;
                        }
                        case 2: {
                            c16 = ';';
                            break;
                        }
                        case 3: {
                            c16 = 'q';
                            break;
                        }
                        default: {
                            c16 = 'w';
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
        j[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "5(W\u0014\u00142".toCharArray();
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
                            c18 = 'F';
                            break;
                        }
                        case 1: {
                            c18 = 'M';
                            break;
                        }
                        case 2: {
                            c18 = ';';
                            break;
                        }
                        case 3: {
                            c18 = 'q';
                            break;
                        }
                        default: {
                            c18 = 'w';
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
        j[n33] = new String(charArray9).intern();
        final int n37 = 9;
        final char[] charArray10 = "/ \\?\u0018".toCharArray();
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
                            c20 = 'F';
                            break;
                        }
                        case 1: {
                            c20 = 'M';
                            break;
                        }
                        case 2: {
                            c20 = ';';
                            break;
                        }
                        case 3: {
                            c20 = 'q';
                            break;
                        }
                        default: {
                            c20 = 'w';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n40;
                } while (n38 == 0);
            }
            if (n38 <= n40) {
                j[n37] = new String(charArray10).intern();
                y.J = j;
                return;
            }
            continue;
        }
    }
}
