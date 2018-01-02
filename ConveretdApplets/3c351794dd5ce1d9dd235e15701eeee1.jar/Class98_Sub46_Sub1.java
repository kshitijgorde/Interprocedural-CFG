// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub1 extends Class98_Sub46
{
    String aString5941;
    int[] anIntArray5942;
    static boolean aBoolean5943;
    int[] anIntArray5944;
    static int anInt5945;
    char[] aCharArray5946;
    char[] aCharArray5947;
    static Class172[][][] aClass172ArrayArrayArray5948;
    static int anInt5949;
    
    private final void method1527(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            if (b <= 7) {
                this.method1527(50, null, (byte)94);
            }
            if (~n != 0xFFFFFFFE) {
                if (~n == 0xFFFFFFFD) {
                    final int unsignedByte = class98_Sub22.readUnsignedByte((byte)30);
                    this.aCharArray5946 = new char[unsignedByte];
                    this.anIntArray5944 = new int[unsignedByte];
                    for (int i = 0; i < unsignedByte; ++i) {
                        this.anIntArray5944[i] = class98_Sub22.readShort((byte)127);
                        final byte signedByte = class98_Sub22.readSignedByte((byte)(-19));
                        this.aCharArray5946[i] = ((~signedByte != -1) ? Class64_Sub7.method576(signedByte, (byte)123) : '\0');
                    }
                }
                else if (n == 3) {
                    final int unsignedByte2 = class98_Sub22.readUnsignedByte((byte)45);
                    this.anIntArray5942 = new int[unsignedByte2];
                    this.aCharArray5947 = new char[unsignedByte2];
                    for (int n2 = 0; ~n2 > ~unsignedByte2; ++n2) {
                        this.anIntArray5942[n2] = class98_Sub22.readShort((byte)127);
                        final byte signedByte2 = class98_Sub22.readSignedByte((byte)(-19));
                        this.aCharArray5947[n2] = ((signedByte2 == 0) ? '\0' : Class64_Sub7.method576(signedByte2, (byte)125));
                    }
                }
            }
            else {
                this.aString5941 = class98_Sub22.readString((byte)84);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aw.F(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final int method1528(final int n, final char c) {
        try {
            if (this.anIntArray5944 == null) {
                return -1;
            }
            for (int i = 0; i < this.anIntArray5944.length; ++i) {
                if (c == this.aCharArray5946[i]) {
                    return this.anIntArray5944[i];
                }
            }
            if (n != -1) {
                this.anIntArray5942 = null;
            }
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aw.C(" + n + ',' + c + ')');
        }
    }
    
    final int method1529(final char c, final byte b) {
        try {
            if (this.anIntArray5942 == null) {
                return -1;
            }
            for (int n = 0; this.anIntArray5942.length > n; ++n) {
                if (c == this.aCharArray5947[n]) {
                    return this.anIntArray5942[n];
                }
            }
            if (b > -119) {
                return 91;
            }
            return -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aw.E(" + c + ',' + b + ')');
        }
    }
    
    public static void method1530(final boolean b) {
        try {
            if (!b) {
                Class98_Sub46_Sub1.anInt5949 = 117;
            }
            Class98_Sub46_Sub1.aClass172ArrayArrayArray5948 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aw.A(" + b + ')');
        }
    }
    
    final void method1531(final int n) {
        try {
            if (n != 32768) {
                Class98_Sub46_Sub1.anInt5945 = 115;
            }
            if (this.anIntArray5942 != null) {
                for (int i = 0; i < this.anIntArray5942.length; ++i) {
                    this.anIntArray5942[i] = Class41.method366(this.anIntArray5942[i], 32768);
                }
            }
            if (this.anIntArray5944 != null) {
                for (int n2 = 0; ~this.anIntArray5944.length < ~n2; ++n2) {
                    this.anIntArray5944[n2] = Class41.method366(this.anIntArray5944[n2], 32768);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aw.B(" + n + ')');
        }
    }
    
    final void method1532(final Class98_Sub22 class98_Sub22, final boolean b) {
        try {
            if (!b) {
                Class98_Sub46_Sub1.aClass172ArrayArrayArray5948 = null;
            }
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)29);
                if (unsignedByte == 0) {
                    break;
                }
                this.method1527(unsignedByte, class98_Sub22, (byte)61);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "aw.D(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static {
        Class98_Sub46_Sub1.anInt5949 = 0;
    }
}
