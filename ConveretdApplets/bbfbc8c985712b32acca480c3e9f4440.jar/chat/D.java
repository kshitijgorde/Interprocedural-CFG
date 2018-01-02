// 
// Decompiled by Procyon v0.5.30
// 

package chat;

final class D extends U
{
    private String a;
    private int a;
    private final E a;
    
    public final Object a(final String s) {
        if ("enabled".equals(s)) {
            return new Boolean(this.a.a.b(this.a));
        }
        return super.a(s);
    }
    
    public final boolean a(final String s, final Object o) {
        if ("enabled".equals(s)) {
            this.a.a.b(this.a, (boolean)o);
        }
        return true;
    }
    
    public final String a() {
        return this.a;
    }
    
    public D(final E a, final String s, final String a2, final int a3) {
        super(0, s);
        this.a = a;
        this.a = a3;
        this.a = a2;
    }
}
