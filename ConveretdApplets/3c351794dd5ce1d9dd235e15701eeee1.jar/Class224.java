// 
// Decompiled by Procyon v0.5.30
// 

class Class224
{
    static OutgoingOpcode aClass171_1684;
    static Class232 aClass232_1685;
    static Class326 aClass326_1686;
    
    public static void method2833(final byte b) {
        try {
            Class224.aClass232_1685 = null;
            if (b < -42) {
                Class224.aClass171_1684 = null;
                Class224.aClass326_1686 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "of.E(" + b + ')');
        }
    }
    
    static {
        Class224.aClass171_1684 = new OutgoingOpcode(57, -1);
        Class224.aClass232_1685 = new Class232();
    }
}
