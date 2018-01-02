// 
// Decompiled by Procyon v0.5.30
// 

final class na extends aa implements Interface9
{
    long nativeid;
    
    private final native void ma(final oa p0, final ya p1, final int p2, final int p3, final int[] p4, final int[] p5);
    
    @Override
    protected final void finalize() {
        if (this.nativeid != 0L) {
            Class192.method2654(false, this);
        }
    }
    
    na(final oa oa, final ya ya, final int n, final int n2, final int[] array, final int[] array2) {
        this.ma(oa, ya, n, n2, array, array2);
    }
    
    @Override
    public final native void w(final boolean p0);
}
