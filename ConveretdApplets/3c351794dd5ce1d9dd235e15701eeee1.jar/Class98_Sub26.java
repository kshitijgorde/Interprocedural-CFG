// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub26 extends Class98
{
    static int anInt4028;
    static IncomingOpcode aClass58_4029;
    static int[] anIntArray4030;
    int anInt4031;
    int anInt4032;
    
    static final int method1275(final String s, final boolean b) {
        try {
            if (b) {
                return -106;
            }
            return 2 + s.length();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kaa.E(" + ((s != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    static final int method1276(final int n) {
        try {
            if (n != -2) {
                Class98_Sub26.anIntArray4030 = null;
            }
            return Class284.anInt2162;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kaa.C(" + n + ')');
        }
    }
    
    static final void method1277(final int n, final String s) {
        try {
            System.exit(n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kaa.B(" + n + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method1278(final int n) {
        try {
            Class98_Sub26.anIntArray4030 = null;
            Class98_Sub26.aClass58_4029 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kaa.D(" + n + ')');
        }
    }
    
    static final void method1279(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final boolean b) {
        try {
            final int i = n3 + n;
            if (!b) {
                method1276(-2);
            }
            final int n7 = -n3 + n5;
            for (int n8 = n; i > n8; ++n8) {
                Class333.method3761(n4, Class97.anIntArrayArray814[n8], n6, n2, (byte)27);
            }
            final int n9 = n2 + -n3;
            for (int j = n5; j > n7; --j) {
                Class333.method3761(n4, Class97.anIntArrayArray814[j], n6, n2, (byte)81);
            }
            final int n10 = n3 + n6;
            for (int n11 = i; ~n11 >= ~n7; ++n11) {
                final int[] array = Class97.anIntArrayArray814[n11];
                Class333.method3761(n4, array, n6, n10, (byte)(-127));
                Class333.method3761(n4, array, n9, n2, (byte)(-126));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kaa.A(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + b + ')');
        }
    }
    
    Class98_Sub26(final int anInt4031, final int anInt4032) {
        try {
            this.anInt4031 = anInt4031;
            this.anInt4032 = anInt4032;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kaa.<init>(" + anInt4031 + ',' + anInt4032 + ')');
        }
    }
    
    static {
        Class98_Sub26.anInt4028 = 0;
        Class98_Sub26.aClass58_4029 = new IncomingOpcode(57, -2);
        Class98_Sub26.anIntArray4030 = new int[200];
    }
}
