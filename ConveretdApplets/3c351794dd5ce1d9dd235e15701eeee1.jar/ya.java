// 
// Decompiled by Procyon v0.5.30
// 

final class ya extends za implements Interface9
{
    long nativeid;
    
    final native void ga();
    
    @Override
    protected final void finalize() {
        if (this.nativeid != 0L) {
            Class192.method2654(false, this);
        }
    }
    
    final native void r();
    
    @Override
    public final native void w(final boolean p0);
    
    private final native void aa(final oa p0, final int p1);
    
    ya(final oa oa, final int n) {
        this.aa(oa, n);
    }
}
