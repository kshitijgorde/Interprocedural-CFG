// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub36 extends Class98_Sub10
{
    static float[] aFloatArray5741;
    static float[] aFloatArray5742;
    private Class119[] aClass119Array5743;
    static Class114[] aClass114Array5744;
    static Class143 aClass143_5745;
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (n == 0) {
                this.aClass119Array5743 = new Class119[class98_Sub22.readUnsignedByte((byte)(-118))];
                for (int n2 = 0; ~this.aClass119Array5743.length < ~n2; ++n2) {
                    final int unsignedByte = class98_Sub22.readUnsignedByte((byte)43);
                    if (unsignedByte != 0) {
                        if (~unsignedByte != 0xFFFFFFFE) {
                            if (unsignedByte != 2) {
                                if (unsignedByte == 3) {
                                    this.aClass119Array5743[n2] = Class300.method3533(class98_Sub22, (byte)51);
                                }
                            }
                            else {
                                this.aClass119Array5743[n2] = Class258.method3203((byte)(-121), class98_Sub22);
                            }
                        }
                        else {
                            this.aClass119Array5743[n2] = Class98_Sub10_Sub14.method1046(false, class98_Sub22);
                        }
                    }
                    else {
                        this.aClass119Array5743[n2] = Class255.method3192(-120, class98_Sub22);
                    }
                }
            }
            else if (n == 1) {
                super.aBoolean3861 = (~class98_Sub22.readUnsignedByte((byte)30) == 0xFFFFFFFE);
            }
            if (b >= -92) {
                this.aClass119Array5743 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "us.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final int[][] method997(final int n, final int n2) {
        try {
            if (n > -76) {
                this.method997(-3, -64);
            }
            final int[][] method2828 = super.aClass223_3859.method2828(n2, 0);
            if (super.aClass223_3859.aBoolean1683) {
                final int[][] array = new int[Class63.anInt492][Class25.anInt268];
                final int[][][] method2829 = super.aClass223_3859.method2830(26279);
                this.method1111(true, array);
                for (int n3 = 0; ~n3 > ~Class63.anInt492; ++n3) {
                    final int[] array2 = array[n3];
                    final int[][] array3 = method2829[n3];
                    final int[] array4 = array3[0];
                    final int[] array5 = array3[1];
                    final int[] array6 = array3[2];
                    for (int i = 0; i < Class25.anInt268; ++i) {
                        final int n4 = array2[i];
                        array6[i] = Class202.method2702(n4, 255) << 1989859300;
                        array5[i] = Class202.method2702(4080, n4 >> -819579196);
                        array4[i] = Class202.method2702(n4 >> 471431020, 4080);
                    }
                }
            }
            return method2828;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "us.C(" + n + ',' + n2 + ')');
        }
    }
    
    static final int method1110(final byte b) {
        try {
            if (b <= 49) {
                Class98_Sub10_Sub36.aClass143_5745 = null;
            }
            if (~Class98_Sub46.anInt4261 == 0xFFFFFFFE) {
                return aa_Sub1.anInt3556;
            }
            return Class151_Sub7.anInt5005;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "us.D(" + b + ')');
        }
    }
    
    private final void method1111(final boolean b, final int[][] array) {
        try {
            if (b) {
                final int anInt268 = Class25.anInt268;
                final int anInt269 = Class63.anInt492;
                Class76_Sub5.method759((byte)(-102), array);
                Class237.method2898(Class329.anInt2761, (byte)(-118), za_Sub1.anInt6075, 0, 0);
                if (this.aClass119Array5743 != null) {
                    for (int n = 0; ~n > ~this.aClass119Array5743.length; ++n) {
                        final Class119 class119 = this.aClass119Array5743[n];
                        final int anInt270 = class119.anInt988;
                        final int anInt271 = class119.anInt985;
                        if (anInt270 < 0) {
                            if (~anInt271 <= -1) {
                                class119.method2179((byte)(-96), anInt269, anInt268);
                            }
                        }
                        else if (anInt271 >= 0) {
                            class119.method2174(anInt268, anInt269, -5515);
                        }
                        else {
                            class119.method2178(82, anInt268, anInt269);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "us.B(" + b + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (n != 255) {
                Class98_Sub10_Sub36.aClass114Array5744 = null;
            }
            if (super.aClass16_3863.aBoolean198) {
                this.method1111(true, super.aClass16_3863.method238(-124));
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "us.G(" + n + ',' + n2 + ')');
        }
    }
    
    public static void method1112(final int n) {
        try {
            if (n == 0) {
                Class98_Sub10_Sub36.aClass114Array5744 = null;
                Class98_Sub10_Sub36.aClass143_5745 = null;
                Class98_Sub10_Sub36.aFloatArray5742 = null;
                Class98_Sub10_Sub36.aFloatArray5741 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "us.E(" + n + ')');
        }
    }
    
    public Class98_Sub10_Sub36() {
        super(0, true);
    }
    
    static {
        Class98_Sub10_Sub36.aFloatArray5742 = new float[16384];
        Class98_Sub10_Sub36.aFloatArray5741 = new float[16384];
        final double n = 3.834951969714103E-4;
        for (int i = 0; i < 16384; ++i) {
            Class98_Sub10_Sub36.aFloatArray5742[i] = (float)Math.sin(n * i);
            Class98_Sub10_Sub36.aFloatArray5741[i] = (float)Math.cos(n * i);
        }
    }
}
