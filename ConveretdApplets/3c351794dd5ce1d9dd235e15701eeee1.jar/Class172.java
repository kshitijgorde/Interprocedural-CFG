// 
// Decompiled by Procyon v0.5.30
// 

final class Class172
{
    static boolean aBoolean1321;
    byte aByte1322;
    short aShort1323;
    Class246_Sub3_Sub3 aClass246_Sub3_Sub3_1324;
    Class154 aClass154_1325;
    Class246_Sub3_Sub5 aClass246_Sub3_Sub5_1326;
    static Interface17[] anInterface17Array1327;
    short aShort1328;
    short aShort1329;
    Class172 aClass172_1330;
    Class246_Sub3_Sub2 aClass246_Sub3_Sub2_1331;
    Class246_Sub3_Sub1 aClass246_Sub3_Sub1_1332;
    Class246_Sub3_Sub3 aClass246_Sub3_Sub3_1333;
    Class246_Sub3_Sub5 aClass246_Sub3_Sub5_1334;
    short aShort1335;
    
    static final void method2542(final boolean b, final Class293 aClass293_3986) {
        try {
            if (Class98_Sub10_Sub9.aBoolean5585) {
                if (aClass293_3986.anObjectArray2253 != null) {
                    final Class293 method3139 = Class246_Sub9.method3139((byte)72, Class187.anInt1450, Class310.anInt2652);
                    if (method3139 != null) {
                        final Class98_Sub21 class98_Sub21 = new Class98_Sub21();
                        class98_Sub21.aClass293_3982 = method3139;
                        class98_Sub21.aClass293_3986 = aClass293_3986;
                        class98_Sub21.anObjectArray3981 = aClass293_3986.anObjectArray2253;
                        Class247.method3144(class98_Sub21);
                    }
                }
                if (!b) {
                    final Class98_Sub11 method3140 = Class246_Sub3_Sub4.method3023(260, Class44.aClass171_380, Class331.aClass117_2811);
                    method3140.aClass98_Sub22_Sub1_3865.writeInt(1571862888, aClass293_3986.anInt2248);
                    method3140.aClass98_Sub22_Sub1_3865.writeShort(Class310.anInt2652, 1571862888);
                    method3140.aClass98_Sub22_Sub1_3865.writeLEShort(aClass293_3986.anInt2302, 17624);
                    method3140.aClass98_Sub22_Sub1_3865.writeShort(aClass293_3986.anInt2300, 1571862888);
                    method3140.aClass98_Sub22_Sub1_3865.writeInt(1571862888, Class187.anInt1450);
                    method3140.aClass98_Sub22_Sub1_3865.writeLEShortA(Class376.anInt3173, 128);
                    Class98_Sub10_Sub29.sendPacket(b, method3140);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lha.A(" + b + ',' + ((aClass293_3986 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2543(final int n) {
        try {
            if (n != 4390) {
                Class172.anInterface17Array1327 = null;
            }
            Class172.anInterface17Array1327 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lha.C(" + n + ')');
        }
    }
    
    Class172(final int n) {
        try {
            this.aByte1322 = (byte)n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lha.<init>(" + n + ')');
        }
    }
    
    final void method2544(final int n) {
        try {
            while (this.aClass154_1325 != null) {
                final Class154 aClass154_1233 = this.aClass154_1325.aClass154_1233;
                this.aClass154_1325.method2491(2);
                this.aClass154_1325 = aClass154_1233;
            }
            if (n != 6730) {
                method2542(false, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lha.B(" + n + ')');
        }
    }
    
    static {
        Class172.aBoolean1321 = false;
    }
}
