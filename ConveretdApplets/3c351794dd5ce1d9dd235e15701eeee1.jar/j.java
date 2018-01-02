// 
// Decompiled by Procyon v0.5.30
// 

final class j extends Class332 implements Interface9
{
    long nativeid;
    
    private final native void b(final long p0, final float p1, final float p2, final float p3, final float p4, final float p5, final float p6, final int p7, final int p8, final int p9, final int p10);
    
    private final native void ma(final oa p0, final int[] p1, final byte[] p2, final byte[] p3, final int p4, final int p5, final int p6, final int p7);
    
    @Override
    final int method3737() {
        return this.wa(this.nativeid);
    }
    
    @Override
    final void method3740(final int n, final int n2, final int n3, final int n4) {
        this.A(this.nativeid, n, n2, n3, n4);
    }
    
    private final native void P(final long p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7);
    
    private final native void R(final long p0, final boolean p1);
    
    private final native int M(final long p0);
    
    @Override
    final void method3728(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        this.P(this.nativeid, n, n2, n3, n4, n5, n6, n7);
    }
    
    private final native void h(final oa p0, final int p1, final int p2, final int p3, final int p4, final boolean p5);
    
    @Override
    final int method3734() {
        return this.M(this.nativeid);
    }
    
    @Override
    final int method3731() {
        return this.I(this.nativeid);
    }
    
    @Override
    final void method3741(final int[] array) {
        this.CA(this.nativeid, array);
    }
    
    private final native int I(final long p0);
    
    private final native void W(final long p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    @Override
    public final void w(final boolean b) {
        this.R(this.nativeid, b);
    }
    
    j(final oa oa, final int n, final int n2) {
        this.EA(oa, n, n2);
    }
    
    @Override
    final void method3742(final int n, final int n2, final int n3) {
        this.N(this.nativeid, n, n2, n3);
    }
    
    @Override
    final void method3747(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7, final aa aa, final int n8, final int n9) {
        this.UA(this.nativeid, n, n2, n3, n4, n5, n6, n7, ((na)aa).nativeid, n8, n9);
    }
    
    private final native void CA(final long p0, final int[] p1);
    
    private final native void EA(final oa p0, final int p1, final int p2);
    
    private final native int JA(final long p0);
    
    j(final oa oa, final int[] array, final int n, final int n2, final int n3, final int n4, final boolean b) {
        this.ua(oa, array, n, n2, n3, n4, b);
    }
    
    private final native void UA(final long p0, final float p1, final float p2, final float p3, final float p4, final float p5, final float p6, final int p7, final long p8, final int p9, final int p10);
    
    @Override
    final void method3733(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7, final int n8, final int n9, final int n10) {
        this.b(this.nativeid, n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
    }
    
    @Override
    protected final void finalize() {
        if (this.nativeid != 0L) {
            Class192.method2654(false, this);
        }
    }
    
    j(final oa oa, final int[] array, final byte[] array2, final byte[] array3, final int n, final int n2, final int n3, final int n4) {
        this.ma(oa, array, array2, array3, n, n2, n3, n4);
    }
    
    private final native void YA(final long p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
    
    @Override
    final void method3736(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.YA(this.nativeid, n, n2, n3, n4, n5, n6);
    }
    
    @Override
    final void method3748(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.W(this.nativeid, n, n2, n3, n4, n5);
    }
    
    private final native void ua(final oa p0, final int[] p1, final int p2, final int p3, final int p4, final int p5, final boolean p6);
    
    private final native void N(final long p0, final int p1, final int p2, final int p3);
    
    @Override
    final void method3729(final int n, final int n2, final aa aa, final int n3, final int n4) {
        this.V(this.nativeid, n, n2, ((na)aa).nativeid, n3, n4);
    }
    
    private final native void V(final long p0, final int p1, final int p2, final long p3, final int p4, final int p5);
    
    private final native void A(final long p0, final int p1, final int p2, final int p3, final int p4);
    
    private final native int wa(final long p0);
    
    j(final oa oa, final int n, final int n2, final int n3, final int n4, final boolean b) {
        this.h(oa, n, n2, n3, n4, b);
    }
    
    private final native void RA(final long p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8);
    
    @Override
    final void method3745(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.RA(this.nativeid, n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    @Override
    final int method3749() {
        return this.JA(this.nativeid);
    }
}
