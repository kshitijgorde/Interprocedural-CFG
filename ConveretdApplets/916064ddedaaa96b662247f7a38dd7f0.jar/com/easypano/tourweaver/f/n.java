// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver.f;

import java.awt.Image;
import java.util.Enumeration;
import com.easypano.tourweaver.TWViewerApplet;
import com.easypano.tourweaver.a.c;
import java.util.Vector;
import com.easypano.tourweaver.d.e;

public class n extends i implements e
{
    boolean q;
    boolean r;
    a s;
    Vector t;
    private String u;
    long v;
    private static String[] z;
    
    public n() {
        this.q = false;
        this.r = false;
        this.t = new Vector();
        this.u = null;
        this.v = 0L;
    }
    
    public boolean d(final String s) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        Object o;
        final Vector vector = (Vector)(o = this.t);
        if (!i) {
            if (vector.isEmpty()) {
                return false;
            }
            o = this.t.elementAt(0);
        }
        final boolean equals = s.equals(((y)((m)o).m()).u());
        return (!i && equals) || equals;
    }
    
    public boolean q() {
        return this.q;
    }
    
    public void a(final boolean q) {
        this.q = q;
    }
    
    public void r() {
        this.q = this.r;
    }
    
    public void f() {
        final boolean i = com.easypano.tourweaver.f.r.i;
        a a = this;
        a s = this;
        n n = this;
        if (!i) {
            if (super.k) {
                return;
            }
            s = (n = (n)(a = this.s));
        }
        if (!i) {
            if (n == null) {
                return;
            }
            a = (s = this.s);
        }
        if (!i) {
            if (!s.i()) {
                final a j;
                a a2 = j = this.s.j();
                if (!i) {
                    if (j == null) {
                        this.c(this.getName());
                        a a3 = this;
                        if (!i) {
                            if (!this.q) {
                                this.e();
                                return;
                            }
                            a3 = this.t.elementAt(0);
                        }
                        a2 = a3;
                    }
                    this.e(a2);
                }
                j.b();
            }
            super.i = this.s.o() + this.s.g();
            a = this.s;
        }
        a.f();
        this.b(super.i / super.h);
    }
    
    private void s() {
        super.e();
        final a s = this.s;
        if (!com.easypano.tourweaver.f.r.i) {
            if (s == null) {
                return;
            }
            final a s2 = this.s;
        }
        s.e();
        this.s = null;
    }
    
    private void e(final a s) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        a a = s;
        n n = this;
        if (!i) {
            if (s == this.s) {
                return;
            }
            a = s;
            n = this;
        }
        a.a(n.m);
        s.a();
        n n2 = this;
        if (!i) {
            if (this.s != null) {
                this.s.e();
            }
            n2 = this;
        }
        n2.s = s;
    }
    
    public void a() {
        final boolean i = com.easypano.tourweaver.f.r.i;
        a s = this;
        n n = null;
        Label_0060: {
            if (!i) {
                if (this.t.size() == 0) {
                    com.easypano.tourweaver.a.c.c(this, com.easypano.tourweaver.f.n.z[8]);
                    return;
                }
                n = this;
                if (i) {
                    break Label_0060;
                }
                s = this.s;
            }
            if (s == null) {
                this.e(this.t.elementAt(0));
                if (!i) {
                    return;
                }
            }
            n = this;
        }
        com.easypano.tourweaver.a.c.c(n, com.easypano.tourweaver.f.n.z[7]);
    }
    
    public void b() {
        com.easypano.tourweaver.a.c.a(this, n.z[6] + super.g + n.z[4] + super.k + n.z[5] + super.j);
        super.b();
        n n = this;
        if (!com.easypano.tourweaver.f.r.i) {
            if (this.s != null) {
                this.s.b();
            }
            TWViewerApplet.d.playMovieSound(true);
            n = this;
        }
        com.easypano.tourweaver.a.c.a(n, com.easypano.tourweaver.f.n.z[6] + super.g + com.easypano.tourweaver.f.n.z[4] + super.k + com.easypano.tourweaver.f.n.z[5] + super.j);
    }
    
    public boolean t() {
        return super.k;
    }
    
    public boolean c(final a a) {
        return this.t.contains(a);
    }
    
    public void a(final double n) {
        final a c = this.c(n);
        a a = this;
        if (!com.easypano.tourweaver.f.r.i) {
            if (!super.k) {
                c.b();
            }
            a = c;
        }
        final double n2 = ((long)(n * super.h) - a.o()) / c.h();
        this.e(c);
        c.a(n2);
    }
    
    private a c(final double n) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        a a = null;
        double empty;
        final int n2 = (int)(empty = (this.t.isEmpty() ? 1 : 0));
        if (!i) {
            if (n2 != 0) {
                return null;
            }
            empty = dcmpg(n, 0.0);
        }
        if (empty <= 0) {
            a = this.t.firstElement();
        }
        double n3 = n;
        final double n4 = 1.0;
        if (!i) {
            if (n >= n4) {
                a = this.t.lastElement();
            }
            n3 = n;
            final double n5 = super.h;
        }
        final long n6 = (long)(n3 * n4);
        final Enumeration d = this.d();
        while (d.hasMoreElements()) {
            final m m = d.nextElement();
            long n7 = m.o();
            final a j;
            final a a2 = j = m.j();
            if (i) {
                return j;
            }
            Label_0142: {
                if (!i) {
                    if (a2 == null) {
                        break Label_0142;
                    }
                    m.j();
                }
                n7 = a2.o();
            }
            final long n8 = lcmp(n6, n7);
            Label_0171: {
                if (!i) {
                    if (n8 >= 0) {
                        break Label_0171;
                    }
                    final long n9 = lcmp(n6, n7);
                }
                if (n8 >= 0) {
                    a = m;
                    if (!i) {
                        break;
                    }
                }
            }
            if (i) {
                break;
            }
        }
        return a;
    }
    
    public void a(final int n) {
        super.h = 1000 * n;
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
        boolean b2;
        boolean equals;
        final boolean b = equals = (b2 = s3.equals(n.z[0]));
        if (!i) {
            if (b) {
                super.h = com.easypano.tourweaver.a.e.a(s2, 0L);
                super.h *= 1000L;
                if (!i) {
                    return;
                }
            }
            final boolean b3;
            equals = (b3 = (b2 = s.equals(n.z[3])));
        }
        if (!i) {
            if (b) {
                this.q = com.easypano.tourweaver.a.e.e(s2);
                if (!i) {
                    return;
                }
            }
            b2 = (equals = s.equals(n.z[2]));
        }
        if (!i) {
            if (equals) {
                this.setName(s2);
                if (!i) {
                    return;
                }
            }
            b2 = s.equals(n.z[1]);
        }
        if (b2) {
            this.u = s2;
        }
    }
    
    public String u() {
        return this.u;
    }
    
    public void a(final e e) {
        final boolean i = com.easypano.tourweaver.f.r.i;
        e e2 = e;
        e e3 = e;
        if (!i) {
            if (e == null) {
                return;
            }
            e2 = e;
            e3 = e;
        }
        if (!i) {
            if (!(e3 instanceof m)) {
                return;
            }
            e2 = e;
        }
        final m m = (m)e2;
        n n = this;
        Label_0089: {
            if (!i) {
                if (!this.t.isEmpty()) {
                    this.t.lastElement().a(m);
                    if (!i) {
                        break Label_0089;
                    }
                }
                this.v = m.o();
                n = this;
            }
            n.h = super.h - this.v;
        }
        m.b(m.o() - this.v);
        this.t.addElement(e);
    }
    
    public void a(final long n) {
        super.a(n - this.v);
    }
    
    public void a(final Image image, final String s) {
    }
    
    public e a(final String s) {
        return null;
    }
    
    public Enumeration d() {
        return this.t.elements();
    }
    
    public void b(final e e) {
        this.t.removeAllElements();
    }
    
    public void c() {
        super.c();
        TWViewerApplet.d.playMovieSound(false);
        n n = this;
        if (!com.easypano.tourweaver.f.r.i) {
            if (this.s != null) {
                this.s.c();
            }
            n = this;
        }
        n.b(super.g);
    }
    
    public void e() {
        this.a(0.0);
        super.e();
        TWViewerApplet.d.playMovieSound(false);
        n n = this;
        if (!com.easypano.tourweaver.f.r.i) {
            if (this.s != null) {
                this.s.e();
                this.s = null;
            }
            n = this;
        }
        n.b(0.0);
    }
    
    public boolean d(final a a) {
        return a instanceof n;
    }
    
    static {
        final String[] z = new String[9];
        final int n = 0;
        final char[] charArray = "o~/R".toCharArray();
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
                            c2 = '\u001b';
                            break;
                        }
                        case 1: {
                            c2 = '\u0017';
                            break;
                        }
                        case 2: {
                            c2 = 'B';
                            break;
                        }
                        case 3: {
                            c2 = '7';
                            break;
                        }
                        default: {
                            c2 = '9';
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
        final char[] charArray2 = "hx7Y]".toCharArray();
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
                            c4 = '\u001b';
                            break;
                        }
                        case 1: {
                            c4 = '\u0017';
                            break;
                        }
                        case 2: {
                            c4 = 'B';
                            break;
                        }
                        case 3: {
                            c4 = '7';
                            break;
                        }
                        default: {
                            c4 = '9';
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
        final char[] charArray3 = "uv/R".toCharArray();
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
                            c6 = '\u001b';
                            break;
                        }
                        case 1: {
                            c6 = '\u0017';
                            break;
                        }
                        case 2: {
                            c6 = 'B';
                            break;
                        }
                        case 3: {
                            c6 = '7';
                            break;
                        }
                        default: {
                            c6 = '9';
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
        final char[] charArray4 = "rd\u000eXVk".toCharArray();
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
                            c8 = '\u001b';
                            break;
                        }
                        case 1: {
                            c8 = '\u0017';
                            break;
                        }
                        case 2: {
                            c8 = 'B';
                            break;
                        }
                        case 3: {
                            c8 = '7';
                            break;
                        }
                        default: {
                            c8 = '9';
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
        final char[] charArray5 = "77+Dizb1R\u0019!7".toCharArray();
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
                            c10 = '\u001b';
                            break;
                        }
                        case 1: {
                            c10 = '\u0017';
                            break;
                        }
                        case 2: {
                            c10 = 'B';
                            break;
                        }
                        case 3: {
                            c10 = '7';
                            break;
                        }
                        default: {
                            c10 = '9';
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
        final char[] charArray6 = "77+Djox2\u0017\u0003;".toCharArray();
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
                            c12 = '\u001b';
                            break;
                        }
                        case 1: {
                            c12 = '\u0017';
                            break;
                        }
                        case 2: {
                            c12 = 'B';
                            break;
                        }
                        case 3: {
                            c12 = '7';
                            break;
                        }
                        default: {
                            c12 = '9';
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
        final char[] charArray7 = "k{#N\u001127x\u0017".toCharArray();
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
                            c14 = '\u001b';
                            break;
                        }
                        case 1: {
                            c14 = '\u0017';
                            break;
                        }
                        case 2: {
                            c14 = 'B';
                            break;
                        }
                        case 3: {
                            c14 = '7';
                            break;
                        }
                        default: {
                            c14 = '9';
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
        z[n25] = new String(charArray7).intern();
        final int n29 = 7;
        final char[] charArray8 = "xb0vWrz#CPtyb^J;y7[U5".toCharArray();
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
                            c16 = '\u001b';
                            break;
                        }
                        case 1: {
                            c16 = '\u0017';
                            break;
                        }
                        case 2: {
                            c16 = 'B';
                            break;
                        }
                        case 3: {
                            c16 = '7';
                            break;
                        }
                        default: {
                            c16 = '9';
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
        z[n29] = new String(charArray8).intern();
        final int n33 = 8;
        final char[] charArray9 = "O\u007f'\u0017Jrm'\u0017V}7!_Pws1\u0017Ph7r\u0019".toCharArray();
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
                            c18 = '\u001b';
                            break;
                        }
                        case 1: {
                            c18 = '\u0017';
                            break;
                        }
                        case 2: {
                            c18 = 'B';
                            break;
                        }
                        case 3: {
                            c18 = '7';
                            break;
                        }
                        default: {
                            c18 = '9';
                            break;
                        }
                    }
                    charArray9[length9] = (char)(c17 ^ c18);
                    ++n36;
                } while (n34 == 0);
            }
            if (n34 <= n36) {
                z[n33] = new String(charArray9).intern();
                com.easypano.tourweaver.f.n.z = z;
                return;
            }
            continue;
        }
    }
}
