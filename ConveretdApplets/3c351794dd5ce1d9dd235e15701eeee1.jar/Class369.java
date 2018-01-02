// 
// Decompiled by Procyon v0.5.30
// 

final class Class369
{
    static int anInt3129;
    static boolean aBoolean3130;
    static float aFloat3131;
    static IncomingOpcode aClass58_3132;
    
    static final void method3952(final int n, final byte b) {
        try {
            Label_0034: {
                if (Class98_Sub46_Sub20_Sub2.anInt6317 == 1) {
                    Class257.anInt1946 = n;
                    if (!client.aBoolean3553) {
                        break Label_0034;
                    }
                }
                if (~Class98_Sub46_Sub20_Sub2.anInt6317 == 0xFFFFFFFD) {
                    Class31.anInt300 = n;
                }
            }
            if (b != -55) {
                method3952(27, (byte)(-126));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wea.C(" + n + ',' + b + ')');
        }
    }
    
    static int method3953(final int n, final int n2) {
        try {
            return n ^ n2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wea.A(" + n + ',' + n2 + ')');
        }
    }
    
    static final void method3954(final int n) {
        try {
            if (Class76.aClass28ArrayArray586 != null) {
                for (int n2 = 0; ~n2 > ~Class76.aClass28ArrayArray586.length; ++n2) {
                    for (int i = 0; i < Class76.aClass28ArrayArray586[n2].length; ++i) {
                        Class76.aClass28ArrayArray586[n2][i] = Class91.aClass28_722;
                    }
                }
            }
            if (n != 0) {
                method3954(-48);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wea.B(" + n + ')');
        }
    }
    
    public static void method3955(final int n) {
        try {
            Class369.aClass58_3132 = null;
            if (n <= 75) {
                method3954(91);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wea.D(" + n + ')');
        }
    }
    
    static {
        Class369.anInt3129 = 0;
        Class369.aBoolean3130 = false;
        Class369.aClass58_3132 = new IncomingOpcode(37, -2);
    }
}
