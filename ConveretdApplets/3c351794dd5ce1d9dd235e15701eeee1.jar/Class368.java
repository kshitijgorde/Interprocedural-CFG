import java.awt.Component;

// 
// Decompiled by Procyon v0.5.30
// 

final class Class368
{
    String aString3123;
    static int anInt3124;
    String aString3125;
    static Class63 aClass63_3126;
    String aString3127;
    static int anInt3128;
    
    public static void method3949(final byte b) {
        try {
            if (b != 49) {
                method3949((byte)(-9));
            }
            Class368.aClass63_3126 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "we.A(" + b + ')');
        }
    }
    
    static final Class77 method3950(final byte b, final Component component) {
        try {
            if (b != 10) {
                method3950((byte)67, null);
            }
            return new Class77_Sub1(component);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "we.B(" + b + ',' + ((component != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final void method3951(final byte b) {
        try {
            if (Class224_Sub3_Sub1.aBoolean6144) {
                while (~Class98_Sub9.anInt3854 > ~Class98_Sub28_Sub1.aClass53_Sub1Array5805.length) {
                    final Class53_Sub1 class53_Sub1 = Class98_Sub28_Sub1.aClass53_Sub1Array5805[Class98_Sub9.anInt3854];
                    if (class53_Sub1 == null || ~class53_Sub1.anInt3631 != 0x0) {
                        ++Class98_Sub9.anInt3854;
                    }
                    else {
                        if (Class220.aClass98_Sub4_1657 == null) {
                            Class220.aClass98_Sub4_1657 = Class48_Sub2_Sub1.aClass265_5531.method3229(90, class53_Sub1.aString3634);
                        }
                        final int anInt3827 = Class220.aClass98_Sub4_1657.anInt3827;
                        if (anInt3827 == -1) {
                            break;
                        }
                        Class220.aClass98_Sub4_1657 = null;
                        ++Class98_Sub9.anInt3854;
                        class53_Sub1.anInt3631 = anInt3827;
                    }
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "we.C(" + b + ')');
        }
    }
    
    Class368(final String aString3127, final String aString3128, final String aString3129) {
        try {
            this.aString3125 = aString3129;
            this.aString3127 = aString3127;
            this.aString3123 = aString3128;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "we.<init>(" + ((aString3127 != null) ? "{...}" : "null") + ',' + ((aString3128 != null) ? "{...}" : "null") + ',' + ((aString3129 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class368.anInt3124 = 0;
        Class368.aClass63_3126 = new Class63();
        Class368.anInt3128 = -1;
    }
}
