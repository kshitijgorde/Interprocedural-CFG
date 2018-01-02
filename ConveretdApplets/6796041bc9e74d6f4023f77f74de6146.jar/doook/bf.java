// 
// Decompiled by Procyon v0.5.30
// 

package doook;

public class bf extends T
{
    public Object a(final String s) {
        if ("default".equals(s)) {
            return new Boolean(this.d(62));
        }
        return super.a(s);
    }
    
    public boolean a(final String s, final Object o) {
        if ("default".equals(s) && !this.d(63)) {
            this.a(62, (boolean)o);
            return true;
        }
        return false;
    }
    
    public String c(final String s) {
        return am.a(ao.e("Double-click here to edit %1: %2."), new String[] { this.f(), super.a });
    }
    
    public bf(final av av) {
        this(av.h(), av.f());
        this.a(av.d());
        super.a = av.a;
        super.a = av.a;
        super.b = av.b;
        super.Z = av.Z;
        super.a = av.a;
        super.aN = av.aN;
        super.d = av.d;
        super.aw = av.aw;
    }
    
    public bf(final int n, final String s) {
        super(n, s);
    }
}
