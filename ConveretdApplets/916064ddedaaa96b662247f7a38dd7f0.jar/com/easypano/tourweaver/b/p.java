// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import com.easypano.tourweaver.a.e;
import java.awt.image.ImageObserver;
import java.awt.Graphics;
import java.util.Vector;
import java.awt.Image;
import java.awt.Color;

public class p extends o
{
    Color T;
    Color U;
    Color V;
    int W;
    int X;
    boolean Y;
    boolean Z;
    private static String[] ab;
    
    public p() {
        this(new u());
        super.C = 2;
    }
    
    public p(final u u) {
        super(u);
    }
    
    protected void a(final t t, final Image image, final Image image2, final Image image3) {
        super.a(t, image, image2, image3);
        final u u = (u)t;
    }
    
    public void a(final Vector vector, final String s) {
        super.a(vector, s);
    }
    
    protected void b(final Graphics graphics) {
        p p = this;
        if (!f.u) {
            if (super.Q != 0) {
                return;
            }
            p = this;
        }
        if (p.F != null) {
            graphics.drawImage(super.F, 10, 10, this);
        }
    }
    
    public void a(t n) {
        final t t = n;
        if (!f.u && t == null) {
            n = super.N;
            goto Label_0015;
        }
        final u u = (u)t;
        u.c(this.W, this.X);
        u.a(this.Y);
        u.b(this.Z);
        u.b(this.getBounds());
        u.a(this.T, this.U, this.V);
        super.a(n);
    }
    
    public void addAttributes(final String s, final String s2) {
        final boolean u = f.u;
        super.addAttributes(s, s2);
        boolean b5;
        boolean equals;
        boolean b4;
        boolean b3;
        boolean b2;
        final boolean b = b2 = (b3 = (b4 = (equals = (b5 = s.equals(p.ab[5])))));
        if (!u) {
            if (b) {
                this.T = com.easypano.tourweaver.a.e.b(s2);
                if (!u) {
                    return;
                }
            }
            final boolean b6;
            b2 = (b6 = (b3 = (b4 = (equals = (b5 = s.equals(p.ab[6]))))));
        }
        if (!u) {
            if (b) {
                this.U = com.easypano.tourweaver.a.e.b(s2);
                if (!u) {
                    return;
                }
            }
            b3 = (b2 = (b4 = (equals = (b5 = s.equals(p.ab[2])))));
        }
        if (!u) {
            if (b2) {
                this.V = com.easypano.tourweaver.a.e.b(s2);
                if (!u) {
                    return;
                }
            }
            b4 = (b3 = (equals = (b5 = s.equals(p.ab[1]))));
        }
        if (!u) {
            if (b3) {
                this.W = com.easypano.tourweaver.a.e.a(s2, 0);
                if (!u) {
                    return;
                }
            }
            equals = (b4 = (b5 = s.equals(p.ab[0])));
        }
        if (!u) {
            if (b4) {
                this.X = com.easypano.tourweaver.a.e.a(s2, 0);
                if (!u) {
                    return;
                }
            }
            b5 = (equals = s.equals(p.ab[4]));
        }
        if (!u) {
            if (equals) {
                this.Y = com.easypano.tourweaver.a.e.e(s2);
                if (!u) {
                    return;
                }
            }
            b5 = s.equals(p.ab[3]);
        }
        if (b5) {
            this.Z = com.easypano.tourweaver.a.e.e(s2);
        }
    }
    
    protected t o() {
        return new u();
    }
    
    static {
        final String[] ab = new String[7];
        final int n = 0;
        final char[] charArray = "d7|}Rb;wyn".toCharArray();
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
                            c2 = '\u0007';
                            break;
                        }
                        case 1: {
                            c2 = 'R';
                            break;
                        }
                        case 2: {
                            c2 = '\u0010';
                            break;
                        }
                        case 3: {
                            c2 = '\u0011';
                            break;
                        }
                        default: {
                            c2 = '\u001a';
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
        ab[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "d7|}Mn6dy".toCharArray();
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
                            c4 = '\u0007';
                            break;
                        }
                        case 1: {
                            c4 = 'R';
                            break;
                        }
                        case 2: {
                            c4 = '\u0010';
                            break;
                        }
                        case 3: {
                            c4 = '\u0011';
                            break;
                        }
                        default: {
                            c4 = '\u001a';
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
        ab[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "t7|tys\u0011\u007f}uu".toCharArray();
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
                            c6 = '\u0007';
                            break;
                        }
                        case 1: {
                            c6 = 'R';
                            break;
                        }
                        case 2: {
                            c6 = '\u0010';
                            break;
                        }
                        case 3: {
                            c6 = '\u0011';
                            break;
                        }
                        default: {
                            c6 = '\u001a';
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
        ab[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "n!Cxt`>u]si7".toCharArray();
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
                            c8 = '\u0007';
                            break;
                        }
                        case 1: {
                            c8 = 'R';
                            break;
                        }
                        case 2: {
                            c8 = '\u0010';
                            break;
                        }
                        case 3: {
                            c8 = '\u0011';
                            break;
                        }
                        default: {
                            c8 = '\u001a';
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
        ab[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "i3}tLn!ysvb".toCharArray();
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
                            c10 = '\u0007';
                            break;
                        }
                        case 1: {
                            c10 = 'R';
                            break;
                        }
                        case 2: {
                            c10 = '\u0010';
                            break;
                        }
                        case 3: {
                            c10 = '\u0011';
                            break;
                        }
                        default: {
                            c10 = '\u001a';
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
        ab[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "i=b|{k\u0011\u007f}uu".toCharArray();
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
                            c12 = '\u0007';
                            break;
                        }
                        case 1: {
                            c12 = 'R';
                            break;
                        }
                        case 2: {
                            c12 = '\u0010';
                            break;
                        }
                        case 3: {
                            c12 = '\u0011';
                            break;
                        }
                        default: {
                            c12 = '\u001a';
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
        ab[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = "h$ucYh>\u007fc".toCharArray();
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
                            c14 = '\u0007';
                            break;
                        }
                        case 1: {
                            c14 = 'R';
                            break;
                        }
                        case 2: {
                            c14 = '\u0010';
                            break;
                        }
                        case 3: {
                            c14 = '\u0011';
                            break;
                        }
                        default: {
                            c14 = '\u001a';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 <= n28) {
                ab[n25] = new String(charArray7).intern();
                p.ab = ab;
                return;
            }
            continue;
        }
    }
}
