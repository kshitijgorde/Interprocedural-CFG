// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class k implements Cloneable
{
    private int e;
    private Object[] b;
    private int[] a;
    private int b;
    
    public void a(final boolean b) {
    }
    
    public void c() {
    }
    
    public boolean a(final Object o) {
        return this.a(o) != -1;
    }
    
    public boolean a(final int n) {
        return this.a(n) != -1;
    }
    
    public synchronized int a(final Object o) {
        if (o instanceof s) {
            return this.a(((s)o).b());
        }
        for (int i = 0; i < this.e; ++i) {
            if (this.b[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }
    
    public synchronized int a(final int n) {
        for (int i = 0; i < this.e; ++i) {
            if (this.a[i] == n) {
                return i;
            }
            if (this.a[i] > n) {
                return -1;
            }
        }
        return -1;
    }
    
    public synchronized Object a(final int n) {
        if (n >= this.e) {
            throw new ArrayIndexOutOfBoundsException(n + " >= " + this.e);
        }
        return this.b[n];
    }
    
    public synchronized Object b(final int n) {
        final int a = this.a(n);
        if (a == -1) {
            return null;
        }
        return this.a(a);
    }
    
    public int a(final s s) {
        return this.a(s, s.b());
    }
    
    public synchronized int a(final Object o, int n) {
        int e = this.e;
        if (n < 0) {
            n = this.b++;
        }
        else if (n >= this.b) {
            this.b = n + 1;
        }
        if (o instanceof s) {
            ((s)o).d(n);
        }
        for (int i = 0; i < this.e; ++i) {
            if (n == this.a[i]) {
                this.b[i] = o;
                return n;
            }
            if (n <= this.a[i]) {
                e = i;
                break;
            }
        }
        this.a(o, n, e);
        if (o instanceof s) {
            ((s)o).d(n);
        }
        return n;
    }
    
    private final void a(final Object o, final int n, final int n2) {
        final int n3 = this.e + 1;
        Object[] b = this.b;
        int[] a = this.a;
        if (n2 >= n3) {
            throw new ArrayIndexOutOfBoundsException(n2 + " > " + this.e);
        }
        if (n3 > this.b.length) {
            b = new Object[this.b.length * 2];
            a = new int[this.b.length * 2];
            if (n2 > 0) {
                System.arraycopy(this.b, 0, b, 0, n2);
                System.arraycopy(this.a, 0, a, 0, n2);
            }
        }
        System.arraycopy(this.b, n2, b, n2 + 1, this.e - n2);
        System.arraycopy(this.a, n2, a, n2 + 1, this.e - n2);
        this.b = b;
        this.a = a;
        this.b[n2] = o;
        this.a[n2] = n;
        ++this.e;
    }
    
    public synchronized int a() {
        return this.e;
    }
    
    public synchronized void b() {
        for (int i = 0; i < this.e; ++i) {
            this.b[i] = null;
            this.a[i] = -1;
        }
        this.e = 0;
    }
    
    public final synchronized void a(final int n) {
        if (n >= this.e) {
            throw new ArrayIndexOutOfBoundsException(n + " >= " + this.e);
        }
        if (n < 0) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        final int n2 = this.e - n - 1;
        if (n2 > 0) {
            System.arraycopy(this.b, n + 1, this.b, n, n2);
            System.arraycopy(this.a, n + 1, this.a, n, n2);
        }
        --this.e;
        this.b[this.e] = null;
        this.a[this.e] = -1;
    }
    
    public final synchronized boolean b(final Object o) {
        final int a = this.a(o);
        if (a >= 0) {
            this.a(a);
            return true;
        }
        return false;
    }
    
    public final synchronized boolean b(final int n) {
        final int a = this.a(n);
        if (a >= 0) {
            this.a(a);
            return true;
        }
        return false;
    }
    
    public synchronized void a(final Object[] array) {
        System.arraycopy(this.b, 0, array, 0, this.e);
    }
    
    public synchronized Object clone() {
        try {
            final k k = (k)super.clone();
            k.b = new Object[this.e];
            k.a = new int[this.e];
            System.arraycopy(this.b, 0, k.b, 0, this.e);
            System.arraycopy(this.a, 0, k.a, 0, this.e);
            return k;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public k() {
        this(10, 0, "KeyedVector");
    }
    
    public k(final int n, final int b, final String s) {
        this.e = 0;
        this.b = 0;
        this.b = new Object[n];
        this.a = new int[n];
        this.b = b;
    }
}
