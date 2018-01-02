// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub25 extends Class64
{
    static int anInt3711;
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (super.aClass98_Sub27_495.method1291((byte)119)) {
                return 3;
            }
            if (n2 != 29053) {
                Class64_Sub25.anInt3711 = 101;
            }
            if (super.aClass98_Sub27_495.method1286((byte)104) == s_Sub1.aClass279_5197) {
                if (n == 0) {
                    if (~super.aClass98_Sub27_495.aClass64_Sub14_4049.method609((byte)124) == 0xFFFFFFFE) {
                        return 2;
                    }
                    if (~super.aClass98_Sub27_495.aClass64_Sub20_4056.method634((byte)124) == 0xFFFFFFFE) {
                        return 2;
                    }
                    if (~super.aClass98_Sub27_495.aClass64_Sub28_4064.method668((byte)125) < -1) {
                        return 2;
                    }
                }
                return 1;
            }
            return 3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vba.F(" + n + ',' + n2 + ')');
        }
    }
    
    static final String method653(final int n, final int[] array) {
        try {
            final StringBuffer sb = new StringBuffer();
            int anInt3201 = RuntimeException_Sub1.anInt3201;
            for (int n2 = n; ~array.length < ~n2; ++n2) {
                final Class220 method220 = Class303.aClass13_2529.method220(array[n2], -11180);
                if (~method220.anInt1649 != 0x0) {
                    Class332 method221 = (Class332)Class98_Sub6.aClass79_3847.method802(-126, method220.anInt1649);
                    if (method221 == null) {
                        final Class324 method222 = Class324.method3685(Class332_Sub2.aClass207_5423, method220.anInt1649, 0);
                        if (method222 != null) {
                            method221 = Class265.aHa1974.method1758(method222, true);
                            Class98_Sub6.aClass79_3847.method805(method220.anInt1649, method221, (byte)(-80));
                        }
                    }
                    if (method221 != null) {
                        Class217.aClass332Array3408[anInt3201] = method221;
                        sb.append(" <img=").append(anInt3201).append(">");
                        ++anInt3201;
                    }
                }
            }
            return sb.toString();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vba.D(" + n + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class64_Sub25(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    static final Object method654(final int n, final byte[] array, final boolean b) {
        try {
            if (n != 2) {
                Class64_Sub25.anInt3711 = 82;
            }
            if (array == null) {
                return null;
            }
            if (array.length > 136 && !PlayerUpdate.aBoolean3413) {
                try {
                    final Class317 class317 = (Class317)Class.forName("Class317_Sub1").newInstance();
                    class317.method3652((byte)(-101), array);
                    return class317;
                }
                catch (Throwable t) {
                    PlayerUpdate.aBoolean3413 = true;
                }
            }
            if (b) {
                return Class246_Sub10.method3140(array, 0);
            }
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vba.H(" + n + ',' + ((array != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (b <= 118) {
                this.method556(69, 53);
            }
            Label_0061: {
                if (super.aClass98_Sub27_495.method1286((byte)104) != s_Sub1.aClass279_5197) {
                    super.anInt494 = 1;
                    if (!client.aBoolean3553) {
                        break Label_0061;
                    }
                }
                if (super.aClass98_Sub27_495.method1291((byte)106)) {
                    super.anInt494 = 0;
                }
            }
            if (super.anInt494 != 0 && super.anInt494 != 1) {
                super.anInt494 = this.method552(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vba.C(" + b + ')');
        }
    }
    
    final int method655(final byte b) {
        try {
            if (b <= 119) {
                return 124;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vba.E(" + b + ')');
        }
    }
    
    static final boolean method656(final int n, final byte b, final int n2) {
        try {
            if (b != -123) {
                method656(11, (byte)(-48), 84);
            }
            return (Class76_Sub9.method766(-91, n, n2) | (0x70000 & n2) != 0x0) || Class238.method2919(-120, n2, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vba.I(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vba.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                this.method655((byte)4);
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vba.A(" + n + ')');
        }
    }
    
    final boolean method657(final int n) {
        try {
            if (super.aClass98_Sub27_495.method1291((byte)112)) {
                return false;
            }
            if (n != -1) {
                Class64_Sub25.anInt3711 = -25;
            }
            return super.aClass98_Sub27_495.method1286((byte)104) == s_Sub1.aClass279_5197;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vba.G(" + n + ')');
        }
    }
    
    Class64_Sub25(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
}
