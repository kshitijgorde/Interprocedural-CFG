// 
// Decompiled by Procyon v0.5.30
// 

final class Class150
{
    static OutgoingOpcode aClass171_1209;
    static IncomingOpcode aClass58_1210;
    static int anInt1211;
    static IncomingOpcode aClass58_1212;
    
    public static void method2436(final boolean b) {
        try {
            Class150.aClass58_1210 = null;
            Class150.aClass171_1209 = null;
            Class150.aClass58_1212 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kca.A(" + b + ')');
        }
    }
    
    static final int method2437(final byte b, final int n) {
        try {
            return n >>> -54059832;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "kca.B(" + b + ',' + n + ')');
        }
    }
    
    static {
        Class150.aClass171_1209 = new OutgoingOpcode(19, 2);
        Class150.anInt1211 = 0;
        Class150.aClass58_1210 = new IncomingOpcode(49, 2);
        Class150.aClass58_1212 = new IncomingOpcode(31, -2);
    }
}
