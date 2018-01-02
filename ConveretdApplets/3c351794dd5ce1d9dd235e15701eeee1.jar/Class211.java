import java.awt.Rectangle;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class211
{
    static boolean aBoolean1593;
    static Class79 aClass79_1594;
    static IncomingOpcode aClass58_1595;
    static IncomingOpcode aClass58_1596;
    
    public static void method2774(final int n) {
        try {
            if (n != 79) {
                method2774(-80);
            }
            Class211.aClass79_1594 = null;
            Class211.aClass58_1596 = null;
            Class211.aClass58_1595 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nl.B(" + n + ')');
        }
    }
    
    static final void method2775(final Rectangle[] array, final int n, final int n2) throws Exception_Sub1 {
        try {
            Label_0040: {
                if (Class98_Sub46.anInt4261 != 1) {
                    Class154.aHa1231.a(array, n, 0, 0);
                    if (!client.aBoolean3553) {
                        break Label_0040;
                    }
                }
                Class154.aHa1231.a(array, n, Class98_Sub46_Sub13_Sub2.anInt6309, Class272.anInt2037);
            }
            if (n2 != 260) {
                Class211.aClass58_1595 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nl.A(" + ((array != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ')');
        }
    }
    
    static {
        Class211.aBoolean1593 = false;
        Class211.aClass79_1594 = new Class79(260);
        Class211.aClass58_1595 = new IncomingOpcode(7, 8);
        Class211.aClass58_1596 = new IncomingOpcode(79, 0);
    }
}
