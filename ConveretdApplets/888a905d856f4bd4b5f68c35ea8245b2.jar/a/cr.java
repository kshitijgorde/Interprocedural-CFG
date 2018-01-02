// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;

public final class cr extends aO
{
    public int j;
    public int k;
    public String p;
    public String s;
    public String d;
    public Image e;
    public Image r;
    public String f;
    
    public cr(final int n, final String s) {
        super(n, s);
    }
    
    public final String q() {
        return B.q(be.w("Click here to edit the virtual user %1."), new String[] { this.toString() });
    }
    
    public final boolean q(final String s, final Object o) {
        if ("connect".equals(s)) {
            this.q(0, (boolean)o);
            return true;
        }
        return false;
    }
    
    public final Object q(final String s) {
        if ("connect".equals(s)) {
            return new Boolean(this.q(0));
        }
        if ("name".equals(s)) {
            return super.a;
        }
        if ("room".equals(s)) {
            return this.s;
        }
        if ("country".equals(s)) {
            return this.d;
        }
        if ("iconImg".equals(s)) {
            return this.e;
        }
        if ("starImg".equals(s)) {
            return this.r;
        }
        if ("group".equals(s)) {
            return this.p;
        }
        return super.q(s);
    }
    
    public final int q(final cr cr) {
        final int q;
        if ((q = super.q(cr)) != 0) {
            return q;
        }
        if (this.j != cr.j) {
            return this.j - cr.j;
        }
        if (this.k != cr.k) {
            return this.k - cr.k;
        }
        if (this.p != null && this.p.compareTo(cr.p) != 0) {
            return this.p.compareTo(cr.p);
        }
        if (this.s != null && this.s.compareTo(cr.s) != 0) {
            return this.s.compareTo(cr.s);
        }
        if (this.d != null && this.d.compareTo(cr.d) != 0) {
            return this.d.compareTo(cr.d);
        }
        if (this.e != null && this.e != cr.e) {
            return 1;
        }
        if (this.r != null && this.r != cr.r) {
            return 1;
        }
        if (this.f != null && this.f.compareTo(cr.f) != 0) {
            return this.f.compareTo(cr.f);
        }
        return 0;
    }
}
