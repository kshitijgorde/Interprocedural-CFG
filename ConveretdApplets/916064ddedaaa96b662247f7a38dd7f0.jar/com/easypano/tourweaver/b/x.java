// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.b;

import java.awt.image.ImageObserver;
import com.easypano.tourweaver.a.e;
import java.awt.Image;

public class x extends a
{
    Image p;
    Image q;
    Image r;
    Image s;
    String t;
    String u;
    String v;
    String w;
    int x;
    int y;
    int z;
    private static String[] A;
    
    public x() {
        this.t = "";
        this.u = "";
        this.v = "";
        this.w = "";
        this.x = 0;
        this.y = 0;
        this.z = 5;
    }
    
    public void addAttributes(final String s, final String s2) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        super.addAttributes(s, s2);
        boolean b3;
        boolean equals;
        boolean b2;
        final boolean b = b2 = (equals = (b3 = s.equals(com.easypano.tourweaver.b.x.A[1])));
        if (!u) {
            if (b) {
                this.t = s2;
                if (!u) {
                    return;
                }
            }
            final boolean b4;
            b2 = (b4 = (equals = (b3 = s.equals(com.easypano.tourweaver.b.x.A[2]))));
        }
        if (!u) {
            if (b) {
                this.u = s2;
                if (!u) {
                    return;
                }
            }
            equals = (b2 = (b3 = s.equals(com.easypano.tourweaver.b.x.A[3])));
        }
        if (!u) {
            if (b2) {
                this.v = s2;
                if (!u) {
                    return;
                }
            }
            b3 = (equals = s.equals(com.easypano.tourweaver.b.x.A[4]));
        }
        if (!u) {
            if (equals) {
                this.w = s2;
                if (!u) {
                    return;
                }
            }
            b3 = s.equals(com.easypano.tourweaver.b.x.A[0]);
        }
        if (b3) {
            this.z = com.easypano.tourweaver.a.e.a(s2, 5);
        }
    }
    
    public Image e() {
        return this.p;
    }
    
    public int getWidth() {
        return this.x;
    }
    
    public int getHeight() {
        return this.y;
    }
    
    public int f() {
        return this.z;
    }
    
    public void a(final b b) {
        b.a(this.q, this.r, this.s);
    }
    
    public void a(final Image image, final String s) {
        final boolean u = com.easypano.tourweaver.b.f.u;
        boolean b2;
        boolean equals;
        final boolean b = equals = (b2 = s.equals(this.t));
        if (!u) {
            if (b) {
                this.p = image;
                this.x = this.p.getWidth(this);
                this.y = this.p.getHeight(this);
            }
            final boolean b3;
            equals = (b3 = (b2 = s.equals(this.u)));
        }
        if (!u) {
            if (b) {
                this.q = image;
            }
            b2 = (equals = s.equals(this.v));
        }
        if (!u) {
            if (equals) {
                this.r = image;
            }
            b2 = s.equals(this.w);
        }
        if (b2) {
            this.s = image;
        }
    }
    
    static {
        final String[] a = new String[5];
        final int n = 0;
        final char[] charArray = "\u001dD\u00199X\u0018D\u0014".toCharArray();
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
                            c2 = 'q';
                            break;
                        }
                        case 1: {
                            c2 = '+';
                            break;
                        }
                        case 2: {
                            c2 = 'z';
                            break;
                        }
                        case 3: {
                            c2 = 'X';
                            break;
                        }
                        default: {
                            c2 = ',';
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
        a[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "\u0013L35K".toCharArray();
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
                            c4 = 'q';
                            break;
                        }
                        case 1: {
                            c4 = '+';
                            break;
                        }
                        case 2: {
                            c4 = 'z';
                            break;
                        }
                        case 3: {
                            c4 = 'X';
                            break;
                        }
                        default: {
                            c4 = ',';
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
        a[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "\u0013_47^\u001cJ\u0016\u0011A\u0016".toCharArray();
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
                            c6 = 'q';
                            break;
                        }
                        case 1: {
                            c6 = '+';
                            break;
                        }
                        case 2: {
                            c6 = 'z';
                            break;
                        }
                        case 3: {
                            c6 = 'X';
                            break;
                        }
                        default: {
                            c6 = ',';
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
        a[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "\u0013_5.I\u0003b\u0017?".toCharArray();
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
                            c8 = 'q';
                            break;
                        }
                        case 1: {
                            c8 = '+';
                            break;
                        }
                        case 2: {
                            c8 = 'z';
                            break;
                        }
                        case 3: {
                            c8 = 'X';
                            break;
                        }
                        default: {
                            c8 = ',';
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
        a[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "\u0013_**I\u0002X35K".toCharArray();
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
                            c10 = 'q';
                            break;
                        }
                        case 1: {
                            c10 = '+';
                            break;
                        }
                        case 2: {
                            c10 = 'z';
                            break;
                        }
                        case 3: {
                            c10 = 'X';
                            break;
                        }
                        default: {
                            c10 = ',';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 <= n20) {
                a[n17] = new String(charArray5).intern();
                x.A = a;
                return;
            }
            continue;
        }
    }
}
