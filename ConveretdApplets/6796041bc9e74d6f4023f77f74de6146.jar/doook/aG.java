// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class aG implements Cloneable
{
    private int g;
    private Object[] b;
    private int[] c;
    private int a;
    
    public void a(final boolean b) {
    }
    
    public void a() {
    }
    
    public boolean a(final Object o) {
        return this.a(o) != -1;
    }
    
    public boolean b(final int n) {
        return this.f(n) != -1;
    }
    
    public synchronized int a(final Object o) {
        if (o instanceof aI) {
            return this.f(((aI)o).h());
        }
        for (int i = 0; i < this.g; ++i) {
            if (this.b[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }
    
    public synchronized int f(final int n) {
        for (int i = 0; i < this.g; ++i) {
            if (this.c[i] == n) {
                return i;
            }
            if (this.c[i] > n) {
                return -1;
            }
        }
        return -1;
    }
    
    public synchronized int b(final int n) {
        if (n >= this.g) {
            throw new ArrayIndexOutOfBoundsException(n + " >= " + this.g);
        }
        return this.c[n];
    }
    
    public synchronized int b(final Object o) {
        return this.b(this.a(o));
    }
    
    public synchronized Object a(final int n) {
        if (n >= this.g) {
            throw new ArrayIndexOutOfBoundsException(n + " >= " + this.g);
        }
        return this.b[n];
    }
    
    public synchronized Object b(final int n) {
        final int f = this.f(n);
        if (f == -1) {
            return null;
        }
        return this.a(f);
    }
    
    public int a(final aI ai) {
        return this.a(ai, ai.h());
    }
    
    public synchronized int a(final Object o, int n) {
        int g = this.g;
        if (n < 0) {
            n = this.a++;
        }
        else if (n >= this.a) {
            this.a = n + 1;
        }
        if (o instanceof aI) {
            ((aI)o).l(n);
        }
        for (int i = 0; i < this.g; ++i) {
            if (n == this.c[i]) {
                this.b[i] = o;
                return n;
            }
            if (n <= this.c[i]) {
                g = i;
                break;
            }
        }
        this.a(o, n, g);
        if (o instanceof aI) {
            ((aI)o).l(n);
        }
        return n;
    }
    
    private final void a(final Object o, final int n, final int n2) {
        final int n3 = this.g + 1;
        Object[] b = this.b;
        int[] c = this.c;
        if (n2 >= n3) {
            throw new ArrayIndexOutOfBoundsException(n2 + " > " + this.g);
        }
        if (n3 > this.b.length) {
            b = new Object[this.b.length * 2];
            c = new int[this.b.length * 2];
            if (n2 > 0) {
                System.arraycopy(this.b, 0, b, 0, n2);
                System.arraycopy(this.c, 0, c, 0, n2);
            }
        }
        System.arraycopy(this.b, n2, b, n2 + 1, this.g - n2);
        System.arraycopy(this.c, n2, c, n2 + 1, this.g - n2);
        this.b = b;
        this.c = c;
        this.b[n2] = o;
        this.c[n2] = n;
        ++this.g;
    }
    
    public synchronized int b() {
        return this.g;
    }
    
    public synchronized int g() {
        return this.a;
    }
    
    public synchronized void b() {
        for (int i = 0; i < this.g; ++i) {
            this.b[i] = null;
            this.c[i] = -1;
        }
        this.g = 0;
    }
    
    public final synchronized void e(final int n) {
        if (n >= this.g) {
            throw new ArrayIndexOutOfBoundsException(n + " >= " + this.g);
        }
        if (n < 0) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        final int n2 = this.g - n - 1;
        if (n2 > 0) {
            System.arraycopy(this.b, n + 1, this.b, n, n2);
            System.arraycopy(this.c, n + 1, this.c, n, n2);
        }
        --this.g;
        this.b[this.g] = null;
        this.c[this.g] = -1;
    }
    
    public final synchronized boolean b(final Object o) {
        final int a = this.a(o);
        if (a >= 0) {
            this.e(a);
            return true;
        }
        return false;
    }
    
    public final synchronized boolean c(final int n) {
        final int f = this.f(n);
        if (f >= 0) {
            this.e(f);
            return true;
        }
        return false;
    }
    
    public synchronized void a(final Object[] array) {
        System.arraycopy(this.b, 0, array, 0, this.g);
    }
    
    public synchronized Object clone() {
        try {
            final aG ag = (aG)super.clone();
            ag.b = new Object[this.g];
            ag.c = new int[this.g];
            System.arraycopy(this.b, 0, ag.b, 0, this.g);
            System.arraycopy(this.c, 0, ag.c, 0, this.g);
            return ag;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public aG() {
        this(10, 0, "KeyedVector");
    }
    
    public aG(final int n, final int a, final String s) {
        this.g = 0;
        this.a = 0;
        this.b = new Object[n];
        this.c = new int[n];
        this.a = a;
    }
}
