// 
// Decompiled by Procyon v0.5.30
// 

class Class246
{
    static short[] aShortArray1869;
    static boolean aBoolean1870;
    static int anInt1871;
    static int anInt1872;
    Class246 aClass246_1873;
    Class246 aClass246_1874;
    
    static final void method2962(final boolean b) {
        try {
            Class154.aHa1231 = null;
            Class98_Sub5_Sub3.aClass111_5540 = null;
            Class224_Sub2_Sub1.anInt6141 = -1;
            if (!b) {
                aa_Sub1.anInt3558 = -1;
                Class98_Sub46.anInt4261 = -1;
                Class31.anInterface17_301 = null;
                Class146_Sub2.anIntArray4873 = null;
                Class42_Sub3.aClass111_5364 = null;
                Class109.anInt926 = -1;
                Class200.aClass111_1543 = null;
                Class172.anInterface17Array1327 = null;
                Class266.aClass84_1988.method833(0);
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pha.HB(" + b + ')');
        }
    }
    
    public static void method2963(final int n) {
        try {
            if (n != -1) {
                Class246.aBoolean1870 = true;
            }
            Class246.aShortArray1869 = null;
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pha.JB(" + n + ')');
        }
    }
    
    static final void method2964(final boolean b, final int n) {
        try {
            if (n != -1 && Class246_Sub3_Sub3_Sub1.aBooleanArray6256[n]) {
                Class98_Sub17_Sub1.aClass207_5783.method2754(-53, n);
                Class159.aClass293ArrayArray1252[n] = null;
                Class64_Sub13.aClass293ArrayArray3674[n] = null;
                Class246_Sub3_Sub3_Sub1.aBooleanArray6256[n] = b;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pha.KB(" + b + ',' + n + ')');
        }
    }
    
    final void method2965(final byte b) {
        try {
            if (this.aClass246_1873 != null) {
                this.aClass246_1873.aClass246_1874 = this.aClass246_1874;
                this.aClass246_1874.aClass246_1873 = this.aClass246_1873;
                this.aClass246_1874 = null;
                this.aClass246_1873 = null;
            }
        }
        catch (RuntimeException ex) {
            throw Class64_Sub27.method667(ex, "pha.IB(" + b + ')');
        }
    }
    
    static {
        Class246.aBoolean1870 = false;
        Class246.anInt1872 = 0;
    }
}
