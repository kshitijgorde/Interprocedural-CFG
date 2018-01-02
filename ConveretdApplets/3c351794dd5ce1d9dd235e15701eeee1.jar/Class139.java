// 
// Decompiled by Procyon v0.5.30
// 

final class Class139
{
    int anInt1086;
    static int anInt1087;
    private Class207 aClass207_1088;
    private Class79 aClass79_1089;
    
    final void method2281(final int n) {
        try {
            synchronized (this.aClass79_1089) {
                this.aClass79_1089.method794(4);
                if (n > -102) {
                    this.anInt1086 = 56;
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jj.D(" + n + ')');
        }
    }
    
    final Class167 method2282(final int n, final int n2) {
        try {
            final Class167 class167;
            synchronized (this.aClass79_1089) {
                class167 = (Class167)this.aClass79_1089.method802(-125, n);
            }
            if (class167 != null) {
                return class167;
            }
            final byte[] method2745;
            synchronized (this.aClass207_1088) {
                if (n2 != 16) {
                    this.anInt1086 = 120;
                }
                method2745 = this.aClass207_1088.method2745(n, 16, false);
            }
            final Class167 class168 = new Class167();
            if (method2745 != null) {
                class168.method2527(new Class98_Sub22(method2745), -2);
            }
            synchronized (this.aClass79_1089) {
                this.aClass79_1089.method805(n, class168, (byte)(-80));
            }
            return class168;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jj.C(" + n + ',' + n2 + ')');
        }
    }
    
    final void method2283(final byte b) {
        try {
            if (b <= 9) {
                this.method2282(-33, -125);
            }
            synchronized (this.aClass79_1089) {
                this.aClass79_1089.method806((byte)30);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jj.A(" + b + ')');
        }
    }
    
    final void method2284(final byte b, final int n) {
        try {
            synchronized (this.aClass79_1089) {
                this.aClass79_1089.method800((byte)62, n);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jj.B(" + b + ',' + n + ')');
        }
    }
    
    Class139(final Class279 class279, final int n, final Class207 aClass207_1088) {
        this.aClass79_1089 = new Class79(64);
        try {
            this.aClass207_1088 = aClass207_1088;
            if (this.aClass207_1088 == null) {
                this.anInt1086 = 0;
            }
            else {
                this.anInt1086 = this.aClass207_1088.method2761(0, 16);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "jj.<init>(" + ((class279 != null) ? "{...}" : "null") + ',' + n + ',' + ((aClass207_1088 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class139.anInt1087 = 0;
    }
}
