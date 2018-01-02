// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.y;

public class i extends f
{
    private static neat.system.f b;
    protected Object[] c;
    protected int d;
    private static /* synthetic */ Class e;
    private static String[] z;
    
    public void a(final Object o) {
        if (o == null) {
            throw new NullPointerException(i.z[0]);
        }
        this.c(1);
        this.c[this.d++] = o;
        this.a(this.d - 1);
    }
    
    public void a(final int n, final Object o) {
        if (o == null) {
            throw new NullPointerException(i.z[0]);
        }
        this.c(1);
        for (int i = this.d - 1; i >= n; --i) {
            this.c[i + 1] = this.c[i];
        }
        this.c[n] = o;
        ++this.d;
        this.a(n);
    }
    
    public void c() {
        for (int i = 0; i < this.d; ++i) {
            this.c[i] = null;
        }
        this.d = 0;
        this.a();
    }
    
    public boolean b(final Object o) {
        for (int i = 0; i < this.d; ++i) {
            if (this.c[i].equals(o)) {
                return true;
            }
        }
        return false;
    }
    
    public boolean equals(final Object o) {
        if (!(o instanceof i)) {
            return false;
        }
        final i i = (i)o;
        if (i.d != this.d) {
            return false;
        }
        for (int j = 0; j < this.d; ++j) {
            if (!this.c[j].equals(i.c[j])) {
                return false;
            }
        }
        return true;
    }
    
    public Object a(final int n) {
        return this.c[n];
    }
    
    public Object d() {
        if (this.d == 0) {
            return null;
        }
        return this.c[this.d - 1];
    }
    
    public int c(final Object o) {
        for (int i = 0; i < this.d; ++i) {
            if (this.c[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }
    
    public boolean e() {
        return this.d == 0;
    }
    
    public r f() {
        final c a = neat.c.a(this);
        this.a(a);
        return a;
    }
    
    public Object b(final int n) {
        if (n < 0 || n >= this.d) {
            throw new ArrayIndexOutOfBoundsException(i.z[2] + n + i.z[1]);
        }
        --this.d;
        final Object o = this.c[n];
        for (int i = n; i < this.d; ++i) {
            this.c[i] = this.c[i + 1];
        }
        this.c[this.d] = null;
        this.b(n);
        return o;
    }
    
    public Object g() {
        if (this.d == 0) {
            return null;
        }
        return this.b(this.d - 1);
    }
    
    public Object h() {
        if (this.d == 0) {
            return null;
        }
        return this.b(this.d - 1);
    }
    
    public boolean d(final Object o) {
        final int c = this.c(o);
        if (c == -1) {
            return false;
        }
        this.b(c);
        return true;
    }
    
    public Object b(final int n, final Object o) {
        if (o == null) {
            throw new NullPointerException(i.z[0]);
        }
        final Object a = this.a(n);
        this.c[n] = o;
        return a;
    }
    
    public int i() {
        return this.d;
    }
    
    public void j() {
        final r f = this.f();
        while (f.a()) {
            final Object b = f.b();
            if (b instanceof y) {
                f.e();
                ((y)b).f();
            }
        }
        f.f();
        this.c();
    }
    
    private void c(final int n) {
        if (this.d + n <= this.c.length) {
            return;
        }
        final Object[] c = i.b.c(this.d + n + 5);
        System.arraycopy(this.c, 0, c, 0, this.d);
        i.b.a(this.c);
        this.c = c;
    }
    
    public static i k() {
        return (i)i.b.a();
    }
    
    public void f() {
        i.b.a(this);
    }
    
    public void g() {
        super.g();
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
    
    public i() {
        this.c = i.b.c(5);
        this.d = 0;
    }
    
    static {
        final String[] z = new String[4];
        final int n = 0;
        final char[] charArray = "9}AJ\u0013Z}K\tG\u001b<A\u0018\u000b\u0016<@\u000f\r\u001f\u007f[M\u0013\u0015<c\u0004\u0014\u000e<\u000e".toCharArray();
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
                            c2 = 'z';
                            break;
                        }
                        case 1: {
                            c2 = '\u001c';
                            break;
                        }
                        case 2: {
                            c2 = '/';
                            break;
                        }
                        case 3: {
                            c2 = 'm';
                            break;
                        }
                        default: {
                            c2 = 'g';
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
        final char[] charArray2 = "Z=".toCharArray();
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
                            c4 = 'z';
                            break;
                        }
                        case 1: {
                            c4 = '\u001c';
                            break;
                        }
                        case 2: {
                            c4 = '/';
                            break;
                        }
                        case 3: {
                            c4 = 'm';
                            break;
                        }
                        default: {
                            c4 = 'g';
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
        final char[] charArray3 = ")lJ\u000e\u000e\u001cuJ\tG\u0013rK\b\u001fZu\\M\b\u000fh\u000f\u0002\u0001ZsM\u0007\u0002\u0019h\u000f\u0001\u000e\th\u0015".toCharArray();
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
                            c6 = 'z';
                            break;
                        }
                        case 1: {
                            c6 = '\u001c';
                            break;
                        }
                        case 2: {
                            c6 = '/';
                            break;
                        }
                        case 3: {
                            c6 = 'm';
                            break;
                        }
                        default: {
                            c6 = 'g';
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
        final char[] charArray4 = "\u0014yN\u0019I\u0013".toCharArray();
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
                            c8 = 'z';
                            break;
                        }
                        case 1: {
                            c8 = '\u001c';
                            break;
                        }
                        case 2: {
                            c8 = '/';
                            break;
                        }
                        case 3: {
                            c8 = 'm';
                            break;
                        }
                        default: {
                            c8 = 'g';
                            break;
                        }
                    }
                    charArray4[length4] = (char)(c7 ^ c8);
                    ++n16;
                } while (n14 == 0);
            }
            if (n14 <= n16) {
                z[n13] = new String(charArray4).intern();
                i.z = z;
                i.b = new neat.system.f((i.e != null) ? i.e : (i.e = a(i.z[3])));
                return;
            }
            continue;
        }
    }
}
