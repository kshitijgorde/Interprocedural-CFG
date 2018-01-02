// 
// Decompiled by Procyon v0.5.30
// 

package com.diginet.digichat.util;

public class k implements Cloneable
{
    private int a;
    private Object[] b;
    private int[] c;
    private int d;
    
    public final void a(final boolean b) {
    }
    
    public final void a() {
    }
    
    public final boolean a(final Object o) {
        return this.b(o) != -1;
    }
    
    public final boolean a(final int n) {
        return this.b(n) != -1;
    }
    
    public final synchronized int b(final Object o) {
        if (o instanceof i) {
            return this.b(((i)o).x());
        }
        for (int i = 0; i < this.a; ++i) {
            if (this.b[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }
    
    public final synchronized int b(final int n) {
        for (int i = 0; i < this.a; ++i) {
            if (this.c[i] == n) {
                return i;
            }
            if (this.c[i] > n) {
                return -1;
            }
        }
        return -1;
    }
    
    public final synchronized Object c(final int n) {
        if (n >= this.a) {
            throw new ArrayIndexOutOfBoundsException(n + " >= " + this.a);
        }
        return this.b[n];
    }
    
    public final synchronized Object d(final int n) {
        final int b = this.b(n);
        if (b == -1) {
            return null;
        }
        return this.c(b);
    }
    
    public final int a(final i i) {
        return this.a(i, i.x());
    }
    
    public final synchronized int a(final Object o, int n) {
        int a = this.a;
        if (n < 0) {
            n = this.d++;
        }
        else if (n >= this.d) {
            this.d = n + 1;
        }
        if (o instanceof i) {
            ((i)o).t(n);
        }
        for (int i = 0; i < this.a; ++i) {
            if (n == this.c[i]) {
                this.b[i] = o;
                return n;
            }
            if (n <= this.c[i]) {
                a = i;
                break;
            }
        }
        this.a(o, n, a);
        if (o instanceof i) {
            ((i)o).t(n);
        }
        return n;
    }
    
    private final void a(final Object o, final int n, final int n2) {
        final int n3 = this.a + 1;
        Object[] b = this.b;
        int[] c = this.c;
        if (n2 >= n3) {
            throw new ArrayIndexOutOfBoundsException(n2 + " > " + this.a);
        }
        if (n3 > this.b.length) {
            b = new Object[this.b.length * 2];
            c = new int[this.b.length * 2];
            if (n2 > 0) {
                System.arraycopy(this.b, 0, b, 0, n2);
                System.arraycopy(this.c, 0, c, 0, n2);
            }
        }
        System.arraycopy(this.b, n2, b, n2 + 1, this.a - n2);
        System.arraycopy(this.c, n2, c, n2 + 1, this.a - n2);
        this.b = b;
        this.c = c;
        this.b[n2] = o;
        this.c[n2] = n;
        ++this.a;
    }
    
    public final synchronized int b() {
        return this.a;
    }
    
    public final synchronized void c() {
        for (int i = 0; i < this.a; ++i) {
            this.b[i] = null;
            this.c[i] = -1;
        }
        this.a = 0;
    }
    
    public final synchronized void e(final int n) {
        if (n >= this.a) {
            throw new ArrayIndexOutOfBoundsException(n + " >= " + this.a);
        }
        if (n < 0) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        final int n2 = this.a - n - 1;
        if (n2 > 0) {
            System.arraycopy(this.b, n + 1, this.b, n, n2);
            System.arraycopy(this.c, n + 1, this.c, n, n2);
        }
        --this.a;
        this.b[this.a] = null;
        this.c[this.a] = -1;
    }
    
    public final synchronized boolean c(final Object o) {
        final int b = this.b(o);
        if (b >= 0) {
            this.e(b);
            return true;
        }
        return false;
    }
    
    public final synchronized boolean f(final int n) {
        final int b = this.b(n);
        if (b >= 0) {
            this.e(b);
            return true;
        }
        return false;
    }
    
    public final synchronized void a(final Object[] array) {
        System.arraycopy(this.b, 0, array, 0, this.a);
    }
    
    public final synchronized Object clone() {
        try {
            final k k = (k)super.clone();
            k.b = new Object[this.a];
            k.c = new int[this.a];
            System.arraycopy(this.b, 0, k.b, 0, this.a);
            System.arraycopy(this.c, 0, k.c, 0, this.a);
            return k;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public k() {
        this(10, 0, "KeyedVector");
    }
    
    public k(final int n, final int d, final String s) {
        this.a = 0;
        this.d = 0;
        this.b = new Object[n];
        this.c = new int[n];
        this.d = d;
    }
}
