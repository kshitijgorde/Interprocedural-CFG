// 
// Decompiled by Procyon v0.5.30
// 

final class Class325
{
    static int[] anIntArray2726;
    static int anInt2727;
    private int anInt2728;
    static int anInt2729;
    int anInt2730;
    
    final void method3695(final int n) {
        try {
            this.anInt2730 &= 0x3FFF;
            if (n != -7741) {
                this.method3699(-29, -15, 43, 76);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ua.A(" + n + ')');
        }
    }
    
    static final void method3696(final byte b, final int anInt5117, final int anInt5118, final int n, final String aString5121, final int anInt5119, final int anInt5120, final int anInt5121) {
        try {
            final Class246_Sub7 class246_Sub7 = new Class246_Sub7();
            class246_Sub7.anInt5118 = Class215.anInt1614 - -n;
            class246_Sub7.anInt5116 = anInt5121;
            class246_Sub7.anInt5123 = anInt5118;
            class246_Sub7.aString5121 = aString5121;
            class246_Sub7.anInt5117 = anInt5117;
            class246_Sub7.anInt5120 = anInt5120;
            class246_Sub7.anInt5122 = anInt5119;
            Class64_Sub20.aClass218_3694.method2808(true, class246_Sub7);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ua.F(" + b + ',' + anInt5117 + ',' + anInt5118 + ',' + n + ',' + ((aString5121 != null) ? "{...}" : "null") + ',' + anInt5119 + ',' + anInt5120 + ',' + anInt5121 + ')');
        }
    }
    
    final void method3697(final boolean b, final int anInt2730) {
        try {
            this.anInt2730 = anInt2730;
            this.anInt2728 = 0;
            if (!b) {
                this.method3699(3, -90, 0, -103);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ua.C(" + b + ',' + anInt2730 + ')');
        }
    }
    
    final int method3698(final byte b) {
        try {
            if (b != 116) {
                this.anInt2730 = 87;
            }
            return this.anInt2730 & 0x3FFF;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ua.D(" + b + ')');
        }
    }
    
    final boolean method3699(final int n, final int anInt2728, final int anInt2729, final int n2) {
        try {
            final int anInt2730 = this.anInt2728;
            if (anInt2729 == this.anInt2730 && this.anInt2728 == 0) {
                return false;
            }
            boolean b;
            if (~this.anInt2728 == -1) {
                if ((this.anInt2730 < anInt2729 && this.anInt2730 - -n2 >= anInt2729) || (anInt2729 < this.anInt2730 && anInt2729 >= this.anInt2730 - n2)) {
                    this.anInt2730 = anInt2729;
                    return false;
                }
                b = true;
            }
            else if (this.anInt2728 <= 0 || ~this.anInt2730 <= ~anInt2729) {
                if (~this.anInt2728 > -1 && this.anInt2730 > anInt2729) {
                    final int n3 = this.anInt2730 + -(this.anInt2728 * this.anInt2728 / (n2 * 2));
                    b = (anInt2729 < n3 && n3 <= this.anInt2730);
                }
                else {
                    b = false;
                }
            }
            else {
                final int n4 = this.anInt2730 + this.anInt2728 * this.anInt2728 / (2 * n2);
                b = (~anInt2729 < ~n4 && ~this.anInt2730 >= ~n4);
            }
            if (n != 4201) {
                this.anInt2730 = -82;
            }
            if (!b) {
                if (~this.anInt2728 < -1) {
                    this.anInt2728 -= n2;
                    if (~this.anInt2728 > -1) {
                        this.anInt2728 = 0;
                    }
                }
                else {
                    this.anInt2728 += n2;
                    if (this.anInt2728 > 0) {
                        this.anInt2728 = 0;
                    }
                }
            }
            else {
                if (~anInt2729 < ~this.anInt2730) {
                    this.anInt2728 += n2;
                    if (anInt2728 != 0 && ~anInt2728 > ~this.anInt2728) {
                        this.anInt2728 = anInt2728;
                    }
                }
                else {
                    this.anInt2728 -= n2;
                    if (anInt2728 != 0 && -anInt2728 > this.anInt2728) {
                        this.anInt2728 = -anInt2728;
                    }
                }
                if (~this.anInt2728 != ~anInt2730) {
                    final int n5 = this.anInt2728 * this.anInt2728 / (n2 * 2);
                    if (this.anInt2730 >= anInt2729) {
                        if (anInt2729 < this.anInt2730 && anInt2729 > -n5 + this.anInt2730) {
                            this.anInt2728 = anInt2730;
                        }
                    }
                    else if (~anInt2729 > ~(n5 + this.anInt2730)) {
                        this.anInt2728 = anInt2730;
                    }
                }
            }
            this.anInt2730 += anInt2730 + this.anInt2728 >> -1761865567;
            return b;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ua.B(" + n + ',' + anInt2728 + ',' + anInt2729 + ',' + n2 + ')');
        }
    }
    
    public static void method3700(final int n) {
        try {
            if (n != 2) {
                Class325.anIntArray2726 = null;
            }
            Class325.anIntArray2726 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ua.E(" + n + ')');
        }
    }
    
    static {
        Class325.anIntArray2726 = new int[1024];
        Class325.anInt2729 = -1;
        Class325.anInt2727 = 1338;
    }
}
