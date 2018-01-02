// 
// Decompiled by Procyon v0.5.30
// 

package doook;

class bn extends cF
{
    protected String a;
    protected int h;
    private final bo a;
    
    public Object a(final String s) {
        if ("enabled".equals(s)) {
            return new Boolean(this.a.a.d(this.h));
        }
        return super.a(s);
    }
    
    public boolean a(final String s, final Object o) {
        if ("enabled".equals(s)) {
            this.a.a.a(this.h, (boolean)o);
        }
        return true;
    }
    
    public String c(final String s) {
        return this.a;
    }
    
    public bn(final bo a, final String s, final String a2, final int h) {
        super(0, s);
        this.a = a;
        this.h = h;
        this.a = a2;
    }
}
