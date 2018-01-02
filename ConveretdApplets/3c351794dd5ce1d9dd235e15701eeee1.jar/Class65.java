// 
// Decompiled by Procyon v0.5.30
// 

final class Class65
{
    static int anInt496;
    static char[] aCharArray497;
    static int anInt498;
    static IncomingOpcode aClass58_499;
    static Class293[] aClass293Array500;
    static int[] anIntArray501;
    static int anInt502;
    static int anInt503;
    static Class aClass504;
    
    static final void method678(final int anInt624, final boolean b) {
        try {
            if (!b) {
                Class81.anInt624 = anInt624;
                synchronized (PlayerUpdate.aClass79_3411) {
                    PlayerUpdate.aClass79_3411.method794(76);
                }
                synchronized (Class211.aClass79_1594) {
                    Class211.aClass79_1594.method794(19);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ef.C(" + anInt624 + ',' + b + ')');
        }
    }
    
    static final boolean method679(final int n, final int n2, final byte b, final int n3) {
        try {
            if (b < 66) {
                method678(-15, true);
            }
            boolean b2 = true;
            final Interface19 interface19 = (Interface19)Class21_Sub1.method268(n2, n, n3);
            if (interface19 != null) {
                b2 &= Class180.method2603((byte)76, interface19);
            }
            final Interface19 interface20 = (Interface19)Class97.method931(n2, n, n3, (Class65.aClass504 != null) ? Class65.aClass504 : (Class65.aClass504 = method681("Interface19")));
            if (interface20 != null) {
                b2 &= Class180.method2603((byte)76, interface20);
            }
            final Interface19 interface21 = (Interface19)Class253.method3177(n2, n, n3);
            if (interface21 != null) {
                b2 &= Class180.method2603((byte)76, interface21);
            }
            return b2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ef.B(" + n + ',' + n2 + ',' + b + ',' + n3 + ')');
        }
    }
    
    public static void method680(final byte b) {
        try {
            Class65.aClass293Array500 = null;
            Class65.aCharArray497 = null;
            Class65.aClass58_499 = null;
            Class65.anIntArray501 = null;
            if (b != -108) {
                method679(44, -79, (byte)121, 60);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ef.A(" + b + ')');
        }
    }
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ef.toString()");
        }
    }
    
    static Class method681(final String s) {
        try {
            return Class.forName(s);
        }
        catch (ClassNotFoundException ex) {
            throw new NoClassDefFoundError(ex.getMessage());
        }
    }
    
    static {
        Class65.anInt496 = 0;
        Class65.aCharArray497 = new char[] { '\u20ac', '\0', '\u201a', '\u0192', '\u201e', '\u2026', '\u2020', '\u2021', '\u02c6', '\u2030', '\u0160', '\u2039', '\u0152', '\0', '\u017d', '\0', '\0', '\u2018', '\u2019', '\u201c', '\u201d', '\u2022', '\u2013', '\u2014', '\u02dc', '\u2122', '\u0161', '\u203a', '\u0153', '\0', '\u017e', '\u0178' };
        Class65.aClass58_499 = new IncomingOpcode(78, 4);
        Class65.anInt502 = 0;
        Class65.anIntArray501 = new int[2048];
    }
}
