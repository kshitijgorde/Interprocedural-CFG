// 
// Decompiled by Procyon v0.5.30
// 

package chat;

public final class cf extends aK implements a
{
    public boolean c;
    
    public final Object a(final String s) {
        if ("site".equals(s)) {
            return new Boolean(this.a(20));
        }
        if ("ftp".equals(s)) {
            return new Boolean(this.a(21));
        }
        return super.a(s);
    }
    
    public final String a() {
        return bm.a(aS.a(323), new String[] { this.toString() });
    }
    
    public cf(final p p) {
        this(p.i, p.d);
        super.b = p.b;
        super.a = p.a;
        super.a = p.a;
        super.b = p.b;
        super.c = p.c;
        super.f = p.f;
        super.g = p.g;
    }
    
    public cf(final int n, final String s) {
        super(n, s);
    }
}
