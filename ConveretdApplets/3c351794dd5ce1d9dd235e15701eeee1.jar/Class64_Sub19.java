// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub19 extends Class64
{
    static short aShort3692;
    static int anInt3693;
    
    final int method630(final byte b) {
        try {
            if (b < 119) {
                this.method551((byte)(-70));
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ra.E(" + b + ')');
        }
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ra.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    static final boolean method631(final int n, final int n2, final int n3) {
        try {
            return n3 != -2 || (s_Sub1.method3433(n2, n3 ^ 0xFFFFC217, n) & za_Sub2.method1682(n2, 0, n));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ra.G(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                return -63;
            }
            return 3;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ra.A(" + n + ')');
        }
    }
    
    Class64_Sub19(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    Class64_Sub19(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                this.method550(-113, 77);
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ra.F(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method632(final int n, final int n2, final byte b, final int n3, final int n4, final int n5, final int n6) {
        try {
            if (~Class76_Sub8.anInt3778 >= ~n4 && Class3.anInt77 >= n2 && ~n5 <= ~Class98_Sub10_Sub38.anInt5753 && Class218.anInt1635 >= n6) {
                if (~n3 == 0xFFFFFFFE) {
                    Class64_Sub3.method565(n5, n6, n2, n4, n, -10194);
                }
                else {
                    Class98_Sub26.method1279(n5, n2, n3, n, n6, n4, true);
                }
            }
            else if (~n3 != 0xFFFFFFFE) {
                Class98_Sub10_Sub19.method1059(false, n4, n, n5, n3, n2, n6);
            }
            else {
                RuntimeException_Sub1.method4010(-31085, n6, n4, n5, n2, n);
            }
            if (b != -51) {
                Class64_Sub19.aShort3692 = 74;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ra.D(" + n + ',' + n2 + ',' + b + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (b < 118) {
                method632(5, 122, (byte)12, -14, -80, 53, -39);
            }
            if (super.anInt494 < 0 || ~super.anInt494 < -5) {
                super.anInt494 = this.method552(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ra.C(" + b + ')');
        }
    }
    
    static {
        Class64_Sub19.anInt3693 = 0;
        Class64_Sub19.aShort3692 = 256;
    }
}
