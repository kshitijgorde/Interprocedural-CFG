// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class M implements Cloneable
{
    public int q;
    public Object[] q;
    private int[] q;
    public int w;
    
    public final boolean q(final Object o) {
        return this.q(o) != -1;
    }
    
    public final boolean q(int q) {
        return (q = this.q(q)) != -1;
    }
    
    public final synchronized int q(final Object o) {
        if (o instanceof dh) {
            return this.q(((dh)o).q());
        }
        for (int i = 0; i < this.q; ++i) {
            if (this.q[i].equals(o)) {
                return i;
            }
        }
        return -1;
    }
    
    public final synchronized int q(final int n) {
        for (int i = 0; i < this.q; ++i) {
            if (this.q[i] == n) {
                return i;
            }
            if (this.q[i] > n) {
                return -1;
            }
        }
        return -1;
    }
    
    public final synchronized Object q(final int n) {
        if (n >= this.q) {
            throw new ArrayIndexOutOfBoundsException(n + " >= " + this.q);
        }
        return this.q[n];
    }
    
    public final synchronized Object w(int q) {
        if ((q = this.q(q)) == -1) {
            return null;
        }
        return this.q(q);
    }
    
    public final int q(final dh dh) {
        return this.q(dh, dh.q());
    }
    
    public final synchronized int q(final Object o, int n) {
        int q = this.q;
        if (n < 0 && n > -2147483638) {
            n = this.w++;
        }
        else if (n >= this.w) {
            this.w = n + 1;
        }
        if (o instanceof dh) {
            ((dh)o).u(n);
        }
        for (int i = 0; i < this.q; ++i) {
            if (n == this.q[i]) {
                this.q[i] = o;
                return n;
            }
            if (n <= this.q[i]) {
                q = i;
                break;
            }
        }
        final int n2 = n;
        final int n3 = q;
        final int n4 = n2;
        final int n5 = this.q + 1;
        Object[] q2 = this.q;
        int[] q3 = this.q;
        if (n3 >= n5) {
            throw new ArrayIndexOutOfBoundsException(n3 + " > " + this.q);
        }
        if (n5 > this.q.length) {
            q2 = new Object[this.q.length << 1];
            q3 = new int[this.q.length << 1];
            if (n3 > 0) {
                System.arraycopy(this.q, 0, q2, 0, n3);
                System.arraycopy(this.q, 0, q3, 0, n3);
            }
        }
        System.arraycopy(this.q, n3, q2, n3 + 1, this.q - n3);
        System.arraycopy(this.q, n3, q3, n3 + 1, this.q - n3);
        this.q = q2;
        (this.q = q3)[n3] = (int)o;
        this.q[n3] = n4;
        ++this.q;
        if (o instanceof dh) {
            ((dh)o).u(n);
        }
        return n;
    }
    
    public final synchronized void q() {
        for (int i = 0; i < this.q; ++i) {
            this.q[i] = null;
            this.q[i] = -1;
        }
        this.q = 0;
    }
    
    private synchronized void q(final int n) {
        if (n >= this.q) {
            throw new ArrayIndexOutOfBoundsException(n + " >= " + this.q);
        }
        if (n < 0) {
            throw new ArrayIndexOutOfBoundsException(n);
        }
        final int n2;
        if ((n2 = this.q - n - 1) > 0) {
            System.arraycopy(this.q, n + 1, this.q, n, n2);
            System.arraycopy(this.q, n + 1, this.q, n, n2);
        }
        (--this.q)[this.q] = null;
        this.q[this.q] = -1;
    }
    
    public final synchronized boolean w(final Object o) {
        final int q;
        if ((q = this.q(o)) >= 0) {
            this.q(q);
            return true;
        }
        return false;
    }
    
    public final synchronized boolean w(int q) {
        if ((q = this.q(q)) >= 0) {
            this.q(q);
            return true;
        }
        return false;
    }
    
    public final synchronized void q(final Object[] array) {
        System.arraycopy(this.q, 0, array, 0, this.q);
        for (int i = 0; i < this.q; ++i) {
            this.q[i] = ((dh)this.q[i]).q();
        }
    }
    
    public final synchronized Object clone() {
        try {
            final M m;
            (m = (M)super.clone()).q = new Object[this.q];
            m.q = new int[this.q];
            System.arraycopy(this.q, 0, m.q, 0, this.q);
            System.arraycopy(this.q, 0, m.q, 0, this.q);
            return m;
        }
        catch (CloneNotSupportedException ex) {
            throw new InternalError();
        }
    }
    
    public M() {
        this(10, 0);
    }
    
    public M(final int n, final int w) {
        this.q = 0;
        this.q = new Object[n];
        this.q = new int[n];
        this.w = w;
    }
}