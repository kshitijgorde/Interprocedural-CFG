// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class aT extends ax implements aU
{
    public boolean a(final String s, final Object o) {
        if ("replace".equals(s)) {
            super.a = (String)o;
            return true;
        }
        if ("name".equals(s)) {
            this.d((String)o);
            return true;
        }
        return false;
    }
    
    public Object a(final String s) {
        if ("replace".equals(s)) {
            return super.a;
        }
        if ("name".equals(s)) {
            return this.f();
        }
        return super.a(s);
    }
    
    public String c(final String s) {
        return ao.e("Click here to edit this item.");
    }
    
    public aT(final ax ax) {
        this(ax.h(), ax.f());
        this.a(ax.d());
        super.a = ax.a;
        super.aN = ax.aN;
        super.d = ax.d;
        super.aw = ax.aw;
    }
    
    public aT(final int n, final String s) {
        super(n, s);
    }
}
