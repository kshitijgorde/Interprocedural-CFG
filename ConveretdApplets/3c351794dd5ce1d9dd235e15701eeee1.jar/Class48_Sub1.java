// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class48_Sub1 extends Class48
{
    static int anInt3628;
    static int[] anIntArray3629;
    
    static final void method455(final int n, final int n2, final int n3, final int n4, final boolean b, final int n5, final int n6, final int n7) {
        try {
            final int n8 = n2 + n5;
            final int i = n6 - n2;
            for (int n9 = n5; ~n9 > ~n8; ++n9) {
                Class333.method3761(n, Class97.anIntArrayArray814[n9], n4, n3, (byte)96);
            }
            final int n10 = -n2 + n3;
            for (int n11 = n6; i < n11; --n11) {
                Class333.method3761(n, Class97.anIntArrayArray814[n11], n4, n3, (byte)91);
            }
            if (b) {
                Class48_Sub1.anIntArray3629 = null;
            }
            final int n12 = n2 + n4;
            for (int n13 = n8; i >= n13; ++n13) {
                final int[] array = Class97.anIntArrayArray814[n13];
                Class333.method3761(n, array, n4, n12, (byte)100);
                Class333.method3761(n7, array, n12, n10, (byte)(-124));
                Class333.method3761(n, array, n10, n3, (byte)63);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "go.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + b + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
    
    abstract Class42_Sub2 method456(final byte p0);
    
    static final Class98_Sub46_Sub10 method457(final int n, final int n2, final int n3, final Class207 class207) {
        try {
            final Class98_Sub22 class98_Sub22 = new Class98_Sub22(class207.method2745(n, n2, false));
            final Class98_Sub46_Sub10 class98_Sub46_Sub10 = new Class98_Sub46_Sub10(n, class98_Sub22.readString((byte)84), class98_Sub22.readString((byte)84), class98_Sub22.readInt(-2), class98_Sub22.readInt(-2), ~class98_Sub22.readUnsignedByte((byte)(-119)) == 0xFFFFFFFE, class98_Sub22.readUnsignedByte((byte)126), class98_Sub22.readUnsignedByte((byte)28));
            for (int i = class98_Sub22.readUnsignedByte((byte)116), n4 = 0; i > n4; ++n4) {
                class98_Sub46_Sub10.aClass148_6010.method2419(new Class98_Sub6(class98_Sub22.readUnsignedByte((byte)(-122)), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127)), -20911);
            }
            class98_Sub46_Sub10.method1569(-1);
            if (n3 != 10443) {
                Class48_Sub1.anIntArray3629 = null;
            }
            return class98_Sub46_Sub10;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "go.A(" + n + ',' + n2 + ',' + n3 + ',' + ((class207 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method458(final Class98_Sub26 class98_Sub26, final int n, final int n2, final int n3, final boolean b) {
        try {
            final long n4 = n << 1428771228 | n2 << 1140143822 | n3;
            final Class98_Sub45 class98_Sub27 = (Class98_Sub45)Class146.aClass377_1180.method3990(n4, -1);
            if (class98_Sub27 == null) {
                final Class98_Sub45 class98_Sub28 = new Class98_Sub45();
                Class146.aClass377_1180.method3996(class98_Sub28, n4, -1);
                class98_Sub28.aClass148_4254.method2419(class98_Sub26, -20911);
            }
            else {
                final Class297 method2714 = Class98_Sub46_Sub19.aClass205_6068.method2714(class98_Sub26.anInt4031, (byte)(-117));
                int anInt2475 = method2714.anInt2475;
                if (~method2714.anInt2469 == 0xFFFFFFFE) {
                    anInt2475 *= class98_Sub26.anInt4032 + 1;
                }
                for (Class98_Sub26 class98_Sub29 = (Class98_Sub26)class98_Sub27.aClass148_4254.method2418(32); class98_Sub29 != null; class98_Sub29 = (Class98_Sub26)class98_Sub27.aClass148_4254.method2417(102)) {
                    final Class297 method2715 = Class98_Sub46_Sub19.aClass205_6068.method2714(class98_Sub29.anInt4031, (byte)(-126));
                    int anInt2476 = method2715.anInt2475;
                    if (method2715.anInt2469 == 1) {
                        anInt2476 *= 1 + class98_Sub29.anInt4032;
                    }
                    if (anInt2475 > anInt2476) {
                        Class279.method3322(class98_Sub29, class98_Sub26, (byte)24);
                        return;
                    }
                }
                if (!b) {
                    method459(true);
                }
                class98_Sub27.aClass148_4254.method2419(class98_Sub26, -20911);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "go.D(" + ((class98_Sub26 != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ',' + b + ')');
        }
    }
    
    public static void method459(final boolean b) {
        try {
            Class48_Sub1.anIntArray3629 = null;
            if (b) {
                Class48_Sub1.anIntArray3629 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "go.B(" + b + ')');
        }
    }
    
    static {
        Class48_Sub1.anIntArray3629 = new int[120];
        int n = 0;
        for (int n2 = 0; ~n2 > -121; ++n2) {
            final int n3 = n2 + 1;
            n += (int)(300.0 * Math.pow(2.0, n3 / 7.0) + n3);
            Class48_Sub1.anIntArray3629[n2] = n / 4;
        }
    }
}
