// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class332 implements Interface5
{
    private final void method3725(final float n, final float n2, final float n3, final float n4, final int n5, final int n6, final int n7, final int n8, final int n9) {
        if (n5 != 0) {
            final double n10 = (n6 & 0xFFFF) * 9.587379924285257E-5;
            final float n11 = (float)Math.sin(n10) * n5;
            final float n12 = (float)Math.cos(n10) * n5;
            this.method3732((-n3 * n12 + -n4 * n11) / 4096.0f + n, (n3 * n11 + -n4 * n12) / 4096.0f + n2, ((this.method3737() - n3) * n12 + -n4 * n11) / 4096.0f + n, (-(this.method3737() - n3) * n11 + -n4 * n12) / 4096.0f + n2, (-n3 * n12 + (this.method3749() - n4) * n11) / 4096.0f + n, (n3 * n11 + (this.method3749() - n4) * n12) / 4096.0f + n2, n7, n8, n9);
        }
    }
    
    final void method3726(final int n, final int n2, final int n3, final int n4) {
        this.method3745(n, n2, n3, n4, 1, 0, 1, 1);
    }
    
    final void method3727(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        this.method3745(n, n2, n3, n4, n5, n6, n7, 1);
    }
    
    abstract void method3728(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6);
    
    abstract void method3729(final int p0, final int p1, final aa p2, final int p3, final int p4);
    
    final void method3730(final float n, final float n2, final int n3, final int n4) {
        this.method3725(n, n2, this.method3737() / 2.0f, this.method3749() / 2.0f, n3, n4, 1, 0, 1);
    }
    
    abstract int method3731();
    
    private final void method3732(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7, final int n8, final int n9) {
        this.method3733(n, n2, n3, n4, n5, n6, n7, n8, n9, 1);
    }
    
    abstract void method3733(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5, final int p6, final int p7, final int p8, final int p9);
    
    abstract int method3734();
    
    final void method3735(final int n, final int n2) {
        this.method3748(n, n2, 1, 0, 1);
    }
    
    abstract void method3736(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5);
    
    abstract int method3737();
    
    final void method3738(final int n, final int n2, final int n3, final int n4) {
        this.method3728(n, n2, n3, n4, 1, 0, 1);
    }
    
    final void method3739(final float n, final float n2, final int n3, final int n4, final aa aa, final int n5, final int n6) {
        this.method3746(n, n2, this.method3737() / 2.0f, this.method3749() / 2.0f, n3, n4, aa, n5, n6);
    }
    
    abstract void method3740(final int p0, final int p1, final int p2, final int p3);
    
    abstract void method3741(final int[] p0);
    
    abstract void method3742(final int p0, final int p1, final int p2);
    
    final void method3743(final float n, final float n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        this.method3725(n, n2, this.method3737() / 2.0f, this.method3749() / 2.0f, n3, n4, n5, n6, n7);
    }
    
    private final void method3744(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final aa aa, final int n7, final int n8) {
        this.method3747(n, n2, n3, n4, n5, n6, 1, aa, n7, n8);
    }
    
    abstract void method3745(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7);
    
    final void method3746(final float n, final float n2, final float n3, final float n4, final int n5, final int n6, final aa aa, final int n7, final int n8) {
        if (n5 != 0) {
            final double n9 = (n6 & 0xFFFF) * 9.587379924285257E-5;
            final float n10 = (float)Math.sin(n9) * n5;
            final float n11 = (float)Math.cos(n9) * n5;
            this.method3744((-n3 * n11 + -n4 * n10) / 4096.0f + n, (n3 * n10 + -n4 * n11) / 4096.0f + n2, ((this.method3737() - n3) * n11 + -n4 * n10) / 4096.0f + n, (-(this.method3737() - n3) * n10 + -n4 * n11) / 4096.0f + n2, (-n3 * n11 + (this.method3749() - n4) * n10) / 4096.0f + n, (n3 * n10 + (this.method3749() - n4) * n11) / 4096.0f + n2, aa, n7, n8);
        }
    }
    
    abstract void method3747(final float p0, final float p1, final float p2, final float p3, final float p4, final float p5, final int p6, final aa p7, final int p8, final int p9);
    
    abstract void method3748(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    abstract int method3749();
}
