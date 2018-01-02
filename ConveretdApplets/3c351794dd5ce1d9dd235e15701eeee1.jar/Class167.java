// 
// Decompiled by Procyon v0.5.30
// 

final class Class167
{
    static Class243[] aClass243Array1281;
    static int anInt1282;
    int anInt1283;
    static Class148 aClass148_1284;
    static Object anObject1285;
    static int anInt1286;
    
    final void method2527(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            while (true) {
                final int unsignedByte = class98_Sub22.readUnsignedByte((byte)80);
                if (~unsignedByte == -1) {
                    break;
                }
                this.method2530(unsignedByte, class98_Sub22, n ^ 0xFFFFFFFC);
            }
            if (n != -2) {
                this.method2527(null, -116);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ld.B(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public static void method2528(final byte b) {
        try {
            Class167.aClass148_1284 = null;
            Class167.anObject1285 = null;
            Class167.aClass243Array1281 = null;
            if (b < 82) {
                method2528((byte)(-25));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ld.A(" + b + ')');
        }
    }
    
    static final void method2529(final Class246_Sub3 class246_Sub3, final boolean b, final boolean aBoolean5082) {
        class246_Sub3.aBoolean5082 = aBoolean5082;
        if (Class375.aBoolean3170) {
            if (b) {
                Class98_Sub43_Sub3.aClass245Array5922[Class98_Sub43_Sub3.aClass245Array5922.length - 1].method2960(class246_Sub3, 0);
            }
            else {
                final int method2692 = Class200.method2692(class246_Sub3.anInt5085);
                final int n = Class302.anIntArray2521[2] * class246_Sub3.method2985(false) / class246_Sub3.anInt5083;
                final int method2693 = Class200.method2692(class246_Sub3.anInt5085 - n);
                final int method2694 = Class200.method2692(class246_Sub3.anInt5085 + n);
                if (method2693 == method2694) {
                    Class98_Sub43_Sub3.aClass245Array5922[method2692].method2960(class246_Sub3, 0);
                }
                else if (method2694 - method2693 == 1) {
                    Class98_Sub43_Sub3.aClass245Array5922[Class18.anInt212 + method2693].method2960(class246_Sub3, 0);
                }
                else {
                    Class98_Sub43_Sub3.aClass245Array5922[Class98_Sub43_Sub3.aClass245Array5922.length - 1].method2960(class246_Sub3, 0);
                }
            }
        }
        else {
            Class289.method3407(class246_Sub3, za_Sub1.aClass98_Sub5Array6077);
        }
    }
    
    public Class167() {
        this.anInt1283 = 0;
    }
    
    private final void method2530(final int n, final Class98_Sub22 class98_Sub22, final int n2) {
        try {
            if (~n == 0xFFFFFFFA) {
                this.anInt1283 = class98_Sub22.readShort((byte)127);
            }
            if (n2 != 2) {
                method2531(null, -110);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ld.D(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n2 + ')');
        }
    }
    
    static final byte[] method2531(final String s, final int n) {
        try {
            final int length = s.length();
            if (~length == -1) {
                return new byte[0];
            }
            final int n2 = 3 + length & 0xFFFFFFFC;
            int n3 = n2 / 4 * 3;
            if (n != 12705) {
                return null;
            }
            Label_0109: {
                if (~length >= ~(n2 - 2) || Class64_Sub6.method574(112, s.charAt(n2 - 2)) == -1) {
                    n3 -= 2;
                    if (!client.aBoolean3553) {
                        break Label_0109;
                    }
                }
                if (~length >= ~(-1 + n2) || Class64_Sub6.method574(74, s.charAt(n2 - 1)) == -1) {
                    --n3;
                }
            }
            final byte[] array = new byte[n3];
            Class98_Sub10_Sub37.method1115(0, array, false, s);
            return array;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ld.C(" + ((s != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static {
        Class167.aClass243Array1281 = new Class243[4];
        Class167.anInt1286 = -1;
        Class167.anInt1282 = 0;
        Class167.aClass148_1284 = new Class148();
    }
}
