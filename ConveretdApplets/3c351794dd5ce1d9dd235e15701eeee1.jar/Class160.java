// 
// Decompiled by Procyon v0.5.30
// 

final class Class160
{
    static Class98_Sub22 aClass98_Sub22_1257;
    static int anInt1258;
    static OutgoingOpcode aClass171_1259;
    
    static final void method2511(final int n) {
        try {
            synchronized (Class98_Sub46_Sub20_Sub2.aClass218Array6316) {
                if (n == 1350) {
                    for (int n2 = 0; Class98_Sub46_Sub20_Sub2.aClass218Array6316.length > n2; ++n2) {
                        Class98_Sub46_Sub20_Sub2.aClass218Array6316[n2] = new Class218();
                        Class1.anIntArray65[n2] = 0;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ko.C(" + n + ')');
        }
    }
    
    public static void method2512(final byte b) {
        try {
            Class160.aClass171_1259 = null;
            if (b == -119) {
                Class160.aClass98_Sub22_1257 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ko.B(" + b + ')');
        }
    }
    
    static final void method2513(final byte b, final int n, final int n2, final int n3, final int n4) {
        try {
            Label_0046: {
                if (~n2 < ~n3) {
                    Class333.method3761(n, Class97.anIntArrayArray814[n4], n3, n2, (byte)65);
                    if (!client.aBoolean3553) {
                        break Label_0046;
                    }
                }
                Class333.method3761(n, Class97.anIntArrayArray814[n4], n2, n3, (byte)(-127));
            }
            if (b >= -66) {
                return;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ko.A(" + b + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    static {
        Class160.aClass98_Sub22_1257 = new Class98_Sub22(1350);
        Class160.aClass171_1259 = new OutgoingOpcode(78, 11);
    }
}
