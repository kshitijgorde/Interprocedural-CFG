// 
// Decompiled by Procyon v0.5.30
// 

final class xa implements Interface13, Interface9
{
    long nativeid;
    
    private final native void va(final long p0, final boolean p1);
    
    @Override
    protected final void finalize() {
        if (this.nativeid != 0L) {
            Class192.method2654(false, this);
        }
    }
    
    @Override
    public final void w(final boolean b) {
        this.va(this.nativeid, b);
    }
    
    private final native void r(final int p0, final int p1);
    
    xa(final int n, final int n2) {
        this.nativeid = 0L;
        this.r(n, n2);
    }
}
