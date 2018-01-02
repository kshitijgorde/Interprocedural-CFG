// 
// Decompiled by Procyon v0.5.30
// 

package z.B;

public class P implements M
{
    private static final long J = -2653101601542927401L;
    public static final int H = 0;
    public static final int G = 1;
    public static final int E = 2;
    private int D;
    private boolean F;
    private boolean I;
    
    public P() {
        this.D = 1;
        this.F = false;
        this.I = false;
    }
    
    public void B(final int d) {
        this.C(d);
        this.D = d;
    }
    
    public int D() {
        return this.D;
    }
    
    public void C(final boolean f) {
        this.F = f;
    }
    
    public boolean F() {
        return this.F;
    }
    
    public void D(final boolean i) {
        this.I = i;
    }
    
    public boolean E() {
        return this.I;
    }
    
    private void C(final int n) {
        if (n != 0 && n != 1 && n != 2) {
            throw new RuntimeException(m.A("BMPEncodeParam0"));
        }
    }
}
