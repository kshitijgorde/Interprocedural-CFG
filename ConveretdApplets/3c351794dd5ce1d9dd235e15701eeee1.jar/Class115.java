// 
// Decompiled by Procyon v0.5.30
// 

final class Class115
{
    private Class79 aClass79_959;
    static int anInt960;
    static float[] aFloatArray961;
    private Class207 aClass207_962;
    static int anInt963;
    
    final void method2152(final int n) {
        try {
            synchronized (this.aClass79_959) {
                this.aClass79_959.method794(97);
            }
            if (n != 21185) {
                method2155();
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hh.G(" + n + ')');
        }
    }
    
    final void method2153(final int n, final byte b) {
        try {
            synchronized (this.aClass79_959) {
                this.aClass79_959.method800((byte)62, n);
            }
            if (b >= -117) {
                method2154((byte)(-111));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hh.C(" + n + ',' + b + ')');
        }
    }
    
    public static void method2154(final byte b) {
        try {
            if (b == -67) {
                Class115.aFloatArray961 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hh.A(" + b + ')');
        }
    }
    
    static final void method2155() {
        Class98_Sub10_Sub27.aClass84_5692 = Class98_Sub10_Sub27.aClass84_5693;
    }
    
    static final void method2156(final boolean b, final String s, final int n) {
        try {
            Class57.method519(b, s, -1, -1, -1);
            if (n > -61) {
                method2156(false, null, 41);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hh.E(" + b + ',' + ((s != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final Class266 method2157(final int n, final byte b) {
        try {
            final Class266 class266;
            synchronized (this.aClass79_959) {
                class266 = (Class266)this.aClass79_959.method802(-124, n);
            }
            if (class266 != null) {
                return class266;
            }
            if (b != -87) {
                this.aClass207_962 = null;
            }
            final byte[] method2745;
            synchronized (this.aClass207_962) {
                method2745 = this.aClass207_962.method2745(n, 30, false);
            }
            final Class266 class267 = new Class266();
            if (method2745 != null) {
                class267.method3236(new Class98_Sub22(method2745), (byte)(-16));
            }
            synchronized (this.aClass79_959) {
                this.aClass79_959.method805(n, class267, (byte)(-80));
            }
            return class267;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hh.B(" + n + ',' + b + ')');
        }
    }
    
    final void method2158(final boolean b) {
        try {
            if (b) {
                synchronized (this.aClass79_959) {
                    this.aClass79_959.method806((byte)(-104));
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hh.D(" + b + ')');
        }
    }
    
    Class115(final Class279 class279, final int n, final Class207 aClass207_962) {
        this.aClass79_959 = new Class79(16);
        try {
            (this.aClass207_962 = aClass207_962).method2761(0, 30);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "hh.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_962 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class115.aFloatArray961 = new float[4];
        Class115.anInt960 = -1;
        Class115.anInt963 = 0;
    }
}
