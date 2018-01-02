// 
// Decompiled by Procyon v0.5.30
// 

final class Class199
{
    boolean aBoolean1526;
    boolean aBoolean1527;
    Class32 aClass32_1528;
    int anInt1529;
    int anInt1530;
    static Class32 aClass32_1531;
    int anInt1532;
    static OutgoingOpcode aClass171_1533;
    int anInt1534;
    int anInt1535;
    int anInt1536;
    int anInt1537;
    boolean aBoolean1538;
    static int anInt1539;
    int anInt1540;
    static int anInt1541;
    int anInt1542;
    
    static final void method2685(final int n, final byte b) {
        try {
            if (n != -15) {
                Class199.anInt1541 = -85;
            }
            if (OutputStream_Sub2.aByteArrayArrayArray41 == null) {
                OutputStream_Sub2.aByteArrayArrayArray41 = new byte[4][Class165.anInt1276][Class98_Sub10_Sub7.anInt5572];
            }
            for (int i = 0; i < 4; ++i) {
                for (int n2 = 0; ~n2 > ~Class165.anInt1276; ++n2) {
                    for (int n3 = 0; ~n3 > ~Class98_Sub10_Sub7.anInt5572; ++n3) {
                        OutputStream_Sub2.aByteArrayArrayArray41[i][n2][n3] = b;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nba.F(" + n + ',' + b + ')');
        }
    }
    
    static final void method2686(final String s, final byte b) {
        try {
            System.out.println("Error: " + Class76_Sub9.method765("\n", 4185, "%0a", s));
            if (b != -80) {
                Class199.anInt1541 = -21;
            }
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    public static void method2687(final boolean b) {
        try {
            Class199.aClass32_1531 = null;
            if (b) {
                Class199.aClass32_1531 = null;
            }
            Class199.aClass171_1533 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nba.D(" + b + ')');
        }
    }
    
    final void method2688(final int n, final Class98_Sub22 class98_Sub22) {
        try {
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)126);
                if (unsignedByte == 0) {
                    break;
                }
                this.method2689(2818, unsignedByte, class98_Sub22);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nba.B(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method2689(final int n, final int n2, final Class98_Sub22 class98_Sub22) {
        try {
            if (~n2 != 0xFFFFFFFE) {
                if (~n2 == 0xFFFFFFFD) {
                    this.anInt1542 = class98_Sub22.readUnsignedByte((byte)(-108));
                }
                else if (~n2 == 0xFFFFFFFC) {
                    this.anInt1542 = class98_Sub22.readShort((byte)127);
                    if (~this.anInt1542 == 0xFFFF0000) {
                        this.anInt1542 = -1;
                    }
                }
                else if (~n2 != 0xFFFFFFFA) {
                    if (n2 == 7) {
                        this.anInt1540 = Class64_Sub24.method652(class98_Sub22.method1186(n ^ 0xFFFFF481), n - 2733);
                    }
                    else if (n2 == 8) {
                        this.aClass32_1528.anInt312 = this.anInt1536;
                    }
                    else if (~n2 == 0xFFFFFFF6) {
                        this.anInt1529 = class98_Sub22.readShort((byte)127) << -242726558;
                    }
                    else if (~n2 == 0xFFFFFFF5) {
                        this.aBoolean1538 = false;
                    }
                    else if (n2 == 11) {
                        this.anInt1535 = class98_Sub22.readUnsignedByte((byte)(-118));
                    }
                    else if (~n2 == 0xFFFFFFF3) {
                        this.aBoolean1526 = true;
                    }
                    else if (n2 == 13) {
                        this.anInt1532 = class98_Sub22.method1186(-128);
                    }
                    else if (~n2 != 0xFFFFFFF1) {
                        if (~n2 == 0xFFFFFFEF) {
                            this.anInt1534 = class98_Sub22.readUnsignedByte((byte)12);
                        }
                    }
                    else {
                        this.anInt1530 = class98_Sub22.readUnsignedByte((byte)(-127)) << 33077314;
                    }
                }
                else {
                    this.aBoolean1527 = false;
                }
            }
            else {
                this.anInt1537 = Class64_Sub24.method652(class98_Sub22.method1186(n - 2942), 76);
            }
            if (n != 2818) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nba.E(" + n + ',' + n2 + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final boolean method2690(final int n, final int n2) {
        try {
            return n2 == 8 && (~n == 0xFFFFFFFB || n == 8 || ~n == 0xFFFFFFF4);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nba.C(" + n + ',' + n2 + ')');
        }
    }
    
    public Class199() {
        this.anInt1530 = 64;
        this.anInt1532 = 1190717;
        this.aBoolean1526 = false;
        this.aBoolean1527 = true;
        this.anInt1534 = 127;
        this.anInt1537 = 0;
        this.anInt1535 = 8;
        this.anInt1540 = -1;
        this.anInt1529 = 512;
        this.aBoolean1538 = true;
        this.anInt1542 = -1;
    }
    
    final void method2691(final byte b) {
        try {
            this.anInt1535 = (this.anInt1535 << -1953787544 | this.anInt1536);
            if (b != 80) {
                method2687(true);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nba.A(" + b + ')');
        }
    }
    
    static {
        Class199.aClass171_1533 = new OutgoingOpcode(63, -1);
        Class199.anInt1541 = -1;
    }
}
