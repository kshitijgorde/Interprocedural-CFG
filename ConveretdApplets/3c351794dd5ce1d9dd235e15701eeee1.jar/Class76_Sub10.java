// 
// Decompiled by Procyon v0.5.30
// 

final class Class76_Sub10 extends Class76
{
    static int anInt3793;
    static int anInt3794;
    
    @Override
    final void method746(final int n, final int n2, final int n3) {
        try {
            if (n3 >= -75) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vga.E(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final void method739(final int n) {
        try {
            if (n != -2) {
                method770(13);
            }
            super.aHa_Sub3_585.method2029((byte)(-62), false);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vga.C(" + n + ')');
        }
    }
    
    @Override
    final void method742(final int n, final int n2, final Interface4 interface4) {
        try {
            super.aHa_Sub3_585.method2005(interface4, 63);
            super.aHa_Sub3_585.method2015(n2, (byte)(-118));
            if (n != 6) {
                this.method742(-90, 37, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vga.I(" + n + ',' + n2 + ',' + ((interface4 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final char method769(final char c, final int n, final byte b) {
        try {
            if (b > -66) {
                return '|';
            }
            if (c >= '\u00c0' && ~c >= -256) {
                if (~c <= -193 && ~c >= -199) {
                    return 'A';
                }
                if (c == '\u00c7') {
                    return 'C';
                }
                if (c >= '\u00c8' && c <= '\u00cb') {
                    return 'E';
                }
                if (~c <= -205 && c <= '\u00cf') {
                    return 'I';
                }
                if (~c <= -211 && ~c >= -215) {
                    return 'O';
                }
                if (c >= '\u00d9' && ~c >= -221) {
                    return 'U';
                }
                if (~c == 0xFFFFFF22) {
                    return 'Y';
                }
                if (~c == 0xFFFFFF20) {
                    return 's';
                }
                if (c >= '\u00e0' && c <= '\u00e6') {
                    return 'a';
                }
                if (c == '\u00e7') {
                    return 'c';
                }
                if (~c <= -233 && c <= '\u00eb') {
                    return 'e';
                }
                if (c >= '\u00ec' && c <= '\u00ef') {
                    return 'i';
                }
                if (~c <= -243 && c <= '\u00f6') {
                    return 'o';
                }
                if (~c <= -250 && ~c >= -253) {
                    return 'u';
                }
                if (c == '\u00fd' || ~c == 0xFFFFFF00) {
                    return 'y';
                }
            }
            if (~c == 0xFFFFFEAD) {
                return 'O';
            }
            if (~c == 0xFFFFFEAC) {
                return 'o';
            }
            if (~c == 0xFFFFFE87) {
                return 'Y';
            }
            return c;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vga.A(" + c + ',' + n + ',' + b + ')');
        }
    }
    
    static final void method770(final int n) {
        try {
            final int anInt71 = Class2.anInt71;
            final int[] anIntArray2705 = Class319.anIntArray2705;
            for (int i = 0; i < anInt71; ++i) {
                final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub2 = Class151_Sub9.aClass246_Sub3_Sub4_Sub2_Sub2Array5030[anIntArray2705[i]];
                if (class246_Sub3_Sub4_Sub2_Sub2 != null && ~class246_Sub3_Sub4_Sub2_Sub2.anInt6384 < -1) {
                    final Class246_Sub3_Sub4_Sub2_Sub2 class246_Sub3_Sub4_Sub2_Sub3 = class246_Sub3_Sub4_Sub2_Sub2;
                    --class246_Sub3_Sub4_Sub2_Sub3.anInt6384;
                    if (~class246_Sub3_Sub4_Sub2_Sub2.anInt6384 == -1) {
                        class246_Sub3_Sub4_Sub2_Sub2.aString6374 = null;
                    }
                }
            }
            for (int n2 = 0; Class150.anInt1211 > n2; ++n2) {
                final Class98_Sub39 class98_Sub39 = (Class98_Sub39)Class260.aClass377_3254.method3990(Class325.anIntArray2726[n2], -1);
                if (class98_Sub39 != null) {
                    final Class246_Sub3_Sub4_Sub2_Sub1 aClass246_Sub3_Sub4_Sub2_Sub1_4187 = class98_Sub39.aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                    if (aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6384 > 0) {
                        final Class246_Sub3_Sub4_Sub2_Sub1 class246_Sub3_Sub4_Sub2_Sub4 = aClass246_Sub3_Sub4_Sub2_Sub1_4187;
                        --class246_Sub3_Sub4_Sub2_Sub4.anInt6384;
                        if (~aClass246_Sub3_Sub4_Sub2_Sub1_4187.anInt6384 == -1) {
                            aClass246_Sub3_Sub4_Sub2_Sub1_4187.aString6374 = null;
                        }
                    }
                }
            }
            if (n != -256) {
                method770(98);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vga.F(" + n + ')');
        }
    }
    
    @Override
    final void method743(final int n, final boolean b) {
        try {
            if (n < 93) {
                Class76_Sub10.anInt3794 = 111;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vga.D(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final void method748(final int n, final boolean b) {
        try {
            if (n != 69) {
                this.method739(77);
            }
            super.aHa_Sub3_585.method2029((byte)(-93), true);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vga.B(" + n + ',' + b + ')');
        }
    }
    
    @Override
    final boolean method745(final byte b) {
        try {
            return b == 27;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vga.H(" + b + ')');
        }
    }
    
    Class76_Sub10(final ha_Sub3 ha_Sub3) {
        super(ha_Sub3);
    }
}
