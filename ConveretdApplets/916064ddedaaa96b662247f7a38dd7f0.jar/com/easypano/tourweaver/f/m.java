// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.util.Enumeration;
import java.awt.Image;
import com.easypano.tourweaver.d.e;

public class m extends i implements e
{
    double q;
    double r;
    double s;
    String t;
    m u;
    String v;
    j w;
    private static String[] z;
    
    public m() {
        this.q = 0.0;
        this.r = 0.0;
        this.s = 0.0;
    }
    
    public synchronized void a(final b b) {
        final j w = this.w;
        if (!com.easypano.tourweaver.f.r.i) {
            if (w == null) {
                return;
            }
            final j w2 = this.w;
        }
        w.a(b);
    }
    
    public void a(final j w) {
        this.w = w;
        j j = w;
        if (!com.easypano.tourweaver.f.r.i) {
            if (w == null) {
                return;
            }
            j = w;
        }
        j.a(this);
    }
    
    public double q() {
        return this.q;
    }
    
    public double r() {
        return this.r;
    }
    
    public double s() {
        return this.s;
    }
    
    public String t() {
        return this.t;
    }
    
    public void a() {
        final j w = this.w;
        if (!com.easypano.tourweaver.f.r.i) {
            if (w == null) {
                return;
            }
            this.w.a(super.m);
            final j w2 = this.w;
        }
        w.a();
    }
    
    public void b() {
        final j w = this.w;
        if (!com.easypano.tourweaver.f.r.i) {
            if (w == null) {
                return;
            }
            final j w2 = this.w;
        }
        w.b();
    }
    
    public void f() {
        final j w = this.w;
        if (!com.easypano.tourweaver.f.r.i) {
            if (w == null) {
                return;
            }
            final j w2 = this.w;
        }
        w.f();
    }
    
    public void a(final double n) {
        final j w = this.w;
        if (!com.easypano.tourweaver.f.r.i) {
            if (w == null) {
                return;
            }
            final j w2 = this.w;
        }
        w.a(n);
    }
    
    public void d(final String t) {
        this.t = t;
    }
    
    public void a(final double q, final double r, final double s) {
        this.q = q;
        this.r = r;
        this.s = s;
    }
    
    public void a(final int n) {
        super.o = n * 1000;
    }
    
    public void e(final String v) {
        this.v = v;
    }
    
    public void addAttributes(final String s, final String s2) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        String s3 = s;
        String s4 = s;
        if (!i) {
            if (s == null) {
                return;
            }
            s3 = s2;
            s4 = s2;
        }
        if (!i) {
            if (s4 == null) {
                return;
            }
            s3 = s;
        }
        boolean b4;
        boolean equals;
        boolean b3;
        boolean b2;
        final boolean b = b2 = (b3 = (equals = (b4 = s3.equals(m.z[5]))));
        if (!i) {
            if (b) {
                this.t = s2;
                if (!i) {
                    return;
                }
            }
            final boolean b5;
            b2 = (b5 = (b3 = (equals = (b4 = s.equals(m.z[6])))));
        }
        if (!i) {
            if (b) {
                this.q = com.easypano.tourweaver.a.e.a(s2, 0.0);
                if (!i) {
                    return;
                }
            }
            b3 = (b2 = (equals = (b4 = s.equals(m.z[3]))));
        }
        if (!i) {
            if (b2) {
                this.r = com.easypano.tourweaver.a.e.a(s2, 0.0);
                if (!i) {
                    return;
                }
            }
            equals = (b3 = (b4 = s.equals(m.z[2])));
        }
        if (!i) {
            if (b3) {
                this.s = com.easypano.tourweaver.a.e.a(s2, 0.0);
                if (!i) {
                    return;
                }
            }
            b4 = (equals = s.equals(m.z[1]));
        }
        if (!i) {
            if (equals) {
                super.o = com.easypano.tourweaver.a.e.a(s2, 0);
                super.o *= 1000L;
                if (!i) {
                    return;
                }
            }
            b4 = s.equals(m.z[4]);
        }
        if (b4) {
            this.v = s2;
        }
    }
    
    public void a(final Image image, final String s) {
    }
    
    public void a(final e e) {
    }
    
    public e a(final String s) {
        return null;
    }
    
    public Enumeration d() {
        return null;
    }
    
    public void b(final e e) {
    }
    
    public void c() {
        final j w = this.w;
        if (!com.easypano.tourweaver.f.r.i) {
            if (w == null) {
                return;
            }
            final j w2 = this.w;
        }
        w.c();
    }
    
    public void e() {
        final j w = this.w;
        if (!com.easypano.tourweaver.f.r.i) {
            if (w == null) {
                return;
            }
            final j w2 = this.w;
        }
        w.e();
    }
    
    public boolean i() {
        final j w = this.w;
        if (!com.easypano.tourweaver.f.r.i) {
            if (w == null) {
                System.out.println(m.z[0] + this.m());
                return false;
            }
            final j w2 = this.w;
        }
        return w.i();
    }
    
    public long g() {
        final j w = this.w;
        if (!com.easypano.tourweaver.f.r.i) {
            if (w == null) {
                return 0L;
            }
            final j w2 = this.w;
        }
        return w.g();
    }
    
    public long h() {
        final j w = this.w;
        if (!com.easypano.tourweaver.f.r.i) {
            if (w == null) {
                return 0L;
            }
            final j w2 = this.w;
        }
        return w.h();
    }
    
    static {
        final String[] z = new String[7];
        final int n = 0;
        final char[] charArray = "\"|\u0012\u000b$2f,\u0000e\"ab\f -}#\n k".toCharArray();
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
                            c2 = 'K';
                            break;
                        }
                        case 1: {
                            c2 = '\u000f';
                            break;
                        }
                        case 2: {
                            c2 = 'B';
                            break;
                        }
                        case 3: {
                            c2 = 'g';
                            break;
                        }
                        default: {
                            c2 = 'E';
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
        final char[] charArray2 = "?f/\u0002".toCharArray();
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
                            c4 = 'K';
                            break;
                        }
                        case 1: {
                            c4 = '\u000f';
                            break;
                        }
                        case 2: {
                            c4 = 'B';
                            break;
                        }
                        case 3: {
                            c4 = 'g';
                            break;
                        }
                        default: {
                            c4 = 'E';
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
        final char[] charArray3 = "-`4".toCharArray();
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
                            c6 = 'K';
                            break;
                        }
                        case 1: {
                            c6 = '\u000f';
                            break;
                        }
                        case 2: {
                            c6 = 'B';
                            break;
                        }
                        case 3: {
                            c6 = 'g';
                            break;
                        }
                        default: {
                            c6 = 'E';
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
        final char[] charArray4 = "?f.\u0013".toCharArray();
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
                            c8 = 'K';
                            break;
                        }
                        case 1: {
                            c8 = '\u000f';
                            break;
                        }
                        case 2: {
                            c8 = 'B';
                            break;
                        }
                        case 3: {
                            c8 = 'g';
                            break;
                        }
                        default: {
                            c8 = 'E';
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
        final char[] charArray5 = "/f0\u0002&?f-\t".toCharArray();
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
                            c10 = 'K';
                            break;
                        }
                        case 1: {
                            c10 = '\u000f';
                            break;
                        }
                        case 2: {
                            c10 = 'B';
                            break;
                        }
                        case 3: {
                            c10 = 'g';
                            break;
                        }
                        default: {
                            c10 = 'E';
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
        z[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "8l'\t ".toCharArray();
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
                            c12 = 'K';
                            break;
                        }
                        case 1: {
                            c12 = '\u000f';
                            break;
                        }
                        case 2: {
                            c12 = 'B';
                            break;
                        }
                        case 3: {
                            c12 = 'g';
                            break;
                        }
                        default: {
                            c12 = 'E';
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
        z[n21] = new String(charArray6).intern();
        final int n25 = 6;
        final char[] charArray7 = ";n,".toCharArray();
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
                            c14 = 'K';
                            break;
                        }
                        case 1: {
                            c14 = '\u000f';
                            break;
                        }
                        case 2: {
                            c14 = 'B';
                            break;
                        }
                        case 3: {
                            c14 = 'g';
                            break;
                        }
                        default: {
                            c14 = 'E';
                            break;
                        }
                    }
                    charArray7[length7] = (char)(c13 ^ c14);
                    ++n28;
                } while (n26 == 0);
            }
            if (n26 <= n28) {
                z[n25] = new String(charArray7).intern();
                m.z = z;
                return;
            }
            continue;
        }
    }
}
