// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.y;

public class g extends f
{
    private static neat.system.f b;
    protected Object[] c;
    protected int d;
    private static /* synthetic */ Class e;
    private static String[] z;
    
    public void c() {
        for (int i = 0; i < this.d; ++i) {
            this.c[i] = null;
        }
        this.a();
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof g)) {
            return false;
        }
        final g g = (g)o;
        if (g.d != this.d) {
            return false;
        }
        for (int i = 0; i < this.d; ++i) {
            final Object o2 = this.c[i];
            final Object o3 = g.c[i];
            if (o2 == null) {
                if (o3 != null) {
                    return false;
                }
            }
            else if (!o2.equals(o3)) {
                return false;
            }
        }
        return true;
    }
    
    public Object a(final int n) {
        if (n < 0 || n >= this.d) {
            throw new ArrayIndexOutOfBoundsException(g.z[1] + n + g.z[0]);
        }
        return this.c[n];
    }
    
    public r d() {
        final b a = neat.b.a(this);
        this.a(a);
        return a;
    }
    
    public Object b(final int n) {
        final Object a = this.a(n);
        this.c[n] = null;
        this.b(n);
        return a;
    }
    
    public Object a(final int n, final Object o) {
        if (o == null) {
            throw new NullPointerException(g.z[2]);
        }
        if (n < 0) {
            throw new ArrayIndexOutOfBoundsException(g.z[1] + n + g.z[0]);
        }
        if (n >= this.d) {
            this.d(n + 1 - this.d);
            this.d = n + 1;
        }
        final Object a = this.a(n);
        this.c[n] = o;
        return a;
    }
    
    public int a(final Object o) {
        if (o == null) {
            throw new NullPointerException(g.z[2]);
        }
        int n;
        for (n = 0; n < this.d && this.c[n] != null; ++n) {}
        if (n >= this.d) {
            this.d(n + 1 - this.d);
            this.d = n + 1;
        }
        this.c[n] = o;
        return n;
    }
    
    public int e() {
        int n = 0;
        for (int i = 0; i < this.d; ++i) {
            if (this.c[i] != null) {
                ++n;
            }
        }
        return n;
    }
    
    public void c(final int d) {
        if (d < 0) {
            throw new ArrayIndexOutOfBoundsException(g.z[1] + d + g.z[0]);
        }
        if (d == this.d) {
            return;
        }
        if (d > this.d) {
            this.d(d - this.d);
        }
        else {
            for (int i = d; i < this.d; ++i) {
                this.c[i] = null;
            }
        }
        this.d = d;
    }
    
    public int f() {
        return this.d;
    }
    
    public void i() {
        final r d = this.d();
        while (d.a()) {
            final Object b = d.b();
            if (b instanceof y) {
                ((y)b).f();
            }
        }
        d.f();
        this.c();
    }
    
    private void d(final int n) {
        if (this.d + n <= this.c.length) {
            return;
        }
        final Object[] c = g.b.c(this.d + n + 5);
        System.arraycopy(this.c, 0, c, 0, this.d);
        g.b.a(this.c);
        this.c = c;
    }
    
    public static g j() {
        return (g)g.b.a();
    }
    
    public void f() {
        g.b.a(this);
    }
    
    public void g() {
        super.g();
        this.d = 0;
    }
    
    public void h() {
        super.h();
        this.c();
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public g() {
        this.c = g.b.c(5);
        this.d = 0;
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "JF".toCharArray();
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
                            c2 = 'j';
                            break;
                        }
                        case 1: {
                            c2 = 'g';
                            break;
                        }
                        case 2: {
                            c2 = 'j';
                            break;
                        }
                        case 3: {
                            c2 = '\u0010';
                            break;
                        }
                        default: {
                            c2 = '\r';
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
        final char[] charArray2 = "9\u0017\u000fsd\f\u000e\u000ft-\u0003\t\u000euuJ\u000e\u00190b\u001f\u0013J\u007fkJ\b\bzh\t\u0013J|d\u0019\u0013P".toCharArray();
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
                            c4 = 'j';
                            break;
                        }
                        case 1: {
                            c4 = 'g';
                            break;
                        }
                        case 2: {
                            c4 = 'j';
                            break;
                        }
                        case 3: {
                            c4 = '\u0010';
                            break;
                        }
                        default: {
                            c4 = '\r';
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
        final char[] charArray3 = ")\u0006\u00047yJ\u0014\u000fd-\u000bG\u0004ea\u0006G\u0005rg\u000f\u0004\u001e0y\u0005G&y~\u001eGK".toCharArray();
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
                            c6 = 'j';
                            break;
                        }
                        case 1: {
                            c6 = 'g';
                            break;
                        }
                        case 2: {
                            c6 = 'j';
                            break;
                        }
                        case 3: {
                            c6 = '\u0010';
                            break;
                        }
                        default: {
                            c6 = '\r';
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
        final char[] charArray4 = "\u0004\u0002\u000bd#\r".toCharArray();
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
                            c8 = 'j';
                            break;
                        }
                        case 1: {
                            c8 = 'g';
                            break;
                        }
                        case 2: {
                            c8 = 'j';
                            break;
                        }
                        case 3: {
                            c8 = '\u0010';
                            break;
                        }
                        default: {
                            c8 = '\r';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 <= n16) {
                z[n13] = new String(charArray4).intern();
                g.z = z;
                g.b = new neat.system.f((g.e != null) ? g.e : (g.e = a(g.z[3])));
                return;
            }
            continue;
        }
    }
}
