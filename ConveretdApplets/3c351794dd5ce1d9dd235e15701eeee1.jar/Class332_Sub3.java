// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class332_Sub3 extends Class332
{
    static int anInt5425;
    private static int anInt5426;
    static int anInt5427;
    static int anInt5428;
    private static int anInt5429;
    private static int anInt5430;
    static int anInt5431;
    static int anInt5432;
    int anInt5433;
    ha_Sub2 aHa_Sub2_5434;
    static int anInt5435;
    static int anInt5436;
    private int[] anIntArray5437;
    static int anInt5438;
    int anInt5439;
    static int anInt5440;
    static int anInt5441;
    static int anInt5442;
    static int anInt5443;
    static int anInt5444;
    private static int anInt5445;
    int anInt5446;
    int anInt5447;
    static int anInt5448;
    static int anInt5449;
    static int anInt5450;
    static int anInt5451;
    private static int anInt5452;
    private static int anInt5453;
    int anInt5454;
    int anInt5455;
    static int anInt5456;
    static int anInt5457;
    
    abstract void method3757(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8);
    
    abstract void method3758(final int[] p0, final int[] p1, final int p2, final int p3);
    
    abstract void method3759(final int p0, final int p1);
    
    @Override
    final int method3737() {
        return this.anInt5446 + this.anInt5433 + this.anInt5455;
    }
    
    private final boolean method3760(float n, float n2, float n3, float n4, float n5, float n6) {
        final int n7 = this.anInt5446 + this.anInt5433 + this.anInt5455;
        final int n8 = this.anInt5439 + this.anInt5454 + this.anInt5447;
        if (n7 != this.anInt5433 || n8 != this.anInt5454) {
            final float n9 = (n3 - n) / n7;
            final float n10 = (n4 - n2) / n7;
            final float n11 = (n5 - n) / n8;
            final float n12 = (n6 - n2) / n8;
            final float n13 = n11 * this.anInt5439;
            final float n14 = n12 * this.anInt5439;
            final float n15 = n9 * this.anInt5446;
            final float n16 = n10 * this.anInt5446;
            final float n17 = -n9 * this.anInt5455;
            final float n18 = -n10 * this.anInt5455;
            final float n19 = -n11 * this.anInt5447;
            final float n20 = -n12 * this.anInt5447;
            n += n15 + n13;
            n2 += n16 + n14;
            n3 += n17 + n13;
            n4 += n18 + n14;
            n5 += n15 + n19;
            n6 += n16 + n20;
        }
        final float n21 = n5 + (n3 - n);
        final float n22 = n4 + (n6 - n2);
        float n23;
        float n24;
        if (n < n3) {
            n23 = n;
            n24 = n3;
        }
        else {
            n23 = n3;
            n24 = n;
        }
        if (n5 < n23) {
            n23 = n5;
        }
        if (n21 < n23) {
            n23 = n21;
        }
        if (n5 > n24) {
            n24 = n5;
        }
        if (n21 > n24) {
            n24 = n21;
        }
        float n25;
        float n26;
        if (n2 < n4) {
            n25 = n2;
            n26 = n4;
        }
        else {
            n25 = n4;
            n26 = n2;
        }
        if (n6 < n25) {
            n25 = n6;
        }
        if (n22 < n25) {
            n25 = n22;
        }
        if (n6 > n26) {
            n26 = n6;
        }
        if (n22 > n26) {
            n26 = n22;
        }
        if (n23 < this.aHa_Sub2_5434.anInt4509) {
            n23 = this.aHa_Sub2_5434.anInt4509;
        }
        if (n24 > this.aHa_Sub2_5434.anInt4507) {
            n24 = this.aHa_Sub2_5434.anInt4507;
        }
        if (n25 < this.aHa_Sub2_5434.anInt4495) {
            n25 = this.aHa_Sub2_5434.anInt4495;
        }
        if (n26 > this.aHa_Sub2_5434.anInt4492) {
            n26 = this.aHa_Sub2_5434.anInt4492;
        }
        final float n27 = n23 - n24;
        if (n27 >= 0.0f) {
            return false;
        }
        final float n28 = n25 - n26;
        if (n28 >= 0.0f) {
            return false;
        }
        Class332_Sub3.anInt5438 = this.aHa_Sub2_5434.anInt4505;
        Class332_Sub3.anInt5451 = (int)((int)n25 * Class332_Sub3.anInt5438 + n23);
        final float n29 = (n3 - n) * (n6 - n2) - (n4 - n2) * (n5 - n);
        final float n30 = (n5 - n) * (n4 - n2) - (n6 - n2) * (n3 - n);
        Class332_Sub3.anInt5444 = (int)((n6 - n2) * 4096.0f * this.anInt5433 / n29);
        Class332_Sub3.anInt5436 = (int)((n4 - n2) * 4096.0f * this.anInt5454 / n30);
        Class332_Sub3.anInt5441 = (int)((n5 - n) * 4096.0f * this.anInt5433 / n30);
        Class332_Sub3.anInt5428 = (int)((n3 - n) * 4096.0f * this.anInt5454 / n29);
        Class332_Sub3.anInt5426 = (int)(n23 * 16.0f + 8.0f - (n + n3 + n5 + n21) / 4.0f * 16.0f);
        Class332_Sub3.anInt5445 = (int)(n25 * 16.0f + 8.0f - (n2 + n4 + n6 + n22) / 4.0f * 16.0f);
        Class332_Sub3.anInt5450 = (this.anInt5433 >> 1 << 12) + (Class332_Sub3.anInt5445 * Class332_Sub3.anInt5441 >> 4);
        Class332_Sub3.anInt5443 = (this.anInt5454 >> 1 << 12) + (Class332_Sub3.anInt5445 * Class332_Sub3.anInt5428 >> 4);
        Class332_Sub3.anInt5425 = Class332_Sub3.anInt5426 * Class332_Sub3.anInt5444 >> 4;
        Class332_Sub3.anInt5456 = Class332_Sub3.anInt5426 * Class332_Sub3.anInt5436 >> 4;
        Class332_Sub3.anInt5453 = (int)n23;
        Class332_Sub3.anInt5448 = (int)n27;
        Class332_Sub3.anInt5429 = (int)n25;
        Class332_Sub3.anInt5431 = (int)n28;
        return true;
    }
    
    @Override
    final int method3731() {
        return this.anInt5454;
    }
    
    @Override
    abstract void method3729(final int p0, final int p1, final aa p2, final int p3, final int p4);
    
    @Override
    final void method3733(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7, final int anInt5432, final int n8, final int n9) {
        if (this.aHa_Sub2_5434.method1920()) {
            throw new IllegalStateException();
        }
        if (this.method3760(n, n2, n3, n4, n5, n6)) {
            Class332_Sub3.anInt5432 = anInt5432;
            if (n7 != 1) {
                Class332_Sub3.anInt5449 = anInt5432 >>> 24;
                Class332_Sub3.anInt5442 = 256 - Class332_Sub3.anInt5449;
                if (n7 == 0) {
                    Class332_Sub3.anInt5435 = (anInt5432 & 0xFF0000) >> 16;
                    Class332_Sub3.anInt5457 = (anInt5432 & 0xFF00) >> 8;
                    Class332_Sub3.anInt5427 = (anInt5432 & 0xFF);
                }
                else if (n7 == 2) {
                    Class332_Sub3.anInt5430 = anInt5432 >>> 24;
                    Class332_Sub3.anInt5452 = 256 - Class332_Sub3.anInt5430;
                    Class332_Sub3.anInt5440 = (((anInt5432 & 0xFF00FF) * Class332_Sub3.anInt5452 & 0xFF00FF00) | ((anInt5432 & 0xFF00) * Class332_Sub3.anInt5452 & 0xFF0000)) >>> 8;
                }
            }
            if (n7 == 1) {
                if (n8 == 0) {
                    this.method3759(1, 0);
                }
                else if (n8 == 1) {
                    this.method3759(1, 1);
                }
                else if (n8 == 2) {
                    this.method3759(1, 2);
                }
            }
            else if (n7 == 0) {
                if (n8 == 0) {
                    this.method3759(0, 0);
                }
                else if (n8 == 1) {
                    this.method3759(0, 1);
                }
                else if (n8 == 2) {
                    this.method3759(0, 2);
                }
            }
            else if (n7 == 3) {
                if (n8 == 0) {
                    this.method3759(3, 0);
                }
                else if (n8 == 1) {
                    this.method3759(3, 1);
                }
                else if (n8 == 2) {
                    this.method3759(3, 2);
                }
            }
            else if (n7 == 2) {
                if (n8 == 0) {
                    this.method3759(2, 0);
                }
                else if (n8 == 1) {
                    this.method3759(2, 1);
                }
                else if (n8 == 2) {
                    this.method3759(2, 2);
                }
            }
        }
    }
    
    @Override
    final int method3749() {
        return this.anInt5439 + this.anInt5454 + this.anInt5447;
    }
    
    @Override
    final void method3728(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        if (this.aHa_Sub2_5434.method1920()) {
            throw new IllegalStateException();
        }
        if (this.anIntArray5437 == null) {
            this.anIntArray5437 = new int[4];
        }
        this.aHa_Sub2_5434.K(this.anIntArray5437);
        this.aHa_Sub2_5434.T(this.aHa_Sub2_5434.anInt4509, this.aHa_Sub2_5434.anInt4495, n + n3, n2 + n4);
        final int method3737 = this.method3737();
        final int method3738 = this.method3749();
        final int n8 = (n3 + method3737 - 1) / method3737;
        for (int n9 = (n4 + method3738 - 1) / method3738, i = 0; i < n9; ++i) {
            final int n10 = i * method3738;
            for (int j = 0; j < n8; ++j) {
                this.method3748(n + j * method3737, n2 + n10, n5, n6, n7);
            }
        }
        this.aHa_Sub2_5434.KA(this.anIntArray5437[0], this.anIntArray5437[1], this.anIntArray5437[2], this.anIntArray5437[3]);
    }
    
    @Override
    abstract void method3745(final int p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7);
    
    @Override
    final void method3747(final float n, final float n2, final float n3, final float n4, final float n5, final float n6, final int n7, final aa aa, final int n8, final int n9) {
        if (this.aHa_Sub2_5434.method1920()) {
            throw new IllegalStateException();
        }
        if (this.method3760(n, n2, n3, n4, n5, n6)) {
            final aa_Sub1 aa_Sub1 = (aa_Sub1)aa;
            this.method3758(aa_Sub1.anIntArray3555, aa_Sub1.anIntArray3557, Class332_Sub3.anInt5453 - n8, -n9 - (Class332_Sub3.anInt5431 - Class332_Sub3.anInt5429));
        }
    }
    
    @Override
    abstract void method3748(final int p0, final int p1, final int p2, final int p3, final int p4);
    
    @Override
    final void method3740(final int anInt5446, final int anInt5447, final int anInt5448, final int anInt5449) {
        this.anInt5446 = anInt5446;
        this.anInt5439 = anInt5447;
        this.anInt5455 = anInt5448;
        this.anInt5447 = anInt5449;
    }
    
    Class332_Sub3(final ha_Sub2 aHa_Sub2_5434, final int anInt5433, final int anInt5434) {
        this.aHa_Sub2_5434 = aHa_Sub2_5434;
        this.anInt5433 = anInt5433;
        this.anInt5454 = anInt5434;
    }
    
    @Override
    final void method3741(final int[] array) {
        array[0] = this.anInt5446;
        array[1] = this.anInt5439;
        array[2] = this.anInt5455;
        array[3] = this.anInt5447;
    }
    
    @Override
    final int method3734() {
        return this.anInt5433;
    }
    
    static {
        Class332_Sub3.anInt5430 = 0;
        Class332_Sub3.anInt5442 = 0;
        Class332_Sub3.anInt5440 = 0;
        Class332_Sub3.anInt5449 = 0;
        Class332_Sub3.anInt5427 = 0;
        Class332_Sub3.anInt5452 = 0;
        Class332_Sub3.anInt5435 = 0;
        Class332_Sub3.anInt5457 = 0;
    }
}
