// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.FontMetrics;
import java.util.Vector;
import java.util.Enumeration;

public final class dQ
{
    private dW q;
    
    public dQ() {
        this.q = new dW();
    }
    
    public final synchronized void q(final cj cj) {
        final int q = cj.q();
        if (cj.q(63)) {
            this.q(q);
            return;
        }
        if (cj.q != null) {
            this.q.q(cj);
        }
    }
    
    public final synchronized cj q(final int n) {
        return (cj)this.q.w(n);
    }
    
    public final synchronized boolean q(final int n) {
        return this.q.w(n);
    }
    
    public final synchronized cj q(final String s) {
        final Enumeration q = this.q();
        while (q.hasMoreElements()) {
            final cj cj = q.nextElement();
            if (s.equals(cj.getName())) {
                return cj;
            }
        }
        return null;
    }
    
    public final synchronized Enumeration q() {
        return this.q.q();
    }
    
    public final synchronized Vector q() {
        final Enumeration q = this.q();
        final Vector<cj> vector = new Vector<cj>();
        while (q.hasMoreElements()) {
            vector.addElement(q.nextElement());
        }
        return vector;
    }
    
    public final synchronized void q() {
        this.q.e();
    }
    
    public final synchronized void w() {
        this.q.e();
    }
    
    public final synchronized boolean q() {
        return this.q.q() == 0;
    }
    
    public final int q(final String s, final int n) {
        final Enumeration q = this.q();
        int length = s.length();
        while (q.hasMoreElements()) {
            final cj cj = q.nextElement();
            int n2 = s.indexOf(cj.getName(), n);
            while (n2 != -1 && n2 < length) {
                if (q(s, n2 - 1) && q(s, n2 + cj.getName().length())) {
                    length = n2;
                    n2 = -1;
                }
                else {
                    n2 = s.indexOf(cj.getName(), n2 + 1);
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
            final cj cj = q.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(cj.getName(), 0); i != -1; i = s.indexOf(cj.getName(), i + 1)) {
                if (q(s, i - 1) && q(s, i + cj.getName().length())) {
                    ++n2;
                }
            }
            n += n2 * fontMetrics.stringWidth(cj.getName());
        }
        return n;
    }
    
    public final int q(final String s) {
        final Enumeration q = this.q();
        int n = 0;
        while (q.hasMoreElements()) {
            final cj cj = q.nextElement();
            int n2 = 0;
            for (int i = s.indexOf(cj.getName(), 0); i != -1; i = s.indexOf(cj.getName(), i + 1)) {
                if (q(s, i - 1) && q(s, i + cj.getName().length())) {
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
