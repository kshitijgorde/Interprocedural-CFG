// 
// Decompiled by Procyon v0.5.30
// 

package doook;

import java.awt.Image;

public class J extends cd implements aU
{
    public String c(final String s) {
        return null;
    }
    
    public boolean a(final String s, final Object o) {
        if ("sort".equals(s)) {
            super.an = Integer.parseInt((String)o);
            return true;
        }
        if ("name".equals(s)) {
            this.d((String)o);
            return true;
        }
        if ("restricted".equals(s)) {
            this.a(5, !(boolean)o);
            return true;
        }
        return false;
    }
    
    public Object a(final String s) {
        if ("image".equals(s)) {
            if (super.b != null && super.b.length() > 0 && y.b.containsKey(super.b)) {
                return y.b.get(super.b);
            }
            return null;
        }
        else {
            if ("sort".equals(s)) {
                return new Integer(super.an);
            }
            if ("restricted".equals(s)) {
                return new Boolean(!this.d(5));
            }
            return super.a(s);
        }
    }
    
    public int a(final aU au, final String s) {
        if ("sort".equals(s)) {
            return super.an - ((cd)au).an;
        }
        return super.a(au, s);
    }
    
    public J(final cd cd) {
        this(cd.h(), cd.f());
        this.a(cd.d());
        super.b = cd.b;
        super.an = cd.an;
        super.description = cd.description;
        super.aw = cd.aw;
    }
    
    public J(final int n, final String s) {
        super(n, s);
    }
}
