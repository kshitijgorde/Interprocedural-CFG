// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.util.Vector;

class x extends w
{
    final u f;
    private Vector g;
    private Vector h;
    private static String[] z;
    
    x(final u f) {
        super(f);
        this.f = f;
        this.g = new Vector();
        this.h = new Vector();
    }
    
    String o(final String s) {
        return this.d(s, "");
    }
    
    int p(final String s) {
        return this.d(s, 0);
    }
    
    double q(final String s) {
        return this.c(s, 0.0);
    }
    
    boolean r(final String s) {
        return this.d(s, false);
    }
    
    String d(final String s, final String s2) {
        final w u = this.u(s);
        if (RotationImageFilter.a == 0 && u == null) {
            return s2;
        }
        return u.b();
    }
    
    int d(final String s, final int n) {
        final w u = this.u(s);
        if (RotationImageFilter.a == 0 && u == null) {
            return n;
        }
        return u.c();
    }
    
    double c(final String s, final double n) {
        final w u = this.u(s);
        if (RotationImageFilter.a == 0 && u == null) {
            return n;
        }
        return u.d();
    }
    
    boolean d(final String s, final boolean b) {
        final w u = this.u(s);
        if (RotationImageFilter.a == 0 && u == null) {
            return b;
        }
        return u.e();
    }
    
    int j() {
        return this.g.size();
    }
    
    int k() {
        return this.h.size();
    }
    
    w c(final int n) {
        return this.g.elementAt(n);
    }
    
    x d(final int n) {
        return this.h.elementAt(n);
    }
    
    int s(final String s) {
        final int a = RotationImageFilter.a;
        final int size = this.g.size();
        int i = 0;
        while (i < size) {
            final w w = this.g.elementAt(i);
            if (a == 0) {
                final int equals;
                final boolean b = (equals = (w.a().equals(s) ? 1 : 0)) != 0;
                if (a != 0) {
                    return equals;
                }
                if (b) {
                    return i;
                }
                ++i;
            }
            if (a != 0) {
                break;
            }
        }
        return -1;
    }
    
    int t(final String s) {
        final int a = RotationImageFilter.a;
        final int size = this.h.size();
        int i = 0;
        while (i < size) {
            final x x = this.h.elementAt(i);
            if (a == 0) {
                final int equals;
                final boolean b = (equals = (x.a().equals(s) ? 1 : 0)) != 0;
                if (a != 0) {
                    return equals;
                }
                if (b) {
                    return i;
                }
                ++i;
            }
            if (a != 0) {
                break;
            }
        }
        return -1;
    }
    
    w u(final String s) {
        final int s2 = this.s(s);
        return (s2 < 0) ? null : ((w)this.g.elementAt(s2));
    }
    
    x v(final String s) {
        final int t = this.t(s);
        return (t < 0) ? null : ((x)this.h.elementAt(t));
    }
    
    x w(final String s) {
        final int t = this.t(s);
        return (x)((t < 0) ? this.z(s) : this.h.elementAt(t));
    }
    
    w e(final String s, final String s2) {
        final int a = RotationImageFilter.a;
        final int size = this.g.size();
        int i = 0;
        while (i < size) {
            final w w = this.g.elementAt(i);
            String b = s2;
            Label_0096: {
                final int length;
                final w w2;
                Label_0090: {
                    if (a == 0) {
                        if (s2 != null) {
                            length = s2.length();
                            if (a != 0) {
                                break Label_0090;
                            }
                            if (length != 0) {
                                final boolean equals;
                                final boolean b2 = equals = w.a().equals(s2);
                                if (a != 0) {
                                    break Label_0090;
                                }
                                if (!b2) {
                                    break Label_0096;
                                }
                            }
                        }
                        w2 = w;
                        if (a != 0) {
                            return w2;
                        }
                        b = w2.b();
                    }
                    b.equals(s);
                }
                if (length != 0) {
                    return w2;
                }
            }
            ++i;
            if (a != 0) {
                break;
            }
        }
        return null;
    }
    
    x a(final String s, final int n, final String s2) {
        final int a = RotationImageFilter.a;
        final int size = this.h.size();
        int i = 0;
        while (i < size) {
            final x x = this.h.elementAt(i);
            String s3 = s2;
            Label_0096: {
                final int length;
                int n3 = 0;
                final x x2;
                Label_0090: {
                    Label_0089: {
                        Label_0078: {
                            if (a == 0) {
                                if (s2 == null) {
                                    break Label_0078;
                                }
                                s3 = s2;
                            }
                            length = s3.length();
                            final int n2 = n3 = 0;
                            if (a != 0) {
                                break Label_0090;
                            }
                            if (length != n2) {
                                final boolean equals;
                                final boolean b = equals = x.a().equals(s2);
                                if (a != 0) {
                                    break Label_0089;
                                }
                                if (!b) {
                                    break Label_0096;
                                }
                            }
                        }
                        x2 = x;
                        if (a != 0) {
                            return x2;
                        }
                        x2.e(s);
                    }
                    n3 = n;
                }
                if (length == n3) {
                    return x2;
                }
            }
            ++i;
            if (a != 0) {
                break;
            }
        }
        return null;
    }
    
    void l() {
        this.g();
        this.m();
        this.n();
    }
    
    void m() {
        this.g.removeAllElements();
    }
    
    void n() {
        this.h.removeAllElements();
    }
    
    protected boolean x(final String s) {
        final int t;
        final int n = t = this.t(s);
        if (RotationImageFilter.a == 0) {
            if (t < 0) {
                return false;
            }
            this.h.removeElementAt(n);
        }
        return t != 0;
    }
    
    w f(final String s, final String s2) {
        final w y = this.y(s);
        y.b(s2);
        return y;
    }
    
    void b(final w w) {
        final w w2 = new w(this.f);
        w2.a(w);
        this.g.addElement(w2);
    }
    
    w y(final String s) {
        final w w = new w(this.f);
        w.a(s);
        this.g.addElement(w);
        return w;
    }
    
    void a(final x x) {
        final int a = RotationImageFilter.a;
        this.g();
        this.m();
        this.a(x.a());
        int n = x.f();
        int i = 0;
        while (true) {
            while (i < n) {
                final v b = x.b(i);
                this.b(b.a(), b.b());
                ++i;
                if (a != 0) {
                    int j = 0;
                    while (true) {
                        while (j < n) {
                            this.b(x.c(j));
                            ++j;
                            if (a != 0) {
                                int k = 0;
                                while (k < n) {
                                    this.z("").a(x.d(k));
                                    ++k;
                                    if (a != 0) {
                                        break;
                                    }
                                }
                                return;
                            }
                            if (a != 0) {
                                break;
                            }
                        }
                        n = x.k();
                        continue;
                    }
                }
                if (a != 0) {
                    break;
                }
            }
            n = x.j();
            continue;
        }
    }
    
    void b(final x x) {
        this.h.addElement(x);
    }
    
    x z(final String s) {
        final x x = new x(this.f);
        x.a(s);
        this.b(x);
        return x;
    }
    
    void c(final x x) {
        this.h.removeElement(x);
    }
    
    w A(final String s) {
        final w u = this.u(s);
        if (RotationImageFilter.a == 0 && u == null) {
            this.y(s);
            goto Label_0023;
        }
        return u;
    }
    
    void B(final String s) {
        final int s2 = this.s(s);
        if (s2 >= 0) {
            this.g.removeElementAt(s2);
        }
    }
    
    void g(final String s, final String s2) {
        this.A(s).b(s2);
    }
    
    void e(final String s, final int n) {
        this.A(s).a(n);
    }
    
    void d(final String s, final double n) {
        this.A(s).a(n);
    }
    
    void e(final String s, final boolean b) {
        this.A(s).a(b);
    }
    
    private int a(final Vector vector, final int n) {
        final int a = RotationImageFilter.a;
        final int l = this.l(vector.elementAt(n));
        if (a == 0 && l >= 0) {
            final String concat = String.valueOf(String.valueOf(x.z[1]).concat(String.valueOf(this.a()))).concat(String.valueOf(">"));
            int n2 = -1;
            int n3 = 0;
            int i = n + 1;
            while (true) {
                while (i < vector.size()) {
                    final String s = vector.elementAt(i);
                    int n7;
                    final int n6;
                    final int n5;
                    final int n4 = n5 = (n6 = (n7 = n3));
                    int n11;
                    final int n10;
                    final int n9;
                    final int n8 = n9 = (n10 = (n11 = 0));
                    if (a != 0) {
                        if (a == 0) {
                            if (n6 < n10) {
                                return -1;
                            }
                            n7 = n;
                            n11 = 1;
                        }
                        int j = n7 + n11;
                        while (j < n2) {
                            final String s2 = vector.elementAt(j);
                            final int index;
                            final int n12 = index = s2.indexOf(x.z[1]);
                            if (a != 0) {
                                return index;
                            }
                            final int n13 = 0;
                            Label_0385: {
                                Label_0382: {
                                    Label_0344: {
                                        if (a == 0) {
                                            if (n12 >= n13) {
                                                break Label_0344;
                                            }
                                            s2.indexOf(x.z[2]);
                                        }
                                        if (n12 < n13) {
                                            final x x = new x(this.f);
                                            final int a2 = x.a(vector, j);
                                            Label_0339: {
                                                Label_0334: {
                                                    if (a == 0) {
                                                        if (a2 <= 0) {
                                                            break Label_0334;
                                                        }
                                                        this.h.addElement(x);
                                                    }
                                                    j = a2;
                                                    if (a == 0) {
                                                        break Label_0339;
                                                    }
                                                }
                                                if (a == 0) {
                                                    break;
                                                }
                                            }
                                            if (a == 0) {
                                                break Label_0382;
                                            }
                                        }
                                    }
                                    final w w = new w(this.f);
                                    if (a != 0) {
                                        break Label_0385;
                                    }
                                    if (cfg8.w.a(w, s2) > 0) {
                                        this.g.addElement(w);
                                    }
                                }
                                ++j;
                            }
                            if (a != 0) {
                                break;
                            }
                        }
                        return n2;
                    }
                    final int index2;
                    final int n14;
                    Label_0148: {
                        if (a == 0) {
                            if (n4 == n8) {
                                index2 = s.indexOf(concat);
                                n14 = 0;
                                if (a != 0) {
                                    break Label_0148;
                                }
                                if (index2 >= n14) {
                                    n2 = i;
                                    if (a == 0) {
                                        break;
                                    }
                                }
                            }
                            s.indexOf(x.z[1]);
                        }
                    }
                    Label_0200: {
                        Label_0194: {
                            if (a == 0) {
                                if (n4 < n8) {
                                    final int index3 = s.indexOf(x.z[2]);
                                    final int n15 = 0;
                                    if (a != 0) {
                                        break Label_0194;
                                    }
                                    if (index3 < n15) {
                                        ++n3;
                                        if (a == 0) {
                                            break Label_0200;
                                        }
                                    }
                                }
                                s.startsWith(x.z[1]);
                            }
                        }
                        if (index2 == n14) {
                            --n3;
                        }
                    }
                    ++i;
                    if (a != 0) {
                        break;
                    }
                }
                int n6;
                int n7 = n6 = n2;
                int n10;
                int n11 = n10 = 0;
                continue;
            }
        }
        return l;
    }
    
    protected String i() {
        final int a = RotationImageFilter.a;
        String s = String.valueOf(this.h()).concat(String.valueOf("\n"));
        final int j = this.j();
        int i = 0;
        while (true) {
            while (i < j) {
                final x x = this;
                if (a != 0) {
                    final int k = x.k();
                    int l = 0;
                    while (l < k) {
                        final x d = this.d(l);
                        if (a != 0) {
                            return s;
                        }
                        if (d != null || a != 0) {
                            s = String.valueOf(s).concat(String.valueOf(d.i()));
                        }
                        ++l;
                        if (a != 0) {
                            break;
                        }
                    }
                    s = String.valueOf(s).concat(String.valueOf(String.valueOf(String.valueOf(cfg8.x.z[1]).concat(String.valueOf(this.a()))).concat(String.valueOf(cfg8.x.z[0]))));
                    return s;
                }
                final w c = this.c(i);
                if (c != null || a != 0) {
                    s = String.valueOf(s).concat(String.valueOf(String.valueOf(c.i()).concat(String.valueOf("\n"))));
                }
                ++i;
                if (a != 0) {
                    break;
                }
            }
            final x x = this;
            continue;
        }
    }
    
    static int a(final x x, final Vector vector, final int n) {
        return x.a(vector, n);
    }
    
    static {
        final String[] z = new String[3];
        final int n = 0;
        final char[] charArray = "\u00056".toCharArray();
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
                            c2 = ';';
                            break;
                        }
                        case 1: {
                            c2 = '<';
                            break;
                        }
                        case 2: {
                            c2 = '\u001c';
                            break;
                        }
                        case 3: {
                            c2 = 'a';
                            break;
                        }
                        default: {
                            c2 = 'C';
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
        final char[] charArray2 = "\u0007\u0013".toCharArray();
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
                            c4 = ';';
                            break;
                        }
                        case 1: {
                            c4 = '<';
                            break;
                        }
                        case 2: {
                            c4 = '\u001c';
                            break;
                        }
                        case 3: {
                            c4 = 'a';
                            break;
                        }
                        default: {
                            c4 = 'C';
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
        final char[] charArray3 = "\u0014\u0002".toCharArray();
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
                            c6 = ';';
                            break;
                        }
                        case 1: {
                            c6 = '<';
                            break;
                        }
                        case 2: {
                            c6 = '\u001c';
                            break;
                        }
                        case 3: {
                            c6 = 'a';
                            break;
                        }
                        default: {
                            c6 = 'C';
                            break;
                        }
                    }
                    charArray3[length3] = (char)(c5 ^ c6);
                    ++n12;
                } while (n10 == 0);
            }
            if (n10 <= n12) {
                z[n9] = new String(charArray3).intern();
                x.z = z;
                return;
            }
            continue;
        }
    }
}
