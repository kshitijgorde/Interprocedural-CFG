// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub6 extends Class64
{
    static int anInt3655;
    static boolean aBoolean3656;
    
    final int method572(final byte b) {
        try {
            if (b < 119) {
                return -73;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dea.E(" + b + ')');
        }
    }
    
    static final void method573(final int n, final long n2) {
        try {
            final int anInt6295 = Class98_Sub46_Sub2_Sub2.anInt6295;
            if (~anInt6295 != ~Class201.anInt1545) {
                final int n3 = -Class201.anInt1545 + anInt6295;
                int n4 = (int)(n2 * n3 / 320L);
                if (n3 <= 0) {
                    if (n4 != 0) {
                        if (n3 > n4) {
                            n4 = n3;
                        }
                    }
                    else {
                        n4 = -1;
                    }
                }
                else if (n4 == 0) {
                    n4 = 1;
                }
                else if (~n3 > ~n4) {
                    n4 = n3;
                }
                Class201.anInt1545 += n4;
            }
            final int anInt6296 = Class135.anInt1051;
            if (n == -1) {
                Class119_Sub4.aFloat4740 += 8.0f * (n2 * Class305.aFloat2545 / 40.0f);
                if (~anInt6296 != ~Class224_Sub3_Sub1.anInt6147) {
                    final int n5 = -Class224_Sub3_Sub1.anInt6147 + anInt6296;
                    int n6 = (int)(n2 * n5 / 320L);
                    if (~n5 >= -1) {
                        if (n6 == 0) {
                            n6 = -1;
                        }
                        else if (~n6 > ~n5) {
                            n6 = n5;
                        }
                    }
                    else if (n6 != 0) {
                        if (n6 > n5) {
                            n6 = n5;
                        }
                    }
                    else {
                        n6 = 1;
                    }
                    Class224_Sub3_Sub1.anInt6147 += n6;
                }
                Class98_Sub22_Sub2.aFloat5794 += 8.0f * (Class180.aFloat3405 * n2 / 40.0f);
                Class42_Sub2.method388(true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dea.H(" + n + ',' + n2 + ')');
        }
    }
    
    static final int method574(final int n, final char c) {
        try {
            if (n <= 72) {
                return 52;
            }
            if (c >= '\0' && Class98_Sub43_Sub3.anIntArray5919.length > c) {
                return Class98_Sub43_Sub3.anIntArray5919[c];
            }
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dea.D(" + n + ',' + c + ')');
        }
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                return 19;
            }
            if (~super.aClass98_Sub27_495.method1289(n ^ 0xFFFFFF9E).method2318(n - 1) > -97) {
                return 0;
            }
            return 2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dea.A(" + n + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (b > 118) {
                if (~super.aClass98_Sub27_495.method1289(-123).method2318(-1) > -97) {
                    super.anInt494 = 0;
                }
                if (super.anInt494 < 0 || super.anInt494 > 2) {
                    super.anInt494 = this.method552(0);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dea.C(" + b + ')');
        }
    }
    
    Class64_Sub6(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
        try {
            Class98_Sub10_Sub1.method1004(-119, super.anInt494);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dea.<init>(" + ((class98_Sub27 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class64_Sub6(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
        try {
            Class98_Sub10_Sub1.method1004(-44, super.anInt494);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dea.<init>(" + n + ',' + ((class98_Sub27 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final boolean method575(final int n) {
        try {
            return super.aClass98_Sub27_495.method1289(-100).method2318(n) >= 96;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dea.G(" + n + ')');
        }
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            Class98_Sub10_Sub1.method1004(-42, super.anInt494 = anInt494);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dea.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                method574(-122, 't');
            }
            if (~super.aClass98_Sub27_495.method1289(-119).method2318(n2 ^ 0xFFFF8E82) > -97) {
                return 3;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dea.F(" + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class64_Sub6.aBoolean3656 = true;
    }
}
