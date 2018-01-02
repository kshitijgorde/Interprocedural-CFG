// 
// Decompiled by Procyon v0.5.30
// 

final class Class145
{
    static int anInt1170;
    private int anInt1171;
    static int anInt1172;
    static Class268 aClass268_1173;
    private boolean aBoolean1174;
    static int[] anIntArray1175;
    private boolean aBoolean1176;
    static int[] anIntArray1177;
    private int anInt1178;
    
    static final long method2313(final byte b, final String s) {
        try {
            final int length = s.length();
            long n = 0L;
            if (b > -118) {
                return 54L;
            }
            for (int n2 = 0; ~length < ~n2; ++n2) {
                n = s.charAt(n2) + (n << -470840507) + -n;
            }
            return n;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "k.B(" + b + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2314(final int n) {
        try {
            if (n != -1) {
                method2315(-27, null);
            }
            Class145.aClass268_1173 = null;
            Class145.anIntArray1175 = null;
            Class145.anIntArray1177 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "k.F(" + n + ')');
        }
    }
    
    static final String method2315(final int n, final String s) {
        try {
            if (Class19.aString3448.startsWith("win")) {
                return s + ".dll";
            }
            if (Class19.aString3448.startsWith("linux")) {
                return "lib" + s + ".so";
            }
            if (Class19.aString3448.startsWith("mac")) {
                return "lib" + s + ".dylib";
            }
            return null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "k.E(" + n + ',' + ((s != null) ? "{...}" : "null") + ')');
        }
    }
    
    final boolean method2316(final boolean b) {
        try {
            if (!b) {
                this.method2316(false);
            }
            return this.aBoolean1176;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "k.D(" + b + ')');
        }
    }
    
    final boolean method2317(final boolean b) {
        try {
            if (b) {
                Class145.aClass268_1173 = null;
            }
            return this.aBoolean1174;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "k.A(" + b + ')');
        }
    }
    
    final int method2318(final int n) {
        try {
            if (n != -1) {
                return 72;
            }
            return this.anInt1178;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "k.G(" + n + ')');
        }
    }
    
    final int method2319(final int n) {
        try {
            if (n != 32755) {
                Class145.anIntArray1175 = null;
            }
            return this.anInt1171;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "k.C(" + n + ')');
        }
    }
    
    Class145(final boolean aBoolean1174, final int anInt1178, final int anInt1179, final boolean aBoolean1175) {
        try {
            this.anInt1171 = anInt1179;
            this.aBoolean1174 = aBoolean1174;
            this.aBoolean1176 = aBoolean1175;
            this.anInt1178 = anInt1178;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "k.<init>(" + aBoolean1174 + ',' + anInt1178 + ',' + anInt1179 + ',' + aBoolean1175 + ')');
        }
    }
    
    static {
        Class145.anInt1170 = -1;
        Class145.anInt1172 = 0;
        Class145.anIntArray1177 = new int[2];
    }
}
