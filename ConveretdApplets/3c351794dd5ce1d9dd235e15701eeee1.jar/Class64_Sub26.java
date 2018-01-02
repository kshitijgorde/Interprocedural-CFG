// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub26 extends Class64
{
    static int anInt3712;
    static Class324 aClass324_3713;
    static int anInt3714;
    
    static final int method658(final int n, final int n2, final int n3) {
        try {
            final int n4 = n2 >>> 749908671;
            if (n != 749908671) {
                return -120;
            }
            return (n4 + n2) / n3 + -n4;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vd.D(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                return 11;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vd.A(" + n + ')');
        }
    }
    
    Class64_Sub26(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    public static void method659(final int n) {
        try {
            if (n != 3) {
                method660(61, 91, -74, 72);
            }
            Class64_Sub26.aClass324_3713 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vd.I(" + n + ')');
        }
    }
    
    Class64_Sub26(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vd.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    static final void method660(final int n, final int anInt6051, final int n2, final int anInt6052) {
        try {
            final Class98_Sub46_Sub17 method2628 = Class185.method2628(n, -98, 9);
            method2628.method1626((byte)(-103));
            method2628.anInt6054 = anInt6052;
            method2628.anInt6051 = anInt6051;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vd.H(" + n + ',' + anInt6051 + ',' + n2 + ',' + anInt6052 + ')');
        }
    }
    
    final boolean method661(final int n) {
        try {
            if (n != -1) {
                Class64_Sub26.aClass324_3713 = null;
            }
            return !super.aClass98_Sub27_495.method1291((byte)102);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vd.G(" + n + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                return -85;
            }
            if (super.aClass98_Sub27_495.method1291((byte)123)) {
                return 3;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vd.F(" + n + ',' + n2 + ')');
        }
    }
    
    final int method662(final byte b) {
        try {
            if (b <= 119) {
                return 24;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vd.E(" + b + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (b <= 118) {
                this.method661(114);
            }
            if (super.aClass98_Sub27_495.method1291((byte)124)) {
                super.anInt494 = 0;
            }
            if (super.anInt494 != 1 && ~super.anInt494 != -1) {
                super.anInt494 = this.method552(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vd.C(" + b + ')');
        }
    }
}
