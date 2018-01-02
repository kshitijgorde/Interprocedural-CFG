// 
// Decompiled by Procyon v0.5.30
// 

final class Class245
{
    static Class207 aClass207_1862;
    private Class218 aClass218_1863;
    static Class207 aClass207_1864;
    static Class338[] aClass338Array1865;
    String aString1866;
    private volatile int anInt1867;
    private Class174 aClass174_1868;
    
    public static void method2955(final byte b) {
        try {
            if (b != 67) {
                Class245.aClass207_1862 = null;
            }
            Class245.aClass207_1862 = null;
            Class245.aClass338Array1865 = null;
            Class245.aClass207_1864 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ph.F(" + b + ')');
        }
    }
    
    final Class246 method2956(final int n) {
        try {
            final Class246 method2803;
            synchronized (this.aClass218_1863) {
                method2803 = this.aClass218_1863.method2803((byte)15);
                method2803.method2965((byte)123);
                --this.anInt1867;
                if (n != 0) {
                    this.method2958((byte)124, null);
                }
            }
            return method2803;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ph.A(" + n + ')');
        }
    }
    
    final void method2957(final Class174 aClass174_1868, final boolean b) {
        try {
            this.aClass174_1868 = aClass174_1868;
            if (b) {
                this.anInt1867 = -25;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ph.D(" + ((aClass174_1868 != null) ? "{...}" : "null") + ',' + b + ')');
        }
    }
    
    final void method2958(final byte b, final Class246_Sub3 class246_Sub3) {
        try {
            class246_Sub3.aBoolean5078 = true;
            synchronized (this.aClass218_1863) {
                this.aClass218_1863.method2808(true, class246_Sub3);
                ++this.anInt1867;
            }
            if (this.aClass174_1868 != null) {
                synchronized (this.aClass174_1868) {
                    this.aClass174_1868.notify();
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ph.B(" + b + ',' + ((class246_Sub3 != null) ? "{...}" : "null") + ')');
        }
    }
    
    final boolean method2959(final int n) {
        try {
            if (n < 113) {
                Class245.aClass338Array1865 = null;
            }
            return this.anInt1867 == 0;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ph.G(" + n + ')');
        }
    }
    
    final void method2960(final Class246_Sub3 class246_Sub3, final int n) {
        try {
            class246_Sub3.aBoolean5078 = false;
            synchronized (this.aClass218_1863) {
                this.aClass218_1863.method2808(true, class246_Sub3);
                ++this.anInt1867;
            }
            if (n != 0) {
                this.method2959(22);
            }
            if (this.aClass174_1868 != null) {
                synchronized (this.aClass174_1868) {
                    this.aClass174_1868.notify();
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ph.E(" + ((class246_Sub3 != null) ? "{...}" : "null") + ',' + n + ')');
        }
    }
    
    final void method2961(final boolean b, final Class246_Sub10 class246_Sub10) {
        try {
            synchronized (this.aClass218_1863) {
                this.aClass218_1863.method2808(b, class246_Sub10);
                ++this.anInt1867;
            }
            if (this.aClass174_1868 != null) {
                synchronized (this.aClass174_1868) {
                    this.aClass174_1868.notify();
                }
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ph.C(" + b + ',' + ((class246_Sub10 != null) ? "{...}" : "null") + ')');
        }
    }
    
    Class245(final String aString1866) {
        this.aClass218_1863 = new Class218();
        try {
            this.aString1866 = aString1866;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "ph.<init>(" + ((aString1866 != null) ? "{...}" : "null") + ')');
        }
    }
    
    static {
        Class245.aClass338Array1865 = new Class338[50];
    }
}
