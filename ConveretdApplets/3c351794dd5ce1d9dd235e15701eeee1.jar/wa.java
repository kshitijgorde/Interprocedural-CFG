// 
// Decompiled by Procyon v0.5.30
// 

final class wa implements Interface17
{
    private long aLong3434;
    j aJ3435;
    xa aXa3436;
    
    @Override
    public final void method56(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2) {
        this.X(this.aLong3434, this.aJ3435.nativeid, this.aXa3436.nativeid, n, n2, n3, n4, n5, n6, b, b2);
    }
    
    private final native void Z(final long p0, final long p1, final long p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final boolean p9, final boolean p10);
    
    private final native void X(final long p0, final long p1, final long p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final boolean p9, final boolean p10);
    
    wa(final oa oa, final j aj3435, final xa aXa3436) {
        this.aLong3434 = 0L;
        this.aLong3434 = oa.nativeid;
        this.aJ3435 = aj3435;
        this.aXa3436 = aXa3436;
    }
    
    @Override
    public final void method57(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b, final boolean b2) {
        this.Z(this.aLong3434, this.aJ3435.nativeid, this.aXa3436.nativeid, n, n2, n3, n4, n5, n6, b, b2);
    }
}
