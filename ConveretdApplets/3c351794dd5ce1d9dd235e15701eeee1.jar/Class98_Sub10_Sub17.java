// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub10_Sub17 extends Class98_Sub10
{
    private int anInt5622;
    static int anInt5623;
    static int[] anIntArray5624;
    static String[] aStringArray5625;
    
    public Class98_Sub10_Sub17() {
        super(0, true);
        this.anInt5622 = 585;
    }
    
    public static void method1053(final byte b) {
        try {
            Class98_Sub10_Sub17.aStringArray5625 = null;
            Class98_Sub10_Sub17.anIntArray5624 = null;
            if (b != 40) {
                method1053((byte)11);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ks.B(" + b + ')');
        }
    }
    
    @Override
    final int[] method990(final int n, final int n2) {
        try {
            final int[] method237 = super.aClass16_3863.method237((byte)98, n2);
            if (n != 255) {
                return null;
            }
            if (super.aClass16_3863.aBoolean198) {
                final int n3 = Class352.anIntArray3001[n2];
                for (int n4 = 0; ~n4 > ~Class25.anInt268; ++n4) {
                    final int n5 = Class64_Sub1.anIntArray3640[n4];
                    if (~this.anInt5622 > ~n5 && n5 < 4096 - this.anInt5622 && -this.anInt5622 + 2048 < n3 && ~(this.anInt5622 + 2048) < ~n3) {
                        final int n6 = -n5 + 2048;
                        method237[n4] = -((((n6 >= 0) ? n6 : (-n6)) << 12) / (2048 + -this.anInt5622)) + 4096;
                    }
                    else if (~n5 < ~(2048 - this.anInt5622) && n5 < 2048 + this.anInt5622) {
                        final int n7 = n3 - 2048;
                        method237[n4] = (((~n7 > -1) ? (-n7) : n7) - this.anInt5622 << 12) / (2048 + -this.anInt5622);
                    }
                    else if (n3 < this.anInt5622 || ~n3 < ~(-this.anInt5622 + 4096)) {
                        final int n8 = -2048 + n5;
                        method237[n4] = (((n8 < 0) ? (-n8) : n8) - this.anInt5622 << 12) / (2048 + -this.anInt5622);
                    }
                    else if (~n5 > ~this.anInt5622 || ~(4096 + -this.anInt5622) > ~n5) {
                        final int n9 = -n3 + 2048;
                        method237[n4] = 4096 + -((((n9 < 0) ? (-n9) : n9) << 12) / (2048 + -this.anInt5622));
                    }
                    else {
                        method237[n4] = 0;
                    }
                }
            }
            return method237;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ks.G(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method991(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b > -92) {
                Class98_Sub10_Sub17.anInt5623 = -16;
            }
            if (~n == -1) {
                this.anInt5622 = class98_Sub22.readShort((byte)127);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ks.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static {
        Class98_Sub10_Sub17.anInt5623 = -1;
        Class98_Sub10_Sub17.anIntArray5624 = new int[14];
        Class98_Sub10_Sub17.aStringArray5625 = new String[200];
    }
}
