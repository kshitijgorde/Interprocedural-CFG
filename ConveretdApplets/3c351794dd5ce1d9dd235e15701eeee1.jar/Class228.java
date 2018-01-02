// 
// Decompiled by Procyon v0.5.30
// 

final class Class228
{
    int anInt1708;
    int anInt1709;
    private int anInt1710;
    private int anInt1711;
    int anInt1712;
    int anInt1713;
    int anInt1714;
    int anInt1715;
    private int anInt1716;
    private int anInt1717;
    
    final void method2860(final int anInt1711, final int n, final int n2, final int n3, final int n4, final int n5, final int anInt1712, final int n6, final int n7, final int n8, final int anInt1713) {
        try {
            this.anInt1711 = anInt1711;
            this.anInt1716 = n3 * n3;
            this.anInt1710 = anInt1713;
            this.anInt1717 = anInt1712;
            this.anInt1708 = n8 + this.anInt1710;
            this.anInt1714 = this.anInt1711 + n2;
            this.anInt1713 = n4 + this.anInt1710;
            if (n != -7409) {
                this.anInt1717 = 79;
            }
            this.anInt1715 = n7 + this.anInt1717;
            this.anInt1712 = this.anInt1711 - -n5;
            this.anInt1709 = this.anInt1717 - -n6;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oia.C(" + anInt1711 + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + anInt1712 + ',' + n6 + ',' + n7 + ',' + n8 + ',' + anInt1713 + ')');
        }
    }
    
    static final void method2861(final int n, int n2, final int n3, final int n4) {
        try {
            if (n4 == 18596) {
                n2 = n2 * Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4069.method641((byte)127) >> -1412563832;
                if (~n2 != -1 && ~n3 != 0x0) {
                    if (!Class151_Sub5.aBoolean4991 && ~Class144.anInt1169 != 0x0 && Class8.method188(false) && !Class168.method2534((byte)(-128))) {
                        Class151_Sub8.aClass98_Sub31_Sub2_5013 = Class83.method831(-59);
                        Class246_Sub3_Sub5_Sub2.method3098(Class111_Sub1.method2115(116, Class151_Sub8.aClass98_Sub31_Sub2_5013), true, 28643);
                    }
                    s_Sub1.method3434(Class61.aClass207_481, false, n2, n3, 0, n4 ^ 0xFFFFF7D1);
                    Class299_Sub2.method3523(255, -1, 0);
                    Class151_Sub5.aBoolean4991 = true;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oia.D(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static final void method2862(final int n) {
        try {
            Class191.anInt1480 = (Class76_Sub5.anInt3748 = (Class181.anInt1432 = (Class98_Sub10_Sub13.anInt5602 = 0)));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oia.B(" + n + ')');
        }
    }
    
    final boolean method2863(final int n, final int n2, final int n3, final int n4) {
        try {
            if (this.anInt1712 > n || ~n < ~this.anInt1714) {
                return false;
            }
            if (~n2 > ~this.anInt1713 || ~n2 < ~this.anInt1708) {
                return false;
            }
            if (this.anInt1715 > n3 || ~n3 < ~this.anInt1709) {
                return false;
            }
            if (n4 != 0) {
                this.method2860(69, -4, -88, -91, -62, 109, -98, 2, -57, 18, -119);
            }
            final int n5 = n - this.anInt1711;
            final int n6 = -this.anInt1717 + n3;
            return ~(n5 * n5 - -(n6 * n6)) > ~this.anInt1716;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oia.E(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static final boolean method2864(final int n, final int n2, final int n3) {
        try {
            return Class87.method854(n3, n + 28678, n2) && ((Class98_Sub5_Sub2.method969(n3, n2, 123) | (n2 & 0xB000) != 0x0 | Class64_Sub10.method594(n2, 6, n3)) || n != 55 || ((n3 & 0x37) == 0x0 & (Class151_Sub2.method2451(n2, 544, n3) | Class246_Sub1.method2967(n3, n2, (byte)91))));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oia.A(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    Class228(final int anInt1711, final int anInt1712, final int anInt1713, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        try {
            this.anInt1711 = anInt1711;
            this.anInt1717 = anInt1713;
            this.anInt1716 = n * n;
            this.anInt1710 = anInt1712;
            this.anInt1713 = this.anInt1710 - -n4;
            this.anInt1715 = n6 + this.anInt1717;
            this.anInt1709 = this.anInt1717 - -n7;
            this.anInt1714 = this.anInt1711 - -n3;
            this.anInt1708 = n5 + this.anInt1710;
            this.anInt1712 = n2 + this.anInt1711;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "oia.<init>(" + anInt1711 + ',' + anInt1712 + ',' + anInt1713 + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + n7 + ')');
        }
    }
}
