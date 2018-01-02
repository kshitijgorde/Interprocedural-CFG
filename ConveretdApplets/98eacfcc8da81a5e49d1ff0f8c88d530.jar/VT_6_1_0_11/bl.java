// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

final class bl implements Runnable
{
    private ai a;
    private Object b;
    
    bl(final aD ad, final ai a, final Object b) {
        this.a = a;
        this.b = b;
    }
    
    public final void run() {
        this.a.a(this.b);
    }
}
