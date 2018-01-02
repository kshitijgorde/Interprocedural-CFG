// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub30 extends Class98_Sub10
{
    static ha aHa5709;
    private int anInt5710;
    private int anInt5711;
    static boolean aBoolean5712;
    private int anInt5713;
    
    @Override
    final void method1001(final byte b) {
        try {
            Class98_Sub31_Sub4.method1386(0);
            if (b != 66) {
                method1094(51);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qo.I(" + b + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (super.aClass16_3863.aBoolean198) {
                final int n3 = Class352.anIntArray3001[n2];
                final int n4 = n3 - 2048 >> -1261703615;
                for (int n5 = 0; Class25.anInt268 > n5; ++n5) {
                    final int n6 = Class64_Sub1.anIntArray3640[n5];
                    final int n7 = n6 - 2048 >> -1945705119;
                    int n8;
                    if (this.anInt5711 != 0) {
                        n8 = (int)(3.141592653589793 * (this.anInt5713 * (int)(Math.sqrt((n7 * n7 + n4 * n4 >> 1054777292) / 4096.0f) * 4096.0)));
                    }
                    else {
                        n8 = (-n3 + n6) * this.anInt5713;
                    }
                    int n9 = n8 - (0xFFFFF000 & n8);
                    if (this.anInt5710 != 0) {
                        if (this.anInt5710 == 2) {
                            n9 -= 2048;
                            if (~n9 > -1) {
                                n9 = -n9;
                            }
                            n9 = -n9 + 2048 << 1820888993;
                        }
                    }
                    else {
                        n9 = aa_Sub2.anIntArray3565[(0xFF8 & n9) >> -627027964] + 4096 >> 2128106177;
                    }
                    method237[n5] = n9;
                }
            }
            if (n != 255) {
                method1094(-79);
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qo.G(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (n != 0) {
                if (n != 1) {
                    if (n == 3) {
                        this.anInt5713 = class98_Sub22.readUnsignedByte((byte)76);
                    }
                }
                else {
                    this.anInt5710 = class98_Sub22.readUnsignedByte((byte)(-112));
                }
            }
            else {
                this.anInt5711 = class98_Sub22.readUnsignedByte((byte)(-117));
            }
            if (b > -92) {
                this.anInt5711 = 61;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qo.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final void method1093(final int n, final int n2, final boolean b, final int n3) {
        try {
            if (n != -29680) {
                Class98_Sub10_Sub30.aBoolean5712 = false;
            }
            if (Class156_Sub1.aClass377_3277.method3990(n3, -1) == null) {
                if (!Class372.aBoolean3152) {
                    Class291.method3414(-1, b, n3);
                }
                else {
                    final Class98_Sub36 class98_Sub36 = new Class98_Sub36(n3, new Class237_Sub1(4096, Class45.aClass207_385, n3), n2, b);
                    class98_Sub36.aClass237_Sub1_4157.method2909(true, Class366.aStringArray3113[Class374.anInt3159]);
                    Class156_Sub1.aClass377_3277.method3996(class98_Sub36, n3, n + 29679);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qo.B(" + n + ',' + n2 + ',' + b + ',' + n3 + ')');
        }
    }
    
    public Class98_Sub10_Sub30() {
        super(0, true);
        this.anInt5710 = 0;
        this.anInt5713 = 1;
        this.anInt5711 = 0;
    }
    
    public static void method1094(final int n) {
        try {
            Class98_Sub10_Sub30.aHa5709 = null;
            if (n != 615) {
                method1094(96);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qo.D(" + n + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub30.aBoolean5712 = false;
    }
}
