// 
// Decompiled by Procyon v0.5.30
// 

final class Class362
{
    static int anInt3090;
    private Class363 aClass363_3091;
    private Class207 aClass207_3092;
    private Class207 aClass207_3093;
    
    static final Class93_Sub1_Sub1 method3924(final int n, final Class98_Sub22 class98_Sub22) {
        try {
            final Class93_Sub1 method3781 = Class338.method3781((byte)94, class98_Sub22);
            final int uShort = class98_Sub22.readUShort(false);
            if (n != 1) {
                Class362.anInt3090 = -67;
            }
            return new Class93_Sub1_Sub1(method3781.aClass63_3509, method3781.aClass110_3511, method3781.anInt3505, method3781.anInt3507, method3781.anInt3514, method3781.anInt3504, method3781.anInt3508, method3781.anInt3506, method3781.anInt3513, method3781.anInt5483, method3781.anInt5480, method3781.anInt5485, method3781.anInt5481, method3781.anInt5478, method3781.anInt5484, uShort);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vu.B(" + n + ',' + ((class98_Sub22 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class362(final Class207 aClass207_3092, final Class207 aClass207_3093) {
        try {
            this.aClass207_3092 = aClass207_3092;
            this.aClass207_3093 = aClass207_3093;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vu.<init>(" + ((aClass207_3092 != null) ? "{...}" : "null") + ',' + ((aClass207_3093 != null) ? "{...}" : "null") + ')');
        }
    }
    
    private final Class363 method3925(final boolean b) {
        try {
            if (this.aClass363_3091 == null) {
                this.aClass363_3091 = new Class363();
            }
            if (b) {
                return null;
            }
            return this.aClass363_3091;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vu.A(" + b + ')');
        }
    }
    
    static final void method3926(final int n, final int anInt4194, final int n2, final int anInt4195, final ha aHa1231, final int anInt4196, final int anInt4197, final int n3) {
        try {
            Class154.aHa1231 = aHa1231;
            Class98_Sub5_Sub3.aClass111_5540 = Class154.aHa1231.method1821();
            Class42_Sub3.aClass111_5364 = Class154.aHa1231.method1821();
            Class200.aClass111_1543 = Class154.aHa1231.method1821();
            Class151.anInt1214 = anInt4197;
            Class31.anInterface17_301 = null;
            Class268.anInt2007 = 0;
            Class98_Sub46.anInt4261 = 1;
            Class98_Sub40.anInt4194 = anInt4194;
            Class98_Sub10_Sub38.anInt5761 = anInt4196;
            Class197.anInt1513 = anInt4195;
            Class76_Sub8.anInt3780 = 0;
            Class287_Sub2.method3391(n3, n2, 2);
            if (n > -112) {
                method3924(42, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vu.D(" + n + ',' + anInt4194 + ',' + n2 + ',' + anInt4195 + ',' + ((aHa1231 != null) ? "{...}" : "null") + ',' + anInt4196 + ',' + anInt4197 + ',' + n3 + ')');
        }
    }
    
    final Interface18 method3927(final byte b, final Interface21 interface21) {
        try {
            if (interface21 == null) {
                return null;
            }
            final Class113 method70 = interface21.method70(30778);
            if (Class100.aClass113_840 == method70) {
                return new Class133((Class163)interface21);
            }
            if (method70 == Class365.aClass113_3109) {
                return new Class334(this.method3925(false), (Class52)interface21);
            }
            if (method70 == Class280.aClass113_2111) {
                return new Class134(this.aClass207_3092, (Class337)interface21);
            }
            if (Class98_Sub10_Sub3.aClass113_5546 == method70) {
                return new Class134_Sub1(this.aClass207_3092, (Class337_Sub1)interface21);
            }
            if (method70 == Class47.aClass113_399) {
                return new Class373_Sub2(this.aClass207_3092, this.aClass207_3093, (Class93_Sub3)interface21);
            }
            if (Class137.aClass113_1078 == method70) {
                return new Class373_Sub3(this.aClass207_3092, this.aClass207_3093, (Class93_Sub2)interface21);
            }
            if (method70 == Class98_Sub44.aClass113_4245) {
                return new PacketParser(this.aClass207_3092, this.aClass207_3093, (Class93_Sub1)interface21);
            }
            if (Class308.aClass113_2582 == method70) {
                return new Class19(this.aClass207_3092, this.aClass207_3093, (Class315)interface21);
            }
            if (Class4.aClass113_80 == method70) {
                return new Class5(this.aClass207_3092, (Class367)interface21);
            }
            if (method70 == Class18.aClass113_210) {
                return new Class373_Sub1_Sub1(this.aClass207_3092, this.aClass207_3093, (Class93_Sub1_Sub1)interface21);
            }
            if (b != 62) {
                this.aClass207_3093 = null;
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "vu.C(" + b + ',' + ((interface21 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class362.anInt3090 = 4;
    }
}
