// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;

public final class bq extends bV
{
    public int j;
    public int k;
    public String p;
    public String a;
    public Image q;
    public Image w;
    public String s;
    
    public bq(final int n, final String s) {
        super(n, s);
    }
    
    public final String q() {
        return cv.q(cv.q("Click here to edit the virtual user %1."), new String[] { this.toString() });
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
            return this.p;
        }
        if ("country".equals(s)) {
            return this.a;
        }
        if ("iconImg".equals(s)) {
            return this.q;
        }
        if ("starImg".equals(s)) {
            return this.w;
        }
        if ("group".equals(s)) {
            return null;
        }
        return super.q(s);
    }
}
