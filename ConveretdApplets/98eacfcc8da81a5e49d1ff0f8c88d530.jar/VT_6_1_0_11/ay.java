// 
// Decompiled by Procyon v0.5.30
// 

package VT_6_1_0_11;

final class ay implements Runnable
{
    private final al a;
    
    ay(final al a) {
        this.a = a;
    }
    
    public final void run() {
        al.a(this.a).selectAll();
    }
}
