// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

final class cw implements Runnable
{
    private Object a;
    private final t b;
    
    cw(final t b, final Object a) {
        this.b = b;
        this.a = a;
    }
    
    public final void run() {
        if (this.b.c != null) {
            this.b.c.update(this.b.b, this.a);
        }
    }
}
