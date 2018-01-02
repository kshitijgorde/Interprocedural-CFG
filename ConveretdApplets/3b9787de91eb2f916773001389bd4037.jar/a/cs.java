// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.NoSuchElementException;

public final class cs
{
    private ct q;
    
    public cs() {
        this.q = new ct("head", null, null);
        final ct q = this.q;
        final ct q2 = this.q;
        final ct q3 = this.q;
        q2.q = q3;
        q.w = q3;
    }
    
    private ct q() {
        final ct w;
        if ((w = this.q.w) == this.q) {
            return null;
        }
        return w;
    }
    
    public final synchronized Object q() {
        final ct q;
        if ((q = this.q()) == null) {
            throw new NoSuchElementException();
        }
        q.q();
        return q.q;
    }
    
    private ct w() {
        final ct q;
        if ((q = this.q.q) == this.q) {
            throw new NoSuchElementException();
        }
        return q;
    }
    
    private synchronized ct q(final Object o, final ct ct) {
        if (o == null) {
            System.out.println("Putting null into the queue!");
        }
        final ct ct3;
        final ct ct2 = ct3 = new ct(o, ct, ct.q);
        ct2.q.w = ct3;
        return ct3.w.q = ct3;
    }
    
    public final synchronized ct q(final Object o) {
        final ct ct2;
        final ct ct = ct2 = new ct(o, this.q.w, this.q);
        ct.q.w = ct2;
        return ct2.w.q = ct2;
    }
    
    public final synchronized ct w(final Object o) {
        final ct ct2;
        final ct ct = ct2 = new ct(o, this.q, this.q.q);
        ct.q.w = ct2;
        return ct2.w.q = ct2;
    }
    
    public final void q(final int n, final Object o) {
        this.q(o, this.q(n));
    }
    
    public final void q() {
        try {
            for (ct ct = this.w(); ct != null; ct = this.w()) {
                ct.q();
            }
        }
        catch (NoSuchElementException ex) {}
        final ct q = this.q;
        final ct q2 = this.q;
        final ct q3 = this.q;
        q2.q = q3;
        q.w = q3;
    }
    
    public final String toString() {
        ct ct = this.q.w;
        final StringBuffer sb = new StringBuffer();
        while (ct != this.q) {
            sb.append(ct.toString()).append(", ");
            ct = ct.w;
        }
        return sb.toString();
    }
    
    public final boolean q() {
        return this.q() == 0;
    }
    
    public final int q() {
        int n = 0;
        ct ct;
        if ((ct = this.q()) == null) {
            return 0;
        }
        while (ct != this.q) {
            ++n;
            ct = ct.w;
        }
        return n;
    }
    
    public ct q(final int n) {
        final int q = this.q();
        if (n < 0 || n > q) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + q);
        }
        ct ct = this.q;
        if (n == q) {
            return ct;
        }
        if (n < q >> 1) {
            for (int i = 0; i <= n; ++i) {
                ct = ct.w;
            }
        }
        else {
            for (int j = q; j > n; --j) {
                ct = ct.q;
            }
        }
        return ct;
    }
}
