import java.util.Hashtable;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class124
{
    int anInt1012;
    Interface21[] anInterface21Array1013;
    int anInt1014;
    static Hashtable aHashtable1015;
    
    static final int method2212(final boolean b, final byte b2, final int n, final int n2) {
        try {
            if (b2 != -96) {
                method2212(true, (byte)(-78), 7, 22);
            }
            final Class98_Sub3 method669 = Class64_Sub28.method669(n, b, 6);
            if (method669 == null) {
                return 0;
            }
            if (n2 < 0 || ~n2 <= ~method669.anIntArray3823.length) {
                return 0;
            }
            return method669.anIntArray3823[n2];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ie.A(" + b + ',' + b2 + ',' + n + ',' + n2 + ')');
        }
    }
    
    private final Interface21 method2213(final Class113 class113, final Class98_Sub22 class98_Sub22, final int n) {
        try {
            if (class113 == Class100.aClass113_840) {
                return Class298.method3503(class98_Sub22, -22);
            }
            if (class113 == Class47.aClass113_399) {
                return Class246_Sub10.method3141(class98_Sub22, -88);
            }
            if (class113 == Class365.aClass113_3109) {
                return Class98_Sub40.method1469(-2, class98_Sub22);
            }
            if (Class98_Sub10_Sub3.aClass113_5546 == class113) {
                return Class138.method2277(class98_Sub22, n ^ 0xFFFFDDE6);
            }
            if (n != -8829) {
                this.method2215(87, null);
            }
            if (class113 == Class280.aClass113_2111) {
                return ha.method1796(9342, class98_Sub22);
            }
            if (class113 == Class137.aClass113_1078) {
                return Class64_Sub27.method663(-36, class98_Sub22);
            }
            if (class113 == Class98_Sub44.aClass113_4245) {
                return Class338.method3781((byte)94, class98_Sub22);
            }
            if (Class308.aClass113_2582 == class113) {
                return Class246_Sub3_Sub4.method3024(class98_Sub22, true);
            }
            if (Class4.aClass113_80 == class113) {
                return Class98_Sub46_Sub19.method1634(class98_Sub22, 93);
            }
            if (Class18.aClass113_210 == class113) {
                return Class362.method3924(1, class98_Sub22);
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ie.D(" + ((class113 != null) ? "{...}" : "null") + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public static void method2214(final int n) {
        try {
            if (n != 0) {
                method2214(-96);
            }
            Class124.aHashtable1015 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ie.C(" + n + ')');
        }
    }
    
    final void method2215(final int n, final Class98_Sub22 class98_Sub22) {
        try {
            this.anInt1012 = class98_Sub22.method1186(-124);
            this.anInt1014 = class98_Sub22.readShort((byte)127);
            this.anInterface21Array1013 = new Interface21[class98_Sub22.readUnsignedByte((byte)124)];
            final Class113[] method476 = Class48_Sub2_Sub1.method476(false);
            for (int n2 = n; ~this.anInterface21Array1013.length < ~n2; ++n2) {
                this.anInterface21Array1013[n2] = this.method2213(method476[class98_Sub22.readUnsignedByte((byte)(-120))], class98_Sub22, -8829);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ie.B(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class124.aHashtable1015 = new Hashtable();
    }
}
