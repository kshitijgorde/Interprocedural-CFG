// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;
import neat.system.cb;

public class p implements cb
{
    private static f a;
    private int[] b;
    private int c;
    private int d;
    private static /* synthetic */ Class e;
    private static String[] z;
    
    private void a(final int d) {
        this.d = d;
        if (this.b == null) {
            this.b = p.a.b(d);
        }
    }
    
    private void b(final int n) {
        if (this.b.length < n) {
            int n2 = this.b.length + this.b.length;
            final int n3 = n + this.d;
            if (n3 > n2) {
                n2 = n3;
            }
            final int[] b = p.a.b(n2);
            System.arraycopy(this.b, 0, b, 0, this.c);
            p.a.a(this.b);
            this.b = b;
        }
    }
    
    public void c(final int c) {
        this.b(c);
        if (this.c < c) {
            this.c = c;
        }
    }
    
    public int a() {
        return this.c;
    }
    
    public boolean b() {
        return this.c <= 0;
    }
    
    public int d(final int n) {
        if (n < 0 || n >= this.b.length) {
            throw new ArrayIndexOutOfBoundsException(p.z[2] + n);
        }
        return this.b[n];
    }
    
    public void a(final int n, final int n2) {
        this.b(n + 1);
        this.b[n] = n2;
    }
    
    public void e(final int n) {
        this.b(this.c + 1);
        this.b[this.c++] = n;
    }
    
    public void b(final int n, final int n2) {
        this.b(this.c + 1);
        this.c(n, 1);
        this.b[n] = n2;
    }
    
    public void c(final int n, final int n2) {
        if (n2 < 0) {
            throw new IllegalArgumentException(p.z[1] + n2 + p.z[0]);
        }
        this.b(this.c + n2);
        for (int i = this.c - 1; i >= n; --i) {
            this.b[i + n2] = this.b[i];
        }
        this.c += n2;
    }
    
    public void f(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException(p.z[1] + n + p.z[0]);
        }
        this.b(this.c + n);
        this.c += n;
    }
    
    public void d(final int c, final int n) {
        if (n < 0) {
            throw new IllegalArgumentException(p.z[1] + n + p.z[0]);
        }
        if (c + n >= this.c) {
            if (c < 0 || c >= this.c) {
                throw new ArrayIndexOutOfBoundsException(p.z[3] + c + p.z[0]);
            }
            this.c = c;
        }
        else {
            this.c -= n;
            for (int i = c; i < this.c; ++i) {
                this.b[i] = this.b[i + n];
            }
        }
    }
    
    public int c() {
        if (this.c > 0) {
            --this.c;
            return this.b[this.c];
        }
        return 0;
    }
    
    public void d() {
        this.c = 0;
    }
    
    public boolean g(final int n) {
        for (int i = this.c - 1; i >= 0; --i) {
            if (this.b[i] == n) {
                return true;
            }
        }
        return false;
    }
    
    public static p e() {
        final p p = (p)neat.p.a.a();
        p.a(10);
        return p;
    }
    
    public static p h(final int n) {
        final p p = (p)neat.p.a.a();
        p.a(n);
        return p;
    }
    
    public void f() {
        p.a.a(this);
    }
    
    public void g() {
        this.c = 0;
    }
    
    public void h() {
    }
    
    static /* synthetic */ Class a(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public p() {
        this.b = null;
    }
    
    static {
        final String[] z = new String[5];
        final int n = 0;
        final char[] charArray = "=1".toCharArray();
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
                            c2 = '\u001d';
                            break;
                        }
                        case 1: {
                            c2 = '\u0010';
                            break;
                        }
                        case 2: {
                            c2 = '\r';
                            break;
                        }
                        case 3: {
                            c2 = '5';
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
        final char[] charArray2 = "\\0`\u00dc\u0015xd-[\u0002p0aP\u000fxd-[\u0002zqy\\\u0011'".toCharArray();
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
                            c4 = '\u001d';
                            break;
                        }
                        case 1: {
                            c4 = '\u0010';
                            break;
                        }
                        case 2: {
                            c4 = '\r';
                            break;
                        }
                        case 3: {
                            c4 = '5';
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
        final char[] charArray3 = "t~iP\u001f'".toCharArray();
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
                            c6 = '\u001d';
                            break;
                        }
                        case 1: {
                            c6 = '\u0010';
                            break;
                        }
                        case 2: {
                            c6 = '\r';
                            break;
                        }
                        case 3: {
                            c6 = '5';
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
        final char[] charArray4 = "\\j-\\\tyuu\u0015\u008eod\u00e4^\u0002=~hXGw\u00e37".toCharArray();
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
                            c8 = '\u001d';
                            break;
                        }
                        case 1: {
                            c8 = '\u0010';
                            break;
                        }
                        case 2: {
                            c8 = '\r';
                            break;
                        }
                        case 3: {
                            c8 = '5';
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
            if (n14 > n16) {
                continue;
            }
            break;
        }
        z[n13] = new String(charArray4).intern();
        final int n17 = 4;
        final char[] charArray5 = "sulAIm".toCharArray();
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
                            c10 = '\u001d';
                            break;
                        }
                        case 1: {
                            c10 = '\u0010';
                            break;
                        }
                        case 2: {
                            c10 = '\r';
                            break;
                        }
                        case 3: {
                            c10 = '5';
                            break;
                        }
                        default: {
                            c10 = 'g';
                            break;
                        }
                    }
                    charArray5[length5] = (char)(c9 ^ c10);
                    ++n20;
                } while (n18 == 0);
            }
            if (n18 <= n20) {
                z[n17] = new String(charArray5).intern();
                p.z = z;
                p.a = new f((p.e != null) ? p.e : (p.e = a(p.z[4])));
                return;
            }
            continue;
        }
    }
}
