// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class av implements Cloneable
{
    private int a;
    private Object[] a;
    private int[] a;
    private int b;
    
    public final boolean a(final Object o) {
        return this.a(o) != -1;
    }
    
    public final boolean a(final int n) {
        return this.a(n) != -1;
    }
    
    public final synchronized int a(final Object o) {
        if (o instanceof am) {
            return this.a(((am)o).a());
        }
        for (int i = 0; i < this.a; ++i) {
            if (this.a[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }
    
    public final synchronized int a(final int n) {
        for (int i = 0; i < this.a; ++i) {
            if (this.a[i] == n) {
                return i;
            }
            if (this.a[i] > n) {
                return -1;
            }
        }
        return -1;
    }
    
    public final synchronized int b(final int n) {
        if (n >= this.a) {
            throw new ArrayIndexOutOfBoundsException(n + " >= " + this.a);
        }
        return this.a[n];
    }
    
    public final synchronized int b(final Object o) {
        return this.b(this.a(o));
    }
    
    public final synchronized Object a(final int n) {
        if (n >= this.a) {
            throw new ArrayIndexOutOfBoundsException(n + " >= " + this.a);
        }
        return this.a[n];
    }
    
    public final synchronized Object b(int a) {
        if ((a = this.a(a)) == -1) {
            return null;
        }
        return this.a(a);
    }
    
    public final int a(final am am) {
        return this.a(am, am.a());
    }
    
    public final synchronized int a(final Object o, int n) {
        int a = this.a;
        if (n < 0) {
            n = this.b++;
        }
        else if (n >= this.b) {
            this.b = n + 1;
        }
        if (o instanceof am) {
            ((am)o).a(n);
        }
        for (int i = 0; i < this.a; ++i) {
            if (n == this.a[i]) {
                this.a[i] = o;
                return n;
            }
            if (n <= this.a[i]) {
                a = i;
                break;
            }
        }
        final av av = this;
        final int n2 = n;
        final int n3 = a;
        final int n4 = n2;
        this = av;
        final int n5 = av.a + 1;
        Object[] a2 = this.a;
        int[] a3 = this.a;
        if (n3 >= n5) {
            throw new ArrayIndexOutOfBoundsException(n3 + " > " + this.a);
        }
        if (n5 > this.a.length) {
            a2 = new Object[this.a.length << 1];
            a3 = new int[this.a.length << 1];
            if (n3 > 0) {
                System.arraycopy(this.a, 0, a2, 0, n3);
                System.arraycopy(this.a, 0, a3, 0, n3);
            }
        }
        System.arraycopy(this.a, n3, a2, n3 + 1, this.a - n3);
        System.arraycopy(this.a, n3, a3, n3 + 1, this.a - n3);
        this.a = a2;
        (this.a = a3)[n3] = (int)o;
        this.a[n3] = n4;
        final av av2 = this;
        ++av2.a;
        if (o instanceof am) {
            ((am)o).a(n);
        }
        return n;
    }
    
    public final synchronized int a() {
        return this.a;
    }
    
    public final synchronized int b() {
        return this.b;
    }
    
    public final synchronized void a() {
        for (int i = 0; i < this.a; ++i) {
            this.a[i] = null;
            this.a[i] = -1;
        }
        this.a = 0;
    }
    
    private synchronized void a(final int n) {
        if (n >= this.a) {
            throw new ArrayIndexOutOfBoundsException(n + " >= " + this.a);
        }
        if (n < 0) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        final int n2;
        if ((n2 = this.a - n - 1) > 0) {
            System.arraycopy(this.a, n + 1, this.a, n, n2);
            System.arraycopy(this.a, n + 1, this.a, n, n2);
        }
        (--this.a)[this.a] = null;
        this.a[this.a] = -1;
    }
    
    public final synchronized boolean b(final Object o) {
        final int a;
        if ((a = this.a(o)) >= 0) {
            this.a(a);
            return true;
        }
        return false;
    }
    
    public final synchronized boolean b(int a) {
        if ((a = this.a(a)) >= 0) {
            this.a(a);
            return true;
        }
        return false;
    }
    
    public final synchronized Object clone() {
        try {
            final av av;
            (av = (av)super.clone()).a = new Object[this.a];
            av.a = new int[this.a];
            System.arraycopy(this.a, 0, av.a, 0, this.a);
            System.arraycopy(this.a, 0, av.a, 0, this.a);
            return av;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public av() {
        this(0);
    }
    
    public av(final int b) {
        this.a = 0;
        this.b = 0;
        this.a = new Object[10];
        this.a = new int[10];
        this.b = b;
    }
}
