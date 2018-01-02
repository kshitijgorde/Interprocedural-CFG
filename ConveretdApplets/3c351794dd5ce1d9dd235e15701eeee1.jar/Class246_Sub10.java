// 
// Decompiled by Procyon v0.5.30
// 

final class Class246_Sub10 extends Class246
{
    int anInt5151;
    static volatile boolean aBoolean5152;
    static int anInt5153;
    static int anInt5154;
    
    static final byte[] method3140(final byte[] array, final int n) {
        try {
            final int length = array.length;
            final byte[] array2 = new byte[length];
            Class236.method2894(array, n, array2, 0, length);
            return array2;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qe.B(" + ((array != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    static final Class93_Sub3 method3141(final Class98_Sub22 class98_Sub22, final int n) {
        try {
            final Class93 method1716 = Class105.method1716(-1, class98_Sub22);
            return new Class93_Sub3(method1716.aClass63_3509, method1716.aClass110_3511, method1716.anInt3505, method1716.anInt3507, method1716.anInt3514, method1716.anInt3504, method1716.anInt3508, method1716.anInt3506, method1716.anInt3513, class98_Sub22.readInt(-2), class98_Sub22.readInt(-2));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qe.A(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    Class246_Sub10(final int anInt5151) {
        try {
            this.anInt5151 = anInt5151;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "qe.<init>(" + anInt5151 + ')');
        }
    }
    
    static {
        Class246_Sub10.aBoolean5152 = true;
    }
}
