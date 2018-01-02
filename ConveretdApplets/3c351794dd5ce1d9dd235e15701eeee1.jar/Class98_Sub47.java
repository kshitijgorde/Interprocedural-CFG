// 
// Decompiled by Procyon v0.5.30
// 

final class Class98_Sub47 extends Class98
{
    int anInt4266;
    int anInt4267;
    int anInt4268;
    int anInt4269;
    static IncomingOpcode aClass58_4270;
    int anInt4271;
    int anInt4272;
    static Class332 aClass332_4273;
    static Class377 aClass377_4274;
    boolean aBoolean4275;
    static int anInt4276;
    
    static final void method1658(final int n, final int n2, final int n3, final int n4, final int n5, final int n6) {
        try {
            if (n5 == n3) {
                Class64_Sub17.method620(n, n3, true, n2, n6);
            }
            else {
                if (n4 != 16977) {
                    Class98_Sub47.aClass58_4270 = null;
                }
                if (~Class76_Sub8.anInt3778 >= ~(-n3 + n2) && n2 + n3 <= Class3.anInt77 && -n5 + n >= Class98_Sub10_Sub38.anInt5753 && ~Class218.anInt1635 <= ~(n + n5)) {
                    Class284.method3362(n, n3, n6, (byte)(-119), n2, n5);
                }
                else {
                    Class40.method364(n5, n2, n3, n, n4 - 17066, n6);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uca.B(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ')');
        }
    }
    
    public static void method1659(final int n) {
        try {
            Class98_Sub47.aClass58_4270 = null;
            Class98_Sub47.aClass332_4273 = null;
            Class98_Sub47.aClass377_4274 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uca.A(" + n + ')');
        }
    }
    
    Class98_Sub47(final int anInt4268) {
        this.anInt4268 = -1;
        this.aBoolean4275 = false;
        try {
            this.anInt4268 = anInt4268;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "uca.<init>(" + anInt4268 + ')');
        }
    }
    
    static {
        Class98_Sub47.aClass58_4270 = new IncomingOpcode(118, 1);
        Class98_Sub47.aClass377_4274 = new Class377(16);
        Class98_Sub47.anInt4276 = 0;
    }
}
