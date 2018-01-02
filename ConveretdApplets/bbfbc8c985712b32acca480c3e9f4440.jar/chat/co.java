// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class co extends bv
{
    public co(final String s) {
        super(-999, s);
        super.b = -1;
    }
    
    public co(final bv bv) {
        super(bv.i, new String(bv.d));
        super.b = bv.b;
        super.b = bv.b;
        super.a = bv.a;
        super.c = bv.c;
        super.a = new String(bv.a);
    }
    
    public final Object a(final String s) {
        if ("room".equals(s)) {
            if (super.b == -1) {
                return aS.a(646);
            }
            return super.b;
        }
        else {
            if ("image".equals(s)) {
                return super.a.a;
            }
            if ("country".equals(s)) {
                return super.a;
            }
            return super.a(s);
        }
    }
}
