// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.FontMetrics;
import java.util.Vector;
import java.util.Enumeration;

public final class N
{
    private A q;
    
    public N() {
        this.q = new A();
    }
    
    public final synchronized void q(final Q q) {
        final int s = q.s;
        if (q.q(63)) {
            this.q(s);
            return;
        }
        if (q.q != null) {
            this.q.q(q);
        }
    }
    
    public final synchronized Q q(final int n) {
        return (Q)this.q.w(n);
    }
    
    public final synchronized boolean q(final int n) {
        return this.q.w(n);
    }
    
    public final synchronized Q q(final String s) {
        final Enumeration q = this.q();
        while (q.hasMoreElements()) {
            final Q q2 = q.nextElement();
            if (s.equals(q2.o)) {
                return q2;
            }
        }
        return null;
    }
    
    private synchronized Enumeration q() {
        return new ad(this.q);
    }
    
    public final synchronized Vector q() {
        final Enumeration q = this.q();
        final Vector<Q> vector = new Vector<Q>();
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
            final Q q2 = q.nextElement();
            int n2 = s.indexOf(q2.o, n);
            while (n2 != -1 && n2 < length) {
                if (q(s, n2 - 1) && q(s, n2 + q2.o.length())) {
                    length = n2;
                    n2 = -1;
                }
                else {
                    n2 = s.indexOf(q2.o, n2 + 1);
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
            final Q q2 = q.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(q2.o, 0); i != -1; i = s.indexOf(q2.o, i + 1)) {
                if (q(s, i - 1) && q(s, i + q2.o.length())) {
                    ++n2;
                }
            }
            n += n2 * fontMetrics.stringWidth(q2.o);
        }
        return n;
    }
    
    public final int q(final String s) {
        final Enumeration q = this.q();
        int n = 0;
        while (q.hasMoreElements()) {
            final Q q2 = q.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(q2.o, 0); i != -1; i = s.indexOf(q2.o, i + 1)) {
                if (q(s, i - 1) && q(s, i + q2.o.length())) {
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
