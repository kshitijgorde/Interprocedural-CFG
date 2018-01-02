// 
// Decompiled by Procyon v0.5.30
// 

final class Class306
{
    static Class332[] aClass332Array2557;
    Class377 aClass377_2558;
    private String aString2559;
    char aChar2560;
    static int anInt2561;
    static Class376[] aClass376Array2562;
    private int anInt2563;
    private Class377 aClass377_2564;
    static Class348 aClass348_2565;
    static int anInt2566;
    char aChar2567;
    
    final void method3593(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)115);
                if (unsignedByte == 0) {
                    break;
                }
                this.method3600(n - 4, class98_Sub22, unsignedByte);
            }
            if (n != 4) {
                Class306.anInt2561 = 4;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sp.B(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final String method3594(final int n, final byte b) {
        try {
            if (this.aClass377_2558 == null) {
                return this.aString2559;
            }
            final Class98_Sub15 class98_Sub15 = (Class98_Sub15)this.aClass377_2558.method3990(n, -1);
            if (class98_Sub15 == null) {
                return this.aString2559;
            }
            if (b < 20) {
                method3597(126);
            }
            return class98_Sub15.aString3917;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sp.J(" + n + ',' + b + ')');
        }
    }
    
    static final Class350[] method3595(final byte b) {
        try {
            if (b < 57) {
                Class306.aClass376Array2562 = null;
            }
            return new Class350[] { Class98_Sub10_Sub31.aClass350_5716, Class98_Sub27.aClass350_4074, Class102.aClass350_888 };
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sp.D(" + b + ')');
        }
    }
    
    final boolean method3596(final int n, final byte b) {
        try {
            if (this.aClass377_2558 == null) {
                return false;
            }
            if (this.aClass377_2564 == null) {
                this.method3599((byte)(-59));
            }
            return this.aClass377_2564.method3990(n, -1) != null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sp.H(" + n + ',' + b + ')');
        }
    }
    
    public static void method3597(final int n) {
        try {
            if (n > 84) {
                Class306.aClass376Array2562 = null;
                Class306.aClass332Array2557 = null;
                Class306.aClass348_2565 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sp.A(" + n + ')');
        }
    }
    
    final int method3598(final int n, final int n2) {
        try {
            if (this.aClass377_2558 == null) {
                return this.anInt2563;
            }
            final Class98_Sub34 class98_Sub34 = (Class98_Sub34)this.aClass377_2558.method3990(n, -1);
            if (n2 != -28629) {
                return -113;
            }
            if (class98_Sub34 == null) {
                return this.anInt2563;
            }
            return class98_Sub34.anInt4126;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sp.G(" + n + ',' + n2 + ')');
        }
    }
    
    private final void method3599(final byte b) {
        try {
            this.aClass377_2564 = new Class377(this.aClass377_2558.method3997((byte)106));
            Class98_Sub34 class98_Sub34 = (Class98_Sub34)this.aClass377_2558.method3998(102);
            if (b < -19) {
                while (class98_Sub34 != null) {
                    this.aClass377_2564.method3996(new Class98_Sub34((int)class98_Sub34.aLong832), class98_Sub34.anInt4126, -1);
                    class98_Sub34 = (Class98_Sub34)this.aClass377_2558.method3995(-1);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sp.C(" + b + ')');
        }
    }
    
    private final void method3600(final int n, final Class98_Sub22 class98_Sub22, final int n2) {
        try {
            if (n2 == 1) {
                this.aChar2560 = Class64_Sub7.method576(class98_Sub22.readSignedByte((byte)(-19)), (byte)121);
            }
            else if (n2 != 2) {
                if (~n2 == 0xFFFFFFFC) {
                    this.aString2559 = class98_Sub22.readString((byte)84);
                }
                else if (n2 == 4) {
                    this.anInt2563 = class98_Sub22.readInt(-2);
                }
                else if (~n2 == 0xFFFFFFFA || ~n2 == 0xFFFFFFF9) {
                    final int i = class98_Sub22.readShort((byte)127);
                    this.aClass377_2558 = new Class377(Class48.method453(423660257, i));
                    for (int n3 = 0; i > n3; ++n3) {
                        final int int1 = class98_Sub22.readInt(-2);
                        Class98 class98;
                        if (~n2 == 0xFFFFFFFA) {
                            class98 = new Class98_Sub15(class98_Sub22.readString((byte)84));
                        }
                        else {
                            class98 = new Class98_Sub34(class98_Sub22.readInt(-2));
                        }
                        this.aClass377_2558.method3996(class98, int1, ~n);
                    }
                }
            }
            else {
                this.aChar2567 = Class64_Sub7.method576(class98_Sub22.readSignedByte((byte)(-19)), (byte)125);
            }
            if (n != 0) {
                Class306.aClass376Array2562 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sp.E(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    private final void method3601(final int n) {
        try {
            this.aClass377_2564 = new Class377(this.aClass377_2558.method3997((byte)53));
            for (Class98_Sub15 class98_Sub15 = (Class98_Sub15)this.aClass377_2558.method3998(n + 111); class98_Sub15 != null; class98_Sub15 = (Class98_Sub15)this.aClass377_2558.method3995(n + 3)) {
                this.aClass377_2564.method3996(new Class98_Sub41(class98_Sub15.aString3917, (int)class98_Sub15.aLong832), Class145.method2313((byte)(-121), class98_Sub15.aString3917), -1);
            }
            if (n != -4) {
                method3597(-68);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sp.F(" + n + ')');
        }
    }
    
    final boolean method3602(final String s, final int n) {
        try {
            if (this.aClass377_2558 == null) {
                return false;
            }
            if (this.aClass377_2564 == null) {
                this.method3601(-4);
            }
            Class98_Sub41 class98_Sub41 = (Class98_Sub41)this.aClass377_2564.method3990(Class145.method2313((byte)(-124), s), -1);
            if (n != -16972) {
                return false;
            }
            while (class98_Sub41 != null) {
                if (class98_Sub41.aString4201.equals(s)) {
                    return true;
                }
                class98_Sub41 = (Class98_Sub41)this.aClass377_2564.method3993(126);
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "sp.I(" + ((s != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public Class306() {
        this.aString2559 = "null";
    }
    
    static {
        Class306.aClass376Array2562 = new Class376[2048];
        Class306.anInt2566 = 0;
        Class306.aClass348_2565 = new Class348(4, 1, 1, 1);
    }
}
