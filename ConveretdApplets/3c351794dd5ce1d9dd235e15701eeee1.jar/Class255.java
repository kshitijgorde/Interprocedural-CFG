// 
// Decompiled by Procyon v0.5.30
// 

final class Class255 implements Interface1
{
    static OutgoingOpcode aClass171_3206;
    static int[] anIntArray3207;
    static Class293 aClass293_3208;
    static String[] aStringArray3209;
    static int[] anIntArray3210;
    static byte[][] aByteArrayArray3211;
    
    public static void method3191(final byte b) {
        try {
            Class255.anIntArray3210 = null;
            Class255.aStringArray3209 = null;
            if (b != 49) {
                Class255.aByteArrayArray3211 = null;
            }
            Class255.aByteArrayArray3211 = null;
            Class255.anIntArray3207 = null;
            Class255.aClass171_3206 = null;
            Class255.aClass293_3208 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pv.A(" + b + ')');
        }
    }
    
    @Override
    public final String method1(final int n, final long n2, final Class348 class348, final int[] array) {
        try {
            if (class348 == Class151_Sub9.aClass348_5023) {
                return Class98_Sub10_Sub16.aClass29_5620.method302(array[0], 1028602529).method3594((int)n2, (byte)43);
            }
            if (Class77_Sub1.aClass348_3801 == class348 || class348 == Class359.aClass348_3046) {
                return Class98_Sub46_Sub19.aClass205_6068.method2714((int)n2, (byte)(-120)).aString2450;
            }
            if (class348 == Class42_Sub3.aClass348_5363 || class348 == Class98_Sub36.aClass348_4156 || Class151_Sub7.aClass348_5008 == class348) {
                return Class98_Sub10_Sub16.aClass29_5620.method302(array[0], 1028602529).method3594((int)n2, (byte)127);
            }
            if (n != 17438) {
                method3191((byte)34);
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pv.B(" + n + ',' + n2 + ',' + ((class348 != null) ? "{...}" : "null") + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final Class119_Sub1 method3192(final int n, final Class98_Sub22 class98_Sub22) {
        try {
            if (n >= -115) {
                return null;
            }
            return new Class119_Sub1(class98_Sub22.readUShort(false), class98_Sub22.readUShort(false), class98_Sub22.readUShort(false), class98_Sub22.readUShort(false), class98_Sub22.method1186(-125), class98_Sub22.readUnsignedByte((byte)(-100)));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pv.C(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class255.aClass293_3208 = null;
        Class255.aStringArray3209 = new String[100];
        Class255.anIntArray3210 = new int[1000];
        Class255.aClass171_3206 = new OutgoingOpcode(25, 7);
    }
}
