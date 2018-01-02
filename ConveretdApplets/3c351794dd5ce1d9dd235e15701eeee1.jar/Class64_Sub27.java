// 
// Decompiled by Procyon v0.5.30
// 

final class Class64_Sub27 extends Class64
{
    static IncomingOpcode aClass58_3715;
    static int anInt3716;
    
    static final Class93_Sub2 method663(final int n, final Class98_Sub22 class98_Sub22) {
        try {
            final Class93 method1716 = Class105.method1716(-1, class98_Sub22);
            if (n > -14) {
                return null;
            }
            return new Class93_Sub2(method1716.aClass63_3509, method1716.aClass110_3511, method1716.anInt3505, method1716.anInt3507, method1716.anInt3514, method1716.anInt3504, method1716.anInt3508, method1716.anInt3506, method1716.anInt3513, class98_Sub22.readInt(-2), class98_Sub22.readInt(-2), class98_Sub22.readShort((byte)127));
        }
        catch (RuntimeException ex) {
            throw method667(ex, "ve.G(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final void method551(final byte b) {
        try {
            if (super.anInt494 < 1 || ~super.anInt494 < -4) {
                super.anInt494 = this.method552(0);
            }
            if (b < 118) {
                Class64_Sub27.anInt3716 = -41;
            }
        }
        catch (RuntimeException ex) {
            throw method667(ex, "ve.C(" + b + ')');
        }
    }
    
    @Override
    final int method552(final int n) {
        try {
            if (n != 0) {
                Class64_Sub27.aClass58_3715 = null;
            }
            if (!super.aClass98_Sub27_495.method1289(-120).method2316(true)) {
                return 2;
            }
            return 3;
        }
        catch (RuntimeException ex) {
            throw method667(ex, "ve.A(" + n + ')');
        }
    }
    
    @Override
    final int method556(final int n, final int n2) {
        try {
            if (n2 != 29053) {
                Class64_Sub27.anInt3716 = -17;
            }
            return 1;
        }
        catch (RuntimeException ex) {
            throw method667(ex, "ve.F(" + n + ',' + n2 + ')');
        }
    }
    
    static final int method664(final int n, final int n2, final int n3, int n4, final int n5, final int n6) {
        try {
            if (Class78.aSArray594 == null) {
                return 0;
            }
            if (n3 < 59) {
                Class64_Sub27.aClass58_3715 = null;
            }
            if (n4 < 3) {
                final int n7 = n5 >> -1338599447;
                final int n8 = n >> -623073207;
                if (~n6 > -1 || ~n2 > -1 || n6 > -1 + Class165.anInt1276 || ~(Class98_Sub10_Sub7.anInt5572 - 1) > ~n2) {
                    return 0;
                }
                if (~n7 > -2 || n8 < 1 || n7 > Class165.anInt1276 - 1 || ~(-1 + Class98_Sub10_Sub7.anInt5572) > ~n8) {
                    return 0;
                }
                boolean b = (0x2 & Class281.aByteArrayArrayArray2117[1][n5 >> 117366825][n >> 1053911209]) != 0x0;
                if (~(n5 & 0x1FF) == -1 && ~(Class281.aByteArrayArrayArray2117[1][n7][n >> -174615703] & 0x2) == -1 == ((Class281.aByteArrayArrayArray2117[1][n7 - 1][n >> 772758441] & 0x2) != 0x0)) {
                    b = (~(0x2 & Class281.aByteArrayArrayArray2117[1][n6][n2]) != -1);
                }
                if (~(0x1FF & n) == -1 && (Class281.aByteArrayArrayArray2117[1][n5 >> -1641555127][n8 - 1] & 0x2) == 0x0 != ((0x2 & Class281.aByteArrayArrayArray2117[1][n5 >> -1729018327][n8]) == 0x0)) {
                    b = ((0x2 & Class281.aByteArrayArrayArray2117[1][n6][n2]) != 0x0);
                }
                if (b) {
                    ++n4;
                }
            }
            return Class78.aSArray594[n4].method3417(n5, n, true);
        }
        catch (RuntimeException ex) {
            throw method667(ex, "ve.I(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    public static void method665(final int n) {
        try {
            if (n != 2) {
                Class64_Sub27.anInt3716 = 3;
            }
            Class64_Sub27.aClass58_3715 = null;
        }
        catch (RuntimeException ex) {
            throw method667(ex, "ve.H(" + n + ')');
        }
    }
    
    @Override
    final void method550(final int n, final int anInt494) {
        try {
            super.anInt494 = anInt494;
        }
        catch (RuntimeException ex) {
            throw method667(ex, "ve.B(" + n + ',' + anInt494 + ')');
        }
    }
    
    Class64_Sub27(final int n, final Class98_Sub27 class98_Sub27) {
        super(n, class98_Sub27);
    }
    
    Class64_Sub27(final Class98_Sub27 class98_Sub27) {
        super(class98_Sub27);
    }
    
    final int method666(final byte b) {
        try {
            if (b <= 119) {
                return -75;
            }
            return super.anInt494;
        }
        catch (RuntimeException ex) {
            throw method667(ex, "ve.E(" + b + ')');
        }
    }
    
    static final RuntimeException_Sub1 method667(final Throwable t, final String s) {
        try {
            RuntimeException_Sub1 runtimeException_Sub1;
            if (!(t instanceof RuntimeException_Sub1)) {
                runtimeException_Sub1 = new RuntimeException_Sub1(t, s);
            }
            else {
                runtimeException_Sub1 = (RuntimeException_Sub1)t;
                final StringBuilder sb = new StringBuilder();
                final RuntimeException_Sub1 runtimeException_Sub2 = runtimeException_Sub1;
                runtimeException_Sub2.aString3202 = sb.append(runtimeException_Sub2.aString3202).append(' ').append(s).toString();
            }
            return runtimeException_Sub1;
        }
        catch (RuntimeException ex) {
            throw ex;
        }
    }
    
    static {
        Class64_Sub27.aClass58_3715 = new IncomingOpcode(16, -2);
    }
}
