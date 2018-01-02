// 
// Decompiled by Procyon v0.5.30
// 

package com.easypano.tourweaver;

import com.easypano.tourweaver.f.ab;
import com.easypano.tourweaver.f.k;
import com.easypano.tourweaver.f.u;
import com.easypano.tourweaver.f.w;
import com.easypano.tourweaver.f.t;
import com.easypano.tourweaver.f.v;
import com.easypano.tourweaver.f.s;
import com.easypano.tourweaver.f.e;
import com.easypano.tourweaver.f.h;
import com.easypano.tourweaver.f.l;
import com.easypano.tourweaver.f.j;
import com.easypano.tourweaver.f.m;
import com.easypano.tourweaver.f.n;
import java.util.Enumeration;
import com.easypano.tourweaver.f.y;
import com.easypano.tourweaver.f.x;
import java.util.Vector;
import com.easypano.tourweaver.f.b;
import java.util.Hashtable;

public class o
{
    Hashtable a;
    Hashtable b;
    Hashtable c;
    b d;
    Vector e;
    Hashtable f;
    com.easypano.tourweaver.f.o g;
    private static String[] z;
    
    public o() {
        this.a = new Hashtable();
        this.b = new Hashtable();
        this.c = new Hashtable();
        this.d = null;
        this.e = new Vector();
        this.f = new Hashtable();
    }
    
    public void a() {
        final boolean v = com.easypano.tourweaver.g.v;
        Enumeration<y> enumeration = this.b.elements();
        while (true) {
            while (enumeration.hasMoreElements()) {
                ((x)enumeration.nextElement()).c();
                if (v) {
                    while (enumeration.hasMoreElements()) {
                        enumeration.nextElement().c();
                        if (v) {
                            break;
                        }
                    }
                    return;
                }
                if (v) {
                    break;
                }
            }
            enumeration = this.a.elements();
            continue;
        }
    }
    
    public void a(final b d) {
        this.d = d;
    }
    
    public int b() {
        return this.a.size();
    }
    
    public int c() {
        return this.b.size();
    }
    
    public String a(final String s) {
        int index = this.e.indexOf(s);
        final int n = --index;
        if (!com.easypano.tourweaver.g.v) {
            if (n >= 0) {
                return (String)this.e.elementAt(index);
            }
            final int n2 = this.e.size() - 1;
        }
        index = n;
        return (String)this.e.elementAt(index);
    }
    
    public String b(final String s) {
        int index = this.e.indexOf(s);
        final int n = ++index;
        if (com.easypano.tourweaver.g.v || n >= this.e.size()) {
            index = n;
        }
        return (String)this.e.elementAt(index);
    }
    
    public x d() {
        final Enumeration elements = this.b.elements();
        Object nextElement;
        final Enumeration<x> enumeration = (Enumeration<x>)(nextElement = elements);
        if (!com.easypano.tourweaver.g.v) {
            if (!enumeration.hasMoreElements()) {
                return null;
            }
            nextElement = elements.nextElement();
        }
        return (x)nextElement;
    }
    
    public com.easypano.tourweaver.f.o e() {
        final com.easypano.tourweaver.f.o g = this.g;
        if (!com.easypano.tourweaver.g.v) {
            if (g == null) {
                (this.g = new com.easypano.tourweaver.f.o()).a(this.d);
            }
            final com.easypano.tourweaver.f.o g2 = this.g;
        }
        return g;
    }
    
    public void a(final y y) {
        this.a.put(y.getName(), y);
        this.e.addElement(y.getName());
    }
    
    public void a(final n n) {
        n.a(this.d);
        this.b(n);
        this.c.put(n.getName(), n);
    }
    
    private j a(final m m, final m i) {
        final boolean v = com.easypano.tourweaver.g.v;
        j a = null;
        m j = i;
        Label_0032: {
            if (!v) {
                if (i == null) {
                    break Label_0032;
                }
                j = m;
            }
            h h2;
            final h h = h2 = j.m();
            h h4;
            final h h3 = h4 = i.m();
            if (!v) {
                if (h == h3) {
                    break Label_0032;
                }
                final h k;
                h2 = (k = m.m());
                final h l;
                h4 = (l = i.m());
            }
            if (!v) {
                if (h == h3) {
                    return a;
                }
                h2 = m.m();
                h4 = i.m();
            }
            a = a(h2, h4, this.d, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0);
            return a;
        }
        a = new l();
        ((l)a).a(this.e());
        return a;
    }
    
    public void b(final n n) {
        final boolean v = com.easypano.tourweaver.g.v;
        final Enumeration d = n.d();
        while (d.hasMoreElements()) {
            final m m = d.nextElement();
            m.b(this.f(m.t()));
            final m i = (m)m.j();
            if (i != null) {
                i.b(this.f(i.t()));
            }
            m.a(this.a(m, i));
            m.a(this.d);
            if (v) {
                break;
            }
        }
    }
    
    public n c(final String s) {
        Object value = s;
        if (!com.easypano.tourweaver.g.v) {
            if (s == null) {
                return null;
            }
            value = this.c.get(s);
        }
        return (n)value;
    }
    
    public void a(final x x) {
        this.b.put(x.getName(), x);
    }
    
    public Enumeration f() {
        return this.a.elements();
    }
    
    public Enumeration g() {
        return this.b.elements();
    }
    
    public Enumeration h() {
        return this.c.elements();
    }
    
    public x d(String s) {
        final boolean v = com.easypano.tourweaver.g.v;
        Object value;
        final String s2 = (String)(value = s);
        if (!v) {
            if (s2 == null) {
                s = o.z[1];
            }
            value = this.b.get(s);
        }
        final x x = (x)value;
        x x2 = null;
        final x x3 = x;
        if (v || x3 != null) {
            x2 = x3;
        }
        return x2;
    }
    
    public x e(final String s) {
        final boolean v = com.easypano.tourweaver.g.v;
        final Enumeration g = this.g();
        while (g.hasMoreElements()) {
            Object nextElement = g.nextElement();
        Label_0025:
            while (true) {
                final x x = (x)nextElement;
                final Enumeration d = x.d();
                while (d.hasMoreElements()) {
                    final String f = d.nextElement().f();
                    String s3;
                    final String s2 = (String)(nextElement = (s3 = f));
                    if (v) {
                        continue Label_0025;
                    }
                    Label_0092: {
                        if (!v) {
                            if (s2 == null) {
                                break Label_0092;
                            }
                            s3 = f;
                        }
                        if (s3.equals(s)) {
                            return x;
                        }
                    }
                    if (v) {
                        break;
                    }
                }
                break;
            }
            if (v) {
                break;
            }
        }
        return null;
    }
    
    public y f(final String s) {
        Object value = s;
        if (!com.easypano.tourweaver.g.v) {
            if (s == null) {
                return null;
            }
            value = this.a.get(s);
        }
        return (y)value;
    }
    
    public n g(final String s) {
        final boolean v = com.easypano.tourweaver.g.v;
        this.h(s);
        final Enumeration<n> elements = (Enumeration<n>)this.c.elements();
        while (elements.hasMoreElements()) {
            final n n = elements.nextElement();
            if (v || n.d(s)) {
                return n;
            }
            if (v) {
                break;
            }
        }
        return null;
    }
    
    public y h(final String s) {
        final boolean v = com.easypano.tourweaver.g.v;
        if (s == null) {
            return null;
        }
        final Enumeration f = this.f();
        while (f.hasMoreElements()) {
            final y y = f.nextElement();
            final String u = y.u();
            if ((v || u != null) && u.equals(s)) {
                return y;
            }
            if (v) {
                break;
            }
        }
        return null;
    }
    
    public void a(final int n, final int n2, final int n3, final int n4, final String s) {
        final y h;
        final y y = h = this.h(s);
        if (!com.easypano.tourweaver.g.v) {
            if (h == null) {
                return;
            }
            y.a(n, n2);
        }
        h.b(n3, n4);
    }
    
    public void a(final byte[] array, final int n, final int n2, final int n3, final int n4, final String s) {
        final y h = this.h(s);
        if (!com.easypano.tourweaver.g.v && h == null) {
            return;
        }
        h.a(array, n, n2, n3, n4);
    }
    
    public void a(final byte[] array, final String s, final int n, final int n2) {
        final y h = this.h(s);
        if (!com.easypano.tourweaver.g.v && h == null) {
            return;
        }
        h.a(array, n, n2);
    }
    
    public void a(final int n, final int n2, final int n3, final String s) {
        final y h = this.h(s);
        if (!com.easypano.tourweaver.g.v && h == null) {
            return;
        }
        h.a(n, n2, n3);
    }
    
    public static s i(final String s) {
        final boolean v = g.v;
        if (s == null) {
            return null;
        }
        s s2 = null;
        boolean b2;
        boolean equals;
        final boolean b = equals = (b2 = s.equals(o.z[2]));
        if (!v) {
            if (b) {
                s2 = new v();
                return s2;
            }
            final boolean b3;
            equals = (b3 = (b2 = s.equals(o.z[3])));
        }
        if (!v) {
            if (b) {
                s2 = new t();
                return s2;
            }
            b2 = (equals = s.equals(o.z[0]));
        }
        if (!v) {
            if (equals) {
                s2 = new w();
                return s2;
            }
            b2 = s.equals(o.z[4]);
        }
        if (b2) {
            s2 = new u();
        }
        return s2;
    }
    
    public static k a(final h h, final h h2, final b b, final double n, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9) {
        return a(h, h2, h2.a(), h2.b(), b, n, n2, n3, n4, n5, n6, n7, n8, n9);
    }
    
    public static k a(final h h, final h h2, final String s, final long n, final b b, final double n2, final double n3, final double n4, final double n5, final double n6, final double n7, final double n8, final double n9, final double n10) {
        if (h == h2) {
            return null;
        }
        final s i = i(s);
        if (i == null) {
            return null;
        }
        if (s.equals(o.z[0])) {
            ((w)i).a(n2, n3, n4, n5, n6, n7, n8, n9, n10);
        }
        final k k = new k();
        k.b(i);
        i.c(h);
        i.d(h2);
        k.a(n);
        k.a(b);
        k.b(n8, n9, n10);
        return k;
    }
    
    public void a(final ab ab, final String s) {
        this.f.put(s, ab);
    }
    
    public Enumeration i() {
        return this.f.elements();
    }
    
    public void j(final String s) {
        this.f.remove(s);
    }
    
    public ab k(final String s) {
        return this.f.get(s);
    }
    
    static {
        final String[] z = new String[5];
        final int n = 0;
        final char[] charArray = "w\f@\bFH\u001fC\u0016uH".toCharArray();
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
                            c2 = ' ';
                            break;
                        }
                        case 1: {
                            c2 = 'm';
                            break;
                        }
                        case 2: {
                            c2 = ',';
                            break;
                        }
                        case 3: {
                            c2 = 'c';
                            break;
                        }
                        default: {
                            c2 = '\u0012';
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
        final char[] charArray2 = "A\u0018X\f".toCharArray();
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
                            c4 = ' ';
                            break;
                        }
                        case 1: {
                            c4 = 'm';
                            break;
                        }
                        case 2: {
                            c4 = ',';
                            break;
                        }
                        case 3: {
                            c4 = 'c';
                            break;
                        }
                        default: {
                            c4 = '\u0012';
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
        final char[] charArray3 = "b\u0001E\rvS".toCharArray();
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
                            c6 = ' ';
                            break;
                        }
                        case 1: {
                            c6 = 'm';
                            break;
                        }
                        case 2: {
                            c6 = ',';
                            break;
                        }
                        case 3: {
                            c6 = 'c';
                            break;
                        }
                        default: {
                            c6 = '\u0012';
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
        final char[] charArray4 = "f\fH\u0006[N+M\u0007wo\u0018X".toCharArray();
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
                            c8 = ' ';
                            break;
                        }
                        case 1: {
                            c8 = 'm';
                            break;
                        }
                        case 2: {
                            c8 = ',';
                            break;
                        }
                        case 3: {
                            c8 = 'c';
                            break;
                        }
                        default: {
                            c8 = '\u0012';
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
        final char[] charArray5 = "z\u0002C\u000e[N".toCharArray();
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
                            c10 = ' ';
                            break;
                        }
                        case 1: {
                            c10 = 'm';
                            break;
                        }
                        case 2: {
                            c10 = ',';
                            break;
                        }
                        case 3: {
                            c10 = 'c';
                            break;
                        }
                        default: {
                            c10 = '\u0012';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 <= n20) {
                z[n17] = new String(charArray5).intern();
                o.z = z;
                return;
            }
            continue;
        }
    }
}
