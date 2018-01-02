// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.FontMetrics;
import java.util.Vector;
import java.util.Enumeration;

public final class O
{
    private B q;
    
    public O() {
        this.q = new B();
    }
    
    public final synchronized void q(final R r) {
        final int a = r.a;
        if (r.q(63)) {
            this.q(a);
            return;
        }
        if (r.q != null) {
            this.q.q(r);
        }
    }
    
    public final synchronized R q(final int n) {
        return (R)this.q.w(n);
    }
    
    public final synchronized boolean q(final int n) {
        return this.q.w(n);
    }
    
    public final synchronized R q(final String s) {
        final Enumeration q = this.q();
        while (q.hasMoreElements()) {
            final R r = q.nextElement();
            if (s.equals(r.o)) {
                return r;
            }
        }
        return null;
    }
    
    private synchronized Enumeration q() {
        return new ae(this.q);
    }
    
    public final synchronized Vector q() {
        final Enumeration q = this.q();
        final Vector<R> vector = new Vector<R>();
        while (q.hasMoreElements()) {
            vector.addElement(q.nextElement());
        }
        return vector;
    }
    
    public final synchronized void q() {
        this.q.q();
    }
    
    public final synchronized void w() {
        this.q.q();
    }
    
    public final synchronized boolean q() {
        return this.q.q == 0;
    }
    
    public final int q(final String s, final int n) {
        final Enumeration q = this.q();
        int length = s.length();
        while (q.hasMoreElements()) {
            final R r = q.nextElement();
            int n2 = s.indexOf(r.o, n);
            while (n2 != -1 && n2 < length) {
                if (q(s, n2 - 1) && q(s, n2 + r.o.length())) {
                    length = n2;
                    n2 = -1;
                }
                else {
                    n2 = s.indexOf(r.o, n2 + 1);
                }
            }
        }
        if (length != s.length()) {
            return length;
        }
        return -1;
    }
    
    public final int q(final String s, final FontMetrics fontMetrics) {
        final Enumeration q = this.q();
        int n = 0;
        while (q.hasMoreElements()) {
            final R r = q.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(r.o, 0); i != -1; i = s.indexOf(r.o, i + 1)) {
                if (q(s, i - 1) && q(s, i + r.o.length())) {
                    ++n2;
                }
            }
            n += n2 * fontMetrics.stringWidth(r.o);
        }
        return n;
    }
    
    public final int q(final String s) {
        final Enumeration q = this.q();
        int n = 0;
        while (q.hasMoreElements()) {
            final R r = q.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(r.o, 0); i != -1; i = s.indexOf(r.o, i + 1)) {
                if (q(s, i - 1) && q(s, i + r.o.length())) {
                    ++n2;
                }
            }
            n += n2;
        }
        return n;
    }
    
    private static boolean q(final String s, final int n) {
        return n < 0 || n >= s.length() || Character.isSpace(s.charAt(n));
    }
}
