// 
// Decompiled by Procyon v0.5.30
// 

final class Class105 implements Interface15
{
    static byte[][] aByteArrayArray3414;
    static int anInt3415;
    int anInt3416;
    static int anInt3417;
    
    public static void method1714(final int n) {
        try {
            Class105.aByteArrayArray3414 = null;
            if (n != -9) {
                method1714(-127);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gm.D(" + n + ')');
        }
    }
    
    @Override
    public final String toString() {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gm.toString()");
        }
    }
    
    static final boolean method1715(final boolean b, final int n, final int n2) {
        try {
            return !b || ~(n & 0x22) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gm.A(" + b + ',' + n + ',' + n2 + ')');
        }
    }
    
    static final Class93 method1716(final int n, final Class98_Sub22 class98_Sub22) {
        try {
            if (n != -1) {
                Class105.aByteArrayArray3414 = null;
            }
            return new Class93(Class98_Sub46_Sub13_Sub1.method1595(122)[class98_Sub22.readUnsignedByte((byte)(-101))], Class331.method3723(256)[class98_Sub22.readUnsignedByte((byte)(-98))], class98_Sub22.readUShort(false), class98_Sub22.readUShort(false), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127), class98_Sub22.readUShort(false), class98_Sub22.readInt(-2), class98_Sub22.readInt(n ^ 0x1));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gm.B(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final int method1717(final int n, final int n2, final int n3, final int n4, final int n5) {
        try {
            final int n6 = 0xF & n4;
            final int n7 = (~n6 > -9) ? n3 : n2;
            final int n8 = (n5 >= ~n6) ? ((n6 != 12 && n6 != 14) ? n : n3) : n2;
            return (((n6 & 0x2) == 0x0) ? n8 : (-n8)) + ((~(n6 & 0x1) == -1) ? n7 : (-n7));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gm.C(" + n + ',' + n2 + ',' + n3 + ',' + n4 + ',' + n5 + ')');
        }
    }
    
    static final Class326 method1718(final int n, final int n2) {
        try {
            if (~n != -1) {
                if (n != 1) {
                    if (~n == 0xFFFFFFFD) {
                        if (Class278.aFloat2064 == 3.0) {
                            return Class151_Sub7.aClass326_5009;
                        }
                        if (Class278.aFloat2064 == 4.0) {
                            return Class271.aClass326_2033;
                        }
                        if (Class278.aFloat2064 == 6.0) {
                            return Class224.aClass326_1686;
                        }
                        if (Class278.aFloat2064 >= 8.0) {
                            return Class260.aClass326_3263;
                        }
                    }
                }
                else {
                    if (Class278.aFloat2064 == 3.0) {
                        return Class339_Sub1.aClass326_5308;
                    }
                    if (Class278.aFloat2064 == 4.0) {
                        return Class137.aClass326_1080;
                    }
                    if (Class278.aFloat2064 == 6.0) {
                        return Class151_Sub7.aClass326_5009;
                    }
                    if (Class278.aFloat2064 >= 8.0) {
                        return Class271.aClass326_2033;
                    }
                }
            }
            else {
                if (Class278.aFloat2064 == 3.0) {
                    return Class339_Sub1.aClass326_5315;
                }
                if (Class278.aFloat2064 == 4.0) {
                    return Class77_Sub1.aClass326_3805;
                }
                if (Class278.aFloat2064 == 6.0) {
                    return Class339_Sub1.aClass326_5308;
                }
                if (Class278.aFloat2064 >= 8.0) {
                    return Class137.aClass326_1080;
                }
            }
            if (n2 != 5466) {
                method1718(35, 58);
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gm.E(" + n + ',' + n2 + ')');
        }
    }
    
    Class105(final String s, final int anInt3416) {
        try {
            this.anInt3416 = anInt3416;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "gm.<init>(" + ((s != null) ? "{...}" : "null") + ',' + anInt3416 + ')');
        }
    }
}
