// 
// Decompiled by Procyon v0.5.30
// 

final class Class90
{
    int anInt718;
    static Class105 aClass105_719;
    char aChar720;
    static int anInt721;
    
    public static void method881(final byte b) {
        try {
            Class90.aClass105_719 = null;
            if (b != -27) {
                method883(88);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fn.E(" + b + ')');
        }
    }
    
    static final void method882(final int[] array, final long[] array2, final byte b) {
        try {
            Class117.method2169(array, array2, 0, array2.length - 1, false);
            if (b != 118) {
                Class90.aClass105_719 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fn.A(" + ((array != null) ? "{...}" : "null") + ',' + ((array2 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final Class246_Sub2 method883(final int n) {
        try {
            final Class246_Sub2 class246_Sub2 = (Class246_Sub2)Class138.aClass218_1084.method2805((byte)(-72));
            if (class246_Sub2 != null) {
                --Class98_Sub50.anInt4294;
                return class246_Sub2;
            }
            if (n > 0) {
                return null;
            }
            return new Class246_Sub2();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fn.D(" + n + ')');
        }
    }
    
    private final void method884(final int n, final Class98_Sub22 class98_Sub22, final byte b) {
        try {
            Label_0045: {
                if (~n == 0xFFFFFFFE) {
                    this.aChar720 = Class64_Sub7.method576(class98_Sub22.readSignedByte((byte)(-19)), (byte)122);
                    if (!client.aBoolean3553) {
                        break Label_0045;
                    }
                }
                if (~n == 0xFFFFFFFD) {
                    this.anInt718 = 0;
                }
            }
            if (b != -37) {
                this.anInt718 = -63;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fn.B(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public Class90() {
        this.anInt718 = 1;
    }
    
    final void method885(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            if (n == -23453) {
                while (true) {
                    final int unsignedByte = class98_Sub22.readUnsignedByte((byte)50);
                    if (unsignedByte == 0) {
                        break;
                    }
                    this.method884(unsignedByte, class98_Sub22, (byte)(-37));
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fn.C(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static {
        Class90.aClass105_719 = new Class105("", 15);
        Class90.anInt721 = 4;
    }
}
