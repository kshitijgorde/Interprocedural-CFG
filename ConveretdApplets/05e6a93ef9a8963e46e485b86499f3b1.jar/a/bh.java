// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.NoSuchElementException;

public final class bh
{
    private Z q;
    
    public bh() {
        this.q = new Z("head", null, null);
        final Z q = this.q;
        final Z q2 = this.q;
        final Z q3 = this.q;
        q2.q = q3;
        q.w = q3;
    }
    
    private Z q() {
        final Z w;
        if ((w = this.q.w) == this.q) {
            return null;
        }
        return w;
    }
    
    public final synchronized Object q() {
        final Z q;
        if ((q = this.q()) == null) {
            throw new NoSuchElementException();
        }
        q.q();
        return q.q;
    }
    
    private Z w() {
        final Z q;
        if ((q = this.q.q) == this.q) {
            throw new NoSuchElementException();
        }
        return q;
    }
    
    private synchronized Z q(final Object o, final Z z) {
        if (o == null) {
            System.out.println("Putting null into the queue!");
        }
        final Z z3;
        final Z z2 = z3 = new Z(o, z, z.q);
        z2.q.w = z3;
        return z3.w.q = z3;
    }
    
    public final synchronized Z q(final Object o) {
        final Z z2;
        final Z z = z2 = new Z(o, this.q.w, this.q);
        z.q.w = z2;
        return z2.w.q = z2;
    }
    
    public final synchronized Z w(final Object o) {
        final Z z2;
        final Z z = z2 = new Z(o, this.q, this.q.q);
        z.q.w = z2;
        return z2.w.q = z2;
    }
    
    public final void q(final int n, final Object o) {
        this.q(o, this.q(n));
    }
    
    public final void q() {
        try {
            for (Z z = this.w(); z != null; z = this.w()) {
                z.q();
            }
        }
        catch (NoSuchElementException ex) {}
        final Z q = this.q;
        final Z q2 = this.q;
        final Z q3 = this.q;
        q2.q = q3;
        q.w = q3;
    }
    
    public final String toString() {
        Z z = this.q.w;
        final StringBuffer sb = new StringBuffer();
        while (z != this.q) {
            sb.append(z.toString()).append(", ");
            z = z.w;
        }
        return sb.toString();
    }
    
    public final boolean q() {
        return this.q() == 0;
    }
    
    public final int q() {
        int n = 0;
        Z z;
        if ((z = this.q()) == null) {
            return 0;
        }
        while (z != this.q) {
            ++n;
            z = z.w;
        }
        return n;
    }
    
    public Z q(final int n) {
        final int q = this.q();
        if (n < 0 || n > q) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + q);
        }
        Z z = this.q;
        if (n == q) {
            return z;
        }
        if (n < q >> 1) {
            for (int i = 0; i <= n; ++i) {
                z = z.w;
            }
        }
        else {
            for (int j = q; j > n; --j) {
                z = z.q;
            }
        }
        return z;
    }
}
