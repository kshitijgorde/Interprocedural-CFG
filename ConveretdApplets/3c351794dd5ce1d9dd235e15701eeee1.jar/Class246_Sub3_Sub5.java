// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class246_Sub3_Sub5 extends Class246_Sub3
{
    short aShort6163;
    static OutgoingOpcode aClass171_6164;
    short aShort6165;
    
    @Override
    final void method2992(final byte b) {
        try {
            if (b != -73) {
                Class246_Sub3_Sub5.aClass171_6164 = null;
            }
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wo.DA(" + b + ')');
        }
    }
    
    @Override
    final boolean method2982(final byte b) {
        try {
            if (b > -70) {
                Class246_Sub3_Sub5.aClass171_6164 = null;
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wo.HA(" + b + ')');
        }
    }
    
    @Override
    final void method2981(final Class246_Sub3 class246_Sub3, final byte b, final boolean b2, final int n, final ha ha, final int n2, final int n3) {
        try {
            throw new IllegalStateException();
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wo.CA(" + ((class246_Sub3 != null) ? "{...}" : "null") + ',' + b + ',' + b2 + ',' + n + ',' + ((ha != null) ? "{...}" : "null") + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    public static void method3089(final int n) {
        try {
            if (n != 4) {
                Class246_Sub3_Sub5.aClass171_6164 = null;
            }
            Class246_Sub3_Sub5.aClass171_6164 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wo.R(" + n + ')');
        }
    }
    
    @Override
    final boolean method2977(final ha ha, final byte b) {
        try {
            return b == 77 && Class195.method2661(super.aByte5081, super.anInt5079 >> Class151_Sub8.anInt5015, super.anInt5084 >> Class151_Sub8.anInt5015, this.method2990(b - 77), (byte)(-123));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wo.AA(" + ((ha != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final int method2980(final int n, final Class98_Sub5[] array) {
        try {
            return this.method2989(super.anInt5084 >> Class151_Sub8.anInt5015, false, array, super.anInt5079 >> Class151_Sub8.anInt5015);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wo.GA(" + n + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class246_Sub3_Sub5(final int anInt5084, final int anInt5085, final int anInt5086, final int n, final int n2, final int n3, final int n4) {
        try {
            super.anInt5084 = anInt5084;
            super.aByte5081 = (byte)n2;
            this.aShort6165 = (short)n3;
            this.aShort6163 = (short)n4;
            super.aByte5088 = (byte)n;
            super.anInt5079 = anInt5086;
            super.anInt5089 = anInt5085;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wo.<init>(" + anInt5084 + ',' + anInt5085 + ',' + anInt5086 + ',' + n + ',' + n2 + ',' + n3 + ',' + n4 + ')');
        }
    }
    
    @Override
    final boolean method2991(final boolean b) {
        try {
            return b || Class74.aBooleanArrayArray551[Class259.anInt1959 + -Class241.anInt1845 + (super.anInt5084 >> Class151_Sub8.anInt5015)][Class259.anInt1959 + (super.anInt5079 >> Class151_Sub8.anInt5015) - Class64_Sub26.anInt3714];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "wo.FA(" + b + ')');
        }
    }
    
    static {
        Class246_Sub3_Sub5.aClass171_6164 = new OutgoingOpcode(34, 4);
    }
}
