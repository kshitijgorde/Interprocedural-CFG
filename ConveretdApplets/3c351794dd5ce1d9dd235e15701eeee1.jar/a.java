// 
// Decompiled by Procyon v0.5.30
// 

final class a implements Interface9
{
    private i anI3280;
    Runnable aRunnable3281;
    private i anI3282;
    private i anI3283;
    private i anI3284;
    long nativeid;
    private i anI3285;
    private i anI3286;
    private i anI3287;
    private i anI3288;
    private i anI3289;
    private oa anOa3290;
    private i anI3291;
    
    private final native void f(final long p0, final long p1, final long p2, final int[] p3, final int p4, final int p5);
    
    @Override
    protected final void finalize() {
        if (this.nativeid != 0L) {
            Class192.method2654(false, this);
        }
    }
    
    private final native void UA(final long p0, final long p1, final long p2, final int[] p3, final int p4);
    
    private final void method135() {
        this.M(this.nativeid);
    }
    
    final void method136(final s s, final int n, final int n2, final int n3) {
        this.Z(this.nativeid, ((t)s).nativeid, n, n2, n3);
    }
    
    final Class146 method137(final i i, final byte b, final int n, final boolean b2) {
        i j;
        i k;
        if (b == 1) {
            j = this.anI3287;
            k = this.anI3285;
        }
        else if (b == 2) {
            j = this.anI3289;
            k = this.anI3286;
        }
        else if (b == 3) {
            j = this.anI3280;
            k = this.anI3288;
        }
        else if (b == 4) {
            j = this.anI3284;
            k = this.anI3291;
        }
        else if (b == 5) {
            j = this.anI3283;
            k = this.anI3282;
        }
        else {
            j = (k = new i(this.anOa3290));
        }
        i.ZA(k, j, n, b != 0, b2);
        k.aClass87Array3294 = i.aClass87Array3294;
        k.aClass35Array3295 = i.aClass35Array3295;
        return k;
    }
    
    private final native void ta(final long p0, final long p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final boolean[][] p9);
    
    final void method138(final ha ha, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        this.na(this.nativeid, ha, n, n2, n3, n4, n5, n6, n7);
    }
    
    private final native void HA(final long p0, final ha p1, final int p2, final int p3);
    
    private final native void Z(final long p0, final long p1, final int p2, final int p3, final int p4);
    
    final void method139() {
        this.aRunnable3281 = null;
        this.method144();
    }
    
    final void method140(final Class146 class146, final Class146 class147, final int n, final int n2, final int n3, final boolean b) {
        this.r(this.nativeid, ((i)class146).nativeid, ((i)class147).nativeid, n, n2, n3, b);
    }
    
    private final native boolean n(final long p0, final long p1, final int p2, final int p3, final long p4, final boolean p5, final int p6);
    
    final void method141() {
        this.aRunnable3281 = Thread.currentThread();
        this.method135();
    }
    
    final void method142(final ha ha, final int[] array, final int[] array2, final int[] array3, final short[] array4, final int n) {
        this.O(this.nativeid, ha, array, array2, array3, array4, n);
    }
    
    private final native void O(final long p0, final ha p1, final int[] p2, final int[] p3, final int[] p4, final short[] p5, final int p6);
    
    private final native void M(final long p0);
    
    private final native void na(final long p0, final ha p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8);
    
    final void method143(final Class146 class146, final int[] array, final Class111 class147) {
        this.e(this.nativeid, ((i)class146).nativeid, array, ((ja)class147).nativeid);
    }
    
    private final void method144() {
        this.W(this.nativeid);
    }
    
    @Override
    public final void w(final boolean b) {
        this.E(this.nativeid, b);
    }
    
    private final native void e(final long p0, final long p1, final int[] p2, final long p3);
    
    final void method145(final s s, final int n, final int n2) {
        this.H(this.nativeid, ((t)s).nativeid, n, n2);
    }
    
    private final native void r(final long p0, final long p1, final long p2, final int p3, final int p4, final int p5, final boolean p6);
    
    private final native void W(final long p0);
    
    final void method146(final Class146 class146, final Class111 class147, final int[] array, final int n, final int n2) {
        this.f(this.nativeid, ((i)class146).nativeid, ((ja)class147).nativeid, array, n, n2);
    }
    
    private final native void E(final long p0, final boolean p1);
    
    private final native void H(final long p0, final long p1, final int p2, final int p3);
    
    private final native boolean R(final long p0, final long p1, final int p2, final int p3, final long p4, final boolean p5);
    
    final boolean method147(final Class146 class146, final int n, final int n2, final Class111 class147, final boolean b, final int n3) {
        return this.n(this.nativeid, ((i)class146).nativeid, n, n2, ((ja)class147).nativeid, b, n3);
    }
    
    final void method148(final s s, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final boolean[][] array) {
        this.ta(this.nativeid, ((t)s).nativeid, n, n2, n3, n4, n5, n6, n7, array);
    }
    
    final void method149(final Class146 class146, final Class111 class147, final int[] array, final int n) {
        this.UA(this.nativeid, ((i)class146).nativeid, ((ja)class147).nativeid, array, n);
    }
    
    final boolean method150(final Class146 class146, final int n, final int n2, final Class111 class147, final boolean b) {
        return this.R(this.nativeid, ((i)class146).nativeid, n, n2, ((ja)class147).nativeid, b);
    }
    
    a(final oa anOa3290, final int n, final int n2) {
        this.anOa3290 = anOa3290;
        this.anI3285 = new i(this.anOa3290);
        this.anI3286 = new i(this.anOa3290);
        this.anI3288 = new i(this.anOa3290);
        this.anI3291 = new i(this.anOa3290);
        this.anI3282 = new i(this.anOa3290);
        this.anI3287 = new i(this.anOa3290);
        this.anI3289 = new i(this.anOa3290);
        this.anI3280 = new i(this.anOa3290);
        this.anI3284 = new i(this.anOa3290);
        this.anI3283 = new i(this.anOa3290);
        this.HA(this.nativeid, anOa3290, n, n2);
    }
}
