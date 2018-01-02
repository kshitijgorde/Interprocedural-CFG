// 
// Decompiled by Procyon v0.5.30
// 

final class Class99
{
    static boolean aBoolean838;
    
    static final boolean method1686(final int n, final int n2, final boolean b) {
        try {
            if (b) {
                Class99.aBoolean838 = true;
            }
            return (Class206.method2725(32768, n, n2) | (0x800 & n) != 0x0) || Class98_Sub27.method1292(n2, (byte)121, n);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ge.A(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    static final void method1687(final Class246_Sub3_Sub4 class246_Sub3_Sub4, final boolean b) {
        for (short aShort6158 = class246_Sub3_Sub4.aShort6158; aShort6158 <= class246_Sub3_Sub4.aShort6160; ++aShort6158) {
            for (short aShort6159 = class246_Sub3_Sub4.aShort6157; aShort6159 <= class246_Sub3_Sub4.aShort6159; ++aShort6159) {
                final Class172 class172 = Class98_Sub46_Sub1.aClass172ArrayArrayArray5948[class246_Sub3_Sub4.aByte5088][aShort6158][aShort6159];
                if (class172 != null) {
                    Class154 class173 = class172.aClass154_1325;
                    Class154 class174 = null;
                    while (class173 != null) {
                        if (class173.aClass246_Sub3_Sub4_1232 == class246_Sub3_Sub4) {
                            if (class174 != null) {
                                class174.aClass154_1233 = class173.aClass154_1233;
                            }
                            else {
                                class172.aClass154_1325 = class173.aClass154_1233;
                            }
                            class173.method2491(2);
                            break;
                        }
                        class174 = class173;
                        class173 = class173.aClass154_1233;
                    }
                }
            }
        }
        if (!b) {
            Class129.method2227(class246_Sub3_Sub4);
        }
    }
    
    static {
        Class99.aBoolean838 = false;
    }
}
