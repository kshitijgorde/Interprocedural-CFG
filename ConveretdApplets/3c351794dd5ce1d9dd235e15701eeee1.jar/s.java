// 
// Decompiled by Procyon v0.5.30
// 

abstract class s
{
    int anInt2200;
    int[][] anIntArrayArray2201;
    static String aString2202;
    int anInt2203;
    int anInt2204;
    static int[] anIntArray2205;
    int anInt2206;
    
    abstract void method3416(final int p0, final int p1, final int p2, final boolean[][] p3, final boolean p4, final int p5, final int p6);
    
    final int method3417(final int n, final int n2, final boolean b) {
        try {
            final int n3 = n >> this.anInt2200;
            if (!b) {
                return -46;
            }
            final int n4 = n2 >> this.anInt2200;
            if (~n3 > -1 || n4 < 0 || this.anInt2203 - 1 < n3 || n4 > -1 + this.anInt2204) {
                return 0;
            }
            final int n5 = n & -1 + this.anInt2206;
            final int n6 = n2 & this.anInt2206 - 1;
            return n6 * ((-n5 + this.anInt2206) * this.anIntArrayArray2201[n3][1 + n4] - -(this.anIntArrayArray2201[n3 + 1][1 + n4] * n5) >> this.anInt2200) + (this.anInt2206 - n6) * ((-n5 + this.anInt2206) * this.anIntArrayArray2201[n3][n4] + n5 * this.anIntArrayArray2201[1 + n3][n4] >> this.anInt2200) >> this.anInt2200;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "s.R(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    abstract boolean method3418(final r p0, final int p1, final int p2, final int p3, final int p4, final boolean p5);
    
    static final boolean method3419(final int n, final int n2) {
        try {
            return ~n2 <= -5 && ~n2 >= -9;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "s.P(" + n + ',' + n2 + ')');
        }
    }
    
    final int method3420(final int n, final int n2, final int n3) {
        try {
            if (n2 != -12639) {
                this.method3425(110, 4);
            }
            return this.anIntArrayArray2201[n3][n];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "s.Q(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    abstract void U(final int p0, final int p1, final int[] p2, final int[] p3, final int[] p4, final int[] p5, final int[] p6, final int[] p7, final int[] p8, final int[] p9, final int p10, final int p11, final int p12, final boolean p13);
    
    abstract void method3421(final Class98_Sub5 p0, final int[] p1);
    
    abstract void method3422(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final boolean[][] p7);
    
    abstract void wa(final r p0, final int p1, final int p2, final int p3, final int p4, final boolean p5);
    
    abstract void YA();
    
    public static void method3423(final boolean b) {
        try {
            if (!b) {
                s.aString2202 = null;
            }
            s.aString2202 = null;
            s.anIntArray2205 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "s.S(" + b + ')');
        }
    }
    
    abstract void CA(final r p0, final int p1, final int p2, final int p3, final int p4, final boolean p5);
    
    abstract void method3424(final int p0, final int p1, final int[] p2, final int[] p3, final int[] p4, final int[] p5, final int[] p6, final int[] p7, final int[] p8, final int[] p9, final int[] p10, final int[] p11, final int[] p12, final int p13, final int p14, final int p15, final boolean p16);
    
    abstract void method3425(final int p0, final int p1);
    
    abstract r fa(final int p0, final int p1, final r p2);
    
    s(final int anInt2203, final int anInt2204, int i, final int[][] anIntArrayArray2201) {
        try {
            this.anInt2203 = anInt2203;
            this.anInt2204 = anInt2204;
            int anInt2205 = 0;
            while (i > 1) {
                ++anInt2205;
                i >>= 1;
            }
            this.anInt2206 = 1 << anInt2205;
            this.anIntArrayArray2201 = anIntArrayArray2201;
            this.anInt2200 = anInt2205;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "s.<init>(" + anInt2203 + ',' + anInt2204 + ',' + i + ',' + ((anIntArrayArray2201 != null) ? "{...}" : "null") + ')');
        }
    }
    
    abstract void method3426(final int p0, final int p1, final int p2, final boolean[][] p3, final boolean p4, final int p5);
    
    abstract void ka(final int p0, final int p1, final int p2);
    
    static {
        s.aString2202 = null;
    }
}
