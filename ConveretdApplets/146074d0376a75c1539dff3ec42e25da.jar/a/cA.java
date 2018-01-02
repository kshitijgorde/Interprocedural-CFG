// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;

public final class cA extends dj
{
    public int k;
    public int l;
    public String a;
    public String s;
    public String d;
    public Image q;
    public Image w;
    public String f;
    
    public cA(final int n, final String s) {
        super(n, s);
    }
    
    public final String q() {
        return ec.q(eb.q("Click here to edit the virtual user %1."), new String[] { this.toString() });
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
            return this.getName();
        }
        if ("room".equals(s)) {
            return this.s;
        }
        if ("country".equals(s)) {
            return this.d;
        }
        if ("iconImg".equals(s)) {
            return this.q;
        }
        if ("starImg".equals(s)) {
            return this.w;
        }
        if ("group".equals(s)) {
            return this.a;
        }
        return super.q(s);
    }
    
    public final int q(final cA ca) {
        final int q;
        if ((q = super.q(ca)) != 0) {
            return q;
        }
        if (this.k != ca.k) {
            return this.k - ca.k;
        }
        if (this.l != ca.l) {
            return this.l - ca.l;
        }
        if (this.a != null && this.a.compareTo(ca.a) != 0) {
            return this.a.compareTo(ca.a);
        }
        if (this.s != null && this.s.compareTo(ca.s) != 0) {
            return this.s.compareTo(ca.s);
        }
        if (this.d != null && this.d.compareTo(ca.d) != 0) {
            return this.d.compareTo(ca.d);
        }
        if (this.q != null && this.q != ca.q) {
            return 1;
        }
        if (this.w != null && this.w != ca.w) {
            return 1;
        }
        if (this.f != null && this.f.compareTo(ca.f) != 0) {
            return this.f.compareTo(ca.f);
        }
        return 0;
    }
}
