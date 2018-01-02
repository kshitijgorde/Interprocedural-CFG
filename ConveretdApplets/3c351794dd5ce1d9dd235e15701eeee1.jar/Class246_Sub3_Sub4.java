// 
// Decompiled by Procyon v0.5.30
// 

abstract class Class246_Sub3_Sub4 extends Class246_Sub3
{
    short aShort6157;
    short aShort6158;
    short aShort6159;
    short aShort6160;
    byte aByte6161;
    boolean aBoolean6162;
    
    void method3022(final int n) {
        try {
            if (n != -8675) {
                method3023(101, null, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lr.F(" + n + ')');
        }
    }
    
    static final Class98_Sub11 method3023(final int n, final OutgoingOpcode aClass171_3864, final Class117 class117) {
        try {
            final Class98_Sub11 method3410 = Class289.method3410(n - 261);
            method3410.aClass171_3864 = aClass171_3864;
            method3410.anInt3867 = aClass171_3864.size;
            if (~method3410.anInt3867 != 0x0) {
                if (~method3410.anInt3867 != 0x1) {
                    if (method3410.anInt3867 <= 18) {
                        method3410.aClass98_Sub22_Sub1_3865 = new Class98_Sub22_Sub1(20);
                    }
                    else if (method3410.anInt3867 <= 98) {
                        method3410.aClass98_Sub22_Sub1_3865 = new Class98_Sub22_Sub1(100);
                    }
                    else {
                        method3410.aClass98_Sub22_Sub1_3865 = new Class98_Sub22_Sub1(260);
                    }
                }
                else {
                    method3410.aClass98_Sub22_Sub1_3865 = new Class98_Sub22_Sub1(10000);
                }
            }
            else {
                method3410.aClass98_Sub22_Sub1_3865 = new Class98_Sub22_Sub1(260);
            }
            method3410.aClass98_Sub22_Sub1_3865.method1252((byte)(-125), class117);
            method3410.aClass98_Sub22_Sub1_3865.method1261(false, method3410.aClass171_3864.method2541(2));
            method3410.anInt3869 = 0;
            if (n != 260) {
                method3023(-19, null, null);
            }
            return method3410;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lr.BA(" + n + ',' + ((aClass171_3864 != null) ? "{...}" : "null") + ',' + ((class117 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static final Class315 method3024(final Class98_Sub22 class98_Sub22, final boolean b) {
        try {
            final String string = class98_Sub22.readString((byte)84);
            final Class63 class63 = Class98_Sub46_Sub13_Sub1.method1595(122)[class98_Sub22.readUnsignedByte((byte)69)];
            final Class110 class64 = Class331.method3723(256)[class98_Sub22.readUnsignedByte((byte)(-120))];
            final int uShort = class98_Sub22.readUShort(false);
            final int uShort2 = class98_Sub22.readUShort(!b);
            if (!b) {
                return null;
            }
            return new Class315(string, class63, class64, uShort, uShort2, class98_Sub22.readUnsignedByte((byte)106), class98_Sub22.readUnsignedByte((byte)51), class98_Sub22.readUnsignedByte((byte)15), class98_Sub22.readShort((byte)127), class98_Sub22.readShort((byte)127), class98_Sub22.readInt(-2), class98_Sub22.readInt(-2), class98_Sub22.readInt(-2));
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lr.EA(" + ((class98_Sub22 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final int method2980(final int n, final Class98_Sub5[] array) {
        try {
            int i = 0;
        Label_0171:
            for (short aShort6158 = this.aShort6158; aShort6158 <= this.aShort6160; ++aShort6158) {
                for (short aShort6159 = this.aShort6157; aShort6159 <= this.aShort6159; ++aShort6159) {
                    final long n2 = Class373_Sub3.aLongArrayArrayArray5476[super.aByte5088][aShort6158][aShort6159];
                    long n3 = 0L;
                Label_0051:
                    while (n3 <= 48L) {
                        final int n4 = (int)(0xFFFFL & n2 >>> (int)n3);
                        if (n4 <= 0) {
                            break;
                        }
                        final Class1 class1 = Class98_Sub10_Sub31.aClass1Array5717[n4 - 1];
                        for (int n5 = 0; i > n5; ++n5) {
                            if (class1.aClass98_Sub5_55 == array[n5]) {
                                n3 += 16L;
                                continue Label_0051;
                            }
                        }
                        array[i++] = class1.aClass98_Sub5_55;
                        if (i == 4) {
                            break Label_0171;
                        }
                        n3 += 16L;
                    }
                }
            }
            for (int j = i; j < 4; ++j) {
                array[j] = null;
            }
            if (this.aByte6161 != 0) {
                final int n6 = this.aShort6158 - Class241.anInt1845;
                final int n7 = -Class64_Sub26.anInt3714 + this.aShort6157;
                short n8;
                short n9;
                short n10;
                short n11;
                if (~this.aByte6161 != 0xFFFFFFFE) {
                    if (n7 <= -n6) {
                        n8 = this.aShort6158;
                        n9 = this.aShort6157;
                        n10 = (short)(1 + this.aShort6157);
                        n11 = (short)(this.aShort6158 + 1);
                    }
                    else {
                        n11 = (short)(this.aShort6158 - 1);
                        n10 = (short)(this.aShort6157 - 1);
                        n9 = this.aShort6157;
                        n8 = this.aShort6158;
                    }
                }
                else if (n6 < n7) {
                    n11 = (short)(this.aShort6158 + 1);
                    n10 = (short)(-1 + this.aShort6157);
                    n8 = this.aShort6158;
                    n9 = this.aShort6157;
                }
                else {
                    n9 = this.aShort6157;
                    n10 = (short)(this.aShort6157 + 1);
                    n11 = (short)(-1 + this.aShort6158);
                    n8 = this.aShort6158;
                }
                int k = 0;
            Label_0381:
                while (k < i) {
                    long n12 = Class373_Sub3.aLongArrayArrayArray5476[super.aByte5088][n8][n10];
                Label_0556:
                    while (true) {
                        while (~n12 != -1L) {
                            final Class1 class2 = Class98_Sub10_Sub31.aClass1Array5717[(int)((0xFFFFL & n12) - 1L)];
                            n12 >>>= 16;
                            if (class2.aClass98_Sub5_55 == array[k]) {
                                ++k;
                                continue Label_0381;
                            }
                        }
                        long n13 = Class373_Sub3.aLongArrayArrayArray5476[super.aByte5088][n11][n9];
                        while (n13 != 0L) {
                            final Class1 class3 = Class98_Sub10_Sub31.aClass1Array5717[(int)(-1L + (n13 & 0xFFFFL))];
                            n13 >>>= 16;
                            if (array[k] == class3.aClass98_Sub5_55) {
                                continue Label_0556;
                            }
                        }
                        for (int n14 = k; ~(i - 1) < ~n14; ++n14) {
                            array[n14] = array[n14 + 1];
                        }
                        --i;
                        continue Label_0556;
                    }
                }
            }
            return i;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lr.GA(" + n + ',' + ((array != null) ? "{...}" : "null") + ')');
        }
    }
    
    @Override
    final boolean method2977(final ha ha, final byte b) {
        try {
            if (b != 77) {
                this.method2991(false);
            }
            return Class63.method547(this.aShort6158, this.aShort6157, this.method2974((byte)(-53), ha), 0, this.aShort6160, this.aShort6159, super.aByte5081);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lr.AA(" + ((ha != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    @Override
    final boolean method2991(final boolean b) {
        try {
            if (b) {
                this.method2991(true);
            }
            for (short aShort6158 = this.aShort6158; this.aShort6160 >= aShort6158; ++aShort6158) {
                for (short aShort6159 = this.aShort6157; this.aShort6159 >= aShort6159; ++aShort6159) {
                    final int n = Class259.anInt1959 + aShort6158 + -Class241.anInt1845;
                    if (n >= 0 && ~n > ~Class74.aBooleanArrayArray551.length) {
                        final int n2 = -Class64_Sub26.anInt3714 + aShort6159 - -Class259.anInt1959;
                        if (~n2 <= -1 && Class74.aBooleanArrayArray551.length > n2 && Class74.aBooleanArrayArray551[n][n2]) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lr.FA(" + b + ')');
        }
    }
    
    Class246_Sub3_Sub4(final int n, final int n2, final int anInt5084, final int anInt5085, final int anInt5086, final int n3, final int n4, final int n5, final int n6, final boolean aBoolean6162, final byte aByte6161) {
        try {
            this.aShort6158 = (short)n3;
            this.aBoolean6162 = aBoolean6162;
            super.anInt5089 = anInt5085;
            super.aByte5081 = (byte)n2;
            this.aShort6157 = (short)n5;
            this.aByte6161 = aByte6161;
            this.aShort6160 = (short)n4;
            super.anInt5084 = anInt5084;
            super.anInt5079 = anInt5086;
            this.aShort6159 = (short)n6;
            super.aByte5088 = (byte)n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "lr.<init>(" + n + ',' + n2 + ',' + anInt5084 + ',' + anInt5085 + ',' + anInt5086 + ',' + n3 + ',' + n4 + ',' + n5 + ',' + n6 + ',' + aBoolean6162 + ',' + aByte6161 + ')');
        }
    }
}
