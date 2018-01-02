// 
// Decompiled by Procyon v0.5.30
// 

package z;

final class y
{
    public int a;
    public int b;
    public int c;
    public int d;
    private static /* synthetic */ boolean e;
    
    public final void a() {
        if (!y.e && this.a < 0) {
            throw new AssertionError();
        }
        if (!y.e && this.b < 0) {
            throw new AssertionError();
        }
        if (!y.e && this.c <= 0) {
            throw new AssertionError();
        }
        if (!y.e && this.d <= 0) {
            throw new AssertionError();
        }
    }
    
    static {
        y.e = !h.class.desiredAssertionStatus();
    }
}
