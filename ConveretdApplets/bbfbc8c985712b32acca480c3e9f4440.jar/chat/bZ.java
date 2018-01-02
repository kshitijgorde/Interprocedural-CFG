// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class bZ extends Z implements a
{
    public Y a;
    
    public final String a() {
        return bm.a(aS.a(167), new String[] { this.toString() });
    }
    
    public final Object a(final String s) {
        if (this.a != null && "star".equals(s)) {
            return this.a.a;
        }
        return super.a(s);
    }
    
    public bZ(final Z z) {
        this(z.i, z.d);
        this.a(z.a);
        super.a = z.a;
        super.a = z.a;
        super.b = z.b;
        super.c = z.c;
        super.a = z.a;
        super.c = z.c;
        super.b = z.b;
        super.d = z.d;
    }
    
    public bZ(final int n, final String s) {
        super(n, s);
    }
}
