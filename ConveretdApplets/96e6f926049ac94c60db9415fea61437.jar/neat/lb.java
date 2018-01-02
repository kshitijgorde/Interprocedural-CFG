// 
// Decompiled by Procyon v0.5.30
// 

package neat;

import neat.system.f;
import neat.system.cb;

public class lb implements cb
{
    private static f a;
    private char[] b;
    private int c;
    private static /* synthetic */ Class d;
    private static String z;
    
    public static lb a() {
        final lb lb = (lb)neat.lb.a.a();
        lb.i();
        return lb;
    }
    
    public static lb a(final String s) {
        final lb lb = (lb)neat.lb.a.a();
        lb.b(s);
        return lb;
    }
    
    public void f() {
        lb.a.a(this);
    }
    
    public void g() {
        this.c = 0;
    }
    
    public void h() {
    }
    
    public kb b() {
        return kb.a(this, true);
    }
    
    public String c() {
        final kb b = this.b();
        final String string = b.toString();
        b.f();
        return string;
    }
    
    char[] d() {
        return this.b;
    }
    
    int e() {
        return 0;
    }
    
    int f() {
        return this.c;
    }
    
    protected void i() {
        this.a(16);
    }
    
    protected void a(final int n) {
        if (this.b.length < n) {
            lb.a.a(this.b);
            this.b = lb.a.a(n);
        }
    }
    
    protected void b(final String s) {
        this.a(s.length() + 16);
        this.c(s);
    }
    
    public int j() {
        return this.c;
    }
    
    public void b(final int n) {
        final int length = this.b.length;
        if (n > length) {
            int n2 = (length + 1) * 2;
            if (n > n2) {
                n2 = n;
            }
            final char[] a = lb.a.a(n2);
            System.arraycopy(this.b, 0, a, 0, this.c);
            lb.a.a(this.b);
            this.b = a;
        }
    }
    
    public void c(final int c) {
        if (c < 0) {
            throw new StringIndexOutOfBoundsException(c);
        }
        this.b(c);
        if (this.c < c) {
            while (this.c < c) {
                this.b[this.c] = '\0';
                ++this.c;
            }
        }
        this.c = c;
    }
    
    public void a(final int n, final int n2, final char[] array, final int n3) {
        if (n < 0 || n >= this.c) {
            throw new StringIndexOutOfBoundsException(n);
        }
        if (n2 < 0 || n2 > this.c) {
            throw new StringIndexOutOfBoundsException(n2);
        }
        if (n < n2) {
            System.arraycopy(this.b, n, array, n3, n2 - n);
        }
    }
    
    public lb a(final Object o) {
        return this.c(String.valueOf(o));
    }
    
    public lb c(String value) {
        if (value == null) {
            value = String.valueOf(value);
        }
        final int length = value.length();
        this.b(this.c + length);
        value.getChars(0, length, this.b, this.c);
        this.c += length;
        return this;
    }
    
    public lb a(kb a) {
        if (a == null) {
            a = kb.a((Object)a);
        }
        final int d = a.d();
        this.b(this.c + d);
        a.a(0, d, this.b, this.c);
        this.c += d;
        return this;
    }
    
    public lb a(final boolean b) {
        final kb a = nb.a(b);
        this.a(a);
        a.f();
        return this;
    }
    
    public lb a(final char c) {
        this.b(this.c + 1);
        this.b[this.c++] = c;
        return this;
    }
    
    public lb d(final int n) {
        final kb a = nb.a(n);
        this.a(a);
        a.f();
        return this;
    }
    
    public lb a(final long n) {
        final kb a = nb.a(n);
        this.a(a);
        a.f();
        return this;
    }
    
    public lb k() {
        final int n = this.c - 1;
        for (int i = n - 1 >> 1; i >= 0; --i) {
            final char c = this.b[i];
            this.b[i] = this.b[n - i];
            this.b[n - i] = c;
        }
        return this;
    }
    
    public kb l() {
        return kb.a(this);
    }
    
    public String toString() {
        return new String(this.b, 0, this.c);
    }
    
    static /* synthetic */ Class d(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    public lb() {
        this.b = lb.a.a(0);
    }
    
    static {
        final char[] charArray = "G\u001a\fn\u0015E\u001d".toCharArray();
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
                            c2 = ')';
                            break;
                        }
                        case 1: {
                            c2 = '\u007f';
                            break;
                        }
                        case 2: {
                            c2 = 'm';
                            break;
                        }
                        case 3: {
                            c2 = '\u001a';
                            break;
                        }
                        default: {
                            c2 = ';';
                            break;
                        }
                    }
                    charArray[length] = (char)(c ^ c2);
                    ++n3;
                } while (n == 0);
            }
            if (n <= n3) {
                lb.z = new String(charArray).intern();
                lb.a = new f((lb.d != null) ? lb.d : (lb.d = d(lb.z)));
                return;
            }
            continue;
        }
    }
}
