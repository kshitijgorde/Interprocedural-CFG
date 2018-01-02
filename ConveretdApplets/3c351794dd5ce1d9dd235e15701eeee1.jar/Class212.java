// 
// Decompiled by Procyon v0.5.30
// 

final class Class212
{
    static int[] anIntArray1597;
    private Class79 aClass79_1598;
    static long aLong1599;
    static int anInt1600;
    private Class207 aClass207_1601;
    private Class207 aClass207_1602;
    static Class207 aClass207_1603;
    
    public static void method2776(final byte b) {
        try {
            if (b == -102) {
                Class212.aClass207_1603 = null;
                Class212.anIntArray1597 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "np.B(" + b + ')');
        }
    }
    
    final Class98_Sub46_Sub1 method2777(final int n, final int n2) {
        try {
            final Class98_Sub46_Sub1 class98_Sub46_Sub1 = (Class98_Sub46_Sub1)this.aClass79_1598.method802(-124, n2);
            if (class98_Sub46_Sub1 != null) {
                return class98_Sub46_Sub1;
            }
            byte[] array = null;
            Label_0064: {
                if (n2 >= 32768) {
                    array = this.aClass207_1602.method2745(0x7FFF & n2, 0, false);
                    if (!client.aBoolean3553) {
                        break Label_0064;
                    }
                }
                array = this.aClass207_1601.method2745(n2, 0, false);
            }
            final Class98_Sub46_Sub1 class98_Sub46_Sub2 = new Class98_Sub46_Sub1();
            if (n != 28559) {
                return null;
            }
            if (array != null) {
                class98_Sub46_Sub2.method1532(new Class98_Sub22(array), true);
            }
            if (~n2 <= -32769) {
                class98_Sub46_Sub2.method1531(n ^ 0xEF8F);
            }
            this.aClass79_1598.method805(n2, class98_Sub46_Sub2, (byte)(-80));
            return class98_Sub46_Sub2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "np.A(" + n + ',' + n2 + ')');
        }
    }
    
    Class212(final int n, final Class207 aClass207_1601, final Class207 aClass207_1602) {
        this.aClass79_1598 = new Class79(64);
        try {
            this.aClass207_1601 = aClass207_1601;
            this.aClass207_1602 = aClass207_1602;
            if (this.aClass207_1601 != null) {
                this.aClass207_1601.method2761(0, 0);
            }
            if (this.aClass207_1602 != null) {
                this.aClass207_1602.method2761(0, 0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "np.<init>(" + n + ',' + ((aClass207_1601 != null) ? "{...}" : "null") + ',' + ((aClass207_1602 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class212.anIntArray1597 = new int[5];
        Class212.anInt1600 = 0;
        Class212.aLong1599 = 20000000L;
    }
}
