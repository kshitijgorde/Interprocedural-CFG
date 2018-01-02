// 
// Decompiled by Procyon v0.5.30
// 

final class Class210 implements Interface10
{
    private Class124 aClass124_3327;
    private ha aHa3328;
    static int[] anIntArray3329;
    private boolean aBoolean3330;
    private Interface18[] anInterface18Array3331;
    
    @Override
    public final int method25(final int n) {
        try {
            if (n != -24591) {
                return -30;
            }
            return this.aClass124_3327.anInt1014;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nk.D(" + n + ')');
        }
    }
    
    @Override
    public final void method27(final int n) {
        try {
            if (Class265.aHa1974 != this.aHa3328) {
                this.aBoolean3330 = true;
                this.aHa3328 = Class265.aHa1974;
            }
            this.aHa3328.GA(0);
            final Interface18[] anInterface18Array3331 = this.anInterface18Array3331;
            for (int n2 = 0; ~anInterface18Array3331.length < ~n2; ++n2) {
                final Interface18 interface18 = anInterface18Array3331[n2];
                if (interface18 != null) {
                    interface18.method58((byte)(-43));
                }
            }
            if (n != -31295) {
                this.method24(75);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nk.H(" + n + ')');
        }
    }
    
    @Override
    public final void method23(final int n, boolean b) {
        try {
            b = true;
            final Interface18[] anInterface18Array3331 = this.anInterface18Array3331;
            int n2 = 0;
            if (n != 32210) {
                this.aHa3328 = null;
            }
            while (~n2 > ~anInterface18Array3331.length) {
                final Interface18 interface18 = anInterface18Array3331[n2];
                if (interface18 != null) {
                    interface18.method60(b || this.aBoolean3330, (byte)(-124));
                }
                ++n2;
            }
            this.aBoolean3330 = false;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nk.I(" + n + ',' + b + ')');
        }
    }
    
    @Override
    public final boolean method28(final int n, final long n2) {
        try {
            return ~Class343.method3819(-47) <= ~(this.aClass124_3327.anInt1012 + n2);
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nk.N(" + n + ',' + n2 + ')');
        }
    }
    
    @Override
    public final int method26(final int n) {
        try {
            int n2 = 0;
            final Interface18[] anInterface18Array3331 = this.anInterface18Array3331;
            for (int i = 0; i < anInterface18Array3331.length; ++i) {
                final Interface18 interface18 = anInterface18Array3331[i];
                if (interface18 == null || interface18.method59(14017)) {
                    ++n2;
                }
            }
            if (n != -794) {
                this.anInterface18Array3331 = null;
            }
            return n2 * 100 / this.anInterface18Array3331.length;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nk.F(" + n + ')');
        }
    }
    
    @Override
    public final void method24(final int n) {
        try {
            if (n != -26363) {
                this.method23(-118, false);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nk.K(" + n + ')');
        }
    }
    
    Class210(final Class124 aClass124_3327, final Class362 class362) {
        try {
            this.aClass124_3327 = aClass124_3327;
            this.anInterface18Array3331 = new Interface18[this.aClass124_3327.anInterface21Array1013.length];
            for (int n = 0; ~this.anInterface18Array3331.length < ~n; ++n) {
                this.anInterface18Array3331[n] = class362.method3927((byte)62, this.aClass124_3327.anInterface21Array1013[n]);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nk.<init>(" + ((aClass124_3327 != null) ? "{...}" : "null") + ',' + ((class362 != null) ? "{...}" : "null") + ')');
        }
    }
    
    public static void method2773(final int n) {
        try {
            Class210.anIntArray3329 = null;
            if (n != 3) {
                method2773(-119);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "nk.A(" + n + ')');
        }
    }
    
    static {
        Class210.anIntArray3329 = new int[3];
    }
}
