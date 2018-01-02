// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class246_Sub3_Sub1 extends Class246_Sub3
{
    static OutgoingOpcode aClass171_6148;
    short aShort6149;
    static Class121 aClass121_6150;
    
    @Override
    final int method2980(final int n, final Class98_Sub5[] array) {
        try {
            return this.method2989(super.anInt5084 >> Class151_Sub8.anInt5015, false, array, super.anInt5079 >> Class151_Sub8.anInt5015);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hc.GA(" + n + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean method2977(final ha ha, final byte b) {
        try {
            return b != 77 || Class76_Sub5.method758((byte)62, super.aByte5081, super.anInt5079 >> Class151_Sub8.anInt5015, super.anInt5084 >> Class151_Sub8.anInt5015);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hc.AA(" + ((ha != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    public static void method2993(final int n) {
        try {
            Class246_Sub3_Sub1.aClass171_6148 = null;
            if (n == 288450632) {
                Class246_Sub3_Sub1.aClass121_6150 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hc.F(" + n + ')');
        }
    }
    
    static final void method2994(final int anInt1169, int n, final byte b, final int n2) {
        try {
            n = n * Class98_Sub9.aClass98_Sub27_3856.aClass64_Sub22_4069.method641((byte)126) >> 288450632;
            if (b != -83) {
                method2995(-90, 54, -126, null, null);
            }
            Label_0113: {
                if (~anInt1169 == 0x0 && !Class151_Sub5.aBoolean4991) {
                    Class337_Sub1.method3777(31585);
                    if (!client.aBoolean3553) {
                        break Label_0113;
                    }
                }
                if (~anInt1169 != 0x0 && (~Class144.anInt1169 != ~anInt1169 || !Class8.method188(false)) && n != 0 && !Class151_Sub5.aBoolean4991) {
                    Class64_Sub29.method674(n, 0, anInt1169, Class98_Sub10_Sub1.aClass207_5544, n2, false, (byte)(-86));
                    Class233.method2883((byte)111);
                }
            }
            if (~Class144.anInt1169 != ~anInt1169) {
                Class151_Sub8.aClass98_Sub31_Sub2_5013 = null;
            }
            Class144.anInt1169 = anInt1169;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hc.D(" + anInt1169 + ',' + n + ',' + b + ',' + n2 + ')');
        }
    }
    
    Class246_Sub3_Sub1(final int anInt5084, final int anInt5085, final int anInt5086, final int n, final int n2, final int n3) {
        try {
            this.aShort6149 = (short)n3;
            super.anInt5084 = anInt5084;
            super.aByte5081 = (byte)n2;
            super.aByte5088 = (byte)n;
            super.anInt5089 = anInt5085;
            super.anInt5079 = anInt5086;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hc.<init>(" + anInt5084 + ',' + anInt5085 + ',' + anInt5086 + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    @Override
    final boolean method2991(final boolean b) {
        try {
            if (b) {
                Class246_Sub3_Sub1.aClass171_6148 = null;
            }
            return Class74.aBooleanArrayArray551[Class259.anInt1959 + (super.anInt5084 >> Class151_Sub8.anInt5015) - Class241.anInt1845][-Class64_Sub26.anInt3714 + (super.anInt5079 >> Class151_Sub8.anInt5015) + Class259.anInt1959];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hc.FA(" + b + ')');
        }
    }
    
    static final void method2995(final int n, final int n2, final int n3, final Class246_Sub3_Sub5 aClass246_Sub3_Sub5_1334, final Class246_Sub3_Sub5 aClass246_Sub3_Sub5_1335) {
        final Class172 method1693 = Class100.method1693(n, n2, n3);
        if (method1693 != null) {
            method1693.aClass246_Sub3_Sub5_1334 = aClass246_Sub3_Sub5_1334;
            method1693.aClass246_Sub3_Sub5_1326 = aClass246_Sub3_Sub5_1335;
            final int n4 = (Class78.aSArray594 == Class81.aSArray618) ? 1 : 0;
            if (aClass246_Sub3_Sub5_1334.method2978(-124)) {
                if (aClass246_Sub3_Sub5_1334.method2987(6540)) {
                    aClass246_Sub3_Sub5_1334.aClass246_Sub3_5090 = Class359.aClass246_Sub3Array3056[n4];
                    Class359.aClass246_Sub3Array3056[n4] = aClass246_Sub3_Sub5_1334;
                }
                else {
                    aClass246_Sub3_Sub5_1334.aClass246_Sub3_5090 = Class379.aClass246_Sub3Array3198[n4];
                    Class379.aClass246_Sub3Array3198[n4] = aClass246_Sub3_Sub5_1334;
                    Class358.aBoolean3033 = true;
                }
            }
            else {
                aClass246_Sub3_Sub5_1334.aClass246_Sub3_5090 = Class130.aClass246_Sub3Array1029[n4];
                Class130.aClass246_Sub3Array1029[n4] = aClass246_Sub3_Sub5_1334;
            }
            if (aClass246_Sub3_Sub5_1335 != null) {
                if (aClass246_Sub3_Sub5_1335.method2978(-7)) {
                    if (aClass246_Sub3_Sub5_1335.method2987(6540)) {
                        aClass246_Sub3_Sub5_1335.aClass246_Sub3_5090 = Class359.aClass246_Sub3Array3056[n4];
                        Class359.aClass246_Sub3Array3056[n4] = aClass246_Sub3_Sub5_1335;
                    }
                    else {
                        aClass246_Sub3_Sub5_1335.aClass246_Sub3_5090 = Class379.aClass246_Sub3Array3198[n4];
                        Class379.aClass246_Sub3Array3198[n4] = aClass246_Sub3_Sub5_1335;
                        Class358.aBoolean3033 = true;
                    }
                }
                else {
                    aClass246_Sub3_Sub5_1335.aClass246_Sub3_5090 = Class130.aClass246_Sub3Array1029[n4];
                    Class130.aClass246_Sub3Array1029[n4] = aClass246_Sub3_Sub5_1335;
                }
            }
        }
    }
    
    static {
        Class246_Sub3_Sub1.aClass171_6148 = new OutgoingOpcode(61, -1);
    }
}
