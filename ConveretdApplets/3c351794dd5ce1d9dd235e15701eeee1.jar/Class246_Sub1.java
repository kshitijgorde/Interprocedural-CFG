// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub1 extends Class246
{
    Class246_Sub6[] aClass246_Sub6Array5067;
    static OutgoingOpcode aClass171_5068;
    Class246_Sub3 aClass246_Sub3_5069;
    boolean aBoolean5070;
    
    static final void method2966(final int n) {
        try {
            if (n == 66) {
                for (Class98_Sub46_Sub3 class98_Sub46_Sub3 = (Class98_Sub46_Sub3)Class98_Sub10_Sub11.aClass148_5596.method2418(32); class98_Sub46_Sub3 != null; class98_Sub46_Sub3 = (Class98_Sub46_Sub3)Class98_Sub10_Sub11.aClass148_5596.method2417(n ^ 0x39)) {
                    final Class246_Sub3_Sub4_Sub3 aClass246_Sub3_Sub4_Sub3_5954 = class98_Sub46_Sub3.aClass246_Sub3_Sub4_Sub3_5954;
                    if (aClass246_Sub3_Sub4_Sub3_5954.aBoolean6450) {
                        class98_Sub46_Sub3.method942(48);
                        aClass246_Sub3_Sub4_Sub3_5954.method3067(120);
                    }
                    else if (~aClass246_Sub3_Sub4_Sub3_5954.anInt6445 >= ~Class215.anInt1614) {
                        aClass246_Sub3_Sub4_Sub3_5954.method3073((byte)31, Class279.anInt2099);
                        if (aClass246_Sub3_Sub4_Sub3_5954.aBoolean6450) {
                            class98_Sub46_Sub3.method942(67);
                        }
                        else {
                            Class222.method2826(aClass246_Sub3_Sub4_Sub3_5954, true);
                        }
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bd.B(" + n + ')');
        }
    }
    
    static final boolean method2967(final int n, final int n2, final byte b) {
        try {
            return b != 91 || ~(n2 & 0x10000) != -1;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bd.C(" + n + ',' + n2 + ',' + b + ')');
        }
    }
    
    public static void method2968(final byte b) {
        try {
            Class246_Sub1.aClass171_5068 = null;
            if (b >= -76) {
                method2968((byte)(-4));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bd.A(" + b + ')');
        }
    }
    
    final boolean method2969(final ha ha, final int n, final int n2, final int n3) {
        try {
            if (n3 > -51) {
                method2967(117, -111, (byte)(-10));
            }
            final int method2986 = this.aClass246_Sub3_5069.method2986(-14240);
            if (this.aClass246_Sub6Array5067 != null) {
                for (int i = 0; i < this.aClass246_Sub6Array5067.length; ++i) {
                    final Class246_Sub6 class246_Sub6 = this.aClass246_Sub6Array5067[i];
                    class246_Sub6.anInt5109 <<= method2986;
                    if (this.aClass246_Sub6Array5067[i].method3130(n, n2) && this.aClass246_Sub3_5069.method2976(n, ha, (byte)79, n2)) {
                        final Class246_Sub6 class246_Sub7 = this.aClass246_Sub6Array5067[i];
                        class246_Sub7.anInt5109 >>= method2986;
                        return true;
                    }
                    final Class246_Sub6 class246_Sub8 = this.aClass246_Sub6Array5067[i];
                    class246_Sub8.anInt5109 >>= method2986;
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "bd.D(" + ((ha != null) ? "{...}" : "null") + ',' + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    static {
        Class246_Sub1.aClass171_5068 = new OutgoingOpcode(66, -1);
    }
}
