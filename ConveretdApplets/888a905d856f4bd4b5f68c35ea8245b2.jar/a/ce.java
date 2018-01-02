// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.util.NoSuchElementException;

public final class ce
{
    private ar q;
    
    public ce() {
        this.q = new ar("head", null, null);
        final ar q = this.q;
        final ar q2 = this.q;
        final ar q3 = this.q;
        q2.q = q3;
        q.w = q3;
    }
    
    private ar q() {
        final ar w;
        if ((w = this.q.w) == this.q) {
            return null;
        }
        return w;
    }
    
    public final synchronized Object q() {
        final ar q;
        if ((q = this.q()) == null) {
            throw new NoSuchElementException();
        }
        q.q();
        return q.q;
    }
    
    private ar w() {
        final ar q;
        if ((q = this.q.q) == this.q) {
            throw new NoSuchElementException();
        }
        return q;
    }
    
    private synchronized ar q(final Object o, final ar ar) {
        if (o == null) {
            System.out.println("Putting null into the queue!");
        }
        final ar ar3;
        final ar ar2 = ar3 = new ar(o, ar, ar.q);
        ar2.q.w = ar3;
        return ar3.w.q = ar3;
    }
    
    public final synchronized ar q(final Object o) {
        final ar ar2;
        final ar ar = ar2 = new ar(o, this.q.w, this.q);
        ar.q.w = ar2;
        return ar2.w.q = ar2;
    }
    
    public final synchronized ar w(final Object o) {
        final ar ar2;
        final ar ar = ar2 = new ar(o, this.q, this.q.q);
        ar.q.w = ar2;
        return ar2.w.q = ar2;
    }
    
    public final void q(final int n, final Object o) {
        this.q(o, this.q(n));
    }
    
    public final void q() {
        try {
            for (ar ar = this.w(); ar != null; ar = this.w()) {
                ar.q();
            }
        }
        catch (NoSuchElementException ex) {}
        final ar q = this.q;
        final ar q2 = this.q;
        final ar q3 = this.q;
        q2.q = q3;
        q.w = q3;
    }
    
    public final String toString() {
        ar ar = this.q.w;
        final StringBuffer sb = new StringBuffer();
        while (ar != this.q) {
            sb.append(ar.toString()).append(", ");
            ar = ar.w;
        }
        return sb.toString();
    }
    
    public final boolean q() {
        return this.q() == 0;
    }
    
    public final int q() {
        int n = 0;
        ar ar;
        if ((ar = this.q()) == null) {
            return 0;
        }
        while (ar != this.q) {
            ++n;
            ar = ar.w;
        }
        return n;
    }
    
    public ar q(final int n) {
        final int q = this.q();
        if (n < 0 || n > q) {
            throw new IndexOutOfBoundsException("Index: " + n + ", Size: " + q);
        }
        ar ar = this.q;
        if (n == q) {
            return ar;
        }
        if (n < q >> 1) {
            for (int i = 0; i <= n; ++i) {
                ar = ar.w;
            }
        }
        else {
            for (int j = q; j > n; --j) {
                ar = ar.q;
            }
        }
        return ar;
    }
}
