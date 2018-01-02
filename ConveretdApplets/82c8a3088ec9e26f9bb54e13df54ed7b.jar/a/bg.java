// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.NoSuchElementException;

public final class bg
{
    private Y q;
    
    public bg() {
        this.q = new Y("head", null, null);
        final Y q = this.q;
        final Y q2 = this.q;
        final Y q3 = this.q;
        q2.q = q3;
        q.w = q3;
    }
    
    private Y q() {
        final Y w;
        if ((w = this.q.w) == this.q) {
            return null;
        }
        return w;
    }
    
    public final synchronized Object q() {
        final Y q;
        if ((q = this.q()) == null) {
            throw new NoSuchElementException();
        }
        q.q();
        return q.q;
    }
    
    private Y w() {
        final Y q;
        if ((q = this.q.q) == this.q) {
            throw new NoSuchElementException();
        }
        return q;
    }
    
    private synchronized Y q(final Object o, final Y y) {
        if (o == null) {
            System.out.println("Putting null into the queue!");
        }
        final Y y3;
        final Y y2 = y3 = new Y(o, y, y.q);
        y2.q.w = y3;
        return y3.w.q = y3;
    }
    
    public final synchronized Y q(final Object o) {
        final Y y2;
        final Y y = y2 = new Y(o, this.q.w, this.q);
        y.q.w = y2;
        return y2.w.q = y2;
    }
    
    public final synchronized Y w(final Object o) {
        final Y y2;
        final Y y = y2 = new Y(o, this.q, this.q.q);
        y.q.w = y2;
        return y2.w.q = y2;
    }
    
    public final void q(final int n, final Object o) {
        this.q(o, this.q(n));
    }
    
    public final void q() {
        try {
            for (Y y = this.w(); y != null; y = this.w()) {
                y.q();
            }
        }
        catch (NoSuchElementException ex) {}
        final Y q = this.q;
        final Y q2 = this.q;
        final Y q3 = this.q;
        q2.q = q3;
        q.w = q3;
    }
    
    public final String toString() {
        Y y = this.q.w;
        final StringBuffer sb = new StringBuffer();
        while (y != this.q) {
            sb.append(y.toString()).append(", ");
            y = y.w;
        }
        return sb.toString();
    }
    
    public final boolean q() {
        return this.q() == 0;
    }
    
    public final int q() {
        int n = 0;
        Y y;
        if ((y = this.q()) == null) {
            return 0;
        }
        while (y != this.q) {
            ++n;
            y = y.w;
        }
        return n;
    }
    
    public Y q(final int n) {
        final int q = this.q();
        if (n < 0 || n > q) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + q);
        }
        Y y = this.q;
        if (n == q) {
            return y;
        }
        if (n < q >> 1) {
            for (int i = 0; i <= n; ++i) {
                y = y.w;
            }
        }
        else {
            for (int j = q; j > n; --j) {
                y = y.q;
            }
        }
        return y;
    }
}
