// 
// Decompiled by Procyon v0.5.30
// 

final class Class32
{
    static int anInt303;
    static String[] aStringArray304;
    static int anInt305;
    private Class207 aClass207_306;
    static Class246_Sub3[] aClass246_Sub3Array307;
    static int anInt308;
    int anInt309;
    private Class79 aClass79_310;
    static int[] anIntArray311;
    int anInt312;
    
    final void method313(final byte b, final int n) {
        try {
            if (b == 30) {
                synchronized (this.aClass79_310) {
                    this.aClass79_310.method800((byte)62, n);
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cfa.D(" + b + ',' + n + ')');
        }
    }
    
    public static void method314(final int n) {
        try {
            Class32.aStringArray304 = null;
            Class32.anIntArray311 = null;
            Class32.aClass246_Sub3Array307 = null;
            if (n != 0) {
                method316(false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cfa.F(" + n + ')');
        }
    }
    
    final void method315(final byte b) {
        try {
            synchronized (this.aClass79_310) {
                this.aClass79_310.method806((byte)(-118));
            }
            if (b != -46) {
                method318(-103, (byte)73);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cfa.E(" + b + ')');
        }
    }
    
    static final void method316(final boolean b) {
        try {
            OutputStream_Sub1.aClass240_36.method2923(b);
            for (int i = 0; i < 32; ++i) {
                Class89.aLongArray709[i] = 0L;
            }
            for (int n = 0; ~n > -33; ++n) {
                Class271.aLongArray2034[n] = 0L;
            }
            if (b) {
                Class32.anInt308 = 51;
            }
            Class42_Sub1.anInt5356 = 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cfa.A(" + b + ')');
        }
    }
    
    final Class199 method317(final int n, final int anInt1536) {
        try {
            final Class199 class199;
            synchronized (this.aClass79_310) {
                class199 = (Class199)this.aClass79_310.method802(-124, anInt1536);
            }
            if (class199 != null) {
                return class199;
            }
            final byte[] method2745;
            synchronized (this.aClass207_306) {
                method2745 = this.aClass207_306.method2745(anInt1536, n, false);
            }
            final Class199 class200 = new Class199();
            class200.anInt1536 = anInt1536;
            class200.aClass32_1528 = this;
            if (method2745 != null) {
                class200.method2688(n + 107, new Class98_Sub22(method2745));
            }
            class200.method2691((byte)80);
            synchronized (this.aClass79_310) {
                this.aClass79_310.method805(anInt1536, class200, (byte)(-80));
            }
            return class200;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cfa.C(" + n + ',' + anInt1536 + ')');
        }
    }
    
    static final int method318(final int n, final byte b) {
        try {
            if (b > -51) {
                return -16;
            }
            return n & 0x3FF;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cfa.G(" + n + ',' + b + ')');
        }
    }
    
    final void method319(final byte b) {
        try {
            synchronized (this.aClass79_310) {
                this.aClass79_310.method794(92);
                if (b != -117) {
                    this.anInt312 = 123;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cfa.B(" + b + ')');
        }
    }
    
    Class32(final Class279 class279, final int n, final Class207 aClass207_306) {
        this.aClass79_310 = new Class79(64);
        this.anInt312 = 0;
        try {
            this.aClass207_306 = aClass207_306;
            this.anInt309 = this.aClass207_306.method2761(0, 4);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "cfa.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_306 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class32.aStringArray304 = new String[] { "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat" };
        Class32.anIntArray311 = new int[14];
        Class32.anInt305 = 0;
    }
}
