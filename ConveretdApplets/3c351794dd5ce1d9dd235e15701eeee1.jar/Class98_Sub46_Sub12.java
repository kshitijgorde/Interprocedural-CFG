// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub46_Sub12 extends Class98_Sub46
{
    private Class377 aClass377_6033;
    static boolean[][] aBooleanArrayArray6034;
    static long aLong6035;
    
    final int method1585(final int n, final boolean b, final int n2) {
        try {
            if (this.aClass377_6033 == null) {
                return n2;
            }
            final Class98_Sub34 class98_Sub34 = (Class98_Sub34)this.aClass377_6033.method3990(n, -1);
            if (!b) {
                this.method1586(62, (byte)80, null);
            }
            if (class98_Sub34 == null) {
                return n2;
            }
            return class98_Sub34.anInt4126;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hd.B(" + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    final String method1586(final int n, final byte b, final String s) {
        try {
            if (this.aClass377_6033 == null) {
                return s;
            }
            if (b != -19) {
                method1587((byte)49);
            }
            final Class98_Sub15 class98_Sub15 = (Class98_Sub15)this.aClass377_6033.method3990(n, b ^ 0x12);
            if (class98_Sub15 == null) {
                return s;
            }
            return class98_Sub15.aString3917;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hd.C(" + n + ',' + b + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method1587(final byte b) {
        try {
            Class98_Sub46_Sub12.aBooleanArrayArray6034 = null;
            if (b > -67) {
                Class98_Sub46_Sub12.aLong6035 = 71L;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hd.D(" + b + ')');
        }
    }
    
    final void method1588(final int n, final Class98_Sub22 class98_Sub22) {
        try {
            if (n != 0) {
                Class98_Sub46_Sub12.aBooleanArrayArray6034 = null;
            }
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-98));
                if (unsignedByte == 0) {
                    break;
                }
                this.method1589(unsignedByte, true, class98_Sub22);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hd.A(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final void method1589(final int n, final boolean b, final Class98_Sub22 class98_Sub22) {
        try {
            if (n == 249) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)(-128));
                if (this.aClass377_6033 == null) {
                    this.aClass377_6033 = new Class377(Class48.method453(423660257, unsignedByte));
                }
                for (int n2 = 0; ~unsignedByte < ~n2; ++n2) {
                    final boolean b2 = ~class98_Sub22.readUnsignedByte((byte)41) == 0xFFFFFFFE;
                    final int method1186 = class98_Sub22.method1186(-124);
                    Class98 class98;
                    if (b2) {
                        class98 = new Class98_Sub15(class98_Sub22.readString((byte)84));
                    }
                    else {
                        class98 = new Class98_Sub34(class98_Sub22.readInt(-2));
                    }
                    this.aClass377_6033.method3996(class98, method1186, -1);
                }
            }
            if (!b) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hd.E(" + n + ',' + b + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class98_Sub46_Sub12.aBooleanArrayArray6034 = new boolean[][] { new boolean[4], new boolean[4], { false, false, true, false }, { false, false, true, false }, { false, false, true, false }, { false, false, true, false }, { true, false, true, false }, { true, false, false, true }, { true, false, false, true }, new boolean[4], new boolean[4], new boolean[4], new boolean[4] };
        Class98_Sub46_Sub12.aLong6035 = 0L;
    }
}
