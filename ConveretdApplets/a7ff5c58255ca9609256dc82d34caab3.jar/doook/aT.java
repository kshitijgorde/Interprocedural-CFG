// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class aT implements Cloneable
{
    private int c;
    private Object[] b;
    private int[] e;
    private int h;
    
    public void a(final boolean b) {
    }
    
    public void f() {
    }
    
    public boolean a(final Object o) {
        return this.a(o) != -1;
    }
    
    public boolean b(final int n) {
        return this.g(n) != -1;
    }
    
    public synchronized int a(final Object o) {
        if (o instanceof aV) {
            return this.g(((aV)o).e());
        }
        for (int i = 0; i < this.c; ++i) {
            if (this.b[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }
    
    public synchronized int g(final int n) {
        for (int i = 0; i < this.c; ++i) {
            if (this.e[i] == n) {
                return i;
            }
            if (this.e[i] > n) {
                return -1;
            }
        }
        return -1;
    }
    
    public synchronized Object a(final int n) {
        if (n >= this.c) {
            throw new ArrayIndexOutOfBoundsException(n + " >= " + this.c);
        }
        return this.b[n];
    }
    
    public synchronized Object b(final int n) {
        final int g = this.g(n);
        if (g == -1) {
            return null;
        }
        return this.a(g);
    }
    
    public int a(final aV av) {
        return this.a(av, av.e());
    }
    
    public synchronized int a(final Object o, int n) {
        int c = this.c;
        if (n < 0) {
            n = this.h++;
        }
        else if (n >= this.h) {
            this.h = n + 1;
        }
        if (o instanceof aV) {
            ((aV)o).k(n);
        }
        for (int i = 0; i < this.c; ++i) {
            if (n == this.e[i]) {
                this.b[i] = o;
                return n;
            }
            if (n <= this.e[i]) {
                c = i;
                break;
            }
        }
        this.a(o, n, c);
        if (o instanceof aV) {
            ((aV)o).k(n);
        }
        return n;
    }
    
    private final void a(final Object o, final int n, final int n2) {
        final int n3 = this.c + 1;
        Object[] b = this.b;
        int[] e = this.e;
        if (n2 >= n3) {
            throw new ArrayIndexOutOfBoundsException(n2 + " > " + this.c);
        }
        if (n3 > this.b.length) {
            b = new Object[this.b.length * 2];
            e = new int[this.b.length * 2];
            if (n2 > 0) {
                System.arraycopy(this.b, 0, b, 0, n2);
                System.arraycopy(this.e, 0, e, 0, n2);
            }
        }
        System.arraycopy(this.b, n2, b, n2 + 1, this.c - n2);
        System.arraycopy(this.e, n2, e, n2 + 1, this.c - n2);
        this.b = b;
        this.e = e;
        this.b[n2] = o;
        this.e[n2] = n;
        ++this.c;
    }
    
    public synchronized int b() {
        return this.c;
    }
    
    public synchronized void g() {
        for (int i = 0; i < this.c; ++i) {
            this.b[i] = null;
            this.e[i] = -1;
        }
        this.c = 0;
    }
    
    public final synchronized void n(final int n) {
        if (n >= this.c) {
            throw new ArrayIndexOutOfBoundsException(n + " >= " + this.c);
        }
        if (n < 0) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        final int n2 = this.c - n - 1;
        if (n2 > 0) {
            System.arraycopy(this.b, n + 1, this.b, n, n2);
            System.arraycopy(this.e, n + 1, this.e, n, n2);
        }
        --this.c;
        this.b[this.c] = null;
        this.e[this.c] = -1;
    }
    
    public final synchronized boolean b(final Object o) {
        final int a = this.a(o);
        if (a >= 0) {
            this.n(a);
            return true;
        }
        return false;
    }
    
    public final synchronized boolean c(final int n) {
        final int g = this.g(n);
        if (g >= 0) {
            this.n(g);
            return true;
        }
        return false;
    }
    
    public synchronized void a(final Object[] array) {
        System.arraycopy(this.b, 0, array, 0, this.c);
    }
    
    public synchronized Object clone() {
        try {
            final aT at = (aT)super.clone();
            at.b = new Object[this.c];
            at.e = new int[this.c];
            System.arraycopy(this.b, 0, at.b, 0, this.c);
            System.arraycopy(this.e, 0, at.e, 0, this.c);
            return at;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public aT() {
        this(10, 0, "KeyedVector");
    }
    
    public aT(final int n, final int h, final String s) {
        this.c = 0;
        this.h = 0;
        this.b = new Object[n];
        this.e = new int[n];
        this.h = h;
    }
}
