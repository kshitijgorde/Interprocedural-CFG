// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class bi extends ax implements aU
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
        if ("showtousers".equals(s)) {
            this.a(36, (boolean)o);
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
        if ("showtousers".equals(s)) {
            return new Boolean(this.d(36));
        }
        return super.a(s);
    }
    
    public String c(final String s) {
        return ao.e("Click here to edit this item.");
    }
    
    public bi(final ax ax) {
        this(ax.h(), ax.f());
        this.a(ax.d());
        super.a = ax.a;
        super.aN = ax.aN;
        super.aw = ax.aw;
    }
    
    public bi(final int n, final String s) {
        super(n, s);
    }
}
