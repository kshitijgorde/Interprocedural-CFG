// 
// Decompiled by Procyon v0.5.30
// 

final class Class335
{
    static OutgoingOpcode aClass171_2812;
    private Class79 aClass79_2813;
    Class207 aClass207_2814;
    private Class207 aClass207_2815;
    static IncomingOpcode aClass58_2816;
    static boolean aBoolean2817;
    Class79 aClass79_2818;
    static int anInt2819;
    
    static final int method3765(final boolean b) {
        try {
            if (b) {
                return -46;
            }
            if (Class98_Sub46.anInt4261 == 1) {
                return Class272.anInt2037;
            }
            return 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ui.G(" + b + ')');
        }
    }
    
    final Class9 method3766(final int n, final byte b) {
        try {
            final Class9 class9;
            synchronized (this.aClass79_2813) {
                class9 = (Class9)this.aClass79_2813.method802(-121, n);
            }
            if (class9 != null) {
                return class9;
            }
            final byte[] method2745;
            synchronized (this.aClass207_2815) {
                method2745 = this.aClass207_2815.method2745(n, 34, false);
            }
            final Class9 class10 = new Class9();
            class10.aClass335_117 = this;
            if (method2745 != null) {
                class10.method192(new Class98_Sub22(method2745), false);
            }
            synchronized (this.aClass79_2813) {
                this.aClass79_2813.method805(n, class10, (byte)(-80));
            }
            return class10;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ui.B(" + n + ',' + b + ')');
        }
    }
    
    final void method3767(final int n, final int n2) {
        try {
            synchronized (this.aClass79_2813) {
                this.aClass79_2813.method800((byte)62, n2);
                if (n != 56) {
                    this.method3768(-12);
                }
            }
            synchronized (this.aClass79_2818) {
                this.aClass79_2818.method800((byte)62, n2);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ui.F(" + n + ',' + n2 + ')');
        }
    }
    
    final void method3768(final int n) {
        try {
            synchronized (this.aClass79_2813) {
                this.aClass79_2813.method806((byte)36);
            }
            synchronized (this.aClass79_2818) {
                if (n != 10673) {
                    this.method3771(65, -107, -65);
                }
                this.aClass79_2818.method806((byte)(-117));
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ui.D(" + n + ')');
        }
    }
    
    public static void method3769(final byte b) {
        try {
            Class335.aClass58_2816 = null;
            Class335.aClass171_2812 = null;
            if (b < 59) {
                Class335.aBoolean2817 = true;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ui.C(" + b + ')');
        }
    }
    
    final void method3770(final int n) {
        try {
            synchronized (this.aClass79_2813) {
                this.aClass79_2813.method794(21);
            }
            synchronized (this.aClass79_2818) {
                this.aClass79_2818.method794(108);
            }
            if (n != 34) {
                this.aClass79_2818 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ui.A(" + n + ')');
        }
    }
    
    final void method3771(final int n, final int n2, final int n3) {
        try {
            this.aClass79_2813 = new Class79(n2);
            if (n < 107) {
                this.method3767(37, 14);
            }
            this.aClass79_2818 = new Class79(n3);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ui.E(" + n + ',' + n2 + ',' + n3 + ')');
        }
    }
    
    Class335(final Class279 class279, final int n, final Class207 aClass207_2815, final Class207 aClass207_2816) {
        this.aClass79_2813 = new Class79(64);
        this.aClass79_2818 = new Class79(64);
        try {
            this.aClass207_2815 = aClass207_2815;
            this.aClass207_2814 = aClass207_2816;
            this.aClass207_2815.method2761(0, 34);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ui.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_2815 != null) ? "{...}" : "null") + ',' + ((aClass207_2816 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class335.aClass171_2812 = new OutgoingOpcode(56, 4);
        Class335.aBoolean2817 = false;
        Class335.aClass58_2816 = new IncomingOpcode(71, 6);
    }
}
