// 
// Decompiled by Procyon v0.5.30
// 

final class Class119_Sub4 extends Class119
{
    private int anInt4736;
    private int anInt4737;
    private int anInt4738;
    static Class332[] aClass332Array4739;
    static float aFloat4740;
    private int anInt4741;
    
    @Override
    final void method2178(final int n, final int n2, final int n3) {
        try {
            Class98_Sub47.method1658(this.anInt4737 * n3 >> -444889972, n2 * this.anInt4738 >> -282666356, n2 * this.anInt4736 >> 151121132, 16977, n3 * this.anInt4741 >> 150376812, super.anInt988);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sr.B(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method2174(final int n, final int n2, final int n3) {
        try {
            final int n4 = n * this.anInt4738 >> 263929676;
            final int n5 = n * this.anInt4736 >> -496298516;
            if (n3 == -5515) {
                za.method1675(n5, super.anInt987, this.anInt4741 * n2 >> 226072012, this.anInt4737 * n2 >> -1264175348, super.anInt985, n4, super.anInt988, (byte)(-89));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sr.C(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final float method2188(final float n, final int n2) {
        try {
            if (n2 != 1024) {
                method2190(-15, -6);
            }
            return n * n * n * (10.0f + n * (6.0f * n - 15.0f));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sr.F(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method2189(final byte b) {
        try {
            Class119_Sub4.aClass332Array4739 = null;
            if (b != -1) {
                method2190(-110, 15);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sr.D(" + b + ')');
        }
    }
    
    Class119_Sub4(final int anInt4738, final int anInt4739, final int anInt4740, final int anInt4741, final int n, final int n2, final int n3) {
        super(n, n2, n3);
        try {
            this.anInt4738 = anInt4738;
            this.anInt4741 = anInt4741;
            this.anInt4736 = anInt4740;
            this.anInt4737 = anInt4739;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sr.<init>(" + anInt4738 + ',' + anInt4739 + ',' + anInt4740 + ',' + anInt4741 + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method2179(final byte b, final int n, final int n2) {
    }
    
    static final void method2190(final int n, final int anInt1945) {
        try {
            Class256.anInt1945 = anInt1945;
            Class98_Sub5_Sub2.anInt5536 = -1;
            Class64_Sub25.anInt3711 = 3;
            Class287.anInt2186 = 100;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sr.A(" + n + ',' + anInt1945 + ')');
        }
    }
    
    static {
        Class119_Sub4.aFloat4740 = 1024.0f;
    }
}
