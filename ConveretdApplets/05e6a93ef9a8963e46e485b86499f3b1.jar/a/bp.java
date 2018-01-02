// 
// Decompiled by Procyon v0.5.30
// 

package a;

import java.awt.Image;

public final class bp extends as
{
    public int g;
    public int h;
    public String p;
    public String a;
    public Image e;
    public Image r;
    public String s;
    
    public bp(final int n, final String s) {
        super(n, s);
    }
    
    public final String q() {
        return a.t.q(al.q("Click here to edit the virtual user %1."), new String[] { this.toString() });
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
            return super.o;
        }
        if ("room".equals(s)) {
            return this.p;
        }
        if ("country".equals(s)) {
            return this.a;
        }
        if ("iconImg".equals(s)) {
            return this.e;
        }
        if ("starImg".equals(s)) {
            return this.r;
        }
        if ("group".equals(s)) {
            return null;
        }
        return super.q(s);
    }
}
