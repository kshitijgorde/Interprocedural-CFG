// 
// Decompiled by Procyon v0.5.30
// 

package a;

public final class cA extends cu implements bJ
{
    public String e;
    
    public final Object q(final String s) {
        if ("group".equals(s)) {
            return this.e;
        }
        return super.q(s);
    }
    
    public final String q() {
        return B.q(be.w("Click here to edit the account %1."), new String[] { this.toString() });
    }
    
    public cA(final cu cu) {
        this(cu.s, cu.a);
        this.q(cu.w());
        super.q = cu.q;
        super.q = cu.q;
        super.h = cu.h;
        super.d = cu.w();
        super.o(cu.g);
        super.q(cu.w);
        super.w(cu.e);
        super.q = cu.q;
        super.w = cu.w;
        super.q = cu.q;
        super.w = cu.w;
    }
    
    public cA(final int n, final String s) {
        super(n, s);
    }
}
