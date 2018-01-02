// 
// Decompiled by Procyon v0.5.30
// 

final class Class111_Sub1 extends Class111
{
    float aFloat4673;
    float aFloat4674;
    float aFloat4675;
    float aFloat4676;
    float aFloat4677;
    float aFloat4678;
    float aFloat4679;
    float aFloat4680;
    static int[] anIntArray4681;
    static int[] anIntArray4682;
    float aFloat4683;
    float aFloat4684;
    static Class239 aClass239_4685;
    float aFloat4686;
    float aFloat4687;
    
    @Override
    final void method2100(final int n, final int n2, final int n3) {
        try {
            this.aFloat4674 = n;
            this.aFloat4677 = n3;
            this.aFloat4683 = n2;
            final float n4 = 0.0f;
            this.aFloat4687 = n4;
            this.aFloat4680 = n4;
            this.aFloat4676 = n4;
            this.aFloat4679 = n4;
            this.aFloat4684 = n4;
            this.aFloat4678 = n4;
            final float aFloat4686 = 1.0f;
            this.aFloat4673 = aFloat4686;
            this.aFloat4675 = aFloat4686;
            this.aFloat4686 = aFloat4686;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.SA(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    final void method2109(final int n, final float n2, final float n3, final float n4, final float n5, final float[] array) {
        try {
            array[0] = n5 * this.aFloat4679 + n4 * this.aFloat4686 + this.aFloat4680 * n2;
            float n7;
            float n8;
            float n9;
            if (n4 > 0.00390625f || n4 < -0.00390625f) {
                final float n6 = -n3 / n4;
                n7 = this.aFloat4683 + this.aFloat4678 * n6;
                n8 = this.aFloat4677 + this.aFloat4684 * n6;
                n9 = n6 * this.aFloat4686 + this.aFloat4674;
            }
            else if (n5 <= 0.00390625f && n5 >= -0.00390625f) {
                final float n10 = -n3 / n2;
                n7 = n10 * this.aFloat4687 + this.aFloat4683;
                n9 = this.aFloat4674 + this.aFloat4680 * n10;
                n8 = this.aFloat4673 * n10 + this.aFloat4677;
            }
            else {
                final float n11 = -n3 / n5;
                n9 = this.aFloat4674 + n11 * this.aFloat4679;
                n7 = this.aFloat4683 + n11 * this.aFloat4675;
                n8 = n11 * this.aFloat4676 + this.aFloat4677;
            }
            if (n == -29834) {
                array[2] = n2 * this.aFloat4673 + (n4 * this.aFloat4684 + this.aFloat4676 * n5);
                array[1] = this.aFloat4687 * n2 + (n4 * this.aFloat4678 + n5 * this.aFloat4675);
                array[3] = -(array[0] * n9 + n7 * array[1] + n8 * array[2]);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.J(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method2096(int n, int n2, int n3, final int[] array) {
        try {
            n -= (int)this.aFloat4674;
            n2 -= (int)this.aFloat4683;
            n3 -= (int)this.aFloat4677;
            array[1] = (int)(n3 * this.aFloat4676 + (n * this.aFloat4679 + this.aFloat4675 * n2));
            array[0] = (int)(n2 * this.aFloat4678 + n * this.aFloat4686 + this.aFloat4684 * n3);
            array[2] = (int)(n3 * this.aFloat4673 + (n * this.aFloat4680 + n2 * this.aFloat4687));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.PA(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    final void method2110(final float aFloat4683, final int n, final int n2, final float aFloat4684, final int n3, final byte b, final float aFloat4685) {
        try {
            Label_0147: {
                if (n2 != 0) {
                    final float n4 = Class98_Sub10_Sub36.aFloatArray5741[0x3FFF & n2];
                    final float n5 = Class98_Sub10_Sub36.aFloatArray5742[n2 & 0x3FFF];
                    this.aFloat4686 = n4 * n3;
                    this.aFloat4679 = -n5 * n;
                    this.aFloat4678 = n5 * n3;
                    final float n6 = 0.0f;
                    this.aFloat4687 = n6;
                    this.aFloat4680 = n6;
                    this.aFloat4676 = n6;
                    this.aFloat4684 = n6;
                    this.aFloat4675 = n4 * n;
                    this.aFloat4673 = 1.0f;
                    if (!client.aBoolean3553) {
                        break Label_0147;
                    }
                }
                this.aFloat4686 = n3;
                final float n7 = 0.0f;
                this.aFloat4687 = n7;
                this.aFloat4680 = n7;
                this.aFloat4676 = n7;
                this.aFloat4679 = n7;
                this.aFloat4684 = n7;
                this.aFloat4678 = n7;
                this.aFloat4675 = n;
                this.aFloat4673 = 1.0f;
            }
            this.aFloat4683 = aFloat4683;
            this.aFloat4674 = aFloat4685;
            this.aFloat4677 = aFloat4684;
            if (b != 63) {
                this.method2092(null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.K(" + aFloat4683 + ',' + n + ',' + n2 + ',' + aFloat4684 + ',' + n3 + ',' + b + ',' + aFloat4685 + ')');
        }
    }
    
    @Override
    final void method2090(final int n) {
        try {
            final float n2 = Class98_Sub10_Sub36.aFloatArray5741[0x3FFF & n];
            final float n3 = Class98_Sub10_Sub36.aFloatArray5742[0x3FFF & n];
            final float aFloat4686 = this.aFloat4686;
            final float aFloat4687 = this.aFloat4679;
            final float aFloat4688 = this.aFloat4680;
            final float aFloat4689 = this.aFloat4674;
            this.aFloat4686 = n2 * aFloat4686 - n3 * this.aFloat4678;
            this.aFloat4678 = n3 * aFloat4686 + n2 * this.aFloat4678;
            this.aFloat4679 = aFloat4687 * n2 - this.aFloat4675 * n3;
            this.aFloat4680 = aFloat4688 * n2 - n3 * this.aFloat4687;
            this.aFloat4675 = n2 * this.aFloat4675 + aFloat4687 * n3;
            this.aFloat4687 = aFloat4688 * n3 + this.aFloat4687 * n2;
            this.aFloat4674 = n2 * aFloat4689 - n3 * this.aFloat4683;
            this.aFloat4683 = this.aFloat4683 * n2 + aFloat4689 * n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.C(" + n + ')');
        }
    }
    
    @Override
    final void method2093(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            final float n7 = Class98_Sub10_Sub36.aFloatArray5741[0x3FFF & n4];
            final float n8 = Class98_Sub10_Sub36.aFloatArray5742[n4 & 0x3FFF];
            final float n9 = Class98_Sub10_Sub36.aFloatArray5741[n5 & 0x3FFF];
            final float n10 = Class98_Sub10_Sub36.aFloatArray5742[n5 & 0x3FFF];
            final float n11 = Class98_Sub10_Sub36.aFloatArray5741[n6 & 0x3FFF];
            final float n12 = Class98_Sub10_Sub36.aFloatArray5742[0x3FFF & n6];
            final float n13 = n11 * n8;
            final float n14 = n12 * n8;
            this.aFloat4684 = n10 * n7;
            this.aFloat4676 = -n8;
            this.aFloat4679 = n7 * n12;
            this.aFloat4673 = n9 * n7;
            this.aFloat4686 = n11 * n9 + n14 * n10;
            this.aFloat4687 = n12 * n10 + n9 * n13;
            this.aFloat4678 = n10 * n13 + -n9 * n12;
            this.aFloat4680 = -n10 * n11 + n14 * n9;
            this.aFloat4675 = n7 * n11;
            this.aFloat4677 = -(n3 * this.aFloat4673) + (-(n2 * this.aFloat4676) + this.aFloat4684 * -n);
            this.aFloat4683 = -(n3 * this.aFloat4687) + (-(this.aFloat4675 * n2) + this.aFloat4678 * -n);
            this.aFloat4674 = this.aFloat4686 * -n - this.aFloat4679 * n2 - n3 * this.aFloat4680;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.U(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    @Override
    final void method2105(final int n) {
        try {
            final float n2 = Class98_Sub10_Sub36.aFloatArray5741[0x3FFF & n];
            final float n3 = Class98_Sub10_Sub36.aFloatArray5742[n & 0x3FFF];
            final float aFloat4678 = this.aFloat4678;
            final float aFloat4679 = this.aFloat4675;
            final float aFloat4680 = this.aFloat4687;
            final float aFloat4681 = this.aFloat4683;
            this.aFloat4678 = -(n3 * this.aFloat4684) + aFloat4678 * n2;
            this.aFloat4684 = aFloat4678 * n3 + n2 * this.aFloat4684;
            this.aFloat4675 = -(this.aFloat4676 * n3) + n2 * aFloat4679;
            this.aFloat4687 = -(n3 * this.aFloat4673) + aFloat4680 * n2;
            this.aFloat4676 = aFloat4679 * n3 + n2 * this.aFloat4676;
            this.aFloat4683 = aFloat4681 * n2 - n3 * this.aFloat4677;
            this.aFloat4673 = n3 * aFloat4680 + n2 * this.aFloat4673;
            this.aFloat4677 = this.aFloat4677 * n2 + aFloat4681 * n3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.O(" + n + ')');
        }
    }
    
    @Override
    final void method2091() {
        try {
            final float aFloat4678 = 0.0f;
            this.aFloat4677 = aFloat4678;
            this.aFloat4683 = aFloat4678;
            this.aFloat4674 = aFloat4678;
            this.aFloat4687 = aFloat4678;
            this.aFloat4680 = aFloat4678;
            this.aFloat4676 = aFloat4678;
            this.aFloat4679 = aFloat4678;
            this.aFloat4684 = aFloat4678;
            this.aFloat4678 = aFloat4678;
            final float aFloat4679 = 1.0f;
            this.aFloat4673 = aFloat4679;
            this.aFloat4675 = aFloat4679;
            this.aFloat4686 = aFloat4679;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.CA()");
        }
    }
    
    final void method2111(final int n) {
        try {
            this.aFloat4684 = -this.aFloat4684;
            this.aFloat4683 = -this.aFloat4683;
            this.aFloat4687 = -this.aFloat4687;
            if (n != 2) {
                this.aFloat4679 = -1.6680864f;
            }
            this.aFloat4676 = -this.aFloat4676;
            this.aFloat4677 = -this.aFloat4677;
            this.aFloat4678 = -this.aFloat4678;
            this.aFloat4673 = -this.aFloat4673;
            this.aFloat4675 = -this.aFloat4675;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.B(" + n + ')');
        }
    }
    
    final void method2112(final Class111 class111, final byte b) {
        try {
            final Class111_Sub1 class111_Sub1 = (Class111_Sub1)class111;
            this.aFloat4686 = class111_Sub1.aFloat4686;
            this.aFloat4680 = class111_Sub1.aFloat4684;
            this.aFloat4679 = class111_Sub1.aFloat4678;
            this.aFloat4684 = class111_Sub1.aFloat4680;
            this.aFloat4675 = class111_Sub1.aFloat4675;
            this.aFloat4678 = class111_Sub1.aFloat4679;
            if (b > -83) {
                this.aFloat4684 = 0.61483526f;
            }
            this.aFloat4687 = class111_Sub1.aFloat4676;
            this.aFloat4676 = class111_Sub1.aFloat4687;
            this.aFloat4673 = class111_Sub1.aFloat4673;
            this.aFloat4674 = -(class111_Sub1.aFloat4677 * this.aFloat4680 + (this.aFloat4686 * class111_Sub1.aFloat4674 + class111_Sub1.aFloat4683 * this.aFloat4679));
            this.aFloat4683 = -(class111_Sub1.aFloat4677 * this.aFloat4687 + (this.aFloat4675 * class111_Sub1.aFloat4683 + this.aFloat4678 * class111_Sub1.aFloat4674));
            this.aFloat4677 = -(class111_Sub1.aFloat4677 * this.aFloat4673 + (this.aFloat4676 * class111_Sub1.aFloat4683 + class111_Sub1.aFloat4674 * this.aFloat4684));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.E(" + ((class111 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final void method2099(final int n, final int n2, final int n3, final int[] array) {
        try {
            array[1] = (int)(n3 * this.aFloat4687 + (this.aFloat4675 * n2 + n * this.aFloat4678));
            array[0] = (int)(n3 * this.aFloat4680 + (this.aFloat4679 * n2 + this.aFloat4686 * n));
            array[2] = (int)(this.aFloat4673 * n3 + (this.aFloat4684 * n + n2 * this.aFloat4676));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.MA(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method2104(final int n) {
        try {
            this.aFloat4673 = 1.0f;
            final float n2 = Class98_Sub10_Sub36.aFloatArray5741[0x3FFF & n];
            this.aFloat4675 = n2;
            this.aFloat4686 = n2;
            this.aFloat4678 = Class98_Sub10_Sub36.aFloatArray5742[n & 0x3FFF];
            final float aFloat4680 = 0.0f;
            this.aFloat4677 = aFloat4680;
            this.aFloat4676 = aFloat4680;
            this.aFloat4684 = aFloat4680;
            this.aFloat4683 = aFloat4680;
            this.aFloat4687 = aFloat4680;
            this.aFloat4674 = aFloat4680;
            this.aFloat4680 = aFloat4680;
            this.aFloat4679 = -this.aFloat4678;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.HA(" + n + ')');
        }
    }
    
    final float[] method2113(final int n) {
        try {
            Class269.aFloatArray2023[4] = this.aFloat4679;
            if (n > -102) {
                this.aFloat4675 = -1.0382957f;
            }
            Class269.aFloatArray2023[8] = this.aFloat4680;
            Class269.aFloatArray2023[14] = this.aFloat4677;
            Class269.aFloatArray2023[10] = this.aFloat4673;
            Class269.aFloatArray2023[6] = this.aFloat4676;
            Class269.aFloatArray2023[1] = this.aFloat4678;
            Class269.aFloatArray2023[13] = this.aFloat4683;
            Class269.aFloatArray2023[2] = this.aFloat4684;
            Class269.aFloatArray2023[0] = this.aFloat4686;
            Class269.aFloatArray2023[5] = this.aFloat4675;
            Class269.aFloatArray2023[9] = this.aFloat4687;
            Class269.aFloatArray2023[12] = this.aFloat4674;
            return Class269.aFloatArray2023;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.L(" + n + ')');
        }
    }
    
    @Override
    final void method2107(final int n) {
        try {
            this.aFloat4686 = 1.0f;
            final float n2 = Class98_Sub10_Sub36.aFloatArray5741[n & 0x3FFF];
            this.aFloat4673 = n2;
            this.aFloat4675 = n2;
            this.aFloat4676 = Class98_Sub10_Sub36.aFloatArray5742[n & 0x3FFF];
            this.aFloat4687 = -this.aFloat4676;
            final float aFloat4679 = 0.0f;
            this.aFloat4677 = aFloat4679;
            this.aFloat4684 = aFloat4679;
            this.aFloat4683 = aFloat4679;
            this.aFloat4678 = aFloat4679;
            this.aFloat4674 = aFloat4679;
            this.aFloat4680 = aFloat4679;
            this.aFloat4679 = aFloat4679;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.N(" + n + ')');
        }
    }
    
    public static void method2114(final byte b) {
        try {
            Class111_Sub1.aClass239_4685 = null;
            if (b != 66) {
                method2114((byte)(-98));
            }
            Class111_Sub1.anIntArray4681 = null;
            Class111_Sub1.anIntArray4682 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.A(" + b + ')');
        }
    }
    
    static final Class98_Sub31_Sub2 method2115(final int n, final Class98_Sub31_Sub2 class98_Sub31_Sub2) {
        try {
            final Class98_Sub31_Sub2 class98_Sub31_Sub3 = (class98_Sub31_Sub2 != null) ? new Class98_Sub31_Sub2(class98_Sub31_Sub2) : new Class98_Sub31_Sub2();
            class98_Sub31_Sub3.method1357(26377, 128, 9);
            return class98_Sub31_Sub3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.I(" + n + ',' + ((class98_Sub31_Sub2 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method2101(final int n) {
        try {
            this.aFloat4675 = 1.0f;
            final float n2 = Class98_Sub10_Sub36.aFloatArray5741[n & 0x3FFF];
            this.aFloat4673 = n2;
            this.aFloat4686 = n2;
            this.aFloat4680 = Class98_Sub10_Sub36.aFloatArray5742[n & 0x3FFF];
            final float aFloat4679 = 0.0f;
            this.aFloat4677 = aFloat4679;
            this.aFloat4676 = aFloat4679;
            this.aFloat4683 = aFloat4679;
            this.aFloat4687 = aFloat4679;
            this.aFloat4678 = aFloat4679;
            this.aFloat4674 = aFloat4679;
            this.aFloat4679 = aFloat4679;
            this.aFloat4684 = -this.aFloat4680;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.F(" + n + ')');
        }
    }
    
    @Override
    final void method2097(final int n) {
        try {
            final float n2 = Class98_Sub10_Sub36.aFloatArray5741[0x3FFF & n];
            final float n3 = Class98_Sub10_Sub36.aFloatArray5742[n & 0x3FFF];
            final float aFloat4686 = this.aFloat4686;
            final float aFloat4687 = this.aFloat4679;
            final float aFloat4688 = this.aFloat4680;
            this.aFloat4686 = this.aFloat4684 * n3 + aFloat4686 * n2;
            final float aFloat4689 = this.aFloat4674;
            this.aFloat4679 = aFloat4687 * n2 + n3 * this.aFloat4676;
            this.aFloat4684 = -(n3 * aFloat4686) + this.aFloat4684 * n2;
            this.aFloat4676 = n2 * this.aFloat4676 - n3 * aFloat4687;
            this.aFloat4680 = aFloat4688 * n2 + n3 * this.aFloat4673;
            this.aFloat4674 = this.aFloat4677 * n3 + aFloat4689 * n2;
            this.aFloat4673 = this.aFloat4673 * n2 - aFloat4688 * n3;
            this.aFloat4677 = -(n3 * aFloat4689) + this.aFloat4677 * n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.RA(" + n + ')');
        }
    }
    
    @Override
    final void method2106(final int n, final int n2, final int n3) {
        try {
            this.aFloat4683 += n2;
            this.aFloat4677 += n3;
            this.aFloat4674 += n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.G(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method2092(final Class111 class111) {
        try {
            final Class111_Sub1 class111_Sub1 = (Class111_Sub1)class111;
            this.aFloat4680 = class111_Sub1.aFloat4680;
            this.aFloat4678 = class111_Sub1.aFloat4678;
            this.aFloat4687 = class111_Sub1.aFloat4687;
            this.aFloat4674 = class111_Sub1.aFloat4674;
            this.aFloat4686 = class111_Sub1.aFloat4686;
            this.aFloat4683 = class111_Sub1.aFloat4683;
            this.aFloat4677 = class111_Sub1.aFloat4677;
            this.aFloat4676 = class111_Sub1.aFloat4676;
            this.aFloat4675 = class111_Sub1.aFloat4675;
            this.aFloat4673 = class111_Sub1.aFloat4673;
            this.aFloat4684 = class111_Sub1.aFloat4684;
            this.aFloat4679 = class111_Sub1.aFloat4679;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.H(" + ((class111 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method2108(final int[] array) {
        try {
            final float n = -this.aFloat4674 + array[0];
            final float n2 = -this.aFloat4683 + array[1];
            final float n3 = -this.aFloat4677 + array[2];
            array[1] = (int)(n3 * this.aFloat4676 + (this.aFloat4675 * n2 + this.aFloat4679 * n));
            array[0] = (int)(n * this.aFloat4686 + n2 * this.aFloat4678 + n3 * this.aFloat4684);
            array[2] = (int)(this.aFloat4673 * n3 + (n2 * this.aFloat4687 + this.aFloat4680 * n));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.LA(" + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method2103(final int n, final int n2, final int n3, final int[] array) {
        try {
            array[0] = (int)(n3 * this.aFloat4680 + (n2 * this.aFloat4679 + this.aFloat4686 * n) + this.aFloat4674);
            array[1] = (int)(this.aFloat4683 + (this.aFloat4687 * n3 + (this.aFloat4675 * n2 + this.aFloat4678 * n)));
            array[2] = (int)(this.aFloat4673 * n3 + (n * this.aFloat4684 + n2 * this.aFloat4676) + this.aFloat4677);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.M(" + n + ',' + n2 + ',' + n3 + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final Class111 method2102() {
        try {
            final Class111_Sub1 class111_Sub1 = new Class111_Sub1();
            class111_Sub1.aFloat4677 = this.aFloat4677;
            class111_Sub1.aFloat4683 = this.aFloat4683;
            class111_Sub1.aFloat4674 = this.aFloat4674;
            class111_Sub1.aFloat4676 = this.aFloat4676;
            class111_Sub1.aFloat4675 = this.aFloat4675;
            class111_Sub1.aFloat4684 = this.aFloat4684;
            class111_Sub1.aFloat4686 = this.aFloat4686;
            class111_Sub1.aFloat4680 = this.aFloat4680;
            class111_Sub1.aFloat4678 = this.aFloat4678;
            class111_Sub1.aFloat4673 = this.aFloat4673;
            class111_Sub1.aFloat4679 = this.aFloat4679;
            class111_Sub1.aFloat4687 = this.aFloat4687;
            return class111_Sub1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.TA()");
        }
    }
    
    final float[] method2116(final int n) {
        try {
            Class269.aFloatArray2023[13] = 0.0f;
            Class269.aFloatArray2023[8] = this.aFloat4680;
            Class269.aFloatArray2023[2] = this.aFloat4684;
            if (n <= 45) {
                Class111_Sub1.aClass239_4685 = null;
            }
            Class269.aFloatArray2023[10] = this.aFloat4673;
            Class269.aFloatArray2023[9] = this.aFloat4687;
            Class269.aFloatArray2023[1] = this.aFloat4678;
            Class269.aFloatArray2023[14] = 0.0f;
            Class269.aFloatArray2023[0] = this.aFloat4686;
            Class269.aFloatArray2023[4] = this.aFloat4679;
            Class269.aFloatArray2023[6] = this.aFloat4676;
            Class269.aFloatArray2023[12] = 0.0f;
            Class269.aFloatArray2023[5] = this.aFloat4675;
            return Class269.aFloatArray2023;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.D(" + n + ')');
        }
    }
    
    public Class111_Sub1() {
        try {
            this.method2091();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gca.<init>()");
        }
    }
    
    static {
        Class111_Sub1.anIntArray4682 = new int[32];
    }
}
