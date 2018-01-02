// 
// Decompiled by Procyon v0.5.30
// 

final class Class84
{
    Class218 aClass218_635;
    static String[] aStringArray636;
    boolean aBoolean637;
    
    static final void method832(final String s, final byte b) {
        try {
            Class98_Sub45.method1521((byte)52, 0, s, 0, "", "", "");
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fh.C(" + ((s != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method833(final int n) {
        try {
            while (true) {
                final Class246_Sub1 class246_Sub1 = (Class246_Sub1)this.aClass218_635.method2805((byte)(-72));
                if (class246_Sub1 == null) {
                    break;
                }
                class246_Sub1.method2965((byte)(-96));
                Class35.method333(class246_Sub1, n ^ 0xFFFFFF8A);
            }
            if (n != 0) {
                this.aClass218_635 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fh.B(" + n + ')');
        }
    }
    
    static final void method834(final Class207 class207, final int n) {
        try {
            Class244.anInt1860 = class207.method2750((byte)(-62), "p11_full");
            Class269.anInt2026 = class207.method2750((byte)(-122), "p12_full");
            Class123_Sub1.anInt4742 = class207.method2750((byte)(-91), "b12_full");
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fh.E(" + ((class207 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    public static void method835(final int n) {
        try {
            Class84.aStringArray636 = null;
            if (n != 2169) {
                Class84.aStringArray636 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fh.D(" + n + ')');
        }
    }
    
    final void method836(final int n, final Class246_Sub1 class246_Sub1) {
        try {
            final Class246_Sub3 aClass246_Sub3_5069 = class246_Sub1.aClass246_Sub3_5069;
            boolean b = true;
            final Class246_Sub6[] aClass246_Sub6Array5067 = class246_Sub1.aClass246_Sub6Array5067;
            for (int n2 = n; aClass246_Sub6Array5067.length > n2; ++n2) {
                if (aClass246_Sub6Array5067[n2].aBoolean5114) {
                    b = false;
                    break;
                }
            }
            if (!b) {
                if (this.aBoolean637) {
                    for (Class246_Sub1 class246_Sub2 = (Class246_Sub1)this.aClass218_635.method2803((byte)15); class246_Sub2 != null; class246_Sub2 = (Class246_Sub1)this.aClass218_635.method2809(false)) {
                        if (aClass246_Sub3_5069 == class246_Sub2.aClass246_Sub3_5069) {
                            class246_Sub2.method2965((byte)18);
                            Class35.method333(class246_Sub2, -120);
                        }
                    }
                }
                for (Class246_Sub1 class246_Sub3 = (Class246_Sub1)this.aClass218_635.method2803((byte)15); class246_Sub3 != null; class246_Sub3 = (Class246_Sub1)this.aClass218_635.method2809(false)) {
                    if (class246_Sub3.aClass246_Sub3_5069.anInt5083 <= aClass246_Sub3_5069.anInt5083) {
                        Class151_Sub3.method2458(class246_Sub1, class246_Sub3, (byte)27);
                        return;
                    }
                }
                this.aClass218_635.method2808(true, class246_Sub1);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fh.A(" + n + ',' + ((class246_Sub1 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class84(final boolean aBoolean637) {
        this.aClass218_635 = new Class218();
        this.aBoolean637 = false;
        try {
            this.aBoolean637 = aBoolean637;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "fh.<init>(" + aBoolean637 + ')');
        }
    }
}
