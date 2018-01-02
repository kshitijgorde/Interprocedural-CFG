// 
// Decompiled by Procyon v0.5.30
// 

final class Class44
{
    static boolean aBoolean378;
    static IncomingOpcode aClass58_379;
    static OutgoingOpcode aClass171_380;
    
    static final void method427(final int n, final int n2, final int n3) {
        try {
            if (n != -19181) {
                method427(-125, 127, -126);
            }
            if (Class64_Sub2.aClass279_3643 == Class4.aClass279_86) {
                if (!Class76_Sub2.requestFlag(0, 0, 1, -2, 0, n2, n3, false, 1)) {
                    Class76_Sub2.requestFlag(0, 0, 1, -3, n + 19181, n2, n3, false, 1);
                }
            }
            else if (!Class76_Sub2.requestFlag(0, 0, 1, -3, n ^ 0xFFFFB513, n2, n3, false, 1)) {
                Class76_Sub2.requestFlag(0, 0, 1, -2, 0, n2, n3, false, 1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dca.C(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static final String method428(final int n, final boolean b, final boolean b2) {
        try {
            if (b) {
                method429((byte)(-9));
            }
            if (!b2 || n < 0) {
                return Integer.toString(n);
            }
            return Class98_Sub10_Sub34.method1103(n, b2, 328, 10);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dca.A(" + n + ',' + b + ',' + b2 + ')');
        }
    }
    
    public static void method429(final byte b) {
        try {
            Class44.aClass58_379 = null;
            Class44.aClass171_380 = null;
            if (b <= 20) {
                Class44.aClass58_379 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "dca.B(" + b + ')');
        }
    }
    
    static {
        Class44.aBoolean378 = false;
        Class44.aClass58_379 = new IncomingOpcode(75, 3);
        Class44.aClass171_380 = new OutgoingOpcode(3, 16);
    }
}
