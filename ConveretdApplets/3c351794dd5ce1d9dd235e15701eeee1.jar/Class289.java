// 
// Decompiled by Procyon v0.5.30
// 

final class Class289
{
    static Class354 aClass354_2197;
    
    static final void method3407(final Class246_Sub3 aClass246_Sub3_5069, final Class98_Sub5[] array) {
        if (Class348.aBoolean2914) {
            Class98_Sub10_Sub30.aHa5709.method1818(aClass246_Sub3_5069.method2980(-51, array), array);
        }
        if (Class81.aSArray618 == Class78.aSArray594) {
            int aShort6158;
            int aShort6159;
            if (aClass246_Sub3_5069 instanceof Class246_Sub3_Sub4) {
                aShort6158 = ((Class246_Sub3_Sub4)aClass246_Sub3_5069).aShort6158;
                aShort6159 = ((Class246_Sub3_Sub4)aClass246_Sub3_5069).aShort6157;
            }
            else {
                aShort6158 = aClass246_Sub3_5069.anInt5084 >> Class151_Sub8.anInt5015;
                aShort6159 = aClass246_Sub3_5069.anInt5079 >> Class151_Sub8.anInt5015;
            }
            Class98_Sub10_Sub30.aHa5709.EA(Class98_Sub46_Sub2_Sub2.aSArray6298[0].method3417(aClass246_Sub3_5069.anInt5084, aClass246_Sub3_5069.anInt5079, true), Class313.method3636(aShort6158, aShort6159), IOException_Sub1.method127(aShort6158, aShort6159), Class98_Sub46_Sub20.method1639(aShort6158, aShort6159));
        }
        final Class246_Sub1 method2975 = aClass246_Sub3_5069.method2975(Class98_Sub10_Sub30.aHa5709, -30);
        if (method2975 != null) {
            if (aClass246_Sub3_5069.aBoolean5082) {
                final Class246_Sub6[] aClass246_Sub6Array5067 = method2975.aClass246_Sub6Array5067;
                for (int i = 0; i < aClass246_Sub6Array5067.length; ++i) {
                    final Class246_Sub6 class246_Sub6 = aClass246_Sub6Array5067[i];
                    if (class246_Sub6.aBoolean5114) {
                        Class93_Sub1_Sub1.method908(class246_Sub6.anInt5112 + class246_Sub6.anInt5109, class246_Sub6.anInt5113 - class246_Sub6.anInt5109, false, class246_Sub6.anInt5111 - class246_Sub6.anInt5109, class246_Sub6.anInt5110 + class246_Sub6.anInt5109);
                    }
                }
            }
            if (method2975.aBoolean5070) {
                method2975.aClass246_Sub3_5069 = aClass246_Sub3_5069;
                if (Class375.aBoolean3170) {
                    synchronized (Class98_Sub10_Sub27.aClass84_5692) {
                        Class98_Sub10_Sub27.aClass84_5692.method836(0, method2975);
                    }
                }
                else {
                    Class98_Sub10_Sub27.aClass84_5692.method836(0, method2975);
                }
            }
            else {
                Class35.method333(method2975, 95);
            }
        }
    }
    
    static final void method3408(final byte b) {
        try {
            if (b < 79) {
                method3410(67);
            }
            for (Class98_Sub36 class98_Sub36 = (Class98_Sub36)Class156_Sub1.aClass377_3277.method3998(126); class98_Sub36 != null; class98_Sub36 = (Class98_Sub36)Class156_Sub1.aClass377_3277.method3995(-1)) {
                if (!class98_Sub36.aBoolean4158) {
                    Class98_Sub11.method1127((byte)67, class98_Sub36.anInt4160);
                }
                else {
                    class98_Sub36.aBoolean4158 = false;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rs.A(" + b + ')');
        }
    }
    
    public static void method3409(final byte b) {
        try {
            Class289.aClass354_2197 = null;
            if (b != -117) {
                method3407(null, null);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rs.B(" + b + ')');
        }
    }
    
    static final Class98_Sub11 method3410(final int n) {
        try {
            if (n == ~Class98_Sub33.anInt4117) {
                return new Class98_Sub11();
            }
            return Class98_Sub46_Sub2_Sub2.aClass98_Sub11Array6302[--Class98_Sub33.anInt4117];
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "rs.C(" + n + ')');
        }
    }
}
