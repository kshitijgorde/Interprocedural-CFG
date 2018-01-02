// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub23 extends Class98_Sub10
{
    private int anInt5657;
    private int[] anIntArray5658;
    static int anInt5659;
    private int[] anIntArray5660;
    static int anInt5661;
    static Class335 aClass335_5662;
    private int anInt5663;
    private int anInt5664;
    
    public Class98_Sub10_Sub23() {
        super(0, true);
        this.anInt5663 = 0;
        this.anInt5664 = 10;
        this.anInt5657 = 2048;
    }
    
    @Override
    final void method1001(final byte b) {
        try {
            this.method1074((byte)(-6));
            if (b != 66) {
                Class98_Sub10_Sub23.aClass335_5662 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nu.I(" + b + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (n != 0) {
                if (n != 1) {
                    if (~n == 0xFFFFFFFD) {
                        this.anInt5663 = class98_Sub22.readUnsignedByte((byte)(-126));
                    }
                }
                else {
                    this.anInt5657 = class98_Sub22.readShort((byte)127);
                }
            }
            else {
                this.anInt5664 = class98_Sub22.readUnsignedByte((byte)4);
            }
            if (b >= -92) {
                this.method1001((byte)102);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nu.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public static void method1073(final int n) {
        try {
            Class98_Sub10_Sub23.aClass335_5662 = null;
            if (n != 4096) {
                method1073(45);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nu.B(" + n + ')');
        }
    }
    
    private final void method1074(final byte b) {
        try {
            this.anIntArray5658 = new int[1 + this.anInt5664];
            this.anIntArray5660 = new int[1 + this.anInt5664];
            int n = 0;
            final int n2 = 4096 / this.anInt5664;
            final int n3 = n2 * this.anInt5657 >> -946071060;
            int i = 0;
            if (b != -6) {
                this.anInt5657 = -68;
            }
            while (i < this.anInt5664) {
                this.anIntArray5660[i] = n;
                this.anIntArray5658[i] = n3 + n;
                n += n2;
                ++i;
            }
            this.anIntArray5660[this.anInt5664] = 4096;
            this.anIntArray5658[this.anInt5664] = this.anIntArray5658[0] + 4096;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nu.D(" + b + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (super.aClass16_3863.aBoolean198) {
                final int n3 = Class352.anIntArray3001[n2];
                if (~this.anInt5663 == -1) {
                    int n4 = 0;
                    int n5 = 0;
                    while (this.anInt5664 > n5) {
                        if (~n3 <= ~this.anIntArray5660[n5] && ~this.anIntArray5660[1 + n5] < ~n3) {
                            if (~this.anIntArray5658[n5] < ~n3) {
                                n4 = 4096;
                                break;
                            }
                            break;
                        }
                        else {
                            ++n5;
                        }
                    }
                    Class236.method2896(method237, 0, Class25.anInt268, n4);
                }
                else {
                    for (int i = 0; i < Class25.anInt268; ++i) {
                        int n6 = 0;
                        int n7 = 0;
                        final int n8 = Class64_Sub1.anIntArray3640[i];
                        final int anInt5663 = this.anInt5663;
                        if (anInt5663 != 1) {
                            if (~anInt5663 != 0xFFFFFFFD) {
                                if (~anInt5663 == 0xFFFFFFFC) {
                                    n6 = (n8 + -n3 >> 2077425665) + 2048;
                                }
                            }
                            else {
                                n6 = 2048 + (n8 - 4096 + n3 >> -752554751);
                            }
                        }
                        else {
                            n6 = n8;
                        }
                        int n9 = 0;
                        while (~this.anInt5664 < ~n9) {
                            if (~this.anIntArray5660[n9] >= ~n6 && ~this.anIntArray5660[n9 + 1] < ~n6) {
                                if (~this.anIntArray5658[n9] < ~n6) {
                                    n7 = 4096;
                                    break;
                                }
                                break;
                            }
                            else {
                                ++n9;
                            }
                        }
                        method237[i] = n7;
                    }
                }
            }
            if (n != 255) {
                Class98_Sub10_Sub23.anInt5661 = -81;
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nu.G(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub23.anInt5661 = 0;
    }
}
