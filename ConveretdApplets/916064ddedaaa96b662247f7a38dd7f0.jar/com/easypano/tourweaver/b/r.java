// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Dimension;
import java.util.Enumeration;
import java.awt.Image;
import com.easypano.tourweaver.d.e;
import java.util.Hashtable;
import com.easypano.tourweaver.PlayerListener;

public class r extends n implements PlayerListener
{
    private static final long serialVersionUID = 2041753100299759977L;
    public static final String F;
    public static final String G;
    public static final String H;
    private Hashtable I;
    private String J;
    private String K;
    private static String[] L;
    
    public r() {
        this.I = new Hashtable();
        this.J = "";
        this.K = r.F;
    }
    
    public void movieStoped(final String s) {
    }
    
    public void a(final e e) {
        final boolean u = f.u;
        e e2 = e;
        Label_0065: {
            if (!u) {
                if (!(e instanceof w)) {
                    break Label_0065;
                }
                e2 = e;
            }
            final w w = (w)e2;
            this.I.put(w.getName(), w);
            r r = this;
            Label_0061: {
                if (!u) {
                    if (!this.J.equals(com.easypano.tourweaver.b.r.L[2])) {
                        break Label_0061;
                    }
                    r = this;
                }
                r.c(com.easypano.tourweaver.b.r.L[1]);
            }
            if (!u) {
                return;
            }
        }
        super.a(e);
    }
    
    public void a(final Image image, final String s) {
        final boolean u = f.u;
        super.a(image, s);
        final Enumeration<w> elements = this.I.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().a(image, s);
            if (u) {
                break;
            }
        }
    }
    
    public void destroy() {
        final boolean u = f.u;
        final Enumeration<w> elements = this.I.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement().destroy();
            if (u) {
                break;
            }
        }
    }
    
    public void addAttributes(final String s, final String j) {
        final boolean u = f.u;
        super.addAttributes(s, j);
        boolean b2;
        boolean equals;
        final boolean b = equals = (b2 = s.equals(r.L[4]));
        if (!u) {
            if (b) {
                this.J = j;
                if (!u) {
                    return;
                }
            }
            final boolean b3;
            equals = (b3 = (b2 = s.equals(r.L[3])));
        }
        if (!u) {
            if (!b) {
                return;
            }
            b2 = (equals = j.equals("2"));
        }
        if (!u) {
            if (equals) {
                this.K = r.G;
                if (!u) {
                    return;
                }
            }
            b2 = j.equals("3");
        }
        if (b2) {
            this.K = r.H;
            if (!u) {
                return;
            }
        }
        this.K = r.F;
    }
    
    public String n() {
        return this.J;
    }
    
    public void sceneSwitching(final String s) {
    }
    
    public void sceneSwitched(final String s) {
        final boolean u = f.u;
        String j = s;
        r r = null;
        Label_0033: {
            if (!u) {
                if (s == null) {
                    return;
                }
                r = this;
                if (u) {
                    break Label_0033;
                }
                j = this.J;
            }
            if (!j.equals(com.easypano.tourweaver.b.r.L[0])) {
                return;
            }
            r = this;
        }
        r.c(s);
    }
    
    private void c(final String s) {
        final boolean u = f.u;
        final s s3;
        final s s2 = s3 = this.I.get(s);
        if (!u) {
            if (s3 == null) {
                return;
            }
            this.a(s2);
            this.doLayout();
        }
        final Dimension size = s3.getSize();
        final Dimension dimension = new Dimension(this.getBounds().width, this.getBounds().height);
        if (!u) {
            r r = null;
            Label_0230: {
                Label_0229: {
                    if (dimension.width > size.width) {
                        boolean b2;
                        final boolean b = b2 = this.K.equals(com.easypano.tourweaver.b.r.F);
                        if (!u) {
                            if (b && !u) {
                                break Label_0229;
                            }
                            final boolean equals;
                            b2 = (equals = this.K.equals(com.easypano.tourweaver.b.r.G));
                        }
                        if (!u) {
                            if (b) {
                                final Rectangle bounds = s2.getBounds();
                                bounds.x = (dimension.width - size.width) / 2;
                                s2.setBounds(bounds);
                                s2.setLocation(bounds.x, bounds.y);
                                s2.b(bounds.width, bounds.height);
                                if (!u) {
                                    break Label_0229;
                                }
                            }
                            r = this;
                            if (u) {
                                break Label_0230;
                            }
                            b2 = this.K.equals(com.easypano.tourweaver.b.r.H);
                        }
                        if (b2) {
                            final Rectangle bounds2 = s2.getBounds();
                            bounds2.x = dimension.width - size.width;
                            s2.setBounds(bounds2);
                        }
                    }
                }
                r = this;
            }
            r.repaint();
        }
    }
    
    public void movieSwitching(final String s) {
        r r = this;
        if (!f.u) {
            if (!this.J.equals(com.easypano.tourweaver.b.r.L[5])) {
                return;
            }
            r = this;
        }
        r.c(s);
    }
    
    public void mapSwitching(final String s) {
    }
    
    public void c() {
        this.repaint();
    }
    
    public void paint(final Graphics graphics) {
        final boolean u = f.u;
        final Color d = super.d;
        Label_0131: {
            if (!u) {
                if (d != null) {
                    graphics.setColor(super.d);
                    graphics.fillRect(0, 0, this.getBounds().width, this.getBounds().height);
                }
                super.paint(graphics);
                if (u) {
                    break Label_0131;
                }
                final Color a = super.a;
            }
            if (d == null) {
                return;
            }
            graphics.setColor(super.a);
            graphics.drawLine(1, 0, this.getBounds().width - 2, 0);
            graphics.drawLine(0, 1, 0, this.getBounds().height - 2);
            graphics.drawLine(this.getBounds().width - 1, 1, this.getBounds().width - 1, this.getBounds().height - 2);
        }
        graphics.drawLine(1, this.getBounds().height - 1, this.getBounds().width - 2, this.getBounds().height - 1);
    }
    
    public void mapSwitched(final String s) {
        r r = this;
        if (!f.u) {
            if (!this.J.equals(com.easypano.tourweaver.b.r.L[6])) {
                return;
            }
            r = this;
        }
        r.c(s);
    }
    
    public void moviePaused(final String s) {
    }
    
    public String o() {
        return this.K;
    }
    
    public void d(final String k) {
        this.K = k;
    }
    
    static {
        final String[] l = new String[7];
        final int n = 0;
        final char[] charArray = "\u0015:_\u000e\u001f".toCharArray();
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
                            c2 = 'g';
                            break;
                        }
                        case 1: {
                            c2 = 'S';
                            break;
                        }
                        case 2: {
                            c2 = '8';
                            break;
                        }
                        case 3: {
                            c2 = 'f';
                            break;
                        }
                        default: {
                            c2 = 'k';
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
        H = new String(charArray).intern();
        final char[] charArray2 = "\u000b6^\u0012".toCharArray();
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
                            c4 = 'g';
                            break;
                        }
                        case 1: {
                            c4 = 'S';
                            break;
                        }
                        case 2: {
                            c4 = '8';
                            break;
                        }
                        case 3: {
                            c4 = 'f';
                            break;
                        }
                        default: {
                            c4 = 'k';
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
        F = new String(charArray2).intern();
        final char[] charArray3 = "\u00046V\u0012\u000e\u0015".toCharArray();
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
                            c6 = 'g';
                            break;
                        }
                        case 1: {
                            c6 = 'S';
                            break;
                        }
                        case 2: {
                            c6 = '8';
                            break;
                        }
                        case 3: {
                            c6 = 'f';
                            break;
                        }
                        default: {
                            c6 = 'k';
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
        G = new String(charArray3).intern();
        final char[] charArray4 = "40]\b\u000e".toCharArray();
        int length4;
        int n12;
        final int n11 = n12 = (length4 = charArray4.length);
        int n13 = 0;
        while (true) {
            Label_0446: {
                if (n11 > 1) {
                    break Label_0446;
                }
                length4 = (n12 = n13);
                do {
                    final char c7 = charArray4[n12];
                    char c8 = '\0';
                    switch (n13 % 5) {
                        case 0: {
                            c8 = 'g';
                            break;
                        }
                        case 1: {
                            c8 = 'S';
                            break;
                        }
                        case 2: {
                            c8 = '8';
                            break;
                        }
                        case 3: {
                            c8 = 'f';
                            break;
                        }
                        default: {
                            c8 = 'k';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n13;
                } while (n11 == 0);
            }
            if (n11 > n13) {
                continue;
            }
            break;
        }
        l[n] = new String(charArray4).intern();
        final int n14 = 1;
        final char[] charArray5 = "C\u001dW\b\u000eC".toCharArray();
        int length5;
        int n16;
        final int n15 = n16 = (length5 = charArray5.length);
        int n17 = 0;
        while (true) {
            Label_0562: {
                if (n15 > 1) {
                    break Label_0562;
                }
                length5 = (n16 = n17);
                do {
                    final char c9 = charArray5[n16];
                    char c10 = '\0';
                    switch (n17 % 5) {
                        case 0: {
                            c10 = 'g';
                            break;
                        }
                        case 1: {
                            c10 = 'S';
                            break;
                        }
                        case 2: {
                            c10 = '8';
                            break;
                        }
                        case 3: {
                            c10 = 'f';
                            break;
                        }
                        default: {
                            c10 = 'k';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n17;
                } while (n15 == 0);
            }
            if (n15 > n17) {
                continue;
            }
            break;
        }
        l[n14] = new String(charArray5).intern();
        final int n18 = 2;
        final char[] charArray6 = ")<V\u0003".toCharArray();
        int length6;
        int n20;
        final int n19 = n20 = (length6 = charArray6.length);
        int n21 = 0;
        while (true) {
            Label_0678: {
                if (n19 > 1) {
                    break Label_0678;
                }
                length6 = (n20 = n21);
                do {
                    final char c11 = charArray6[n20];
                    char c12 = '\0';
                    switch (n21 % 5) {
                        case 0: {
                            c12 = 'g';
                            break;
                        }
                        case 1: {
                            c12 = 'S';
                            break;
                        }
                        case 2: {
                            c12 = '8';
                            break;
                        }
                        case 3: {
                            c12 = 'f';
                            break;
                        }
                        default: {
                            c12 = 'k';
                            break;
                        }
                    }
                    charArray6[length6] = (char)(c11 ^ c12);
                    ++n21;
                } while (n19 == 0);
            }
            if (n19 > n21) {
                continue;
            }
            break;
        }
        l[n18] = new String(charArray6).intern();
        final int n22 = 3;
        final char[] charArray7 = "\u0006?Q\u0001\u0005".toCharArray();
        int length7;
        int n24;
        final int n23 = n24 = (length7 = charArray7.length);
        int n25 = 0;
        while (true) {
            Label_0794: {
                if (n23 > 1) {
                    break Label_0794;
                }
                length7 = (n24 = n25);
                do {
                    final char c13 = charArray7[n24];
                    char c14 = '\0';
                    switch (n25 % 5) {
                        case 0: {
                            c14 = 'g';
                            break;
                        }
                        case 1: {
                            c14 = 'S';
                            break;
                        }
                        case 2: {
                            c14 = '8';
                            break;
                        }
                        case 3: {
                            c14 = 'f';
                            break;
                        }
                        default: {
                            c14 = 'k';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n25;
                } while (n23 == 0);
            }
            if (n23 > n25) {
                continue;
            }
            break;
        }
        l[n22] = new String(charArray7).intern();
        final int n26 = 4;
        final char[] charArray8 = "\u00112T\u0013\u000e".toCharArray();
        int length8;
        int n28;
        final int n27 = n28 = (length8 = charArray8.length);
        int n29 = 0;
        while (true) {
            Label_0910: {
                if (n27 > 1) {
                    break Label_0910;
                }
                length8 = (n28 = n29);
                do {
                    final char c15 = charArray8[n28];
                    char c16 = '\0';
                    switch (n29 % 5) {
                        case 0: {
                            c16 = 'g';
                            break;
                        }
                        case 1: {
                            c16 = 'S';
                            break;
                        }
                        case 2: {
                            c16 = '8';
                            break;
                        }
                        case 3: {
                            c16 = 'f';
                            break;
                        }
                        default: {
                            c16 = 'k';
                            break;
                        }
                    }
                    charArray8[length8] = (char)(c15 ^ c16);
                    ++n29;
                } while (n27 == 0);
            }
            if (n27 > n29) {
                continue;
            }
            break;
        }
        l[n26] = new String(charArray8).intern();
        final int n30 = 5;
        final char[] charArray9 = "*<N\u000f\u000e".toCharArray();
        int length9;
        int n32;
        final int n31 = n32 = (length9 = charArray9.length);
        int n33 = 0;
        while (true) {
            Label_1026: {
                if (n31 > 1) {
                    break Label_1026;
                }
                length9 = (n32 = n33);
                do {
                    final char c17 = charArray9[n32];
                    char c18 = '\0';
                    switch (n33 % 5) {
                        case 0: {
                            c18 = 'g';
                            break;
                        }
                        case 1: {
                            c18 = 'S';
                            break;
                        }
                        case 2: {
                            c18 = '8';
                            break;
                        }
                        case 3: {
                            c18 = 'f';
                            break;
                        }
                        default: {
                            c18 = 'k';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n33;
                } while (n31 == 0);
            }
            if (n31 > n33) {
                continue;
            }
            break;
        }
        l[n30] = new String(charArray9).intern();
        final int n34 = 6;
        final char[] charArray10 = "*2H".toCharArray();
        int length10;
        int n36;
        final int n35 = n36 = (length10 = charArray10.length);
        int n37 = 0;
        while (true) {
            Label_1146: {
                if (n35 > 1) {
                    break Label_1146;
                }
                length10 = (n36 = n37);
                do {
                    final char c19 = charArray10[n36];
                    char c20 = '\0';
                    switch (n37 % 5) {
                        case 0: {
                            c20 = 'g';
                            break;
                        }
                        case 1: {
                            c20 = 'S';
                            break;
                        }
                        case 2: {
                            c20 = '8';
                            break;
                        }
                        case 3: {
                            c20 = 'f';
                            break;
                        }
                        default: {
                            c20 = 'k';
                            break;
                        }
                    }
                    charArray10[length10] = (char)(c19 ^ c20);
                    ++n37;
                } while (n35 == 0);
            }
            if (n35 <= n37) {
                l[n34] = new String(charArray10).intern();
                r.L = l;
                return;
            }
            continue;
        }
    }
}
