// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.NoSuchElementException;

public final class dY
{
    private dZ q;
    
    public dY() {
        this.q = new dZ("head", null, null);
        final dZ q = this.q;
        final dZ q2 = this.q;
        final dZ q3 = this.q;
        q2.q = q3;
        q.w = q3;
    }
    
    private dZ q() {
        final dZ w;
        if ((w = this.q.w) == this.q) {
            return null;
        }
        return w;
    }
    
    public final synchronized Object q() {
        final dZ q;
        if ((q = this.q()) == null) {
            throw new NoSuchElementException();
        }
        q.q();
        return q.q;
    }
    
    private dZ w() {
        final dZ q;
        if ((q = this.q.q) == this.q) {
            throw new NoSuchElementException();
        }
        return q;
    }
    
    private synchronized dZ q(final Object o, final dZ dz) {
        if (o == null) {
            System.out.println("Putting null into the queue!");
        }
        final dZ dz3;
        final dZ dz2 = dz3 = new dZ(o, dz, dz.q);
        dz2.q.w = dz3;
        return dz3.w.q = dz3;
    }
    
    public final synchronized dZ q(final Object o) {
        final dZ dz2;
        final dZ dz = dz2 = new dZ(o, this.q.w, this.q);
        dz.q.w = dz2;
        return dz2.w.q = dz2;
    }
    
    public final synchronized dZ w(final Object o) {
        final dZ dz2;
        final dZ dz = dz2 = new dZ(o, this.q, this.q.q);
        dz.q.w = dz2;
        return dz2.w.q = dz2;
    }
    
    public final void q(final int n, final Object o) {
        this.q(o, this.q(n));
    }
    
    public final void q() {
        try {
            for (dZ dz = this.w(); dz != null; dz = this.w()) {
                dz.q();
            }
        }
        catch (NoSuchElementException ex) {}
        final dZ q = this.q;
        final dZ q2 = this.q;
        final dZ q3 = this.q;
        q2.q = q3;
        q.w = q3;
    }
    
    public final String toString() {
        dZ dz = this.q.w;
        final StringBuffer sb = new StringBuffer();
        while (dz != this.q) {
            sb.append(dz.toString()).append(", ");
            dz = dz.w;
        }
        return sb.toString();
    }
    
    public final boolean q() {
        return this.q() == 0;
    }
    
    public final int q() {
        int n = 0;
        dZ dz;
        if ((dz = this.q()) == null) {
            return 0;
        }
        while (dz != this.q) {
            ++n;
            dz = dz.w;
        }
        return n;
    }
    
    public dZ q(final int n) {
        final int q = this.q();
        if (n < 0 || n > q) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + q);
        }
        dZ dz = this.q;
        if (n == q) {
            return dz;
        }
        if (n < q >> 1) {
            for (int i = 0; i <= n; ++i) {
                dz = dz.w;
            }
        }
        else {
            for (int j = q; j > n; --j) {
                dz = dz.q;
            }
        }
        return dz;
    }
}
