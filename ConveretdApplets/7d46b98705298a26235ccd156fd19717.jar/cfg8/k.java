// 
// Decompiled by Procyon v0.5.30
// 

package cfg8;

import java.util.StringTokenizer;
import java.util.Vector;

class k
{
    final g a;
    protected boolean b;
    private boolean c;
    private l d;
    private Vector e;
    private static String z;
    
    k(final g a) {
        this.a = a;
        this.b = false;
        this.c = false;
        this.d = new l(this, 0);
        this.e = new Vector();
    }
    
    boolean a() {
        return this.c;
    }
    
    void a(final String s, final String c, final int d) {
        final int a = RotationImageFilter.a;
        final StringTokenizer stringTokenizer = new StringTokenizer(s, ",", false);
        while (stringTokenizer.hasMoreTokens()) {
            final l c2 = this.c(t.d(stringTokenizer.nextToken()));
            c2.c = c;
            c2.d = d;
            this.c = true;
            if (a != 0) {
                break;
            }
        }
    }
    
    void a(final int n, final boolean b) {
        final int a = RotationImageFilter.a;
        int n2 = b ? 1 : 0;
        if (a == 0) {
            if (b) {
                this.c(n);
                if (a == 0) {
                    return;
                }
            }
            n2 = 0;
        }
        int i = n2;
        while (i < this.e.size()) {
            final l l = this.e.elementAt(i);
            if (a == 0) {
                if (l.b == n) {
                    this.e.removeElementAt(i);
                    if (a == 0) {
                        break;
                    }
                }
                ++i;
            }
            if (a != 0) {
                break;
            }
        }
    }
    
    String a(final int n) {
        return this.c(n).c;
    }
    
    int b(final int n) {
        return this.c(n).d;
    }
    
    String b() {
        final int a = RotationImageFilter.a;
        final boolean b = this.b;
        final boolean b2 = false;
        if (a == 0) {
            Label_0031: {
                if (b != b2) {
                    k k = this;
                    if (a == 0) {
                        if (g.f(this.a) < 1) {
                            break Label_0031;
                        }
                        k = this;
                    }
                    return k.c(g.f(this.a)).c;
                }
            }
            final k i = this;
            if (a != 0) {
                return i.d.c;
            }
            final boolean c = this.c;
        }
        if (b == b2) {
            return k.z;
        }
        final k i = this;
        return i.d.c;
    }
    
    int c() {
        final int a = RotationImageFilter.a;
        final boolean b = this.b;
        final boolean b2 = false;
        if (a == 0) {
            Label_0031: {
                if (b != b2) {
                    final int f = g.f(this.a);
                    if (a == 0) {
                        if (f < 1) {
                            break Label_0031;
                        }
                        final int d = this.c(g.f(this.a)).d;
                    }
                    return f;
                }
            }
            final k k = this;
            if (a != 0) {
                return k.d.d;
            }
            final boolean c = this.c;
        }
        if (b == b2) {
            return -1;
        }
        final k k = this;
        return k.d.d;
    }
    
    boolean d() {
        final int a = RotationImageFilter.a;
        final boolean b = this.b;
        final boolean b2 = false;
        if (a == 0) {
            Label_0031: {
                if (b != b2) {
                    boolean b3;
                    final int n = (b3 = (g.f(this.a) != 0)) ? 1 : 0;
                    if (a == 0) {
                        if (n < 1) {
                            break Label_0031;
                        }
                        b3 = this.c(g.f(this.a)).e;
                    }
                    return b3;
                }
            }
            final k k = this;
            if (a != 0) {
                return k.d.e;
            }
            final boolean c = this.c;
        }
        if (b == b2) {
            return false;
        }
        final k k = this;
        return k.d.e;
    }
    
    boolean e() {
        final int a = RotationImageFilter.a;
        final boolean b = this.b;
        final boolean b2 = false;
        if (a == 0) {
            Label_0031: {
                if (b != b2) {
                    boolean b3;
                    final int n = (b3 = (g.f(this.a) != 0)) ? 1 : 0;
                    if (a == 0) {
                        if (n < 1) {
                            break Label_0031;
                        }
                        b3 = this.c(g.f(this.a)).f;
                    }
                    return b3;
                }
            }
            final k k = this;
            if (a != 0) {
                return k.d.f;
            }
            final boolean c = this.c;
        }
        if (b == b2) {
            return false;
        }
        final k k = this;
        return k.d.f;
    }
    
    void a(final String c, final int d) {
        final int a = RotationImageFilter.a;
        int b;
        final int n = b = (this.b ? 1 : 0);
        k k = null;
        Label_0112: {
            Label_0111: {
                if (a == 0) {
                    if (n != 0) {
                        k = this;
                        if (a != 0) {
                            break Label_0112;
                        }
                        if (g.f(this.a) >= 1) {
                            break Label_0111;
                        }
                    }
                    this.d.c = c;
                    this.d.d = d;
                    b = 0;
                }
                int i = b;
                while (true) {
                    while (i < this.e.size()) {
                        final l l = this.e.elementAt(i);
                        l.c = c;
                        l.d = d;
                        ++i;
                        if (a == 0) {
                            if (a != 0) {
                                break;
                            }
                            continue;
                        }
                        else {
                            if (a != 0) {
                                break Label_0111;
                            }
                            return;
                        }
                    }
                    this.c = false;
                    continue;
                }
            }
            k = this;
        }
        final l c2 = k.c(g.f(this.a));
        c2.c = c;
        c2.d = d;
        this.c = true;
    }
    
    void a(final boolean e) {
        final int a = RotationImageFilter.a;
        int b;
        final int n = b = (this.b ? 1 : 0);
        k k = null;
        Label_0083: {
            Label_0082: {
                if (a == 0) {
                    if (n != 0) {
                        k = this;
                        if (a != 0) {
                            break Label_0083;
                        }
                        if (g.f(this.a) >= 1) {
                            break Label_0082;
                        }
                    }
                    this.d.e = e;
                    b = 0;
                }
                int i = b;
                while (i < this.e.size()) {
                    ((l)this.e.elementAt(i)).e = e;
                    ++i;
                    if (a != 0) {
                        return;
                    }
                    if (a != 0) {
                        break;
                    }
                }
                if (a == 0) {
                    return;
                }
            }
            k = this;
        }
        k.c(g.f(this.a)).e = e;
    }
    
    void b(final boolean f) {
        final int a = RotationImageFilter.a;
        int b;
        final int n = b = (this.b ? 1 : 0);
        k k = null;
        Label_0083: {
            Label_0082: {
                if (a == 0) {
                    if (n != 0) {
                        k = this;
                        if (a != 0) {
                            break Label_0083;
                        }
                        if (g.f(this.a) >= 1) {
                            break Label_0082;
                        }
                    }
                    this.d.f = f;
                    b = 0;
                }
                int i = b;
                while (i < this.e.size()) {
                    ((l)this.e.elementAt(i)).f = f;
                    ++i;
                    if (a != 0) {
                        return;
                    }
                    if (a != 0) {
                        break;
                    }
                }
                if (a == 0) {
                    return;
                }
            }
            k = this;
        }
        k.c(g.f(this.a)).f = f;
    }
    
    l c(final int n) {
        final int a = RotationImageFilter.a;
        int b = n;
        final int n2 = 1;
        int n3 = 0;
        Label_0034: {
            if (a == 0) {
                if (n < n2) {
                    return this.d;
                }
                n3 = (b = (this.b ? 1 : 0));
                if (a != 0) {
                    break Label_0034;
                }
            }
            if (b != n2) {
                n3 = 0;
                break Label_0034;
            }
            return this.d;
        }
        int i = n3;
        while (true) {
            while (i < this.e.size()) {
                final l j;
                final l l = j = this.e.elementAt(i);
                if (a == 0) {
                    if (a == 0) {
                        if (l.b == n) {
                            final l k = this.e.elementAt(i);
                        }
                        else {
                            ++i;
                            if (a != 0) {
                                break;
                            }
                            continue;
                        }
                    }
                    return l;
                }
                final l m = j;
                m.c = this.d.c;
                m.d = this.d.d;
                m.e = this.d.e;
                m.f = this.d.f;
                this.e.addElement(m);
                return m;
            }
            l j = new l(this, n);
            continue;
        }
    }
    
    static {
        final char[] charArray = "3!?\u001b{\u007fE".toCharArray();
        int length;
        int n2;
        final int n = n2 = (length = charArray.length);
        int n3 = 0;
        while (true) {
            Label_0094: {
                if (n > 1) {
                    break Label_0094;
                }
                length = (n2 = n3);
                do {
                    final char c = charArray[n2];
                    char c2 = '\0';
                    switch (n3 % 5) {
                        case 0: {
                            c2 = '\u001b';
                            break;
                        }
                        case 1: {
                            c2 = 'l';
                            break;
                        }
                        case 2: {
                            c2 = 'V';
                            break;
                        }
                        case 3: {
                            c2 = 'c';
                            break;
                        }
                        default: {
                            c2 = '\u001e';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                k.z = new String(charArray).intern();
                return;
            }
            continue;
        }
    }
}
