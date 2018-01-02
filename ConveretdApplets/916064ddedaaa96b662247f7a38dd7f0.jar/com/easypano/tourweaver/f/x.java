// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.awt.image.ImageObserver;
import java.util.Enumeration;
import java.util.Vector;
import java.awt.Image;
import com.easypano.tourweaver.d.e;

public class x extends r implements e
{
    Image j;
    Image k;
    String l;
    String m;
    String n;
    int o;
    int p;
    f q;
    com.easypano.tourweaver.f.e r;
    boolean s;
    Vector t;
    private static String[] z;
    
    public x() {
        this.j = null;
        this.k = null;
        this.l = "";
        this.m = "";
        this.n = "";
        this.o = 0;
        this.p = 0;
        this.q = new f();
        this.s = false;
    }
    
    public void c() {
        final boolean i = com.easypano.tourweaver.f.r.i;
        super.c();
        this.j = null;
        this.k = null;
        x x = this;
        if (!i) {
            if (this.q != null) {
                this.q.g();
            }
            x = this;
        }
        final Vector t = x.t;
        if (!i) {
            if (t == null) {
                return;
            }
            final Vector t2 = this.t;
        }
        t.removeAllElements();
    }
    
    public int g() {
        return this.o;
    }
    
    public int h() {
        return this.p;
    }
    
    public com.easypano.tourweaver.f.e j() {
        return this.r;
    }
    
    public Vector k() {
        final boolean i = com.easypano.tourweaver.f.r.i;
        final Vector t = this.t;
        if (!i) {
            if (t == null) {
                this.t = new Vector();
                final Enumeration d = this.d();
                while (d.hasMoreElements()) {
                    final String f = d.nextElement().f();
                    if (f != null) {
                        this.t.addElement(f);
                    }
                    if (i) {
                        break;
                    }
                }
            }
            final Vector t2 = this.t;
        }
        return t;
    }
    
    public void b(final String s) {
        if (s == null) {
            return;
        }
        x x = this;
        if (!com.easypano.tourweaver.f.r.i) {
            if (this.r != null && s.equals(this.r.f())) {
                return;
            }
            this.r = this.c(s);
            x = this;
        }
        x.s = true;
    }
    
    public boolean l() {
        return this.s;
    }
    
    public void a(final boolean s) {
        this.s = s;
    }
    
    public com.easypano.tourweaver.f.e c(final String s) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        final Enumeration d = this.d();
        while (d.hasMoreElements()) {
            final com.easypano.tourweaver.f.e e = d.nextElement();
            final String f = e.f();
            if (e.e()) {
                final String s2 = f;
                if ((i || s2 != null) && s2.equals(s)) {
                    return e;
                }
            }
            if (i) {
                break;
            }
        }
        return null;
    }
    
    public void a(final Image image, final String s) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        if (image != null) {
            String s2 = s;
            if (!i) {
                if (s == null) {
                    return;
                }
                s2 = s;
            }
            final boolean equals = s2.equals(this.l);
            if (!i) {
                if (equals) {
                    com.easypano.tourweaver.a.e.a(this.j = image);
                    this.o = image.getWidth(com.easypano.tourweaver.a.e.f);
                    this.p = image.getHeight(com.easypano.tourweaver.a.e.f);
                }
                s.equals(this.m);
            }
            if (equals) {
                this.k = image;
            }
            super.a(image, s);
        }
    }
    
    public void addAttributes(final String s, final String f) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        String s2 = s;
        String s3 = s;
        if (!i) {
            if (s == null) {
                return;
            }
            s2 = f;
            s3 = f;
        }
        if (!i) {
            if (s3 == null) {
                return;
            }
            s2 = s;
        }
        boolean b4;
        boolean equals;
        boolean b3;
        boolean b2;
        final boolean b = b2 = (b3 = (equals = (b4 = s2.equals(x.z[4]))));
        if (!i) {
            if (b) {
                this.setName(f);
                if (!i) {
                    return;
                }
            }
            final boolean b5;
            b2 = (b5 = (b3 = (equals = (b4 = s.equals(x.z[2])))));
        }
        if (!i) {
            if (b) {
                this.l = f;
                if (!i) {
                    return;
                }
            }
            b3 = (b2 = (equals = (b4 = s.equals(x.z[1]))));
        }
        if (!i) {
            if (b2) {
                this.m = f;
                if (!i) {
                    return;
                }
            }
            equals = (b3 = (b4 = s.equals(x.z[3])));
        }
        if (!i) {
            if (b3) {
                this.n = f;
                if (!i) {
                    return;
                }
            }
            b4 = (equals = s.equals(x.z[0]));
        }
        if (!i) {
            if (equals) {
                super.f = f;
                if (!i) {
                    return;
                }
            }
            b4 = s.equals(x.z[5]);
        }
        if (b4) {
            super.g = com.easypano.tourweaver.a.e.a(f, 2);
            super.g *= 1000;
        }
    }
    
    public String m() {
        return this.n;
    }
    
    public Image n() {
        return this.j;
    }
    
    static {
        final String[] z = new String[6];
        final int n = 0;
        final char[] charArray = "vA0\u0000rgs/\u0015t".toCharArray();
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
                            c2 = '\u0013';
                            break;
                        }
                        case 1: {
                            c2 = '\'';
                            break;
                        }
                        case 2: {
                            c2 = 'V';
                            break;
                        }
                        case 3: {
                            c2 = 'e';
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
        z[n] = new String(charArray).intern();
        final int n5 = 1;
        final char[] charArray2 = "wB%\u0006czW\"\f~}".toCharArray();
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
                            c4 = '\u0013';
                            break;
                        }
                        case 1: {
                            c4 = '\'';
                            break;
                        }
                        case 2: {
                            c4 = 'V';
                            break;
                        }
                        case 3: {
                            c4 = 'e';
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
        z[n5] = new String(charArray2).intern();
        final int n9 = 2;
        final char[] charArray3 = "~F&,|t".toCharArray();
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
                            c6 = '\u0013';
                            break;
                        }
                        case 1: {
                            c6 = '\'';
                            break;
                        }
                        case 2: {
                            c6 = 'V';
                            break;
                        }
                        case 3: {
                            c6 = 'e';
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
        z[n9] = new String(charArray3).intern();
        final int n13 = 3;
        final char[] charArray4 = "wB0\u0004d\u007fS\u0002\u0004ctB\"".toCharArray();
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
                            c8 = '\u0013';
                            break;
                        }
                        case 1: {
                            c8 = '\'';
                            break;
                        }
                        case 2: {
                            c8 = 'V';
                            break;
                        }
                        case 3: {
                            c8 = 'e';
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
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "}F;\u0000".toCharArray();
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
                            c10 = '\u0013';
                            break;
                        }
                        case 1: {
                            c10 = '\'';
                            break;
                        }
                        case 2: {
                            c10 = 'V';
                            break;
                        }
                        case 3: {
                            c10 = 'e';
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
        z[n17] = new String(charArray5).intern();
        final int n21 = 5;
        final char[] charArray6 = "vA0\u0000rgs?\bt".toCharArray();
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
                            c12 = '\u0013';
                            break;
                        }
                        case 1: {
                            c12 = '\'';
                            break;
                        }
                        case 2: {
                            c12 = 'V';
                            break;
                        }
                        case 3: {
                            c12 = 'e';
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
            if (n22 <= n24) {
                z[n21] = new String(charArray6).intern();
                x.z = z;
                return;
            }
            continue;
        }
    }
}
