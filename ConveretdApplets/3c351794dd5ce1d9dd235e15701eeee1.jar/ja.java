// 
// Decompiled by Procyon v0.5.30
// 

final class ja extends Class111 implements Interface9
{
    long nativeid;
    
    @Override
    final void method2099(final int n, final int n2, final int n3, final int[] array) {
        this.XA(this.nativeid, n, n2, n3, array);
    }
    
    @Override
    final void method2100(final int n, final int n2, final int n3) {
        this.FA(this.nativeid, n, n2, n3);
    }
    
    private final native void l(final long p0, final long p1);
    
    private final native void a(final long p0, final int p1, final int p2, final int p3);
    
    @Override
    final void method2107(final int n) {
        this.VA(this.nativeid, n);
    }
    
    @Override
    final void method2090(final int n) {
        this.za(this.nativeid, n);
    }
    
    private final native void XA(final long p0, final int p1, final int p2, final int p3, final int[] p4);
    
    private final native void b(final long p0, final int p1, final int p2, final int p3, final int[] p4);
    
    private final native void FA(final long p0, final int p1, final int p2, final int p3);
    
    @Override
    final void method2093(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        this.P(this.nativeid, n, n2, n3, n4, n5, n6);
    }
    
    @Override
    protected final void finalize() {
        if (this.nativeid != 0L) {
            Class192.method2654(false, this);
        }
    }
    
    @Override
    final void method2103(final int n, final int n2, final int n3, final int[] array) {
        this.b(this.nativeid, n, n2, n3, array);
    }
    
    @Override
    final void method2092(final Class111 class111) {
        this.l(this.nativeid, ((ja)class111).nativeid);
    }
    
    @Override
    final void method2101(final int n) {
        this.t(this.nativeid, n);
    }
    
    private final native void m(final long p0, final int p1);
    
    @Override
    final void method2097(final int n) {
        this.m(this.nativeid, n);
    }
    
    private final native void AA(final long p0, final boolean p1);
    
    private final native void VA(final long p0, final int p1);
    
    @Override
    final void method2108(final int[] array) {
        this.w(this.nativeid, array);
    }
    
    private final native void va(final long p0, final int p1, final int p2, final int p3, final int[] p4);
    
    @Override
    final void method2104(final int n) {
        this.NA(this.nativeid, n);
    }
    
    @Override
    public final void w(final boolean b) {
        this.AA(this.nativeid, b);
    }
    
    private final native void t(final long p0, final int p1);
    
    private final native void NA(final long p0, final int p1);
    
    private final native void J(final long p0, final int p1);
    
    private final native void la();
    
    private final native void P(final long p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
    
    @Override
    final void method2106(final int n, final int n2, final int n3) {
        this.a(this.nativeid, n, n2, n3);
    }
    
    @Override
    final Class111 method2102() {
        final ja ja = new ja();
        ja.method2092(this);
        return ja;
    }
    
    private final native void za(final long p0, final int p1);
    
    @Override
    final void method2091() {
        this.u(this.nativeid);
    }
    
    @Override
    final void method2105(final int n) {
        this.J(this.nativeid, n);
    }
    
    private final native void u(final long p0);
    
    private final native void w(final long p0, final int[] p1);
    
    @Override
    final void method2096(final int n, final int n2, final int n3, final int[] array) {
        this.va(this.nativeid, n, n2, n3, array);
    }
    
    ja() {
        this.la();
    }
}
