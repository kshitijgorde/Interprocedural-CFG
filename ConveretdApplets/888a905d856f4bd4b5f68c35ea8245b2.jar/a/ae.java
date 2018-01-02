// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.FontMetrics;
import java.util.Vector;
import java.util.Enumeration;

public final class ae
{
    private M q;
    
    public ae() {
        this.q = new M();
    }
    
    public final synchronized void q(final aj aj) {
        final int s = aj.s;
        if (aj.q(63)) {
            this.q(s);
            return;
        }
        if (aj.q != null) {
            this.q.q(aj);
        }
    }
    
    public final synchronized aj q(final int n) {
        return (aj)this.q.w(n);
    }
    
    public final synchronized boolean q(final int n) {
        return this.q.w(n);
    }
    
    public final synchronized aj q(final String s) {
        final Enumeration q = this.q();
        while (q.hasMoreElements()) {
            final aj aj = q.nextElement();
            if (s.equals(aj.a)) {
                return aj;
            }
        }
        return null;
    }
    
    public final synchronized Enumeration q() {
        return new aw(this.q);
    }
    
    public final synchronized Vector q() {
        final Enumeration q = this.q();
        final Vector<aj> vector = new Vector<aj>();
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
            final aj aj = q.nextElement();
            int n2 = s.indexOf(aj.a, n);
            while (n2 != -1 && n2 < length) {
                if (q(s, n2 - 1) && q(s, n2 + aj.a.length())) {
                    length = n2;
                    n2 = -1;
                }
                else {
                    n2 = s.indexOf(aj.a, n2 + 1);
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
            final aj aj = q.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(aj.a, 0); i != -1; i = s.indexOf(aj.a, i + 1)) {
                if (q(s, i - 1) && q(s, i + aj.a.length())) {
                    ++n2;
                }
            }
            n += n2 * fontMetrics.stringWidth(aj.a);
        }
        return n;
    }
    
    public final int q(final String s) {
        final Enumeration q = this.q();
        int n = 0;
        while (q.hasMoreElements()) {
            final aj aj = q.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(aj.a, 0); i != -1; i = s.indexOf(aj.a, i + 1)) {
                if (q(s, i - 1) && q(s, i + aj.a.length())) {
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
